import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.lang.NumberFormatException;
import java.lang.UnsupportedOperationException;

public class Control extends JFrame implements ActionListener
{
	
	//Used for pop up messages
	JFrame popUpFrame;
	
	//JPanels
	JPanel content;
	JPanel radioButtonPanel;
	JPanel radioFromPanel;
	JPanel radioToPanel;
	
	//JLabels
	JLabel fromLabel;
	JLabel toLabel;
	JLabel convertLabel;
	
	//Text Field
	JTextField text;

	//JRadioButtons
	JRadioButton fromC;
	JRadioButton fromK;
	JRadioButton fromF;
	JRadioButton toC;
	JRadioButton toK;
	JRadioButton toF;
	
	/**
	 * Constructor.  Creates the GUI.
	 */
	public Control()
	{
		super("Excercise 2: Temperature Converter");
		
		//Sets the size, width px X height px
		setSize(400, 300);

		//Puts the window in the middle of the screen
		setLocationRelativeTo(null);
		
		//Make the window not resizable
		setResizable(false);
		
		//Exit the application when the "X" button is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create JPanels
		content = new JPanel();
		content.setPreferredSize(new Dimension(400, 300));
		radioButtonPanel = new JPanel(new GridLayout(1, 2));
		radioFromPanel = new JPanel(new GridLayout(4, 1));
		radioToPanel = new JPanel(new GridLayout(4, 1));
		
		//Create the JLabels
		fromLabel = new JLabel("From Unit");
		toLabel = new JLabel("To Unit");
		convertLabel = new JLabel("");
		convertLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		
		//Create the text field
		text = new JTextField(3);

		//Create Radio Buttons and group them
		fromC = new JRadioButton("C");
		fromC.setSelected(true);
		fromK = new JRadioButton("K");
		fromF = new JRadioButton("F");

		ButtonGroup fromGroup = new ButtonGroup();
		fromGroup.add(fromC);
		fromGroup.add(fromK);
		fromGroup.add(fromF);

		toC = new JRadioButton("C");
		toC.setSelected(true);
		toK = new JRadioButton("K");
		toF = new JRadioButton("F");

		ButtonGroup toGroup = new ButtonGroup();
		toGroup.add(toC);
		toGroup.add(toK);
		toGroup.add(toF);

		//Add Radio Buttons and Labels to appropriate JPanels
		radioFromPanel.add(fromLabel);
		radioFromPanel.add(fromC);
		radioFromPanel.add(fromK);
		radioFromPanel.add(fromF);

		radioToPanel.add(toLabel);
		radioToPanel.add(toC);
		radioToPanel.add(toK);
		radioToPanel.add(toF);

		radioToPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		radioFromPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


		radioButtonPanel.add(radioFromPanel);
		radioButtonPanel.add(radioToPanel);
		content.add(radioButtonPanel);

		//Add the text field
		content.add(text);
		
		//Create the buttons
	    JButton convertButton = new JButton("Convert"); 

	    //Add actionListeners for the buttons
	    convertButton.addActionListener(this);
	    
	    //Add the buttons to appropriate JPanels
	    content.add(convertButton);

	    //Add JLabels to appropriate JPanels
		content.add(convertLabel);
	    
	    //Add the content to our JFrame
	    this.getContentPane().add(content);

	    //Reveal the temperature converter to the world
		setVisible(true);

		//For pop up messages
		popUpFrame = new JFrame("Dialogue");
	}

	public char getUnitFrom()
	{
		if(fromC.isSelected())
		{
			return 'C';
		}
		else if(fromK.isSelected())
		{
			return 'K';
		}
		else if(fromF.isSelected())
		{
			return 'F';
		}
		return 'X';
	}

	public char getUnitTo()
	{
		if(toC.isSelected())
		{
			return 'C';
		}
		else if(toK.isSelected())
		{
			return 'K';
		}
		else if(toF.isSelected())
		{
			return 'F';
		}
		return 'X';
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
			case "Convert":
				char fromUnit = getUnitFrom();
				char toUnit = getUnitTo();
				String inputStr = text.getText();
				double temperature;
				try
				{
					temperature = Double.parseDouble(inputStr);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "That wasn't a number!");
					break;
				}

				try
				{
					convertLabel.setText(temperature + " " + fromUnit + " is " + TemperatureConverter.convert(fromUnit, toUnit, temperature) + " " + toUnit);
				}
				catch(UnsupportedOperationException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "You need to select valid units!");
					break;
				}

				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "ERROR: Unrecognized event");
				break;
		}
	}
}
