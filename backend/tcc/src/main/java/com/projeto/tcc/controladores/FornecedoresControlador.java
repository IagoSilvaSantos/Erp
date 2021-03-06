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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tcc.entidades.Fornecedores;
import com.projeto.tcc.servicos.FornecedoresServico;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/fornecedores")
public class FornecedoresControlador {

	@Autowired
	FornecedoresServico fornecedoresServico;

	@GetMapping
	public List<Fornecedores> listar() {
		return fornecedoresServico.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedores> buscarPeloCodigo(@PathVariable Long id) {
		Fornecedores fornecedores = fornecedoresServico.buscarPeloCodigo(id);
		return fornecedores != null ? ResponseEntity.ok(fornecedores) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/filtrado")
	public ResponseEntity<List<Fornecedores>> findPagina(@RequestParam(required = false, name = "id") Long id, @RequestParam(required = false, name = "nomeFantasia") String nomeFantasia, 
			@RequestParam(required = false, name = "razaoSocial") String razaoSocial,
			@RequestParam(required = false, name = "representante") String representante,
			@RequestParam(required = false, name = "telefone") String telefone,
			@RequestParam(required = false, name = "cnpj") String cnpj) {
		List<Fornecedores> fornecedores = fornecedoresServico.buscarFiltrada(id, razaoSocial,nomeFantasia, representante, telefone,cnpj );
		return fornecedores != null ? ResponseEntity.ok(fornecedores) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Fornecedores> salvar(@RequestBody Fornecedores fornecedores) {
		fornecedores = fornecedoresServico.salvar(fornecedores);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedores);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		fornecedoresServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Fornecedores> atualizar(@PathVariable Long id, @RequestBody Fornecedores fornecedores) {
		fornecedores = fornecedoresServico.atualizar(id, fornecedores);
		return ResponseEntity.ok().body(fornecedores);
	}

}
