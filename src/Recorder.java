import java.io.*;
import java.util.Vector;

public class Recorder {
public static int allEnemyTankNum=0;
public static FileWriter fw=null;
public static BufferedWriter bfw=null;
public static BufferedReader bfr=null;
public static String recordfile="D:\\myrecord.txt";

//保存敌人信息，这里用于写出文件。
public static Vector<Node> lastinformation=new Vector<>();

//获取敌方坦克，保存信息用，这里是用于写入文件
 public  static  Vector<enermyTank> enermyTanks = new Vector<>();

 public static  mytank mytank2;

 public static void setMytank2(mytank mytank2){
     Recorder.mytank2=mytank2;
 }

    public  static void setVector(Vector<enermyTank> vector) {
	Recorder.enermyTanks = vector;
    }

public static void isExit() throws IOException {
    File file=new File(recordfile);
    if(file.exists()){
	System.out.println("该文件已经存在");
    }
    else{
	file.createNewFile();
	System.out.println("文件创建成功！");
    }
}

public static void add(){
    allEnemyTankNum++;
}

//保存信息
public static void save() throws IOException {
    bfw=new BufferedWriter(new FileWriter(recordfile));
    bfw.write(allEnemyTankNum+"");

    bfw.newLine();
    String record1=mytank2.x+" "+mytank2.y+" "+mytank2.direct;

    bfw.write(record1);
    bfw.newLine();
    for (int i = 0; i <enermyTanks.size() ; i++) {
	enermyTank currenttank=enermyTanks.get(i);
	if(currenttank.isAlive){
	    String record=currenttank.x+" "+currenttank.y+" "+currenttank.direct;
	    bfw.write(record);
	    bfw.newLine();
	}
    }

    bfw.close();
}

//读出信息
    public  static Vector<Node> getInformation() throws IOException {
	 bfr=new BufferedReader(new FileReader(recordfile));
	allEnemyTankNum=Integer.parseInt(bfr.readLine());//先读取一行，读到的是字符串，要转成整型。
	String line="";
	while ((line= bfr.readLine())!=null){
	String [] line1=line.split(" ");
	 Node node=   new Node(Integer.parseInt(line1[0]),
		    Integer.parseInt(line1[1]),
		    Integer.parseInt(line1[2]));
	    lastinformation.add(node);
	}
	bfr.close();
	return lastinformation;
    }
}

