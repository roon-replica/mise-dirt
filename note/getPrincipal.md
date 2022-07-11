### 문제
현재 사용자 정보를 얻으려고 함

근데 user의 principal을 얻어도 password는 memory에 저장하지 않아서 null로 나옴

그래서 userDetailsService 관련 클래스를 통해 loadUserByUsername 메서드를 이용해야 함..

근데 이렇게 하는게 정상적인가? 

비효율적인것 같음

게다가 DB에 저장된 password가 encrypt되어 있어서 authentication을 얻을 수 없음..


### 해결
securityContext에서 정보 얻는 방식으로 해결함.

```java
  SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
  return securityContext.getAuthentication();
```