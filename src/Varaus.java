import java.util.Date;

/**
 * 
 * @author ilta
 */
public class Varaus {
	private int id;
	private Date pvm;
	
	public Varaus (int id, Date pvm) {
		this.id = id;
		this.pvm = pvm;
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
	
	
}
