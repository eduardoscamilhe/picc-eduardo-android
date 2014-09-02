package br.com.treinamento.app;

import br.com.treinamento.app.entity.Contato;
import br.com.treinamento.app.service.ContatoService;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.provider.Telephony.Mms.Rate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ContatoActivity extends ActionBarActivity {

	private EditText txtNome, txtEndereco, txtSite, txtTelefone;
	private RatingBar rateEstrelas;
	private Button btnSalvar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);
		bindElementosLayout();
		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Contato contato = new Contato();
				contato = popularContato(contato);
				ContatoService.getInstancia().salvar(contato);
				ContatoActivity.this.finish();
			}

		});

	}
	

	private Contato popularContato(Contato contato) {
		contato.setNome(txtNome.getText().toString());
		contato.setEndereco(txtEndereco.getText().toString());
		contato.setSite(txtSite.getText().toString());
		contato.setTelefone(txtTelefone.getText().toString());
		contato.setRate(rateEstrelas.getRating());
		return contato;
	}

	private void bindElementosLayout() {
		txtNome = (EditText) findViewById(R.id.txtNomeContato);
		txtEndereco = (EditText) findViewById(R.id.txtEnderecoContato);
		txtSite = (EditText) findViewById(R.id.txtSiteContato);
		txtTelefone = (EditText) findViewById(R.id.txtTelefoneContato);

		rateEstrelas = (RatingBar) findViewById(R.id.rateEstrelas);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
	}

}
