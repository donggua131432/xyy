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
   ecd_id               char(32) not null comment 'Ӣ���ʵ�Id',
   single_word          varchar(32) comment '����',
   uk_phonetic          varchar(32) comment 'Ӣʽ����',
   us_phonetic          varchar(32) comment '��ʽ����',
   translation          varchar(512) comment '����',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (ecd_id)
);

alter table en_cn_dict comment 'Ӣ���ʵ�';

/*==============================================================*/
/* Table: en_sentence_lib                                       */
/*==============================================================*/
create table en_sentence_lib
(
   esl_id               char(32) not null comment '����ID',
   user_id              char(32) comment '�û�ID',
   sentence_content     varchar(512) comment '��������',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (esl_id)
);

alter table en_sentence_lib comment 'Ӣ����ӿ�';

/*==============================================================*/
/* Table: mark_single_word                                      */
/*==============================================================*/
create table mark_single_word
(
   msw_id               char(32) not null comment '��ǵ���Id',
   user_id              char(32) comment '�û�ID',
   singel_word          varchar(32) comment '����',
   phonetic             varchar(32) comment '����',
   consult_status       char(1) default '0' comment '�Ƿ���ġ�0:δ����  1:�Ѳ��ġ�',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '����ʱ��',
   update_time          timestamp not null default CURRENT_TIMESTAMP comment '�޸�ʱ��',
   primary key (msw_id)
);

alter table mark_single_word comment '��ǵ���';

/*==============================================================*/
/* Table: mark_word_explains                                    */
/*==============================================================*/
create table mark_word_explains
(
   mwe_id               char(32) not null comment '����ID',
   msw_id               char(32) comment '����ID',
   explains             varchar(256) comment '����',
   create_time          timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (mwe_id)
);

alter table mark_word_explains comment '��ǵ�������';

/*==============================================================*/
/* Table: read_sentence_list                                    */
/*==============================================================*/
create table read_sentence_list
(
   rsl_id               char(32) not null comment '���Ӳ���ID',
   rsl_name             varchar(64) comment '��������',
   user_id              char(32) comment '�û�ID',
   finish_rate          int comment '�����',
   strange_rate         int comment '������',
   rsl_status           char(1) default '0' comment '����״̬��0;��ѧϰ 1:����ѧϰ 2:����ѧϰ��',
   sort_type            char(1) comment '�����������͡�1:��ʱ������ 2:����',
   create_time          timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (rsl_id)
);

alter table read_sentence_list comment '���Ӳ���';

/*==============================================================*/
/* Table: read_sentence_rel                                     */
/*==============================================================*/
create table read_sentence_rel
(
   rsr_id               char(32) not null comment '�������ӹ�ϵID',
   rsl_id               char(32) not null comment '����ID',
   esl_id               char(32) not null comment '����ID',
   esl_sort             int,
   primary key (rsr_id)
);

alter table read_sentence_rel comment '�������ӹ�ϵ��';

/*==============================================================*/
/* Table: read_sentence_time                                    */
/*==============================================================*/
create table read_sentence_time
(
   rst_id               char(32) not null comment '����ѧϰʱ��ID',
   rsl_id               char(32) comment '���Ӳ���ID',
   start_time           timestamp comment '�Ķ���ʼʱ��',
   end_time             timestamp comment '�Ķ�����ʱ��',
   primary key (rst_id)
);

alter table read_sentence_time comment '����ѧϰʱ��';

/*==============================================================*/
/* Table: read_word_list                                        */
/*==============================================================*/
create table read_word_list
(
   rwl_id               char(32) not null comment '��������ID',
   rsl_id               char(32) comment '����ID',
   esl_id               char(32) comment '����ID',
   msw_id               char(32) comment '����ID',
   singel_word          varchar(32) comment '����',
   start                int comment '���ʿ�ʼλ��',
   end                  int comment '���ʽ���λ��',
   strange              char(1) comment '�Ƿ����ʡ�0:�� 1:��',
   create_time          timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (rwl_id)
);

alter table read_word_list comment '��������';

/*==============================================================*/
/* Table: read_word_list_history                                */
/*==============================================================*/
create table read_word_list_history
(
   rwl_id               char(32) not null comment '��������ID',
   rsl_id               char(32) comment '����ID',
   esl_id               char(32) comment '����ID',
   msw_id               char(32) comment '����ID',
   singel_word          varchar(32) comment '����',
   start                int comment '���ʿ�ʼλ��',
   end                  int comment '���ʽ���λ��',
   strange              char(1) comment '�Ƿ����ʡ�0:�� 1:��',
   create_time          timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (rwl_id)
);

alter table read_word_list_history comment '����������ʷ';

/*==============================================================*/
/* Table: sentence_word_rel                                     */
/*==============================================================*/
create table sentence_word_rel
(
   swl_id               char(32) not null comment '���ӵ��ʹ���ID',
   esl_id               char(32) comment '����ID',
   msw_id               char(32) comment '����ID',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '����ʱ��',
   primary key (swl_id)
);

alter table sentence_word_rel comment '���Ӻ͵��ʹ�����ϵ';

