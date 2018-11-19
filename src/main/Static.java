package main;

import java.util.ArrayList;
import java.util.HashMap;

import dao.BoardDAO;
import vo.MemberVO;
import vo.MenuVO;

public class Static {

	public static boolean isInt(String str) {
		
		try {
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
		
		
	}
	
	public static boolean isLogin(HashMap<MemberVO, ArrayList<MenuVO>> login) {
		if(login == null) {
			return false;
		}else if(login.size() == 0) {
			System.out.println("로그인 후 이용해주세요");
			return false;
		}else return true;
	}
	
	
	public static boolean isMyboard(int boardseq, HashMap<MemberVO, ArrayList<MenuVO>> login) {
		
		BoardDAO dao = new BoardDAO();
		if(dao.searchSeq(boardseq, login) >0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public static boolean spaceCheck(String spaceCheck)	{
		int cnt = 0;
	    for(int i = 0 ; i < spaceCheck.length() ; i++) {
	        if(spaceCheck.charAt(i) == ' ') {
	        	cnt++;
	        }
	    }
	    if(cnt == 0) {
	    	return false;
	    }else return true;
	}
	
	
}
