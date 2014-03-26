import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class WorkSheetCreatorGUI extends JFrame {
	// label for the combobox theme
	private JLabel lblThemes;
	// saves all the words
	private JComboBox<String> themes;
	// holds the pnlOptions for the user to manipulate
	private JPanel pnlOptions;
	// panel for the pnlChooser
	private JPanel pnlChooser;
	// menu for help and validation summary
	private JPanel pnlMenu;
	// maximum length of the word
	private JLabel lblMinVal;
	// Displays the theme count for each word
	private JLabel lblThemeCount;
	// minimum length of the word
	private JTextField txtNumWords;
	// minimum value for the user to select
	private JTextField txtWordLength;
	// maximum value for the user to select
	private JLabel lblWordLength;
	// displays validation errors
	private JLabel lblValidationSummary;
	// holds the word count
	private JLabel lblWordCount;
	// holds the file
	private java.io.File file;
	// holds the txtFileName/directory
	private JTextField txtFileName;
	// FileChooser to select a file
	private JFileChooser open;
	// button which open(s)
	private JButton btnOpenFile;
	// closes the application
	private JButton btnClose;
	// btnHelp button
	private JButton btnHelp;
	// label for JCheckBox
	private JLabel lblRemovedLetters;
	// removes letters from the words
	private JCheckBox checkRemoveLetters;
	// create button
	private JButton btnCreate;
	
	// collection of words
	private WordCollection collection;
	/**
	 * Default Constructor
	 */
	public WorkSheetCreatorGUI()
	{
		this.collection = new WordCollection();
		this.InitializeComponents();
	}
	/**
	 * Initializes local fields/components of the class
	 */
	private void InitializeComponents()
	{
		//
		// JFrame
		//
		this.setLayout(null);
		this.setBounds(100, 100, 410, 580);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//
		// pnlMenu
		//
		this.pnlMenu = new JPanel(new java.awt.FlowLayout());
		this.pnlMenu.setBounds(10, 450 , 370, 80);
		this.pnlMenu.setBackground(Color.DARK_GRAY);
		//
		// pnlChooser
		//
		this.pnlChooser = new JPanel(null);
		this.pnlChooser.setBounds(10, 50, 370, 100);
		this.pnlChooser.setBackground(Color.DARK_GRAY);
		//
		// pnlOptions
		//
		this.pnlOptions = new JPanel(null);
		this.pnlOptions.setBounds(10, 150, 370, 300);
		//this.pnlOptions.setBackground(Color.RED); // comment out after testing
		//
		// lblMinVal
		//
		this.lblMinVal = new JLabel("# of words");
		this.lblMinVal.setBounds(10, 10, 100, 30);
		//
		// txtNumWords (# of words)
		//
		this.txtNumWords = new JTextField("");
		this.txtNumWords.setBounds(110, 10, 30, 30);
		this.txtNumWords.setEnabled(false);
		this.txtNumWords.addFocusListener(new java.awt.event.FocusListener(){

			@Override
			public void focusGained(FocusEvent e) { }

			@Override
			public void focusLost(FocusEvent e) {
				try{
					Integer.parseInt(txtNumWords.getText());
					int val = Integer.parseInt(txtNumWords.getText());
					if(val > -1 && val < 11){
						if(val > collection.getThemeCount(themes.getSelectedItem().toString())){
							setlblValidationSummaryText("There are Less themes than requested...");
						}
					} else {
						setlblValidationSummaryText("Minimum word length must be between 0 and 10");
						txtNumWords.requestFocus();
					}
					
				} catch(NumberFormatException nfEx){
					txtNumWords.requestFocus();
					setlblValidationSummaryText("Minimum word length must be a number");
				}
			}
			
		});
		//
		// lblRemovedLetters
		//
		this.lblRemovedLetters = new JLabel("Default Text");
		this.lblRemovedLetters.setBounds(10, 20, 100, 30);
		this.lblRemovedLetters.setForeground(Color.RED);
		//
		// lblWordLength
		//
		this.lblWordLength = new JLabel("Word Length");
		this.lblWordLength.setBounds(10, 40, 100, 30);
		//
		//  txtWordLength
		//
		this.txtWordLength = new JTextField("");
		this.txtWordLength.setBounds(110, 40, 30, 30);
		this.txtWordLength.setEnabled(false);
		this.txtWordLength.addFocusListener(new java.awt.event.FocusListener(){

			@Override
			public void focusGained(FocusEvent e) { }

			@Override
			public void focusLost(FocusEvent e) {
				try{
					int val = Integer.parseInt(txtWordLength.getText());
					if(val > -1 && val < 11){
						setlblValidationSummaryText("Minimum word length set to " + val);
					} else {
						setlblValidationSummaryText("Word length must be between 0 and 10");
						txtWordLength.requestFocus();
					}
				} catch(NumberFormatException nfEx){
					txtWordLength.requestFocus();
					setlblValidationSummaryText("Word length Value must be a number");
				}
				
			}
			
		});
		//
		// lblWordCount
		//
		this.lblWordCount = new JLabel("Word Count: N/A");
		this.lblWordCount.setBounds(150, 10, 110, 30);
		//
		// lblThemeCount
		//
		this.lblThemeCount = new JLabel("Theme Count: N/A");
		this.lblThemeCount.setBounds(270, 10, 110, 30);
		//
		// lblThemes
		//
		this.lblThemes = new JLabel("Select a theme");
		this.lblThemes.setBounds(150, 40, 110, 30);
		//
		// themes
		//
		this.themes = new JComboBox<String>();
		this.themes.setEnabled(false);
		this.themes.setBounds(240, 40, 110, 30);
		//this.themes.addItem("All Words");
		this.themes.addItemListener(new java.awt.event.ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(themes.isEnabled() && !themes.getSelectedItem().toString().equals("All Words")){
					setlblThemeCount(collection.getThemeCount(themes.getSelectedItem().toString()));
					setlblValidationSummaryText("Selected theme " + themes.getSelectedItem().toString());
					checkRemoveLetters.setSelected(false);
				} else {//(themes.getSelectedItem().toString().equals("All Words")){
					int total = 0;
					
					for(Word word : collection.getWordList()){
						total += word.getThemeCount();
					}
					setlblThemeCount(total);
					setlblValidationSummaryText("Selected theme " + themes.getSelectedItem().toString());
				}
			}
			
		});
		//
		// lblValidationSummary
		//
		this.lblValidationSummary = new JLabel("Test display");
		this.lblValidationSummary.setForeground(Color.RED);
		//
		// txtFileName
		//
		this.txtFileName = new JTextField("Select a file");
		this.txtFileName.setEnabled(false); // disables, and makes read-only
		this.txtFileName.setBounds(30, 10, 300, 30);
		this.txtFileName.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showChooserDialog();
			}
		});
		//
		// open
		//
		this.open = new JFileChooser();
		//
		// btnOpenFile
		//
		this.btnOpenFile = new JButton("Open CSV");
		this.btnOpenFile.setBounds(30, 45, 100, 30);
		this.btnOpenFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showChooserDialog();
			}
		});
		//
		// btnHelp
		//
		this.btnHelp = new JButton("Help");
		this.btnHelp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelpDialaog();
			}
		});
		//
		// btnClose
		//
		this.btnClose = new JButton("Close");
		this.btnClose.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		//
		// lblRemovedLetters
		//
		this.lblRemovedLetters = new JLabel("Removed letters");
		this.lblRemovedLetters.setBounds(10, 100, 400, 70);
		//
		// checkRemoveLetters
		//
		this.checkRemoveLetters = new JCheckBox("Remove letters");
		this.checkRemoveLetters.setBounds(10, 70, 120, 30);
		this.checkRemoveLetters.setSelected(false);
		this.checkRemoveLetters.setEnabled(false);
		this.checkRemoveLetters.addItemListener(new java.awt.event.ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(checkRemoveLetters.isSelected()){
					setlblValidationSummaryText("Letters will be removed when HTML file is created");
				} else {
					setlblValidationSummaryText("Letters will NOT be removed when HTML file is created");
				}
			}
			
		});
		//
		// btnCreate
		//
		this.btnCreate = new JButton("Create");
		this.btnCreate.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				create();
			}
		});
		//
		// add components to the panels
		//
		this.pnlMenu.add(this.btnCreate);
		this.pnlMenu.add(this.btnHelp);
		this.pnlMenu.add(this.btnClose);
		this.pnlMenu.add(this.lblValidationSummary);
		
		this.pnlChooser.add(this.txtFileName);
		this.pnlChooser.add(this.btnOpenFile);
		
		this.pnlOptions.add(this.lblRemovedLetters);
		this.pnlOptions.add(this.lblWordCount);
		this.pnlOptions.add(this.lblThemeCount);
		this.pnlOptions.add(this.lblThemes);
		this.pnlOptions.add(this.themes);
		this.pnlOptions.add(this.lblMinVal);
		this.pnlOptions.add(this.txtNumWords);
		this.pnlOptions.add(this.lblWordLength);
		this.pnlOptions.add(this.txtWordLength);
		this.pnlOptions.add(this.checkRemoveLetters);
		
		
		this.add(this.pnlChooser);
		this.add(this.pnlOptions);
		this.add(this.pnlMenu);
		
	}
	/**
	 * Displays a JFileChooser Dialog and setup the display controls
	 */
	private void showChooserDialog()
	{
		int returnVal = this.open.showOpenDialog(WorkSheetCreatorGUI.this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			this.file = this.open.getSelectedFile();
			
			if(this.validExtension(file).equals("csv")){
				this.setlblValidationSummaryText("File Selected");
				this.txtFileName.setText(this.file.getPath());
				this.setup();
			} else if(returnVal == JFileChooser.CANCEL_OPTION){
				this.setlblValidationSummaryText("User action canceled");
				this.txtFileName.setText("Select a file");
				this.setDefault();
			} else {
				this.setlblValidationSummaryText("Invalid File chosen");
				this.txtFileName.setText("Select a file");
				this.setDefault();
			}
		} else {
			this.txtFileName.setText("Select a file");
			this.setDefault();
		}
	}
	/**
	 * 
	 * @param f
	 * 	file which is being attempted to open
	 * @return
	 * 	the last few characters after the last index of "."
	 * 
	 * @see http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html#filters
	 */
	private String validExtension(java.io.File f)
	{
		String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        
        return ext;
	}
	/**
	 * Output error to the user
	 * @param text
	 * 	Text to be displayed as a error to the user
	 */
	private void setlblValidationSummaryText(String text)
	{
		this.lblValidationSummary.setText(text);
	}
	/**
	 * Displays the help dialog
	 */
	public void showHelpDialaog()
	{
		JOptionPane.showMessageDialog(this, "1. Click the button label \"Open\"\n 2. Select a \".csv\" file \n 3. ");
	}
	/**
	 * Enables and fills all the controls
	 */
	private void setup()
	{
		this.collection.readFile(this.file);
		this.fillThemeBox();
		this.lblWordCount.setText("Word Count: " + this.collection.getSize());
		this.txtNumWords.setText("10");
		this.txtNumWords.setEnabled(true);
		this.txtWordLength.setEnabled(true);
		this.txtWordLength.setText("2");
		this.checkRemoveLetters.setEnabled(true);
	}
	/**
	 * Fills the JComboBox with all the <i>Key</i> Words
	 */
	private void fillThemeBox()
	{
		this.themes.addItem("All Words");
		for(Word word: this.collection.getWordList()){
			this.themes.addItem(word.getWord());
		}
		this.themes.setEnabled(true);
		
		int total = 0;
		
		for(Word word : collection.getWordList()){
			total += word.getThemeCount();
		}
		setlblThemeCount(total);
	}
	/**
	 * Defaults the form
	 */
	private void setDefault()
	{
		this.collection.getWordList();
		this.txtFileName.setText("Select a file");
		this.txtWordLength.setText("");
		this.txtWordLength.setEnabled(false);
		this.txtNumWords.setText("");
		this.txtNumWords.setEnabled(false);
		this.lblWordCount.setText("Word Count: N/A");
		this.collection.removeAllThemes();
		this.themes.removeAllItems();
		this.themes.setEnabled(false);
		this.lblThemeCount.setText("Theme Count: N/A");
		this.checkRemoveLetters.setEnabled(false);
		this.lblRemovedLetters.setText("");
	}
	/**
	 * Sets the text
	 */
	public void setlblThemeCount(int count)
	{
		this.lblThemeCount.setText("Theme Count: " + count);
	}
	/**
	 * Closes and disables the parent JFrame
	 */
	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}
	/**
	 * creates the HTML form and opens it
	 */
	private void create()
	{
		
		if(themes.getSelectedItem().toString() == "All Words") {
			java.util.Random rand = new java.util.Random();
			int value = rand.nextInt(themes.getItemCount() - 1); // randomly select a word
			collection.toHtmlFile(themes.getItemAt(value), 
									Integer.parseInt(txtWordLength.getText()), 
									Integer.parseInt(txtNumWords.getText()), 
									checkRemoveLetters.isSelected());
		} else {
			collection.toHtmlFile(themes.getSelectedItem().toString(), 
									Integer.parseInt(txtWordLength.getText()), 
									Integer.parseInt(txtNumWords.getText()), 
									checkRemoveLetters.isSelected());
		}
	}
	
}
