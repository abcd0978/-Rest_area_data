package frame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*; // ActionListener & ActionEvent ��Ű���� ���� Import

public class MainFrame {
	
	JButton button01, button02, button03;
	JMenu jm01, jm02, jm03;
	JMenuBar jmb;
	JMenuItem m01_item, m02_item, m03_item;
	
	
	public static void main(String[] args) {	
		//���� �޼ҵ� ����
		MainFrame MF = new MainFrame();
		MF.printFunc();
		MF.JframeFunc();
	}
	
	public void printFunc() {
		System.out.println("------Programe TEST1------");
	}
	
	public void JframeFunc() {
		JFrame frame = new JFrame(); // ������ ����
		
		jmb = new JMenuBar(); // JMenuBar ����
		
		jm01 = new JMenu("CSV"); // Menu ����
		m01_item = new JMenuItem("CSV ���� �����ϱ�"); // Item ����
		jm01.add(m01_item);
		//jm.addSeparator(); // Menu Item ���м�
		jm01.add(new JMenuItem("CSV ���� �ҷ�����"));
		
		jmb.add(jm01); //JmenuBar�� Menu Item �߰�

		jm02 = new JMenu("DATABASE"); // Menu ����
		m02_item = new JMenuItem("DB�� �����ϱ�"); // Item ����
		jm02.add(m02_item);
		jm02.add(new JMenuItem("DB�κ��� �ҷ�����"));

		jmb.add(jm02); //JmenuBar�� Menu Item �߰�
		
		jm03 = new JMenu("���� ����"); // Menu ����
		m03_item = new JMenuItem("��ȸ(Lookup)"); // Item ����
		jm03.add(m03_item);
		jm03.add(new JMenuItem("����(Modify)"));
		jm03.add(new JMenuItem("����(insert)"));
		jm03.add(new JMenuItem("����(Delete)"));
		
		jmb.add(jm03); //JmenuBar�� Menu Item �߰�

		
		frame.getContentPane().add(BorderLayout.NORTH, jmb);
		
		m01_item.addActionListener(new Menu01ActionListener());
		m02_item.addActionListener(new Menu01ActionListener());
		m03_item.addActionListener(new Menu01ActionListener());

		
		// ��ư ����
		button01 = new JButton("Button 01");
		button02 = new JButton("Button 02");
		button03 = new JButton("Button 03");
		
		// Button ������ ��Ͽ� ���
		button01.addActionListener(new btn01Listener());
		
		// â close �ÿ� ���α׷� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ��ư�� ��������  Content Pane(����Ʋ)�� �߰�
		frame.getContentPane().add(BorderLayout.SOUTH, button01);
		frame.getContentPane().add(BorderLayout.EAST, button02);
		frame.getContentPane().add(BorderLayout.WEST, button03);
		
		frame.setSize(500, 550); // ������ ũ��
		
		frame.setVisible(true); // ������ ȭ�� ǥ�� ����
	}
	
	class btn01Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			button01.setText("button is Cliked");
		}
		
	}
	
	
	// ��ư�� �ش� �޼ҵ� ȣ���� ���� �̺�Ʈ �߻��� �˷���
	class Menu01ActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			button03.setText("Ŭ�� �̺�Ʈ");
		}
		
	}

}
