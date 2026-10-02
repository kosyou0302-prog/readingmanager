MERGE INTO author (id, name, birth, death)
KEY(id)
VALUES(1, '東野圭吾', '1958-02-04', NULL);

MERGE INTO work(id, author_id, title, published)
KEY(id)
VALUES(1, 1, '容疑者Xの献身', '2008-08-05' );

MERGE INTO readlog(id, work_id, finished_date, rating)
KEY(id)
VALUES(1, 1, '2026-09-01', 4);