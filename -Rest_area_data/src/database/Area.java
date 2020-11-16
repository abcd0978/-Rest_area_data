package database;

public class Area
{
	private int stndate;//기준연월
	private int slranking;//전체판매순위
	private int slrankingra;//휴게소내 판매순위
	private String racode;//휴게소 코드
	private String raname;//휴게소명
	private int stcode;//매장코드
	private String stname;//매장명
	
	public Area(int stndate,int slranking,int slrankingra,String racode,String raname,int stcode,String stname) 
	{
		this.stndate = stndate;
		this.slranking = slranking;
		this.slrankingra = slrankingra;
		this.racode = racode;
		this.raname = raname;
		this.stcode = stcode;
		this.stname = stname;
	}
	
	public int getStndate() {
		return stndate;
	}
	public void setStndate(int stndate) {
		this.stndate = stndate;
	}
	
	public int getSlranking() {
		return slranking;
	}
	public void setSlranking(int slranking) {
		this.slranking = slranking;
	}
	
	public int getSlrankingra() {
		return slrankingra;
	}
	public void setSlrankingra(int slrankingra) {
		this.slrankingra = slrankingra;
	}
	
	public String getRacode() {
		return racode;
	}
	public void setRacode(String racode) {
		this.racode = racode;
	}
	public String getRaname() {
		return raname;
	}
	public void setRaname(String raname) {
		this.raname = raname;
	}
	
	public int getStcode() {
		return stcode;
	}
	public void setStcode(int stcode) {
		this.stcode = stcode;
	}
	
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
}
