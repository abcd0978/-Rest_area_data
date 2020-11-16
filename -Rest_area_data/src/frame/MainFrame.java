package frame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.Area;
import database.Csvtodb;
import database.LookupAndModify;
import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.*; // ActionListener & ActionEvent 패키지를 위한 Import
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class MainFrame extends JFrame
{
   
   JButton button01, button02, button03, b01, b02;
   JMenu jm01,jm02,jm03;
   JMenuBar jmb;
   JMenuItem load_local,save_local,save_db;//불러오기,저장
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
   JTextField searchBar;//검색창 텍스트필드
   DrawingPanel drawingPanel;//그래프그리는 객체
   JPanel p1,p2;//패널
   boolean clicked = false;
   public void printFunc() 
   {
      System.out.println("------Programe TEST1------");
   }
   public void JframeFunc() 
   {
      frame = new JFrame(); // 프레임 생성
      
      frame.setTitle("고속도로 휴게소 매장 프로그램");
      jmb = new JMenuBar(); // JMenuBar 생성
      
      
      jm01 = new JMenu("파일"); // Menu 생성
      
      load_local = new JMenuItem("파일 로컬에서 불러오기"); // load메뉴아이템
      save_local = new JMenuItem("파일 로컬에 저장하기"); //save메뉴아이템
      save_db = new JMenuItem("현재 표시된내용 DB에 저장하기");
      
      jm01.add(save_local);//Menu에 MenuItem추가
      jm01.add(load_local);//Menu에 MenuItem추가
      jm01.add(save_db);
      
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
      
      load_local.addActionListener(new LoadActionListener());
      save_local.addActionListener(new SaveActionListener());
      save_db.addActionListener(new saveDB());
      jtb = new JToolBar();
      searchBar = new JTextField(10);
      
      
      combo2 = new JComboBox<String>();
      combo2.addItem("2019-11");
      combo2.addItem("2019-12");
      combo2.addItem("2020-01");
      combo2.addItem("2020-02");
      combo2.addItem("2020-03");
      combo2.addItem("2020-04");
      combo2.addItem("2020-05");
      combo2.addItem("2020-06");
      combo2.addItem("2020-07");
      combo2.addItem("2020-08");
      combo2.addItem("2020-09");
      combo2.addItem("2020-10");

      
      combo3 = new JComboBox<String>();
      combo3.addItem("2019-11");
      combo3.addItem("2019-12");
      combo3.addItem("2020-01");
      combo3.addItem("2020-02");
      combo3.addItem("2020-03");
      combo3.addItem("2020-04");
      combo3.addItem("2020-05");
      combo3.addItem("2020-06");
      combo3.addItem("2020-07");
      combo3.addItem("2020-08");
      combo3.addItem("2020-09");
      combo3.addItem("2020-10");

      
      jtb.add(searchBar);
      jtb.add(combo2);
      jtb.add(combo3);
      
      b01 = new JButton("조회/통계");
      jtb.add(b01);
      frame.getContentPane().add(jtb,BorderLayout.NORTH);
      
      
      // 버튼 생성
      button01 = new JButton("그래프보기");
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
      
      ////////////////////////////////////////////////////////////////////////////위에것들은 JFrame내부
      p1 = new JPanel();
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
      scroll.setPreferredSize(new Dimension(1000,500));
      drawingPanel = new DrawingPanel();
      drawingPanel.setPreferredSize(new Dimension(1000,500));
      
      p1.add(scroll);
      frame.getContentPane().add(drawingPanel,BorderLayout.CENTER); 
      frame.getContentPane().add(p1,BorderLayout.CENTER);
     
      
      //frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setSize(1020, 640); // 프레임 크기
      frame.setVisible(true); // 프레임 화면 표시 설정

   }
class DrawingPanel extends JPanel{
	   public void paint(Graphics g) {
		   g.clearRect(0,0,getWidth(),getHeight());
		   g.drawLine(50,400,450,400);
		   
		   for(int gb=1;gb<13;gb++) //그래프 배경 그리기
		   { 
			   g.drawString(gb*5+"",20,400-30*gb);//y축 표시값
			   g.drawLine(50, 400-30*gb, 450, 400-30*gb); //x축 라인 그리기 20씩
		   }
		   g.drawLine(50, 20, 50, 400);
		   g.setColor(Color.red);
	   }
}
//조회버튼 
class LookListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		lu = new LookupAndModify();
		String combo2Id = combo2.getSelectedItem().toString().replace("-","");//시작하는 달
		String combo3Id = combo3.getSelectedItem().toString().replace("-","");//끝나는 달
		if(Integer.parseInt(combo2Id)>Integer.parseInt(combo3Id))//시작하는달이 끝나는달보다 작을수없음.
		{
			System.out.println("invalid content");
			return;
		}
		model.setRowCount(0);//먼저 테이블을 초기화한다.
		if(searchBar.getText().equals(""))//검색창에 아무것도 입력하지 않았을때.
		{
			Vector<Area> temp = lu.lookAll(combo2Id);//객체를 담은 벡터
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
			System.out.println(model.getRowCount());
		}
		else
		{
			
			System.out.println(searchBar.getText());
			Vector<Vector<Area>> temp = lu.lookarea(searchBar.getText(),combo2Id,combo3Id);//객체벡터를 담은 벡터
			for (int i=0;i<temp.size();i++) 
			{
				Vector<Area> temp2 = temp.get(i);//객체백터를 뽑아낸다.
				for (Area area : temp2)
				{
					Vector<String> temp3 = new Vector<String>();//스트링타입의 벡터
					temp3.add(Integer.toString(area.getStndate()));//기준연월
					temp3.add(Integer.toString(area.getSlranking()));//판매순위
					temp3.add(Integer.toString(area.getSlrankingra()));//휴게소내 판매순위
					temp3.add(area.getRacode());//휴게소 코드
					temp3.add(area.getRaname());//휴게소명
					temp3.add(Integer.toString(area.getStcode()));//매장코드
					temp3.add(area.getStname());//매장명
					model.addRow(temp3);
				}
			}
		}
		
	}
}
class btn01Listener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(clicked==false)
		{
			clicked = true;
			button01.setText("테이블보기");
			frame.getContentPane().add(drawingPanel,BorderLayout.CENTER); 
			p1.setVisible(false);
			drawingPanel.setVisible(true);
			drawingPanel.repaint();
		}
		else
		{
			button01.setText("그래프보기");
			frame.getContentPane().add(p1,BorderLayout.CENTER);
			clicked=false;
			p1.setVisible(true);
			drawingPanel.setVisible(false);
		}

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
            try 
            {
            	JOptionPane.showMessageDialog(null,ctd.invert(Path));
            } 
            catch (SQLException | IOException e1) 
            {
    			e1.printStackTrace();
    		}
         }
	}
}
//csv로 저장하는 리스너.
class SaveActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("wait for download");
	}
}

//데이터 삭제 리스너.
class DeleteActionListener implements ActionListener{
 @Override
 public void actionPerformed(ActionEvent e) 
 {
 	lu.Delete(combo2.getSelectedItem().toString().replace("-",""), Table.getValueAt(Table.getSelectedRow(), 4).toString(), Table.getValueAt(Table.getSelectedRow(), 6).toString());
 }  
}

//데이터 수정 리스너.
class ModifyActionListener implements ActionListener{
 @Override
 public void actionPerformed(ActionEvent e) 
 {
 	lu.Modify(combo2.getSelectedItem().toString().replace("-",""), Table.getSelectedColumn(), Table.getValueAt(Table.getSelectedRow(), Table.getSelectedColumn()).toString(), Table.getValueAt(Table.getSelectedRow(), 3).toString(), Table.getValueAt(Table.getSelectedRow(), 5).toString());
 }  
}
//현재 표시된 내용 DB에 저장하기
class saveDB implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showInputDialog("테이블명: ");
	}
	
}
}