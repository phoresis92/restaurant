package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.Static;
import vo.MemberVO;
import vo.MenuVO;

public class Orderview {
	
	HashMap<MemberVO, ArrayList<MenuVO>> login;
	ArrayList<MenuVO> basketlist ;
	Scanner sc = new Scanner(System.in);
	
	public Orderview(HashMap<MemberVO, ArrayList<MenuVO>> login ,ArrayList<MenuVO> basketlist) {
		this.login = login;
		this.basketlist = basketlist;
	}

	
	public void pay() {
		
		
		try {
		if(Static.isLogin(login)) {
			if(basketlist.size() == 0) {
				System.out.println("===================================");
				System.out.println();
				System.out.println("\t  장바구니가 비어있습니다.");
				System.out.println();
				System.out.println("===================================");
				return;
			}
			
			int cnt = 1;
			int sum = 0;
			System.out.println("-----------------------------------");
			System.out.println();
			System.out.println("\t        장바구니 목록");
			System.out.println();
			for(MenuVO vo : basketlist) {
				System.out.println(cnt++ +") "+ vo);
				sum +=vo.getPrice()*vo.getCount();
			}
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println();
			System.out.println("\t   총가격 : "+sum+"원");
			System.out.println();
			System.out.println("-----------------------------------");
			
			
			yorn();
			
		
		}else System.out.println("로그인 후 사용하세요");
		}catch(Exception e) {
			System.out.println("===================================");
			System.out.println();
			System.out.println("\t  장바구니가 비어있습니다.");
			System.out.println();
			System.out.println("===================================");
		}

	}// pay method end
				
	
	public void yorn() {
		
		System.out.println("==================================");
		System.out.println();
		System.out.println("\t  결제하시겠습니까?");
		System.out.println();
		System.out.println("==================================");
		System.out.print("\t(Y/N)로 입력해 주세요 : ");
		
		String yn = sc.nextLine();
		if(yn.equalsIgnoreCase("y")) {
			
			
			
		}else if(yn.equalsIgnoreCase("n")) {
			return;
		}else {
			System.out.println("Y/N 로 입력해주세요");
			yorn();
		}
		
	}
	
				
}// class end
	
	
	

