chap 5 실습 [EC2 수동 배포해보기](http://jojoldu.tistory.com/263)

한 달 동안만 무료로 쓸 수 있다고 해서 사용하지 않을 때는 인스턴스를 중지했다가 재 시작한다.(구축하는 동안)

## EC2에 Git 설치 및 프로젝트 Clone

### 1. Java 17 설치

```bash
sudo yum install -y java-17-amazon-corretto-devel.x86_64
```

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/0b4a86d2-362f-4cf5-a15f-93e16219bdf6) width="80%" height="80%"/><br>

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/16eab0b6-f1e8-4138-899e-f350e5ba6fb1) width="80%" height="80%"/><br>

### 2. Git 설치 및 clone

```bash
sudo yum install git
```

설치가 완료되면 설치 상태 확인

```bash
git --version
```

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/09be96ab-e453-4d15-83a4-b75444075c5b) width="60%" height="60%"/><br>

git clone으로 프로젝트 저장할 디렉토리 생성

```bash
mkdir app
mkdir app/git
```

https 주소 복사

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/a42bf172-a576-425a-9bc3-3517adcab5e0) width="80%" height="80%"/><br>

복사한 주소를 통해 `git clone` 진행

```bash
git clone https://github.com/프로젝트주소.git
```

`ll`으로 파일이 잘 복사되었는지 확인

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/77ac3d46-1561-44ab-a223-beab98d39450) width="80%" height="80%"/><br>

프로젝트가 잘 받아졌는지 확인하기 위해 테스트 수행

```bash
./gradlew test
```

permission denied가 나왔으므로 권한을 준 뒤에 실행한다

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/86d3f902-f51b-4fe0-bcbe-0b7815adfdec) width="80%" height="80%"/><br>

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/3ed67a92-0e0e-48de-9219-34e1e0848ad2) width="80%" height="80%"/><br>

빌드 성공 확인

## 배포 스크립트 생성

배포는 다음 과정을 모두 합친 뜻이라고 보면 된다.

- git clone 혹은 git pull을 통해 새 버전의 프로젝트 받음
- Gradle / Maven을 통해 Test & Build
- EC2 서버에서 해당 프로젝트 실행 및 재실행

배포 마다 개발자가 하나하나 명령어를 실행하는 것은 불편함이 많다. 이를 쉘 스크립트로 작성해 스크립트만 실행하면 위 과정이 차례대로 진행되도록 하자

### deploy 스크립트 생성

###### ~/app/git/deploy.sh 

```bash

#!/bin/bash

REPOSITORY=/home/ec2-user/app/git

cd $REPOSITORY/resize-markdown-img-2

echo "> Git Pull"

git pull

echo "> 프로젝트 Build 시작"

./gradlew build

echo "> Build 파일 복사"

cp ./build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f resize-markdown-img-2)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -2 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
fi

echo ">새 애플리케이션 배포"

JAR_NAME=$(ls $REPOSITORY/ |grep 'resize-markdown-img-2' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohub java -jar $REPOSITORY/$JAR_NAME &

```

- `REPOSITORY=/home/ec2-user/app/git`
    - 프로젝트 디렉토리 주소는 스크립트 내에서 자주 사용하기에 이를 변수로 저장한다.
    - `$변수명`으로 변수를 사용할 수 있다.
- `cd $REPOSITORY/resize-markdown-img-2`
    - 제일 처음 git clone 받았던 디렉토리로 이동
    - `/home/ec2-user/app/git`에 `/resize-markdown-img-2` 붙인 디렉토리 주소로 이동한다.
- `git pull`
    - 디렉토리 이동 후, main 브랜치의 최신 내용을 받는다.
- `./gradlew build`
    - 프로젝트 내부의 `gradlew`로 build를 수행한다.
- `cp ./build/libs/*.jar $REPOSITORY/`
    - build의 결과물인 jar 파일을 복사해 jar파일을 모아둔 위치로 복사한다.
- `CURRENT_PID=$(pgrep -f resize-markdown-img-2)`
    - 기존에 수행중이던 스프링부트 애플리케이션을 종료한다.
    - `pgrep`: process id만 추출한다.
    - `-f`: 프로세스 이름으로 찾는다.
- `if ~ else ~ fi`
    - 현재 구동 중인 프로세스가 있는지 없는지를 판단해서 기능을 수행한다.
    - process id 값을 보고 프로세스가 있으면 해당 프로세스를 종료한다.
- `JAR_NAME=$(ls $REPOSITORY/ |grep 'resize-markdown-img-2' | tail -n 1)`
    - 새로 실행할 jar 파일명을 찾는다.
    - 여러 jar 파일이 생기기 때문에 `tail -n`으로 가장 나중에 jar 파일을 변수에 저장한다.
- `nohup java -jar $REPOSITORY/$JAR_NAME &`
    - 찾은 jar 파일명으로 해당 jar 파일을 `nohup`으로 실행시킨다.
    - 스프링 부트의 장점으로 외장 톰캣을 설치할 필요가 없다. 내장 톰캣을 사용해서 jar 파일만 있으면 바로 웹 애플리케이션 서버를 실행할 수 있다.

프로세스가 잘 실행되는지 확인하자.

```bash
ps -ef|grep resize-markdown-img-2
```

<img src=https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/78b72fc0-828c-46f1-8fbf-ddd0452bc4a1) width="80%" height="80%"/><br>

## 외부에서 서비스 접속

EC2의 스프링 부트는 8080으로 실행되었다.

그러면 우리의 EC2 인스턴스도 8080포트가 외부에서 접근 가능하도록 열려있어야 한다.

EC2 대시보드 → 보안 그룹 → 현재 프로젝트 인스턴스 선택 → 인바운드 규칙

![image](https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/08a10f38-4291-47f3-9eb3-62b4e476c12f)

인바운드 규칙 편집 버튼을 클릭하고 다음과 같이 사용자 지정, 8080 포트를 추가한다.

![image](https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/2cf52d36-4394-4a31-b761-e5d42f7a8377)

퍼블릭 DNS로 브라우저에 접근하자.

![image](https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/901f3c4c-81d9-4ba7-8c09-2d52339df0a1)

스프링 부트가 8080 포트로 열려있기 때문에 퍼블릭 DNS 뒤에 8080 포트를 붙여 접근한다. 페이지를 만들지 않아서 그런가..? 사이트에 연결은 되지 않는다.
페이지를 만들고 나서 다시 실행해보자.

![image](https://github.com/muyaaho/resize-markdown-img-2/assets/76798969/f50367bf-3bb9-4281-acdb-2c3fc279afb8)
