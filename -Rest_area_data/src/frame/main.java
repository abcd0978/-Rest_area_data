package frame;
import java.io.IOException;
import java.sql.SQLException;

import database.Csvtodb;

/*csv���� db�� �̽ĵǴ��� �׽�Ʈ�ҷ��� ���� �ӽ�Ŭ����*/
public class main 
{
	public static void main(String args[]) throws IOException
	{
		Csvtodb cs = new Csvtodb();
		try {
			cs.invert("C:\\Users\\abcd0\\Downloads\\ETC____202009\\ETC_R3_05_02_398358.csv");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
