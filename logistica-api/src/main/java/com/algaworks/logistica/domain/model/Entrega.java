package com.algaworks.logistica.domain.model;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.logistica.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@ManyToOne
	private Cliente cliente ;
	
	@Embedded
	private Destinatario destinatario ; 
	
	private BigDecimal taxa;

	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	private OffsetDateTime dataPedido;

	private OffsetDateTime dataFinalizacao;
	
	
}
