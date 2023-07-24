package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.CareVO;


public class CareDAO {
	private Connection conn = null;
	   private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	   private String id = "soft";
	   private String pass = "soft";
	   private String driver = "oracle.jdbc.driver.OracleDriver" ;
	   PreparedStatement ps = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   CareVO vo =null;
	   ArrayList list = null;
	   
	   
	   public CareDAO() throws Exception
	   {
	      /*============================================
	         생성자 함수    - DB 연결
	         1. 드라이버를 로딩
	         2. Connection 얻어오기
	      */
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	       conn = DriverManager.getConnection(url, id, pass);
	   }
	   public void careInsert(CareVO vo) throws Exception {
		   String sql = "insert into careservice (cscode, csstartdate, cslastdate, memcode, cgcode, csdate)"
				+ "values(cares_seq.nextval, ?, ?, ?, ?, sysdate)";
		   ps = conn.prepareStatement(sql);
		  
			   
		   
			  ps.setString(1, vo.getCsstartdate());
			  ps.setString(2, vo.getCslastdate());
			  ps.setInt(3, vo.getMemcode());
			  ps.setInt(4, vo.getCgcode());
		      
			  ps.executeUpdate();
	   
	   
	   }
//	   public int CareModify(CareVO vo) throws Exception
//	   {
//		   String sql = "update careservice set csstartdate = ?, cslastdate = ?";
//		   ps = conn.prepareStatement(sql);
//		   ps.setString(1, vo.getCsstartdate());
//		   ps.setString(2, vo.getCslastdate());
//		   
//		   
//		   int count = ps.executeUpdate();
//		   ps.close();
//		   return count;
//		  
//	   }
	   public CareVO findByCgcode(int vNum) throws Exception {
		   String sql = "select cgcode, cgname, cgcom, cgtel, sfcode"
				   				+ " where cgcode = " + vNum;
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   
		   vo = new CareVO();
		   if(rs.next()) {
			   vo.setCgcode(rs.getInt("cgcode"));
			   vo.setCgname(rs.getString("cgname"));
			   vo.setCgcom(rs.getString("cgcom"));
			   vo.setCgtel(rs.getString("cgtel"));
			   vo.setSfcode(rs.getInt("sfcode"));
		   }
		   rs.close();
		   stmt.close();
		   return vo;
	   }
	   
	   public CareVO findByCscode(int vnum) throws Exception {
		   String sql = "select cscode, csstartdate, cslastdate, memcode, cgcode, csdate from careservice"
				   				+ " where cscode = " + vnum;
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   
		   vo = new CareVO();
		   if(rs.next() ) {
			   vo.setCscode(rs.getInt("cscode"));
			   vo.setCsstartdate(rs.getString("csstartdate"));
			   vo.setCslastdate(rs.getString("cslastdate"));
			   vo.setMemcode(rs.getInt("memcode"));
			   vo.setCgcode(rs.getInt("cgcode"));
			   vo.setCsdate(rs.getString("csdate"));
		   }
		   rs.close();
		   stmt.close();
		   return vo;
		   
	   }
	   public ArrayList CareSearch(int care_loc) throws Exception {
		   String index[] = {"'서울'", "'대전'", "'부산'", "'경상도'", "'전라도'"};
		   String sql = "select cgcode, cgname, cgcom, cgtel, sfcode, care_loc from caregiver"
				   				+ " where care_loc = " + index[care_loc];
		   list = new ArrayList();
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   
		   
		   while(rs.next()) {
			   ArrayList tmp = new ArrayList();
			   tmp.add(rs.getInt("cgcode"));
			   tmp.add(rs.getString("cgname"));
			   tmp.add(rs.getString("cgcom"));
			   tmp.add(rs.getString("cgtel"));
			   tmp.add(rs.getInt("sfcode"));
			   tmp.add(rs.getString("care_loc"));
			   
			   list.add(tmp);
		   }
		   stmt.close();
		   rs.close();
		   return list;
		   
	   }
	   public ArrayList ServiceSearch(int cscode) throws Exception {
		   
		   String sql = "select cscode, csstartdate, cslastdate, memcode, cgcode, csdate from careservice"
	   				+ " where cscode = " + cscode;
		   list = new ArrayList();
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   if(rs.next() ) {
			   ArrayList tmp = new ArrayList();
			   tmp.add(rs.getInt("cscode"));
			   tmp.add(rs.getString("csstartdate"));
			   tmp.add(rs.getString("cslastdate"));
			   tmp.add(rs.getInt("memcode"));
			   tmp.add(rs.getInt("cgcode"));
			   tmp.add(rs.getString("csdate"));
			   
			   list.add(tmp);
		   }
		   stmt.close();
		   rs.close();
		   return list;
	   }
	   
	
}
