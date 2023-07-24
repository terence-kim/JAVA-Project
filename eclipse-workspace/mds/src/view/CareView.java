package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
public class CareView extends JPanel implements ActionListener {
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JTextField textField_3;
	JLabel lblcssdateLabel, lblcsldateLabel, lblmemcodeLabel, lblcgLabel, lbldescLabel, lblcscodeLabel;
	JLabel lblappLabel_1, lblmemcodeLabel_1, lblcssdateLabel_2, lblcgcodeLabel;
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
	JTextField textField_4;
	int memCode;
	String id;
	
	/**
	 * Create the panel.
	 */
	public CareView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		
		btnOkButton = new JButton("확인");
		//eventProc(); 에 쓰고 싶다면 정리를 하거라
		//정리를 안해서 니가 뷰에서 버튼 하나하나에 넣는거다
		//버스떠나고 확인누르면 되냐
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o =e.getSource();
				String csstartdate = textField.getText();
				String cslastdate = textField_1.getText();
				int memcode = Integer.parseInt(textField_3.getText());
				int cgcode = Integer.parseInt(textField_2.getText());
								
				try {
					
					vo = new CareVO(csstartdate, cslastdate, memcode, cgcode);
					dao.careInsert(vo);
					System.out.println("신청완료!");
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "신청실패 : " + e3.getMessage());
				}
			}
			
		});
		setLayout(null);
		btnOkButton.setBounds(283, 371, 57, 23);
		add(btnOkButton);
		
		JButton btnCancelButton = new JButton("취소");
		btnCancelButton.setBounds(496, 371, 57, 23);
		add(btnCancelButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"서울", "대전", "부산", "경상도", "전라도"}));
		comboBox.setBounds(12, 77, 63, 21);
		add(comboBox);
		//시작
		textField = new JTextField();
		textField.setBounds(12, 168, 116, 21);
		add(textField);
		textField.setColumns(10);
		//종료
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 224, 116, 21);
		add(textField_1);
		//간병인코드
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 280, 116, 21);
		add(textField_2);
		//회원코드
		textField_3 = new JTextField();
		textField_3.setText(String.valueOf(memCode));
		textField_3.setBounds(12, 336, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblcgLabel = new JLabel("간병인");
		lblcgLabel.setBounds(12, 255, 36, 15);
		add(lblcgLabel);
		  
		lblcssdateLabel_2 = new JLabel("시작날짜");
		lblcssdateLabel_2.setBounds(12, 143, 48, 15);
		add(lblcssdateLabel_2);
		
		lblcsldateLabel = new JLabel("종료날짜");
		lblcsldateLabel.setBounds(12, 199, 48, 15);
		add(lblcsldateLabel);
		
		lblcgcodeLabel = new JLabel("간병인코드");
		lblcgcodeLabel.setBounds(223, 127, 60, 15);
		add(lblcgcodeLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 152, 452, 205);
		add(scrollPane);
		
		cgtable = new JTable();
		cgtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(cgtable);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(223, 404, 452, 246);
		add(scrollPane_1);
		
		apptable = new JTable();
		apptable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(apptable);
		
		lblappLabel_1 = new JLabel("신청");
		lblappLabel_1.setBounds(338, 127, 24, 15);
		add(lblappLabel_1);
		
		
		lblmemcodeLabel_1 = new JLabel("회원코드");
		lblmemcodeLabel_1.setBounds(12, 311, 48, 15);
		add(lblmemcodeLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(12, 402, 116, 21);
		add(textField_4);
		
		lblcscodeLabel = new JLabel("돌봄신청코드");
		lblcscodeLabel.setBounds(12, 377, 72, 15);
		add(lblcscodeLabel);
		newObject();
		initialize();
		eventProc(memCode, id);
		
		//데이터베이스 연결
		try {
			dao = new CareDAO();
			System.out.println("연결완료");
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,  "연결못했어요 : " + e.getMessage());
		}
	}
	
	//객체 생성
	void newObject() {
		btnOkButton = new JButton();
		tmCare = new CareTableModel();
		tmgiver = new CaregiverTableModel();
	}
	
	//자리배치
	void initialize() {


	}
	// 이벤트 생성
	void eventProc(int memCode, String id) {
	
		this.memCode = memCode;
		this.id = id;
		
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(textField_4.getText());
				ArrayList all;
				try {
					all = dao.ServiceSearch(index);
					System.out.println(all);
					tmgiver.data = all;
					apptable.setModel(tmgiver);
					tmgiver.fireTableDataChanged();
				}catch (Exception ev) {
					// TODO: handle exception
					ev.printStackTrace();
					JOptionPane.showMessageDialog(null,  "연결못했어요 : " + ev.getMessage());
				}
			}
		});
		
		// click이에요 clik아니에요
		cgtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = cgtable.getSelectedRow();
				String vNum = String.valueOf(cgtable.getValueAt(row, col));
				
				textField_2.setText(vNum);
			}
		}); 
	
		
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
						JOptionPane.showMessageDialog(null,  "1콤보연결못했어요 : " + e1.getMessage());
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
						JOptionPane.showMessageDialog(null,  "2콤보연결못했어요 : " + e1.getMessage());
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
						JOptionPane.showMessageDialog(null,  "3콤보연결못했어요 : " + e1.getMessage());
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
						JOptionPane.showMessageDialog(null,  "4콤보연결못했어요 : " + e1.getMessage());
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
						JOptionPane.showMessageDialog(null,  "5콤보연결못했어요 : " + e1.getMessage());
					}
				// TODO Auto-generated method stub
				}
			}
		
		});
	}
	
	
	
	
	class CareTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "간병인코드", "간병인명", "소속회사", "연락처", "급여코드", "지역"};
		
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
			String[] columnNames = { "돌봄서비스코드", "시작일", "종료일", "회원코드", "간병인코드" , "신청날짜"};
			
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
