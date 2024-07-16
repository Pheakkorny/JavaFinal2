import javax.swing.*;import java.awt.*;
import java.awt.event.ActionEvent;import java.awt.event.ActionListener;
import java.sql.*;
public class CustomerInfo extends JFrame implements ActionListener {    private JTextField txtCustomerID, txtLastName, txtFirstName, txtPhone;
    private JButton btnPrevious, btnNext;    private Connection connection;
    private Statement statement;    private ResultSet resultSet;
    public CustomerInfo() {
        // Initialize the frame        setTitle("Customer Information");
        setSize(400, 200);        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        // Create components        JLabel lblCustomerID = new JLabel("ID:");
        txtCustomerID = new JTextField();        txtCustomerID.setEditable(false);
        JLabel lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField();        txtLastName.setEditable(false);

        JLabel lblFirstName = new JLabel("First Name:");        txtFirstName = new JTextField();
        txtFirstName.setEditable(false);
        JLabel lblPhone = new JLabel("Phone:");        txtPhone = new JTextField();
        txtPhone.setEditable(false);
        btnPrevious = new JButton("Previous");        btnNext = new JButton("Next");
        // Add action listeners
        btnPrevious.addActionListener(this);        btnNext.addActionListener(this);
        // Add components to frame
        add(lblCustomerID);        add(txtCustomerID);
        add(lblLastName);        add(txtLastName);
        add(lblFirstName);        add(txtFirstName);
        add(lblPhone);        add(txtPhone);
        add(btnPrevious);        add(btnNext);
        // Initialize database connection
        initDBConnection();
        // Load the first record        loadFirstRecord();
        setVisible(true);
    }
    private void initDBConnection() {        try {
        // Load SQLite JDBC driver            Class.forName("org.sqlite.JDBC");
        // Connect to the database            connection = DriverManager.getConnection("jdbc:sqlite:customer.db");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);            resultSet = statement.executeQuery("SELECT * FROM Customer");
    } catch (Exception e) {            e.printStackTrace();
    }    }
    private void loadFirstRecord() {
        try {            if (resultSet.first()) {
            displayRecord();            }
        } catch (SQLException e) {            e.printStackTrace();
        }    }
    private void displayRecord() {
        try {            txtCustomerID.setText(resultSet.getString("customer_id"));
            txtLastName.setText(resultSet.getString("customer_last_name"));            txtFirstName.setText(resultSet.getString("customer_first_name"));
            txtPhone.setText(resultSet.getString("customer_phone"));        } catch (SQLException e) {
            e.printStackTrace();        }
    }
    @Override    public void actionPerformed(ActionEvent e) {
        try {            if (e.getSource() == btnPrevious) {
            if (resultSet.previous()) {                    displayRecord();
            } else {
                resultSet.last();                    displayRecord();
            }            } else if (e.getSource() == btnNext) {
            if (resultSet.next()) {                    displayRecord();
            } else {                    resultSet.first();
                displayRecord();                }
        }        } catch (SQLException ex) {
            ex.printStackTrace();        }
    }
    public static void main(String[] args) {        new CustomerInfo();
    }}