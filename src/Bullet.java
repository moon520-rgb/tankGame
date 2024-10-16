@SuppressWarnings("all")
public class Bullet implements Runnable {
    public int x;
    public int y;
    public int direction;
    public boolean isAlive;
    public int speed;

    public Bullet(int x, int y, int direction, boolean isAlive,int speed) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.isAlive = isAlive;
        this.speed=speed;
    }

    @Override
    //让每个子弹可以自己移动
    public void run() {
	while (true){
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
                switch (direction){
                    case 0:
                        y-=speed;
                        break;
                    case 1:
                        x+=speed;
                        break;
                    case 2:
                        y+=speed;
                        break;
                    case 3:
                        x-=speed;
                        break;
                }

                if(!(x>=0&&x<=1000&&y>=0&&y<=750))//边界判断
                {
                    isAlive=false;
                    break;
                }
            }
    }
}
