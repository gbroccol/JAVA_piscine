DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.user (
    userID SERIAL PRIMARY KEY,
    login varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom (
    chatID SERIAL PRIMARY KEY,
    chatName varchar(30) NOT NULL UNIQUE,
    chatOwner int NOT NULL,
    FOREIGN KEY (chatOwner) REFERENCES chat.user(userID)
);

CREATE TABLE IF NOT EXISTS chat.message (
    messageID SERIAL PRIMARY KEY,
    author int NOT NULL,
    FOREIGN KEY (author) REFERENCES chat.user(userID),
    chatRoom int NOT NULL,
    FOREIGN KEY (chatRoom) REFERENCES chat.chatroom(chatID),
    text text NOT NULL,
    localDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat.chats(
    chatsID SERIAL PRIMARY KEY,
    userID int NOT NULL,
    FOREIGN KEY (userID) REFERENCES chat.user(userID),
    roomID int NOT NULL,
    FOREIGN KEY (roomID) REFERENCES chat.chatroom(chatID)
);