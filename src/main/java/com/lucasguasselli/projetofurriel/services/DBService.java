package com.lucasguasselli.projetofurriel.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.AuxilioTransporteDAO;
import com.lucasguasselli.projetofurriel.dao.ConducaoDAO;
import com.lucasguasselli.projetofurriel.dao.EnderecoDAO;
import com.lucasguasselli.projetofurriel.dao.MilitarDAO;
import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Conducao;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

@Service
public class DBService {

	@Autowired
	private PostoGraduacaoDAO postoGradDAO;
	@Autowired
	private MilitarDAO militarDAO;
	@Autowired
	private AuxilioTransporteDAO auxilioTransporteDAO;
	@Autowired
	private ConducaoDAO conducaoDAO;
	@Autowired
	private EnderecoDAO enderecoDAO;
	
	public void instantiateTestDatabase() {
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

		Militar lucas = new Militar(123456789, "Lucas");
		Militar grillo = new Militar(2456, "Grillo");
		Militar mauro = new Militar(258,"Mauro");
		// Militar guilherme = new Militar(147,"Guilherme");

		
		lucas.setPostoGraduacao(cabo);
		grillo.setPostoGraduacao(segundoSargento);
		mauro.setPostoGraduacao(segundoSargento);
		// guilherme.setPostoGraduacao(cabo);
		
		AuxilioTransporte aux1 = new AuxilioTransporte(220,11,lucas);
		AuxilioTransporte aux2 = new AuxilioTransporte(298,15,grillo);
				
		Conducao cond1 = new Conducao("bairro-centro","Viva-Sul", "Onibus",4.70, aux1);
		Conducao cond2 = new Conducao("centro-bairro", "Viva-Sul", "Onibus", 4.70, aux1);
		
		Endereco end = new Endereco("Fronteira","Campo Novo","Porto Alegre",169,"fundos");
		Endereco end2 = new Endereco("Fronteira","Campo Novo","Porto Alegre",169,"fundos");

		
		postoGradDAO.saveAll(Arrays.asList(soldadoEV, soldadoEP, cabo, terceiroSargento, segundoSargento,
				primeiroSargento, subtenente, aspirante, segundoTenente, primeiroTenente, capitao, major));
		militarDAO.saveAll(Arrays.asList(lucas, grillo, mauro));
		auxilioTransporteDAO.saveAll(Arrays.asList(aux1, aux2));
		conducaoDAO.saveAll(Arrays.asList(cond1, cond2));
		enderecoDAO.saveAll(Arrays.asList(end, end2));
				
		// cabo.getMilitares().addAll(Arrays.asList(lucas));
		// segundoSargento.getMilitares().addAll(Arrays.asList(grillo));
	
	}
}
