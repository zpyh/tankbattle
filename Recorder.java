import java.io.*;

/**
 * 记录玩家游戏信息，使用IO和文件进行交互
 */
public class Recorder {
    // 击毁坦克数量
    private static int allEnemyTankNum = 0;
    // 判断该局游戏是不是第一次从文件中读取分数
    private static boolean flag = false;
    // 定义IO对象
    // 写数据到文件中
    private static BufferedWriter bw = null;
    // 从文件中读数据
    private static BufferedReader br = null;
    // 文件路径
    private static String filePath = "D:\\record.txt";

    public static int getAllEnemyTankNum() {
        if (!flag) {
            getAllEnemyTankNumFromFile();
            flag = true;
        }
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    /**
     * 从文件中读取分数
     */
    public static void getAllEnemyTankNumFromFile() {
        try {
            br = new BufferedReader(new FileReader(filePath));
            allEnemyTankNum = Integer.parseInt(br.readLine());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 我方坦克击毁敌方一辆坦克，记录++
     */
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }

    /**
     * 退出游戏是保存记录到文件中
     */
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(allEnemyTankNum + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
