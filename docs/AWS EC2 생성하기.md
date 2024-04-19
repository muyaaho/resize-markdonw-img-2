chap 4 실습 
4. [AWS EC2 & AWS RDS 운영 환경 구축](http://jojoldu.tistory.com/259)

## aws ec2 생성하기

### 1. 인스턴스 생성

region 서울로 변경

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/5851d4f6-74d2-4fa5-a2cc-472a8814eeb1) width="80%" height="80%"/><br>

ec2 검색해서 ec2 대시보드 이동

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/144b6d5d-bd46-4ed9-98ca-5f4318f8bdcf) width="80%" height="80%"/><br>


인스턴스 시작

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/fb0d3fee-3928-47a7-8696-e59af049280d) width="60%" height="60%"/><br>

빠른 시작 - Amazone Linux

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/94b7b0e7-0c3e-4d71-b0f8-e6a0b3a06da2) width="60%" height="60%"/><br>

기본 선택된 인스턴스 유형 사용

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/89b76e31-8115-4b9d-baab-b4f35c75fbab) width="60%" height="60%"/><br>

네트워크 설정, 본인 ip 변경

인터넷에서 HTTPS 트래픽 허용, 인터넷에서 HTTP 트래픽 허용을 체크하고 편집 버튼을 누르면 아래와 같이 나타난다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/9afd7032-bedd-4084-8846-7db301651f8a) width="60%" height="60%"/><br>

보안 그룹 이름, 설명을 변경한다.

처음 규칙의 유형은 ssh, 소스 유형은 본인 ip

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/d3658a72-7230-40a6-ba82-30c4ee843135) width="60%" height="60%"/><br>

나머지는 그대로 둔다. 2, 3 규칙은 외부에서 웹서비스에 접근할 때 사용하는 유형이다. 이걸 닫으면 외부에서 서비스에 접근하지 못한다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/9d6787fd-f14d-4569-abdf-e68418c7cd77) width="60%" height="60%"/><br>

스토리지 구성, 30GB까지 무료이므로 30GB으로 변경한다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/d7b801df-92bc-4301-a516-7650d25afe9d) width="60%" height="60%"/><br>

새 키 페어 생성

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/a83134b5-3024-40d7-bb87-92a284713b28) width="60%" height="60%"/><br>

pem 파일이 다운로드 된다. 이 키페어 파일을 갖고 있어야 해당 인스턴스에 접근이 가능하다. 잘 보관해야 한다. 

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/543ace7f-f3ac-4e22-b283-0f4b76d05b84) width="60%" height="60%"/><br>

다음으로 인스턴스를 시작한다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/3c3c86d9-1835-4bc8-8aa8-1526b58a9238) width="60%" height="60%"/><br>

모든 인스턴스 보기 버튼을 누르면 생성된 인스턴스 정보가 나온다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/c1f50c00-6c6c-4d89-99c9-6d52cf24ca79) width="60%" height="60%"/><br>

이렇게 두면 EC2 인스턴스에 고정 IP가 할당되지 않아 재시작 할 때마다 새 IP가 할당된다고 한다. 고정 IP를 할당해보자.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/10ef1fd7-6343-40b8-9b02-b464e7e8cd5f) width="80%" height="80%"/><br>

탄력적 IP 선택

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/08433b4b-4be9-4b3d-82f8-5d0ade485640) width="80%" height="80%"/><br>

탄력적 IP 주소 할당

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/5a8e2738-18b6-4426-b084-c7122f36da21) width="60%" height="60%"/><br>

할당

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/11bb14cc-b495-42dd-bab0-9f63899f4d94) width="60%" height="60%"/><br>

탄력적 IP 주소 연결

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/797d8973-77fe-4d6d-a3ad-f8138376b99e) width="60%" height="60%"/><br>

방금 생성한 인스턴스 선택, 본인 ip 주소 연결

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/903d7cf7-6fa0-43e4-ae0f-a25546dabed4) width="60%" height="60%"/><br>

인스턴스 탭에서 퍼블릭 IPv4 주소가 아까 만든 고정 IP인지 확인

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/bf982ba3-9acc-4b4f-9b2f-bfc147034401) width="60%" height="60%"/><br>

### EC2 터미널 접속

mobaXterm으로 ssh에 접속하겠습니다. https://minjii-ya.tistory.com/23

mobaXterm 설치

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/f7036a0c-ca5d-4b0f-9cc7-7b3fd758f959) width="50%" height="50%"/><br>

session 선택

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/146d3c77-610e-4511-aa6e-fe67e77040fa) width="80%" height="80%"/><br>

1. ssh 버튼 클릭
2. Remote host: 아까 만든 탄력적 IP
3. Specify username 체크 → `ec2-user` 입력
4. Advanced SSH setting 선택
5. Use private key 선택 → 저장한 pem 경로

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/040318bb-6663-4c1a-8379-783a48a766e3) width="80%" height="80%"/><br>

예쁜 Amazone linux에 잘 접속된다.

<img src=https://github.com/muyaaho/resize-markdonw-img-2/assets/76798969/9a695061-a1d3-4cca-b55d-d64fc9267081) width="80%" height="80%"/><br>

데이터베이스는 사용하지 않을 것 같아 RDS 설정은 하지 않았다.
