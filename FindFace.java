import java.awt.*;
import java.awt.event  .*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.border.*;

public class FindFace extends JFrame 
	{
    
    Connection cn;
    Statement stmt;
    
	boolean selectstatus=false;
    int cids[]=new int[6];
    
	public FindFace()
		{
        
		initComponents();
        setSize(650,650);
		
		java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Rectangle frame=getBounds();
		this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);
        
		setTitle("Find Face & Criminal Details Screen");
		setResizable(false);
        
		try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           Database d1=new Database();
            cn=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
            stmt=cn.createStatement();
            ResultSet rs=stmt.executeQuery("select *from face_suspectphoto");
            cmbCrimeId.removeAllItems();
            cmbCrimeId.addItem("Select");
            while(rs.next())
            {
                cmbCrimeId.addItem(rs.getString(1));
            }
        }catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Suspect Details", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        selectstatus=true;
    }
    
    private void initComponents() 
		{
        
		lblPhoto = new javax.swing.JLabel();
        cmbCrimeId = new javax.swing.JComboBox();
        lblCrimeId = new javax.swing.JLabel();
        
		btnAllSuspects = new javax.swing.JButton();
        btnMostSupspect = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        
		jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        getContentPane().add(lblPhoto);
        lblPhoto.setBounds(200, 200, 250, 280);

        cmbCrimeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCrimeIdActionPerformed(evt);
            }
        });

        getContentPane().add(cmbCrimeId);
        cmbCrimeId.setBounds(90, 100, 160, 25);

        lblCrimeId.setText("Crime ID");
        getContentPane().add(lblCrimeId);
        lblCrimeId.setBounds(20, 100, 150, 16);

        btnAllSuspects.setText("Show All Suspects");
        btnAllSuspects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllSuspectsActionPerformed(evt);
            }
        });

        getContentPane().add(btnAllSuspects);
        btnAllSuspects.setBounds(20, 550, 200, 30);

        btnMostSupspect.setText("Show Most Suitable Supect");
        btnMostSupspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostSupspectActionPerformed(evt);
            }
        });

        getContentPane().add(btnMostSupspect);
        btnMostSupspect.setBounds(230, 550, 200, 30);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        getContentPane().add(btnClose);
        btnClose.setBounds(450, 550, 100, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", Font.BOLD/Font.ITALIC, 25));
        jLabel1.setText("Suspected Criminal Faces");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 10, 300, 30);

        pack();
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) 
		{
          setVisible(false);
	      dispose();
	    }

    private void btnMostSupspectActionPerformed(java.awt.event.ActionEvent evt) 
		{
			  new ShowMaxPossibleSuspect(this, true, cids, false).show();
		}

    private void btnAllSuspectsActionPerformed(java.awt.event.ActionEvent evt) 
		{
            new ShowMaxPossibleSuspect(this, true, cids, true).show();
		}

    private void cmbCrimeIdActionPerformed(java.awt.event.ActionEvent evt) 
		{
        
        if(cmbCrimeId.getSelectedIndex()!=0)
        {
        String crimeid=cmbCrimeId.getSelectedItem().toString();
        try
        {
            ResultSet rs=stmt.executeQuery("select * from face_suspectphoto where crimeid=" +crimeid);
            String photofile="";
            int photoheight=0;
            while (rs.next())
            {
                photofile=rs.getString(2);
                photoheight=Integer.parseInt(rs.getString(3));
            }
            FileInputStream fin=new FileInputStream("Suspects\\" + photofile);
            DataInputStream din=new DataInputStream(fin);
            int len=fin.available();
            int pixels[]=new int[len];
            int i=0;
            while(fin.available()>0)
            {
                pixels[i]=din.readInt();
		i++;
            }
            fin.close();
            din.close();
            Image clipimg=createImage(new MemoryImageSource(200,photoheight,pixels,0,200));
            ImageIcon eyesicn=new ImageIcon(clipimg);
            lblPhoto.setIcon(eyesicn);
            rs.close();
            
            rs=stmt.executeQuery("select * from face_suspects where crimeid=" + crimeid);
            i=0;
            while(rs.next())
            {
                cids[i]=Integer.parseInt(rs.getString(2));
                i++;
            }
        }catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Photo Status", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) 
		{
        
    }
    
    
    private void exitForm(java.awt.event.WindowEvent evt) 
		{
        System.exit(0);
    }
    
    public static void main(String args[]) {
        new FindFace().show();
    }
    
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMostSupspect;
    private javax.swing.JButton btnAllSuspects;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblCrimeId;
    private javax.swing.JComboBox cmbCrimeId;
    private javax.swing.JLabel jLabel1;
        
}
