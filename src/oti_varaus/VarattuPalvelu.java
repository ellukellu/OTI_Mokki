package oti_varaus;

/**
 * 
 * @author ilta
 */
public class VarattuPalvelu {
	private String nimi;
	private int id;
	private int varaus_id;
	private int maara;
	private double hinta;
	private String pvm = "";
	
	public VarattuPalvelu(String nimi, int id, int maara, double hinta) {
		this.nimi = nimi;
		this.id = id;
		this.maara = maara;
		this.hinta = hinta * maara;
	}
	
	public String toString() {
		return  maara + "* " + nimi;
	}
	
	public String raportti() {
		return pvm + ", varaus " + varaus_id + ": " + maara + "* " + nimi + 
				" (" + id + "), " + hinta + "€";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the maara
	 */
	public int getMaara() {
		return maara;
	}

	/**
	 * @return the varaus_id
	 */
	public int getVaraus_id() {
		return varaus_id;
	}

	/**
	 * @param varaus_id the varaus_id to set
	 */
	public void setVaraus_id(int varaus_id) {
		this.varaus_id = varaus_id;
	}

	/**
	 * @return the pvm
	 */
	public String getPvm() {
		return pvm;
	}

	/**
	 * @param pvm the pvm to set
	 */
	public void setPvm(String pvm) {
		this.pvm = pvm;
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
		this.hinta = hinta;
	}
	
}
