package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import model.LoginDAO;
import model.MemlogDAO;
import model.MyPageDAO;
import model.rec.LoginVO;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

public class LoginVIEW extends JFrame implements ActionListener {
	JTextField tfid;
	JPasswordField pfpw;
	private boolean flag = false;

	LoginVO vo;
	ArrayList list = null;
	LoginDAO ldao;
	MemlogDAO mdao;
	MyPageDAO mpdao;
	MemlogVIEW mv;
	LoginVIEW lv;
	JFrame frame; 
	
	public LoginVIEW() {
		
		try {
			ldao = new LoginDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		frame = new JFrame();
		
		JLabel lbID = new JLabel("ID");
		lbID.setBounds(480, 251, 30, 20);
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbpw = new JLabel("PW");
		lbpw.setBounds(480, 311, 30, 20);
		
		tfid = new JTextField();
		tfid.setBounds(569, 251, 160, 20);
		tfid.setColumns(10);
		
		pfpw = new JPasswordField();
		pfpw.setBounds(569, 311, 160, 20);
		
		JButton btlog = new JButton("로그인");
		btlog.setBounds(485, 430, 97, 23);
		
		JButton btmemlog = new JButton("회원가입");
		btmemlog.setBounds(632, 430, 97, 23);
		getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btlog);
		frame.getContentPane().add(btmemlog);
		frame.getContentPane().add(lbpw);
		frame.getContentPane().add(lbID);
		frame.getContentPane().add(tfid);
		frame.getContentPane().add(pfpw);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon icon = new ImageIcon("/img/\uD68C\uC6D0.png");
	
		
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\\uAC15\uC758\uC2E42\\Desktop\\\uC774\uBBF8\uC9C0\\\uD68C\uC6D0.png"));
		lblNewLabel.setBounds(558, 146, 70, 70);
		frame.getContentPane().add(lblNewLabel);
		frame.setSize(1280,690);
		setResizable(false);
		setLocationRelativeTo(null);

	
		
		btlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();
				String pw = pfpw.getText();
				
				try {
					int code = ldao.Login(id, pw);				
				
					if(code == 0){ 								
						JOptionPane.showMessageDialog(null, "ID, PW를 확인해주세요!");
					} else {
						JOptionPane.showMessageDialog(null,""+ id +"님 환영합니다!");
//						Frame.setVisible(false); 				
//						new LoginView(code);	
						new MedicalStart(code, id);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, " 로그인 실패 : " + ex.getMessage());
				}
				
			}
		});

		btmemlog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // 현재 화면 닫기
                MemlogVIEW view = new MemlogVIEW(); // 회원가입 화면 객체 생성
                view.setVisible(true);
			}

		
		});
//		initialize();
	}
	
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginVIEW window = new LoginVIEW();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				
				}
			}
		});
	}



	public void addWindowListener(WindowAdapter windowAdapter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}



