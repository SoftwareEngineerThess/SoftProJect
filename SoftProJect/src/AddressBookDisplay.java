import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddressBookDisplay extends JFrame {

	private JButton browseButton;
	private JButton nextButton;
	private JButton previousButton;
	private JButton queryButton;
	private JButton insertButton;

	private JLabel emailLabel;
	private JLabel firstNameLabel;
	private JLabel idLabel;
	private JLabel lastNameLabel;
	private JLabel ofLabel;
	private JLabel phoneLabel;
	private JLabel queryLabel;
	private JLabel imageLabel;

	private JTextField emailTextField;
	private JTextField firstNameTextField;
	private JTextField idTextField;
	private JTextField indexTextField;
	private JTextField lastNameTextField;
	private JTextField maxTextField;
	private JTextField phoneTextField;
	private JTextField queryTextField;

	private ImageIcon image;

	private JPanel queryPanel;
	private JPanel navigatePanel;
	private JPanel displayPanel;

	public AddressBookDisplay() {
		super("Address Book");

		// Dimiourgia GUI

		previousButton = new JButton();
		nextButton = new JButton();
		queryButton = new JButton();
		browseButton = new JButton();
		insertButton = new JButton();

		ofLabel = new JLabel();
		idLabel = new JLabel();
		firstNameLabel = new JLabel();
		lastNameLabel = new JLabel();
		emailLabel = new JLabel();
		phoneLabel = new JLabel();
		queryLabel = new JLabel();

		indexTextField = new JTextField(2);
		maxTextField = new JTextField(2);
		idTextField = new JTextField(10);
		firstNameTextField = new JTextField(10);
		lastNameTextField = new JTextField(10);
		emailTextField = new JTextField(10);
		phoneTextField = new JTextField(10);
		queryTextField = new JTextField(10);

		image = new ImageIcon("image.jpg");
		imageLabel = new JLabel(image);

		navigatePanel = new JPanel();
		displayPanel = new JPanel();
		queryPanel = new JPanel();

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(400, 300);
		setResizable(false);

		add(imageLabel);

		navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));

		previousButton.setText("Προηγούμενο");
		previousButton.setEnabled(false);

		navigatePanel.add(previousButton);
		navigatePanel.add(Box.createHorizontalStrut(10));

		indexTextField.setHorizontalAlignment(JTextField.CENTER);

		navigatePanel.add(indexTextField);
		navigatePanel.add(Box.createHorizontalStrut(10));

		ofLabel.setText("από");
		navigatePanel.add(ofLabel);
		navigatePanel.add(Box.createHorizontalStrut(10));

		maxTextField.setHorizontalAlignment(JTextField.CENTER);
		maxTextField.setEditable(false);
		navigatePanel.add(maxTextField);
		navigatePanel.add(Box.createHorizontalStrut(10));

		nextButton.setText("Επόμενο");
		nextButton.setEnabled(false);

		navigatePanel.add(nextButton);
		add(navigatePanel);

		displayPanel.setLayout(new GridLayout(5, 2, 4, 4));

		idLabel.setText("Διεύθυνση ID:");
		displayPanel.add(idLabel);

		idTextField.setEditable(false);
		displayPanel.add(idTextField);

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

		queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.X_AXIS));

		queryPanel.setBorder(BorderFactory
				.createTitledBorder("Αναζήτηση εγγραφής με βάση το επώνυμο"));
		queryLabel.setText("Επώνυμο:");
		queryPanel.add(Box.createHorizontalStrut(5));
		queryPanel.add(queryLabel);
		queryPanel.add(Box.createHorizontalStrut(10));
		queryPanel.add(queryTextField);
		queryPanel.add(Box.createHorizontalStrut(10));

		queryButton.setText("Αναζήτηση");

		queryPanel.add(queryButton);
		queryPanel.add(Box.createHorizontalStrut(5));
		add(queryPanel);

		browseButton.setText("Εμφάνιση όλων των εγγραφών");

		add(browseButton);

		insertButton.setText("Εισαγωγή νέας εγγραφής");

		add(insertButton);

		this.setSize(450, 710);
		setVisible(true);

	}

	public static void main(String args[]) {
		AddressBookDisplay abd = new AddressBookDisplay();
		abd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
