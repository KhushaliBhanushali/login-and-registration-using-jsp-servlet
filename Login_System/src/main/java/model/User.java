package model;

public class User{
	protected int id;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String username;
	protected String phone;
	protected String password;
	protected String status;
	protected String last_login;
	protected String created_date;
	protected String role;
	
	public User() {
		super();
	}

	public User(int id) {
		super();
		this.id = id;
	}
	
	public User(int id, String firstname, String lastname, String email, String username, String phone,
			String password,String status, String last_login, String created_date, String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.status = status;
		this.last_login = last_login;
		this.created_date = created_date;
		this.role = role;
	}

	public User(String firstname, String lastname, String email, String username, String phone, String status,
			String password,String last_login, String created_date, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.status = status;
		this.last_login = last_login;
		this.created_date = created_date;
		this.role = role;
	}

	public User(String firstname, String lastname, String email, String username, String phone, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.phone = phone;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", username="
				+ username + ", phone=" + phone + ", password=" + password + ", status="
				+ status + ", last_login=" + last_login + ", created_date=" + created_date + ", role=" + role + "]";
	}
	
	
	
}