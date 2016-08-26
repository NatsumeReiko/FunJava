package amy.com.funjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class VolatileLab_object extends AppCompatActivity {
    private static final String TAG = "VOLATILELAB";
    private static final int MAX_INDEX = 1001;
    private TestDataClass target = new TestDataClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
    }

    public void onClickedButton_A(View view) {
        reset();
        final Thread wCountT01 = new Thread(makeWriteCountRunnable(false));
        final Thread wCountT02 = new Thread(makeWriteCountRunnable(false));
        final Thread wVCountT01 = new Thread(makeWriteVolatileCountRunnable(false));
        final Thread wVCountT02 = new Thread(makeWriteVolatileCountRunnable(false));

        startThread(wCountT01, wCountT02, wVCountT01, wVCountT02);

    }

    public void onClickedButton_B(View view) {
        reset();
        final Thread wCountT01 = new Thread(makeWriteCountRunnable(true));
        final Thread wCountT02 = new Thread(makeReadCountRunnable());
        final Thread wVCountT01 = new Thread(makeWriteVolatileCountRunnable(true));
        final Thread wVCountT02 = new Thread(makeReadVolatileCountRunnable());

        startThread(wCountT01, wCountT02, wVCountT01, wVCountT02);


    }

    public void onClickedButton_C(View view) {
        reset();

    }

    public void onClickedButton_D(View view) {
        reset();

    }

    public void onClickedButton_E(View view) {
    }

    @NonNull
    private Runnable makeWriteCountRunnable(final boolean log) {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name:" + Thread.currentThread().getName());
                int start = 0;

                for (int index = 0; index < MAX_INDEX; index++) {
                    start++;
                    target.count = start;
                    if (log) {
                        Log.d(TAG, "W countN index= " + start);
                    }
                    sleepThread();
                }
            }
        };
    }

    @NonNull
    private Runnable makeReadCountRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name:" + Thread.currentThread().getName());

                for (int index = 0; index < MAX_INDEX; index++) {
                    Log.d(TAG, "R countN = " + target.count);
                    sleepThread();
                }
            }
        };
    }

    @NonNull
    private Runnable makeWriteVolatileCountRunnable(final boolean log) {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name:" + Thread.currentThread().getName());
                int start = 0;

                for (int index = 0; index < MAX_INDEX; index++) {
                    start++;
                    target.volatileCount = start;
                    if (log) {
                        Log.d(TAG, "W volatileCount index= " + start);
                    }
                    sleepThread();
                }
            }
        };
    }

    @NonNull
    private Runnable makeReadVolatileCountRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name:" + Thread.currentThread().getName());

                for (int index = 0; index < MAX_INDEX; index++) {
                    Log.d(TAG, "R volatileCount = " + target.volatileCount);
                    sleepThread();
                }
            }
        };
    }

    private void sleepThread() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d(TAG, "sleep Exception");
        }
    }

//    private void ExecutorWithMode(final int mode) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(makeWriteCountRunnable());
//        executorService.shutdown();
//        printOutR();
//    }

    private void reset() {
        target.volatileCount = 0;
        target.count = 0;
        printOutR();
    }

    private void printOutR() {
        Log.d(TAG, " countN = " + target.count + " volatileCount = " + target.volatileCount);
    }

    private void startThread(Thread... threads) {
        try {
            for (final Thread item : threads) {
                item.start();
            }

            for (final Thread item : threads) {
                item.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printOutR();
        }
    }


}
