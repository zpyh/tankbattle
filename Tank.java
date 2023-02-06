/**
 * 坦克框架定义
 * domain层
 */

public class Tank {
    private int x;
    private int y;
    // 坦克方向（0 1 2 3）
    private int direct;
    // 坦克速度
    private int speed = 5;
    boolean isLive = true;

    // 坦克移动方法
    public void moveUp() {
        y -= speed;
    }
    public void moveRight() {
        x += speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.direct = 2; // 坦克默认炮筒朝下
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
