import java.io.FileNotFoundException;
import java.io.IOException;


public class WordCollection {
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
	}
	/**
	 * Adds a word object to the ArrayList
	 * @param a_word
	 * 	String to be added to the WordList
	 * @return
	 * 	<b>true</b> if the word successfully added, <b>false</b> if the word already exists
	 */
	public boolean addWord(String a_word)
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
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Adds a word object to the ArrayList
	 * @param word
	 * 	Word to be added to the Collection
	 * @return
	 * 	<b>true</b> if the word successfully added, <b>false</b> if the word already exists
	 */
	public boolean addWord(Word a_word)
	{	
		boolean exists = false;
		
		for(Word word : this.wordList)
		{
			if(word.equals(a_word)){
				word.addLists(a_word.getThemeList(), a_word.getThemeList());
				exists = true;
				break;
			}
		}
		
		if(!exists){
			this.wordList.add(a_word);
			exists = true;
		} 
		
		return exists;
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
	public void ReadFile(java.io.File f) throws FileNotFoundException, IOException, NullPointerException, ArrayIndexOutOfBoundsException
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
		
	}
}
