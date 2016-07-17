package �ɻ���ս;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

public class Bullet {
	/**
	 * �����ӵ��࣬���������ӵ����ٶȣ���ѧ������
	 * �ӵ����趨Ϊֻ�����·���
	 */
	private static int yspeed = -2;
	public static final int WIDTH = 8;
	public static final int HEIGHT =15 ;
	public int x,y;
	public WarUI warui;
	private int img=0;//0��ʾ���Լ��ķɻ�
	public boolean good;
	private boolean live=true;
	/**
	 * �����Ĺ��췽��
	 * @param x2 xλ��
	 * @param y2 yλ��
	 * @param warui2  ���ഫ�����Ķ���
	 * @param good �û�
	 */
	public Bullet(int x,int y,WarUI warui,boolean good){
		this.x=x;
		this.y=y;
		this.good=good;
		this.warui=warui;
	}
	/**
	 * �л��Ĺ��췽��
	 * @param x2 xλ��
	 * @param y2 yλ��
	 * @param warui2  ���ഫ�����Ķ���
	 * @param good �û�
	 * @param img  ͼƬ��ţ�ȷ�����汬ըʱ���ػ���ͼƬ
	 */
	public Bullet(int x, int y, WarUI warui2, boolean good, int img) {
		this.x=x;
		this.y=y;
		this.good=good;
		this.warui=warui;
		this.img=img;
	
	}
	/**
	 * ���ӵ��ķ���
	 */
	ImageIcon imgp3 = new ImageIcon("Ӣ��\\zidan.jpg");
	ImageIcon dimgp = new ImageIcon("����\\bullet2.png");
	public void draw(Graphics g){ 
		if(good==true&&live==true){
		g.drawImage(imgp3.getImage(), x+30-3, y,WIDTH,HEIGHT , warui);
		}
		if(good==false&&live==true){
			g.drawImage(dimgp.getImage(), x+30-3, y+30,WIDTH,HEIGHT , warui);
		}
		
	} 
	/**
	 * �ӵ����ƶ��Լ��ӵ���������
	 */
	public void move(){
		if(warui.score>5000){
			 yspeed = -3;
		}else if(warui.score>10000){
			yspeed=-4;
		}else{
			yspeed=-2;
		}
		
		if(good==true){
		y=y+yspeed;
		}else{
			y=y-yspeed;
		}
		if(y<0||y>warui.HEIGHT){
			WarUI.bullets.remove(this);
		}
	}
	
/**
 * �õ���ΧС����,(��ײ��⸨����Rectangle)
 * @return
 */
	public Rectangle getRect(){
		return new Rectangle(x+30,y,WIDTH,HEIGHT);
	}
	
	/**
	 * 	// ��ӱ�ը�����б������
	 * @param t
	 * @return
	 */
		public boolean hitplane(Plane p) {
			// ����Ƿ���ײ,��ײ֮�󣬻����ж�̹�������������������ӵ����Ǹ��ط����ǻ���ʧ	
			if (this.live&&this.getRect().intersects(p.getRect()) && p.isLive() == true&&this.good!=p.isGood()) {
				Explode e = new Explode(x, y, warui,p.img);// ���һ������img������ʾ���ֵл������㱬ըʱͼƬ�л�
				if(p.isGood()){//���ɻ�����Ѫ��
				p.life-=40;//����Ǻõļ���40��
				}else{
					p.life-=50;
				}
				warui.explodes.add(e);//�½�һ����ը����
				this.live = false;//�ӵ��Ƴ�
				return true;
			}
			return false;
		}
	/**
	 * ����hitplane����
	 * 	��һ������Ļ��ɻ������������ӣ�ʵ���ϻ���Ҫ�����������ΪPanle��hitplane����
	 * @param planes  ��������list����ĵл�
	 * @return
 */
		public boolean hitplane(List<Plane> planes) {
			for (int i = 0; i < planes.size(); i++) {
				if (hitplane(planes.get(i))) {
					warui.score+=100;
					return true;
				}
			}
			return false;
		}	
		
		/**
		 * �ӵ��򱾻��ķ���
		 * ����getRect()������һ�������Ա�����������
		 * @param p
		 * @return
		 */
		public boolean hitmyplane(Plane p) {
			// ����Ƿ���ײ,��ײ֮�󣬻����ж�̹�������������������ӵ����Ǹ��ط����ǻ���ʧ	
			if (this.live&&this.getRect().intersects(p.getRect2()) && p.isLive() == true&&this.good!=p.isGood()) {
				Explode e = new Explode(x, y, warui,p.img);// ���һ������img������ʾ���ֵл������㱬ըʱͼƬ�л�
				if(p.isGood()){//���ɻ�����Ѫ��
				p.life-=40;//����Ǻõļ���40��
				}else{
					p.life-=50;
				}
				warui.explodes.add(e);//�½�һ����ը����
				this.live = false;//�ӵ��Ƴ�
				return true;
			}
			return false;
		}
}
