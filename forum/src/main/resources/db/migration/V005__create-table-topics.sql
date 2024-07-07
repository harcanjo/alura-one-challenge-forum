create table topics(

    id bigint not null auto_increment,
    title varchar(255) not null,
    message text not null,
    created_at datetime not null,
    status varchar(100) not null,
    user_id bigint not null,
    course_id bigint not null,

    primary key(id),
    constraint fk_topics_user_id foreign key(user_id) references users(id),
    constraint fk_topics_course_id foreign key(course_id) references courses(id)

);