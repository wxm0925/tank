package wxm.tank.util;

import wxm.tank.Bullet;
import wxm.tank.StandardBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenxiangmin
 * @ClassName BulletPool.java
 * @Description 子弹池 NOTE：资源池知识
 * @createTime 2022年06月21日 22:30:00
 */
public class BulletPool {
    //默认的子弹池数量
    public static final int DEFAULT_POOL_SIZE = 200;

    //子弹池的最大数量
    public static final int MAX_POOL_SIZE = 400;

    private static List<Bullet> data;

    //初始化
    static {
        data = new ArrayList<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            data.add(new StandardBullet());
        }
    }

    /**
     * 获取子弹
     * @return
     */
    public static Bullet get() {
        Bullet bullet;
        if (data.size() == 0) {
            bullet = new StandardBullet();
        } else {
            bullet = data.remove(0);
            System.out.println("从池中");
        }
        return bullet;
    }

    /**
     * 释放子弹
     * @param bullet
     */
    public static void release(Bullet bullet) {
        if (DEFAULT_POOL_SIZE == data.size()) {
            return;
        }
        data.add(bullet);
    }

    public static int size () {
        return data.size();
    }
}
