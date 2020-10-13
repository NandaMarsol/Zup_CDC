package br.com.zup.casadocodigoapi.validations;

import java.util.Collection;

public class ErroPadronizado {
	private Collection<String> mensagens;
	
	// construtor
	public ErroPadronizado(Collection<String> mensagens){
		this.mensagens = mensagens;
	}
	
	// getters and setters
	public Collection<String> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(Collection<String> mensagens){
		this.mensagens = mensagens;
	}

}
