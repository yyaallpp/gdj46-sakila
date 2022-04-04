package dao;
import java.util.*;

import util.DBUtil;

import java.sql.*;

public class StoreDao {
	// ArrayList는 List 인터페이스의 구현체 중 하나이다.
	// HashMap은 Map 인터페이스의 구현체 중 하나이다.
	public List<Map<String,Object>> selectStoreList(){
		
		List<Map<String,Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT"
					+ "	s1.store_id storeId,"
					+ "	s1.manager_staff_id staffId,"
					+ "	CONCAT(s2.first_name,' ',s2.last_name) staffName,"
					+ "	s1.address_id addressId,"
					+ "	concat(a.address, ifnull(a.address2,' '),district ) storeAddress,"
					+ "	s1.last_update lastUpdate"
					+ " FROM store s1"
					+ "	INNER JOIN staff s2"
					+ "	INNER JOIN address a"
					+ " ON s1.manager_staff_id = s2.staff_id AND"
					+ "	s1.address_id = a.address_id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>(); // 다형성
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("storeAddress", rs.getString("storeAddress"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
			
		} catch (Exception e) {  // ClassNotFoundException, SQLException 두개의 예외를 부모타입 Exception으로 처리 -> 다형성
			e.printStackTrace();
			System.out.println("예외 발생");
		} finally {			
			// DB자원 해지 -> close();
			// try절에서 예외가 발생하면 자원 해지를 못한 상태에서 코드가 종료된다. -> finally가 필요
			try {
				conn.close();
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// selectionStoreList() 테스트 코드
	public static void main(String[] args) {
		StoreDao storeDao = new StoreDao();
		List<Map<String, Object>> list = storeDao.selectStoreList();
		for(Map m : list) {
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.print(m.get("addressId")+", ");
			System.out.print(m.get("storeAddress")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println();
		}
	}
}
