package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ReviewVO;

public class Review2Dao {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pass = "soft";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	PreparedStatement ps = null;
	Statement stmt = null;
	ArrayList list = null;
	ReviewVO vo = null;

	public Review2Dao() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		/*
		 * 1. 드라이버를 드라이버메니저에 등록 2. 연결 객체 얻어오기
		 */
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pass);
	}

	public int reviewEdit(ReviewVO vo) throws Exception {
		String sql = "update review set revcon = ?, revavg = ? where  revcode = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getContent());
		ps.setString(2, vo.getAvg());
		ps.setInt(3, vo.getNum());
		
		int cnt = ps.executeUpdate();
		ps.close();
		return cnt;
	}

	public int reviewRegist(ReviewVO vo) throws Exception {
		String sql = "insert into review values (review_seq.nextval, ?, ?, ?)";
		ps = conn.prepareStatement(sql);

		ps.setString(1, vo.getContent());
		ps.setString(2, vo.getAvg());
		ps.setInt(3, vo.getCode());

		int cnt = ps.executeUpdate();
		ps.close();
		return cnt;
	}

	public int reviewDelete(ReviewVO vo) throws Exception {
		String sql = "delete review where revcode = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, vo.getNum());
		
		int cnt = ps.executeUpdate();
		ps.close();
		return cnt;
	}

	public ReviewVO findByNum(int rNum) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select  shopcode, revcon, revavg from review where revcode  = " + rNum;
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ReviewVO vo = new ReviewVO();
		
//		String sql = "select r.shopcode, r.revcon, r.revavg from review r, shoppinglist s, machineorder m where r.shopcode = s.shopcode and s.mocode = m.mocode and m.memcode = "
//				+ memCode + " and revcode  = " + rNum;

		if (rs.next()) {
			vo.setCode(rs.getInt("shopcode"));
			vo.setContent(rs.getString("revcon"));
			vo.setAvg(rs.getString("revavg"));
		}
		rs.close();
		ps.close();
		return vo;
	}

	public ArrayList ReviewSearch(String text, int memcode) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select r.revcode, r.revcon, r.revavg, r.shopcode from review r, shoppinglist s, am_seller a, machine m, machineorder ma where m.machinename like '%"
								+text+"%' and m.machinecode = a.machinecode and a.amscode = s.amscode and s.mocode = ma.mocode and s.shopcode = r.shopcode and ma.memcode = "+ memcode;
		list = new ArrayList();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println(text+"이건다오");
		System.out.println(memcode + "여기도 다오");
		while (rs.next()) {
			ArrayList al = new ArrayList();

			al.add(rs.getInt("revcode"));
			al.add(rs.getString("revcon"));
			al.add(rs.getString("revavg"));
			al.add(rs.getInt("shopcode"));

			list.add(al);
		}
		System.out.println("다오리스트" + list);
		rs.close();
		stmt.close();
		return list;
	}

}
