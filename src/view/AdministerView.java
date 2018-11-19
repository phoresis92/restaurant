package view;

import java.util.Scanner;

public class AdministerView {

	Scanner sc = new Scanner(System.in);
	
	
	
	public void adminMain() {
		
		
		System.out.println("=== 관리자 페이지 ===");

		System.out.println("1. 메   뉴   수   정"); // 메뉴 추가 , 제거 , 수정
		System.out.println("2. 주   문   관   리"); // 
		System.out.println("3. 예   약   관   리"); // 예약 수정, 삭제, 추가
		System.out.println("4. 게 시 판   관 리"); // 게시판 삭제 수정
		System.out.println("5. 회   원   관   리"); // 관리자 권한 주기, 회원탈퇴, 
		System.out.println("10. 종          료");
	
		System.out.print("번호 : ");
		String page = sc.nextLine();
	
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
