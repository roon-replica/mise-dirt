인증을 JWT 쓰는 방식으로 바꿀 때 까다로운 부분들

- jwt를 어떻게 클라이언트로 전달할 것인지
  > PrintWriter로 화면에 쓰기

- 클라이언트가 jwt를 어떻게 가지고 있을지?

- JWT로 보호하는 API를 요청할 때 jwt 검사
  > AOP로?
  > 

- 현재 로그인 상태면 jwt 발급이 가능하도록 되어있는데
  
  url에 파라미터 따로 안 붙여도 자동으로 발급되도록 해야 함
  > 파라미터 붙여서 redirect?


