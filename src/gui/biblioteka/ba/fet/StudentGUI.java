package gui.biblioteka.ba.fet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;


import org.jdesktop.swingx.JXDatePicker;

import db.biblioteka.ba.fet.DBPrimjerak;
import db.biblioteka.ba.fet.DBRezervacija;
import db.biblioteka.ba.fet.DBRezervacijaPrimjerakStudent;
import db.biblioteka.ba.fet.DBStudent;
import db.biblioteka.ba.fet.GetDbTables;
import modeli.biblioteka.ba.fet.MAutor;
import modeli.biblioteka.ba.fet.MIzdavac;
import modeli.biblioteka.ba.fet.MKnjiga;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MPredmet;
import modeli.biblioteka.ba.fet.MPrimjerak;
import modeli.biblioteka.ba.fet.MRezervacija;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;
import modeli.biblioteka.ba.fet.MSemestar;
import modeli.biblioteka.ba.fet.MStudent;
import modeli.biblioteka.ba.fet.MVrstaKnjige;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class StudentGUI {

	private static JFrame frame;
	private JTable tableZaduzenja;
	private static JTable tableKnjige;
	private static JTable tableAutori;
	private static JTable tableIzdavaci;
	private static JTable tableNastavnici;
	private static JTable tableStudenti;
	private static JTable tablePredmeti;
	private JTextField txtPassword;
	private JTable tableRezervacija;
	private static JTextField txtFilterTableKnjige;
	private static JTextField txtFilterTableAutori;
	private static JTextField txtFilterIzdavaci;
	private static JTextField txtFilterNastavnici;
	private static JTextField txtFilterStudent;
	private static JTextField txtFilterPredmet;
	
	/**
	 * Launch the application.
	 */
	public static void startStudent() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI window = new StudentGUI();
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
	public StudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 600);
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

		JMenuItem mntmMojaZaduenja = new JMenuItem("Moja zaduženja");
		mntmMojaZaduenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mojaZaduzenja();
			}
		});
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI.startLogin();
				frame.dispose();
			}
		});

		mnMojProfil.add(mntmMojProfil);
		mnMojProfil.add(mntmMojaZaduenja);
		mnMojProfil.add(mntmLogout);

		JMenu mnBiblioteka = new JMenu("Biblioteka");
		menuBar.add(mnBiblioteka);

		JMenuItem mntmPregledajSveKnjige = new JMenuItem("Knjige");
		mntmPregledajSveKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sveKnjige();
			}
		});
		mnBiblioteka.add(mntmPregledajSveKnjige);

		JMenuItem mntmPregledajSveAutore = new JMenuItem("Autori");
		mntmPregledajSveAutore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviAutori();
			}
		});
		mnBiblioteka.add(mntmPregledajSveAutore);

		JMenuItem mntmPregledajSveIzdavae = new JMenuItem("Izdavači");
		mntmPregledajSveIzdavae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sviIzdavaci();
			}
		});
		mnBiblioteka.add(mntmPregledajSveIzdavae);

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
		 * test
		 */
		
		sviStudenti();
	}
	
	private void mojProfil(){
		JInternalFrame mojProfil = new JInternalFrame("Moj profil", true, true, true);
		mojProfil.setBounds(12, 12, 482, 181);
		mojProfil.setVisible(true);
		frame.getContentPane().add(mojProfil);
		mojProfil.getContentPane().setLayout(null);

		/*
		 * aktivni student
		 */
		MStudent student = GetDbTables.getStudentBySifra(LoginGUI.sifStudentActive);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(150, 12, 30, 15);
		mojProfil.getContentPane().add(lblIme);

		JLabel lblImeStud = new JLabel(student.getImeStudent());
		lblImeStud.setBounds(203, 12, 200, 15);
		mojProfil.getContentPane().add(lblImeStud);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(118, 39, 62, 15);
		mojProfil.getContentPane().add(lblPrezime);

		JLabel lblPrezimeStud = new JLabel(student.getPrezStudent());
		lblPrezimeStud.setBounds(203, 39, 200, 15);
		mojProfil.getContentPane().add(lblPrezimeStud);

		JLabel lblBrojIndexa = new JLabel("Broj indexa:");
		lblBrojIndexa.setBounds(96, 66, 84, 15);
		mojProfil.getContentPane().add(lblBrojIndexa);

		JLabel lblBrojIndexaStud = new JLabel(student.getBrIndexa());
		lblBrojIndexaStud.setBounds(203, 66, 200, 15);
		mojProfil.getContentPane().add(lblBrojIndexaStud);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(105, 93, 75, 15);
		mojProfil.getContentPane().add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setText(student.getPassword());
		txtPassword.setBounds(203, 91, 150, 19);
		mojProfil.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnPromjeniPassword = new JButton("Promjeni");
		btnPromjeniPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String noviPass = txtPassword.getText();
				DBStudent.updatePassword(noviPass, LoginGUI.sifStudentActive);
			}
		});
		btnPromjeniPassword.setBounds(364, 88, 96, 25);
		mojProfil.getContentPane().add(btnPromjeniPassword);

		JLabel lblBrojNegativnihBodova = new JLabel("Broj negativnih bodova:");
		lblBrojNegativnihBodova.setBounds(12, 120, 168, 15);
		mojProfil.getContentPane().add(lblBrojNegativnihBodova);

		JLabel lblNegBodoviStud = new JLabel(String.valueOf(student.getNegBodovi()));
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
				return false; 
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

		DefaultTableModel modelZaduzene = (DefaultTableModel) tableZaduzenja.getModel();
		modelZaduzene.setRowCount(0);
		
	
		/*
		 * Ispisujemo samo od aktivnog studenta pozajmljene knjige. U LoginGUI smo pohranili sifru 
		 * logovanog studenta u sifStudentActive. To radimo u ifu odma nakon for-a.
		 */
		for (MRezervacijaPrimjerakStudent posPrimStud : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
			String invBr = new String();
			String knjigaStr = new String();
			String datPosudbeStr = new String();
			String datVracaanjaStr = new String();
			int upisUtabelu = -1; //u odnosu na ovo je dozvoljen upis u tabelu.
			if (posPrimStud.getSifStudent() == LoginGUI.sifStudentActive) {
				for (MPrimjerak primjerak : GetDbTables.getTablePrimjerak()) {
					if(posPrimStud.getSifPrimjerak() == primjerak.getSifPrimjerak()){
						invBr = primjerak.getInventartniBr(); 

						for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {
							if(knjiga.getSifKnjiga() == primjerak.getSifKnjiga()){
								knjigaStr = knjiga.getOrigNaslov();
								break;
							}
						}
						break;
					}
				}

				//dohvatamo iz tabele posudba, da vidimo kada smo posudili i kada treba vratiti			
				for (MRezervacija rezerv : GetDbTables.getTableRezervacije()) {
					if(rezerv.getSifRezervacija() == posPrimStud.getSifRezervacija() && rezerv.getNastStud() == 2 && rezerv.getOdobrena() == 1){ //0 je za neodobrene rezervacije sto zelimo i da prikazemo
						upisUtabelu = 1;//posto su ispunjeni svi uslovi,dozvoljen je ispis u tabelu

						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						datPosudbeStr = sdf.format(rezerv.getDatRezervacija());
						datVracaanjaStr = sdf.format(rezerv.getDatVracanja());
						break;
					}
				}
			}

			if(upisUtabelu == 1)
				modelZaduzene.addRow(new Object[]{knjigaStr, datPosudbeStr, datVracaanjaStr, invBr});

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

		tableRezervacija = new JTable()
		{

			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
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
		
		DefaultTableModel modelRez = (DefaultTableModel) tableRezervacija.getModel();
		modelRez.setRowCount(0);

		/*
		 * Ispisujemo samo od aktivnog studenta pozajmljene knjige. U LoginGUI smo pohranili sifru 
		 * logovanog studenta u sifStudentActive. To radimo u ifu odma nakon for-a.
		 */
		for (MRezervacijaPrimjerakStudent rps : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
			MPrimjerak primjerak = new MPrimjerak();
			MKnjiga knjiga = new MKnjiga();
			String datPosudbeStrRez = new String();
			String datVracaanjaStrRez = new String();
			int upisUtabelu = -1;
			if (rps.getSifStudent() == LoginGUI.sifStudentActive) {
				for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
					if(rps.getSifPrimjerak() == primj.getSifPrimjerak()){
						primjerak.setInventartniBr(primj.getInventartniBr()); 
						for (MKnjiga knj : GetDbTables.getTableKnjige()) {
							if(knj.getSifKnjiga() == primj.getSifKnjiga()){
								knjiga = knj;
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
					if(rez.getSifRezervacija() == rps.getSifRezervacija() && rez.getNastStud() == 2 && rez.getOdobrena() == 0){
						upisUtabelu = 1;
						SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
						datPosudbeStrRez = sdf.format(rez.getDatRezervacija());
						datVracaanjaStrRez = sdf.format(rez.getDatVracanja());
						break;
					}
				}
			}

			if(upisUtabelu == 1)
				modelRez.addRow(new Object[]{knjiga.getOrigNaslov(), datPosudbeStrRez, datVracaanjaStrRez, primjerak.getInventartniBr()});
		}	
	}
	
	private static void zaduziKnjigu(String origNaslov) {
		JInternalFrame zaduziKnjigu = new JInternalFrame("Zaduži knjigu", true, true, true);
		zaduziKnjigu.setBounds(12, 12, 455, 168);
		zaduziKnjigu.setVisible(true);
		frame.getContentPane().add(zaduziKnjigu);
		zaduziKnjigu.getContentPane().setLayout(null);

		/*
		 * Ako je korisnik iznajmio 3 i vise knjiga, ovaj prozor se nece otvoriti vec poruka 
		 * da je iznajmio vec 3 knjige. PRvo dohvatimo trenutnog korisnika i broj iznajmljenih knjiga
		 * isto vrijedi i za rezervacije
		 */
		MStudent student = new MStudent();
		
		for (MStudent stud : GetDbTables.getTableStudenti()) {
			if(stud.getSifStudent() == LoginGUI.sifStudentActive){
				student.setBrPosudjenihKnjiga(stud.getBrPosudjenihKnjiga());
				student.setBrRezervacija(stud.getBrRezervacija());
				break;
			}
		}

		if (student.getBrPosudjenihKnjiga() >= 3 || student.getBrRezervacija() >= 3) {
			JLabel lblPoruka = new JLabel("Imate već iznajmljene 3 knjige ili izvršene 3 rezervacije ");
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
					 * Dohvacamo odabranu knjigu za rezervaciju iz comboBoxa i pronalazimo je u bazi
					 */
					MKnjiga knjiga = GetDbTables.getKnjigaByOrigNaslov(origNaslov);

					/*
					 * Posto knjige ima na stanju, tada je sifKnjige upisano (nije -1), i onda cemo pronaci 
					 * prvi slobodan primjerak  iz bp tabele "primjerak", i zapamtit njegovu sifru, 
					 * te mu promjeniti "rezervisan" na 1, sto znaci da je  taj primjerak rezervisan.
					 * Takodje posto ima primjerak koji se moze rezervisati, prvo u tabelul "rezervacija"
					 * unosimo podatke, tj kad ce se doci po knjigu i kad ce se vratiti.
					 */
					int sifRezervacija = -1; //sadrzavat ce sifru rezervacije koja je odabrana
					MPrimjerak primjerak = new MPrimjerak(); //sadrzavat ce sifru primjerka knjige koja je rezervisana
					MRezervacija rezervacija = new MRezervacija();
					
					if(knjiga.getSifKnjiga() != -1){
						/*
						 * Pronalazimo prvi slobodan primjerak
						 */
						for (MPrimjerak primjerakPom : GetDbTables.getTablePrimjerak()) {
							if(primjerakPom.getSifKnjiga() == knjiga.getSifKnjiga() && primjerakPom.getRezervisan() != 1){
								primjerak.setSifPrimjerak(primjerakPom.getSifPrimjerak());//dohvatimo prvi primjerak koji je slobodan
								DBPrimjerak.updateRezervisan(1, primjerak.getSifPrimjerak());//mijenjamo rezervisanost primjerka na 1
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
						
						rezervacija.setSifKorisnik(LoginGUI.sifStudentActive); 
						rezervacija.setNastStud(2); 
						
						/*
						 * imamo sada rezervaciju tj datume kad cemo podici i kad cemo vratiti knjigu. 
						 * To sada upisujemo u BP rezervacija
						 */
						DBRezervacija.insertRezervacija(rezervacija);
						
						/*
						 * Pronalazimo sad datume rezervacija, da dobijemo sifRezervacija iz tabele rezervacija za aktivnog korisnika						
						 */
						for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
							//ovaj dio nam treba za poredjenje dva datuma
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
							
							if(rez.getSifKorisnik() == LoginGUI.sifStudentActive && 
									rez.getSifPrimjerak() == primjerak.getSifPrimjerak() &&
									istiDanRezervacije && istiDanVracanja &&
									rez.getNastStud() == 2){
								sifRezervacija = rez.getSifRezervacija();
								break;
							}
						}

						/*
						 * Kako imamo sve sto nam treba, sifPrimjerak, sifStudent, sifRezervacija, to upisujemo
						 * u BP tabelu RezervacijaPrimjerakStudent
						 */
						MRezervacijaPrimjerakStudent rps = new MRezervacijaPrimjerakStudent();
						rps.setSifStudent(LoginGUI.sifStudentActive);
						rps.setSifRezervacija(sifRezervacija);
						rps.setSifPrimjerak(primjerak.getSifPrimjerak());
						
						DBRezervacijaPrimjerakStudent.insertRezPrimjStud(rps);
						
						/*
						 * Posto je izvrsena rezervacija, treba se i povecati br rezervacija za studenta
						 */
						MStudent student = new MStudent();
					
						for (MStudent stud : GetDbTables.getTableStudenti()) {
							if(stud.getSifStudent() == LoginGUI.sifStudentActive){
								student = stud;
								break;
							}
						}
						int trenutniBrRezervacija = student.getBrRezervacija();
						trenutniBrRezervacija++;
						DBStudent.updateBrRezervacija(trenutniBrRezervacija, student.getSifStudent());
						
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
		sveKnjige.setBounds(12, 12, 799, 402);
		sveKnjige.setVisible(true);
		frame.getContentPane().add(sveKnjige);
		sveKnjige.getContentPane().setLayout(null);
		
		prikazSveKnjige(sveKnjige);
	}

	private void sviAutori() {
		JInternalFrame sviAutori = new JInternalFrame("Autori", true, true, true);
		sviAutori.setBounds(12, 12, 282, 357);
		sviAutori.setVisible(true);
		frame.getContentPane().add(sviAutori);
		sviAutori.getContentPane().setLayout(null);

		prikazSviAutori(sviAutori);
	}

	private void sviIzdavaci() {
		JInternalFrame sviIzdavaci = new JInternalFrame("Izdavači", true, true, true);
		sviIzdavaci.setBounds(12, 12, 293, 361);
		sviIzdavaci.setVisible(true);
		frame.getContentPane().add(sviIzdavaci);
		sviIzdavaci.getContentPane().setLayout(null);
		
		prikazSviIzdavaci(sviIzdavaci);
	}

	private void sviNastavnici() {
		JInternalFrame sviNastavnici = new JInternalFrame("Nastavnici", true, true, true);
		sviNastavnici.setBounds(12, 12, 484, 346);
		sviNastavnici.setVisible(true);
		frame.getContentPane().add(sviNastavnici);
		sviNastavnici.getContentPane().setLayout(null);
		
		prikazSviNastavnici(sviNastavnici);
	}

	private void sviStudenti() {
		JInternalFrame sviStudenti = new JInternalFrame("Studenti", true, true, true);
		sviStudenti.setBounds(12, 12, 430, 361);
		sviStudenti.setVisible(true);
		frame.getContentPane().add(sviStudenti);
		sviStudenti.getContentPane().setLayout(null);
		
		prikazSviStudenti(sviStudenti);
	}

	private void sviPredmeti() {
		JInternalFrame sviPredmeti = new JInternalFrame("Predmeti", true, true, true);
		sviPredmeti.setBounds(12, 12, 682, 476);
		sviPredmeti.setVisible(true);
		frame.getContentPane().add(sviPredmeti);
		sviPredmeti.getContentPane().setLayout(null);
		
		prikazSviPredmeti(sviPredmeti);
	}
	
	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor za zaduzivanje te knjige
	 */
	public static void prikazSveKnjige(JInternalFrame sveKnjige) {
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

		
		
		/*
		 * punimo tabelu studentima
		 */
		for (MKnjiga knjiga : GetDbTables.getTableKnjige()) {

			/*
			 * trebamo dohvatiti vrstu knjige i izdavaca na osnovu trenutne knjige
			 */
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

			/*
			 * Trebamo samo godinu izdanja(bez mjeseca i dana)
			 */
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

	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor sa knjigama od tog autora
	 */
	public static void prikazSviAutori(JInternalFrame sviAutori) {
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

	}

	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor sa knjigama od tih izdavaca
	 */	
	public static void prikazSviIzdavaci(JInternalFrame sviIzdavaci) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 259, 274);
		sviIzdavaci.getContentPane().add(scrollPane);

		tableIzdavaci = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableIzdavaci);
		tableIzdavaci.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Izdava\u010Di"
				}
				));
		tableIzdavaci.getColumnModel().getColumn(0).setPreferredWidth(283);

		DefaultTableModel model = (DefaultTableModel) tableIzdavaci.getModel();
		model.setRowCount(0);

		for (MIzdavac izdavac : GetDbTables.getTableIzdavaci()) {
			model.addRow(new Object[]{izdavac.getNazIzdavac()}); //upisujemo u tabelu
		}
		
		txtFilterIzdavaci = new JTextField();
		txtFilterIzdavaci.setBounds(12, 12, 259, 19);
		sviIzdavaci.getContentPane().add(txtFilterIzdavaci);
		txtFilterIzdavaci.setColumns(10);
		
		filterTable(tableIzdavaci, txtFilterIzdavaci);

		
	}

	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor sa knjigama od tih nastavnika
	 */	
	private void prikazSviNastavnici(JInternalFrame sviNastavnici) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 450, 259);
		sviNastavnici.getContentPane().add(scrollPane);

		tableNastavnici = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableNastavnici);
		tableNastavnici.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Prezime i ime", "Zvanje"
				}
				));
		tableNastavnici.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableNastavnici.getColumnModel().getColumn(1).setPreferredWidth(100);
		//tableNastavnici.getColumnModel().getColumn(2).setPreferredWidth(232);

		DefaultTableModel model = (DefaultTableModel) tableNastavnici.getModel();
		model.setRowCount(0);

		for (MNastavnik nastavnik : GetDbTables.getTableNastavnici()) {
			model.addRow(new Object[]{nastavnik.getPrezNastavnik() + " " + nastavnik.getImeNastavnik(), nastavnik.getZvanje()}); //upisujemo u tabelu
		}
		

		txtFilterNastavnici = new JTextField();
		txtFilterNastavnici.setBounds(12, 12, 250, 19);
		sviNastavnici.getContentPane().add(txtFilterNastavnici);
		txtFilterNastavnici.setColumns(10);
		
		JRadioButton rdbtnPrezimeIme = new JRadioButton("Prezime i ime");
		rdbtnPrezimeIme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableNastavnici, 0, txtFilterNastavnici);
			}
		});
		rdbtnPrezimeIme.setBounds(270, 12, 119, 23);
		sviNastavnici.getContentPane().add(rdbtnPrezimeIme);
		rdbtnPrezimeIme.setSelected(true);
		if(rdbtnPrezimeIme.isSelected()){
			filterTableByColumn(tableNastavnici, 0, txtFilterNastavnici);
		}
		
		JRadioButton rdbtnZvanje = new JRadioButton("Zvanje");
		rdbtnZvanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTableByColumn(tableNastavnici, 2, txtFilterNastavnici);
			}
		});
		rdbtnZvanje.setBounds(393, 12, 73, 23);
		sviNastavnici.getContentPane().add(rdbtnZvanje);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPrezimeIme);
		group.add(rdbtnZvanje);

	}

	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor sa knjigama tih studenata
	 */	
	private void prikazSviStudenti(JInternalFrame sviStudenti) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 400, 273);
		sviStudenti.getContentPane().add(scrollPane);

		tableStudenti = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			     return false; //onemogucujemo editovanje nakon dva klika
			}
		};
		scrollPane.setViewportView(tableStudenti);
		tableStudenti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Sifra", "Prezime i Ime", "Semestar"
				}
				));
		tableStudenti.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStudenti.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableStudenti.getColumnModel().getColumn(2).setPreferredWidth(50);

		DefaultTableModel model = (DefaultTableModel) tableStudenti.getModel();
		model.setRowCount(0);
		
		for (MStudent student : GetDbTables.getTableStudenti()) {

			String semestarStr = new String();
			for (MSemestar semestar : GetDbTables.getTableSemestr()) {
				if(student.getSifSemestar() == semestar.getSifSemestar()){
					semestarStr = semestar.getSemestar();
				}
			}
			model.addRow(new Object[]{student.getSifStudent(), student.getPrezStudent() + " " + student.getImeStudent(), semestarStr}); //upisujemo u tabelu
		}
		
		txtFilterStudent = new JTextField();
		txtFilterStudent.setBounds(12, 12, 400, 19);
		sviStudenti.getContentPane().add(txtFilterStudent);
		txtFilterStudent.setColumns(10);
		
		filterTable(tableStudenti, txtFilterStudent);
	}
	
	/*
	 * zavrseno filtriranje po kolonama samo, treba staviti da ako klikne 2 puta na kolonu da izbaci prozor sa knjigama za taj predmet
	 */	
	public static void prikazSviPredmeti(JInternalFrame sviPredmeti) {
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
	}

	
	/*
	 * filtriranje tabela
	 */
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
