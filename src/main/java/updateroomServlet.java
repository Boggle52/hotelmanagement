

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateroomServlet
 */
@WebServlet("/updateroom")
public class updateroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateroomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int roomnum = Integer.parseInt(request.getParameter("roomnum"));
		String roomname = request.getParameter("roomname");
		int typenum = Integer.parseInt(request.getParameter("typenum"));
		int person = Integer.parseInt(request.getParameter("person"));
		int price = Integer.parseInt(request.getParameter("price"));
		hotelroomDTO hDto = new hotelroomDTO();
		hDto.setRoomnum(roomnum);
		hDto.setRoomname(roomname);
		hDto.setTypenum(typenum);
		hDto.setPerson(person);
		hDto.setPrice(price);
		hotelroomDAO dao = new hotelroomDAO();
		dao.updateRoom(hDto);
//		dao.updateRoom_org(hDto);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
