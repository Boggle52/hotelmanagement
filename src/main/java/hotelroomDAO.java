import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class hotelroomDAO {
	
	private Statement stmt;
	private Connection conn;
	
	public ArrayList<hotelroomDTO> listRoom(){
		ArrayList<hotelroomDTO> list = new ArrayList<hotelroomDTO>();
		try {
			connDB();
			String query = "select a.roomnum, a.roomname, b.roomtype, a.person, a.price "
					+ "from hotelroom a, roomtype b where a.typenum=b.typenum order by person";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int roomnum = rs.getInt("roomnum");
				String roomname = rs.getString("roomname");
				String roomtype = rs.getString("roomtype");
				int person = rs.getInt("person");
				int price = rs.getInt("price");
				hotelroomDTO hDto = new hotelroomDTO();
				hDto.setRoomnum(roomnum);
				hDto.setRoomname(roomname);
				hDto.setRoomtype(roomtype);
				hDto.setPerson(person);
				hDto.setPrice(price);
				list.add(hDto);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addnewRoom(hotelroomDTO hDto) {
		try {
			connDB();
			String query = "insert into hotelroom values(room_seq.nextval,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, hDto.getRoomname());
			psmt.setInt(2, hDto.getTypenum());
			psmt.setInt(3, hDto.getPerson());
			psmt.setInt(4, hDto.getPrice());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void addnewRoom_org(hotelroomDTO hDto) {
//		try {
//			connDB();
//			String query = "insert into hotelroom_org values(room_seq_org.nextval,?,?,?,?)";
//			PreparedStatement psmt = conn.prepareStatement(query);
//			psmt.setString(1, hDto.getRoomname());
//			psmt.setInt(2, hDto.getTypenum());
//			psmt.setInt(3, hDto.getPerson());
//			psmt.setInt(4, hDto.getPrice());
//			psmt.executeUpdate();
//			psmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void updateRoom(hotelroomDTO hDto) {
		try {
			connDB();
			String query = "update hotelroom set roomname=?,typenum=?,person=?,price=? where roomnum=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, hDto.getRoomname());
			psmt.setInt(2, hDto.getTypenum());
			psmt.setInt(3, hDto.getPerson());
			psmt.setInt(4, hDto.getPrice());
			psmt.setInt(5, hDto.getRoomnum());
			psmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void updateRoom_org(hotelroomDTO hDto) {
//		try {
//			connDB();
//			String query = "insert into hotelroom_org values(room_seq_org.nextval,?,?,?,?)";
//			PreparedStatement psmt = conn.prepareStatement(query);
//			psmt.setString(1, hDto.getRoomname());
//			psmt.setInt(2, hDto.getTypenum());
//			psmt.setInt(3, hDto.getPerson());
//			psmt.setInt(4, hDto.getPrice());
//			psmt.executeUpdate();
//			psmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public boolean deleteRoom(int roomnum) {
		try {
			connDB();
			String query = "delete from hotelroom where roomnum=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, roomnum);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
