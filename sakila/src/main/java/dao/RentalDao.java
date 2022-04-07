package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.FilmList;

public class RentalDao {
	// rental 검색 결과의 리스트를 호출하는 메서드
	public List<Map<String,Object>> selectRentalSearchList(int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage){
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		// ?는 4가지 -> storeId, customerName, beginDate, endDate
		// 기본 쿼리문 
		String sql = "SELECT rental_id rentalId, rental_date rentalDate, r.customer_id customerId, return_date returnDate, r.staff_id staffId, r.last_update lastUpdate,"
				+ " CONCAT(c.first_name,' ',c.last_name) cName,"
				+ " s.store_id storeId,"
				+ " i.film_id filmId,"
				+ " f.title title"
				+ " FROM rental r"
				+ "	INNER JOIN customer c"
				+ "	ON r.customer_id = c.customer_id "
				+ "		INNER JOIN  staff s"
				+ "		ON r.staff_id = s.staff_id"
				+ "			INNER JOIN inventory i"
				+ "			ON r.inventory_id = i.inventory_id"
				+ "				INNER JOIN film f"
				+ "				ON i.film_id = f.film_id";
		
		try {
			if(storeId == -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // 모두 입력이 되지 않은 경우
				sql += " ORDER BY rentalId LIMIT ?,? "; 
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
			} else if(storeId != -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // storeID만 입력한 경우
				sql += " WHERE s.store_id= ? ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(storeId == -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // customerName만 입력한 경우
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // rentalDate만 입력한 경우
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, beginDate);
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // storeId, customerId를 입력한 경우
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // storeId, rentalDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // customerName, rentalDate를 입력한 경우
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(storeId != -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("")) { // 모두 입력한 경우
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("rentalId", rs.getInt("rentalId"));
				m.put("rentalDate", rs.getString("rentalDate"));
				m.put("customerId", rs.getInt("customerId"));
				m.put("returnDate", rs.getString("returnDate"));
				m.put("staffId", rs.getInt("staffId"));
				m.put("lastUpdate", rs.getString("lastUpdate"));
				m.put("cName", rs.getString("cName"));
				m.put("storeId", rs.getInt("storeId"));
				m.put("filmId", rs.getInt("filmId"));
				m.put("title", rs.getString("title"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	// rental의 총 행의 개수구하는 메서드
	public int totalRow(int storeId, String customerName, String beginDate, String endDate){
		int row = -1;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "SELECT count(*) cnt "
					+ " FROM rental r"
					+ "	INNER JOIN customer c"
					+ "	ON r.customer_id = c.customer_id "
					+ "		INNER JOIN  staff s"
					+ "		ON r.staff_id = s.staff_id"
					+ "			INNER JOIN inventory i"
					+ "			ON r.inventory_id = i.inventory_id"
					+ "				INNER JOIN film f"
					+ "				ON i.film_id = f.film_id";
			try {
				if(storeId == -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // 모두 입력 안한 경우
					sql += " ";
					stmt = conn.prepareStatement(sql);
				} else if(storeId != -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // storeId만 입력한 경우
					sql += " Where s.store_id = ? ";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, storeId);
				} else if(storeId == -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("")) { // customerName만 입력한 경우
					sql += " Where CONCAT(c.first_name,' ',c.last_name) LIKE ? ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");
				} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // rentalDate만 입력한 경우
					sql += " Where r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,beginDate);
					stmt.setString(2,endDate);
				} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // storeId와 customerName을 입력한 경우
					sql += " Where s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, storeId);
					stmt.setString(2,"%"+customerName+"%");
				} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // storeId와 rentalDate을 입력한 경우
					sql += " Where s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, storeId);
					stmt.setString(2,beginDate);
					stmt.setString(3,endDate);
				} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // customerName와 rentalDate을 입력한 경우
					sql += " Where  CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");
					stmt.setString(2,beginDate);
					stmt.setString(3,endDate);
				} else if(storeId != -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // 모두 입력한 경우
					sql += " Where s.store_id= ? CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, storeId);
					stmt.setString(2,"%"+customerName+"%");
					stmt.setString(3,beginDate);
					stmt.setString(4,endDate);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				rs = stmt.executeQuery();
				while(rs.next()) {
					row = rs.getInt("cnt");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return row;
	}
	public static void main(String[] args) {
		RentalDao rentalDao = new RentalDao();
		List<Map<String, Object>> list = rentalDao.selectRentalSearchList(-1,"","","",0,10);
		for(Map m : list) {
			System.out.print(m.get("rentalId")+", ");
			System.out.print(m.get("rentalDate")+", ");
			System.out.print(m.get("customerId")+", ");
			System.out.print(m.get("returnDate")+", ");
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("lastUpdate")+ ", ");
			System.out.print(m.get("cName")+ ", ");
			System.out.print(m.get("storeId")+ ", ");
			System.out.print(m.get("filmId")+ ", ");
			System.out.print(m.get("title")+ ", ");
			System.out.println();
		}
		int row = rentalDao.totalRow(1,"COLLAZO", "", "");
		System.out.println(row);
	}
}
/*
SELECT r.*,
 CONCAT(c.first_name,' ',c.last_name) cName,
 s.store_id storeId,
 i.film_id film_id,
 f.title
 FROM rental r
	INNER JOIN customer c
	ON r.customer_id = c.customer_id 
		INNER JOIN  staff s
		ON r.staff_id = s.staff_id
			INNER JOIN inventory i
			ON r.inventory_id = i.inventory_id
				INNER JOIN film f
				ON i.film_id = f.film_id
 WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ?
 AND r.rental_date between STR_TO_DATE(?,'%Y-%m-%d') 
 AND  STR_TO_DATE(?,'%Y-%m-%d');
 */
