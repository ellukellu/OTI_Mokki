import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import OTI_Projekti.Palveluntarjoaja;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author ilta
 */
public class LisaaPalvelu extends JFrame {
	static String id_s;
	static String palveluNimi;
	static Object[] alueet = alueLista();
	static String[] tyypit = tyyppiLista();
	static String kuvaus;
	static String hinta_s;
	static String alv_s;
	
	static int palveluID;
	static int toimiAlueID = 1;
	static int palveluTyyppi;
	static double hinta;
	static double alv;

	private JPanel contentPane;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JLabel lblNewLabel_4;
	private static JLabel lblNewLabel_5;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JLabel lblNewLabel_6;
	private static JLabel lblNewLabel_7;
	private static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LisaaPalvelu frame = new LisaaPalvelu();
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
	public LisaaPalvelu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Lisää palvelu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Palvelu ID:");
		JLabel lblNewLabel_2 = new JLabel("Nimi:");
		JLabel lblNewLabel_3 = new JLabel("Toimialue:");
		JLabel lblNewLabel_4 = new JLabel("Tyyppi:");
		JLabel lblNewLabel_5 = new JLabel("Kuvaus:");
		JLabel lblNewLabel_6 = new JLabel("Hinta:");
		JLabel lblNewLabel_7 = new JLabel("ALV:");
		
		textArea = new JTextArea();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Tallenna");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_s = textField.getText();
				palveluNimi = textField_1.getText();
				kuvaus = textArea.getText();
				hinta_s = textField_5.getText();
				alv_s = textField_6.getText();
				if (tarkistaTietoTyypit() == true) {
					tallennaTiedot();
					setVisible(false);
					new LisaaPalvelu().setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Syötä tiedot oikeassa muodossa.");
			}
		});
		JButton btnNewButton_1 = new JButton("Sulje");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(alueet));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//toimiAlueID = comboBox.getSelectedIndex();
			};
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(tyypit));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				palveluTyyppi = comboBox_1.getSelectedIndex();
			};
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1))
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
					.addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(184)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
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
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private static Object[] alueLista () {
		List<String> alueet = new ArrayList<>();
		alueet.add("");
		String alue;
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from alue order by alue_id");
	        while (resultSet.next())
	        {
	        alue = resultSet.getString("nimi");
	        alueet.add(alue);
	        }

	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {}
		
		Object[] a = alueet.toArray();
		return a;
	}
	
	private static String[] tyyppiLista () {
		String[] a = {"palvelutyyppi 1", "palvelutyyppi 2"};
		return a;
	}
	
	private static void tallennaTiedot () {
		try {
			Connection con=Palveluntarjoaja.getCon();
			Statement st=con.createStatement();
			st.executeUpdate("Insert into palvelu values('"+palveluID+"','"+toimiAlueID+"','"+palveluNimi+"','"+palveluTyyppi+"','"+kuvaus+"','"+hinta+"','"+alv+"')");
			JOptionPane.showMessageDialog(null, "Palvelu lisätty järjestelmään.");
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Palvelun lisäys epäonnistui.");
		}
	}
	
	private static boolean tarkistaTietoTyypit () {
		boolean oikeat = true;
		try {
			palveluID = Integer.parseInt(id_s);
			hinta = Double.parseDouble(hinta_s);
			alv = Double.parseDouble(alv_s);
		}
		catch (NumberFormatException ex) {
			oikeat = false;
		}
		if (palveluNimi.length() > 40 || kuvaus.length() > 150)
			oikeat = false;
		return oikeat;
	}
	
}
