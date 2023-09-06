package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import model.LoginDAO;
import model.MemlogDAO;
import model.MyPageDAO;
import model.rec.LoginVO;
import model.rec.MemlogVO;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class MyPageView extends JPanel {

	private JFrame frame;
	LoginDAO ldao;
	MemlogDAO mdao;
	MyPageDAO mpdao;
	MemlogVO mvo;
	LoginVO lvo;
	JTextField tfpw, tfaddr, tftel, tfemr, tfname, tfid, tfju;
	JButton btchinfo, btlogout;
	JComboBox cbob;
	JLabel lbid, lbju, lbmypage, lbpw, lbaddr, lbob, lbname, lbemr, lbtel;
	JTable motable, sotable;
	ShoppingListTableModel tmShop;
	SomoListTableModel tmSomo;
	ArrayList list, somoList;
	int memCode;

	/**
	 * Create the application.
	 */
	public MyPageView(int memCode, String id) {
		this.memCode = memCode;
		try {
			ldao = new LoginDAO();
			mdao = new MemlogDAO();
			mpdao = new MyPageDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		newObject();
		initialize(memCode);
		eventProc();
		mvo = new MemlogVO();
		try {
			// 회원 개인정보 출력
			mvo = mdao.info(memCode);
			// 기구 쇼핑 리스트
			list = mpdao.myShoppinglist(memCode);
			tmShop.data = list;
			motable.setModel(tmShop);
			// 소모품 쇼핑 리스트
			somoList = mpdao.mySomoList(memCode);
			tmSomo.data = somoList;
			sotable.setModel(tmSomo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		tfid.setText(mvo.getID());
		tfpw.setText(mvo.getPW());
		tfaddr.setText(mvo.getMemaddr());
		tftel.setText(mvo.getMemtel());
		tfemr.setText(mvo.getMememr());
		tfname.setText(mvo.getMemname());
		tfju.setText(mvo.getMemjuban());
		cbob.setSelectedItem(mvo.getMemobstacle());

	}

	void newObject() {
		tfpw = new JTextField();
		tfaddr = new JTextField();
		tftel = new JTextField();
		tfemr = new JTextField();
		tfname = new JTextField();
		tfid = new JTextField();
		tfju = new JTextField();
		cbob = new JComboBox();
		motable = new JTable(tmShop);
		sotable = new JTable(tmSomo);
		lbid = new JLabel("아이디");
		lbju = new JLabel("주민번호");
		btchinfo = new JButton("회원정보 수정");
		btlogout = new JButton("로그아웃");
		tmShop = new ShoppingListTableModel();
		tmSomo = new SomoListTableModel();
	}

	void initialize(int memCode) {
		setLayout(null);

		btchinfo.setLocation(105, 620);
		btchinfo.setSize(120, 30);
		add(btchinfo);
		lbmypage = new JLabel("마이페이지");
		lbmypage.setBounds(40, 30, 140, 35);
		lbmypage.setFont(new Font("HY견고딕", Font.BOLD, 24));
		lbmypage.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbmypage);
		btlogout.setBounds(706, 95, 90, 30);
		add(btlogout);
		tfpw.setBounds(172, 310, 120, 30);
		tfpw.setColumns(10);
		add(tfpw);
		tfaddr.setBounds(172, 370, 120, 30);
		tfaddr.setColumns(10);
		add(tfaddr);
		tftel.setBounds(172, 430, 120, 30);
		tftel.setColumns(10);
		add(tftel);
		tfemr.setBounds(172, 490, 120, 30);
		tfemr.setColumns(10);
		add(tfemr);
		lbpw = new JLabel("비밀번호");
		lbpw.setBounds(37, 310, 55, 30);
		add(lbpw);
		lbaddr = new JLabel("주소");
		lbaddr.setBounds(37, 370, 25, 30);
		add(lbaddr);
		lbtel = new JLabel("전화번호");
		lbtel.setBounds(37, 430, 50, 30);
		add(lbtel);
		lbemr = new JLabel("비상연락처");
		lbemr.setBounds(37, 490, 90, 30);
		add(lbemr);
		lbname = new JLabel("이름");
		lbname.setBounds(37, 250, 25, 30);
		add(lbname);
		tfname.setBounds(172, 250, 120, 30);
		tfname.setColumns(10);
		add(tfname);
		lbob = new JLabel("장애등급");
		lbob.setBounds(37, 550, 50, 30);
		add(lbob);
		lbid.setBounds(35, 130, 40, 30);
		add(lbid);
		cbob.setBounds(172, 550, 120, 30);
		cbob.setModel(new DefaultComboBoxModel(new String[] { "5급", "4급", "3급", "2급", "1급" }));
		cbob.setMaximumRowCount(5);
		add(cbob);
		lbju.setBounds(35, 190, 55, 30);
		add(lbju);
		tfid.setBounds(170, 130, 120, 30);
		tfid.setEditable(false);
		tfid.setColumns(10);
		add(tfid);
		tfju.setBounds(172, 190, 120, 30);
		tfju.setEditable(false);
		tfju.setColumns(10);
		add(tfju);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 135, 450, 200);
		add(scrollPane);
		motable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "상세주문코드", "기구명", "주문수량", "결제금액", "주문일자", "배송현황" }));
		scrollPane.setViewportView(motable);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(344, 376, 450, 200);
		add(scrollPane_1);

		sotable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "주문상세번호", "소모품명", "주문수량", "결제금액", "주문일자", "배송현황" }));
		scrollPane_1.setViewportView(sotable);
	}

	void eventProc() {
		btchinfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					while (true) {

						if (tfpw.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "바꿀 비밀번호를 입력해주세요");
							break;
						} else if (tfaddr.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "바꿀 주소를 입력해주세요");
							break;
						} else if (tftel.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, " 바꿀 연락처를 입력해주세요");
							break;
						} else if (tfemr.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "바꿀 비상연락처를 입력해주세요");
							break;
						} else if (tfname.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "바꿀 이름을 입력해주세요");
							break;
						} else {
							mvo.setPW(tfpw.getText());
							mvo.setMemaddr(tfaddr.getText());
							mvo.setMemtel(tftel.getText());
							mvo.setMememr(tfemr.getText());
							mvo.setMemname(tfname.getText());
							mvo.setMemobstacle(cbob.getSelectedItem());
							mdao.updateInfo(mvo);
							JOptionPane.showMessageDialog(null, "정보수정이 완료되었습니다.");
						}
						break;
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "정보수정이 되지 않았습니다.");
				}
			}

		});

		btlogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		// 주문한 물건 클릭하면 리뷰 등록창 뜨게
		motable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 1;
				int col2 = 0;
				int row = motable.getSelectedRow();
				String mName = String.valueOf(motable.getValueAt(row, col));
				int desc = Integer.parseInt(String.valueOf(motable.getValueAt(row, col2)));
				Review2 rv = new Review2(memCode, mName, desc);
				rv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				rv.setVisible(true);
			}
		});
	}

	class ShoppingListTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "상세주문코드", "기구명", "주문수량", "결제금액", "주문일자", "배송현황" };

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
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	class SomoListTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "상세주문코드", "소모품명", "주문수량", "결제금액", "주문일자", "배송현황" };

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
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}
}
