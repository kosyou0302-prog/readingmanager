INSERT INTO author (name, birth, death)
VALUES('東野圭吾', '1958-02-04', NULL);

-- 作品一覧 -------------------------------------------------------------
INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), '容疑者Xの献身', '2008-08-05');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), '永遠の記憶', '2026-08-05');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), 'マスカレード・ライフ', '2025-07-30');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), '架空犯', '2024-11-01');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), 'クスノキの女神', '2024-05-23');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), '魔女と過ごした七日間', '2026-06-16');

INSERT INTO work(author_id, title, published)
VALUES((SELECT id FROM author WHERE name='東野圭吾' LIMIT 1), 'あなたが誰かを殺した', '2026-08-12');

-- 読了記録　----------------------------------------------------------------
INSERT INTO readlog(work_id, finished_date, rating)
VALUES((SELECT id FROM work WHERE title='容疑者Xの献身' LIMIT 1), '2026-09-01', 4);