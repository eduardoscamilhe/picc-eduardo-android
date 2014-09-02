package br.com.treinamento.app.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.treinamento.app.entity.Contato;

public final class ContatoDao {

	private static long SEQUENCE = 1;
	private static final ContatoDao INSTANCIA = new ContatoDao();
	private static final List<Contato> REPOSITORIO = new ArrayList<>();

	private ContatoDao() {
		super();
	}

	public static ContatoDao getInstancia() {
		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		return REPOSITORIO;

	}

	public void salvar(Contato contato) {
		if (contato.getId() == null) {
			contato.setId(SEQUENCE++);
			REPOSITORIO.add(contato);
		} else {
			REPOSITORIO.set(REPOSITORIO.indexOf(contato), contato);
		}
	}

}
