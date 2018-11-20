package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import vo.MemberVO;
import vo.MenuVO;

public class ReserveDAO {

	
	public int insert(String time, int rtable, String year, String month, String day, HashMap<MemberVO,ArrayList<MenuVO>> login) {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", month+year, "1234");
			// System.out.println("연결성공");

			con.setAutoCommit(false);
			
			MemberVO vo = null ;
			Set<MemberVO> set = login.keySet();
			for(MemberVO a : set) {
				vo = a;
			}
			
			
			int cnt = 0;
			String sql ="";
			for(int i = 1 ; i < 9 ; i++) {
				sql = "select * from "+day+" where tabnum = "+i;
	
				
				Statement state = con.createStatement();
				ResultSet rset = state.executeQuery(sql);
				
				
				while(rset.next()) {
					
					cnt = count(rset, cnt, vo);
					
				}
			}
			
			
			
			
			if(cnt >3) {
				System.out.println("예약은 하루에 4개 이상 하실 수 없습니다");
				System.out.println("관리자에게 문의하세요");
				return 0;
			}
			
			sql = "select "+time+"mem from "+day+" where tabnum = ?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, rtable);
			ResultSet rs = st.executeQuery();
			
			int who = 0;
			while(rs.next()) {
				who = rs.getInt(time+"mem");
			}
			
			if(who!=0&&who != vo.getSeq()) {
				System.out.println("이미 예약된 테이블 입니다");
				return 0;
			}
			
			
			sql = "update "+day+" set "+time+"mem = "+vo.getSeq()+" where tabnum = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, rtable);
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
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
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
		
	}// insert method end
	
	
	public ArrayList<int[]> drawTable(String year, String month, String day) {
		
		ArrayList<int[]> list = null;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", month+year, "1234");
			// System.out.println("연결성공");

			con.setAutoCommit(false);


			String sql = "select * from "+day+" order by tabnum";   

			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sql);

			list = new ArrayList<int[]>();
			
			int[] tabone = new int[9];
			int[] tabtwo = new int[9];
			int[] tabthr = new int[9];
			int[] tabfou = new int[9];
			int[] tabfiv = new int[9];
			int[] tabsix = new int[9];
			int[] tabsev = new int[9];
			int[] tabeig = new int[9];
			
				
		
			while(rs.next()) {
				
				int tabnum = rs.getInt("tabnum");
				int tenmem = rs.getInt("tenmem");
				int elemem = rs.getInt("elemem");
				int twelmem = rs.getInt("twelmem");
				int thirmem = rs.getInt("thirmem");
				int fourmem = rs.getInt("fourmem");
				int fifmem = rs.getInt("fifmem");
				int sixmem = rs.getInt("sixmem");
				int sevmem = rs.getInt("sevmem");
				int eighmem = rs.getInt("eighmem");
				
				int[] inputmem = {tenmem,elemem,twelmem,thirmem,fourmem,fifmem,sixmem,sevmem,eighmem};
				
				if(tabnum == 1) {
					
					inputline(tabone, inputmem);
					
				}else if(tabnum ==2) {
					
					inputline(tabtwo, inputmem);
					
				}else if(tabnum ==3) {
					
					inputline(tabthr, inputmem);

				}else if(tabnum ==4) {
					
					inputline(tabfou, inputmem);
					
				}else if(tabnum ==5) {
					
					inputline(tabfiv, inputmem);
					
				}else if(tabnum ==6) {
					
					inputline(tabsix, inputmem);
					
				}else if(tabnum ==7) {
					
					inputline(tabsev, inputmem);
					
				}else if(tabnum ==8) {
					
					inputline(tabeig, inputmem);
					
				}
				
			}
			
			
			list.add(tabone);
			list.add(tabtwo);
			list.add(tabthr);
			list.add(tabfou);
			list.add(tabfiv);
			list.add(tabsix);
			list.add(tabsev);
			list.add(tabeig);
				

			

			con.commit();

			rs.close();
			ps.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
		} finally {

			try {
				con.close(); // 상황에 따른 문제 / 자바의 문제가 아니다
			} catch (SQLException e) {
			}
			// System.out.println("연결해제성공");
		}
		
		return list;
	}//drawTable method end
	
	
	
	public int delete(String time, int ctable, String year, String month, String day, HashMap<MemberVO, ArrayList<MenuVO>> login) {
		
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", month+year, "1234");
			// System.out.println("연결성공");

			con.setAutoCommit(false);
			
			MemberVO vo = null ;
			Set<MemberVO> set = login.keySet();
			for(MemberVO a : set) {
				vo = a;
			}
			
			
			String sql = "select "+time+"mem from "+day+" where tabnum = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, ctable);
			ResultSet rset = st.executeQuery();
			int memseq = 0 ;
			while(rset.next()) {
				memseq = rset.getInt(time+"mem");
			}
			
			if(memseq != vo.getSeq()) {
				System.out.println("본인의 예약만 취소할 수 있습니다!");
				return 0;

			}
			
			
			sql = "update "+day+" set "+time+"mem = 0 where tabnum = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, ctable);
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
			System.out.println("연결정보확인!!!");
			System.out.println(e.getMessage());
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
	
	
	
	public void inputline(int[] tabnumname, int[] inputmem) {
		
		for(int i = 0 ; i<9 ; i++) {
			if(i == 0) {
				tabnumname[i] = inputmem[0];
			}else if(i==1) {
				tabnumname[i] = inputmem[1];
			}else if(i==2) {
				tabnumname[i] = inputmem[2];
			}else if(i==3) {
				tabnumname[i] = inputmem[3];
			}else if(i==4) {
				tabnumname[i] = inputmem[4];
			}else if(i==5) {
				tabnumname[i] = inputmem[5];
			}else if(i==6) {
				tabnumname[i] = inputmem[6];
			}else if(i==7) {
				tabnumname[i] = inputmem[7];
			}else if(i==8) {
				tabnumname[i] = inputmem[8];
			}
		}
		
	}//inputline method end
	
	
	
	public int count(ResultSet rset, int cnt, MemberVO vo) throws SQLException {
		
		
		if(rset.getInt("tenmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("elemem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("twelmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("thirmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("fourmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("fifmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("sixmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("sevmem") == vo.getSeq()) {
			cnt++;
		}
		if(rset.getInt("eighmem") == vo.getSeq()) {
			cnt++;
		}
		
		return cnt;
		
	}
	
	
	
}//class end;
