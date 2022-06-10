

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
 * Servlet implementation class selectroomServlet
 */
@WebServlet("/selectroom")
public class selectroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectroomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelroomDAO dao = new hotelroomDAO();
		ArrayList<hotelroomDTO> list = dao.listRoom();
		JSONArray ja = new JSONArray();
		for(int i = 0; i<list.size(); i++) {
			hotelroomDTO hDto = new hotelroomDTO();
			hDto = list.get(i);
			JSONObject jo = new JSONObject();
			jo.put("roomnum",hDto.getRoomnum());
			jo.put("roomname",hDto.getRoomname());
			jo.put("roomtype",hDto.getRoomtype());
			jo.put("person",hDto.getPerson());
			jo.put("price",hDto.getPrice());
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
