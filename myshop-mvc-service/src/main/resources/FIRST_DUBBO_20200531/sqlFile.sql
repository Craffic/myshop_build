--kafka消费者消息表
create table TB_KAFKA_OUT_MSG
(
  id       NUMBER(20)  not null primary key,
  DOCUMENT_NO VARCHAR2(20),
  HANDLE_FLAG VARCHAR2(2) DEFAULT '0' ,
  SERVER_ID NUMBER(20) not null,
  created  DATE NOT NULL ,
  updated  DATE
);