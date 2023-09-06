package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.rec.AsVO;
import model.rec.AscurrentVO;
import model.rec.AsnowVO;

public class ASDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";
	AsVO asVO;
	AscurrentVO acvo;
	PreparedStatement ps = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	ArrayList list = new ArrayList();
	ArrayList asnlist = new ArrayList();
	public ASDAO() throws Exception

	{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);

	}

	public int asappInsert(AscurrentVO acvo) throws Exception {
		
		String sql2 = "select as_seq.nextval from dual";
		
		int num = 0;
		stmt = conn.createStatement();
		rs= stmt.executeQuery(sql2);
		
		if (rs.next()) {
			num = rs.getInt(1);
		}
		
		String sql = "insert into afterserveice values(?, sysdate,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setString(2, acvo.getAssch());
		ps.setInt(3, acvo.getMemcode());
		ps.setString(4, acvo.getMachinename());

		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return num;
	}
	
	public void asnow(int afsccode, int asapcode, String ascdate, String ascsname, String ascstel) throws Exception{
		
		String sql = "insert into afterservicenow values(asnow_seq.nextval,?,?,?,'N',?,?)";
		
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, afsccode);
		ps.setInt(2, asapcode);
		ps.setString(3, ascdate);
		ps.setString(4, ascsname);
		ps.setString(5, ascstel);
		
		ps.executeUpdate();
		
		ps.close();
	}

	

	public AsVO findByAsVO(String asloc) throws Exception {

		String sql = "select afsccode,afscname, afscloc, afsctel, URL, afsctime, afscprice from afscom "
				+ "where afscloc = " + asloc;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		AsVO asvo = new AsVO();
		
		if (rs.next()) {
			asvo.setAfsccode(rs.getInt("afsccode"));
			asvo.setAfscname(rs.getString("afscname"));
			asvo.setAfscloc(rs.getString("afscloc"));
			asvo.setAfsctel(rs.getString("afsctel"));
			asvo.setURL(rs.getString("URL"));
			asvo.setAfsctime(rs.getString("afsctime"));
			asvo.setAfscprice(rs.getString("afscprice"));

		}
		rs.close();
		stmt.close();
		return asvo;
	}

	public ArrayList AscomSearch(String str) throws Exception {

		list = new ArrayList();

		String sql = " select afsccode , afscname, afscloc, afsctel, url, afsctime, afscprice from afscom where afscloc = '"
				+ str + "'";

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList tem = new ArrayList();
			tem.add(rs.getInt("afsccode"));
			tem.add(rs.getString("afscname"));
			tem.add(rs.getString("afscloc"));
			tem.add(rs.getString("afsctel"));
			tem.add(rs.getString("url"));
			tem.add(rs.getString("afsctime"));
			tem.add(rs.getString("afscprice"));

			list.add(tem);
			System.out.println(list);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public ArrayList asnList(String anNum) throws Exception {
		
		asnlist = new ArrayList();
		String sql = " select a.ascscode, a.afsccode, a.asapcode, a.ascsdate, a.ascsconfirm, a.ascsname, a.ascstel from afterservicenow a, member b, afterserveice c where b.memcode = c.memcode and c.asapcode = a.asapcode and c.memcode = '" + anNum + "'" ;
		asnlist = new ArrayList();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("ascscode"));
			temp.add(rs.getInt("afsccode"));
			temp.add(rs.getInt("asapcode"));
			temp.add(rs.getString("ascsdate"));
			temp.add(rs.getString("ascsconfirm"));
			temp.add(rs.getString("ascsname"));
			temp.add(rs.getString("ascstel"));
			
			asnlist.add(temp);

		}
		rs.close();
		stmt.close();
		return asnlist;

	}


	public AsVO findAslist(int asNum) throws Exception {
		
		String sql = "select afsccode, afscname, afscloc, afsctel, url, afsctime, afscprice from afscom where afsccode = " + asNum;
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		AsVO asVO = new AsVO();
		if (rs.next()) {
			asVO.setAfsccode(rs.getInt("afsccode"));
			asVO.setAfscname(rs.getString("afscname"));
			asVO.setAfscloc(rs.getString("afscloc"));
			asVO.setAfsctel(rs.getString("afsctel"));
			asVO.setURL(rs.getString("url"));
			asVO.setAfsctime(rs.getString("afsctime"));
			asVO.setAfscprice(rs.getString("afscprice"));
		}
		return asVO;

}
	public AsnowVO findAsnowlist(String anNum) throws Exception{
		
		String sql = "select ascscode, afsccode, asapcode, ascsdate, ascsconfirm, ascsname, ascstel from afterservicenow where afsccode = " + anNum;
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		AsnowVO anvo = new AsnowVO();
		if (rs.next()) {
			anvo.setAscscode(rs.getInt("ascscode"));
			anvo.setAfsccode(rs.getInt("afsccode"));
			anvo.setAsapcode(rs.getInt("asapcode"));
			anvo.setAscsdate(rs.getString("ascsdate"));
			anvo.setAscsconfirm(rs.getString("ascsconfirm"));
			anvo.setAscsname(rs.getString("ascsname"));
			anvo.setAscstel(rs.getString("ascstel"));
		}
		return anvo;
		
	}
}

