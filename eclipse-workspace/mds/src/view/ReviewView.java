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
	// ������ ������ ����
	private String shareData;

	// �θ�κ��� �ʱⵥ���� ���� ������ ����
	public ReviewView(String shareData) {
		this();
		this.shareData = shareData;
		
//		System.out.println("�θ�κ��� ���� �ʱ� shareData : " + shareData);
		newObject();
		eventProc();
		System.out.println("���⿡ �������� ��: "+shareData);
		try {
			dao = new ReviewDAO();
			System.out.println("review ��� ���� ����");
			selectTable();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB���� ���� : " + e.getMessage());
		}
	}

	// getter, setter, �Ϸ�ȵ����ͺ����� �޼��� ����
	public String getShareData() {
		return shareData;
	}

	public void setShareData(String shareData) {
		this.shareData = shareData;
	}

	public void printShareData() {
		System.out.println("�θ�κ��� ���� ���� shareData : " + this.shareData);
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
		lbMachineName.setFont(new Font("����", Font.PLAIN, 15));
		lbMachineName.setBounds(12, 53, 60, 40);
		lbMachineName.setText("�ⱸ�� : ");
		contentPane.add(lbMachineName);
		
		JLabel lbMachine = new JLabel();
		lbMachine.setFont(new Font("����", Font.PLAIN, 15));
		lbMachine.setBounds(100, 53, 60, 40);
		lbMachine.setText(this.shareData);
		contentPane.add(lbMachine);

		JLabel lbRegistReview = new JLabel("��ϵȸ���");
		lbRegistReview.setFont(new Font("����", Font.PLAIN, 15));
		lbRegistReview.setBounds(12, 103, 80, 40);
		contentPane.add(lbRegistReview);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 153, 480, 400);
		contentPane.add(scrollPane);
		tbAcceptedReview = new JTable();
		tbAcceptedReview.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, },
						new String[] { "�����ڵ�", "����", "����", "��ǰ�ڵ�" }));
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
				System.out.println("�˻�  ����");
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
			System.out.println("�丮��Ʈ" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
//			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���� �˻� ���� : " + e.getMessage());

		}
	}


}

class ReviewTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "�����ڵ�", "����", "����", "��ǰ�ڵ�" };

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
