import java.io.FileNotFoundException;
import java.io.IOException;


public class WordCollection {
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
	 * 	the wordlist object
	 */
	public java.util.ArrayList<Word> getWordList()
	{
		return this.wordList;
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
	public boolean removeAllThemes()
	{
		return this.wordList.removeAll(this.wordList);
	}
	/**
	 * Generates an ArrayList<Word>
	 * @return
	 * 	An ArrayList of Word Objects
	 */
	public java.util.ArrayList<Word> ToList()
	{
		return this.wordList;
	}
	/**
	 * Generates a Word array
	 * @return
	 * 	A collection of Word Objects
	 */
	public Word[] ToArray()
	{
		return (Word[]) this.wordList.toArray();
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
	/**
	 * Removes letters randomly from the themes
	 */
	public void removeLetters()
	{
		System.out.println("Removing letters");
		
		java.util.Random rand = new java.util.Random();
		for(Word word : this.wordList){
			switch(rand.nextInt(6)) // produces a number between 0 and 5
			{
			case 0:
				One_Blank_At_the_beginning_of_the_word(word);
				break;
			case 1:
				One_Blank_At_the_end_of_the_word(word);
				break;
			case 2:
				One_Blank_At_Random_Location(word);
				break;
			case 3:
				Multiple_Blanks_at_all_Consonants(word);
				break;
			case 5:
				Multiple_Blanks_at_all_Vowels(word);
				break;
			default:
				One_Blank_At_the_beginning_of_the_word(word);
				break;
			}
		}
	}
	/**
	 * Removes the first letter of all the themes
	 */
	private void  One_Blank_At_the_beginning_of_the_word(Word a_word)
	{
		for(int i = 0; i < a_word.getThemeCount(); i++){
			this.lettersRemoved.add(String.valueOf(a_word.getThemeList().get(i).charAt(0)));
			a_word.getThemeList().set(i, a_word.getThemeList().get(i).substring(1));
		}
	}
	/**
	 *  Removes the last letter of the theme
	 */
	private void  One_Blank_At_the_end_of_the_word(Word a_word)
	{
		
		for(String string : a_word.getThemeList()){
			StringBuilder builder = new StringBuilder(string);
			this.lettersRemoved.add(builder.substring(string.length() - 1));
			builder.setCharAt(string.length() - 1, '_');
			string = builder.toString();
		}
	}
	/**
	 * 
	 */
	private void  One_Blank_At_Random_Location(Word a_word)
	{
		java.util.Random rand = new java.util.Random();
		
		for(String string : a_word.getThemeList()){
			StringBuilder builder = new StringBuilder(string);
			
			int val = rand.nextInt(string.length() - 1);
			this.lettersRemoved.add(String.valueOf(builder.charAt(val)));
			builder.setCharAt(val, '_');
			string = builder.toString();
		}
	}
	/**
	 * 
	 */
	private void  Multiple_Blanks_at_all_Consonants(Word a_word)
	{
		
		for(String string : a_word.getThemeList()){
			
			StringBuilder builder = new StringBuilder(string);
			for(int i = 0; i < string.length(); i++){
				if(!isVowel(string.charAt(i))){
					this.lettersRemoved.add(String.valueOf(string.charAt(i)));
					builder.setCharAt(i, '_');
				}
			}
			string = builder.toString();
		}
	}
	/**
	 * 
	 */
	private void  Multiple_Blanks_at_all_Vowels(Word a_word)
	{	
		for(String string : a_word.getThemeList()){
			
			StringBuilder builder = new StringBuilder(string);
			
			for(int i = 0; i < string.length(); i++){
				if(isVowel(string.charAt(i))){
					this.lettersRemoved.add(String.valueOf(string.charAt(i)));
					builder.setCharAt(i, '_');
				}
			}
			string = builder.toString();
		}
	}
	/**
	 * Tells whether a letter is a character
	 * @param letter
	 * 	letter to tell if it is a vowel
	 * @return
	 * 	<b>true</b> if the letter is a vowel, else <b>false</b>
	 */
	private boolean isVowel(char letter)
	{
		boolean ret = false;
		
		char[] vowels = {'a', 'e', 'i', 'o', 'u' };
		
		for(int i = 0; i < vowels.length; i++){
			if(vowels[i] == letter){
				ret = true;
				break;
			}
		}
		
		return ret;
	}
}
