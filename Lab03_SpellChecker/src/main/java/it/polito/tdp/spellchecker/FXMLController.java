package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.model.Dictionary;
import it.polito.tdp.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Dictionary model;
	private String language = null;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> boxLingua;

	@FXML
	private TextArea inputTextList;

	@FXML
	private Button btnSpell;

	@FXML
	private TextArea spellCheckText;

	@FXML
	private TextField txtTime;

	@FXML
	private TextField txtError;

	@FXML
	private Button btnClear;

	@FXML
	public void doClearText(ActionEvent event) {

		this.inputTextList.clear();
		this.spellCheckText.clear();
		this.txtError.clear();
		this.txtTime.clear();
		

	}

	@FXML
	public void doSpellCheck(ActionEvent event) {
		
		this.language = this.boxLingua.getValue();

		model.loadDictionary(this.language);

		List <String> input = new ArrayList<>();
		List <RichWord> errori = new ArrayList<>();
		
		String parole = this.inputTextList.getText();
		
		if(parole.isBlank()) {
			return;
		}
		
		parole.replaceAll("[.,?\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
		
		if (!parole.matches("([a-z]*)")) {
			return;}
		
		String[] words = parole.split(" ");
		
		for(String w : words) {
			input.add(w);
		}
		
		double startTime = System.nanoTime();
		errori.addAll(model.spellCheckText(input));
		double time = (System.nanoTime() - startTime) / 1000000000;
		
		String output = "";

		for (RichWord e : errori) {
			output += e.toString();
		}
			this.spellCheckText.setText(output);



		this.txtTime.setText("Spell check completed in: " + time + " seconds");
		this.txtError.setText("The text contains " + errori.size() + " errors");

	}

	@FXML
	void initialize() {
		boxLingua.getItems().addAll("English", "Italian");
		assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
		assert inputTextList != null : "fx:id=\"inputTextList\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
		assert spellCheckText != null : "fx:id=\"spellCheckText\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Dictionary model) {

		this.model = model;

	}
}
