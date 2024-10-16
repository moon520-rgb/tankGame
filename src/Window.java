import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("all")
public class Window extends JFrame {
    myPanel mp=null;
    public static void main(String[] args) throws IOException {
	new Window();

    }
    public Window() throws IOException {
	mp=new myPanel();
	Thread thread=new Thread(mp);
	thread.start();
	this.add(mp);
	this.setSize(1300,950);
	this.addKeyListener(mp);//JFram的一个对象监听到面板上发生的按键
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);

	//关闭窗口，存盘
	this.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		try {
		    Recorder.isExit();
		    Recorder.save();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("监听到关闭窗口了");
	    }
	});
    }
}
