public class Word {
	// key word
	private String word;
	// list of themes
	private java.util.ArrayList<String> themeList;
	// url list
	private java.util.ArrayList<String> urlList;
	
	private java.util.ArrayList<String> alteredThemes;
	/**
	 * Default constructor
	 */
	public Word()	
	{
		this.word = null;
		this.themeList =  new java.util.ArrayList<String>();
		this.urlList = new java.util.ArrayList<String>();
		this.alteredThemes = new java.util.ArrayList<String>();
	}
	/**
	 * Overloaded Constructor
	 * @param a_word
	 * 	Key word
	 */
	public Word(String a_word)
	{
		this.word = a_word;
		this.themeList = new java.util.ArrayList<String>();
		this.urlList = new java.util.ArrayList<String>();
		this.alteredThemes = new java.util.ArrayList<String>();
	}
	/**
	 * Overloaded Constructor
	 * @param a_word
	 * 	Key word
	 * @param a_theme
	 * 	List of themes associated with the Key Word
	 */
	public Word(String a_word, java.util.ArrayList<String> a_theme)
	{
		this.word = a_word;
		this.themeList = a_theme;
		this.urlList = new java.util.ArrayList<String>();
	}
	/**
	 * Overloaded Constructor
	 * @param a_word
	 * 	Key word
	 * @param a_theme
	 * 	List of themes associated with the Key Word
	 * @param a_url
	 * 	List of Url's associated with the Key Word
	 */
	public Word(String a_word, java.util.ArrayList<String> a_theme, java.util.ArrayList<String> a_url)
	{
		this.word = a_word;
		this.themeList = a_theme;
		this.urlList = a_url;
	}
	
	public Word(String a_word, String a_theme, String a_url)
	{
		this.word = a_word;
		this.themeList = new java.util.ArrayList<String>();
		this.themeList.add(a_theme);
		
		this.urlList = new java.util.ArrayList<String>();
		this.urlList.add(a_url);
	}
	/**
	 * Getter method
	 * @return
	 * 	Returns the key word
	 */
	public String getWord()
	{
		return this.word;
	}
	/**
	 * Setter method
	 * @param a_word
	 * 	key word to be set
	 */
	public void setWord(String a_word)
	{
		this.word = a_word;
	}
	/**
	 * Setter method
	 * @param a_theme
	 *  String List of themes associated with the Key Word
	 */
	public void setThemeList(java.util.ArrayList<String> a_theme)
	{
		this.themeList = a_theme;
	}
	/**
	 * Getter method
	 * @return
	 * 	Gets a list of themes associated with the Key Word
	 */
	public java.util.ArrayList<String> getThemeList()
	{
		return this.themeList;
	}
	/**
	 * Setter method
	 * @param a_url
	 * 	List of Urls associated with a Key Word, and Theme
	 */
	public void setUrlList(java.util.ArrayList<String> a_url)
	{
		this.urlList = a_url;
	}
	/**
	 * Getter method
	 * @return
	 * 	Gets the List of Urls associated with Themese and Key Word
	 */
	public java.util.ArrayList<String> getUrlList()
	{
		return this.urlList;
	}
	/**
	 * Adds a theme to the list
	 * @param a_theme
	 * 	Theme to be added
	 * @return
	 * 	<b>true</b> if the theme was successfully added, else <b>false</b>
	 */
	public boolean addTheme(String a_theme)
	{
		return this.themeList.add(a_theme);
	}
	/**
	 * Adds a theme to the list
	 * @param a_theme
	 * 	Theme list to be added to the already existing list
	 * @return
	 * 		<b>true</b> if the theme was successfully added, else <b>false</b>
	 */
	public boolean addTheme(java.util.ArrayList<String> a_theme)
	{
		for(String string : a_theme){
			this.themeList.add(string);
		}
		
		return true;
	}
	/**
	 * Adds a URL to the list
	 * @param a_url
	 * 	url to be added
	 * @return
 * 		<b>true</b> if the URL was successfully added, else <b>false</b>
	 */
	public boolean addUrl(String a_url)
	{
		return this.urlList.add(a_url);
	}
	/**
	 * Adds a URL to the list
	 * @param a_url
	 * 	url to be added
	 * @return
 * 		<b>true</b> if the URL was successfully added, else <b>false</b>
	 */
	public boolean addUrl(java.util.ArrayList<String> a_url)
	{
		for(String string : a_url){
			this.urlList.add(string);
		}
		
		return true;
	}
	/**
	 * Adds a collection of URLs and themes
	 * @param a_theme
	 * 	theme collection to be added
	 * @param a_url
	 * 	url collection to be added
	 */
	public void addLists(java.util.ArrayList<String> a_theme, java.util.ArrayList<String> a_url)
	{
		this.addTheme(a_theme);
		this.addUrl(a_url);
	}
	/**
	 * Removes a theme from the list
	 * @param themeName
	 * 	Theme to be removed
	 * @return
	 * 	<b>true</b> if the theme was successfully removed, else <b>false</b>
	 */
	public boolean removeTheme(String themeName)
	{
		return this.themeList.remove(themeName);
	}
	/**
	 * Gets the Count of themes
	 * @return
	 * 	the number of themes the word has
	 */
	public int getThemeCount()
	{
		return this.themeList.size();
	}
	/**
	 * Returns the list of alter themes
	 * @return
	 * 	An ArrayList
	 */
	public java.util.ArrayList<String> getAlteredThemes()
	{
		return this.alteredThemes;
	}
	/**
	 * Removes letters randomly from the themes
	 * @param length
	 *  maximum length of the word
	 */
	public java.util.ArrayList<String> removeLetters(int length, int numOfWords)
	{	
		java.util.Random rand = new java.util.Random();
		java.util.ArrayList<String> letters = new java.util.ArrayList<String>();
		this.alteredThemes = new java.util.ArrayList<String>();
		
		for(int i = 0; i < getThemeCount() && i < numOfWords; i++){
			if(this.themeList.get(i).length() <= length)
			{
				switch(rand.nextInt(5)) // produces a number between 0 and 4
				{
				case 0:
					letters.add(One_Blank_At_the_beginning_of_the_word(i));
					break;
				case 1:
					letters.add(One_Blank_At_the_end_of_the_word(i));
					break;
				case 2:
					letters.add(One_Blank_At_Random_Location(i));
					break;
				case 3:
					letters.addAll(Multiple_Blanks_at_all_Consonants(i));
					break;
				case 4:
					letters.addAll(Multiple_Blanks_at_all_Vowels(i));
					break;
				default:
					letters.add(One_Blank_At_the_beginning_of_the_word(i));
					break;
				}
			}
		}
		
		return letters;
	}
	/**
	 * Removes the first letter of all the themes
	 */
	private String One_Blank_At_the_beginning_of_the_word(int index)
	{
		String a_word = this.themeList.get(index);
		
		String lettersRemoved = String.valueOf(a_word.charAt(0));
		
		a_word = "_" + a_word.substring(1);
		
		this.alteredThemes.add(a_word);
		
		return lettersRemoved;
	}
	/**
	 *  Removes the last letter of the theme
	 */
	private String One_Blank_At_the_end_of_the_word(int index)
	{
		String a_word = this.themeList.get(index);
		
		String lettersRemoved = String.valueOf(a_word.charAt(a_word.length() - 1));
		
		a_word = a_word.substring(0, a_word.length() - 1);
		a_word += "_";
		
		this.alteredThemes.add(a_word);
		
		return lettersRemoved;
	}
	/**
	 * 
	 */
	private String One_Blank_At_Random_Location(int index)
	{
		String lettersRemoved;
		String a_word = this.themeList.get(index);
		
		java.util.Random rand = new java.util.Random();
		char[] word = a_word.toCharArray();
		
		int val = rand.nextInt(a_word.length());
		
		lettersRemoved = String.valueOf(word[val]);
		word[val] = '_';
		
		this.alteredThemes.add(String.valueOf(word));
		
		return lettersRemoved;
	}
	/**
	 * 
	 */
	private java.util.ArrayList<String> Multiple_Blanks_at_all_Consonants(int index)
	{
		java.util.ArrayList<String> lettersRemoved = new java.util.ArrayList<String>();
		char[] word = this.themeList.get(index).toCharArray();
		
		for(int i = 0; i < word.length; i++){
			if(!isVowel(word[i])){
				lettersRemoved.add(String.valueOf(word[i]));
				word[i] = '_';
			}
		}
		
		this.alteredThemes.add(String.valueOf(word));
		
		return lettersRemoved;
	}
	/**
	 * 
	 */
	private java.util.ArrayList<String> Multiple_Blanks_at_all_Vowels(int index)
	{	
		java.util.ArrayList<String> lettersRemoved = new java.util.ArrayList<String>();
		
		char[] word = this.themeList.get(index).toCharArray();
		
		for(int i = 0; i < word.length; i++){
			if(isVowel(word[i])){
				lettersRemoved.add(String.valueOf(word[i]));
				word[i] = '_';
			}
		}
		
		this.alteredThemes.add(String.valueOf(word));
		
		return lettersRemoved;
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
	/**
	 * Returns all themes, URLs and words in a datarow format.
	 * i.e. <tr><td>word</td>theme[i]<td></td>url[i]<td></td>
	 * @return
	 * 	an html string
	 */
	public String toHtmlDataRow()
	{	
		StringBuilder string = new StringBuilder();
		
		for(int i = 0; i < alteredThemes.size(); i++){
			string.append("<tr><td>" + this.word + "</td>");
			string.append("<td>" + this.alteredThemes.get(i) + "</td>");
			string.append("<td>" + this.urlList.get(i) + "</td></tr>");
		}
		
		return string.toString();
	}
}