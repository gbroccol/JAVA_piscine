
INSERT INTO users VALUES
(default,'Nastya', 'password'),
(default, 'Lena', 'password'),
(default, 'Olya', 'password'),
(default, 'Katya', 'password'),
(default, 'Anya', 'password');

INSERT INTO chatrooms VALUES
(default, 'School21', (SELECT id FROM users WHERE login = 'Nastya')),
(default, 'My Lovely Cats', (SELECT id FROM users WHERE login = 'Lena')),
(default, 'Golang', (SELECT id FROM users WHERE login = 'Olya')),
(default, 'Android', (SELECT id FROM users WHERE login = 'Katya')),
(default, 'Java', (SELECT id FROM users WHERE login = 'Anya'));

INSERT INTO messages VALUES
(default, 1, 1, 'Welcome to my room', now()),
(default, 2, 2, 'Welcome to my crazy world', now()),
(default, 3, 3, 'Hi', now()),
(default, 4, 4, 'Hello', now()),
(default, 5, 5, 'Hi everyone', now());