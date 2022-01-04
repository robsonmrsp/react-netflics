 ALTER SEQUENCE hibernate_sequence RESTART WITH 10001;

 TRUNCATE TABLE tenant CASCADE;
 TRUNCATE TABLE app_user CASCADE;
 TRUNCATE TABLE role CASCADE;
 TRUNCATE TABLE user_role CASCADE;

 INSERT INTO tenant (id, cnpj, corporate_name,  phone_number, logo, name) values (101,'','JSetup Developer', '997608620','','JSetup Developer');

 INSERT INTO role( id, id_tenant, authority, description)  VALUES (101, 101, 'ROLE_USER', 'Usuário do sistema');
 INSERT INTO app_user( id, id_tenant, enable, image, name, password, username, email) VALUES (101, 101, true, '', 'Usuário JSetup Comum', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'jsetup', 'contato@jsetup.com');
 INSERT INTO user_role(id_role, id_user) values (101, 101);

TRUNCATE TABLE FILME CASCADE;
INSERT INTO FILME 	( id, id_tenant
			,TITULO_ORIGINAL
			,POSTER
			,SINOPSE
			,TITULO
			,DIRETOR
			)values(1, 101
			, 'tituloOriginal filme1'
			, 'poster filme1'
			, 'sinopse filme1'
			, 'titulo filme1'
			, 'diretor filme1'
			);
INSERT INTO FILME 	( id, id_tenant
			,TITULO_ORIGINAL
			,POSTER
			,SINOPSE
			,TITULO
			,DIRETOR
			)values(2, 101
			, 'tituloOriginal filme2'
			, 'poster filme2'
			, 'sinopse filme2'
			, 'titulo filme2'
			, 'diretor filme2'
			);
INSERT INTO FILME 	( id, id_tenant
			,TITULO_ORIGINAL
			,POSTER
			,SINOPSE
			,TITULO
			,DIRETOR
			)values(3, 101
			, 'tituloOriginal filme3'
			, 'poster filme3'
			, 'sinopse filme3'
			, 'titulo filme3'
			, 'diretor filme3'
			);
INSERT INTO FILME 	( id, id_tenant
			,TITULO_ORIGINAL
			,POSTER
			,SINOPSE
			,TITULO
			,DIRETOR
			)values(4, 101
			, 'tituloOriginal filme4'
			, 'poster filme4'
			, 'sinopse filme4'
			, 'titulo filme4'
			, 'diretor filme4'
			);
INSERT INTO FILME 	( id, id_tenant
			,TITULO_ORIGINAL
			,POSTER
			,SINOPSE
			,TITULO
			,DIRETOR
			)values(5, 101
			, 'tituloOriginal filme5'
			, 'poster filme5'
			, 'sinopse filme5'
			, 'titulo filme5'
			, 'diretor filme5'
			);
