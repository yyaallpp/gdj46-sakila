package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;
import vo.StaffList;

public class StaffListViewDao {
	public List<StaffList> selectStaffListByPage(){
		List<StaffList> list = new ArrayList<StaffList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select id staffId, name, address, phone, city, country, sid storeId from staff_list order by id";
			try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				StaffList s = new StaffList();
				s.setStaffId(rs.getInt("staffId"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
				s.setPhone(rs.getString("phone"));
				s.setCity(rs.getString("city"));
				s.setCountry(rs.getString("country"));
				s.setStoreId(rs.getInt("storeId"));
				System.out.println(s.toString());
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
