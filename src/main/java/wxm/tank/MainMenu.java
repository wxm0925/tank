package wxm.tank;


/**
 * @author wenxiangmin
 * @ClassName MainMenu.java
 * @Description 主菜单类
 * @createTime 2022年06月10日 23:38:00
 */
public class MainMenu {
    private int initIndex = 1;
    //当前菜单位置
    private int index = initIndex;
    //菜单项的总数量
    private final int MAX_MENUINDEX = Item.count();

    /**
     * 菜单上移
     */
    public void moveUp() {
        if (index == initIndex) {
            index = MAX_MENUINDEX;
        }else {
            index--;
        }
    }

    /**
     * 菜单下移
     */
    public void moveDown() {
        if (index == MAX_MENUINDEX) {
            index = initIndex;
        }else {
            index++;
        }
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * 菜单项 （参考Thread.State）
     */
    public enum Item {
        BEGIN(1, "开始游戏"),
        CONTINUE(2, "继续游戏"),
        HELP(3, "游戏帮助"),
        ABOUT(4, "游戏关于"),
        EXIT(5, "退出游戏");

        private int index;
        private String name;

        Item(int index, String name) {
            this.index = index;
            this.name = name;
        }

        @Override
        public String toString() {
            return "MenuEnum{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public static String getName(int index) {
            index = index - 1;
            Item[] menus = Item.values();
            for (int i = 0; i < menus.length; i++) {
                if (i == index) {
                    return menus[index].getName();
                }
            }
            return "";
        }

        public static int count() {
            return Item.values().length;
        }
    }
}
