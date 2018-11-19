
/* Drop Tables */

DROP TABLE ADMINS CASCADE CONSTRAINTS;
DROP TABLE VERSES CASCADE CONSTRAINTS;
DROP TABLE MONTHS CASCADE CONSTRAINTS;
DROP TABLE TOKENS CASCADE CONSTRAINTS;
DROP TABLE UPDATEDATES CASCADE CONSTRAINTS;
DROP TABLE YEARS CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_ADMINS_ADMINS_SQ;
DROP SEQUENCE SEQ_TOKENS_TOKENS_SQ;
DROP SEQUENCE SEQ_UPDATEDATES_UPDATEDATE_SQ;
DROP SEQUENCE SEQ_VERSES_VERSES_SQ;




/* Create Sequences */

CREATE SEQUENCE SEQ_ADMINS_ADMINS_SQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TOKENS_TOKENS_SQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_UPDATEDATES_UPDATEDATE_SQ INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_VERSES_VERSES_SQ INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE ADMINS
(
	ADMINS_SQ number(2) NOT NULL,
	ID varchar2(10) NOT NULL UNIQUE,
	PW varchar2(20) NOT NULL,
	NAME varchar2(10),
	PRIMARY KEY (ADMINS_SQ)
);


CREATE TABLE MONTHS
(
	MON number(2) NOT NULL,
	PRIMARY KEY (MON)
);


CREATE TABLE TOKENS
(
	TOKENS_SQ number(7,0) NOT NULL,
	MAC varchar2(20),
	TOKEN varchar2(150),
	PRIMARY KEY (TOKENS_SQ)
);


CREATE TABLE UPDATEDATES
(
	UPDATEDATE_SQ number(7,0) NOT NULL,
	LASTDATE varchar2(25),
	UPDATE_TYPE number(1),
	VERSES_SQ number(7,0),
	VERSE_TITLE_KR varchar2(30),
	VERSE_TITLE_EN varchar2(30),
	VERSE_SECTION number(1),
	VERSE_KR varchar2(1000),
	VERSE_EN varchar2(1000),
	YR number(4),
	MON number(2),
	PRIMARY KEY (UPDATEDATE_SQ)
);


CREATE TABLE VERSES
(
	VERSES_SQ number(7,0) NOT NULL,
	VERSE_TITLE_KR varchar2(30),
	VERSE_TITLE_EN varchar2(30),
	VERSE_SECTION number(1),
	VERSE_KR varchar2(1000),
	VERSE_EN varchar2(1000),
	VERSION number(2,0),
	MON number(2) NOT NULL,
	YR number(4) NOT NULL,
	ISPUSHED number(1),
	PRIMARY KEY (VERSES_SQ)
);


CREATE TABLE YEARS
(
	YR number(4) NOT NULL,
	PRIMARY KEY (YR)
);



/* Create Foreign Keys */

ALTER TABLE VERSES
	ADD FOREIGN KEY (MON)
	REFERENCES MONTHS (MON)
;


ALTER TABLE VERSES
	ADD FOREIGN KEY (YR)
	REFERENCES YEARS (YR)
;



