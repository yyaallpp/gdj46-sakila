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
				+ "	INNER JOIN  staff s"
				+ "	ON r.staff_id = s.staff_id"
				+ "	INNER JOIN inventory i"
				+ "	ON r.inventory_id = i.inventory_id"
				+ "	INNER JOIN film f"
				+ "	ON i.film_id = f.film_id ";
		
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
			} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { // beginDate만 입력한 경우
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-30','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, beginDate);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(storeId == -1 && customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { // endDate만 입력한 경우
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, endDate);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { // storeId, customerId를 입력한 경우
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { // storeId, beginDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}  else if(storeId != -1 && customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { // storeId, endDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { // customerName, beginDate를 입력한 경우
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId == -1 && !customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { // customerName, endDate를 입력한 경우
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // beginDate, endDate를 입력한 경우
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, beginDate);
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(storeId != -1 && !customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { // storeId,customerName, beginDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, beginDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { // storeId,customerName, endDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // storeId,beginDate, endDate를 입력한 경우
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { // customerName, beginDate, endDate를 입력한 경우
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
			System.out.println(sql);
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
	public int selectRentalTotalRow(int storeId, String customerName,String beginDate,String endDate) {
		int totalRow = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		conn = DBUtil.getConnection();
		try {
			 String sql = "SELECT COUNT(*) cnt"
						+ " FROM rental r INNER JOIN customer c"
						+ " ON r.customer_id = c.customer_id"
						+ "	INNER JOIN staff s"
						+ "	ON r.staff_id = s.staff_id"
						+ "	INNER JOIN inventory i "
						+ "	ON i.inventory_id = r.inventory_id "
						+ "	INNER JOIN film f"
						+ "	ON f.film_id = i.film_id"
						+ " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";

			 if(storeId == -1 && beginDate.equals("") && endDate.equals("")){ // customerName을 기본으로
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
			 } else if (storeId == -1 && beginDate.equals("") && !endDate.equals("")) { // endDate
				 sql += " AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
			 	stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
			} else if(storeId == -1 && !beginDate.equals("") && endDate.equals(""))  { // beginDate
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
			} else if(storeId == -1 && !beginDate.equals("") && !endDate.equals("")) { // rentalDate
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
			} else if(storeId != -1 && beginDate.equals("") && endDate.equals("")) { // storId
				sql += " AND s.store_id=?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
			} else if(storeId != -1 && beginDate.equals("") && !endDate.equals("")) { // storeId, endDate
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
			} else if(storeId != -1 && !beginDate.equals("") && endDate.equals("")) { // storeId, beginDate
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
			} else if(storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { // storeId, beginDate, endDate
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
			}

				rs = stmt.executeQuery();
				if(rs.next()) {
					totalRow=rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return totalRow;
	}
	
	public static void main(String[] args) {
		RentalDao rentalDao = new RentalDao();
		List<Map<String, Object>> list = rentalDao.selectRentalSearchList(1,"","2005-01-01","2006-01-01",0,10);
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
		int totalRow = rentalDao.selectRentalTotalRow(-1, "", "", "");
		System.out.println(totalRow);
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
