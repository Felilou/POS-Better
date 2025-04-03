-- Teams with fixed UUIDs
INSERT INTO team (id, uuid, archived, name) VALUES
                                                (1, '11111111-1111-1111-1111-111111111111', false, 'FC Bayern München'),
                                                (2, '22222222-2222-2222-2222-222222222222', false, 'Borussia Dortmund'),
                                                (3, '33333333-3333-3333-3333-333333333333', false, 'RB Leipzig'),
                                                (4, '44444444-4444-4444-4444-444444444444', false, 'Bayer Leverkusen'),
                                                (5, '55555555-5555-5555-5555-555555555555', true, 'Schalke 04');

-- Players with fixed UUIDs
INSERT INTO player (id, uuid, first_name, last_name, birth_date, email, country_code, number, position, team_id, joined_current_team_at, archived) VALUES
                                                                                                                                                       (1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Manuel', 'Neuer', '1986-03-27', 'neuer@example.com', '+49', '1234567', 'GK', 1, '2011-06-01 10:00:00', false),
                                                                                                                                                       (2, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Thomas', 'Müller', '1989-09-13', 'mueller@example.com', '+49', '2345678', 'AM', 1, '2008-08-15 10:00:00', false),
                                                                                                                                                       (3, 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'Joshua', 'Kimmich', '1995-02-08', 'kimmich@example.com', '+49', '3456789', 'CM', 1, '2015-07-01 10:00:00', false),
                                                                                                                                                       (4, 'dddddddd-dddd-dddd-dddd-dddddddddddd', 'Marco', 'Reus', '1989-05-31', 'reus@example.com', '+49', '4567890', 'AM', 2, '2012-07-01 10:00:00', false),
                                                                                                                                                       (5, 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Mats', 'Hummels', '1988-12-16', 'hummels@example.com', '+49', '5678901', 'CB', 2, '2019-06-19 10:00:00', false),
                                                                                                                                                       (6, 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Erling', 'Haaland', '2000-07-21', 'haaland@example.com', '+47', '6789012', 'ST', null, '2019-06-19 10:00:00', true),
                                                                                                                                                       (7, 'aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee', 'Christopher', 'Nkunku', '1997-11-14', 'nkunku@example.com', '+33', '7890123', 'AM', 3, '2019-07-01 10:00:00', false),
                                                                                                                                                       (8, '88888888-8888-8888-8888-888888888888', 'Dani', 'Olmo', '1998-05-07', 'olmo@example.com', '+34', '8901234', 'AM', 3, '2020-01-25 10:00:00', false),
                                                                                                                                                       (9, '99999999-9999-9999-9999-999999999999', 'Florian', 'Wirtz', '2003-05-03', 'wirtz@example.com', '+49', '9012345', 'AM', 4, '2020-01-18 10:00:00', false);

-- Staff with fixed UUIDs
INSERT INTO staff (id, uuid, first_name, last_name, birth_date, email, country_code, number, team_id, role) VALUES
                                                                                                                (1, 'aaaaabbb-ffff-ffff-eeee-aaaaaaaaaaaa', 'Julian', 'Nagelsmann', '1987-07-23', 'nagelsmann@example.com', '+49', '1122334', 1, 'HEAD_COACH'),
                                                                                                                (2, 'dadadaed-baab-abba-baba-fafafafafafa', 'Edin', 'Terzić', '1982-10-30', 'terzic@example.com', '+49', '2233445', 2, 'HEAD_COACH'),
                                                                                                                (3, 'a1a1a1a1-a1a1-a1a1-a1a1-a1a1a1a1a1a1', 'Marco', 'Rose', '1976-09-11', 'rose@example.com', '+49', '3344556', 3, 'HEAD_COACH'),
                                                                                                                (4, 'a2a2a2a2-a2a2-a2a2-a2a2-a2a2a2a2a2a2', 'Xabi', 'Alonso', '1981-11-25', 'alonso@example.com', '+34', '4455667', 4, 'HEAD_COACH'),
                                                                                                                (5, 'a3a3a3a3-a3a3-a3a3-a3a3-a3a3a3a3a3a3', 'Thomas', 'Müller Sr.', '1960-05-15', 'mueller.sr@example.com', '+49', '5566778', 1, 'SCOUT');

-- Player Team Memberships (historical) with fixed UUIDs
INSERT INTO player_team_membership (id, uuid, player_id, team_id, from_time, to_time) VALUES
                                                                                          (1, 'a4a4a4a4-a4a4-a4a4-a4a4-a4a4a4a4a4a4', 5, 1, '2009-07-01 10:00:00', '2014-06-30 10:00:00'),
                                                                                          (2, 'a5a5a5a5-a5a5-a5a5-a5a5-a5a5a5a5a5a5', 5, 2, '2014-07-01 10:00:00', '2016-06-30 10:00:00'),
                                                                                          (3, 'a6a6a6a6-a6a6-a6a6-a6a6-a6a6a6a6a6a6', 5, 1, '2016-07-01 10:00:00', '2019-06-18 10:00:00'),
                                                                                          (4, 'a7a7a7a7-a7a7-a7a7-a7a7-a7a7a7a7a7a7', 6, 2, '2020-01-01 10:00:00', '2022-06-30 10:00:00'),
                                                                                          (5, 'a8a8a8a8-a8a8-a8a8-a8a8-a8a8a8a8a8a8', 4, 3, '2009-07-01 10:00:00', '2012-06-30 10:00:00');

-- Matches with fixed UUIDs
INSERT INTO match (id, uuid, home_team_id, away_team_id, kick_off_time) VALUES
                                                                            (1, 'aaaaaa0a-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 1, 2, '2023-04-01 15:30:00'),
                                                                            (2, 'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 3, 4, '2023-04-08 18:30:00'),
                                                                            (3, 'a9a9a9a9-a9a9-a9a9-a9a9-a9a9a9a9a9a9', 2, 3, '2023-04-15 15:30:00'),
                                                                            (4, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 4, 1, '2023-04-22 15:30:00'),
                                                                            (5, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 1, 3, '2023-04-29 15:30:00');

-- Match Events with fixed UUIDs
INSERT INTO match_event (id, uuid, player_id, match_id, event_type, min) VALUES
                                                                             (1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa0', 2, 1, 'GOAL', 23),
                                                                             (2, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 3, 1, 'ASSIST', 23),
                                                                             (3, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 4, 1, 'GOAL', 45),
                                                                             (4, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5', 5, 1, 'YELLOW_CARD', 67),
                                                                             (5, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa6', 7, 2, 'GOAL', 12),
                                                                             (6, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa7', 8, 2, 'ASSIST', 12),
                                                                             (7, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa8', 9, 2, 'GOAL', 34),
                                                                             (8, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa9', 4, 3, 'GOAL', 56),
                                                                             (9, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 5, 3, 'ASSIST', 56),
                                                                             (10, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 7, 3, 'RED_CARD', 78),
                                                                             (11, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb3', 9, 4, 'GOAL', 10),
                                                                             (12, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb4', 2, 4, 'GOAL', 67),
                                                                             (13, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb5', 3, 4, 'ASSIST', 67),
                                                                             (14, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb6', 2, 5, 'GOAL', 22),
                                                                             (15, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb7', 3, 5, 'ASSIST', 22),
                                                                             (16, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb8', 7, 5, 'GOAL', 55);
