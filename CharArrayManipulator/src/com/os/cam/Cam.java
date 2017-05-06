package com.os.cam;
import org.apache.commons.math3.util.FastMath;

/**
 * Created by Okubo Shigemasa on 2017/03/25.
 * Character Array Manipulator
 * メモリを消費しない文字列操作を実現する
 * int OK
 * long OK
 * float OK
 * double
 * char OK
 * char array はデータ量に応じてSystem.arraycopyとstrsetを使い分けたほうがよい
 * なぜなら、System.arraycopyはネイティブメソッドのオーバーヘッドが存在するため、少量の配列ではむしろ時間がかかってしまう
 * String
 * TODO 0埋め,スペース埋め　データ設定後の配列位置の確認、位置の返却
 */
public class Cam {
    private static int COMMA = 1;
    private static final char NUM[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final long POWER_10[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000,
            1000000000, 10000000000l};
    private static final long POWER_10L[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000,
            1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L
            , 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
    private static final float POWER_10F[] = {1e0f, 1e1f, 1e2f, 1e3f, 1e4f, 1e5f, 1e6f, 1e7f, 1e8f, 1e9f, 1.0e10f, 1e11f, 1e12f, 1e13f, 1e14f, 1e15f, 1e16f,
            1e17f, 1e18f, 1e19f, 1e20f, 1e21f, 1e22f, 1e23f, 1e24f, 1e25f, 1e26f, 1e27f, 1e28f, 1e29f, 1e30f, 1e31f, 1e32f, 1e33f, 1e35f, 1e36f
            , 1e37f, 1e38f};
    private static final double POWER_10D[] = {1e0f, 1e1f, 1e2f, 1e3f, 1e4f, 1e5f, 1e6f, 1e7f, 1e8f, 1e9f, 1.0e10f, 1e11f, 1e12f, 1e13f, 1e14f, 1e15f, 1e16f,
            1e17f, 1e18f, 1e19f, 1e20f, 1e21f, 1e22f, 1e23f, 1e24f, 1e25f, 1e26f, 1e27f, 1e28f, 1e29f, 1e30f, 1e31f, 1e32f, 1e33f, 1e35f, 1e36f
            , 1e37f, 1e38f};

    /**
     * 値addValueを文字列destに追加する
     * addPointが0未満である場合は終了
     * 追加する始点addPointがdestを容量を超えている場合は終了
     * addValueの桁数がdestに収まりきらない場合は値を切り捨てる
     *
     * @param dest
     * @param addValue
     * @param addPoint
     * @return 挿入した桁数
     */
    public static int numset(char dest[], int addValue, int addPoint) {
        int mod = addValue;
        int srcmod;
        //addValueがマイナスであったときのフラグ
        int mflag = 0;
        //数字を文字列にしたときの長さ
        int length = 0;
        if (dest.length < addPoint || addPoint < 0) {
            System.out.println("break first");
            return -1;
        }
        if (addValue < 0) {
            mflag = 1;
//            length+=1;
            mod = -mod;
        }
        length += getDigit(addValue);
        //値がマイナスであったとき
        //挿入する桁数が容量を超える場合は終了
        if ((dest.length - addPoint) < length) {
            System.out.println("break second ");
            return -1;
        }
        //値がマイナスであれば最初に'-'を追加し,位置を調整
        if (mflag == 1) {
            dest[addPoint] = '-';
        }
        for (int i = 0; i < length; i++) {
            srcmod = (int) (mod / POWER_10[length - 1 - i]);
            dest[addPoint + i + mflag] = NUM[srcmod % 10];
        }
        return length;
    }

    public static int numset(char dest[], long addValue, int addPoint) {
        long mod = addValue;
        long srcmod;
        int mflag = 0;
        int length = 0;
        if (dest.length < addPoint || addPoint < 0) {
            System.out.println("break first");
            return -1;
        }
        if (addValue < 0) {
            mflag = 1;
            mod = -mod;
        }
        length += getDigit(addValue);
        if ((dest.length - addPoint) < length) {
            System.out.println("break second ");
            return -1;
        }
        //値がマイナスであれば最初に'-'を追加し,位置を調整
        if (mflag == 1) {
            dest[addPoint] = '-';
        }
        for (int i = 0; i < length; i++) {
            srcmod = mod / POWER_10L[length - 1 - i];
            dest[addPoint + i + mflag] = NUM[(int) (srcmod % 10)];
        }
        return length;
    }

    /**
     * 小数点の桁を指定する
     * (マイナス)整数部分 小数点 小数部部分
     *
     * @param dest
     * @param addValue
     * @param addPoint
     * @param decimalSize
     * @return
     */
    public static int numset(char dest[], float addValue, int addPoint, int decimalSize) {
        float mod = addValue;
        int srcmod;
        int integer = 0;
        int decimal;
        int iLength;
        int mflag = 0;
        if (dest.length < addPoint || addPoint < 0) {
            System.out.println("break first");
            return -1;
        }
        //マイナス符号,整数,小数あり
        if (addValue < -1) {
            mflag = 1;
            integer = -(int) addValue;
            decimal = (FastMath.round((-addValue - integer) * POWER_10F[decimalSize]));

            iLength = getDigit(integer);
        }
        //マイナス符号,少数あり
        else if (addValue < 0) {
            mflag = 1;
            integer = (int) addValue;
            decimal = (FastMath.round((-addValue - integer) * POWER_10F[decimalSize]));
            iLength = 1;
        }
        //少数あり
        else if (addValue < 1) {
            decimal = (FastMath.round((addValue - integer) * POWER_10F[decimalSize]));
            iLength = 1;
        }
        //整数、少数あり
        else {
            integer = (int) addValue;
            decimal = (FastMath.round((addValue - integer) * POWER_10F[decimalSize]));
            iLength = getDigit(integer);
        }
//        System.out.println(addValue - integer);
//        System.out.println("int:"+integer+":decimal:"+decimal);
//        System.out.println("iLength = " + iLength);
//        System.out.println("dLength = " + dLength);

        if (mflag == 1) {
            dest[addPoint] = '-';
        }

        //整数部設定
        for (int i = 0; i < iLength; i++) {
            srcmod = (int) (integer / POWER_10[iLength - 1 - i]);
            dest[addPoint + i + mflag] = NUM[srcmod % 10];
        }
        //コンマ
        dest[addPoint + iLength + mflag] = '.';
        //小数部設定
        for (int i = 0; i < decimalSize; i++) {
            srcmod = (int) (decimal / POWER_10[decimalSize - 1 - i]);
            dest[addPoint + iLength + mflag + 1 + i] = NUM[srcmod % 10];
        }
        return iLength + decimalSize + mflag + COMMA;
    }

    public static int numset(char dest[], int addValue) {
        return numset(dest, addValue, 0);
    }

    public static int numset(char dest[], long addValue) {
        return numset(dest, addValue, 0);
    }

    public static int numset(char dest[], float addValue, int decimalSize) {
        return numset(dest, addValue, 0,decimalSize);
    }

    public static int charset(char dest[], char addValue) {
        return charset(dest, addValue, 0);
    }

    public static int charset(char dest[], char addValue, int addPoint) {
        if (dest.length < addPoint || addPoint < 0 || (dest.length - addPoint) < 1) {
            System.out.println("break first");
            return -1;
        }
        dest[addPoint] = addValue;
        return addPoint + 1;
    }

    public static void strset(char dest[], char addValue[]) {
        strset(dest, addValue, 0);
    }

    /**
     * ディープコピーで実装しているため配列の量によってはSystem.arraycopyのほうが早いこともある
     *
     * @param dest
     * @param addValue
     * @param addPoint
     */
    public static void strset(char dest[], char addValue[], int addPoint) {
        for (int i = 0; i < addValue.length; i++) {
            dest[addPoint + i] = addValue[i];
        }
    }

    /**
     * 柔軟性のためメソッドは実装しているがメモリ占有は発生しているためGC発生抑制にはならない
     *
     * @param dest
     * @param addValue
     */
    public static void strset(char dest[], String addValue) {
        strset(dest, addValue.toCharArray());
    }

    public static void strset(char dest[], String addValue, int addPoint) {
        strset(dest, addValue.toCharArray(), addPoint);
    }

    /**
     * TODO 桁数取得についてはlog10と10除算があるどちらを使用するかは検討
     * @param value
     * @return
     */
    private static int getDigit(int value) {
        int count = 0;
        while (value != 0) {
            value /= 10;
            count++;
        }
        return count;
    }

    private static int getDigit(long value) {
        int count = 0;
        while (value != 0) {
            value /= 10;
            count++;
        }
        return count;
    }

    public static void clear(char dest[]) {
        clearFrom(dest, 0, '\0');
    }

    public static void clearFrom(char dest[], int startPoint) {
        clearFrom(dest, startPoint, '\0');
    }

    public static void clearFrom(char dest[], int startPoint, char ch) {
        for (int i = 0; i < (dest.length - startPoint); i++) {
            dest[startPoint + i] = ch;
        }
    }

    public static void clearFromTo(char dest[], int startPoint, int endPoint) {
        clearFromTo(dest, startPoint, endPoint, '\0');
    }

    public static void clearFromTo(char dest[], int startPoint, int endPoint, char ch) {
        for (int i = startPoint; i < endPoint; i++) {
            dest[i] = ch;
        }
    }

    public static int lastIndex(char dest[]) {
        return lastIndex(dest, '\0');
    }

    public static int lastIndex(char dest[], char ch) {
        for (int i = 0; i < dest.length; i++) {
            if (dest[i] == ch) return i;
        }
        return dest.length;
    }


}
