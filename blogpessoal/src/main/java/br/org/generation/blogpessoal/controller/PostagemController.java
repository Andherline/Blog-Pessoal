package br.org.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll()
	{
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	/*
	- Criação de uma varíavel de caminho : "/[variavel]"
	- @PathVariable: pega do caminho e retorna para o atributo id do tipo long
	- Método que retorna um elemento da "tabela" através da passagem da chave primaria(id)
	  pelo caminho passado no enderenço.
	 *  
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id)
	{
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//("/titulo/{titulo}"): primeiro está passando a indicação e depois a variavel
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo)
	{
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	/*
	 * - Método que realiza a  inserção de dados na tabela
	 * - Semelhante ao "insert into" do SQL.
	   - @RequestBody: pega o conteúdo da requisição  e o repassa para o 
	    			  atributo tipo postagem(objeto vindo da classe postagem).
	   - status(HttpStatus.CREATED): retorno 201 para confirma a criação criação
	   - body(postagemRepository.save(postagem))
	 *  
	 */
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem)
	{
		//status(HttpStatus.CREATED): retorno 201 para confirma a criação criação
		//body(postagemRepository.save(postagem));
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	/*
	 * 
	 - Método para atualizar os dados da tabela
	  -Desafio: fazer checagem antes de fazer atualização
	 *
	 */
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem)
	{
		//status(HttpStatus.OK): 
		return ResponseEntity.status(HttpStatus.OK)
				.body(postagemRepository.save(postagem));
	}
	
	/*
	- Método para deleção de uma postagem
	- Retorno dos status em caso de deleção é 200
	- Desafio: apagar somente o que exitem na tabela
	 * 
	 */
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id)
	{
		postagemRepository.deleteById(id);
	}
}
