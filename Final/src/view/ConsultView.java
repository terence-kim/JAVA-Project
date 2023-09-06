package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ConsultDAO;
import model.rec.ConsultVO;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

public class ConsultView extends JPanel implements ActionListener {

	JTextField tfRepcode, tfMemCode, tfMememr;
	JLabel lbivcon, lbivDate, lbReporter, lbRepdept, lbRepcode, lbMemCode, lbMememr;
	JComboBox cbRepdept;
	JTable tbReporter;
	JButton btnRegist, btnDelete;
	JScrollPane scrollPane, scrollPane_1;
	JTextArea taivcon;

	ConsultTableModel tmConsult;
	InterviewTableModel tmInterview;

	ConsultVO vo;
	ConsultDAO dao;
	ArrayList list;

	int memCode;
	String id, emr;
	JTable tbivList;
	JDateChooser hopeDate;
	SimpleDateFormat simpleFormat;

	public ConsultView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		setLayout(null);
		newObject(memCode, id);
		eventProc(memCode, id);
		initialize();


		tfMemCode.setText(String.valueOf(memCode));

		// 데이터베이스 연결
		try {
			dao = new ConsultDAO();
			emr = dao.findMemTel(memCode);
			tfMememr.setText(emr);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결실패했어요 : " + e.getMessage());
		}
		selectTable();
	}

	// 객체생성
	void newObject(int memCode, String id) {
		tmConsult = new ConsultTableModel();
		tmInterview = new InterviewTableModel();
		tfMemCode = new JTextField();
		tfMememr = new JTextField();
		tfRepcode = new JTextField();
		cbRepdept = new JComboBox();
		tbReporter = new JTable();
		tbivList = new JTable();
		scrollPane = new JScrollPane();
		scrollPane_1 = new JScrollPane();
		taivcon = new JTextArea();
		lbMemCode = new JLabel("회원코드");
		lbMememr = new JLabel("보호자 연락망");
		lbivDate = new JLabel("희망날짜");
		lbReporter = new JLabel("상담원");
		lbRepcode = new JLabel("상담원코드");
		lbivcon = new JLabel("상담 내용");
		lbRepdept = new JLabel("상담분야");
		btnRegist = new JButton("등록");
		btnDelete = new JButton("취소");
		hopeDate = new JDateChooser();
		simpleFormat = new SimpleDateFormat("yy/MM/dd");
	}

	// 자리배치
	void initialize() {

		tfMemCode.setEditable(false);
		tfMemCode.setColumns(10);
		tfMemCode.setBounds(136, 40, 120, 30);
		tfMememr.setEditable(false);
		tfMememr.setColumns(10);
		tfMememr.setBounds(136, 80, 120, 30);
		tfRepcode.setEditable(false);
		tfRepcode.setBounds(136, 160, 120, 30);
		tfRepcode.setColumns(10);
		lbivcon.setBounds(23, 426, 80, 30);
		lbivDate.setBounds(23, 120, 90, 30);
		lbReporter.setBounds(23, 252, 80, 30);
		lbRepdept.setBounds(23, 200, 80, 30);
		lbRepcode.setBounds(23, 160, 90, 30);
		lbMemCode.setBounds(23, 40, 90, 30);
		lbMememr.setBounds(23, 80, 90, 30);
		tbReporter.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "상담원코드", "상담원이름", "담당부서" }));
		tbivList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "상담코드", "상담 희망 날짜", "상담 내용", "회원코드", "상담원 코드" }));
		scrollPane.setBounds(23, 292, 452, 110);
		scrollPane.setViewportView(tbReporter);
		scrollPane_1.setBounds(23, 643, 452, 150);
		scrollPane_1.setColumnHeaderView(tbivList);
		taivcon.setBounds(23, 466, 452, 110);
		cbRepdept.setModel(new DefaultComboBoxModel(new String[] { "A/S", "수거", "구매", "돌봄" }));
		cbRepdept.setBounds(136, 200, 120, 30);
		btnRegist.setBounds(395, 586, 80, 30);
		btnDelete.setBounds(395, 803, 80, 30);
		hopeDate.setBounds(136, 120, 120, 30);

		add(tfMemCode);
		add(tfMememr);
		add(tfRepcode);
		add(taivcon);
		add(scrollPane);
		add(scrollPane_1);
		add(lbMemCode);
		add(lbMememr);
		add(lbivDate);
		add(lbivcon);
		add(lbReporter);
		add(lbRepdept);
		add(lbRepcode);
		add(cbRepdept);
		add(btnRegist);
		add(btnDelete);
		add(hopeDate);
	}

	// 이벤트 생성
	public void eventProc(int memCode, String id) {
		
		btnRegist.addActionListener(this);
		btnDelete.addActionListener(this);
		this.memCode = memCode;
		this.id = id;
		tbReporter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = tbReporter.getSelectedRow();
				String vNum = String.valueOf(tbReporter.getValueAt(row, col));
				tfRepcode.setText(vNum);
			}
		});

		
		cbRepdept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = (int)cbRepdept.getSelectedIndex();
				if (index == 0) {
					System.out.println("A/S선택");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "1콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 1) {
					System.out.println("수거선택");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "2콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 2) {
					System.out.println("구매선택");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "3콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 3) {
					System.out.println("돌봄선택");
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "4콤보연결못했어요 : " + e1.getMessage());
					}
				}
			}
		});
	}
	
	private void selectTable() {
		try {
			ArrayList list = new ArrayList();
			list = dao.findByIvcode(memCode);
			tmInterview.data1 = list;
			tbivList.setModel(tmInterview);
			tmInterview.fireTableDataChanged();
			System.out.println(list);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	class ConsultTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "상담원코드", "상담원이름", "담당부서" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	class InterviewTableModel extends AbstractTableModel {
		ArrayList data1 = new ArrayList();
		String[] columnNames1 = { "상담코드", "상담 희망 날짜", "상담 내용", "회원코드", "상담원 코드" };

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames1.length;
		}
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data1.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			ArrayList temp1 = (ArrayList) data1.get(rowIndex);
			return temp1.get(columnIndex);
		}
		public String getColumnName(int col) {
			return columnNames1[col];
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnRegist) {
			Date date = hopeDate.getDate();
			
			String ivDate = simpleFormat.format(date);
			String ivcon = taivcon.getText();
			int memcode = Integer.parseInt(tfMemCode.getText());
			int repcode = Integer.parseInt(tfRepcode.getText());
			ConsultVO vo = new ConsultVO(ivDate, ivcon, memcode, repcode);
			System.out.println(ivDate + ivcon + memcode + repcode);
			try {
				dao.consultInsert(vo);
				System.out.println("등록 완료");
				selectTable();
			} catch (Exception e1) {
				// TODO: handle exception
			}
		} else if (o == btnDelete) {
			int row = tbivList.getSelectedRow();
			int rNum = (Integer)tbivList.getValueAt(row, 0);
			String ivDate = (String) tbivList.getValueAt(row, 1);
			String ivcon = (String)tbivList.getValueAt(row, 2);
			int memcode = (Integer)tbivList.getValueAt(row, 3);
			int repcode = (Integer)tbivList.getValueAt(row, 4);
			ConsultVO vo = new ConsultVO(ivDate, ivcon, memcode, repcode);
			try {
				dao.consultDelete(rNum, vo);
				selectTable();
			}catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
