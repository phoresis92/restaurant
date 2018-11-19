package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;
import vo.MenuVO;

public class SalesDAO {
/*
	public int charge(MemberVO vo, ArrayList<MenuVO> basket) {
		
		
		ArrayList<MenuVO> list = null;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 완료");

			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			//System.out.println("연결성공");

			con.setAutoCommit(false);

			String sql = "select menu_id, menu_name, price, commen from main_menu";

			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			list = new ArrayList<MenuVO>();

			int cnt = 1;
			
			System.out.println("==============================================");
			while (rs.next()) {
				int id = rs.getInt("menu_id");
				String name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");

				
				MenuVO vo = new MenuVO(id, name, price, comment);

				list.add(vo);

				System.out.println(cnt++ + ") " + name + " " + price + "원 \r\n\t" + comment);
				System.out.println();
			}
			System.out.println("==============================================");


			con.commit();

			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("컬럼값이 잘못 입력되었습니다.");
			

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
	*/
}
