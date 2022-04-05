package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.ActorInfo;

public class ActorInfoDao {
		// ActorInfo List보여주기
		public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage){
		List<ActorInfo> list = new ArrayList<ActorInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); // 드라이브 연결하는 메서드
		// 모든 정보를 보여줘야할듯
		String sql = "Select actor_id actorId, first_name firstName, last_name lastName, film_info filmInfo from actor_info order by actor_id limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ActorInfo actorInfo = new ActorInfo();
				actorInfo.setActorId(rs.getInt("actorId"));
				actorInfo.setFirstName(rs.getString("firstName"));
				actorInfo.setLastName(rs.getString("lastName"));
				actorInfo.setFilmInfo(rs.getString("filmInfo"));
				System.out.println(actorInfo.toString());
				list.add(actorInfo);
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
		// ActorInfo 총 개수 구하기
		public int selectActorInfoTotalRow() {
			int row = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			conn = DBUtil.getConnection();
			// actor_info count(*) 구하기
			String sql = "select count(*) cnt from actor_Info";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
					row = rs.getInt("cnt");
					System.out.println(row+ " <-- selectActorInfoTotalRow");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return row;	
		}
}
