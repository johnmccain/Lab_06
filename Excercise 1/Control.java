import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.lang.NumberFormatException;

public class Control extends JFrame implements ActionListener
{
	
	//Used for pop up messages
	JFrame popUpFrame;
	
	//JPanels
	JPanel content;
	
	JPanel rollPanel;
	JPanel rollMain;
	JPanel rollButtons;
	
	JPanel viewPanel;
	JPanel viewMain;
	JPanel viewButtons;
	
	//JLabels
	JLabel rollLabel;
	JLabel viewLabel;
	
	//Text Field
	JTextField text;
	
	//Card Layout
	CardLayout myLayout = new CardLayout();
	
	//For getting a random number
	Dice die;
	
	/**
	 * Constructor.  Creates the GUI.
	 */
	public Control()
	{
		super("Excercise 1: Dice");
		
		//Create the die
		die = new Dice();

		//Sets the size, width px X height px
		setSize(300, 240);

		//Puts the window in the middle of the screen
		setLocationRelativeTo(null);
		
		//Make the window not resizable
		setResizable(false);
		
		//Exit the application when the "X" button is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel(myLayout);
		content.setPreferredSize(new Dimension(300, 240));
		
		//rollPanel is the first card
		rollPanel = new JPanel();
		rollPanel.setPreferredSize(new Dimension(300, 200));
		
		rollMain = new JPanel();
		rollMain.setPreferredSize(new Dimension(300, 100));
		rollButtons = new JPanel(new GridLayout());
		rollButtons.setPreferredSize(new Dimension(300, 100));
		
		//viewPanel is the second card
		viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(300, 200));
		
		viewMain = new JPanel();
		viewMain.setPreferredSize(new Dimension(300, 100));
		viewButtons = new JPanel( new GridLayout());
		viewButtons.setPreferredSize(new Dimension(300, 100));
		
		//Create the JLabels
		rollLabel = new JLabel("Enter the number of sides:");
		viewLabel = new JLabel("View!");
		viewLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		
		//Create the text field
		text = new JTextField(3);
		
		//Add JLabels to appropriate JPanels
		rollMain.add(rollLabel);
		viewMain.add(viewLabel);
		
		//Add the text field
		rollMain.add(text);
		
		//Create the buttons
	    JButton rollButton = new JButton("Roll"); 
	    JButton reRollButton = new JButton("reRoll"); 

	    
	    //Add actionListeners for the buttons
	    rollButton.addActionListener(this);
	    reRollButton.addActionListener(this);
	    
	    //Add the buttons to appropriate JPanels
	    rollButtons.add(rollButton);
	    viewButtons.add(reRollButton);
	    
	    //Add the main and button JLabels to their card JLabels
	    rollPanel.add(rollMain);
	    rollPanel.add(rollButtons);
	    viewPanel.add(viewMain);
	    viewPanel.add(viewButtons);
	    
	    //Add the roll and view JLabel cards to the main content JLabel
	    content.add(rollPanel);
	    content.add(viewPanel);

	    //Add the content to our JFrame
	    this.getContentPane().add(content);

	    //Reveal the dice roller to the world
		setVisible(true);

		//For pop up messages
		popUpFrame = new JFrame("Dialogue");
	}
	
	/**
	*	Handle event responses (like button presses)
	*	@param	event the event that has occurred
	*	@post	the appropriate response to the event will be executed
	*/
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "Roll":
				String inputStr = text.getText();
				int sides;
				try
				{
					sides = Integer.parseInt(inputStr);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "That wasn't an integer");
					break;
				}
				if(sides < 1)
				{
					JOptionPane.showMessageDialog(popUpFrame, "You can't have less than 1 side");
					break;
				}
				
				viewLabel.setText(Integer.toString(die.roll(sides)));
				
				myLayout.next(content);
				break;
			case "reRoll":
				myLayout.next(content);
				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "ERROR: Unrecognized event");
				break;
		}
	}
}
