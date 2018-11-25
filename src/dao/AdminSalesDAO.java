package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminSalesDAO {
	
	
	public HashMap<Integer,Integer> monthsalescal() {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			int cnt = 1;
			con.setAutoCommit(false);
			PreparedStatement st = null;
			for(int i = 1 ; i<=12 ; i++) {
				String k = Integer.toString(i);
				
				if(i<10) {
					k = "0"+k;
				}
				
				String sql = "select count, price\r\n" + 
							"from salary_"+i+" salary, member mem, menu\r\n" + 
							"where salary.member_seq = mem.member_seq\r\n" + 
							"and salary.menu_seq = menu.menu_id\r\n" + 
							"and to_char(paydate,'yy-mm-dd') <= '18-"+k+"-15'\r\n" + 
							"order by paydate";
					
					
				
				
	
				st = con.prepareStatement(sql);
				
				ResultSet set = st.executeQuery();
				int result1 = 0;
				while(set.next()) {
					int a = set.getInt("price");
					int b = set.getInt("count");
					result1 += a*b;
	
				}
				map.put(cnt++, result1);
				
				sql = "select count, price\r\n" + 
						"from salary_"+i+" salary, member mem, menu\r\n" + 
						"where salary.member_seq = mem.member_seq\r\n" + 
						"and salary.menu_seq = menu.menu_id\r\n" + 
						"and to_char(paydate,'yy-mm-dd') > '18-"+k+"-15'\r\n" + 
						"order by paydate";
				
				
				st = con.prepareStatement(sql);
				
				set = st.executeQuery();
				int result2 = 0;
				while(set.next()) {
					int a = set.getInt("price");
					int b = set.getInt("count");
					result2 += a*b;
	
				}
				map.put(cnt++, result2);
				
			}
			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("연결해제성공");
		}
		
		
		return map;
	}

	
	public HashMap<Integer,Integer> menusalescal() {
		
		
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);
		
			
				
				
				String sql = "select menu_id , sum(count) from  menu, salary_11 sal\r\n" + 
						"where  menu.menu_id = sal.menu_seq(+)\r\n" + 
						"group by menu_id";
					
					
				
				
	
				PreparedStatement st = con.prepareStatement(sql);
				
				ResultSet set = st.executeQuery();
				while(set.next()) {
					int menu_id = set.getInt("menu_id");
					int total = set.getInt("sum(count)");
					
					map.put(menu_id, total);
				}
				
				
				
			
				
			
			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("연결해제성공");
		}
		
		
		return map;
		
		
		
	}
	
	
	
	public ArrayList<String[]> menuinfo() {
		
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);
		
			
				
				
				String sql = "select menu_id, menu_name, price "
						+ " from menu order by menu_id";
					
					
				
	
				PreparedStatement st = con.prepareStatement(sql);
				
				ResultSet set = st.executeQuery();
				while(set.next()) {
					String menu_id = Integer.toString(set.getInt("menu_id"));
					String price = Integer.toString(set.getInt("price"));
					String menu_name = set.getString("menu_name");
					
					String[] sar = new String[3];
					sar[0]=menu_id;
					sar[1]=price;
					sar[2]=menu_name;
					
					
					
					list.add(sar);
					
					
				}
				
				
			
				
				
				
				
			
			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("연결해제성공");
		}
		
		
		return list;
		
		
	}
	
	
	
	public ArrayList<int[]> kindsalescal() {
		
		
		ArrayList<int[]> arlist = new ArrayList<int[]>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);
		
			
				
				for(int i = 1 ; i <= 12 ; i++) {
					int[] arr = new int[3];
				String sql = "select * from salary_"+i+" sal, menu where sal.menu_seq = menu.menu_id and kind = 1";
					
					
				
	
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet set = st.executeQuery();
				int total_main = 0;
				while(set.next()) {
					int price = set.getInt("price");
					int count = set.getInt("count");

					total_main += price*count;
					
				}
				
				sql = "select * from salary_"+i+" sal, menu where sal.menu_seq = menu.menu_id and kind = 2";
			
				st = con.prepareStatement(sql);
				set = st.executeQuery();
				int total_side = 0;
				while(set.next()) {
					int price = set.getInt("price");
					int count = set.getInt("count");

					total_side += price*count;
				}
				
				
				
				sql = "select * from salary_"+i+" sal, menu where sal.menu_seq = menu.menu_id and kind = 3";
				
				st = con.prepareStatement(sql);
				set = st.executeQuery();
				int total_drink = 0;
				while(set.next()) {
					int price = set.getInt("price");
					int count = set.getInt("count");

					total_drink += price*count;
				}
				
			
				
				arr[0]=total_main;
				arr[1]=total_side;
				arr[2]=total_drink;
				
				arlist.add(arr);
				
				
			con.commit();

			st.close();
				}

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();

		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("연결해제성공");
		}
		
		
		return arlist;
		
	}
	
	

}
