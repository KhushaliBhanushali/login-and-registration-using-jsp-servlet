import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public User checkLogin(String email, String password) throws SQLException {

		String sql = "Select * from users where email=? and password=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			
			user = getUserById(id);
			
		}
		resultSet.close();
		statement.close();
		disconnect();
		return user;
	}

	public boolean insertUser(User U1) throws SQLException {
		String sql = "Insert into users(firstname,lastname,username,email,phone,password) values (?,?,?,?,?,?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, U1.getFirstname());
		statement.setString(2, U1.getLastname());
		statement.setString(3, U1.getUsername());
		statement.setString(4, U1.getEmail());
		statement.setString(5, U1.getPhone());
		statement.setString(6, U1.getPassword());

		boolean b = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return b;
	}

	public User showPassword(String email) throws SQLException {

		String sql = "Select * from users where email=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			user = getUserById(id);	
		}
		resultSet.close();
		statement.close();
		disconnect();
		return user;
	}
	
	public List<User> ListAllUser() throws SQLException {
		List<User> listUser = new ArrayList<>();

		String sql = "Select * from users";
		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String email = resultSet.getString("email");
			String username = resultSet.getString("username");
			String phone = resultSet.getString("phone");
			String password = resultSet.getString("password");
			String status = resultSet.getString("status");
			String created_date = resultSet.getString("created_date");
			String last_login = resultSet.getString("last_login");
			String role = resultSet.getString("role");
			
			User user = new User(id, firstname, lastname, email, username, phone, password, status, last_login, created_date, role);
			listUser.add(user);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listUser;
	}
	
	public User getUserById(int id) throws SQLException {
		User user = new User();
		String sql = "Select * from users where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String email = resultSet.getString("email");
			String username = resultSet.getString("username");
			String phone = resultSet.getString("phone");
			String password = resultSet.getString("password");
			String status = resultSet.getString("status");
			String created_date = resultSet.getString("created_date");
			String last_login = resultSet.getString("last_login");
			String role = resultSet.getString("role");
			
			//user = new User(id,firstname,lastname,email,phone,created_date,last_login);
		user = new User(id, firstname, lastname, email, username, phone, password, status, last_login, created_date, role);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return user;
	}
	
	public Boolean updateProfile(User U1) throws SQLException {
		String sql = "Update users set firstname=?,lastname=?,email=?,phone=? where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,U1.getFirstname());
		statement.setString(2, U1.getLastname());
		statement.setString(3, U1.getEmail());
		statement.setString(4, U1.getPhone());
		statement.setInt(5, U1.getId());
		
		boolean b = statement.executeUpdate() > 0;
		statement.close();
		
		disconnect();
		return b;
	}
	
	public Boolean changePassword(User U1) throws SQLException {
		String sql = "Update users set password=? where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, U1.getPassword());
		statement.setInt(2, U1.getId());
		
		boolean b = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return b;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		String sql = "Delete from users where id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setInt(1, id);

		boolean b = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return b;
	}
}
