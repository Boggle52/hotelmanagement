

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
 * Servlet implementation class selectavailServlet
 */
@WebServlet("/selectavail")
public class selectavailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectavailServlet() {
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
		hDto.setTypenum(Integer.parseInt(request.getParameter("typenum")));
		hDto.setPerson(Integer.parseInt(request.getParameter("person")));
		hDto.setBegindate(request.getParameter("begindate"));
		hDto.setEnddate(request.getParameter("enddate"));
		hotelreservationDAO dao = new hotelreservationDAO();
		ArrayList<hotelreservationDTO> list = dao.availRoom(hDto);
		JSONArray ja = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			hotelreservationDTO hDto2 = new hotelreservationDTO();
			hDto2 = list.get(i);
			JSONObject jo = new JSONObject();
			jo.put("booknum",hDto2.getBooknum());
			jo.put("roomnum",hDto2.getRoomnum());
			jo.put("roomname",hDto2.getRoomname());
			jo.put("roomtype",hDto2.getRoomtype());
			jo.put("person",hDto2.getPerson());
			jo.put("price",hDto2.getPrice());
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
