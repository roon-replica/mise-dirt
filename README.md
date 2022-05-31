### 기획 아이디어
- 현재 미세먼지 측정치 + 미세먼지 예보 알려주기

### 공공 API
- https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861



### 기술 레퍼런스
- https://www.baeldung.com/spring-value-annotation



### 문제, 해결
- com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field 오류
  
  이런거 역직렬화 어케하지 -> objectMapper, 적절한 dto 또는 map
  ~~~
  {"response":{"body":{"totalCount":40,"items":[{"so2Grade":"1","coFlag":null,"khaiValue":"53"} ... ]}} 
   ~~~


- 스프링부트 프로퍼티 파일 여러개 설정하기
  - https://blog.jiniworld.me/81

### TODO
- 캐시 적용
- API 호출 비동기로 변경
- circuit breaker, retry 적용
- encoded auth key 사용하도록 변경 필요