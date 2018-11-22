package view;

import java.util.Scanner;
import java.util.Set;

import dao.BoardDAO;
import dao.MenuDAO;
import main.Administer;
import main.Static;
import vo.MemberVO;

public class AdminBoardView {
	
	
	BoardDAO dao = new BoardDAO();
	



	public void start() {

		Scanner sc = new Scanner(System.in);

		System.out.println("--------------------------------------------");
		System.out.println("\t1.페이지 넘기기");
		System.out.println("\t2.게시물 조회");
		System.out.println("\t3.게시물 검색");
		System.out.println("\t4.게시물 삭제하기");
		System.out.println("\t0.나가기");
		System.out.print("\t번호입력: ");
		String menu = sc.nextLine();
		int select = 0;
		if (Static.isInt(menu)) {
			select = Integer.parseInt(menu);
		} else {
			System.out.println("번호를 입력해주세요");
			start();
			return;
		}
		System.out.println("--------------------------------------------");

		if (select == 0) {
			new Administer().adminMain();
			return;
		} else if (select == 1) {
			System.out.print("검색할 페이지를 입력해주세요 : ");
			String page = sc.nextLine();
			System.out.println("--------------------------------------------");
			if (Static.isInt(page)) {
				int pagenum = Integer.parseInt(page);

				if (dao.totalpage() < pagenum || pagenum < 1) {
					System.out.println("페이지 수를 확인해주세요");
					System.out.println("--------------------------------------------");

					showlist();
					start();
					return;
				}
				dao.selectBoard(pagenum);
				start();
				return;
			} else {
				System.out.println("숫자를 입력해주세요");
				showlist();
				start();
				return;
			}

		} else if (select == 2) {
			System.out.println("\t\t\t[ 1 ~ "+dao.maxseq()+" ]");
			System.out.print("조회할 게시물 번호를 입력해주세요 : ");
			String boardSeq = sc.nextLine();
			System.out.println("--------------------------------------------");
			
			if(Static.isInt(boardSeq)) {
				int seq = Integer.parseInt(boardSeq);
				if(seq < 1 || seq >dao.maxseq()) {
					System.out.println("범위를 초과 했습니다. [ 1 ~ "+dao.maxseq()+" ]");
					start();
					return;
				}else {
					dao.searchSeq(seq);
					dao.viewcountup(seq);
					start();
					return;
				}
			}else {
				System.out.println("숫자를 입력해 주세요");
				start();
				return;
			}
			

		} else if (select == 3) {
			System.out.println("1. 제목별 검색");
			System.out.println("2. 내용별 검색");
			System.out.println("3. 컬럼별 검색");
			System.out.println("4. 뒤로가기");
			System.out.print("번호를 입력해 주세요 : ");
			String searchnum = sc.nextLine();
			int search = 0;
			if(Static.isInt(searchnum)) {
				search = Integer.parseInt(searchnum);
			
				if(search == 1) {
					System.out.print("검색할 제목을 입력해 주세요 : ");
					String value = sc.nextLine();
					
					if(dao.searchBoard("title", value) == 0) {
						System.out.println("찾으시는 검색 결과가 없습니다.");
					
					}
					start();
					return;
				}else if(search == 2) {
					System.out.print("검색할 내용을 입력해 주세요 : ");
					String value = sc.nextLine();
					if(dao.searchBoard("contents", value) == 0) {
						System.out.println("찾으시는 검색 결과가 없습니다.");
					}
					
					start();
					return;
				}else if(search == 3) {
					System.out.println("--------검색할 조건을 입력해 주세요--------");
					System.out.print(" 제목| 내용 | 메뉴 | 작성자 | 조회수 | 작성일(ex>18/11/19) : ");
					String column = sc.nextLine();
					if(column.equals("제목")||column.equals("내용")||column.equals("메뉴")||
							column.equals("작성자")||column.equals("작성일")||column.equals("조회수")) {
							
						switch(column) {
						case "제목" : column = "title";
						break;
						case "내용" : column = "contents";
						break;
						case "메뉴" : column = "menu";
						break;
						case "작성자" : column = "writer";
						break;
						case "작성일" : column = "indate";
						break;
						case "조회수" : column = "viewcount";
						break;
						}
					}else {
						System.out.println("다시 확인후 입력해 주세요");
						start();
						return;
					}
					System.out.print("검색할 내용을 입력해 주세요 : ");
					String value = sc.nextLine();
					
					int result = dao.searchBoard(column, value);
					if(result == 0) {
						System.out.println("검색 결과가 없습니다.");
					}
					start();
					return;
					
				}else if(search == 4) {
					showlist();
					start();
					return;
				}else {
					System.out.println("번호를 확인해주세요");
					start();
					return;
				}
			
			
			}else {
				System.out.println("숫자를 입력해 주세요.");
				showlist();
				start();
				return;
			}
			
			
			
		}else if (select == 4) {
			
				
	
				String number = "";
				int num = 0;
				while(number.equals("")) {
					System.out.print("삭제할 글의 번호를 입력하세요(뒤로가기 0) : ");
					number = sc.nextLine();
					if(Static.isInt(number)) {
						num = Integer.parseInt(number);
						
						if(num == 0) {
							showlist();
							start();
							return;
						}
					}else {
						System.out.println("숫자를 입력해 주세요");
						number = "";
					}
				}
				
					
				
				dao.searchSeq(num); // 게시물 보여 주기 
				
				
				
				String yorn = "";
				while (yorn.equals("")) {
					System.out.print("게시물을삭제하시겠습니까? (Y/N) : ");
					yorn = sc.nextLine();
					if (yorn.equalsIgnoreCase("Y") || yorn.equalsIgnoreCase("N")) {
						if (yorn.equalsIgnoreCase("Y")) {
							
							
							
							
							int result = dao.deleteboard(num);
							if (result == 1) {
								System.out.println("삭제 완료 되었습니다.");
								System.out.println("--------------------------------------------");
								
								showlist();
								start();
								return;
							} else {
								System.out.println("입력 도중 오류가 발생하였습니다.");
								return;
							}
						} else if (yorn.equalsIgnoreCase("N")) {
							showlist();
							start();
							return;
						}
					} else {
						System.out.println("Y 또는 N을 입력해 주세요");
						yorn = "";
					}
				}
				
				
				
				
				
				
			
				
			
				
				
				
		} else {
			System.out.println("번호를 확인해주세요");
			start();
		}
	}// start method end

	public void showlist() {

		dao.selectBoard(1);

	}

}
