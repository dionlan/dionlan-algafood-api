package com.dionlan.primeira.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.model.dto.RestauranteModel;

@Configuration //instancia de modelmapper dentro do contexto do spring. Auxilia no mapeamento do Medelo para Entidate Java
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper ModelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);
		
		return modelMapper;
	}
}
