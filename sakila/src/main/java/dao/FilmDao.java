package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;

public class FilmDao {
	// filmInStock -> film이 store에 있는지 확인
	public Map<String,Object> filmInStock(int filmId, int storeId){
		Map<String,Object> map = new HashMap<String, Object>();
		
		Connection conn = null;
		// PreparedStatement -> 쿼리를 실행
		// CallableStatement -> 프로시저를 실행
		CallableStatement stmt = null; 
		ResultSet rs = null; 
		List<Integer> list = new ArrayList<>();	// select inventory_id...
		Integer count = 0;  // select count(inventory_id)..	
		
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{CALL film_in_stock(?,?,?)}");
			stmt.setInt(1,filmId);
			stmt.setInt(2,storeId);
			stmt.registerOutParameter(3,Types.INTEGER); // 숫자값으로 return 하는 것 타입이 들어가야한다.
			rs= stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt(""inventory_id")
			}
			count = stmt.getInt(3); //  프로시제 3번째 out 변수의 값 count는 3번째 물음표의 값이다.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		map.put("list", list);
		map.put("count", count);
		
		return map;
	}
	
	// 대여된것의 사본이 있는지 확인
	public Map<String,Object> filmNotInStock(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String,Object>();
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>(); // inventoryID;
		Integer count = 0; // count(inventory);
		
		conn=DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call film_not_in_stock(?,?,?)}");
			stmt.setInt(1,filmId);
			stmt.setInt(2,storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs= stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			count = stmt.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		
		return map;
	}
	

	public static void main(String[] args) {
		FilmDao filmDao = new FilmDao();
		int filmId = 7;
		int storeId = 2;
		Map<String,Object> map = filmDao.filmInStock(filmId, storeId);
		List<Integer> list = (List<Integer>) map.get("list");
		int count = (int) map.get("count");
		
		System.out.println(filmId + "번 영화가 " + storeId +"번 가게에 "+count+"개 남음");
		for(int id : list) {
			System.out.println(id);
		}
		System.out.println(count +"개" + "");

	}
}
















