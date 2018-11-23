package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import dao.LoginDAO;
import main.Static;
import vo.MemberVO;
import vo.MenuVO;

public class LoginView {

	Scanner sc = new Scanner(System.in);
	
	HashMap<MemberVO, ArrayList<MenuVO>> login = null;
	
	MemberVO mvo = null;
	
	
	public LoginView() {}
	
	
	public LoginView(HashMap<MemberVO, ArrayList<MenuVO>> login, MemberVO mvo){
		this.login = login;
		this.mvo = mvo;
	}
	
	
	
	
	public MemberVO loginow() {
		
		
		
		
				System.out.println("로그인을 시작합니다.");
				System.out.print("아이디 입력 : ");
				String member_id = sc.nextLine().toLowerCase();
				System.out.print("비밀번호 입력 : ");
				String password = sc.nextLine();
				int pw = 0; 
				if(Static.isInt(password)) {
					pw = Integer.parseInt(password);
				}else {
					System.out.println("비밀번호는 숫자로 입력해주세요");
					logout();
				}
	
				LoginDAO dao = new LoginDAO();
				mvo = dao.selectlogin(member_id, pw);
				login.put(mvo, null);
				
		
			
			
		return mvo;
		

	}//method end
	
	
	
	
public void logout() {
		
	login.clear();
	login.remove(mvo);
	mvo = new MemberVO();
		
	}
	
	
	public void Searchidpw() {
		
		System.out.println("1. 아이디 찾기");
		System.out.println("2. 비밀번호 찾기");
		System.out.println("3. 뒤로가기");
		System.out.print("번호를 입력하세요 : ");
		String menu = sc.nextLine();
		
		if(Static.isInt(menu)) {
			if(menu.equals("1")) {
				System.out.println("아이디 찾기를 시작합니다.");


				String member_name = "";
				while(member_name.equals("")) {
					
					System.out.print("[0]입력시 취소)이름 입력(3자리 이상 5자리 이하) : ");
					member_name = sc.nextLine().toLowerCase();
					
					if(member_name.equals("0")) {
						return;
					}
					
					if(member_name.length() < 3) {
						System.out.println("3자리 이상 입력해 주세요");
						member_name = "";
					}else if(member_name.length()>5) {
						System.out.println("5자리 이하로 입력해 주세요");
						member_name = "";
					}else if(Static.spaceCheck(member_name)) {
						System.out.println("공백을 입력할 수 없습니다");
						member_name = "";
					}
						

				}
				
				
				
				String phone = "";
				while(phone.equals("")) {
					System.out.print("[0]입력시 취소)전화번호 입력(- 포함): ");
					phone = sc.nextLine();
					String reg = "[0-9]{2,3}-\\d{3,4}-[0-9]{3,4}";
					
					if(phone.equals("0")) {
						return;
					}
					
					if(!Pattern.matches(reg, phone)) {
						System.out.println("번호 구성에 맞게 입력해 주세요");
						phone = "";
					}
				}
				
				
	
				LoginDAO dao = new LoginDAO();
				dao.searchId(member_name, phone);
				
			}else if(menu.equals("2")) {
				System.out.println("비밀번호 찾기를 시작합니다.");
				
				
				String member_id = "";
				while(member_id.equals("")) {
					System.out.print("아이디 입력(4자리 이상 10자리 이하) : ");
					member_id = sc.nextLine().toLowerCase();
					
					if(member_id.equals("0")) {
						return;
					}
					
					if(Static.spaceCheck(member_id)) {
						System.out.println("공백을 입력하실 수 없습니다");
						member_id ="";
					}else if(member_id.length() <4) {
						System.out.println("4자리 이상 입력해 주세요");
						member_id ="";
					}else if(member_id.length()>10) {
						System.out.println("10자리 이하로 입력해 주세요");
						member_id ="";
					}
							
				}
				
				
				String phone = "";
				while(phone.equals("")) {
					System.out.print("전화번호 입력([-]를 입력해주세요): ");
					phone = sc.nextLine();
					String reg = "[0-9]{2,3}-\\d{3,4}-[0-9]{3,4}";
					
					if(phone.equals("0")) {
						return;
					}
					
					if(!Pattern.matches(reg, phone)) {
						System.out.println("번호 구성에 맞게 입력해 주세요");
						phone = "";
					}
				}
					
				LoginDAO dao = new LoginDAO();
				dao.searchPw(member_id, phone);
			}else if(menu.equals("3")) {
				return;
			}else {
				System.out.println("번호확인 후 다시 입력해주세요");
			}
		}else {
			System.out.println("번호를 입력해주세요");
			Searchidpw();
		}
		
	}
	
	

}
