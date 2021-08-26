
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    login varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS chatrooms (
    id SERIAL PRIMARY KEY,
    name varchar(30) NOT NULL UNIQUE,
    ownerId int NOT NULL
);

ALTER TABLE chatrooms
    ADD FOREIGN KEY (ownerId)
        REFERENCES users(id);

CREATE TABLE IF NOT EXISTS messages (
    id SERIAL PRIMARY KEY,
    authorId INTEGER NOT NULL,
    chatroomId INTEGER NOT NULL,
    text TEXT NOT NULL,
    date TIMESTAMP
);

ALTER TABLE messages
    ADD FOREIGN KEY (authorId)
    REFERENCES users(id);

ALTER TABLE messages
    ADD FOREIGN KEY (chatroomId)
    REFERENCES chatrooms(id);