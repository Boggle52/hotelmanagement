

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class booklistServlet
 */
@WebServlet("/booklist")
public class booklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelreservationDAO dao = new hotelreservationDAO();
		ArrayList<hotelreservationDTO> list = dao.listbook();
		JSONArray ja = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			hotelreservationDTO hDto = new hotelreservationDTO();
			hDto = list.get(i);
			JSONObject jo = new JSONObject();
			jo.put("booknum",hDto.getBooknum());
			jo.put("roomname",hDto.getRoomname());
			jo.put("begindate",hDto.getBegindate());
			jo.put("enddate",hDto.getEnddate());
			jo.put("name",hDto.getName());
			ja.add(jo);
		}
		response.getWriter().print(ja.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
