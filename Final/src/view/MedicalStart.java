package view;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.Review2Dao;

import java.awt.*;

public class MedicalStart extends JFrame{
	OrderView order;
	MyPageView myPage;
	CareView care;
	ConsultView consult;
	AsView as;
	String machine;
	Review2Dao dao;
	ReturnView rtn;
	
	public MedicalStart(int memCode, String id) {
		myPage = new MyPageView(memCode, id);
		order = new OrderView(memCode, id);
		care = new CareView(memCode, id);
		consult = new ConsultView(memCode, id);
		as = new AsView(memCode, id);		
		rtn = new ReturnView(memCode, id);
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("마이페이지", myPage);
		pane.addTab("주문", order);
		pane.addTab("돌봄", care);
		pane.addTab("상담", consult);
		pane.addTab("AS 신청", as);
		pane.addTab("수거", rtn);
		
		getContentPane().add("Center", pane);
		setSize(1200, 1000);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new MedicalStart();
//	}

}
