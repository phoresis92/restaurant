package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Restaurant;
import vo.MemberVO;

public class LoginDAO {
	
	public MemberVO selectlogin(String member_id, int pw) {
		MemberVO vo = null;
		Connection con = null;
		Restaurant rest = new Restaurant();
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from member where member_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, member_id);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				int seq = rs.getInt("member_seq");
				String mid = rs.getString("member_id");
				int tablepw = rs.getInt("pw");
				String member_name = rs.getString("member_name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int administer = rs.getInt("administer");
				
				vo = new MemberVO(seq, mid, tablepw, member_name, phone, email, administer);
				
			}
			if(vo == null) {
				System.out.println("아이디가 일치하지 않습니다.");
				rest.logout();
			}else if(vo.getMember_pw() == pw) {
				System.out.println("로그인 되었습니다.");
			}else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				
				rest.logout();
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return vo;

	}

	public void searchId(String member_name, String phone) {
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from member where phone = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, phone);
			ResultSet rs = pt.executeQuery();
			
			String mname = "";
			String mphone = "";
			String findid = "";
			while(rs.next()) {
				mname = rs.getString("member_name");
				mphone = rs.getString("phone");
				findid = rs.getString("member_id");
				
			}
			
			
			if(mphone.equals(phone)&&mname.equals(member_name)) {
				System.out.println("찾으시는 아이디는 : [ "+findid+" ] 입니다");
			}else {
				System.out.println("일치하는 정보가 없습니다.");
			}
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

	public void searchPw(String member_id, String phone) {
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from member where member_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, member_id);
			ResultSet rs = pt.executeQuery();
			
			String mphone = "";
			String mid = "";
			int pw = 0;
			while(rs.next()) {
				mphone = rs.getString("phone");
				mid = rs.getString("member_id");
				pw = rs.getInt("pw");
			}
			
			if(member_id.equals(mid)) {
				if(phone.equals(mphone)) {
					System.out.println("찾으시는 비밀번호는 : [ "+pw+" ] 입니다");
				}else {
					System.out.println("연락처가 일치하지 않습니다.");
					return;
				}
			}else {
				System.out.println("아이디가 일치하지 않습니다.");
				return;
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

}
