package br.pardini.clienteapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClienteApiApplication {
	
	/*Configuração para evitar que o ModelMapper faça alteração dos campos que não forem enviados na função atualizarCliente por
	 * null;
	 * e 
	
	*/
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteApiApplication.class, args);
	}

}
