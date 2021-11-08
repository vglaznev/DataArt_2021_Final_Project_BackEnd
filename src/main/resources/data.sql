DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE ARTICLE
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR (200) NOT NULL ,
    TOPIC VARCHAR (20) NOT NULL ,
    BODY CLOB NOT NULL ,
    TIME SMALLDATETIME NOT NULL
);

INSERT INTO ARTICLE (TITLE,TOPIC,BODY,TIME) VALUES ('Title_1','Topic_1', 'Some body', NOW());
INSERT INTO ARTICLE (TITLE,TOPIC,BODY,TIME) VALUES ('Title_2','Topic_1', 'Some body', NOW());
INSERT INTO ARTICLE (TITLE,TOPIC,BODY,TIME) VALUES ('Title_3','Topic_2', 'Some body', NOW());
INSERT INTO ARTICLE (TITLE,TOPIC,BODY,TIME) VALUES ('Title_4','Topic_2', 'Some body', NOW());
INSERT INTO ARTICLE (TITLE,TOPIC,BODY,TIME) VALUES ('Title_5','Topic_2', 'Some body', NOW());

COMMIT;