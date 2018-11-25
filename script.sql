create database platzi;
use platzi;
create table teacher (
  id_teacher int auto_increment primary key,
  name       varchar(200) not null,
  avatar     varchar(200) null
);
create table course (
  id_course  int auto_increment primary key,
  id_teacher int          null,
  name       varchar(200) not null,
  themes     varchar(200) null,
  project    varchar(200) null,
  constraint `fk_id_teacher` foreign key (id_teacher) references teacher (id_teacher)
);
create table social_media (
  id_social_media int auto_increment primary key,
  name            varchar(200) not null,
  icon            varchar(200) null
);
create table teacher_social_media (
  id_teacher_social_media int auto_increment primary key,
  id_teacher              int          not null,
  id_social_media         int          not null,
  nickname                varchar(200) not null,
  constraint `fk_id_teacher_sm` foreign key (id_teacher) references teacher (id_teacher),
    constraint `fk_id_social_media` foreign key (id_social_media) references social_media (id_social_media)
);
select *
from teacher;
select *
from course;
select *
from social_media;
select *
from teacher_social_media;
insert into teacher (id_teacher, name, avatar)
values (1, 'Juan Balceda', 'https://pbs.twimg.com/profile_images/853965326870097924/IySwo55g_400x400.jpg');
insert into course (id_course, id_teacher, name, themes, project)
values (1, 1, 'Java EE 8', 'aster Class', 'Project Management Application');
insert into social_media (id_social_media, name, icon)
values (1,'twitter','https://vignette.wikia.nocookie.net/es.starwars/images/9/92/Twitter_Icon.png');
insert into teacher_social_media (id_teacher, id_social_media, nickname)
values (1,1,'@juanbalceda');

select * from course;