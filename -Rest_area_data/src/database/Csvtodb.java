package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*csv������ ��θ� �޾Ƽ� db�� �������ִ� Ŭ����*/
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
	/*CSV�������ִ� ��θ� �޾Ƽ� db�� �־��ش�.*/
    public void invert(String Location) throws SQLException, IOException
    {
    	/*���ؿ����� �޴� �ڵ�*/
    	BufferedReader lineReader2 = new BufferedReader(new FileReader(Location));//��ο��ִ� ���� ����
		String lineText2 = null;//��
		String yearmonth = null;//���ؿ����� ������ ��Ʈ��
		lineReader2.readLine();//ù���� �׳� �ѱ��, ���������.
		lineText2 = lineReader2.readLine();//�״������� �о �����Ѵ�.
		String[] data2 = lineText2.split(",");//�׵� �������� ������.
		yearmonth = data2[0];//�������� ���� �� ������ ù��° ���ؿ����� �����Ѵ�.
    	lineReader2.close();//�ݱ�
    	if(ct.does_exist(yearmonth))//��� ���� ���ؿ����� ���� ���̺��� �����ϴ°�?
    	{
    		System.out.println("�����Ͱ� �̹� �����մϴ�.");
    		return;
    	}
    	else//ó�� �޴� csv�����̶��,
    	{
    		ct.createAction(yearmonth);//���� ���̺��� ������ش�.
    		
    		String sql = "INSERT INTO table_"+yearmonth+" (stndate, slranking, slrankingra, racode, raname, stcode, stname) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	    ps = con.prepareStatement(sql);
    	    BufferedReader lineReader = new BufferedReader(new FileReader(Location));//��ο��ִ� ���� ����
    	    String lineText = null;//��
    	    
    	    int count = 0;
    	 
    	    lineReader.readLine(); // skip header line
    	 
    	    while ((lineText = lineReader.readLine()) != null)
    	    {
    		    String[] data = lineText.split(",");//�������� ������.
    	        //String standard_date = data[0];//���ؿ���
    	        String sales_ranking = data[1];//�Ǹż���
    	        String sales_ranking_rest_area = data[2];//�ްԼҳ� �Ǹż���
                String rest_area_code = data[3];//�ްԼ��ڵ�
                String rest_area_name = data[4];//�ްԼ��̸�
                String store_code = data[5];//�Ǹ����ڵ�
                String store_name = data[6];//�Ǹ����̸�
    	        
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
    	System.out.println("�����ͺ��̽� �̽ĿϷ�");
    	}
}