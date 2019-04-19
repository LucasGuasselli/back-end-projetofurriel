package com.lucasguasselli.projetofurriel.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

@RestController
@RequestMapping(value="/postosGraduacoes")
public class PostoGraduacaoResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<PostoGraduacao> listar() {
		PostoGraduacao soldadoEV = new PostoGraduacao("Soldado EV", 854.00, 37.576 );
		PostoGraduacao soldadoEP = new PostoGraduacao("Soldado EP", 1758.00, 65.032);
		
		List<PostoGraduacao> lista = new ArrayList<>();
			lista.add(soldadoEV);
			lista.add(soldadoEP);
			
				return lista;
	}
}
