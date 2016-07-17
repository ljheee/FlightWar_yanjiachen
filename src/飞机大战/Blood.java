package 飞机大战;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/**
 * 移动血块类
 * @author yan
 */
//移动血块
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
 * 初始化血块
 */
	public Blood(){
		w=h=20;
		x=300;
		y=300;
		
	}
	/**
	 * 用来设定下一次血块随机出现的地点，避免线程带来的闪烁，（不归线程来控制）
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
 * 血块的碰撞检测
 * @return
 */
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	
}
