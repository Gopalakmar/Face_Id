import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.sql.*;
import java.lang.String.*;
import java.awt.image.MemoryImageSource.*;
import java.util.*;

class Construct extends JFrame implements ActionListener
{
	String stor;
	String selcrimid="";

	int i,hr,fr,ey,no,lip,ch;
	int ht1,ht2,ht3,ht4,ht5,ht6;
	int ha1,ha2,ha3,ha4,ha5,ha6;
	int cids[]=new int[6];
	
	int finalhairht,finalfhht,finaleyesht, finalnoseht, finallipsht, finalchinht;
	ArrayList hairAL=new ArrayList();
	ArrayList fhAL=new ArrayList();
	ArrayList eyesAL=new ArrayList();
	ArrayList noseAL=new ArrayList();
	ArrayList lipsAL=new ArrayList();
	ArrayList chinAL=new ArrayList();
	
	Connection con;
	Statement st;
	
	int nextheight=0;
	
	JComboBox cHair,cForehead,cEyes,cNose,cLips,cChin;
	JButton bSave,bCancel,bClear;
	
	JLabel lHair,lForehead,lEyes,lNose,lLips,lChin;
	JLabel lHair1,lForehead1,lEyes1,lNose1,lLips1,lChin1;
	
	int hair,forehead,eyes,nose,lips,chin;
	String s="";
	
	Construct()
	{
		super("Construct Face Screen");
		cHair=new JComboBox();
		cForehead=new JComboBox();
		cEyes=new JComboBox();
		cNose=new JComboBox();
		cLips=new JComboBox();
		cChin=new JComboBox();

		cHair.setBounds(10,40,130,30);
		cForehead.setBounds(180,40,130,30);
		cEyes.setBounds(330,40,130,30);
		cNose.setBounds(480,40,130,30);
		cLips.setBounds(630,40,130,30);
		cChin.setBounds(780,40,130,30);

		bSave=new JButton("Save");
		bCancel=new JButton("Cancel");
		bClear=new JButton("Clear");
		
		bSave.setBounds(100,600,100,30);
		bCancel.setBounds(350,600,100,30);
		bClear.setBounds(600,600,100,30);
		
		
		bClear.addActionListener(this);
		bCancel.addActionListener(this);
		bSave.addActionListener(this);

		
		lHair=new JLabel("  Hair	");
		lForehead=new JLabel("	Forehead	");
		lEyes=new JLabel("	Eyes	  ");
		lNose=new JLabel("	Nose	  ");
		lLips=new JLabel("	Lips	  ");
		lChin=new JLabel("	Chin	  ");

		lHair1=new JLabel("");
		lForehead1=new JLabel("");
		lEyes1=new JLabel("");
		lNose1=new JLabel("");
		lLips1=new JLabel("");
		lChin1=new JLabel("");
			
		lHair.setBounds(30,10,130,30);
		lForehead.setBounds(200,10,130,30);
		lEyes.setBounds(350,10,130,30);
		lNose.setBounds(500,10,130,30);
		lLips.setBounds(650,10,130,30);
		lChin.setBounds(800,10,130,30);

		lHair1.setBounds(350,150,250,30);
		lForehead1.setBounds(350,181,250,30);
		lEyes1.setBounds(350,212,250,30);
		lNose1.setBounds(350,243,250,30);
		lLips1.setBounds(350,274,250,30);
		lChin1.setBounds(350,305,250,30);

		cHair.addActionListener(this);
		cForehead.addActionListener(this);
		cEyes.addActionListener(this);
		cNose.addActionListener(this);
		cLips.addActionListener(this);
		cChin.addActionListener(this);	

		Container c=getContentPane();
		c.setLayout(null);
	
		c.add(cHair);
		c.add(cForehead);
		c.add(cEyes);
		c.add(cNose);
		c.add(cLips);
		c.add(cChin);

		c.add(bSave);
		c.add(bCancel);
		c.add(bClear);

		c.add(lHair);
		c.add(lForehead);
		c.add(lEyes);
		c.add(lNose);
		c.add(lLips);
		c.add(lChin);
		
		c.add(lHair1);
		c.add(lForehead1);
		c.add(lEyes1);
		c.add(lNose1);
		c.add(lLips1);
		c.add(lChin1);
									
		
		setSize(950,700);
		setResizable(false);
		java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Rectangle frame=getBounds();
		this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Database d1=new Database();
			con=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from face_height");
			while(rs.next())
		{
			ArrayList a1=new ArrayList();
			selcrimid=rs.getString(1);
			a1.add(new Integer(Integer.parseInt(selcrimid)));
			if(selcrimid.equalsIgnoreCase("Select Hair"))
				JOptionPane.showMessageDialog(this,"Select the hair of the Criminal to construct the Photo", "constructing Photograph", JOptionPane.ERROR_MESSAGE);
	        FileInputStream hairfin=new FileInputStream("Clips\\hair\\hair"+selcrimid+".gif");
			DataInputStream hairdin=new DataInputStream(hairfin);
			int len=hairfin.available();
			int hairpixels[]=new int[len];
			int i=0;
			while(hairfin.available()>0)
			{
				hairpixels[i]=hairdin.readInt();
				i++;
			}
			hairfin.close();
			hairdin.close();
			int hairht=Integer.parseInt(rs.getString(2));
			a1.add(new Integer(hairht));
			hairAL.add(a1);
			Image hairclipimg=createImage(new MemoryImageSource(200,hairht,hairpixels,0,200));
			ImageIcon hairicn=new ImageIcon(hairclipimg);
			cHair.addItem(hairicn);

           
			ArrayList a2=new ArrayList();
	        FileInputStream fhfin=new FileInputStream("Clips\\forehead\\forehead"+selcrimid+".gif");
			DataInputStream fhdin=new DataInputStream(fhfin);
			len=fhfin.available();
			int fhpixels[]=new int[len];
			i=0;
			while(fhfin.available()>0)
			{
				fhpixels[i]=fhdin.readInt();
				i++;
			}
			fhfin.close();
			fhdin.close();
			int fhht=Integer.parseInt(rs.getString(3));
			a2.add(new Integer(Integer.parseInt(selcrimid)));
			a2.add(new Integer(fhht));
			fhAL.add(a2);
			Image fhclipimg=createImage(new MemoryImageSource(200,fhht,fhpixels,0,200));
			ImageIcon fhicn=new ImageIcon(fhclipimg);
			cForehead.addItem(fhicn);

            ArrayList a3=new ArrayList();
	        FileInputStream eyesfin=new FileInputStream("Clips\\eyes\\eyes"+selcrimid+".gif");
			DataInputStream eyesdin=new DataInputStream(eyesfin);
			len=eyesfin.available();
			int eyespixels[]=new int[len];
			i=0;
			while(eyesfin.available()>0)
			{
				eyespixels[i]=eyesdin.readInt();
				i++;
			}
			eyesfin.close();
			eyesdin.close();
			int eyesht=Integer.parseInt(rs.getString(4));
            a3.add(new Integer(Integer.parseInt(selcrimid)));
			a3.add(new Integer(eyesht));
			eyesAL.add(a3);
			Image eyesclipimg=createImage(new MemoryImageSource(200,eyesht,eyespixels,0,200));
			ImageIcon eyesicn=new ImageIcon(eyesclipimg);
			cEyes.addItem(eyesicn);


			ArrayList a4=new ArrayList();
			FileInputStream nosefin=new FileInputStream("Clips\\nose\\nose"+selcrimid+".gif");
			DataInputStream nosedin=new DataInputStream(nosefin);
			len=nosefin.available();
			int nosepixels[]=new int[len];
			i=0;
			while(nosefin.available()>0)
			{
				nosepixels[i]=nosedin.readInt();
				i++;
			}
			nosefin.close();
			nosedin.close();
			int noseht=Integer.parseInt(rs.getString(5));
			a4.add(new Integer(Integer.parseInt(selcrimid)));
			a4.add(new Integer(noseht));
			noseAL.add(a4);
			Image noseclipimg=createImage(new MemoryImageSource(200,noseht,nosepixels,0,200));
			ImageIcon noseicn=new ImageIcon(noseclipimg);
			cNose.addItem(noseicn);

		
            ArrayList a5=new ArrayList();
	        FileInputStream lipsfin=new FileInputStream("Clips\\lips\\lips"+selcrimid+".gif");
			DataInputStream lipsdin=new DataInputStream(lipsfin);
			len=lipsfin.available();
			int lipspixels[]=new int[len];
			i=0;
			while(lipsfin.available()>0)
			{
				lipspixels[i]=lipsdin.readInt();
				i++;
			}
			lipsfin.close();
			lipsdin.close();
			int lipsht=Integer.parseInt(rs.getString(6));
			 a5.add(new Integer(Integer.parseInt(selcrimid)));
			a5.add(new Integer(lipsht));
			lipsAL.add(a5);
			Image lipsclipimg=createImage(new MemoryImageSource(200,lipsht,lipspixels,0,200));
			ImageIcon lipsicn=new ImageIcon(lipsclipimg);
			cLips.addItem(lipsicn);
		
		     ArrayList a6=new ArrayList();
	        FileInputStream chinfin=new FileInputStream("Clips\\chin\\chin"+selcrimid+".gif");
			DataInputStream chindin=new DataInputStream(chinfin);
			len=chinfin.available();
			int chinpixels[]=new int[len];
			i=0;
			while(chinfin.available()>0)
			{
				chinpixels[i]=chindin.readInt();
				i++;
			}
			chinfin.close();
			chindin.close();
			int chinht=Integer.parseInt(rs.getString(7));
			 a6.add(new Integer(Integer.parseInt(selcrimid)));
			a6.add(new Integer(chinht));
			chinAL.add(a6);
			Image chinclipimg=createImage(new MemoryImageSource(200,chinht,chinpixels,0,200));
			ImageIcon chinicn=new ImageIcon(chinclipimg);
			cChin.addItem(chinicn);
			
			}					
	}catch(Exception e)
	{ 
		JOptionPane.showMessageDialog(this,e.getMessage(), "Construct Face", JOptionPane.ERROR_MESSAGE);
	}

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==bClear)
		{
			cHair.setSelectedIndex(0);
			cForehead.setSelectedIndex(0);
			cEyes.setSelectedIndex(0);
			cNose.setSelectedIndex(0);
			cLips.setSelectedIndex(0);
			cChin.setSelectedIndex(0);
			
		}

		if(ae.getSource()==bCancel)
		{
			setVisible(false);
			dispose();
		}

		if(ae.getSource()==cHair)
		{
		 /*ImageIcon stt=(ImageIcon)cHair.getSelectedItem();
		 ht1=stt.getIconHeight();
		 int wt1=stt.getIconWidth();
		 lHair1.setBounds(400,200,wt1,ht1);
		 ha1=200+ht1;
		 lHair1.setIcon(stt);
		 hr=cHair.getSelectedIndex();*/

		 int selind=cHair.getSelectedIndex();
		ArrayList selhair=(ArrayList)hairAL.get(selind);
		Integer selhairht=(Integer)selhair.get(1);
		finalhairht=selhairht.intValue();
		Integer selhairid=(Integer)selhair.get(0);
		cids[0]=selhairid.intValue();		
		ImageIcon selhairicn=(ImageIcon)cHair.getSelectedItem();
		Rectangle recthair=lHair1.getBounds();
		lHair1.setBounds(new Rectangle((int)recthair.getX(),(int)recthair.getY(),(int)recthair.getWidth(), finalhairht));
		lHair1.setIcon(selhairicn);
		}
		
		if(ae.getSource()==cForehead)
		{

		/*ImageIcon stt1=(ImageIcon)cForehead.getSelectedItem();
		ht2=stt1.getIconHeight();
		int wt2=stt1.getIconWidth();
		lForehead1.setBounds(400,ha1,wt2,ht2);
		ha2=ha1+ht2;
		lForehead1.setIcon(stt1);
		fr=cForehead.getSelectedIndex();*/

		int selind=cForehead.getSelectedIndex();
		ArrayList selfh=(ArrayList)fhAL.get(selind);
		Integer selfhht=(Integer)selfh.get(1);
		finalfhht=selfhht.intValue();
		Integer selfhid=(Integer)selfh.get(0);
		cids[1]=selfhid.intValue();		
		ImageIcon selfhicn=(ImageIcon)cForehead.getSelectedItem();
		Rectangle rectfh=lForehead1.getBounds();
		Rectangle recthair=lHair1.getBounds();
		lForehead1.setBounds(new Rectangle((int)rectfh.getX(),(int)recthair.getY()+finalhairht,(int)rectfh.getWidth(), finalfhht));
		lForehead1.setIcon(selfhicn);
		}
	
		if(ae.getSource()==cEyes)
		{
		/*ImageIcon sttEye=(ImageIcon)cEyes.getSelectedItem();
		ht3=sttEye.getIconHeight();
		int wt3=sttEye.getIconWidth();
		lEyes1.setBounds(400,ha2,wt3,ht3);
		ha3=ha2+ht3;
		lEyes1.setIcon(sttEye);
		ey=cEyes.getSelectedIndex();*/

		int selind=cEyes.getSelectedIndex();
		ArrayList seleyes=(ArrayList)eyesAL.get(selind);
		Integer seleyesht=(Integer)seleyes.get(1);
		finaleyesht=seleyesht.intValue();
		Integer seleyesid=(Integer)seleyes.get(0);
		cids[2]=seleyesid.intValue();		
		ImageIcon seleyesicn=(ImageIcon)cEyes.getSelectedItem();
		Rectangle recthair=lHair1.getBounds();
		Rectangle rectfh=lForehead1.getBounds();
		Rectangle recteyes=lEyes1.getBounds();
		lEyes1.setBounds(new Rectangle((int)rectfh.getX(),(int)recthair.getY()+finalhairht+finalfhht,(int)rectfh.getWidth(), finaleyesht));
		lEyes1.setIcon(seleyesicn);
	
		}

		if(ae.getSource()==cNose)
		{
			/*ImageIcon sttNose=(ImageIcon)cNose.getSelectedItem();
			ht4=sttNose.getIconHeight();
			int wt4=sttNose.getIconWidth();
			lNose1.setBounds(400,ha3,wt4,ht4);
			ha4=ha3+ht4;
			lNose1.setIcon(sttNose);
			no=cNose.getSelectedIndex();*/
			
			int selind=cNose.getSelectedIndex();
		ArrayList selnose=(ArrayList)noseAL.get(selind);
		Integer selnoseht=(Integer)selnose.get(1);
		finalnoseht=selnoseht.intValue();
		Integer selnoseid=(Integer)selnose.get(0);
		cids[3]=selnoseid.intValue();		
		ImageIcon selnoseicn=(ImageIcon)cNose.getSelectedItem();
		Rectangle rectnose=lNose1.getBounds();
		Rectangle recthair=lHair1.getBounds();
        Rectangle rectfh=lForehead1.getBounds();
        Rectangle recteyes=lEyes1.getBounds();
		lNose1.setBounds(new Rectangle((int)rectfh.getX(),(int)recthair.getY()+finalhairht+finalfhht+finaleyesht,(int)rectfh.getWidth(), finalnoseht));
		lNose1.setIcon(selnoseicn);

		}

		if(ae.getSource()==cLips)
		{
			/*ImageIcon sttLips=(ImageIcon)cLips.getSelectedItem();
			ht5=sttLips.getIconHeight();
			int wt5=sttLips.getIconWidth();
			lLips1.setBounds(400,ha4,wt5,ht5);
			ha5=ha4+ht5;
			lLips1.setIcon(sttLips);
			lip=cLips.getSelectedIndex();*/

			int selind=cLips.getSelectedIndex();
		ArrayList sellips=(ArrayList)lipsAL.get(selind);
		Integer sellipsht=(Integer)sellips.get(1);
		finallipsht=sellipsht.intValue();
		Integer sellipsid=(Integer)sellips.get(0);
		cids[4]=sellipsid.intValue();		
		ImageIcon sellipsicn=(ImageIcon)cLips.getSelectedItem();
		Rectangle rectfh=lForehead1.getBounds();
		Rectangle recthair=lHair1.getBounds();
        Rectangle recteyes=lEyes1.getBounds();
		Rectangle rectnose=lNose1.getBounds();
        Rectangle rectlips=lLips1.getBounds();
		lLips1.setBounds(new Rectangle((int)rectfh.getX(),(int)recthair.getY()+finalhairht+finalfhht+finaleyesht+finalnoseht,(int)rectfh.getWidth(), finallipsht));
		lLips1.setIcon(sellipsicn);
		
		}

		if(ae.getSource()==cChin)
		{
			/*ImageIcon sttChin=(ImageIcon)cChin.getSelectedItem();
			ht6=sttChin.getIconHeight();
			int wt6=sttChin.getIconWidth();
			lChin1.setBounds(400,ha5,wt6,ht6);
			lChin1.setIcon(sttChin);
			ch=cChin.getSelectedIndex();*/
			
			int selind=cChin.getSelectedIndex();
		ArrayList selchin=(ArrayList)chinAL.get(selind);
		Integer selchinht=(Integer)selchin.get(1);
		finalchinht=selchinht.intValue();
		Integer selchinid=(Integer)selchin.get(0);
		cids[5]=selchinid.intValue();		
		ImageIcon selchinicn=(ImageIcon)cChin.getSelectedItem();
		Rectangle rectfh=lForehead1.getBounds();
		Rectangle recthair=lHair1.getBounds();
        Rectangle recteyes=lEyes1.getBounds();
		Rectangle rectnose=lNose1.getBounds();
        Rectangle rectlips=lLips1.getBounds();
        Rectangle rectchin=lChin1.getBounds();
		lChin1.setBounds(new Rectangle((int)rectfh.getX(),(int)recthair.getY()+finalhairht+finalfhht+finaleyesht+finalnoseht+finallipsht,(int)rectfh.getWidth(), finalchinht));
		lChin1.setIcon(selchinicn);
		}
		if(ae.getSource()==bSave)
	{
			Rectangle recthair=lHair1.getBounds();
		lHair1.setBounds(new Rectangle((int)recthair.getX(),(int)recthair.getY(),(int)recthair.getWidth(),(int)recthair.getHeight()));

		Rectangle rectfh=lForehead1.getBounds();				
		nextheight=(int)recthair.getY()+(int)recthair.getHeight();
		lForehead1.setBounds(new Rectangle((int)rectfh.getX(),nextheight,(int)rectfh.getWidth(), (int)rectfh.getHeight()));

		Rectangle recteyes=lEyes1.getBounds();				
		nextheight=nextheight+(int)rectfh.getHeight();
		lEyes1.setBounds(new Rectangle((int)recteyes.getX(),nextheight,(int)recteyes.getWidth(), (int)recteyes.getHeight()));

        Rectangle rectnose=lNose1.getBounds();				
		nextheight=nextheight+(int)recteyes.getHeight();
		lNose1.setBounds(new Rectangle((int)rectnose.getX(),nextheight,(int)rectnose.getWidth(), (int)rectnose.getHeight()));

        Rectangle rectlips=lLips1.getBounds();				
		nextheight=nextheight+(int)rectnose.getHeight();
		lLips1.setBounds(new Rectangle((int)rectlips.getX(),nextheight,(int)rectlips.getWidth(), (int)rectlips.getHeight()));

        Rectangle rectchin=lChin1.getBounds();				
		nextheight=nextheight+(int)rectlips.getHeight();
		lChin.setBounds(new Rectangle((int)rectchin.getX(),nextheight,(int)rectchin.getWidth(), (int)rectchin.getHeight()));

		nextheight=nextheight+(int)rectchin.getHeight();

		int crid=0;
		try
		{
			ResultSet rs=st.executeQuery("select max(crimeid) from face_suspectphoto");
			if(rs.next())
			{
				String crimeid=rs.getString(1);
				if(crimeid!=null)
					crid=Integer.parseInt(crimeid);
				crid++;
				writePhoto(cids,crid);
				st.executeUpdate("insert into face_suspectphoto(crimeid, suspectphoto, photo_height) values("+crid+",'suspect"+crid+".gif',"+nextheight+")");
				for(int i=0;i<6;i++)
				{
					st.executeUpdate("insert into face_suspects(crimeid, cid) values("+crid+","+cids[i]+")");
				}
				JOptionPane.showMessageDialog(this,"Photo saved successfully for the Crime Identification Number: "+crid, "Crime Status",  JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Construct Image", JOptionPane.ERROR_MESSAGE);
		}
	}
	}
	public void writePhoto(int a[],int crid)
	{
		try
		{
		FileOutputStream suspectfout=new FileOutputStream("Suspects\\suspect"+crid+".gif");
		DataOutputStream suspectdout=new DataOutputStream(suspectfout);

		FileInputStream hairfin=new FileInputStream("Clips\\hair\\hair"+cids[0]+".gif");
		DataInputStream hairdin=new DataInputStream(hairfin);
		while(hairfin.available()>0)
		{
			suspectdout.writeInt(hairdin.readInt());
		}
		hairfin.close();
		hairdin.close();

		FileInputStream fhfin=new FileInputStream("Clips\\forehead\\forehead"+cids[1]+".gif");
		DataInputStream fhdin=new DataInputStream(fhfin);
		while(fhfin.available()>0)
		{
			suspectdout.writeInt(fhdin.readInt());
		}
		fhfin.close();
		fhdin.close();

        FileInputStream eyesfin=new FileInputStream("Clips\\eyes\\eyes"+cids[2]+".gif");
		DataInputStream eyesdin=new DataInputStream(eyesfin);
		while(eyesfin.available()>0)
		{
			suspectdout.writeInt(eyesdin.readInt());
		}
		eyesfin.close();
		eyesdin.close();

		FileInputStream nosefin=new FileInputStream("Clips\\nose\\nose"+cids[3]+".gif");
		DataInputStream nosedin=new DataInputStream(nosefin);
		while(nosefin.available()>0)
		{
			suspectdout.writeInt(nosedin.readInt());
		}
		nosefin.close();
		nosedin.close();

        FileInputStream lipsfin=new FileInputStream("Clips\\lips\\lips"+cids[4]+".gif");
		DataInputStream lipsdin=new DataInputStream(lipsfin);
		while(lipsfin.available()>0)
		{
			suspectdout.writeInt(lipsdin.readInt());
		}
		lipsfin.close();
		lipsdin.close();

        FileInputStream chinfin=new FileInputStream("Clips\\chin\\chin"+cids[5]+".gif");
		DataInputStream chindin=new DataInputStream(chinfin);
		while(chinfin.available()>0)
		{
			suspectdout.writeInt(chindin.readInt());
		}
		chinfin.close();
		chindin.close();
		suspectdout.close();
		suspectfout.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
class ConstructFace
{
	public static void main(String ar[])
	{
		Construct ob=new Construct();
		ob.show();
		ob.addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
      });
	}
}