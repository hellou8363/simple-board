# simple-board
테스트를 위해 먼저 Local Database에 테이블을 생성해야 한다.  
- - -
## Oracle Database  
### Oracle - board 테이블
```SQL
DROP TABLE board;

CREATE TABLE board(
	num NUMBER(4) PRIMARY KEY,
	author VARCHAR2(20),
	title VARCHAR2(50),
	content VARCHAR2(100),
	writeday DATE DEFAULT SYSDATE,
	readCnt NUMBER(4) DEFAULT 0,
	repRoot NUMBER(4),
	repStep NUMBER(4),
	repIndent NUMBER(4)
);
```  
### Oracle - board_seq 시퀀스
```SQL
DROP SEQUENCE board_seq;

CREATE SEQUENCE board_seq NOCACHE;

INSERT INTO 
	board (num, author, title, content, repRoot, repStep, repIndent)
	values( board_seq.NEXTVAL, '홍길동', '테스트', '테스트입니다.', board_seq.CURRVAL, 0, 0);

COMMIT;
```  

<br/>
<br/>

## MySQL Database   
### MySQL - board 테이블
```SQL
CREATE TABLE board(
	num INT(4) AUTO_INCREMENT PRIMARY KEY,
	author CHAR(20),
	title CHAR(50),
	content CHAR(100),
	writeday TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	read_cnt INT(4) DEFAULT 0,
	rep_root INT(4),
	rep_step INT(4),
	rep_indent INT(4)    
);
```
\
<br/>

## 스크린샷  
![스크린샷 2023-03-19 225644](https://user-images.githubusercontent.com/89592727/226186431-26c14541-51b6-443d-9b74-27aebbfb7bf6.png)
![스크린샷 2023-03-19 224509](https://user-images.githubusercontent.com/89592727/226186454-dc992937-a91c-4587-972f-e5efc9c15922.png)
![스크린샷 2023-03-19 232920](https://user-images.githubusercontent.com/89592727/226186472-c0797e17-cfdc-49d0-9314-3375ab9a8bcf.png)
![스크린샷 2023-03-19 234234](https://user-images.githubusercontent.com/89592727/226186487-d77c01d4-1234-41c9-a450-18fdc7505711.png)
