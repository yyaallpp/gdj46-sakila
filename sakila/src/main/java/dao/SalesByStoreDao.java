package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;
import vo.SalesByStore;

public class SalesByStoreDao {
	public List<SalesByStore> selectSalesByStoreListByPage(){
		List<SalesByStore> list = new ArrayList<SalesByStore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select store, manager, total_sales totalSales from sales_by_store order by store";
			try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SalesByStore s = new SalesByStore();
				s.setStore(rs.getString("store"));
				s.setManager(rs.getString("manager"));
				s.setTotalSales(rs.getDouble("totalSales"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
