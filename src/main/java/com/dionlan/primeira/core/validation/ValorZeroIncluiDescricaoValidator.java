package com.dionlan.primeira.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraint) {
		this.valorField = constraint.valorField();
		this.descricaoField = constraint.descricaoField();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
	}
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		
		boolean valido = true;
		/**
		 * BeanUtils = classe utilitária para invocar dinamicamente os métodos em tempo de execução na JVM (ReflectionAPI)
		 * Se a validação for para a classe Restaurante, o objetoValidacao.getClass() será do tipo Restaurante e 
		 * o valorFiel buscado da constraint valorField = "taxaFrete". Resumo: recupera a taxaFrete do tipo Restaurante
		 */
		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField).getReadMethod()
					.invoke(objetoValidacao);
			
			String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField).getReadMethod()
					.invoke(objetoValidacao);
			
			if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
			
			return valido;
			
		}catch(Exception e) {
			throw new ValidationException(e);
		
		}
	}
}
