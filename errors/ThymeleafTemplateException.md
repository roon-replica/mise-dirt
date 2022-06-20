# thymeleaf header를 별도의 파일로 분리하던 중...

## 오류
   >org.thymeleaf.exceptions.TemplateInputException: Error resolving template [/fragments/header.html],
   template might not exist or might not be accessible by any of the configured Template Resolvers

## 해결
template 경로를 잘못 지정해줬음.....

/mise/fragments/header.html가 맞는데 /fragments/header.html로 지정함..

근데 상대경로로 설정은 왜 안되지? "./"는 컴파일 타임에 파싱에러를 표시함
   