package �ɻ���ս;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;



/**
 * �ɻ��࣬�õĻ��Ķ���һ��ֻ�ǵ��õķ�����ͬ
 * @author yan
 *
 */
public class Plane {
	public  int xspeed =0;
	public  int yspeed = 0;
	public double dspeed=0;
	public int x, y;
	public  int size = 60;
	private WarUI warui;
	public int img=1;//�л�ͼƬ���
	public int life=100;
	
	private  boolean good=false;
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	private  boolean live=true;
	
	private Random random = new Random();
	private ImageIcon diren;//���˷ɻ�
	private int[] addr={30,60,80,100,130,150,180,200,230,260,290,320,350};//���ɻ����ֵ�xλ��
	private int[] xspeeds={0,0,-1,0,1,0,1,0,-1,0};
	
/**
 * 
 * @param x ��������xλ��
 * @param y ��������xλ��
 * @param warui
 * @param size ������С
 * @param good �û�
 */
	public Plane(int x, int y, WarUI warui,int size,boolean good) {
		this.x = x;
		this.y = y;
		this.warui = warui;
		this.good=good;
		this.size=size;
		
	}
	/**
	 * ���˳�ʼλ�������
	 * @param good �û�
	 * @param warui �������
	 */
	public Plane(boolean good,WarUI warui) {
		this.good=false;
		this.warui = warui;
		img=random.nextInt(3)+1;
		
		//��÷ɻ���y�����ٶ�
		dspeed=1;	
		
		this.x=addr[random.nextInt(13)];
		this.y=random.nextInt(50)+10;
		//ȷ���ɻ�x������ٶ�
		this.xspeed=xspeeds[random.nextInt(10)];		
	}
	

	/**
	 * // ��ϼ����¼����ƶ�����
	 */
	public void left() {
		xspeed=-3;
	}

	public void right() {
		xspeed=+3;
	}

	public void up() {
		yspeed=-3;
	}

	public void down() {
		yspeed=+3;
	}

	public void initXc() {
		xspeed = 0;
	}

	public void initYc() {
		yspeed = 0;
	}


	
	/**
	 * �ҵĺ÷ɻ��ƶ�����
	 */
	public void move() {
			this.x += xspeed;
			this.y += yspeed;
			//��������
			if(y<=30)y=30;
			if(x<=0){x=WarUI.WIDTH-30;}
			if(x>=WarUI.WIDTH){x=20;}
			//if(x+size>=WarUI.WIDTH){x=WarUI.WIDTH-size;}
			if(y+size>=WarUI.HEIGHT){y=WarUI.HEIGHT-size;}

	}
	//���ɻ�move����
	public void dmove(){
		this.y+=dspeed;
		this.x+=xspeed;
		//��������
		if(y<=30)y=30;
		if(x<=0||x+size>=WarUI.WIDTH){
			xspeed=-xspeed;
			}
		if(y+size>=WarUI.HEIGHT){//��������±߽磬�򽫻��ɻ��Ƴ�
			warui.planes.remove(this);
		}

	}
	ImageIcon mimg1 = new ImageIcon("Ӣ��\\hero1.png");
	ImageIcon mimg2 = new ImageIcon("Ӣ��\\hero2.png");
	/**
	 * �����ɻ�������Ѫ��
	 * @param g
	 */
	public void draw(Graphics g) {
		//�жϷɻ�������ֵ��С��0���������󣬲����ػ��������
	
		if(life<=0){
		this.setLive(false);
		WarUI.planes.remove(this);
		}
		
		if(good==true&&live==true){//�Լ�
			//���Լ���Ѫ����������λ�ò�һ��
			//g.drawRect(x+2,y+10,size+15,size-10);//���������ɻ�����ײ���
			Color c=g.getColor();
			g.setColor(Color.blue);
			g.drawRect(20, 550, 100, 20);//�����ο�
			int w=100*life/100;
			g.fillRect(20, 550, w, 20);//����
			g.setColor(c);
			//����л�ͼƬ������ɻ�����Ч��
			int i = random.nextInt(100);
			if (i >50) {
				g.drawImage(mimg1.getImage(), x, y, size+20, size+20, warui);
			} else {
				g.drawImage(mimg2.getImage(), x, y, size+20, size+20, warui);
			
			}
		}
		if(good==false&&live==true){
			//g.drawRect(x,y+20,size-10,size-30);//���������ɻ�����ײ���
			 diren = new ImageIcon("����\\enemy"+img+".png");
			g.drawImage(diren.getImage(),x,y,size ,size,warui);
			//Ѫ��
			Color c=g.getColor();
			g.setColor(Color.red);
			g.drawRect(x+5, y-12, 50, 10);
			int w=50*life/100;
			g.fillRect(x+5, y-12, w, 10);
			g.setColor(c);
			}
			
		}
		
		

	/**
	 * ���𷽷�
	 */
	public void fire(){
		Bullet b=new Bullet(x,y,warui,good);
		WarUI.bullets.add(b);
		
	}
	/**
	 * ���һ������img������ʾ���ֵл������㱬ըʱͼƬ�л�
	 */
	 
	public void dfire(){
		Bullet b=new Bullet(x,y,warui,false,this.img);
		WarUI.bullets.add(b);
		
	}
	
	
////�õ���ΧС����,(��ײ��⸨����Rectangle)
	public Rectangle getRect(){
		return new Rectangle(x,y+20,size-10,size-30);
	}
	
	public Rectangle getRect2(){
		return new Rectangle(x+2,y+10,size+15,size-15);
	}
	/**
	 * ��Ѫ�鷽��
	 * @param b  Ѫ�����
	 * @return
	 */
	public boolean eat (Blood b){
		if(this.isGood()&&this.live&&b.isLive()&&this.getRect2().intersects(b.getRect())){
			this.life=100;
			b.setLive(false);
			b.setBlood();
			return true;
		}
		
		return false;
	}
/**
 * ʵ�ֱ�����л�����ײ
 * @param planes
 * @return
 */
	public boolean pengplane(List<Plane> planes) {
		for (int i = 0; i < planes.size(); i++) {
			Plane p=planes.get(i);
			if (this.live&&this.getRect2().intersects(p.getRect()) && p.isLive() == true&&this.good!=p.isGood()) {// ����Ƿ���ײ,��ײ֮�󣬻����ж�̹�������������������ӵ����Ǹ��ط����ǻ���ʧ	
				Explode e = new Explode(x, y, warui,p.img);// ���һ������img������ʾ���ֵл������㱬ըʱͼƬ�л�
				Explode e2 = new Explode(x, y, warui,this.img);
				warui.explodes.add(e2);
				warui.explodes.add(e);//�½���ը����
				p.live=false;
				this.life=0;
				this.live = false;
				return true;
			}
		}
		return false;
	}	
	
	
	
	
	

}
