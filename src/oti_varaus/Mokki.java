package oti_varaus;

public class Mokki {
	private int id;
	private String nimi;
	private double hinta;
	private String kuvaus;
	private int asukkaita;
	private int alue;
	private static int maara;
	
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
	 * @return the asukkaita
	 */
	public int getAsukkaita() {
		return asukkaita;
	}
	
	/**
	 * @param asukkaita the asukkaita to set
	 */
	public void setAsukkaita(int asukkaita) {
		this.asukkaita = asukkaita;
	}
	
	/**
	 * @return the alue
	 */
	public int getAlue() {
		return alue;
	}
	
	/**
	 * @param alue the alue to set
	 */
	public void setAlue(int alue) {
		this.alue = alue;
	}
	
	/**
	 * @return the maara
	 */
	public int getMaara() {
		return maara;
	}
	
	public Mokki (int id, String nimi, double hinta, String kuvaus, int asukkaita, int alue) {
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.kuvaus = kuvaus;
		this.asukkaita = asukkaita;
		this.alue = alue;
		maara++;
	}

	@Override
	public String toString () {
		return nimi + ", " + asukkaita + " henkeä, " + hinta + "€ \n" + kuvaus;
	}
}
