### 기획 아이디어
- 현재 미세먼지 측정치 + 미세먼지 예보 알려주기
- 도시별 미세먼지 정보 선택해서 볼 수 있도록 하기
- 미세먼지 수치(PM 값)에 따라 색상 표시하기

### 사용한 공공 API
- https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861

### 기술 레퍼런스
- https://www.baeldung.com/spring-value-annotation
  

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

- [ ] 미세먼지 API 불러올 때 마다 DB에 저장해보기
- [ ] aws에 배포해보기
- [ ] 로그인 구현하기
- [ ] JWT 적용해서 API 인증된 사람만 호출할 수 있도록 하기
- [ ] 미세먼지 pm값에 따라 화면에 표시할 때 색상 표시하기
- [ ] OPEN API 서버가 터졌을 때 대응 정책
  1. 캐시된거 보여주기
  2. cache 초기화되면 DB에 있는 가장 최근거 보여주기