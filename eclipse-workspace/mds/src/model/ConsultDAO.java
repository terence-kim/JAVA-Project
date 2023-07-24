package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ConsultVO;

public class ConsultDAO {
	private Connection conn = null;
	   private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	   private String id = "soft";
	   private String pass = "soft";
	   private String driver = "oracle.jdbc.driver.OracleDriver" ;
	   PreparedStatement ps = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   ConsultVO vo =null;
	   ArrayList list = null;
	   Connection con;
	   
	   public ConsultDAO() throws Exception{
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	       conn = DriverManager.getConnection(url, id, pass);
	   }
	   public void consultInsert(int count, ConsultVO vo) throws Exception {
		   String sql = "insert into interview (ivcode, ivtitle, ivcon, memcode, repcode)"
				   + "values(in_seq.nextval, ?, ?, ?, ?)";
		   ps =conn.prepareStatement(sql);
		   
			   
			   ps.setString(1, vo.getIvtitle());
			   ps.setString(2, vo.getIvcon());
			   ps.setInt(3, vo.getMemcode());
			   ps.setInt(4, vo.getRepcode());
			   
		   
	   }
	   
//	   public int ConsultModify(ConsultVO vo) throws Exception {
//		   String sql = "update interview set ivtitle = ?, ivcon = ?";
//		   ps = conn.prepareStatement(sql);
//		   ps.setString(1, vo.getIvtitle());
//		   ps.setString(2, vo.getIvcon());
//		   
//		   int count = ps.executeUpdate();
//		   ps.close();
//		   return count;
//	   }
	   public ConsultVO findByIvcode(int vnum) throws Exception {
		   String sql = "select ivcode, ivtitle, ivcon, memcode, repcode from interview"
				   		+ " where ivcode = " + vnum;
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   
		   vo = new ConsultVO();
		   if(rs.next() ) {
			   vo.setIvcode(rs.getInt("ivcode"));
			   vo.setIvtitle(rs.getString("ivtitle"));
			   vo.setIvcon(rs.getString("ivcon"));
			   vo.setMemcode(rs.getInt("memcode"));
			   vo.setRepcode(rs.getInt("repcode"));
			   
		   }
		   rs.close();
		   stmt.close();
		   return vo;
	   }
	   public ArrayList ConSearch(int repdept) throws Exception {
		   String index[] = { "'A/S'", "'수거'", "'구매'", "'돌봄'"};
		   String sql = "select repcode, repname, repdept from reporter"
				   				+ " where repdept = " + index[repdept];
		   list = new ArrayList();
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   
		   while(rs.next()) {
			   ArrayList tmp = new ArrayList();
			   tmp.add(rs.getInt("repcode"));
			   tmp.add(rs.getString("repname"));
			   tmp.add(rs.getString("repdept"));
			   
			   list.add(tmp);
		   }
		   stmt.close();
		   rs.close();
		   return list;
		   
	   }
}
