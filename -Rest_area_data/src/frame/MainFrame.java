package frame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.Area;
import database.Csvtodb;
import database.LookupAndModify;

import java.awt.*;
import java.awt.event.*; // ActionListener & ActionEvent 패키지를 위한 Import
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class MainFrame
{
   
   JButton button01, button02, button03, b01, b02;
   JMenu jm01,jm02,jm03;
   JMenuBar jmb;
   JMenuItem load,save;//불러오기,저장
   JMenuItem modify,insert,delete;//수정,삽입,삭제,
   JMenuItem asc,desc;//오름,내림
   JToolBar jtb;
   JComboBox<String> combo1,combo2,combo3;
   JTable Table;
   JScrollPane scroll;
   DefaultTableModel model;
   Csvtodb ctd;//db객체 매개변수
   LookupAndModify lu;
   JFrame frame;
   public static void main(String[] args) 
   {   
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
      frame = new JFrame(); // 프레임 생성
      
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
      
      jtb = new JToolBar();
      combo1 = new JComboBox<String>();
      combo1.addItem("전체 휴게소");
      
      combo2 = new JComboBox<String>();
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
      
      combo3 = new JComboBox<String>();
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
      b01.addActionListener(new LookListener());
      this.asc.addActionListener(new ascListener());
      
      
      
      // 창 close 시에 프로그램 종료
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // 버튼을 프레임의  Content Pane(내용틀)에 추가
      frame.getContentPane().add(BorderLayout.SOUTH, button01);
      //frame.getContentPane().add(BorderLayout.EAST, button02);
      //frame.getContentPane().add(BorderLayout.WEST, button03);
      
      Vector<String> header = new Vector<String>();
      header.add("기준연월");
      header.add("전체판매순위");
      header.add("휴게소내 판매순위");
      header.add("휴게소 코드");
      header.add("휴게소명");
      header.add("매장코드");
      header.add("매장명");
      model = new DefaultTableModel(header,0);
      Table = new JTable(model);
      Table.setRowSorter(new TableRowSorter(model));
      scroll = new JScrollPane(Table);
      frame.add(scroll);
      
      frame.setSize(750, 550); // 프레임 크기
      
      frame.setVisible(true); // 프레임 화면 표시 설정
      
      DrawingPanel drawingPanel=new DrawingPanel(); //
      frame.getContentPane().add(drawingPanel,BorderLayout.CENTER);
      
      JPanel p1=new JPanel();//패널 객체 생성
      
   }
   
   
class DrawingPanel extends JPanel{
	   int jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;
	   public void paint(Graphics g) {
		   g.clearRect(0,0,getWidth(),getHeight());
		   g.drawLine(50,400,450,400);
		   
		   for(int gb=1;gb<13;gb++) { //그래프 배경 그리기
			   g.drawString(gb*10+"",20,400-30*gb);//y축 표시값
			   g.drawLine(50, 400-30*gb, 450, 400-30*gb); //x축 라인 그리기 20씩
		   }
		   g.drawLine(50, 20, 50, 400);
		   g.drawString("1월", 70, 420);
		   g.drawString("2월", 100, 420);
		   g.drawString("3월", 130, 420);
		   g.drawString("4월", 160, 420);
		   g.drawString("5월", 190, 420);
		   g.drawString("6월", 220, 420);
		   g.drawString("7월", 250, 420);
		   g.drawString("8월", 280, 420);
		   g.drawString("9월", 310, 420);
		   g.drawString("10월", 340, 420);
		   g.drawString("11월", 370, 420);
		   g.drawString("12월", 400, 420);
		   g.setColor(Color.red);
		   
		   if(jan>0)
			   g.fillRect(80, 450-jan*2, 10, jan*2);
		   if(feb>0)
			   g.fillRect(110, 450-feb*2, 10, feb*2);
		   if(mar>0)
			   g.fillRect(140, 450-mar*2, 10, mar*2);
		   if(apr>0)
			   g.fillRect(170, 450-apr*2, 10, apr*2);
		   if(may>0)
			   g.fillRect(200, 450-may*2, 10, may*2);
		   if(jun>0)
			   g.fillRect(230, 450-jun*2, 10, jun*2);
		   if(jul>0)
			   g.fillRect(260, 450-jul*2, 10, jul*2);
		   if(aug>0)
			   g.fillRect(290, 450-aug*2, 10, aug*2);
		   if(sep>0)
			   g.fillRect(320, 450-sep*2, 10, sep*2);
		   if(oct>0)
			   g.fillRect(350, 450-oct*2, 10, oct*2);
		   if(nov>0)
			   g.fillRect(380, 450-nov*2, 10, nov*2);
		   if(dec>0)
			   g.fillRect(410, 450-dec*2, 10, dec*2);
	   }
}
//"조회" 버튼을 누르면 실행되는 메소드 우선 전체 휴게소에 대해서 구현해보았음 그러므로 시작하는 한달만 될것.
class LookListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		lu = new LookupAndModify();
		String combo2Id = combo2.getSelectedItem().toString().split("월")[0];//시작하는 달
		String combo3Id = combo3.getSelectedItem().toString().split("월")[0];//끝나는 달 
		if(Integer.parseInt(combo2Id)>Integer.parseInt(combo3Id))//시작하는달이 끝나는달보다 작을수없음.
		{
			System.out.println("invalid content");
			return;
		}else if(Integer.parseInt(combo2Id)<Integer.parseInt(combo3Id)) {
			new DrawingPanel();
		}
		model.setRowCount(0);//먼저 테이블을 초기화한다.
		Vector<Area> temp = lu.lookAll(Integer.parseInt(combo2Id));//객체를 담은 벡터
		for (Area area : temp)//각각의 객체에 대해서
		{
			Vector<String> temp2 = new Vector<String>();//스트링타입의 벡터를 넣는다.
			temp2.add(Integer.toString(area.getStndate()));//기준연월
			temp2.add(Integer.toString(area.getSlranking()));//판매순위
			temp2.add(Integer.toString(area.getSlrankingra()));//휴게소내 판매순위
			temp2.add(area.getRacode());//휴게소 코드
			temp2.add(area.getRaname());//휴게소명
			temp2.add(Integer.toString(area.getStcode()));//매장코드
			temp2.add(area.getStname());//매장명
			model.addRow(temp2);
		}
		System.out.println(combo2Id+" "+combo3Id);
		System.out.println(model.getRowCount());
	}
}
class btn01Listener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
     class ascListener implements ActionListener
     {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//model.getDataVector().sort();
		}
     }
// 파일불러오기 메뉴아이템 
class LoadActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("csv파일","csv"));
        int returnval = fileChooser.showOpenDialog(null);//열기옵션으로 filechooser를 연뒤에 행동에 따라 return 값이 나온다.
        if(returnval == JFileChooser.APPROVE_OPTION)//yes,ok,열기 등 확인버튼을 누르면 실행되는것
        {
        	String Path = fileChooser.getSelectedFile().getPath();//선택된파일의 경로를 스트링으로 저장
            ctd = new Csvtodb();
            System.out.println(Path);
            try 
            {
            	ctd.invert(Path);
            } 
            catch (SQLException | IOException e1) 
            {
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