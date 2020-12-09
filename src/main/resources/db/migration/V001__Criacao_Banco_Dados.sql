create table Categoria(
	id int not null auto_increment,
	nome varchar(255) not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table CategoriaCelular(
	id int not null auto_increment,
	nome varchar(255) not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Pais(
	id int not null auto_increment,
	nome varchar(255) not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Estado(
	id int not null auto_increment,
	nome varchar(255) not null,
	sigla char(2) not null,
	id_pais int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Cidade(
	id int not null auto_increment,
	nome varchar(255) not null,
	id_estado int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Equipamento(
	id int not null auto_increment,
	nome varchar(255) not null,
    id_endereco int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Alerta(
	id int not null auto_increment,
	 `data` date NOT NULL DEFAULT CURRENT_TIMESTAMP,
    hora time NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_equipamento int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table ClienteAdministrador(
	id int not null auto_increment,
	nome varchar(255) not null,
	cpf varchar(255) not null,
	telefone varchar(255) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Endereco(
	id int not null auto_increment,
    nome varchar(255) not null,
	rua varchar(255) not null,
	numero varchar(45) not null,
	cep varchar(15) not null,
	bairro varchar(255) not null,
	latitude decimal(10,6) not null,
	longitude decimal(10,6) not null,
	id_cidade int not null,
	id_clienteadministrador int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table ClienteFuncionario(
	id int not null auto_increment,
	nome varchar(255) not null,
	cpf varchar(255) not null,
	telefone varchar(255) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	id_clienteadministrador int not null,
	id_categoriacelular int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Veiculo(
	id int not null auto_increment,
    nome varchar(255) not null,
    cor varchar(255) not null,
    placa varchar(255) not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;

create table Funcionario(
	id int not null auto_increment,
    nome varchar(255) not null,
    login varchar(255) not null,
    senha varchar(255) not null,
    id_categoria int not null,
    id_veiculo int not null,
	PRIMARY KEY(id)
) ENGINE = InnoDB DEFAULT CHARSET=UTF8;