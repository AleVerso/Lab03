package it.polito.tdp.model;

import java.beans.Beans;

public class RichWord extends Beans {

	private boolean corretta;
	private String word;

	public RichWord() {

	}

	public RichWord(String w, boolean b) {
		this.corretta = b;
		this.word = w;
	}

	public boolean isEsistente() {
		return corretta;
	}

	public void setEsistente(boolean esistente) {
		this.corretta = esistente;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return word + "\n";
	}

}
