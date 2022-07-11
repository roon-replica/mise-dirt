### 기획 아이디어
- [X] 현재 미세먼지 측정치
- [X] 미세먼지 수치(PM 값)에 따라 색상 표시하기

- [ ] 미세먼지 예보 알려주기
- [ ] 도시별 미세먼지 정보 선택해서 볼 수 있도록 하기

### 사용한 공공 API
- https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861

### 기술 레퍼런스
- [spring @Value 사용법](https://www.baeldung.com/spring-value-annotation)
- [thymeleaf로 html class 동적으로 추가하기 ](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=writer0713&logNo=221465984855)
- [기존 테이블에 외래키 추가하기](https://hoho325.tistory.com/62)
- [테이블 DDL 확인하기](https://stackoverflow.com/questions/201621/how-do-i-see-all-foreign-keys-to-a-table-or-column)
- spring security form login
  - https://spring.io/guides/gs/securing-web/
  - https://www.baeldung.com/spring-security-login

- spring security with JWT
  - [spring security with JWT for REST API tutorial](https://www.toptal.com/spring/spring-security-tutorial)
  - [how to decode jwt in java](https://www.baeldung.com/java-jwt-token-decode)
  - [jwt decoder online](https://jwt.io/)

### TODO
- [ ] 캐시 적용
  - [x] 로컬 캐시
  - [ ] 다른 캐시(memcached?) 적용해보기
  - [ ] LRU 방식의 캐시 직접 만들어보기
    - https://www.baeldung.com/java-lru-cache
- [ ] API 호출 비동기로 변경
- [ ] circuit breaker, retry 적용
- [ ] encoded auth key 사용하도록 변경 필요
- [x] 프로퍼티 파일 여러개 설정하기
  - https://blog.jiniworld.me/81

- [x] 미세먼지 API 불러올 때 마다 DB에 저장해보기
- [ ] 도시별 미세먼지 정보 선택할 수 있도록 하기
- [ ] 페이지네이션
  
- [ ] nginx 연결하기
- [ ] aws에 배포해보기
- [ ] 로그인 구현하기
  - [x] form 로그인
  - [ ] 소셜 로그인
  
- [ ] remember me
  
- [x] override default cache-control header
  
- [ ] custom logout

- [ ] 회원가입 구현
  - [x] 회원 가입
    - [x] validate DTO
  - [ ] 회원 정보 수정
  - [ ] 탈퇴
  
- [ ] 보안적 이슈 없는지 (csrf, xss)
- [ ] JWT 적용해서 API 인증된 사람만 호출할 수 있도록 하기
   - [x] validate jwt token
   - [x] generate jwt token
   - [ ] UI / UX
- [x] 미세먼지 pm값에 따라 화면에 표시할 때 색상 표시하기
- [ ] OPEN API 서버가 터졌을 때 대응 정책
  1. 캐시된거 보여주기
  2. cache 초기화되면 DB에 있는 가장 최근거 보여주기


- [ ] 예외 처리
  - [ ] OPEN API가 응답하지 않을 때
  

### 생각해 볼 사항들
- [ ] pm 색상 설정하는 로직 개선할 수 없을지...
- [ ] m1 맥에는 mysql workbench 설치 안 되는 듯...
- [ ] 지역 테이블이 미세먼지 테이블의 외래키를 가지고 있어야 하나? 아니면 반대여야 하나?
