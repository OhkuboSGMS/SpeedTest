/**
 * Created by Shigemasa on 2017/03/03.
 */
public class MathSpeed {
    public static void main(String[] args) throws Exception{
        for (; ; ){
            int trialCount = 10000;
            int angle = 360;

            double start = System.currentTimeMillis();
            for (int k = 0; k < trialCount; k++) {
                for (int i = 0; i < angle; i++) {
                    Math.cos(Math.toRadians(i));
                }
            }
            System.out.println(start - System.currentTimeMillis());
        }
    }
}
