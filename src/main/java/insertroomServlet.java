

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertroomServlet
 */
@WebServlet("/insertroom")
public class insertroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertroomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelroomDTO hDto = new hotelroomDTO();
		hDto.setRoomname(request.getParameter("roomname"));
		hDto.setTypenum(Integer.parseInt(request.getParameter("typenum")));
		hDto.setPerson(Integer.parseInt(request.getParameter("person")));
		hDto.setPrice(Integer.parseInt(request.getParameter("price")));
		hotelroomDAO dao = new hotelroomDAO();
		dao.addnewRoom(hDto);
//		dao.addnewRoom_org(hDto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
