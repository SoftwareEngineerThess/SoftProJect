import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertPerson extends JFrame {

	
	private JButton insertButton;

	private JLabel emailLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel phoneLabel;
	private JLabel imageLabel;

	private JTextField emailTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneTextField;

	private JPanel navigatePanel;
	private JPanel displayPanel;

	
	private PersonQueries personQueries;
	

	public InsertPerson() {
		super("Insert Contact");

		personQueries = new PersonQueries();

		insertButton = new JButton();

		firstNameLabel = new JLabel();
		lastNameLabel = new JLabel();
		emailLabel = new JLabel();
		phoneLabel = new JLabel();

		
		firstNameTextField = new JTextField(10);
		lastNameTextField = new JTextField(10);
		emailTextField = new JTextField(10);
		phoneTextField = new JTextField(10);

		navigatePanel = new JPanel();
		displayPanel = new JPanel();

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(400, 300);
		setResizable(false);

		navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));
		
		displayPanel.setLayout(new GridLayout(5, 2, 4, 4));
	
		firstNameLabel.setText("Όνομα:");
		displayPanel.add(firstNameLabel);
		displayPanel.add(firstNameTextField);

		lastNameLabel.setText("Επώνυμο:");
		displayPanel.add(lastNameLabel);
		displayPanel.add(lastNameTextField);

		emailLabel.setText("Email:");
		displayPanel.add(emailLabel);
		displayPanel.add(emailTextField);

		phoneLabel.setText("Τηλέφωνο:");
		displayPanel.add(phoneLabel);
		displayPanel.add(phoneTextField);
		add(displayPanel);

		insertButton.setText("Εισαγωγή νέας εγγραφής");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				insertButtonActionPerformed(evt);
				setVisible(false);
			}
		});
		

		add(insertButton);

		this.setSize(400, 200);
		setVisible(true);

	}

	private void insertButtonActionPerformed(ActionEvent evt) {
		int result = personQueries.addPerson(firstNameTextField.getText(),
				lastNameTextField.getText(), emailTextField.getText(),
				phoneTextField.getText());

		if (result == 1){
			JOptionPane.showMessageDialog(this, "Προσθήκη Επαφής!",
					"Μήνυμα", JOptionPane.PLAIN_MESSAGE);
			
		}else
			JOptionPane.showMessageDialog(this, "Η Επαφή δεν προστέθηκε!", "Μήνυμα",
					JOptionPane.PLAIN_MESSAGE);
		
		
	}

	public static void main(String args[]) {
		InsertPerson abd = new InsertPerson();
		abd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
