package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

/**
 * "@SpringBootTest:" indica que um parametro tipo teste (webEnvironment =
 * WebEnvironment.RANDOM_PORT): busca por uma porta quando a padrão esta sendo
 * usada.
 * 
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	//Anotação indica que o método deve ser executado primeiro
	// 0L: indica um Long que para id que vai gerá-lo.
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278"));

	}
	
	@Test
	@DisplayName("Retorna um usuário 😊")
	public void deveRetornaUmUsuario() 
	{
		Optional <Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}
	
	//assertEquals: recebe dois parâmetros e os comparala e retorna um valor verdadeiro caso sejam iguais.
	@Test
	@DisplayName("Retorna Três usuário 😊")
	public void deveRetornaTresUsuario() 
	{
		List <Usuario> listaDeUsuario = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuario.size());
		assertTrue(listaDeUsuario.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuario.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuario.get(2).getNome().equals("Adriana da Silva"));
	}
	
}
