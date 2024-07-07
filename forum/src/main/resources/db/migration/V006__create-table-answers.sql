create table answers(

    id bigint not null auto_increment,
    message text not null,
    topic_id bigint not null,
    created_at datetime not null,
    user_id bigint not null,
    topic_solution tinyint(1) not null,
    active tinyint(1) not null,

    primary key(id),
    constraint fk_answers_user_id foreign key(user_id) references users(id),
    constraint fk_answers_topic_id foreign key(topic_id) references topics(id)

);