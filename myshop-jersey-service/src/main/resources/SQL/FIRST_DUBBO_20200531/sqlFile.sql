--kafka生产者消息表
create table TB_KAFKA_IN_MSG
(
  id       NUMBER(20)  not null primary key,
  DOCUMENT_NO VARCHAR2(20),
  HANDLE_FLAG VARCHAR2(2) DEFAULT '0' ,
  SERVER_ID NUMBER(20) not null,
  created  DATE NOT NULL ,
  updated  DATE
);
SELECT * FROM TB_kafka_in_msg;

--身份证前六位地区编号
create table IdCard_AREA
(
  id        VARCHAR2(50) not null PRIMARY KEY,
  AREA_CODE NUMBER(10),
  AREA_NAME VARCHAR2(50),
  created   DATE,
  updated   DATE
)