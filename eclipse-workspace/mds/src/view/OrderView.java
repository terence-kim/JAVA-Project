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
	ReviewDAO rDao;
	ArrayList list;
	int index, totalQuantity;
	int orderNum; // �ڵ������� �ֹ� ��ȣ
	int memCode; // ȸ�� ��ȣ �޾ƿ� ����
	String id; // ȸ�� ���̵� �޾ƿ� ����

	public OrderView(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;
		
		newObject(memCode, id);
		initialize(memCode, id);
		eventProc(memCode, id);
		
		
		// �����ͺ��̽� ����
		try {
			dao = new OrderDAO();
			System.out.println("���� ����");
			System.out.println("ȸ����ȣ : "+memCode+"ȸ�� ���̵�: "+id);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���� ���� : " + e.getMessage());
			e.printStackTrace();
		}
	}

	void newObject(int memCode, String id) {
		frame = new JFrame();
		tbTotal = new JTable();
		tbTotal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"�ڵ�", "ǰ���", "������", "������", "����", "����", "Ư¡"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tbShop = new JTable();
		tbShop.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "�ⱸ��", "����", "����", "�԰��ڵ�", "�ֹ��ڵ�" }) {
			Class[] columnTypes = new Class[] { String.class, Integer.class, String.class, Integer.class,
					Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		// ���̺� ���ǳ� �ֱ�
		TableColumnModel model = tbShop.getColumnModel();
		TableColumn col = model.getColumn(1);
		col.setCellEditor(new SpinnerEditor());

		btnCard = new JButton("ī�����");
		btnAccount = new JButton("���°���");
		btnShowReview = new JButton("����Ȯ��");
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

		JLabel lblNewLabel = new JLabel("�ֹ�");
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

		cbCategory.setModel(new DefaultComboBoxModel(new String[] { "�Ҹ�ǰ", "�ⱸ" }));
		cbCategory.setBounds(12, 10, 144, 23);
		tablePane.add(cbCategory);
		
		btnShowReview.setBounds(12, 378, 97, 23);
		tablePane.add(btnShowReview);

		JPanel panel_1 = new JPanel();
		total.add(panel_1, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("��������");
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
					System.out.println("�Ҹ�ǰ ����");
					ArrayList all;
					try {
						orderNum = dao.aNum();
						all = dao.somoSearch();
						tmShop.data = all;
						tbTotal.setModel(tmShop);
						btnShowReview.setVisible(false);
						//�޺��ڽ� �����ϸ� ��ٱ��� �ʱ�ȭ
//						DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
//						model.setNumRows(0);
						clearShopTb(tbShop);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					System.out.println("�ⱸ ����");
					ArrayList all;
					try {
						orderNum = dao.mNum();
						all = dao.machineSearch();
						tmOrder.data = all;
						tbTotal.setModel(tmOrder);
						btnShowReview.setVisible(true);
						//�޺��ڽ� �����ϸ� ��ٱ��� �ʱ�ȭ
//						DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
//						model.setNumRows(0);
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
			// ī�� ���� ������ ��
			if (evt == btnCard) {
				int payMethod = 3;
				int rowCount = tbShop.getModel().getRowCount();
				int sum = 0;// �Ѽ���
				int price = 0;// �Ѱ���
				int quantity = 0; // ����

				if(rowCount > 0 ) {
					for(int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						int temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = sum*temp;											
					}
					System.out.println(quantity+" "+ price);
				}
				
				// �Ҹ�ǰ
				if (index == 0) {
					//�ٿ� �ҷ��ͼ� �ֹ��̶� �ֹ� �� �����ϱ�
					try {
						dao.aOrder(memCode, orderNum, payMethod, sum, price);
						for(int i = 0; i <rowCount; i++) {
							//���̺� �ִ°� list�� ��Ƽ� �����ϱ�
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc ,orderNum);
							dao.aOrderDesc(vo);							
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "�Ҹ�ǰ �ֹ� ���� : "+e2.getMessage());
						e2.printStackTrace();
					}
					SoCardPay sc = new SoCardPay();
					sc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sc.setModal(true);
					sc.setVisible(true);
				} else { //�ⱸ
					try {
						dao.mOrder(memCode ,orderNum, payMethod, sum, price);
						for(int i = 0; i <rowCount; i++) {
							//���̺� �ִ°� list�� ��Ƽ� �����ϱ�
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc ,orderNum);
							dao.mOrderDesc(vo);							
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "�ⱸ �ֹ� ���� : "+e2.getMessage());
					}
					McardPay mc = new McardPay();
					mc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					mc.setModal(true);
					mc.setVisible(true);
				}
				//������ü ��������
			} else if (evt == btnAccount) {
				int payMethod = 2;
				int rowCount = tbShop.getRowCount();
				int sum = 0;// �Ѽ���
				int price = 0;// �Ѱ���
				int quantity = 0;// ����
				
				if(rowCount > 0 ) {
					for(int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						int temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = sum*temp;											
					}
					System.out.println(quantity+" "+ price);
				}
				// �Ҹ�ǰ
				if (index == 0) {
					try {
						dao.aOrder(memCode ,orderNum, payMethod, sum, price);
						for(int i = 0; i <rowCount; i++) {
							//���̺� �ִ°� list�� ��Ƽ� �����ϱ�
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc ,orderNum);
							dao.aOrderDesc(vo);							
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "�Ҹ�ǰ �ֹ� ���� : "+e2.getMessage());
						e2.printStackTrace();
					}
					SoAccountPay sa = new SoAccountPay();
					sa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sa.setModal(true);
					sa.setVisible(true);
				} else { //�ⱸ
					try {
						dao.mOrder(memCode, orderNum, payMethod, sum, price);
						for(int i = 0; i <rowCount; i++) {
							//���̺� �ִ°� list�� ��Ƽ� �����ϱ�
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc ,orderNum);
							dao.mOrderDesc(vo);							
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "�ⱸ �ֹ� ���� : "+e2.getMessage());
					}
					MaccountPay ma = new MaccountPay();
					ma.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					ma.setModal(true);
					ma.setVisible(true);
				}
			}//īƮ�� �߰� 
			else if(evt == btnCartIn) { 
				System.out.println("īƮ�� �߰�~");
				int row = tbTotal.getSelectedRow();
				int code = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 0)));
				int price = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 4)));
				String name = String.valueOf(tbTotal.getValueAt(row, 1));
				
				Vector shop = new Vector();

				shop.add(name);
				shop.add(1);
				shop.add(price);
				shop.add(code);
				shop.add(orderNum);
				
				DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
				model.addRow(shop);
			}//īƮ���� ���� 
			else if(evt == btnCartOut) { 
				System.out.println("īƮ���� ����~");
				DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
				int index = tbShop.getSelectedRow();
				
				if(index < 0) {
					System.out.println("������ ���� �����ϼ���");
				}else {
					model.removeRow(index);
				}
			}//���� Ȯ��
			else if(evt == btnShowReview) {
				System.out.println("����Ȯ�ι�ư Ŭ��");
				int row = tbTotal.getSelectedRow();
				String shareData = String.valueOf(tbTotal.getValueAt(row, 1));
				ReviewView rView = new ReviewView(shareData);
				rView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				rView.setVisible(true);
			}
		}
	}
}

class OrderTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "�ڵ�", "�ⱸ��", "������", "������", "����", "Ư¡" };
	

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
	String[] columnNames = { "�ڵ�", "�Ҹ�ǰ��", "������", "������", "����",  "�������" };

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
