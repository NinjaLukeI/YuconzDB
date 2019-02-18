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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.KeyEvent; 
import java.io.*;

public class AppController {

	private JFrame frmYuconzDatabase;
	private JTextField txtUsr;
	private JTextField txtPwd;

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
		frmYuconzDatabase = new JFrame();
		frmYuconzDatabase.setTitle("Yuconz Database");
		frmYuconzDatabase.setBounds(100, 100, 450, 300);
		frmYuconzDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYuconzDatabase.getContentPane().setLayout(null);
		
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
		txtPwd.setBounds(154, 134, 154, 20);
		frmYuconzDatabase.getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				File users = new File("users.txt");
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
		frmYuconzDatabase.getContentPane().add(btnLogin);
	}
	

}

	
