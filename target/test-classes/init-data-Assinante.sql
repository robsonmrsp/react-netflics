 ALTER SEQUENCE hibernate_sequence RESTART WITH 10001;

 TRUNCATE TABLE tenant CASCADE;
 TRUNCATE TABLE app_user CASCADE;
 TRUNCATE TABLE role CASCADE;
 TRUNCATE TABLE user_role CASCADE;

 INSERT INTO tenant (id, cnpj, corporate_name,  phone_number, logo, name) values (101,'','JSetup Developer', '997608620','','JSetup Developer');

 INSERT INTO role( id, id_tenant, authority, description)  VALUES (101, 101, 'ROLE_USER', 'Usuário do sistema');
 INSERT INTO app_user( id, id_tenant, enable, image, name, password, username, email) VALUES (101, 101, true, '', 'Usuário JSetup Comum', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'jsetup', 'contato@jsetup.com');
 INSERT INTO user_role(id_role, id_user) values (101, 101);

TRUNCATE TABLE ASSINANTE CASCADE;
INSERT INTO ASSINANTE 	( id, id_tenant
			,TELEFONE
			,CPF
			,NOME
			,OBSERVACAO
			,FOTO
			,CELULAR
			,RG
			)values(1, 101
			, 'telefone assinante1'
			, 'cpf assinante1'
			, 'nome assinante1'
			, 'observacao assinante1'
			, 'foto assinante1'
			, 'celular assinante1'
			, 'rg assinante1'
			);
INSERT INTO ASSINANTE 	( id, id_tenant
			,TELEFONE
			,CPF
			,NOME
			,OBSERVACAO
			,FOTO
			,CELULAR
			,RG
			)values(2, 101
			, 'telefone assinante2'
			, 'cpf assinante2'
			, 'nome assinante2'
			, 'observacao assinante2'
			, 'foto assinante2'
			, 'celular assinante2'
			, 'rg assinante2'
			);
INSERT INTO ASSINANTE 	( id, id_tenant
			,TELEFONE
			,CPF
			,NOME
			,OBSERVACAO
			,FOTO
			,CELULAR
			,RG
			)values(3, 101
			, 'telefone assinante3'
			, 'cpf assinante3'
			, 'nome assinante3'
			, 'observacao assinante3'
			, 'foto assinante3'
			, 'celular assinante3'
			, 'rg assinante3'
			);
INSERT INTO ASSINANTE 	( id, id_tenant
			,TELEFONE
			,CPF
			,NOME
			,OBSERVACAO
			,FOTO
			,CELULAR
			,RG
			)values(4, 101
			, 'telefone assinante4'
			, 'cpf assinante4'
			, 'nome assinante4'
			, 'observacao assinante4'
			, 'foto assinante4'
			, 'celular assinante4'
			, 'rg assinante4'
			);
INSERT INTO ASSINANTE 	( id, id_tenant
			,TELEFONE
			,CPF
			,NOME
			,OBSERVACAO
			,FOTO
			,CELULAR
			,RG
			)values(5, 101
			, 'telefone assinante5'
			, 'cpf assinante5'
			, 'nome assinante5'
			, 'observacao assinante5'
			, 'foto assinante5'
			, 'celular assinante5'
			, 'rg assinante5'
			);
