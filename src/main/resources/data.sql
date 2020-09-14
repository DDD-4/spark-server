insert into user(name, created_at, updated_at) values ('TEST', now(), now());

insert into user(name, created_at, updated_at) values ('TEST2', now(), now());

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true, '기본폴더', 0, false, 1);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true, '기본폴더', 0, false, 2);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니', 0, true, 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'tomato', '토마토', 1, 1, 1);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'apple', '사과', 3, 1, 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'banana', '바나나', 3, 1, 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'pineapple', '파인애플', 3, 1, 2);

-- insert into photo(created_at, updated_at, photo_name, photo_original_name)
-- values(now(), now(), '사과', 'apple.jpeg');


