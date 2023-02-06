import java.util.Vector;

/**
 * 敌人的坦克实体
 */
public class EnemyTank extends Tank implements Runnable {
    // 敌人坦克装弹，用Vector保存多个shot对象
    Vector<Shot> shots = new Vector<>();
    // 增加成员，EnemyTank可以得到其余敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    // 将MyPanel中的坦克Vector集合成员设置给EnemyTank类
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    /**
     * 判断当前敌方坦克是否和Vector中的坦克发生了重叠
     * 防止坦克重叠现象
     */
    public boolean isTouch() {
        // 得到当前坦克的方向
        switch (this.getDirect()) {
            case 0: // 上
                // 当前坦克和其他所有敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不要和自己做碰撞比较
                    if (enemyTank != this) {
                        // 敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 图形妙用，几何
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 敌人坦克是左右方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: // 右
                // 当前坦克和其他所有敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不要和自己做碰撞比较
                    if (enemyTank != this) {
                        // 敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 图形妙用，几何
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 敌人坦克是左右方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 60 >= enemyTank.getX() &&
                                    this.getX() + 60 <= enemyTank.getX() + 60 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: // 下
                // 当前坦克和其他所有敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不要和自己做碰撞比较
                    if (enemyTank != this) {
                        // 敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 图形妙用，几何
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 敌人坦克是左右方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX() &&
                                    this.getX() + 40 <= enemyTank.getX() + 60 &&
                                    this.getY() + 60 >= enemyTank.getY() &&
                                    this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: // 左
                // 当前坦克和其他所有敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不要和自己做碰撞比较
                    if (enemyTank != this) {
                        // 敌人坦克是上下方向
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 图形妙用，几何
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 敌人坦克是左右方向
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() >= enemyTank.getY() &&
                                    this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() >= enemyTank.getX() &&
                                    this.getX() <= enemyTank.getX() + 60 &&
                                    this.getY() + 40 >= enemyTank.getY() &&
                                    this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            // 敌方坦克子弹消失，重新装弹
            if (isLive && shots.size() < 50) {
                // 初始化子弹对象
                Shot shot = null;
                switch (getDirect()) {
                    case 0:
                        shot = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(shot);
                // 再次启动子弹线程
                new Thread(shot).start();
            }
            // 根据坦克的方向继续移动
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        // 防止坦克走出上边界，下同
                        if (getY() > 0 && !isTouch()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && !isTouch()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouch()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouch()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            // 随机改变坦克的方向
            setDirect((int) (Math.random() * 4));
            // 坦克被击中，退出线程
            if (!isLive) {
                break;
            }
        }
    }
}
