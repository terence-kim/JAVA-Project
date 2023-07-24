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
	AccountPaymentVO vo;
	SAccountPaymentDAO dao;
	
	private JTextField tfAccountNum;
	private JTextField tfOrderCode;
	private JTextField tfBillNum;
	private JTextField tfName;
	
	private final JPanel contentPanel = new JPanel();
	//공유할 데이터 선언
	private String shareData;
	
	//부모로부터 초기데이터 받을 생성자 선언
	public SoAccountPay(String shareData) {
		this();
		this.shareData = shareData;
		
		System.out.println( "부모로부터 받은 초기 shareData : " + shareData );
	}
	
	//getter, setter, 완료된데이터보여줄 메서드 선언
	public String getShareData() {
		return shareData;
	}
	public void setShareData(String shareData) {
		this.shareData = shareData;
	}
	public void printShareData() {
		System.out.println( "부모로부터 받은 변경 shareData : " + this.shareData );
	}


	public SoAccountPay() {
		initialize();
		
		try {
			dao = new SAccountPaymentDAO();
			System.out.println("디비 연결 성공!");

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB 연결 실패 : " + e.getMessage());
		}
	}
	void initialize() {
		/*
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dialogEx.this.setShareData( "30" );
						dialogEx.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dialogEx.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		*/
		setBounds(100, 100, 411, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 393, 253);
		getContentPane().add(panel);

		JLabel lbBankName = new JLabel("은행명");
		lbBankName.setBounds(40, 60, 100, 20);
		panel.add(lbBankName);

		JLabel lbAccountNum = new JLabel("계좌번호");
		lbAccountNum.setBounds(40, 90, 100, 20);
		panel.add(lbAccountNum);

		JLabel lbName = new JLabel("입금자명");
		lbName.setBounds(40, 120, 100, 20);
		panel.add(lbName);

		String[] bank = { "국민은행", "하나은행", "카카오뱅크", "신한은행", "기업은행", "우리은행", "농협" };
		JComboBox cbBankName = new JComboBox(bank);
		cbBankName.setBounds(150, 60, 135, 20);
		panel.add(cbBankName);

		tfAccountNum = new JTextField();
		tfAccountNum.setColumns(10);
		tfAccountNum.setBounds(150, 90, 200, 20);
		panel.add(tfAccountNum);

		JLabel lbBillNum = new JLabel("현금영수증번호");
		lbBillNum.setBounds(40, 150, 100, 20);
		panel.add(lbBillNum);

		JLabel lbOrderCode = new JLabel("주문코드");
		lbOrderCode.setBounds(40, 30, 100, 20);
		panel.add(lbOrderCode);

		tfOrderCode = new JTextField();
		tfOrderCode.setColumns(10);
		tfOrderCode.setBounds(150, 30, 135, 20);
		panel.add(tfOrderCode);

		JButton btnPay = new JButton("결제");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == btnPay) {
					// String account,String name,String bank,String date,String cashbill, int scode
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
		btnPay.setBounds(150, 200, 100, 25);
		panel.add(btnPay);

		tfBillNum = new JTextField();
		tfBillNum.setColumns(10);
		tfBillNum.setBounds(150, 150, 200, 20);
		panel.add(tfBillNum);

		tfName = new JTextField();
		tfName.setBounds(150, 120, 100, 20);
		panel.add(tfName);
		tfName.setColumns(10);
	}
	void clearScreen() {
		tfOrderCode.setText(null);
		tfAccountNum.setText(null);
		tfBillNum.setText(null);
		tfName.setText(null);
	}
}