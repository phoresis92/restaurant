package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;

public class AdminMemberDAO {

	
	public void showmember(int page) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from(select rownum r, t.* " + " from (select * from member order by member_seq desc) t)"
					+ " where r >= ? and r <= ?";
			
			int boardnum = 10;
			int start = (page - 1) * boardnum + 1;
			int end = page * boardnum;
			
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, start);
			pt.setInt(2, end);
			ResultSet rs = pt.executeQuery();
			System.out.println();
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println();
				while(rs.next()) {
					int seq = rs.getInt("member_seq");
					String mid = rs.getString("member_id");
					int tablepw = rs.getInt("pw");
					String member_name = rs.getString("member_name");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					int administer = rs.getInt("administer");
						
					System.out.println("회원번호:"+seq+" 회원아이디:"+mid+" 비밀번호:"+tablepw+" 이름:"+member_name+
								" 전화번호:"+phone+" 이메일:"+email+" 관리자권한식별:"+administer);
				}
				System.out.println();
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("현재 페이지 ["+page+"]");

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
		
	}//
	
	
	public int totalmempage() {

		int boardnum = 10;
		Double count = 0D;
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select count(*) from member";

			PreparedStatement pt2 = con.prepareStatement(sql);// 준비
			ResultSet rs = pt2.executeQuery();// 실행 저장

			rs.next();
			count = rs.getDouble("count(*)");

			result = (int) Math.ceil(count / boardnum);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			// 5.연결해제. 파일close 소켓close
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}

		if(result == 0) {
			result = 1;
		}
		return result;
	}//
	
	
	
	public int maxmem() {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select count(*) from member";

			PreparedStatement pt2 = con.prepareStatement(sql);// 준비
			ResultSet rs = pt2.executeQuery();// 실행 저장
			
			rs.next();
			int count = rs.getInt("count(*)");

			result =count;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			// 5.연결해제. 파일close 소켓close
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}

		if(result == 0) {
			result = 1;
		}
		return result;
	}//
	
	
	public int selectmem(int seq) {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from member where member_seq=?";

			PreparedStatement pt2 = con.prepareStatement(sql);// 준비
			pt2.setInt(1, seq);
			ResultSet rs = pt2.executeQuery();// 실행 저장

			rs.next();
			
			
			result = rs.getInt("member_seq");
				
				

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("회원번호가 존재하지않습니다.");
			
		} finally {
			// 5.연결해제. 파일close 소켓close
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}

		return result;
	}//
	
	
	
	public int adminchange(int administer, int seq) {

		int result = 0;
		Connection con = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			
			
			
			String sql = "update member set administer = ? where member_seq = ?";


			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, administer);
			pt.setInt(2, seq);
			result = pt.executeUpdate();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			// 5.연결해제. 파일close 소켓close
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}

		return result;
	}
	
	
	
	public int deleteMember(int seq) {

		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			
			String sql = "delete member  where member_seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, seq);
	
			result = pt.executeUpdate();
			System.out.println("회원번호:"+seq+"를 삭제하였습니다.");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			// 5.연결해제. 파일close 소켓close
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	
}
