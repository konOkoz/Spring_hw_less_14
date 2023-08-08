insert into users(email, state, role)
values ('test@mail.com', 'NOT_CONFIRMED', 'USER');
insert into users(email, state, role)
values ('test121@mail.com', 'NOT_CONFIRMED', 'USER');

insert into task(description, title, startDate, finishDate, aboutUserId)
values('Test task 1','Test title 1', '2023-10-10', '2023-10-12', 1);
insert into task(description, title, startDate, finishDate, aboutUserId)
values('Test task 2','Test title 2', '2023-11-11', '2023-11-13', 1);