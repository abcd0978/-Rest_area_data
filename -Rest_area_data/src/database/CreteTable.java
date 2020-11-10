package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

/*기준연월을 받아 기준연월에 대응하는 데이터테이블을 생성하는 클래스이다.*/
public class CreteTable 
{
	private PreparedStatement ps = null;//쿼리문을 실행하는 객체
	private Connection con = null;//DB에 연결하는 객체
	private ResultSet rs = null;
	private String query = "SELECT 1 FROM Information_schema.tables WHERE table_name = ?";
	public CreteTable()
	{
		con = DBConnection.getInstance();//DBConnection인스턴스 받아오기(DB에 연결한다.)
	}
	//테이블이 존재하는지 안하는지 확인하는 메소드
	public boolean does_exist(String yearmonth) throws SQLException
	{
		ps = con.prepareStatement(query);
		ps.setString(1,"table_"+yearmonth);//앞에 table문자열을 추가한 이유는 숫자로만 테이블명을 정하면 오류를 일으키기때문.
		System.out.println(ps.toString());//디버그메세지
		ps.execute();//쿼리실행
		rs = ps.getResultSet();//실행결과 받아오기
		if(rs.next())
			return true;//테이블이 존재하면 true반환
		else
			return false;//테이블이 존재하지 않으면 false반환
	}
	//테이블을 생성해주는 메소드이다.
	public void createAction(String yearmonth) throws SQLException
	{
		System.out.println("테이블 생성시작");
		String create_query = "create table table_"+yearmonth
				+ "("
				+ "stndate int,"//기준연월
				+ "slranking int,"//판매순위
				+ "slrankingra int,"//휴게소내 판매순위
				+ "racode varchar(10),"//휴게소 코드
				+ "raname varchar(30),"//휴게소명
				+ "stcode int,"//매장코드
				+ "stname varchar(30)"//매장이름
				+ ")";
		ps.execute(create_query);//테이블 생성
		System.out.println("테이블 생성완료");
	}
}
	

