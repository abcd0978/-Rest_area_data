package frame;
import java.io.IOException;
import java.sql.SQLException;

import database.Csvtodb;

/*csv파일 db로 이식되는지 테스트할려고 만든 임시클래스*/
public class main 
{
	public static void main(String args[]) throws IOException
	{
		Csvtodb cs = new Csvtodb();
		try {
			cs.invert("C:\\Users\\고나연\\Desktop\\csv\\ETC_R3_05_02_398358.csv");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}