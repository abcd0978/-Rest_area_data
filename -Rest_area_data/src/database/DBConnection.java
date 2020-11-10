package database;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection 
{
	private final static String JDCB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://14.38.252.76/restareadata?characterEncoding=UTF-8&serverTimezone=UTC";//mysql 주소
	private final static String USER_NAME = "aoop";//아이디
	private final static String PASSWORD = "0978";//비번
	private static Connection con = null;
	private DBConnection() {}//접근 x
	public static Connection getInstance()
	{
		if(con==null)
		{
			try {
				Class.forName(JDCB_DRIVER);
				con = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
				System.out.println("///////DB연결됨//////");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("연결오류");
			}
		}
		return con;
	}
	
}