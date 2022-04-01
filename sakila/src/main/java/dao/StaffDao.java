package dao;
import java.util.*;
import java.sql.*;

public class StaffDao {
	
	public List<Map<String, Object>> selectStaffList(){
		//staff를 담을 list
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// db 정보 저장
			String dburl = "jdbc:mariadb://localhost:3306/sakila";
			String dbpw = "java1234";
			String dbuser = "root";
			conn = DriverManager.getConnection(dburl,dbuser,dbpw); 
			// sql문 staffId, staffName, storeId, staffAddress, userName, lastUpdate을 가져온다.
			String sql ="SELECT"
					+ "	s1.staff_id staffId,"
					+ "	CONCAT(s1.first_name,' ',s1.last_name) staffName,"
					+ "	s1.store_id storeId,"
					+ "	CONCAT(a.address, ifnull(a.address2,''),district ) staffAddress,"
					+ "	s1.username userName,"
					+ "	s1.last_update lastUpdate"
					+ " FROM staff s1"
					+ "	INNER JOIN store s2"
					+ "	INNER JOIN address a"
					+ "	ON s1.staff_id = s2.manager_staff_id AND"
					+ "	s1.address_id = a.address_id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("userName", rs.getString("userName"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 발생!");
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		StaffDao staffDao = new StaffDao();
		List<Map<String, Object>> list = staffDao.selectStaffList();
		for(Map m : list) {
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("userName")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println();
		}
		
		
	}
	
}
