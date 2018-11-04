insert into event(name, description) values
  ('Dni miasta', NULL),
  ('Dożynki', NULL),
  ('Impreza tematyczna', 'np. Swięto Kwiatów, Dni Wina, Dni Karpia'),
  ('Wynajem', 'impreza zamknięta płatna z góry'),
  ('Jarmark', 'impreza długoterminowa');

insert into message(name, surname, city, begin_date, end_date, category_id) values
  ('Mariola', 'Rumlova', 'Wrocław', '2018-10-02', '2018-10-03', 1),
  ('Zdenek', 'Ruml', 'Hradec Kralove', '2018-09-02', '2018-09-03', 2),
  ('Grzesiu', 'Nowak', 'Pierwoszów', '2017-10-09', '2017-10-11', 3),
  ('Zdzisia', 'Musialska', 'Sulików', '2020-10-03', '2020-10-03', 3),
  ('Krzysiu', 'Nowak', 'Rzeszów', '2019-10-02', '2019-10-03', 4),
  ('Paweł', 'Kowalski', 'Wrocław', '2018-05-02', '2018-05-09', 1),
  ('Michał', 'Malinowski', 'Krzyki', '2019-04-11', '2019-05-15', 3),
  ('Zdenek', 'Ruml', 'Wisznia Mała', '2018-06-28', '2018-06-30', 5);
