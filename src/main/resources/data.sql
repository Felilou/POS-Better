-- Teams with fixed UUIDs
INSERT INTO team (id, uuid, archived, name) VALUES
                                                (1, '11111111-1111-1111-1111-111111111111', false, 'FC Bayern München'),
                                                (2, '22222222-2222-2222-2222-222222222222', false, 'Borussia Dortmund'),
                                                (3, '33333333-3333-3333-3333-333333333333', false, 'RB Leipzig'),
                                                (4, '44444444-4444-4444-4444-444444444444', false, 'Bayer Leverkusen'),
                                                (5, '55555555-5555-5555-5555-555555555555', true, 'Schalke 04');

-- Players with fixed UUIDs
INSERT INTO player (id, uuid, first_name, last_name, birth_date, email, country_code, number, position, team_id, joined_current_team_at, archived) VALUES
                                                                                                                                             (1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Manuel', 'Neuer', '1986-03-27', 'neuer@example.com', '+49', '1234567', 'GK', 1, '2011-06-01', false),
                                                                                                                                             (2, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Thomas', 'Müller', '1989-09-13', 'mueller@example.com', '+49', '2345678', 'AM', 1, '2008-08-15', false),
                                                                                                                                             (3, 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'Joshua', 'Kimmich', '1995-02-08', 'kimmich@example.com', '+49', '3456789', 'CM', 1, '2015-07-01', false),
                                                                                                                                             (4, 'dddddddd-dddd-dddd-dddd-dddddddddddd', 'Marco', 'Reus', '1989-05-31', 'reus@example.com', '+49', '4567890', 'AM', 2, '2012-07-01', false),
                                                                                                                                             (5, 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Mats', 'Hummels', '1988-12-16', 'hummels@example.com', '+49', '5678901', 'CB', 2, '2019-06-19', false),
                                                                                                                                             (6, 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Erling', 'Haaland', '2000-07-21', 'haaland@example.com', '+47', '6789012', 'ST', null, '2019-06-19', true),
                                                                                                                                             (7, 'gggggggg-gggg-gggg-gggg-gggggggggggg', 'Christopher', 'Nkunku', '1997-11-14', 'nkunku@example.com', '+33', '7890123', 'AM', 3, '2019-07-01', true),
                                                                                                                                             (8, 'hhhhhhhh-hhhh-hhhh-hhhh-hhhhhhhhhhhh', 'Dani', 'Olmo', '1998-05-07', 'olmo@example.com', '+34', '8901234', 'AM', 3, '2020-01-25', true),
                                                                                                                                             (9, 'iiiiiiii-iiii-iiii-iiii-iiiiiiiiiiii', 'Florian', 'Wirtz', '2003-05-03', 'wirtz@example.com', '+49', '9012345', 'AM', 4, '2020-01-18', true);

-- Staff with fixed UUIDs
INSERT INTO staff (id, uuid, first_name, last_name, birth_date, email, country_code, number, team_id, role) VALUES
                                                                                                                (1, 'jjjjjjjj-jjjj-jjjj-jjjj-jjjjjjjjjjjj', 'Julian', 'Nagelsmann', '1987-07-23', 'nagelsmann@example.com', '+49', '1122334', 1, 'HEAD_COACH'),
                                                                                                                (2, 'kkkkkkkk-kkkk-kkkk-kkkk-kkkkkkkkkkkk', 'Edin', 'Terzić', '1982-10-30', 'terzic@example.com', '+49', '2233445', 2, 'HEAD_COACH'),
                                                                                                                (3, 'llllllll-llll-llll-llll-llllllllllll', 'Marco', 'Rose', '1976-09-11', 'rose@example.com', '+49', '3344556', 3, 'HEAD_COACH'),
                                                                                                                (4, 'mmmmmmmm-mmmm-mmmm-mmmm-mmmmmmmmmmmm', 'Xabi', 'Alonso', '1981-11-25', 'alonso@example.com', '+34', '4455667', 4, 'HEAD_COACH'),
                                                                                                                (5, 'nnnnnnnn-nnnn-nnnn-nnnn-nnnnnnnnnnnn', 'Thomas', 'Müller Sr.', '1960-05-15', 'mueller.sr@example.com', '+49', '5566778', 1, 'SCOUT');

-- Player Team Memberships (historical) with fixed UUIDs
-- INSERT INTO player_team_membership (id, uuid, player_id, team_id, from_time, to_time) VALUES
--                                                                                          (1, 'oooooooo-oooo-oooo-oooo-oooooooooooo', 5, 1, '2009-07-01', '2014-06-30'),
--                                                                                          (2, 'pppppppp-pppp-pppp-pppp-pppppppppppp', 5, 2, '2014-07-01', '2016-06-30'),
--                                                                                          (3, 'qqqqqqqq-qqqq-qqqq-qqqq-qqqqqqqqqqqq', 5, 1, '2016-07-01', '2019-06-18'),
--                                                                                          (4, 'rrrrrrrr-rrrr-rrrr-rrrr-rrrrrrrrrrrr', 6, 2, '2020-01-01', '2022-06-30'),
--                                                                                          (5, 'ssssssss-ssss-ssss-ssss-ssssssssssss', 4, 3, '2009-07-01', '2012-06-30');

-- Matches with fixed UUIDs
INSERT INTO match (id, uuid, home_team_id, away_team_id, kick_off_time) VALUES
                                                                            (1, 'tttttttt-tttt-tttt-tttt-tttttttttttt', 1, 2, '2023-04-01 15:30:00'),
                                                                            (2, 'uuuuuuuu-uuuu-uuuu-uuuu-uuuuuuuuuuuu', 3, 4, '2023-04-08 18:30:00'),
                                                                            (3, 'vvvvvvvv-vvvv-vvvv-vvvv-vvvvvvvvvvvv', 2, 3, '2023-04-15 15:30:00'),
                                                                            (4, 'wwwwwwww-wwww-wwww-wwww-wwwwwwwwwwww', 4, 1, '2023-04-22 15:30:00'),
                                                                            (5, 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx', 1, 3, '2023-04-29 15:30:00');

-- Match Events with fixed UUIDs
INSERT INTO match_event (id, uuid, player_id, match_id, event_type, min) VALUES
                                                                             (1, 'yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy', 2, 1, 'GOAL', 23),
                                                                             (2, 'zzzzzzzz-zzzz-zzzz-zzzz-zzzzzzzzzzzz', 3, 1, 'ASSIST', 23),
                                                                             (3, '11111111-aaaa-bbbb-cccc-dddddddddddd', 4, 1, 'GOAL', 45),
                                                                             (4, '22222222-aaaa-bbbb-cccc-dddddddddddd', 5, 1, 'YELLOW_CARD', 67),
                                                                             (5, '33333333-aaaa-bbbb-cccc-dddddddddddd', 7, 2, 'GOAL', 12),
                                                                             (6, '44444444-aaaa-bbbb-cccc-dddddddddddd', 8, 2, 'ASSIST', 12),
                                                                             (7, '55555555-aaaa-bbbb-cccc-dddddddddddd', 9, 2, 'GOAL', 34),
                                                                             (8, '66666666-aaaa-bbbb-cccc-dddddddddddd', 4, 3, 'GOAL', 56),
                                                                             (9, '77777777-aaaa-bbbb-cccc-dddddddddddd', 5, 3, 'ASSIST', 56),
                                                                             (10, '88888888-aaaa-bbbb-cccc-dddddddddddd', 7, 3, 'RED_CARD', 78),
                                                                             (11, '99999999-aaaa-bbbb-cccc-dddddddddddd', 9, 4, 'GOAL', 10),
                                                                             (12, 'aaaaaaaa-1111-2222-3333-444444444444', 2, 4, 'GOAL', 67),
                                                                             (13, 'bbbbbbbb-1111-2222-3333-444444444444', 3, 4, 'ASSIST', 67),
                                                                             (14, 'cccccccc-1111-2222-3333-444444444444', 2, 5, 'GOAL', 22),
                                                                             (15, 'dddddddd-1111-2222-3333-444444444444', 3, 5, 'ASSIST', 22),
                                                                             (16, 'eeeeeeee-1111-2222-3333-444444444444', 7, 5, 'GOAL', 55);
