package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

/*���ؿ����� �޾� ���ؿ����� �����ϴ� ���������̺��� �����ϴ� Ŭ�����̴�.*/
public class CreteTable 
{
	private PreparedStatement ps = null;//�������� �����ϴ� ��ü
	private Connection con = null;//DB�� �����ϴ� ��ü
	private ResultSet rs = null;
	private String query = "SELECT 1 FROM Information_schema.tables WHERE table_name = ?";
	public CreteTable()
	{
		con = DBConnection.getInstance();//DBConnection�ν��Ͻ� �޾ƿ���(DB�� �����Ѵ�.)
	}
	//���̺��� �����ϴ��� ���ϴ��� Ȯ���ϴ� �޼ҵ�
	public boolean does_exist(String yearmonth) throws SQLException
	{
		ps = con.prepareStatement(query);
		ps.setString(1,"table_"+yearmonth);//�տ� table���ڿ��� �߰��� ������ ���ڷθ� ���̺���� ���ϸ� ������ ����Ű�⶧��.
		System.out.println(ps.toString());//����׸޼���
		ps.execute();//��������
		rs = ps.getResultSet();//������ �޾ƿ���
		if(rs.next())
			return true;//���̺��� �����ϸ� true��ȯ
		else
			return false;//���̺��� �������� ������ false��ȯ
	}
	//���̺��� �������ִ� �޼ҵ��̴�.
	public void createAction(String yearmonth) throws SQLException
	{
		System.out.println("���̺� ��������");
		String create_query = "create table table_"+yearmonth
				+ "("
				+ "stndate int,"//���ؿ���
				+ "slranking int,"//�Ǹż���
				+ "slrankingra int,"//�ްԼҳ� �Ǹż���
				+ "racode varchar(10),"//�ްԼ� �ڵ�
				+ "raname varchar(30),"//�ްԼҸ�
				+ "stcode int,"//�����ڵ�
				+ "stname varchar(30)"//�����̸�
				+ ")";
		ps.execute(create_query);//���̺� ����
		System.out.println("���̺� �����Ϸ�");
	}
}
	

