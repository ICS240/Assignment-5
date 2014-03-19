

import java.io.FileNotFoundException;
import java.io.IOException;

public class Tester {
	/**
	 * Test class
	 */
	public static WordCollection collection = new WordCollection();
	/**
	 * @param args
	 */
	public static void main(String[] args){ 
		java.io.File f = new java.io.File("C:\\Users\\CodeBracket\\workspace\\Assignment-5\\Assignmnet-5\\Data\\Test.csv");
		try {
			ReadFile(f);
		} catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param f
	 * 	File 
	 * @precondition
	 * 	Assumes that only the URL of the .csv file can be null
	 * @throws FileNotFoundException
	 * 	The file could not be opened, or was not found
	 * @throws IOException
	 * 	Did not have permissions to open the file, or the file is the wrong format
	 * @throws NullPointerException
	 * 	The list was split and ran outside of the bounds
	 */
	@SuppressWarnings("resource")
	public static void ReadFile(java.io.File f) throws FileNotFoundException, IOException, NullPointerException, ArrayIndexOutOfBoundsException
	{
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(f));
		
		java.util.ArrayList<String> lines = new java.util.ArrayList<String>();
		
		String line = null;
		
		while ((line = reader.readLine()) != null) {
		    lines.add(line);
		}
		
		System.out.println("Printing out Lines........................................");
		for(int i = 0; i< lines.size(); i++){
			String[] items = lines.get(i).split(",");
			if(items.length > 2) { 
				//System.out.println("Key word = " + items[0] + " theme = " + items[1] + " url = " + items[2]);
				collection.addWord(new Word(items[0], items[1], items[2])); // could have an error occur here 
			} else { 
				//System.out.println("Key word = " + items[0] + " theme = " + items[1] + " url = null");
				collection.addWord(new Word(items[0], items[1], "")); 
			}
		}
		
		System.out.println(collection);
		
	}

}
