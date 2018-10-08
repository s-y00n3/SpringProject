package user.domain;

//DTO(Data Transfer Object)
public class LoginDTO {

	private String id;
	private String pw;
	private boolean useCookie;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(String id, String pw, boolean useCookie) {
		super();
		this.id = id;
		this.pw = pw;
		this.useCookie = useCookie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public boolean isUseCookie() {
		return useCookie;
	}

	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", pw=" + pw + ", useCookie=" + useCookie + "]";
	}
	
	
}
