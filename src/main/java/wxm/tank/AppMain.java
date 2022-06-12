package wxm.tank;

import wxm.tank.frame.MainFrame;

/**
 * @author wenxiangmin
 * @ClassName AppMain.java
 * @Description 程序入口
 * @createTime 2022年06月10日 19:44:00
 */
public class AppMain {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(new MainMenu());
        new Thread(mainFrame).start();
    }
}
