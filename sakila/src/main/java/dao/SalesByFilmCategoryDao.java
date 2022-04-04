package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;
import vo.SalesByFilmCategory;

public class SalesByFilmCategoryDao {
	public List<SalesByFilmCategory> selectSalesByFilmCategoryByPage(){
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select category, total_sales totalSales from sales_by_film_category order by category ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SalesByFilmCategory s = new SalesByFilmCategory();
				s.setCategory(rs.getString("category"));
				s.setTotalSales(rs.getDouble("totalSales"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
