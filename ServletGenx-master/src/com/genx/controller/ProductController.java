package com.genx.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genx.dao.ProductDao;
import com.genx.dao.ProductDaoImpl;
import com.genx.model.Product;

@WebServlet("/")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao todoDAO;

	public void init() {
		todoDAO = new ProductDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTodo(request, response);
				break;
			case "/delete":
				deleteTodo(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTodo(request, response);
				break;
			case "/list":
				listTodo(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listTodo = todoDAO.selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);

	}

	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");

		/*
		 * DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd"); LocalDate
		 * targetDate = LocalDate.parse(request.getParameter("targetDate"),df);
		 */

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Product newTodo = new Product(title, username, description, LocalDate.now(), isDone);
		todoDAO.insertTodo(newTodo);
		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		Product updateTodo = new Product(id, title, username, description, targetDate, isDone);

		todoDAO.updateTodo(updateTodo);

		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("in delete");
		System.out.println(id);
		todoDAO.deleteTodo(id);
		response.sendRedirect("list");
	}
}
