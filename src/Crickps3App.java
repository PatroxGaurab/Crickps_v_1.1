import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Crickps3App extends Applet implements Runnable{

	private Thread t = null;
	private Dimension d;
	private String str;
	private String intro="Welcome Back To Gptr.org";
	private int wd,ht;
	private int[] xc=new int[4];
	private int[] yc=new int[4];
	private int ballX;
	private int ballY;
	private int ballRad;
	private double v = 27;
	private double u;
	private static final double g = 3;
	private double time_del = 0.005;
	private double time_del_tar = 1;
	private static final int time_del_slp = 2;
	private double actual_y;
	private double ball_progress;
	private double ht_cam;
	private boolean stopflag;
	private double tmp1;
	private boolean forflag;
	private boolean tar_for_flag;
	private int tarX=200;
	private int tarY=150;
	private Random rand_obj=new Random();
	private ImageIcon bgimgicon;
	private ImageIcon bgimgicon2;
	private Image bgimage;
	private Image im2,im3;
	private Graphics gim;
	private Graphics g2;
	private JButton sp_in = new JButton("Speed++");
	
	private int tmpX;
	private int tmpY;
	
	private int curX=50;
	private int disX=50;
	private int bounce=50;
	private int slope;
	
	private double real_slope;
	
	private double ht_ball=8.0;
	
	private Rectangle velR = new Rectangle(10,10,(800),3);
	
	public void init()
	{
		stopflag=true;
		t = new Thread(this);
		resize(600,450);
		d=getSize();
		ht=d.height;
		wd=ht*1024/728;
		bgimage = createImage(wd,ht);
		bgimgicon = new ImageIcon(this.getClass().getResource("images2.jpg"));
		im2=bgimgicon.getImage();
		gim = bgimage.getGraphics();
		
		bgimgicon2 = new ImageIcon(this.getClass().getResource("screenshot4.jpg"));
		im3=bgimgicon2.getImage();
		
		//setBackground(Color.GREEN);
		
		
		//doDraw(gim);
		t.start();
		addMouseListener(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						
						intro="";
						tmpX=e.getX();
						System.out.print(ballX);
						stopflag=false;
						
					}
					
					
				}
		);
		
		addMouseMotionListener(
				new MouseAdapter()
				{
					public void mouseMoved(MouseEvent e)
					{
						tmpX=e.getX();
						//System.out.println(tmpX);
					}
					
					public void mouseDragged(MouseEvent me)
					{
						if(me.getY() >= 7 && me.getY() <= 17)
							curX=me.getX();
						else if(me.getY() >= 22 && me.getY() <= 32)
							disX=me.getX();
						else if(me.getY() >= 37 && me.getY() <= 47)
							bounce=me.getX();
						else if(me.getY() >= 52 && me.getY() <= 62)
							slope=me.getX();
						
						time_del=0.003+(curX*3.0/100000);
						
						v=(disX*5.8/100);
					}
				}
		);
	}
	
	public void start()
	{
		
		
		resize(wd,ht);
		
		/*forflag=true;
		ballX=(wd)*5/10;
		tmp1=ht*(1-((8.0/22)+(28.66666667*4/(154*22))));
		//ballY=ht-(ht*8/10+ht*12*4/1540);
		ballY=(int)tmp1;
		actual_y=(ht*8.0/22);
		u=Math.sqrt(2*g*actual_y*ht/22.0);
		double test_x=(wd*0.3);
		int tmp_y=ballY;
		double time_cntX=0;
		double time_cntY=0;
		double tmp_r=15;
		ballRad=15;
		double ball_down;
		ht_cam=(220/6);
		double pitch_len=(66.0*ht)/22;
		
		tmp1=(wd/7);
		xc[0]=(int)tmp1;
		tmp1=(wd*6)/7;
		xc[1]=(int)tmp1;
		tmp1=wd*(0.5+(5.0*150/14)/223);
		xc[2]=(int)tmp1;
		tmp1=wd*(0.5-(5.0*150/14)/223);
		xc[3]=(int)tmp1;
		
		//yc[0]=ht*(1-(220*4/(154*6))/100);
		tmp1=ht*(1-(36.6666666667*4/154)/22);
		yc[0]=(int)tmp1;
		yc[1]=(int)tmp1;
		tmp1=ht*(1-(11.6666666667/22));
		yc[2]=(int)tmp1;
		yc[3]=(int)tmp1;*/
		
		
		
		
		str="Width = " + wd + " Height = " + ht;
		repaint();
	}
	
	/*public void set_all_values()
	{
		forflag=true;
		ball_progress=0.0;
		ballX=(wd)*5/10;
		tmp1=ht*(1-((8.0/22)+(28.66666667*4/(154*22))));
		//ballY=ht-(ht*8/10+ht*12*4/1540);
		ballY=(int)tmp1;
		actual_y=(ht*8.0/22);
		u=Math.sqrt(2*g*actual_y*ht/22.0);
		double test_x=(wd*0.3);
		int tmp_y=ballY;
		double time_cntX=0;
		double time_cntY=0;
		double tmp_r=15;
		ballRad=15;
		double ball_down;
		ht_cam=(220/6);
		double pitch_len=(66.0*ht)/22;
		
		tmp1=(wd/7);
		xc[0]=(int)tmp1;
		tmp1=(wd*6)/7;
		xc[1]=(int)tmp1;
		tmp1=wd*(0.5+(5.0*150/14)/223);
		xc[2]=(int)tmp1;
		tmp1=wd*(0.5-(5.0*150/14)/223);
		xc[3]=(int)tmp1;
		
		//yc[0]=ht*(1-(220*4/(154*6))/100);
		tmp1=ht*(1-(36.6666666667*4/154)/22);
		yc[0]=(int)tmp1;
		yc[1]=(int)tmp1;
		tmp1=ht*(1-(11.6666666667/22));
		yc[2]=(int)tmp1;
		yc[3]=(int)tmp1;
		
	}*/
	
	public void run()
	{
		while(stopflag)
		{
			d=getSize();
			ht=d.height;
			wd=ht*1024/728;
			bgimage = createImage(wd,ht);
			gim = bgimage.getGraphics();
			slope=(wd/2);
			resize(wd,ht);
			draw_intro(getGraphics());
		}
		tarX=200;
		tarY=250;
		
		forflag=true;
		ball_progress=0.0;
		//ballX=(wd)*5/10;
		tmp1=ht*(1-((ht_ball/22)+(28.66666667*4/(154*22))));
		//ballY=ht-(ht*8/10+ht*12*4/1540);
		ballY=(int)tmp1;
		actual_y=(ht*ht_ball/22);
		u=Math.sqrt(2*g*actual_y*ht/22.0);
		double test_x=(wd*0.3);
		int tmp_y=ballY;
		double time_cntX=0;
		double time_cntY=0;
		double tmp_r=15;
		ballRad=15;
		double ball_down;
		ht_cam=(220/6);
		double pitch_len=(66.0*ht)/22;
		
		tmp1=(wd/7);
		xc[0]=(int)tmp1;
		tmp1=(wd*6)/7;
		xc[1]=(int)tmp1;
		tmp1=wd*(0.5+(5.0*150/14)/223);
		xc[2]=(int)tmp1;
		tmp1=wd*(0.5-(5.0*150/14)/223);
		xc[3]=(int)tmp1;
		
		//yc[0]=ht*(1-(220*4/(154*6))/100);
		tmp1=ht*(1-(36.6666666667*4/154)/22);
		yc[0]=(int)tmp1;
		yc[1]=(int)tmp1;
		tmp1=ht*(1-(11.6666666667/22));
		yc[2]=(int)tmp1;
		yc[3]=(int)tmp1;
		
		int ttt=0;
		
		while( t != null )
		{
			
			time_del_tar=(rand_obj.nextDouble()%5+1);
			
			ballX=tmpX;
			
			
			double tmp2;
			tar_for_flag=true;
			//System.out.println(ttt++);
			//set_all_values();
			
			tarX=200;
			tarY=150;
			
			forflag=true;
			ball_progress=0.0;
			//ballX=(wd)*5/10;
			tmp1=ht*(1-((ht_ball/22)+(28.66666667*4/(154*22))));
			//ballY=ht-(ht*8/10+ht*12*4/1540);
			ballY=(int)tmp1;
			actual_y=(ht*ht_ball/22);
			u=Math.sqrt(2*g*actual_y*ht/22.0);
			//test_x=(wd*0.3);
			test_x=ballX;
			tmp_y=ballY;
			time_cntX=0;
			time_cntY=0;
			tmp_r=15;
			
			real_slope=(slope-tmpX)/(ballY-52.0);
			//System.out.println(real_slope);
			
			ballRad=15;
			ht_cam=(220/6);
			pitch_len=(66.0*ht)/22;
			
			tmp1=(wd/7);
			xc[0]=(int)tmp1;
			tmp1=(wd*6)/7;
			xc[1]=(int)tmp1;
			tmp1=wd*(0.5+(5.0*150/14)/223);
			xc[2]=(int)tmp1;
			tmp1=wd*(0.5-(5.0*150/14)/223);
			xc[3]=(int)tmp1;
			
			//yc[0]=ht*(1-(220*4/(154*6))/100);
			tmp1=ht*(1-(36.6666666667*4/154)/22);
			yc[0]=(int)tmp1;
			yc[1]=(int)tmp1;
			tmp1=ht*(1-(11.6666666667/22));
			yc[2]=(int)tmp1;
			yc[3]=(int)tmp1;
			
			
			
			repaint();
			while( !stopflag  )
			{
			//paint(getGraphics());
				repaint();
				try{
					if(ball_progress >= 66.0*ht/22)
					{
						//stop();
						//stopflag=true;
						//start();
						Thread.sleep(1000);
						stopflag=true;
						//break;
					}
					time_cntX++;
					time_cntY++;
				
				
					//ballY+=(time_cnt*time_del/100)*(time_cnt*time_del/100)*g/2;
					//ball_down = 20-ht+(ballY+((((time_cntY*time_del)/100)*((time_cntY*time_del)/100)*g)/2));
					ball_progress=((v*time_del*time_cntX)*ht/22.0);
				
					if( tar_for_flag )
					{
						tmp2=tarX+time_del_tar;
					}
					else
						tmp2=tarX-time_del_tar;
				
					tarX=(int)tmp2;
					tarY=150;
				
					if( tarX >= 450)
						tar_for_flag=false;
					if( tarX <= 200 )
						tar_for_flag=true;
				
					//actual_y-=(ball_down*(ball_progress+4)/(154+ball_progress));
					if(!forflag)
					{
						//ball_down = 20-ht+(ballY+(u*(time_cntY*time_del/100))-((time_cntY*time_del/100)*(time_cntY*time_del/100)*g/2));
						actual_y=((u*(time_cntY*time_del))-((((time_cntY*time_del))*((time_cntY*time_del))*g)*ht/(2.0*22.0)));
					
						ball_down=(ht*36.666666667/22)-actual_y;
						tmp1=(ball_down*(ball_progress+ht*4.0/22)/(ball_progress+154.0*ht/22));
						//ballY=tmp_y-(int)(ball_down*(ball_progress+ht*4.0/22)/(ball_progress+154.0*ht/22));
						ballY=(ht)-(int)(actual_y+tmp1);
					}
					else
					{
						actual_y=(ht*ht_ball/22)-((((time_cntY*time_del))*((time_cntY*time_del))*g)*ht/(2.0*22));
						ball_down = (ht*36.666666667/22)-actual_y;
						tmp1=(ball_down*(ball_progress+ht*4.0/22)/(ball_progress+154.0*ht/22));
						ballY=(ht)-(int)(actual_y+tmp1);
					}
				
					if(actual_y <= 0)
					{
						time_cntY=0;
						forflag=false;
						
						u=u*(bounce*2.0/1000);
						//ball_down = 20-ht+(ballY+((time_cntY*time_del/100)*(time_cntY*time_del/100)*g/2));
					
					}
					/*else
					{
						forflag=true;
					}*/
				
					//test_x+=0.05;
					
					test_x+=(real_slope);
					//tmp_r=tmp_r-0.0037037037;
					ballX=(int)test_x;
					ballRad=(int)(tmp_r-0.0037037037*ball_progress);
					str=" Actual-Y = " + actual_y + " Ball_prog = " + ball_progress + " Ball_Down = " + ball_down;
				
					int w,h;
					w=im2.getWidth(null);
					h=im2.getHeight(null);
				
					Rectangle r1=new Rectangle(tarX,tarY,w,h);
					Rectangle r2=new Rectangle(ballX,ballY,ballRad,ballRad);
				
					if(r1.intersects(r2) && (ball_progress >= 1340) )
					{
						str="Yes You Have Hit The Target..!!";
						repaint();
						stopflag=true;
						Thread.sleep(1000);
					}
				
					Thread.sleep(time_del_slp);
					//break;
				
				}catch(InterruptedException e){}
			
			}
			
			ballX=tmpX;
		}
		
	}
	
	public void paint(Graphics g)
	{
		
		doDraw(gim);
		
		//g.drawImage(im2, 0, 0, null);
		g.drawImage(bgimage,0,0,null);
		
	}
	
	public void doDraw(Graphics g)
	{
		//setBackground(Color.GREEN);
		
		
		
		g.setColor(Color.GREEN);
		g.fillRect(0,0,wd,ht);
		
		
		g.setColor(Color.PINK);
		g.fillRect(10,10,(wd-20),3);
		
		g.setColor(Color.BLACK);
		g.fillOval(curX,7,3,10);
		
		g.setColor(Color.YELLOW);
		g.fillRect(10,25,(wd-20),3);
		
		g.setColor(Color.BLACK);
		g.fillOval(disX,22,3,10);
		
		
		g.setColor(Color.CYAN);
		g.fillRect(10,40,(wd-20),3);
		
		g.setColor(Color.BLACK);
		g.fillOval(bounce,37,3,10);
		
		
		g.setColor(Color.MAGENTA);
		g.fillRect(10,55,(wd-20),3);
		
		g.setColor(Color.BLACK);
		g.fillOval(slope,52,3,10);
		
		//g.drawImage(im3,0,0,this);
		
		g.setColor(Color.YELLOW);
		g.fillPolygon(xc,yc,4);
		
		g.setColor(Color.RED);
		g.drawString(str,100,100);
		
		g.drawImage(im2,tarX,tarY,this);
		
		g.fillOval(ballX,ballY, ballRad, ballRad);
		

	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void draw_intro(Graphics ig)
	{
		setBackground(Color.PINK);
		ig.drawString(intro, 10, 10);
	}
}
