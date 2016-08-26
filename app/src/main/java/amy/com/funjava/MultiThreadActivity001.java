package amy.com.funjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*set class and Integer test class*/
public class MultiThreadActivity001 extends AppCompatActivity {

    final private static int MODE_A = 1;
    final private static int MODE_B = 2;
    final private static int MODE_C = 3;

    final Set<Integer> testList01 = new HashSet<>();

    /*test datas*/
    final Integer testA = 110033;
    final int testB = 110033;
    final Integer testC = Integer.valueOf(110033);


    /*target datas*/
    final Integer targetA = 110033;
    final int targetB = 110033;
    final Integer targetC = Integer.valueOf(110033);

    int mode = MODE_A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set);

        setViews();
    }

    private void setViews() {

        ((RadioButton) findViewById(R.id.radio_a)).setText("Integer testA = 110033");
        ((RadioButton) findViewById(R.id.radio_b)).setText("radio B");
        ((RadioButton) findViewById(R.id.radio_c)).setText("radio C");

        ((RadioGroup) findViewById(R.id.radio_group))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radio_a: {
                                mode = MODE_A;
                            }
                            break;
                            case R.id.radio_b: {
                                mode = MODE_B;
                            }
                            break;
                            case R.id.radio_c: {
                                mode = MODE_C;
                            }
                            break;
                        }
                    }
                });
    }

    public void onClickedButton_A(View view) {
        startMultiThread();
    }

    private void startMultiThread() {
        Integer[] values = new Integer[] { 3, 1, 14, 3, 4, 5, 6, 7, 8, 9, 11,
                3, 2, 1 };
        Average avg = new Average(values);
        Median median = new Median(values);
        Thread t1 = new Thread(avg, "t1");
        Thread t2 = new Thread(median, "t2");
        System.out.println("Start the thread t1 to calculate average");
        t1.start();
        System.out.println("Start the thread t2 to calculate median");
        t2.start();
        try {
            System.out.println("Join on t1");
            t1.join();
            System.out
                    .println("t1 has done with its job of calculating average");
        } catch (InterruptedException e) {
            System.out.println(t1.getName() + " interrupted");
        }
        try {
            System.out.println("Join on t2");
            t2.join();
            System.out
                    .println("t2 has done with its job of calculating median");
        } catch (InterruptedException e) {
            System.out.println(t2.getName() + " interrupted");
        }
        System.out.println("Average: " + avg.getMean() + ", Median: "
                + median.getMedian());
    }

    public void onClickedButton(View view) {
    }


    /**
     * Calculate average of numbers. Sum all the int values and divide it by
     * total count.
     */
    private static class Average implements Runnable {
        private Integer[] values;
        private int mean;

        Average(Integer[] values) {
            this.values = values;
        }

        @Override
        public void run() {
            mean = 0;
            int n = values.length;
            for (int i : values) {
                mean += i;
            }
            mean /= n;
        }

        public int getMean() {
            return mean;
        }
    }

    /**
     * Sorts the given int list and calculates the median value. If size is
     * even, the mean of middle and middle-1.
     *
     */
    private static class Median implements Runnable {
        private Integer[] values;
        private Integer median;

        Median(Integer[] values) {
            this.values = values;
        }

        @Override
        public void run() {
            List<Integer> sortedList = Arrays.asList(values);
            Collections.sort(sortedList);
            int n = values.length;
            int middle = n / 2;
            if (n % 2 == 0) {
                median = (sortedList.get(middle) + sortedList.get(middle - 1)) / 2;
            } else {
                median = sortedList.get(middle);
            }
        }

        public int getMedian() {
            return median;
        }

    }

}
