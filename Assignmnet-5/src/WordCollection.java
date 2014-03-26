import java.io.FileNotFoundException;
import java.io.IOException;


public class WordCollection {
	
	/// collection of letters which were removed
	private java.util.ArrayList<String> lettersRemoved;
	// collection of words which contain the words
	private java.util.ArrayList<Word> wordList;
	/**
	 * Default constructor
	 */
	public WordCollection()
	{
		this.InitalizeComponents();
	}
	/**
	 * Initializes the local fields/components of the class
	 */
	private void InitalizeComponents()
	{
		this.wordList = new java.util.ArrayList<Word>();
		this.lettersRemoved = new java.util.ArrayList<String>();
	}
	/**
	 * Getter word method
	 * @return
	 * 	the wordList object
	 */
	public java.util.ArrayList<Word> getWordList()
	{
		return this.wordList;
	}
	/**
	 * Gets a word object. If no object is found, returns <i>null</i>
	 * @param a_word
	 * 	Key word to be used to find a word object
	 * @return
	 * 	A word object
	 */
	public Word getWord(String a_word)
	{
		for(int i = 0; i < wordList.size(); i++){
			if(wordList.get(i).getWord().equals(a_word)){
				return wordList.get(i);
			}
		}
		
		return null;
	}
	/**
	 * Adds a word object to the ArrayList
	 * @param a_word
	 * 	String to be added to the WordList
	 * @return
	 * 	<b>true</b> if the word successfully added, <b>false</b> if the word already exists
	 */
	public void addWord(String a_word)
	{
		boolean exists = false;
		
		for(Word word : this.wordList)
		{
			if(word.getWord().equals(a_word)){
				exists = true;
				break;
			}
		}
		
		if(!exists){
			wordList.add(new Word(a_word));
		}
	}
	/**
	 * Adds a word object to the ArrayList
	 * @param word
	 * 	Word to be added to the Collection
	 * @return
	 * 	<b>true</b> if the word successfully added, <b>false</b> if the word already exists
	 */
	public void addWord(Word a_word)
	{	
		boolean exists = false;
		
		for(Word word : this.wordList){
			if(word.getWord().equals(a_word.getWord())){
				word.addLists(a_word.getThemeList(), a_word.getUrlList());
				exists = true;
				break;
			}
		}
		
		if(!exists){
			wordList.add(a_word);
		} 
	}
	/**
	 * Removes a word from the collection
	 * @param a_word
	 * 	the word to be removed from the collection
	 * @return
	 * 	returns <b>true</b> if the word was removed, else <b>false</b>
	 */
	public boolean removeWord(String a_word)
	{
		boolean returnVal = false;
		
		for(Word word : this.wordList)
		{
			if(word.getWord().equals(a_word)){
				returnVal = this.wordList.remove(word);
				break;
			}
		}
		
		return returnVal;
	}
	/**
	 * Removes a word from the collection
	 * @param a_word
	 * 	word object to be removed from the collection
	 * @return
	 * 	returns <b>true</b> if the word was removed, else <b>false</b>
	 */
	public boolean removeWord(Word a_word)
	{
		return this.wordList.remove(a_word);
	}
	/**
	 * Removes all the themes from the collection
	 * @return
	 * 	<b>true</b> if the method was successful, else <b>false</b>
	 */
	public void removeAllThemes()
	{
		this.wordList.clear();
	}
	/**
	 * Gets the size of the array
	 * @return
	 * 	the size of the collection
	 */
	public int getSize()
	{
		return this.wordList.size();
	}
	/**
	 * Gets the theme count of a word
	 * @param a_word
	 */
	public int getThemeCount(Word a_word)
	{
		int count = 0;
		
		for(Word word: this.wordList){
			if(word.equals(a_word)){
				count = word.getThemeCount();
				break;
			}
		}
		
		return count;
	}
	/**
	 * Gets the theme count of a word
	 * @param a_word
	 */
	public int getThemeCount(String a_word)
	{
		int count = 0;
		
		for(Word word : this.wordList){
			if(word.getWord().equals(a_word)){
				count = word.getThemeCount();
				break;
			}
		}
		
		return count;
	}
	/**
	 * Getter method
	 * @return
	 * 	returns all the removed letters
	 */
	public java.util.ArrayList<String> getRemovedLetters()
	{
		return this.lettersRemoved;
	}
	/**
	 * 
	 * @param f
	 * 	File 
	 * @throws FileNotFoundException
	 * 	The file could not be opened, or was not found
	 * @throws IOException
	 * 	Did not have permissions to open the file, or the file is the wrong format
	 * @throws NullPointerException
	 * 	The list was split and ran outside of the bounds
	 * @throws ArrayIndexOutOfBoundsException
	 * 	When there is no url
	 */
	@SuppressWarnings("resource")
	public void readFile(java.io.File f) 
	{
		try
		{
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(f));
			
			java.util.ArrayList<String> lines = new java.util.ArrayList<String>();
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
			    lines.add(line);
			}
			
			for(int i = 0; i< lines.size(); i++){
				String[] items = lines.get(i).split(",");
				if(items.length > 2) { 
					this.addWord(new Word(items[0], items[1], items[2])); // could have an error occur here 
				} else { 
					this.addWord(new Word(items[0], items[1], "")); 
				}
			}
		} catch(IOException | NullPointerException | ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
	}
	
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < this.getSize(); i++){
			string.append("Word = " + this.wordList.get(i).getWord());
			string.append("\n");
			for(int j = 0; j < this.getWordList().get(i).getThemeList().size() && j < this.getWordList().get(i).getThemeList().size(); j++){
				string.append("\t theme = " + this.wordList.get(i).getThemeList().get(j));
				string.append("\t url = " + this.wordList.get(i).getUrlList().get(j));
				string.append("\n");
			}
		}
		
		return string.toString();
	}
	
	public void toHtmlFile(String a_word, int wordLength, int numOfWords, boolean withRemovedLetters)
	{
		Word word = getWord(a_word);
		StringBuilder builder = new StringBuilder("<table border=\"3\" cellspacing=\"10\" cellpadding=\"10\"><thead><tr><th>Word</th><th>Theme</th><th>Image</th></thead><tbody>");
		lettersRemoved.clear();
		lettersRemoved.addAll(word.removeLetters(wordLength, numOfWords));
		
		builder.append(word.toHtmlDataRow());
		//for(int i = 0; i < word.getThemeCount(); i++){
		//	builder.append("<tr><td>" + word.getWord() + "</td><td>" + word.getThemeList().get(i) + "</td><td>" + word.getUrlList().get(i) + "</td></tr>");
		//}
		
		if(withRemovedLetters){
			builder.append("<tr><td colspan=\"3\">");
			for(int i = 0; i < lettersRemoved.size(); i++){
				builder.append(lettersRemoved.get(i) + ", ");
			}
			builder.append("</td></tr>");
		}
		builder.append("</tbody></table>");
		
		Html.toHtml(builder.toString());
	}
	/*
	public java.util.ArrayList<String> removeLetters(int wordLength, int numOfWords)
	{
		this.lettersRemoved.clear();
		
		java.util.ArrayList<String> letters = new java.util.ArrayList<String>();
		
		for(int i = 0; i < this.wordList.size() && i < numOfWords; i++)
		{
			letters.addAll(this.wordList.get(i).removeLetters(wordLength, numOfWords));
		}
		
		return letters;
	}
	*/

}
