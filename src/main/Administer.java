package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import view.AdminBoardView;
import view.AdminMemberView;
import view.AdminMenuEditView;
import view.AdminOrderView;
import view.AdminReserveView;
import view.AdminSalesView;
import vo.MemberVO;
import vo.MenuVO;

public class Administer {

	HashMap<MemberVO, ArrayList<MenuVO>> login = null;
	
	AdminSalesView asv = new AdminSalesView();
	Scanner sc = new Scanner(System.in);
	
	
	
	
	
	public Administer(HashMap<MemberVO, ArrayList<MenuVO>> login) {
		super();
		this.login = login;
	}

	public Administer() {
		
	}




	public void adminMain() {
		
		
		System.out.println("=== 관리자 페이지 ===");

		System.out.println("1. 메   뉴   수   정"); // 메뉴 추가 , 제거 , 수정
		System.out.println("2. 주   문   관   리"); // 
		System.out.println("3. 매   출   현   황"); // 메뉴별 매출, 월별매출, 
		System.out.println("4. 예   약   관   리"); // 예약 수정, 삭제, 추가
		System.out.println("5. 게 시 판   관 리"); // 게시판 삭제 수정
		System.out.println("6. 회   원   관   리"); // 관리자 권한 주기, 회원탈퇴, 
		System.out.println("10. 메 인 페 이 지");
	
		System.out.print("번호 : ");
		String page = sc.nextLine();
	
		if(Static.isInt(page)) {
			
			if(page.equals("10")) {
				return;
			}else if(page.equals("1")) {
				
				
				AdminMenuEditView amev = new AdminMenuEditView();
				amev.menuEdit();
				
				return;
				
			}else if(page.equals("2")) {
				
				AdminOrderView aov = new AdminOrderView();
				aov.allorder();
				return;
				
			}else if(page.equals("3")){
				
				System.out.println("1.월별 매출 현황");
				System.out.println("2.메뉴별 매출 현황");
				System.out.println("3.종류별 매출");
				System.out.println("4.뒤로가기");
				System.out.print("번호 입력 : ");
				String menu = sc.nextLine();
				if(Static.isInt(menu)) {
					
					if(menu.equals("4")) {
						adminMain();
						return;
						
					}else if(menu.equals("1")) {
						asv.monthsales();

						adminMain();
						return;
						
					}else if(menu.equals("2")) {
						
						asv.menusales();
						
						adminMain();
						return;
						
					}else if(menu.equals("3")) {
						
						asv.kindsales();
						adminMain();
						return;
						
					}else {
						System.out.println("번호를 확인하고 입력하세요");
						adminMain();
						return;
					}
					
					
					
				}else {
					System.out.println("숫자를 입력해 주세요");
					adminMain();
					return;
				}
				
				
				
				
				
				
				
			}else if(page.equals("4")) {
				

				AdminReserveView arv = new AdminReserveView(login);
				arv.when();
				
				
				
			}else if(page.equals("5")) {
				
				AdminBoardView abv = new AdminBoardView();
				abv.showlist();
				abv.start();
				return;
				
			}else if(page.equals("6")) {
				

				AdminMemberView amv = new AdminMemberView();
				amv.firstpage();
				amv.memberlist();
				
				
				
			
				
			}else {
				System.out.println("번호를 확인해 주세요");
				adminMain();
				return;
			}
			
		}else {
			System.out.println("숫자를 입력해 주세요");
			adminMain();
			return;
		}
		
		
	}
	

	
	
	
}
