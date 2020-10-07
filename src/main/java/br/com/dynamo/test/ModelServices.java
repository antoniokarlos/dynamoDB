package br.com.dynamo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModelServices {
	
	@Autowired
	private ModelRepository repo;
	
	public Page<Model> retornaRegistros(Pageable paginacao){
		
		Page<Model> pagina = repo.findAll(paginacao);
		
		List<Model> results = pagina.getContent();
		
		return pagina;
	}
}
