DROP TABLE IF EXISTS NEWS;

--NEWS TABLE
CREATE TABLE IF NOT EXISTS NEWS (
    IDNEWS INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    NEWSAUTHOR VARCHAR(255) NOT NULL,
    NEWSHEADLINE  VARCHAR(255) NOT NULL,
    NEWSMAIN  VARCHAR(255) NOT NULL,
    NEWSDATE  TIMESTAMP NOT NULL,
);



