package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.OrderDAO;
import model.ReviewDAO;
import model.rec.OrderVO;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

public class OrderView extends JPanel {

	JFrame frame;
	JPanel total;
	JTable tbTotal, tbShop;
	JButton btnCartIn, btnCartOut, btnCard, btnAccount, btnShowReview;
	JComboBox cbCategory;
	OrderTableModel tmOrder;
	ShopTableModel tmShop;
	OrderVO vo;
	OrderDAO dao;
//	ReviewDAO rDao;
	ArrayList list;
	int index, totalQuantity;
	int orderNum; // 자동생성된 주문 번호
	int memCode; // 회원 번호 받아올 변수
	String id; // 회원 아이디 받아올 변수

	public OrderView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;

		newObject(memCode, id);
		initialize(memCode, id);
		eventProc(memCode, id);

		// 데이터베이스 연결
		try {
			dao = new OrderDAO();
			System.out.println("연결 성공");
			System.out.println("회원번호 : " + memCode + "회원 아이디: " + id);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	void newObject(int memCode, String id) {
		frame = new JFrame();
		tbTotal = new JTable();
		tbTotal.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "코드", "품목명", "제조국", "제조사", "가격", "수량", "특징" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class,
							String.class, Integer.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		tbShop = new JTable();
		tbShop.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "기구명", "수량", "가격", "입고코드", "주문코드" }) {
			Class[] columnTypes = new Class[] { String.class, Integer.class, String.class, Integer.class,
					Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		// 테이블에 스피너 넣기
		TableColumnModel model = tbShop.getColumnModel();
		TableColumn col = model.getColumn(1);
		col.setCellEditor(new SpinnerEditor());

		btnCard = new JButton("카드결제");
		btnAccount = new JButton("계좌결제");
		btnShowReview = new JButton("리뷰확인");
		btnCartIn = new JButton(">");
		btnCartOut = new JButton("<");
		cbCategory = new JComboBox();
		tmOrder = new OrderTableModel();
		tmShop = new ShopTableModel();
	}

	public class SpinnerEditor extends DefaultCellEditor {
		JSpinner sp;
		DefaultEditor defaultEdit;
		JTextField text;

		public SpinnerEditor() {
			super(new JTextField());
			sp = new JSpinner();
			defaultEdit = ((DefaultEditor) sp.getEditor());
			text = defaultEdit.getTextField();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			sp.setValue(value);
			return sp;
		}

		public Object getCellEditorValue() {
			return sp.getValue();
		}
	}

	void initialize(int memCode, String id) {
		total = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		total.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("주문");
		panel.add(lblNewLabel);

		JPanel tablePane = new JPanel();
		total.add(tablePane, BorderLayout.CENTER);
		tablePane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(12, 104, 450, 260);
		tablePane.add(scrollPane);

		scrollPane.setViewportView(tbTotal);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(549, 104, 450, 260);
		tablePane.add(scrollPane_1);

		scrollPane_1.setViewportView(tbShop);

		btnCartIn.setBounds(477, 174, 57, 23);
		tablePane.add(btnCartIn);

		btnCartOut.setBounds(477, 246, 57, 23);
		tablePane.add(btnCartOut);

		cbCategory.setModel(new DefaultComboBoxModel(new String[] { "소모품", "기구" }));
		cbCategory.setBounds(12, 10, 144, 23);
		tablePane.add(cbCategory);

		btnShowReview.setBounds(12, 378, 97, 23);
		tablePane.add(btnShowReview);

		JPanel panel_1 = new JPanel();
		total.add(panel_1, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("결제수단");
		panel_1.add(lblNewLabel_1);

		panel_1.add(btnAccount);
		panel_1.add(btnCard);

		setLayout(new BorderLayout());
		add("Center", total);
	}

	void eventProc(int memCode, String id) {
		BtnEvent evt = new BtnEvent();

		btnAccount.addActionListener(evt);
		btnCard.addActionListener(evt);
		btnCartIn.addActionListener(evt);
		btnCartOut.addActionListener(evt);
		btnShowReview.addActionListener(evt);
		cbCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index = cbCategory.getSelectedIndex();
				if (index == 0) {
					System.out.println("소모품 선택");
					ArrayList all;
					try {
						orderNum = dao.aNum();
						all = dao.somoSearch();
						tmShop.data = all;
						tbTotal.setModel(tmShop);
						btnShowReview.setVisible(false);
						// 콤보박스 변경하면 장바구니 초기화
//						DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
//						model.setNumRows(0);
						clearShopTb(tbShop);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					System.out.println("기구 선택");
					ArrayList all;
					try {
						orderNum = dao.mNum();
						all = dao.machineSearch();
						tmOrder.data = all;
						tbTotal.setModel(tmOrder);
						btnShowReview.setVisible(true);
						// 콤보박스 변경하면 장바구니 초기화
						clearShopTb(tbShop);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	void clearShopTb(JTable tbShops) {
		DefaultTableModel model = (DefaultTableModel) tbShops.getModel();
		model.setNumRows(0);
	}

	class BtnEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			// 카드 결제 눌렀을 때
			if (evt == btnCard) {
				int payMethod = 3;
				int rowCount = tbShop.getModel().getRowCount();
				int sum = 0;// 총수량
				int price = 0;// 총가격
				int quantity = 0; // 수량

				if (rowCount > 0) {
					for (int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						int temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = sum * temp;
					}
					System.out.println(quantity + " " + price);
				}

				// 소모품
				if (index == 0) {
					// 다오 불러와서 주문이랑 주문 상세 생성하기
					try {
						dao.aOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.aOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "소모품 주문 실패 : " + e2.getMessage());
						e2.printStackTrace();
					}
					SoCardPay sc = new SoCardPay(orderNum, price);
					sc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sc.setModal(true);
					sc.setVisible(true);
				} else { // 기구
					try {
						dao.mOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.mOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "기구 주문 실패 : " + e2.getMessage());
					}
					McardPay mc = new McardPay(orderNum,price);
					mc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					mc.setModal(true);
					mc.setVisible(true);
				}
				// 계좌이체 눌렀을때
			} else if (evt == btnAccount) {
				int payMethod = 2;
				int rowCount = tbShop.getRowCount();
				int sum = 0;// 총수량
				int price = 0;// 총가격
				int quantity = 0;// 수량

				if (rowCount > 0) {
					for (int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						int temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = sum * temp;
					}
					System.out.println(quantity + " " + price);
				}
				// 소모품
				if (index == 0) {
					try {
						dao.aOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.aOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "소모품 주문 실패 : " + e2.getMessage());
						e2.printStackTrace();
					}
					SoAccountPay sa = new SoAccountPay(orderNum,price);
					sa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sa.setModal(true);
					sa.setVisible(true);
				} else { // 기구
					try {
						dao.mOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.mOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "기구 주문 실패 : " + e2.getMessage());
					}
					MaccountPay ma = new MaccountPay(orderNum, price);
					ma.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					ma.setModal(true);
					ma.setVisible(true);
				}
			} // 카트에 추가
			else if (evt == btnCartIn) {

				int row = tbTotal.getSelectedRow();
				int code = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 0)));
				int price = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 4)));
				String name = String.valueOf(tbTotal.getValueAt(row, 1));
				int rowCount = tbShop.getRowCount();
				
				int sameCnt = 0;
				
				if (rowCount <= 0) {
					CartIn(name, price, code);
				}else {
					for (int i = 0; i < rowCount; i++) {
						int shopCode = Integer.parseInt(String.valueOf(tbShop.getValueAt(i, 3)));
						if (code == shopCode) {
							sameCnt+=1;
						}
					}
					if(sameCnt == 1) {
						JOptionPane.showMessageDialog(null, "이미 추가한 내역입니다.");
						sameCnt=0;
					}else {
						CartIn(name, price, code);
					}
				}

			} // 카트에서 삭제
			else if (evt == btnCartOut) {
				CartOut();
				
			} // 리뷰 확인
			else if (evt == btnShowReview) {
				int row = tbTotal.getSelectedRow();
				String shareData = String.valueOf(tbTotal.getValueAt(row, 1));

				ReviewView rView = new ReviewView(shareData);
				rView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				rView.setVisible(true);
			}
		}
	}

	void CartIn(String name, int price, int code) {
		Vector shop = new Vector();

		shop.add(name);
		shop.add(1);
		shop.add(price);
		shop.add(code);
		shop.add(orderNum);

		DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
		model.addRow(shop);
		System.out.println("카트에 추가~");
	}
	void CartOut() {
		DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
		int index = tbShop.getSelectedRow();

		if (index < 0) {
			System.out.println("삭제할 행을 선택하세요");
		} else {
			model.removeRow(index);
		}
	}
}

class OrderTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "코드", "기구명", "제조국", "제조사", "가격", "특징" };

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

class ShopTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "코드", "소모품명", "제조국", "제조사", "가격", "유통기한" };

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
