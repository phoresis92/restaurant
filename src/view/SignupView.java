package view;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.SignupDAO;
import main.Static;
import vo.MemberVO;

public class SignupView {
	Scanner sc = new Scanner(System.in);

	public void input() {
		System.out.println("회원가입을 시작합니다.");
		System.out.println("(취소를 원하시면 언제든 [ 0 ]을 입력해 주세요)");
		System.out.println("--------------------------------------------");

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
		System.out.println("--------------------------------------------");

		
		int mpw = 0;
		while(mpw == 0) {

			System.out.print("비밀번호 입력(4자리 숫자) : ");
			String member_pw = sc.nextLine();
			if(Static.isInt(member_pw)) {
				if(member_pw.equals("0")) {
					return;
				}
				if(member_pw.length() == 4) {
					mpw = Integer.parseInt(member_pw);
				}else {
					System.out.println("4자리 숫자로 입력해주세요");
				}
			}else if(Static.spaceCheck(member_pw)){
				System.out.println("공백을 입력하실 수 없습니다");
			}else {
				System.out.println("숫자를 입력해주세요");
			}
		}
		System.out.println("--------------------------------------------");

		String member_name = "";
		while(member_name.equals("")) {
			
			System.out.print("이름 입력(3자리 이상 5자리 이하) : ");
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
				
			System.out.println("--------------------------------------------");

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
		
		System.out.println("--------------------------------------------");

		
		String email = "";
		while(email.equals("")) {
			System.out.print("이메일 입력 : ");
			email = sc.nextLine().toLowerCase();
			String reg = "\\w+@[a-zA-Z]+.[a-zA-Z]+";
			if(email.equals("0")) {
				return;
			}
			
			if(!Pattern.matches(reg, email)) {
				System.out.println("이메일 형식이 맞지 않습니다.");
				email = "";
			}
			
			
		}
		System.out.println("--------------------------------------------");


		MemberVO vo = new MemberVO(0, member_id, mpw, member_name, phone, email, 0);
		SignupDAO dao = new SignupDAO();
		int result = dao.insertMember(vo);
		if(result ==1) {
			System.out.println("회원가입이 완료되었습니다.");
		}else {
			System.out.println("일치하는 전화번호가 있습니다. 다른 전화번호를 입력해 주세요");
		}

	}
	
}
