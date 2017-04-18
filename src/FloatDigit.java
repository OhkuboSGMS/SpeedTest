import org.apache.commons.math3.util.FastMath;

import java.nio.FloatBuffer;
import java.util.Arrays;

/**
 * Created by Shigemasa on 2017/03/27.
 */
public class FloatDigit {
    public static void main(String[] args) {
        float t = 0.9f;
        int i = (int) t;
        char str[] =new char[30];
        long start =System.currentTimeMillis();
        System.out.println(t);
        System.out.println(i);
        System.out.println("."+Math.round((t-i)));
        System.out.println(Math.pow(10,10));
        System.out.println(Runtime.getRuntime().totalMemory() / 1000 / 1000);
//        for (int j = 0; j < 10; j++) {
//            for (int k = 0; k < 1000000; k++) {
//                Math.round((float)k);
//                FastMath.round((float)k);
//                Cam.numset(str,(float)k/j,0,2);
//                Cam.clearFrom(str,0);
//                String tmp =String.valueOf((float)k/j);
//            }
//        }
        System.out.println("time:"+(System.currentTimeMillis()-start));
        System.out.println(Runtime.getRuntime().totalMemory() / 1000 / 1000);


        System.out.println("length:"+Cam.numset(str,-0.1f,0,1));
        System.out.println(str);
    }
}
