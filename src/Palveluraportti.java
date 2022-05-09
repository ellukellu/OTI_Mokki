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
import oti_varaus.MokkiKriteerit;
import oti_varaus.VarattuPalvelu;
import OTI_Projekti.Palveluntarjoaja;

/**
 * 
 * @author ilta
 */
public class Palveluraportti extends JFrame {
	
	private static JLabel lblNewLabel_2 = new JLabel();
	private static JList list = new JList();
	
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
					Palveluraportti frame = new Palveluraportti();
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
	public Palveluraportti() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Raportti ostetuista palveluista");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Alkuajankohta:");
		
		JComboBox comboBox = new JComboBox(MokkiKriteerit.paivat());
		
		JComboBox comboBox_1 = new JComboBox(MokkiKriteerit.kuut());
		
		JComboBox comboBox_2 = new JComboBox(MokkiKriteerit.paivat());
		
		JComboBox comboBox_3 = new JComboBox(MokkiKriteerit.kuut());
		
		JComboBox comboBox_4 = new JComboBox(MokkiKriteerit.vuodet());
		
		JComboBox comboBox_4_1 = new JComboBox(MokkiKriteerit.vuodet());
		
		JLabel lblNewLabel_1_1 = new JLabel("Loppuajankohta:");
		
		JButton btnNewButton = new JButton("Takaisin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		lblNewLabel_2.setText((""));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton("Hae");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valikko(p1, comboBox);
				valikko(p2, comboBox_2);
				valikko(k1, comboBox_1);
				valikko(k2, comboBox_3);
				valikko(v1, comboBox_4);
				valikko(v2, comboBox_4_1);
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
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(111)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
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
	}
	
	private static List<Varaus> haeVaraukset() {
		List<Varaus> varaukset = new ArrayList<>();
		int id;
		Date v_pvm;
		Date a = haeAlkuPaiva();
		Date b = haeLoppuPaiva();
		
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from varaus order by vahvistus_pvm");
	        while (resultSet.next()){
	        	v_pvm = resultSet.getDate("vahvistus_pvm");
	        	if (v_pvm.after(a) && v_pvm.before(b)) {
		        	id = resultSet.getInt("varaus_id");
		        	varaukset.add(new Varaus(id, v_pvm));
	        	}
	        }
	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {}
		
		return varaukset;
	}
	
	private static List<VarattuPalvelu> haePalvelut() {
		List<Varaus> varaukset = haeVaraukset();
		List<VarattuPalvelu> palvelut = new ArrayList<>();
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyy");
		String n, pvm;
		int id, vid, m;
		double h;
		int i = 0;
		
		if (!varaukset.isEmpty()) {
			try {
		        Connection con=Palveluntarjoaja.getCon();
		        Statement st = con.createStatement();
		        ResultSet resultSet = st.executeQuery("Select * from varauksen_palvelut order by varaus_id");
		        while (resultSet.next()){
		        	vid = resultSet.getInt("varaus_id");
		        	for (Varaus v : varaukset) {
		        		if (v.getId()==vid) {
		        			pvm = f.format(v.getPvm());
		        			id = resultSet.getInt("palvelu_id");
		        			m = resultSet.getInt("lkm");
		        			Statement st2 = con.createStatement();
		    		        ResultSet resultSet2 = st2.executeQuery("Select * from palvelu order by palvelu_id");
		    		        while (resultSet2.next()){
		    		        	if (id == resultSet2.getInt("palvelu_id")) {
		    		        		n = resultSet2.getString("nimi");
		    		        		h = resultSet2.getDouble("hinta");
		    		        		palvelut.add(new VarattuPalvelu(n, id, m, h));
		    		            	palvelut.get(i).setPvm(pvm);
		    		            	palvelut.get(i).setVaraus_id(vid);
		    		            	i++;
		    		        	}}}}}
		        resultSet.close();
		        st.close();
			}
			catch (SQLException ex) {}}
		
		
		
		if (varaukset.size()!=0) {
			for (Varaus v : varaukset) {
				
			}
		}
		
		return palvelut;
	}
	
	private static void haeRaportit() {
		List<VarattuPalvelu> palvelut = haePalvelut();
		DefaultListModel<String> a = new DefaultListModel<>();
		double tulot = 0;
		
		for (VarattuPalvelu p : palvelut) {
			a.addElement(p.raportti());
			tulot = tulot + p.getHinta();
		}
		if (a.isEmpty())
			a.addElement("Ei ostettuja palveluja valitulla ajanjaksolla.");
		
		lblNewLabel_2.setText("Yhteensä: " + tulot + "€");
		list = new JList (a);
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
		Instant loppu = Instant.now();
		Date b = Date.from(loppu);
		try {
			b = s.parse(a);
		} catch (ParseException e) {}
		return b;
	}
	
	private static void valikko(String s, JComboBox<Integer> box) {
		s = box.getSelectedItem().toString();
		haeRaportit();
	}
}
