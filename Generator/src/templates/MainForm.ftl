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

<#list classes as class>
import action.Open${class.name}FormAction;
</#list>

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
	
	<#list classes as class>
	private JButton btn${class.name} = new JButton();
	</#list>
	<#list classes as class>
	private JMenuItem mi${class.name} = new JMenuItem();
	</#list>
	
	
	public MainForm() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("${main.uiClass.putanjaDoIkonice}"));
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
	
		<#list classes as class>
		btn${class.name}.setFocusable(false);
		btn${class.name}.setAction(new Open${class.name}FormAction());
		toolbar.add(btn${class.name});
		
		</#list>
		
		
        // meni bar
        // meni otvori
		mbMeni.add(mOtvori);
        
        <#list classes as class>
		mi${class.name}.setAction(new Open${class.name}FormAction());
		mi${class.name}.setText("${class.name}");
		mOtvori.add(mi${class.name});
		
		</#list>
        
        
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
		lCentar.setIcon(new javax.swing.ImageIcon("${main.uiClass.putanjaDoSlike}"));
		pCentar.add(lCentar,BorderLayout.CENTER);
		
		// podesavanja prozora
        this.setTitle("${main.uiClass.label}");
		this.setSize(1350, 715);
		this.setMinimumSize(new Dimension(500, 350));
		this.setLocationRelativeTo(null);
		
	}
}
