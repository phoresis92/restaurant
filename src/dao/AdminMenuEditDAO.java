package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MenuVO;

public class AdminMenuEditDAO {

	MenuVO mvo = new MenuVO();
	

	public void menuAdd(MenuVO vo, String seq) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql1 = "select * from menu where menu_name=?";
			PreparedStatement pt = con.prepareStatement(sql1);
			pt.setString(1, vo.getMenu_name());
			ResultSet rs = pt.executeQuery();
			if (rs.next()) {
				System.out.println("아래와 중복된 이름의 메뉴입니다. 확인하시고 다시 시도해 주세요.");
				menuSearch(vo.getMenu_name());

			} else {
				String sql = "insert into menu values( " + seq + ",?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, vo.getMenu_name());
				st.setInt(2, vo.getPrice());
				st.setString(3, vo.getComment());
				int result = st.executeUpdate();
				if (result == 1) {
					System.out.println("메뉴가 추가되었습니다.");
					menuSearch(vo.getMenu_name());
				} else {
					System.out.println("메뉴 추가 실패 : 다시 시도하여 주세요.");
				}
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

	public void menuModify(String menuColumn, int menu_id, String value) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql1 = "select * from menu where menu_id=?";
			PreparedStatement pt = con.prepareStatement(sql1);
			pt.setInt(1, menu_id);
			ResultSet rs = pt.executeQuery();
			if (rs.next()) {

				String sql = "update menu set " + menuColumn + "=? where menu_id = ?";
				PreparedStatement st = con.prepareStatement(sql);

				st.setString(1, value);
				st.setInt(2, menu_id);

				int result = st.executeUpdate();
				if (result == 1) {
					System.out.println("메뉴가 수정되었습니다.");
					ResultSet rs1 = pt.executeQuery();
					rs1.next();
					menuSearch(rs1.getString("menu_name"));

				} else {
					System.out.println("메뉴 수정 실패 : 다시 시도하여 주세요.");
				}

			} else {
				System.out.println("존재하지 않는 번호입니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("길이를 수정해서 입력해 주세요");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

	public void menuDelete(int menu_id) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql1 = "select * from menu where menu_id=?";
			PreparedStatement pt = con.prepareStatement(sql1);
			pt.setInt(1, menu_id);
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				menuSearch(rs.getString("menu_name"));
				String sql = "delete menu where menu_id= ?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, menu_id);
				int result = st.executeUpdate();

				if (result == 1) {
					System.out.println("메뉴가 삭제되었습니다.");
				} else {
					System.out.println("메뉴 삭제 실패 : 다시 시도하여 주세요.");
				}
			} else {
				System.out.println("존재하지 않는 번호입니다.");
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

	public void main_menuSearch() {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			String sql = "select * from menu where menu_id>=1 and menu_id<=1000 order by menu_id";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			System.out.println("<<< 메 인  메 뉴 >>>");
			while (rs.next()) {
				int menu_id = rs.getInt("menu_id");
				String menu_name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");

				
				if(menu_id>=1 &&menu_id<=9) {
					System.out.println("[  " + menu_id + " ] ▶ 메뉴명 : " + menu_name + "\t\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
				}else if (menu_id>=10 &&menu_id<=99) {
					System.out.println("[ " + menu_id + " ] ▶ 메뉴명 : " + menu_name + "\t\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
				}else if (menu_id>=100 &&menu_id<=999) {
					System.out.println("[" + menu_id + "] ▶ 메뉴명 : " + menu_name + "\t\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
				}
				
				
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

	public void side_menuSearch() {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			String sql = "select * from menu where menu_id>=1001 and menu_id<=1500 order by menu_id";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			System.out.println("<<< 사 이 드  메 뉴 >>>");
			while (rs.next()) {
				int menu_id = rs.getInt("menu_id");
				String menu_name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");

				//System.out.println("[" + menu_id + "] ▶ 메뉴명 : " + menu_name + "\t\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
				System.out.println("[" + menu_id + "] ▶ 메뉴명 : " + menu_name + "\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
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

	public void drink_menuSearch() {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			String sql = "select * from menu where menu_id>=1500 and menu_id<=2000 order by menu_id";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			System.out.println("<<< 드 링 크  메 뉴 >>>");
			while (rs.next()) {
				int menu_id = rs.getInt("menu_id");
				String menu_name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");

				System.out.println("[" + menu_id + "] ▶ 메뉴명 : " + menu_name + "\t\t  ▶ 가격 : " + price + "원 \t  ▶ 설명 : " + comment);
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

	public void menuSearch(String name) {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			String sql = "select * from menu where menu_name = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, name);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int menu_id = rs.getInt("menu_id");
				String menu_name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
				System.out.println("[ [" + menu_id + "] ▷ 메뉴 : " + menu_name + "     ▷ 가격 : " + price + "원     ▷ 설명 : " + comment + " ]");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
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

	public void menuSearch(int id) {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");
			String sql = "select * from menu where menu_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				int menu_id = rs.getInt("menu_id");
				String menu_name = rs.getString("menu_name");
				int price = rs.getInt("price");
				String comment = rs.getString("commen");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
				System.out.println("[ [" + menu_id + "] ▷ 메뉴 : " + menu_name + "     ▷ 가격 : " + price + "원     ▷ 설명 : " + comment + " ]");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
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
