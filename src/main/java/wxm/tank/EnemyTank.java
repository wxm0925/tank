package wxm.tank;

import wxm.tank.constant.DirectionEnum;
import wxm.tank.constant.TankConstants;
import wxm.tank.frame.MainFrame;
import wxm.tank.util.BulletPool;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenxiangmin
 * @ClassName EnemyTank.java
 * @Description 敌人坦克
 * @createTime 2022年06月23日 17:00:00
 */
public class EnemyTank extends Tank {
    public static Map<DirectionEnum, Image> enemyImgMap;

    static {
        //初始化坦克图片
        //enemy
        enemyImgMap = new HashMap<>(4);
        InputStream enemyUpIs = Tank.class.getClassLoader().getResourceAsStream("img/enemy_1_u.gif");
        InputStream enemyDownIs = Tank.class.getClassLoader().getResourceAsStream("img/enemy_1_d.gif");
        InputStream enemyLeftIs = Tank.class.getClassLoader().getResourceAsStream("img/enemy_1_l.gif");
        InputStream enemyRightIs = Tank.class.getClassLoader().getResourceAsStream("img/enemy_1_r.gif");
        byte[] imgData = new byte[6048];
        try {
            enemyUpIs.read(imgData);
            Image EnemyImaUp = new ImageIcon(imgData).getImage();
            enemyImgMap.put(DirectionEnum.UP,EnemyImaUp);
            enemyDownIs.read(imgData);
            Image enemyImaDown = new ImageIcon(imgData).getImage();
            enemyImgMap.put(DirectionEnum.DOWN,enemyImaDown);
            enemyLeftIs.read(imgData);
            Image enemyImaLeft = new ImageIcon(imgData).getImage();
            enemyImgMap.put(DirectionEnum.LEFT,enemyImaLeft);
            enemyRightIs.read(imgData);
            Image enemyImaRight = new ImageIcon(imgData).getImage();
            enemyImgMap.put(DirectionEnum.RIGHT,enemyImaRight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnemyTank(int x, int y,DirectionEnum dir) {
        super(x, y, dir);
    }

    @Override
    public void draw(Graphics g) {
        if (this.state == STATE_STANDING) {
            if (dir == DirectionEnum.UP) {
                g.drawImage(enemyImgMap.get(DirectionEnum.UP),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.DOWN) {
                g.drawImage(enemyImgMap.get(DirectionEnum.DOWN),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.RIGHT) {
                g.drawImage(enemyImgMap.get(DirectionEnum.RIGHT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            } else if (dir == DirectionEnum.LEFT) {
                g.drawImage(enemyImgMap.get(DirectionEnum.LEFT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            }
        } else if (this.state == STATE_MOVING) {
            if (dir == DirectionEnum.UP) {
                y = y - speed;
                if (y < MainFrame.titleHight + (HIGHT / 2)) {
                    y = MainFrame.titleHight + (HIGHT / 2);
                }
                g.drawImage(enemyImgMap.get(DirectionEnum.UP),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.DOWN) {
                y = y + speed;
                if (y > TankConstants.FRAME_HIGHT - (HIGHT / 2)) {
                    y = TankConstants.FRAME_HIGHT - (HIGHT / 2);
                }
                g.drawImage(enemyImgMap.get(DirectionEnum.DOWN),x- (WIDTH / 2),y - (HIGHT / 2),WIDTH,HIGHT,null);
            } else if (dir == DirectionEnum.RIGHT) {
                x = x + speed;
                if (x > TankConstants.FRAME_WIDTH - (HIGHT / 2)) {
                    x = TankConstants.FRAME_WIDTH - (HIGHT / 2);
                }
                g.drawImage(enemyImgMap.get(DirectionEnum.RIGHT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            } else if (dir == DirectionEnum.LEFT) {
                x = x - speed;
                if (x < HIGHT / 2) {
                    x = HIGHT / 2;
                }
                g.drawImage(enemyImgMap.get(DirectionEnum.LEFT),x- (HIGHT / 2),y - (WIDTH / 2),HIGHT,WIDTH,null);
            }
        }
        for (Bullet bullet : bullets) {
            if (bullet.isVisible()) {
                bullet.fly(g,dir);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (!bullet.isVisible()) {
                Bullet remove = bullets.remove(i);
                BulletPool.release(remove);
            }
        }

        /*
        NOTE：从集合中删除元素，Java8写法，如果是java8以前，就用迭代器删除
         */
        bullets.removeIf(bullet -> !bullet.isVisible());
    }
}
