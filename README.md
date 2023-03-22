# Blog 검색 프로젝트

1. [JAR 다운로드 경로](#JAR-다운로드-경로)
2. [사용 기술](#사용-기술)
3. [Dependency](#Dependency)
4. [API](#API)
5. [Test Case](#Test-Case)
6. [Database (H2)](#Database-(H2))

## JAR 다운로드 경로
https://github.com/chani529/assignment/raw/main/libs/son-0.0.1-SNAPSHOT.jar

## 사용 기술
- Kotlin : 1.6.21
- Java : 11
- Spinrg Boot :  2.7.9

## Dependency
- **org.springframework.boot:spring-boot-starter-web**
  - 웹 애플리케이션 개발을 위한 스타터 패키지
- **org.springframework.boot:spring-boot-starter-data-jpa**
  - JPA를 사용하기 위한 스타터 패키지
- **org.jetbrains.kotlin:kotlin-reflect**
  - Kotlin 언어에서 reflection을 사용하기 위한 라이브러리
- **com.h2database:h2**
  - 메모리 내 데이터베이스인 H2 데이터베이스를 사용하기 위한 라이브러리
- **org.springframework.boot:spring-boot-starter-test** 
  - 테스트를 위한 스타터 패키지 (JUnit)
- **com.squareup.okhttp3:okhttp:4.10.0**
  - HTTP 클라이언트 라이브러리
  - 커넥션 풀링 지원하여, 커넥션 재사용을 하기 때문에 내장 클래스(HttpURLConnection)에 비해 성능이 좋으며,
    connectTimeout 등의 기능을 제공해주어 대규모 애플리케이션에 적합하다 생각되어 적용
- **com.google.code.gson:gson:2.10.1**
  - 객체를 JSON 형태로 변환해주는 라이브러리
  - 코드 가독성이 좋으며,객체로 변환하는 데 더 간단하고 안정적인 방법을 제공되어 적용

## API
Default endpoint = localhost:8080

GET {endpoint}/search/top-ten-keyword
```curl
curl --location --request GET 'localhost:8080/search/top-ten-keyword'
  
# 200 ok
[
    {
        "keywordId": 1,
        "keyword": "kakao10",
        "searchCount": 10
    },
    {
        "keywordId": 2,
        "keyword": "kakao9",
        "searchCount": 9
    },
    ...
]
```

GET {endpoint}/search?query=${query}&page=${page}&sort=${sort}
```curl
curl --location --request GET 'localhost:8080/search?query=집짓&page=1&sort=recency' \
--data-raw ''

# 200 ok
{
    "documents": [
        {
            "blogname": "톨레 레게",
            "contents": "산당을 짓고 레위인이 아닌 자로 제사장을 삼고 절기도 바꾸었으니 한미다로 여호와의 이름을 빙자한 자신의 종교를 만든 것이다. 율법에 순종하면 그 <b>집</b>을 견고하게 하시겠다고 약속 하신 여호와를 멸시하고 스스로 자신의 왕권을 유지하기 위하여 율법을 짓밟는 일을 한 것이다. 여로보암이 한 <b>짓</b>은 이스라엘이 광야...",
            "datetime": "2023-03-22T19:18:35.000+09:00",
            "thumbnail": "",
            "title": "열왕기상 12장 왕국의 분열",
            "url": "http://hichang56.tistory.com/306"
        },
        ...
    ],
    "meta": {
        "pageableCount": 800,
        "totalCount": 1992244,
        "isEnd": false
    }
```
| 필드 이름 | 타입  | 기본 값 | 필수여부 | 설명                         |
|-------|-----|------|------|-----------------------------|
| query | 문자열 | -    | Y    | 검색어 255자 제한                   |
| page  | 정수  | 1    | N    | 페이지 번호 1~50                   |
| sort  | 문자열 |accuracy| N    |  accuracy(정확도순), recency(최신순) |
## Test Case
- Test Code 기반으로 문서 작성
- 실제 테스트 코드로 작업되어 있으며, 
@DisplayName을 아래 이름으로 작업되어 있습니다.


### controller
- TestCase001 - Blog 검색 성공
- TestCase002 - 검색어 비여 있을 경우 에러
- TestCase003 - 검색어가 너무 길 경우
- TestCase004 - 페이지 검색 범위가 벗어 났을 경우
- TestCase005 - 정렬 값이 잘못 되었을 경우

### openApi
- TestCase006 - 카카오 블로그 검색 성공
- TestCase007 - 카카오 블로그 검색 실패

### repository
- TestCase008 - 많이 검색된 검색어 top10 가져오기
- TestCase009 - 검색어 추가 기능
- TestCase010 - 검색어 업데이트 기능

## Database (H2)
**resources/h2/init_database.sql**

```roomsql
CREATE TABLE tb_keyword (
    keyword_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    keyword VARCHAR(255) NOT NULL,
    search_count INT NOT NULL DEFAULT 0
);

INSERT INTO tb_keyword (keyword, search_count)
VALUES ('kakao10', 10),
       ('kakao9', 9),
       ('kakao8', 8),
       ('kakao7', 7),
       ('kakao6', 6),
       ('kakao5', 5),
       ('kakao4', 4),
       ('kakao3', 3),
       ('kakao2', 2),
       ('kakao1', 1);
```