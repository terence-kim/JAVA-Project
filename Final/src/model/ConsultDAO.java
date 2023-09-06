package model;

import java.lang.reflect.Array;
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
	   public void consultInsert(ConsultVO vo) throws Exception {
		   String sql = "insert into interview values(in_seq.nextval, ?, ?, ?, ?)";
		   ps =conn.prepareStatement(sql);
		   
			   ps.setString(1, vo.getivDate());
			   ps.setString(2, vo.getIvcon());
			   ps.setInt(3, vo.getMemcode());
			   ps.setInt(4, vo.getRepcode());
			   
				ps.executeUpdate();

			    System.out.println("dao   "+ vo.getRepcode());
			    ps.close();	
	   }
	   
	   public void consultDelete(int ivcode, ConsultVO vo) throws Exception {
		   String sql = "delete interview where ivcode = " + ivcode;
		   stmt = conn.createStatement();
//		   ps.setInt(1, ivcode);
		   System.out.println(ivcode);
		   stmt.executeQuery(sql);
		   stmt.close();
	   }
	 
	   public ArrayList findByIvcode(int vnum) throws Exception {
		  
		   String sql = "select ivcode, ivDate, ivcon, memcode, repcode from interview"
				   		+ " where memcode = " + vnum;
		   list = new ArrayList();
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   
		   while (rs.next() ) {
			   ArrayList al = new ArrayList();
			   
			   al.add(rs.getInt("ivcode"));
			   al.add(rs.getString("ivDate"));
			   al.add(rs.getString("ivcon"));
			   al.add(rs.getInt("memcode"));
			   al.add(rs.getInt("repcode"));
			   
			   list.add(al);
		   }
		   System.out.println("다오리슽츠트트"+ list);
		   rs.close();
		   stmt.close();
		   return list;
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
	   public String findMemTel(int memCode) throws Exception{
		   String sql = "select mememr from member where memcode = " + memCode;
		   		   
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
		   String emTel = "";
		   if(rs.next()) {
			   emTel = rs.getString(1);
		   }
		   return emTel;
	   }
}
