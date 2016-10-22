package gui.biblioteka.ba.fet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.biblioteka.ba.fet.GetDbTables;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MStudent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	/*
	 * Ova dva vektora nam trebaju za snimanje svih ntorki iz odgovarajucih tabela iz BP
	 */
	private ArrayList<MNastavnik> listaNastavnik = GetDbTables.getTableNastavnici();
	private ArrayList<MStudent> listaStudent = GetDbTables.getTableStudenti();
	
	public static int sifStudentActive = -1;
	public static int sifNastavnikActive = -1;
	/**
	 * Launch the application.
	 */
	public static void startLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 147, 208);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(29, 12, 76, 15);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(12, 39, 120, 19);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(30, 70, 75, 15);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField(50);
		passwordField.setBounds(12, 97, 120, 19);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Kada se klikne na Login dugme, treba se provjeriti poklapanje odgovarajuceg username-a sa passwordom.
				 */
				String username = txtUsername.getText();
				String password = new String(passwordField.getPassword());
				/*
				 * Kako smo pokupili vrijednost nastavnika, trbamo provjeriti da li unesene vrijednosti odgovaraju nekoj iz BP tabela nastavnik i student.
				 * Prvo provjeravano za nastavnike, i prolazimo kroz sve n-torke nastavnika
				 */
				for (MNastavnik nastavnik : listaNastavnik) {
					/*
					 * Provjera da li uneseni username i password odgovara nekom iz BP. Za nastavnike je username ime.prezime, 
					 * pa prvo pravimo takav username.
					 */
					String usernamePom = nastavnik.getImeNastavnik() + "." + nastavnik.getPrezNastavnik();
					usernamePom = usernamePom.toLowerCase(); //username je sve mala slova
					/*
					 * provjera da li je nastavnik unio ispravan username i password
					 */
					if(usernamePom.equals(username) && nastavnik.getPassword().equals(password)){
						//u varijblu sifNastavnikActive upisujemo sifru nastavnika koji se loguje
						sifNastavnikActive = nastavnik.getSifNastavnik();
					
						/*
						 * pokrece gui za nastavnika
						 */
						NastavnikGUI.startNastavnik();
						frame.dispose();
					}
				}
				/*
				 * provjera za studente
				 */
				for (MStudent student : listaStudent) {
					if(student.getBrIndexa().equals(username) && student.getPassword().equals(password)){
						//u sifStudentActive upisujemo sifru studenta koji se loguje
						sifStudentActive = student.getSifStudent();
						
						/*
						 * pokreni GUI za studente
						 */
						StudentGUI.startStudent();
						frame.dispose();
					}
				}
			}
		});
		btnLogin.setBounds(12, 127, 120, 25);
		frame.getContentPane().add(btnLogin);
	}
}
