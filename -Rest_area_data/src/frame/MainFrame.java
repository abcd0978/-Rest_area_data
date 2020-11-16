package frame;

import java.awt.*;
// ActionListener & ActionEvent 패키지를 위한 Import
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Area;
import database.Csvtodb;
import database.LookupAndModify;

public class MainFrame{
   
   JButton button01, button02, button03, b01, b02;
   JMenu jm01,jm02,jm03;
   JMenuBar jmb;
   JMenuItem load,save;//불러오기,저장
   JMenuItem modify,insert,delete;//수정,삽입,삭제,
   JMenuItem asc,desc;//오름,내림
   Csvtodb ctd;//db객체 매개변수
   LookupAndModify lu;
   
   public static void main(String[] args) {   
      //메인 메소드 실행
      MainFrame MF = new MainFrame();
      MF.printFunc();
      MF.JframeFunc();
   }
   
   public void printFunc() {
      System.out.println("------Programe TEST1------");
   }
   
   public void JframeFunc() 
   {
      JFrame frame = new JFrame(); // 프레임 생성
      
      frame.setTitle("고속도로 휴게소 매장 프로그램");
      
      jmb = new JMenuBar(); // JMenuBar 생성
      
      
      jm01 = new JMenu("파일"); // Menu 생성
      
      load = new JMenuItem("파일 불러오기"); // load메뉴아이템
      save = new JMenuItem("파일 저장하기"); //save메뉴아이템
      
      jm01.add(save);//Menu에 MenuItem추가
      jm01.add(load);//Menu에 MenuItem추가
      
      jmb.add(jm01); //JmenuBar에 Menu추가
      
      
      jm02 = new JMenu("매장 정보"); // Menu 생성
      modify = new JMenuItem("수정(Modify)"); // Item 생성
      insert = new JMenuItem("삽입(Insert)");
      delete = new JMenuItem("삭제(Delete)");
      
      
      jm02.add(modify);
      jm02.add(insert);
      jm02.add(delete);
      jmb.add(jm02); //JmenuBar에 Menu추가
      
      
      
      ImageIcon asc=new ImageIcon("src/image/오름차순.png"); //이미지 아이콘 생성
      ImageIcon desc=new ImageIcon("src/image/내림차순.png");      
      Image as = asc.getImage();
      Image des = desc.getImage();
      
      jm03=new JMenu("정렬"); //Menu 생성
      this.asc = new JMenuItem(asc); //Item 생성
      this.desc = new JMenuItem(desc);
      jm03.add(this.asc);
      jm03.add(this.desc);
      jmb.add(jm03); //JmenuBar에 Menu Item 추가
      
      //frame.getContentPane().add(BorderLayout.NORTH, jmb);
      frame.setJMenuBar(jmb);
      
      load.addActionListener(new LoadActionListener());
      save.addActionListener(new SaveActionListener());
      
      JToolBar jtb = new JToolBar();
      JComboBox combo1 = new JComboBox();
      combo1.addItem("전체 휴게소");
      
      JComboBox combo2 = new JComboBox();
      combo2.addItem("1월");
      combo2.addItem("2월");
      combo2.addItem("3월");
      combo2.addItem("4월");
      combo2.addItem("5월");
      combo2.addItem("6월");
      combo2.addItem("7월");
      combo2.addItem("8월");
      combo2.addItem("9월");
      combo2.addItem("10월");
      combo2.addItem("11월");
      combo2.addItem("12월");
      
      JComboBox combo3 = new JComboBox();
      combo3.addItem("1월");
      combo3.addItem("2월");
      combo3.addItem("3월");
      combo3.addItem("4월");
      combo3.addItem("5월");
      combo3.addItem("6월");
      combo3.addItem("7월");
      combo3.addItem("8월");
      combo3.addItem("9월");
      combo3.addItem("10월");
      combo3.addItem("11월");
      combo3.addItem("12월");
      
      jtb.add(combo1);
      jtb.add(combo2);
      jtb.add(combo3);
      
      b01 = new JButton("조회/통계");
      
      jtb.add(b01);
      
      frame.getContentPane().add(jtb,BorderLayout.NORTH);
      
      // 버튼 생성
      button01 = new JButton("Button 01");
      //button02 = new JButton("Button 02");
      //button03 = new JButton("Button 03");
      
      // Button 리스너 목록에 등록
      button01.addActionListener(new btn01Listener());
      
      // 창 close 시에 프로그램 종료
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // 버튼을 프레임의  Content Pane(내용틀)에 추가
      frame.getContentPane().add(BorderLayout.SOUTH, button01);
      //frame.getContentPane().add(BorderLayout.EAST, button02);
      //frame.getContentPane().add(BorderLayout.WEST, button03);
      
            
      frame.setSize(500, 550); // 프레임 크기
      
      frame.setVisible(true); // 프레임 화면 표시 설정
      
   }
   
class btn01Listener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		lu = new LookupAndModify();
		ArrayList<ArrayList<Area>> temp = lu.lookarea("평택휴게소",9,10);
		for(int i=0;i<=1;i++)
    	{
			ArrayList<Area> temp1 = temp.get(i);
			System.out.println((9+i)+"월달");
    		for (Area area : temp1) 
    		{
    			System.out.println("가게이름:"+area.getStname()+"  가게 전체 순위:"+area.getSlranking());
    		}
    	}
	}
}
   

   // 파일불러오기 메뉴아이템 
   class LoadActionListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e) 
      {
         button03.setText("클릭 이벤트");
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setFileFilter(new FileNameExtensionFilter("csv파일","csv"));
         int returnval = fileChooser.showOpenDialog(null);//열기옵션으로 filechooser를 연뒤에 행동에 따라 return 값이 나온다.
         if(returnval == JFileChooser.APPROVE_OPTION)//yes,ok,열기 등 확인버튼을 누르면 실행되는것
         {
             String Path = fileChooser.getSelectedFile().getPath();//선택된파일의 경로를 스트링으로 저장
             ctd = new Csvtodb();
             System.out.println(Path);
             try {
    			ctd.invert(Path);
    		} catch (SQLException | IOException e1) {
    			e1.printStackTrace();
    		}
         }
      }

   }
   class SaveActionListener implements ActionListener
   {
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("wait for download");
	}
   }
}