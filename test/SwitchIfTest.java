import org.junit.Test;

/**
 * Created by Shigemasa on 2017/03/23.
 * 分岐数と処理速度の関係を調査
 */
public class SwitchIfTest {
    int count = 10000000;
    int branch = 10;

    @Test
    public void ifSpeedTest() {
        int type;
        for (int i = 0; i < count; i++) {
            type = i % branch;
            if (type == 0);
            else if (type == 1);
            else if (type == 2);
            else if (type == 3);
            else if (type == 4);
            else if (type == 5);
            else if (type == 6);
            else if (type == 7);
            else if (type == 8);
            else if (type == 9);
        }
    }

    @Test
    public void switchSpedTest() {
        int type;
        for (int i = 0; i < count; i++) {
            type = i % branch;
            switch (type) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
        }
    }
}