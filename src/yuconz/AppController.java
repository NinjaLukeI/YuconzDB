package yuconz;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.KeyEvent; 
import java.io.*;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Label;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

//note for other programmers:
//because this is prototype code; this is probably unlike how the actual code should be
//purely based on the fact this is different to the UML. 
//in regards to the users themselves, code could potentially be written to make users using params obtained
//from the users.txt file. however, an implementation using an sql database may be more manageable and smarter
//than trying to make a class out of every single user in the database. in reality this makes more sense and 
//is most likely the ideal route. as long as they have the same attributes noted in the UML and their permission
//types are noted in the sql, an implementation using this is the wisest thing

public class AppController {

	private JFrame frmYuconzDatabase;
	private JTextField txtUsr;
	private JPasswordField txtPwd;
	private JTextField txtName;
	private JTextField txtDob;
	private JTextField txtPostcode;
	private JTextField txtAddress;
	private JTextField txtTelNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppController window = new AppController();
					window.frmYuconzDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. This is automatically made with windowbuilder so this
	 *doesn't really need to be modified or touched.
	 */
	private void initialize() {
		
		File users = new File("users.txt");
		//this is opening the users.txt file that stores information about each user of the system
		
		AuthenticationServer AuthServ = new AuthenticationServer();
		
		frmYuconzDatabase = new JFrame();
		frmYuconzDatabase.setVisible(true);
		frmYuconzDatabase.setTitle("Yuconz Database");
		frmYuconzDatabase.setBounds(100, 100, 450, 300);
		frmYuconzDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYuconzDatabase.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setVisible(false);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 434, 261);
		frmYuconzDatabase.getContentPane().add(tabbedPane);
		
		JPanel personalDetails = new JPanel();
		tabbedPane.addTab("Personal Details", null, personalDetails, null);
		personalDetails.setLayout(null);
		
		JButton btnModify = new JButton("Modify Personal File");
		btnModify.setBounds(52, 186, 172, 23);
		personalDetails.add(btnModify);
		
		JButton btnCreatePersonalFile = new JButton("Create Personal File");
		btnCreatePersonalFile.setBounds(234, 186, 172, 23);
		personalDetails.add(btnCreatePersonalFile);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(28, 44, 84, 14);
		personalDetails.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(25, 158, 111, 14);
		personalDetails.add(lblDateOfBirth);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(28, 126, 108, 14);
		personalDetails.add(lblPostcode);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(28, 72, 84, 14);
		personalDetails.add(lblAddress);
		
		JLabel lblWelcomeUser = new JLabel("Welcome, User");
		lblWelcomeUser.setBounds(18, 9, 175, 14);
		personalDetails.add(lblWelcomeUser);
		
		txtName = new JTextField();
		txtName.setBounds(132, 41, 171, 20);
		personalDetails.add(txtName);
		txtName.setEditable(false);
		txtName.setColumns(10);
		
		txtDob = new JTextField();
		txtDob.setBounds(132, 155, 171, 20);
		personalDetails.add(txtDob);
		txtDob.setEditable(false);
		txtDob.setColumns(10);
		
		txtPostcode = new JTextField();
		txtPostcode.setBounds(132, 123, 88, 20);
		personalDetails.add(txtPostcode);
		txtPostcode.setEditable(false);
		txtPostcode.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(132, 69, 171, 20);
		personalDetails.add(txtAddress);
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		
		txtTelNum = new JTextField();
		txtTelNum.setBounds(132, 97, 171, 20);
		personalDetails.add(txtTelNum);
		txtTelNum.setEditable(false);
		txtTelNum.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(333, 5, 86, 23);
		personalDetails.add(btnLogout);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Num:");
		lblTelephoneNumber.setBounds(28, 100, 108, 14);
		personalDetails.add(lblTelephoneNumber);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Annual Review", null, panel, null);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setVisible(false);
				btnLogin.setVisible(true);
				txtUsr.setText("");
				txtPwd.setText("");
				txtName.setText("");
				txtDob.setText("");
				txtPostcode.setText("");
				txtAddress.setText("");
				txtTelNum.setText("");
				txtName.setEditable(false);
				txtDob.setEditable(false);
				txtPostcode.setEditable(false);
				txtAddress.setEditable(false);
				txtTelNum.setEditable(false);
				JOptionPane.showMessageDialog(null, "You have successfully been logged out. ", 
						"Success", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//so for example, after this point, the code would be moved to another class.
				//in it's place, there would be a function call to said method, in this class.
				
				Scanner in;
				try {
					in = new Scanner(users);
					
					while(in.hasNextLine()) {
						String s = in.nextLine(); //for looping through the users file
						String[] sArray = s.split(","); //this code takes each value on a line and removes commas seperating them
						
						
						Path path = Paths.get("users.txt");
						Charset charset = StandardCharsets.UTF_8;
						
						//tries to get everything from lines in the yuconz system, and replace them with details in the users.txt file
						try {
							String content = new String(Files.readAllBytes(path), charset);
							content = content.replaceAll(sArray[3], txtName.getText());
							content = content.replaceAll(sArray[4], txtDob.getText());
							content = content.replaceAll(sArray[5], txtPostcode.getText());
							content = content.replaceAll(sArray[6], txtAddress.getText());
							content = content.replaceAll(sArray[7], txtTelNum.getText());
							Files.write(path, content.getBytes(charset));
							
							JOptionPane.showMessageDialog(null, "Details have been successfully modified.", 
									"Success", JOptionPane.INFORMATION_MESSAGE);
							
							//closes the scanner
							in.close();
							
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
					}
					
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
		}});
		
		txtUsr = new JTextField();
		txtUsr.setBounds(154, 94, 154, 20);
		frmYuconzDatabase.getContentPane().add(txtUsr);
		txtUsr.setColumns(6);
		
		txtPwd = new JPasswordField();
		txtPwd.setVerifyInputWhenFocusTarget(false);
		txtPwd.setBounds(154, 134, 154, 20);
		frmYuconzDatabase.getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		
		
		/**this code is responsible for writing to the users.txt file, modifying personal details
		 * this is in relation to user files
		 * potentially this sort of code would be in database.
		 * when moving this code to it's own individual class, after the 'actionPerformed' method
		 * is called, the code can just be called from the database class or w/e class this code would pertain to.
		 */
		
		/**this button is responsible for 'logging' the user into the system.
		 * but like all the other sort of 'methods' in this appcontroller class, all of the code
		 * can just be moved to possibly the authenticator class, and a call just being made.
		 * the only thing that needs to be noted is because this is prototype code; 
		 * it does not have the implementations the UML document states, so those sorts of things would need to be added
		 * 
		 */
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				try {
					Scanner in = new Scanner(users);
					
					//loops through lines in the users file
					while(in.hasNextLine()) {
						String s = in.nextLine();
						String[] sArray = s.split(","); //splits the values in the users.txt file
						
						String usrNameInp = txtUsr.getText();
						String usrPwdInp = String.valueOf(txtPwd.getPassword());
						
						//sArray[0] is the username, sArray[1] is the password
						System.out.println(sArray[0]); 
						System.out.println(sArray[1]); //test to see if values were actually obtained
						
						if(usrNameInp.trim().equals(sArray[0].trim()) && usrPwdInp.trim().equals(sArray[1])) {
							JOptionPane.showMessageDialog(null, "Login has been successful.", 
									"Success", JOptionPane.INFORMATION_MESSAGE);
							
							tabbedPane.setVisible(true); //this is setting the YuconzSysFrame to being visible.
							//this frame is the one that shows the employee details and such.
							btnLogin.setVisible(false);//this removes the login button from being visible
							
							//if user has perms to modify, the modify button will be visible, otherwise it'll be invisible
							
							if(!sArray[8].trim().equals("canModify")) {
								btnModify.setVisible(false);
							}
							else {
								btnModify.setVisible(true);
							}
							
							
							lblWelcomeUser.setText("Welcome " + sArray[0]);
							
							//sets the text fields to have the same details as those in the users file
							txtName.setText(sArray[3]);
							txtDob.setText(sArray[4]);
							txtPostcode.setText(sArray[5]);
							txtAddress.setText(sArray[6]);
							txtTelNum.setText(sArray[7]);
							
							//if they have this flag in the users file, they'll be able to modify
							if(sArray[8].trim().equals("canModify")) {
								txtName.setEditable(true);
								txtDob.setEditable(true);
								txtPostcode.setEditable(true);
								txtAddress.setEditable(true);
								txtTelNum.setEditable(true);
							}
							
							
							
								
							break;
							
						} 
						else {
							//if there's no line left in the users file, bring up this error message
							if(in.hasNextLine() == false) {
								JOptionPane.showMessageDialog(null, "Invalid username or password.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}
					
					in.close();
				
					
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "User DB not found.", "Error", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		
		
		btnLogin.setBounds(186, 199, 89, 23);
		
		
		
		
		JLabel lblYuconz = new JLabel("Welcome To Yuconz User Database");
		lblYuconz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYuconz.setHorizontalAlignment(SwingConstants.CENTER);
		lblYuconz.setBounds(95, 26, 258, 14);
		frmYuconzDatabase.getContentPane().add(lblYuconz);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(39, 97, 64, 14);
		frmYuconzDatabase.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(39, 137, 64, 14);
		frmYuconzDatabase.getContentPane().add(lblPassword);
		
		
		
		
		

		
		frmYuconzDatabase.getContentPane().add(btnLogin);
		
		
	}
}

	
