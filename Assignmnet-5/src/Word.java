public class Word{
	// key word
	private String word;
	// list of themes
	private java.util.ArrayList<String> themeList;
	// url list
	private java.util.ArrayList<String> urlList;
	/**
	 * Default constructor
	 */
	public Word()
	{
		this.word = null;
		this.themeList =  new java.util.ArrayList<String>();
		this.urlList = new java.util.ArrayList<String>();
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
	
}