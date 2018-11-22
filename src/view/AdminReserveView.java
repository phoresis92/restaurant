package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import dao.AdminMemberDAO;
import dao.AdminReserveDAO;
import main.Administer;
import main.CalendarPrint;
import main.Static;
import vo.MemberVO;
import vo.MenuVO;

public class AdminReserveView {

	
	HashMap<MemberVO, ArrayList<MenuVO>> login;
	Scanner sc = new Scanner(System.in);
	AdminReserveDAO dao = new AdminReserveDAO();
	
	
	public AdminReserveView(HashMap<MemberVO, ArrayList<MenuVO>> login) {
		this.login = login;
	}
	
	public AdminReserveView() {
		
	}
	
	
	public void when() {
		
		Calendar cal = Calendar.getInstance();
		
		CalendarPrint cp = new CalendarPrint(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
		cp.printhismonth(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
		cp.printCal();
		
		System.out.println("구현 범위 [ 2018/11월]");
		System.out.println("구현 범위 [ 2019/1월]");
		System.out.println("1. 일자 선택");
		System.out.println("2. 다른 연도, 다른 월 선택하기");
		System.out.println("3. 예약 취소하기");
		System.out.println("4. 뒤로가기");
		System.out.print("번호를 입력해 주세요 : ");
		String menunum = sc.nextLine();
		
		
		String resultyear = "";
		String resultmonth = "";
		String resultday = "";
		
		
		if(Static.isInt(menunum)) {
			if(menunum.equals("1")) {
				
				
				String reserveday = "";
				int rday = 0;
				while(reserveday.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 예약할 일자  : ");
					reserveday = sc.nextLine();
					if(Static.isInt(reserveday)) {
						rday = Integer.parseInt(reserveday);
						if(rday == 0) {
							when();
							return;
						}else if(rday<1||rday>cp.lastDay) {
							System.out.println("범위를 초과하였습니다.");
							reserveday = "";
						}else {
							
							
							
							resultday = reserveday;
							
							resultmonth = transformonth(cp.monthnum);
							
							resultyear = cp.yearnum;
							
							reserve(resultyear, resultmonth, resultday);
							
							
							
							
							
						}
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						reserveday = "";
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
			}else if(menunum.equals("2")) {
				String yearnum = "";
				int year = 0;
				while(yearnum.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 4자리 연도 : ");
					yearnum = sc.nextLine();
					String reg = "[0-9]{4}";
					
					
					if(Static.isInt(yearnum)) {
						year = Integer.parseInt(yearnum);
						if(year == 0) {
							when();
							return;
						}else if(Pattern.matches(reg, yearnum)){
							
							
							
							
						}else {
							System.out.println("4자리 연도를 입력해 주세요");
							yearnum = "";
						}
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						yearnum = "";
					}
					
				}
				
				String monthnum = "";
				int month = 0;
				while(monthnum.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 월 : ");
					monthnum = sc.nextLine();
					
					
					if(Static.isInt(monthnum)) {
						month = Integer.parseInt(monthnum);
						
						if(month == 0) {
							when();
							return;
						}else if(month<1||month>12) {
							System.out.println("1 ~ 12월 안의 숫자로 입력해 주세요");
							monthnum = "";
						}else {
														
						}
						
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						monthnum = "";
					}
					
				}
				
				
				
				CalendarPrint calp = new CalendarPrint(year, month);
				calp.printhismonth(year,month);
				calp.printCal();
				
				
				
				
				
				
				
				String reserveday = "";
				int rday = 0;
				while(reserveday.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 예약할 일자  : ");
					reserveday = sc.nextLine();
					if(Static.isInt(reserveday)) {
						rday = Integer.parseInt(reserveday);
						if(rday == 0) {
							when();
							return;
						}else if(rday<1||rday>cp.lastDay) {
							System.out.println("범위를 초과하였습니다.");
							reserveday = "";
						}else {
							
							
							
							
							
							
							
							resultday = reserveday;
							
							resultmonth = transformonth(monthnum);
							
							resultyear = yearnum;
							
							reserve(resultyear, resultmonth, resultday);
							
							
							
							
							
						}
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						reserveday = "";
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}else if(menunum.equals("3")) {
				
				
				String yearnum = "";
				int year = 0;
				while(yearnum.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 4자리 연도 : ");
					yearnum = sc.nextLine();
					String reg = "[0-9]{4}";
					
					
					if(Static.isInt(yearnum)) {
						year = Integer.parseInt(yearnum);
						if(year == 0) {
							when();
							return;
						}else if(Pattern.matches(reg, yearnum)){
							
							
							
							
						}else {
							System.out.println("4자리 연도를 입력해 주세요");
							yearnum = "";
						}
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						yearnum = "";
					}
					
				}
				
				String monthnum = "";
				int month = 0;
				while(monthnum.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 월 : ");
					monthnum = sc.nextLine();
					
					
					if(Static.isInt(monthnum)) {
						month = Integer.parseInt(monthnum);
						
						if(month == 0) {
							when();
							return;
						}else if(month<1||month>12) {
							System.out.println("1 ~ 12월 안의 숫자로 입력해 주세요");
							monthnum = "";
						}else {
														
						}
						
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						monthnum = "";
					}
					
				}
				
				
				
				CalendarPrint calp = new CalendarPrint(year, month);
				calp.printhismonth(year,month);
				calp.printCal();
				
				
				
				
				
				
				
				
				
				String reserveday = "";
				int rday = 0;
				while(reserveday.equals("")) {
					System.out.print("([ 0 ]입력시 뒤로갑니다) 취소할 일자  : ");
					reserveday = sc.nextLine();
					if(Static.isInt(reserveday)) {
						rday = Integer.parseInt(reserveday);
						if(rday == 0) {
							when();
							return;
						}else if(rday<1||rday>cp.lastDay) {
							System.out.println("범위를 초과하였습니다.");
							reserveday = "";
						}else {
							
							
							
							
							
							
							
							resultday = reserveday;
							
							resultmonth = transformonth(monthnum);
							
							resultyear = yearnum;
							
							
							cancel(resultyear, resultmonth, resultday);
							
							
							
							
						}
						
					}else {
						System.out.println("숫자를 입력해 주세요");
						reserveday = "";
					}
				}
				
				
				
				
				
				
				
				
				
			}else if(menunum.equals("4")) {
				new Administer().adminMain();
				return;
			}else {
				System.out.println("번호 확인후 입력해 주세요");
				when();
				return;
			}
			
			
			
			
			
			
			
			
			
		}else {
			System.out.println("숫자를 입력해 주세요");
			when();
			return;
		}
	
	}
	
	
	
	
	
	
	public void reserve(String year, String month, String day) {
	
			
			showtable(year, month, day);
			System.out.println("[*] : 예약됨  | [@] : 내 예약 | 뒤로가기 0번");
			System.out.print("예약할 시간을 입력해주세요(ex 10) : ");
			String reservetime = sc.nextLine();
			if(Static.isInt(reservetime)) {
				int rtime = Integer.parseInt(reservetime);
				if(rtime == 0){
					when();
					return;
				}else if(rtime < 10 || rtime >18) {
					System.out.println("[10]시 에서 [18]시 사이의 값을 입력해 주세요");
					reserve(year, month, day);
					return;
				}
				
			}else {
				System.out.println("숫자를 입력해주세요");
				reserve(year, month, day);
				return;
			}
			
			System.out.print("예약할 테이블을 입력해주세요(ex 3) : ");
			String reservetable = sc.nextLine();
			if(Static.isInt(reservetable)) {
				int rtable = Integer.parseInt(reservetable);
				if(rtable == 0) {
					when();
					return;
				}else if(rtable <1 || rtable > 8) {
					System.out.println("[1]번 에서 [8]번 테이블을 입력해 주세요");
					reserve(year, month, day);
					return;
				}
			}else {
				System.out.println("숫자를 입력해 주세요");
				reserve(year, month, day);
				return;
			}
			
			
			System.out.print("예약할 회원 번호를 입력하세요(뒤로가기 0) : ");
			String reservemem = sc.nextLine();
			int rmem = 0;
			if(Static.isInt(reservemem)) {
				rmem = Integer.parseInt(reservemem);
				
				AdminMemberDAO amdao = new AdminMemberDAO();
				if(amdao.selectmem(rmem) != rmem) {
					System.out.println("존재하지 않는 회원 입니다.");
					reserve(year,month,day);
					return;
				}
				
				
				if(rmem == 0) {
					reserve(year,month,day);
					return;
				}
				
				
			}else {
				System.out.println("숫자를 입력해 주세요");
				reserve(year, month, day);
				return;
			}
			
			
			
			int rtime = Integer.parseInt(reservetime);
			int rtable = Integer.parseInt(reservetable);
			String time = "";
			switch(rtime) {
			case 10 : time = "ten";
				break;
			case 11 : time = "ele";
				break;
			case 12 : time = "twel";
				break;
			case 13 : time = "thir";
				break;
			case 14 : time = "four";
				break;
			case 15 : time = "fif";
				break;
			case 16 : time = "six";
				break;
			case 17 : time = "sev";
				break;
			case 18 : time = "eigh";
				break;
			
			}
			
			int result = dao.insert(time, rtable, year, month, day, rmem);
			if(result == 1) {
				System.out.println("예약이 완료 되었습니다.");
				reserve(year, month, day);
				return;
			}else {
				System.out.println("예약과정중 오류가 발생하였습니다");
				reserve(year, month, day);
				return;
			}
			
			
		
	}
	
	
	
	public void cancel(String year, String month, String day) {
		
		
		
		
			
			showtable(year, month, day);
			System.out.println("[*] : 예약됨  | [@] : 내 예약 | 뒤로가기 0번");
			System.out.print("취소할 시간을 입력해주세요(ex 10) : ");
			String canceltime = sc.nextLine();
			if(Static.isInt(canceltime)) {
				int ctime = Integer.parseInt(canceltime);
				if(ctime == 0){
					when();
					return;
				}else if(ctime < 10 || ctime >18) {
					System.out.println("[10]시 에서 [18]시 사이의 값을 입력해 주세요");
					cancel(year, month, day);
					return;
				}
				
			}else {
				System.out.println("숫자를 입력해주세요");
				cancel(year, month, day);
				return;
			}
			
			System.out.print("취소할 테이블을 입력해주세요(ex 3) : ");
			String canceltable = sc.nextLine();
			if(Static.isInt(canceltable)) {
				int ctable = Integer.parseInt(canceltable);
				if(ctable == 0) {
					return;
				}else if(ctable <1 || ctable > 8) {
					System.out.println("[1]번 에서 [8]번 테이블을 입력해 주세요");
					cancel(year, month, day);
					return;
				}
			}else {
				System.out.println("숫자를 입력해 주세요");
				cancel(year, month, day);
				return;
			}
			
			
			int ctime = Integer.parseInt(canceltime);
			int ctable = Integer.parseInt(canceltable);
			String time = "";
			switch(ctime) {
			case 10 : time = "ten";
				break;
			case 11 : time = "ele";
				break;
			case 12 : time = "twel";
				break;
			case 13 : time = "thir";
				break;
			case 14 : time = "four";
				break;
			case 15 : time = "fif";
				break;
			case 16 : time = "six";
				break;
			case 17 : time = "sev";
				break;
			case 18 : time = "eigh";
				break;
			
			}
			
			int result = dao.delete(time, ctable, year, month, day);
			if(result == 1) {
				System.out.println("취소가 완료 되었습니다.");
				cancel(year, month, day);
				return;
			}else {
				System.out.println("취소과정중 오류가 발생하였습니다");
				cancel(year,month,day);
				return;
			}
			
			
		
		
		
		
	}
	
	
	
	public void showtable(String year, String month, String day) {
		
		String[][] arr = new String[8][9];
		
		MemberVO mvo = null;

		Set<MemberVO> set = login.keySet();
		for(MemberVO vo : set) {
			mvo = vo;
		}
		
				
		ArrayList<int[]> list = dao.drawTable(year,month,day);
		
		
		int[] tabone = list.get(0); //table one 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0
		int[] tabtwo = list.get(1); //table two
		int[] tabthr = list.get(2); //table three
		int[] tabfou = list.get(3); //table four
		int[] tabfiv = list.get(4); //table five
		int[] tabsix = list.get(5); //table six
		int[] tabsev = list.get(6); //table seven
		int[] tabeig = list.get(7); //table eight
	
		
		tableLoop(arr, mvo, tabone, 0);
		tableLoop(arr, mvo, tabtwo, 1);
		tableLoop(arr, mvo, tabthr, 2);
		tableLoop(arr, mvo, tabfou, 3);
		tableLoop(arr, mvo, tabfiv, 4);
		tableLoop(arr, mvo, tabsix, 5);
		tableLoop(arr, mvo, tabsev, 6);
		tableLoop(arr, mvo, tabeig, 7);
		
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("	10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00 18:00");
		System.out.println("--------------------------------------------------------------");

		for(int i = 0 ; i< arr.length ; i++) {
			System.out.print(i+1 + "번 테이블     ");
			for(int j = 0 ; j < arr.length+1 ; j++) {
				if(arr[i][j] == null) {
					arr[i][j] = "[ ]";
					
				}
				
				System.out.print(arr[i][j]+"   ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------");

	}// showtable method end
	
	public void tableLoop(String[][] arr, MemberVO mvo, int[] tablenum, int tabn) {

		for (int i = 0; i < 9; i++) {
			String k = String.valueOf(tablenum[i]);

			if (tablenum[i] == 0) {
				k = "[   ]";
			} else if (tablenum[i] >= 1 && tablenum[i] <= 9) {
				k = "[ " + tablenum[i] + " ]";
			} else if (tablenum[i] >= 10 && tablenum[i] <= 99) {
				k = "[ " + tablenum[i] + "]";
			} else  {
				k = "[" + tablenum[i] + "]";
			}

			arr[tabn][i] = k;



		}

	}// tableLoop method end
	
	

	public String transformday(int rday) {
		
		
		String day = "";
		switch(rday) {
		case 1: day = "one";
		break;
		case 2: day = "two";
		break;
		case 3: day = "three";
		break;
		case 4: day = "four";
		break;
		case 5: day = "five";
		break;
		case 6: day = "six";
		break;
		case 7: day = "seven";
		break;
		case 8: day = "eight";
		break;
		case 9: day = "nine";
		break;
		case 10: day = "ten";
		break;
		case 11: day = "eleven";
		break;
		case 12: day = "twelve";
		break;
		case 13: day = "thirten";
		break;
		case 14: day = "fourten";
		break;
		case 15: day = "fiften";
		break;
		case 16: day = "sixten";
		break;
		case 17: day = "seventen";
		break;
		case 18: day = "eighten";
		break;
		case 19: day = "nineten";
		break;
		case 20: day = "twenty";
		break;
		case 21: day = "twentyone";
		break;
		case 22: day = "twentytwo";
		break;
		case 23: day = "twentythre";
		break;
		case 24: day = "twentyfour";
		break;
		case 25: day = "twentyfive";
		break;
		case 26: day = "twentysix";
		break;
		case 27: day = "twentyseven";
		break;
		case 28: day = "twentyeight";
		break;
		case 29: day = "twentynine";
		break;
		case 30: day = "thirty";
		break;
		case 31: day = "thirtyone";
		break;
		
		}
		
		return day;
		
	}//transformday method end
	
	
	public String transformonth(String inputmonth) {
		
		
		String month = "";
		switch(inputmonth) {
		case "1" : month = "january";
		break;
		case "2" : month = "february";
		break;
		case "3" : month = "march";
		break;
		case "4" : month = "april";
		break;
		case "5" : month = "may";
		break;
		case "6" : month = "june";
		break;
		case "7" : month = "july";
		break;
		case "8" : month = "august";
		break;
		case "9" : month = "september";
		break;
		case "10" : month = "october";
		break;
		case "11" : month = "november";
		break;
		case "12" : month = "december";
		break;
		
		}
		
		
		return month;
		
	}//transformonth method end
	
	
	
}
