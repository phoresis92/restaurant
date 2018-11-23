package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarPrint {
	

	Calendar cal = Calendar.getInstance();
	
	String[] calHead = {"일","월","화","수","목","금","토"};
		
	String[][] calDate = new String[6][7];//달력 틀
	
	
	int width = calHead.length; // 가로 넓이
	int startDay; //월 시작 일
	public int lastDay; // 월 마지막 일
	int inputDate = 1; //입력 날짜
	
	public String yearnum = "";
	public String monthnum = "";
	
	public CalendarPrint(int year, int month) {
		
		yearnum = Integer.toString(year);
		monthnum = Integer.toString(month);
		
		if(month<1||month>12) {
			System.out.println("1 ~ 12월 사이로 입력해 주세요");
			
		}else {
			
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.DATE, 1);
			
			startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일
			lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜
			
			
			//2차 배열에 날짜 입력
			int row = 0;
			for(int i = 1 ; inputDate <= lastDay ; i++) {
				
				//시작 요일이 오기전에는 공백 입력
				if(i<startDay) {
					calDate[row][i-1]="  ";
				}else {//날짜 배열에 입력
					
					String indate = Integer.toString(inputDate);
					if(inputDate <10) {
						indate = " " + indate;
					}
					calDate[row][(i-1)%width]=indate;
					inputDate++;
					
					if(i%width == 0) {
						row++;
					}//가로 마지막 열에 오면 행 바꿈
					
				}
				
			}// for end
			
			
			
			
		}
		
		
	}// constructor end
	
	
	public void printhismonth(int year, int month) {
		System.out.println("----------------------");

		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month, 1);
		
		int ryear = cal.get(Calendar.YEAR);
		int rmonth = cal.get(Calendar.MONTH);
		
		if(rmonth== 0) {
			rmonth = 12;
			ryear = ryear-1;
		}
		
		System.out.println("      "+ryear+"년 "+rmonth+"월");
		
		/*cal.set(2017, 3, 1);
		System.out.println(cal.getTime());
		System.out.println(sdf.format(cal.getTime()));*/
		
		
	}
	
	
	
	public void printCal() {
		
		//헤더 출력 일월화수목금토
		
		System.out.println("----------------------");
		for(int i = 0 ; i<width ; i++) {
			System.out.print(calHead[i]+"    ");
		}
		System.out.println();
		System.out.println("----------------------");

		int row = 0;
		for(int j = 1 ; j < lastDay+startDay ; j++) {
			
			System.out.print(calDate[row][(j-1)%width]+" ");
			
			if((j-1)%width == width-1) {
				System.out.println();
				row++;
			}
			
		}
		System.out.println();
		System.out.println("----------------------");

		
		
	}//printCal method end
	

}//calss end
