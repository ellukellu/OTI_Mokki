import java.util.Date;

/**
 * 
 * @author ilta
 */
public class Varaus {
	private int id;
	private Date pvm;
	private String pvm_s;
	private int mokki_id;
	private String mokki_nimi;
	private double hinta;
	private int paivia;
	
	public Varaus (int id, Date pvm) {
		this.id = id;
		this.pvm = pvm;
		
	}
	
	public String toString() {
		return pvm_s + ", varaus " + id + ": " + mokki_nimi + " (id " + mokki_id + "), "
				+ paivia + " päivää, " + String.format("%.2f", hinta) + "€";
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pvm
	 */
	public Date getPvm() {
		return pvm;
	}
	/**
	 * @param pvm the pvm to set
	 */
	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}

	/**
	 * @return the mokki_id
	 */
	public int getMokki_id() {
		return mokki_id;
	}

	/**
	 * @param mokki_id the mokki_id to set
	 */
	public void setMokki_id(int mokki_id) {
		this.mokki_id = mokki_id;
	}

	/**
	 * @return the mokki_nimi
	 */
	public String getMokki_nimi() {
		return mokki_nimi;
	}

	/**
	 * @param mokki_nimi the mokki_nimi to set
	 */
	public void setMokki_nimi(String mokki_nimi) {
		this.mokki_nimi = mokki_nimi;
	}

	/**
	 * @return the hinta
	 */
	public double getHinta() {
		return hinta;
	}

	/**
	 * @param hinta the hinta to set
	 */
	public void setHinta(double hinta) {
		this.hinta = hinta*paivia;
	}

	/**
	 * @return the paivia
	 */
	public int getPaivia() {
		return paivia;
	}

	/**
	 * @param paivia the paivia to set
	 */
	public void setPaivia(int paivia) {
		this.paivia = paivia;
	}

	/**
	 * @return the pvm_s
	 */
	public String getPvm_s() {
		return pvm_s;
	}

	/**
	 * @param pvm_s the pvm_s to set
	 */
	public void setPvm_s(String pvm_s) {
		this.pvm_s = pvm_s;
	}
	
	
}
