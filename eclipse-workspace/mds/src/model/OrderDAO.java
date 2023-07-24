package model;

import java.sql.*;
import java.util.ArrayList;

import model.rec.OrderVO;

public class OrderDAO {
	// 주문누르면 주문 상세랑 주문테이블에 insert해야하니까 두 테이블 다 필요
	int mocode, memcode, paynum, shopcode, amscode;
	String modate,mototalprice, modeliver,mototal, shopquantity;
	
	// DB연결
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pw = "soft";
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs= null;
	OrderVO vo = null;
	ArrayList list = null;
	
	public OrderDAO() throws Exception{
		// TODO Auto-generated constructor stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
	}
	
	// 기구 주문번호 생성하기
	public int mNum() throws Exception{
		String sql = "select morder_seq.nextval from dual";
		int mNum = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			mNum = rs.getInt(1);
		}
		String sql2 = "insert into machineorder (mocode)values(?)";
		ps = conn.prepareStatement(sql2);
		ps.setInt(1, mNum);
		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return mNum;
	}
	// 소모품 주문번호 생성하기
	public int aNum() throws Exception{
		String sql = "select somo_seq.nextval from dual";
		int aNum = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			aNum = rs.getInt(1);
		}
		String sql2 = "insert into somo (scode)values(?)";
		ps = conn.prepareStatement(sql2);
		ps.setInt(1, aNum);
		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return aNum;
	}
	
	//기구 주문에 insert
	public void mOrder(int memcode, int mocode, int paynum, int total, int totPrice) throws Exception{
		String totalQuantity = String.valueOf(total);
		String totalPrice = String.valueOf(totPrice);
		String sql = "update machineorder set modate = sysdate, mototalprice = ?, modeliver = 'N',"
				+"memcode = 2, paynum =?, mototal=? where mocode = "+mocode;
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, totalPrice);
		ps.setInt(2, memcode);
		ps.setInt(3, paynum);
		ps.setString(4, totalQuantity);
		
		ps.executeUpdate();
		ps.close();
	}
	
	//소모품 주문에 insert
	public void aOrder(int memcode, int ocode, int paynum, int total, int totPrice) throws Exception{
		String totalQuantity = String.valueOf(total);
		String totalPrice = String.valueOf(totPrice);
		String sql = "update somo set sodate = sysdate, sototalprice = ?, sodeliver = 'N', memcode = ?, "
				+"paynum=?, sototal=? where scode = "+ocode;
		
		ps = conn.prepareStatement(sql);		
		ps.setString(1, totalPrice);
		ps.setInt(2, memcode);
		ps.setInt(3, paynum);
		ps.setString(4, totalQuantity);
		
		ps.executeUpdate();
		ps.close();
	}
	
	//기구 주문 상세에 insert
	public void mOrderDesc(OrderVO vo) throws Exception{
		// TODO Auto-generated method stub
		String sql = "insert into shoppinglist (shopcode, shopquantity, amscode, mocode) "
				+"values (modesc_seq.nextval, ?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getShopquantity());
		ps.setInt(2, vo.getAmscode());
		ps.setInt(3, vo.getMocode());
		
		ps.executeUpdate();
		ps.close();
	}
	// 소모품 주문 상세 insert
	public void aOrderDesc(OrderVO vo) throws Exception{
		// TODO Auto-generated method stub
		String sql = "insert into smdesc (sodcode, sodquantity, scode, smcode) "
				+"values (smdesc_seq.nextval, ?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getShopquantity());
		ps.setInt(2, vo.getAmscode());
		ps.setInt(3, vo.getMocode());
		
		ps.executeUpdate();
		ps.close();		
	}
	
	// 기구 리스트 출력
	public ArrayList machineSearch() throws Exception{
		String sql = "select a.amscode, m.machinename, a.amsnation, a.ascom, a.amsprice, a.amssignificant from am_seller a, machine m where a.machinecode = m.machinecode";
		
		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			
			temp.add(rs.getInt("amscode"));
			temp.add(rs.getString("machinename"));
			temp.add(rs.getString("amsnation"));
			temp.add(rs.getString("ascom"));
			temp.add(rs.getString("amsprice"));
			temp.add(rs.getString("amssignificant"));
			
			list.add(temp);
		}
		stmt.close();
		rs.close();
		return list;
	}
	
	// 소모품 리스트 출력
	public ArrayList somoSearch() throws Exception{
		String sql = "select a.acscode, ac.acname, a.acsnation, a.acscom, a.acsprice, a.acsexpirationdate from accstore a, accesories ac where a.accode = ac.accode";

		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			
			temp.add(rs.getInt("acscode"));
			temp.add(rs.getString("acname"));
			temp.add(rs.getString("acsnation"));
			temp.add(rs.getString("acscom"));
			temp.add(rs.getString("acsprice"));
			temp.add(rs.getString("acsexpirationdate"));
			
			list.add(temp);
		}
		
		return list;
	}
}
