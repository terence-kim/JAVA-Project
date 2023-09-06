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

import model.CareDAO;
import model.rec.CareVO;
import view.CareView.CareTableModel;
import view.CareView.CaregiverTableModel;
import com.toedter.calendar.JDateChooser;

public class CareView extends JPanel implements ActionListener {
	JTextField textField_2;
	JTextField textField_3;
	JLabel lblcssdateLabel, lblcsldateLabel, lblmemcodeLabel, lblcgLabel, lbldescLabel;
	JLabel lblmemcodeLabel_1, lblcssdateLabel_2, lblcgcodeLabel;
	JButton btnOkButton, btnCancel;
	JComboBox comboBox;
	JScrollPane SPdesc, scrollPane, scrollPane_1;
	CareTableModel tmCare;
	CaregiverTableModel tmgiver;
	CareVO vo;
	CareDAO dao;
	ArrayList list;
	JTable cgtable;
	JTable apptable;
	int memCode;
	String id;
	JDateChooser startDate, endDate;
	SimpleDateFormat simpleFormat;

	/**
	 * Create the panel.
	 */
	public CareView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;

		// eventProc(); 에 쓰고 싶다면 정리를 하거라
		// 정리를 안해서 니가 뷰에서 버튼 하나하나에 넣는거다
		// 버스떠나고 확인누르면 되냐

		newObject();
		initialize();
		eventProc(memCode, id);
		

		// 데이터베이스 연결
		try {
			dao = new CareDAO();
			System.out.println("연결완료");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결못했어요 : " + e.getMessage());
		}
		System.out.println("본인 예약 리스트"+memCode);
		showBookList(memCode);
	}
	
	// 본인 예약 리스트 띄우기
	void showBookList(int memCode) {
		try {
			list = dao.findByCscode(memCode);
			tmgiver.data = list;
			apptable.setModel(tmgiver);
			tmgiver.fireTableDataChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 객체 생성
	void newObject() {
		btnOkButton = new JButton("신청");
		btnCancel = new JButton("취소");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "서울", "대전", "부산", "경상도", "전라도" }));
		// 간병인코드
		textField_2 = new JTextField();
		// 회원코드
		textField_3 = new JTextField();
		lblcgLabel = new JLabel("간병인");
		lblcssdateLabel_2 = new JLabel("시작날짜");
		lblcsldateLabel = new JLabel("종료날짜");
		lblcgcodeLabel = new JLabel("간병인코드");
		tmCare = new CareTableModel();
		tmgiver = new CaregiverTableModel();
		scrollPane = new JScrollPane();
		cgtable = new JTable();
		cgtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "간병인코드", "간병인명", "소속회사", "연락처", "급여코드", "지역"}));
		scrollPane.setViewportView(cgtable);
		scrollPane_1 = new JScrollPane();
		apptable = new JTable();
		apptable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "돌봄서비스코드", "시작일", "종료일", "회원코드", "간병인코드", "신청날짜" }));
		scrollPane_1.setViewportView(apptable);
		lblmemcodeLabel_1 = new JLabel("회원코드");
		startDate = new JDateChooser();
		endDate = new JDateChooser();
		simpleFormat = new SimpleDateFormat("yy/MM/dd");
	}

	// 자리배치
	void initialize() {
		setLayout(null);
		btnOkButton.setBounds(12, 369, 91, 23);
		add(btnOkButton);
		btnCancel.setBounds(663, 655, 91, 23);
		add(btnCancel);
		comboBox.setBounds(12, 77, 63, 21);
		add(comboBox);
		textField_2.setColumns(10);
		textField_2.setBounds(12, 280, 116, 21);
		add(textField_2);
		textField_3.setText(String.valueOf(memCode));
		textField_3.setBounds(12, 336, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);
		lblcgLabel.setBounds(223, 127, 86, 15);
		add(lblcgLabel);
		lblcssdateLabel_2.setBounds(12, 143, 86, 15);
		add(lblcssdateLabel_2);
		lblcsldateLabel.setBounds(12, 199, 100, 15);
		add(lblcsldateLabel);
		lblcgcodeLabel.setBounds(12, 255, 100, 15);
		add(lblcgcodeLabel);
		scrollPane.setBounds(223, 152, 531, 205);
		add(scrollPane);
		scrollPane_1.setBounds(223, 404, 531, 246);
		add(scrollPane_1);

		lblmemcodeLabel_1.setBounds(12, 311, 100, 15);
		add(lblmemcodeLabel_1);
		
		startDate.setBounds(12, 168, 116, 21);
		add(startDate);
		endDate.setBounds(12, 224, 116, 21);
		add(endDate);
	}

	// 이벤트 생성
	void eventProc(int memCode, String id) {

		this.memCode = memCode;
		this.id = id;
		
		// 서비스 신청
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date start = startDate.getDate();
				Date end = endDate.getDate();
				
				Object o = e.getSource();
				String csstartdate = simpleFormat.format(start);
				String cslastdate = simpleFormat.format(end);
				int memcode = Integer.parseInt(textField_3.getText());
				int cgcode = Integer.parseInt(textField_2.getText());

				System.out.println(csstartdate + " " + cslastdate);

				try {
					vo = new CareVO(csstartdate, cslastdate, memcode, cgcode);
					dao.careInsert(vo);
					JOptionPane.showMessageDialog(null, "신청 완료");
					showBookList(memCode);
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "신청실패 : " + e3.getMessage());
				}
			}

		});
		// 선택한 서비스 조기종료
		// 종료일 sysdate로 update
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int col = 0;
				int row = apptable.getSelectedRow();
				int sCode = (Integer)apptable.getValueAt(row, col);
				try {
					dao.CareModify(sCode);
					JOptionPane.showMessageDialog(null, "오늘부로 서비스가 종료됩니다.");
					showBookList(memCode);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		// click이에요 clik아니에요
		// 테이블에서 선택한 간병인 코드 띄우기
		cgtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = cgtable.getSelectedRow();
				String vNum = String.valueOf(cgtable.getValueAt(row, col));

				textField_2.setText(vNum);
			}
		});

		// 지역별 간병인 정보 띄우기
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					System.out.println("서울선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "1콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 1) {
					System.out.println("대전선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "2콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 2) {
					System.out.println("부산선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "3콤보연결못했어요 : " + e1.getMessage());
					}

				} else if (index == 3) {
					System.out.println("경상도선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "4콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 4) {
					System.out.println("전라도선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "5콤보연결못했어요 : " + e1.getMessage());
					}
					// TODO Auto-generated method stub
				}
			}

		});
	}

	class CareTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "간병인코드", "간병인명", "소속회사", "연락처", "급여코드", "지역" };

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

	class CaregiverTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "돌봄서비스코드", "시작일", "종료일", "회원코드", "간병인코드", "신청날짜" };

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
