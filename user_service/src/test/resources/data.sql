insert into "user"(id, first_name, last_name, latitude, longitude, created_date, last_modified_date)
values
    (1000, 'Ali', 'Veli', 35.2, 26.45, '2024-03-06 23:21:37.048', '2024-03-06 23:21:37.048'),
    (1001, 'John', 'Doe', 40.7128, -74.0060, '2024-03-06 23:21:37.048', '2024-03-06 23:21:37.048'),
    (1002, 'Jane', 'Smith', 51.5074, -0.1278, '2024-03-06 23:21:37.048', '2024-03-06 23:21:37.048'),
    (1003, 'Michael', 'Johnson', 34.0522, -118.2437, '2024-03-06 23:21:37.048', '2024-03-06 23:21:37.048'),
    (1004, 'Maria', 'Garcia', -33.8688, 151.2093, '2024-03-06 23:21:37.048', '2024-03-06 23:21:37.048');

insert into review (id, user_id, restaurant_id, created_date, last_modified_date, rate, comment)
values
    (2000, 1000, '0f263a34-36c6-4884-b6a6-26a260cf85fa', '2024-03-08 10:00:00', '2024-03-08 10:00:00', 'ONE', 'Excellent service!'),
    (2001, 1000, '401e67d6-de9b-4331-8040-a63129b42a84', '2024-03-08 11:30:00', '2024-03-08 11:45:00', 'FOUR','Good food, but service could be better'),
    (2002, 1000, '581c6536-a89f-4174-89a3-d18d787e5273', '2024-03-08 12:45:00', '2024-03-08 13:15:00', 'THREE', 'Average experience'),
    (2003, 1000, 'dfb1721c-62df-4f5b-afbb-585bb49d66ce', '2024-03-08 14:00:00', '2024-03-08 14:30:00', 'FIVE', 'Amazing food and service!'),
    (2004, 1000, '41094400-df4b-43f9-be3f-b51a5aac801b', '2024-03-08 15:20:00', '2024-03-08 16:00:00', 'TWO', 'Disappointing meal');