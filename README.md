### 기획 아이디어
- 현재 미세먼지 측정치 + 미세먼지 예보 알려주기

### 사용한 공공 API
- https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861


### 기술 레퍼런스
- https://www.baeldung.com/spring-value-annotation


### 문제, 해결
  

### TODO
- [x] json string -> 객체로 매핑
  - com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field 오류 발생했었음
    - objectMapper 쓰고, 적절한 dto나 Map 쓰면 됨
- 캐시 적용
  - [x] 로컬 캐시
  - [ ] 다른 캐시 뭐 있지? 적용하기
  - [ ] LRU 방식의 캐시 직접 만들어보기
    - https://www.baeldung.com/java-lru-cache
- [ ] API 호출 비동기로 변경
- [ ] circuit breaker, retry 적용
- [ ] encoded auth key 사용하도록 변경 필요
  - 스프링부트 프로퍼티 파일 여러개 설정하기
    - https://blog.jiniworld.me/81
