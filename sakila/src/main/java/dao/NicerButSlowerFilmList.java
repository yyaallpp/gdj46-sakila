package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;
import vo.FilmList;

public class NicerButSlowerFilmList {
	public List<FilmList> selectNicerButSlowerFilmListByPage(int beginRow, int rowPerPage){
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select fid filmId, title, description, category, price, length, rating, actors from nicer_but_slower_film_list order by fid limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFilmId(rs.getInt("filmId"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int selectNicerButSlowerFilmListTotalRow(){
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn=DBUtil.getConnection();
		
		String sql = "select count(*) cnt from nicer_but_slower_film_list ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
				System.out.println(row+ " <-- selectNicerButSlowerFilmListTotalRow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
}