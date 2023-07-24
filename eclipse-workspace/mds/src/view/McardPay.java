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
import model.rec.AccountPaymentVO;
import model.rec.CardPaymentVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class McardPay extends JDialog {
	JPanel contentPane;
	JTextField tfOrderCode;
	JTextField tfCardNum;
	JPasswordField pwfCVC;
	JPasswordField pwfPassTwo;
	
	MCardPaymentDAO dao;
	CardPaymentVO vo;
	OrderView ov;
	
	private final JPanel contentPanel = new JPanel();
	//������ ������ ����
	private String shareData;
	
	//�θ�κ��� �ʱⵥ���� ���� ������ ����
	public McardPay(String shareData) {
		this();
		this.shareData = shareData;
		
		System.out.println( "�θ�κ��� ���� �ʱ� shareData : " + shareData );
	}
	
	//getter, setter, �Ϸ�ȵ����ͺ����� �޼��� ����
	public String getShareData() {
		return shareData;
	}
	public void setShareData(String shareData) {
		this.shareData = shareData;
	}
	public void printShareData() {
		System.out.println( "�θ�κ��� ���� ���� shareData : " + this.shareData );
	}


	public McardPay() {
		initialize();
		
		try {
			dao = new MCardPaymentDAO();
			System.out.println("��� ���� ����!");

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB ���� ���� : " + e.getMessage());
		}
	}
	void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbOrderCode = new JLabel("�ֹ��ڵ�");
		lbOrderCode.setBounds(35, 40, 57, 23);
		contentPane.add(lbOrderCode);

		JLabel lbCardCom = new JLabel("ī��ȸ��");
		lbCardCom.setBounds(35, 80, 57, 23);
		contentPane.add(lbCardCom);

		JLabel lbCardNum = new JLabel("ī���ȣ");
		lbCardNum.setBounds(35, 120, 57, 23);
		contentPane.add(lbCardNum);

		JLabel lbCardDate = new JLabel("\uC720\uD6A8\uAE30\uAC04 mm/yy");
		lbCardDate.setBounds(35, 160, 102, 23);
		contentPane.add(lbCardDate);

		JLabel lbCVC = new JLabel("CVC");
		lbCVC.setBounds(35, 200, 57, 23);
		contentPane.add(lbCVC);

		JLabel lbPassTwo = new JLabel("��й�ȣ��2�ڸ�");
		lbPassTwo.setBounds(35, 240, 102, 23);
		contentPane.add(lbPassTwo);

		tfOrderCode = new JTextField();
		tfOrderCode.setBounds(140, 40, 83, 23);
		contentPane.add(tfOrderCode);
		tfOrderCode.setColumns(10);

		JComboBox cbCardCom = new JComboBox();
		cbCardCom.setModel(new DefaultComboBoxModel(new String[] { "�Ｚī��", "����ī��", "��ī��", "�ϳ�ī��", "�츮ī��", "����ī��" }));
		cbCardCom.setBounds(140, 80, 83, 23);
		contentPane.add(cbCardCom);

		tfCardNum = new JTextField();
		tfCardNum.setBounds(140, 120, 160, 23);
		contentPane.add(tfCardNum);
		tfCardNum.setColumns(10);

		JComboBox cbCardDateMM = new JComboBox();
		cbCardDateMM.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cbCardDateMM.setBounds(140, 160, 44, 23);
		contentPane.add(cbCardDateMM);

		JComboBox cbCardDateYY = new JComboBox();
		cbCardDateYY.setModel(new DefaultComboBoxModel(new String[] { "2023", "2024", "2025", "2026", "2027" }));
		cbCardDateYY.setBounds(190, 160, 57, 23);
		contentPane.add(cbCardDateYY);

		pwfCVC = new JPasswordField();
		pwfCVC.setBounds(140, 200, 65, 23);
		contentPane.add(pwfCVC);

		pwfPassTwo = new JPasswordField();
		pwfPassTwo.setBounds(140, 240, 65, 23);
		contentPane.add(pwfPassTwo);

		JButton btnPay = new JButton("����");
		btnPay.setBounds(116, 285, 80, 25);
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
					vo = new CardPaymentVO(cardnum, date, cvc, bank,passtwo, num);

					try {
						int cnt = dao.regist(vo);
						if (cnt > 0) {
							clearScreen();
//							ov.clearShopTb();
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