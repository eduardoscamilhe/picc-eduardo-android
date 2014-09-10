package br.com.treinamento.app.dao.mapping;

import java.util.ArrayList;
import java.util.List;

import br.com.treinamento.app.domain.Contato;
import android.database.Cursor;
import android.provider.BaseColumns;

public class ContatoEntity implements BaseColumns {

	public static final String TABELA = "TB_CONTATO";
	public static final String COLUNA_ID = "ID";
	public static final String COLUNA_NOME = "NOME";
	public static final String COLUNA_SITE = "SITE";
	public static final String COLUNA_ENDERECO = "ENDERECO";
	public static final String COLUNA_TELEFONE = "TELEFONE";
	public static final String COLUNA_RATE = "RATE";

	public static final String CREATE_BANCO = "CREATE TABLE %s ("
			+ "%s INTEGER PRIMARY KEY NOT NULL,"
			+ "%s TEXT NOT NULL,"
			+ "%s TEXT NOT NULL,"
			+ "%s TEXT NOT NULL,"
			+ "%s TEXT NOT NULL,"
			+ "%s REAL);";
	public static final String DROP_BANCO = "DROP TABLE IF EXISTS %s;";
	
	
	public static final String[] COLUNAS = new String[] { 
		COLUNA_ID,
		COLUNA_NOME, 
		COLUNA_SITE, 
		COLUNA_ENDERECO, 
		COLUNA_TELEFONE, 
		COLUNA_RATE };

	private ContatoEntity() {
		super();
	}

	public static Contato bindContato(Cursor c) {
		if (!c.isBeforeFirst() || c.moveToNext()) {
			Contato contato = new Contato();
			contato.setId(c.getLong(c.getColumnIndex(COLUNA_ID)));
			contato.setNome(c.getString(c.getColumnIndex(COLUNA_NOME)));
			contato.setEndereco(c.getString(c.getColumnIndex(COLUNA_SITE)));
			contato.setSite(c.getString(c.getColumnIndex(COLUNA_ENDERECO)));
			contato.setTelefone(c.getString(c.getColumnIndex(COLUNA_TELEFONE)));
			int indice = c.getColumnIndex(COLUNA_RATE);
			if (!c.isNull(indice)) {
				contato.setRate(c.getFloat(indice));

			}
			return contato;
		}
		return null;
	}

	public static List<Contato> bindContatos(Cursor c) {
		List<Contato> contatos = new ArrayList<>();
		while (c.moveToNext()) {
			contatos.add(ContatoEntity.bindContato(c));
		}

		return contatos;
	}
}