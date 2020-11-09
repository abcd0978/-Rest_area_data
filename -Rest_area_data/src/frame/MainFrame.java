package frame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*; // ActionListener & ActionEvent 패키지를 위한 Import

public class MainFrame {
	
	JButton button01, button02, button03;
	JMenu jm01, jm02, jm03;
	JMenuBar jmb;
	JMenuItem m01_item, m02_item, m03_item;
	
	
	public static void main(String[] args) {	
		//메인 메소드 실행
		MainFrame MF = new MainFrame();
		MF.printFunc();
		MF.JframeFunc();
	}
	
	public void printFunc() {
		System.out.println("------Programe TEST1------");
	}
	
	public void JframeFunc() {
		JFrame frame = new JFrame(); // 프레임 생성
		
		jmb = new JMenuBar(); // JMenuBar 생성
		
		jm01 = new JMenu("CSV"); // Menu 생성
		m01_item = new JMenuItem("CSV 파일 저장하기"); // Item 생성
		jm01.add(m01_item);
		//jm.addSeparator(); // Menu Item 구분선
		jm01.add(new JMenuItem("CSV 파일 불러오기"));
		
		jmb.add(jm01); //JmenuBar에 Menu Item 추가

		jm02 = new JMenu("DATABASE"); // Menu 생성
		m02_item = new JMenuItem("DB에 저장하기"); // Item 생성
		jm02.add(m02_item);
		jm02.add(new JMenuItem("DB로부터 불러오기"));

		jmb.add(jm02); //JmenuBar에 Menu Item 추가
		
		jm03 = new JMenu("매장 정보"); // Menu 생성
		m03_item = new JMenuItem("조회(Lookup)"); // Item 생성
		jm03.add(m03_item);
		jm03.add(new JMenuItem("수정(Modify)"));
		jm03.add(new JMenuItem("삽입(insert)"));
		jm03.add(new JMenuItem("삭제(Delete)"));
		
		jmb.add(jm03); //JmenuBar에 Menu Item 추가

		
		frame.getContentPane().add(BorderLayout.NORTH, jmb);
		
		m01_item.addActionListener(new Menu01ActionListener());
		m02_item.addActionListener(new Menu01ActionListener());
		m03_item.addActionListener(new Menu01ActionListener());

		
		// 버튼 생성
		button01 = new JButton("Button 01");
		button02 = new JButton("Button 02");
		button03 = new JButton("Button 03");
		
		// Button 리스너 목록에 등록
		button01.addActionListener(new btn01Listener());
		
		// 창 close 시에 프로그램 종료
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 버튼을 프레임의  Content Pane(내용틀)에 추가
		frame.getContentPane().add(BorderLayout.SOUTH, button01);
		frame.getContentPane().add(BorderLayout.EAST, button02);
		frame.getContentPane().add(BorderLayout.WEST, button03);
		
		frame.setSize(500, 550); // 프레임 크기
		
		frame.setVisible(true); // 프레임 화면 표시 설정
	}
	
	class btn01Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			button01.setText("button is Cliked");
		}
		
	}
	
	
	// 버튼은 해당 메소드 호출을 통해 이벤트 발생을 알려줌
	class Menu01ActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			button03.setText("클릭 이벤트");
		}
		
	}

}
