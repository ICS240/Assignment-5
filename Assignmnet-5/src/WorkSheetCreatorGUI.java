import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	// minimum length of the word
	private JTextField txtMinVal;
	// minimum value for the user to select
	private JTextField txtMaxVal;
	// maximm value for the user to select
	private JLabel lblMaxVal;
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
		this.setBounds(100, 100, 500, 550);
		//
		// pnlMenu
		//
		this.pnlMenu = new JPanel(new java.awt.FlowLayout());
		this.pnlMenu.setBounds(10, 0, 370, 50);
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
		this.pnlOptions.setBounds(10, 150, 370, 120);
		//this.pnlOptions.setBackground(Color.RED); // comment out after testing
		//
		// lblMinVal
		//
		this.lblMinVal = new JLabel("Minimum Value");
		this.lblMinVal.setBounds(10, 10, 100, 30);
		//
		// txtMinVal
		//
		this.txtMinVal = new JTextField("");
		this.txtMinVal.setBounds(110, 10, 30, 30);
		//
		// lblMaxVal
		//
		this.lblMaxVal = new JLabel("Maximum Value");
		this.lblMaxVal.setBounds(10, 40, 100, 30);
		//
		//  txtMaxVal
		//
		this.txtMaxVal = new JTextField("");
		this.txtMaxVal.setBounds(110, 40, 30, 30);
		//
		// lblWordCount
		//
		this.lblWordCount = new JLabel("Word Count: N/A");
		this.lblWordCount.setBounds(150, 10, 110, 30);
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
		this.themes.setBounds(150, 80, 110, 30);
		this.themes.addItem("All Words");
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
				
			}
		});
		//
		// add components to the panels
		//
		this.pnlMenu.add(this.btnHelp);
		this.pnlMenu.add(this.btnClose);
		this.pnlMenu.add(this.lblValidationSummary);
		
		this.pnlChooser.add(this.txtFileName);
		this.pnlChooser.add(this.btnOpenFile);
		
		this.pnlOptions.add(this.lblWordCount);
		this.pnlOptions.add(this.lblThemes);
		this.pnlOptions.add(this.themes);
		this.pnlOptions.add(this.lblMinVal);
		this.pnlOptions.add(this.txtMinVal);
		this.pnlOptions.add(this.lblMaxVal);
		this.pnlOptions.add(this.txtMaxVal);
		
		this.add(this.pnlMenu);
		this.add(this.pnlChooser);
		this.add(this.pnlOptions);
		
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
			} else {
				this.setlblValidationSummaryText("Invalid File chosen");
				this.txtFileName.setText("Select a file");
			}
		} else {
			this.txtFileName.setText("Select a file");
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
		this.fillThemeBox();
	}
	/**
	 * Fills the JComboBox with all the <i>Key</i> Words
	 */
	private void fillThemeBox()
	{
		
	}
	
	
}
