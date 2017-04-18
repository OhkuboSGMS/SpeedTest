import org.apache.commons.math3.util.FastMath;

/**
 * Created by Shigemasa on 2017/03/23.
 */
public class StringCharArray {

    static final char NUM[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final int POWER_10[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};

    /*
     *
     * ゲームのスコアなどを表示するときに
     * "Score:"といった文字列とそのスコアの数値を分離してひょうじすることもあるが
     * 　基本的には"Score:XXX"といった形で合体した文字列で表示することが主である
     * このときに"Score"+x(値)としたときにjavaではString型で生成するため新しい文字列が生成されてしまい
     *  Score:1とScore:2では違ったインスタンスを持つため無駄なメモリを使用することになる
     *  また、Score:2からScore:1に戻ったときに前のScore:1に対する参照がないためおなじ文字列データを
     *  再利用できない。
     *  String型はイミュータブル(文字列を変更できない)であるため、
     *  char []を使用することで省メモリ化を実現することができるようになるか検証
     *
     *  String.valueOf().toCharArray()で値をchar []型で生成した際はvalueOfでStringを生成してそのごchar[]を
     *  生成しているためまったく意味がない
     *  openJDKで
     *  またString.valueOfの中身が何をしているかというと各型のラッパークラスのtoStringを読み出しているに過ぎず
     *  各toStringではchar[]を作成し、String(char[])でStringを生成しているため
     *  新しいStringが生成されている
     *
     *  これに対して、Score:XXXを全てchar[]に保存し
     *  XXXの部分の数値だけを適宜変更することでStringを生成しない方法を提案する
     *
     *  FastMathのlog10では関数内で配列を生成しているため無駄なメモリを使用している
     */
    public static void main(String[] args) {
        long start;
        char mStr[] = new char[15];
        String imStr = null;
        final char NAME[] = "File:".toCharArray();
        System.arraycopy(NAME, 0, mStr, 0, NAME.length);

        System.out.println("numset");
        start = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5000000; i++) {
                /* String+結合
                 */
//                imStr = "File:" + i;
//                System.out.println(imStr);

                /*
                char[]生成によるarraycopy*/
//                char[] aa = String.valueOf(i).toCharArray();
//                System.arraycopy(aa, 0, mStr, NAME.length, aa.length);

                /*
                char[]の一部を変更
                */
                numset(mStr, i, NAME.length);

            }
            //ヒープサイズを確認
            System.out.println(Runtime.getRuntime().totalMemory() / 1000 / 1000.0 + "MB");
        }
        //実行速度を確認
        System.out.println("End:" + (System.currentTimeMillis() - start));
    }

    static void numset(char dest[], int value, int point) {
        int mod = value;
        int srcmod;
        //数字の桁数を確認
//        int length =(int) FastMath.log10(value) + 1;
//        int length = (int) Math.log10(value) + 1;
        int length = getDegit(value);
        for (int i = 0; i < length; i++) {
//            srcmod = (mod / (int) Math.pow(10, length - 1 - i));
//            srcmod = (mod / (int) FastMath.pow(10, length - 1 - i));
            //X桁目の値を抽出
            srcmod = mod / POWER_10[length - 1 - i];
            dest[point + i] = NUM[srcmod % 10];
        }
    }
    static int getDegit(int value) {
        int count = 0;
        while (value != 0) {
            value /= 10;
            count++;
        }
        return count;
    }
}
