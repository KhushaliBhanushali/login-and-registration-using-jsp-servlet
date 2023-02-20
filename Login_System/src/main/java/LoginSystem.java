
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Helper;
import model.User;

@WebServlet("/")
public class LoginSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;

	@Override
	public void init() throws ServletException {
		String jdbcURL = "jdbc:mysql://localhost:3306/user_db";
		String jdbcUsername = "root";
		String jdbcPassword = "";

		dao = new UserDao(jdbcURL, jdbcUsername, jdbcPassword);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showRegistrationForm(request, response);
				break;

			case "/insert":
				insertUser(request, response);
				break;

			case "/login":
				checkLogin(request, response);
				break;

			case "/forgot":
				showPassword(request, response);
				break;

			case "/profile":
				showUser(request, response);
				break;

			case "/edit":
				editProfile(request, response);
				break;

			case "/update":
				updateProfile(request, response);
				break;

			case "/password":
				editPassword(request, response);
				break;
			case "/changepassword":
				changePassword(request, response);
				break;

			default:
				index(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = dao.checkLogin(email, password);
		if (user != null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
			out.print("Login Successful");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			out.print("Sorry UserName or Password Error!" + email + "--" + password);
			rd.include(request, response);
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		phone = String.valueOf( Helper.randomNumber(1000, 9999));

		User newuser = new User(firstname, lastname, email, username, phone, password);
		dao.insertUser(newuser);
		response.sendRedirect("index.jsp");
		// PrintWriter pr = response.getWriter();
		// pr.print(newuser.toString());
	}

	private void showPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		User user = dao.showPassword(email);
		if (user != null) {
			RequestDispatcher rd = request.getRequestDispatcher("forgot_password.jsp");
			out.print("Your Password is: " + user.getPassword());
			rd.include(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("forgot_password.jsp");
			out.print("Enter your valid email!");
			rd.include(request, response);
		}
	}

	private void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// int id = Integer.parseInt(request.getParameter("id"));
		int id = 1;
		User user = dao.getUserById(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user_profile.jsp");
		dispatcher.forward(request, response);
	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// int id = Integer.parseInt(request.getParameter("id"));
		int id = 1;
		User user = dao.getUserById(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit_profile2.jsp");
		dispatcher.forward(request, response);
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = dao.getUserById(id);
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		// User user = new User(id, firstname, lastname, email, lastname, phone,email,
		// phone, last_login, created_date, last_login);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setPhone(phone);
		dao.updateProfile(user);
		response.sendRedirect("profile");
	}

	private void editPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// int id = Integer.parseInt(request.getParameter("id"));
		int id = 1;
		User user = dao.getUserById(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.jsp");
		dispatcher.forward(request, response);
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		User user = dao.getUserById(id);
		String curr_password = request.getParameter("password");
		String new_password = request.getParameter("password1");
		String conf_password = request.getParameter("password2");

		/*response.setContentType("text/html");
		RequestDispatcher rd1 = request.getRequestDispatcher("change_password.jsp");
		out.print("Please enter your confirm_password same as new_password!" + new_password + "--"
				+ conf_password + user.toString());
		rd1.include(request, response);
		*/

		if ((user.getPassword()).equals(curr_password)) {

			if (new_password.equals(conf_password)) {
				user.setPassword(new_password);
				dao.changePassword(user);
				response.sendRedirect("profile");
			} else {
				response.setContentType("text/html");
				RequestDispatcher rd = request.getRequestDispatcher("change_password.jsp");
				out.print("Please enter your confirm_password same as new_password!" + new_password + "--"
						+ conf_password);
				rd.include(request, response);
			}

		} else {
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("change_password.jsp");
			out.print("Your current password is wrong...Please enter your valid password!");
			rd.include(request, response);
		}
	}
}
