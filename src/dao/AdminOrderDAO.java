package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.Static;

public class AdminOrderDAO {

	
	public HashMap<Integer,String[]> showlist() {
		
		HashMap<Integer,String[]> pay = null ;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);
			
			
			
			String sql = "select distinct pay_seq, paydate\r\n" + 
					"from salary_11 salary , member mem , menu\r\n" + 
					"where salary.member_seq = mem.member_seq\r\n" + 
					"and salary.menu_seq = menu.menu_id\r\n" + 
					"and pay_seq in (select pay_seq from salary_11_delete)\r\n" + 
					"order by paydate desc";

			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet set = st.executeQuery();
			
			int num = 1;
			pay = new HashMap<Integer,String[]>();
			System.out.println("--------------------------------------------------------");
			System.out.println("====================== 주문취소 대기중 ======================");
			while(set.next()) {
				String pay_seq = set.getString("pay_seq");

				
				OrderDAO orderdao = new OrderDAO();
				String[] output = orderdao.billist(pay_seq);
				String totalpay = output[0];
				String paydate = output[1];
				String member_id = output[2];
				String[] data = new String[4];
				data[0] = pay_seq;
				data[1] = paydate;
				data[2] = totalpay;
				data[3] = member_id;
				
				//System.out.println(num+") 결제번호: "+pay_seq+" 결제일 : "+paydate+" 총금액 : "+totalpay);
				
				pay.put(num, data);
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
	
	
	public int deleteorder(String pay_seq) {
		
		
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "delete salary_11_delete where pay_seq = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			
			result = st.executeUpdate();
			if(result == 0) {
				System.out.println("삭제도중 오류가 발생 하였습니다.");
			}

			sql = "delete salary_11 where pay_seq =?";
			
			st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			result = st.executeUpdate();
			
			if(result == 0) {
				System.out.println("삭제도중 오류가 발생 하였습니다.");
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
		return result;
		
		
		
	}
	
	
	public HashMap<Integer,String[]> allist() {
		
		
		HashMap<Integer,String[]> pay = null ;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);
			
			
			
			String sql = "select distinct pay_seq, paydate from salary_11 salary , member mem , menu\r\n" + 
					"where salary.member_seq = mem.member_seq\r\n" + 
					"and salary.menu_seq = menu.menu_id order by paydate desc";

			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet set = st.executeQuery();
			
			int num = 1;
			
			
			
			pay = new HashMap<Integer,String[]>();
			while(set.next()) {
				String pay_seq = set.getString("pay_seq");

			
				OrderDAO orderdao = new OrderDAO();
				String[] output = orderdao.billist(pay_seq);
				String totalpay = output[0];
				String paydate = output[1];
				String member_id = output[2];
				
				//System.out.println(num+") 결제번호: "+pay_seq+" 결제일 : "+paydate+" 총금액 : "+totalpay);
				String[] data = new String[4];
				data[0] = pay_seq;
				data[1] = paydate;
				data[2] = totalpay;
				data[3] = member_id;
				
				pay.put(num, data);
				num++;

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
	
		return pay;
		
		
	}
	
	
}
