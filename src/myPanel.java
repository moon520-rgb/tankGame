import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;



@SuppressWarnings("all")

public class myPanel extends JPanel implements KeyListener,Runnable {
    mytank mytank1 = null;//自己坦克
    Vector<enermyTank> vector = new Vector<>();//装敌方坦克

    Vector<Node> nodes=new Vector<>();
    int enermytank_size = 5;//地方坦克数量

    public myPanel() throws IOException {

	//nodes=Recorder.getInformation();
	mytank1 = new mytank(100, 100, 5, 0);//初始化自己的坦克
	//初始化敌方坦克
	for (int i = 0; i < enermytank_size; i++) {
	    vector.add(new enermyTank(100 * i, 100, 5, 2));
	    vector.get(i).setVector(vector);
	    new Thread(vector.get(i)).start();

	    //初始化一个地方子弹对象
	    //Bullet bullet1 = new Bullet(vector.get(i).x + 20, vector.get(i).y + 60, vector.get(i).direct, true, 5);

	    //vector.get(i).enermyBullets.add(bullet1);//把这个子弹对象加入到集合里，
	    //因为enermyTank类有个Bullet对象。

	    //new Thread(bullet1).start();//启动线程，让子弹的坐标变化。
	}
	//Recorder.setVector(vector);
    }

    @Override//当画板第一次显示，会自动调用这个方法
    public void paint(Graphics g) {
	super.paint(g);

	Recorder.setMytank2(mytank1);

	Recorder.setVector(vector);

	g.fillRect(0, 0, 1000, 750);//填充矩形，默认为黑色
	showInfo(g);
	if(!mytank1.isAlive)
	{
	    g.setColor(Color.red);
	    g.setFont(new Font("隶书",Font.BOLD,50));
	    g.drawString("您的坦克死了，游戏结束，请退出游戏!",50,50);
	}

	if(mytank1.isAlive)//坦克被击中，就不画了
		drawTank(mytank1.x, mytank1.y, g, mytank1.direct, 0);

	//画出敌人坦克和子弹
	for (int i = 0; i < enermytank_size; i++) {
	    if (vector.get(i).isAlive)//敌人坦克活着时，才画该敌人坦克,被击中了，就不会画坦克
		drawTank(vector.get(i).x, vector.get(i).y, g, vector.get(i).direct, 1);
	    //vector.get(i)表示当前是哪一个地方坦克，
	    // .enermyBullets代表当前坦克的第几科子弹。
	    for (int j = 0; j < vector.get(i).enermyBullets.size(); j++) {

		if (vector.get(i).enermyBullets.get(j).isAlive) {//当前子弹存活，就绘制
		    g.draw3DRect(vector.get(i).enermyBullets.get(j).x, vector.get(i).enermyBullets.get(j).y, 3, 3, false);
		} else {//当前子弹死亡，就从集合里移除子弹，不绘制了。
		    vector.get(i).enermyBullets.remove(vector.get(i).enermyBullets.get(j));
		}
	    }


	}


	/*if (mytank1.mybullet != null && mytank1.mybullet.isAlive == true) {
	    g.fill3DRect(mytank1.mybullet.x, mytank1.mybullet.y, 5, 5, false);
	}*/ //before

	for (int i = 0; i < mytank1.myBullets.size(); i++) {
	    Bullet mybullet = mytank1.myBullets.get(i);
	    if (mybullet != null && mybullet.isAlive == true) {
		g.setColor(Color.cyan);
		g.fill3DRect(mybullet.x, mybullet.y, 5, 5, false);
	    }
	}//画出发射的多颗子弹


    }

    @Override//有字符输出，该方法会触发。
    public void keyTyped(KeyEvent e) {

    }

    @Override//某个键按下，该方法触发。
    public void keyPressed(KeyEvent e) {
	if (e.getKeyChar() == 'w' && mytank1.y != 0) {
	    mytank1.direct = 0;
	    mytank1.y -= mytank1.speed;//改变坦克坐标
	} else if (e.getKeyChar() == 'd' && mytank1.x + 70 != 1000) {
	    mytank1.direct = 1;
	    mytank1.x += mytank1.speed;
	} else if (e.getKeyChar() == 's' && mytank1.y + 90 != 750) {
	    mytank1.direct = 2;
	    mytank1.y += mytank1.speed;
	} else if (e.getKeyChar() == 'a' && mytank1.x != 0) {
	    mytank1.direct = 3;
	    mytank1.x -= mytank1.speed;
	}
	   /* if ((mytank1.mybullet==null||!(mytank1.mybullet.isAlive))&&e.getKeyChar() == 'j') {
		mytank1.shot();
	    }*/  //before
	if (e.getKeyChar() == 'j'&&mytank1.isAlive) {
	    mytank1.shot();
	}
	this.repaint();//重新调用paint方法，画出新的坦克的位置。
    }


    @Override//当某个建松开了，该方法会触发
    public void keyReleased(KeyEvent e) {

    }


    //画坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
	switch (type) {
	    case 0://我方坦克
		g.setColor(Color.cyan);
		break;
	    case 1:
		g.setColor(Color.yellow);
		break;
	}
	switch (direct) {
	    case 0://0表示坦克炮筒向上
		g.fill3DRect(x, y, 10, 60, false);
		g.fill3DRect(x + 30, y, 10, 60, false);
		g.fill3DRect(x + 10, y + 10, 20, 40, false);
		//g.setColor(Color.red);
		g.fillOval(x + 10, y + 20, 20, 20);
		g.drawLine(x + 20, y, x + 20, y + 30);
		break;
	    case 1://1表示 向右
		g.fill3DRect(x, y, 60, 10, false);
		g.fill3DRect(x, y + 30, 60, 10, false);
		g.fill3DRect(x + 10, y + 10, 40, 20, false);
		//g.setColor(Color.red);
		g.fillOval(x + 20, y + 10, 20, 20);
		g.drawLine(x + 30, y + 20, x + 60, y + 20);
		break;
	    case 2://向下
		g.fill3DRect(x, y, 10, 60, false);
		g.fill3DRect(x + 30, y, 10, 60, false);
		g.fill3DRect(x + 10, y + 10, 20, 40, false);
		//g.setColor(Color.red);
		g.fillOval(x + 10, y + 20, 20, 20);
		g.drawLine(x + 20, y + 30, x + 20, y + 60);
		break;
	    case 3://向左
		g.fill3DRect(x, y, 60, 10, false);
		g.fill3DRect(x, y + 30, 60, 10, false);
		g.fill3DRect(x + 10, y + 10, 40, 20, false);
		//g.setColor(Color.red);
		g.fillOval(x + 20, y + 10, 20, 20);
		g.drawLine(x + 30, y + 20, x, y + 20);
		break;
	}

    }




    public static void hitTank(Bullet bullet, Object tank0) {

	if(tank0 instanceof enermyTank) {   //击中敌方坦克
	    enermyTank tank = (enermyTank) tank0;
	    switch (tank.direct) {
		//处理坦克向上向下
		case 0:
		case 2:
		    if (bullet.x > tank.x && bullet.x < tank.x + 40
			    && bullet.y > tank.y && bullet.y < tank.y + 60 && tank.isAlive) {
			bullet.isAlive = false;
			tank.isAlive = false;
			Recorder.add();
		    }//要根据方向来判断坦克跟子弹是否重叠。上下一组，左右一组
		    break;

		//坦克向右向左
		case 1:
		case 3:
		    if (bullet.x > tank.x && bullet.x < tank.x + 60
			    && bullet.y > tank.y && bullet.y < tank.y + 40 && tank.isAlive) {
			bullet.isAlive = false;
			tank.isAlive = false;
			Recorder.add();
		    }
		    break;
	    }
	}
	else {
	    mytank tank = (mytank) tank0;
	    switch (tank.direct) {
		//处理坦克向上向下
		case 0:
		case 2:
		    if (bullet.x > tank.x && bullet.x < tank.x + 40
			    && bullet.y > tank.y && bullet.y < tank.y + 60 && tank.isAlive) {
			bullet.isAlive = false;
			tank.isAlive = false;
		    }
		    break;

		//坦克向右向左
		case 1:
		case 3:
		    if (bullet.x > tank.x && bullet.x < tank.x + 60
			    && bullet.y > tank.y && bullet.y < tank.y + 40 && tank.isAlive) {
			bullet.isAlive = false;
			tank.isAlive = false;
		    }
		    break;
	    }
	}

    }


//显示我方击毁敌方坦克的信息。
public void showInfo(Graphics g){
	g.setColor(Color.black);
	Font font=new Font("宋体",Font.BOLD,25);
	g.setFont(font);
	g.drawString("您累计击毁敌方坦克",1020,30);
	drawTank(1020,60,g,0,0);
	g.setColor(Color.black);
	g.drawString(Recorder.allEnemyTankNum+"",1080,100);
}



    @Override
    //让画板每个50ms就自己重画一次，并且判断是否有坦克被子弹击中
    public void run() {
	while (true) {
	    try {
		Thread.sleep(50);
	    } catch (Exception e) {
		e.printStackTrace();
	    }


	    //判断我方子弹击中了哪个坦克
	    for (int i = 0; i < mytank1.myBullets.size(); i++) {
		Bullet bullet = mytank1.myBullets.get(i);
		if (bullet != null && bullet.isAlive) {
		    for (int j = 0; j < vector.size(); j++) {
			hitTank(bullet, vector.get(j));
		    }
		}


			//判断敌方子弹是否集中了我方坦克
		for (int j = 0; j <enermytank_size ; j++) {
		     enermyTank tank=vector.get(j);
		    for (int k = 0; k < vector.get(j).enermyBullets.size(); k++) {
			Bullet bullet2=tank.enermyBullets.get(k);
			if(bullet2 != null && bullet2.isAlive)
			    hitTank(bullet2, mytank1);
		    }
		}


		this.repaint();
	    }

	}
    }
}
