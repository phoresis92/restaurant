package view;

import java.util.Scanner;

import dao.AdminMemberDAO;
import main.Administer;
import main.Static;

public class AdminMemberView {

	AdminMemberDAO dao = new AdminMemberDAO();
	Scanner sc = new Scanner(System.in);
	Administer adm = new Administer();
	
	
	public void memberlist() {
		
		
		System.out.print("최대 페이지 : "+dao.totalmempage()+" | 페이지 입력(뒤로가기 -1 | 선택  0): ");
		String in = sc.nextLine();
		if(Static.isInt(in)) {
			int num = Integer.parseInt(in);
			
			if(in.equals("-1")) {
				adm.adminMain();
				return;
			}else if(in.equals("0")) {
				String select = "";
				while(select.equals("")) {
					System.out.println("1.권리자 권한 변경");
					System.out.println("2.회원삭제");
					System.out.println("3.나가기");
					System.out.print("번호 선택 : ");
					select = sc.nextLine();
					
					if(Static.isInt(select)) {
						if(select.equals("1")) {
							
							System.out.print("관리자 권한 변경할 회원번호 입력 : ");
							String change = sc.nextLine();
							if(Static.isInt(change)) {
								int chan = Integer.parseInt(change);
								if(chan == 1) {
									System.out.println("최고 관리자입니다");
									select = "";
								}else if(chan<1) {
									System.out.println("범위를 초과 하였습니다.");
									select = "";
								}else if(dao.selectmem(chan) != chan) {
									System.out.println("번호를 확인하고 입력해 주세요");
									select = "";
								}else {
									
									System.out.println("회원번호 "+chan+"  관리자 : [1]  일반사용자 : [0]");
									System.out.print("[ 0 / 1 ] : ");
									String admin = sc.nextLine();
									if(Static.isInt(admin)) {
										int adm = Integer.parseInt(admin);
										if(admin.equals("1")) {
											int result = dao.adminchange(adm, chan);
											if(result == 1) {
												System.out.println("회원번호:"+chan+"을 관리자로 변경하였습니다.");
												memberlist();
												return;
											}else {
												System.out.println("변경도중 오류가 발생 하였습니다.");
												memberlist();
												return;
											}
										}else if(admin.equals("0")) {
											int result = dao.adminchange(adm, chan);
											if(result == 1) {
												System.out.println("회원번호:"+chan+"을 일반사용자로 변경하였습니다.");
												memberlist();
												return;
											}else {
												System.out.println("변경도중 오류가 발생 하였습니다.");
												memberlist();
												return;
											}
										}
										
									}else {
										System.out.println("숫자를 입력해 주세요");
										
									}
									
									
									
									
								}
							}else {
								System.out.println("숫자를 입력해 주세요");
								select ="";
							}

							
						}else if(select.equals("2")) {
							String seqnum = "";
							while(seqnum.equals("")) {
								System.out.print("삭제할 회원번호 입력 : ");
								seqnum = sc.nextLine();
								
								if(Static.isInt(seqnum)){
									int seq =  Integer.parseInt(seqnum);
									if(seq == 1) {
										System.out.println("최고관리자 입니다");
										seqnum = "";
									}else if(dao.selectmem(seq) != seq) {
										System.out.println("존재하지 않는 회원 입니다.");
										seqnum = ""; 
									}else {
										
										
										
										System.out.println("==================================");
										System.out.println();
										System.out.println("정말로 삭제하시겠습니까? ");
										System.out.println();
										System.out.println("==================================");
										System.out.print("\t(Y/N)로 입력해 주세요 : ");
										String yn = sc.nextLine();
										if(yn.equalsIgnoreCase("y")) {
											
											
											int result = dao.deleteMember(seq);
											if(result == 1) {
												System.out.println("회원삭제가 완료되었습니다.");
												adm.adminMain();
												return;
											}else {
												System.out.println("회원삭제 도중 오류가 발생 하였습니다.");
												adm.adminMain();
												return;
											}
											
											
										}else if(yn.equalsIgnoreCase("n")) {
											adm.adminMain();
											return;
										}else {
											System.out.println("Y/N 로 입력해주세요");
											adm.adminMain();
											return;
										}
										
										
										
									}
									
								}else {
									System.out.println("숫자를 입력해 주세요");
									seqnum = "";
								}
							}
									
									
							
							
							
							
						}else if(select.equals("3")) {
							adm.adminMain();
							return;
							
						}else {
							System.out.println("번호를 확인하고 입력해주세요");
							select = "";
						}
						
						
						
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						select = "";
					}
					
				
				}
				
				
				
				
				
			}else if(num <1 || num>dao.totalmempage()){
				System.out.println("페이지 범위 1 ~ "+dao.totalmempage());
				memberlist();
				return;
			}else {
				
				dao.showmember(num);
				memberlist();
				return;
				
			}
			
			
			
		}else {
			System.out.println("숫자를 입력해주세요");
			memberlist();
			return;
		}
		
		

		
	}
	

	public void firstpage() {
		
		
		dao.showmember(1);
		
		
	}
	
	
	
	
}
