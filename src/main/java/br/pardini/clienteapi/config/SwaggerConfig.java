package br.pardini.clienteapi.config;

import org.springdoc.core.GroupedOpenApi;

import org.springframework.context.annotation.Bean;

//Configuração do Swagger;

public class SwaggerConfig {
	
	@Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("cliente-api")
	              .pathsToMatch("/clienteapi/**")
	              .build();
	  }
	

   
	
	
	

}
