import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import oti_varaus.MokkiKriteerit;
import OTI_Projekti.Palveluntarjoaja;

/**
 * 
 * @author ilta
 */
public class Mokkiraportti extends JFrame {
	
	private static String alku;
	private static String loppu;
	private static List<Varaus> varaukset = new ArrayList<>();
	private static DefaultListModel<String> raportit = new DefaultListModel<>();
	
	private static JLabel lblNewLabel_2 = new JLabel("Yhteensä: 0,00€");
	private static JList<String> list = new JList<String>();
	
	private static String p1 = "01";
	private static String p2 = "01";
	private static String k1 = "01";
	private static String k2 = "01";
	private static String v1 = "2022";
	private static String v2 = "2022";

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		haeRaportit();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mokkiraportti frame = new Mokkiraportti();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mokkiraportti() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Raportti varatuista mökeistä");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Alkuajankohta:");
		
		JComboBox<Object> comboBox = new JComboBox<Object>(MokkiKriteerit.paivat());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1 = comboBox.getSelectedItem().toString();
			};
		});
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(MokkiKriteerit.kuut());
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k1 = comboBox_1.getSelectedItem().toString();
			};
		});
		
		JComboBox<Object> comboBox_2 = new JComboBox<Object>(MokkiKriteerit.paivat());
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2 = comboBox_2.getSelectedItem().toString();
			};
		});
		
		JComboBox<Object> comboBox_3 = new JComboBox<Object>(MokkiKriteerit.kuut());
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k2 = comboBox_3.getSelectedItem().toString();
			};
		});
		
		JComboBox<Object> comboBox_4 = new JComboBox<Object>(MokkiKriteerit.vuodet());
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1 = comboBox_4.getSelectedItem().toString();
			};
		});
		
		
		JComboBox<Object> comboBox_4_1 = new JComboBox<Object>(MokkiKriteerit.vuodet());
		comboBox_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v2 = comboBox_4_1.getSelectedItem().toString();
			};
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("Loppuajankohta:");
		
		JButton btnNewButton = new JButton("Takaisin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton("Hae");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				haeVaraukset();
				haeMokkitiedot();
				haeRaportit();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(134, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(100))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_4_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(111, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(80)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
		
		scrollPane.setViewportView(list);
	}
	
	private static void haeVaraukset() {
		varaukset.clear();
		int id;
		String pvm;
		Date v_pvm;
		int mokki_id;
		
		String alku;
		String loppu;
		Date v_alku;
		Date v_loppu;
		long aikaa;
		int paivia;
		
		Date a = haeAlkuPaiva();
		Date b = haeLoppuPaiva();
		
		int i = 0;
		
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from varaus order by vahvistus_pvm");
	        while (resultSet.next()){
	        	pvm = resultSet.getString("vahvistus_pvm");
	        	alku = resultSet.getString("varattu_alkupvm");
	        	loppu = resultSet.getString("varattu_loppupvm");
	        	try {
					v_pvm = MokkiKriteerit.s.parse(pvm);
					v_alku = MokkiKriteerit.s.parse(alku);
					v_loppu = MokkiKriteerit.s.parse(loppu);
					if (!v_pvm.before(a) && !v_pvm.after(b)) {
			        	id = resultSet.getInt("varaus_id");
			        	varaukset.add(new Varaus(id, v_pvm));
			        	mokki_id = resultSet.getInt("mokki_mokki_id");
			        	aikaa = v_loppu.getTime() - v_alku.getTime();
			        	paivia = (int) TimeUnit.DAYS.convert(aikaa, TimeUnit.MILLISECONDS);
			        	varaukset.get(i).setPvm_s(pvm);
			        	varaukset.get(i).setMokki_id(mokki_id);
			        	varaukset.get(i).setPaivia(paivia);
			        	i++;
		        	}
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Virhe päivämäärien kääntämisessä.");
					e.printStackTrace();
				}
	        }
	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Virhe varausten haussa.");
		}
	}
	
	private static void haeMokkitiedot() {
		String n;
		double h;
		int id;
		
		if (!varaukset.isEmpty()) {
			for (int i=0; i<varaukset.size();i++) {
				try {
			        Connection con=Palveluntarjoaja.getCon();
			        Statement st = con.createStatement();
			        ResultSet resultSet = st.executeQuery("Select * from mokki order by mokki_id");
			        	while (resultSet.next()){
			        		id = resultSet.getInt("mokki_id");
			        		if (varaukset.get(i).getMokki_id()==id) {
			        			n = resultSet.getString("mokkinimi");
			        			h = resultSet.getDouble("hinta");
			        			varaukset.get(i).setMokki_nimi(n);
			        			varaukset.get(i).setHinta(h);
			        			System.out.println("varauksen " +i+ " mökki haettu");
			        			}}
			        resultSet.close();
			        st.close();
				}
			catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Virhe palvelulistan teossa.");
			}}}}
	
	private static void haeRaportit() {
		raportit.clear();
		double tulot = 0;
		if (!varaukset.isEmpty()) {
			for (Varaus v : varaukset) {
				raportit.addElement(v.toString());
				tulot = tulot + v.getHinta();
			}
		}
		if (raportit.isEmpty())
			raportit.addElement("Ei tehtyjä mökkivarauksia valitulla ajanjaksolla.");
		lblNewLabel_2.setText("Yhteensä: " + String.format("%.2f", tulot) + "€");
		list = new JList<String>(raportit);
	}
	
	protected static Date haeAlkuPaiva() {
		alku = p1+"."+k1+"."+v1;
		Date a;
		try {
			a = MokkiKriteerit.s.parse(alku);
		} catch (ParseException e) {
			e.printStackTrace();
			Instant b = Instant.now();
			a = Date.from(b);
		}
		return a;
	}
	
	protected static Date haeLoppuPaiva() {
		loppu = p2+"."+k2+"."+v2;
		Date a;
		try {
			a = MokkiKriteerit.s.parse(loppu);
		} catch (ParseException e) {
			e.printStackTrace();
			Instant b = Instant.now();
			a = Date.from(b);
		}
		return a;
	}
}