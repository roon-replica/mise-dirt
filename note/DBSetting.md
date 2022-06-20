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
  
  CREATE TABLE `region` (
  `name` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

  CREATE TABLE `dirt` (
  `dirt_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `pm10` int NOT NULL,
  `pm25` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dirt_id`),
  KEY `FKs9xkl768cp2pxhi7vtf8yo0e0` (`name`),
  CONSTRAINT `FKs9xkl768cp2pxhi7vtf8yo0e0` FOREIGN KEY (`name`) REFERENCES `region` (`name`)
  ) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
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