/*****************************************************************/
/*          Generisano na osnovu templejta: MainForm.ftl          */
/*****************************************************************/
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import action.OpenJezikFormAction;
import action.OpenKnjigaOgrankaFormAction;
import action.OpenKnjigaFormAction;
import action.OpenIzdavacFormAction;
import action.OpenRezervacijaFormAction;
import action.OpenOgranakFormAction;
import action.OpenClanFormAction;
import action.OpenBibliotekarFormAction;
import action.OpenZaduzenjeFormAction;
import action.OpenOpomenaFormAction;
import action.OpenAutorFormAction;
import action.OpenPrimerakFormAction;
import action.OpenZanrFormAction;
import action.OpenVrstaOpomeneFormAction;

public class MainForm extends javax.swing.JFrame {

	private static MainForm instance = null;
	
	private JPanel pCentar = new JPanel();
	private JLabel lCentar = new JLabel();
	private JPanel pStatusBar = new JPanel();
	private JToolBar toolbar = new JToolBar();
	private JMenuBar mbMeni = new JMenuBar();
	private JMenu mOtvori = new JMenu("Otvori");
	private JMenu mOperacije = new JMenu("Operacije");
	private JMenuItem miIzvestaj = new JMenuItem();
	private JPanel pStatus1;
	private JPanel pStatus2;
	private JPanel pStatus3;
	private JLabel lDatum;
	
	private JButton btnJezik = new JButton();
	private JButton btnKnjigaOgranka = new JButton();
	private JButton btnKnjiga = new JButton();
	private JButton btnIzdavac = new JButton();
	private JButton btnRezervacija = new JButton();
	private JButton btnOgranak = new JButton();
	private JButton btnClan = new JButton();
	private JButton btnBibliotekar = new JButton();
	private JButton btnZaduzenje = new JButton();
	private JButton btnOpomena = new JButton();
	private JButton btnAutor = new JButton();
	private JButton btnPrimerak = new JButton();
	private JButton btnZanr = new JButton();
	private JButton btnVrstaOpomene = new JButton();
	private JMenuItem miJezik = new JMenuItem();
	private JMenuItem miKnjigaOgranka = new JMenuItem();
	private JMenuItem miKnjiga = new JMenuItem();
	private JMenuItem miIzdavac = new JMenuItem();
	private JMenuItem miRezervacija = new JMenuItem();
	private JMenuItem miOgranak = new JMenuItem();
	private JMenuItem miClan = new JMenuItem();
	private JMenuItem miBibliotekar = new JMenuItem();
	private JMenuItem miZaduzenje = new JMenuItem();
	private JMenuItem miOpomena = new JMenuItem();
	private JMenuItem miAutor = new JMenuItem();
	private JMenuItem miPrimerak = new JMenuItem();
	private JMenuItem miZanr = new JMenuItem();
	private JMenuItem miVrstaOpomene = new JMenuItem();
	
	
	public MainForm() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        initComponents();
    }
    
    
    public static MainForm getInstance() {
		if (instance == null) {
			instance = new MainForm();
		}
		return instance;
	}


	private void initComponents() {
		
		// toolbar
        toolbar.setRollover(true);
	
		btnJezik.setFocusable(false);
		btnJezik.setAction(new OpenJezikFormAction());
		toolbar.add(btnJezik);
		
		btnKnjigaOgranka.setFocusable(false);
		btnKnjigaOgranka.setAction(new OpenKnjigaOgrankaFormAction());
		toolbar.add(btnKnjigaOgranka);
		
		btnKnjiga.setFocusable(false);
		btnKnjiga.setAction(new OpenKnjigaFormAction());
		toolbar.add(btnKnjiga);
		
		btnIzdavac.setFocusable(false);
		btnIzdavac.setAction(new OpenIzdavacFormAction());
		toolbar.add(btnIzdavac);
		
		btnRezervacija.setFocusable(false);
		btnRezervacija.setAction(new OpenRezervacijaFormAction());
		toolbar.add(btnRezervacija);
		
		btnOgranak.setFocusable(false);
		btnOgranak.setAction(new OpenOgranakFormAction());
		toolbar.add(btnOgranak);
		
		btnClan.setFocusable(false);
		btnClan.setAction(new OpenClanFormAction());
		toolbar.add(btnClan);
		
		btnBibliotekar.setFocusable(false);
		btnBibliotekar.setAction(new OpenBibliotekarFormAction());
		toolbar.add(btnBibliotekar);
		
		btnZaduzenje.setFocusable(false);
		btnZaduzenje.setAction(new OpenZaduzenjeFormAction());
		toolbar.add(btnZaduzenje);
		
		btnOpomena.setFocusable(false);
		btnOpomena.setAction(new OpenOpomenaFormAction());
		toolbar.add(btnOpomena);
		
		btnAutor.setFocusable(false);
		btnAutor.setAction(new OpenAutorFormAction());
		toolbar.add(btnAutor);
		
		btnPrimerak.setFocusable(false);
		btnPrimerak.setAction(new OpenPrimerakFormAction());
		toolbar.add(btnPrimerak);
		
		btnZanr.setFocusable(false);
		btnZanr.setAction(new OpenZanrFormAction());
		toolbar.add(btnZanr);
		
		btnVrstaOpomene.setFocusable(false);
		btnVrstaOpomene.setAction(new OpenVrstaOpomeneFormAction());
		toolbar.add(btnVrstaOpomene);
		
		
		
        // meni bar
        // meni otvori
		mbMeni.add(mOtvori);
        
		miJezik.setAction(new OpenJezikFormAction());
		miJezik.setText("Jezik");
		mOtvori.add(miJezik);
		
		miKnjigaOgranka.setAction(new OpenKnjigaOgrankaFormAction());
		miKnjigaOgranka.setText("KnjigaOgranka");
		mOtvori.add(miKnjigaOgranka);
		
		miKnjiga.setAction(new OpenKnjigaFormAction());
		miKnjiga.setText("Knjiga");
		mOtvori.add(miKnjiga);
		
		miIzdavac.setAction(new OpenIzdavacFormAction());
		miIzdavac.setText("Izdavac");
		mOtvori.add(miIzdavac);
		
		miRezervacija.setAction(new OpenRezervacijaFormAction());
		miRezervacija.setText("Rezervacija");
		mOtvori.add(miRezervacija);
		
		miOgranak.setAction(new OpenOgranakFormAction());
		miOgranak.setText("Ogranak");
		mOtvori.add(miOgranak);
		
		miClan.setAction(new OpenClanFormAction());
		miClan.setText("Clan");
		mOtvori.add(miClan);
		
		miBibliotekar.setAction(new OpenBibliotekarFormAction());
		miBibliotekar.setText("Bibliotekar");
		mOtvori.add(miBibliotekar);
		
		miZaduzenje.setAction(new OpenZaduzenjeFormAction());
		miZaduzenje.setText("Zaduzenje");
		mOtvori.add(miZaduzenje);
		
		miOpomena.setAction(new OpenOpomenaFormAction());
		miOpomena.setText("Opomena");
		mOtvori.add(miOpomena);
		
		miAutor.setAction(new OpenAutorFormAction());
		miAutor.setText("Autor");
		mOtvori.add(miAutor);
		
		miPrimerak.setAction(new OpenPrimerakFormAction());
		miPrimerak.setText("Primerak");
		mOtvori.add(miPrimerak);
		
		miZanr.setAction(new OpenZanrFormAction());
		miZanr.setText("Zanr");
		mOtvori.add(miZanr);
		
		miVrstaOpomene.setAction(new OpenVrstaOpomeneFormAction());
		miVrstaOpomene.setText("VrstaOpomene");
		mOtvori.add(miVrstaOpomene);
		
        
        
        // menu operacije
    	mbMeni.add(mOperacije);
	        
	    miIzvestaj.setText("Generiši izveštaj");
	    miIzvestaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reporter reporter = new Reporter();
				reporter.generateReport();
			}
		});
	    mOperacije.add(miIzvestaj);
               
		
		// status bar [,,,datum]
		pStatus1 = new JPanel();
		pStatus2 = new JPanel();
		pStatus3 = new JPanel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy.");
		lDatum = new JLabel(sdf.format(new Date()));
		pStatus3.add(lDatum);
		
		pStatus1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pStatus2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pStatus3.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		pStatusBar.setLayout(new GridLayout(1,3));
		pStatusBar.add(pStatus1);
		pStatusBar.add(pStatus2);
		pStatusBar.add(pStatus3);
		pStatusBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		// postavljanje panela na frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		BorderLayout bLayout = new BorderLayout();
		this.setLayout(bLayout);
		pCentar.setLayout(new BorderLayout());
		pCentar.add(toolbar, BorderLayout.NORTH);
		this.add(pCentar, BorderLayout.CENTER);
		this.add(mbMeni,BorderLayout.NORTH);
		this.add(pStatusBar, BorderLayout.SOUTH);
		
		// centralna slika
		lCentar.setIcon(new javax.swing.ImageIcon("images/21.jpg"));
		pCentar.add(lCentar,BorderLayout.CENTER);
		
		// podesavanja prozora
        this.setTitle("Biblioteka");
		this.setSize(1350, 715);
		this.setMinimumSize(new Dimension(500, 350));
		this.setLocationRelativeTo(null);
		
	}
}
