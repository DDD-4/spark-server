insert into user(name, created_at, updated_at) values ('TEST', now(), now());

insert into user(name, created_at, updated_at) values ('TEST2', now(), now());

insert into photo(id, name, extension, created_at, updated_at)
values('b2f6e721aeef478fb81e942c705071bc', 'apple', 'jpeg', now(), now());

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true,'기본폴더', 0, false, 1);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true, '기본폴더', 0, true, 2);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니', 0, true, 2);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 모음', 0, false, 1);

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '동물 바구니', 0, true, 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'tomato', '토마토', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'apple', '사과', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'banana', '바나나', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'pineapple', '파인애플', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'peach', '복숭아', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'melon', '멜론', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'grape', '포도', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'kiwi', '키위', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'lemon', '레몬', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'mango', '망고', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'lime', '라임', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'pear', '배', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 3, 'b2f6e721aeef478fb81e942c705071bc', 2);



-- insert into photo(created_at, updated_at, photo_name, photo_original_name)
-- values(now(), now(), '사과', 'apple.jpeg');


