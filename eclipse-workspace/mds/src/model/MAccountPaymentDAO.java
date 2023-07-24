package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.rec.AccountPaymentVO;

public class MAccountPaymentDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pass = "soft";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	PreparedStatement ps = null;
	Statement stmt = null;

	// constructor
		public MAccountPaymentDAO() 	throws Exception {
			// TODO Auto-generated constructor stub
			connectDB();
		}
		
		void connectDB() throws Exception {
			/*
			 * 1. ����̹��� ����̹��޴����� ��� 2. ���� ��ü ������
			 */
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pass);
		}
		
		
	public int regist(AccountPaymentVO vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into maccount values(macc_seq.nextval, ?,?,?,sysdate,?,?)";
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
