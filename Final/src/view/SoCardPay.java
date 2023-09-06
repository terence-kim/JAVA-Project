package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.MAccountPaymentDAO;
import model.MCardPaymentDAO;
import model.SCardPaymentDAO;
import model.rec.AccountPaymentVO;
import model.rec.CardPaymentVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SoCardPay extends JDialog {
	JPanel contentPane;
	JTextField tfOrderCode, tfCardNum, tfPrice;
	JPasswordField pwfCVC, pwfPassTwo;
	JLabel lbOrderCode, lbCardCom, lbCardNum, lbCardDate, lbCVC, lbPassTwo, lbPrice;
	JComboBox cbCardCom, cbCardDateMM, cbCardDateYY;
	JButton btnPay;

	SCardPaymentDAO dao;
	CardPaymentVO vo;

	private final JPanel contentPanel = new JPanel();
	// ������ ������ ����
	private int orderNum, price;

	// �θ�κ��� �ʱⵥ���� ���� ������ ����
	public SoCardPay(int orderNum, int price) {
		this();
		this.orderNum = orderNum;
		this.price = price;

		System.out.println("�θ�κ��� ���� �ʱ� shareData : " + orderNum);

		initialize(orderNum, price);

		try {
			dao = new SCardPaymentDAO();
			System.out.println("��� ���� ����!");

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB ���� ���� : " + e.getMessage());
		}
	}

	// getter, setter, �Ϸ�ȵ����ͺ����� �޼��� ����
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public void printOrderNum() {
		System.out.println("�θ�κ��� ���� ���� shareData : " + this.orderNum);
	}

	public SoCardPay() {
	}

	void initialize(int orderNum, int price) {

		tfOrderCode = new JTextField(String.valueOf(orderNum));
		tfCardNum = new JTextField();
		tfPrice = new JTextField(String.valueOf(price));
		lbOrderCode = new JLabel("�ֹ��ڵ�");
		lbCardCom = new JLabel("ī��ȸ��");
		lbCardNum = new JLabel("ī���ȣ");
		lbCardDate = new JLabel("��ȿ�Ⱓ mm/yy");
		lbCVC = new JLabel("CVC");
		lbPassTwo = new JLabel("��й�ȣ��2�ڸ�");
		lbPrice = new JLabel("���� �ݾ�");
		btnPay = new JButton("����");
		
		cbCardCom = new JComboBox();
		cbCardDateMM = new JComboBox();
		cbCardDateYY = new JComboBox();
		pwfCVC = new JPasswordField();
		pwfPassTwo = new JPasswordField();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfOrderCode.setBounds(140, 40, 83, 23);
		contentPane.add(tfOrderCode);
		tfOrderCode.setColumns(10);
		tfOrderCode.setEditable(false);
		tfCardNum.setBounds(140, 120, 160, 23);
		contentPane.add(tfCardNum);
		tfCardNum.setColumns(10);
		tfPrice.setBounds(140,280,160,20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		tfPrice.setEditable(false);

		lbOrderCode.setBounds(35, 40, 57, 23);
		contentPane.add(lbOrderCode);
		lbCardCom.setBounds(35, 80, 57, 23);
		contentPane.add(lbCardCom);
		lbCardNum.setBounds(35, 120, 57, 23);
		contentPane.add(lbCardNum);
		lbCardDate.setBounds(35, 160, 102, 23);
		contentPane.add(lbCardDate);
		lbCVC.setBounds(35, 200, 57, 23);
		contentPane.add(lbCVC);
		lbPassTwo.setBounds(35, 240, 102, 23);
		contentPane.add(lbPassTwo);
		lbPrice.setBounds(35, 280,102,23);
		contentPane.add(lbPrice);

		pwfCVC.setBounds(140, 200, 65, 23);
		contentPane.add(pwfCVC);
		pwfPassTwo.setBounds(140, 240, 65, 23);
		contentPane.add(pwfPassTwo);

		cbCardCom.setModel(new DefaultComboBoxModel(new String[] { "�Ｚī��", "����ī��", "��ī��", "�ϳ�ī��", "�츮ī��", "����ī��" }));
		cbCardCom.setBounds(140, 80, 83, 23);
		contentPane.add(cbCardCom);
		cbCardDateMM.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cbCardDateMM.setBounds(140, 160, 44, 23);
		contentPane.add(cbCardDateMM);
		cbCardDateYY.setModel(new DefaultComboBoxModel(new String[] { "2023", "2024", "2025", "2026", "2027" }));
		cbCardDateYY.setBounds(190, 160, 57, 23);
		contentPane.add(cbCardDateYY);

		btnPay.setBounds(116, 320, 80, 25);
		contentPane.add(btnPay);

		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == btnPay) {
					int num = Integer.parseInt(tfOrderCode.getText());
					String bank = (String) cbCardCom.getSelectedItem();
					String cardnum = tfCardNum.getText();
					String date = ((String) cbCardDateMM.getSelectedItem() + "/"
							+ (String) cbCardDateYY.getSelectedItem());
					char[] cvce = pwfCVC.getPassword();
					char[] passtwoe = pwfPassTwo.getPassword();
					String cvc = "";
					String passtwo = "";
					for (char cha : cvce) {
						Character.toString(cha);
						cvc += (cvc.equals("")) ? "" + cha + "" : "" + cha + "";
					}
					for (char cha : passtwoe) {
						Character.toString(cha);
						passtwo += (passtwo.equals("")) ? "" + cha + "" : "" + cha + "";
					}
					vo = new CardPaymentVO(cardnum, date, cvc, bank, passtwo, num);

					try {
						int cnt = dao.regist(vo);
						if (cnt > 0) {
							clearScreen();
							System.out.println("���� �Ϸ�");
						} else {
							System.out.println("���� ����");
						}
					} catch (Exception e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "���� ����!! : " + e1.getMessage());
					}
				}
			}
		});
	}

	void clearScreen() {
		tfCardNum.setText(null);
		tfOrderCode.setText(null);
		pwfCVC.setText(null);
		pwfPassTwo.setText(null);
	}
}