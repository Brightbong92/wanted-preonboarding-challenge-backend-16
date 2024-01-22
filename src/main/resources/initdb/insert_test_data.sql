INSERT INTO `performance` (name, price, round, type, start_date, is_reserve)
VALUES ('레베카', 100000, 1, 0, '2024-01-20 19:30:00', 'disable');

INSERT INTO performance_seat_info VALUES
 (DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 1, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 2, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 3, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 4, 'enable', DEFAULT, DEFAULT);


// 공연정보
INSERT INTO `performance` (id, name, price, round, type, start_date, is_reserve)
VALUES
    (UUID_TO_BIN(UUID()), '레베카', 100000, 1, 0, '2024-01-20 19:30:00', 'disable'),
    (UUID_TO_BIN(UUID()), '검정치마', 150000, 3, 0, '2024-02-20 19:30:00', 'enable');

// 좌석정보
INSERT INTO wanted.performance_seat_info (gate, is_reserve, line, performance_id, round, seat)
VALUES
(1, 'disable', 'A',(SELECT id FROM `performance` WHERE wanted.performance.name = '레베카'), 1, 1)
,(1, 'disable', 'A', (SELECT id FROM `performance` WHERE wanted.performance.name = '레베카'), 1, 2)
,(1, 'disable', 'A', (SELECT id FROM `performance` WHERE wanted.performance.name = '레베카'), 1, 3)
,(2, 'enable', 'B',(SELECT id FROM `performance` WHERE wanted.performance.name = '검정치마'), 3, 1)
,(2, 'enable', 'B', (SELECT id FROM `performance` WHERE wanted.performance.name = '검정치마'), 3, 2)
,(2, 'enable', 'B', (SELECT id FROM `performance` WHERE wanted.performance.name = '검정치마'), 3, 3);

