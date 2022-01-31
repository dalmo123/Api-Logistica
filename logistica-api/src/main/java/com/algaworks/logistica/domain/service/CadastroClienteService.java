package com.algaworks.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.logistica.domain.exception.NegocioException;
import com.algaworks.logistica.domain.model.Cliente;
import com.algaworks.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar (Long clienteId) {
		//tratar se o consumidor passar o id invalido
		return clienteRepository.findById(clienteId)
			   .orElseThrow(() -> new NegocioException("Cliente nao encontrado")) ; 
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		//abaixo :: regra de negocio que verifica emails duplicados
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {
			throw new NegocioException("Ja existe um cliente cadastrado com esse email");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void Excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
