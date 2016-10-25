package gui.biblioteka.ba.fet;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;

import db.biblioteka.ba.fet.DBAutor;
import db.biblioteka.ba.fet.DBIzdavac;
import db.biblioteka.ba.fet.DBKnjiga;
import db.biblioteka.ba.fet.DBKnjigaAutorRBr;
import db.biblioteka.ba.fet.DBKnjigaPredmetObaveznost;
import db.biblioteka.ba.fet.DBNastavnik;
import db.biblioteka.ba.fet.DBPredmet;
import db.biblioteka.ba.fet.DBPrimjerak;
import db.biblioteka.ba.fet.DBRezervacija;
import db.biblioteka.ba.fet.DBRezervacijaPrimjerakNastavnik;
import db.biblioteka.ba.fet.DBStudent;
import db.biblioteka.ba.fet.GetDbTables;
import modeli.biblioteka.ba.fet.MAutor;
import modeli.biblioteka.ba.fet.MIzdavac;
import modeli.biblioteka.ba.fet.MKnjiga;
import modeli.biblioteka.ba.fet.MKnjigaAutorRBr;
import modeli.biblioteka.ba.fet.MKnjigaPredmetObaveznost;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MPredmet;
import modeli.biblioteka.ba.fet.MPrimjerak;
import modeli.biblioteka.ba.fet.MRezervacija;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakNastavnik;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;
import modeli.biblioteka.ba.fet.MSemestar;
import modeli.biblioteka.ba.fet.MStudent;
import modeli.biblioteka.ba.fet.MVrstaKnjige;
import razno.biblioteka.fet.ba.DateLabelFormatter;
import tabele.biblioteka.ba.fet.TRezervacijaPrimjerakNastavnik;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class NastavnikGUI {

	private static JFrame frame;
	private JTextField txtPassword;
	private JTextField txtIme;
	private JTextField txtPrezime;

	private JTable tableZaduzenja;
	private JTable tableRezervacija;
	private static JTable tableNastavnici;
	private static JTable tableStudenti;

	private JTextField bibliotekarTxtNaslovKnjiga;
	private JTextField bibliotekarTxtOrigNaslovKnjiga;
	private JTextField bibliotekarTxtBrStranicaKnjiga;
	private JTextField bibliotekarTxtGodIzdanjaKnjiga;
	private JTextField bibliotekarTxtNegBodoviKnjiga;
	private JTextField bibliotekarTxtIzdavac;
	private JTextField bibliotekarTxtImeNastavnik;
	private JTextField bibliotekarTxtPrezimeNastavnik;
	private JTextField bibliotekarTxtPasswordNastavnik;
	private JTextField bibliotekarTxtImeStudent;
	private JTextField bibliotekarTxtPrezimeStudent;
	private JTextField bibliotekarTxtPasswordStudent;
	private JTextField bibliotekarTxtBrIndexa;
	private JTextField bibliotekarTxtNazpredmet;
	private JTextField bibliotekarTxtKratpredmet;
	private JTextField bibliotekarTxtImeAutora;
	private JTextField bibliotekarTxtPrezimeAutora;
	private JTextField bibliotekarTxtInvBroj;
	private JTable bibliotekarTableRezervStud;
	private JTable bibliotekarTableRezervNast;
	private JTable bibliotekarTableNeizdateKnjige;
	private JTable bibliotekarTableIzdateKnjigeNast;
	private JTable bibliotekarTableIzdateKnjigeStud;
	private static JTable tablePredmeti;
	private JTable tableAutori;
	private static JTable tableKnjige;
	private static JTextField txtFilterNastavnici;
	private static JTextField txtFilterTableKnjige;
	private static JTextField txtFilterTableAutori;
	private static JTextField txtFilterTableIzdateKjnjigeStudentima;
	private static JTextField txtFilterTableIzdateKjnjigeNastavnicima;
	private static JTextField txtFilterPredmet;
	private JTextField txtFilterrezervacijestudenti;
	private JTextField txtFilterrezervacijenastavnik;
	private JTextField txtFilterrezervacijestudent;
	private JTextField txtFilterrezervacijeNast;
	private JTable tableRezervacijeStudenata;
	private JTable tableRezervacijeNastavnika;
	private JTextField txtFiltervraceneknjige;
	private JTable tableVraceneKnjige;
	
	/**
	 * Launch the application.
	 */
	public static void startNastavnik() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NastavnikGUI window = new NastavnikGUI();
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
	public NastavnikGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 859, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnMojProfil = new JMenu("Moj profil");
		menuBar.add(mnMojProfil);

		JMenuItem mntmMojProfil = new JMenuItem("Moj profil");
		mntmMojProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mojProfil();
			}
		});
		mnMojProfil.add(mntmMojProfil);

		JMenuItem mntmMojaZaduenja = new JMenuItem("Moja zaduženja");
		mntmMojaZaduenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mojaZaduzenja();
			}
		});
		mnMojProfil.add(mntmMojaZaduenja);
		
		JMenuItem mntmMojeVraceneKnjige = new JMenuItem("Vracene knjige");
		mntmMojeVraceneKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mojeVraceneKnjige();
			}
		});
		mnMojProfil.add(mntmMojeVraceneKnjige);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI.startLogin();
				frame.dispose();
			}
		});
		mnMojProfil.add(mntmLogout);

		JMenu mnBiblioteka = new JMenu("Biblioteka");
		menuBar.add(mnBiblioteka);

		JMenuItem mntmKnjige = new JMenuItem("Knjige");
		mntmKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sveKnjige();
			}
		});
		mnBiblioteka.add(mntmKnjige);

		JMenuItem mntmAutori = new JMenuItem("Autori");
		mntmAutori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviAutori();
			}
		});
		mnBiblioteka.add(mntmAutori);

		JMenuItem mntmIzdavai = new JMenuItem("Izdavači");
		mntmIzdavai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviIzdavaci();
			}
		});
		mnBiblioteka.add(mntmIzdavai);

		JMenu mnFakultet = new JMenu("Fakultet");
		menuBar.add(mnFakultet);

		JMenuItem mntmNastavnici = new JMenuItem("Nastavnici");
		mntmNastavnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviNastavnici();
			}
		});
		mnFakultet.add(mntmNastavnici);

		JMenuItem mntmStudenti = new JMenuItem("Studenti");
		mntmStudenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviStudenti();
			}
		});
		mnFakultet.add(mntmStudenti);

		JMenuItem mntmPredmeti = new JMenuItem("Predmeti");
		mntmPredmeti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviPredmeti();
			}
		});
		mnFakultet.add(mntmPredmeti);

		/*
		 * za literaturu treba gledati gdje dodati
		 */
		JMenu mnLiteratura = new JMenu("Literatura");	
		mnFakultet.add(mnLiteratura);

		JMenuItem mntmDodajLiteraturu = new JMenuItem("Dodaj literaturu");
		mntmDodajLiteraturu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaLiteraturaPredmet();
			}
		});
		mnLiteratura.add(mntmDodajLiteraturu);

		JMenuItem mntmIzbriiLiteraturu = new JMenuItem("Izmjeni literaturu");
		mntmIzbriiLiteraturu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izmjeniLiteraturuPredmet();
			}
		});
		mnLiteratura.add(mntmIzbriiLiteraturu);

		
		
		/*
		 * meni za bibliotekara
		 * 
		 */
	
		MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(LoginGUI.sifNastavnikActive);
		
		JMenu mnUpravljanjeBibliotekom = new JMenu("Upravljanje bibliotekom");
		if(nastavnik.getBibliotekar() == 1) {
			menuBar.add(mnUpravljanjeBibliotekom);	
		}
		
		JMenu mnBibliotekaBibliotekar = new JMenu("Biblioteka");
		if(nastavnik.getBibliotekar() == 1) {
			mnUpravljanjeBibliotekom.add(mnBibliotekaBibliotekar);	
		}

		JMenuItem mntmRezervacijeStud = new JMenuItem("Rezervacije studenata");
		mntmRezervacijeStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacijeStudent();
			}
		});
		mnBibliotekaBibliotekar.add(mntmRezervacijeStud);
		
		JMenuItem mntmRezervacijeNast = new JMenuItem("Rezervacije nastavnika");
		mntmRezervacijeNast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacijeNastavnik();
			}
		});
		mnBibliotekaBibliotekar.add(mntmRezervacijeNast);
		
		JMenuItem mntmNeizdateKnjige = new JMenuItem("Neizdate knjige");
		mntmNeizdateKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neizdateKnjige();
			}
		});
		mnBibliotekaBibliotekar.add(mntmNeizdateKnjige);

		JMenuItem mntmIzdateKnjige = new JMenuItem("Izdate knjige studentima");
		mntmIzdateKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izdateKnjigeStudenti();
			}
		});
		mnBibliotekaBibliotekar.add(mntmIzdateKnjige);

		JMenuItem mntmIzdateKnjigeNastavnicima = new JMenuItem("Izdate knjige nastavnicima");
		mntmIzdateKnjigeNastavnicima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izdateKnjigeNastastavnici();
			}
		});
		mnBibliotekaBibliotekar.add(mntmIzdateKnjigeNastavnicima);

		JMenuItem mntmDodajNoviPrimjerak = new JMenuItem("Dodaj novi primjerak knjige");
		mntmDodajNoviPrimjerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviPrimjerak();
			}
		});

		mnBibliotekaBibliotekar.add(mntmDodajNoviPrimjerak);

		/*
		 * Meni Korisnici
		 */
		JMenu mnKorisnici = new JMenu("Korisnici");
		if(nastavnik.getBibliotekar() == 1) {
			mnUpravljanjeBibliotekom.add(mnKorisnici);
		}

		JMenu mnIzmjenaKorisnici = new JMenu("Izmjena postojecih");
		if(nastavnik.getBibliotekar() == 1) {
			mnKorisnici.add(mnIzmjenaKorisnici);
		}
		
		JMenuItem mntmIzmjeniNastavnik = new JMenuItem("Nastavnik");
		mntmIzmjeniNastavnik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviNastavniciBibliotekar();;
			}
		});
		mnIzmjenaKorisnici.add(mntmIzmjeniNastavnik);

		JMenuItem mntmIzmjeniStudent = new JMenuItem("Student");
		mntmIzmjeniStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviStudentiBibliotekar();;
			}
		});
		mnIzmjenaKorisnici.add(mntmIzmjeniStudent);
		
		JMenu mnNoviKorisnici = new JMenu("Novi koriskik");
		if(nastavnik.getBibliotekar() == 1) {
			mnKorisnici.add(mnNoviKorisnici);
		}
		
		JMenuItem mntmNastavnik = new JMenuItem("Nastavnik");
		mntmNastavnik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviNastavnik();
			}
		});
		mnNoviKorisnici.add(mntmNastavnik);

		JMenuItem mntmStudent = new JMenuItem("Student");
		mntmStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviStudent();
			}
		});
		mnNoviKorisnici.add(mntmStudent);

		/*
		 * Meni Knjiga
		 */
		JMenu mnKnjiga = new JMenu("Knjiga");
		if(nastavnik.getBibliotekar() == 1) {
			mnUpravljanjeBibliotekom.add(mnKnjiga);
		}

		JMenuItem mntmDodajKnjigu = new JMenuItem("Dodaj knjigu");
		mntmDodajKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaKnjiga();
			}
		});
		mnKnjiga.add(mntmDodajKnjigu);
		
		JMenuItem mntmDodajAutora = new JMenuItem("Dodaj autora");
		mntmDodajAutora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviAutor();
			}
		});
		mnKnjiga.add(mntmDodajAutora);
		
		JMenuItem mntmDodajIzdavaa = new JMenuItem("Dodaj izdavača");
		mntmDodajIzdavaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviIzdavac();
			}
		});
		mnKnjiga.add(mntmDodajIzdavaa);
	
		/*
		 * Meni Predmet
		 */
		JMenu mnPredmeti = new JMenu("Predmeti");
		if(nastavnik.getBibliotekar() == 1) {
			mnUpravljanjeBibliotekom.add(mnPredmeti);
		}

		JMenuItem mntmDodajPredmet = new JMenuItem("Dodaj predmet");
		mntmDodajPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviPredmet();
			}
		});
		mnPredmeti.add(mntmDodajPredmet);
		
		/*
		 * test
		 */
		mojeVraceneKnjige();
		
	}

	private void mojProfil(){
		JInternalFrame mojProfil = new JInternalFrame("Moj profil", true, true, true);
		mojProfil.setBounds(12, 12, 482, 181);
		mojProfil.setVisible(true);
		frame.getContentPane().add(mojProfil);
		mojProfil.getContentPane().setLayout(null);

		/*
		 *  dohvatimo n-torku koja odgovara trenutno aktivnom nastavniku
		 */
		MNastavnik nastavnik = new MNastavnik();
		for (MNastavnik pom : GetDbTables.getTableNastavnici()) {
			if(pom.getSifNastavnik() == LoginGUI.sifNastavnikActive){
				nastavnik = pom; //u studenta smo unjeli aktivnog studenta
				break;
			}
		}

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(150, 12, 30, 15);
		mojProfil.getContentPane().add(lblIme);

		txtIme = new JTextField();
		txtIme.setBounds(203, 12, 150, 19);
		txtIme.setText(nastavnik.getImeNastavnik());
		mojProfil.getContentPane().add(txtIme);
		txtIme.setColumns(10);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(118, 39, 62, 15);
		mojProfil.getContentPane().add(lblPrezime);

		txtPrezime = new JTextField();
		txtPrezime.setBounds(203, 39, 150, 19);
		txtPrezime.setText(nastavnik.getPrezNastavnik());
		mojProfil.getContentPane().add(txtPrezime);
		txtPrezime.setColumns(10);
		
		JLabel lblZvanje = new JLabel("Zvanje:");
		lblZvanje.setBounds(127, 66, 53, 15);
		mojProfil.getContentPane().add(lblZvanje);

		JLabel lblZvanjeNast = new JLabel(nastavnik.getZvanje());
		lblZvanjeNast.setBounds(203, 66, 200, 15);
		mojProfil.getContentPane().add(lblZvanjeNast);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(105, 93, 75, 15);
		mojProfil.getContentPane().add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setText(nastavnik.getPassword());
		txtPassword.setBounds(203, 91, 150, 19);
		mojProfil.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnPromjeniPassword = new JButton("Promjeni");
		btnPromjeniPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBNastavnik.updateNastavnik(txtIme.getText(), txtPrezime.getText(), txtPassword.getText(), LoginGUI.sifNastavnikActive);
				mojProfil.dispose();
			}
		});
		btnPromjeniPassword.setBounds(364, 88, 96, 25);
		mojProfil.getContentPane().add(btnPromjeniPassword);

		JLabel lblBrojNegativnihBodova = new JLabel("Broj negativnih bodova:");
		lblBrojNegativnihBodova.setBounds(12, 120, 168, 15);
		mojProfil.getContentPane().add(lblBrojNegativnihBodova);

		JLabel lblNegBodoviStud = new JLabel(String.valueOf(nastavnik.getNegBodovi()));
		lblNegBodoviStud.setBounds(203, 122, 200, 15);
		mojProfil.getContentPane().add(lblNegBodoviStud);

	}

	private void mojaZaduzenja() {
		JInternalFrame mojaZaduzenja = new JInternalFrame("Moja zaduženja", true, true, true);
		mojaZaduzenja.setBounds(12, 12, 682, 306);
		mojaZaduzenja.setVisible(true);
		frame.getContentPane().add(mojaZaduzenja);
		mojaZaduzenja.getContentPane().setLayout(null);

		JLabel lblZadueneKnjige = new JLabel("Zadužene knjige:");
		lblZadueneKnjige.setBounds(12, 7, 124, 15);
		mojaZaduzenja.getContentPane().add(lblZadueneKnjige);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 34, 648, 95);
		mojaZaduzenja.getContentPane().add(scrollPane);

		tableZaduzenja = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableZaduzenja);
		tableZaduzenja.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Knjiga", "Datum posudbe", "Datum vra\u0107anja", "Inv. Br. Knjige"
				}
				));
		tableZaduzenja.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableZaduzenja.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableZaduzenja.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableZaduzenja.getColumnModel().getColumn(3).setPreferredWidth(30);

		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableZaduzenja.getModel();
		model.setRowCount(0);

		TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik.clear();
		DBRezervacijaPrimjerakNastavnik.getRezPrimjNast();
		ArrayList<MRezervacijaPrimjerakNastavnik> rezPrimNastLista = TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik;
		/*
		 * Ispisujemo samo od aktivnog studenta pozajmljene knjige. U LoginGUI smo pohranili sifru 
		 * logovanog studenta u sifStudentActive. To radimo u ifu odma nakon for-a.
		 */
		for (MRezervacijaPrimjerakNastavnik posPrimNast : rezPrimNastLista) {
			String invBr = new String();
			String knjigaStr = new String();
			String datPosudbeStr = new String();
			String datVracanjaStr = new String();
			int upisUtabelu = -1;
			if (posPrimNast.getSifNastavnik() == LoginGUI.sifNastavnikActive) {
				/*
				 * dohvatamo primjerak knjige (od njega nam treba inventarni br i naslov knjige)
				 */	
				for (MPrimjerak primjerak : GetDbTables.getTablePrimjerak()) {
					if(posPrimNast.getSifPrimjerak() == primjerak.getSifPrimjerak()){
						invBr = primjerak.getInventartniBr(); //u invBr smo upisali invent broj, koji ce uci u tabelu
						/*
						 * Pretrazimo sada i knjigu za odgovarajuci primjerak.
						 */
						for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {
							if(knjiga.getSifKnjiga() == primjerak.getSifKnjiga()){
								knjigaStr = knjiga.getOrigNaslov();
								break;
							}
						}
						break;
					}
				}

				/*
				 * dohvatamo iz tabele posudba, da vidimo kada smo posudili i kada treba vratiti
				 */
				for (MRezervacija rezerv : GetDbTables.getTableRezervacije()) {
					if(rezerv.getSifRezervacija() == posPrimNast.getSifRezervacija() && rezerv.getNastStud() == 1 && rezerv.getOdobrena() == 0){ //0 je za neodobrene rezervacije sto zelimo i da prikazemo
						upisUtabelu = 1;
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						datPosudbeStr = sdf.format(rezerv.getDatRezervacija());
						datVracanjaStr = sdf.format(rezerv.getDatVracanja());
						break;
					}
				}
			}

			/*
			 * imamo sve sto trebamo, upisujemo red po red u tabelu
			 */
			if (upisUtabelu == 1) 
				model.addRow(new Object[]{knjigaStr, datPosudbeStr, datVracanjaStr, invBr});
		}

		/*
		 * tabela rezervacija
		 */
		JLabel lblRezervisaneKnjige = new JLabel("Rezervisane knjige:");
		lblRezervisaneKnjige.setBounds(12, 141, 139, 15);
		mojaZaduzenja.getContentPane().add(lblRezervisaneKnjige);

		JScrollPane scrollPaneRezerv = new JScrollPane();
		scrollPaneRezerv.setBounds(12, 168, 648, 95);
		mojaZaduzenja.getContentPane().add(scrollPaneRezerv);

		tableRezervacija = new JTable();
		scrollPaneRezerv.setViewportView(tableRezervacija);
		tableRezervacija.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Knjiga", "Datum posudbe", "Datum vra\u0107anja", "Inv. Br. Knjige"
				}
				));
		tableRezervacija.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableRezervacija.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableRezervacija.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableRezervacija.getColumnModel().getColumn(3).setPreferredWidth(30);
		
		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel modelRez = (DefaultTableModel) tableRezervacija.getModel();
		modelRez.setRowCount(0);

		/*
		 * Ispisujemo samo od aktivnog studenta pozajmljene knjige. U LoginGUI smo pohranili sifru 
		 * logovanog studenta u sifStudentActive. To radimo u ifu odma nakon for-a.
		 */
		for (MRezervacijaPrimjerakNastavnik rpn : GetDbTables.getTableRezervacijaPrimjerakNastavnik()) {
			String invBrRez = new String();
			String knjigaStrRez = new String();
			String datPosudbeStrRez = new String();
			String datVracaanjaStrRez = new String();
			int upisUtabelu = -1;
			if (rpn.getSifNastavnik() == LoginGUI.sifNastavnikActive) {
				/*
				 * dohvatamo primjerak knjige (od njega nam treba inventarni br i naslov knjige)
				 */
				for (MPrimjerak primjerak : GetDbTables.getTablePrimjerak()) {
					if(rpn.getSifPrimjerak() == primjerak.getSifPrimjerak()){
						invBrRez = primjerak.getInventartniBr(); //u invBr smo upisali invent broj, koji ce uci u tabelu

						//Pretrazimo sada i knjigu za odgovarajuci primjerak.
						for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {
							if(knjiga.getSifKnjiga() == primjerak.getSifKnjiga()){
								knjigaStrRez = knjiga.getOrigNaslov();
								break;
							}
						}
						break;
					}
				}

				/*
				 * dohvatamo iz tabele posudba, da vidimo kada smo posudili i kada treba vratiti
				 */
				for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
					if(rez.getSifRezervacija() == rpn.getSifRezervacija() && rez.getNastStud() == 1 && rez.getOdobrena() == 1){ // ==1 jer su to nastavnici
						upisUtabelu = 1;
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						datPosudbeStrRez = sdf.format(rez.getDatRezervacija());
						datVracaanjaStrRez = sdf.format(rez.getDatVracanja());
						break;
					}
				}
			}

			if(upisUtabelu == 1)
				modelRez.addRow(new Object[]{knjigaStrRez, datPosudbeStrRez, datVracaanjaStrRez, invBrRez});
		}	
	}

	private void mojeVraceneKnjige(){
		JInternalFrame mojeVraceneKnjige = new JInternalFrame("Moje vracene knjige", true, true, true);
		mojeVraceneKnjige.setBounds(12, 12, 682, 477);
		mojeVraceneKnjige.setVisible(true);
		frame.getContentPane().add(mojeVraceneKnjige);
		mojeVraceneKnjige.getContentPane().setLayout(null);
		
		JLabel lblPretraga = new JLabel("Pretraga:");
		lblPretraga.setBounds(12, 12, 68, 15);
		mojeVraceneKnjige.getContentPane().add(lblPretraga);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 648, 394);
		mojeVraceneKnjige.getContentPane().add(scrollPane);
		
		tableVraceneKnjige = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableVraceneKnjige);
		tableVraceneKnjige.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Knjiga", "Inv.br", "Rezervisana", "Podignuta", "Vracena"
			}
		));
		tableVraceneKnjige.getColumnModel().getColumn(0).setPreferredWidth(210);
		tableVraceneKnjige.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableVraceneKnjige.getColumnModel().getColumn(2).setPreferredWidth(95);
		tableVraceneKnjige.getColumnModel().getColumn(3).setPreferredWidth(95);
		tableVraceneKnjige.getColumnModel().getColumn(4).setPreferredWidth(93);
		
		DefaultTableModel modelMojeVraceneKnjige = (DefaultTableModel) tableVraceneKnjige.getModel();
		modelMojeVraceneKnjige.setRowCount(0);
		
		for (MRezervacija rezervacija : GetDbTables.getRezervacijaBySifNastavnik(LoginGUI.sifNastavnikActive)) {
			if(rezervacija.getOdobrena() == -1 && rezervacija.getNastStud() == 1) {
				MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rezervacija.getSifPrimjerak());
				MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());
				
				modelMojeVraceneKnjige.addRow(new Object[] {
						knjiga.getOrigNaslov(), primjerak.getInventartniBr(), rezervacija.getDatRezervacija(), rezervacija.getDatPodizanja(), rezervacija.getDatKadVracena()
				});
			}
		}
		
		
		txtFiltervraceneknjige = new JTextField();
		txtFiltervraceneknjige.setBounds(98, 12, 200, 19);
		mojeVraceneKnjige.getContentPane().add(txtFiltervraceneknjige);
		txtFiltervraceneknjige.setColumns(10);
		
		filterTableByColumn(tableVraceneKnjige, 0, txtFiltervraceneknjige);
		
	}
	
	private static void zaduziKnjigu(String origNaslov) {
		JInternalFrame zaduziKnjigu = new JInternalFrame("Zaduži knjigu", true, true, true);
		zaduziKnjigu.setBounds(12, 12, 455, 168);
		zaduziKnjigu.setVisible(true);
		frame.getContentPane().add(zaduziKnjigu);
		zaduziKnjigu.getContentPane().setLayout(null);

		/*
		 * Ako je korisnik iznajmio manje 3 i vise knjiga, ovaj prozor se nece otvoriti vec poruka 
		 * da je iznajmio vec 3 knjige. PRvo dohvatimo trenutnog korisnika i broj iznajmljenih knjiga
		 */
		MNastavnik nastavnik = new MNastavnik();
		
		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			if(nast.getSifNastavnik() == LoginGUI.sifNastavnikActive){
				nastavnik.setBrPosudjenihKnjiga(nast.getBrPosudjenihKnjiga());
				nastavnik.setBrRezervacija(nast.getBrRezervacija());
				break;
			}
		}

		if (nastavnik.getBrPosudjenihKnjiga() >= 3 || nastavnik.getBrRezervacija() >=3 ) {
			JLabel lblPoruka = new JLabel("Imate već iznajmljene 3 knjige ili izvršene 3 rezervacije");
			lblPoruka.setBounds(24, 49, 400, 15);
			zaduziKnjigu.getContentPane().add(lblPoruka);
		} else {

			JLabel lblOdaberiKnjigu = new JLabel("Knjiga:");
			lblOdaberiKnjigu.setBounds(36, 12, 109, 15);
			zaduziKnjigu.getContentPane().add(lblOdaberiKnjigu);

			JLabel lblodabranaKnjiga = new JLabel(origNaslov);
			lblodabranaKnjiga.setBounds(163, 12, 268, 15);
			zaduziKnjigu.getContentPane().add(lblodabranaKnjiga);
			
			JLabel lblDatumRezervacije = new JLabel("Datum rezervacije:");
			lblDatumRezervacije.setBounds(12, 39, 133, 15);
			zaduziKnjigu.getContentPane().add(lblDatumRezervacije);

			/*
			 * Dodavaje Date Pickera za datum rezervacije
			 */
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
			java.util.Date utilDate = cal.getTime();
			
			Date todayDate = new Date(utilDate.getTime());//Danasnji datum
			String danasDatum = dateFormat.format(todayDate);
			
			JLabel lblDanasnji = new JLabel(danasDatum);
			lblDanasnji.setBounds(163, 39, 268, 15);
			zaduziKnjigu.getContentPane().add(lblDanasnji);

			JLabel lblDatumVraanja = new JLabel("Datum vraćanja:");
			lblDatumVraanja.setBounds(30, 66, 115, 15);
			zaduziKnjigu.getContentPane().add(lblDatumVraanja);

			/*
			 * DatePicker za datum vracanja
			 */
			JXDatePicker pickerVracanje = new JXDatePicker();
			pickerVracanje.setFormats(dateFormat);
			pickerVracanje.getMonthView().setLowerBound(todayDate);//postavljanje donje 
			zaduziKnjigu.getContentPane().add(pickerVracanje);
			pickerVracanje.setBounds(163, 62, 268, 24);

			JButton btnRezervii = new JButton("Rezerviši");
			btnRezervii.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * Kada student rezervise knjigu, treba da se popuni tabela za rezervaciju, i tu rezervaciju bibliotekar treba kasnije da odobri.
					 * prilikom odobravanja rezervacije, bibliotekar ce u tabelu posudbaPrimjerakStudent onda da unese podatke za posudbu, sto ce
					 * biti odradjeno u BibliotekarGUI
					 */

					/*
					 * Dohvacamo odabranu knjigu za rezervaciju iz comboBoxa
					 * u string knjigaStr nalazi se originalni naslov odabran iz comboBoxa.
					 * tu knjigu cemo pronaci u BP i da vidimo da li je ima na stanju.
					 */					
					
					MKnjiga knjiga = GetDbTables.getKnjigaByOrigNaslov(origNaslov);


					/*
					 * Posto knjige ima na stanju, tada je sifKnjige upisano (nije -1), i onda cemo pronaci 
					 * prvi slobodan primjerak  iz bp tabele "primjerak", i zapamtit njegovu sifru, 
					 * te mu promjeniti "rezervisan" na 1, sto znaci da je  taj primjerak rezervisan.
					 * Takodje posto ima primjerak koji se moze rezervisati, prvo u tabelul "rezervacija"
					 * unosimo podatke, tj kad ce se doci po knjigu i kad ce se vratiti.
					 */
					int sifRezervacija = -1; 
					MPrimjerak primjerak = new MPrimjerak(); 
					MRezervacija rezervacija = new MRezervacija();

					if(knjiga.getSifKnjiga() != -1){
						
						/*
						 * Pronalazimo prvi slobodan primjerak
						 */
						for (MPrimjerak primjerakPom : GetDbTables.getTablePrimjerak()) {
							if(primjerakPom.getSifKnjiga() == knjiga.getSifKnjiga() && primjerakPom.getRezervisan() != 1){
								primjerak.setSifPrimjerak(primjerakPom.getSifPrimjerak());
								DBPrimjerak.updateRezervisan(1, primjerak.getSifPrimjerak());
								break;
							}
						}
						
						rezervacija.setSifPrimjerak(primjerak.getSifPrimjerak());
						/*
						 * Upisivanje u db tabelu rezervacija danasnji datum i datumm skad se vraca kjiga
						 */
						java.util.Date utilDate = todayDate;
						Date datRezervcije = new Date(utilDate.getTime());
						rezervacija.setDatRezervacija(datRezervcije);  //
						
						utilDate = pickerVracanje.getDate();
						Date datVracanje = new Date(utilDate.getTime());
						rezervacija.setDatVracanja(datVracanje);
						
						rezervacija.setSifKorisnik(LoginGUI.sifNastavnikActive); 
						rezervacija.setNastStud(1); //radi se o nastavniku
						
						/*
						 * imamo sada rezervaciju tj datume kad cemo podici i kad cemo vratiti knjigu. 
						 * To sada upisujemo u BP rezervacija
						 */
						DBRezervacija.insertRezervacija(rezervacija);

						/*
						 * Pronalazimo sad datume rezervacija, da dobijemo sifRezervacija iz tabele rezervacija za aktivnog korisnika
						 */
						for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
							/*
							 * ovaj dio nam treba za poredjenje dva datuma
							 */
							Calendar cal1 = Calendar.getInstance();
							Calendar cal2 = Calendar.getInstance();
							Calendar cal3 = Calendar.getInstance();
							Calendar cal4= Calendar.getInstance();
							cal1.setTime(rez.getDatRezervacija());
							cal2.setTime(datRezervcije);
							cal3.setTime(rez.getDatVracanja());
							cal4.setTime(datVracanje);
							/*
							 * true ako je isti dan rezervacije, false ako nije
							 */
							boolean istiDanRezervacije = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
														 cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
							/*
							 * true ako je isti dan vracanja, false ako nije
							 */
							boolean istiDanVracanja = cal3.get(Calendar.YEAR) == cal4.get(Calendar.YEAR) &&
													  cal3.get(Calendar.DAY_OF_YEAR) == cal4.get(Calendar.DAY_OF_YEAR);						 
							
							if(rez.getSifKorisnik() == LoginGUI.sifNastavnikActive && 
									rez.getSifPrimjerak() == primjerak.getSifPrimjerak() &&
									istiDanRezervacije && istiDanVracanja &&
									rez.getNastStud() == 1){
								sifRezervacija = rez.getSifRezervacija();
								break;
							}
						}
						
						/*
						 * Kako imamo sve sto nam treba, sifPrimjerak, sifStudent, sifRezervacija, to upisujemo
						 * u BP tabelu RezervacijaPrimjerakStudent
						 */
						/*
						 * Kako imamo sve sto nam treba, sifPrimjerak, sifStudent, sifRezervacija, to upisujemo
						 * u BP tabelu RezervacijaPrimjerakStudent
						 */
						MRezervacijaPrimjerakNastavnik rpn = new MRezervacijaPrimjerakNastavnik();
						rpn.setSifNastavnik(LoginGUI.sifNastavnikActive);
						rpn.setSifRezervacija(sifRezervacija);
						rpn.setSifPrimjerak(primjerak.getSifPrimjerak());

						DBRezervacijaPrimjerakNastavnik.insertRezPrimjNast(rpn);
						
						/*
						 * Posto je izvrsena rezervacija, treba se i povecati br rezervacija za studenta
						 */
						MNastavnik nastavnik = new MNastavnik();
						
						for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
							if(nast.getSifNastavnik() == LoginGUI.sifNastavnikActive){
								nastavnik = nast;
								break;
							}
						}
						int trenutniBrRezervacija = nastavnik.getBrRezervacija();
						trenutniBrRezervacija++;
						DBNastavnik.updateBrRezervacija(trenutniBrRezervacija, nastavnik.getSifNastavnik());
						zaduziKnjigu.dispose();
					}
				}
				
			});
			btnRezervii.setBounds(163, 98, 117, 25);
			zaduziKnjigu.getContentPane().add(btnRezervii);
		}
	}

	private void sveKnjige() {
		JInternalFrame sveKnjige = new JInternalFrame("Knjige", true, true, true);
		sveKnjige.setBounds(0, 12, 799, 377);
		sveKnjige.setVisible(true);
		frame.getContentPane().add(sveKnjige);
		sveKnjige.getContentPane().setLayout(null);
		prikazSveKnjige(sveKnjige);
	}
	
	private void sveKnjigeOdAutora(String autor) {
		JInternalFrame sveKnjigeOdAutora = new JInternalFrame("Knjige", true, true, true);
		sveKnjigeOdAutora.setBounds(0, 12, 799, 377);
		sveKnjigeOdAutora.setVisible(true);
		frame.getContentPane().add(sveKnjigeOdAutora);
		sveKnjigeOdAutora.getContentPane().setLayout(null);
		prikazSveKnjigeOdAutora(sveKnjigeOdAutora, autor);

	}
	
	private void sveKnjigeZaPredmet(String predmet) {
		JInternalFrame sveKnjigezaPredmet = new JInternalFrame("Knjige", true, true, true);
		sveKnjigezaPredmet.setBounds(0, 12, 799, 377);
		sveKnjigezaPredmet.setVisible(true);
		frame.getContentPane().add(sveKnjigezaPredmet);
		sveKnjigezaPredmet.getContentPane().setLayout(null);
		prikazSveKnjigeZaPredmet(sveKnjigezaPredmet, predmet);
	}

	private void sviAutori() {
		JInternalFrame sviAutori = new JInternalFrame("Autori", true, true, true);
		sviAutori.setBounds(12, 12, 468, 308);
		sviAutori.setVisible(true);
		frame.getContentPane().add(sviAutori);
		sviAutori.getContentPane().setLayout(null);
		prikazSviAutori(sviAutori);
	}

	private void sviIzdavaci() {
		JInternalFrame sviIzdavaci = new JInternalFrame("Izdavači", true, true, true);
		sviIzdavaci.setBounds(12, 12, 293, 308);
		sviIzdavaci.setVisible(true);
		frame.getContentPane().add(sviIzdavaci);
		sviIzdavaci.getContentPane().setLayout(null);
		StudentGUI.prikazSviIzdavaci(sviIzdavaci);
	}

	private void sviNastavnici() {
		JInternalFrame sviNastavnici = new JInternalFrame("Nastavnici", true, true, true);
		sviNastavnici.setBounds(12, 12, 682, 308);
		sviNastavnici.setVisible(true);
		frame.getContentPane().add(sviNastavnici);
		sviNastavnici.getContentPane().setLayout(null);
		prikazSviNastavnici(sviNastavnici);
	}

	private static void sviStudenti() {
		JInternalFrame sviStudenti = new JInternalFrame("Studenti", true, true, true);
		sviStudenti.setBounds(12, 12, 601, 308);
		sviStudenti.setVisible(true);
		frame.getContentPane().add(sviStudenti);
		sviStudenti.getContentPane().setLayout(null);
		prikazSviStudenti(sviStudenti);
	}
	
	private void sviNastavniciBibliotekar() {
		JInternalFrame sviNastavnici = new JInternalFrame("Nastavnici", true, true, true);
		sviNastavnici.setBounds(12, 12, 480, 340);
		sviNastavnici.setVisible(true);
		frame.getContentPane().add(sviNastavnici);
		sviNastavnici.getContentPane().setLayout(null);
		prikazSviNastavniciBibliotekar(sviNastavnici);
	}

	private static void sviStudentiBibliotekar() {
		JInternalFrame sviStudenti = new JInternalFrame("Studenti", true, true, true);
		sviStudenti.setBounds(12, 12, 601, 308);
		sviStudenti.setVisible(true);
		frame.getContentPane().add(sviStudenti);
		sviStudenti.getContentPane().setLayout(null);
		prikazSviStudentiBibliotekar(sviStudenti);
	}

	private void sviPredmeti() {
		JInternalFrame sviPredmeti = new JInternalFrame("Predmeti", true, true, true);
		sviPredmeti.setBounds(12, 12, 682, 308);
		sviPredmeti.setVisible(true);
		frame.getContentPane().add(sviPredmeti);
		sviPredmeti.getContentPane().setLayout(null);
		prikazSviPredmeti(sviPredmeti);
	}

	private void sviPredmetiZaNastavnika(String nastavnik) {
		JInternalFrame sviPredmeti = new JInternalFrame("Predmeti", true, true, true);
		sviPredmeti.setBounds(12, 12, 682, 308);
		sviPredmeti.setVisible(true);
		frame.getContentPane().add(sviPredmeti);
		sviPredmeti.getContentPane().setLayout(null);
		prikazSviPredmetiZaNastavnika(sviPredmeti, nastavnik);
	}
	
	private void novaLiteraturaPredmet(){
		JInternalFrame novaLiteraturaPredmet = new JInternalFrame("Unos literature za predmet", true, true, true);
		novaLiteraturaPredmet.setBounds(33, 12, 565, 409);
		novaLiteraturaPredmet.setVisible(true);
		frame.getContentPane().add(novaLiteraturaPredmet);
		novaLiteraturaPredmet.getContentPane().setLayout(null);

		JLabel lblPredLiteratura = new JLabel("Predmet:");
		lblPredLiteratura.setBounds(12, 12, 65, 15);
		novaLiteraturaPredmet.getContentPane().add(lblPredLiteratura);

		JComboBox<String> cmbBoxPredmLiteratura = new JComboBox<String>();
		cmbBoxPredmLiteratura.setBounds(95, 7, 443, 24);
		novaLiteraturaPredmet.getContentPane().add(cmbBoxPredmLiteratura);

		/*
		 * popunjavanje combo boxa predmetima koje samo trenutni nastavnik predaje
		 */
		for (MPredmet predmet : GetDbTables.getTablePredmeti()) {
			if(predmet.getSifnastavnik() == LoginGUI.sifNastavnikActive)
				cmbBoxPredmLiteratura.addItem(predmet.getNazPredmet());
		}

		JLabel lblObaveznaLiteratura = new JLabel("Obavezna literatura:");
		lblObaveznaLiteratura.setBounds(12, 54, 148, 15);
		novaLiteraturaPredmet.getContentPane().add(lblObaveznaLiteratura);

		JComboBox<String> cmbBoxObavLit1 = new JComboBox<String>();
		cmbBoxObavLit1.setBounds(12, 81, 250, 24);
		novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit1);

		/*
		 * Punimo prvi combo box sa svim knjigama
		 */
		
		final ArrayList<MKnjiga> knjigaLista = GetDbTables.getTableKnjige();
		final ArrayList<MKnjiga> knjigaLista1 = new ArrayList<>(knjigaLista);

		cmbBoxObavLit1.addItem("Obavezna literatura 1:");
		for (MKnjiga knjiga : knjigaLista) {
			cmbBoxObavLit1.addItem(knjiga.getNaslov());
		}

		JComboBox<String> cmbBoxObavLit2 = new JComboBox<String>();
		cmbBoxObavLit2.setBounds(12, 117, 250, 24);
		cmbBoxObavLit2.addItem("Obavezna literatura 2:");

		JComboBox<String> cmbBoxObavLit3 = new JComboBox<String>();
		cmbBoxObavLit3.setBounds(12, 157, 250, 24);
		cmbBoxObavLit3.addItem("Obavezna literatura 3:");

		JComboBox<String> cmbBoxObavLit4 = new JComboBox<String>();
		cmbBoxObavLit4.setBounds(12, 193, 250, 24);
		cmbBoxObavLit4.addItem("Obavezna literatura 4:");

		JComboBox<String> cmbBoxObavLit5 = new JComboBox<String>();
		cmbBoxObavLit5.setBounds(12, 229, 250, 24);
		cmbBoxObavLit5.addItem("Obavezna literatura 5:");

		JComboBox<String> cmbBoxObavLit6 = new JComboBox<String>();
		cmbBoxObavLit6.setBounds(12, 267, 250, 24);
		cmbBoxObavLit6.addItem("Obavezna literatura 6:");

		JComboBox<String> cmbBoxObavLit7 = new JComboBox<String>();
		cmbBoxObavLit7.setBounds(12, 303, 250, 24);
		cmbBoxObavLit7.addItem("Obavezna literatura 7:");

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 54, 2, 273);
		novaLiteraturaPredmet.getContentPane().add(separator);

		JLabel lblNeobaveznaLiteratura = new JLabel("Neobavezna literatura:");
		lblNeobaveznaLiteratura.setBounds(294, 54, 165, 15);
		novaLiteraturaPredmet.getContentPane().add(lblNeobaveznaLiteratura);

		JComboBox<String> cmbBoxNeObavLit1 = new JComboBox<String>();
		cmbBoxNeObavLit1.setBounds(288, 81, 250, 24);
		novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit1);

		cmbBoxNeObavLit1.addItem("Neobavezna literatura 1:");
		for (MKnjiga knjiga : knjigaLista) {
			cmbBoxNeObavLit1.addItem(knjiga.getNaslov());
		}

		JComboBox<String> cmbBoxNeObavLit2 = new JComboBox<String>();
		cmbBoxNeObavLit2.setBounds(288, 117, 250, 24);
		cmbBoxNeObavLit2.addItem("Neobavezna literatura 2:");

		JComboBox<String> cmbBoxNeObavLit3 = new JComboBox<String>();
		cmbBoxNeObavLit3.setBounds(288, 157, 250, 24);
		cmbBoxNeObavLit3.addItem("Neobavezna literatura 3:");

		JComboBox<String> cmbBoxNeObavLit4 = new JComboBox<String>();
		cmbBoxNeObavLit4.setBounds(288, 193, 250, 24);
		cmbBoxNeObavLit4.addItem("Neobavezna literatura 4:");

		JComboBox<String> cmbBoxNeObavLit5 = new JComboBox<String>();
		cmbBoxNeObavLit5.setBounds(288, 229, 250, 24);
		cmbBoxNeObavLit5.addItem("Neobavezna literatura 5:");

		JComboBox<String> cmbBoxNeObavLit6 = new JComboBox<String>();
		cmbBoxNeObavLit6.setBounds(288, 267, 250, 24);
		cmbBoxNeObavLit6.addItem("Neobavezna literatura 6:");

		JComboBox<String> cmbBoxNeObavLit7 = new JComboBox<String>();
		cmbBoxNeObavLit7.setBounds(288, 303, 250, 24);
		cmbBoxNeObavLit7.addItem("Neobavezna literatura 7:");

		/*
		 * u sljedecem dijelu definisemo sta se desava kada se klikne tj odabere neki combo box. 
		 * Treba da odabirom jednog da se pojavi sljedeci, itd. 
		 */
		/*
		 * Dio za obaveznu literaturu
		 */
		cmbBoxObavLit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxObavLit1.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit2);
					cmbBoxObavLit2.setVisible(true);

					cmbBoxObavLit2.removeAllItems();
					cmbBoxObavLit2.addItem("Obavezna literatura 2:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);
					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit2.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit2.setVisible(false);
					cmbBoxObavLit3.setVisible(false);
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}
			}
		});

		cmbBoxObavLit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit2.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit3);
					cmbBoxObavLit3.setVisible(true);

					cmbBoxObavLit3.removeAllItems();
					cmbBoxObavLit3.addItem("Obavezna literatura 3:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit3.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit3.setVisible(false);
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit3.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit4);
					cmbBoxObavLit4.setVisible(true);

					cmbBoxObavLit4.removeAllItems();
					cmbBoxObavLit4.addItem("Obavezna literatura 4:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit4.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}


			}
		});

		cmbBoxObavLit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit4.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit5);
					cmbBoxObavLit5.setVisible(true);

					cmbBoxObavLit5.removeAllItems();
					cmbBoxObavLit5.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit5.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit5.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit6);
					cmbBoxObavLit6.setVisible(true);

					cmbBoxObavLit6.removeAllItems();
					cmbBoxObavLit6.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit6.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit6.setVisible(false);	
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit6.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxObavLit7);
					cmbBoxObavLit7.setVisible(true);

					cmbBoxObavLit7.removeAllItems();
					cmbBoxObavLit7.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit6.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit7.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit7.setVisible(false);
				}


			}
		});


		/*
		 * za neobaveznu literaturu
		 */

		cmbBoxNeObavLit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxNeObavLit1.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit2);
					cmbBoxNeObavLit2.setVisible(true);

					cmbBoxNeObavLit2.removeAllItems();
					cmbBoxNeObavLit2.addItem("Neobavezna literatura 2:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);
					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit2.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit2.setVisible(false);
					cmbBoxNeObavLit3.setVisible(false);
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}
			}
		});

		cmbBoxNeObavLit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit2.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit3);
					cmbBoxNeObavLit3.setVisible(true);

					cmbBoxNeObavLit3.removeAllItems();
					cmbBoxNeObavLit3.addItem("Neobavezna literatura 3:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit3.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit3.setVisible(false);
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit3.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit4);
					cmbBoxNeObavLit4.setVisible(true);

					cmbBoxNeObavLit4.removeAllItems();
					cmbBoxNeObavLit4.addItem("Neobavezna literatura 4:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit4.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}


			}
		});

		cmbBoxNeObavLit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit4.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit5);
					cmbBoxNeObavLit5.setVisible(true);

					cmbBoxNeObavLit5.removeAllItems();
					cmbBoxNeObavLit5.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit5.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit5.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit6);
					cmbBoxNeObavLit6.setVisible(true);

					cmbBoxNeObavLit6.removeAllItems();
					cmbBoxNeObavLit6.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit6.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit6.setVisible(false);	
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit6.getSelectedIndex() > 0) {
					novaLiteraturaPredmet.getContentPane().add(cmbBoxNeObavLit7);
					cmbBoxNeObavLit7.setVisible(true);

					cmbBoxNeObavLit7.removeAllItems();
					cmbBoxNeObavLit7.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit6.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit7.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit7.setVisible(false);
				}


			}
		});

		JButton btnPotvrdiUnos_7 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MKnjigaPredmetObaveznost kpo = new MKnjigaPredmetObaveznost();

				/*
				 * Prvo dohvatimo sifru odabranog predmeta
				 */
				String pomS = (String) cmbBoxPredmLiteratura.getSelectedItem();
				kpo.setSifPredmet(GetDbTables.getSifPredmetByNaziv(pomS)); 

				/*
				 * Trebamo pokupiti obavezne knjige i redni broj vaznost
				 */
				if(cmbBoxObavLit1.getSelectedIndex() > 0){
					/* ako je odabrana prva knjiga, vaznost je 1 i obaveznost DA (sifOb=1). pronalazimo u tabeli
					 * vaznostObaveznost n-torku koja odgovara ovim vrijednostima.
					 * trebamo dohvatiti sifru za vaznost 1 iz tabele vaznost 
					 */
					int sifraVazn = GetDbTables.getSifVaznost(1);
					/*
					 * obaveznost DA (sifOb=1)
					 */
					int sifraObav = GetDbTables.getSifObaveznost(1);

					/*
					 * na osnovu pomVazn i pomObav, trebamo pronaci sifru za tu ntorku u tabeli VaznostObaveznost
					 * upisujemo dohvacenu sifru pom (koja je ustvai sifVaznObav) u varijablu kpo
					 */
					kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));

					/*
					 * Sada radimo sa dohvacanjem odabranih knjiga i onda upisivanje u BP. citamo prvo dohvacenu knjigu iz BP odabranu u comboBoxu 1 za obavezne knjige
					 */
					String odabKnjiga = (String) cmbBoxObavLit1.getSelectedItem();
				
					kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
					DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
					
					/*
					 * ponovimo sve sa ostalim knjigama. ugnjizdeni if lanac ide zato jer ako je odabrana prva knjiga, tek tad moze biti
					 * odabrana i druga, itd
					 */
					if (cmbBoxObavLit2.getSelectedIndex() > 0) {
						sifraVazn = GetDbTables.getSifVaznost(2);
						sifraObav = GetDbTables.getSifObaveznost(1);
						kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
						odabKnjiga = (String) cmbBoxObavLit2.getSelectedItem();
						kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
						DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
						if (cmbBoxObavLit3.getSelectedIndex() > 0) {
							sifraVazn = GetDbTables.getSifVaznost(3);
							sifraObav = GetDbTables.getSifObaveznost(1);
							kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
							odabKnjiga = (String) cmbBoxObavLit3.getSelectedItem(); 
							kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
							DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
							if (cmbBoxObavLit4.getSelectedIndex() > 0) {
								sifraVazn = GetDbTables.getSifVaznost(4);
								sifraObav = GetDbTables.getSifObaveznost(1);
								kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
								odabKnjiga = (String) cmbBoxObavLit4.getSelectedItem(); 
								kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
								DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
								if (cmbBoxObavLit5.getSelectedIndex() > 0) {
									sifraVazn = GetDbTables.getSifVaznost(5);
									sifraObav = GetDbTables.getSifObaveznost(1);
									kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
									odabKnjiga = (String) cmbBoxObavLit5.getSelectedItem(); 
									kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
									DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
									if (cmbBoxObavLit6.getSelectedIndex() > 0) {
										sifraVazn = GetDbTables.getSifVaznost(6);
										sifraObav = GetDbTables.getSifObaveznost(1);
										kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
										odabKnjiga = (String) cmbBoxObavLit6.getSelectedItem();
										kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
										DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
										if (cmbBoxObavLit7.getSelectedIndex() > 0) {
											sifraVazn = GetDbTables.getSifVaznost(7);
											sifraObav = GetDbTables.getSifObaveznost(1);
											kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
											odabKnjiga = (String) cmbBoxObavLit7.getSelectedItem(); 
											kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
											DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
										}
									}
								}
							}
						}
					}
				}
					
				
				/*
				 * Trebamo pokupiti neobavezne knjige
				 */
				if(cmbBoxNeObavLit1.getSelectedIndex() > 0){
					int sifraVazn = GetDbTables.getSifVaznost(1);
					//obaveznost NE (sifOb=2)
					int sifraObav = GetDbTables.getSifObaveznost(2);
					kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
					String odabKnjiga = (String) cmbBoxNeObavLit1.getSelectedItem(); 
					kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
					DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 

					if (cmbBoxNeObavLit2.getSelectedIndex() > 0) {
						sifraVazn = GetDbTables.getSifVaznost(7);
						sifraObav = GetDbTables.getSifObaveznost(2);
						kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
						odabKnjiga = (String) cmbBoxNeObavLit2.getSelectedItem(); 
						kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
						DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
						if (cmbBoxNeObavLit3.getSelectedIndex() > 0) {
							sifraVazn = GetDbTables.getSifVaznost(3);
							sifraObav = GetDbTables.getSifObaveznost(2);
							kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
							odabKnjiga = (String) cmbBoxNeObavLit3.getSelectedItem(); 
							kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
							DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
							if (cmbBoxNeObavLit4.getSelectedIndex() > 0) {
								sifraVazn = GetDbTables.getSifVaznost(4);
								sifraObav = GetDbTables.getSifObaveznost(2);
								kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
								odabKnjiga = (String) cmbBoxNeObavLit4.getSelectedItem(); 
								kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
								DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
								if (cmbBoxNeObavLit5.getSelectedIndex() > 0) {
									sifraVazn = GetDbTables.getSifVaznost(5);
									sifraObav = GetDbTables.getSifObaveznost(2);
									kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
									odabKnjiga = (String) cmbBoxNeObavLit5.getSelectedItem(); 
									kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
									DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
									if (cmbBoxNeObavLit6.getSelectedIndex() > 0) {
										sifraVazn = GetDbTables.getSifVaznost(6);
										sifraObav = GetDbTables.getSifObaveznost(2);
										kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
										odabKnjiga = (String) cmbBoxNeObavLit6.getSelectedItem(); 
										kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
										DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
										if (cmbBoxNeObavLit7.getSelectedIndex() > 0) {
											sifraVazn = GetDbTables.getSifVaznost(7);
											sifraObav = GetDbTables.getSifObaveznost(2);
											kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
											odabKnjiga = (String) cmbBoxNeObavLit7.getSelectedItem(); 
											kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
											DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
										}
									}
								}
							}
						}
					}
				}
				novaLiteraturaPredmet.dispose();
			}
		});
		btnPotvrdiUnos_7.setBounds(217, 339, 130, 25);
		novaLiteraturaPredmet.getContentPane().add(btnPotvrdiUnos_7);
	}

	private void izmjeniLiteraturuPredmet(){
		JInternalFrame izmjeniLiteraturaPredmet = new JInternalFrame("Izmjena literature za predmet", true, true, true);
		izmjeniLiteraturaPredmet.setBounds(33, 12, 564, 409);
		izmjeniLiteraturaPredmet.setVisible(true);
		frame.getContentPane().add(izmjeniLiteraturaPredmet);
		izmjeniLiteraturaPredmet.getContentPane().setLayout(null);

		JLabel lblPredLiteratura = new JLabel("Predmet:");
		lblPredLiteratura.setBounds(12, 12, 65, 15);
		izmjeniLiteraturaPredmet.getContentPane().add(lblPredLiteratura);

		JComboBox<String> cmbBoxPredmLiteratura = new JComboBox<String>();
		cmbBoxPredmLiteratura.setBounds(95, 7, 320, 24);
		izmjeniLiteraturaPredmet.getContentPane().add(cmbBoxPredmLiteratura);
		
		JButton btnIzmjeni = new JButton("Izmjeni");
		btnIzmjeni.setBounds(427, 7, 117, 25);
		izmjeniLiteraturaPredmet.getContentPane().add(btnIzmjeni);

		/*
		 * popunjavanje combo boxa predmetima koje samo trenutni nastavnik predaje
		 */
		cmbBoxPredmLiteratura.addItem("-----------------------------------------");
		for (MPredmet predmet : GetDbTables.getTablePredmeti()) {
			if(predmet.getSifnastavnik() == LoginGUI.sifNastavnikActive)
				cmbBoxPredmLiteratura.addItem(predmet.getNazPredmet());
		}
		
		/*
		 * kada kliknemo na izmjenu, treba se pozvati prozor za mijenjanje literature za predeme
		 */
		btnIzmjeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				//prosljedjujemo predmet preko naziv predmeta iz combo boxa
				izmjeniLiteraturu(GetDbTables.getPredmetBynaziv((String)cmbBoxPredmLiteratura.getSelectedItem()));
				izmjeniLiteraturaPredmet.dispose();
			}
		});
	}
	
	private void izmjeniLiteraturu(MPredmet predmet){		
		JInternalFrame izmjeniLiteraturu = new JInternalFrame("Izmjena literature za predmet", true, true, true);
		izmjeniLiteraturu.setBounds(33, 12, 565, 409);
		izmjeniLiteraturu.setVisible(true);
		frame.getContentPane().add(izmjeniLiteraturu);
		izmjeniLiteraturu.getContentPane().setLayout(null);

		/*
		 * ispisati prosljedjeni predmet
		 */
		JLabel lblPredLiteratura = new JLabel("Predmet: " + predmet.getNazPredmet());
		lblPredLiteratura.setBounds(12, 12, 400, 15);
		izmjeniLiteraturu.getContentPane().add(lblPredLiteratura);

		JLabel lblObaveznaLiteratura = new JLabel("Obavezna literatura:");
		lblObaveznaLiteratura.setBounds(12, 54, 148, 15);
		izmjeniLiteraturu.getContentPane().add(lblObaveznaLiteratura);

		JComboBox<String> cmbBoxObavLit1 = new JComboBox<String>();
		cmbBoxObavLit1.setBounds(12, 81, 250, 24);
		izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit1);

		/*
		 * Punimo prvi combo box sa svim knjigama
		 */
		final ArrayList<MKnjiga> knjigaLista = GetDbTables.getTableKnjige();
		final ArrayList<MKnjiga> knjigaLista1 = new ArrayList<>(knjigaLista);

		cmbBoxObavLit1.addItem("Obavezna literatura 1:");
		for (MKnjiga knjiga : knjigaLista) {
			cmbBoxObavLit1.addItem(knjiga.getNaslov());
		}

		JComboBox<String> cmbBoxObavLit2 = new JComboBox<String>();
		cmbBoxObavLit2.setBounds(12, 117, 250, 24);
		cmbBoxObavLit2.addItem("Obavezna literatura 2:");

		JComboBox<String> cmbBoxObavLit3 = new JComboBox<String>();
		cmbBoxObavLit3.setBounds(12, 157, 250, 24);
		cmbBoxObavLit3.addItem("Obavezna literatura 3:");

		JComboBox<String> cmbBoxObavLit4 = new JComboBox<String>();
		cmbBoxObavLit4.setBounds(12, 193, 250, 24);
		cmbBoxObavLit4.addItem("Obavezna literatura 4:");

		JComboBox<String> cmbBoxObavLit5 = new JComboBox<String>();
		cmbBoxObavLit5.setBounds(12, 229, 250, 24);
		cmbBoxObavLit5.addItem("Obavezna literatura 5:");

		JComboBox<String> cmbBoxObavLit6 = new JComboBox<String>();
		cmbBoxObavLit6.setBounds(12, 267, 250, 24);
		cmbBoxObavLit6.addItem("Obavezna literatura 6:");

		JComboBox<String> cmbBoxObavLit7 = new JComboBox<String>();
		cmbBoxObavLit7.setBounds(12, 303, 250, 24);
		cmbBoxObavLit7.addItem("Obavezna literatura 7:");

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 54, 2, 273);
		izmjeniLiteraturu.getContentPane().add(separator);

		JLabel lblNeobaveznaLiteratura = new JLabel("Neobavezna literatura:");
		lblNeobaveznaLiteratura.setBounds(294, 54, 165, 15);
		izmjeniLiteraturu.getContentPane().add(lblNeobaveznaLiteratura);

		JComboBox<String> cmbBoxNeObavLit1 = new JComboBox<String>();
		cmbBoxNeObavLit1.setBounds(288, 81, 250, 24);
		izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit1);

		cmbBoxNeObavLit1.addItem("Neobavezna literatura 1:");
		for (MKnjiga knjiga : knjigaLista) {
			cmbBoxNeObavLit1.addItem(knjiga.getNaslov());
		}

		JComboBox<String> cmbBoxNeObavLit2 = new JComboBox<String>();
		cmbBoxNeObavLit2.setBounds(288, 117, 250, 24);
		cmbBoxNeObavLit2.addItem("Neobavezna literatura 2:");

		JComboBox<String> cmbBoxNeObavLit3 = new JComboBox<String>();
		cmbBoxNeObavLit3.setBounds(288, 157, 250, 24);
		cmbBoxNeObavLit3.addItem("Neobavezna literatura 3:");

		JComboBox<String> cmbBoxNeObavLit4 = new JComboBox<String>();
		cmbBoxNeObavLit4.setBounds(288, 193, 250, 24);
		cmbBoxNeObavLit4.addItem("Neobavezna literatura 4:");

		JComboBox<String> cmbBoxNeObavLit5 = new JComboBox<String>();
		cmbBoxNeObavLit5.setBounds(288, 229, 250, 24);
		cmbBoxNeObavLit5.addItem("Neobavezna literatura 5:");

		JComboBox<String> cmbBoxNeObavLit6 = new JComboBox<String>();
		cmbBoxNeObavLit6.setBounds(288, 267, 250, 24);
		cmbBoxNeObavLit6.addItem("Neobavezna literatura 6:");

		JComboBox<String> cmbBoxNeObavLit7 = new JComboBox<String>();
		cmbBoxNeObavLit7.setBounds(288, 303, 250, 24);
		cmbBoxNeObavLit7.addItem("Neobavezna literatura 7:");

		/*
		 * u sljedecem dijelu definisemo sta se desava kada se klikne tj odabere neki combo box. 
		 * Treba da odabirom jednog da se pojavi sljedeci, itd. 
		 */
		/*
		 * Dio za obaveznu literaturu
		 */
		cmbBoxObavLit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxObavLit1.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit2);
					cmbBoxObavLit2.setVisible(true);

					cmbBoxObavLit2.removeAllItems();
					cmbBoxObavLit2.addItem("Obavezna literatura 2:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);
					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit2.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit2.setVisible(false);
					cmbBoxObavLit3.setVisible(false);
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}
			}
		});

		cmbBoxObavLit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit2.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit3);
					cmbBoxObavLit3.setVisible(true);

					cmbBoxObavLit3.removeAllItems();
					cmbBoxObavLit3.addItem("Obavezna literatura 3:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit3.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit3.setVisible(false);
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit3.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit4);
					cmbBoxObavLit4.setVisible(true);

					cmbBoxObavLit4.removeAllItems();
					cmbBoxObavLit4.addItem("Obavezna literatura 4:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit4.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit4.setVisible(false);
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}


			}
		});

		cmbBoxObavLit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit4.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit5);
					cmbBoxObavLit5.setVisible(true);

					cmbBoxObavLit5.removeAllItems();
					cmbBoxObavLit5.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit5.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit5.setVisible(false);
					cmbBoxObavLit6.setVisible(false);
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit5.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit6);
					cmbBoxObavLit6.setVisible(true);

					cmbBoxObavLit6.removeAllItems();
					cmbBoxObavLit6.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit6.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit6.setVisible(false);	
					cmbBoxObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxObavLit6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxObavLit6.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxObavLit7);
					cmbBoxObavLit7.setVisible(true);

					cmbBoxObavLit7.removeAllItems();
					cmbBoxObavLit7.addItem("Obavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxObavLit6.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxObavLit7.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxObavLit7.setVisible(false);
				}


			}
		});


		/*
		 * za neobaveznu literaturu
		 */

		cmbBoxNeObavLit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxNeObavLit1.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit2);
					cmbBoxNeObavLit2.setVisible(true);

					cmbBoxNeObavLit2.removeAllItems();
					cmbBoxNeObavLit2.addItem("Neobavezna literatura 2:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);
					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit2.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit2.setVisible(false);
					cmbBoxNeObavLit3.setVisible(false);
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}
			}
		});

		cmbBoxNeObavLit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit2.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit3);
					cmbBoxNeObavLit3.setVisible(true);

					cmbBoxNeObavLit3.removeAllItems();
					cmbBoxNeObavLit3.addItem("Neobavezna literatura 3:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit3.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit3.setVisible(false);
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit3.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit4);
					cmbBoxNeObavLit4.setVisible(true);

					cmbBoxNeObavLit4.removeAllItems();
					cmbBoxNeObavLit4.addItem("Neobavezna literatura 4:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit4.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit4.setVisible(false);
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}


			}
		});

		cmbBoxNeObavLit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit4.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit5);
					cmbBoxNeObavLit5.setVisible(true);

					cmbBoxNeObavLit5.removeAllItems();
					cmbBoxNeObavLit5.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit5.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit5.setVisible(false);
					cmbBoxNeObavLit6.setVisible(false);
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit5.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit6);
					cmbBoxNeObavLit6.setVisible(true);

					cmbBoxNeObavLit6.removeAllItems();
					cmbBoxNeObavLit6.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit6.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit6.setVisible(false);	
					cmbBoxNeObavLit7.setVisible(false);
				}

			}
		});

		cmbBoxNeObavLit6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxNeObavLit6.getSelectedIndex() > 0) {
					izmjeniLiteraturu.getContentPane().add(cmbBoxNeObavLit7);
					cmbBoxNeObavLit7.setVisible(true);

					cmbBoxNeObavLit7.removeAllItems();
					cmbBoxNeObavLit7.addItem("Neobavezna literatura 5:");

					int p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit1.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit2.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit3.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit4.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);

					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit5.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					p = -1;
					for (int i = 0; i < knjigaLista.size(); i++) {
						MKnjiga k = knjigaLista.get(i);
						if(cmbBoxNeObavLit6.getSelectedItem().equals(k.getNaslov())){
							p = i;
							break;
						}
					}
					if(p != -1)
						knjigaLista.remove(p);


					for(MKnjiga knjiga : knjigaLista)
						cmbBoxNeObavLit7.addItem(knjiga.getNaslov());

					knjigaLista.clear();
					for (MKnjiga knjiga : knjigaLista1) 
						knjigaLista.add(knjiga);

				} else {
					cmbBoxNeObavLit7.setVisible(false);
				}


			}
		});

		JButton btnPotvrdiUnos_7 = new JButton("Potvrdi");
		btnPotvrdiUnos_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ako izvrsi izmjenu, prvo trebaju za odgovarajuci tip izbrisati pa opet upisati
				 */
				DBKnjigaPredmetObaveznost.deleteLiteraturu(predmet.getSifPredmet());
				MKnjigaPredmetObaveznost kpo = new MKnjigaPredmetObaveznost();
				String pomS = predmet.getNazPredmet();
				kpo.setSifPredmet(GetDbTables.getSifPredmetByNaziv(pomS)); 

				if(cmbBoxObavLit1.getSelectedIndex() > 0){
					int sifraVazn = GetDbTables.getSifVaznost(1);
					/*
					 * obaveznost DA (sifOb=1)
					 */
					int sifraObav = GetDbTables.getSifObaveznost(1);
					kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
					String odabKnjiga = (String) cmbBoxObavLit1.getSelectedItem();
					kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
					DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
						if (cmbBoxObavLit2.getSelectedIndex() > 0) {
							sifraVazn = GetDbTables.getSifVaznost(2);
							sifraObav = GetDbTables.getSifObaveznost(1);
							kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
							odabKnjiga = (String) cmbBoxObavLit2.getSelectedItem(); 
							kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
							DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
							if (cmbBoxObavLit3.getSelectedIndex() > 0) {
								sifraVazn = GetDbTables.getSifVaznost(3);
								sifraObav = GetDbTables.getSifObaveznost(1);
								kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
								odabKnjiga = (String) cmbBoxObavLit3.getSelectedItem(); 
								kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
								DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
								if (cmbBoxObavLit4.getSelectedIndex() > 0) {
									sifraVazn = GetDbTables.getSifVaznost(4);
									sifraObav = GetDbTables.getSifObaveznost(1);
									kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
									odabKnjiga = (String) cmbBoxObavLit4.getSelectedItem(); 
									kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
									DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
									if (cmbBoxObavLit5.getSelectedIndex() > 0) {
										sifraVazn = GetDbTables.getSifVaznost(5);
										sifraObav = GetDbTables.getSifObaveznost(1);
										kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
										odabKnjiga = (String) cmbBoxObavLit5.getSelectedItem(); 
										kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
										DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
										if (cmbBoxObavLit6.getSelectedIndex() > 0) {
											sifraVazn = GetDbTables.getSifVaznost(6);
											sifraObav = GetDbTables.getSifObaveznost(1);
											kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
											odabKnjiga = (String) cmbBoxObavLit6.getSelectedItem(); 
											kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
											DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
											if (cmbBoxObavLit7.getSelectedIndex() > 0) {
												sifraVazn = GetDbTables.getSifVaznost(7);
												sifraObav = GetDbTables.getSifObaveznost(1);
												kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
												odabKnjiga = (String) cmbBoxObavLit7.getSelectedItem(); 
												kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
												DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 
										}
									}
								}
							}
						}
					}
				}


				/*
				 * Trebamo pokupiti neobavezne knjige
				 */
				if(cmbBoxNeObavLit1.getSelectedIndex() > 0){
					int sifraVazn = GetDbTables.getSifVaznost(1);
					//obaveznost NE (sifOb=2)
					int sifraObav = GetDbTables.getSifObaveznost(2);
					kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
					String odabKnjiga = (String) cmbBoxNeObavLit1.getSelectedItem(); 
					kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
					DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo); 

					if (cmbBoxNeObavLit2.getSelectedIndex() > 0) {
						sifraVazn = GetDbTables.getSifVaznost(7);
						sifraObav = GetDbTables.getSifObaveznost(2);
						kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
						odabKnjiga = (String) cmbBoxNeObavLit2.getSelectedItem(); 
						kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
						DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
						if (cmbBoxNeObavLit3.getSelectedIndex() > 0) {
							sifraVazn = GetDbTables.getSifVaznost(3);
							sifraObav = GetDbTables.getSifObaveznost(2);
							kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
							odabKnjiga = (String) cmbBoxNeObavLit3.getSelectedItem(); 
							kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
							DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
							if (cmbBoxNeObavLit4.getSelectedIndex() > 0) {
								sifraVazn = GetDbTables.getSifVaznost(4);
								sifraObav = GetDbTables.getSifObaveznost(2);
								kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
								odabKnjiga = (String) cmbBoxNeObavLit4.getSelectedItem(); 
								kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
								DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
								if (cmbBoxNeObavLit5.getSelectedIndex() > 0) {
									sifraVazn = GetDbTables.getSifVaznost(5);
									sifraObav = GetDbTables.getSifObaveznost(2);
									kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
									odabKnjiga = (String) cmbBoxNeObavLit5.getSelectedItem(); 
									kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
									DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
									if (cmbBoxNeObavLit6.getSelectedIndex() > 0) {
										sifraVazn = GetDbTables.getSifVaznost(6);
										sifraObav = GetDbTables.getSifObaveznost(2);
										kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
										odabKnjiga = (String) cmbBoxNeObavLit6.getSelectedItem(); 
										kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
										DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
										if (cmbBoxNeObavLit7.getSelectedIndex() > 0) {
											sifraVazn = GetDbTables.getSifVaznost(7);
											sifraObav = GetDbTables.getSifObaveznost(2);
											kpo.setSifVaznObav(GetDbTables.getSifVazObavBySifVaznostSifObaveznost(sifraVazn, sifraObav));
											odabKnjiga = (String) cmbBoxNeObavLit7.getSelectedItem(); 
											kpo.setSifKnjiga(GetDbTables.getSifKnjigaByNaslov(odabKnjiga));
											DBKnjigaPredmetObaveznost.insertKnjiPredObav(kpo);
										}
									}
								}
							}
						}
					}
				}
				izmjeniLiteraturuPredmet();
				izmjeniLiteraturu.dispose();
			}
		});
		btnPotvrdiUnos_7.setBounds(132, 339, 130, 25);
		izmjeniLiteraturu.getContentPane().add(btnPotvrdiUnos_7);
		
		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izmjeniLiteraturuPredmet();
				izmjeniLiteraturu.dispose();
			}
		});
		btnPoniti.setBounds(294, 339, 130, 25);
		izmjeniLiteraturu.getContentPane().add(btnPoniti);
	}
		
	private void prikazSveKnjige(JInternalFrame sveKnjige) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 765, 321);
		sveKnjige.getContentPane().add(scrollPane);

		tableKnjige = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		
		scrollPane.setViewportView(tableKnjige);
		tableKnjige.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Originalni naslov", "Tip", "Izdava\u010D", "Godina izdanja", "Br.prim."
				}
				));
		tableKnjige.getColumnModel().getColumn(0).setPreferredWidth(234);
		tableKnjige.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableKnjige.getColumnModel().getColumn(3).setPreferredWidth(97);
		tableKnjige.getColumnModel().getColumn(4).setPreferredWidth(50);


		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableKnjige.getModel();
		model.setRowCount(0);

		for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {

			String vrstaKnjige = new String();
			for (MVrstaKnjige vrKnj : GetDbTables.getTableVrstaKnjige()) {
				if(knjiga.getSifVrstaKnjige() == vrKnj.getSifVrstaKnjige()){
					vrstaKnjige = vrKnj.getVrsta();
					break;
				}
			}
			String izdavacStr = new String();			
			for (MIzdavac izd : GetDbTables.getTableIzdavaci()) {
				if(knjiga.getSifIzdavac() == izd.getSifIzdavac()){
					izdavacStr = izd.getNazIzdavac();
					break;
				}
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			String godIzdanja = dateFormat.format(knjiga.getGodIzdanja());

			model.addRow(new Object[]{knjiga.getOrigNaslov(), vrstaKnjige, izdavacStr, godIzdanja, String.valueOf(knjiga.getBrPrimjeraka())});
		}
		
		/*
		 * table sorter
		 */
		
		txtFilterTableKnjige = new JTextField();
		txtFilterTableKnjige.setBounds(12, 10, 256, 19);
		sveKnjige.getContentPane().add(txtFilterTableKnjige);
		txtFilterTableKnjige.setColumns(10);
		
		JRadioButton rdbtnNaslov = new JRadioButton("Naslov");
		rdbtnNaslov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
			}
		});
		rdbtnNaslov.setBounds(276, 8, 72, 23);
		sveKnjige.getContentPane().add(rdbtnNaslov);
		rdbtnNaslov.setSelected(true);
		if(rdbtnNaslov.isSelected()) {
			filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
		}
		
		JRadioButton rdbtnTip = new JRadioButton("Tip");
		rdbtnTip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 1, txtFilterTableKnjige);
			}
		});
		rdbtnTip.setBounds(352, 8, 46, 23);
		sveKnjige.getContentPane().add(rdbtnTip);
		
		JRadioButton rdbtnIzdavac = new JRadioButton("Izdavac");
		rdbtnIzdavac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 2, txtFilterTableKnjige);
			}
		});
		rdbtnIzdavac.setBounds(402, 8, 78, 23);
		sveKnjige.getContentPane().add(rdbtnIzdavac);
		
		JRadioButton rdbtnGodinaIzdanja = new JRadioButton("Godina izdanja");
		rdbtnGodinaIzdanja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 3, txtFilterTableKnjige);
			}
		});
		rdbtnGodinaIzdanja.setBounds(484, 8, 131, 23);
		sveKnjige.getContentPane().add(rdbtnGodinaIzdanja);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNaslov);
		group.add(rdbtnTip);
		group.add(rdbtnIzdavac);
		group.add(rdbtnGodinaIzdanja);
		
		
		
		/*
		 * ako se duplo klikne na knjigu, da se otvori prozor za posudjivanje te knjige
		 */
		tableKnjige.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableKnjige.rowAtPoint(evt.getPoint());
				int col = tableKnjige.columnAtPoint(evt.getPoint());
				System.out.println( "test " );
				
				System.out.println( row + " " + col);
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
					 * koja je ustvari novi prozor za resetovanje negativnih bodova 
					 */
					String origNaslov = (String) tableKnjige.getModel().getValueAt(tableKnjige.convertRowIndexToModel(row), 0);
					zaduziKnjigu(origNaslov);			
					sveKnjige.dispose();
				}
			}
		});
		
	}
	
	private void prikazSveKnjigeOdAutora(JInternalFrame sveKnjige, String autor) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 765, 321);
		sveKnjige.getContentPane().add(scrollPane);

		tableKnjige = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		
		scrollPane.setViewportView(tableKnjige);
		tableKnjige.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Originalni naslov", "Tip", "Izdava\u010D", "Godina izdanja", "Br.prim."
				}
				));
		tableKnjige.getColumnModel().getColumn(0).setPreferredWidth(234);
		tableKnjige.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableKnjige.getColumnModel().getColumn(3).setPreferredWidth(97);
		tableKnjige.getColumnModel().getColumn(4).setPreferredWidth(50);


		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableKnjige.getModel();
		model.setRowCount(0);

		for (MKnjiga knjiga : GetDbTables.getTableKnjigeOdAutora(autor)) {

			String vrstaKnjige = new String();
			for (MVrstaKnjige vrKnj : GetDbTables.getTableVrstaKnjige()) {
				if(knjiga.getSifVrstaKnjige() == vrKnj.getSifVrstaKnjige()){
					vrstaKnjige = vrKnj.getVrsta();
					break;
				}
			}
			String izdavacStr = new String();			
			for (MIzdavac izd : GetDbTables.getTableIzdavaci()) {
				if(knjiga.getSifIzdavac() == izd.getSifIzdavac()){
					izdavacStr = izd.getNazIzdavac();
					break;
				}
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			String godIzdanja = dateFormat.format(knjiga.getGodIzdanja());

			model.addRow(new Object[]{knjiga.getOrigNaslov(), vrstaKnjige, izdavacStr, godIzdanja, String.valueOf(knjiga.getBrPrimjeraka())});
		}
		
		/*
		 * table sorter
		 */
		
		txtFilterTableKnjige = new JTextField();
		txtFilterTableKnjige.setBounds(12, 10, 256, 19);
		sveKnjige.getContentPane().add(txtFilterTableKnjige);
		txtFilterTableKnjige.setColumns(10);
		
		JRadioButton rdbtnNaslov = new JRadioButton("Naslov");
		rdbtnNaslov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
			}
		});
		rdbtnNaslov.setBounds(276, 8, 72, 23);
		sveKnjige.getContentPane().add(rdbtnNaslov);
		rdbtnNaslov.setSelected(true);
		if(rdbtnNaslov.isSelected()) {
			filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
		}
		
		JRadioButton rdbtnTip = new JRadioButton("Tip");
		rdbtnTip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 1, txtFilterTableKnjige);
			}
		});
		rdbtnTip.setBounds(352, 8, 46, 23);
		sveKnjige.getContentPane().add(rdbtnTip);
		
		JRadioButton rdbtnIzdavac = new JRadioButton("Izdavac");
		rdbtnIzdavac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 2, txtFilterTableKnjige);
			}
		});
		rdbtnIzdavac.setBounds(402, 8, 78, 23);
		sveKnjige.getContentPane().add(rdbtnIzdavac);
		
		JRadioButton rdbtnGodinaIzdanja = new JRadioButton("Godina izdanja");
		rdbtnGodinaIzdanja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 3, txtFilterTableKnjige);
			}
		});
		rdbtnGodinaIzdanja.setBounds(484, 8, 131, 23);
		sveKnjige.getContentPane().add(rdbtnGodinaIzdanja);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNaslov);
		group.add(rdbtnTip);
		group.add(rdbtnIzdavac);
		group.add(rdbtnGodinaIzdanja);
		
		
		
		/*
		 * ako se duplo klikne na knjigu, da se otvori prozor za posudjivanje te knjige
		 */
		tableKnjige.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableKnjige.rowAtPoint(evt.getPoint());
				int col = tableKnjige.columnAtPoint(evt.getPoint());
				System.out.println( "test " );
				
				System.out.println( row + " " + col);
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
					 * koja je ustvari novi prozor za resetovanje negativnih bodova 
					 */
					String origNaslov = (String) tableKnjige.getModel().getValueAt(tableKnjige.convertRowIndexToModel(row), 0);
					zaduziKnjigu(origNaslov);			
					sveKnjige.dispose();
				}
			}
		});
		
	}
	
	private void prikazSveKnjigeZaPredmet(JInternalFrame sveKnjige, String predmet) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 765, 321);
		sveKnjige.getContentPane().add(scrollPane);

		tableKnjige = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		
		scrollPane.setViewportView(tableKnjige);
		tableKnjige.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Originalni naslov", "Tip", "Izdava\u010D", "Godina izdanja", "Br.prim."
				}
				));
		tableKnjige.getColumnModel().getColumn(0).setPreferredWidth(234);
		tableKnjige.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableKnjige.getColumnModel().getColumn(3).setPreferredWidth(97);
		tableKnjige.getColumnModel().getColumn(4).setPreferredWidth(50);


		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableKnjige.getModel();
		model.setRowCount(0);

		for (MKnjiga knjiga : GetDbTables.getTableKnjigeZaPredmet(predmet)) {

			String vrstaKnjige = new String();
			for (MVrstaKnjige vrKnj : GetDbTables.getTableVrstaKnjige()) {
				if(knjiga.getSifVrstaKnjige() == vrKnj.getSifVrstaKnjige()){
					vrstaKnjige = vrKnj.getVrsta();
					break;
				}
			}
			String izdavacStr = new String();			
			for (MIzdavac izd : GetDbTables.getTableIzdavaci()) {
				if(knjiga.getSifIzdavac() == izd.getSifIzdavac()){
					izdavacStr = izd.getNazIzdavac();
					break;
				}
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			String godIzdanja = dateFormat.format(knjiga.getGodIzdanja());

			model.addRow(new Object[]{knjiga.getOrigNaslov(), vrstaKnjige, izdavacStr, godIzdanja, String.valueOf(knjiga.getBrPrimjeraka())});
		}
		
		/*
		 * table sorter
		 */
		
		txtFilterTableKnjige = new JTextField();
		txtFilterTableKnjige.setBounds(12, 10, 256, 19);
		sveKnjige.getContentPane().add(txtFilterTableKnjige);
		txtFilterTableKnjige.setColumns(10);
		
		JRadioButton rdbtnNaslov = new JRadioButton("Naslov");
		rdbtnNaslov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
			}
		});
		rdbtnNaslov.setBounds(276, 8, 72, 23);
		sveKnjige.getContentPane().add(rdbtnNaslov);
		rdbtnNaslov.setSelected(true);
		if(rdbtnNaslov.isSelected()) {
			filterTableByColumn(tableKnjige, 0, txtFilterTableKnjige);
		}
		
		JRadioButton rdbtnTip = new JRadioButton("Tip");
		rdbtnTip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 1, txtFilterTableKnjige);
			}
		});
		rdbtnTip.setBounds(352, 8, 46, 23);
		sveKnjige.getContentPane().add(rdbtnTip);
		
		JRadioButton rdbtnIzdavac = new JRadioButton("Izdavac");
		rdbtnIzdavac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 2, txtFilterTableKnjige);
			}
		});
		rdbtnIzdavac.setBounds(402, 8, 78, 23);
		sveKnjige.getContentPane().add(rdbtnIzdavac);
		
		JRadioButton rdbtnGodinaIzdanja = new JRadioButton("Godina izdanja");
		rdbtnGodinaIzdanja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableKnjige, 3, txtFilterTableKnjige);
			}
		});
		rdbtnGodinaIzdanja.setBounds(484, 8, 131, 23);
		sveKnjige.getContentPane().add(rdbtnGodinaIzdanja);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNaslov);
		group.add(rdbtnTip);
		group.add(rdbtnIzdavac);
		group.add(rdbtnGodinaIzdanja);
		
		
		
		/*
		 * ako se duplo klikne na knjigu, da se otvori prozor za posudjivanje te knjige
		 */
		tableKnjige.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableKnjige.rowAtPoint(evt.getPoint());
				int col = tableKnjige.columnAtPoint(evt.getPoint());
				System.out.println( "test " );
				
				System.out.println( row + " " + col);
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
					 * koja je ustvari novi prozor za resetovanje negativnih bodova 
					 */
					String origNaslov = (String) tableKnjige.getModel().getValueAt(tableKnjige.convertRowIndexToModel(row), 0);
					zaduziKnjigu(origNaslov);			
					sveKnjige.dispose();
				}
			}
		});
		
	}
	
	private void prikazSviAutori(JInternalFrame sviAutori) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 250, 270);
		sviAutori.getContentPane().add(scrollPane);

		tableAutori = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; 
			}
		};
		scrollPane.setViewportView(tableAutori);
		tableAutori.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Prezime i ime",
				}
				));
		tableAutori.getColumnModel().getColumn(0).setPreferredWidth(241);

		DefaultTableModel model = (DefaultTableModel) tableAutori.getModel();
		model.setRowCount(0);

		for (MAutor autor : GetDbTables.getTableAutori()) {
			model.addRow(new Object[]{autor.getPrezAutor() + " " + autor.getImeAutor()});
		}
		
		
		txtFilterTableAutori = new JTextField();
		txtFilterTableAutori.setBounds(12, 12, 250, 19);
		sviAutori.getContentPane().add(txtFilterTableAutori);
		txtFilterTableAutori.setColumns(10);
		
		filterTable(tableAutori, txtFilterTableAutori);
		
		/*
		 * klikom na autora da izbaci sve njegove knjige
		 */
		tableAutori.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableAutori.rowAtPoint(evt.getPoint());
				int col = tableAutori.columnAtPoint(evt.getPoint());
				
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					
					String autor = (String) tableAutori.getModel().getValueAt(tableAutori.convertRowIndexToModel(row), 0);
					
					//potvrdiVracanje(invBr);
					//ispisi sve knjige za tog autora
					sveKnjigeOdAutora(autor);
					sviAutori.dispose();
				}
			}
		});
		
		
		
	}
	
	private void prikazSviPredmeti(JInternalFrame sviPredmeti) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 648, 389);
		sviPredmeti.getContentPane().add(scrollPane);

		tablePredmeti = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tablePredmeti);
		tablePredmeti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Predmet", "Kratica", "Predaje", "Semestar"
				}
				));
		tablePredmeti.getColumnModel().getColumn(0).setPreferredWidth(267);
		tablePredmeti.getColumnModel().getColumn(1).setPreferredWidth(86);
		tablePredmeti.getColumnModel().getColumn(2).setPreferredWidth(190);

		DefaultTableModel model = (DefaultTableModel) tablePredmeti.getModel();
		model.setRowCount(0);

		for (MPredmet predmet : GetDbTables.getTablePredmeti()) {
			String semestarStr = new String();
			for (MSemestar semestar : GetDbTables.getTableSemestr()) {
				if(predmet.getSifSemestar() == semestar.getSifSemestar()){
					semestarStr = semestar.getSemestar();
				}
			}
			String nastStr = new String();
			for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
				if(predmet.getSifnastavnik() == nastavnik.getSifNastavnik()){
					nastStr = nastavnik.getPrezNastavnik() + " " + nastavnik.getImeNastavnik();
				}
			}

			model.addRow(new Object[]{predmet.getNazPredmet(), predmet.getKratPredmet(), nastStr, semestarStr}); //upisujemo u tabelu
		}
		
		txtFilterPredmet = new JTextField();
		txtFilterPredmet.setBounds(12, 12, 385, 19);
		sviPredmeti.getContentPane().add(txtFilterPredmet);
		txtFilterPredmet.setColumns(10);
		
		JRadioButton rdbtnPredmet = new JRadioButton("Predmet");
		rdbtnPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tablePredmeti, 0, txtFilterPredmet);
			}
		});
		rdbtnPredmet.setSelected(true);
		rdbtnPredmet.setBounds(405, 10, 85, 23);
		sviPredmeti.getContentPane().add(rdbtnPredmet);
		if (rdbtnPredmet.isSelected()) {
			filterTableByColumn(tablePredmeti, 0, txtFilterPredmet);
		}
		
		
		JRadioButton rdbtnKratica = new JRadioButton("Kratica");
		rdbtnKratica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tablePredmeti, 1, txtFilterPredmet);
			}
		});
		rdbtnKratica.setBounds(494, 10, 80, 23);
		sviPredmeti.getContentPane().add(rdbtnKratica);
		
		JRadioButton rdbtnProfesor = new JRadioButton("Profesor");
		rdbtnProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tablePredmeti, 2, txtFilterPredmet);
			}
		});
		rdbtnProfesor.setBounds(578, 10, 86, 23);
		sviPredmeti.getContentPane().add(rdbtnProfesor);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPredmet);
		group.add(rdbtnKratica);
		group.add(rdbtnProfesor);
		
		
		tablePredmeti.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tablePredmeti.rowAtPoint(evt.getPoint());
				int col = tablePredmeti.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					String predmet= (String) tablePredmeti.getModel().getValueAt(tablePredmeti.convertRowIndexToModel(row), 0);
					sveKnjigeZaPredmet(predmet);
					sviPredmeti.dispose();
				}
			}
		});
		
	}
	
	private void prikazSviPredmetiZaNastavnika(JInternalFrame sviPredmeti, String prezIme) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 648, 389);
		sviPredmeti.getContentPane().add(scrollPane);

		tablePredmeti = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tablePredmeti);
		tablePredmeti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Predmet", "Kratica", "Predaje", "Semestar"
				}
				));
		tablePredmeti.getColumnModel().getColumn(0).setPreferredWidth(267);
		tablePredmeti.getColumnModel().getColumn(1).setPreferredWidth(86);
		tablePredmeti.getColumnModel().getColumn(2).setPreferredWidth(190);

		DefaultTableModel model = (DefaultTableModel) tablePredmeti.getModel();
		model.setRowCount(0);
		
		for (MPredmet predmet : GetDbTables.getTablePredmetiOdNastavnika(prezIme)) {
			String semestarStr = new String();
			for (MSemestar semestar : GetDbTables.getTableSemestr()) {
				if(predmet.getSifSemestar() == semestar.getSifSemestar()){
					semestarStr = semestar.getSemestar();
				}
			}

			model.addRow(new Object[]{predmet.getNazPredmet(), predmet.getKratPredmet(), prezIme, semestarStr}); //upisujemo u tabelu
		}
		
		txtFilterPredmet = new JTextField();
		txtFilterPredmet.setBounds(12, 12, 385, 19);
		sviPredmeti.getContentPane().add(txtFilterPredmet);
		txtFilterPredmet.setColumns(10);
		
		JRadioButton rdbtnPredmet = new JRadioButton("Predmet");
		rdbtnPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tablePredmeti, 0, txtFilterPredmet);
			}
		});
		rdbtnPredmet.setSelected(true);
		rdbtnPredmet.setBounds(405, 10, 85, 23);
		sviPredmeti.getContentPane().add(rdbtnPredmet);
		if (rdbtnPredmet.isSelected()) {
			filterTableByColumn(tablePredmeti, 0, txtFilterPredmet);
		}
		
		
		JRadioButton rdbtnKratica = new JRadioButton("Kratica");
		rdbtnKratica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tablePredmeti, 1, txtFilterPredmet);
			}
		});
		rdbtnKratica.setBounds(494, 10, 80, 23);
		sviPredmeti.getContentPane().add(rdbtnKratica);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPredmet);
		group.add(rdbtnKratica);
		
		
		tablePredmeti.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tablePredmeti.rowAtPoint(evt.getPoint());
				int col = tablePredmeti.columnAtPoint(evt.getPoint());
				System.out.println( "test " );
				
				System.out.println( row + " " + col);
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {

					String predmet = (String) tablePredmeti.getModel().getValueAt(tablePredmeti.convertRowIndexToModel(row), 0);
					sveKnjigeZaPredmet(predmet);
					sviPredmeti.dispose();
				}
			}
		});
		
	}
	
	private void prikazSviNastavnici(JInternalFrame sviNastavnici) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 450, 259);
		sviNastavnici.getContentPane().add(scrollPane);

		tableNastavnici = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			     return false; 
			}
		};;
		scrollPane.setViewportView(tableNastavnici);
		tableNastavnici.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Šifra", "Prezime i ime", "Zvanje"
				}
				));

		tableNastavnici.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableNastavnici.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableNastavnici.getColumnModel().getColumn(2).setPreferredWidth(100);
		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableNastavnici.getModel();
		model.setRowCount(0);

		for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
			model.addRow(new Object[]{nastavnik.getSifNastavnik(), nastavnik.getPrezNastavnik() + " " + nastavnik.getImeNastavnik(), nastavnik.getZvanje()}); //upisujemo u tabelu
		}
		
		txtFilterNastavnici = new JTextField();
		txtFilterNastavnici.setBounds(12, 12, 250, 19);
		sviNastavnici.getContentPane().add(txtFilterNastavnici);
		txtFilterNastavnici.setColumns(10);
		
		filterTableByColumn(tableNastavnici, 1, txtFilterNastavnici);
		/*
		 * postavljamo mouseListener koji odsluskuje da li se klikce na neki red u tabeli.
		 * Ideja je da kllikom na odredjeni red, da se otvori prozor koji ce editovati odabranog korisnika
		 * tj da bibliotekar moze resetovati negativne bodove.
		 * pristup ovoj opciji ima samo bibliotekar, pa treba o tome voditi racuna
		 */
	
			tableNastavnici.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableNastavnici.rowAtPoint(evt.getPoint());
					int col = tableNastavnici.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
						/*
						 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
						 * koja je ustvari novi prozor za resetovanje negativnih bodova 
						 */
						String nastavnik= (String) tableNastavnici.getModel().getValueAt(tableNastavnici.convertRowIndexToModel(row), 1);
						sviPredmetiZaNastavnika(nastavnik);
						sviNastavnici.dispose();
					}
				}
			});
	
	}
	
	private void prikazSviNastavniciBibliotekar(JInternalFrame sviNastavnici) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 450, 259);
		sviNastavnici.getContentPane().add(scrollPane);

		tableNastavnici = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			     return false; 
			}
		};;
		scrollPane.setViewportView(tableNastavnici);
		tableNastavnici.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Šifra", "Prezime i ime", "Zvanje"
				}
				));

		tableNastavnici.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableNastavnici.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableNastavnici.getColumnModel().getColumn(2).setPreferredWidth(100);
		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableNastavnici.getModel();
		model.setRowCount(0);

		for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
			model.addRow(new Object[]{nastavnik.getSifNastavnik(), nastavnik.getPrezNastavnik() + " " + nastavnik.getImeNastavnik(), nastavnik.getZvanje()}); //upisujemo u tabelu
		}
		
		txtFilterNastavnici = new JTextField();
		txtFilterNastavnici.setBounds(12, 12, 250, 19);
		sviNastavnici.getContentPane().add(txtFilterNastavnici);
		txtFilterNastavnici.setColumns(10);
		
		filterTableByColumn(tableNastavnici, 1, txtFilterNastavnici);
		/*
		 * postavljamo mouseListener koji odsluskuje da li se klikce na neki red u tabeli.
		 * Ideja je da kllikom na odredjeni red, da se otvori prozor koji ce editovati odabranog korisnika
		 * tj da bibliotekar moze resetovati negativne bodove.
		 * pristup ovoj opciji ima samo bibliotekar, pa treba o tome voditi racuna
		 */
		int bibliotekar = -1;
		
		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			if (nast.getSifNastavnik() == LoginGUI.sifNastavnikActive) { //provjeravamo da li je aktivni nastavnik bibliotekar
				bibliotekar = nast.getBibliotekar(); //1 za da, 0 za ne
			}
		}
		
		if(bibliotekar == 1){
			/*
			 * u row i col dobivamo redni broj reda i kolone gdje je kliknuto.
			 */
			tableNastavnici.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableNastavnici.rowAtPoint(evt.getPoint());
					int col = tableNastavnici.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
						/*
						 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
						 * koja je ustvari novi prozor za resetovanje negativnih bodova 
						 */
						int sifNast = (int) tableNastavnici.getModel().getValueAt(tableNastavnici.convertRowIndexToModel(row), 0);
						resetNastavnikBodovi(sifNast);
						sviNastavnici.dispose();
					}
				}
			});
		}
	}
	
	private static void prikazSviStudenti(JInternalFrame sviStudenti) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 567, 252);
		sviStudenti.getContentPane().add(scrollPane);

		tableStudenti = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			     return false; 
			}
		};
		scrollPane.setViewportView(tableStudenti);
		tableStudenti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Sifra", "Prezime", "Ime", "Semestar"
				}
				));
		tableStudenti.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStudenti.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableStudenti.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableStudenti.getColumnModel().getColumn(3).setPreferredWidth(50);
		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableStudenti.getModel();
		model.setRowCount(0);

		for (MStudent student : GetDbTables.getTableStudenti()) {
			/*
			 * dohvatimo semestar
			 */
			String semestarStr = new String();
			for (MSemestar semestar : GetDbTables.getTableSemestr()) {
				if(student.getSifSemestar() == semestar.getSifSemestar()){
					semestarStr = semestar.getSemestar();
				}
			}
			model.addRow(new Object[]{student.getSifStudent(), student.getPrezStudent(), student.getImeStudent(), semestarStr}); //upisujemo u tabelu
		}
		
		/*
		 * postavljamo mouseListener koji odsluskuje da li se klikce na neki red u tabeli.
		 * Ideja je da kllikom na odredjeni red, da se otvori prozor koji ce editovati odabranog korisnika
		 * tj da bibliotekar moze resetovati negativne bodove.
		 * pristup ovoj opciji ima samo bibliotekar, pa treba o tome voditi racuna
		 */

			tableStudenti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableStudenti.rowAtPoint(evt.getPoint());
					int col = tableStudenti.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
						/*
						 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
						 * koja je ustvari novi prozor za resetovanje negativnih bodova 
						 */
						int sifStud = -1;
						sifStud = (int) tableStudenti.getModel().getValueAt(row, 0);
						resetStudentBodovi(sifStud);
						sviStudenti.dispose();
					}
				}
			});
		
	}

	private static void prikazSviStudentiBibliotekar(JInternalFrame sviStudenti) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 567, 252);
		sviStudenti.getContentPane().add(scrollPane);

		tableStudenti = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			     return false; 
			}
		};
		scrollPane.setViewportView(tableStudenti);
		tableStudenti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Sifra", "Prezime", "Ime", "Semestar"
				}
				));
		tableStudenti.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStudenti.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableStudenti.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableStudenti.getColumnModel().getColumn(3).setPreferredWidth(50);
		/*
		 * Praznimo sve elemente tabele 
		 */
		DefaultTableModel model = (DefaultTableModel) tableStudenti.getModel();
		model.setRowCount(0);

		for (MStudent student : GetDbTables.getTableStudenti()) {
			/*
			 * dohvatimo semestar
			 */
			String semestarStr = new String();
			for (MSemestar semestar : GetDbTables.getTableSemestr()) {
				if(student.getSifSemestar() == semestar.getSifSemestar()){
					semestarStr = semestar.getSemestar();
				}
			}
			model.addRow(new Object[]{student.getSifStudent(), student.getPrezStudent(), student.getImeStudent(), semestarStr}); //upisujemo u tabelu
		}
		
		/*
		 * postavljamo mouseListener koji odsluskuje da li se klikce na neki red u tabeli.
		 * Ideja je da kllikom na odredjeni red, da se otvori prozor koji ce editovati odabranog korisnika
		 * tj da bibliotekar moze resetovati negativne bodove.
		 * pristup ovoj opciji ima samo bibliotekar, pa treba o tome voditi racuna
		 */
		int bibliotekar = -1;
		
		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			if (nast.getSifNastavnik() == LoginGUI.sifNastavnikActive) { //provjeravamo da li je aktivni nastavnik bibliotekar
				bibliotekar = nast.getBibliotekar(); //1 za da, 0 za ne
			}
		}
		
		if(bibliotekar == 1){
			/*
			 * u row i col dobivamo redni broj reda i kolone gdje je kliknuto.
			 */
			tableStudenti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableStudenti.rowAtPoint(evt.getPoint());
					int col = tableStudenti.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
						/*
						 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to u funkciju resetStudentBodovi
						 * koja je ustvari novi prozor za resetovanje negativnih bodova 
						 */
						int sifStud = -1;
						sifStud = (int) tableStudenti.getModel().getValueAt(row, 0);
						System.out.println(sifStud);
						resetStudentBodovi(sifStud);
						sviStudenti.dispose();
					}
				}
			});
		}
	}
		
	private void resetNastavnikBodovi(int sifNast){
		JInternalFrame resetBodoviNast = new JInternalFrame("Izmjena nastavnika", true, true, true);
		resetBodoviNast.setBounds(12, 12, 480, 170); 
		resetBodoviNast.setVisible(true);
		frame.getContentPane().add(resetBodoviNast);
		resetBodoviNast.getContentPane().setLayout(null);
		
		MNastavnik nastavnik = new MNastavnik();
		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			if(nast.getSifNastavnik() == sifNast){
				nastavnik = nast;
				break;
			}
		}
		
		JLabel lblStudent = new JLabel("Nastavnik:");
		lblStudent.setBounds(81, 12, 62, 15);
		resetBodoviNast.getContentPane().add(lblStudent);
		
		JLabel lblStudImePrezime = new JLabel(nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik());
		lblStudImePrezime.setBounds(155, 12, 200, 15);
		resetBodoviNast.getContentPane().add(lblStudImePrezime);
		
		JLabel lblBrojIndexa_1 = new JLabel("Zvanje:");
		lblBrojIndexa_1.setBounds(59, 39, 84, 15);
		resetBodoviNast.getContentPane().add(lblBrojIndexa_1);
		
		JComboBox<String> cmbBoxZvanjeNastavnik = new JComboBox<String>();
		cmbBoxZvanjeNastavnik.setBounds(155, 39, 150, 24);
		resetBodoviNast.getContentPane().add(cmbBoxZvanjeNastavnik);
		cmbBoxZvanjeNastavnik.addItem("Redovan profesor");
		cmbBoxZvanjeNastavnik.addItem("Vanredan profesor");
		cmbBoxZvanjeNastavnik.addItem("Docent profesor");
		cmbBoxZvanjeNastavnik.addItem("Asistent");
		
		JLabel lblPosudjenihKnjiga = new JLabel("Posudjenih knjiga:");
		lblPosudjenihKnjiga.setBounds(12, 66, 131, 15);
		resetBodoviNast.getContentPane().add(lblPosudjenihKnjiga);
		
		JLabel lblBrPosKnj = new JLabel(String.valueOf(nastavnik.getBrPosudjenihKnjiga()));
		lblBrPosKnj.setBounds(155, 66, 200, 15);
		resetBodoviNast.getContentPane().add(lblBrPosKnj);
		
		JLabel lblNegaitivniBodovi = new JLabel("Negaitivni bodovi:");
		lblNegaitivniBodovi.setBounds(16, 93, 127, 15);
		resetBodoviNast.getContentPane().add(lblNegaitivniBodovi);
		
		JLabel lblNegBod = new JLabel(String.valueOf(nastavnik.getNegBodovi()));
		lblNegBod.setBounds(155, 93, 70, 15);
		resetBodoviNast.getContentPane().add(lblNegBod);
		

		JButton btnpromjeniZvanje = new JButton("Promjeni");
		btnpromjeniZvanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DBNastavnik.updateZvanje((String)cmbBoxZvanjeNastavnik.getSelectedItem(), sifNast);
				sviNastavniciBibliotekar();
				resetBodoviNast.dispose();
			}
		});
		btnpromjeniZvanje.setBounds(317, 39, 140, 25); //155, 39, 200, 24
		resetBodoviNast.getContentPane().add(btnpromjeniZvanje);
		
		JButton btnResetBodovi = new JButton("Reset bodova");
		btnResetBodovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DBNastavnik.updateNegBodovi(0, sifNast);
				sviNastavniciBibliotekar();
				resetBodoviNast.dispose();
			}
		});
		btnResetBodovi.setBounds(317, 66, 140, 25);
		resetBodoviNast.getContentPane().add(btnResetBodovi);
		
		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviNastavniciBibliotekar();
				resetBodoviNast.dispose();
			}
		});
		btnPoniti.setBounds(317, 93, 140, 25);
		resetBodoviNast.getContentPane().add(btnPoniti);
	}

	private static void resetStudentBodovi(int sifStud){
		JInternalFrame resetBodoviStud = new JInternalFrame("Predmeti", true, true, true);
		resetBodoviStud.setBounds(12, 12, 377, 188);
		resetBodoviStud.setVisible(true);
		frame.getContentPane().add(resetBodoviStud);
		resetBodoviStud.getContentPane().setLayout(null);
		
		MStudent student = new MStudent();
		for (MStudent st : GetDbTables.getTableStudenti()) {
			if(st.getSifStudent() == sifStud){
				student = st;
				break;
			}
		}
		
		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setBounds(81, 12, 62, 15);
		resetBodoviStud.getContentPane().add(lblStudent);
		
		JLabel lblStudImePrezime = new JLabel(student.getImeStudent() + " " + student.getPrezStudent());
		lblStudImePrezime.setBounds(155, 12, 200, 15);
		resetBodoviStud.getContentPane().add(lblStudImePrezime);
		
		JLabel lblBrojIndexa_1 = new JLabel("Broj indexa:");
		lblBrojIndexa_1.setBounds(59, 39, 84, 15);
		resetBodoviStud.getContentPane().add(lblBrojIndexa_1);
		
		JLabel lblBrIndexStudent = new JLabel(student.getBrIndexa());
		lblBrIndexStudent.setBounds(155, 39, 200, 15);
		resetBodoviStud.getContentPane().add(lblBrIndexStudent);
		
		JLabel lblPosudjenihKnjiga = new JLabel("Posudjenih knjiga:");
		lblPosudjenihKnjiga.setBounds(12, 66, 131, 15);
		resetBodoviStud.getContentPane().add(lblPosudjenihKnjiga);
		
		JLabel lblBrPosKnj = new JLabel(String.valueOf(student.getBrPosudjenihKnjiga()));
		lblBrPosKnj.setBounds(155, 66, 200, 15);
		resetBodoviStud.getContentPane().add(lblBrPosKnj);
		
		JLabel lblNegaitivniBodovi = new JLabel("Negaitivni bodovi:");
		lblNegaitivniBodovi.setBounds(16, 93, 127, 15);
		resetBodoviStud.getContentPane().add(lblNegaitivniBodovi);
		
		JLabel lblNegBod = new JLabel(String.valueOf(student.getNegBodovi()));
		lblNegBod.setBounds(155, 93, 70, 15);
		resetBodoviStud.getContentPane().add(lblNegBod);
		
		JButton btnResetBodovi = new JButton("Reset");
		btnResetBodovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DBStudent.updateNegBodovi(0, sifStud);
				sviStudentiBibliotekar();
				resetBodoviStud.dispose();
			}
		});
		btnResetBodovi.setBounds(255, 88, 100, 25);
		resetBodoviStud.getContentPane().add(btnResetBodovi);
		
		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NastavnikGUI.sviStudenti();
				sviStudentiBibliotekar();
				resetBodoviStud.dispose();
			}
		});
		btnPoniti.setBounds(255, 125, 100, 25);
		resetBodoviStud.getContentPane().add(btnPoniti);
	}

	//dio za bibliotekara

	private void novaKnjiga() {		
		JInternalFrame novaKnjiga = new JInternalFrame("Unos knjige", true, true, true);
		novaKnjiga.setBounds(12, 12, 612, 298);
		novaKnjiga.setVisible(true);
		frame.getContentPane().add(novaKnjiga);
		novaKnjiga.getContentPane().setLayout(null);

		JLabel lblNaslov = new JLabel("Naslov:");
		lblNaslov.setBounds(84, 12, 52, 15);
		novaKnjiga.getContentPane().add(lblNaslov);

		bibliotekarTxtNaslovKnjiga = new JTextField();
		bibliotekarTxtNaslovKnjiga.setText("");
		bibliotekarTxtNaslovKnjiga.setBounds(146, 10, 114, 19);
		novaKnjiga.getContentPane().add(bibliotekarTxtNaslovKnjiga);
		bibliotekarTxtNaslovKnjiga.setColumns(10);

		JLabel lblOriginalniNaslov = new JLabel("Originalni naslov:");
		lblOriginalniNaslov.setBounds(12, 39, 124, 15);
		novaKnjiga.getContentPane().add(lblOriginalniNaslov);

		bibliotekarTxtOrigNaslovKnjiga = new JTextField();
		bibliotekarTxtOrigNaslovKnjiga.setText("");
		bibliotekarTxtOrigNaslovKnjiga.setBounds(146, 37, 114, 19);
		novaKnjiga.getContentPane().add(bibliotekarTxtOrigNaslovKnjiga);
		bibliotekarTxtOrigNaslovKnjiga.setColumns(10);

		JLabel lblBrojStranica = new JLabel("Broj stranica:");
		lblBrojStranica.setBounds(41, 66, 95, 15);
		novaKnjiga.getContentPane().add(lblBrojStranica);

		bibliotekarTxtBrStranicaKnjiga = new JTextField();
		bibliotekarTxtBrStranicaKnjiga.setText("");
		bibliotekarTxtBrStranicaKnjiga.setBounds(146, 64, 114, 19);
		novaKnjiga.getContentPane().add(bibliotekarTxtBrStranicaKnjiga);
		bibliotekarTxtBrStranicaKnjiga.setColumns(10);

		JLabel lblGodinaIzdanja = new JLabel("Godina izdanja:");
		lblGodinaIzdanja.setBounds(25, 93, 111, 15);
		novaKnjiga.getContentPane().add(lblGodinaIzdanja);

		bibliotekarTxtGodIzdanjaKnjiga = new JTextField();
		bibliotekarTxtGodIzdanjaKnjiga.setText("");
		bibliotekarTxtGodIzdanjaKnjiga.setBounds(146, 91, 114, 19);
		novaKnjiga.getContentPane().add(bibliotekarTxtGodIzdanjaKnjiga);
		bibliotekarTxtGodIzdanjaKnjiga.setColumns(10);

		JLabel lblIzdava = new JLabel("Izdavač:");
		lblIzdava.setBounds(78, 120, 58, 15);
		novaKnjiga.getContentPane().add(lblIzdava);

		JComboBox<String> cmbBoxIzdavac = new JComboBox<String>();
		cmbBoxIzdavac.setBounds(146, 115, 114, 24);
		novaKnjiga.getContentPane().add(cmbBoxIzdavac);

		/*
		 * Punimo ComboBox izdavacima koje smo procitai iz BP.
		 */
		for (MIzdavac izdavac : GetDbTables.getTableIzdavaci()) {
			cmbBoxIzdavac.addItem(izdavac.getNazIzdavac());
		}

		JLabel lblVrstaKnjige = new JLabel("Vrsta knjige:");
		lblVrstaKnjige.setBounds(46, 147, 90, 15);
		novaKnjiga.getContentPane().add(lblVrstaKnjige);

		JComboBox<String> CBoxVrstaKnjige = new JComboBox<String>();
		CBoxVrstaKnjige.setBounds(146, 142, 114, 24);
		novaKnjiga.getContentPane().add(CBoxVrstaKnjige);

		for (MVrstaKnjige vrsta : GetDbTables.getTableVrstaKnjige()) {
			CBoxVrstaKnjige.addItem(vrsta.getVrsta());
		}

		JLabel lblIznosNegatvnihBodova = new JLabel("Iznos negatvnih bodova:");
		lblIznosNegatvnihBodova.setBounds(12, 203, 174, 15);
		novaKnjiga.getContentPane().add(lblIznosNegatvnihBodova);

		bibliotekarTxtNegBodoviKnjiga = new JTextField();
		bibliotekarTxtNegBodoviKnjiga.setText("");
		bibliotekarTxtNegBodoviKnjiga.setBounds(196, 201, 64, 19);
		novaKnjiga.getContentPane().add(bibliotekarTxtNegBodoviKnjiga);
		bibliotekarTxtNegBodoviKnjiga.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(278, 12, 11, 206);
		novaKnjiga.getContentPane().add(separator);
	
		/*
		 * Kod autora, ako je u pitanju diplomski rad, onda je prvi autor
		 * kandidat a drugi je mentor. Treba napraviti da se moze ostaviti neka
		 * opcija i neodabrana. Takodje, ako odaberemo autora u prvoj opciji,
		 * taj autor ne treba da se pojavljuje u ostalim opcijama.
		 */

		JLabel lblAutori = new JLabel("Autori:");
		lblAutori.setBounds(301, 12, 70, 15);
		novaKnjiga.getContentPane().add(lblAutori);

		JComboBox<String> cmbBoxAutor1 = new JComboBox<String>();
		cmbBoxAutor1.setBounds(295, 34, 300, 24);
		novaKnjiga.getContentPane().add(cmbBoxAutor1);

		
		final ArrayList<MAutor> autorLista = GetDbTables.getTableAutori();
		final ArrayList<MAutor> autorLista1 = new ArrayList<>(autorLista);

		cmbBoxAutor1.addItem("Prvi autor:");
		for (MAutor autor : autorLista) {
			cmbBoxAutor1.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());
		}

		JComboBox<String> cmbBoxAutor2 = new JComboBox<String>();
		cmbBoxAutor2.setBounds(295, 61, 300, 24);
		cmbBoxAutor2.addItem("Drugi autor:");

		JComboBox<String> cmbBoxAutor3 = new JComboBox<String>();
		cmbBoxAutor3.setBounds(295, 88, 300, 24);
		cmbBoxAutor3.addItem("Treći autor:");

		JComboBox<String> cmbBoxAutor4 = new JComboBox<String>();
		cmbBoxAutor4.setBounds(295, 115, 300, 24);
		cmbBoxAutor4.addItem("Četvti autor:");

		JComboBox<String> cmbBoxAutor5 = new JComboBox<String>();
		cmbBoxAutor5.setBounds(295, 142, 300, 24);
		cmbBoxAutor5.addItem("Peti autor:");

		JComboBox<String> cmbBoxAutor6 = new JComboBox<String>();
		cmbBoxAutor6.setBounds(295, 171, 300, 24);
		cmbBoxAutor6.addItem("Šesti autor:");

		JComboBox<String> cmbBoxAutor7 = new JComboBox<String>();
		cmbBoxAutor7.setBounds(295, 198, 300, 24);
		cmbBoxAutor7.addItem("Sedmi autor:");

		/*
		 * Upisivnje u sve ostale comboBoxove u slucaju da je u prethodnom
		 * odabran neki autor
		 */
		cmbBoxAutor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxAutor1.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor2); 
					cmbBoxAutor2.setVisible(true);

					cmbBoxAutor2.removeAllItems();
					cmbBoxAutor2.addItem("Drugi autor:");
					int p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor())) {
							p = i;
							break;
						}
					}
					if (p != -1)
						autorLista.remove(p);
					for (MAutor autor : autorLista)
						cmbBoxAutor2.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					/*
					 * Brisemo sadrzaj arrayliste
					 */
					autorLista.clear();
					/*
					 * upisujemo opet u tu arraylistu sve elemente iz BP, jer
					 * ako odaberemo neki drugog autora, da se ipak moze
					 * ispisati u sljedecem ComboBoxu taj
					 */
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));

					/*
					 * Ako smo odabrali prvo autora, prikazi combo box za drugog
					 * autora i ako nije odabrano "-----" iz comboBoxa onda
					 * prikazi combo boxzasljedeg autora
					 */

				} else { // ako se ne odabere niko, onda sakriti ostale
					cmbBoxAutor2.setVisible(false);
					cmbBoxAutor3.setVisible(false);
					cmbBoxAutor4.setVisible(false);
					cmbBoxAutor5.setVisible(false);
					cmbBoxAutor6.setVisible(false);
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		cmbBoxAutor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxAutor2.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor3);
					cmbBoxAutor3.setVisible(true);

					cmbBoxAutor3.removeAllItems();
					cmbBoxAutor3.addItem("Treći autor:");
					int p = -1;

					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor2.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					for (MAutor autor : autorLista)
						cmbBoxAutor3.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					autorLista.clear();
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));
				} else {
					cmbBoxAutor3.setVisible(false);
					cmbBoxAutor4.setVisible(false);
					cmbBoxAutor5.setVisible(false);
					cmbBoxAutor6.setVisible(false);
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		cmbBoxAutor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxAutor3.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor4);
					cmbBoxAutor4.setVisible(true);

					cmbBoxAutor4.removeAllItems();
					cmbBoxAutor4.addItem("Četvrti autor:");
					int p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor2.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor3.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					for (MAutor autor : autorLista)
						cmbBoxAutor4.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					autorLista.clear();
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));
				} else {
					cmbBoxAutor4.setVisible(false);
					cmbBoxAutor5.setVisible(false);
					cmbBoxAutor6.setVisible(false);
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		cmbBoxAutor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxAutor4.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor5);
					cmbBoxAutor5.setVisible(true);

					cmbBoxAutor5.removeAllItems();
					cmbBoxAutor5.addItem("Peti autor:");
					int p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor2.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor3.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor4.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					for (MAutor autor : autorLista)
						cmbBoxAutor5.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					autorLista.clear();
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));
				} else {
					cmbBoxAutor5.setVisible(false);
					cmbBoxAutor6.setVisible(false);
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		cmbBoxAutor5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxAutor5.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor6);
					cmbBoxAutor6.setVisible(true);

					cmbBoxAutor6.removeAllItems();
					cmbBoxAutor6.addItem("Šesti autor:");
					int p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor2.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor3.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor4.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor5.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					for (MAutor autor : autorLista)
						cmbBoxAutor6.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					autorLista.clear();
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));
				} else {
					cmbBoxAutor6.setVisible(false);
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		cmbBoxAutor6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbBoxAutor6.getSelectedIndex() > 0) {
					novaKnjiga.getContentPane().add(cmbBoxAutor7);
					cmbBoxAutor7.setVisible(true);
					cmbBoxAutor7.removeAllItems();
					cmbBoxAutor7.addItem("Sedmi autor:");
					int p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor1.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor2.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor3.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor4.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor5.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					p = -1;
					for (int i = 0; i < autorLista.size(); i++) {
						MAutor a = autorLista.get(i);
						if (cmbBoxAutor6.getSelectedItem().equals(a.getImeAutor() + " " + a.getPrezAutor()))
							p = i;
					}
					if (p != -1)
						autorLista.remove(p);

					for (MAutor autor : autorLista)
						cmbBoxAutor7.addItem(autor.getImeAutor() + " " + autor.getPrezAutor());

					autorLista.clear();
					for (int i = 0; i < autorLista1.size(); i++)
						autorLista.add(autorLista1.get(i));
				} else {
					cmbBoxAutor7.setVisible(false);
				}
			}
		});

		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Kada se pritisne dugme treba da upiseu BP sve ovo.
				 */
				MKnjiga knjiga = new MKnjiga();

				knjiga.setNaslov(bibliotekarTxtNaslovKnjiga.getText());
				knjiga.setOrigNaslov(bibliotekarTxtOrigNaslovKnjiga.getText());
				knjiga.setBrStranica(Integer.parseInt(bibliotekarTxtBrStranicaKnjiga.getText()));
				knjiga.setGodIzdanja(Date.valueOf(bibliotekarTxtGodIzdanjaKnjiga.getText() + "-1-1"));
				knjiga.setBrPrimjeraka(0);
				knjiga.setNegBodovi(Integer.parseInt(bibliotekarTxtNegBodoviKnjiga.getText()));
				/*
				 * Da bi unjeli vrstu knige, moramo sljedece uraditi: dohvatimo
				 * indeks odabranog elementa iz comboboxa, ako je ijedan element
				 * odabran. Procitamo vrijednost koja je odabrana, na osnovu nje
				 * pretrazimo tabelu u BP i procitamo odgovarajucu sifru za taj
				 * element, te tu sifru onda upisemo
				 */
				int pom = -1; // sadrzavat ce sifru odabrane vrste knjige
				if (CBoxVrstaKnjige.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana vrsta knjige");
				} else {
					String s = (String) CBoxVrstaKnjige.getSelectedItem();
					/*
					 * Prolazimo kroz sve n-torke i zelimo indeks odabrane.
					 * Imamo koja je odabrana, pa poredimo string s sa svkom
					 * ntorkom.Ako se string s i ta n-torka poklope, zpamtimo
					 * indeks, iskocimo iz petlje i onda taj indeks kasnije
					 * upisemo u Knjigu
					 */
					for (MVrstaKnjige vrsta : GetDbTables.getTableVrstaKnjige()) {
						if (s.equals(vrsta.getVrsta())) {
							pom = vrsta.getSifVrstaKnjige();
							break;
						}
					}
				}

				knjiga.setSifVrstaKnjige(pom);
				/*
				 * ponavljamo gornje za izdavaca
				 */
				pom = -1; // sadrzavat ce sifru odabrane vrste knjige
				if (cmbBoxIzdavac.getSelectedIndex() == -1) {
					System.err.println("Nije odabran izdavac");
				} else {
					String s = (String) cmbBoxIzdavac.getSelectedItem();
					for (MIzdavac izdavac : GetDbTables.getTableIzdavaci()) {
						if (s.equals(izdavac.getNazIzdavac())) {
							pom = izdavac.getSifIzdavac();
							break;
						}
					}
				}

				knjiga.setSifIzdavac(pom);

				/*
				 * Knjigu treba upisati sad u BP
				 */
				try {
					DBKnjiga.insertKnjiga(knjiga);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				/*
				 * Zakljucnosa zadnjom linijom koda smo dodali knjigu u BP. Sada
				 * za odgovarajucu knjigu treba dodati i autore, te r.brojeve
				 * autora
				 */

				MKnjigaAutorRBr knjAutRBr = new MKnjigaAutorRBr();

				if (cmbBoxAutor1.getSelectedIndex() > 0) { 
					knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
					knjAutRBr.setSifAutorRBr(1);

					pom = -1; // sadrzavat ce sifru odabrane vrste knjige
					String s1 = (String) cmbBoxAutor1.getSelectedItem();
					ArrayList<MAutor> autorLista = GetDbTables.getTableAutori();

					for (MAutor autor : autorLista) {
						if (s1.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
							pom = autor.getSifAutor();
							break;
						}
					}
					knjAutRBr.setSifAutor(pom);
					DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);
					/*
					 * ako ima odabran sljedeci autor tj drugi
					 */
					if (cmbBoxAutor2.getSelectedIndex() > 0) {

						knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
						knjAutRBr.setSifAutorRBr(2);

						pom = -1;
						String s2 = (String) cmbBoxAutor2.getSelectedItem(); 
						for (MAutor autor : autorLista) {
							if (s2.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
								pom = autor.getSifAutor();
								break;
							}
						}
						knjAutRBr.setSifAutor(pom);
						DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);

						/*
						 * ako ima odabran sljedeci autor tj treci
						 */
						if (cmbBoxAutor3.getSelectedIndex() > 0) {

							knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
							knjAutRBr.setSifAutorRBr(3);

							pom = -1; 
							String s3 = (String) cmbBoxAutor3.getSelectedItem(); 
							for (MAutor autor : autorLista) {
								if (s3.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
									pom = autor.getSifAutor();
									break;
								}
							}
							knjAutRBr.setSifAutor(pom);
							DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);

							/*
							 * ako ima odabran sljedeci autor tj 4ti
							 */
							if (cmbBoxAutor4.getSelectedIndex() > 0) {

								knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
								knjAutRBr.setSifAutorRBr(4);

								pom = -1; 
								String s4 = (String) cmbBoxAutor4.getSelectedItem(); 

								for (MAutor autor : autorLista) {
									if (s4.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
										pom = autor.getSifAutor();
										break;
									}
								}
								knjAutRBr.setSifAutor(pom);
								DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);

								/*
								 * ako ima odabran sljedeci autor tj 5ti
								 */
								if (cmbBoxAutor5.getSelectedIndex() > 0) {

									knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
									knjAutRBr.setSifAutorRBr(5);

									pom = -1; 
									String s5 = (String) cmbBoxAutor5.getSelectedItem(); 

									for (MAutor autor : autorLista) {
										if (s5.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
											pom = autor.getSifAutor();
											break;
										}
									}
									knjAutRBr.setSifAutor(pom);
									DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);

									/*
									 * ako ima odabran sljedeci autor tj 6ti
									 */
									if (cmbBoxAutor6.getSelectedIndex() > 0) {

										knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
										knjAutRBr.setSifAutorRBr(6);

										pom = -1; 
										String s6 = (String) cmbBoxAutor6.getSelectedItem(); 

										for (MAutor autor : autorLista) {
											if (s6.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
												pom = autor.getSifAutor();
												break;
											}
										}
										knjAutRBr.setSifAutor(pom);
										DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);

										/*
										 * ako ima odabran sljedeci autor tj 5ti
										 */
										if (cmbBoxAutor7.getSelectedIndex() > 0) {

											knjAutRBr.setSifKnjiga(knjiga.getSifKnjiga());
											knjAutRBr.setSifAutorRBr(7);

											pom = -1;
											String s7 = (String) cmbBoxAutor7.getSelectedItem();

											for (MAutor autor : autorLista) {
												if (s7.equals(autor.getImeAutor() + " " + autor.getPrezAutor())) {
													pom = autor.getSifAutor();
													break;
												}
											}
											knjAutRBr.setSifAutor(pom);
											DBKnjigaAutorRBr.insertKnjigaAutorRBr(knjAutRBr);
										}
									}
								}
							}
						}
					}
				}
			}
		});
		btnPotvrdiUnos.setBounds(217, 232, 125, 25);
		novaKnjiga.getContentPane().add(btnPotvrdiUnos);
	}
	
	private void noviIzdavac() {
		JInternalFrame noviIzdavac = new JInternalFrame("Unos izdavaca", true, true, true);
		noviIzdavac.setBounds(12, 12, 386, 109);
		noviIzdavac.setVisible(true);
		frame.getContentPane().add(noviIzdavac);
		noviIzdavac.getContentPane().setLayout(null);

		JLabel lblIzdava_1 = new JLabel("Izdavač:");
		lblIzdava_1.setBounds(12, 12, 58, 15);
		noviIzdavac.getContentPane().add(lblIzdava_1);

		bibliotekarTxtIzdavac = new JTextField();
		bibliotekarTxtIzdavac.setText("");
		bibliotekarTxtIzdavac.setBounds(88, 10, 281, 19);
		noviIzdavac.getContentPane().add(bibliotekarTxtIzdavac);
		bibliotekarTxtIzdavac.setColumns(10);

		JButton btnPotvrdiUnos_1 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MIzdavac izdavac = new MIzdavac();
				izdavac.setNazIzdavac(bibliotekarTxtIzdavac.getText());
				DBIzdavac.insertIzdavac(izdavac);
			}
		});
		btnPotvrdiUnos_1.setBounds(88, 41, 130, 25);
		noviIzdavac.getContentPane().add(btnPotvrdiUnos_1);

	}

	private void noviNastavnik() {
		JInternalFrame noviNastavnik = new JInternalFrame("Unos nastavnika", true, true, true);
		noviNastavnik.setBounds(12, 12, 320, 218);
		noviNastavnik.setVisible(true);
		frame.getContentPane().add(noviNastavnik);
		noviNastavnik.getContentPane().setLayout(null);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(64, 14, 30, 15);
		noviNastavnik.getContentPane().add(lblIme);

		bibliotekarTxtImeNastavnik = new JTextField();
		bibliotekarTxtImeNastavnik.setBounds(98, 12, 200, 19);
		noviNastavnik.getContentPane().add(bibliotekarTxtImeNastavnik);
		bibliotekarTxtImeNastavnik.setColumns(10);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(32, 41, 62, 15);
		noviNastavnik.getContentPane().add(lblPrezime);

		bibliotekarTxtPrezimeNastavnik = new JTextField();
		bibliotekarTxtPrezimeNastavnik.setBounds(98, 39, 200, 19);
		noviNastavnik.getContentPane().add(bibliotekarTxtPrezimeNastavnik);
		bibliotekarTxtPrezimeNastavnik.setColumns(10);

		JLabel lblZvanje = new JLabel("Zvanje:");
		lblZvanje.setBounds(41, 68, 53, 15);
		noviNastavnik.getContentPane().add(lblZvanje);

		JComboBox<String> cmbBoxZvanjeNastavnik = new JComboBox<String>();
		cmbBoxZvanjeNastavnik.setBounds(98, 63, 200, 24);
		noviNastavnik.getContentPane().add(cmbBoxZvanjeNastavnik);
		cmbBoxZvanjeNastavnik.addItem("Redovan profesor");
		cmbBoxZvanjeNastavnik.addItem("Vanredan profesor");
		cmbBoxZvanjeNastavnik.addItem("Docent profesor");
		cmbBoxZvanjeNastavnik.addItem("Asistent");

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(19, 95, 75, 15);
		noviNastavnik.getContentPane().add(lblPassword);

		bibliotekarTxtPasswordNastavnik = new JTextField();
		bibliotekarTxtPasswordNastavnik.setBounds(98, 99, 200, 19);
		noviNastavnik.getContentPane().add(bibliotekarTxtPasswordNastavnik);
		bibliotekarTxtPasswordNastavnik.setColumns(10);

		JLabel lblBibliotekar = new JLabel("Bibliotekar:");
		lblBibliotekar.setBounds(12, 122, 82, 15);
		noviNastavnik.getContentPane().add(lblBibliotekar);

		JRadioButton opcijaDa = new JRadioButton("Da");
		opcijaDa.setBounds(98, 118, 50, 23);
		noviNastavnik.getContentPane().add(opcijaDa);

		JRadioButton opcijaNe = new JRadioButton("Ne");
		opcijaNe.setBounds(152, 118, 149, 23);
		noviNastavnik.getContentPane().add(opcijaNe);

		ButtonGroup group = new ButtonGroup();
		group.add(opcijaDa);
		group.add(opcijaNe);

		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MNastavnik nastavnik = new MNastavnik();

				nastavnik.setImeNastavnik(bibliotekarTxtImeNastavnik.getText());
				nastavnik.setPrezNastavnik(bibliotekarTxtPrezimeNastavnik.getText());
				String s = (String) cmbBoxZvanjeNastavnik.getSelectedItem();
				
				nastavnik.setZvanje(s);
				nastavnik.setNegBodovi(0);
				nastavnik.setPassword(bibliotekarTxtPasswordNastavnik.getText());
				/*
				 * provjera koju oopciju za bibiliotekara smo odabrali
				 */
				int pom = -1;
				if (opcijaDa.isSelected())
					pom = 1;
				if (opcijaNe.isSelected())
					pom = 0;
				nastavnik.setBibliotekar(pom);

				DBNastavnik.insertNastavnik(nastavnik);
			}
		});
		btnPotvrdiUnos.setBounds(98, 149, 150, 25);
		noviNastavnik.getContentPane().add(btnPotvrdiUnos);

	}

	private void noviStudent() {
		JInternalFrame noviStudent = new JInternalFrame("Unos studenta", true, true, true);
		noviStudent.setBounds(12, 12, 320, 218);
		noviStudent.setVisible(true);
		frame.getContentPane().add(noviStudent);
		noviStudent.getContentPane().setLayout(null);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(64, 14, 30, 15);
		noviStudent.getContentPane().add(lblIme);

		bibliotekarTxtImeStudent = new JTextField();
		bibliotekarTxtImeStudent.setBounds(98, 12, 200, 19);
		noviStudent.getContentPane().add(bibliotekarTxtImeStudent);
		bibliotekarTxtImeStudent.setColumns(10);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(32, 41, 62, 15);
		noviStudent.getContentPane().add(lblPrezime);

		bibliotekarTxtPrezimeStudent = new JTextField();
		bibliotekarTxtPrezimeStudent.setBounds(98, 39, 200, 19);
		noviStudent.getContentPane().add(bibliotekarTxtPrezimeStudent);
		bibliotekarTxtPrezimeStudent.setColumns(10);

		JLabel lblBrIndexa = new JLabel("Broj indexa:");
		lblBrIndexa.setBounds(10, 68, 84, 15);
		noviStudent.getContentPane().add(lblBrIndexa);

		bibliotekarTxtBrIndexa = new JTextField();
		bibliotekarTxtBrIndexa.setBounds(98, 67, 200, 19);
		noviStudent.getContentPane().add(bibliotekarTxtBrIndexa);
		bibliotekarTxtBrIndexa.setColumns(10);

		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(21, 122, 73, 15);
		noviStudent.getContentPane().add(lblSemestar);

		JComboBox<String> cmbBoxSemestar = new JComboBox<String>();
		cmbBoxSemestar.setBounds(98, 117, 200, 24);
		noviStudent.getContentPane().add(cmbBoxSemestar);

		/*
		 * punimo comboBox semestrima
		 */
		for (MSemestar semestar : GetDbTables.getTableSemestr()) {
			cmbBoxSemestar.addItem(semestar.getSemestar());
		}

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(19, 95, 75, 15);
		noviStudent.getContentPane().add(lblPassword);

		bibliotekarTxtPasswordStudent = new JTextField();
		bibliotekarTxtPasswordStudent.setBounds(98, 93, 200, 19);
		noviStudent.getContentPane().add(bibliotekarTxtPasswordStudent);
		bibliotekarTxtPasswordStudent.setColumns(10);

		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MStudent student = new MStudent();

				student.setImeStudent(bibliotekarTxtImeStudent.getText());
				student.setPrezStudent(bibliotekarTxtPrezimeStudent.getText());
				student.setBrIndexa(bibliotekarTxtBrIndexa.getText());
				student.setNegBodovi(0);
				student.setPassword(bibliotekarTxtPasswordStudent.getText());

				/*
				 * Da bi unjeli vrstu knige, moramo sljedece uraditi: dohvatimo
				 * indeks odabranog elementa iz comboboxa, ako je ijedan element
				 * odabran. Procitamo vrijednost koja je odabrana, na osnovu nje
				 * pretrazimo tabelu u BP i procitamo odgovarajucu sifru za taj
				 * element, te tu sifru onda upisemo
				 */
				int pom = -1; // sadrzavat ce sifru odabrane vrste knjige
				if (cmbBoxSemestar.getSelectedIndex() == -1) {
					System.err.println("Nije odabran semestar");
				} else {
					String s = (String) cmbBoxSemestar.getSelectedItem();

					for (MSemestar semestar : GetDbTables.getTableSemestr()) {
						if (s.equals(semestar.getSemestar())) {
							pom = semestar.getSifSemestar();
							break;
						}
					}
				}

				student.setSifSemestar(pom);

				DBStudent.insertStudent(student);
			}
		});
		btnPotvrdiUnos.setBounds(98, 149, 150, 25);
		noviStudent.getContentPane().add(btnPotvrdiUnos);
	}

	private void noviPredmet() {
		JInternalFrame noviPredmet = new JInternalFrame("Unos predmeta", true, true, true);
		noviPredmet.setBounds(12, 122, 479, 189);
		noviPredmet.setVisible(true);
		frame.getContentPane().add(noviPredmet);
		noviPredmet.getContentPane().setLayout(null);

		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(75, 12, 65, 15);
		noviPredmet.getContentPane().add(lblPredmet);

		bibliotekarTxtNazpredmet = new JTextField();
		bibliotekarTxtNazpredmet.setBounds(158, 10, 300, 19);
		noviPredmet.getContentPane().add(bibliotekarTxtNazpredmet);
		bibliotekarTxtNazpredmet.setColumns(10);

		JLabel lblKraticaPredmeta = new JLabel("Kratica predmeta:");
		lblKraticaPredmeta.setBounds(12, 39, 128, 15);
		noviPredmet.getContentPane().add(lblKraticaPredmeta);

		bibliotekarTxtKratpredmet = new JTextField();
		bibliotekarTxtKratpredmet.setBounds(158, 37, 300, 19);
		noviPredmet.getContentPane().add(bibliotekarTxtKratpredmet);
		bibliotekarTxtKratpredmet.setColumns(10);

		JLabel lblSemestar_2 = new JLabel("Semestar:");
		lblSemestar_2.setBounds(67, 66, 73, 15);
		noviPredmet.getContentPane().add(lblSemestar_2);

		JComboBox<String> cmbBoxSemestar = new JComboBox<String>();
		cmbBoxSemestar.setBounds(158, 61, 300, 24);
		noviPredmet.getContentPane().add(cmbBoxSemestar);

		/*
		 * Punimo combo box semestrima iz BP
		 */

		for (MSemestar semestar : GetDbTables.getTableSemestr()) {
			cmbBoxSemestar.addItem(semestar.getSemestar());
		}

		JLabel lblNastavnik = new JLabel("Nastavnik:");
		lblNastavnik.setBounds(65, 93, 75, 15);
		noviPredmet.getContentPane().add(lblNastavnik);

		JComboBox<String> cmbBoxNastavnik = new JComboBox<String>();
		cmbBoxNastavnik.setBounds(158, 88, 300, 24);
		noviPredmet.getContentPane().add(cmbBoxNastavnik);

		/*
		 * Punimo combo box nastavnicima iz BP
		 */
		for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
			cmbBoxNastavnik.addItem(nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik());
		}

		JButton btnPotvrdiUnos_3 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MPredmet predmet = new MPredmet();

				predmet.setNazPredmet(bibliotekarTxtNazpredmet.getText());
				predmet.setKratPredmet(bibliotekarTxtKratpredmet.getText());
				/*
				 * odabir koji semestar
				 */
				int pom = -1;
				if (cmbBoxSemestar.getSelectedIndex() == -1) {
					System.err.println("Nije odabran semestar");
				} else {
					String s = (String) cmbBoxSemestar.getSelectedItem();

					for (MSemestar semestar : GetDbTables.getTableSemestr()) {
						if (s.equals(semestar.getSemestar())) {
							pom = semestar.getSifSemestar();
							break;
						}
					}
				}
				predmet.setSifSemestar(pom);
				/*
				 * odabir nastavnika
				 */
				pom = -1;
				if (cmbBoxNastavnik.getSelectedIndex() == -1) {
					System.err.println("Nije odabran nastavnik");
				} else {
					String s = (String) cmbBoxNastavnik.getSelectedItem();

					for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
						if (s.equals(nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik())) {
							pom = nastavnik.getSifNastavnik();
							break;
						}
					}
				}
				predmet.setSifnastavnik(pom);

				/*
				 * upisivanje predmeta u BP
				 */
				DBPredmet.insertPredmet(predmet);

			}
		});
		btnPotvrdiUnos_3.setBounds(240, 124, 130, 25);
		noviPredmet.getContentPane().add(btnPotvrdiUnos_3);
	}

	private void noviAutor() {
		JInternalFrame noviAutor = new JInternalFrame("Unos autora", true, true, true);
		noviAutor.setBounds(12, 12, 321, 386);
		noviAutor.setVisible(true);
		frame.getContentPane().add(noviAutor);
		noviAutor.getContentPane().setLayout(null);

		JLabel lblIme_1 = new JLabel("Ime:");
		lblIme_1.setBounds(44, 36, 30, 15);
		noviAutor.getContentPane().add(lblIme_1);

		bibliotekarTxtImeAutora = new JTextField();
		bibliotekarTxtImeAutora.setBounds(92, 34, 207, 19);
		noviAutor.getContentPane().add(bibliotekarTxtImeAutora);
		bibliotekarTxtImeAutora.setColumns(10);

		JLabel lblPrezime_1 = new JLabel("Prezime:");
		lblPrezime_1.setBounds(12, 63, 62, 15);
		noviAutor.getContentPane().add(lblPrezime_1);

		bibliotekarTxtPrezimeAutora = new JTextField();
		bibliotekarTxtPrezimeAutora.setBounds(92, 61, 207, 19);
		noviAutor.getContentPane().add(bibliotekarTxtPrezimeAutora);
		bibliotekarTxtPrezimeAutora.setColumns(10);

		JButton btnPotvrdiUnos_5 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAutor autor = new MAutor();
				autor.setImeAutor(bibliotekarTxtImeAutora.getText());
				autor.setPrezAutor(bibliotekarTxtPrezimeAutora.getText());
				DBAutor.insertAutor(autor);
			}
		});
		btnPotvrdiUnos_5.setBounds(91, 92, 130, 25);
		noviAutor.getContentPane().add(btnPotvrdiUnos_5);
		
		JLabel lblUnosNovogAutora = new JLabel("Unos novog autora:");
		lblUnosNovogAutora.setBounds(12, 9, 150, 15);
		noviAutor.getContentPane().add(lblUnosNovogAutora);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 129, 287, 2);
		noviAutor.getContentPane().add(separator);
		
		JLabel lblOdabirNastavnikaKao = new JLabel("Odabir nastavnika kao autora:");
		lblOdabirNastavnikaKao.setBounds(12, 143, 287, 15);
		noviAutor.getContentPane().add(lblOdabirNastavnikaKao);
		
		JComboBox<String> cmbBoxNastAutor = new JComboBox<String>();
		cmbBoxNastAutor.setBounds(12, 170, 287, 24);
		noviAutor.getContentPane().add(cmbBoxNastAutor);
		
		for (MNastavnik mNastavnik : GetDbTables.getTableNastavnici()) {
			cmbBoxNastAutor.addItem(mNastavnik.getImeNastavnik() + " " + mNastavnik.getPrezNastavnik());
		}
		
		JButton btnPotvrdiUnosNastAutor = new JButton("Potvrdi unos");
		btnPotvrdiUnosNastAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxNastAutor.getSelectedIndex() == -1) {
					System.err.println("Nije odabran nastavnik");
				} else {
					String s = (String) cmbBoxNastAutor.getSelectedItem();
					String[] imeIPrezime = s.split(" ");
					
					MAutor autor = new MAutor();
					autor.setImeAutor(imeIPrezime[0]);
					autor.setPrezAutor(imeIPrezime[1]);
					DBAutor.insertAutor(autor);
				}
			}
		});
		btnPotvrdiUnosNastAutor.setBounds(92, 206, 129, 25);
		noviAutor.getContentPane().add(btnPotvrdiUnosNastAutor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 243, 287, 2);
		noviAutor.getContentPane().add(separator_1);
		
		JLabel lblOdabirStudentaKao = new JLabel("Odabir studenta kao autora:");
		lblOdabirStudentaKao.setBounds(12, 257, 287, 15);
		noviAutor.getContentPane().add(lblOdabirStudentaKao);
		
		JComboBox<String> cmbBoxStudAutor = new JComboBox<String>();
		cmbBoxStudAutor.setBounds(12, 284, 287, 24);
		noviAutor.getContentPane().add(cmbBoxStudAutor);
		
		for (MStudent mStudent : GetDbTables.getTableStudenti()) {
			cmbBoxStudAutor.addItem(mStudent.getImeStudent() + " " + mStudent.getPrezStudent());
		}
		
		JButton btnPotrvrdiUnosStudAutor = new JButton("Potrvrdi unos");
		btnPotrvrdiUnosStudAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbBoxStudAutor.getSelectedIndex() == -1) {
					System.err.println("Nije odabran nastavnik");
				} else {
					String s = (String) cmbBoxStudAutor.getSelectedItem();
					String[] imeIPrezime = s.split(" ");
					
					MAutor autor = new MAutor();
					autor.setImeAutor(imeIPrezime[0]);
					autor.setPrezAutor(imeIPrezime[1]);
					DBAutor.insertAutor(autor);
				}
			}
		});
		btnPotrvrdiUnosStudAutor.setBounds(92, 320, 130, 25);
		noviAutor.getContentPane().add(btnPotrvrdiUnosStudAutor);
	}

	private void noviPrimjerak() {
		JInternalFrame noviPrimjerak = new JInternalFrame("Novi primjerak knjige", true, true, true);
		noviPrimjerak.setBounds(33, 12, 367, 195);
		noviPrimjerak.setVisible(true);
		frame.getContentPane().add(noviPrimjerak);
		noviPrimjerak.getContentPane().setLayout(null);

		JLabel lblKnjiga = new JLabel("Knjiga:");
		lblKnjiga.setBounds(78, 12, 49, 15);
		noviPrimjerak.getContentPane().add(lblKnjiga);

		JComboBox<String> cmbPrimjerakKnjiga = new JComboBox<String>();
		cmbPrimjerakKnjiga.setBounds(145, 7, 200, 24);
		noviPrimjerak.getContentPane().add(cmbPrimjerakKnjiga);

		for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {
			cmbPrimjerakKnjiga.addItem(knjiga.getOrigNaslov());
		}

		JLabel lblDatumNabavke = new JLabel("Datum nabavke:");
		lblDatumNabavke.setBounds(12, 43, 115, 15);
		noviPrimjerak.getContentPane().add(lblDatumNabavke);

		/*
		 * Dodavaje Date Pickera
		 */
		UtilDateModel modelDate = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mjesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		noviPrimjerak.getContentPane().add(datePicker);
		datePicker.setBounds(145, 35, 200, 25);

		JLabel lblStanjeKnjige = new JLabel("Stanje knjige:");
		lblStanjeKnjige.setBounds(29, 70, 98, 15);
		noviPrimjerak.getContentPane().add(lblStanjeKnjige);

		JComboBox<String> cmbBoxStanjeKnjige = new JComboBox<String>();
		cmbBoxStanjeKnjige.setBounds(145, 65, 200, 24);
		noviPrimjerak.getContentPane().add(cmbBoxStanjeKnjige);

		cmbBoxStanjeKnjige.addItem("Odlično");
		cmbBoxStanjeKnjige.addItem("Vrlo dobro");
		cmbBoxStanjeKnjige.addItem("Dobro");
		cmbBoxStanjeKnjige.addItem("Zadovoljavajuće");
		cmbBoxStanjeKnjige.addItem("Loše");

		JLabel lblInventarniBroj = new JLabel("Inventarni broj:");
		lblInventarniBroj.setBounds(18, 97, 109, 15);
		noviPrimjerak.getContentPane().add(lblInventarniBroj);

		bibliotekarTxtInvBroj = new JTextField();
		bibliotekarTxtInvBroj.setBounds(145, 95, 200, 19);
		noviPrimjerak.getContentPane().add(bibliotekarTxtInvBroj);
		bibliotekarTxtInvBroj.setColumns(10);

		JButton btnPotvrdiUnos_8 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Kada se potvrdi unos knjige, treba sljedece odraditi: 1.
				 * potraziti tu knjigu u BP i povecati brPrimjeraka za 1, a onda
				 * upisati primjerak u BP sa svojim elementima
				 * 
				 * 1. prvo da povećamo brojPrimjeraka u BP tabeli knjiga.
				 * Dohvatimo odgovarajucu knjigu, i update-ujemo br primjeraka
				 * te n-torke
				 */
				String primjerakKnjiga = (String) cmbPrimjerakKnjiga.getSelectedItem(); 
								
				int pomSifKnjiga = -1; 
				int pomBrPrimjeraka = -1;
				for (MKnjiga knjiga : GetDbTables.getTableKnjige()) { 
					if (knjiga.getOrigNaslov().equals(primjerakKnjiga)) {
						pomSifKnjiga = knjiga.getSifKnjiga();
						pomBrPrimjeraka = knjiga.getBrPrimjeraka();
						break;
					}
				}
				pomBrPrimjeraka++; // povecvamo broj primjeraka za 1
				DBKnjiga.updateBrPrimjeraka(pomSifKnjiga, pomBrPrimjeraka); 
				/*
				 * Sada upisujemo konkretne primjerke u BP.
				 */
				MPrimjerak primjerak = new MPrimjerak();
				primjerak.setInventartniBr(bibliotekarTxtInvBroj.getText());

				java.util.Date utilDate = (java.util.Date) datePicker.getModel().getValue();
				Date datNabavke = new Date(utilDate.getTime());
				primjerak.setDatumNabavke(datNabavke);

				primjerak.setStanje((String) cmbBoxStanjeKnjige.getSelectedItem());
				primjerak.setSifKnjiga(pomSifKnjiga);

				DBPrimjerak.insertPredmet(primjerak);
			}
		});
		btnPotvrdiUnos_8.setBounds(145, 126, 130, 25);
		noviPrimjerak.getContentPane().add(btnPotvrdiUnos_8);

	}

	private void rezervacijeStudent() {
		JInternalFrame rezervacije = new JInternalFrame("Rezervacije studenata", true, true, true);
		rezervacije.setBounds(12, 12, 799, 500);
		rezervacije.setVisible(true);
		frame.getContentPane().add(rezervacije);
		rezervacije.getContentPane().setLayout(null);
		
		JLabel lblRezervacijeStudenata = new JLabel("Pretraga studenata:");
		lblRezervacijeStudenata.setBounds(12, 12, 164, 15);
		rezervacije.getContentPane().add(lblRezervacijeStudenata);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 765, 417);
		rezervacije.getContentPane().add(scrollPane);
		
		tableRezervacijeStudenata = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableRezervacijeStudenata);
		
		tableRezervacijeStudenata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sif.rezerv.", "Br.indeksa", "Student", "Knjiga", "Inv.br", "Datum rezervacije", "Datum vracanja"
			}
		));
		tableRezervacijeStudenata.getColumnModel().getColumn(2).setPreferredWidth(158);
		tableRezervacijeStudenata.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableRezervacijeStudenata.getColumnModel().getColumn(4).setPreferredWidth(59);
		tableRezervacijeStudenata.getColumnModel().getColumn(5).setPreferredWidth(113);
		tableRezervacijeStudenata.getColumnModel().getColumn(6).setPreferredWidth(106);
		
		DefaultTableModel modelRezervStud = (DefaultTableModel) tableRezervacijeStudenata.getModel();
		modelRezervStud.setRowCount(0);
		
		for (MRezervacijaPrimjerakStudent rps : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
			MStudent student = GetDbTables.getStudentBySifra(rps.getSifStudent());
			MRezervacija rezervacijaStud = GetDbTables.getRezervacijaBySifra(rps.getSifRezervacija());
			MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rps.getSifPrimjerak());
			MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());
			// upisivanje tabele
			if (rezervacijaStud.getOdobrena() == 0)
				modelRezervStud.addRow(new Object[] { 
						rps.getSifRezPrimStud(), 
						student.getBrIndexa(), 
						student.getImeStudent() + " " + student.getPrezStudent(), 
						knjiga.getOrigNaslov(), 
						primjerak.getInventartniBr(), 
						rezervacijaStud.getDatRezervacija(),
						rezervacijaStud.getDatVracanja() }); 
		}
		
		tableRezervacijeStudenata.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableRezervacijeStudenata.rowAtPoint(evt.getPoint());
				int col = tableRezervacijeStudenata.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj
					 * vrijednost za sifStdenta, i prosljedi to u funkciju
					 * resetStudentBodovi koja je ustvari novi prozor za
					 * resetovanje negativnih bodova
					 */
					int sifRezPrimjStud = (int) tableRezervacijeStudenata.getModel().getValueAt(tableRezervacijeStudenata.convertRowIndexToModel(row), 0);

					odobriRezervacijuStud(sifRezPrimjStud);
					rezervacije.dispose();
				}
			}
		});
		
		txtFilterrezervacijestudent = new JTextField();
		txtFilterrezervacijestudent.setBounds(194, 12, 200, 19);
		rezervacije.getContentPane().add(txtFilterrezervacijestudent);
		txtFilterrezervacijestudent.setColumns(10);
		
		filterTableByColumn(tableRezervacijeStudenata, 1, txtFilterrezervacijestudent);
		JRadioButton rdbtnBrIndexa = new JRadioButton("Br. Indexa");
		rdbtnBrIndexa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableRezervacijeStudenata, 1, txtFilterrezervacijestudent);
			}
		});
		rdbtnBrIndexa.setSelected(true);
		rdbtnBrIndexa.setBounds(402, 12, 96, 23);
		rezervacije.getContentPane().add(rdbtnBrIndexa);
		
		JRadioButton rdbtnImeIPrezime = new JRadioButton("Ime i prezime");
		rdbtnImeIPrezime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableRezervacijeStudenata, 2, txtFilterrezervacijestudent);
			}
		});
		rdbtnImeIPrezime.setBounds(502, 12, 119, 23);
		rezervacije.getContentPane().add(rdbtnImeIPrezime);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBrIndexa);
		group.add(rdbtnImeIPrezime);
		
	}
	
	private void rezervacijeNastavnik() {
		JInternalFrame rezervacije = new JInternalFrame("Rezervacije nastavnika", true, true, true);
		rezervacije.setBounds(12, 12, 799, 500);
		rezervacije.setVisible(true);
		frame.getContentPane().add(rezervacije);
		rezervacije.getContentPane().setLayout(null);
		
		JLabel lblRezervacijeStudenata = new JLabel("Pretraga nastavnika:");
		lblRezervacijeStudenata.setBounds(12, 12, 150, 15);
		rezervacije.getContentPane().add(lblRezervacijeStudenata);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 765, 417);
		rezervacije.getContentPane().add(scrollPane);
		
		tableRezervacijeNastavnika = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableRezervacijeNastavnika);
		
		tableRezervacijeNastavnika.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sif.rezerv.", "Nastavnik", "Knjiga", "Inv.br", "Datum rezervacije", "Datum vracanja"
			}
		));
		tableRezervacijeNastavnika.getColumnModel().getColumn(1).setPreferredWidth(158);
		tableRezervacijeNastavnika.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableRezervacijeNastavnika.getColumnModel().getColumn(3).setPreferredWidth(59);
		tableRezervacijeNastavnika.getColumnModel().getColumn(4).setPreferredWidth(113);
		tableRezervacijeNastavnika.getColumnModel().getColumn(5).setPreferredWidth(106);
		
		DefaultTableModel modelRezervNast = (DefaultTableModel) tableRezervacijeNastavnika.getModel();
		modelRezervNast.setRowCount(0);
		
		for (MRezervacijaPrimjerakNastavnik rps : GetDbTables.getTableRezervacijaPrimjerakNastavnik()) {
			MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rps.getSifNastavnik());
			MRezervacija rezervacijaNast = GetDbTables.getRezervacijaBySifra(rps.getSifRezervacija());
			MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rps.getSifPrimjerak());
			MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());
			// upisivanje tabele
			if (rezervacijaNast.getOdobrena() == 0)
				modelRezervNast.addRow(new Object[] { 
						rps.getSifRezPrimNast(),
						nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik(), 
						knjiga.getOrigNaslov(), 
						primjerak.getInventartniBr(), 
						rezervacijaNast.getDatRezervacija(),
						rezervacijaNast.getDatVracanja() }); 
		}
		
		tableRezervacijeNastavnika.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tableRezervacijeNastavnika.rowAtPoint(evt.getPoint());
				int col = tableRezervacijeNastavnika.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					int sifRezPrimjNast = (int) tableRezervacijeNastavnika.getModel().getValueAt(tableRezervacijeNastavnika.convertRowIndexToModel(row), 0);
					odobriRezervacijuNast(sifRezPrimjNast);
					rezervacije.dispose();
				}
			}
		});
		
		txtFilterrezervacijeNast = new JTextField();
		txtFilterrezervacijeNast.setBounds(180, 12, 200, 19);
		rezervacije.getContentPane().add(txtFilterrezervacijeNast);
		txtFilterrezervacijeNast.setColumns(10);
		
		filterTableByColumn(tableRezervacijeNastavnika, 1, txtFilterrezervacijeNast);
		
		
	}

	private void odobriRezervacijuStud(int sifRezPrimjStud) {
		JInternalFrame odobriRezStud = new JInternalFrame("Odobravanje rezervacije", true, true, true);
		odobriRezStud.setBounds(12, 12, 403, 221);
		odobriRezStud.setVisible(true);
		frame.getContentPane().add(odobriRezStud);
		odobriRezStud.getContentPane().setLayout(null);

		/*
		 * treba dohvatiti primjerak, knjigu, rezervaciju, studenta. To
		 * dohvatamo na osnovu tabele RezervacijaPrimjerakStudent, a u ovaj
		 * metod nam je prosljedjena sifra za odabranu rezervaciju koju trebamo
		 * odobriti ili ponistiti.
		 */
		
		MRezervacijaPrimjerakStudent rps = GetDbTables.getRezPrimStudBySifra(sifRezPrimjStud);
		MStudent student = GetDbTables.getStudentBySifra(rps.getSifStudent());
		MRezervacija rezervacija = GetDbTables.getRezervacijaBySifra(rps.getSifRezervacija());
		MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rps.getSifPrimjerak());
		MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());

		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setBounds(106, 12, 62, 15);
		odobriRezStud.getContentPane().add(lblStudent);

		JLabel lblImePrezStud = new JLabel(student.getImeStudent() + " " + student.getPrezStudent());
		lblImePrezStud.setBounds(180, 12, 200, 15);
		odobriRezStud.getContentPane().add(lblImePrezStud);

		JLabel lblKnjiga_1 = new JLabel("Knjiga:");
		lblKnjiga_1.setBounds(119, 39, 49, 15);
		odobriRezStud.getContentPane().add(lblKnjiga_1);

		JLabel lblKnjigaNaslov = new JLabel(knjiga.getOrigNaslov());
		lblKnjigaNaslov.setBounds(180, 39, 200, 15);
		odobriRezStud.getContentPane().add(lblKnjigaNaslov);

		JLabel lblInventarniBrojKnjige = new JLabel("Inventarni broj knjige:");
		lblInventarniBrojKnjige.setBounds(12, 66, 156, 15);
		odobriRezStud.getContentPane().add(lblInventarniBrojKnjige);

		JLabel lblInvBrKnj = new JLabel(primjerak.getInventartniBr());
		lblInvBrKnj.setBounds(180, 66, 200, 15);
		odobriRezStud.getContentPane().add(lblInvBrKnj);

		JLabel lblDatumRezervacije = new JLabel("Datum rezervacije:");
		lblDatumRezervacije.setBounds(35, 93, 133, 15);
		odobriRezStud.getContentPane().add(lblDatumRezervacije);

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String datRezervStr = df.format(rezervacija.getDatRezervacija());
		JLabel lblDatRezerv = new JLabel(datRezervStr);
		lblDatRezerv.setBounds(180, 93, 200, 15);
		odobriRezStud.getContentPane().add(lblDatRezerv);

		JLabel lblDatumVraanja = new JLabel("Datum vraćanja:");
		lblDatumVraanja.setBounds(53, 120, 115, 15);
		odobriRezStud.getContentPane().add(lblDatumVraanja);

		String datVracStr = df.format(rezervacija.getDatVracanja());
		JLabel lblDatVracanja = new JLabel(datVracStr);
		lblDatVracanja.setBounds(180, 120, 200, 15);
		odobriRezStud.getContentPane().add(lblDatVracanja);

		JButton btnIzdajKnjigu = new JButton("Izdaj knjigu");
		btnIzdajKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * rez nam treba sifra primjerka, sifra posudbe, te sifra
				 * studenta. Takodje trebamo unjet u tabelu posudba,i to kad je
				 * knjiga posudjena (tj kad je odobrena posudba), kad treba biti
				 * vracena, i kad je ustvari vracena knjiga
				 */
				MRezervacijaPrimjerakStudent rps = GetDbTables.getRezPrimStudBySifra(sifRezPrimjStud);
				/*
				 * Treba odgovarajuci primjerak oznaciti da je izdat. rez = 0
				 * znaci da nije izdat. rez = 1 znaci da je rezervisan rez = 2
				 * znaci daje izdat
				 */
				MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rps.getSifPrimjerak());
				DBPrimjerak.updateRezervisan(2, primjerak.getSifPrimjerak());
				/*
				 * treba odgovarajucu rezervaciju odobriti u tabeli rezervacija
				 */
				MRezervacija rezervacija = GetDbTables.getRezervacijaBySifra(rps.getSifRezervacija());
				DBRezervacija.updateOdobrenaRezervacija(1, rezervacija.getSifRezervacija());
				DBRezervacija.updateNastStudRezervacija(2, rezervacija.getSifRezervacija()); // definisemo da je rijec o studentu
				//uzimanje datum i upisivanje u BP
				java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
				java.util.Date utilDate = cal.getTime();
				Date todayDate = new Date(utilDate.getTime());// Danasnji datum;
				DBRezervacija.updateDatumPodizanja(todayDate, rezervacija.getSifRezervacija());

				/*
				 * Za studenta prvo dohvatimo trenutni broj posudjenih knjiga,
				 * inkrementiramo za 1, i onda upisemo
				 * 
				 */
				MStudent student = GetDbTables.getStudentBySifra(rps.getSifStudent());
				int br = student.getBrPosudjenihKnjiga();
				br++;
				DBStudent.updateBrPosudjenihKnjiga(br, student.getSifStudent());

				rezervacijeStudent();
				odobriRezStud.dispose();
			}
		});
		btnIzdajKnjigu.setBounds(51, 147, 117, 25);
		odobriRezStud.getContentPane().add(btnIzdajKnjigu);

		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacijeStudent();
				odobriRezStud.dispose();
			}
		});
		btnPoniti.setBounds(180, 147, 117, 25);
		odobriRezStud.getContentPane().add(btnPoniti);

	}
	
	private void odobriRezervacijuNast(int sifRezPrimjNast) {
		JInternalFrame odobriRezNast = new JInternalFrame("Odobravanje rezervacije", true, true, true);
		odobriRezNast.setBounds(12, 12, 403, 221);
		odobriRezNast.setVisible(true);
		frame.getContentPane().add(odobriRezNast);
		odobriRezNast.getContentPane().setLayout(null);

		MRezervacijaPrimjerakNastavnik rpn = GetDbTables.getRezPrimNastBySifra(sifRezPrimjNast);
		MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rpn.getSifNastavnik());
		MRezervacija rezervacija = GetDbTables.getRezervacijaBySifra(rpn.getSifRezervacija());
		MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rpn.getSifPrimjerak());
		MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());

		JLabel lblNastavnik = new JLabel("Nastavnik:");
		lblNastavnik.setBounds(106, 12, 62, 15);
		odobriRezNast.getContentPane().add(lblNastavnik);

		JLabel lblImePrezNast = new JLabel(nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik());
		lblImePrezNast.setBounds(180, 12, 200, 15);
		odobriRezNast.getContentPane().add(lblImePrezNast);

		JLabel lblKnjiga_1 = new JLabel("Knjiga:");
		lblKnjiga_1.setBounds(119, 39, 49, 15);
		odobriRezNast.getContentPane().add(lblKnjiga_1);

		JLabel lblKnjigaNaslov = new JLabel(knjiga.getOrigNaslov());
		lblKnjigaNaslov.setBounds(180, 39, 200, 15);
		odobriRezNast.getContentPane().add(lblKnjigaNaslov);

		JLabel lblInventarniBrojKnjige = new JLabel("Inventarni broj knjige:");
		lblInventarniBrojKnjige.setBounds(12, 66, 156, 15);
		odobriRezNast.getContentPane().add(lblInventarniBrojKnjige);

		JLabel lblInvBrKnj = new JLabel(primjerak.getInventartniBr());
		lblInvBrKnj.setBounds(180, 66, 200, 15);
		odobriRezNast.getContentPane().add(lblInvBrKnj);

		JLabel lblDatumRezervacije = new JLabel("Datum rezervacije:");
		lblDatumRezervacije.setBounds(35, 93, 133, 15);
		odobriRezNast.getContentPane().add(lblDatumRezervacije);

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String datRezervStr = df.format(rezervacija.getDatRezervacija());
		JLabel lblDatRezerv = new JLabel(datRezervStr);
		lblDatRezerv.setBounds(180, 93, 200, 15);
		odobriRezNast.getContentPane().add(lblDatRezerv);

		JLabel lblDatumVraanja = new JLabel("Datum vraćanja:");
		lblDatumVraanja.setBounds(53, 120, 115, 15);
		odobriRezNast.getContentPane().add(lblDatumVraanja);

		String datVracStr = df.format(rezervacija.getDatVracanja());
		JLabel lblDatVracanja = new JLabel(datVracStr);
		lblDatVracanja.setBounds(180, 120, 200, 15);
		odobriRezNast.getContentPane().add(lblDatVracanja);

		JButton btnIzdajKnjigu = new JButton("Izdaj knjigu");
		btnIzdajKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MRezervacijaPrimjerakNastavnik rpn = GetDbTables.getRezPrimNastBySifra(sifRezPrimjNast);		

				MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rpn.getSifPrimjerak());
				DBPrimjerak.updateRezervisan(2, primjerak.getSifPrimjerak());

				MRezervacija rezervacija = GetDbTables.getRezervacijaBySifra(rpn.getSifRezervacija());
				DBRezervacija.updateOdobrenaRezervacija(1, rezervacija.getSifRezervacija());
				DBRezervacija.updateNastStudRezervacija(1, rezervacija.getSifRezervacija()); 

				java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
				java.util.Date utilDate = cal.getTime();
				Date todayDate = new Date(utilDate.getTime());// Danasnji datum;
				DBRezervacija.updateDatumPodizanja(todayDate, rezervacija.getSifRezervacija());

				MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rpn.getSifNastavnik());
				int br = nastavnik.getBrPosudjenihKnjiga();
				br++;
				DBNastavnik.updateBrPosudjenihKnjiga(br, nastavnik.getSifNastavnik());

				rezervacijeNastavnik();
				odobriRezNast.dispose();
			}
		});
		btnIzdajKnjigu.setBounds(51, 147, 117, 25);
		odobriRezNast.getContentPane().add(btnIzdajKnjigu);

		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacijeNastavnik();
				odobriRezNast.dispose();
			}
		});
		btnPoniti.setBounds(180, 147, 117, 25);
		odobriRezNast.getContentPane().add(btnPoniti);

	}

	private void neizdateKnjige() {
		JInternalFrame neIzdateKnjige = new JInternalFrame("Lista neizdatih knjiga", true, true, true);
		neIzdateKnjige.setBounds(12, 12, 799, 438);
		neIzdateKnjige.setVisible(true);
		frame.getContentPane().add(neIzdateKnjige);
		neIzdateKnjige.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 765, 382);
		neIzdateKnjige.getContentPane().add(scrollPane);

		bibliotekarTableNeizdateKnjige = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(bibliotekarTableNeizdateKnjige);
		bibliotekarTableNeizdateKnjige.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Originalni naslov", "Vrsta knjige", "Datum nabavke", "Inv.Br.", "Stanje" }));

		bibliotekarTableNeizdateKnjige.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableNeizdateKnjige.getColumnModel().getColumn(1).setPreferredWidth(50);
		bibliotekarTableNeizdateKnjige.getColumnModel().getColumn(2).setPreferredWidth(150);
		bibliotekarTableNeizdateKnjige.getColumnModel().getColumn(3).setPreferredWidth(60);
		bibliotekarTableNeizdateKnjige.getColumnModel().getColumn(4).setPreferredWidth(30);

		DefaultTableModel modelNeizdKnj = (DefaultTableModel) bibliotekarTableNeizdateKnjige.getModel();
		modelNeizdKnj.setRowCount(0);

		/*
		 *  unos neizdatih knjiga u tabelu
		 */
		MPrimjerak primjerak = new MPrimjerak();
		for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
			/*
			 *  rez=2 znaci da je izdat. 0 je da nije izdat, 1 da je rezervisan.
			 *	mi zelimo koji nisu izdati i koji su rezervisani
			 */
			if (primj.getRezervisan() != 2) {
				primjerak.setDatumNabavke(primj.getDatumNabavke());
				primjerak.setInventartniBr(primj.getInventartniBr());
				primjerak.setStanje(primj.getStanje());

				/*
				 * trebamo dohvatiti tabelu kjiga iz bp da uzmemo naslov knjige,
				 * vrstu knjige
				 */
				MKnjiga knjiga = new MKnjiga();
				MVrstaKnjige vrsta = new MVrstaKnjige();
				for (MKnjiga knj : GetDbTables.getTableKnjige()) {
					if (primj.getSifKnjiga() == knj.getSifKnjiga()) {
						knjiga.setOrigNaslov(knj.getOrigNaslov());
						/*
						 * treba da dohvatio i vrstu knjige
						 */
						for (MVrstaKnjige vrs : GetDbTables.getTableVrstaKnjige()) {
							if (knj.getSifVrstaKnjige() == vrs.getSifVrstaKnjige()) {
								vrsta = vrs;
								break;
							}
						}
						knjiga = knj;
						break;
					}
				}
				
				modelNeizdKnj.addRow(new Object[] { knjiga.getOrigNaslov(), vrsta.getVrsta(),
						primjerak.getDatumNabavke(), primjerak.getInventartniBr(), primjerak.getStanje() });
			}
		}

	}

	private void izdateKnjigeStudenti() {
		JInternalFrame izdateKnjige = new JInternalFrame("Lista izdatih knjiga studentima", true, true, true);
		izdateKnjige.setBounds(12, 12, 799, 400);
		izdateKnjige.setVisible(true);
		frame.getContentPane().add(izdateKnjige);
		izdateKnjige.getContentPane().setLayout(null);

		JScrollPane scrollPaneStudent = new JScrollPane();
		scrollPaneStudent.setBounds(12, 43, 765, 313);
		izdateKnjige.getContentPane().add(scrollPaneStudent);

		bibliotekarTableIzdateKnjigeStud = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneStudent.setViewportView(bibliotekarTableIzdateKnjigeStud);
		bibliotekarTableIzdateKnjigeStud.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Originalni naslov", "Br Indexa", "Student", "Datum izdavanja", "Datum vraćanja", "InvBr" }));

		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(1).setPreferredWidth(45);
		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(2).setPreferredWidth(120);
		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(3).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(4).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeStud.getColumnModel().getColumn(5).setPreferredWidth(10);
		DefaultTableModel modelIzdKnj = (DefaultTableModel) bibliotekarTableIzdateKnjigeStud.getModel();
		modelIzdKnj.setRowCount(0);

		/*
		 *  unos izdatih knjiga u tabelu
		 */
		MPrimjerak primjerak = new MPrimjerak();
		for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
			/*
			 *  rez=2 znaci da je izdat. 0 je da nije izdat, 1 da je rezervisan.
			 *	mi zelimo koji nisu izdati i koji su rezervisani
			 */
			if (primj.getRezervisan() == 2) {
				primjerak.setDatumNabavke(primj.getDatumNabavke());
				primjerak.setInventartniBr(primj.getInventartniBr());
				primjerak.setStanje(primj.getStanje());

				/*
				 * trebamo dohvatiti tabelu kjiga iz bp da uzmemo naslov knjige,
				 * vrstu knjige
				 */
				MKnjiga knjiga = new MKnjiga();
				MVrstaKnjige vrsta = new MVrstaKnjige();
				for (MKnjiga knj : GetDbTables.getTableKnjige()) {
					if (primj.getSifKnjiga() == knj.getSifKnjiga()) {
						knjiga.setOrigNaslov(knj.getOrigNaslov());
						/*
						 * treba da dohvatio i vrstu knjige
						 */
						for (MVrstaKnjige vrs : GetDbTables.getTableVrstaKnjige()) {
							if (knj.getSifVrstaKnjige() == vrs.getSifVrstaKnjige()) {
								vrsta = vrs;
								break;
							}
						}
						knjiga = knj;
						break;
					}
				}

				/*
				 * Za studente pronalazimo kome je izdata, na nacin da
				 * pretrezimo tabelu rezervacija
				 */
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				String datPodizanja = new String();
				String datVracanja = new String();

				MStudent student = new MStudent(); 
				MRezervacija rezervacijaStud = new MRezervacija();

				for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
					if (rez.getSifPrimjerak() == primj.getSifPrimjerak() && rez.getNastStud() == 2
							&& rez.getOdobrena() == 1) { 
						for (MStudent stud : GetDbTables.getTableStudenti()) {
							if (stud.getSifStudent() == rez.getSifKorisnik()) {
								student = stud;
								rezervacijaStud = rez;
								break;
							}
						}
						datPodizanja = sdf.format(rezervacijaStud.getDatPodizanja());
						datVracanja = sdf.format(rezervacijaStud.getDatVracanja());
						
						modelIzdKnj.addRow(new Object[] { knjiga.getOrigNaslov(), student.getBrIndexa(),
								student.getImeStudent() + " " + student.getPrezStudent(), datPodizanja, datVracanja, primjerak.getInventartniBr() });
						break;
					}
				}
			}
		}
		
		txtFilterTableIzdateKjnjigeStudentima = new JTextField();
		txtFilterTableIzdateKjnjigeStudentima.setBounds(12, 12, 337, 19);
		izdateKnjige.getContentPane().add(txtFilterTableIzdateKjnjigeStudentima);
		txtFilterTableIzdateKjnjigeStudentima.setColumns(10);
		
		filterTableByColumn(bibliotekarTableIzdateKnjigeStud, 1, txtFilterTableIzdateKjnjigeStudentima);
		
		JRadioButton rdbtnBrojIndeksa = new JRadioButton("Broj indeksa");
		rdbtnBrojIndeksa.setSelected(true);
		rdbtnBrojIndeksa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(bibliotekarTableIzdateKnjigeStud, 1, txtFilterTableIzdateKjnjigeStudentima);
			}
		});
		rdbtnBrojIndeksa.setBounds(357, 12, 113, 23);
		izdateKnjige.getContentPane().add(rdbtnBrojIndeksa);
		
		JRadioButton rdbtnImeIPrezime = new JRadioButton("Ime i prezime");
		rdbtnImeIPrezime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(bibliotekarTableIzdateKnjigeStud, 2, txtFilterTableIzdateKjnjigeStudentima);
			}
		});
		rdbtnImeIPrezime.setBounds(474, 12, 149, 23);
		izdateKnjige.getContentPane().add(rdbtnImeIPrezime);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBrojIndeksa);
		group.add(rdbtnImeIPrezime);

		
		
		bibliotekarTableIzdateKnjigeStud.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableIzdateKnjigeStud.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableIzdateKnjigeStud.columnAtPoint(evt.getPoint());
				
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					
					String invBr = (String) bibliotekarTableIzdateKnjigeStud.getModel().getValueAt(bibliotekarTableIzdateKnjigeStud.convertRowIndexToModel(row), 5);
					potvrdiVracanje(invBr);
					izdateKnjige.dispose();
				}
			}
		});
		
	}

	private void izdateKnjigeNastastavnici() {
		JInternalFrame izdateKnjigeNast = new JInternalFrame("Lista izdatih knjiga nastavnicima", true, true, true);
		izdateKnjigeNast.setBounds(12, 12, 799, 400);
		izdateKnjigeNast.setVisible(true);
		frame.getContentPane().add(izdateKnjigeNast);
		izdateKnjigeNast.getContentPane().setLayout(null);

		JScrollPane scrollPaneStudent = new JScrollPane();
		scrollPaneStudent.setBounds(12, 43, 765, 313);
		izdateKnjigeNast.getContentPane().add(scrollPaneStudent);

		bibliotekarTableIzdateKnjigeNast = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneStudent.setViewportView(bibliotekarTableIzdateKnjigeNast);
		bibliotekarTableIzdateKnjigeNast.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Originalni naslov",
				"Vrsta knjige", "Nastavnik", "Datum izdavanja", "Datum vraćanja", "InvBr" }));

		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(1).setPreferredWidth(45);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(2).setPreferredWidth(120);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(3).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(4).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(5).setPreferredWidth(10);

		DefaultTableModel modelIzdKnj = (DefaultTableModel) bibliotekarTableIzdateKnjigeNast.getModel();
		modelIzdKnj.setRowCount(0);


		MPrimjerak primjerak = new MPrimjerak();
		for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
			
			if (primj.getRezervisan() == 2) {
				primjerak.setDatumNabavke(primj.getDatumNabavke());
				primjerak.setInventartniBr(primj.getInventartniBr());
				primjerak.setStanje(primj.getStanje());

				MKnjiga knjiga = new MKnjiga();
				MVrstaKnjige vrsta = new MVrstaKnjige();
				
				for (MKnjiga knj : GetDbTables.getTableKnjige()) {
					if (primj.getSifKnjiga() == knj.getSifKnjiga()) {
						knjiga.setOrigNaslov(knj.getOrigNaslov());

						for (MVrstaKnjige vrs : GetDbTables.getTableVrstaKnjige()) {
							if (knj.getSifVrstaKnjige() == vrs.getSifVrstaKnjige()) {
								vrsta = vrs;
								break;
							}
						}
						knjiga = knj;
						break;
					}
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				String datPodizanja = new String();
				String datVracanja = new String();

				MNastavnik nastavnik = new MNastavnik(); 
				MRezervacija rezervacijaNast = new MRezervacija();
				
				for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
					if (rez.getSifPrimjerak() == primj.getSifPrimjerak() && rez.getNastStud() == 1
							&& rez.getOdobrena() == 1) {

						for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
							if (nast.getSifNastavnik() == rez.getSifKorisnik()) {
								nastavnik = nast;
								rezervacijaNast = rez;
								break;
							}
						}
						datPodizanja = sdf.format(rezervacijaNast.getDatPodizanja());
						datVracanja = sdf.format(rezervacijaNast.getDatVracanja());
						modelIzdKnj.addRow(new Object[] { knjiga.getOrigNaslov(), vrsta.getVrsta(),
								nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik(), datPodizanja,
								datVracanja, primjerak.getInventartniBr() });
						break;
					}
				}
			}
		}
		
		JLabel lblTraziNastavnika = new JLabel("Pretrazi po imenu studenta:");
		lblTraziNastavnika.setBounds(12, 12, 200, 15);
		izdateKnjigeNast.getContentPane().add(lblTraziNastavnika);
		
		txtFilterTableIzdateKjnjigeNastavnicima = new JTextField();
		txtFilterTableIzdateKjnjigeNastavnicima.setBounds(230, 12, 337, 19);
		izdateKnjigeNast.getContentPane().add(txtFilterTableIzdateKjnjigeNastavnicima);
		txtFilterTableIzdateKjnjigeNastavnicima.setColumns(10);
		
		filterTableByColumn(bibliotekarTableIzdateKnjigeNast, 2, txtFilterTableIzdateKjnjigeNastavnicima);
		
		bibliotekarTableIzdateKnjigeNast.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableIzdateKnjigeNast.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableIzdateKnjigeNast.columnAtPoint(evt.getPoint());
				
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					
					String invBr = (String) bibliotekarTableIzdateKnjigeNast.getModel().getValueAt(bibliotekarTableIzdateKnjigeNast.convertRowIndexToModel(row), 5);
					potvrdiVracanje(invBr);
					izdateKnjigeNast.dispose();
				}
			}
		});
	}
	
	private void potvrdiVracanje(String invBr) {
		JInternalFrame potvrdiVracanje = new JInternalFrame("potvrdi vracanje", true, true, true);
		potvrdiVracanje.setBounds(12, 12, 310, 193);
		potvrdiVracanje.setVisible(true);
		frame.getContentPane().add(potvrdiVracanje);
		potvrdiVracanje.getContentPane().setLayout(null);
		
		MPrimjerak primjerak = GetDbTables.getPrimjerakByInventBroj(invBr);
		MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());
		MVrstaKnjige vrsta = GetDbTables.getVrstaKnjigeBySifra(knjiga.getSifVrstaKnjige());
		MRezervacija rezervacija = GetDbTables.getRezervacijaBySifPrimjerak(primjerak.getSifPrimjerak());

		JLabel lblKnjiga = new JLabel("Knjiga:");
		lblKnjiga.setBounds(72, 12, 49, 15);
		potvrdiVracanje.getContentPane().add(lblKnjiga);

		JLabel lblKnjigaNaslov_1 = new JLabel(knjiga.getOrigNaslov());
		lblKnjigaNaslov_1.setBounds(133, 12, 154, 15);
		potvrdiVracanje.getContentPane().add(lblKnjigaNaslov_1);

		JLabel lblVrstaKnjige = new JLabel("Vrsta knjige:");
		lblVrstaKnjige.setBounds(31, 39, 90, 15);
		potvrdiVracanje.getContentPane().add(lblVrstaKnjige);

		JLabel lblKnjigaVrsta = new JLabel(vrsta.getVrsta());
		lblKnjigaVrsta.setBounds(133, 39, 154, 15);
		potvrdiVracanje.getContentPane().add(lblKnjigaVrsta);

		JLabel lblInventarniBroj_1 = new JLabel("Inventarni broj:");
		lblInventarniBroj_1.setBounds(12, 66, 109, 15);
		potvrdiVracanje.getContentPane().add(lblInventarniBroj_1);

		JLabel lblInvBr = new JLabel(primjerak.getInventartniBr());
		lblInvBr.setBounds(133, 66, 154, 15);
		potvrdiVracanje.getContentPane().add(lblInvBr);

		JLabel lblStanje = new JLabel("Stanje:");
		lblStanje.setBounds(70, 93, 51, 15);
		potvrdiVracanje.getContentPane().add(lblStanje);

		JComboBox<String> cmbBoxStanje = new JComboBox<String>();
		cmbBoxStanje.setBounds(133, 88, 154, 24);
		potvrdiVracanje.getContentPane().add(cmbBoxStanje);

		cmbBoxStanje.addItem("Odlično");
		cmbBoxStanje.addItem("Vrlo dobro");
		cmbBoxStanje.addItem("Dobro");
		cmbBoxStanje.addItem("Zadovoljavajuće");
		cmbBoxStanje.addItem("Loše");

		JButton btnPotvrdiPovrat = new JButton("Potvrdi povrat");
		btnPotvrdiPovrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rezervacija.getNastStud() == 2){
					
					/*
					 * kada korisnik potvrdi vracanje jedne knjige, treba da se iz
					 * bp ttabele student smanji broj posudjenih knjiga i br
					 * rezervacija za 1. odgovarajuca rezervacija treba da se popuni
					 * datum vracanja. primjerak treba da se oznaci da je vracen i
					 * da se stavi u kakvom je stanju vracen.
					 */
					MPrimjerak primjerak = GetDbTables.getPrimjerakByInventBroj(invBr);
					MRezervacija rezervacija = GetDbTables.getRezervacijaBySifPrimjerak(primjerak.getSifPrimjerak());
					MStudent student = GetDbTables.getStudentBySifra(rezervacija.getSifKorisnik());
					
					/*
					 * prvo cemo studentu da smanjimo br posudjenih knjiga i br
					 * rezervacija za 1.
					 */
					int brRez = student.getBrRezervacija();
					int brPosudj = student.getBrPosudjenihKnjiga();
					brRez--;
					brPosudj--;
					DBStudent.updateBrRezervacija(brRez, student.getSifStudent());
					DBStudent.updateBrPosudjenihKnjiga(brPosudj, student.getSifStudent());
					
					/*
					 * unosimo za primjerak da je slobodan tj rezervisan = 0 i kakvo
					 * je stanje po primanju
					 */
					String stanje = (String) cmbBoxStanje.getSelectedItem();
					DBPrimjerak.updateStanje(stanje, primjerak.getSifPrimjerak());
					DBPrimjerak.updateRezervisan(0, primjerak.getSifPrimjerak());
					
					/*
					 * unosimo u rezevaciju kada je vracena knjiga. kao i
					 * odobrena=-1, to znaci da je je vracena knjiga
					 */
					java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
					java.util.Date utilDate = cal.getTime();
					Date todayDate = new Date(utilDate.getTime());
					DBRezervacija.updateDatumKadVracena(todayDate, rezervacija.getSifRezervacija());
					DBRezervacija.updateOdobrenaRezervacija(-1, rezervacija.getSifRezervacija());
					izdateKnjigeStudenti();
				} 
				else if(rezervacija.getNastStud() == 1) {
			
					MPrimjerak primjerak = GetDbTables.getPrimjerakByInventBroj(invBr);
					MRezervacija rezervacija = GetDbTables.getRezervacijaBySifPrimjerak(primjerak.getSifPrimjerak());
					MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rezervacija.getSifKorisnik());

					int brRez = nastavnik.getBrRezervacija();
					int brPosudj = nastavnik.getBrPosudjenihKnjiga();
					brRez--;
					brPosudj--;
					DBNastavnik.updateBrRezervacija(brRez, nastavnik.getSifNastavnik());
					DBNastavnik.updateBrPosudjenihKnjiga(brPosudj, nastavnik.getSifNastavnik());
					
					String stanje = (String) cmbBoxStanje.getSelectedItem();
					DBPrimjerak.updateStanje(stanje, primjerak.getSifPrimjerak());
					DBPrimjerak.updateRezervisan(0, primjerak.getSifPrimjerak());

					java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
					java.util.Date utilDate = cal.getTime();
					Date todayDate = new Date(utilDate.getTime());
					DBRezervacija.updateDatumKadVracena(todayDate, rezervacija.getSifRezervacija());
					DBRezervacija.updateOdobrenaRezervacija(-1, rezervacija.getSifRezervacija());
					izdateKnjigeNastastavnici();
				}

				potvrdiVracanje.dispose();
			}
		});
		btnPotvrdiPovrat.setBounds(12, 124, 146, 25);
		potvrdiVracanje.getContentPane().add(btnPotvrdiPovrat);

		JButton btnPoniti_1 = new JButton("Poništi");
		btnPoniti_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MPrimjerak primjerak = GetDbTables.getPrimjerakByInventBroj(invBr);
				MRezervacija rezervacija = GetDbTables.getRezervacijaBySifPrimjerak(primjerak.getSifPrimjerak());
				if(rezervacija.getNastStud() == 2)
					izdateKnjigeStudenti();
				else if(rezervacija.getNastStud() == 1)
					izdateKnjigeNastastavnici();

				potvrdiVracanje.dispose();
			}
		});
		btnPoniti_1.setBounds(170, 124, 117, 25);
		potvrdiVracanje.getContentPane().add(btnPoniti_1);

	}
	
	private static void filterTableByColumn(JTable jTable, int columnIndex, JTextField txtFilterText) {
		TableRowSorter<TableModel> rowSorter1 = new TableRowSorter<>(jTable.getModel());
		jTable.setRowSorter(rowSorter1);
				
		txtFilterText.getDocument().addDocumentListener(new DocumentListener(){
			
			
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtFilterText.getText();

                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text, columnIndex));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtFilterText.getText();

                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text, columnIndex));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
	}
	
	private static void filterTable(JTable jTable, JTextField txtFilterText) {
		TableRowSorter<TableModel> rowSorter1 = new TableRowSorter<>(jTable.getModel());
		jTable.setRowSorter(rowSorter1);
				
		txtFilterText.getDocument().addDocumentListener(new DocumentListener(){
			
			
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtFilterText.getText();

                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtFilterText.getText();

                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
	}
}
