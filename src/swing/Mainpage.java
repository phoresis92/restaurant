package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class Mainpage extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage frame = new Mainpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainpage() {
		setTitle("식당운영 프로그램");
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(11, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 0, 434, 541);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel lblLogIn = new JLabel("log in");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("굴림", Font.BOLD, 40));
		lblLogIn.setBounds(0, 46, 434, 88);
		panel_1.add(lblLogIn);
		
		JLabel lblId = new JLabel("id : ");
		lblId.setFont(new Font("굴림", Font.PLAIN, 25));
		lblId.setBounds(111, 207, 52, 63);
		panel_1.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(153, 215, 190, 55);
		panel_1.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPassword = new JLabel("password : ");
		lblPassword.setFont(new Font("굴림", Font.PLAIN, 25));
		lblPassword.setBounds(23, 309, 133, 63);
		panel_1.add(lblPassword);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(153, 317, 190, 55);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("로  그  인");
		btnNewButton.setBounds(75, 424, 278, 70);
		panel_1.add(btnNewButton);
		
		
		
		
		
		
		JPanel mainpage = new JPanel();
		mainpage.setBackground(Color.LIGHT_GRAY);
		mainpage.setBounds(0, 0, 434, 541);
		contentPane.add(mainpage);
		mainpage.setLayout(null);
		
		JLabel title = new JLabel("우웅치킨 키트리점");
		title.setBounds(0, 10, 434, 79);
		title.setFont(new Font("굴림", Font.BOLD, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		mainpage.add(title);
		
		JButton manager_page = new JButton("관리자");
		manager_page.setBounds(365, 518, 69, 23);
		mainpage.add(manager_page);
		
		JButton go_menu = new JButton("메뉴보기");
		go_menu.setBounds(61, 99, 312, 61);
		go_menu.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(go_menu);
		
		JButton order = new JButton("주문하기");
		order.setBounds(61, 170, 312, 61);
		order.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(order);
		
		JButton button_1 = new JButton("예약하기");
		button_1.setBounds(61, 241, 312, 61);
		button_1.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(button_1);
		
		JButton button_2 = new JButton("후기 게시판");
		button_2.setBounds(61, 312, 312, 61);
		button_2.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(button_2);
		
		JButton btnNewButton_2 = new JButton("로그인");
		btnNewButton_2.setBounds(12, 482, 147, 49);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(btnNewButton_2);
		
		JButton button_3 = new JButton("계정 찾기");
		button_3.setBounds(171, 482, 147, 49);
		button_3.setFont(new Font("굴림", Font.PLAIN, 20));
		mainpage.add(button_3);
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
