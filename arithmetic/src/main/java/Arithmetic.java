import java.util.Arrays;

/**
 * @author zhangtian
 * @date 2019/2/15
 */

public class Arithmetic {

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤，直到排序完成。
     * @param sources
     */
    public static int[] bubbleSort(int[] sources){
        int temp;
        for(int i = 0; i < sources.length - 1; i++){
            for(int j = 0; j < sources.length - i -1; j++){
                if(sources[j] > sources[j+1]){
                    temp = sources[j];
                    sources[j] = sources[j+1];
                    sources[j+1]=temp;
                }
            }
        }
        return sources;
    }

    /**
     * 选择排序/交换排序
     * 第一次拿第一个元素进行比较，比它小的第一个记录，进行交换位置，以此类推
     * @param sources
     * @return
     */
    public static int[] selectSort(int[] sources){
        int minIndex, temp;
        for (int i = 0; i < sources.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < sources.length; j++) {
                //寻找最小的数
                if (sources[j] < sources[minIndex]) {
                    //将最小数的索引保存
                    minIndex = j;
                }
            }
            temp = sources[i];
            sources[i] = sources[minIndex];
            sources[minIndex] = temp;
        }
        return sources;
    }

    /**
     * 插入排序
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5
     * @param sources
     * @return
     */
    public static int[] insertSort(int[] sources){
        int n;
        for(int i = 1; i <sources.length; i++){
            n = i - 1;
            //当前需要比较的元素
            int temp = sources[i];
            while (n >= 0 && temp < sources[n]){
                //如果当前元素比已排序的小，就往前移动
                sources[n + 1] = sources[n];
                sources[n] = temp;
                n--;
            }
        }
        return sources;
    }

    /**
     * 归并排序
     * 将需要排序的数字分为两组，将两个区间的数据进行排序合并
     * 将数组逐步拆分为"组",直到最小的"组",然后每个组内排序,然后依次和相邻的组"排序合并"
     * @param sources
     * @param left
     * @param right
     * @return
     */
    public static int[] MegreSort(int[] sources, int left, int right){
      int mid = (left + right)/2;
      if(left < right){
          //左边
          MegreSort(sources,left,mid);
          //右边
          MegreSort(sources,mid +1,right);
          //合并
          merge(sources,left,mid,right);
      }
      return sources;
    }

    private static void merge(int[] sources, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        // 左指针
        int i = left;
        // 右指针
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= right) {
            if (sources[i] < sources[j]) {
                temp[k++] = sources[i++];
            } else {
                temp[k++] = sources[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = sources[i++];
        }
        // 把右边剩余的数移入数组
        while (j <= right) {
            temp[k++] = sources[j++];
        }
        // 将tmp数组的数据,替换到source中,begin~end
        // 因为此时tmp中的数据是排序好的
        for (int c = 0; c < temp.length; c++) {
            sources[c + left] = temp[c];
        }

    }




}
