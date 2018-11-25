package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.OrderDAO;
import main.Static;
import vo.MemberVO;
import vo.MenuVO;

public class Orderview {
	
	Calendar cal = Calendar.getInstance();
	OrderDAO dao = new OrderDAO();
	HashMap<MemberVO, ArrayList<MenuVO>> login;
	ArrayList<MenuVO> basketlist ;
	Scanner sc = new Scanner(System.in);
	int pay_cnt;
	
	
	public Orderview(HashMap<MemberVO, ArrayList<MenuVO>> login ,ArrayList<MenuVO> basketlist, int pay_cnt) {
		this.login = login;
		this.basketlist = basketlist;
		this.pay_cnt = pay_cnt;
	}
	
	public Orderview(HashMap<MemberVO, ArrayList<MenuVO>> login ,ArrayList<MenuVO> basketlist) {
		
		this.login = login;
		this.basketlist = basketlist;
		
	}

	
	public void orderpage() {
		
		System.out.println("1. 장바구니 결제하기");
		System.out.println("2. 결제 내역 보기 / 취소하기");
		System.out.println("3. 뒤로가기");
		System.out.print("번호 입력 : ");
		
		String menu = sc.nextLine();
		if(Static.isInt(menu)) {
			
			if(menu.equals("3")) {
				return;
			}else if(menu.equals("1")) {
				
				
				pay();
				
				
				
				
			}else if(menu.equals("2")) {
				
				bill();
				
			}else {
				System.out.println("번호 확인 후 입력해 주세요");
				orderpage();
			}
			
		}else {
			System.out.println("숫자를 입력해 주세요");
			orderpage();
		}
		
		
		
	}
	
	
	public void bill() { //결제내역보기,취소하기
		
		MemberVO memvo = null ;
		Set<MemberVO> set = login.keySet();
		for(MemberVO b : set) {
			memvo = b;
		}
		HashMap<Integer,String[]> list = dao.transaction(memvo); // 결제내역 가져오기
		String num = "";
		while(num.equals("")) {
			int pagesize = 10; // 페이지 게시물 개수
			String a = "";
			String b = "";
			String c = "";
			String d = "";
			try {
			System.out.println("----------------------------------------------");
			for(int i = 1 ; i <= pagesize; i++) {
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
								bill();
								return;
							}else {
								
								
								
								dao.showbill(list.get(choi)[0]/*결제번호*/);
								
								System.out.println("결제를 취소하시겠습니까?");
								System.out.print("(Y/N) : ");
								String yorn = "";
								while(yorn.equals("")) {
									yorn= sc.nextLine();
									if(yorn.equalsIgnoreCase("y")) {
										
										
										
										dao.rtdelete(list.get(choi)[0], memvo);
										
										
										
										
									}else if(yorn.equalsIgnoreCase("n")) {
										bill();
										return;
									}else {
										System.out.println("Y 또는 N 으로 입력해 주세요");
										yorn = "";
									}
									
								}
								
								
								
								
								
								
								
								bill();
								return;
							}
							
							
						}else {
							System.out.println("숫자를 입력해 주세요");
							select = "";
							bill();
							return;
						}
						
					}else if(selec == -1) {
						orderpage();
						return;
					}
					
					int start = 0;
					int last = selec*pagesize;
					
					if(selec>lastpage||selec<=0) {
						System.out.println("페이지 범위를 넘었습니다.");
						select = "";
						bill();
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
				
				String yn = "";
				while(yn.equals("")) {
					System.out.println("==================================");
					System.out.println();
					System.out.println("\t  결제하시겠습니까?");
					System.out.println();
					System.out.println("==================================");
					System.out.print("\t(Y/N)로 입력해 주세요 : ");
					
					yn = sc.nextLine();
					if(yn.equalsIgnoreCase("y")) {
						
						
						
						
						
						MemberVO memvo = null ;
						Set<MemberVO> set = login.keySet();
						for(MemberVO b : set) {
							memvo = b;
						}
						
						int member_seq = memvo.getSeq();
						
						String pay_seq = memvo.getSeq()+""+cal.get(Calendar.DATE)+""+cal.get(Calendar.HOUR_OF_DAY)+""+cal.get(Calendar.MINUTE)+""+Math.floor(cal.get(Calendar.MILLISECOND));
					
						
						int count = 0;
						MenuVO menuvo;
						for(MenuVO a : basketlist) {
							int menu_seq = 0;
							int menu_count = 0;

							menuvo = a;
							
							menu_seq = menuvo.getMenu_id();
							menu_count = menuvo.getCount();
							
						
						
						
						
						int month = cal.get(Calendar.MONTH)+1;
						
						int kind = 0;
						if(menu_seq<=1000) {
							kind = 1;
						}else if(menu_seq<=1500) {
							kind = 2;
						}else {
							kind = 3;
						}
						
						
						
						int result = dao.charge(pay_seq, member_seq, menu_seq, menu_count, month , kind);
						if(result == 1) {
							count++;
						}
						
						}
						
						if (basketlist.size() == count) {
							
							System.out.println("구매가 정상처리 되었습니다");
							basketlist.clear();
							return;
						}else {
						System.out.println("구매도중 오류가 발생 하였습니다.");
						
						}
						
						
						
						
						
						
						
						
					}else if(yn.equalsIgnoreCase("n")) {
						return;
					}else {
						System.out.println("Y/N 로 입력해주세요");
						yn = "";
					}
				}
			
			}else System.out.println("로그인 후 사용하세요");
			}catch(Exception e) {
				System.out.println("===================================");
				System.out.println();
				System.out.println("\t  장바구니가 비어있습니다.");
				System.out.println();
				System.out.println("===================================");
			}
		
		
	}
	
	
	
				
}// class end
	
	
	

