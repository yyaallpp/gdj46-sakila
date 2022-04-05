package dao;
import vo.CustomerList;
import java.util.*;
import java.sql.*;
import util.DBUtil;

public class CustomerListDao {
	// CustomerList 보여주기
	public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage){
		List<CustomerList> list = new ArrayList<CustomerList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql ="select id customerId, name, address, phone, city, country, notes, sid storeId from customer_list order by id limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerList c = new CustomerList();
				c.setCustomerId(rs.getInt("customerId"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setNotes(rs.getString("notes"));
				c.setStoreId(rs.getInt("storeId"));
				System.out.println(c.toString());
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// CustomerList 총 개수 구하기
	public int selectCustomerListTotalRow(){
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn=DBUtil.getConnection();
		
		String sql = "select count(*) cnt from customer_list ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
				System.out.println(row+ " <-- selectCustomerListTotalRow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
}
