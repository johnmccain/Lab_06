import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;

public class Control extends JFrame implements ActionListener
{
	
	//Used for pop up messages
	JFrame popUpFrame;
	
	//JPanels
	JPanel content;
	
	JPanel votePanel;
	JPanel voteMain;
	JPanel voteCandidates;
	JPanel voteButtons;
	
	JPanel viewPanel;
	JPanel viewMain;
	JPanel viewButtons;
	
	//JLabels
	JLabel voteLabel;
	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel viewLabel;

	//JRadioButtons
	JRadioButton candidate1;
	JRadioButton candidate2;
	JRadioButton candidate3;
	JRadioButton candidate4;

	
	//Text Field
	JTextField firstName;
	JTextField lastName;
	
	//Card Layout
	CardLayout myLayout = new CardLayout();

	//VoteManager
	VoteManager voteManager;
	
	/**
	 * Constructor.  Creates the GUI.
	 */
	public Control()
	{
		super("Excercise 3: Voting Machine");
		
		voteManager = new VoteManager();

		//Sets the size, width px X height px
		setSize(500, 540);

		//Puts the window in the middle of the screen
		setLocationRelativeTo(null);
		
		//Make the window not resizable
		setResizable(false);
		
		//Exit the application when the "X" button is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel(myLayout);
		content.setPreferredSize(new Dimension(500, 540));
		
		//votePanel is the first card
		votePanel = new JPanel();
		votePanel.setPreferredSize(new Dimension(500, 500));
		
		voteMain = new JPanel(new GridLayout(0, 1));
		voteMain.setPreferredSize(new Dimension(500, 300));
		voteButtons = new JPanel(new GridLayout());
		voteButtons.setPreferredSize(new Dimension(500, 200));
		
		//viewPanel is the second card
		viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(500, 500));
		
		viewMain = new JPanel();
		viewMain.setPreferredSize(new Dimension(500, 300));
		viewButtons = new JPanel( new GridLayout());
		viewButtons.setPreferredSize(new Dimension(500, 200));
		
		//Create the JLabels
		voteLabel = new JLabel("Enter your name and choose your candidate");
		firstNameLabel = new JLabel("First:");
		lastNameLabel = new JLabel("Last:");
		viewLabel = new JLabel("");
		
		//Create the text fields
		firstName = new JTextField(8);
		lastName = new JTextField(8);

		//Create JPanel containers for fields
		JPanel firstField = new JPanel();
		JPanel lastField = new JPanel();

		//Create Radio Buttons and group them
		candidate1 = new JRadioButton("Carly Rae Jepsen");
		candidate2 = new JRadioButton("Aaron Carter");
		candidate3 = new JRadioButton("Justin Bieber");
		candidate4 = new JRadioButton("Snoop Dogg");

		ButtonGroup candidateGroup = new ButtonGroup();
		candidateGroup.add(candidate1);
		candidateGroup.add(candidate2);
		candidateGroup.add(candidate3);
		candidateGroup.add(candidate4);
		
		//Add RadioButtons, JLabels, and JTextFields to  to appropriate JPanels
		firstField.add(firstNameLabel);
		firstField.add(firstName);

		lastField.add(lastNameLabel);
		lastField.add(lastName);

		voteCandidates = new JPanel(new GridLayout(0, 1));
		voteCandidates.add(candidate1);
		voteCandidates.add(candidate2);
		voteCandidates.add(candidate3);
		voteCandidates.add(candidate4);

		voteMain.add(firstField);
		voteMain.add(lastField);
		voteMain.add(voteCandidates);
		
		viewMain.add(viewLabel);

		
		//Create the buttons
	    JButton voteButton = new JButton("Vote"); 
	    JButton backButton = new JButton("Back");
	    
	    //Add actionListeners for the buttons
	    voteButton.addActionListener(this);
	    backButton.addActionListener(this);
    
	    //Add the buttons to appropriate JPanels
	    voteButtons.add(voteButton);
	    viewButtons.add(backButton);
    
	    //Add the main and button JLabels to their card JLabels
	    votePanel.add(voteMain);
	    votePanel.add(voteButtons);
	    viewPanel.add(viewMain);
	    viewPanel.add(viewButtons);
	    
	    //Add the roll and view JLabel cards to the main content JLabel
	    content.add(votePanel);
	    content.add(viewPanel);

	    //Add the content to our JFrame
	    this.getContentPane().add(content);

	    //Reveal the dice roller to the world
		setVisible(true);

		//For pop up messages
		popUpFrame = new JFrame("Dialogue");
	}

	/**
	*	Check if a string can be part of a valid filename
	*	@param str the string to check
	*	@return true if the string is valid, false otherwise
	*/
	public boolean isValid(String str)
	{
		if (str.length() == 0)
		{
			return false;
		}
		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i) == '_')
			{
				return false;
			}
		}
		return true;
	}

	/**
	*	Check which candidate is selected
	*	@return the candidates name or "X" if no candidate is selected
	*/
	public String getVote()
	{
		if(candidate1.isSelected())
		{
			return "Carly Rae Jepsen";
		}
		else if(candidate2.isSelected())
		{
			return "Aaron Carter";
		}
		else if(candidate3.isSelected())
		{
			return "Justin Bieber";
		}
		else if(candidate4.isSelected())
		{
			return "Snoop Dogg";
		}
		return "X";
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
			case "Vote":
				String firstStr = firstName.getText();
				String lastStr = lastName.getText();
				if(!isValid(firstStr) || !isValid(lastStr))
				{
					JOptionPane.showMessageDialog(popUpFrame, "Error: Invalid name, names must be at least 1 character long and cannot contain the character _");
					break;
				}
				else if(voteManager.hasVoted(firstStr, lastStr))
				{
					JOptionPane.showMessageDialog(popUpFrame, "Error: This person has already voted!");
					break;
				}
				else if(getVote().equals("X"))
				{
					JOptionPane.showMessageDialog(popUpFrame, "Error: You must select a candidate.");
					break;
				}
				else
				{
					voteManager.submitVote(firstStr, lastStr, getVote());
					viewLabel.setText("Ballot cast by " + firstStr + " " + lastStr + " for candidate " + getVote());
				}
								
				myLayout.next(content);
				break;
			case "Back":
				viewLabel.setText("");
				myLayout.next(content);
				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "ERROR: Unrecognized event");
				break;
		}
	}
}
