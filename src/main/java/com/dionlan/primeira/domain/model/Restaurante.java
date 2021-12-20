package com.dionlan.primeira.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dionlan.primeira.core.validation.Groups;
import com.dionlan.primeira.core.validation.Multiplo;
import com.dionlan.primeira.core.validation.ValorZeroIncluiDescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis") //implementando a regra: se o valor da taxaFrete == 0, incluir a descrição obrigatória: Frete Grátis!
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento realizado pelo banco de dados
	private Long id;

	//@NotNull não pode ser nulo
	//@NotEmpty //não pode ter vazio ""
	@NotBlank //não pode ser nulo, vazio ou em branco "", "       "
	@Column(nullable = false)
	private String nome;

	@NotNull
	@PositiveOrZero
	@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;


	//por padrão, as terminações das assossiações *ToOne será EAGER é carregada junto com a entidade (faz o select em cozinha), mesmo com o JsonIgnore anotado por usar a estratégia fetch padrão Eager *ToOne
	//@JsonIgnore
	//s@JsonIgnoreProperties("hibernateLazyInitializer") //consultas os restaurantes e cozinha fica null, é necessário ignorar por essa anotação, mas se precisar, instancia cozinha em tempo de execução e coloca em um proxy do hibernate
	//@NotBlank
	@Valid //valida em cascata na classe Cozinha para o id de cozinha não aceitar valores null
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne// sobrescrevendo com Lazy, o JPA só faz a consulta se precisar
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@JsonIgnore
	@Embedded //essa propriedade endereco é do tipo embedável, parte incorporada na entidade Restaurante
	private Endereco endereco;

	@JsonIgnore
	@OneToMany (mappedBy = "restaurante") //terminado em *ToMany usa a estratégia Lazy Loading
	private List<Produto> produtos = new ArrayList<>(); //lazy por padrão não faz o select e nem exibe na representação, mas se fizer o getPropriedade o JPA entende que deverá realizar a consulta para essa propriedade específica chamada

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	@JsonIgnore //se retirar essa anotação será utilizada as formas de pagamento que será apresentada na representação, logo será realizado os selects para todas as formas de pagamento
	@ManyToMany //terminado em *ToMany usa a estratégia Lazy Loading. Não faz o select em forma_pagamento
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) //tabela intermediária NxN entre Restaurante e Forma de Pagamento
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
}
