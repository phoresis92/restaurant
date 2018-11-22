package swing;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mainpage extends Frame{
	
	Label lb;
	
	Panel p;
	Button menu, order, reserve, board, login, logout, find, signup, managerpage;
	

	public Mainpage() {
		
		super("식당운영 프로그램");
		setSize(840,840/12*8);
		setBackground(Color.DARK_GRAY);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scr = tk.getScreenSize();
		setLocation(scr.width/2-420, scr.height/2-(840/12*8/2));
		
		
		lb = new Label("Restaurant management ver1.0",Label.CENTER);
		Font font = new Font("Serif",Font.BOLD,40);
		lb.setFont(font);
		add(lb);
		
	/*	menu = "메뉴보기"; 
		order = "주문하기";
		reserve = "예약하기";
		board = "리뷰 게시판";
		login = "로그인";
		logout = "로그아웃";*/
		//find = " signup, managerpage
				
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		Mainpage main = new Mainpage();
		
	}
	
}
