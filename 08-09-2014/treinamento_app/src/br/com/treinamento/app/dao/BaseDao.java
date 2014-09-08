package br.com.treinamento.app.dao;

import br.com.treinamento.app.dao.mapping.ContatoEntity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDao extends SQLiteOpenHelper {

	
	private static final String DB_NAME = "TreinamentoAndroid";
	private static final int DB_VERSION = 4;

	public BaseDao(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create = String.format(
				ContatoEntity.CREATE_BANCO, 
				ContatoEntity.COLUNA_ID,
				ContatoEntity.COLUNA_NOME,
				ContatoEntity.COLUNA_SITE,
				ContatoEntity.COLUNA_ENDERECO,
				ContatoEntity.COLUNA_TELEFONE,
				ContatoEntity.COLUNA_RATE);
		db.execSQL(create);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String drop = String.format(ContatoEntity.DROP_BANCO,ContatoEntity.TABELA);
		db.execSQL(drop);
		this.onCreate(db);
	}
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		this.onUpgrade(db, oldVersion, newVersion);
	}

}
