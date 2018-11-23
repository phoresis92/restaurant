package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.MemberVO;

public class SignupDAO {
	
	public int insertMember(MemberVO vo) {
		
		Connection con = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "study", "study");

			String sql = "insert into member values((select max(member_seq) from member)+1, ?, ?, ?, ?, ?, 0)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, vo.getMember_id());
			st.setInt(2, vo.getMember_pw());
			st.setString(3, vo.getMember_name());
			st.setString(4, vo.getPhone());
			st.setString(5, vo.getEmail());
			result = st.executeUpdate();
			

		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;

	}

}
