package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import dao.AdminSalesDAO;
import main.Administer;
import main.Static;

public class AdminSalesView {

	
	AdminSalesDAO dao = new AdminSalesDAO();
	
	public void monthsales() {
		
		
		//HashMap<Integer,Integer> map = dao.monthsalescal();
	
		int jan1 = 1400000;//map.get(1);
		int jan2 = 2400000;//map.get(2);
		int feb1 = 3400000;//map.get(3);
		int feb2 = 9400000;//map.get(4);
		int mar1 = 400000;//map.get(5);
		int mar2 = 2400000;//map.get(6);
		int apr1 = 1400000;//map.get(7);
		int apr2 = 2234000;//map.get(8);
		int may1 = 1234000;//map.get(9);
		int may2 = 240000;//map.get(10);
		int jun1 = 2400000;//map.get(11);
		int jun2 = 2400000;//map.get(12);
		int jul1 = 2400000;//map.get(13);
		int jul2 = 2400000;//map.get(14);
		int aug1 = 2400000;//map.get(15);
		int aug2 = 2400000;//map.get(16);
		int sep1 = 2400000;//map.get(17);
		int sep2 =0;// map.get(18);
		int oct1 = 2400000;//map.get(19);
		int oct2 = 2400000;//map.get(20);
		int nov1 = 2400000;//map.get(21);
		int nov2 = 2400000;//map.get(22);
		int dec1 = 2400000;//map.get(23);
		int dec2 = 2400000;//map.get(24);
		
		
		int min = 200*1000;
		
		
		String[][] sar = new String[24][25];
		
		
		arinsert(min,jan1,sar,0);
		arinsert(min,jan2,sar,1);
		arinsert(min,feb1,sar,2);
		arinsert(min,feb2,sar,3);
		arinsert(min,mar1,sar,4);
		arinsert(min,mar2,sar,5);
		arinsert(min,apr1,sar,6);
		arinsert(min,apr2,sar,7);
		arinsert(min,may1,sar,8);
		arinsert(min,may2,sar,9);
		arinsert(min,jun1,sar,10);
		arinsert(min,jun2,sar,11);
		arinsert(min,jul1,sar,12);
		arinsert(min,jul2,sar,13);
		arinsert(min,aug1,sar,14);
		arinsert(min,aug2,sar,15);
		arinsert(min,sep1,sar,16);
		arinsert(min,sep2,sar,17);
		arinsert(min,oct1,sar,18);
		arinsert(min,oct2,sar,19);
		arinsert(min,nov1,sar,20);
		arinsert(min,nov2,sar,21);
		arinsert(min,dec1,sar,22);
		arinsert(min,dec2,sar,23);

		
		for(int k = 23 ; k >= 0 ; k--) {
			sar[k][24] = "|"+((24-k)*20)+"만원";
		}
		
		System.out.println("===============================================================================");

		for(int i = 0 ; i < 24 ; i++) {
			
			for(int j = 0 ; j < 25 ; j++) {
				if(sar[i][j]==null) {
					sar[i][j] = " - ";
				}
				System.out.print(sar[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println(" 1  월    2  월    3  월    4  월   5  월    6  월    7  월    8  월   9  월   10 월    11 월    12 월");
		System.out.println("===============================================================================");

		System.out.println();

		String menu = "";
		while(menu.equals("")) {
			System.out.println("1.월별 상세 보기");
			System.out.println("2.뒤로가기");
			System.out.print("번호 입력 : ");
			Scanner sc = new Scanner(System.in);
			menu = sc.nextLine();
			if(Static.isInt(menu)) {
				
				if(menu.equals("1")) {
					System.out.println();
					System.out.println("------------------------");
					System.out.println("1월 : "+((jan1+jan2)/1000)+"천원");
					System.out.println("2월 : "+((feb1+feb2)/1000)+"천원");
					System.out.println("3월 : "+((mar1+mar2)/1000)+"천원");
					System.out.println("4월 : "+((apr1+apr2)/1000)+"천원");
					System.out.println("5월 : "+((may1+may2)/1000)+"천원");
					System.out.println("6월 : "+((jun1+jun2)/1000)+"천원");
					System.out.println("7월 : "+((jul1+jul2)/1000)+"천원");
					System.out.println("8월 : "+((aug1+aug2)/1000)+"천원");
					System.out.println("9월 : "+((sep1+sep2)/1000)+"천원");
					System.out.println("10월 : "+((oct1+oct2)/1000)+"천원");
					System.out.println("11월 : "+((nov1+nov2)/1000)+"천원");
					System.out.println("12월 : "+((dec1+dec2)/1000)+"천원");
					System.out.println("------------------------");
					System.out.println();
				}else if(menu.equals("2")) {
					System.out.println();
					new Administer().adminMain();
					return;
				}else {
					System.out.println("번호를 확인후 입력해주세요");
					menu="";
				}
				
			}else {
				System.out.println("숫자를 입력해 주세요");
				menu = "";
			}
		}
	}
	
	
	public void arinsert(int min, int month, String[][] sar, int where) {
		int max = (int)Math.floor(month/min);
		if(month == 0) {
			max = 24;
		}
		
		if(month>4800000) {
			max=0;
		}
		
		for(int k = 23 ; k>=max ; k--) {
			
			sar[k][where] = " @ ";
		}
		
		
	}
	
	
	public void menusales() {
		
		HashMap<Integer,Integer> map = dao.menusalescal();
		
		ArrayList<String[]> list = dao.menuinfo();
		
		int main_cnt = 1;
		int side_cnt = 1;
		int drink_cnt = 1;
		System.out.println("-----------------------------------------------");
		for(int i = 0 ; i < list.size() ; i++) {
			int menu_id = Integer.parseInt(list.get(i)[0]);
			String menu_name = list.get(i)[2];
			int count = map.get(menu_id);
			int price = Integer.parseInt(list.get(i)[1]);
			int totalprice = count*price;
		
			if(menu_id <=1000) {

				System.out.println(main_cnt+") "+menu_name+" | 총판매수량 : "+count+" | 총판매금액 : "+totalprice/1000+"천원");
				main_cnt++;
		
			}
		}
		System.out.println("-----------------------------------------------");

		for(int i = 0 ; i < list.size() ; i++) {
			int menu_id = Integer.parseInt(list.get(i)[0]);
			String menu_name = list.get(i)[2];
			int count = map.get(menu_id);
			int price = Integer.parseInt(list.get(i)[1]);
			int totalprice = count*price;
		
			if(menu_id<=1500&&menu_id>1000) {
				
				System.out.println(side_cnt+") "+menu_name+" | 총판매수량 : "+count+" | 총판매금액 : "+totalprice/1000+"천원");
				side_cnt++;
				
			}
		}
		System.out.println("-----------------------------------------------");

		for(int i = 0 ; i < list.size() ; i++) {
			int menu_id = Integer.parseInt(list.get(i)[0]);
			String menu_name = list.get(i)[2];
			int count = map.get(menu_id);
			int price = Integer.parseInt(list.get(i)[1]);
			int totalprice = count*price;
		
			if(menu_id>1500){

				System.out.println(drink_cnt+") "+menu_name+" | 총판매수량 : "+count+" | 총판매금액 : "+totalprice/1000+"천원");
				drink_cnt++;

			}
		}

			
			System.out.println("-----------------------------------------------");
		
		

	
	
	}
	
	
	public void kindsales() {
		
		
		int[] arr = dao.kindsalescal();
		
		System.out.println("11월");
		System.out.println("메인메뉴 : "+(arr[0]/1000)+"천원");
		System.out.println("사이드메뉴 : "+(arr[1]/1000)+"천원");
		System.out.println("드링크메뉴 : "+(arr[2]/1000)+"천원");
		
		
	}
	
	
	
}
