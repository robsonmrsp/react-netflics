 ALTER SEQUENCE hibernate_sequence RESTART WITH 10001;

 TRUNCATE TABLE tenant CASCADE;
 TRUNCATE TABLE app_user CASCADE;
 TRUNCATE TABLE role CASCADE;
 TRUNCATE TABLE user_role CASCADE;

 INSERT INTO tenant (id, cnpj, corporate_name,  phone_number, logo, name) values (101,'','JSetup Developer', '997608620','','JSetup Developer');

 INSERT INTO role( id, id_tenant, authority, description)  VALUES (101, 101, 'ROLE_USER', 'Usuário do sistema');
 INSERT INTO app_user( id, id_tenant, enable, image, name, password, username, email) VALUES (101, 101, true, '', 'Usuário JSetup Comum', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'jsetup', 'contato@jsetup.com');
 INSERT INTO user_role(id_role, id_user) values (101, 101);

TRUNCATE TABLE ATOR CASCADE;
INSERT INTO ATOR 	( id, id_tenant
			,BIOGRAFIA
			,NOME
			,FOTO
			)values(1, 101
			, 'biografia ator1'
			, 'nome ator1'
			, 'foto ator1'
			);
INSERT INTO ATOR 	( id, id_tenant
			,BIOGRAFIA
			,NOME
			,FOTO
			)values(2, 101
			, 'biografia ator2'
			, 'nome ator2'
			, 'foto ator2'
			);
INSERT INTO ATOR 	( id, id_tenant
			,BIOGRAFIA
			,NOME
			,FOTO
			)values(3, 101
			, 'biografia ator3'
			, 'nome ator3'
			, 'foto ator3'
			);
INSERT INTO ATOR 	( id, id_tenant
			,BIOGRAFIA
			,NOME
			,FOTO
			)values(4, 101
			, 'biografia ator4'
			, 'nome ator4'
			, 'foto ator4'
			);
INSERT INTO ATOR 	( id, id_tenant
			,BIOGRAFIA
			,NOME
			,FOTO
			)values(5, 101
			, 'biografia ator5'
			, 'nome ator5'
			, 'foto ator5'
			);
