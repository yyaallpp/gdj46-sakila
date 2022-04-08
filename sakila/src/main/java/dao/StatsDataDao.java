package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.DBUtil;

public class StatsDataDao {
	// 1. customer 별 총액 (amount) 180이상
	public List<Map<String,Object>> amountByCustomer(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		// customerID, name, total
		String sql = "SELECT p.customer_id customerId, CONCAT(c.first_name,'',c.last_name) name, SUM(amount) total"
				+ "		FROM payment p INNER JOIN customer c "
				+ "		ON p.customer_id = c.customer_id"
				+ "		GROUP BY p.customer_id"
				+ " 	HAVING sum(p.amount) >= 180"
				+ "		ORDER BY SUM(p.amount) DESC ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("customerId", rs.getInt("customerId"));
				m.put("name",rs.getString("name"));
				m.put("total", rs.getDouble("total"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	// 2. RentalRate 별 영화개수(filmCount)
	public List<Map<String,Object>> filmCountByRentalRate(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB접속 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		// rentalRate랑 cnt
		String sql=" SELECT rental_rate rentalRate, COUNT(*) cnt"
				+ " FROM film"
				+ " GROUP BY rental_rate"
				+ " ORDER BY COUNT(*) desc";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("rentalRate", rs.getDouble("rentalRate"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 3. Rating 별 영화 개수(filmCount)
	public List<Map<String,Object>> filmCountByRating(){		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		// rating, cnt
		String sql=" SELECT rating, COUNT(*) cnt"
				+ " FROM film"
				+ " GROUP BY rating";
	
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("rating", rs.getString("rating"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		return list;
	}
	
	// 4. Customer 중 가장 많이 빌려간사람(RentalAmount)
	public List<Map<String,Object>> RentalAmountByCustomer(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// customerID, name
		String sql = "SELECT c.customer_id customerId, CONCAT(c.first_name,'',c.last_name) name "
				+ " FROM customer c"
				+ " WHERE customer_id = (select customer_id"
				+ " FROM payment"
				+ " GROUP BY customer_id "
				+ " ORDER BY COUNT(*) DESC "
				+ " LIMIT 0,1) ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("customerId", rs.getInt("customerId"));
				m.put("name",rs.getString("name"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	// 5. language 별 영화개수(FilmAmount)
	public List<Map<String,Object>> FilmAmountByLanguage(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// name, cnt
		String sql = "SELECT NAME, COUNT(*) cnt"
				+ " FROM film f INNER JOIN language l"
				+ " ON f.language_id = l.language_id"
				+ " GROUP BY l.name ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("name", rs.getString("name"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 6. length별 영화 개수(구간을 정해서)
	public List<Map<String,Object>> FilmAmountByLength(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// length2, cnt
		String sql = "SELECT t.LENGTH2 length2, COUNT(*) cnt"
				+ " FROM (SELECT title, LENGTH, "
				+ "		 CASE WHEN LENGTH<=60 THEN 'less60' "
				+ "		 		WHEN LENGTH BETWEEN 61 AND 120 THEN 'between61and120' "
				+ "		 		WHEN LENGTH BETWEEN 121 AND 180 THEN 'between121and180' "
				+ "		 		ELSE 'more180' "
				+ "		 END LENGTH2 "
				+ "		FROM film) t "
				+ " GROUP BY t.length2 ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("length2", rs.getString("length2"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 7. actor 별 영화 개수 상위 10명
	public List<Map<String,Object>> FilmAmountByActor(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// name, cnt 
		String sql = " SELECT CONCAT(a.first_name,' ',a.last_name) name, COUNT(*) cnt "
				+ " from film_actor fm INNER JOIN actor a "
				+ " ON fm.actor_id = a.actor_id "
				+ " GROUP BY fm.actor_id "
				+ " ORDER BY COUNT(*) DESC LIMIT 0,10 ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("name", rs.getString("name"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 8. store별 가지고 있는 영화의 수
	public List<Map<String,Object>> FilmAmountByStore(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// storeId, cnt
		String sql = " SELECT store_id storeId, COUNT(*) cnt"
				+ " FROM inventory i INNER JOIN film f "
				+ " ON i.film_id = f.film_id "
				+ " GROUP BY store_id ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("storeId", rs.getString("storeId"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 9. store 요일별 매출
	public List<Map<String,Object>> WeekAmountByStore(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// storeId, dayofweek, sum
		String sql = "SELECT s.store_id storeId, CASE t.w \r\n"
				+ "								WHEN 0 THEN 'MON'"
				+ "								WHEN 1 THEN 'TUE'"
				+ "								WHEN 2 THEN 'WED'"
				+ "								WHEN 3 THEN 'THU'"
				+ "								WHEN 4 THEN 'FIR'"
				+ "								WHEN 5 THEN 'SAT'"
				+ "								WHEN 6 THEN 'SUN'"
				+ "							END DAYOFWEEK,t.c sum"
				+ " FROM (SELECT staff_id, WEEKDAY(payment_date) w , COUNT(*) c"
				+ "	FROM payment"
				+ "	GROUP BY staff_id, WEEKDAY(payment_date)) t"
				+ "	INNER JOIN staff s"
				+ "	ON t.staff_id = s.staff_id"
				+ " ORDER BY s.store_id, t.w asc";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("storeId", rs.getString("storeId"));
				m.put("DAYOFWEEK", rs.getString("DAYOFWEEK"));
				m.put("sum",rs.getInt("sum"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 10. staff가 customer에게 rental한 횟수
	public List<Map<String,Object>> CustomerAmountByStaff(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// name,cnt
		String sql = " SELECT CONCAT(s.first_name,' ',s.last_name) name ,COUNT(*) cnt"
				+ " FROM rental r INNER JOIN staff s"
				+ "	ON r.staff_id = s.staff_id "
				+ " group BY s.staff_id ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("name", rs.getString("name"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 11. 나라별 customer 수 상위 10개국
	public List<Map<String,Object>> CustomerAmountByCountry(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// DB 접속
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		
		// country, cnt
		String sql = " SELECT c.country country, COUNT(*) cnt"
				+ " FROM country c INNER JOIN city ci"
				+ "	ON c.country_id = ci.country_id"
				+ "	INNER JOIN address a"
				+ "	ON a.city_id = ci.city_id"
				+ "	INNER JOIN customer cu"
				+ "	ON cu.address_id = a.address_id"
				+ "	GROUP BY c.country ORDER BY COUNT(*) DESC LIMIT 0, 10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				// 값 넣기
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("country", rs.getString("country"));
				m.put("cnt",rs.getInt("cnt"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
