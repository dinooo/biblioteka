package gui.biblioteka.ba.fet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import db.biblioteka.ba.fet.DBAutor;
import db.biblioteka.ba.fet.DBAutorRBr;
import db.biblioteka.ba.fet.DBIzdavac;
import db.biblioteka.ba.fet.DBKnjiga;
import db.biblioteka.ba.fet.DBKnjigaAutorRBr;
import db.biblioteka.ba.fet.DBNastavnik;
import db.biblioteka.ba.fet.DBObaveznost;
import db.biblioteka.ba.fet.DBPredmet;
import db.biblioteka.ba.fet.DBPrimjerak;
import db.biblioteka.ba.fet.DBRezervacija;
import db.biblioteka.ba.fet.DBSemestar;
import db.biblioteka.ba.fet.DBStudent;
import db.biblioteka.ba.fet.DBVaznost;
import db.biblioteka.ba.fet.DBVaznostObaveznost;
import db.biblioteka.ba.fet.DBVrstaKnjige;
import db.biblioteka.ba.fet.GetDbTables;
import modeli.biblioteka.ba.fet.MAutor;
import modeli.biblioteka.ba.fet.MAutorRBr;
import modeli.biblioteka.ba.fet.MIzdavac;
import modeli.biblioteka.ba.fet.MKnjiga;
import modeli.biblioteka.ba.fet.MKnjigaAutorRBr;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MObaveznost;
import modeli.biblioteka.ba.fet.MPredmet;
import modeli.biblioteka.ba.fet.MPrimjerak;
import modeli.biblioteka.ba.fet.MRezervacija;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakNastavnik;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;
import modeli.biblioteka.ba.fet.MSemestar;
import modeli.biblioteka.ba.fet.MStudent;
import modeli.biblioteka.ba.fet.MVaznost;
import modeli.biblioteka.ba.fet.MVaznostObaveznost;
import modeli.biblioteka.ba.fet.MVrstaKnjige;
import razno.biblioteka.fet.ba.DateLabelFormatter;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class BibliotekarGUI {

	private JFrame frame;
	private JTextField bibliotekarTxtNaslovKnjiga;
	private JTextField bibliotekarTxtOrigNaslovKnjiga;
	private JTextField bibliotekarTxtBrStranicaKnjiga;
	private JTextField bibliotekarTxtGodIzdanjaKnjiga;
	private JTextField bibliotekarTxtNegBodoviKnjiga;
	private JTextField bibliotekarTxtVrstaKnjiga;
	private JTextField bibliotekarTxtIzdavac;
	private JTextField bibliotekarTxtImeNastavnik;
	private JTextField bibliotekarTxtPrezimeNastavnik;
	private JTextField bibliotekarTxtPasswordNastavnik;
	private JTextField bibliotekarTxtImeStudent;
	private JTextField bibliotekarTxtPrezimeStudent;
	private JTextField bibliotekarTxtPasswordStudent;
	private JTextField bibliotekarTxtBrIndexa;
	private JTextField bibliotekarTxtSemestar;
	private JTextField bibliotekarTxtNazpredmet;
	private JTextField bibliotekarTxtKratpredmet;
	private JTextField bibliotekarTxtVaznost;
	private JTextField bibliotekarTxtObaveznost;
	private JTextField bibliotekarTxtImeAutora;
	private JTextField bibliotekarTxtPrezimeAutora;
	private JTextField bibliotekarTxtRbrAutora;
	private JTextField bibliotekarTxtInvBroj;
	private JTable bibliotekarTableRezervStud;
	private JTable bibliotekarTableRezervNast;
	private JTable bibliotekarTableNeizdateKnjige;
	private JTable bibliotekarTableIzdateKnjigeNast;
	private JTable bibliotekarTableStudenti;
	private JTable bibliotekarTableNastavnici;
	private JTable bibliotekarTableIzdateStudentima;
	private JTable bibliotekarTableIzdateNastavnicima;
	/**
	 * Launch the application.
	 */
	public static void startBibliotekar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliotekarGUI window = new BibliotekarGUI();
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
	public BibliotekarGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * Kreireanje menija
		 */
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		/*
		 * Meni Biblioteka
		 */
		JMenu mnBibliotekaBibliotekar = new JMenu("Biblioteka");
		menuBar.add(mnBibliotekaBibliotekar);

		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacije();
			}
		});
		mnBibliotekaBibliotekar.add(mntmRezervacije);

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

		JMenuItem mntmPovratKnjige = new JMenuItem("Povrat knjige");
		mntmPovratKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vratiKnjigu();
			}
		});
		mnBibliotekaBibliotekar.add(mntmPovratKnjige);
		mnBibliotekaBibliotekar.add(mntmDodajNoviPrimjerak);

		/*
		 * Meni Korisnici
		 */
		JMenu mnKorisnici = new JMenu("Korisnici");
		menuBar.add(mnKorisnici);

		JMenu mnNoviKorisnik = new JMenu("Novi korisnik");
		mnKorisnici.add(mnNoviKorisnik);

		JMenuItem mntmNastavnik = new JMenuItem("Nastavnik");
		mntmNastavnik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviNastavnik();
			}
		});
		mnNoviKorisnik.add(mntmNastavnik);

		JMenuItem mntmStudent = new JMenuItem("Student");
		mntmStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviStudent();
			}
		});
		mnNoviKorisnik.add(mntmStudent);

		/*
		 * Meni Knjiga
		 */
		JMenu mnKnjiga = new JMenu("Knjiga");
		menuBar.add(mnKnjiga);

		JMenu mnKnjige = new JMenu("Knjige");
		mnKnjiga.add(mnKnjige);

		JMenuItem mntmDodajKnjigu = new JMenuItem("Dodaj knjigu");
		mntmDodajKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaKnjiga();
			}
		});
		mnKnjige.add(mntmDodajKnjigu);
		/*
		JMenuItem mntmIzbriiKnjigu = new JMenuItem("Izbriši knjigu");
		mnKnjige.add(mntmIzbriiKnjigu);
		*/
		JMenu mnAutori = new JMenu("Autori");
		mnKnjiga.add(mnAutori);

		JMenuItem mntmDodajAutora = new JMenuItem("Dodaj autora");
		mntmDodajAutora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviAutor();
			}
		});
		mnAutori.add(mntmDodajAutora);
		/*
		JMenuItem mntmIzbriiAutora = new JMenuItem("Izbriši autora");
		mnAutori.add(mntmIzbriiAutora);
		*/
		JMenu mnIzdava = new JMenu("Izdavač");
		mnKnjiga.add(mnIzdava);

		JMenuItem mntmDodajIzdavaa = new JMenuItem("Dodaj izdavača");
		mntmDodajIzdavaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviIzdavac();
			}
		});
		mnIzdava.add(mntmDodajIzdavaa);
		/*
		JMenuItem mntmIzbriiIzdava = new JMenuItem("Izbriši izdavča");
		mnIzdava.add(mntmIzbriiIzdava);
		 */
		JMenu mnTipKnjige = new JMenu("Tip knjige");
		mnKnjiga.add(mnTipKnjige);

		JMenuItem mntmDodajTipKnjige = new JMenuItem("Dodaj tip knjige");
		mntmDodajTipKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaVrstaKnjige();
			}
		});
		mnTipKnjige.add(mntmDodajTipKnjige);
		/*
		JMenuItem mntmIzbriiTipKnjige = new JMenuItem("Izbriši tip knjige");
		mnTipKnjige.add(mntmIzbriiTipKnjige);
		*/
		/*
		 * Meni Predmet
		 */
		JMenu mnPredmeti = new JMenu("Predmeti");
		menuBar.add(mnPredmeti);

		JMenu mnPredmeti_1 = new JMenu("Predmeti");
		mnPredmeti.add(mnPredmeti_1);

		JMenuItem mntmDodajPredmet = new JMenuItem("Dodaj predmet");
		mntmDodajPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviPredmet();
			}
		});
		mnPredmeti_1.add(mntmDodajPredmet);
		/*
		JMenuItem mntmIzbriiPredmet = new JMenuItem("Izbriši predmet");
		mnPredmeti_1.add(mntmIzbriiPredmet);
		*/
		JMenu mnSemestar = new JMenu("Semestar");
		mnPredmeti.add(mnSemestar);

		JMenuItem mntmDodajSemestar = new JMenuItem("Dodaj semestar");
		mntmDodajSemestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviSemestar();
			}
		});
		mnSemestar.add(mntmDodajSemestar);
		
		/*
		JMenuItem mntmIzbriiSemestar = new JMenuItem("Izbriši semestar");
		mnSemestar.add(mntmIzbriiSemestar);
		 */
		JMenu mnOstalo = new JMenu("Ostalo");
		menuBar.add(mnOstalo);

		JMenu mnTabelaVaznostiPredmeta = new JMenu("Tabela važnosti predmeta");
		mnOstalo.add(mnTabelaVaznostiPredmeta);

		JMenuItem mntmDodajVaznost = new JMenuItem("Dodaj važnost");
		mntmDodajVaznost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaVaznost();
			}
		});
		mnTabelaVaznostiPredmeta.add(mntmDodajVaznost);
		/*
		JMenuItem mntmIzbriiVanost = new JMenuItem("Izbriši važnost");
		mnTabelaVaznostiPredmeta.add(mntmIzbriiVanost);
		 */
		JMenu mnTabelaObaveznostiPredmeta = new JMenu("Tabela obaveznosti predmeta");
		mnOstalo.add(mnTabelaObaveznostiPredmeta);

		JMenuItem mntmDodajObaveznost = new JMenuItem("Dodaj obaveznost");
		mntmDodajObaveznost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaObaveznost();
			}
		});
		mnTabelaObaveznostiPredmeta.add(mntmDodajObaveznost);
		
		/*
		JMenuItem mntmIzbrisiObaveznost = new JMenuItem("Izbrisi obaveznost");
		mnTabelaObaveznostiPredmeta.add(mntmIzbrisiObaveznost);
		 */
		JMenu mnTabelaRednihBrojeva = new JMenu("Tabela rednih brojeva autora");
		mnOstalo.add(mnTabelaRednihBrojeva);

		JMenuItem mntmDodajRedniBroj = new JMenuItem("Dodaj redni broj");
		mntmDodajRedniBroj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noviRBrAutora();
			}
		});
		mnTabelaRednihBrojeva.add(mntmDodajRedniBroj);
	}

	
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

	private void novaVrstaKnjige() {
		JInternalFrame novaVrstaKnjige = new JInternalFrame("Unos vrste knjige", true, true, true);
		novaVrstaKnjige.setBounds(12, 12, 386, 109);
		novaVrstaKnjige.setVisible(true);
		frame.getContentPane().add(novaVrstaKnjige);
		novaVrstaKnjige.getContentPane().setLayout(null);

		JLabel lblVrstaKnjige = new JLabel("Vrsta knjige:");
		lblVrstaKnjige.setBounds(12, 12, 90, 15);
		novaVrstaKnjige.getContentPane().add(lblVrstaKnjige);

		bibliotekarTxtVrstaKnjiga = new JTextField();
		bibliotekarTxtVrstaKnjiga.setText("");
		bibliotekarTxtVrstaKnjiga.setBounds(116, 10, 253, 19);
		novaVrstaKnjige.getContentPane().add(bibliotekarTxtVrstaKnjiga);
		bibliotekarTxtVrstaKnjiga.setColumns(10);

		JButton btnPotvrdiUnos_1 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MVrstaKnjige vrsta = new MVrstaKnjige();
				vrsta.setVrsta(bibliotekarTxtVrstaKnjiga.getText());
				DBVrstaKnjige.insertAutor(vrsta);
			}
		});
		btnPotvrdiUnos_1.setBounds(88, 41, 130, 25);
		novaVrstaKnjige.getContentPane().add(btnPotvrdiUnos_1);

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
	
	private void noviSemestar() {
		JInternalFrame noviSemestar = new JInternalFrame("Unos semestra", true, true, true);
		noviSemestar.setBounds(12, 12, 249, 109);
		noviSemestar.setVisible(true);
		frame.getContentPane().add(noviSemestar);
		noviSemestar.getContentPane().setLayout(null);

		JLabel lblSemestar_1 = new JLabel("Semestar:");
		lblSemestar_1.setBounds(12, 12, 73, 15);
		noviSemestar.getContentPane().add(lblSemestar_1);

		bibliotekarTxtSemestar = new JTextField();
		bibliotekarTxtSemestar.setBounds(103, 10, 114, 19);
		noviSemestar.getContentPane().add(bibliotekarTxtSemestar);
		bibliotekarTxtSemestar.setColumns(10);

		JButton btnPotvrdiUnos_2 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MSemestar semestar = new MSemestar();
				semestar.setSemestar(bibliotekarTxtSemestar.getText());

				DBSemestar.insertSemestar(semestar);

			}
		});
		btnPotvrdiUnos_2.setBounds(84, 40, 133, 25);
		noviSemestar.getContentPane().add(btnPotvrdiUnos_2);

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

	private void novaVaznost() {
		JInternalFrame novaVaznost = new JInternalFrame("Unos Vaznosti", true, true, true);
		novaVaznost.setBounds(12, 122, 224, 109);
		novaVaznost.setVisible(true);
		frame.getContentPane().add(novaVaznost);
		novaVaznost.getContentPane().setLayout(null);

		JLabel lblRedniBrojVanosti = new JLabel("Redni broj važnosti:");
		lblRedniBrojVanosti.setBounds(12, 12, 141, 15);
		novaVaznost.getContentPane().add(lblRedniBrojVanosti);

		bibliotekarTxtVaznost = new JTextField();
		bibliotekarTxtVaznost.setBounds(171, 10, 30, 19);
		novaVaznost.getContentPane().add(bibliotekarTxtVaznost);
		bibliotekarTxtVaznost.setColumns(10);

		JButton btnPotvrdiUnos_4 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MVaznost vaznost = new MVaznost();
				vaznost.setrBrVaznost(Integer.parseInt(bibliotekarTxtVaznost.getText()));
				DBVaznost.insertAutor(vaznost);

				/*
				 * Svaka vaznost je i za obavezne i neobavezne predmete, pa
				 * ovdje punimo i tabelu vaznostObaveznost
				 */

				MVaznostObaveznost vo = new MVaznostObaveznost();
				vo.setSifVaznost(vaznost.getSifVaznost());
				vo.setSifObaveznost(1); // Sifra za Da
				DBVaznostObaveznost.insertKnjiPredObav(vo);

				vo.setSifObaveznost(2); // Sifra za Na
				DBVaznostObaveznost.insertKnjiPredObav(vo);

			}
		});
		btnPotvrdiUnos_4.setBounds(71, 39, 130, 25);
		novaVaznost.getContentPane().add(btnPotvrdiUnos_4);
	}
	
	private void novaObaveznost() {
		JInternalFrame novaObaveznost = new JInternalFrame("Unos obaveznosti", true, true, true);
		novaObaveznost.setBounds(12, 122, 224, 109);
		novaObaveznost.setVisible(true);
		frame.getContentPane().add(novaObaveznost);
		novaObaveznost.getContentPane().setLayout(null);

		JLabel lblRedniBrojVanosti = new JLabel("Obaveznost:");
		lblRedniBrojVanosti.setBounds(12, 12, 141, 15);
		novaObaveznost.getContentPane().add(lblRedniBrojVanosti);

		bibliotekarTxtObaveznost = new JTextField();
		bibliotekarTxtObaveznost.setBounds(171, 10, 30, 19);
		novaObaveznost.getContentPane().add(bibliotekarTxtObaveznost);
		bibliotekarTxtObaveznost.setColumns(10);

		JButton btnPotvrdiUnos_4 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MObaveznost obaveznost = new MObaveznost();
				obaveznost.setObavezna(bibliotekarTxtObaveznost.getText());
				DBObaveznost.insertObaveznost(obaveznost);
			}
		});
		btnPotvrdiUnos_4.setBounds(71, 39, 130, 25);
		novaObaveznost.getContentPane().add(btnPotvrdiUnos_4);
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

	private void noviRBrAutora() {
		JInternalFrame noviRBrAutora = new JInternalFrame("Unos rBr autora", true, true, true);
		noviRBrAutora.setBounds(23, 113, 234, 114);
		noviRBrAutora.setVisible(true);
		frame.getContentPane().add(noviRBrAutora);
		noviRBrAutora.getContentPane().setLayout(null);

		JLabel lblRedniBrojeviAutora = new JLabel("Redni brojevi autora:");
		lblRedniBrojeviAutora.setBounds(12, 12, 149, 15);
		noviRBrAutora.getContentPane().add(lblRedniBrojeviAutora);

		bibliotekarTxtRbrAutora = new JTextField();
		bibliotekarTxtRbrAutora.setBounds(179, 10, 34, 19);
		noviRBrAutora.getContentPane().add(bibliotekarTxtRbrAutora);
		bibliotekarTxtRbrAutora.setColumns(10);

		JButton btnPotvrdiUnos_6 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAutorRBr autorRBr = new MAutorRBr();
				autorRBr.setRBrAutora(Integer.parseInt(bibliotekarTxtRbrAutora.getText()));
				DBAutorRBr.insertAutorRBr(autorRBr);
			}
		});
		btnPotvrdiUnos_6.setBounds(83, 39, 130, 25);
		noviRBrAutora.getContentPane().add(btnPotvrdiUnos_6);

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

	private void rezervacije() {
		JInternalFrame rezervacije = new JInternalFrame("Rezervacije", true, true, true);
		rezervacije.setBounds(12, 12, 799, 438);
		rezervacije.setVisible(true);
		frame.getContentPane().add(rezervacije);
		rezervacije.getContentPane().setLayout(null);

		JLabel lblRezervacijeStudenata = new JLabel("Rezervacije studenata:");
		lblRezervacijeStudenata.setBounds(12, 12, 164, 15);
		rezervacije.getContentPane().add(lblRezervacijeStudenata);

		JScrollPane scrollPaneStud = new JScrollPane();
		scrollPaneStud.setBounds(12, 39, 765, 169);
		rezervacije.getContentPane().add(scrollPaneStud);

		bibliotekarTableRezervStud = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		;
		scrollPaneStud.setViewportView(bibliotekarTableRezervStud);
		bibliotekarTableRezervStud.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sif.Rezerv.", "Student",
				"Knjiga", "Inv.Br.", "Dat.rezervacije", "Dat. vra\u0107anja" }));

		bibliotekarTableRezervStud.getColumnModel().getColumn(0).setPreferredWidth(50);
		bibliotekarTableRezervStud.getColumnModel().getColumn(1).setPreferredWidth(150);
		bibliotekarTableRezervStud.getColumnModel().getColumn(2).setPreferredWidth(200);
		bibliotekarTableRezervStud.getColumnModel().getColumn(3).setPreferredWidth(50);
		bibliotekarTableRezervStud.getColumnModel().getColumn(4).setPreferredWidth(80);
		bibliotekarTableRezervStud.getColumnModel().getColumn(5).setPreferredWidth(80);

		/*
		 * praznimo tabelu kako bi svaki put bila novim podacima napunjena (da
		 * ne bi se punila duplo itd).
		 */
		DefaultTableModel model = (DefaultTableModel) bibliotekarTableRezervStud.getModel();
		model.setRowCount(0);

		/*
		 * konektovanje na bp tabelu RezervacijaPrimjerakStudent, kako bi
		 * popunili tabelu odgovarajucim elementima
		 */
		for (MRezervacijaPrimjerakStudent rps : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
			/*
			 * Treba da dohvatimo studenta, rezervaciju i primjerak (a iz
			 * primjerka naziv knjige iz tabele kjiga) kako bi ih upisali u
			 * tabelu
			 */
			MStudent student = GetDbTables.getStudentBySifra(rps.getSifStudent());
			MRezervacija rezervacijaStud = GetDbTables.getRezervacijaBySifra(rps.getSifRezervacija());
			MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rps.getSifPrimjerak());
			MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());
			// upisivanje tabele
			if (rezervacijaStud.getOdobrena() == 0)
				model.addRow(new Object[] { rps.getSifRezPrimStud(), student.getImeStudent() + " " + student.getPrezStudent(), 
						knjiga.getOrigNaslov(), primjerak.getInventartniBr(), rezervacijaStud.getDatRezervacija(),
						rezervacijaStud.getDatVracanja() }); 
		}

		bibliotekarTableRezervStud.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableRezervStud.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableRezervStud.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj
					 * vrijednost za sifStdenta, i prosljedi to u funkciju
					 * resetStudentBodovi koja je ustvari novi prozor za
					 * resetovanje negativnih bodova
					 */
					int sifRezPrimjStud = (int) bibliotekarTableRezervStud.getModel().getValueAt(row, 0);

					odobriRezervacijuStud(sifRezPrimjStud);
					rezervacije.dispose();
				}
			}
		});

		
		// Za nastavnike
		JLabel lblRezervacijeNastavnika = new JLabel("Rezervacije nastavnika:");
		lblRezervacijeNastavnika.setBounds(12, 220, 170, 15);
		rezervacije.getContentPane().add(lblRezervacijeNastavnika);

		JScrollPane scrollPaneNast = new JScrollPane();
		scrollPaneNast.setBounds(12, 244, 765, 150);
		rezervacije.getContentPane().add(scrollPaneNast);

		bibliotekarTableRezervNast = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		;
		scrollPaneNast.setViewportView(bibliotekarTableRezervNast);
		bibliotekarTableRezervNast.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sif.Rezerv.", "Nastavnik",
				"Knjiga", "Inv.Br.", "Dat.rezervacije", "Dat. vra\u0107anja" }));
		bibliotekarTableRezervNast.getColumnModel().getColumn(0).setPreferredWidth(50);
		bibliotekarTableRezervNast.getColumnModel().getColumn(1).setPreferredWidth(150);
		bibliotekarTableRezervNast.getColumnModel().getColumn(2).setPreferredWidth(200);
		bibliotekarTableRezervNast.getColumnModel().getColumn(3).setPreferredWidth(50);
		bibliotekarTableRezervNast.getColumnModel().getColumn(4).setPreferredWidth(80);
		bibliotekarTableRezervNast.getColumnModel().getColumn(5).setPreferredWidth(80);

		DefaultTableModel modelNast = (DefaultTableModel) bibliotekarTableRezervNast.getModel();
		modelNast.setRowCount(0);

		for (MRezervacijaPrimjerakNastavnik rpn : GetDbTables.getTableRezervacijaPrimjerakNastavnik()) {

			MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rpn.getSifNastavnik());
			MRezervacija rezervacijaNast = GetDbTables.getRezervacijaBySifra(rpn.getSifRezervacija());
			MPrimjerak primjerak = GetDbTables.getPrimjerakBySifra(rpn.getSifPrimjerak());
			MKnjiga knjiga = GetDbTables.getKnjigaBySifra(primjerak.getSifKnjiga());

			if (rezervacijaNast.getOdobrena() == 0)
				modelNast.addRow(new Object[] { rpn.getSifRezPrimNast(), nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik(), 
						knjiga.getOrigNaslov(), primjerak.getInventartniBr(), rezervacijaNast.getDatRezervacija(),
						rezervacijaNast.getDatVracanja() }); 
		}

		bibliotekarTableRezervNast.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableRezervNast.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableRezervNast.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {

					int sifRezPrimjNast = (int) bibliotekarTableRezervNast.getModel().getValueAt(row, 0);
					odobriRezervacijuNast(sifRezPrimjNast);
					rezervacije.dispose();
				}
			}
		});
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

				rezervacije();
				odobriRezStud.dispose();
			}
		});
		btnIzdajKnjigu.setBounds(51, 147, 117, 25);
		odobriRezStud.getContentPane().add(btnIzdajKnjigu);

		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacije();
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

				rezervacije();
				odobriRezNast.dispose();
			}
		});
		btnIzdajKnjigu.setBounds(51, 147, 117, 25);
		odobriRezNast.getContentPane().add(btnIzdajKnjigu);

		JButton btnPoniti = new JButton("Poništi");
		btnPoniti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rezervacije();
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
		izdateKnjige.setBounds(12, 12, 799, 438);
		izdateKnjige.setVisible(true);
		frame.getContentPane().add(izdateKnjige);
		izdateKnjige.getContentPane().setLayout(null);

		JScrollPane scrollPaneStudent = new JScrollPane();
		scrollPaneStudent.setBounds(12, 12, 765, 382);
		izdateKnjige.getContentPane().add(scrollPaneStudent);

		bibliotekarTableIzdateKnjigeNast = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneStudent.setViewportView(bibliotekarTableIzdateKnjigeNast);
		bibliotekarTableIzdateKnjigeNast.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Originalni naslov", "Vrsta knjige", "Student", "Datum izdavanja", "Datum vraćanja" }));

		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(1).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(2).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(3).setPreferredWidth(40);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(4).setPreferredWidth(40);

		DefaultTableModel modelIzdKnj = (DefaultTableModel) bibliotekarTableIzdateKnjigeNast.getModel();
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
						
						modelIzdKnj.addRow(new Object[] { knjiga.getOrigNaslov(), vrsta.getVrsta(),
								student.getImeStudent() + " " + student.getPrezStudent(), datPodizanja, datVracanja });
						break;
					}
				}
			}
		}
	}

	private void izdateKnjigeNastastavnici() {
		JInternalFrame izdateKnjigeNast = new JInternalFrame("Lista izdatih knjiga nastavnicima", true, true, true);
		izdateKnjigeNast.setBounds(12, 12, 799, 438);
		izdateKnjigeNast.setVisible(true);
		frame.getContentPane().add(izdateKnjigeNast);
		izdateKnjigeNast.getContentPane().setLayout(null);

		JScrollPane scrollPaneStudent = new JScrollPane();
		scrollPaneStudent.setBounds(12, 12, 765, 382);
		izdateKnjigeNast.getContentPane().add(scrollPaneStudent);

		bibliotekarTableIzdateKnjigeNast = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneStudent.setViewportView(bibliotekarTableIzdateKnjigeNast);
		bibliotekarTableIzdateKnjigeNast.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Originalni naslov",
				"Vrsta knjige", "Nastavnik", "Datum izdavanja", "Datum vraćanja" }));

		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(1).setPreferredWidth(50);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(2).setPreferredWidth(150);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(3).setPreferredWidth(40);
		bibliotekarTableIzdateKnjigeNast.getColumnModel().getColumn(4).setPreferredWidth(40);

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
								datVracanja });
						break;
					}
				}
			}
		}
	}
	
	private void vratiKnjigu() {
		JInternalFrame vratiKnjigu = new JInternalFrame("Povrat knjige", true, true, true);
		vratiKnjigu.setBounds(12, 12, 799, 438);
		vratiKnjigu.setVisible(true);
		frame.getContentPane().add(vratiKnjigu);
		vratiKnjigu.getContentPane().setLayout(null);

		JLabel lblStudenti = new JLabel("Studenti:");
		lblStudenti.setBounds(12, 12, 66, 15);
		vratiKnjigu.getContentPane().add(lblStudenti);

		JScrollPane scrollPaneStud = new JScrollPane();
		scrollPaneStud.setBounds(12, 39, 366, 355);
		vratiKnjigu.getContentPane().add(scrollPaneStud);

		bibliotekarTableStudenti = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneStud.setViewportView(bibliotekarTableStudenti);
		bibliotekarTableStudenti.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Sifra", "Ime i prezime", "Br.indexa" }));
		bibliotekarTableStudenti.getColumnModel().getColumn(0).setPreferredWidth(50);
		bibliotekarTableStudenti.getColumnModel().getColumn(1).setPreferredWidth(150);
		bibliotekarTableStudenti.getColumnModel().getColumn(2).setPreferredWidth(50);

		DefaultTableModel modelStud = (DefaultTableModel) bibliotekarTableStudenti.getModel();
		modelStud.setRowCount(0);

		for (MStudent stud : GetDbTables.getStudentiSaZaduzenjima()) {
			modelStud.addRow(new Object[] { stud.getSifStudent(), stud.getImeStudent() + " " + stud.getPrezStudent(), 
					stud.getBrIndexa() });
		}

		bibliotekarTableStudenti.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableStudenti.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableStudenti.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					/*
					 * ako dodje do duplog klika, iz odabranog reda procitaj vrijednost za sifStdenta, i prosljedi to 
					 * u funkciju vratiKjiguStudent
					 */
					int sifStudent = (int) bibliotekarTableStudenti.getModel().getValueAt(row, 0);
					vratiKnjiguStudent(sifStudent);
					vratiKnjigu.dispose();
				}
			}
		});

		/*
		 * 
		 */
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(390, 12, 1, 382);
		vratiKnjigu.getContentPane().add(separator);
		/*
		 * 
		 */

		JLabel lblNastavnici = new JLabel("Nastavnici");
		lblNastavnici.setBounds(409, 12, 73, 15);
		vratiKnjigu.getContentPane().add(lblNastavnici);

		JScrollPane scrollPaneNast = new JScrollPane();
		scrollPaneNast.setBounds(403, 40, 374, 354);
		vratiKnjigu.getContentPane().add(scrollPaneNast);

		bibliotekarTableNastavnici = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPaneNast.setViewportView(bibliotekarTableNastavnici);
		bibliotekarTableNastavnici.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "\u0160ifra", "Ime i prezime", "Zvanje" }));
		bibliotekarTableNastavnici.getColumnModel().getColumn(0).setPreferredWidth(50);
		bibliotekarTableNastavnici.getColumnModel().getColumn(1).setPreferredWidth(150);
		bibliotekarTableNastavnici.getColumnModel().getColumn(2).setPreferredWidth(50);

		DefaultTableModel modelNast = (DefaultTableModel) bibliotekarTableNastavnici.getModel();
		modelNast.setRowCount(0);

		for (MNastavnik nast : GetDbTables.getNastavniciSaZaduzenjima()) {
			modelNast.addRow(new Object[] { nast.getSifNastavnik(), nast.getImeNastavnik() + " " + nast.getPrezNastavnik(), 
					nast.getZvanje() });
		}

		bibliotekarTableNastavnici.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableNastavnici.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableNastavnici.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					int sifNastavnik = (int) bibliotekarTableStudenti.getModel().getValueAt(row, 0);
					vratiKnjiguNastavnik(sifNastavnik);
					vratiKnjigu.dispose();
				}
			}
		});

	}

	private void vratiKnjiguStudent(int sifStudent) {
		JInternalFrame vratiKnjiguStud = new JInternalFrame("Povrat knjige student", true, true, true);
		vratiKnjiguStud.setBounds(12, 12, 583, 187);
		vratiKnjiguStud.setVisible(true);
		frame.getContentPane().add(vratiKnjiguStud);
		vratiKnjiguStud.getContentPane().setLayout(null);

		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setBounds(12, 12, 62, 15);
		vratiKnjiguStud.getContentPane().add(lblStudent);
		
		MStudent mStudent = GetDbTables.getStudentBySifra(sifStudent);
		
		JLabel lblImePrezime = new JLabel(mStudent.getImeStudent() + " " + mStudent.getPrezStudent());
		lblImePrezime.setBounds(86, 12, 200, 15);
		vratiKnjiguStud.getContentPane().add(lblImePrezime);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 550, 104);
		vratiKnjiguStud.getContentPane().add(scrollPane);

		bibliotekarTableIzdateStudentima = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(bibliotekarTableIzdateStudentima);
		bibliotekarTableIzdateStudentima
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Naslov", "Vrsta", "Inv.Br." }));

		bibliotekarTableIzdateStudentima.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateStudentima.getColumnModel().getColumn(1).setPreferredWidth(100);
		bibliotekarTableIzdateStudentima.getColumnModel().getColumn(2).setPreferredWidth(50);

		DefaultTableModel modelStud = (DefaultTableModel) bibliotekarTableIzdateStudentima.getModel();
		modelStud.setRowCount(0);

		/*
		 * za svaku rezervaciju treba naci za koji je primjerak, a onda i koja je knjiga
		 */
		MPrimjerak primjerak = new MPrimjerak();
		MKnjiga knjiga = new MKnjiga();
		MVrstaKnjige vrsta = new MVrstaKnjige();
		for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
			if (rez.getOdobrena() == 1 && rez.getSifKorisnik() == sifStudent && rez.getNastStud() == 2) {	
				for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
					if (primj.getSifPrimjerak() == rez.getSifPrimjerak()) {
						primjerak = primj;
						break;
					}
				}
				for (MKnjiga knj : GetDbTables.getTableKnjige()) {
					if (knj.getSifKnjiga() == primjerak.getSifKnjiga()) {
						knjiga = knj;
						break;
					}
				}
				for (MVrstaKnjige vrs : GetDbTables.getTableVrstaKnjige()) {
					if (vrs.getSifVrstaKnjige() == knjiga.getSifVrstaKnjige()) {
						vrsta = vrs;
						break;
					}
				}
				modelStud.addRow(
						new Object[] { knjiga.getOrigNaslov(), vrsta.getVrsta(), primjerak.getInventartniBr() });
			}
		}

		bibliotekarTableIzdateStudentima.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableIzdateStudentima.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableIzdateStudentima.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					String invBr = (String) bibliotekarTableIzdateStudentima.getModel().getValueAt(row, 2);
					potvrdiVracanje(invBr);
					vratiKnjiguStud.dispose();
				}
			}
		});
	}
	
	private void vratiKnjiguNastavnik(int sifNastavnik) {
		JInternalFrame vratiKnjiguNast = new JInternalFrame("Povrat knjige nastavnik", true, true, true);
		vratiKnjiguNast.setBounds(12, 12, 583, 187);
		vratiKnjiguNast.setVisible(true);
		frame.getContentPane().add(vratiKnjiguNast);
		vratiKnjiguNast.getContentPane().setLayout(null);

		JLabel lblNastavnik = new JLabel("Nastavnik:");
		lblNastavnik.setBounds(12, 12, 62, 15);
		vratiKnjiguNast.getContentPane().add(lblNastavnik);
		
		MNastavnik mNastavnik = GetDbTables.getNastavnikBySifra(sifNastavnik);
		
		JLabel lblImePrezime = new JLabel(mNastavnik.getImeNastavnik() + " " + mNastavnik.getPrezNastavnik());
		lblImePrezime.setBounds(86, 12, 200, 15);
		vratiKnjiguNast.getContentPane().add(lblImePrezime);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 550, 104);
		vratiKnjiguNast.getContentPane().add(scrollPane);

		bibliotekarTableIzdateNastavnicima = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false; // onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(bibliotekarTableIzdateNastavnicima);
		bibliotekarTableIzdateNastavnicima
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Naslov", "Vrsta", "Inv.Br." }));

		bibliotekarTableIzdateNastavnicima.getColumnModel().getColumn(0).setPreferredWidth(150);
		bibliotekarTableIzdateNastavnicima.getColumnModel().getColumn(1).setPreferredWidth(100);
		bibliotekarTableIzdateNastavnicima.getColumnModel().getColumn(2).setPreferredWidth(50);

		DefaultTableModel modelNast = (DefaultTableModel) bibliotekarTableIzdateNastavnicima.getModel();
		modelNast.setRowCount(0);

		MPrimjerak primjerak = new MPrimjerak();
		MKnjiga knjiga = new MKnjiga();
		MVrstaKnjige vrsta = new MVrstaKnjige();
		for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
			if (rez.getOdobrena() == 1 && rez.getSifKorisnik() == sifNastavnik && rez.getNastStud() == 1) {	
				for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
					if (primj.getSifPrimjerak() == rez.getSifPrimjerak()) {
						primjerak = primj;
						break;
					}
				}
				for (MKnjiga knj : GetDbTables.getTableKnjige()) {
					if (knj.getSifKnjiga() == primjerak.getSifKnjiga()) {
						knjiga = knj;
						break;
					}
				}
				for (MVrstaKnjige vrs : GetDbTables.getTableVrstaKnjige()) {
					if (vrs.getSifVrstaKnjige() == knjiga.getSifVrstaKnjige()) {
						vrsta = vrs;
						break;
					}
				}
				modelNast.addRow(
						new Object[] { knjiga.getOrigNaslov(), vrsta.getVrsta(), primjerak.getInventartniBr() });
			}
		}

		bibliotekarTableIzdateNastavnicima.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = bibliotekarTableIzdateNastavnicima.rowAtPoint(evt.getPoint());
				int col = bibliotekarTableIzdateNastavnicima.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0 && evt.getClickCount() == 2) {
					String invBr = (String) bibliotekarTableIzdateNastavnicima.getModel().getValueAt(row, 2);
					potvrdiVracanje(invBr);
					vratiKnjiguNast.dispose();
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
					vratiKnjiguStudent(student.getSifStudent());
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
					vratiKnjiguNastavnik(nastavnik.getSifNastavnik());
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
				MStudent student = GetDbTables.getStudentBySifra(rezervacija.getSifKorisnik());
				MNastavnik nastavnik = GetDbTables.getNastavnikBySifra(rezervacija.getSifKorisnik());
				if(rezervacija.getNastStud() == 2)
					vratiKnjiguStudent(student.getSifStudent());
				else if(rezervacija.getNastStud() == 1)
					vratiKnjiguNastavnik(nastavnik.getSifNastavnik());

				potvrdiVracanje.dispose();
			}
		});
		btnPoniti_1.setBounds(170, 124, 117, 25);
		potvrdiVracanje.getContentPane().add(btnPoniti_1);

	}
}
