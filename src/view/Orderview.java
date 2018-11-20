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
								
								
								
								
								MenuVO menuvo;
								
								MemberVO memvo = null ;
								Set<MemberVO> set = login.keySet();
								for(MemberVO b : set) {
									memvo = b;
								}
								
								int member_seq = memvo.getSeq();
								
								String pay_seq = memvo.getSeq()+""+cal.get(Calendar.DATE)+""+cal.get(Calendar.HOUR_OF_DAY)+""+cal.get(Calendar.MINUTE)+""+Math.floor(cal.get(Calendar.MILLISECOND));
								System.out.println(pay_seq);
								for(MenuVO a : basketlist) {
									int menu_seq = 0;
									int menu_count = 0;

									menuvo = a;
									
									menu_seq = menuvo.getMenu_id();
									menu_count = menuvo.getCount();
									
								
								
								
								
								int month = cal.get(Calendar.MONTH)+1;
								
								String table = "";
								if(menu_seq<=1000) {
									table = "main_"+month;
								}else if(menu_seq<=1500) {
									table = "side_"+month;
								}else {
									table = "drink_"+month;
								}
								
								
								System.out.println(member_seq);
								System.out.println(menu_seq);
								System.out.println(menu_count);
								System.out.println(table);
								
								
								int result = dao.charge(pay_seq, member_seq, menu_seq, menu_count, table);
								if(result == 1) {
									System.out.println("구매가 정상처리 되었습니다");
									
								}else {
									System.out.println("구매도중 오류가 발생 하였습니다.");
									
								}
								
								
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
	
	
	public void bill() {
		
		MemberVO memvo = null ;
		Set<MemberVO> set = login.keySet();
		for(MemberVO b : set) {
			memvo = b;
		}
		HashMap<Integer,String> pay = dao.transaction(memvo);
		String num = "";
		while(num.equals("")) {
			
			System.out.print("확인할 번호를 입력해주세요(0 뒤로가기) : ");
			num = sc.nextLine();
			if(Static.isInt(num)) {
				int number = Integer.parseInt(num);
				if(number == 0) {
					orderpage();
					return;
				}if(number<0||number>pay.size()) {
					System.out.println("범위안의 숫자를 입력해 주세요");
					num = "";
				}else {
					
					
					dao.showbill(pay.get(number), memvo);
					
					System.out.println("결제를 취소하시겠습니까?");
					System.out.print("(Y/N) : ");
					String yorn = "";
					while(yorn.equals("")) {
						yorn= sc.nextLine();
						if(yorn.equalsIgnoreCase("y")) {
							
							
							
							
							
							
						}else if(yorn.equalsIgnoreCase("n")) {
							bill();
							return;
						}else {
							System.out.println("Y 또는 N 으로 입력해 주세요");
							yorn = "";
						}
						
					}
				}
				
			}else {
				System.out.println("숫자를 입력해 주세요");
				num = "";
			}
		}
		
		
		
	}

	
				
}// class end
	
	
	

