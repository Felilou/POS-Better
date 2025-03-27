-- Teams
INSERT INTO team (id, uuid, archived, name) VALUES
                                                (1, random_uuid(), false, 'FC Bayern München'),
                                                (2, random_uuid(), false, 'Borussia Dortmund'),
                                                (3, random_uuid(), false, 'RB Leipzig'),
                                                (4, random_uuid(), false, 'Bayer Leverkusen'),
                                                (5, random_uuid(), true, 'Schalke 04');

-- Players
INSERT INTO player (id, uuid, first_name, last_name, birth_date, email, country_code, number, position, team_id, joined_current_team_at) VALUES
                                                                                                                                             (1, random_uuid(), 'Manuel', 'Neuer', '1986-03-27', 'neuer@example.com', '+49', '1234567', 'GK', 1, '2011-06-01'),
                                                                                                                                             (2, random_uuid(), 'Thomas', 'Müller', '1989-09-13', 'mueller@example.com', '+49', '2345678', 'AM', 1, '2008-08-15'),
                                                                                                                                             (3, random_uuid(), 'Joshua', 'Kimmich', '1995-02-08', 'kimmich@example.com', '+49', '3456789', 'CM', 1, '2015-07-01'),
                                                                                                                                             (4, random_uuid(), 'Marco', 'Reus', '1989-05-31', 'reus@example.com', '+49', '4567890', 'AM', 2, '2012-07-01'),
                                                                                                                                             (5, random_uuid(), 'Mats', 'Hummels', '1988-12-16', 'hummels@example.com', '+49', '5678901', 'CB', 2, '2019-06-19'),
                                                                                                                                             (6, random_uuid(), 'Erling', 'Haaland', '2000-07-21', 'haaland@example.com', '+47', '6789012', 'ST', null, null),
                                                                                                                                             (7, random_uuid(), 'Christopher', 'Nkunku', '1997-11-14', 'nkunku@example.com', '+33', '7890123', 'AM', 3, '2019-07-01'),
                                                                                                                                             (8, random_uuid(), 'Dani', 'Olmo', '1998-05-07', 'olmo@example.com', '+34', '8901234', 'AM', 3, '2020-01-25'),
                                                                                                                                             (9, random_uuid(), 'Florian', 'Wirtz', '2003-05-03', 'wirtz@example.com', '+49', '9012345', 'AM', 4, '2020-01-18');

-- Staff
INSERT INTO staff (id, uuid, first_name, last_name, birth_date, email, country_code, number, team_id, role) VALUES
                                                                                                          (1, random_uuid(), 'Julian', 'Nagelsmann', '1987-07-23', 'nagelsmann@example.com', '+49', '1122334', 1, 'HEAD_COACH'),
                                                                                                          (2, random_uuid(), 'Edin', 'Terzić', '1982-10-30', 'terzic@example.com', '+49', '2233445', 2, 'HEAD_COACH'),
                                                                                                          (3, random_uuid(), 'Marco', 'Rose', '1976-09-11', 'rose@example.com', '+49', '3344556', 3, 'HEAD_COACH'),
                                                                                                          (4, random_uuid(), 'Xabi', 'Alonso', '1981-11-25', 'alonso@example.com', '+34', '4455667', 4, 'HEAD_COACH'),
                                                                                                          (5, random_uuid(), 'Thomas', 'Müller Sr.', '1960-05-15', 'mueller.sr@example.com', '+49', '5566778', 1, 'SCOUT');

-- Player Team Memberships (historical)
INSERT INTO player_team_membership (id, uuid, player_id, team_id, from_time, to_time) VALUES
                                                                                (1, random_uuid(), 5, 1, '2009-07-01', '2014-06-30'),
                                                                                (2, random_uuid(), 5, 2, '2014-07-01', '2016-06-30'),
                                                                                (3, random_uuid(), 5, 1, '2016-07-01', '2019-06-18'),
                                                                                (4, random_uuid(), 6, 2, '2020-01-01', '2022-06-30'),
                                                                                (5, random_uuid(), 4, 3, '2009-07-01', '2012-06-30');

-- Matches
INSERT INTO match (id, uuid, home_team_id, away_team_id, kick_off_time) VALUES
                                                                            (1, random_uuid(), 1, 2, '2023-04-01 15:30:00'),
                                                                            (2, random_uuid(), 3, 4, '2023-04-08 18:30:00'),
                                                                            (3, random_uuid(), 2, 3, '2023-04-15 15:30:00'),
                                                                            (4, random_uuid(), 4, 1, '2023-04-22 15:30:00'),
                                                                            (5, random_uuid(), 1, 3, '2023-04-29 15:30:00');

-- Match Events
INSERT INTO match_event (id, uuid, player_id, match_id, event_type, min) VALUES
                                                                                (1, random_uuid(), 2, 1, 'GOAL', 23),
                                                                                (2, random_uuid(), 3, 1, 'ASSIST', 23),
                                                                                (3, random_uuid(), 4, 1, 'GOAL', 45),
                                                                                (4, random_uuid(), 5, 1, 'YELLOW_CARD', 67),
                                                                                (5, random_uuid(), 7, 2, 'GOAL', 12),
                                                                                (6, random_uuid(), 8, 2, 'ASSIST', 12),
                                                                                (7, random_uuid(), 9, 2, 'GOAL', 34),
                                                                                (8, random_uuid(), 4, 3, 'GOAL', 56),
                                                                                (9, random_uuid(), 5, 3, 'ASSIST', 56),
                                                                                (10, random_uuid(), 7, 3, 'RED_CARD', 78),
                                                                                (11, random_uuid(), 9, 4, 'GOAL', 10),
                                                                                (12, random_uuid(), 2, 4, 'GOAL', 67),
                                                                                (13, random_uuid(), 3, 4, 'ASSIST', 67),
                                                                                (14, random_uuid(), 2, 5, 'GOAL', 22),
                                                                                (15, random_uuid(), 3, 5, 'ASSIST', 22),
                                                                                (16, random_uuid(), 7, 5, 'GOAL', 55);