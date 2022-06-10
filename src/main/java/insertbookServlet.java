

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertbookServlet
 */
@WebServlet("/insertbook")
public class insertbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelreservationDTO hDto = new hotelreservationDTO();
		hDto.setRoomnum(Integer.parseInt(request.getParameter("roomnum")));
		hDto.setPerson(Integer.parseInt(request.getParameter("person")));
		hDto.setBegindate(request.getParameter("begindate"));
		hDto.setEnddate(request.getParameter("enddate"));
		hDto.setName(request.getParameter("name"));
		hDto.setMobile(Integer.parseInt(request.getParameter("mobile")));
		hDto.setTotal(Integer.parseInt(request.getParameter("total")));
		hotelreservationDAO dao = new hotelreservationDAO();
		dao.insertbook(hDto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
