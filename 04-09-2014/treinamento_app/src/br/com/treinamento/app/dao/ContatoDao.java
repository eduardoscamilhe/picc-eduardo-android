package br.com.treinamento.app.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
			String[] coluna = { "ID", "NOME", "SITE", "ENDERECO", "TELEFONE", "RATE" };
			Cursor cursor = db.query("TB_CONTATO", coluna, null, null, null, null, null);
			List<Contato> contatos = new ArrayList<Contato>();

			while (cursor.moveToNext()) {
				Contato contato = new Contato();
				contato.setId(cursor.getLong(0));
				int indiceColunaNome = cursor.getColumnIndex("NOME");
				contato.setNome(cursor.getString(indiceColunaNome));
				contato.setEndereco(cursor.getString(2));
				contato.setSite(cursor.getString(3));
				contato.setTelefone(cursor.getString(4));
				contato.setRate(cursor.getFloat(5));

				contatos.add(contato);

			}

			return contatos;
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
			values.put("NOME", contato.getNome());
			values.put("ENDERECO", contato.getEndereco());
			values.put("SITE", contato.getSite());
			values.put("TELEFONE", contato.getTelefone());
			values.put("RATE", contato.getRate());
			if (contato.getId() == null) {
				db.insert("TB_CONTATO", null, values);
			} else {
				String[] whereValues = new String[] { contato.getId().toString() };
				db.update("TB_CONTATO", values, "id=?", whereValues);
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
			String[] whereValues = new String[] { selecionado.getId().toString() };
			db.delete("TB_CONTATO", "id=?", whereValues);
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
