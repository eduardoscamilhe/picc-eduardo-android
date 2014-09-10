package br.com.treinamento.app.widget;

import java.util.List;

import br.com.treinamento.app.R;
import br.com.treinamento.app.domain.Contato;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContatoAdapter extends BaseAdapter {

	private List<Contato> itens;
	private Activity contexto;
	

	public ContatoAdapter(List<Contato> itens, Activity contexto) {
		super();
		this.itens = itens;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Contato getItem(int posicao) {
		
		return itens.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		
		Contato id = getItem(posicao);
				return id.getId();
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewPai) {
		 LayoutInflater layout  = contexto.getLayoutInflater();
		 View layoutItem = layout.inflate(R.layout.item_list_view_contato, viewPai,false);
		 
		 TextView lblNome = (TextView) layoutItem.findViewById(R.id.lblNome);
		 lblNome.setText(getItem(posicao).getNome());
		 
		 TextView lblTelefone = (TextView) layoutItem.findViewById(R.id.lblTelefone);
		 lblTelefone.setText(getItem(posicao).getTelefone());
		
		 //if(posicao % 2 == 0){
//			 layoutItem.setBackgroundColor(contexto.getResources().getColor(R.color.urso_quando_foge));
//		 }
		return layoutItem;
	}

}
