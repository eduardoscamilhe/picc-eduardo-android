package br.com.treinamento.app.service;

import java.util.List;

import android.text.TextUtils;
import br.com.treinamento.app.R;
import br.com.treinamento.app.dao.ContatoDao;
import br.com.treinamento.app.domain.Contato;
import br.com.treinamento.app.domain.exceptions.ExcecaoNegocio;

public final class ContatoService {

	private static final ContatoService INSTANCIA = new ContatoService();

	private ContatoService() {
		super();
	}

	public static ContatoService getInstancia() {
		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		return ContatoDao.getInstancia().listarTodos();

	}

	public void salvar(Contato contato) throws ExcecaoNegocio{
		ExcecaoNegocio ex = new ExcecaoNegocio();
		if (TextUtils.isEmpty(contato.getNome().toString())) {
			ex.getMapaErros().put(R.id.txtNomeContato, R.string.erro_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getEndereco().toString())) {
			ex.getMapaErros().put(R.id.txtEnderecoContato, R.string.erro_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getTelefone().toString())) {
			ex.getMapaErros().put(R.id.txtTelefoneContato, R.string.erro_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getSite().toString())) {
			ex.getMapaErros().put(R.id.txtSiteContato, R.string.erro_obrigatorio);

		}	
		
		if(!ex.getMapaErros().isEmpty()){
			throw ex;
		}
		ContatoDao.getInstancia().salvar(contato);
		
		
	}

	public void excluir(Contato selecionado) {
	ContatoDao.getInstancia().excluir(selecionado);
		
	}

}
