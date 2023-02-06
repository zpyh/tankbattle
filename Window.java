import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 窗口
 */
public class Window extends JFrame {
    MyPanel myPanel = null;
    public static void main(String[] args) {
        Window window = new Window();

    }
    public Window() {
        myPanel = new MyPanel();
        Thread thread = new Thread(myPanel);
        thread.start();
        this.add(myPanel);
        this.setTitle("Happy Tank Battle");
        this.setSize(1300,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        // 开启监听键盘事件
        this.addKeyListener(myPanel);
        // 监听关闭窗口事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
