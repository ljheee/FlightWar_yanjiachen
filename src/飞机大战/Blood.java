package �ɻ���ս;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/**
 * �ƶ�Ѫ����
 * @author yan
 */
//�ƶ�Ѫ��
public class Blood {
	int x,y,w,h;
	Random ran=new Random();
    private boolean live=true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
 WarUI warui;
/**
 * ��ʼ��Ѫ��
 */
	public Blood(){
		w=h=20;
		x=300;
		y=300;
		
	}
	/**
	 * �����趨��һ��Ѫ��������ֵĵص㣬�����̴߳�������˸���������߳������ƣ�
	 */
	public void setBlood(){
		x=ran.nextInt(200)+50;
		y=ran.nextInt(200)+50;
		live=true;
	}
	public void draw(Graphics g){
		if(!live){
			return ;
		}
	
		Color c=g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
	}
/**
 * Ѫ�����ײ���
 * @return
 */
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	
}
