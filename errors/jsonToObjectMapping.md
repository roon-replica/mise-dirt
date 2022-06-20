# json string -> 객체로 매핑하는 중...
## 오류
> com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field 오류 발생

## 해결
objectMapper 쓰고, 적절한 dto나 Map 쓰면 됨
```java
ApiResponse cityMeasure = objectMapper.readValue(response.getBody(), ApiResponse.class);
```