DELETE FROM film_genres;
DELETE FROM genres;
DELETE FROM mpa;

MERGE INTO genres (id, name) VALUES
(1, 'Комедия'), (2, 'Драма'), (3, 'Боевик'), (4, 'Супергерои'), (5, 'Фэнтези'), (6, 'Биопик');

MERGE INTO mpa (id, name) VALUES
(1, 'G'), (2, 'PG'), (3, 'PG-13'), (4, 'R'), (5, 'NC-17'), (6, 'NC-21');
