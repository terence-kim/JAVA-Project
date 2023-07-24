package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import model.LoginDAO;
import model.rec.LoginVO;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;


public class MyPageView extends JPanel implements ActionListener{

	private JFrame frame;
	LoginDAO dao = null;
	LoginVO vo = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPageView window = new MyPageView();
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
	public MyPageView() {
		try {
			dao = new LoginDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}

		JLabel lbinfo = new JLabel("\uB0B4\uC815\uBCF4");
		lbinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbinfo.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		
		JLabel lbchinfo = new JLabel("\uB0B4\uC815\uBCF4\uC218\uC815");
		lbchinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbchinfo.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(232)
							.addComponent(lbinfo, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(259)
							.addComponent(lbchinfo)))
					.addContainerGap(268, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lbinfo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(145)
					.addComponent(lbchinfo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(538, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		initialize();
	
	}
	public MyPageView(LoginVIEW loginVIEW) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 500, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setTaCheck(String string) {
		// TODO Auto-generated method stub
		
	}
}
