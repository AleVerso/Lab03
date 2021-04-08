package it.polito.tdp.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	private List<String> words = new ArrayList<>();
	private List<RichWord> errate = new ArrayList<>();
	
	
	

	public Dictionary() {
		super();
	}



	public void loadDictionary(String language) {

		try {
			String nomeFile = "src/main/resources/"+language+".txt";
			FileReader fr = new FileReader(nomeFile);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				words.add(word.toLowerCase());
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException fnfe) {
			System.out.println("File non trovato");
		}  catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}

	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		for(String w : inputTextList) {
			if(!words.contains(w.toLowerCase())) {
				errate.add(new RichWord(w, false));
			}
		}
		
		return errate;
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		int c = 0;
		for(String i : inputTextList) {
			for(String d : words) {
				if(d.equals(i.toLowerCase()))
					c++;
			}
			if(c == 0)
				errate.add(new RichWord(i, false));
			c = 0;
		}
		
		return errate;
		
		
	}


	public List<String> getWords() {
		return words;
	}


	public void setWords(List<String> words) {
		this.words = words;
	}




	public List<RichWord> getErrate() {
		return errate;
	}


	public void setErrate(List<RichWord> errate) {
		this.errate = errate;
	}
	
		
}
	
	
	
