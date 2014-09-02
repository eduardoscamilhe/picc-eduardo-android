package br.com.treinamento.app;

import java.util.List;

import br.com.treinamento.app.entity.Contato;
import br.com.treinamento.app.service.ContatoService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaContatosActivity extends ActionBarActivity {

	protected ListView listViewContatos;

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		super.setContentView(R.layout.activity_lista_contatos);
		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);
		
		

	
		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String mensagem = getString(R.string.para_de_clicar_carolhos,posicao);
				Toast.makeText(ListaContatosActivity.this, mensagem, Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		listViewContatos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String mensagem = getString(R.string.voce_clickou_posicao, posicao); 
				Toast.makeText(ListaContatosActivity.this, mensagem, Toast.LENGTH_SHORT).show();

			}

		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		
		return true;
	}
	@Override
	protected void onResume() {
	
		super.onResume();
		List<Contato> contatos = ContatoService.getInstancia().listarTodos();

		ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
		listViewContatos.setAdapter(adapter);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int key = item.getItemId();
		switch (key) {
		case R.id.action_novo:
			Intent intent = new Intent(this, ContatoActivity.class);
			super.startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
