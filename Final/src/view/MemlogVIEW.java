package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import model.MemlogDAO;
import model.rec.LoginVO;
import model.rec.MemlogVO;

public class MemlogVIEW extends JFrame implements ActionListener {

	JFrame frame;
	JButton btdouid;
	LoginVIEW lv;
	MemlogDAO mdao;
	LoginVO vo;
	JTextField tfid;
	private JTextField tftel;
	private JTextField tfemr;
	private JTextField tfaddr;
	private JTextField tfname;
	JButton btmemlog;
	JButton btcancel;
	JComboBox cbob;
	private JPasswordField pfpw;
	private JPasswordField pfju;
	JButton btdoupw;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemlogVIEW window = new MemlogVIEW();
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MemlogVIEW() {
		getContentPane().setLayout(null);

		JLabel lbmemlog = new JLabel("\uD68C\uC6D0\uAC00\uC785\uCC3D");
		lbmemlog.setFont(new Font("HY견고딕", Font.BOLD, 20));
		lbmemlog.setHorizontalAlignment(SwingConstants.CENTER);
		lbmemlog.setBounds(160, 21, 182, 53);
		getContentPane().add(lbmemlog);

		JLabel lbid = new JLabel("\uC544\uC774\uB514");
		lbid.setBounds(12, 110, 90, 25);
		getContentPane().add(lbid);

		JLabel lbpw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lbpw.setBounds(12, 170, 90, 25);
		getContentPane().add(lbpw);

		JLabel lbtel = new JLabel("\uC804\uD654\uBC88\uD638");
		lbtel.setBounds(12, 290, 90, 25);
		getContentPane().add(lbtel);

		JLabel lbemr = new JLabel("\uBE44\uC0C1\uC5F0\uB77D\uCC98");
		lbemr.setBounds(12, 350, 90, 25);
		getContentPane().add(lbemr);

		JLabel lbaddr = new JLabel("\uC8FC\uC18C");
		lbaddr.setBounds(12, 410, 90, 25);
		getContentPane().add(lbaddr);

		JLabel lbname = new JLabel("\uC774\uB984");
		lbname.setBounds(12, 470, 90, 25);
		getContentPane().add(lbname);

		JLabel lbju = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lbju.setBounds(12, 530, 90, 25);
		getContentPane().add(lbju);

		JLabel lbob = new JLabel("\uC7A5\uC560\uB4F1\uAE09");
		lbob.setBounds(12, 230, 90, 25);
		getContentPane().add(lbob);

		btdouid = new JButton("\uC911\uBCF5\uD655\uC778");
		btdouid.setBounds(379, 110, 110, 25);
		getContentPane().add(btdouid);

		btmemlog = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btmemlog.setBounds(80, 780, 110, 25);
		getContentPane().add(btmemlog);

		btcancel = new JButton("\uCDE8\uC18C");
		btcancel.setBounds(320, 780, 110, 25);
		getContentPane().add(btcancel);

		tfid = new JTextField();
		tfid.setColumns(10);
		tfid.setBounds(160, 110, 182, 25);
		getContentPane().add(tfid);

		tftel = new JTextField();
		tftel.setColumns(10);
		tftel.setBounds(160, 290, 182, 25);
		getContentPane().add(tftel);

		tfemr = new JTextField();
		tfemr.setColumns(10);
		tfemr.setBounds(160, 350, 182, 25);
		getContentPane().add(tfemr);

		tfaddr = new JTextField();
		tfaddr.setColumns(10);
		tfaddr.setBounds(160, 410, 182, 25);
		getContentPane().add(tfaddr);

		tfname = new JTextField();
		tfname.setColumns(10);
		tfname.setBounds(160, 470, 182, 25);
		getContentPane().add(tfname);
		
		pfpw = new JPasswordField();
		pfpw.setBounds(160, 170, 182, 25);
		getContentPane().add(pfpw);
		
		pfju = new JPasswordField();
		pfju.setBounds(160, 530, 182, 25);
		getContentPane().add(pfju);
		
		cbob = new JComboBox();
		cbob.setModel(new DefaultComboBoxModel(new String[] {"5\uAE09", "4\uAE09", "3\uAE09", "2\uAE09", "1\uAE09"}));
		cbob.setMaximumRowCount(5);
		cbob.setBounds(160, 231, 182, 23);
		getContentPane().add(cbob);
		
		btdoupw = new JButton("\uC911\uBCF5\uD655\uC778");
		btdoupw.setBounds(379, 171, 110, 25);
		getContentPane().add(btdoupw);

		try {
			mdao = new MemlogDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		initialize();
//		frame.setVisible(true);
		setSize(550, 1000);
		setTitle("회원가입");
	}

	private void initialize() {
		// TODO Auto-generated method stub
		// 아이디 중복확인 및 자리수 버튼 이벤트
		btdouid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();

				try {
					String chid = mdao.checkid(id);
					if (tfid.getText().length() < 2) {
						JOptionPane.showMessageDialog(null, "최소 2글자 이상아이디를 입력해주세요!");
					} else {
						if (chid == null) {
							JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "이미 사용된 아이디입니다.");
							tfid.setText("");
						}
					}
				} catch (Exception e1) {
				}
			}
		});
		
		// 비밀번호 중복확인 및 자리수 버튼 이벤트
		btdoupw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		String pw = pfpw.getText();
		try {
			String chpw = mdao.checkpw(pw);
			if (pfpw.getText().length() < 2) {
				JOptionPane.showMessageDialog(null, "최소 2글자 이상비밀번호를 입력해주세요!");
			} else {
				if (chpw == null) {
					JOptionPane.showMessageDialog(null, "사용 가능한 비밀번호입니다.");
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용된 비밀번호입니다.");
					pfpw.setText("");
				}
			}
		} catch (Exception e1) {
		}
	}
});
		//회원가입 버튼 이벤트
		btmemlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				MemlogVO vo = new MemlogVO();

				String id = tfid.getText();
				String pw = pfpw.getText();
				String memtel = tftel.getText();
				String memaddr = tfaddr.getText();
				String mememr = tfemr.getText();
				String memname = tfname.getText();
				String memjuban = pfju.getText();
				Object memobstacle = cbob.getSelectedItem();

				vo.setID(id);
				vo.setPW(pw);
				vo.setMemtel(memtel);
				vo.setMemaddr(memaddr);
				vo.setMememr(mememr);
				vo.setMemname(memname);
				vo.setMemjuban(memjuban);
				vo.setMemobstacle(memobstacle);

				while (true) {

					if (tfid.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요!");
						break;
					} else if (pfpw.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요!");
						break;
					} else if (tftel.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "연락처을 입력해주세요!");
						break;
					} else if (tfemr.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "비상연락처를 입력해주세요!");
						break;
					} else if (tfaddr.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "주소를 입력해주세요!");
						break;
					} else if (tfname.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "이름을 입력해주세요!");
						break;
					} else if (pfju.getText().length() == 0) {
						LoginVIEW window = new LoginVIEW();
						window.frame.setVisible(false);
						window.frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "주민번호 앞자리를 입력해주세요!");
						break;
					} else {
						try {
							mdao.ijoin(vo);
							dispose();
							LoginVIEW window = new LoginVIEW();
							window.frame.setVisible(true);
							window.frame.setSize(500, 500);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
					}
					break;
				}
			}
		});

		btcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginVIEW window = new LoginVIEW();
				window.frame.setVisible(true);
				window.frame.setSize(500, 500);
			}

		});
	}

	// 클리어 스크린 메소드
	public void clearScreen() {
		tfid.setText("");
		pfpw.setText("");
		tftel.setText("");
		tfemr.setText("");
		tfaddr.setText("");
		tfname.setText("");
		pfju.setText("");
		cbob.setSelectedItem("");
	}

	// 글자 수 제한 메소드
	public class JTextFieldLimit extends PlainDocument {
		private int limit;
		private boolean toUppercase = false;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
			this.toUppercase = upper;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null) {
				return;
			}

			if ((getLength() + str.length()) <= limit) {
				if (toUppercase) {
					str = str.toUpperCase();
				}
				super.insertString(offset, str, (javax.swing.text.AttributeSet) attr);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
