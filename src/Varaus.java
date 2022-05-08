import java.time.LocalDate;

/**
 * 
 * @author ilta
 */
public class Varaus {
	private int id;
	private LocalDate pvm;
	
	public Varaus (int id, LocalDate pvm) {
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
	public LocalDate getPvm() {
		return pvm;
	}
	/**
	 * @param pvm the pvm to set
	 */
	public void setPvm(LocalDate pvm) {
		this.pvm = pvm;
	}
	
	
}
