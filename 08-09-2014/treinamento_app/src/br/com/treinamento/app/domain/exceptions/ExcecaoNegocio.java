package br.com.treinamento.app.domain.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExcecaoNegocio extends Exception {

	private static final long serialVersionUID = -1321654321;
	private final Map<Integer, Integer> mapaErros = new LinkedHashMap<>();
	
	
	public Map<Integer, Integer> getMapaErros() {
		return mapaErros;
	}
	
}
