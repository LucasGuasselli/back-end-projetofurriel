package com.lucasguasselli.projetofurriel.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.AditamentoDAO;
import com.lucasguasselli.projetofurriel.dao.AlteracaoValorPassagemDAO;
import com.lucasguasselli.projetofurriel.dao.AuxilioTransporteDAO;
import com.lucasguasselli.projetofurriel.dao.ConducaoDAO;
import com.lucasguasselli.projetofurriel.dao.DespesaDAO;
import com.lucasguasselli.projetofurriel.dao.EnderecoDAO;
import com.lucasguasselli.projetofurriel.dao.ExclusaoDAO;
import com.lucasguasselli.projetofurriel.dao.InclusaoDAO;
import com.lucasguasselli.projetofurriel.dao.MilitarDAO;
import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.dao.SaqueAtrasadoDAO;
import com.lucasguasselli.projetofurriel.dao.UsuarioDAO;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.domain.Usuario;

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
	@Autowired
	private AditamentoDAO aditamentoDAO;
	@Autowired
	private DespesaDAO despesaDAO;
	@Autowired
	private InclusaoDAO inclusaoDAO;
	@Autowired
	private ExclusaoDAO exclusaoDAO;
	@Autowired
	private SaqueAtrasadoDAO pagamentoAtrasadoDAO;
	@Autowired
	private AlteracaoValorPassagemDAO alteracaoValorPassagemDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public void instantiateTestDatabase() throws ParseException {
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

		Militar lucas = new Militar(123456789, "Lucas");
		Militar grillo = new Militar(2456, "Grillo");
		Usuario furriel = new Usuario("Felipe GRILLO", "grillo@cms.eb.mil.br", "857.452.333.96", "123");
		
		lucas.setPostoGraduacao(cabo);
		grillo.setPostoGraduacao(segundoSargento);
		furriel.setPostoGraduacao(segundoSargento);
/*
		// Militar mauro = new Militar(258,"Mauro");
		// Militar guilherme = new Militar(147,"Guilherme");
		
		
		// mauro.setPostoGraduacao(segundoSargento);
			
		AuxilioTransporte aux1 = new AuxilioTransporte(220,11, false, true, false, lucas);
		AuxilioTransporte aux2 = new AuxilioTransporte(298,15, false, false, false, grillo);
				
		Conducao cond1 = new Conducao("bairro-centro","Viva-Sul", "Onibus",4.70, aux1);
		Conducao cond2 = new Conducao("centro-bairro", "Viva-Sul", "Onibus", 4.70, aux1);
		
		Endereco end = new Endereco("Fronteira","Campo Novo","Porto Alegre",169,"fundos", lucas);
		Endereco end2 = new Endereco("Fronteira","Campo Novo","Porto Alegre",169,"fundos", grillo);
		
		// formatando a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Aditamento adt = new Aditamento("Aditamento_nr_01", sdf.parse("25/12/1994"), "texto despesa", "texto exclusao");
		Aditamento adt1 = new Aditamento("Aditamento_nr_02", sdf.parse("06/02/1994"), "texto despesa", "texto exclusao");

		DespesaAAnular desp = new DespesaAAnular(sdf.parse("05/02/1994"),sdf.parse("06/02/1994"), 3,"viagem", adt,lucas);
		DespesaAAnular desp1 = new DespesaAAnular(sdf.parse("05/02/2000"),sdf.parse("06/02/2000"), 1,"servico", adt,lucas);

		InclusaoAuxilioTransporte incAux = new InclusaoAuxilioTransporte(sdf.parse("05/02/2000"), 20, adt, lucas); 
		InclusaoAuxilioTransporte incAux1 = new InclusaoAuxilioTransporte(sdf.parse("18/12/2010"), 10, adt1, grillo); 

		ExclusaoAuxilioTransporte excAux = new ExclusaoAuxilioTransporte(sdf.parse("05/02/2000"), 20,"", adt, lucas);
		ExclusaoAuxilioTransporte excAux1 = new ExclusaoAuxilioTransporte(sdf.parse("05/02/2000"), 20,"", adt, lucas);

		PagamentoAtrasado pgmto = new PagamentoAtrasado("Janeiro", 3, "diferenca de aumento da passagem", 20, sdf.parse("05/02/2000"), adt, lucas);
		PagamentoAtrasado pgmto1 = new PagamentoAtrasado("Abril", 2, "diferenca de aumento da passagem", 10, sdf.parse("05/04/2019"), adt1, grillo);

		AlteracaoValorPassagem altValPass = new AlteracaoValorPassagem(sdf.parse("05/02/2000"),"motivo", 20, adt, lucas);
		AlteracaoValorPassagem altValPass1 = new AlteracaoValorPassagem(sdf.parse("05/12/2019"),"motivo", 5, adt1, grillo);
	
		// guilherme.setPostoGraduacao(cabo);
		
 */

		militarDAO.saveAll(Arrays.asList(lucas, grillo));
		usuarioDAO.saveAll(Arrays.asList(furriel));
		
/*
		auxilioTransporteDAO.saveAll(Arrays.asList(aux1, aux2));
		conducaoDAO.saveAll(Arrays.asList(cond1, cond2));
		enderecoDAO.saveAll(Arrays.asList(end, end2));
		aditamentoDAO.saveAll(Arrays.asList(adt, adt1));
		despesaDAO.saveAll(Arrays.asList(desp, desp1));
		inclusaoDAO.saveAll(Arrays.asList(incAux, incAux1));
		exclusaoDAO.saveAll(Arrays.asList(excAux, excAux1));
		pagamentoAtrasadoDAO.saveAll(Arrays.asList(pgmto, pgmto1));		
		alteracaoValorPassagemDAO.saveAll(Arrays.asList(altValPass, altValPass1));
		
		// cabo.getMilitares().addAll(Arrays.asList(lucas));
		// segundoSargento.getMilitares().addAll(Arrays.asList(grillo));
	*/
		
	}
}
