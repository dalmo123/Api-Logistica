package com.algaworks.logistica.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.logistica.domain.model.Cliente;
import com.algaworks.logistica.domain.repository.ClienteRepository;
import com.algaworks.logistica.domain.service.CadastroClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CadastroClienteService cadastroClienteService;
	
	@GetMapping
	public List<Cliente> listar() {		
		
		//return clienteRepository.findAll();
		return clienteRepository.findAll();
}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente)) msm coisa da L debaixo
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

// a parte comentada faz a msm coisa que o map, so que o map é + enxuto
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
//		if(cliente.isPresent()) {
//  	return ResponseEntity.ok(cliente.get());
		}
//		return ResponseEntity.notFound().build();
//	}	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // para retornar status 201
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente); para n botar regra de negocio no controlador criamos a classe cadastroClienteService para isso..
		return cadastroClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente>atualizar(@PathVariable
			Long clienteId,@Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId); // para atualizar o id em vez de criar
		//cliente = clienteRepository.save(cliente);   msm coisa do adicionar, salvando na classe em vez do repository
		cliente = cadastroClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		}
	
	@DeleteMapping ("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {																																	
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(clienteId); salvar na classe cadastroclienteservice																																						
		cadastroClienteService.Excluir(clienteId);
		
		return ResponseEntity.noContent().build(); //retorna codigo 204																																																								
	}










}