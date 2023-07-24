package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import model.ReviewDAO;
import model.rec.ReviewVO;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ReviewView extends JDialog implements ActionListener {
	JPanel contentPane;
	JTable tbAcceptedReview;

	ReviewVO vo;
	ReviewDAO dao;
	ArrayList list;

	ReviewTableModel tmReview;
	private final JPanel contentPanel = new JPanel();
	// 공유할 데이터 선언
	private String shareData;

	// 부모로부터 초기데이터 받을 생성자 선언
	public ReviewView(String shareData) {
		this();
		this.shareData = shareData;
		
//		System.out.println("부모로부터 받은 초기 shareData : " + shareData);
		newObject();
		eventProc();
		System.out.println("여기에 공유받은 값: "+shareData);
		try {
			dao = new ReviewDAO();
			System.out.println("review 디비 연결 성공");
			selectTable();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB연결 실패 : " + e.getMessage());
		}
	}

	// getter, setter, 완료된데이터보여줄 메서드 선언
	public String getShareData() {
		return shareData;
	}

	public void setShareData(String shareData) {
		this.shareData = shareData;
	}

	public void printShareData() {
		System.out.println("부모로부터 받은 변경 shareData : " + this.shareData);
	}

	public ReviewView() {
	}

	void newObject() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tmReview = new ReviewTableModel();

		JLabel lbMachineName = new JLabel();
		lbMachineName.setFont(new Font("굴림", Font.PLAIN, 15));
		lbMachineName.setBounds(12, 53, 60, 40);
		lbMachineName.setText("기구명 : ");
		contentPane.add(lbMachineName);
		
		JLabel lbMachine = new JLabel();
		lbMachine.setFont(new Font("굴림", Font.PLAIN, 15));
		lbMachine.setBounds(100, 53, 60, 40);
		lbMachine.setText(this.shareData);
		contentPane.add(lbMachine);

		JLabel lbRegistReview = new JLabel("등록된리뷰");
		lbRegistReview.setFont(new Font("굴림", Font.PLAIN, 15));
		lbRegistReview.setBounds(12, 103, 80, 40);
		contentPane.add(lbRegistReview);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 153, 480, 400);
		contentPane.add(scrollPane);
		tbAcceptedReview = new JTable();
		tbAcceptedReview.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, },
						new String[] { "리뷰코드", "내용", "평점", "제품코드" }));
		scrollPane.setViewportView(tbAcceptedReview);
		tbAcceptedReview.setFont(new Font("Gulim", Font.PLAIN, 12));
//				setVisible(true);
	}

	void eventProc() {

		tbAcceptedReview.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = tbAcceptedReview.getSelectedRow();
				int rNum = (Integer) tbAcceptedReview.getValueAt(row, col);
				try {
					vo = dao.findByNum(rNum);
				} catch (Exception e1) {
					// TODO: handle exception
				}
				System.out.println("검색  성공");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
	}

	void selectTable() {
		// TODO Auto-generated method stub
//		String text = tfMachineName.getText();
		try {
			ArrayList list = new ArrayList();

			list = dao.ReviewSearch(shareData);
			System.out.println("뷰리스트" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
//			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "리뷰 검색 실패 : " + e.getMessage());

		}
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
