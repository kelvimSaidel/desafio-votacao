create table usuario
(
id_usuario integer primary key auto_increment,
nome_usuario varchar2(100),
cpf integer
);


create table pauta
(
id_pauta integer primary key auto_increment,
fk_id_usuario integer references usuario(id_usuario),
nome_pauta varchar2(100)
);


create table sessao_votacao
(
id_sessao integer primary key auto_increment,
fk_id_pauta integer references pauta(id_pauta),
sim integer,
nao integer,
status varchar2(1) check status in ('A','D'), -- (A) ativado ou (D) desativado
dt_abertura timeStamp,
dt_fechamento timeStamp
);

