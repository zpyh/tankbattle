import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战的绘图区域
 */
// 为了让 Panel 不停的重绘子弹，需要将 MyPanel 实现Runnable接口，当作一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    // 定义己方坦克
    MyTank myTank = null;
    // 定义敌方坦克，放入Vector中
    Vector<EnemyTank> enemyTanks = new Vector<>();

    // 敌方坦克数量
    int enemyTankSize = 9;

    public MyPanel() {
        myTank = new MyTank(100, 500);
        myTank.setSpeed(10);
        // 初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            // 传递enemyTanks给EnemyTank的enemyTanks
            enemyTank.setEnemyTanks(enemyTanks);
            // 启动敌人的坦克线程，让他动起来
            new Thread(enemyTank).start();
            // 给敌人的坦克装弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 40, enemyTank.getDirect());
            // 将创建的子弹对象加入到enemyTank的Vector成员
            enemyTank.shots.add(shot);
            // 子弹发射
            new Thread(shot).start();
            enemyTanks.add(enemyTank);

        }
    }

    /**
     * 右方显示我方击毁敌方坦克的信息
     *
     * @param g 画笔
     */
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 填充黑色矩形
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        if (myTank != null && myTank.isLive) {
            // 调用方法画自己坦克
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }
        // 画出己方坦克发射的子弹
        // 发射子弹的线程存在，从子弹集合中取出所有子弹进行绘制
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (shot != null && shot.isLive) {
                g.draw3DRect(shot.x, shot.y, 4, 4, false);
            } else {
                // 某一个子弹已经死亡。将子弹从shots集合中拿掉
                myTank.shots.remove(shot);
            }
        }

        // 调用方法画自敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            // 只有敌人的坦克是存活状态，我们才进行绘制，否则将视为坦克已经死亡
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
            }
            // 画出当前敌人坦克的子弹
            for (int i1 = 0; i1 < enemyTank.shots.size(); i1++) {
                // 取出子弹
                Shot shot = enemyTank.shots.get(i1);
                // 绘制子弹，子弹的生命存在才绘制，否则不绘制
                if (shot.isLive) {
                    g.draw3DRect(shot.x, shot.y, 4, 4, false);
                } else {
                    // 当前子弹已经死亡，从vector中移除该子弹，否则该子弹将会一直绘制
                    enemyTank.shots.remove(shot);
                }
            }
        }
    }


    /**
     * 画坦克
     *
     * @param x      坦克左上角x坐标
     * @param y      坦克左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // 根据不同的坦克类型，设置不同的颜色
        switch (type) {
            case 0: // 己方坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 敌方坦克
                g.setColor(Color.red);
                break;
        }

        // 根据坦克的方向，来绘制坦克
        switch (direct) {
            // 坦克头向上
            case 0:
                // 左边的轮子
                g.fill3DRect(x, y, 10, 60, false);
                // 右边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                // 坦克的身子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                // 坦克的旋转炮筒
                g.fillOval(x + 10, y + 20, 20, 20);
                // 炮筒
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            // 坦克头向右
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            // 坦克头向下
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            // 坦克头向左
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("未开发...");
        }

    }

    /**
     * 判断一颗子弹是否击中了坦克
     *
     * @param shot      发射出去的子弹
     * @param enemyTank 敌方或者我方的坦克
     */
    public static void isHitTank(Shot shot, Tank enemyTank) {
        switch (enemyTank.getDirect()) {
            // 上下方向判定击中
            case 0:
            case 2:
                if (shot.x > enemyTank.getX() && shot.x < enemyTank.getX() + 40
                        && shot.y > enemyTank.getY() && shot.y < enemyTank.getY() + 60) {
                    shot.isLive = false;
                    enemyTank.isLive = false;
                    // 我方击毁了敌方一辆坦克，记录++
                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
            // 左右方向判定击中
            case 1:
            case 3:
                if (shot.x > enemyTank.getX() && shot.x < enemyTank.getX() + 60
                        && shot.y > enemyTank.getY() && shot.y < enemyTank.getY() + 40) {
                    shot.isLive = false;
                    enemyTank.isLive = false;
                    // 我方击毁了敌方一辆坦克，记录++
                    if (enemyTank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
        }
    }

    /**
     * 判断我方坦克是否被敌方击中
     */
    public void hitMyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            // 遍历当前坦克对象的所有子弹，进行判断
            for (int i1 = 0; i1 < enemyTank.shots.size(); i1++) {
                Shot shot = enemyTank.shots.get(i1);
                // 判断shot是否击中我方坦克
                if (myTank.isLive && shot.isLive) {
                    isHitTank(shot, myTank);
                }
            }
        }
    }

    /**
     * 判断敌方坦克是否被我方击中
     */
    public void hitEnemyTank() {
        // 判断是否击中了敌人的坦克
        if (myTank.shot != null && myTank.shot.isLive) {
            // 循环遍历所有的坦克，判断是否击中！
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                isHitTank(myTank.shot, enemyTank);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    // 监听键盘事件，让坦克动起来！
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            // 改变坦克的方向
            myTank.setDirect(0);
            // 修改坦克的坐标
            // 控制坦克不移出边界
            if (myTank.getY() > 0) {
                myTank.moveUp();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirect(1);
            if (myTank.getX() + 60 < 1000) {
                myTank.moveRight();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirect(2);
            if (myTank.getY() + 60 < 750) {
                myTank.moveDown();
            }
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            if (myTank.getX() > 0) {
                myTank.moveLeft();
            }
        }

        // 如果用户按下J键，就启动子弹发射线程，可以发射多颗子弹
        if (keyEvent.getKeyCode() == KeyEvent.VK_J) {
            myTank.shotEnemyTank();
        }

        // 让面板重新绘制
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    /**
     * 每隔100ms自动重绘区域，达到子弹动态的效果
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemyTank();
            hitMyTank();
            this.repaint();
        }
    }
}
