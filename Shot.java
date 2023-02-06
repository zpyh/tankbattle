/**
 * 完成子弹射击
 */
public class Shot implements Runnable {
    int x; // 子弹x坐标
    int y; // 子弹y坐标
    int direct = 0; // 子弹方向
    int speed = 2; // 子弹速度
    boolean isLive = true; // 子弹是否存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() { // 射击
        while (true) {
            // 休眠50毫秒，以实现子弹动态效果
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 控制子弹方向
            switch (direct) {
                case 0: // 上
                    y -= speed;
                    break;
                case 1: // 右
                    x += speed;
                    break;
                case 2: // 下
                    y += speed;
                    break;
                case 3: // 左
                    x -= speed;
                    break;
            }
            System.out.println("x：" + x + "\ty：" + y);
            // 子弹碰到面板边缘，子弹线程退出
            // 子弹碰到敌人的坦克，子弹线程退出
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
