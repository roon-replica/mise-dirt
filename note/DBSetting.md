# install mysql
- m1 mac이라 제약이 좀 있음
- mysql install for m1 mac (arm)
  - [mysql install for mac OS](https://dev.mysql.com/downloads/mysql/)
  - [mysql 설치 및 db 생성 블로그](https://secure-key.tistory.com/56)
  
# db 세팅
- 사용자 추가 및 권한 부여
  ```sql
     cd /usr/local/mysql/bin
    ./mysql -u root -p로 접속
     
     CREATE USER '{유저명}'@'{host명 or ip}' IDENTIFIED BY '{password}';
     GRANT ALL PRIVILEGES ON *.* TO '{유저명}'@'{host명}' WITH GRANT OPTION;
     FLUSH PRIVILEGES;
  ```
  
- db, table 생성
  ```sql
    CREATE DATABASE {DB명};
    SHOW DATABASES;
  
    CREATE TABLE region(
      id int not null auto_increment primary key,
      name varchar(255) not null
    );
    
    CREATE TABLE dirt(
      id int not null auto_increment primary key,
      pm10 int(4),
      pm25 int(4),
      date_created datetime default now()
    );
    
  ```
  
# spring - db 연결 설정
- 의존성 추가
  - mysql connector
  - JPA

- application.yml에 DB 연결 정보, JPA 설정

# JPA 설정
- entity 작성
  - 기본 컬럼 매핑
  - 연관관계 컬럼 매핑(외래키)
- repository 작성
- JPA Auditing으로 생성,수정 시간 자동화 설정