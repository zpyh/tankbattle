import java.util.Vector;

/**
 * 坦克实体，继承了坦克的框架
 * 玩家操纵的那个坦克
 */
public class MyTank extends Tank {
    // 定义一个shot对象，表示一个射击行为
    Shot shot = null;
    // 支持发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    // 射击
    public void shotEnemyTank() {
        // 面板上的子弹最多存在5颗
        if (shots.size() == 5) {
            return;
        }
        // 创建shot对象，要根据当前自己坦克的位置和方向来创建shot对象
        switch (getDirect()) {
            case 0: // 上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: // 右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: // 下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: // 左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        // 把新创建的shot放入shots中
        shots.add(shot);
        // 启动shot线程
        new Thread(shot).start();
    }
}
