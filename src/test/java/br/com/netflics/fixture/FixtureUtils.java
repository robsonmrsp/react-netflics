package br.com.netflics.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

import br.com.netflics.model.Linguagem;
import br.com.netflics.model.Avaliacao;
import br.com.netflics.model.Assinante;
import br.com.netflics.model.Genero;
import br.com.netflics.model.Critico;
import br.com.netflics.model.Filme;
import br.com.netflics.model.Visualizacao;
import br.com.netflics.model.Classificacao;
import br.com.netflics.model.Ator;
import br.com.netflics.model.User;
import br.com.netflics.model.Role;
import br.com.netflics.model.Permission;
import br.com.netflics.model.Group;
import br.com.netflics.model.Item;
public class FixtureUtils {

	public static void init() {
		Fixture.of(Linguagem.class).addTemplate("novo", new Rule() {
			{
				add("nome", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Avaliacao.class).addTemplate("novo", new Rule() {
			{
			}
		});
		Fixture.of(Assinante.class).addTemplate("novo", new Rule() {
			{
				add("telefone", regex("[a-z ]{5,15}"));
				add("cpf", regex("[a-z ]{5,15}"));
				add("nome", regex("[a-z ]{5,15}"));
				add("observacao", regex("[a-z ]{5,15}"));
				add("foto", regex("[a-z ]{5,15}"));
				add("celular", regex("[a-z ]{5,15}"));
				add("rg", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Genero.class).addTemplate("novo", new Rule() {
			{
				add("nome", regex("[a-z ]{5,15}"));
				add("descricao", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Critico.class).addTemplate("novo", new Rule() {
			{
				add("nome", regex("[a-z ]{5,15}"));
				add("empresa", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Filme.class).addTemplate("novo", new Rule() {
			{
				add("tituloOriginal", regex("[a-z ]{5,15}"));
				add("poster", regex("[a-z ]{5,15}"));
				add("sinopse", regex("[a-z ]{5,15}"));
				add("titulo", regex("[a-z ]{5,15}"));
				add("diretor", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Visualizacao.class).addTemplate("novo", new Rule() {
			{
				add("percentualAssistido", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Classificacao.class).addTemplate("novo", new Rule() {
			{
				add("nome", regex("[a-z ]{5,15}"));
				add("descricao", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Ator.class).addTemplate("novo", new Rule() {
			{
				add("biografia", regex("[a-z ]{5,15}"));
				add("nome", regex("[a-z ]{5,15}"));
				add("foto", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(User.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("username", regex("[a-z ]{5,15}"));
				add("email", regex("[a-z ]{5,15}"));
				add("password", regex("[a-z ]{5,15}"));
				add("image", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Role.class).addTemplate("novo", new Rule() {
			{
				add("authority", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Permission.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
				add("operation", regex("[a-z ]{5,15}"));
				add("tagReminder", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Group.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Item.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("itemType", regex("[a-z ]{5,15}"));
				add("identifier", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});

	}
}