package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.FilmList;

public class FilmDao {
	   public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actor) {      
		      List<FilmList> list = new ArrayList<FilmList>();
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      conn = DBUtil.getConnection();
		      try {
		         // 동적쿼리
		         String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
		         if(category.equals("") && rating.equals("") && price==-1 && length==-1) { // 모두 입력을 하지 않은 경우
		            sql += " ORDER BY fid LIMIT ?, ?";
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setInt(3, beginRow);
		            stmt.setInt(4, rowPerPage);
		         } else if(category.equals("") && rating.equals("") && price==-1 && length !=-1) { // length만 입력된 경우
		            if(length == 0) {
		               sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setInt(3, beginRow);
		            stmt.setInt(4, rowPerPage);
		         } else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) { // price만 입력된 경우
		            sql += " AND price=? ORDER BY fid LIMIT ?, ?";
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setDouble(3, price);
		            stmt.setInt(4, beginRow);
		            stmt.setInt(5, rowPerPage);
		         } else if(category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { // rating만 입력된 경우
		        	 sql += " AND rating = ? ORDER BY fid LIMIT ?,?";
	        		 stmt = conn.prepareStatement(sql);
			         stmt.setString(1, "%"+title+"%");
			         stmt.setString(2, "%"+actor+"%");
			         stmt.setString(3, rating);
			         stmt.setInt(4, beginRow);
			         stmt.setInt(5, rowPerPage);
		         } else if(!category.equals("") && rating.equals("") && price ==-1 && length ==-1) { // category만 입력된 경우
		        	 sql += " AND category = ? ORDER BY fid LIMIT ?,?";
	        		 stmt = conn.prepareStatement(sql);
			         stmt.setString(1, "%"+title+"%");
			         stmt.setString(2, "%"+actor+"%");
			         stmt.setString(3, category);
			         stmt.setInt(4, beginRow);
			         stmt.setInt(5, rowPerPage);
		         } else if(category.equals("") && rating.equals("") && price !=-1 && length !=-1) { // length, price가 입력된 경우
		        	 if(length == 0) {
		        		 sql += " AND length < 60 AND price = ? ORDER BY fid LIMIT ?,?";
		        		 stmt = conn.prepareStatement(sql);
				         stmt.setString(1, "%"+title+"%");
				         stmt.setString(2, "%"+actor+"%");
				         stmt.setDouble(3, price);
				         stmt.setInt(4, beginRow);
				         stmt.setInt(5, rowPerPage);
		        	 } else if(length == 1) {
		        		 sql += " AND length >= 60 AND price = ? ORDER BY fid LIMIT ?,?";
		        		 stmt = conn.prepareStatement(sql);
				         stmt.setString(1, "%"+title+"%");
				         stmt.setString(2, "%"+actor+"%");
				         stmt.setDouble(3, price);
				         stmt.setInt(4, beginRow);
				         stmt.setInt(5, rowPerPage);
		        	 	}
		          } else if(category.equals("") && !rating.equals("") && price ==-1 && length !=-1) { // length, rating이 입력된 경우
		         		if(length == 0) {
			        		 sql += " AND length < 60 AND rating = ? ORDER BY fid LIMIT ?,?";
			        		 stmt = conn.prepareStatement(sql);
					         stmt.setString(1, "%"+title+"%");
					         stmt.setString(2, "%"+actor+"%");
					         stmt.setString(3, rating);
					         stmt.setInt(4, beginRow);
					         stmt.setInt(5, rowPerPage);
			        	 } else if(length == 1) {
			        		 sql += " AND length >= 60 AND rating = ? ORDER BY fid LIMIT ?,?";
			        		 stmt = conn.prepareStatement(sql);
					         stmt.setString(1, "%"+title+"%");
					         stmt.setString(2, "%"+actor+"%");
					         stmt.setString(3, rating);
					         stmt.setInt(4, beginRow);
					         stmt.setInt(5, rowPerPage);
			        	 } 
		          } else if(!category.equals("") && rating.equals("") && price ==-1 && length !=-1) { // length, category가 입력된 경우
			         	if(length == 0) {
				        	 sql += " AND length < 60 AND category = ? ORDER BY fid LIMIT ?,?";
				        	 stmt = conn.prepareStatement(sql);
						     stmt.setString(1, "%"+title+"%");
						     stmt.setString(2, "%"+actor+"%");
						     stmt.setString(3, category);
						     stmt.setInt(4, beginRow);
						     stmt.setInt(5, rowPerPage);
				         } else if(length == 1) {
				        	 sql += " AND length >= 60 AND category = ? ORDER BY fid LIMIT ?,?";
				        	 stmt = conn.prepareStatement(sql);
						     stmt.setString(1, "%"+title+"%");
						     stmt.setString(2, "%"+actor+"%");
						     stmt.setString(3, category);
						     stmt.setInt(4, beginRow);
						     stmt.setInt(5, rowPerPage);
				         } 
			        } else if(category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { // price, rating이 입력된 경우
					         sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
					         stmt = conn.prepareStatement(sql);
							 stmt.setString(1, "%"+title+"%");
							 stmt.setString(2, "%"+actor+"%");
							 stmt.setString(3, rating);
							 stmt.setDouble(4, price);
							 stmt.setInt(5, beginRow);
							 stmt.setInt(6, rowPerPage);
			        } else if(!category.equals("") && rating.equals("") && price !=-1 && length ==-1) { // price, category가 입력된 경우
			        	 	 sql += " AND category=? AND price=? ORDER BY fid LIMIT ?,?";
					         stmt = conn.prepareStatement(sql);
							 stmt.setString(1, "%"+title+"%");
							 stmt.setString(2, "%"+actor+"%");
							 stmt.setString(3, category);
							 stmt.setDouble(4, price);
							 stmt.setInt(5, beginRow);
							 stmt.setInt(6, rowPerPage);
			        } else if(!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { // rating, category가 입력된 경우
		        	 	 sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
				         stmt = conn.prepareStatement(sql);
						 stmt.setString(1, "%"+title+"%");
						 stmt.setString(2, "%"+actor+"%");
						 stmt.setString(3, category);
						 stmt.setString(4, rating);
						 stmt.setInt(5, beginRow);
						 stmt.setInt(6, rowPerPage);
			        } else if(category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { // length, price, rating이 입력된 경우
		        	 	 if(length == 0) {
		        	 		sql += " AND length < 60 AND price=? AND rating=? ORDER BY fid LIMIT ?,?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
							stmt.setString(4, rating);
							stmt.setInt(5, beginRow);
							stmt.setInt(6, rowPerPage);
		        	 	 } else if(length == 1) {
		        	 		sql += " AND length >= 60 AND price=? AND rating=? ORDER BY fid LIMIT ?,?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
							stmt.setString(4, rating);
							stmt.setInt(5, beginRow);
							stmt.setInt(6, rowPerPage); 
		        	 	 }
			        } else if(!category.equals("") && rating.equals("") && price !=-1 && length !=-1) { // length, price, category가 입력된 경우
		        	 	 if(length == 0) {
		        	 		sql += " AND length < 60 AND price=? AND category=? ORDER BY fid LIMIT ?,?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
							stmt.setString(4, category);
							stmt.setInt(5, beginRow);
							stmt.setInt(6, rowPerPage);
		        	 	 } else if(length == 1) {
		        	 		sql += " AND length >= 60 AND price=? AND category=? ORDER BY fid LIMIT ?,?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
							stmt.setString(4, category);
							stmt.setInt(5, beginRow);
							stmt.setInt(6, rowPerPage); 
		        	 	 }
			        } else if(!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { // price, rating, category가 입력된 경우
			        	sql += " AND price =? AND rating=? AND category=? ORDER BY fid LIMIT ?,?";
			        	stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setString(4, rating);
						stmt.setString(5, category);
						stmt.setInt(6, beginRow);
						stmt.setInt(7, rowPerPage); 
			        } else if(!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { // 모두 입력된 경우
			        	 if(length == 0) {
			        	 		sql += " AND length < 60 AND rating =? AND price=? AND category=? ORDER BY fid LIMIT ?,?";
			        	 		stmt = conn.prepareStatement(sql);
								stmt.setString(1, "%"+title+"%");
								stmt.setString(2, "%"+actor+"%");
								stmt.setString(3, rating);
								stmt.setDouble(4, price);
								stmt.setString(5, category);
								stmt.setInt(6, beginRow);
								stmt.setInt(7, rowPerPage); 
			        	 	 } else if(length == 1) {
			        	 		sql += " AND length >= 60 AND rating =? AND price=? AND category=? ORDER BY fid LIMIT ?,?";
			        	 		stmt = conn.prepareStatement(sql);
								stmt.setString(1, "%"+title+"%");
								stmt.setString(2, "%"+actor+"%");
								stmt.setString(3, rating);
								stmt.setDouble(4, price);
								stmt.setString(5, category);
								stmt.setInt(6, beginRow);
								stmt.setInt(7, rowPerPage); 
			        	 	 } 
			        }
			        	
		         rs = stmt.executeQuery();
		         while(rs.next()) {
		            FilmList f = new FilmList();
		            f.setFilmId(rs.getInt("fid"));
		            f.setTitle(rs.getString("title"));
		            f.setDescription(rs.getString("description"));
		            f.setCategory(rs.getString("category"));
		            f.setPrice(rs.getDouble("price"));
		            f.setLength(rs.getInt("length"));
		            f.setRating(rs.getString("rating"));
		            f.setActors(rs.getString("actors"));
		            list.add(f);
		         }
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
		      return list;
		   }
	 
	// 총 행수 구하기
	public int totalRow(String category, String rating, double price, int length, String title, String actor) {
		int row = -1;
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn=DBUtil.getConnection();
		 try {
	         // 동적쿼리
	         String sql = "SELECT count(*) as cnt from film_list WHERE title LIKE ? AND actors LIKE ?";
	         if(category.equals("") && rating.equals("") && price==-1 && length==-1) { // 모두 입력을 하지 않은 경우
	            sql += " ";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	         } else if(category.equals("") && rating.equals("") && price==-1 && length !=-1) { // length만 입력된 경우
	            if(length == 0) {
	               sql += " AND length<60";
	            } else if(length == 1) {
	               sql += " AND length>=60";
	            }
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	         } else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) { // price만 입력된 경우
	            sql += " AND price=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	            stmt.setDouble(3, price);
	         } else if(category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { // rating만 입력된 경우
	        	 sql += " AND rating = ?";
        		 stmt = conn.prepareStatement(sql);
		         stmt.setString(1, "%"+title+"%");
		         stmt.setString(2, "%"+actor+"%");
		         stmt.setString(3, rating);
	         } else if(!category.equals("") && rating.equals("") && price ==-1 && length ==-1) { // category만 입력된 경우
	        	 sql += " AND category = ?";
        		 stmt = conn.prepareStatement(sql);
		         stmt.setString(1, "%"+title+"%");
		         stmt.setString(2, "%"+actor+"%");
		         stmt.setString(3, category);
	         } else if(category.equals("") && rating.equals("") && price !=-1 && length !=-1) { // length, price가 입력된 경우
	        	 if(length == 0) {
	        		 sql += " AND length < 60 AND price = ?";
	        		 stmt = conn.prepareStatement(sql);
			         stmt.setString(1, "%"+title+"%");
			         stmt.setString(2, "%"+actor+"%");
			         stmt.setDouble(3, price);
	        	 } else if(length == 1) {
	        		 sql += " AND length >= 60 AND price = ?";
	        		 stmt = conn.prepareStatement(sql);
			         stmt.setString(1, "%"+title+"%");
			         stmt.setString(2, "%"+actor+"%");
			         stmt.setDouble(3, price);
	        	 	}
	          } else if(category.equals("") && !rating.equals("") && price ==-1 && length !=-1) { // length, rating이 입력된 경우
	         		if(length == 0) {
		        		 sql += " AND length < 60 AND rating = ?";
		        		 stmt = conn.prepareStatement(sql);
				         stmt.setString(1, "%"+title+"%");
				         stmt.setString(2, "%"+actor+"%");
				         stmt.setString(3, rating);
		        	 } else if(length == 1) {
		        		 sql += " AND length >= 60 AND rating = ?";
		        		 stmt = conn.prepareStatement(sql);
				         stmt.setString(1, "%"+title+"%");
				         stmt.setString(2, "%"+actor+"%");
				         stmt.setString(3, rating);
		        	 } 
	          } else if(!category.equals("") && rating.equals("") && price ==-1 && length !=-1) { // length, category가 입력된 경우
		         	if(length == 0) {
			        	 sql += " AND length < 60 AND category = ?";
			        	 stmt = conn.prepareStatement(sql);
					     stmt.setString(1, "%"+title+"%");
					     stmt.setString(2, "%"+actor+"%");
					     stmt.setString(3, category);
			         } else if(length == 1) {
			        	 sql += " AND length >= 60 AND category = ?";
			        	 stmt = conn.prepareStatement(sql);
					     stmt.setString(1, "%"+title+"%");
					     stmt.setString(2, "%"+actor+"%");
					     stmt.setString(3, category);
			         } 
		        } else if(category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { // price, rating이 입력된 경우
				         sql += " AND rating=? AND price=?";
				         stmt = conn.prepareStatement(sql);
						 stmt.setString(1, "%"+title+"%");
						 stmt.setString(2, "%"+actor+"%");
						 stmt.setString(3, rating);
						 stmt.setDouble(4, price);
		        } else if(!category.equals("") && rating.equals("") && price !=-1 && length ==-1) { // price, category가 입력된 경우
		        	 	 sql += " AND category=? AND price=?";
				         stmt = conn.prepareStatement(sql);
						 stmt.setString(1, "%"+title+"%");
						 stmt.setString(2, "%"+actor+"%");
						 stmt.setString(3, category);
						 stmt.setDouble(4, price);
		        } else if(!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { // rating, category가 입력된 경우
	        	 	 sql += " AND category=? AND rating=?";
			         stmt = conn.prepareStatement(sql);
					 stmt.setString(1, "%"+title+"%");
					 stmt.setString(2, "%"+actor+"%");
					 stmt.setString(3, category);
					 stmt.setString(4, rating);
		        } else if(category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { // length, price, rating이 입력된 경우
	        	 	 if(length == 0) {
	        	 		sql += " AND length < 60 AND price=? AND rating=?";
	        	 		stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setString(4, rating);
	        	 	 } else if(length == 1) {
	        	 		sql += " AND length >= 60 AND price=? AND rating=?";
	        	 		stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setString(4, rating);
	        	 	 }
		        } else if(!category.equals("") && rating.equals("") && price !=-1 && length !=-1) { // length, price, category가 입력된 경우
	        	 	 if(length == 0) {
	        	 		sql += " AND length < 60 AND price=? AND category=?";
	        	 		stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setString(4, category);
	        	 	 } else if(length == 1) {
	        	 		sql += " AND length >= 60 AND price=? AND category=?";
	        	 		stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setString(4, category);
	        	 	 }
		        } else if(!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { // price, rating, category가 입력된 경우
		        	sql += " AND price =? AND rating=? AND category=?";
		        	stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setDouble(3, price);
					stmt.setString(4, rating);
					stmt.setString(5, category);
		        } else if(!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { // 모두 입력된 경우
		        	 if(length == 0) {
		        	 		sql += " AND length < 60 AND rating =? AND price=? AND category=?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
							stmt.setDouble(4, price);
							stmt.setString(5, category);
		        	 	 } else if(length == 1) {
		        	 		sql += " AND length >= 60 AND rating =? AND price=? AND category=?";
		        	 		stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
							stmt.setDouble(4, price);
							stmt.setString(5, category);
		        	 	 } 
		        }
		        	
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            row=rs.getInt("cnt");
	         }
	      } catch(SQLException e) {
	         e.printStackTrace();
	      }
	      return row;
		
	}
	   
	   
	
	// 영화의 가격을 가져오는 메서드
	public List<Double> selectfilmPriceList() {
	      List<Double> list = new ArrayList<Double>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            list.add(rs.getDouble("price"));
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
















