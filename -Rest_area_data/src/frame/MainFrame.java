package frame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*; // ActionListener & ActionEvent ��Ű���� ���� Import

public class MainFrame{
   
   JButton button01, button02, button03, b01, b02;
   JMenu jm01, jm03;
   JMenuBar jmb;
   JMenuItem m01_item, m02_item, m03_item;
   
   /*
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
      
      frame.setTitle("��ӵ��� �ްԼ� ���� ���α׷�");
      
      jmb = new JMenuBar(); // JMenuBar ����
      
      jm01 = new JMenu("����"); // Menu ����
      m01_item = new JMenuItem("���� �ҷ�����"); // Item ����
      jm01.add(m01_item);
      //jm.addSeparator(); // Menu Item ���м�
      jm01.add(new JMenuItem("���� �����ϱ�"));
      
      jmb.add(jm01); //JmenuBar�� Menu Item �߰�

      
      jm03 = new JMenu("���� ����"); // Menu ����
      m03_item = new JMenuItem("����(Modify)"); // Item ����
      jm03.add(m03_item);
      jm03.add(new JMenuItem("����(insert)"));
      jm03.add(new JMenuItem("����(Delete)"));
      
      jmb.add(jm03); //JmenuBar�� Menu Item �߰�

      
      ///frame.getContentPane().add(BorderLayout.NORTH, jmb);
      frame.setJMenuBar(jmb);
      
      m01_item.addActionListener(new Menu01ActionListener());
      m03_item.addActionListener(new Menu01ActionListener());
      
      JToolBar jtb = new JToolBar();
      JComboBox combo1 = new JComboBox();
      combo1.addItem("��ü �ްԼ�");
      
      JComboBox combo2 = new JComboBox();
      combo2.addItem("1��");
      combo2.addItem("2��");
      combo2.addItem("3��");
      combo2.addItem("4��");
      combo2.addItem("5��");
      combo2.addItem("6��");
      combo2.addItem("7��");
      combo2.addItem("8��");
      combo2.addItem("9��");
      combo2.addItem("10��");
      combo2.addItem("11��");
      combo2.addItem("12��");
      
      JComboBox combo3 = new JComboBox();
      combo3.addItem("1��");
      combo3.addItem("2��");
      combo3.addItem("3��");
      combo3.addItem("4��");
      combo3.addItem("5��");
      combo3.addItem("6��");
      combo3.addItem("7��");
      combo3.addItem("8��");
      combo3.addItem("9��");
      combo3.addItem("10��");
      combo3.addItem("11��");
      combo3.addItem("12��");
      
      jtb.add(combo1);
      jtb.add(combo2);
      jtb.add(combo3);
      
      b01 = new JButton("��ȸ");
      b02 = new JButton("���");
      jtb.add(b01);
      jtb.add(b02);
      
      
      frame.getContentPane().add(jtb,BorderLayout.NORTH);
      
      
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
   class Menu01ActionListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e) 
      {
         // TODO Auto-generated method stub
         button03.setText("Ŭ�� �̺�Ʈ");
      }
      
   }
*/
}