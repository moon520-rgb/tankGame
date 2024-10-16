import java.time.Year;
import java.util.Vector;

@SuppressWarnings("all")
public class enermyTank extends Tank implements Runnable {
    boolean isAlive = true;

    public int num = 0;

    Vector<Bullet> enermyBullets = new Vector<>();//敌方坦克的子弹集合

    Vector<enermyTank> enermyTanks = new Vector<>();

    public enermyTank(int x, int y, int speed, int direct) {
	super(x, y, speed, direct);
    }

    //让每一个敌方坦克对象可以得到整个坦克的集合，后面做碰撞判断

    public void setVector(Vector<enermyTank> vector) {
	this.enermyTanks = vector;
    }

    public boolean isTouchEnemyTank() {
	switch (this.direct) {
	    //让当前坦克跟其它坦克循环比较，不和自己比较。
	    case 0:
		for (int i = 0; i < enermyTanks.size(); i++) {
		    enermyTank enermyTank1 = enermyTanks.get(i);//取出一辆坦克
		    if (enermyTank1 != this) {//不和自己比较

			//判断左上角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 40) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 60))
				return true;
			}
			//右上角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x + 40 >= enermyTank1.x
				    && this.x + 40 <= (enermyTank1.x + 40) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 60))
				return true;
			}
			//左上角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 60) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 40))
				return true;
			}
			//右上角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x +40>= enermyTank1.x
				    && this.x+40 <= (enermyTank1.x + 60) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 40))
				return true;
			}

		    }
		}
		break;
	    case 1:
		for (int i = 0; i < enermyTanks.size(); i++) {
		    enermyTank enermyTank1 = enermyTanks.get(i);//取出一辆坦克
		    if (enermyTank1 != this) {//不和自己比较

			//判断右上角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x+60 >= enermyTank1.x
				    && this.x+60  <= (enermyTank1.x + 40) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 60))
				return true;
			}
			//右下角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)
			{
			    if (this.x + 60 >= enermyTank1.x
				    && this.x + 60 <= (enermyTank1.x + 40) &&
				    this.y+40 >= enermyTank1.y &&
				    (this.y+40 <= enermyTank1.y + 60))
				return true;
			}
			//右上角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x+60 >= enermyTank1.x
				    && this.x+60 <= (enermyTank1.x + 60) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 40))
				return true;
			}
			//右下角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x +60>= enermyTank1.x
				    && this.x+60 <= (enermyTank1.x + 60) &&
				    this.y+40 >= enermyTank1.y &&
				    (this.y+40 <= enermyTank1.y + 40))
				return true;
			}

		    }
		}
		break;

	    case 2:
		for (int i = 0; i < enermyTanks.size(); i++) {
		    enermyTank enermyTank1 = enermyTanks.get(i);//取出一辆坦克
		    if (enermyTank1 != this) {//不和自己比较

			//判断左下角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 40) &&
				    this.y+60 >= enermyTank1.y &&
				    (this.y+60 <= enermyTank1.y + 60))
				return true;
			}
			//右下角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x + 40 >= enermyTank1.x
				    && this.x + 40 <= (enermyTank1.x + 40) &&
				    this.y+60 >= enermyTank1.y &&
				    (this.y+60 <= enermyTank1.y + 60))
				return true;
			}
			//左下角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 60) &&
				    this.y+60 >= enermyTank1.y &&
				    (this.y+60 <= enermyTank1.y + 40))
				return true;
			}
			//右下角的点是否发生了碰撞，当前坦克朝上，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x +40>= enermyTank1.x
				    && this.x+40 <= (enermyTank1.x + 60) &&
				    this.y+60 >= enermyTank1.y &&
				    (this.y+60 <= enermyTank1.y + 40))
				return true;
			}

		    }
		}
		break;
	    case 3:
		for (int i = 0; i < enermyTanks.size(); i++) {
		    enermyTank enermyTank1 = enermyTanks.get(i);//取出一辆坦克
		    if (enermyTank1 != this) {//不和自己比较

			//判断左上角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)//敌方坦克方向是上或者下
			{
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 40) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 60))
				return true;
			}
			//左下角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着上下的情况
			if (enermyTank1.direct == 0 || enermyTank1.direct == 2)
			{
			    if (this.x  >= enermyTank1.x
				    && this.x  <= (enermyTank1.x + 40) &&
				    this.y+40 >= enermyTank1.y &&
				    (this.y+40 <= enermyTank1.y + 60))
				return true;
			}
			//左上角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 60) &&
				    this.y >= enermyTank1.y &&
				    (this.y <= enermyTank1.y + 40))
				return true;
			}
			//左下角的点是否发生了碰撞，当前坦克朝右，敌方坦克朝着左右的情况
			if(enermyTank1.direct==1||enermyTank1.direct==3){
			    if (this.x >= enermyTank1.x
				    && this.x <= (enermyTank1.x + 60) &&
				    this.y+40 >= enermyTank1.y &&
				    (this.y+40 <= enermyTank1.y + 40))
				return true;
			}

		    }
		}
		break;

	}
	return false;
    }


    @Override
    //控制地方坦克不停的自由移动
    public void run() {
	while (true) {
	    if (isAlive && enermyBullets.size() < 100) {
		Bullet s = null;
		switch (direct) {
		    case 0:
			s = new Bullet(x + 20, y, 0, true, 10);//加入子弹
			enermyBullets.add(s);
			break;
		    case 1:
			s = new Bullet(x + 60, y + 20, 1, true, 10);
			enermyBullets.add(s);
			break;
		    case 2:
			s = new Bullet(x + 20, y + 60, 2, true, 10);
			enermyBullets.add(s);
			break;
		    case 3:
			s = new Bullet(x, y + 20, 3, true, 10);
			enermyBullets.add(s);
			break;
		}
		new Thread(s).start();//让敌方坦克可以自己发射子弹
	    }


	    //坦克自由移动
	    switch (direct) {
		case 0:
		    for (int i = 0; i < 30; i++) {
			if(x>0 && (!isTouchEnemyTank())) {
			    y -= speed;
			}
			try {
				Thread.sleep(50);
			    } catch (InterruptedException e) {
				e.printStackTrace();
			    }
		    }


//		    if (y - speed * 30 >= 0&&(!isTouchEnemyTank())) {
//			for (int i = 0; i < 30; i++) {
//			    y -= speed;
//			    try {
//				Thread.sleep(50);
//			    } catch (InterruptedException e) {
//				e.printStackTrace();
//			    }
//
//			}
//
//		    }

		    break;
		case 1:
		    for (int i = 0; i < 30; i++) {
			if(x+60<1000 && (!isTouchEnemyTank())) {
			    x += speed;
			}
			try {
			    Thread.sleep(50);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		    }
//		    if (x + speed * 30 <= 1000 - 80&&(!isTouchEnemyTank())) {
//			for (int i = 0; i < 30; i++) {
//			    x += speed;
//			    try {
//				Thread.sleep(50);
//			    } catch (InterruptedException e) {
//				e.printStackTrace();
//			    }
//
//			}
//		    }
		    break;
		case 2:
		    for (int i = 0; i < 30; i++) {
			if(y+60<750 && (!isTouchEnemyTank())) {
			    y += speed;
			}
			try {
			    Thread.sleep(50);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		    }
//		    if (y + speed * 30 <= 750 - 80&&(!isTouchEnemyTank())) {
//			for (int i = 0; i < 30; i++) {
//			    y += speed;
//			    try {
//				Thread.sleep(50);
//			    } catch (InterruptedException e) {
//				e.printStackTrace();
//			    }
//			}
//
//		    }
		    break;
		case 3:
		    for (int i = 0; i < 30; i++) {
			if(x>0 && (!isTouchEnemyTank())) {
			    x -= speed;
			}
			try {
			    Thread.sleep(50);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		    }
//		    if (x - speed * 30 >= 0&&(!isTouchEnemyTank())) {
//			for (int i = 0; i < 30; i++) {
//			    x -= speed;
//			    try {
//				Thread.sleep(50);
//			    } catch (InterruptedException e) {
//				e.printStackTrace();
//			    }
//			}
//		    }
		    break;
	    }
	    direct = (int) (Math.random() * 4);
	    if (isAlive == false)
		break;

	}
    }
}

