SET DATABASE SQL SYNTAX PGS TRUE;

CREATE SCHEMA IF NOT EXISTS test;

DROP TABLE IF EXISTS test.user;

CREATE TABLE test.user (
 	identifier  SERIAL PRIMARY KEY,
 	"email"     VARCHAR(200),
	"password"  VARCHAR(200)
);

COMMIT;