package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.MAccountPaymentDAO;
import model.SAccountPaymentDAO;
import model.rec.AccountPaymentVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SoAccountPay extends JDialog {
	JTextField tfAccountNum, tfOrderCode, tfBillNum, tfName, tfPrice;
	JLabel lbBankName, lbAccountNum, lbName, lbBillNum, lbOrderCode, lbPrice;
	JComboBox cbBankName;
	JPanel panel;
	JButton btnPay;

	AccountPaymentVO vo;
	SAccountPaymentDAO dao;

	private final JPanel contentPanel = new JPanel();
	// 공유할 데이터 선언
	private int orderNum, price;

	// 부모로부터 초기데이터 받을 생성자 선언
	public SoAccountPay(int orderNum, int price) {
		this();
		this.orderNum = orderNum;
		this.price = price;
		System.out.println("부모로부터 받은 초기 shareData : " + orderNum);
		
		initialize(orderNum, price);

		try {
			dao = new SAccountPaymentDAO();
			System.out.println("디비 연결 성공!");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB 연결 실패 : " + e.getMessage());
		}
	}

	// getter, setter, 완료된데이터보여줄 메서드 선언
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public void printOrderNum() {
		System.out.println("부모로부터 받은 변경 shareData : " + this.orderNum);
	}
	public SoAccountPay() {
		
	}

	void initialize(int orderNum, int price) {

		tfAccountNum = new JTextField();
		tfOrderCode = new JTextField(String.valueOf(orderNum));
		tfBillNum = new JTextField();
		tfName = new JTextField();
		tfPrice = new JTextField(String.valueOf(price));
		
		lbBankName = new JLabel("은행명");
		lbAccountNum = new JLabel("계좌번호");
		lbName = new JLabel("입금자명");
		lbBillNum = new JLabel("현금영수증번호");
		lbOrderCode = new JLabel("주문코드");
		lbPrice = new JLabel("결제금액");
		
		String[] bank = { "국민은행", "하나은행", "카카오뱅크", "신한은행", "기업은행", "우리은행", "농협" };
		cbBankName = new JComboBox(bank);
		panel = new JPanel();
		btnPay = new JButton("결제");

		tfOrderCode.setColumns(10);
		tfOrderCode.setBounds(150, 30, 135, 20);
		tfOrderCode.setEditable(false);
		tfAccountNum.setBounds(150, 90, 200, 20);
		tfAccountNum.setColumns(10);
		tfBillNum.setColumns(10);
		tfBillNum.setBounds(150, 150, 200, 20);
		tfName.setColumns(10);
		tfName.setBounds(150, 120, 100, 20);
		tfPrice.setBounds(150, 180, 100, 20);
		tfPrice.setEditable(false);
		
		lbBankName.setBounds(40, 60, 100, 20);
		lbAccountNum.setBounds(40, 90, 100, 20);
		lbName.setBounds(40, 120, 100, 20);
		lbBillNum.setBounds(40, 150, 100, 20);
		lbOrderCode.setBounds(40, 30, 100, 20);
		lbPrice.setBounds(40, 180, 100, 20);
		
		cbBankName.setBounds(150, 60, 135, 20);
		btnPay.setBounds(150, 210, 100, 25);

		setBounds(100, 100, 411, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(null);
		panel.setBounds(0, 0, 393, 253);
		getContentPane().add(panel);

		panel.add(tfBillNum);
		panel.add(tfName);
		panel.add(tfAccountNum);
		panel.add(tfOrderCode);
		panel.add(tfPrice);
		panel.add(lbBankName);
		panel.add(lbAccountNum);
		panel.add(lbName);
		panel.add(cbBankName);
		panel.add(lbBillNum);
		panel.add(lbOrderCode);
		panel.add(lbPrice);
		panel.add(btnPay);

		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == btnPay) {
					String account = tfAccountNum.getText();
					String name = tfName.getText();
					String bank = (String) cbBankName.getSelectedItem();
					String cashbill = tfBillNum.getText();
					int scode = Integer.parseInt(tfOrderCode.getText());
					vo = new AccountPaymentVO(account, name, bank, cashbill, scode);

					try {
						int cnt = dao.regist(vo);
						if (cnt > 0) {
							clearScreen();
							System.out.println("결제완료");
						} else {
							System.out.println("결제실패");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "결제 실패!! : " + e2.getMessage());
					}
				}
			}
		});
	}

	void clearScreen() {
		tfOrderCode.setText(null);
		tfAccountNum.setText(null);
		tfBillNum.setText(null);
		tfName.setText(null);
	}
}