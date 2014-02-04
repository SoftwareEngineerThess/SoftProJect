import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddressBookDisplay extends JFrame {

	private JButton browseButton;
	private JButton nextButton;
	private JButton previousButton;
	private JButton queryButton;
	private JButton queryButton2;
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

	private Person currentEntry;
	private PersonQueries personQueries;
	private List<Person> results;
	private int numberOfEntries = 0;
	private int currentEntryIndex;

	public AddressBookDisplay() {
		super("Address Book");

		personQueries = new PersonQueries();

		// Dimiourgia GUI

		previousButton = new JButton();
		nextButton = new JButton();
		queryButton = new JButton();
		queryButton2 = new JButton();
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
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				previousButtonActionPerformed(evt);
			} // Telos methodou actionPerformed
		}); // Telos klisis addActionListener

		navigatePanel.add(previousButton);
		navigatePanel.add(Box.createHorizontalStrut(10));

		indexTextField.setHorizontalAlignment(JTextField.CENTER);
		indexTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				indexTextFieldActionPerformed(evt);
			}
		});

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
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		navigatePanel.add(nextButton);
		add(navigatePanel);

		displayPanel.setLayout(new GridLayout(5, 2, 4, 4));

		idLabel.setText("Κωδικός Εγγραφής:");
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
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				queryButtonActionPerformed(evt);
			}
		});

		queryButton2.setText("Σύνθετη Αναζήτηση");
		
		queryButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				queryButtonActionPerformed2(evt);
			}
		});
		
		queryPanel.add(queryButton);
		queryPanel.add(queryButton2);
		queryPanel.add(Box.createHorizontalStrut(5));
		add(queryPanel);

		browseButton.setText("Εμφάνιση όλων των εγγραφών");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				browseButtonActionPerformed(evt);
			}
		});

		add(browseButton);

		insertButton.setText("Εισαγωγή νέας εγγραφής");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				InsertPerson abd = new InsertPerson();
			}
		});

		add(insertButton);

		this.setSize(450, 710);
		setVisible(true);

	}

	private void previousButtonActionPerformed(ActionEvent evt) {
		currentEntryIndex--;

		if (currentEntryIndex < 0)
			currentEntryIndex = numberOfEntries - 1;

		indexTextField.setText("" + (currentEntryIndex + 1));
		indexTextFieldActionPerformed(evt);
	}

	private void nextButtonActionPerformed(ActionEvent evt) {
		currentEntryIndex++;

		if (currentEntryIndex >= numberOfEntries)
			currentEntryIndex = 0;

		indexTextField.setText("" + (currentEntryIndex + 1));
		indexTextFieldActionPerformed(evt);
	}

	private void queryButtonActionPerformed(ActionEvent evt) {
		results = personQueries.getPeopleByLastName(queryTextField.getText());
		numberOfEntries = results.size();

		if (numberOfEntries != 0) {
			JOptionPane.showMessageDialog(this, "Η Επαφή βρέθηκε!", "Μήνυμα",
					JOptionPane.PLAIN_MESSAGE);
			currentEntryIndex = 0;
			currentEntry = results.get(currentEntryIndex);
			idTextField.setText("" + currentEntry.getAddressID());
			firstNameTextField.setText(currentEntry.getFirstName());
			lastNameTextField.setText(currentEntry.getLastName());
			emailTextField.setText(currentEntry.getEmail());
			phoneTextField.setText(currentEntry.getPhoneNumber());
			maxTextField.setText("" + numberOfEntries);
			indexTextField.setText("" + (currentEntryIndex + 1));
			nextButton.setEnabled(true);
			previousButton.setEnabled(true);
		} else
			JOptionPane.showMessageDialog(this, "Η Επαφή δεν βρέθηκε!", "Μήνυμα",
					JOptionPane.PLAIN_MESSAGE);
			
	}
	private void queryButtonActionPerformed2(ActionEvent evt) {
		 new DisplayQueryResults();
	}

	private void indexTextFieldActionPerformed(ActionEvent evt) {
		currentEntryIndex = (Integer.parseInt(indexTextField.getText()) - 1);

		if (numberOfEntries != 0 && currentEntryIndex < numberOfEntries) {
			currentEntry = results.get(currentEntryIndex);
			idTextField.setText("" + currentEntry.getAddressID());
			firstNameTextField.setText(currentEntry.getFirstName());
			lastNameTextField.setText(currentEntry.getLastName());
			emailTextField.setText(currentEntry.getEmail());
			phoneTextField.setText(currentEntry.getPhoneNumber());
			maxTextField.setText("" + numberOfEntries);
			indexTextField.setText("" + (currentEntryIndex + 1));
		}
	}

	private void browseButtonActionPerformed(ActionEvent evt) {
		try {
			results = personQueries.getAllPeople();
			numberOfEntries = results.size();

			if (numberOfEntries != 0) {
				currentEntryIndex = 0;
				currentEntry = results.get(currentEntryIndex);
				idTextField.setText("" + currentEntry.getAddressID());
				firstNameTextField.setText(currentEntry.getFirstName());
				lastNameTextField.setText(currentEntry.getLastName());
				emailTextField.setText(currentEntry.getEmail());
				phoneTextField.setText(currentEntry.getPhoneNumber());
				maxTextField.setText("" + numberOfEntries);
				indexTextField.setText("" + (currentEntryIndex + 1));
				nextButton.setEnabled(true);
				previousButton.setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*	
	private void insertButtonActionPerformed(ActionEvent evt) {
		int result = personQueries.addPerson(firstNameTextField.getText(),
				lastNameTextField.getText(), emailTextField.getText(),
				phoneTextField.getText());

		if (result == 1)
			JOptionPane.showMessageDialog(this, "Προσθήκη Επαφής!",
					"Προσθήκη Επαφής!", JOptionPane.PLAIN_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Η Επαφή δεν προστέθηκε!", "Η Επαφή δεν προστέθηκε!",
					JOptionPane.PLAIN_MESSAGE);

		browseButtonActionPerformed(evt);
	}
	*/
	public static void main(String args[]) {
		AddressBookDisplay abd = new AddressBookDisplay();
		abd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
