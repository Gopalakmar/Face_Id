import java.awt.*;
import java.awt.event  .*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.border.*;

public class ShowMaxPossibleSuspect extends javax.swing.JDialog 
	{
    
    Vector cidvect=new Vector(2,2);
    Vector cidrepeats=new Vector(2,2);
    Vector finalVect=new Vector(2,2);
    
	Connection cn;
    Statement stmt;
    
	int presentsuspect=0;
    public ShowMaxPossibleSuspect(java.awt.Frame parent, boolean modal, int cids[], boolean all)
		{
        super(parent, modal);
        initComponents();
        setSize(575,650);
        
		java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Rectangle frame=getBounds();
		this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);
        
		setResizable(false);
        
		if(all)
           lblHeading.setText("View All Suspects");
        else
            lblHeading.setText("Most Possible Suspect(s)");
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(false);
        setTitle("Suspects Mostly suitable with the Designed Photo");
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Database d1=new Database();
        cn=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
        stmt=cn.createStatement();
        }catch(Exception e)
        {
        }
        int max=0;
		int count=0;
        
		for(int i=0;i<6;i++)
        {
            count=1;
            if(cidvect.contains(new Integer(cids[i]))==false)
            {
                for(int j=i+1;j<6;j++)
                {
                    if(cids[i]==cids[j])
                            count++;
                }
                cidvect.add(new Integer(cids[i]));
                cidrepeats.add(new Integer(count));
                if(count>max)
                    max=count;
            }
        }
        if(all==false)
        {
            finalVect=finalList(max);
        }
        else
        {
            finalVect=cidvect;
        }

		Integer pressuspobj=(Integer)finalVect.elementAt(presentsuspect);
        displayDetails(pressuspobj.intValue());
        
		if(finalVect.size()==1)
        {
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(false);
        }
        else
        {
            btnNext.setEnabled(true);
        }
    }
    

    private void initComponents() 
	{
        
		lblCriminalId = new javax.swing.JLabel("Criminal Id");
        
		jLabel1 = new javax.swing.JLabel("First Name");
        jLabel2 = new javax.swing.JLabel("Last Name");
        jLabel3 = new javax.swing.JLabel("Alias Name");
        jLabel4 = new javax.swing.JLabel("D.O.B");
        jLabel5 = new javax.swing.JLabel("Age");
        jLabel6 = new javax.swing.JLabel("Gender");
        jLabel7 = new javax.swing.JLabel("Address");
        jLabel8 = new javax.swing.JLabel("City");
		jLabel9=new javax.swing.JLabel("State");
		jLabel10=new javax.swing.JLabel("Arrested Date");
		jLabel11=new javax.swing.JLabel("Crime Involved");
     	lblPhoto = new javax.swing.JLabel();
        lblPhotoSpace = new javax.swing.JLabel();
		
		lcr=new JLabel("cr");
		lfname=new JLabel("fn");
		llast=new JLabel("la");
		lalias=new JLabel("al");
		ldob=new JLabel("dop");
		lage=new JLabel("afe");
		lgender=new JLabel("ge");
		laddress=new JLabel("add");
		lcity=new JLabel("ci");
		lstate=new JLabel("st");
		larrdate=new JLabel("ar");
		lcrimein=new JLabel("cri");

		btnClose = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        
        lblHeading = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        getContentPane().add(lblCriminalId);
        lblCriminalId.setBounds(10, 40, 90, 30);

		getContentPane().add(lcr);
        lcr.setBounds(130, 40, 90, 30);

        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 80, 90, 30);
		
		getContentPane().add(lfname);
        lfname.setBounds(130, 80, 90, 30);

        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 120, 90, 30);

		getContentPane().add(llast);
        llast.setBounds(130, 120, 90, 30);

        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 160, 90, 30);
		
		 getContentPane().add(lalias);
         lalias.setBounds(130, 160, 90, 30);

        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 200, 90, 30);

		getContentPane().add(ldob);
        ldob.setBounds(130, 200, 120, 30);

        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 240, 90, 30);

		getContentPane().add(lage);
        lage.setBounds(130, 240, 120, 30);

        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 280, 90, 30);
		
		getContentPane().add(lgender);
        lgender.setBounds(130, 280, 90, 30);

        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 320, 90, 30);
		
	    getContentPane().add(laddress);
        laddress.setBounds(130, 320, 90, 30);

        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 360, 90, 30);

		getContentPane().add(lcity);
        lcity.setBounds(130, 360, 90, 30);

		getContentPane().add(jLabel9);
		jLabel9.setBounds(10,400,90,30);
		
		getContentPane().add(lstate);
		lstate.setBounds(130,400,90,30);

		getContentPane().add(jLabel10);
		jLabel10.setBounds(10,440,90,30);

		getContentPane().add(larrdate);
		larrdate.setBounds(130,440,130,30);

		getContentPane().add(jLabel11);
		jLabel11.setBounds(10,480,90,30);
		
		getContentPane().add(lcrimein);
		lcrimein.setBounds(130,480,90,30);

        lblPhoto.setText("Photo");
        getContentPane().add(lblPhoto);
        lblPhoto.setBounds(410, 40, 33, 16);
 
        getContentPane().add(lblPhotoSpace);
        lblPhotoSpace.setBounds(310, 60, 240, 260);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        getContentPane().add(btnClose);
        btnClose.setBounds(30, 540, 140, 30);

        btnPrevious.setText("Previous Suspect");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        getContentPane().add(btnPrevious);
        btnPrevious.setBounds(220, 540, 140, 30);

        btnNext.setText("Next Suspect");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        getContentPane().add(btnNext);
        btnNext.setBounds(400, 540, 140, 30);

        lblHeading.setFont(new java.awt.Font("Times New Roman", Font.BOLD/Font.ITALIC, 25));

        lblHeading.setText("View All Suspects");
        getContentPane().add(lblHeading);
        lblHeading.setBounds(180, 10, 280, 30);

        pack();
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) 
		{
          setVisible(false);
        dispose();
    }

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) 
		{
      
        presentsuspect--;
        Integer pressuspobj=(Integer)finalVect.get(presentsuspect);
        displayDetails(pressuspobj.intValue());
        if(presentsuspect==0)
        {
            btnPrevious.setEnabled(false);
        }
        btnNext.setEnabled(true);
    }

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) 
		{
          presentsuspect++;
        Integer pressuspobj=(Integer)finalVect.get(presentsuspect);
        displayDetails(pressuspobj.intValue());
        if(presentsuspect==(finalVect.size()-1))
        {
            btnNext.setEnabled(false);
        }
        btnPrevious.setEnabled(true);
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) 
		{
        }
    
    
    private void closeDialog(java.awt.event.WindowEvent evt) 
		{
        setVisible(false);
        dispose();
    }
    
    
    public static void main(String args[]) 
		{
        int cids[]={1,1,2,1,2,1};
        new ShowMaxPossibleSuspect(new javax.swing.JFrame(), true, cids, true).show();
    }
    
    

    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnNext;

    private javax.swing.JLabel lblCriminalId;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblPhotoSpace;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel1;
	
	private javax.swing.JLabel lcr;
	private javax.swing.JLabel lfname;
	private javax.swing.JLabel llast;
	private javax.swing.JLabel lalias;
	private javax.swing.JLabel ldob;
	private javax.swing.JLabel lage;
	private javax.swing.JLabel lgender;
	private javax.swing.JLabel laddress;
	private javax.swing.JLabel lcity;
	private javax.swing.JLabel lstate;
	private javax.swing.JLabel larrdate;
	private javax.swing.JLabel lcrimein;

    public void displayDetails(int cid)
    {
        try
        {

            ResultSet rs=stmt.executeQuery("select cid, fname, lname, aname, dob, age, gender, address, city, state, arresteddate, crimein, photo from face where cid="+cid);
            if(rs.next())
            {

                lcr.setText(rs.getString(1));
                lfname.setText(rs.getString(2));
                llast.setText(rs.getString(3));
                lalias.setText(rs.getString(4));
                ldob.setText(rs.getString(5));
                lage.setText(rs.getString(6));
                lgender.setText(rs.getString(7));
                laddress.setText(rs.getString(8));
                lcity.setText(rs.getString(9));
                lstate.setText(rs.getString(10));
                larrdate.setText(rs.getString(11));
				lcrimein.setText(rs.getString(12));
                String photofile=rs.getString(13);
                ImageIcon icophoto=new ImageIcon("Images\\"+photofile);
                lblPhotoSpace.setIcon(icophoto);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Vector finalList(int max)
    {
        Vector fv=new Vector(2,2);
        int loc=0;
        loc=cidrepeats.indexOf(new Integer(max),loc);
        while(loc!=-1)
        {
            fv.add(cidvect.elementAt(loc));
            loc=cidrepeats.indexOf(new Integer(max),loc+1);
        }
        return fv;
    }
}

