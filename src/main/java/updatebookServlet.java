

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updatebookServlet
 */
@WebServlet("/updatebook")
public class updatebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatebookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int booknum = Integer.parseInt(request.getParameter("booknum"));
		int person = Integer.parseInt(request.getParameter("person"));
		String name = request.getParameter("name");
		int mobile = Integer.parseInt(request.getParameter("mobile")); 
		hotelreservationDTO hDto = new hotelreservationDTO();
		hDto.setBooknum(booknum);
		hDto.setPerson(person);
		hDto.setName(name);
		hDto.setMobile(mobile);
		hotelreservationDAO dao = new hotelreservationDAO();
		dao.updatebook(hDto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
