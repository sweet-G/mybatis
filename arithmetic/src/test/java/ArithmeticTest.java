import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhangtian
 * @date 2019/2/15
 */

public class ArithmeticTest {

    public static void main(String[] args) {
        int[] sources = {1,56,78,-1,25,23,4,6};
        Arithmetic.shellSort(sources);
        System.out.println(Arrays.toString(sources));
    }
}
