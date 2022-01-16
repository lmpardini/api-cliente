package br.pardini.clienteapi.resources;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.pardini.clienteapi.entity.Cliente;
import br.pardini.clienteapi.repository.ClienteRepository;

@RestController
@RequestMapping(value="api")

public class ClienteController {
	
	//Instancia da classe ClienteRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Instancia da classe ModelMapper;
	@Autowired
	private ModelMapper modelMapper;
	
	//Metodo que lista os clientes já cadastrados no banco de dados;
	@GetMapping("/cliente")
	public List<Cliente> listaCliente(){
		return clienteRepository.findAll();
	}
	
	//Metodo que busca no banco um cliente através do seu Id/
	@GetMapping("/cliente/{id}")
	public Optional<Cliente> listaClientePorId(@PathVariable(value="id")Long id){
		return clienteRepository.findById(id);
	}
	
	//Metodo que cria um novo cliente no banco de dados;
	@PostMapping("/cliente")
	public Cliente salvarProduto(@RequestBody Cliente cliente){
		return clienteRepository.save(cliente);		
	}
	
	/*Metodo que deleta um usuário através do seu ID 
	 * Neste metodo ocorre uma verificação se o usuário existe, caso exista o cliente é deletado. Em caso 
	 * em qua não exista o ID, retorna uma mensagem de erro informando que não existe o Id;*/
	//Para deletar, é necessário ter permissões de admin;
	@DeleteMapping("/cliente/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteClientePorId(@PathVariable(value="id") Long id) {
		clienteRepository.findById(id)
		.map(cliente ->{
			clienteRepository.deleteById(cliente.getId());
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro -> Cadastro não encontrado")); 
	}
	
	/* 
	 * Metodo que atualiza um cadastro já existente. Nele ocorre uma verificação se o usuário existe. Em caso afirmativo, Ocorre a atualização
	 * dos dados. Atráves do ModelMapper ele atualiza apenas os dados que forem fornecidos na requisição, mantendo os outros sem alteração.
	 * */	
	@PutMapping("/cliente/{id}")    
	public void atualizarCliente(@PathVariable(value="id")Long id, @RequestBody Cliente cliente){
		 clienteRepository.findById(id)
            .map(clienteBase-> {
                modelMapper.map(cliente, clienteBase);
                clienteRepository.save(clienteBase);
                return Void.TYPE;
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
	}

}
