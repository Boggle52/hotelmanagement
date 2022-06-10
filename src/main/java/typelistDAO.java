import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class typelistDAO {
	private Statement stmt;
	private Connection conn;
	
	public ArrayList<typelistDTO> listType(){
		ArrayList<typelistDTO> list = new ArrayList<typelistDTO>();
		try {
			connDB();
			String query = "select * from roomtype order by typenum";
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int typenum = rs.getInt("typenum");
				String roomtype = rs.getString("roomtype");
				typelistDTO tDto = new typelistDTO();
				tDto.setTypenum(typenum);
				tDto.setRoomtype(roomtype);
				list.add(tDto);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
