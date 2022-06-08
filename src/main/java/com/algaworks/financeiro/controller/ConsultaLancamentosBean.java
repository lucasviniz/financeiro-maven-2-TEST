package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	private List<Lancamento> lancamentos;
	
	
	private Lancamento lancamentoSelecionado;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@PostConstruct
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
		limpar();
	}
	
	public void limpar() {
		lancamentoSelecionado = new Lancamento();
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			System.out.println("\n\n\n\n\n"+lancamentoSelecionado.getId()+"\n\n\n\n\n\n");
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage(
					"Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
	
}
