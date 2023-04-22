import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testDemo {

    static int cout;

    public static void main(String[] args) {
        int[] ints1 = {1, 5, 3, 7, 8, 4, 0, 3, 7};
        int[] ints2 = {1, 5, 3, 7, 8, 4, 0, 3, 7};
        int[] ints3 = {1, 5, 3, 7, 8, 4, 0, 3, 7};
        int[] ints4 = {1, 5, 3, 7, 8, 4, 0, 3, 7};
        testDemo testDemo = new testDemo();
//        testDemo.bubbleSort(ints1);
//        testDemo.selectionSort(ints2);
//        testDemo.insertionSort(ints3);
//        testDemo.sort(ints4, 0, ints4.length - 1);
//
//        System.out.println(Arrays.toString(ints1));
//        System.out.println(Arrays.toString(ints2));
//        System.out.println(Arrays.toString(ints3));
//        System.out.println(Arrays.toString(ints4));
        Integer[] arr = {1, 2, 3, 4};
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
        testDemo.algorithm1(0, new StringBuffer(), list);
    }

    /**
     * 冒泡
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 相邻元素比较
                if (arr[j] > arr[j + 1]) {
                    // 元素交换
                    var temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * 记录当前位置，选择一个数比较，记录最小的数位置最后与记录位置交换。
     */
    public void selectionSort(int[] arr) {
        int minVar = 0;
        for (int i = 0; i < arr.length; i++) {
            minVar = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 寻找最小的数
                if (arr[j] < arr[minVar]) {
                    // 将最小数的索引保存
                    minVar = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minVar];
            arr[minVar] = temp;
        }
    }

    /**
     * 插入排序
     * <p>
     * 第二个开始，提出标记元素，一直和前边的比较，如果比前边的小，则当前元素后移，标记元素前移动。
     */
    public void insertionSort(int[] arr) {
        int preIndex, current;

        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            // 提出当前元素
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }

    }

    /**
     * 分段 第一次以基准值为标准，值左右平移。两端的指针向中间平移。
     * 递归 重复步骤一
     * 结束：指针无法在移动了（起始点不小于左指针|终止点不大于右边指针）
     * <p>
     * $---------------------------------
     * <p>
     * ---------------------------------#
     * #---------------------------------
     * --------------##------------------
     *
     * @param arr
     */
    public void sort(int[] arr, int low, int high) {
        int start = low;
        int end = high;

        // 小的值
        int key = arr[low];
        //        {1, 5, 3, 7,   8  , 4, 0, 3, 7};
        while (end > start) {
            //从后往前比较
            while (end > start && arr[end] >= key) {
                //如果没有比关键值小的，比较下一个，直到有比关键值小的，跳出交换位置，然后又从前往后比较。
                end--;
            }
            // 交换位置(和当前小值位置交换,相当于往左边扔)
            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            //从前往后比较
            while (end > start && arr[start] <= key) {
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }

            // 交换位置(和当前大值位置交换,相当于往右边扔)
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的
            // 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            //左边序列。第一个索引位置到关键值索引-1
            sort(arr, low, start - 1);
        }
        if (end < high) {
            //右边序列。从关键值索引+1 到最后一个
            sort(arr, end + 1, high);
        }
    }


    /**
     * 要求输出排列组合。
     */
    public void algorithm1(int cou, StringBuffer str, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
//            // 打印好理解
//            System.out.println("第"+ (cou+1) +"层:"+list);
            // 数据拼接
            StringBuffer str1 = new StringBuffer();
            str1.append(str.toString());
            str1.append(list.get(i));

            // 复制当前集合
            List<Integer> collect = list.stream().collect(Collectors.toList());
            // 删除当前集合中已使用的元素，数据递减。
            collect.remove(i);
            // 只要拷贝的数据没有元素，就证明到底了。可以打印了，不需要再递归。
            // 该写法是以底层前推。
            if (collect.size() <= 0) {
                // 记个数
                cout++;
                System.out.println("序号：" + cout + "，第" + (cou + 1) + "层，结果：:" + str1.toString());
                return;
            }
            algorithm1(cou + 1, str1, collect);
        }
    }
}
