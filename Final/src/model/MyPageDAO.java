package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MyPageDAO {
	Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;
	ArrayList list;
	
	public MyPageDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	public ArrayList myShoppinglist(int memcode) throws Exception{
		String sql = " select s.shopcode, m.machinename, s.shopquantity, s.shopquantity*a.amsprice, mo.modate, mo.modeliver"
				+ " from machine m, shoppinglist s, am_seller a, machineorder mo"
				+ " where m.machinecode = a.machinecode and a.amscode = s.amscode"
				+ " and s.mocode = mo.mocode and mo.memcode = "+memcode;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));
			list.add(temp);
		}
		
		return list;
	}
	
	public ArrayList mySomoList(int memcode) throws Exception{
		String sql = "select smd.sodcode, acc.acname, smd.sodquantity, smd.sodquantity*acs.acsprice, sm.sodate, sm.sodeliver "
				+ "from somo sm, smdesc smd, accstore acs, accesories acc "
				+ "where acc.accode = acs.accode and acs.acscode = smd.smcode and "
				+ "smd.scode = sm.scode and sm.memcode = "+memcode;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));
			list.add(temp);
		}
		
		return list;
	}
}
