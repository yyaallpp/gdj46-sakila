package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class RewardsReport {
	// 이전달의 우수 고객을 보여주는 메서드
		public Map<String,Object> rewardsReport(int minMonthlyPurchase, double minDollarAmountPurchase){
			Map<String, Object> map = new HashMap<String,Object>();
			Connection conn = null;
			CallableStatement stmt = null;
			ResultSet rs = null;
			ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>(); // hm을 담을 list
			HashMap<String,Object> hm = null; // c.*의 정보를 담는 hm
			Integer countRewardees = 0; // count(*)를 저장할 변수
			
			conn = DBUtil.getConnection();
			try {
				stmt = conn.prepareCall("{CALL rewards_report(?,?,?)}");
				stmt.setInt(1, minMonthlyPurchase);
				stmt.setDouble(2, minDollarAmountPurchase);
				stmt.registerOutParameter(3,Types.INTEGER);
				rs = stmt.executeQuery();
				while(rs.next()) {
					hm = new HashMap<String,Object>();
					hm.put("customerId",rs.getInt(1));
					hm.put("storeId",rs.getInt(2));
					hm.put("firstName",rs.getString(3));
					hm.put("lastName",rs.getString(4));
					hm.put("email",rs.getString(5));
					hm.put("addressId",rs.getInt(6));
					hm.put("active",rs.getInt(7));
					hm.put("createDate",rs.getString(8));
					hm.put("updateDate",rs.getString(9));
					
					// 디버깅 
					System.out.println(rs.getInt(1) + " <--customerId");
					System.out.println(rs.getInt(2) + " <--storeId");
					System.out.println(rs.getString(3) + " <--firstName");
					System.out.println(rs.getString(4) + " <--lastName");
					System.out.println(rs.getString(5) + " <--email");
					System.out.println(rs.getInt(6) + " <--addressId");
					System.out.println(rs.getInt(7) + " <--active");
					System.out.println(rs.getString(8) + " <--createDate");
					System.out.println(rs.getString(9) + " <--updateDate");
					list.add(hm);
				}
				countRewardees = stmt.getInt(3); // count(*)의 개수
				System.out.println(countRewardees + "<--countRewardees");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			map.put("list", list);
			map.put("countRewardees", countRewardees); // 총 개수
			
			return map;
		}
		
		public static void main(String[] args) {
			RewardsReport r = new RewardsReport();
			int minMonthlyPurchase = 7;
			double minDollarAmountPurchase = 50.00;
			Map<String,Object> map = r.rewardsReport(minMonthlyPurchase, minDollarAmountPurchase);
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			int countRewardees = (int)map.get("countRewardees");
			System.out.println(countRewardees + "<--main countRewardees");
			
		}
}




















