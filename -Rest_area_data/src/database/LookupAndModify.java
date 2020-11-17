package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.protocol.Resultset;

public class LookupAndModify 
{
	Connection con;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement delete = null;
	private PreparedStatement modify = null;
	public LookupAndModify()
	{
		con = DBConnection.getInstance();
	}
	//다른년도와 다른월 의 콤보박스 스트링을 처리할수있도록 하는 메소드
	public ArrayList<String> TakeyearMonth(String StartyearMonth,String EndyearMonth)
	{
		ArrayList<String> temp = new ArrayList<String>();
		int startM = Integer.parseInt(StartyearMonth)%100;//시작하는 월을 정수로 바꾼다.
		int endM = Integer.parseInt(EndyearMonth)%100;//끝나는월도 정수로 바꾼다.
		int startY = Integer.parseInt(StartyearMonth)/100;//연
		int endY = Integer.parseInt(EndyearMonth)/100;//연
		while(endY+endM >= startY+startM)
		{
			if(startM<10)
				temp.add(Integer.toString(startY)+"0"+Integer.toString(startM));
			else
				temp.add(Integer.toString(startY)+Integer.toString(startM));
			if(startM==12)
			{
				startY++;
				startM = 1;
			}
			else
				startM++;
		}
		return temp;//날짜를 담은 리스트를 반환함.
	}
	//전체휴게소
	public Vector<Vector<Area>> lookAll(String StartyearMonth,String EndyearMonth)
	{
		ArrayList<String> temp = TakeyearMonth(StartyearMonth, EndyearMonth);//첫달과 끝달을 받아 연속되는 리스트로 반환
		Vector<Vector<Area>> total = new Vector<Vector<Area>>();//전체달
		try {
			for(int i=0;i<temp.size();i++)
			{
				String query = "select * from table_"+temp.get(i);
				Vector<Area> areas = new Vector<Area>();
				st = con.createStatement();
				st.execute(query);
				rs = st.getResultSet();
				while(rs.next())
				{
					int temp1 = rs.getInt("stndate");
					int temp2 = rs.getInt("slranking");
					int temp3 = rs.getInt("slrankingra");
					String temp4 = rs.getString("racode");
					String temp5 = rs.getString("raname");
					int temp6 = rs.getInt("stcode");
					String temp7 = rs.getString("stname");
					areas.add(new Area(temp1,temp2,temp3,temp4,temp5,temp6,temp7));
				}
				total.add(areas);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return total;
	}
	
	//특정휴게소 휴게소 (검색)
	public Vector<Vector<Area>> lookarea(String raname,String StartyearMonth,String EndyearMonth)
	{
		ArrayList<String> temp = TakeyearMonth(StartyearMonth, EndyearMonth);
		Vector<Vector<Area>> total = new Vector<Vector<Area>>();//전체달
		try {
			for (int i=0;i<temp.size();i++)
			{
				String query = "SELECT * FROM table_"+temp.get(i)+" WHERE raname LIKE '"+raname+"%';";
				Vector<Area> areas = new Vector<Area>();//특정달에 대한 휴게소 정보 
				st = con.createStatement();
				st.execute(query);
				rs = st.getResultSet();
				while(rs.next())
				{
					int temp1 = rs.getInt("stndate");
					int temp2 = rs.getInt("slranking");
					int temp3 = rs.getInt("slrankingra");
					String temp4 = rs.getString("racode");
					String temp5 = rs.getString("raname");
					int temp6 = rs.getInt("stcode");
					String temp7 = rs.getString("stname");
					areas.add(new Area(temp1,temp2,temp3,temp4,temp5,temp6,temp7));//객체 저장
				}
				total.add(areas);
			}
			System.out.println(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	//삽입
	public void insert(int stndate,int slranking,int slrankingra,String racode,String raname,int stcode,String stname)
	{
		
	}
	// 삭제.
	public void Delete(String yearMonth, String raname, String stname) 
	{
		try {
			String str = "DELETE FROM table_";
			str = str + yearMonth;
			delete = con.prepareStatement(str + " WHERE raname=" + "?" + " AND " + "stname=" + "?");
			delete.setNString(1, raname); // 명령어 입력.
			delete.setNString(2, stname); // 명령어 입력.
			delete.executeUpdate(); // 삭제.
		} catch(SQLException sqlException) {
			sqlException.getStackTrace();
		}
	}
		// 수정.
	public void Modify(String yearMonth, int col, String after, String racode, String stcode) 
	{
		try {
			String str = "UPDATE table_";//쿼리문
			// 맞는 열 찾기.
			if(col == 0) {
				str = str + yearMonth + " SET " + "stndate" + "=";
			} else if(col == 1) {
				str = str + yearMonth + " SET " + "slranking" + "=";
			} else if(col == 2) {
				str = str + yearMonth + " SET " + "slrankingra" + "=";
			} else if(col == 3) {
				str = str + yearMonth + " SET " + "racode" + "=";
			} else if(col == 4) {
				str = str + yearMonth + " SET " + "raname" + "=";
			} else if(col == 5) {
				str = str + yearMonth + " SET " + "stcode" + "=";
			} else {
				str = str + yearMonth + " SET " + "stname" + "=";
			}
			modify = con.prepareStatement(str + "?" + " WHERE racode=" + "?" + " AND " + "stcode=" + "?");
			modify.setNString(1, after);
			modify.setNString(2, racode);
			modify.setNString(3, stcode);
			System.out.println("명령어: " + modify);
			modify.executeUpdate(); // 수정.
		} catch(SQLException sqlException) {
			sqlException.getStackTrace();
		}
	}
}
