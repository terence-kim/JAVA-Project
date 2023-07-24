package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ReviewDAO;
import model.rec.ReviewVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ReviewRegistView extends JPanel implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	JTextField tfMemberCode, tfShopCode, tfReviewAvg;
	JButton btnEdit, btnRegist, btnDelete;
	JTextArea taReviewContent;
	JLabel lbMemberCode, lbShopCode, lbReviewAvg;

	ReviewVO vo;
	ReviewDAO dao;
	ArrayList list;
	
	int memCode;
	String id;

//	ReviewRegistView dialog = new ReviewRegistView();
	private JLabel lbMachineName;
	private JTextField tfMachineName;

	public ReviewRegistView(int memCode, String id) {
//		getContentPane().setLayout(null);
		newObject();
		eventProc();
		
		this.memCode = memCode;
		this.id = id;

		try {
			dao = new ReviewDAO();
			System.out.println("reviewRegist 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "reviewRegist DB연결 실패 : " + e.getMessage());
		}
	}

	void newObject() {

		tfMemberCode = new JTextField();
		tfShopCode = new JTextField();
		tfReviewAvg = new JTextField();
		taReviewContent = new JTextArea();
		btnRegist = new JButton("등록");
		btnEdit = new JButton("수정");
		btnDelete = new JButton("삭제");
		lbMemberCode = new JLabel("회원코드");
		lbShopCode = new JLabel("주문상세내역코드");
		lbReviewAvg = new JLabel("평점");
		lbMachineName = new JLabel("기기명");

		setBounds(100, 100, 567, 650);
		setLayout(null);
		contentPanel.setBounds(26, 7, 20, 20);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel);

		tfMemberCode.setFont(new Font("굴림", Font.PLAIN, 15));
		tfMemberCode.setEditable(false);
		tfMemberCode.setColumns(10);
		tfMemberCode.setBounds(168, 47, 116, 24);
		add(tfMemberCode);
		tfShopCode.setEditable(false);
		tfShopCode.setFont(new Font("굴림", Font.PLAIN, 15));
		tfShopCode.setColumns(10);
		tfShopCode.setBounds(168, 108, 116, 24);
		add(tfShopCode);
		tfReviewAvg.setFont(new Font("굴림", Font.PLAIN, 15));
		tfReviewAvg.setColumns(10);
		tfReviewAvg.setBounds(168, 223, 116, 24);
		add(tfReviewAvg);
		tfMachineName = new JTextField();
		tfMachineName.setEditable(false);
		tfMachineName.setFont(new Font("굴림", Font.PLAIN, 15));
		tfMachineName.setColumns(10);
		tfMachineName.setBounds(168, 167, 116, 24);
		add(tfMachineName);

		taReviewContent.setFont(new Font("굴림", Font.PLAIN, 12));
		taReviewContent.setBounds(26, 266, 436, 256);
		add(taReviewContent);

		btnRegist.setFont(new Font("굴림", Font.PLAIN, 15));
		btnRegist.setBounds(208, 531, 76, 27);
		add(btnRegist);
		btnEdit.setFont(new Font("굴림", Font.PLAIN, 15));
		btnEdit.setBounds(291, 532, 76, 27);
		add(btnEdit);
		btnDelete.setFont(new Font("굴림", Font.PLAIN, 15));
		btnDelete.setBounds(379, 532, 83, 27);
		add(btnDelete);

		lbMemberCode.setFont(new Font("굴림", Font.PLAIN, 15));
		lbMemberCode.setBounds(26, 50, 56, 18);
		add(lbMemberCode);
		lbShopCode.setFont(new Font("굴림", Font.PLAIN, 15));
		lbShopCode.setBounds(26, 111, 112, 18);
		add(lbShopCode);
		lbReviewAvg.setFont(new Font("굴림", Font.PLAIN, 15));
		lbReviewAvg.setBounds(26, 226, 28, 18);
		add(lbReviewAvg);
		lbMachineName.setFont(new Font("굴림", Font.PLAIN, 15));
		lbMachineName.setBounds(26, 170, 42, 18);
		add(lbMachineName);
	}

	void eventProc() {
		btnEdit.addActionListener(this);
		btnRegist.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnRegist) {
			String avg = tfReviewAvg.getText();
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			ReviewVO vo = new ReviewVO(avg, content, code);

			try {

				dao.reviewRegist(vo);
				System.out.println("등록 완료");
			} catch (Exception e3) {
				// TODO: handle exception
				System.out.println("등록 실패");
			}

		} else if (o == btnEdit) {
			vo = new ReviewVO();

			try {
				int cnt = dao.reviewEdit(vo);
				if (cnt > 0) {
					System.out.println("수정 완료");
				} else {
					System.out.println("수정 실패");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "리뷰 수정 실패!! : " + e2.getMessage());
			}

		} else if (o == btnDelete) {
			vo = new ReviewVO();

			try {
				int cnt = dao.reviewDelete(vo);
				if (cnt > 0) {
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제 실패");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "리뷰 삭제 실패!! : " + e2.getMessage());
			}
		}
	}
	
	private void selectTable() {
		// TODO Auto-generated method stub
		tfReviewAvg.setText(String.valueOf(vo.getAvg()));
		tfShopCode.setText(String.valueOf(vo.getCode()));
		taReviewContent.setText(String.valueOf(vo.getContent()));
		}
		
		
	
}
