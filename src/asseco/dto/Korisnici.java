package asseco.dto;

public class Korisnici {
	
	private String username;
	private String password;
	private String imeIPrezime;
	private int admin;
	private int id;
	
	
	
	
	public Korisnici() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Korisnici [username=" + username + ", password=" + password + ", imeIPrezime=" + imeIPrezime
				+ ", admin=" + admin + "]";
	}
	public Korisnici(String username, String password, String imeIPrezime, int admin, int id) {
		super();
		this.username = username;
		this.password = password;
		this.imeIPrezime = imeIPrezime;
		this.admin = admin;
		this.id  = id ;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
