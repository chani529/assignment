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