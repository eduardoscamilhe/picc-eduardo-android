package br.com.treinamento.app;

import java.util.List;

import br.com.treinamento.app.domain.Contato;
import br.com.treinamento.app.service.ContatoService;
import br.com.treinamento.app.widget.ContatoAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListaContatosActivity extends LifeCicleActivity {

	protected ListView listViewContatos;
	private Contato selecionado;

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		super.setContentView(R.layout.activity_lista_contatos);
		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);

		super.registerForContextMenu(listViewContatos);
		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {


			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				selecionado = (Contato) adapter.getItemAtPosition(posicao);
				
				return false;
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.lista_contato_context, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int key = item.getItemId();
		switch (key) {
		case R.id.action_editar:
			Intent intent = new Intent(this, ContatoActivity.class);
			intent.putExtra("CHAVE_CONTATO", selecionado);
			super.startActivity(intent);
			break;
		case R.id.action_excluir:
			new AlertDialog.Builder(this)
			.setTitle(R.string.dialog_confirmacao)
			.setMessage(getString(R.string.dialong_contato_excluir,selecionado.getNome()))
			.setNeutralButton(R.string.dialog_nao,null)
			.setPositiveButton(R.string.dialog_sim, new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					ContatoService.getInstancia().excluir(selecionado);
					carregarLista();
				}
			}).create().show();
			
			break;
		default:
			
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);

		return true;
	}

	@Override
	protected void onResume() {

		super.onResume();
		carregarLista();
	}

	private void carregarLista() {
		List<Contato> contatos = ContatoService.getInstancia().listarTodos();
		ContatoAdapter adapter = new ContatoAdapter(contatos,this);
	//	ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
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

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}
}
