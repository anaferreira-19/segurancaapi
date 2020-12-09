INSERT INTO Categoria(nome) VALUES ('Vigilante'), ('Monitorador');

INSERT INTO CategoriaCelular(nome) VALUES ('Administrador'), ('Funcionário');

INSERT INTO Pais(nome) VALUES ('Brasil');

INSERT INTO Estado(nome, sigla, id_pais) VALUES ('São Paulo', 'SP', 1);

INSERT INTO Cidade(nome, id_estado) VALUES ('Lençóis Paulista', 1), ('Agudos', 1), ('Macatuba', 1), ('Bauru', 1);

INSERT INTO ClienteAdministrador(nome, cpf, telefone, email, senha) VALUES ('Marcelo', '999.999.999-01', '3264-3915', 'admin@admin', '123456');  

INSERT INTO Endereco(rua, nome, numero, cep, bairro, latitude, longitude, id_cidade, id_clienteadministrador) VALUES ('Rua São Paulo', 'Livraria Livros Novos', '201', '18681-000', 'Mamedina',-22.597543,-48.794383, 1, 1), ('Rua Bartolomeu Bueno Da Silva', 'Davoi', '383', '18680-120', 'Mamedina',-22.59650099812379,-48.78954739078372, 1, 1);

INSERT INTO Equipamento(nome, id_endereco) VALUES ('Sensor de Movimento', 1);

INSERT INTO Veiculo(nome, cor, placa) VALUES ('Nenhum', 'Nenhum', 'Nenhum'), ('Fiat Palio', 'Cinza', 'AAA-0000');

INSERT INTO Funcionario(nome, login, senha, id_categoria, id_veiculo) VALUES ('Ana', 'ui120@gmail.com', '123456', 2, 1), ('Carla', 'ui555@gmail.com', '123456', 1, 2),('Jorge Marcos Narida Oliveira Carlos', 'ui555@gmail.com', '123456', 2, 1);

INSERT INTO Equipamento(nome, id_endereco) VALUES ('Sensor de Movimento', 1);

INSERT INTO Alerta(id_equipamento) VALUES (1);