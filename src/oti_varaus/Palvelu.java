package oti_varaus;

public class Palvelu {
	private int id;
	private String nimi;
	private String alue;
	private String kuvaus;
	private double hinta;
	private static int maara;
	
	public Palvelu (int id, String nimi, String alue, String kuvaus, double hinta) {
		this.id = id;
		this.nimi = nimi;
		this.alue = alue;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}
	
	public Palvelu() {
		nimi = "";
	}

	public String toString() {
		return nimi + ", " + alue + ", " + hinta + "€ \n" + kuvaus;
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
	 * @return the nimi
	 */
	public String getNimi() {
		return nimi;
	}

	/**
	 * @param nimi the nimi to set
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	/**
	 * @return the alue
	 */
	public String getAlue() {
		return alue;
	}

	/**
	 * @param alue the alue to set
	 */
	public void setAlue(String alue) {
		this.alue = alue;
	}

	/**
	 * @return the kuvaus
	 */
	public String getKuvaus() {
		return kuvaus;
	}

	/**
	 * @param kuvaus the kuvaus to set
	 */
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
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
	
	/**
	 * @return the maara
	 */
	public static int getMaara() {
		return maara;
	}

}
