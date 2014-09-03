package br.com.treinamento.app;



import java.util.Map.Entry;

import br.com.treinamento.app.domain.Contato;
import br.com.treinamento.app.domain.exceptions.ExcecaoNegocio;
import br.com.treinamento.app.service.ContatoService;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ContatoActivity extends LifeCicleActivity {

	public static final String CHAVE_CONTATO = "CHAVE_CONTATO";
	private EditText txtNome, txtEndereco, txtSite, txtTelefone;
	private RatingBar rateEstrelas;
	private Button btnSalvar;
	private Contato contato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);
		bindElementosLayout();
		contato = (Contato) getIntent().getSerializableExtra(CHAVE_CONTATO);
		if(contato == null){
			contato = new Contato();
		}else{
			popularCampos(contato);
		}
		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				contato = popularContato(contato);
				try {
					ContatoService.getInstancia().salvar(contato);
					ContatoActivity.this.finish();
				} catch (ExcecaoNegocio e) {
					Drawable icone = getResources().getDrawable(R.drawable.ic_av_rewind);
					icone.setBounds(0, 0, 70, 70);
					for (Entry<Integer, Integer> erro : e.getMapaErros().entrySet()) {
						EditText campoErro = (EditText)findViewById(erro.getKey());
						campoErro.setError(getString(erro.getValue()),icone);
					}
				}
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
	
	private void popularCampos(Contato contato){
		txtNome.setText(contato.getNome());
		txtSite.setText(contato.getSite());
		txtTelefone.setText(contato.getTelefone());
		txtEndereco.setText(contato.getEndereco());
		rateEstrelas.setRating(contato.getRate());
		
	}

	private void bindElementosLayout() {
		txtNome = (EditText) findViewById(R.id.txtNomeContato);
		txtEndereco = (EditText) findViewById(R.id.txtEnderecoContato);
		txtSite = (EditText) findViewById(R.id.txtSiteContato);
		txtTelefone = (EditText) findViewById(R.id.txtTelefoneContato);

		rateEstrelas = (RatingBar) findViewById(R.id.rateEstrelas);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
	}


	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}

}
