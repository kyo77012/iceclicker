package service;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_struct.Question;
import database.Database;
import database.QuestionTable;

/**
 * Servlet implementation class updatetime
 */
@WebServlet("/updatetime")
public class updatetime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatetime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn = null;
		try {
		  conn = Database.ConnectDatabase();
		  int time = 20160305;
		  Vector<Question> questions = new Vector<Question>();
		  QuestionTable.SelectQuestion( conn, questions );
		  for ( Question question : questions ) {
		    QuestionTable.UpdateTime( conn, question.id, time );
		  } // for
		} // try
		catch ( Exception exception ) {
		  Exception_utility.PrintException( exception );
		} // catch
		finally {
		  Connection_utility.CloseConnection( conn );
		} // finally
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
