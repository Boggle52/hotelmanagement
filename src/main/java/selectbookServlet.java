

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import oracle.net.aso.h;

/**
 * Servlet implementation class selectbookServlet
 */
@WebServlet("/selectbook")
public class selectbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectbookServlet() {
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
		hDto.setBegindate(request.getParameter("begindate"));
		hDto.setEnddate(request.getParameter("enddate"));
		hDto.setTypenum(Integer.parseInt(request.getParameter("typenum")));
		hotelreservationDAO dao = new hotelreservationDAO();
		ArrayList<hotelreservationDTO> list = dao.selectbook(hDto);
		JSONArray ja = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			hotelreservationDTO hDto2 = new hotelreservationDTO();
			hDto2 = list.get(i);
			JSONObject jo = new JSONObject();
			jo.put("booknum",hDto2.getBooknum());
			jo.put("roomname",hDto2.getRoomname());
			jo.put("begindate",hDto2.getBegindate());
			jo.put("enddate",hDto2.getEnddate());
			jo.put("name",hDto2.getName());
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
