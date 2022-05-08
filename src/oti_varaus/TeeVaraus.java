package oti_varaus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import OTI_Projekti.Palveluntarjoaja;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author ilta
 */
public class TeeVaraus extends JFrame {
	
	private static int varausID;
	private static String id_s;
	private static int asiakasID;
	private static int mokkiID;
	private static LocalDate varausPaiva = LocalDate.now();
	
	private static Object[] asiakasLista = haeAsiakasLista();
	protected static Object[] alueLista = haeAlueLista();
	

	private static JPanel contentPane;
	private static JTextField textField;
	protected static JTextField txtA;
	private static JTextField txtPiv;
	private static JTextField txtP;
	private static JTextField txtP_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		setMokki();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeeVaraus frame = new TeeVaraus();
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
	public TeeVaraus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Uusi varaus");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Varaus ID:");
		
		JLabel lblNewLabel_2 = new JLabel("Asiakas:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(asiakasLista));
		
		JLabel lblNewLabel_3 = new JLabel("Mökki:");
		
		txtA = new JTextField();
		txtA.setEditable(false);
		txtA.setColumns(10);
		setMokki();
		
		JButton btnNewButton = new JButton("Hae");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MokkiKriteerit.setKalleinJaHalvinMokki();
				MokkiKriteerit.haeMokit();
				MokinValinta.valinta = -1;
				new MokkiKriteerit().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 8));
		
		JLabel lblNewLabel_4 = new JLabel("Varauspäivä:");
		
		txtPiv = new JTextField();
		txtPiv.setText(formatDate(varausPaiva));
		txtPiv.setEditable(false);
		txtPiv.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Lisäpalvelut:");
		
		JButton btnNewButton_1 = new JButton("Hae");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Lisapalvelut.haePalvelut();
				Lisapalvelut.haePalveluKuvaukset();
				new Lisapalvelut().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 8));
		
		JLabel lblNewLabel_6 = new JLabel("Majoituksen alku:");
		
		txtP = new JTextField();
		if (!MokkiKriteerit.haeAlkuPaiva().isBefore(varausPaiva))
			txtP.setText(formatDate(MokkiKriteerit.haeAlkuPaiva()));
		txtP.setEditable(false);
		txtP.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Majoituksen loppu:");
		
		txtP_1 = new JTextField();
		if (!MokkiKriteerit.haeLoppuPaiva().isBefore(varausPaiva)
				&& MokkiKriteerit.haeLoppuPaiva().isAfter(MokkiKriteerit.haeAlkuPaiva()))
			txtP_1.setText(formatDate(MokkiKriteerit.haeLoppuPaiva()));
		txtP_1.setEditable(false);
		txtP_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Vahvista");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_s = textField.getText();
				if (tarkistaTietoTyypit() == true) {
					tallennaTiedot();
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("Peruuta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(omatPalvelut()));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
									.addComponent(btnNewButton_3))
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
									.addGap(85)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(txtP_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
													.addComponent(txtP, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
													.addComponent(txtPiv, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
													.addComponent(txtA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
												.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(textField, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
											.addComponent(comboBox, Alignment.LEADING, 0, 135, Short.MAX_VALUE)))))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPiv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(txtP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(txtP_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private static Object[] haeAsiakasLista () {
		List<String> asiakkaat = new ArrayList<>();
		String nimi;
		String en;
		String sn;
		int i;
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from asiakas order by asiakas_id");
	        while (resultSet.next()){
	        	i = resultSet.getInt("asiakas_id");
		        en = resultSet.getString("etunimi");
		        sn = resultSet.getString("sukunimi");
		        nimi = en + " " + sn;
		        asiakkaat.add(i, nimi);
	        }
	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {
			System.out.println("Virhe! asiakas");
		}
		if (asiakkaat.size()==0)
			asiakkaat.add("");
		
		return asiakkaat.toArray();
	}
	
	private static Object[] haeAlueLista() {
		ArrayList<String> alueet = new ArrayList<>();
		String alue;
		int i;
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from alue order by alue_id");
	        while (resultSet.next()) {
	        	i = resultSet.getInt("alue_id");
		        alue = resultSet.getString("nimi");
		        alueet.add(i, alue);
	        }
	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {}
		if (alueet.size()==0)
			alueet.add("");
		
		return alueet.toArray();
	}
	
	private static Object[] omatPalvelut() {
		ArrayList<String> palvelut = new ArrayList<>();
		if (Lisapalvelut.valitutPalvelut.size()!=0) {
			for (int i=0; i<Lisapalvelut.valitutPalvelut.size();i++)
				palvelut.add(Lisapalvelut.valitutPalvelut.get(i).toString());
		}
		else
			palvelut.add("");
		Object[] a = palvelut.toArray();
		return a;
	}
	
	protected static String formatDate (LocalDate d) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyy");
		String a = d.format(f);
		return a;
		
	}
	
	protected static void setMokki() {
		if (MokinValinta.valinta >= 0) {
			mokkiID = MokkiKriteerit.sopivatMokit.get(MokinValinta.valinta).getId();
			txtA.setText(MokkiKriteerit.sopivatMokit.get(MokinValinta.valinta).toString());
		}
	}
	
	/*

	
	protected static void setMokki() {
		if (MokinValinta.valinta >= 0) {
			mokkiID = sopivatMokit.get(MokinValinta.valinta).getId();
			MokinValinta.valinta = -1;
			txtA.setText("Mökki " + mokkiID);
		}
	}
	
	**/
	
	private void tallennaTiedot() {
		setVisible(false);
	}
	
	private static boolean tarkistaTietoTyypit() {
		boolean oikeat = true;
		try {
			varausID = Integer.parseInt(id_s);
		}
		catch (NumberFormatException ex) {
			oikeat = false;
		}
		return oikeat;
	}
}
