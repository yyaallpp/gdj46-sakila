package dao;
import java.util.*;

import util.DBUtil;

import java.sql.*;

public class StaffDao {
	
	public List<Map<String, Object>> selectStaffList(){
		//staff를 담을 list
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			// sql문 staffId, staffName, email, staffAddress, storeId, userName, lastUpdate을 가져온다.
			String sql ="SELECT"
					+ "	s1.staff_id staffId,"
					+ "	CONCAT(s1.first_name,' ',s1.last_name) staffName,"
					+ " s1.email email,"
					+ "	CONCAT(a.address, ifnull(a.address2,''),district ) staffAddress,"
					+ "	s1.store_id storeId,"
					+ "	s1.username userName,"
					+ " s1.password password,"
					+ "	s1.last_update lastUpdate"
					+ " FROM staff s1"
					+ "	INNER JOIN store s2"
					+ "	INNER JOIN address a"
					+ "	ON s1.store_id = s2.store_id AND"
					+ "	s1.address_id = a.address_id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("email", rs.getString("email"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("userName", rs.getString("userName"));
				map.put("password", rs.getString("password"));
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
			System.out.print(m.get("email")+", ");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("userName")+", ");
			System.out.print(m.get("password")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println();
		}
		
		
	}
	
}
