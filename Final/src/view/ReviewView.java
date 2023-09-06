package view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ReviewDAO;
import model.rec.ReviewVO;

public class ReviewView extends JDialog{

	JTextField tfMachineName;
	JTable tbAcceptedReview;

	ReviewVO vo;
	ReviewDAO dao;
	ArrayList list;

	ReviewTableModel tmReview;
	
	String machineName;

	
	public ReviewView(String machineName) {
		this.machineName = machineName;
		getContentPane().setLayout(null);
		newObject(machineName);
		tfMachineName.setText(machineName);
		try {
			dao = new ReviewDAO();
			System.out.println("review 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB연결 실패 : " + e.getMessage());
		}
		selectTable(machineName);
	}

	void selectTable(String text) {
		// TODO Auto-generated method stub
		try {
			ArrayList list = new ArrayList();
			
			list = dao.ReviewSearch(text);
			System.out.println("뷰리스트" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "리뷰 검색 실패 : " + e.getMessage());
			
		}
	}
	
	void newObject(String machineName) {
		setSize(850,700);
		tfMachineName = new JTextField();
		tfMachineName.setEditable(false);
		tmReview = new ReviewTableModel();

		tfMachineName.setFont(new Font("굴림", Font.PLAIN, 15));
		tfMachineName.setBounds(84, 10, 210, 40);
		getContentPane().add(tfMachineName);
		tfMachineName.setColumns(10);

		JLabel lbMachineName = new JLabel("기기명");
		lbMachineName.setFont(new Font("굴림", Font.PLAIN, 15));
		lbMachineName.setBounds(12, 10, 60, 40);
		getContentPane().add(lbMachineName);

		JLabel lbRegistReview = new JLabel("등록된리뷰");
		lbRegistReview.setFont(new Font("굴림", Font.PLAIN, 15));
		lbRegistReview.setBounds(12, 76, 80, 40);
		getContentPane().add(lbRegistReview);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 480, 227);
		getContentPane().add(scrollPane);
		tbAcceptedReview = new JTable();
		tbAcceptedReview.setModel(
				new DefaultTableModel(
			new Object[][] {{null, null, null, null},{null, null, null, null},{null, null, null, null},{null, null, null, null}},
			new String[] {"리뷰코드", "내용", "평점", "제품코드"}));
		scrollPane.setViewportView(tbAcceptedReview);
		tbAcceptedReview.setFont(new Font("Gulim", Font.PLAIN, 12));
	}

}

class ReviewTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "리뷰코드", "내용", "평점", "제품코드" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}
