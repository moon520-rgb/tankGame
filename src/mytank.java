import java.util.ArrayList;
import java.util.Vector;

public class mytank extends Tank {
  public static int num=0;

  public boolean isAlive=true;//坦克是否存活

  Vector<Bullet> myBullets=new Vector<>();//存放着多颗子弹


    public mytank(int x,int y,int speed,int direct){
	super(x,y,speed,direct);
    }

    public void shot(){//发射子弹，一旦按下j，就会根据坦克方向初始化一个子弹，加到集合里
	switch (direct){
	    case 0:

		myBullets.add((new Bullet(x+20,y,0,true,10)));
		break;
	    case 1:

		myBullets.add((new Bullet(x+60,y+20,1,true,10)));
		break;
	    case 2:

		myBullets.add((new Bullet(x+20,y+60,2,true,10)));
		break;
	    case 3:

		myBullets.add((new Bullet(x,y+20,3,true,10)));
		break;
	}
	new Thread(myBullets.get(num++)).start();//启动加入的子弹线程，让子弹自己动

    }
}
