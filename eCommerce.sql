create table usuario(
	idUsuario serial primary key,
	usuario varchar(100),
	senha varchar(100),
	email varchar(100),
	ativo boolean,
	credencial varchar(100)
);

create table endereco(
	idendereco serial primary key,
	logradouro varchar(100),
	numero numeric,
	bairro varchar(100),
	cidade varchar(100),
	cep numeric	
);

create table cliente(
	idCliente serial primary key,	
	nome varchar(100),
	rg varchar(100),
	cpf varchar(100),
	telefone varchar(100),
	celular varchar(100),
	idUsuarioFk int references usuario (idUsuario),
	idEnderecoFk int references endereco(idEndereco)
);

create table funcionario(
	idfuncionario serial primary key,	
	nome varchar(100),
	rg varchar(100),
	cpf varchar(100),
	telefone varchar(100),
	celular varchar(100),
	idUsuarioFk int references usuario (idUsuario),
	idEnderecoFk int references endereco(idEndereco)
);


insert into usuario (usuario,senha,email,ativo,credencial) values ('funcionario1','123','email@gmail.com',true,'Funcionario');
insert into usuario (usuario,senha,email,ativo,credencial) values ('adm','adm','etapa.arthur@gmail.com',true,'Administrador');


insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente2','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente3','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente4','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente5','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente6','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente7','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente8','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente9','123','email@gmail.com',true,'Cliente');
insert into usuario (usuario,senha,email,ativo,credencial) values ('cliente10','123','email@gmail.com',true,'Cliente');

create table categoria(
	idCategoria serial primary key,
	nome varchar(100)
);

insert into categoria (nome) values('Brinquedos');
insert into categoria (nome) values('Natal');

create table produto(
	idProduto serial primary key,
	nome varchar(100),	
	idCategoriaFk int references categoria (idCategoria),	
	valorCompra numeric,
	valorVenda numeric,
	imagem varchar(100),

	descricao varchar(1000)		
);
	
	
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Barraca de Bolinhas',1,1000,1250,'barracaDeBolinhas.jpg','Barraca de bolinhas octogonal com 3 metros de diâmetro e 3 metros de altura . Com garantia de 1 ano e dois sacos de bolinhas para encher o brinquedo. ');	
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao)values ('Cama Elástica Colorida',1,1100,1375,'camaElasticaColorida.jpg','Cama Elástica com 1 metro de altura, suportes para até 250 quilos com diâmetro de 3 metros. Feita de polietileno com fibras reforçadas. Material da estrutura é de inox. ');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Campo de Futebol ',1,800,1000,'campoFutebol.jpg','Campo de futebol com 3 metros de largura, 3 metros de altura  e 2 metros de comprimento. Com garantia de 1 ano é necessário encher com ar.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Castelo com Bolinhas',1,950,1187.5,'casteloComBolinhas.jpg','Castelo com Bolinhas tem 3 metros de largura, 2 metros de altura  e 2 metros de comprimento. Com garantia de 1 ano é necessário encher com ar e acompanha 2 sacos com bolinhas para encher o brinquedo após cheio de ar.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Escorregador',1,1200,1500,'escorrega.jpg','Escorregador com 1,5 metros de largura, 2 metros de altura  e 2 metros de comprimento. Com garantia de 1 ano é necessário encher com ar .');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Pula-pula Inflavel',1,1300,1625,'pulapulaInflavel.jpg','Pula-pula inflável  tem 2,5 metros de largura, 2 metros de altura  e 3 metros de comprimento. Com garantia de 1 ano é necessário encher com ar .');


insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Árvore de Natal 150cm',2,123,153.75,'arvore150cm.jpg','Árvore com 150 cm de polietileno com propriedades anti-aderente o que ajuda a evitar excesso de poeira e manchar em caso de queda de qualquer líquido sobre a mesma.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Mangueira Luminosa 10m',2,48,60,'mangueiraLuminosa.jpg','A mangueira liminosa com 240 lâmpadas de led é capaz de trabalhar sob grande variação de temperatura, que vai de -20ªC a 60ªC., tem a vantagem de oferecer maior durabilidade e economia, graças ao led. Pode ser aplicado em residências e áreas comerciais, servindo para decoração em geral. Além de produzir efeitos decorativos em fechadas, sacadas, entre outros, é muito utilizada para decorações natalinas tornando ambientes mais alegres e bonitos.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Papai Noel 50cm',2,120,150,'papaiNoel50cm.jpg','Papai Noel feito 100% de algodão com 50 cm de altura e uma corda de 2 metros para pendurar onde melhor desejar. Feito pela empresa FactoryDirect In com garantia de 1 ano .');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Enfeite de Acrílico 15cm',2,65,81.25,'enfeiteAcrilico.jpg','Em acrílico com botão de ligar e desligar as luzes led, acompanhado de uma fonte 12V-Bivolt com cabo de 1,3m. Altura: 15.00 cm, Largura: 4.00 cm, Comprimento: 27.00 cm, Peso: 400 g e sol e chuva podem danificar o enfeite.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Papai Noel Porcelana',2,56,70,'noelPorcelana.jpg','Papai Noel de porcelana com 25 cm de altura e iluminação led interna. Acompanhado com fonte 12V-Bivol.');
insert into produto (nome,idCategoriaFk,valorCompra,valorVenda,imagem,descricao) values ('Olaf 60cm',2,24,30,'olaf60cm.jpg','Display Olaf 60cm
Todos em MDF 6mm e acabamento sem bordas brancas. Garantia de 3 meses e não suporta chuva ou sol em excesso.');	


create table compra (
	idcompra serial primary key,
	datacompra date,
	horacompra time,
	valor numeric,
	formadepagamento varchar,
	idclientefk int references cliente (idCliente)	
);


create table ItemDeCompra(
	idItemDeCompra serial primary key,
	idproduto numeric,
	quantidade numeric,
	total numeric,
	idcomprafk int references compra(idcompra)
);

create table promocao(
	idproduto numeric,
	nome varchar,
	porcentagem numeric,
	ativo boolean
);

insert into promocao values ('1', 'Barraca de Bolinhas', 15 , false);

	





