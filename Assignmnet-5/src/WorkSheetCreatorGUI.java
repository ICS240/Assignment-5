import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class WorkSheetCreatorGUI extends JFrame {
	// saves all the words
	private JComboBox<String> themes;
	// holds the options for the user to manipulate
	private JPanel options;
	// panel for the chooser
	private JPanel chooser;
	// menu for help and validation summary
	private JPanel menu;
	// maximum length of the word
	private JLabel minVal;
	// minimum length of the word
	private JLabel maxVal;
	// displays validation errors
	private JLabel validationSummary;
	// holds the word count
	private JLabel wordCount;
	// holds the file
	private java.io.File file;
	// holds the filename/directory
	private JTextField fileName;
	// button which open(s)
	private JFileChooser open;
	// closes the application
	private JButton close;
	// help button
	private JButton help;
	
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
		this.setLayout(new java.awt.GridLayout(1,3));
		this.setBounds(100, 100, 300, 550);
		//
		// chooser
		//
		this.chooser = new JPanel(new java.awt.FlowLayout());
		//
		// menu
		//
		this.menu = new JPanel(new java.awt.FlowLayout());
		//
		// options
		//
		this.options = new JPanel(new java.awt.FlowLayout());
		//
		// wordCount
		//
		this.wordCount = new JLabel("0");
		this.wordCount.setEnabled(false); // read-only
		this.setSize(50, 30);
		//
		// themes
		//
		this.themes = new JComboBox<String>();
		this.themes.setEnabled(false);
		this.themes.addItem("All Words");
		//
		// validationSummary
		//
		this.validationSummary = new JLabel("Test display");
		this.validationSummary.setForeground(Color.RED);
		//
		// fileNAme
		//
		this.fileName = new JTextField("Select a file");
		this.fileName.setEnabled(false); // disables, and makes read-only
		this.fileName.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showChooserDialog();
			}
		});
		//
		// open
		//
		this.open = new JFileChooser();
		this.open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showChooserDialog();
			}
		});
		//
		// help
		//
		this.help = new JButton("Help");
		this.help.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelpDialaog();
			}
		});
		//
		// close
		//
		this.close = new JButton("Close");
		this.close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//
		// add components to the panels
		//
		this.menu.add(this.help);
		this.menu.add(this.close);
		this.menu.add(this.validationSummary);
		
		this.chooser.add(this.fileName);
		this.chooser.add(this.open);
		
		this.options.add(this.wordCount);
		this.options.add(this.themes);
		this.options.add(this.minVal);
		this.options.add(this.maxVal);
		
		this.add(this.menu);
		this.add(this.chooser);
		this.add(this.options);
		
	}
	/**
	 * Displays a JFileChooser Dialog
	 */
	private void showChooserDialog()
	{
		int returnVal = this.open.showOpenDialog(WorkSheetCreatorGUI.this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			this.file = this.open.getSelectedFile();
			
			if(this.validExtension(file).equals("csv")){
				this.fileName.setText(this.file.getPath());
			} else if(returnVal == JFileChooser.CANCEL_OPTION){
				this.fileName.setText("Select a file");
			} else {
				this.setValidationSummaryText("Invalid File chosen");
				this.fileName.setText("Select a file");
			}
		} else {
			this.fileName.setText("Select a file");
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
	private void setValidationSummaryText(String text)
	{
		this.validationSummary.setText(text);
	}
	/**
	 * Displays the help dialog
	 */
	public void showHelpDialaog()
	{
		JOptionPane.showMessageDialog(open, this, null, JOptionPane.OK_OPTION);
	}
	/**
	 * Enables and fills all the controls
	 */
	private void setup()
	{
		
	}
	/**
	 * Fills the JComboBox with all the themes
	 */
	private void fillThemeBox()
	{
		
	}
	
	
}
