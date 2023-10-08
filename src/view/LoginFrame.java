/*
 * TCSS 305 Assignment 1 - UW Bookstore
 */

package view;

import io.CredentialingLoader;
import io.InventoryLoader;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import model.Item;
import res.R;


/**
 * LoginFrame provides a user interface for login and registration.
 * 
 * @author Charles Bryan
 * @version 7 September 2019
 */
public class LoginFrame extends JFrame {

    /** The Serialization ID. */
    private static final long serialVersionUID = 1728929170437545812L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The text field used to enter the user name. */
    private final JTextField myUserNameField;

    /** The text field used to enter the password. */
    private final JPasswordField myPasswdField;

    /** The text field used to enter the password the 2nd time on registration. */
    private final JPasswordField myPasswdField2;

    /** The combo box used to select the campus on registration. */
    private final JComboBox<String> myCampusBox;

    /** The button used to trigger login sequence. */
    private final JButton myLoginButton;

    /** The button used to trigger login registration. */
    private final JButton myRegisterButton;

    /** The panel that holds the Login/registration text fields. */
    private final JPanel myFieldsPanel;

    /**
     * Initializes the bookstore GUI.
     * @param theCampusList the list of university campuses
     */
    public LoginFrame(final List<String> theCampusList) {
        super();
        myUserNameField = new JTextField(R.Dimensions.LF_TEXTFIELD_WIDTH);
        myPasswdField = new JPasswordField(R.Dimensions.LF_TEXTFIELD_WIDTH);
        myPasswdField2 = new JPasswordField(R.Dimensions.LF_TEXTFIELD_WIDTH);
        myCampusBox = new JComboBox<>(theCampusList.toArray(new String[0]));

        myFieldsPanel = new JPanel(new GridLayout(R.Dimensions.LF_LOGIN_ROWS,
                                                  R.Dimensions.LF_LOGIN_COLS));

        myLoginButton = new JButton(R.Strings.LF_BUTTON_LOGIN);
        myRegisterButton = new JButton(R.Strings.LF_BUTTON_REGISTER);

        setupGUI();
    }

    /**
     * Setup the various parts of the GUI.
     */
    private void setupGUI() {
        layoutComponents();
        assignActions();
        finalizeFrame();
    }
    
    /**
     * Layout the Swing components.
     */
    private void layoutComponents() {
        final JPanel pane = new JPanel(new BorderLayout());

        final JPanel header = new JPanel();
        header.setBackground(R.Colors.HEADER_FOOTER_BG);
        final JLabel headerLabel = new JLabel(R.Strings.LF_LABEL_HEADER);
        headerLabel.setForeground(R.Colors.HEADER_FOOTER_TEXT);
        header.add(headerLabel);
        pane.add(header, BorderLayout.NORTH);

        myFieldsPanel.setBorder(BorderFactory.
                        createEmptyBorder(R.Dimensions.V_PADDING, R.Dimensions.H_PADDING,
                                           R.Dimensions.V_PADDING, R.Dimensions.H_PADDING));
        myFieldsPanel.setBackground(R.Colors.CONTENT_BG);

        final JLabel uNameLabel = new JLabel(R.Strings.LF_LABEL_USERNAME);
        uNameLabel.setForeground(R.Colors.CONTENT_TEXT);
        myFieldsPanel.add(uNameLabel);
        myFieldsPanel.add(myUserNameField);

        final JLabel pwdLabel = new JLabel(R.Strings.LF_LABEL_PASSWORD);
        pwdLabel.setForeground(R.Colors.CONTENT_TEXT);
        myFieldsPanel.add(pwdLabel);
        myFieldsPanel.add(myPasswdField);

        pane.add(myFieldsPanel, BorderLayout.CENTER);

        final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(R.Colors.HEADER_FOOTER_BG);
        bottom.add(myRegisterButton);
        bottom.add(myLoginButton);

        pane.add(bottom, BorderLayout.SOUTH);

        setContentPane(pane);        
    }

    /**
     * Add Listeners to any GUI components that require them.
     */
    private void assignActions() {
        myLoginButton.addActionListener(this::loginButtonAction);
        myRegisterButton.addActionListener(this::registerButtonAction);
    }
    
    /**
     * Finalize this JFrame before making visible. 
     */
    private void finalizeFrame() {
        // make the GUI so that it cannot be resized by the user dragging a corner
        setResizable(false);
        
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);        
    }

    /**
     * Validate the Login Fields for empty. If the fields are empty, an error
     * message is returned, otherwise the empty String is returned.
     * 
     * @return an error message if the login fields are empty, otherwise the
     *         empty String
     */
    private String validateLoginFieldsEmpty() {
        String result = validateEmpty(myUserNameField);
        final String pwd = validateEmpty(myPasswdField);
        if (result.isEmpty()) {
            result = pwd;
        }
        return result;
    }

    /**
     * Validate TextField for empty. If the field is empty, an error message is
     * returned, otherwise the empty String is returned.
     * 
     * @param theField The Field to validate.
     * @return an error message if the field is empty, otherwise the empty
     *         String.
     */
    private String validateEmpty(final JTextField theField) {
        String result = "";
        if (theField.getText().isEmpty()) {
            theField.setBackground(R.Colors.ERROR_TEXT_FIELD_BG);
            result = R.Strings.ERROR_MSG_EMPTY_FIELDS;
        } else {
            theField.setBackground(R.Colors.NO_ERROR_TEXT_FIELD_BG);
        }
        return result;
    }
    
    /* Event Handlers follow */

    /**
     * Event handler for the Login Button during login. This method is implicitly called when
     * the user presses the "Login" button.  It is not explicitly called in  this class: you 
     * will not find a method invocation for it.
     * 
     * @param theEvent the ActionEvent that triggered this method.
     */
    private void loginButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
        }
        final String errorMsg = validateLoginFieldsEmpty();
        if (errorMsg.isEmpty()) {
            LoginFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            myRegisterButton.setEnabled(false);
            myLoginButton.setEnabled(false);
            new AttemptLoginWorker().execute();
        } else {
            JOptionPane.showMessageDialog(this, 
                                          errorMsg, 
                                          R.Strings.ERROR_MSG_TITLE,
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Event handler for the Login Button during registration. This method is implicitly called
     * when the user presses the "Register and Login" button. It is not explicitly called in 
     * this class: you will not find a method invocation for it.
     * 
     * @param theEvent the ActionEvent that triggered this method.
     */
    private void loginRegisterButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
        }
        final String errorMsg = validateLoginFieldsEmpty();
        if (Arrays.equals(myPasswdField.getPassword(), 
                                                 myPasswdField2.getPassword())) {
            if (errorMsg.isEmpty()) {
                LoginFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                myRegisterButton.setEnabled(false);
                myLoginButton.setEnabled(false);
                new AttemptRegistrationWorker().execute();
            } else {
                JOptionPane.showMessageDialog(this, 
                                              errorMsg, 
                                              R.Strings.ERROR_MSG_TITLE,
                                              JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                                          R.Strings.ERROR_MSG_PASSWORDS_NO_MATCH,
                                          R.Strings.ERROR_MSG_TITLE,
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Event handler for the Register Button. This method is implicitly called when
     * the user presses the "Register" button. It is not explicitly called in this class: you 
     * will not find a method invocation for it. 
     * 
     * @param theEvent the ActionEvent that triggered this method.
     */
    private void registerButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException(R.Strings.ERROR_MSG_ILLEGAL_INVOKE);
        }

        myRegisterButton.setVisible(false);
        myFieldsPanel.setLayout(new GridLayout(R.Dimensions.LF_REGISTRATION_ROWS,
                                               R.Dimensions.LF_REGISTRATION_COLS));
        final JLabel pwdLabel = new JLabel(R.Strings.LF_LABEL_PASSWORD_2);
        pwdLabel.setForeground(R.Colors.CONTENT_TEXT);
        myFieldsPanel.add(pwdLabel);
        myFieldsPanel.add(myPasswdField2);

        final JLabel campusLabel = new JLabel(R.Strings.LF_LABEL_CAMPUS);
        campusLabel.setForeground(R.Colors.CONTENT_TEXT);
        myFieldsPanel.add(campusLabel);
        myFieldsPanel.add(myCampusBox);

        myLoginButton.setText(R.Strings.LF_BUTTON_LOGIN_REGISTRATION);
        myLoginButton.removeActionListener(myLoginButton.getActionListeners()[0]);
        myLoginButton.addActionListener(this::loginRegisterButtonAction);

        pack();
    }
    
    /* Inner Classes follow */
    
    /**
     * A worker thread to attempt register user.
     * 
     * NOTE: THERE ARE NO ERRORS/BUGS IN THIS INNER CLASS
     * 
     * Operations that may take a long time to complete (like file I/O or network connections) 
     * should be performed in a thread separate from the user interface thread. This will keep 
     * your graphical user interface responsive to user interactions. 
     * 
     * This inner class starts a background thread to perform the file I/O operations found in
     * io.Credentialing.java
     * 
     * @author Charles Bryan
     *
     */
    private class AttemptRegistrationWorker extends SwingWorker<String, Integer> {

        /** The campus of the user registering in. */
        private String myCampus;
        
        @Override
        public String doInBackground() {
            myCampus = myCampusBox.getItemAt(myCampusBox.getSelectedIndex());
            return CredentialingLoader.register(myUserNameField.getText(), 
                                             myPasswdField.getPassword(),
                                             myCampus);
        }

        @Override
        public void done() {
            try {
                final String message = get();
                if (message.isEmpty()) {
                    new LoadInventoryWorker(myCampus).execute();
                } else {
                    LoginFrame.this.setCursor(Cursor.getDefaultCursor());
                    myRegisterButton.setEnabled(true);
                    myLoginButton.setEnabled(true);
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                                                  message, 
                                                  R.Strings.ERROR_MSG_TITLE,
                                                  JOptionPane.ERROR_MESSAGE);
                } 
            } catch (final InterruptedException ex1) {
                ex1.printStackTrace();
            } catch (final ExecutionException ex2) {
                ex2.printStackTrace();
            }
        }
    } 
    
    /**
     * A worker thread to attempt login.
     * 
     * NOTE: THERE ARE NO ERRORS/BUGS IN THIS INNER CLASS
     * 
     * Operations that may take a long time to complete (like file I/O or network connections) 
     * should be performed in a thread separate from the user interface thread. This will keep 
     * your graphical user interface responsive to user interactions. 
     * 
     * This inner class starts a background thread to perform the file I/O operations found in
     * io.Credentialing.java
     * 
     * @author Charles Bryan
     *
     */
    private class AttemptLoginWorker extends SwingWorker<String, Integer> {

        @Override
        public String doInBackground() {
            return CredentialingLoader.login(myUserNameField.getText(), 
                                             myPasswdField.getPassword());
        }

        @Override
        public void done() {
            try {
                final String campus = get();
                if (campus.isEmpty()) {
                    LoginFrame.this.setCursor(Cursor.getDefaultCursor());
                    myRegisterButton.setEnabled(true);
                    myLoginButton.setEnabled(true);
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                                                  R.Strings.ERROR_MSG_CREDENTIALS, 
                                                  R.Strings.ERROR_MSG_TITLE,
                                                  JOptionPane.ERROR_MESSAGE);
                } else {
                    new LoadInventoryWorker(campus).execute();
                } 
            } catch (final InterruptedException ex1) {
                ex1.printStackTrace();
            } catch (final ExecutionException ex2) {
                ex2.printStackTrace();
            }
        }
    }  

    /**
     * A worker thread to load the inventory files. 
     * 
     * NOTE: THERE ARE NO ERRORS/BUGS IN THIS INNER CLASS
     * 
     * Operations that may take a long time to complete (like file I/O or network connections) 
     * should be performed in a thread separate from the user interface thread. This will keep 
     * your graphical user interface responsive to user interactions. 
     * 
     * This inner class starts a background thread to perform the file I/O operations found in
     * io.InventoryLoader.java
     * 
     * @author Charles Bryan
     */
    private class LoadInventoryWorker extends SwingWorker<Map<String, List<Item>>, Integer> {

        /** The campus of the user logging in. */
        private final String myCampus;
        
        /**
         * Creates a worked to load the inventory file. 
         * @param theCampus the campus of the user logging in
         */
        LoadInventoryWorker(final String theCampus) {
            super();
            myCampus = theCampus;
        }
        
        @Override
        public Map<String, List<Item>> doInBackground() {
            final Map<String, List<Item>> inventories = new HashMap<>();
            final List<String> campusNames =
                InventoryLoader.readConfigurationFromFile(R.Strings.IO_FILE_LOCATION
                                                                 + R.Strings.IO_CONFIG_FILE);
            for (final String campusName : campusNames) {
                inventories.put(campusName, InventoryLoader.
                                readItemsFromFile(R.Strings.IO_FILE_LOCATION
                                                   + campusName.toLowerCase(Locale.ENGLISH)
                                                   + R.Strings.IO_FILE_EXTENSION));
            }
            return inventories;
        }

        @Override
        public void done() {
            LoginFrame.this.setCursor(Cursor.getDefaultCursor());
            myRegisterButton.setEnabled(true);
            myLoginButton.setEnabled(true);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new BookstoreFrame(get(), myCampus);
                        LoginFrame.this.dispose();
                    } catch (final InterruptedException ex1) {
                        ex1.printStackTrace();
                    } catch (final ExecutionException ex2) {

                        ex2.printStackTrace();
                    }
                }
            });
        }
    }
}
