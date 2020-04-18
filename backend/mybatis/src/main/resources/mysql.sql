create table t_student(
 id int primary key auto_increment comment '编号',
 name varchar(20) not null comment '姓名'
)Engine=InnoDB default charset=utf8 comment='学生表';

insert into t_student (name) values ('张三');
insert into t_student (name) values ('李四');
