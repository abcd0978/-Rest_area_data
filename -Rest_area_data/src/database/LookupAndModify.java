package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.protocol.Resultset;

public class LookupAndModify 
{
	Connection con;
	Vector<Area> areas;
	Statement st = null;
	ResultSet rs = null;
	public LookupAndModify()
	{
		con = DBConnection.getInstance();
	}
	//1~9월은 앞에 0이 붙어서 넘겨야 일일이 해주기 귀찮아서 매소드로 만듬.
	public String getMonString(int Month)
	{
		if(Month<10)
			return "0"+Month;
		else
			return Integer.toString(Month);
	}
	//전체휴게소 
	public Vector<Area> lookAll(int Month)
	{
		String query = "SELECT * FROM table_2020"+getMonString(Month);
		areas = new Vector<Area>();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areas;
	}
	//특정휴게소 휴게소 기간
	public Vector<Vector<Area>> lookarea(String raname,int StartM,int EndM)
	{
		int count = EndM - StartM;
		Vector<Vector<Area>> temp = new Vector<Vector<Area>>();//전체달
		try {
			for(int i=0;i<count;i++)
			{
				String query = "SELECT * FROM table_2020"+getMonString(StartM)+" WHERE raname = '"+raname+"';";
				areas = new Vector<Area>();//특정달에 대한 휴게소 정보 
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
				if(StartM!=EndM)
					StartM++;
				temp.add(areas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	//특정휴게소 한달
	public Vector<Area> lookarea(String raname, int Month)
	{
		String query = "SELECT * FROM table_2020"+getMonString(Month)+" WHERE raname = "+raname;
		areas = new Vector<Area>();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areas;
	}
	//삽입
	public void insert(int stndate,int slranking,int slrankingra,String racode,String raname,int stcode,String stname)
	{
		
	}
	//휴게소 검색 name으로 시작하는 것을 찾는다.
	public Vector<Area> search(int Month,String name)
	{
		String query = "SELECT * FROM table_2020"+getMonString(Month)+" WHERE raname LIKE '"+name+"%'";
		areas = new Vector<Area>();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}
}
