package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.service.UsuarioService;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class): ordem de execuçãoi
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	// Trabalha com as requisões:
	@Autowired
	private TestRestTemplate testRestTemplate;

	//
	@Autowired
	private UsuarioService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar um usuário")
	public void deveCriarUmUsuario() {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Paulo Antunes", "paulo_antunes@email.com.br", "13465278"));

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao,Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao, resposta.getBody().getNome());
		assertEquals(requisicao, resposta.getBody().getUsuario());
	}

	/*
	//
	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do usuário")
	public void naoDeveCriarDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Paulo Antunes", "paulo_antunes@email.com.br", "13465278"));

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Paulo Antunes", "paulo_antunes@email.com.br", "13465278"));

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());

	}

	// criar metodode teste put

	@Test
	@Order(3)
	@DisplayName("Listar todos os usuário")
	public void deveMostrarTodosrUsuario() 
	{
		usuarioService
				.cadastrarUsuario(new Usuario(0L, "Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123"));

		usuarioService
				.cadastrarUsuario(new Usuario(0L, "Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123"));
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuario/cadastrar", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}
	*/
}
