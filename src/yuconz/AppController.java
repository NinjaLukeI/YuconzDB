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
import java.time.LocalDateTime;
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
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

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
	private JTextField tBoxReviewer1;
	private JTextField tBoxSignRev1;
	private JTextField tBoxReviewer2;
	private JTextField tBoxSignRev2;
	private JTextField tBoxReviewee;
	private JTextField tBoxSignReviewee;
	private JTextField txtRevDocSrc;
	private String sArray[];

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
		
		AnnualReview AnnRev = new AnnualReview();
		Authoriser Auth = new Authoriser();
		
		frmYuconzDatabase = new JFrame();
		frmYuconzDatabase.setVisible(true);
		frmYuconzDatabase.setTitle("Yuconz Database");
		frmYuconzDatabase.setBounds(100, 100, 514, 428);
		frmYuconzDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYuconzDatabase.getContentPane().setLayout(null);
		
		
		
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setVisible(false);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 498, 389);
		frmYuconzDatabase.getContentPane().add(tabbedPane);
		
		JButton btnModify = new JButton("Modify Personal File");
		btnModify.setBounds(131, 307, 172, 23);
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//so for example, after this point, the code would be moved to another class.
				//in it's place, there would be a function call to said method, in this class.
				
				
				
				Scanner in;
				try {
					in = new Scanner(users);
					
					while(in.hasNextLine()) {
						
						
						if(!sArray[2].trim().equals("HR")) {
							try {
								Auth.logAttempt(sArray[0].trim(), "modify");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("naughty");
							break;
						}
						else {
							System.out.println(sArray[0]);
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
						
						
						
						
					}
					
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
		}});
		
		JLabel lblWelcomeUser = new JLabel("Welcome, User");
		lblWelcomeUser.setBounds(18, 9, 175, 14);
		
		JPanel personalDetails = new JPanel();
		tabbedPane.addTab("Personal Details", null, personalDetails, null);
		personalDetails.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(25, 59, 84, 14);
		personalDetails.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(25, 229, 111, 14);
		personalDetails.add(lblDateOfBirth);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(28, 188, 108, 14);
		personalDetails.add(lblPostcode);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(28, 97, 84, 14);
		personalDetails.add(lblAddress);
		
		
		personalDetails.add(lblWelcomeUser);
		
		txtName = new JTextField();
		txtName.setBounds(132, 56, 171, 20);
		personalDetails.add(txtName);
		txtName.setColumns(10);
		
		txtDob = new JTextField();
		txtDob.setBounds(132, 226, 171, 20);
		personalDetails.add(txtDob);
		txtDob.setColumns(10);
		
		txtPostcode = new JTextField();
		txtPostcode.setBounds(132, 185, 91, 20);
		personalDetails.add(txtPostcode);
		txtPostcode.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(132, 94, 171, 20);
		personalDetails.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtTelNum = new JTextField();
		txtTelNum.setBounds(132, 134, 171, 20);
		personalDetails.add(txtTelNum);
		txtTelNum.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(397, 5, 86, 23);
		personalDetails.add(btnLogout);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Num:");
		lblTelephoneNumber.setBounds(28, 137, 108, 14);
		personalDetails.add(lblTelephoneNumber);
		
		
		personalDetails.add(btnModify);
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(146, 258, 243, 23);
		
		
		
		JPanel annualReview = new JPanel();
		tabbedPane.addTab("Write Review", null, annualReview, null);
		annualReview.setLayout(null);
		
		JEditorPane reviewBox = new JEditorPane();
		reviewBox.setBounds(10, 167, 409, 89);
		annualReview.add(reviewBox);
		
		tBoxReviewer1 = new JTextField();
		tBoxReviewer1.setBounds(33, 25, 86, 20);
		annualReview.add(tBoxReviewer1);
		tBoxReviewer1.setColumns(10);
		
		tBoxSignRev1 = new JTextField();
		tBoxSignRev1.setBounds(33, 114, 86, 20);
		annualReview.add(tBoxSignRev1);
		tBoxSignRev1.setColumns(10);
		
		tBoxReviewer2 = new JTextField();
		tBoxReviewer2.setBounds(156, 25, 86, 20);
		annualReview.add(tBoxReviewer2);
		tBoxReviewer2.setColumns(10);
		
		tBoxSignRev2 = new JTextField();
		tBoxSignRev2.setBounds(156, 114, 86, 20);
		annualReview.add(tBoxSignRev2);
		tBoxSignRev2.setColumns(10);
		
		tBoxReviewee = new JTextField();
		tBoxReviewee.setColumns(10);
		tBoxReviewee.setBounds(285, 25, 86, 20);
		annualReview.add(tBoxReviewee);
		
		JLabel lblstReviewer = new JLabel("1st Reviewer");
		lblstReviewer.setBounds(33, 11, 116, 14);
		annualReview.add(lblstReviewer);
		
		JLabel lblndReviewer = new JLabel("2nd Reviewer");
		lblndReviewer.setBounds(156, 11, 104, 14);
		annualReview.add(lblndReviewer);
		
		tBoxSignReviewee = new JTextField();
		tBoxSignReviewee.setColumns(10);
		tBoxSignReviewee.setBounds(285, 114, 86, 20);
		annualReview.add(tBoxSignReviewee);
		
		JLabel lblReviewee = new JLabel("Reviewee");
		lblReviewee.setBounds(285, 11, 113, 14);
		annualReview.add(lblReviewee);
		
		JLabel lblNewLabel = new JLabel("Sign Off Review");
		lblNewLabel.setBounds(156, 74, 126, 14);
		annualReview.add(lblNewLabel);
		
		JLabel lblstReviewer_1 = new JLabel("1st Reviewer");
		lblstReviewer_1.setBounds(33, 99, 120, 14);
		annualReview.add(lblstReviewer_1);
		
		JLabel lblndReviewer_1 = new JLabel("2nd Reviewer");
		lblndReviewer_1.setBounds(156, 99, 104, 14);
		annualReview.add(lblndReviewer_1);
		
		JLabel lblReviewee_1 = new JLabel("Reviewee");
		lblReviewee_1.setBounds(285, 99, 113, 14);
		annualReview.add(lblReviewee_1);
		
		JButton btnPostReview = new JButton("Post Review");
		btnPostReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(sArray[2].trim().equals("HR") | sArray[2].trim().equals("Reviewer")) {
					String content = "Review on: " + tBoxReviewee.getText() + "\r\n";
					content += "Written on: " + LocalDateTime.now() + "\r\n";
					content += reviewBox.getText();
					content += "\r\n" + "Signed by: " + tBoxReviewer1.getText() + " and " + tBoxReviewer2.getText() + ".";
					System.out.println(content);
					
					try {
						AnnRev.saveNewReview(tBoxReviewee.getText(), content);
						JOptionPane.showMessageDialog(null, "Review has successfully been made.", 
								"Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Review could not be posted.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					try {
						Auth.logAttempt(sArray[0], "post a review");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "An error has occurred.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		btnPostReview.setBounds(186, 298, 128, 23);
		annualReview.add(btnPostReview);
		
		JPanel readReview = new JPanel();
		tabbedPane.addTab("Read Review", null, readReview, null);
		readReview.setLayout(null);
		
		JLabel lblEnterTheName = new JLabel("Enter the name of the review document you'd wish to view:");
		lblEnterTheName.setBounds(96, 54, 354, 14);
		readReview.add(lblEnterTheName);
		
		txtRevDocSrc = new JTextField();
		txtRevDocSrc.setBounds(96, 80, 290, 20);
		readReview.add(txtRevDocSrc);
		txtRevDocSrc.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 144, 290, 151);
		readReview.add(scrollPane);
		
		JTextPane readRev = new JTextPane();
		readRev.setEditable(false);
		scrollPane.setViewportView(readRev);
		
		JButton btnSearchForRev = new JButton("Search");
		btnSearchForRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(sArray[2].trim().equals("HR") || sArray[2].trim().equals("Reviewer") || sArray[2].trim().equals("Director")) {
					String fileToSearch = txtRevDocSrc.getText();
					try {
						readRev.setText(AnnRev.readReview(fileToSearch));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						Auth.logAttempt(sArray[0], "search for a review");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
		btnSearchForRev.setBounds(192, 110, 89, 23);
		readReview.add(btnSearchForRev);
		
		
		JPanel login = new JPanel();
		login.setBounds(0, 0, 498, 389);
		frmYuconzDatabase.getContentPane().add(login);
		login.setLayout(null);
		
		txtUsr = new JTextField();
		txtUsr.setBounds(218, 139, 96, 20);
		login.add(txtUsr);
		txtUsr.setColumns(6);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(218, 199, 96, 20);
		login.add(txtPwd);
		txtPwd.setVerifyInputWhenFocusTarget(false);
		txtPwd.setColumns(10);
		
		JLabel lblYuconz = new JLabel("Welcome To Yuconz User Database");
		lblYuconz.setBounds(159, 6, 218, 17);
		login.add(lblYuconz);
		lblYuconz.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYuconz.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(237, 127, 86, 14);
		login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(237, 187, 96, 14);
		login.add(lblPassword);
		
		
		login.add(btnLogin);
		
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
				JOptionPane.showMessageDialog(null, "You have successfully been logged out. ", 
						"Success", JOptionPane.INFORMATION_MESSAGE);
				login.setVisible(true);
				sArray = null;
				
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				try {
					Scanner in = new Scanner(users);
					
					//loops through lines in the users file
					while(in.hasNextLine()) {
						String s = in.nextLine();
						sArray = s.split(","); //splits the values in the users.txt file
						
						String usrNameInp = txtUsr.getText();
						String usrPwdInp = String.valueOf(txtPwd.getPassword());
						
						//sArray[0] is the username, sArray[1] is the password
						System.out.println(sArray[0]); 
						System.out.println(sArray[1]); //test to see if values were actually obtained
						
						if(usrNameInp.trim().toUpperCase().equals(sArray[0].trim().toUpperCase()) && usrPwdInp.trim().equals(sArray[1])) {
							JOptionPane.showMessageDialog(null, "Login has been successful.", 
									"Success", JOptionPane.INFORMATION_MESSAGE);
							
							tabbedPane.setVisible(true); //this is setting the YuconzSysFrame to being visible.
							//this frame is the one that shows the employee details and such.
							btnLogin.setVisible(false);//this removes the login button from being visible
							login.setVisible(false);
							
							
							
							
							lblWelcomeUser.setText("Welcome " + sArray[0]);
							
							//sets the text fields to have the same details as those in the users file
							txtName.setText(sArray[3]);
							txtDob.setText(sArray[4]);
							txtPostcode.setText(sArray[5]);
							txtAddress.setText(sArray[6]);
							txtTelNum.setText(sArray[7]);
							
							
							
							
								
							break;
							
						} 
						else {
							//if there's no line left in the users file, bring up this error message
							if(in.hasNextLine() == false) {
								JOptionPane.showMessageDialog(null, "Invalid username or password.",
										"Error", JOptionPane.ERROR_MESSAGE);
								try {
									Auth.logAttempt(usrNameInp, "log in with an incorrect password.");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
						}
					}
					
					in.close();
				
					
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "User DB not found.", "Error", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		
		
		
	}
}

	
