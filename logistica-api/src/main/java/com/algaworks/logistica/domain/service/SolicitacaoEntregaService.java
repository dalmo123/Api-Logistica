package com.algaworks.logistica.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.logistica.domain.model.Cliente;
import com.algaworks.logistica.domain.model.Entrega;
import com.algaworks.logistica.domain.model.StatusEntrega;
import com.algaworks.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CadastroClienteService cadastroClienteService ;
	private EntregaRepository entregaRepository ;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId()) ;
				
		entrega.setCliente(cliente); // para devolver o cliente com as propriedades cheias.
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
			
		return entregaRepository.save(entrega);
	}
}
