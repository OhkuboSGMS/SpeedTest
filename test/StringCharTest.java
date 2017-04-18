import org.junit.Test;

import java.util.Random;

/**
 * Created by Shigemasa on 2017/03/23.
 */
public class StringCharTest {
    @Test
    public void stringTest(){
        char str[] =new char[50];
        final String pre ="Score:";
        final char post[] ="oint".toCharArray();
        Random random =new Random(System.currentTimeMillis());
        System.arraycopy(pre.toCharArray(),0,str,0,pre.length());
        for (int i = 60; i >0 ; i-=1) {
            Cam.clearFrom(str,pre.length());
//            int r =random.nextInt();
            Cam.numset(str,i,pre.length());
            Cam.charset(str,'P',Cam.lastIndex(str));
            Cam.strset(str,post,Cam.lastIndex(str));
            System.out.println(str);
        }
    }

    @Test
    public void CharArrayManipulatorClearTest(){
        char str[] =new char[50];
        final String pre ="Score:";
        final char post[] ="Point".toCharArray();
//        Random random =new Random(System.currentTimeMillis());
        System.arraycopy(pre.toCharArray(),0,str,0,pre.length());
//        Cam.strset(str,post,pre.length()+2);
        System.arraycopy(post,0,str,pre.length()+2,post.length);
        for (int i = 60; i >0 ; i-=1) {
//            Cam.clearFrom(str,pre.length());
            Cam.clearFromTo(str,pre.length(),pre.length()+2,' ');
            int digit =
            Cam.numset(str,i,pre.length());
//            Cam.strset(str,post,Cam.lastIndex(str));
            System.out.print("digit = " + digit);
            System.out.println(str);
        }
    }
}
