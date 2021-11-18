INSERT INTO chat.user (login, password) VALUES
('Nastya', 'password'),
('Lena', 'password'),
('Olya', 'password'),
('Katya', 'password'),
('Anya', 'password')
ON CONFLICT DO NOTHING;

INSERT INTO chat.chatroom (chatName, chatOwner) VALUES
('School21', (SELECT userID FROM chat.user WHERE login = 'Nastya')),
('My Lovely Cats', (SELECT userID FROM chat.user WHERE login = 'Lena')),
('Golang', (SELECT userID FROM chat.user WHERE login = 'Olya')),
('Android', (SELECT userID FROM chat.user WHERE login = 'Katya')),
('Java', (SELECT userID FROM chat.user WHERE login = 'Anya'))
ON CONFLICT DO NOTHING;

INSERT INTO chat.message (author, chatRoom, text, localDateTime) VALUES
(1, 1, 'Welcome to my room', now()),
(2, 2, 'Welcome to my crazy world', now()),
(3, 3, 'Hi', now()),
(4, 4, 'Hello', now()),
(5, 5, 'Hi everyone', now())
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS chat.chats(
                                         chatsID SERIAL PRIMARY KEY,
                                         userID int NOT NULL,
                                         FOREIGN KEY (userID) REFERENCES chat.user(userID),
                                         roomID int NOT NULL,
                                         FOREIGN KEY (roomID) REFERENCES chat.chatroom(chatID)
);

INSERT INTO chat.chats (userID, roomID) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 2), (2, 4), (3, 3), (4, 4), (5, 5), (5, 1)
ON CONFLICT DO NOTHING;