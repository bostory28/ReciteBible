
/* Drop Tables */

DROP TABLE [USER_BB];
DROP TABLE [VERSES_BB];




/* Create Tables */

CREATE TABLE [USER_BB]
(
	[_ID] integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	[LANGUAGES] text,
	[LAST_PAGE] integer,
	[TEXT_SIZE] integer,
	[BACKGROUND_R] integer,
	[BACKGROUND_G] integer,
	[BACKGROUND_B] integer
);


CREATE TABLE [VERSES_BB]
(
	[_ID] integer NOT NULL PRIMARY KEY AUTOINCREMENT,
	[VERSE_TITLE_KR] text,
	[VERSE_TITLE_EN] text,
	[VERSE_KR] text,
	[VERSE_EN] text,
	[VERSION] integer,
	[MON] text,
	[YR] text
);



