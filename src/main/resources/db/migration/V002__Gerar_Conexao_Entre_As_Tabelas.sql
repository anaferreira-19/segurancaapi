alter table Alerta add constraint FK_Alerta_Equipamento foreign key(id_equipamento) references Equipamento(id);
alter table ClienteFuncionario add constraint FK_ClienteFuncionario_CategoriaCelular foreign key(id_categoriacelular) references CategoriaCelular(id);
alter table ClienteFuncionario add constraint FK_ClienteFuncionario_ClienteAdministrador foreign key(id_clienteadministrador) references ClienteAdministrador(id);
alter table Endereco add constraint FK_Endereco_ClienteAdministrador foreign key(id_clienteadministrador) references ClienteAdministrador(id);
alter table Estado add constraint FK_Estado_Pais foreign key (id_pais) references Pais(id);
alter table Cidade add constraint FK_Cidade_Estado foreign key(id_estado) references Estado(id);
alter table Endereco add constraint FK_Endereco_Cidade foreign key(id_cidade) references Cidade(id);
alter table Equipamento add constraint FK_Equipamento_Endereco foreign key(id_endereco) references Endereco(id);
alter table Funcionario add constraint FK_Funcionario_Veiculo foreign key(id_veiculo) references Veiculo(id);
alter table Funcionario add constraint FK_Funcionario_Categoria foreign key(id_categoria) references Categoria(id);
