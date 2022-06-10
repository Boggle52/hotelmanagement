import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class hotelreservationDAO {
	
	private Statement stmt;
	private Connection conn;
	
	public ArrayList<hotelreservationDTO> selectbook(hotelreservationDTO hDto){
		ArrayList<hotelreservationDTO> list = new ArrayList<hotelreservationDTO>();
		try {
			connDB();
			String query = "select a.booknum, b.roomname, to_char(a.begindate,'yyyymmdd') begindate, "
					+ "to_char(a.enddate,'yyyymmdd') enddate, a.name, b.typenum "
					+ "from book a, hotelroom b "
					+ "where a.roomnum=b.roomnum and b.typenum=? "
					+ "and ((begindate between ? and ? or enddate between ? and ?) "
					+ "or (begindate<=? and enddate>=?)) "
					+ "order by begindate";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, hDto.getTypenum());
			psmt.setString(2, hDto.getBegindate());
			psmt.setString(3, hDto.getEnddate());
			psmt.setString(4, hDto.getBegindate());
			psmt.setString(5, hDto.getEnddate());
			psmt.setString(6, hDto.getBegindate());
			psmt.setString(7, hDto.getEnddate());
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				int booknum = rs.getInt("booknum");
				String roomname = rs.getString("roomname");
				String begindate = rs.getString("begindate");
				String enddate = rs.getString("enddate");
				String name = rs.getString("name");
				hotelreservationDTO hDto2 = new hotelreservationDTO();
				hDto2.setBooknum(booknum);
				hDto2.setRoomname(roomname);
				hDto2.setBegindate(begindate);
				hDto2.setEnddate(enddate);
				hDto2.setName(name);
				list.add(hDto2);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<hotelreservationDTO> availRoom(hotelreservationDTO hDto) {
		ArrayList<hotelreservationDTO> list = new ArrayList<hotelreservationDTO>();
		try {
			connDB();
			String query = "select distinct a.roomnum, a.roomname, b.roomtype, a.person, a.price "
					+ "from hotelroom a, roomtype b where a.typenum=b.typenum and "
					+ "a.typenum=? and a.person>=? and roomnum not in "
					+ "(select roomnum from book where "
					+ "((begindate between ? and ? or enddate between ? and ?) "
					+ "or (begindate<=? and enddate>=?))) "
					+ "order by a.person";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, hDto.getTypenum());
			psmt.setInt(2, hDto.getPerson());
			psmt.setString(3, hDto.getBegindate());
			psmt.setString(4, hDto.getEnddate());
			psmt.setString(5, hDto.getBegindate());
			psmt.setString(6, hDto.getEnddate());
			psmt.setString(7, hDto.getBegindate());
			psmt.setString(8, hDto.getEnddate());
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				int roomnum = rs.getInt("roomnum");
				String roomname = rs.getString("roomname");
				String roomtype = rs.getString("roomtype");
				int person = rs.getInt("person");
				int price = rs.getInt("price");
				hotelreservationDTO hDto2 = new hotelreservationDTO();
				hDto2.setRoomnum(roomnum);
				hDto2.setRoomname(roomname);
				hDto2.setRoomtype(roomtype);
				hDto2.setPerson(person);
				hDto2.setPrice(price);
				list.add(hDto2);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public ArrayList<hotelreservationDTO> listbook(){
//		ArrayList<hotelreservationDTO> list = new ArrayList<hotelreservationDTO>();
//		try {
//			connDB();
//			String query = "select a.booknum, b.roomname, "
//					+ "to_char(a.begindate,'yyyymmdd') begindate, "
//					+ "to_char(a.enddate,'yyyymmdd') enddate, a.name "
//					+ "from book a, hotelroom b "
//					+ "where a.roomnum=b.roomnum "
//					+ "and enddate>=sysdate "
//					+ "order by begindate";
//			this.stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()) {
//				int booknum = rs.getInt("booknum");
//				String roomname = rs.getString("roomname");
//				String begindate = rs.getString("begindate");
//				String enddate = rs.getString("enddate");
//				String name = rs.getString("name");
//				hotelreservationDTO hDto = new hotelreservationDTO();
//				hDto.setBooknum(booknum);
//				hDto.setRoomname(roomname);
//				hDto.setBegindate(begindate);
//				hDto.setEnddate(enddate);
//				hDto.setName(name);
//				list.add(hDto);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//	
//	public ArrayList<hotelreservationDTO> listavail(){
//		ArrayList<hotelreservationDTO> list = new ArrayList<hotelreservationDTO>();
//		try {
//			connDB();
//			String query = "select a.roomname, b.roomtype, a.person, a.price "
//				+ "from hotelroom a, roomtype b "
//				+ "where a.typenum=b.typenum "
//				+ "order by a.person";
//			this.stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()) {
//				String roomname = rs.getString("roomname");
//				String roomtype = rs.getString("roomtype");
//				int person = rs.getInt("person");
//				int price = rs.getInt("price");
//				hotelreservationDTO hDto = new hotelreservationDTO();
//				hDto.setRoomname(roomname);
//				hDto.setRoomtype(roomtype);
//				hDto.setPerson(person);
//				hDto.setPrice(price);
//				list.add(hDto);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	public void insertbook(hotelreservationDTO hDto) {
		try {
			connDB();
			String query = "insert into book values(book_seq.nextval,?,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, hDto.getRoomnum());
			psmt.setInt(2, hDto.getPerson());
			psmt.setString(3, hDto.getBegindate());
			psmt.setString(4, hDto.getEnddate());
			psmt.setString(5, hDto.getName());
			psmt.setInt(6, hDto.getMobile());
			psmt.setInt(7, hDto.getTotal());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatebook(hotelreservationDTO hDto) {
		try {
			connDB();
			String query = "update book set person=?,name=?,mobile=? where booknum=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, hDto.getPerson());
			psmt.setString(2, hDto.getName());
			psmt.setInt(3, hDto.getMobile());
			psmt.setInt(4, hDto.getBooknum());
			psmt.executeUpdate();
			psmt.close();
			conn.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<hotelreservationDTO> selectdetail(int data){
		ArrayList<hotelreservationDTO> list = new ArrayList<hotelreservationDTO>();
		try {
			connDB();
			String query = "select b.roomname, c.roomtype, a.person, "
					+ "to_char(a.begindate,'yyyy-mm-dd') begindate, "
					+ "to_char(a.enddate,'yyyy-mm-dd') enddate, "
					+ "a.name, a.mobile, a.total, b.person availperson, b.typenum "
					+ "from book a, hotelroom b, roomtype c "
					+ "where booknum=? and a.roomnum=b.roomnum and b.typenum=c.typenum";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, data);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				String roomname = rs.getString("roomname");
				String roomtype = rs.getString("roomtype");
				int person = rs.getInt("person");
				String begindate = rs.getString("begindate");
				String enddate = rs.getString("enddate");
				String name = rs.getString("name");
				int mobile = rs.getInt("mobile");
				int total = rs.getInt("total");
				int availperson = rs.getInt("availperson");
				hotelreservationDTO hDto = new hotelreservationDTO();
				hDto.setRoomname(roomname);
				hDto.setRoomtype(roomtype);
				hDto.setPerson(person);
				hDto.setBegindate(begindate);
				hDto.setEnddate(enddate);
				hDto.setName(name);
				hDto.setMobile(mobile);
				hDto.setTotal(total);
				hDto.setAvailperson(availperson);
				list.add(hDto);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deletebook(int number) {
		try {
			connDB();
			String query = "delete from book where booknum=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, number);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connDB() {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		try {
			Class.forName(driver);
			this.conn=DriverManager.getConnection(url,userid,passcode);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
