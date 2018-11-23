package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import vo.MemberVO;

public class OrderDAO {

	public int charge(String pay_seq, int member_seq, int menu_seq, int menu_count ,int month, int kind) {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "insert into salary_"+month+" values("+pay_seq+", ? , ? , sysdate, ?, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, member_seq);
			st.setInt(2, menu_seq);
			st.setInt(3, menu_count);
			st.setInt(4, kind);
			
			
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
	
	
	
	public HashMap<Integer,String[]>  transaction(MemberVO memvo) {
		
		
		HashMap<Integer,String[]> pay = null ;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);
			
			
			
			String sql = "select distinct pay_seq, paydate from salary_11 \r\n" + 
					"where member_seq = ? order by paydate desc";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, memvo.getSeq());
			
			ResultSet set = st.executeQuery();
			
			int num = 1;
			pay = new HashMap<Integer,String[]>();
			System.out.println("--------------------------------------------------------");
			while(set.next()) {
				String pay_seq = set.getString("pay_seq");

			

			

			
			
				
				String[] output = billist(pay_seq);
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
				//System.out.println(pay.get(num));
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
	
	
	
	public void showbill(String pay_seq) {
		
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
			System.out.println("\t    신       용       승       인");
			System.out.println();
			System.out.println("===================================");
			System.out.println();
			
			String sql = "select * " + 
					"from salary_11 salary , member mem , menu\r\n" + 
					"where salary.member_seq = mem.member_seq\r\n" + 
					"and salary.menu_seq = menu.menu_id\r\n" + 
					"and pay_seq= ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			String paydate = "";
			String member_name= "";
			ResultSet set = st.executeQuery();
			
			int cnt = 0;
			
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				member_name = set.getString("member_name");
				System.out.println("   "+cnt+") "+menu_name+"  "+price+"  "+count+"  "+price*count);
				totalpay += price*count;
			
			}
			
			System.out.println();

			
			
			
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println();
			System.out.println("     금     액 :     "+totalpay*0.9);
			System.out.println("     부  가  세 :    "+totalpay*0.1);
			System.out.println();
			System.out.println("     합     계 :     "+totalpay);
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println("     고객성함 : "+member_name);
			System.out.println(paydate);
			System.out.println("----------------------------------");

			
			
			
			
			
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

	
	
	public String[] billist(String pay_seq) {
		
		String[] output = new String[3];
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			
			con.setAutoCommit(false);

			String member_id = "";
			String paydate = "";
			int totalpay = 0 ;
			
			String sql = "select *\r\n" + 
					"from salary_11 salary , member mem , menu\r\n" + 
					"where salary.member_seq = mem.member_seq\r\n" + 
					"and salary.menu_seq = menu.menu_id\r\n" + 
					"and pay_seq = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			ResultSet set = st.executeQuery();
			while(set.next()) {
				String menu_name = set.getString("menu_name");
				int count = set.getInt("count");
				int price = set.getInt("price");
				paydate = set.getString("paydate");
				member_id = set.getString("member_id");
				totalpay += price*count;
			}
			
			

			
			String total = Integer.toString(totalpay);
			
			output[0] = total;
			output[1] = paydate;
			output[2] = member_id;
			
			
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

			}
			// System.out.println("연결해제성공");
		}
		
		return output;
	}
	
	
	
	public void rtdelete(String pay_seq, MemberVO memvo) {
		
		
		
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "select pay_seq from salary_11_delete where pay_seq = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			ResultSet rs = st.executeQuery();

			
			int cnt = 0;
			while(rs.next()) {
				
				rs.getString("pay_seq");
				cnt++;
			
			}
			if(cnt >=1) {
				
				System.out.println("이미 취소 대기중인 상태입니다");
				return;
				
			}else {
				
				
				int result = insertdelete(pay_seq);
				if(result == 1) {
					System.out.println("관리자의 취소 승인 후 삭제됩니다.");
					System.out.println("취소 대기 처리 되었습니다.");
				}else {
					System.out.println("취소도중 에러가 발생하였습니다.");
				}
				
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
		
		
		
	}
	
	
	
	public int insertdelete(String pay_seq) {
		
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "insert into salary_11_delete values(?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, pay_seq);
			
			
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
	
	
}
