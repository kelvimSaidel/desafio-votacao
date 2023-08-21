create database sessao_votacao;

use sessao_votacao;


create table usuario
(
id_usuario integer primary key auto_increment,
nome_usuario varchar(100),
cpf integer not null
);


create table pauta
(
id_pauta integer primary key auto_increment,
id_usuario integer references usuario(id_usuario),
nome_pauta varchar(100)
);



select * from sessao;
#drop table sessao;
create table sessao
(
id_sessao integer primary key auto_increment,
id_pauta integer references pauta(id_pauta),
sim integer,
nao integer,
status varchar(8),
constraint ch_status check (status in ('ABERTA','FECHADA')), -- (A) ativado ou (D) desativado
dt_abertura varchar(25),
dt_fechamento varchar(25),
tempo_vigencia_em_minutos varchar(300)
);


create table registro_votos_usuarios
(id_registoVU integer primary key auto_increment,
id_usuario integer,
voto varchar(5),
id_pauta integer references pauta(id_pauta)
);

#truncate table usuario;
#truncate table pauta;
#truncate table sessao;
#truncate table registro_votos_usuarios;
#select * from usuario;
#drop table RegistroVotosUsuarios;
#select * from pauta



