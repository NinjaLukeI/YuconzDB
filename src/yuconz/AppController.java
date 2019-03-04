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

public class AppController {

	private JFrame frmYuconzDatabase;
	private JTextField txtUsr;
	private JTextField txtPwd;
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		File users = new File("users.txt");
		
		frmYuconzDatabase = new JFrame();
		frmYuconzDatabase.setVisible(true);
		frmYuconzDatabase.setTitle("Yuconz Database");
		frmYuconzDatabase.setBounds(100, 100, 450, 300);
		frmYuconzDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYuconzDatabase.getContentPane().setLayout(null);
		
		JInternalFrame YuconzSysFrame = new JInternalFrame("Yuconz Database");
		YuconzSysFrame.setVisible(false);
		YuconzSysFrame.setBounds(-14, -32, 461, 307);
		frmYuconzDatabase.getContentPane().add(YuconzSysFrame);
		YuconzSysFrame.getContentPane().setLayout(null);
		
		JButton btnModify = new JButton("Modify Personal File");
		
		btnModify.setBounds(21, 67, 169, 64);
		YuconzSysFrame.getContentPane().add(btnModify);
		
		JButton btnCreatePersonalFile = new JButton("Create Personal File");
		btnCreatePersonalFile.setBounds(21, 156, 169, 64);
		YuconzSysFrame.getContentPane().add(btnCreatePersonalFile);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(200, 67, 50, 14);
		YuconzSysFrame.getContentPane().add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(200, 103, 81, 14);
		YuconzSysFrame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(200, 141, 61, 14);
		YuconzSysFrame.getContentPane().add(lblPostcode);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(200, 181, 50, 14);
		YuconzSysFrame.getContentPane().add(lblAddress);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number:");
		lblTelephoneNumber.setBounds(200, 218, 106, 14);
		YuconzSysFrame.getContentPane().add(lblTelephoneNumber);
		
		JLabel lblWelcomeUser = new JLabel("Welcome, User");
		lblWelcomeUser.setBounds(10, 11, 425, 14);
		YuconzSysFrame.getContentPane().add(lblWelcomeUser);
		
		
		
		
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
		
		txtUsr = new JTextField();
		txtUsr.setBounds(154, 94, 154, 20);
		frmYuconzDatabase.getContentPane().add(txtUsr);
		txtUsr.setColumns(6);
		
		txtPwd = new JTextField();
		txtPwd.setVerifyInputWhenFocusTarget(false);
		txtPwd.setBounds(154, 134, 154, 20);
		frmYuconzDatabase.getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(246, 67, 189, 20);
		YuconzSysFrame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtDob = new JTextField();
		txtDob.setEditable(false);
		txtDob.setBounds(277, 100, 158, 20);
		YuconzSysFrame.getContentPane().add(txtDob);
		txtDob.setColumns(10);
		
		txtPostcode = new JTextField();
		txtPostcode.setEditable(false);
		txtPostcode.setBounds(256, 138, 179, 20);
		YuconzSysFrame.getContentPane().add(txtPostcode);
		txtPostcode.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setBounds(246, 178, 189, 20);
		YuconzSysFrame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtTelNum = new JTextField();
		txtTelNum.setEditable(false);
		txtTelNum.setBounds(298, 215, 137, 20);
		YuconzSysFrame.getContentPane().add(txtTelNum);
		txtTelNum.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				try {
					Scanner in = new Scanner(users);
					
					while(in.hasNextLine()) {
						String s = in.nextLine();
						String[] sArray = s.split(",");
						
						String usrNameInp = txtUsr.getText();
						String usrPwdInp = txtPwd.getText();
						
						
						System.out.println(sArray[0]);
						System.out.println(sArray[1]); //test to see if values were actually obtained
						
						if(usrNameInp.trim().equals(sArray[0].trim()) && usrPwdInp.trim().equals(sArray[1])) {
							JOptionPane.showMessageDialog(null, "Login has been successful.", 
									"Success", JOptionPane.INFORMATION_MESSAGE);
							
							YuconzSysFrame.setVisible(true);
							btnLogin.setVisible(false);
							lblWelcomeUser.setText("Welcome " + sArray[0]);
							
							txtName.setText(sArray[3]);
							txtDob.setText(sArray[4]);
							txtPostcode.setText(sArray[5]);
							txtAddress.setText(sArray[6]);
							txtTelNum.setText(sArray[7]);
							
							if(sArray[8].trim().equals("canModify")) {
								txtName.setEditable(true);
								txtDob.setEditable(true);
								txtPostcode.setEditable(true);
								txtAddress.setEditable(true);
								txtTelNum.setEditable(true);
							}
							
							btnModify.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									Path path = Paths.get("users.txt");
									Charset charset = StandardCharsets.UTF_8;
									
									try {
										String content = new String(Files.readAllBytes(path), charset);
										content = content.replaceAll(sArray[3], txtName.getText());
										content = content.replaceAll(sArray[4], txtDob.getText());
										content = content.replaceAll(sArray[5], txtPostcode.getText());
										content = content.replaceAll(sArray[6], txtAddress.getText());
										content = content.replaceAll(sArray[7], txtTelNum.getText());
										Files.write(path, content.getBytes(charset));
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									
									
								}
							});
							
								
							break;
							
						}
						else {
							
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
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				YuconzSysFrame.setVisible(false);
				btnLogin.setVisible(true);
				txtUsr.setText("");
				txtPwd.setText("");
				txtName.setEditable(false);
				txtDob.setEditable(false);
				txtPostcode.setEditable(false);
				txtAddress.setEditable(false);
				txtTelNum.setEditable(false);
				
			}
		});
		btnLogout.setBounds(346, 7, 89, 23);
		YuconzSysFrame.getContentPane().add(btnLogout);
		

		
		frmYuconzDatabase.getContentPane().add(btnLogin);
		
		
	}
}

	
