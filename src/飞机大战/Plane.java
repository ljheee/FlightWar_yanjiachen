package 飞机大战;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;



/**
 * 飞机类，好的坏的都在一起，只是调用的方法不同
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
	public int img=1;//敌机图片序号
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
	private ImageIcon diren;//敌人飞机
	private int[] addr={30,60,80,100,130,150,180,200,230,260,290,320,350};//坏飞机出现的x位置
	private int[] xspeeds={0,0,-1,0,1,0,1,0,-1,0};
	
/**
 * 
 * @param x 本机出现x位置
 * @param y 本机出现x位置
 * @param warui
 * @param size 本机大小
 * @param good 好坏
 */
	public Plane(int x, int y, WarUI warui,int size,boolean good) {
		this.x = x;
		this.y = y;
		this.warui = warui;
		this.good=good;
		this.size=size;
		
	}
	/**
	 * 敌人初始位置随机化
	 * @param good 好坏
	 * @param warui 主类对象
	 */
	public Plane(boolean good,WarUI warui) {
		this.good=false;
		this.warui = warui;
		img=random.nextInt(3)+1;
		
		//获得飞机的y方向速度
		dspeed=1;	
		
		this.x=addr[random.nextInt(13)];
		this.y=random.nextInt(50)+10;
		//确定飞机x方向的速度
		this.xspeed=xspeeds[random.nextInt(10)];		
	}
	

	/**
	 * // 配合键盘事件的移动方法
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
	 * 我的好飞机移动方法
	 */
	public void move() {
			this.x += xspeed;
			this.y += yspeed;
			//出界问题
			if(y<=30)y=30;
			if(x<=0){x=WarUI.WIDTH-30;}
			if(x>=WarUI.WIDTH){x=20;}
			//if(x+size>=WarUI.WIDTH){x=WarUI.WIDTH-size;}
			if(y+size>=WarUI.HEIGHT){y=WarUI.HEIGHT-size;}

	}
	//坏飞机move方法
	public void dmove(){
		this.y+=dspeed;
		this.x+=xspeed;
		//出界问题
		if(y<=30)y=30;
		if(x<=0||x+size>=WarUI.WIDTH){
			xspeed=-xspeed;
			}
		if(y+size>=WarUI.HEIGHT){//如果超出下边界，则将坏飞机移除
			warui.planes.remove(this);
		}

	}
	ImageIcon mimg1 = new ImageIcon("英雄\\hero1.png");
	ImageIcon mimg2 = new ImageIcon("英雄\\hero2.png");
	/**
	 * 画出飞机，包括血量
	 * @param g
	 */
	public void draw(Graphics g) {
		//判断飞机的生命值，小于0则消除对象，不再重绘在面板上
	
		if(life<=0){
		this.setLive(false);
		WarUI.planes.remove(this);
		}
		
		if(good==true&&live==true){//自己
			//给自己画血量，，但是位置不一样
			//g.drawRect(x+2,y+10,size+15,size-10);//用来画出飞机的碰撞检测
			Color c=g.getColor();
			g.setColor(Color.blue);
			g.drawRect(20, 550, 100, 20);//画矩形框
			int w=100*life/100;
			g.fillRect(20, 550, w, 20);//填充框
			g.setColor(c);
			//随机切换图片，制造飞机喷气效果
			int i = random.nextInt(100);
			if (i >50) {
				g.drawImage(mimg1.getImage(), x, y, size+20, size+20, warui);
			} else {
				g.drawImage(mimg2.getImage(), x, y, size+20, size+20, warui);
			
			}
		}
		if(good==false&&live==true){
			//g.drawRect(x,y+20,size-10,size-30);//用来画出飞机的碰撞检测
			 diren = new ImageIcon("敌人\\enemy"+img+".png");
			g.drawImage(diren.getImage(),x,y,size ,size,warui);
			//血量
			Color c=g.getColor();
			g.setColor(Color.red);
			g.drawRect(x+5, y-12, 50, 10);
			int w=50*life/100;
			g.fillRect(x+5, y-12, w, 10);
			g.setColor(c);
			}
			
		}
		
		

	/**
	 * 开火方法
	 */
	public void fire(){
		Bullet b=new Bullet(x,y,warui,good);
		WarUI.bullets.add(b);
		
	}
	/**
	 * 最后一个参数img用来表示哪种敌机，方便爆炸时图片切换
	 */
	 
	public void dfire(){
		Bullet b=new Bullet(x,y,warui,false,this.img);
		WarUI.bullets.add(b);
		
	}
	
	
////得到外围小方块,(碰撞检测辅助类Rectangle)
	public Rectangle getRect(){
		return new Rectangle(x,y+20,size-10,size-30);
	}
	
	public Rectangle getRect2(){
		return new Rectangle(x+2,y+10,size+15,size-15);
	}
	/**
	 * 吃血块方法
	 * @param b  血块对象
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
 * 实现本机与敌机的碰撞
 * @param planes
 * @return
 */
	public boolean pengplane(List<Plane> planes) {
		for (int i = 0; i < planes.size(); i++) {
			Plane p=planes.get(i);
			if (this.live&&this.getRect2().intersects(p.getRect()) && p.isLive() == true&&this.good!=p.isGood()) {// 检测是否碰撞,碰撞之后，还得判断坦克是生还是死，否则子弹在那个地方还是会消失	
				Explode e = new Explode(x, y, warui,p.img);// 最后一个参数img用来表示哪种敌机，方便爆炸时图片切换
				Explode e2 = new Explode(x, y, warui,this.img);
				warui.explodes.add(e2);
				warui.explodes.add(e);//新建爆炸对象
				p.live=false;
				this.life=0;
				this.live = false;
				return true;
			}
		}
		return false;
	}	
	
	
	
	
	

}
