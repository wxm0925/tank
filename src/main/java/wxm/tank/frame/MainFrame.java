package wxm.tank.frame;

import wxm.tank.MainMenu;
import wxm.tank.Tank;
import wxm.tank.constant.TankConstants;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wenxiangmin
 * @ClassName MainFrame.java
 * @Description 主窗口类
 * @createTime 2022年06月10日 19:45:00
 */
public class MainFrame extends  Frame implements Runnable{
    //游戏状态
    private static int state;

    private MainMenu mainMenu;

    private Tank p1Tank = new Tank(500,200,Tank.DIR_UP);

    public static int titleHight;
    public MainFrame(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        //初始化的游戏状态为选择菜单界面
        this.state = TankConstants.STATE_MENU;
        super.setVisible(true);
        titleHight = getInsets().top;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        super.setTitle(TankConstants.title);
        super.setSize(TankConstants.FRAME_WIDTH,TankConstants.FRAME_HIGHT);
        super.setLocation(TankConstants.LOCATION_X,TankConstants.LOCATION_Y);
        //更新窗口
        initEventListner();
    }


    /**
     * 注册监听事件
     */
    private void initEventListner() {
        //注册窗口关闭退出程序监听事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode  = e.getKeyCode();
                if (state == TankConstants.STATE_MENU) {
                    // 38=上、 40=下、 10=Enter
                    //按键的处理
                    //处理↑
                    if (keyCode == KeyEvent.VK_UP) {
                        mainMenu.moveUp();
                    }
                    //处理↓
                    if (keyCode == KeyEvent.VK_DOWN) {
                        mainMenu.moveDown();
                    }
                    if (keyCode == KeyEvent.VK_ENTER) {
                        if (mainMenu.getIndex() == MainMenu.Item.BEGIN.getIndex()) {
                            //开始游戏
                            newGame();
                        }
                    }
                    //游戏中的按键处理
                } else if (state == TankConstants.STATE_IN_GAME) {
                    if (keyCode == KeyEvent.VK_UP) {
                        p1Tank.setDir(Tank.DIR_UP);
                        p1Tank.setState(Tank.STATE_MOVING);
                    }
                    //处理↓
                    if (keyCode == KeyEvent.VK_DOWN) {
                        p1Tank.setDir(Tank.DIR_DOWN);
                        p1Tank.setState(Tank.STATE_MOVING);
                    }
                    if (keyCode == KeyEvent.VK_LEFT) {
                        p1Tank.setDir(Tank.DIR_LEFT);
                        p1Tank.setState(Tank.STATE_MOVING);
                    }
                    if (keyCode == KeyEvent.VK_RIGHT) {
                        p1Tank.setDir(Tank.DIR_RIGHT);
                        p1Tank.setState(Tank.STATE_MOVING);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (state == TankConstants.STATE_IN_GAME && p1Tank.getState() == Tank.STATE_MOVING) {
                    p1Tank.setState(Tank.STATE_STANDING);
                }
            }
        });

    }

    /**
     * 开始新游戏
     */
    private void newGame() {
        state = TankConstants.STATE_IN_GAME;
        System.out.println("开始新游戏");
    }


    /**
     * 系统回调，用于更新窗口内容 30ms调用一次
     * @param g 画笔
     */
    @Override
    public void update(Graphics g) {
        g.setFont(TankConstants.FONT);
        if (state == TankConstants.STATE_MENU) {
            drawMenu(g);
        }else if (state == TankConstants.STATE_IN_GAME) {
            drawRun(g);
        }
    }

    /**
     * 新开游戏窗口
     * @param g
     */
    private void drawRun(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,TankConstants.FRAME_WIDTH,TankConstants.FRAME_HIGHT);
        p1Tank.draw(g);
    }

    /**
     * 绘制游戏菜单
     * @param g
     */
    private void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,TankConstants.FRAME_WIDTH,TankConstants.FRAME_HIGHT);

        final int STR_WIDTH = 70;
        int x = TankConstants.FRAME_WIDTH  - STR_WIDTH >> 1;
        int y = (TankConstants.FRAME_HIGHT-150)  - STR_WIDTH >> 1;
        //菜单间距
        final int DIS = 60;
        for (int i = 1; i <= MainMenu.Item.count(); i++) {
            if (i == mainMenu.getIndex()) {
                g.setColor(Color.GREEN);
            }else {
                g.setColor(Color.WHITE);
            }
            g.drawString(MainMenu.Item.getName(i), x,y + DIS * i);
        }
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
