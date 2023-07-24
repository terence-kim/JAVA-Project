package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import model.LoginDAO;
import model.rec.LoginVO;
import model.rec.MemlogVO;

public class MemlogVIEW extends JPanel implements ActionListener{

	private JFrame frame;
	private JTextField tfid;
	private JTextField tfpw;
	private JTextField tfrepw;
	private JTextField tfname;
	private JTextField tftel;
	private JTextField tfemr;
	private JTextField tfwuaddr;
	private JTextField tfdoaddr;
	private JTextField tfsangaddr;
	private JTextField tfju;
	private JPasswordField pspw;
	private	LoginVIEW lv;
	private	Same sa;
	LoginDAO dao = null;
	LoginVO vo = null;

//	public MemlogVIEW(LoginVIEW lv) {
//		super(lv, "Memlog", true);
//		this.lv = lv;
//		sa = lv.getUsers();
//		}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemlogVIEW window = new MemlogVIEW();
					window.frame.setVisible(true);
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
		
		try {
			dao = new LoginDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		
		JLabel lbmemlog = new JLabel("\uD68C\uC6D0\uAC00\uC785\uCC3D");
		lbmemlog.setHorizontalAlignment(SwingConstants.CENTER);
		lbmemlog.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		
		JLabel lbmemid = new JLabel("\uC544\uC774\uB514\t*");
		
		JLabel lbmempw =new JLabel("\uBE44\uBC00\uBC88\uD638*");
		
		JLabel lbrepw = new JLabel("\uBE44\uBC00\uBC88\uD638\uC7AC\uD655\uC778*");
		
		tfid = new JTextField();
		tfid.setColumns(10);
		
		tfpw = new JTextField();
		tfpw.setColumns(10);
		
		tfrepw = new JTextField();
		tfrepw.setColumns(10);
		
		tfname = new JTextField();
		tfname.setColumns(10);
		
		tftel = new JTextField();
		tftel.setColumns(10);
		
		tfemr = new JTextField();
		tfemr.setColumns(10);
		
		tfwuaddr = new JTextField();
		tfwuaddr.setText("\uC6B0\uD3B8\uBC88\uD638");
		tfwuaddr.setColumns(10);
		
		tfdoaddr = new JTextField();
		tfdoaddr.setText("\uB3C4\uB85C\uBA85\uC8FC\uC18C");
		tfdoaddr.setColumns(10);
		
		tfsangaddr = new JTextField();
		tfsangaddr.setText("\uC0C1\uC138\uC8FC\uC18C");
		tfsangaddr.setColumns(10);
		
		JComboBox cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"1\uB4F1\uAE09", "2\uB4F1\uAE09", "3\uB4F1\uAE09", "4\uB4F1\uAE09", "5\uB4F1\uAE09"}));
		cb.setMaximumRowCount(5);
		
		JLabel lbmemname = new JLabel("\uC774\uB984*");
		
		JLabel lbmemtel = new JLabel("\uC5F0\uB77D\uCC98*");
		
		JLabel lbmememr = new JLabel("\uBCF4\uD638\uC790 \uC5F0\uB77D\uCC98");
		
		JLabel lbmemaddr = new JLabel("\uC8FC\uC18C*");
		
		JLabel lbmemju = new JLabel("\uC8FC\uBBFC\uBC88\uD638*");
		
		JLabel lbmemob = new JLabel("\uC7A5\uC560\uB4F1\uAE09*");
		
		JButton btdouble = new JButton("\uC911\uBCF5\uD655\uC778");
		
		JButton btmake = new JButton("\uB9CC\uB4E4\uAE30");
		
		JButton btmemlog = new JButton("\uD68C\uC6D0\uAC00\uC785");
		
		JButton btcancel = new JButton("\uCDE8\uC18C");
		
		tfju = new JTextField();
		tfju.setColumns(10);
		
		pspw = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
							.addComponent(lbmemlog, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btcancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lbmemid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lbmempw, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lbmemname, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbmemtel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbmememr, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbmemaddr, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbmemju, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbmemob, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(31)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(tfpw, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfid, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfrepw, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfname, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tftel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfemr, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfwuaddr, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfdoaddr, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfsangaddr, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addComponent(cb, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(tfju, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(pspw, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
									.addGap(66)
									.addComponent(btdouble)))))
					.addContainerGap(95, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbrepw, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
					.addGap(427))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(151, Short.MAX_VALUE)
							.addComponent(btmake, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(65))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(81)
							.addComponent(btmemlog, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(135)))
					.addGap(174))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(lbmemlog, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(120)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbrepw, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfrepw, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbmemid, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfid, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(btdouble, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbmempw, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfpw, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbmemname, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfname, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbmemtel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tftel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbmememr, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfemr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbmemaddr, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfwuaddr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfdoaddr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbmemju, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfju, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(pspw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbmemob, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(cb, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(539)
							.addComponent(tfsangaddr, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(135)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btmemlog, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcancel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(761)
					.addComponent(btmake, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		initialize();
	}
	
	

		public MemlogVIEW(LoginVIEW loginVIEW) {
		// TODO Auto-generated constructor stub
	}

		private void addListeners(AbstractButton btmemlog, AbstractButton btCancel) {
		        addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent we) {
		                dispose();
		                lv.setVisible(true);
		            }
		        });
		        btCancel.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		                dispose();
		                lv.setVisible(true);
		            }
		        });
		        btmemlog.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		                // 정보 하나라도 비어있으면
		                if(isBlank()) {
		                    JOptionPane.showMessageDialog(
		                    		MemlogVIEW.this,
		                            "모든 정보를 입력해주세요."
		                    );
		                    // 모두 입력했을 때
		                } else {
		                    // Id 중복일 때
		                    if(sa.isIDOverlap(tfid.getText())) {
		                        JOptionPane.showMessageDialog(
		                        		MemlogVIEW.this,
		                                "이미 존재하는 Id입니다."
		                        );
		                        tfid.requestFocus();

		                        // Pw와 Re가 일치하지 않았을 때
		                    } else if(!String.valueOf(pspw.getPassword()).equals(String.valueOf(tfrepw.getText()))) {
		                        JOptionPane.showMessageDialog(
		                        		MemlogVIEW.this,
		                                "pw와 repw가 일치하지 않습니다."
		                        );
		                        pspw.requestFocus();
		                    } else {
//		                        sa.addlvo(new LoginVO(
//		                                tfid.getText(),
//		                                pspw.getText(),
//		                                tfrepw.getText(),
//		                                tfname.getText(),
//		                                tftel.getText(),
//		                                tfemr.getText(),
//		                                tfdoaddr.getText(),
//		                                tfdoaddr.getText()));
		                        JOptionPane.showMessageDialog(
		                        		MemlogVIEW.this,
		                                "회원가입을 완료했습니다!"
		                        );
		                        dispose();
		                        lv.setVisible(true);
		                    }
		                }
		            }
		        });
		    }
		    private void addWindowListener(WindowAdapter windowAdapter) {
			// TODO Auto-generated method stub
			
		}
			protected void dispose() {
			// TODO Auto-generated method stub
			
		}

			public boolean isBlank() {
		        boolean result = false;
		        if(tfid.getText().isEmpty()) {
		            tfid.requestFocus();
		            return true;
		        }
		        if(String.valueOf(pspw.getPassword()).isEmpty()) {
		            pspw.requestFocus();
		            return true;
		        }
		        if(String.valueOf(tfrepw.getText()).isEmpty()) {
		            tfrepw.requestFocus();
		            return true;
		        }
		        if(tfname.getText().isEmpty()) {
		            tfname.requestFocus();
		            return true;
		        }
		        if(tftel.getText().isEmpty()) {
		            tftel.requestFocus();
		            return true;
		        }
		        if(tfemr.getText().isEmpty()) {
		            tfemr.requestFocus();
		            return true;
		        }
		        if(tfwuaddr.getText().isEmpty()) {
		        	tfwuaddr.requestFocus();
		            return true;
		        }
		        if(tfdoaddr.getText().isEmpty()) {
		        	tfdoaddr.requestFocus();
		            return true;
		        }
		        if(tfsangaddr.getText().isEmpty()) {
		        	tfsangaddr.requestFocus();
		            return true;
		        }
		        return result;
		    }
		    
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
