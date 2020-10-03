insert into user(name, created_at, updated_at) values ('TEST', now(), now());
insert into user(name, created_at, updated_at) values ('TEST2', now(), now());

insert into photo(path_key, name, extension, created_at, updated_at)
values ('b2f6e721aeef478fb81e942c705071bc', 'apple', 'jpeg', now(), now());

insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true,'기본폴더', 0, false, 1);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, true, '기본폴더', 0, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니1', 1, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니2', 2, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니3', 3, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니4', 4, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니5', 5, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니6', 6, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니7', 7, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니8', 8, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니9', 9, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니10', 10, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니11', 11, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니12', 12, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 바구니13', 13, true, 2);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '과일 모음', 1, false, 1);
insert into folder (created_at, updated_at, active, default, name, priority, sharable, user_id)
values(now(), now(), true, false, '동물 바구니', 2, true, 1);

insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'tomato', '토마토', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
values (now(), now(), true, 'banana', '바나나', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'pineapple', '파인애플', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'peach', '복숭아', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'melon', '멜론', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'grape', '포도', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'kiwi', '키위', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'lemon', '레몬', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'mango', '망고', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'lime', '라임', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'pear', '배', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 1, 'b2f6e721aeef478fb81e942c705071bc', 1);
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
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 4, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 5, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 6, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 7, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 8, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 9, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 10, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 11, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 12, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 13, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 14, 'b2f6e721aeef478fb81e942c705071bc', 2);
insert into vocabulary(created_at, updated_at, active, english, korean, folder_id , photo_path , user_id)
values (now(), now(), true, 'cherry', '체리', 15, 'b2f6e721aeef478fb81e942c705071bc', 2);
