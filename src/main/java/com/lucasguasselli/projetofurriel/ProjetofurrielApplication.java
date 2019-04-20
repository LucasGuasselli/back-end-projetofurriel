package com.lucasguasselli.projetofurriel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

@SpringBootApplication
public class ProjetofurrielApplication implements CommandLineRunner{

	@Autowired
	private PostoGraduacaoDAO postoGradDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetofurrielApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		PostoGraduacao soldadoEV = new PostoGraduacao("SoldadoEV", 854.00, 37.576);
		PostoGraduacao soldadoEP = new PostoGraduacao("SoldadoEP", 1758.00, 65.032);
		PostoGraduacao cabo = new PostoGraduacao("Cabo", 2449.00, 107.756);
		PostoGraduacao terceiroSargento = new PostoGraduacao("3º Sargento", 3584.00, 157.696);
		PostoGraduacao segundoSargento = new PostoGraduacao("2º Sargento", 4445.00, 195.58);
		PostoGraduacao primeiroSargento = new PostoGraduacao("1º Sargento", 5110.00, 224.84);
		PostoGraduacao subtenente = new PostoGraduacao("Subtenente", 5751.00, 256.044);
		PostoGraduacao aspirante = new PostoGraduacao("Aspirante", 6625.00, 397.5);
		PostoGraduacao segundoTenente = new PostoGraduacao("2º Tenente", 7082.00, 311.608);
		PostoGraduacao primeiroTenente = new PostoGraduacao("1º Tenente", 7796.00, 467.76);
		PostoGraduacao capitao = new PostoGraduacao("Capitão", 8517.00, 511.02);
		PostoGraduacao major = new PostoGraduacao("Major", 10472.00, 628.32);

		postoGradDAO.saveAll(Arrays.asList(soldadoEV, soldadoEP, cabo, terceiroSargento, segundoSargento,
				primeiroSargento, subtenente, aspirante, segundoTenente, primeiroTenente, capitao, major));
	}

}
