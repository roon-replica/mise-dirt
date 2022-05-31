### API
- https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15073861



### 기술 레퍼런스
- https://www.baeldung.com/spring-value-annotation



### 문제, 해결
- com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field 오류
  
  이런거 역직렬화 어케하지
  ~~~
  {"response":{"body":{"totalCount":40,"items":[{"so2Grade":"1","coFlag":null,"khaiValue":"53"} ... ]}} 
   ~~~


- 스프링부트 프로퍼티 파일 여러개 설정하기
  - https://blog.jiniworld.me/81