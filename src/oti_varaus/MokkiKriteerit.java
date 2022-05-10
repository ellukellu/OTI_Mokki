package oti_varaus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import OTI_Projekti.Palveluntarjoaja;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author ilta
 */
public class MokkiKriteerit extends JFrame {
	
	private static int kalleinMokki = 500;
	private static int halvinMokki = 0;
	private static int asukkaat;
	private static int hinta = halvinMokki;
	private static int alue = 0; 
	protected static Date alkuPaiva = haeAlkuPaiva();
	protected static Date loppuPaiva = haeLoppuPaiva();
	public static SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
	
	private static Object[] paivat = paivat();
	private static Object[] kuut = kuut();
	private static Object[] vuodet = vuodet();
	private static Integer[] asukkaita = {1, 2, 3, 4, 5, 6, 7, 8};
	
	private static List<Mokki> mokit = new ArrayList<>();
	private static List<Integer> varatutMokit = new ArrayList<>();
	protected static List<Mokki> sopivatMokit = new ArrayList<>();
	
	private static JSlider slider;
	private static JPanel contentPane;
	private static JTextField txtTeksti;
	
	JComboBox<Object> comboBox;
	JComboBox<Object> comboBox_1;
	JComboBox<Object> comboBox_2;
	JComboBox<Object> comboBox_3;
	JComboBox<Object> comboBox_4;
	JComboBox<Object> comboBox_4_1;
	
	private static String p1 = "01";
	private static String p2 = "01";
	private static String k1 = "01";
	private static String k2 = "01";
	private static String v1 = "2022";
	private static String v2 = "2022";
	private static JComboBox<Object> comboBox_4_2;
	private static JComboBox<Integer> comboBox_4_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MokkiKriteerit frame = new MokkiKriteerit();
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
	public MokkiKriteerit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Hae mökkejä");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Majoituksen alku:");
		JLabel lblNewLabel_1_1 = new JLabel("Majoituksen loppu:");
		JLabel lblNewLabel_2 = new JLabel("Alue:");
		JLabel lblNewLabel_3 = new JLabel("Hinnan yläraja:");
		
		slider = new JSlider(JSlider.HORIZONTAL,halvinMokki,kalleinMokki,halvinMokki);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
				public void stateChanged (ChangeEvent e) {
					hinta = slider.getValue();
					txtTeksti.setText(hinta + "€");
			}
		});
		
		txtTeksti = new JTextField();
		txtTeksti.setText(hinta + "€");
		txtTeksti.setEditable(false);
		txtTeksti.setColumns(10);
		
		JButton btnNewButton = new JButton("Hae mökkejä");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MokinValinta().setVisible(true);
				haeSopivatMokit();
				MokinValinta.haeMokkiKuvaukset();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Takaisin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TeeVaraus.txtA.setText(null);
				new TeeVaraus().setVisible(true);
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Henkilömäärä");
		
		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(paivat));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1 = comboBox.getSelectedItem().toString();
			};
		});
		
		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(paivat));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2 = comboBox_1.getSelectedItem().toString();
			};
		});
		
		comboBox_2 = new JComboBox<Object>();
		comboBox_2.setModel(new DefaultComboBoxModel<Object>(kuut));
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k1 = comboBox_2.getSelectedItem().toString();
			};
		});
		
		comboBox_3 = new JComboBox<Object>();
		comboBox_3.setModel(new DefaultComboBoxModel<Object>(kuut));
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k2 = comboBox_3.getSelectedItem().toString();
			};
		});
		
		comboBox_4 = new JComboBox<Object>();
		comboBox_4.setModel(new DefaultComboBoxModel<Object>(vuodet));
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v1 = comboBox_4.getSelectedItem().toString();
			};
		});
		
		comboBox_4_1 = new JComboBox<Object>();
		comboBox_4_1.setModel(new DefaultComboBoxModel<Object>(vuodet));
		comboBox_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v2 = comboBox_4_1.getSelectedItem().toString();
			};
		});
		
		comboBox_4_2 = new JComboBox<Object>();
		comboBox_4_2.setModel(new DefaultComboBoxModel<Object>(TeeVaraus.alueLista));
		comboBox_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alue = comboBox_4_2.getSelectedIndex();
			};
		});
		
		comboBox_4_3 = new JComboBox<Integer>();
		comboBox_4_3.setModel(new DefaultComboBoxModel<Integer>(asukkaita));
		comboBox_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				String a = comboBox_4_3.getSelectedItem().toString();
				try {
					asukkaat = Integer.parseInt(a);
				}
				catch (NumberFormatException ex){
					asukkaat = 1;
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtTeksti, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(slider, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
										.addComponent(btnNewButton_1))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(comboBox_4_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(comboBox_4, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addComponent(lblNewLabel)
											.addComponent(comboBox_4_2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(comboBox_4_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
								.addGap(105)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_4_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox_4_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBox_4_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtTeksti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected static void haeMokit() {
		int id, as, al;
		String nm, kv;
		double h;
		mokit.clear();
		
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from mokki order by mokki_id");
	        while (resultSet.next()) {
	        id = resultSet.getInt("mokki_id");
	        as = resultSet.getInt("henkilomaara");
	        nm = resultSet.getString("mokkinimi");
	        kv = resultSet.getString("kuvaus");
	        al = resultSet.getInt("alue_id");
	        h = resultSet.getDouble("hinta");
	        
	        mokit.add(new Mokki (id, nm, h, kv, as, al));
	        }
	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {System.out.println("Virhe mökkien haussa");}
	}
	
	private static void haeVaraukset() {
		String alku;
		String loppu;
		Date a;
		Date b;
		int id;
		varatutMokit.clear();
		
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from varaus order by varaus_id");
	        while (resultSet.next()) {
	        	alku = resultSet.getString("varattu_alkupvm");
	        	loppu = resultSet.getString("varattu_loppupvm");
	        	
	        	try {
					a = s.parse(alku);
					b = s.parse(loppu);
					
					if (alku!=null && loppu!=null) {
			        	if ((a.after(alkuPaiva)&&a.before(loppuPaiva))||(b.after(alkuPaiva)&&b.before(loppuPaiva))) {
			        		id = resultSet.getInt("mokki_mokki_id");
			        		varatutMokit.add(id);
			        	}}}
	        	catch (ParseException e) {
					e.printStackTrace();
				}}}
		catch (SQLException ex) {}
	}
	
	protected static void haeSopivatMokit () {
		haeVaraukset();
		sopivatMokit.clear();
		if (mokit.size()!=0) {
			for (Mokki m : mokit) {
				if (m.getAlue()==alue && m.getHinta()<=hinta && m.getAsukkaita()>=asukkaat)
					sopivatMokit.add(m);
				}
			for (int i=0;i<sopivatMokit.size();i++) {
				if (varatutMokit.contains(sopivatMokit.get(i).getId()))
					sopivatMokit.remove(i);
				}}
	}
	
	protected static Date haeAlkuPaiva() {
		String a = p1+k1+v1;
		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
		Instant b = Instant.now();
		Date alku = Date.from(b);
		try {
			alku = s.parse(a);
		} catch (ParseException e) {}
		return alku;
	}
	
	protected static Date haeLoppuPaiva() {
		String a = p2+k2+v2;
		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
		Instant b = Instant.now();
		Date loppu = Date.from(b);
		try {
			loppu = s.parse(a);
		} catch (ParseException e) {}
		return loppu;
	}
	
	public static Object[] paivat() {
		List<String> a = new ArrayList<>();
		for (int i=1;i<=31;i++) {
			if (i<10)
				a.add("0"+i);
			else
				a.add(Integer.toString(i));
		}
		return a.toArray();
	}
	
	public static Object[] kuut() {
		List<String> a = new ArrayList<>();
		for (int i=1;i<=12;i++) {
			if (i<10)
				a.add("0"+i);
			else
				a.add(Integer.toString(i));
		}
		return a.toArray();
	}
	
	public static Object[] vuodet() {
		List<String> a = new ArrayList<>();
		int vuosi = 2022;
		for (int i=vuosi;i<=vuosi+7;i++)
			a.add(Integer.toString(i));
		return a.toArray();
	}
	
	protected static void setKalleinJaHalvinMokki() {
		for (Mokki m : mokit) {
			if (m.getHinta()<halvinMokki)
				halvinMokki = (int) Math.ceil(m.getHinta());
			if (m.getHinta()>kalleinMokki)
				kalleinMokki = (int) Math.ceil(m.getHinta());
		}
	}
}