
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_ADMIN', 'Administrador') -- loga horas, controla sistema
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_EMPLOYEE', 'Colaborador') -- loga horas
insert into role values (HIBERNATE_SEQUENCE.nextval, 1, getdate(), 'ROLE_MANAGER', 'Gestor') -- controla sistema, visualiza relátórios

INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'naresh@naresh.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'bruno@bruno.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'wesley@wesley.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'zeller@zeller.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'joao@joao.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'maria@maria.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'thais@thais.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'pedro@pedro.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'amanda@amanda.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')
INSERT INTO `USER` VALUES(HIBERNATE_SEQUENCE.nextval, 1, getDate(), 1, 1, 1, 'carlos@carlos.com', 1, '$2a$16$pzxobnpJsCrSiXV.t97iweXy3yytjsPUN.8IgI/Cokx6KxXbg65H.')

insert into user_role values (select id from `user` where email='naresh@naresh.com', select id from role where role_name='Gestor')
insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Gestor')
insert into user_role values (select id from `user` where email='bruno@bruno.com', select id from role where role_name='Administrador')
insert into user_role values (select id from `user` where email='bruno@bruno.com', select id from role where role_name='Gestor')
insert into user_role values (select id from `user` where email='wesley@wesley.com', select id from role where role_name='Gestor')
insert into user_role values (select id from `user` where email='zeller@zeller.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='bruno@bruno.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='wesley@wesley.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='joao@joao.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='maria@maria.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='thais@thais.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='pedro@pedro.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='amanda@amanda.com', select id from role where role_name='Colaborador')
insert into user_role values (select id from `user` where email='carlos@carlos.com', select id from role where role_name='Colaborador')

insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Trivedi', 'Naresh', 12345678901, null, null, (select id from user where email='naresh@naresh.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Wesley', 'José', 12345678901, null, (select id from employee where last_name='Trivedi'), (select id from user where email='wesley@wesley.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Holanda', 'Bruno', 12345678901, null, (select id from employee where last_name='Wesley'), (select id from user where email='bruno@bruno.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Zeller', 'José', 12345678901, null, (select id from employee where last_name='Wesley'), (select id from user where email='zeller@zeller.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Silva', 'João', 12345678901, null, (select id from employee where last_name='Holanda'), (select id from user where email='joao@joao.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Souza', 'Maria', 12345678901, null, (select id from employee where last_name='Zeller'), (select id from user where email='maria@maria.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Oliveira', 'Thais', 12345678901, null, (select id from employee where last_name='Holanda'), (select id from user where email='thais@thais.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Rafael', 'Pedro', 12345678901, null, (select id from employee where last_name='Holanda'), (select id from user where email='pedro@pedro.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Freitas', 'Amanda', 12345678901, null, (select id from employee where last_name='Zeller'), (select id from user where email='amanda@amanda.com'))
insert into employee values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 'Rodrigues', 'Carlos', 12345678901, null, (select id from employee where last_name='Zeller'), (select id from user where email='carlos@carlos.com'))

insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 0, 'Hora Extra', 'Hora Extra', 2, null, null)
insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 0, 'Banco de Horas', 'Banco de Horas', 2, null, null)
insert into HOUR_TYPE values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 1, 'Ambos', 'Hora Extra', 2, 2, 'Banco de Horas')

insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='wesley@wesley.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='zeller@zeller.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='joao@joao.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='maria@maria.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='thais@thais.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='pedro@pedro.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='amanda@amanda.com'), (select id from hour_type where description like '%Banco de Horas%'))
insert into BASE_HOUR_CALCULATION values(HIBERNATE_SEQUENCE.nextval, 1, getDate(), getDate(), 20, 50, 10000, 100, 8, (select e.id from employee e inner join user u on e.user_id = u.id where u.email='carlos@carlos.com'), (select id from hour_type where description like '%Banco de Horas%'))

--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='bruno@bruno.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='zeller@zeller.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='wesley@wesley.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='joao@joao.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='maria@maria.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='thais@thais.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='pedro@pedro.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='amanda@amanda.com'))
--insert into BANKED_HOURS values(HIBERNATE_SEQUENCE.nextval,1,getDate(),0,(select e.id from employee e inner join user u on e.user_id = u.id where u.email='carlos@carlos.com'))

insert into PARAMETER values(HIBERNATE_SEQUENCE.nextval, 1, getdate(), 6, null, 20, 50, 12, '01:30:00', 100, (select id from HOUR_TYPE where first = 'Hora Extra' and both = 1), (select id from HOUR_TYPE where first = 'Banco de Horas' and both = 0), (select id from HOUR_TYPE where first = 'Hora Extra' and both = 0))

-- Requests
INSERT INTO REQUEST VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), NULL, 'Solicitacao realizada pelo Zeller', NULL,  NULL, NULL, getDate() + 1, 1, 1, NULL, (select id from employee where last_name='Zeller'))
-- Bruno
INSERT INTO REQUEST VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), NULL, 'Solicitacao realizada pelo Holanda', NULL,  NULL, NULL, getDate() + 1, 1, 1, NULL, (select id from employee where last_name='Holanda'))

-- Thelephones
INSERT INTO TELEPHONE (ID, ACTIVE, CREATION_DATE, NUMBER, TYPE, EMPLOYEE_ID) VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), 46778899, 'Fixo', (select id from employee where last_name='Holanda'))
INSERT INTO TELEPHONE (ID, ACTIVE, CREATION_DATE, NUMBER, TYPE, EMPLOYEE_ID) VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), 49700555, 'Trabalho', (select id from employee where last_name='Holanda'))
INSERT INTO TELEPHONE (ID, ACTIVE, CREATION_DATE, NUMBER, TYPE, EMPLOYEE_ID) VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), 998221122, 'Móvel', (select id from employee where last_name='Holanda'))
INSERT INTO TELEPHONE (ID, ACTIVE, CREATION_DATE, NUMBER, TYPE, EMPLOYEE_ID) VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), 46776543, 'Casa', (select id from employee where last_name='Zeller'))
INSERT INTO TELEPHONE (ID, ACTIVE, CREATION_DATE, NUMBER, TYPE, EMPLOYEE_ID) VALUES (HIBERNATE_SEQUENCE.nextval, 1, getDate(), 998229123, 'Móvel', (select id from employee where last_name='Zeller'))

