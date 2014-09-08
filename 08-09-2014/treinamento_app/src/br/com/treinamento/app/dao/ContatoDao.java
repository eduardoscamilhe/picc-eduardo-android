package br.com.treinamento.app.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.treinamento.app.dao.mapping.ContatoEntity;
import br.com.treinamento.app.domain.Contato;

public class ContatoDao extends BaseDao {

	private static ContatoDao INSTANCIA;

	private ContatoDao(Context contexto) {
		super(contexto);
	}

	public static ContatoDao getInstancia(Context contexto) {
		if (INSTANCIA == null) {
			INSTANCIA = new ContatoDao(contexto);

		}

		return INSTANCIA;
	}

	public List<Contato> listarTodos() throws SQLException {
		try {
			SQLiteDatabase db = super.getReadableDatabase();

			Cursor cursor = db.query(ContatoEntity.TABELA, ContatoEntity.COLUNAS, null, null, null, null, ContatoEntity.COLUNA_NOME.toUpperCase());
			// Opção 2
			// String sqlTodos = String.format("SELECT * FROM %s ORDER BY %s", ContatoEntity.TABELA, ContatoEntity.COLUNA_NOME);
			// Cursor cursor = db.rawQuery(sqlTodos, null);

			return ContatoEntity.bindContatos(cursor);

		} catch (SQLException e) {
			Log.e("DAO", e.getMessage());
			throw e;
		} finally {
			super.close();
		}

	}

	public void salvar(Contato contato) throws SQLException {
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(ContatoEntity.COLUNA_NOME, contato.getNome());
			values.put(ContatoEntity.COLUNA_ENDERECO, contato.getEndereco());
			values.put(ContatoEntity.COLUNA_SITE, contato.getSite());
			values.put(ContatoEntity.COLUNA_TELEFONE, contato.getTelefone());
			if (contato.getRate().floatValue() == 0) {
				values.putNull(ContatoEntity.COLUNA_RATE);
			} else {

				values.put(ContatoEntity.COLUNA_RATE, contato.getRate());

			}
			if (contato.getId() == null) {
				db.insert(ContatoEntity.TABELA, null, values);
			} else {
				String clausulaWhere = ContatoEntity.COLUNA_ID + "=?";
				String[] whereValues = new String[] { contato.getId().toString() };

				db.update(ContatoEntity.TABELA, values, clausulaWhere, whereValues);
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("DAO", e.getMessage());
			throw e;
		} finally {
			db.endTransaction();
			super.close();
		}
	}

	public void excluir(Contato selecionado) throws SQLException {
		SQLiteDatabase db = super.getWritableDatabase();
		try {
			db.beginTransaction();
			// Opção 1
			String clausulaWhere = ContatoEntity.COLUNA_ID + "=?";
			String[] whereValues = new String[] { selecionado.getId().toString() };
			db.delete(ContatoEntity.TABELA, clausulaWhere, whereValues);

			// Opção 2 - Compile Statement
			// String sqlDelete = String.format("DELETE FROM %s WHERE %s = ?", ContatoEntity.TABELA, ContatoEntity.COLUNA_ID);
			// SQLiteStatement comandoSql = db.compileStatement(sqlDelete);
			// comandoSql.bindLong(1, selecionado.getId());
			// comandoSql.execute();

			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("DAO", e.getMessage());
			throw e;
		} finally {

			db.endTransaction();
			super.close();
		}
	}
}
