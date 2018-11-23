package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.LoginDAO;
import view.BoardView;
import view.LoginView;
import view.MenuView;
import view.Orderview;
import view.ReserveView;
import view.SignupView;
import vo.MemberVO;
import vo.MenuVO;

public class Restaurant {
	
	public static void main(String[] args) {
		
		HashMap<MemberVO, ArrayList<MenuVO>> login = null;
		ArrayList<MenuVO> basketlist = null;
		MemberVO mvo = null;
		int pay_cnt = 0;
		
		
		
		
		while(true) {
			System.out.println("--------------------------------------------");

			try {
				if(login.size() == 0 || login == null || mvo == null) {
					System.out.println("          안녕하세요. 로그인후 다양한 메뉴사용이 가능합니다! ");
				}else if(mvo.getMember_id().equals("system")) {
					System.out.println("=========== 관리자 모드로 진입하였습니다! ===========");
					
				}else {
					System.out.println("\t      안녕하세요. "+mvo.getMember_id()+" 님 환영합니다!");
				}
			}catch(Exception e) {
				System.out.println("          안녕하세요. 로그인후 다양한 메뉴사용이 가능합니다! ");
			}
			System.out.println("--------------------------------------------");

		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t1. 메   뉴   보   기");
		System.out.println("\t\t2. 주   문   하   기");
		System.out.println("\t\t3. 예   약   하   기");
		System.out.println("\t\t4. 리 뷰   게 시 판");
		System.out.println("\t\t5. 로      그      인");
		System.out.println("\t\t6. 로   그   아   웃");
		System.out.println("\t\t7. 계   정   찾   기");
		System.out.println("\t\t8. 회   원   가   입");
		System.out.println("\t\t9. 관 리 자   메 뉴");
		System.out.println("\t\t10. 종          료");
		System.out.println("--------------------------------------------");
		System.out.print("\t              번호를 입력하세요 : ");
		String menu = sc.nextLine();
		System.out.println("--------------------------------------------");
		
		
		if(Static.isInt(menu)) {
			int select =  Integer.parseInt(menu);
			
			if(select == 10) {
				System.exit(0);
			}else if(select == 1) {
				MenuView mv = new MenuView(login, basketlist);
				basketlist = mv.menuSearch();
				try {
					login.put(mvo, basketlist);
				}catch(Exception e){
					
				}
			}else if(select == 2) {
				if(Static.isLogin(login)) {
					Orderview ov = new Orderview(login, basketlist, pay_cnt++);
					ov.orderpage();
				}else {
					System.out.println("로그인 후 사용하세요");
				}
			}else if(select == 3) {
				if(Static.isLogin(login)) {
					ReserveView rv = new ReserveView(login);
					rv.when();
				}else {
					System.out.println("로그인 후 사용하세요");
				}
			}else if(select == 4) {
				
				BoardView bv = new BoardView(login);
				bv.showlist();
				bv.start();
				
			}else if(select == 5) {
				try {
					if(login == null || login.size() == 0) {
						
						login = new HashMap<MemberVO, ArrayList<MenuVO>>();
						basketlist = new ArrayList<MenuVO>();
						LoginView lv = new LoginView(login, mvo);
						mvo = lv.loginow();
						login.put(mvo, basketlist);
					}else {
						System.out.println("\t현재 [ "+mvo.getMember_id()+" ] 로 로그인 되어 있습니다.");
						
					}
				}catch(Exception e) {
					login.clear();
					login.remove(mvo);
					mvo = null;
				}
			}else if(select == 6) {
				if(login == null|| login.size() == 0) {
					System.out.println("로그인을 먼저 해주세요.");
					
				}else {
					
					System.out.println("==================================");
					System.out.println();
					System.out.println("\t  로그아웃하시겠습니까?");
					System.out.println();
					System.out.println("==================================");
					System.out.print("\t(Y/N)로 입력해 주세요 : ");
					String yorn = sc.nextLine();
					if(yorn.equalsIgnoreCase("Y")) {
						login.clear();
						login.remove(mvo);
						mvo = new MemberVO();
						System.out.println("\t     로그아웃이 정상적으로 처리되었습니다.");
					}else if(yorn.equalsIgnoreCase("N")) {
						
					}else {
						System.out.println("Y/N 로 입력해주세요");
						
					}
					
				}
			}else if(select == 7) {
				if(Static.isLogin(login)) {
					System.out.println("로그인 사용중에 검색하실 수 없습니다");
				}else {
					LoginView lv = new LoginView(login, mvo);
					lv.Searchidpw();
					
				}
			}else if(select == 8) {
				try {
				if(login == null || login.size() == 0) {
					SignupView sv = new SignupView();
					sv.input();
				}else {
					System.out.println("\t현재 [ "+mvo.getMember_id()+" ] 로 로그인 되어 있습니다.");
					
				}
				}catch(Exception e) {
					
				}
			}else if(select == 9) {
				if(Static.isLogin(login)) {
					
					if(mvo.getAdminister() == 1) {
						
						Administer ap = new Administer(login);
						ap.adminMain();
						
						
						
					}else {
						System.out.println("관리자 페이지 입니다");
					}
				}else {
					System.out.println("관리자 페이지 입니다");
				}
			}
			
		}else {
			System.out.println("숫자를 입력해주세요!");
		}
		
		}//while end
	
	}//main end
	
	public void logout() {
		
		HashMap<MemberVO, ArrayList<MenuVO>> login = null;
		MemberVO mvo = null;
		
		login.clear();
		login.remove(mvo);
		mvo = new MemberVO();
		
	}
	
	
}
