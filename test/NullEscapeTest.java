import org.junit.Test;

/**
 * Created by Shigemasa on 2017/03/27.
 */
public class NullEscapeTest {
    @Test
    public void NullEscapeTest(){
        char str[] ="Niaho".toCharArray();
        String mstr ="NiahoS";
        for (int i = 0; i <str.length ; i++) {
            System.out.println(str[i]);
        }
        int i =-10;
        System.out.println(-i);
    }
}
