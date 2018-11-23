package view;

import java.util.HashMap;
import java.util.Scanner;

import dao.AdminOrderDAO;
import dao.OrderDAO;
import main.Administer;
import main.Static;

public class AdminOrderView {
	AdminOrderDAO aodao = new AdminOrderDAO();
	Scanner sc = new Scanner(System.in);
	Administer admin = new Administer();

public void allorder() {
		
		
		String menu = "";
		while(menu.equals("")) {
			System.out.println("1.모든 주문 내역 보기");
			System.out.println("2.주문 취소 내역 보기");
			System.out.println("3.뒤로가기");
			System.out.print("번호 입력 :");
			menu = sc.nextLine();
			if(Static.isInt(menu)) {
				
				if(menu.equals("3")) {
					admin.adminMain();
					return;
				}else if(menu.equals("1")) {
					
					HashMap<Integer,String[]> list = aodao.allist();
					int pagesize = 10; // 페이지 게시물 개수
					String a = "";
					String b = "";
					String c = "";
					String d = "";
					try {
					System.out.println("----------------------------------------------");
					for(int i = 1 ; i <= pagesize ; i++) {
						a = list.get(i)[0];
						b = list.get(i)[1];
						c = list.get(i)[2];
						d = list.get(i)[3];
						System.out.println(i+") 결제번호: "+a+" 회원아이디 : "+d+" 결제일 : "+b+" 총금액 : "+c);
					}
					System.out.println("----------------------------------------------");

					System.out.println("1번 페이지 입니다.");
					System.out.println("----------------------------------------------");
					}catch(Exception e) {
						
					}
					String select = "";
					while(select.equals("")) {
						
						int lastpage = (int)Math.ceil(list.size()/(double)pagesize);
						System.out.println("마지막페이지:"+lastpage+" (뒤로가기:-1 / 확정시:0)페이지 입력:");
						
						select = sc.nextLine();
						if(Static.isInt(select)) {
							int selec = Integer.parseInt(select);
							if(selec == 0) {
								
								System.out.print("선택할 게시물 번호를 입력하세요 : ");
								String choice = sc.nextLine();
								if(Static.isInt(choice)) {
									int choi = Integer.parseInt(choice);
									
									if(choi<=0||choi>list.size()) {
										System.out.println("게시물 범위를 넘어갔습니다.");
										allorder();
										return;
									}else {
										OrderDAO orderdao = new OrderDAO();
										orderdao.showbill(list.get(choi)[0]);
										allorder();
										return;
									}
									
									
								}else {
									System.out.println("숫자를 입력해 주세요");
									select = "";
									allorder();
									return;
								}
								
							}else if(selec == -1) {
								allorder();
								return;
							}
							
							int start = 0;
							int last = selec*pagesize;
							
							if(selec>lastpage||selec<=0) {
								System.out.println("페이지 범위를 넘었습니다.");
								select = "";
								allorder();
								return;
							}else if(list.size()<last) {
								start = list.size()-pagesize;
								last = list.size();
							}else {
								start = selec;
							}
							
							
							System.out.println("----------------------------------------------");

							for(int i = (selec-1)*pagesize+1 ; i <= last ; i++) {
								a = list.get(i)[0];
								b = list.get(i)[1];
								c = list.get(i)[2];
								d = list.get(i)[3];
							
								System.out.println(i+") 결제번호: "+a+" 회원아이디 : "+d+" 결제일 : "+b+" 총금액 : "+c);
								
							}
							System.out.println("----------------------------------------------");

							System.out.println(selec+"번 페이지 입니다.");
							System.out.println("----------------------------------------------");

							if(select.equals("0")) {
								
							}else {
								select = "";
								
							}
						
						}else {
							System.out.println("숫자를 입력해주세요");
							select = "";
						}
					
					}
					
				}else if(menu.equals("2")) {
					
					cancelorder();
					
				}
				
			}else {
				System.out.println("숫자로 입력해 주세요");
				menu = "";
			}
		}
		
		
		
		
	}
	
	
	
	public void cancelorder() {
		
		
		HashMap<Integer,String[]> list = aodao.showlist();
			
			int pagesize = 5; // 페이지 게시물 개수
			String a = "";
			String b = "";
			String c = "";
			String d = "";
			try {
			System.out.println("----------------------------------------------");
			for(int i = 1 ; i <= 5 ; i++) {
				a = list.get(i)[0];
				b = list.get(i)[1];
				c = list.get(i)[2];
				d = list.get(i)[3];
				System.out.println(i+") 결제번호: "+a+" 회원아이디 : "+d+" 결제일 : "+b+" 총금액 : "+c);
			}
			
			System.out.println("----------------------------------------------");

			System.out.println("1번 페이지 입니다.");
			System.out.println("----------------------------------------------");
			}catch(Exception e) {
				
			}
			
			String select = "";
			while(select.equals("")) {
				
				int lastpage = (int)Math.ceil(list.size()/(double)pagesize);
				System.out.println("마지막페이지:"+lastpage+" (뒤로가기:-1 / 확정시:0)페이지 입력:");
				
				select = sc.nextLine();
				if(Static.isInt(select)) {
					int selec = Integer.parseInt(select);
					if(selec == 0) {
						
						System.out.print("선택할 게시물 번호를 입력하세요 : ");
						String choice = sc.nextLine();
						if(Static.isInt(choice)) {
							int choi = Integer.parseInt(choice);
							
							if(choi<=0||choi>list.size()) {
								System.out.println("게시물 범위를 넘어갔습니다.");
								allorder();
								return;
							}else {
								OrderDAO orderdao = new OrderDAO();
								orderdao.showbill(list.get(choi)[0]);
								
								
								
								
								
								System.out.println("결제를 취소하시겠습니까?");
								System.out.print("(Y/N) : ");
								String yorn = "";
								while(yorn.equals("")) {
									yorn= sc.nextLine();
									if(yorn.equalsIgnoreCase("y")) {
										
										
										
										int result = aodao.deleteorder(list.get(choi)[0]);
										if(result == 1) {
											System.out.println("결제 취소가 완료되었습니다.");
										}else {
											System.out.println("결제취소도중 오류가 발생하였습니다.");
										}
										
										
										
									}else if(yorn.equalsIgnoreCase("n")) {
										cancelorder();
										return;
									}else {
										System.out.println("Y 또는 N 으로 입력해 주세요");
										yorn = "";
									}
									
								}
								
								
								
								
								
								
								
								
								
								
								allorder();
								return;
							}
							
							
						}else {
							System.out.println("숫자를 입력해 주세요");
							select = "";
							allorder();
							return;
						}
						
					}else if(selec == -1) {
						allorder();
						return;
					}
					
					int start = 0;
					int last = selec*pagesize;
					
					if(selec>lastpage||selec<=0) {
						System.out.println("페이지 범위를 넘었습니다.");
						select = "";
						allorder();
						return;
					}else if(list.size()<last) {
						start = list.size()-pagesize;
						last = list.size();
					}else {
						start = selec;
					}
					
					
					System.out.println("----------------------------------------------");

					for(int i = (selec-1)*pagesize+1 ; i <= last ; i++) {
						a = list.get(i)[0];
						b = list.get(i)[1];
						c = list.get(i)[2];
						d = list.get(i)[3];
					
						System.out.println(i+") 결제번호: "+a+" 회원아이디 : "+d+" 결제일 : "+b+" 총금액 : "+c);
						
					}
					System.out.println("----------------------------------------------");

					System.out.println(selec+"번 페이지 입니다.");
					System.out.println("----------------------------------------------");

					if(select.equals("0")) {
						
					}else {
						select = "";
						
					}
				
				}else {
					System.out.println("숫자를 입력해주세요");
					select = "";
				}
			
			}
			
			
		
		
	}
	
	
	
	
}
