package br.com.treinamento.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class BaseDao extends SQLiteOpenHelper {

	
	private static final String DB_NAME = "TreinamentoAndroid";
	private static final int DB_VERSION = 1;

	public BaseDao(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create = "CREATE TABLE TB_CONTATO (ID INTEGER PRIMARY KEY,NOME TEXT,ENDERECO TEXT,SITE TEXT,TELEFONE TEXT,RATE REAL);";
		db.execSQL(create);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String drop = "DROP TABLE IF EXISTS TB_CONTATO;";
		db.execSQL(drop);
	}

}
