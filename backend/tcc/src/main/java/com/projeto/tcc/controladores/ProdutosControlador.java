package com.projeto.tcc.controladores;

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

import com.projeto.tcc.entidades.Produtos;
import com.projeto.tcc.servicos.ProdutosServico;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/produtos")
public class ProdutosControlador {

	@Autowired
	ProdutosServico produtosServico;

	@GetMapping
	public List<Produtos> listar() {
		return produtosServico.listar();
	}
    
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> buscarPeloCodigo(@PathVariable Long id) {
		Produtos produtos = produtosServico.buscarPeloCodigo(id);
		return produtos != null ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Produtos> salvar(@RequestBody Produtos produtos) {
		produtos = produtosServico.salvar(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtosServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produtos> atualizar(@PathVariable Long id, @RequestBody Produtos produtos) {
		produtos = produtosServico.atualizar(id, produtos);
		return ResponseEntity.ok().body(produtos);
	}

}
