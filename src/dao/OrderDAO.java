package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import vo.MemberVO;

public class OrderDAO {

	public int charge(String pay_seq, int member_seq, int menu_seq, int menu_count, String table) {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "insert into "+table+" values("+pay_seq+", ? , ? , sysdate, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, member_seq);
			st.setInt(2, menu_seq);
			st.setInt(3, menu_count);
			
			result = st.executeUpdate();

			

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
		return result;
		
	}
	
	
	
	public HashMap<Integer,String>  transaction(MemberVO memvo) {
		
		
		HashMap<Integer,String> pay = null ;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);
			
			Set<String> payseq_set = new HashSet<String>();
			
			
			String sql = "select pay_seq\r\n" + 
					"from main_11 main , member mem , main_menu menu\r\n" + 
					"where main.member_seq = mem.member_seq\r\n" + 
					"and main.main_seq = menu.menu_id\r\n" + 
					"and mem.member_id = ?\r\n" + 
					"group by pay_seq";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, memvo.getMember_id());
			
			ResultSet set = st.executeQuery();
			String main_order = "";
			while(set.next()) {
				main_order = set.getString("pay_seq");
				payseq_set.add(main_order);
			}

			sql = "select pay_seq\r\n" + 
					"from side_11 side, member mem, side_menu menu\r\n" + 
					"where side.member_seq = mem.member_seq\r\n" + 
					"and side.side_seq = menu.menu_id\r\n" + 
					"and mem.member_id = ?\r\n" + 
					"group by pay_seq";
			
			st = con.prepareStatement(sql);
			st.setString(1, memvo.getMember_id());
			
			set = st.executeQuery();
			String side_order = "";
			while(set.next()) {
				side_order = set.getString("pay_seq");
				payseq_set.add(side_order);
			}
			
			sql = "select pay_seq\r\n" + 
					"from drink_11 drink, member mem, drink_menu menu\r\n" + 
					"where drink.member_seq = mem.member_seq\r\n" + 
					"and drink.drink_seq =menu.menu_id\r\n" + 
					"and mem.member_id = ?\r\n" + 
					"group by pay_seq";
			
			
			st = con.prepareStatement(sql);
			st.setString(1, memvo.getMember_id());
			
			set = st.executeQuery();
			String drink_order = "";
			while(set.next()) {
				drink_order = set.getString("pay_seq");
				payseq_set.add(drink_order);
			}

			pay = new HashMap<Integer,String>();
			
			System.out.println("--------------------------------------------------------");

			int num = 1;
			for(String pay_seq : payseq_set) {
				
				String[] output = billist(pay_seq, memvo);
				String totalpay = output[0];
				String paydate = output[1];
				
				System.out.println(num+") 결제번호: "+pay_seq+" 결제일 : "+paydate+" 총금액 : "+totalpay);
				pay.put(num, pay_seq);
				num++;
				
			}
			
			System.out.println("--------------------------------------------------------");

			
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
		
		return pay;
	}
	
	
	
	public void showbill(String pay_seq, MemberVO memvo) {
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);

			int totalpay = 0 ;
			
			
			System.out.println("===================================");
			System.out.println();
			System.out.println("\t신     용     승     인\t(고객용)");
			System.out.println();
			System.out.println("===================================");
			System.out.println();
			
			String sql = "select * " + 
					"from main_11 main , member mem , main_menu menu\r\n" + 
					"where main.member_seq = mem.member_seq\r\n" + 
					"and main.main_seq = menu.menu_id\r\n" + 
					"and pay_seq= ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			String paydate = "";
			ResultSet set = st.executeQuery();
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				System.out.println(menu_name+"  "+price+"  "+count+"  "+price*count);
				totalpay += price*count;
			
			}
			
			System.out.println();

			sql = "select * " + 
					"from side_11 side, member mem, side_menu menu\r\n" + 
					"where side.member_seq = mem.member_seq\r\n" + 
					"and side.side_seq = menu.menu_id\r\n" + 
					"and pay_seq = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			
			set = st.executeQuery();
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				System.out.println(menu_name+"  "+price+"  "+count+"  "+price*count);
				totalpay += price*count;
			}
			
			System.out.println();
			
			sql = "select * " + 
					"from drink_11 drink, member mem, drink_menu menu\r\n" + 
					"where drink.member_seq = mem.member_seq\r\n" + 
					"and drink.drink_seq =menu.menu_id\r\n" + 
					"and pay_seq = ?";
			
			
			st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			set = st.executeQuery();
			
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				System.out.println(menu_name+"  "+price+"  "+count+"  "+price*count);
				totalpay += price*count;
			}
			
			
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println();
			System.out.println("     금     액 :     "+totalpay*0.9);
			System.out.println("     부  가  세 :    "+totalpay*0.1);
			System.out.println();
			System.out.println("     합     계 :     "+totalpay);
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println("     고객성함 : "+memvo.getMember_name());
			System.out.println(paydate);
			
			
			
			
			
			
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
		
	}

	
	
	public String[] billist(String pay_seq, MemberVO memvo) {
		
		String[] output = new String[2];
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);

			String paydate = "";
			int totalpay = 0 ;
			
			String sql = "select * " + 
					"from main_11 main , member mem , main_menu menu\r\n" + 
					"where main.member_seq = mem.member_seq\r\n" + 
					"and main.main_seq = menu.menu_id\r\n" + 
					"and pay_seq= ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			ResultSet set = st.executeQuery();
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				totalpay += price*count;
			}
			

			sql = "select * " + 
					"from side_11 side, member mem, side_menu menu\r\n" + 
					"where side.member_seq = mem.member_seq\r\n" + 
					"and side.side_seq = menu.menu_id\r\n" + 
					"and pay_seq = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			
			set = st.executeQuery();
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				totalpay += price*count;
			}
			
			
			sql = "select * " + 
					"from drink_11 drink, member mem, drink_menu menu\r\n" + 
					"where drink.member_seq = mem.member_seq\r\n" + 
					"and drink.drink_seq =menu.menu_id\r\n" + 
					"and pay_seq = ?";
			
			
			st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			set = st.executeQuery();
			
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				totalpay += price*count;
			}
			
			
			String total = Integer.toString(totalpay);
			
			output[0] = total;
			output[1] = paydate;
			
			
			
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
		
		return output;
	}
	
	
	
	
	
	
}
