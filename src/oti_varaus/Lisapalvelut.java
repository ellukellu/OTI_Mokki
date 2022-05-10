package oti_varaus;

import OTI_Projekti.Palveluntarjoaja;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

/**
 * 
 * @author ilta
 */
public class Lisapalvelut extends JFrame {
	
	int valittu;
	int maara = 1;
	
	private static List<Palvelu> palvelut = new ArrayList<>();
	private static DefaultListModel<String> palveluKuvaukset = new DefaultListModel<>();
	protected static List<VarattuPalvelu> valitutPalvelut = new ArrayList<>();

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lisapalvelut frame = new Lisapalvelut();
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
	public Lisapalvelut() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Valitse lisäpalvelut");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Määrä:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Lisää");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (palvelut.size()!=0) {
					Palvelu p = palvelut.get(valittu);
					try {
						maara = Integer.parseInt(textField_1.getText());
						textField_1.setText("");
					}
					catch (NumberFormatException ex) {}
					valitutPalvelut.add(new VarattuPalvelu(p.getNimi(), p.getId(), maara, p.getHinta()));
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnNewButton_1 = new JButton("Peruuta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				tyhjennaListat();
				valitutPalvelut.clear();
				new TeeVaraus().setVisible(true);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnNewButton_1_1 = new JButton("Valmis");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				tyhjennaListat();
				new TeeVaraus().setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
									.addGap(32))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_1, 76, 76, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnNewButton_1, 0, 0, Short.MAX_VALUE)))
									.addGap(30))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addGap(84))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JList<String> list = new JList<String>(palveluKuvaukset);
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
		
		list.addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged (ListSelectionEvent e) {
						valittu = list.getSelectedIndex();
					}
				}
		);
	}
	
	protected static void haePalvelut() {
		int id;
		String nimi;
		String alue;
		String kuvaus;
		double hinta;
		
		try {
	        Connection con=Palveluntarjoaja.getCon();
	        Statement st = con.createStatement();
	        ResultSet resultSet = st.executeQuery("Select * from palvelu order by palvelu_id");
	        while (resultSet.next()){
	        	id = resultSet.getInt("palvelu_id");
	        	nimi = resultSet.getString("nimi");
	        	alue = TeeVaraus.alueLista[resultSet.getInt("alue_id")].toString();
	        	kuvaus = resultSet.getString("kuvaus");
	        	hinta = resultSet.getDouble("hinta");
	        
	        	palvelut.add(new Palvelu (id, nimi, alue, kuvaus, hinta));
	        }

	        resultSet.close();
	        st.close();
		}
		catch (SQLException ex) {}
	}
	
	protected static void haePalveluKuvaukset() {
		for (int i=0; i<palvelut.size();i++)
			palveluKuvaukset.addElement(palvelut.get(i).toString());
		
		if (palveluKuvaukset.size() == 0)
			palveluKuvaukset.addElement("");
	}
	
	protected static void tyhjennaListat() {
		palvelut.clear();
		palveluKuvaukset.clear();
	}
}
