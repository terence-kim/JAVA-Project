package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.rec.AccountPaymentVO;

public class SAccountPaymentDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pass = "soft";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	PreparedStatement ps = null;
	Statement stmt = null;

	// constructor
		public SAccountPaymentDAO() 	throws Exception {
			// TODO Auto-generated constructor stub
			connectDB();
		}
		
		void connectDB() throws Exception {
			/*
			 * 1. 드라이버를 드라이버메니저에 등록 2. 연결 객체 얻어오기
			 */
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pass);
		}
		
		
	public int regist(AccountPaymentVO vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into aacount values(aacount_seq.nextval, ?,?,?,sysdate,?,?)";
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, vo.getAccount());
		ps.setString(2, vo.getName());
		ps.setString(3, vo.getBank());
		ps.setString(4, vo.getCashbill());
		ps.setInt(5, vo.getScode());
		
		int cnt = ps.executeUpdate();
		ps.close();
		return cnt;
	}

}
