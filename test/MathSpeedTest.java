import org.junit.Test;
public class MathSpeedTest {
    int trialCount = 10000;
    int angle =360;

    @Test
    public void TrigonometricUse() throws Exception {
        double start = System.currentTimeMillis();
        for (int k = 0; k < trialCount; k++) {
            for (int i = 0; i < angle; i++) {
                Math.cos(Math.toRadians(i));
            }
        }
        System.out.println(start - System.currentTimeMillis());
    }

    @Test
    public void MemoryUse() throws Exception {
        double start = System.currentTimeMillis();
        float x[] = new float[angle];
        for (int i = 0; i < x.length; i++) {
            x[i] = (float) Math.cos(Math.toRadians(i));
        }

        for (int i = 0; i < trialCount; i++) {
            for (int j = 0; j < x.length; j++) {
                x[j] = x[j] + x[j];
            }
        }
        System.out.println(start - System.currentTimeMillis());
    }

    @Test
    public void over180() {
        for (int i = 0; i < trialCount; i++) {
            Math.cos(265);
        }
    }

    @Test
    public void under180() {
        for (int i = 0; i < trialCount; i++) {
            Math.cos(45);

        }
    }

}