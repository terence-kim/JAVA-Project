package view;

import java.awt.EventQueue;
import java.awt.Font;
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

import model.MemlogDAO;
import model.rec.LoginVO;
import javax.swing.JInternalFrame;

public class LoginVIEW extends JFrame implements ActionListener {
	private boolean flag = false;

	LoginVO vo;
	ArrayList list = null;
	MemlogDAO mdao;
	MemlogVIEW mv;
	LoginVIEW lv;
	JFrame frame; 
	JTextField tfid;
	JPasswordField pfpw;
	JButton btlog;

	
	public LoginVIEW() {
		
		try {
			mdao = new MemlogDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		frame = new JFrame();
		
		JLabel lbID = new JLabel("ID");
		lbID.setBounds(32, 69, 30, 20);
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbpw = new JLabel("PW");
		lbpw.setBounds(32, 129, 30, 20);
		
		tfid = new JTextField();
		tfid.setBounds(121, 69, 160, 20);
		tfid.setColumns(10);
		
		JLabel lblog = new JLabel("\uB85C\uADF8\uC778\uCC3D");
		lblog.setBounds(141, 25, 80, 34);
		lblog.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		
		pfpw = new JPasswordField();
		pfpw.setBounds(121, 129, 160, 20);
		
		btlog = new JButton("로그인");
		btlog.setBounds(83, 267, 97, 23);
		
		JButton btmemlog = new JButton("회원가입");
		btmemlog.setBounds(230, 267, 97, 23);
		getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btlog);
		frame.getContentPane().add(btmemlog);
		frame.getContentPane().add(lbpw);
		frame.getContentPane().add(lbID);
		frame.getContentPane().add(lblog);
		frame.getContentPane().add(tfid);
		frame.getContentPane().add(pfpw);
		frame.setSize(500,500);
		setResizable(false);
		setLocationRelativeTo(null);

	
		//로그인 버튼이벤트
		btlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();
				String pw = pfpw.getText();
				
				try {
					int code = mdao.Login(id, pw);				
					if(tfid.getText().length() == 0 || pfpw.getText().length() == 0){ 								
						JOptionPane.showMessageDialog(null, "ID, PW칸을 확인해주세요!");
					}else if(code == 0) {
						JOptionPane.showMessageDialog(null, "ID, PW을 확인해주세요!");
					}else {
						JOptionPane.showMessageDialog(null,""+ id +"님 환영합니다!");
						
						frame.setVisible(false); 				
						new MedicalStart(code, id);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, " 로그인 실패 : " + ex.getMessage());
				}
				
			}
		});

		//회원가입 버튼이벤트
		btmemlog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // 현재 화면 닫기
                MemlogVIEW view = new MemlogVIEW(); // 회원가입 화면 객체 생성
                view.setVisible(true);
			}
		
		});
	
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



