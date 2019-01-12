/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/11 16:30:49                           */
/*==============================================================*/


drop table if exists en_cn_dict;

drop table if exists en_sentence_lib;

drop table if exists mark_single_word;

drop table if exists mark_word_explains;

drop table if exists read_sentence_list;

drop table if exists read_sentence_rel;

drop table if exists read_sentence_time;

drop table if exists read_word_list;

drop table if exists read_word_list_history;

drop table if exists sentence_word_rel;

/*==============================================================*/
/* Table: en_cn_dict                                            */
/*==============================================================*/
create table en_cn_dict
(
   ecd_id               char(32) not null comment '英汉词典Id',
   single_word          varchar(32) comment '单词',
   uk_phonetic          varchar(32) comment '英式音标',
   us_phonetic          varchar(32) comment '美式音标',
   translation          varchar(512) comment '翻译',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (ecd_id)
);

alter table en_cn_dict comment '英汉词典';

/*==============================================================*/
/* Table: en_sentence_lib                                       */
/*==============================================================*/
create table en_sentence_lib
(
   esl_id               char(32) not null comment '句子ID',
   user_id              char(32) comment '用户ID',
   sentence_content     varchar(512) comment '句子内容',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (esl_id)
);

alter table en_sentence_lib comment '英语句子库';

/*==============================================================*/
/* Table: mark_single_word                                      */
/*==============================================================*/
create table mark_single_word
(
   msw_id               char(32) not null comment '标记单词Id',
   user_id              char(32) comment '用户ID',
   singel_word          varchar(32) comment '单词',
   phonetic             varchar(32) comment '音标',
   consult_status       char(1) default '0' comment '是否查阅【0:未查阅  1:已查阅】',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP comment '修改时间',
   primary key (msw_id)
);

alter table mark_single_word comment '标记单词';

/*==============================================================*/
/* Table: mark_word_explains                                    */
/*==============================================================*/
create table mark_word_explains
(
   mwe_id               char(32) not null comment '释意ID',
   msw_id               char(32) comment '单词ID',
   explains             varchar(256) comment '释意',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   primary key (mwe_id)
);

alter table mark_word_explains comment '标记单词释意';

/*==============================================================*/
/* Table: read_sentence_list                                    */
/*==============================================================*/
create table read_sentence_list
(
   rsl_id               char(32) not null comment '句子播单ID',
   rsl_name             varchar(64) comment '播单名称',
   user_id              char(32) comment '用户ID',
   finish_rate          int comment '完成率',
   strange_rate         int comment '生词率',
   rsl_status           char(1) default '0' comment '播单状态【0;待学习 1:正在学习 2:曾经学习】',
   sort_type            char(1) comment '播单排序类型【1:按时间排序 2:乱序】',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   primary key (rsl_id)
);

alter table read_sentence_list comment '句子播单';

/*==============================================================*/
/* Table: read_sentence_rel                                     */
/*==============================================================*/
create table read_sentence_rel
(
   rsr_id               char(32) not null comment '播单句子关系ID',
   rsl_id               char(32) not null comment '播单ID',
   esl_id               char(32) not null comment '句子ID',
   esl_sort             int,
   primary key (rsr_id)
);

alter table read_sentence_rel comment '播单句子关系表';

/*==============================================================*/
/* Table: read_sentence_time                                    */
/*==============================================================*/
create table read_sentence_time
(
   rst_id               char(32) not null comment '播单学习时间ID',
   rsl_id               char(32) comment '句子播单ID',
   start_time           timestamp comment '阅读开始时间',
   end_time             timestamp comment '阅读结束时间',
   primary key (rst_id)
);

alter table read_sentence_time comment '播单学习时间';

/*==============================================================*/
/* Table: read_word_list                                        */
/*==============================================================*/
create table read_word_list
(
   rwl_id               char(32) not null comment '播单单词ID',
   rsl_id               char(32) comment '播单ID',
   esl_id               char(32) comment '句子ID',
   msw_id               char(32) comment '单词ID',
   singel_word          varchar(32) comment '单词',
   start                int comment '单词开始位置',
   end                  int comment '单词结束位置',
   strange              char(1) comment '是否生词【0:是 1:否】',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   primary key (rwl_id)
);

alter table read_word_list comment '播单单词';

/*==============================================================*/
/* Table: read_word_list_history                                */
/*==============================================================*/
create table read_word_list_history
(
   rwl_id               char(32) not null comment '播单单词ID',
   rsl_id               char(32) comment '播单ID',
   esl_id               char(32) comment '句子ID',
   msw_id               char(32) comment '单词ID',
   singel_word          varchar(32) comment '单词',
   start                int comment '单词开始位置',
   end                  int comment '单词结束位置',
   strange              char(1) comment '是否生词【0:是 1:否】',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   primary key (rwl_id)
);

alter table read_word_list_history comment '播单单词历史';

/*==============================================================*/
/* Table: sentence_word_rel                                     */
/*==============================================================*/
create table sentence_word_rel
(
   swl_id               char(32) not null comment '句子单词关联ID',
   esl_id               char(32) comment '句子ID',
   msw_id               char(32) comment '单词ID',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (swl_id)
);

alter table sentence_word_rel comment '句子和单词关联关系';

