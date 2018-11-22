package view;

import java.util.Scanner;

import dao.AdminMenuEditDAO;
import main.Administer;
import main.Static;
import vo.MenuVO;

public class AdminMenuEditView {

	Scanner sc = new Scanner(System.in);
	AdminMenuEditDAO dao = new AdminMenuEditDAO();
	
public void menuEdit() {
		

		while (true) {
			System.out.println("--------------------------------------------");
			System.out.println("\t\t1. 메   뉴   조  회");
			System.out.println("\t\t2. 메   뉴   추  가");
			System.out.println("\t\t3. 메   뉴   수  정");
			System.out.println("\t\t4. 메   뉴   삭  제");
			System.out.println("\t\t0. 뒤   로   가   기");
			System.out.println("--------------------------------------------");
			System.out.print("\t                 번호를 입력하세요 : ");
			String menu = sc.nextLine();
			System.out.println("--------------------------------------------");


			if (Static.isInt(menu)) {
				String seq = null;
				int select = Integer.parseInt(menu);

				if (select == 0) {
					new Administer().adminMain();
					break;
				} else if (select == 1) {

					dao.main_menuSearch();
					System.out.println();
					dao.side_menuSearch();
					System.out.println();
					dao.drink_menuSearch();

				} else if (select == 2) {
					while (true) {
						
						System.out.println("--------------------------------------------");
						System.out.println("추가하려는 메뉴를 입력하여 주세요.");
						System.out.println("\t1) 메 인  메 뉴");
						System.out.println("\t2) 사이드 메뉴");
						System.out.println("\t3) 드링크 메뉴");
						System.out.println("\t9) 뒤 로  가 기");
						System.out.print("번호를 입력하세요 : ");
						String menuTableNum = sc.nextLine();
						System.out.println("--------------------------------------------");

						if (menuTableNum.equals("9")) {
							break;
						} else if (menuTableNum.equals("1")) {
							seq = "main_menu_seq.nextval";
							dao.main_menuSearch();
						} else if (menuTableNum.equals("2")) {
							seq = "side_menu_seq.nextval";
							dao.side_menuSearch();
						} else if (menuTableNum.equals("3")) {
							seq = "drink_menu_seq.nextval";
							dao.drink_menuSearch();
						} else {
							System.out.println("번호를 확인한 후 입력해 주세요!");
							continue;
						}

						System.out.print("추가할 메뉴명 : ");
						String menu_name = sc.nextLine();
						System.out.print("메뉴 가격 : ");
						String pri = sc.nextLine();
						int price = 0;
						if (Static.isInt(pri)) {
							price = Integer.parseInt(pri);
						} else {
							System.out.println("금액(숫자만)을 입력하여 주세요.");
							continue;
						}
						System.out.print("메뉴 설명 : ");
						String comment = sc.nextLine();

						MenuVO vo = new MenuVO(0, menu_name, price, comment);

						dao.menuAdd(vo, seq);
						
					}

				} else if (select == 3) {

					String menucolumn = null;
					while (true) {
						
						System.out.println("수정하려는 메뉴를 선택하여 주세요.");
						System.out.println(" 1) 메 인  메 뉴");
						System.out.println(" 2) 사이드 메뉴");
						System.out.println(" 3) 드링크 메뉴");
						System.out.println(" 9) 뒤 로  가 기");
						System.out.print("번호를 입력하세요 : ");
						String menuTableNum = sc.nextLine();
						System.out.println("--------------------------------------------");

						if (menuTableNum.equals("9")) {
							break;
						} else if (menuTableNum.equals("1")) {
							dao.main_menuSearch();
						} else if (menuTableNum.equals("2")) {
							dao.side_menuSearch();
						} else if (menuTableNum.equals("3")) {
							dao.drink_menuSearch();
						} else {
							System.out.println("번호를 확인한 후 입력하여 주세요.");
							continue;
						}

						System.out.println("수정할 메뉴 번호를 입력하여 주세요 : ");

						String menu_num = sc.nextLine();
						int menu_id = 0;
						if (Static.isInt(menu_num)) {
							menu_id = Integer.parseInt(menu_num);

						}
						dao.menuSearch(menu_id);

						while (true) {
							System.out.println("------------------------------");
							System.out.println("수정할 항목을 선택하여 주세요.");
							System.out.println("(1)메뉴이름 (2)메뉴가격 (3)메뉴설명 (9)취소");
							System.out.print("번호입력: ");
							String menucolumnnum = sc.nextLine();
							String value = "";

							if (menucolumnnum.equals("9")) {
								break;
							} else if (menucolumnnum.equals("1")) {
								menucolumn = "menu_name";
								System.out.print("변경 메뉴명 : ");
								value = sc.nextLine();

							} else if (menucolumnnum.equals("2")) {
								menucolumn = "price";
								System.out.print("변경 가격 : ");
								value = sc.nextLine();

								if (Static.isInt(value)) {
								} else {
									System.out.println("금액(숫자만)을 입력하여 주세요.");
									continue;
								}
							} else if (menucolumnnum.equals("3")) {
								menucolumn = "commen";
								System.out.print("변경 설명 : ");
								value = sc.nextLine();
							} else {
								System.out.println("번호를 확인한 후 입력해 주세요!");
								continue;
							}
							
							dao.menuModify(menucolumn, menu_id, value);

						}
					}

				} else if (select == 4) {
					while (true) {

						System.out.println("--------------------------------------------");
						System.out.println("삭제하려는 메뉴를 입력하여 주세요.");
						System.out.println(" 1) 메 인  메 뉴");
						System.out.println(" 2) 사이드 메뉴");
						System.out.println(" 3) 드링크 메뉴");
						System.out.println(" 9) 뒤 로  가 기");
						System.out.print("번호를 입력하세요 : ");
						String menuTableNum = sc.nextLine();
						System.out.println("--------------------------------------------");
						
						if (menuTableNum.equals("9")) {
							break;
						} else if (menuTableNum.equals("1")) {
							dao.main_menuSearch();
						} else if (menuTableNum.equals("2")) {
							dao.side_menuSearch();
						} else if (menuTableNum.equals("3")) {
							dao.drink_menuSearch();
						} else {
							System.out.println("번호를 확인한 후 입력해 주세요!");
							continue;
						}
						int menu_id = 0;
						System.out.print("삭제할 메뉴번호를 입력하여주세요. : ");
						String menu_num = sc.nextLine();
						if (Static.isInt(menu_num)) {
							menu_id = Integer.parseInt(menu_num);

						}

						dao.menuDelete(menu_id);

					}
				} else {
					System.out.println("번호를 확인한 후 입력해 주세요!");
					continue;
				}
			} else {
				System.out.println("번호를 입력해주세요!");

			}

		}
	}
	
	
}
