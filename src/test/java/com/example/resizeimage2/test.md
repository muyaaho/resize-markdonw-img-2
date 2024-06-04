## 안녕하세요

![image](https://github.com/muyaaho/http-basic/assets/76798969/e37dd643-9cb8-4f5d-8427-d7ce36202992)



당신은 굿이에요


![image](https://github.com/muyaaho/http-basic/assets/76798969/a85df2cf-95e4-4ae1-8a09-ea83b0d9c9b1)


- 서블릿을 지원하는 WAS 사용

  ![image](https://github.com/muyaaho/spring-basic/assets/76798969/a112e369-6ae2-4a7e-b107-f439670e2475)

### 스레드 풀

#### 요청마다 스레드 생성의 단점 보완

- 특징

  ![image](https://github.com/muyaaho/spring-basic/assets/76798969/c3511405-9e55-482e-8b64-ccd929f83480)

    - 필요한 스레드를 스레드 풀에 보관하고 관리한다.
    - 스레드 풀에 생성할 수 있는 스레드의 최대치를 관리한다. 톰캣은 최대 200개 기본 설정
- 사용
    - 스레드가 필요하면, 이미 생성되어 있는 스레드를 스레드 풀에서 꺼내 사용한다.
    - 사용을 종료하면 스레드 풀에 해당 스레드를 반납한다.
    - 최대 스레드가 모두 사용 중이어서 스레드 풀에 스레드가 없으면?

      ![image](https://github.com/muyaaho/spring-basic/assets/76798969/393acb13-4f18-49b3-8d3b-4a7adcfcfe29)

        - 기다리는 요청은 거절하거나 특정 숫자만큼 대기하도록 설정할 수 있다.
- 장점
    - 스레드가 미리 생성되어 있으므로, 스레드를 생성하고 종료하는 비용이 절약되고 응답시간이 빠르다.
    - 생성할 수 있는 스레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다.