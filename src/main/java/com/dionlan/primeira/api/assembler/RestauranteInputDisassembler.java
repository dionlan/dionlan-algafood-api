package com.dionlan.primeira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.model.input.RestauranteInputModel;

@Component
public class RestauranteInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Conversão da classe de Input (entrada dos dados) para um objeto de domínio restaurante
	 */
	public Restaurante toDomainObject(RestauranteInputModel restauranteInput) {
		
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInputModel restauranteInput, Restaurante restaurante) {
		/*no momento da atualização, que o id da cozinha atualizada sera atribuida ao id da cozinha ja existente,
		*ou seja, permitir que seja alterada a cozinha para determinado restaurante */
		restaurante.setCozinha(new Cozinha());
		modelMapper.map(restauranteInput, restaurante);
	}
}
