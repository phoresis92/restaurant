package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import vo.BoardVO;
import vo.MemberVO;
import vo.MenuVO;

public class BoardDAO {

	
	public int maxseq() {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select count(*) from board";

			PreparedStatement pt2 = con.prepareStatement(sql);// 준비
			ResultSet rs = pt2.executeQuery();// 실행 저장

			rs.next();
			result = rs.getInt("count(*)");

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
	
	
	public int totalpage() {
		int boardnum = 5;
		Double count = 0D;
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select count(*) from board";

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
	}

	
	public ArrayList<BoardVO> selectBoard(int pagenumber) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from(select rownum r, t.* " + " from (select * from board order by seq desc) t)"
					+ " where r >= ? and r <= ?";

			int boardnum = 5;
			int start = (pagenumber - 1) * boardnum + 1;
			int end = pagenumber * boardnum;

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, start);
			pt.setInt(2, end);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				int viewcount = rs.getInt("viewcount");

				System.out.print("   " + seq + ")");
				System.out.print(" 제목 : " + title);
				System.out.print(" | 작성자 : " + writer);
				System.out.print(" | 조회수 : " + viewcount);

				System.out.println();
			}
			System.out.println("\t\t1\t~\t" + totalpage());
			System.out.println("\t       현재 페이지 [" + pagenumber + "] 페이지 입니다.");

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

		return list;
	}// selectBoard(페이지별 조회)

	
	public void viewcountup(int boardseq) {
		
		
		int result = 0;
		Connection con = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

					
			
			String sql = "update board set viewcount = viewcount + 1 "
					+ " where seq = ?";


			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, boardseq);
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
				e.printStackTrace();
			}
		}

		
		
	}
	

	public int insertboard(String title, String contents, String menutitle, HashMap<MemberVO, ArrayList<MenuVO>> login) {

		int result = 0;
		Connection con = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			MemberVO mvo = null;
			
			Set<MemberVO> set = login.keySet();
			for(MemberVO vo : set) {
				mvo = vo;
			}
			
			
			
			String sql = "insert into board values"
	+ "((select max(seq) from board)+1, ?, ?, ?, ?, ?, sysdate, 0, ?)";


			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, title);
			pt.setString(2, contents);
			pt.setString(3, menutitle);
			pt.setString(4, mvo.getMember_id());
			pt.setInt(5, mvo.getSeq());
			pt.setInt(6, mvo.getMember_pw());
			
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

	
	public void searchSeq(int boardSeq) {

		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

		
			
			String sql = "select * from board where seq = ?";

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, boardSeq);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				String menu = rs.getString("menu");
				String indate = rs.getString("indate");
				int viewcount = rs.getInt("viewcount");
				
			
				
				System.out.print("글번호 : "+seq);
				System.out.print("      제목 : " + title);
				System.out.println("      작성자 : " + writer);
				System.out.println("--------------------------------------------");
				System.out.println();
				System.out.println(" 내용 : "+ contents);
				System.out.println();
				System.out.println(" 메뉴 : "+ menu);
				System.out.println("--------------------------------------------");

				System.out.print(" 조회수 : " + viewcount);
				System.out.println("       작성일 : "+ indate);
				System.out.println("--------------------------------------------");

			}
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}
		
	}
	
	
	public int searchSeq(int boardSeq, HashMap<MemberVO, ArrayList<MenuVO>> login) {
		int cnt = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			MemberVO mvo = null;
			Set<MemberVO> set = login.keySet();
			for(MemberVO vo : set) {
				mvo = vo;
			}
			
			String sql = "select * from board where seq = ?";

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, boardSeq);
			ResultSet rs = pt.executeQuery();

			
			while (rs.next()) {

				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				int writer_seq = rs.getInt("writer_seq");
				String contents = rs.getString("contents");
				String menu = rs.getString("menu");
				String indate = rs.getString("indate");
				int viewcount = rs.getInt("viewcount");
				
				if(mvo.getSeq() == writer_seq) {
					cnt++;
				}
				
				
			}
			
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}
		return cnt;
	}

	
	
	public int searchBoard(String column, String value) {
		
		int cnt = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "";
			if(column.equals("viewcount")) {
				sql = "select * from board where "+column+" = ?";
			}else {
				value = "%"+value+"%";
				sql = "select * from board where "+column+" like ?";
				
			}
			
			

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, value);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				
				cnt++;
				
				int seq = rs.getInt("seq");
				viewcountup(seq);
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				String menu = rs.getString("menu");
				String indate = rs.getString("indate");
				int viewcount = rs.getInt("viewcount");
			
				
				
				
				System.out.print("글번호 : "+seq);
				System.out.print("      제목 : " + title);
				System.out.println("      작성자 : " + writer);
				System.out.println("--------------------------------------------");
				System.out.println();
				System.out.println(" 내용 : "+ contents);
				System.out.println();
				System.out.println(" 메뉴 : "+ menu);
				System.out.println("--------------------------------------------");

				System.out.print(" 조회수 : " + viewcount);
				System.out.println("       작성일 : "+ indate);
				System.out.println("--------------------------------------------");

			}
		
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}
		return cnt;
	}

	
	public int searchMy(int writer_seq) {
		int result = 1;
		ArrayList<Integer> list = null;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "select * from board where writer_seq = ?";

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, writer_seq);
			ResultSet rs = pt.executeQuery();

			
			int cnt = 0;
			while (rs.next()) {

				int seq = rs.getInt("seq");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				String menu = rs.getString("menu");
				String indate = rs.getString("indate");
				int viewcount = rs.getInt("viewcount");
			
				
				System.out.print("   " + seq + ")");
				System.out.print(" 제목 : " + title);
				System.out.print(" | 작성자 : " + writer);
				System.out.print(" | 조회수 : " + viewcount);

				System.out.println();
				cnt++;
			}
			
			
			if(cnt == 0) {
				System.out.println("내 게시물이 없습니다");
				System.out.println("--------------------------------------------");

				result = 0;
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}
		return result;
	}

	
	public int updateboard(String title, String contents, String menutitle, int board_seq, HashMap<MemberVO, ArrayList<MenuVO>> login) {

		int result = 0;
		Connection con = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			MemberVO mvo = null;
			
			Set<MemberVO> set = login.keySet();
			for(MemberVO vo : set) {
				mvo = vo;
			}
			
			
			
			String sql = "update board set title = ?, contents = ?, menu = ? where seq = ?";


			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, title);
			pt.setString(2, contents);
			pt.setString(3, menutitle);
			pt.setInt(4, board_seq);
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

	
	public int deleteboard(int board_seq) {

		int result = 0;
		Connection con = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			
			
			
			String sql = "delete board where seq = ?";


			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, board_seq);
			result = pt.executeUpdate();

			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();// 상황적
			} catch (SQLException e) {
			}
		}

		return result;
	}

	
	
	
	
	
	
	
}
