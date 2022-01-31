package com.algaworks.logistica.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter 
@Entity //manipular tabela no bd mais facil 

public class Cliente {
	
	
	
	@EqualsAndHashCode.Include
	@Id // define a chave primaria da entidade 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra usar a forma nativa do bd, caso do mysql(incremento)
	private Long id;
	
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@Column (name = "fone") 
	@NotBlank
	@Size(max = 20)
	private String telefone;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
			

}
