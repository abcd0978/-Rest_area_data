package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*csv파일의 경로를 받아서 db에 저장해주는 클래스*/
public class Csvtodb 
{
	private Connection con = null;
	private PreparedStatement ps = null;
	private CreteTable ct = null;
	int batchSize = 20;
	public Csvtodb()
	{
		con = DBConnection.getInstance();
		ct = new CreteTable();
	}
	/*CSV파일이있는 경로를 받아서 db에 넣어준다.*/
    public void invert(String Location) throws SQLException, IOException
    {
    	/*기준연월을 받는 코드*/
    	BufferedReader lineReader2 = new BufferedReader(new FileReader(Location));//경로에있는 파일 읽음
		String lineText2 = null;//줄
		String yearmonth = null;//기준연월을 저장할 스트링
		lineReader2.readLine();//첫줄은 그냥 넘긴다, 헤더라인임.
		lineText2 = lineReader2.readLine();//그다음줄을 읽어서 저장한다.
		String[] data2 = lineText2.split(",");//그뒤 반점으로 나눈다.
		yearmonth = data2[0];//반점으로 나눈 각 열들중 첫번째 기준연월을 저장한다.
    	lineReader2.close();//닫기
    	if(ct.does_exist(yearmonth))//방금 구한 기준연월을 가진 테이블이 존재하는가?
    	{
    		System.out.println("데이터가 이미 존재합니다.");
    		return;
    	}
    	else//처음 받는 csv파일이라면,
    	{
    		ct.createAction(yearmonth);//먼저 테이블을 만들어준다.
    		
    		String sql = "INSERT INTO table_"+yearmonth+" (stndate, slranking, slrankingra, racode, raname, stcode, stname) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	    ps = con.prepareStatement(sql);
    	    BufferedReader lineReader = new BufferedReader(new FileReader(Location));//경로에있는 파일 읽음
    	    String lineText = null;//줄
    	    
    	    int count = 0;
    	 
    	    lineReader.readLine(); // skip header line
    	 
    	    while ((lineText = lineReader.readLine()) != null)
    	    {
    		    String[] data = lineText.split(",");//반점으로 나눈다.
    	        //String standard_date = data[0];//기준연월
    	        String sales_ranking = data[1];//판매순위
    	        String sales_ranking_rest_area = data[2];//휴게소내 판매순위
                String rest_area_code = data[3];//휴게소코드
                String rest_area_name = data[4];//휴게소이름
                String store_code = data[5];//판매점코드
                String store_name = data[6];//판매점이름
    	        
    	        ps.setString(1,yearmonth);
    	        ps.setString(2,sales_ranking);
    	        ps.setString(3,sales_ranking_rest_area);
    	        ps.setString(4,rest_area_code);
    	        ps.setString(5,rest_area_name);
    	        ps.setString(6,store_code);
    	        ps.setString(7,store_name);
    	        
    	        ps.addBatch();
    	        if (count % batchSize == 0) 
    	        {
    	    	    ps.executeBatch();
    	        }
    	    }
    	    lineReader.close();
    	    // execute the remaining queries
    	    ps.executeBatch();
    	 }
    	System.out.println("데이터베이스 이식완료");
    	}
}