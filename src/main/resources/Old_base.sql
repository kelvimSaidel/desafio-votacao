create table usuario
(
id_usuario integer primary key auto_increment,
nome_usuario varchar2(100),
cpf integer not null
);


create table pauta
(
id_pauta integer primary key auto_increment,
id_usuario integer references usuario(id_usuario),
nome_pauta varchar2(100)
);


create table sessao_votacao
(
id_sessao integer primary key auto_increment,
id_pauta integer references pauta(id_pauta),
sim integer,
nao integer,
status varchar2(1) check status in ('ABERTA','FECHADA'), -- (A) ativado ou (D) desativado
dt_abertura varchar2(25),
dt_fechamento varchar2(25),
tempoVigenciaEmMinutos varchar2(300)
);

