import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegistrationManagerAWT extends Frame implements ActionListener {
    // Components for registration form
    private TextField nameField, emailField;
    private CheckboxGroup genderGroup;
    private Checkbox male, female;
    private Choice countryChoice;
    private Button submitBtn, displayBtn, exportBtn, clearBtn;
    
    // Data storage
    private String name, email, gender, country;
    private TextArea displayArea;

    // Constructor to set up the GUI
    public RegistrationManagerAWT() {
        setTitle("AWT Registration Manager");
        setSize(500, 600);
        setLayout(new FlowLayout());
        setResizable(false);

        // Create and set up form components
        Label nameLabel = new Label("Name:");
        nameField = new TextField(20);

        Label emailLabel = new Label("Email:");
        emailField = new TextField(20);

        Label genderLabel = new Label("Gender:");
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);

        Label countryLabel = new Label("Country:");
        countryChoice = new Choice();
        countryChoice.add("United States");
        countryChoice.add("Canada");
        countryChoice.add("India");
        countryChoice.add("United Kingdom");
        countryChoice.add("Australia");

        // Buttons
        submitBtn = new Button("Submit");
        displayBtn = new Button("Display");
        exportBtn = new Button("Export to File");
        clearBtn = new Button("Clear");

        // Add action listeners to buttons
        submitBtn.addActionListener(this);
        displayBtn.addActionListener(this);
        exportBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        // Text area to display registration details
        displayArea = new TextArea(10, 40);
        displayArea.setEditable(false);

        // Add components to the Frame
        add(nameLabel);
        add(nameField);

        add(emailLabel);
        add(emailField);

        add(genderLabel);
        add(male);
        add(female);

        add(countryLabel);
        add(countryChoice);

        add(submitBtn);
        add(displayBtn);
        add(exportBtn);
        add(clearBtn);

        add(displayArea);

        // Window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Action listener for button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            // Capture user data from form fields
            name = nameField.getText();
            email = emailField.getText();
            gender = genderGroup.getSelectedCheckbox().getLabel();
            country = countryChoice.getSelectedItem();

            // Validate the input
            if (name.isEmpty() || email.isEmpty()) {
                displayArea.setText("Please fill in all fields.");
            } else {
                displayArea.setText("User Registered Successfully!");
            }
        } else if (e.getSource() == displayBtn) {
            // Display user details
            if (name != null && email != null && gender != null && country != null) {
                displayArea.setText("Name: " + name + "\n" +
                                    "Email: " + email + "\n" +
                                    "Gender: " + gender + "\n" +
                                    "Country: " + country);
            } else {
                displayArea.setText("No user data available. Please submit the form.");
            }
        } else if (e.getSource() == exportBtn) {
            // Export the data to a text file
            if (name != null && email != null && gender != null && country != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("registration_data.txt"))) {
                    writer.write("Name: " + name + "\n");
                    writer.write("Email: " + email + "\n");
                    writer.write("Gender: " + gender + "\n");
                    writer.write("Country: " + country + "\n");
                    displayArea.setText("Data exported to registration_data.txt");
                } catch (IOException ex) {
                    displayArea.setText("Error: Unable to export data.");
                }
            } else {
                displayArea.setText("No user data available. Please submit the form.");
            }
        } else if (e.getSource() == clearBtn) {
            // Clear the form and display area
            nameField.setText("");
            emailField.setText("");
            genderGroup.setSelectedCheckbox(male); // Default to male
            countryChoice.select(0); // Default to first country
            displayArea.setText("");
        }
    }

    // Main method to run the registration manager
    public static void main(String[] args) {
        new RegistrationManagerAWT();
    }
}
