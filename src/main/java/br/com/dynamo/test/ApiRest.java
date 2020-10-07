package br.com.dynamo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRest {
	
	@Autowired
	private ModelServices service;
	
	@GetMapping
	public Page<Model> busca(){
		Pageable page = PageRequest.of(0, 10);
		return service.retornaRegistros(page);
	}

}
