package wxm.tank;

import wxm.tank.constant.DirectionEnum;

/**
 * @author wenxiangmin
 * @ClassName StandardBullet.java
 * @Description TODO
 * @createTime 2022年06月13日 21:36:00
 */
public class StandardBullet extends Bullet{

    public StandardBullet(int x, int y, DirectionEnum dir) {
        super.dir = dir;
        super.x = x;
        super.y = y;
        super.RADIUS = 5;
        super.speed = Tank.DEFAULT_SPEED * 2;
    }
}
