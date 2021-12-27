package com.dionlan.primeira.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.model.dto.RestauranteModel;

/**
 * Classe montadora de retaurante model converter a dto para entity
 * @author Dionlan
 *
 */
@Component
public class RestauranteModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper; //mapeia o que está recebendo de entrada - restaurante - para um objeto destino - RestauranteModel.class
	
	public RestauranteModel toModel(Restaurante restaurante) {
		
		return modelMapper.map(restaurante, RestauranteModel.class);
	}
	
	public List<RestauranteModel> toColletionModel(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
	
}
