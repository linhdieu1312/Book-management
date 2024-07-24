import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel; 

public class Frame extends JFrame implements ActionListener,MouseListener{
    private quanlySach s;
    private List<Sach> list;
    private JTable table;
    private JTextField txtMaSach,txtTenSach,txtTacGia,txtNamXB,txtNhaXB,txtSoTrang,txtGiaTien;
    private JButton btnThem,btnXoa,btnXoaTrang,btnLuu;
    private DefaultTableModel tableModel;
    private static final String FILENAME="dulieusach.txt";
    
    public Frame(quanlySach s) {
	setTitle("Quan ly sach");
        setSize(800,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel pnlNorth;
        add(pnlNorth= new JPanel(), BorderLayout.NORTH);
        javax.swing.border.Border centerBorder= BorderFactory.createLineBorder(Color.black);
        TitledBorder northTitleBorder= new TitledBorder(centerBorder, "");
        pnlNorth.setBorder(northTitleBorder);
        Box b= Box.createVerticalBox();
        Box b1,b2,b3,b4,b5;
        JLabel lblMaSach, lblTenSach, lblTacGia,lblNamXB, lblNhaXB, lblSoTrang, lblGiaTien;
        b.add(b1 =Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b1.add(lblMaSach= new JLabel("Ma Sach: "));
        b1.add(txtMaSach= new JTextField(10));
        
        b.add(b2=Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b2.add(lblTenSach= new JLabel("Ten Sach: "));
        b2.add(txtTenSach= new JTextField(15));
        b2.add(lblTacGia= new JLabel("Tac Gia: "));
        b2.add(txtTacGia= new JTextField(15));

        b.add(b3=Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b3.add(lblNamXB= new JLabel("Nam Xuat Ban: "));
        b3.add(txtNamXB= new JTextField(10));
        b3.add(lblNhaXB= new JLabel("Nha Xuat Ban: "));
        b3.add(txtNhaXB= new JTextField(15));
			
        b.add(b4=Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(15));
        b4.add(lblSoTrang= new JLabel("So Trang: "));
        b4.add(txtSoTrang= new JTextField(15));
        b4.add(lblGiaTien= new JLabel("Gia Tien: "));
        b4.add(txtGiaTien= new JTextField(15));
			
        lblMaSach.setPreferredSize(lblMaSach.getPreferredSize());
        lblTenSach.setPreferredSize(lblTenSach.getPreferredSize());
        lblTacGia.setPreferredSize(lblTacGia.getPreferredSize());
        lblNamXB.setPreferredSize(lblNamXB.getPreferredSize());
        lblNhaXB.setPreferredSize(lblNhaXB.getPreferredSize());
        lblSoTrang.setPreferredSize(lblSoTrang.getPreferredSize());
        lblGiaTien.setPreferredSize(lblGiaTien.getPreferredSize());
			
        b.add(b5=Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        String [] headers="Ma Sach;Ten Sach;Tac Gia;Nam XB;Nha XB;So Trang;Gia Tien;".split(";");
        tableModel =new DefaultTableModel(headers,0);
        JScrollPane scroll= new JScrollPane();
        scroll.setViewportView(table = new JTable(tableModel));
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JPanel pnS = new JPanel();
        add(pnS,BorderLayout.SOUTH);
        javax.swing.border.Border southBorder= BorderFactory.createLineBorder(Color.black);
        TitledBorder southTitleBorder= new TitledBorder(southBorder, "DANH SACH CAC CUON SACH");
        pnS.setBorder(southTitleBorder);
        pnS.add(scroll);
        pnlNorth.add(b);
			
        JSplitPane split= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(split, BorderLayout.CENTER);
        JPanel pnlLeft;
        split.add(pnlLeft= new JPanel());		
        pnlLeft.add(btnThem= new JButton("Them"));
        pnlLeft.add(btnXoaTrang= new JButton("Xoa Trang"));
        pnlLeft.add(btnXoa= new JButton("Xoa"));
        pnlLeft.add(btnLuu=new JButton("Luu"));
        LoadDatabase();
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnLuu.addActionListener(this);
        table.addMouseListener(this);
		}
	@Override
	public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Object o= e.getSource();
            if(o.equals(btnThem))
		themActions();
            if(o.equals(btnXoa))
		xoaActions();
            if(o.equals(btnXoaTrang))
		xoaTrangActions();	
            if(o.equals(btnLuu))
		luuActions(s.getList());
        }
	
        private void xoaTrangActions() {
            txtMaSach.setText("");
            txtTenSach.setText("");
            txtTacGia.setText("");
            txtNamXB.setText("");
            txtNhaXB.setText("");
            txtSoTrang.setText("");
            txtGiaTien.setText("");
            txtMaSach.requestFocus();	
        }
	
        private void themActions() {
            try {
                int maSach= Integer.parseInt(txtMaSach.getText());
                String tenSach= txtTenSach.getText();
                String tacGia= txtTacGia.getText();
                int namXB = Integer.parseInt(txtNamXB.getText());
                String nhaXB= txtNhaXB.getText();
                int soTrang = Integer.parseInt(txtSoTrang.getText());
                double giaTien= Double.parseDouble(txtGiaTien.getText());
                Sach s1 = new Sach(maSach, tenSach, tacGia,namXB,nhaXB, soTrang,giaTien);
                if(s.themSach(s1)) {
                    String[] row= {Integer.toString(maSach),tenSach,tacGia,Integer.toString(namXB),nhaXB,Integer.toString(soTrang),Double.toString(giaTien)+""};
                    tableModel.addRow(row);
                    xoaTrangActions();
		} else {
                    JOptionPane.showMessageDialog(null, "Trung ma sach.");
                    txtMaSach.selectAll();
                    txtMaSach.requestFocus();
		}
            }
            
            catch(Exception ex) {
		JOptionPane.showMessageDialog(null, "Loi nhap du lieu.");
		return;
            }
	}
        
        private void xoaActions(){
            int row= table.getSelectedRow();
            if(row!=-1) { 
                int maSach= Integer.parseInt((String)table.getModel().getValueAt(row, 0));
                if(hoiNhac==JOptionPane.YES_OPTION) {
                    if(s.xoaSach(maSach)){
                        tableModel.removeRow(row);
                    }
                }
            }
	}
        
        void LoadDatabase() {
            BufferedReader br=null;
            s=new quanlySach();
            try{ 
                if(new File(FILENAME).exists()) {
                    br=new BufferedReader (new FileReader(FILENAME));
                    br.readLine();
                    while(br.ready()) {
                        String line = br.readLine();
			if(line !=null&& !line.trim().equals("")) {
                            String[]a= line.split(";");
                            Sach s1= new Sach(Integer.parseInt(a[0]),a[1],a[2],Integer.parseInt(a[3]),a[4],Integer.parseInt(a[5]),Double.parseDouble(a[6]));
                            s.themSach(s1);
                            tableModel.addRow(a);
			}
                    }
		}
            }
            
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        void luuActions(ArrayList<Sach> ds) {
            BufferedWriter bw;
            try{
                bw=new BufferedWriter(new FileWriter(FILENAME));
		bw.write("Ma Sach-Ten Sach-Tac Gia-Nam XB-Nha XB-So Trang-Gia Tien\n");
                for(Sach s1: ds) { 
                    bw.write(s1.toString()+"\n");
		}
                bw.close();
            }
            
            catch(Exception ex) {
                ex.printStackTrace();
            }
	}

        @Override
	public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            int row= table.getSelectedRow();
            txtMaSach.setText(table.getValueAt(row, 0).toString());
            txtTenSach.setText(table.getValueAt(row, 1).toString());
            txtTacGia.setText(table.getValueAt(row, 2).toString());
            txtNamXB.setText(table.getValueAt(row, 3).toString());
            txtNhaXB.setText(table.getValueAt(row, 4).toString());
            txtSoTrang.setText(table.getValueAt(row, 5).toString());
            txtGiaTien.setText(table.getValueAt(row, 6).toString());
	}

        @Override
        public void mouseEntered(MouseEvent e) { 
            // TODO Auto-generated method stub
        }

        @Override
	public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }
	
        @Override
	public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
	public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
	}
}