package CMS;

import CMS.Person;
import CMS.Main;

import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI implements ActionListener{

    public static String viewContacts="";
    public static String lastSearchedContact="";
    JLabel projectName;
    JLabel statusLabel;
    JLabel searchResultLabel;
    JLabel deleteResultLabel;
    JLabel viewContactsTextArea;


    JFrame frame;
    JButton addContactButton;
    JButton viewContactsButton;
    JButton searchButton;
    JButton deleteButton;

    JTextField nameField;
    JTextField numberField;

    JTextField searchField;

    JPanel leftPanel;
    JPanel topPanel;
    JPanel centerPanel;
    JPanel rightPanel;
    public GUI(){


        frame= new JFrame();

        projectName = new JLabel("Contact Management System", SwingConstants.CENTER);
        projectName.setFont(new Font("Serif", Font.BOLD, 30));


        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        topPanel.setBackground(Color.RED);
        topPanel.add(projectName);


        //Left Panel - Button Menu

        nameField = new JTextField("Name");
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Name")) {
                    nameField.setText("");
//                    nameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
//                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Name");
                }
            }
        });

        numberField = new JTextField("Number");
        numberField.setHorizontalAlignment(JTextField.CENTER);
        numberField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (numberField.getText().equals("Number")) {
                    numberField.setText("");
//                  numberField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (numberField.getText().isEmpty()) {
//                  numberField.setForeground(Color.GRAY);
                    numberField.setText("Number");
                }
            }
        });

        addContactButton = new JButton("Add Contact");
        addContactButton.addActionListener((ActionListener) this);



        statusLabel = new JLabel("", SwingConstants.CENTER);


        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(7,1,10,10));
        leftPanel.setBackground(Color.CYAN);
        leftPanel.add(nameField);
        leftPanel.add(numberField);
        leftPanel.add(addContactButton);
        leftPanel.add(statusLabel);



        //Center Panel

        viewContactsButton = new JButton("View Contacts");
        viewContactsButton.addActionListener(viewContactsButtonListener);

        viewContactsTextArea = new JLabel();
        viewContactsTextArea.setSize(50,200);
        viewContactsTextArea.setVisible(false);
        viewContactsTextArea.setHorizontalAlignment(SwingConstants.CENTER);

        viewContactsTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7,1,50,10));
        centerPanel.setBackground(Color.BLUE);
        centerPanel.add(viewContactsButton);
        centerPanel.add(viewContactsTextArea);




        //Right Panel

        searchField = new JTextField("Search By Name");
        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search By Name")) {
                    searchField.setText("");
//                  searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
//                  searchField.setForeground(Color.GRAY);
                    searchField.setText("Search By Name");
                }
            }
        });

        searchButton = new JButton("Search in Contacts");
        searchButton.addActionListener(searchButtonListener);

        searchResultLabel = new JLabel("", SwingConstants.CENTER);

        deleteButton = new JButton("Delete searched Contact");
        deleteButton.addActionListener(deleteButtonListener);

        deleteResultLabel = new JLabel("", SwingConstants.CENTER);


        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(7,5,50,10));
        rightPanel.setBackground(Color.yellow);
        rightPanel.add(searchField);
        rightPanel.add(searchButton);
        rightPanel.add(searchResultLabel);
        rightPanel.add(deleteButton);
        rightPanel.add(deleteResultLabel);




        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contact Management System");
        frame.pack();
        frame.setVisible(true);

//        GraphicsEnvironment graphics =
//                GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice device = graphics.getDefaultScreenDevice();
//        device.setFullScreenWindow(frame);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(nameField.getText()=="" || numberField.getText()==""){
            statusLabel.setText("Invalid Contact Details");
            statusLabel.setForeground(Color.RED);
        }
        if(nameField.getText()=="Name" || numberField.getText()=="Number"){
            statusLabel.setText("Set both Name and number!");
            statusLabel.setForeground(Color.RED);
        }
        if(!nameField.getText().equals("") && !numberField.getText().equals("") && !nameField.getText().equals("Name") && !numberField.getText().equals("Number")){
            Main.saveXML(nameField.getText(), numberField.getText());

            statusLabel.setText("Contact Saved");
            statusLabel.setForeground(Color.BLACK);

            nameField.setText("Name");
            numberField.setText("Number");
        }
        else {
            statusLabel.setText("Set both Name and number!");
            statusLabel.setForeground(Color.RED);
        }
    }

    ActionListener viewContactsButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewContactsTextArea.setVisible(true);
            viewContactsTextArea.setText(Main.viewXML());

            viewContacts="";
        }
    };

    ActionListener searchButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lastSearchedContact = searchField.getText();
            searchResultLabel.setText(searchField.getText() + ": " + Main.searchContacts(searchField.getText()));
            searchField.setText("Search By Name");
        }
    };

    ActionListener deleteButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(lastSearchedContact==""){
                deleteResultLabel.setText("Search a string!");
                deleteResultLabel.setForeground(Color.RED);
            }
            else{
                Main.deleteContacts(lastSearchedContact, Main.searchContacts(lastSearchedContact));
                deleteResultLabel.setForeground(Color.BLACK);
                deleteResultLabel.setText(lastSearchedContact + " Deleted");
                lastSearchedContact="";
            }

        }
    };


}
