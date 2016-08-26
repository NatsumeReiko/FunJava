package amy.com.funjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileLab001 extends AppCompatActivity {
    private static final String TAG = "VOLATILELAB";
    final private static int MODE_A = 1;
    final private static int MODE_B = 2;
    final private static int MODE_C = 3;

    private volatile boolean stopFlag = false;
    private int count = 0;
    private volatile int volatileCount = 0;
    private final Thread thread01 = new Thread(makeRunnable(MODE_A));
    private final Thread thread02 = new Thread(makeRunnable(MODE_A));
    private final Thread threadRead = new Thread(makeReadRunnable());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
    }

    public void onClickedButton_A(View view) {
        reset();
        Toast.makeText(this.getApplication(), "onClickedButton_A", Toast.LENGTH_SHORT).show();
        thread01.start();
        thread02.start();

        try {
            thread01.join();
            thread02.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.d(TAG, "thread01 status = " + thread01.isAlive());
            Log.d(TAG, "thread02 status = " + thread02.isAlive());
            Log.d(TAG, " count = " + count);
            printOutR();
        }

    }

    private void reset() {
        count = 0;
        volatileCount = 0;
    }

    private void printOutR() {
        Log.d(TAG, " count = " + count + " volatileCount = " + volatileCount);
    }

    public void onClickedButton_B(View view) {
        reset();

        Toast.makeText(this.getApplication(), "onClickedButton_B", Toast.LENGTH_SHORT).show();
        ExecutorWithMode(MODE_B);
    }

    public void onClickedButton_C(View view) {
        reset();

        Toast.makeText(this.getApplication(), "onClickedButton_C", Toast.LENGTH_SHORT).show();
        ExecutorWithMode(MODE_C);
    }

    public void onClickedButton_D(View view) {
        reset();
        thread01.start();
        threadRead.start();

        try {
            thread01.join();
            threadRead.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.d(TAG, "thread01 status = " + thread01.isAlive());
            Log.d(TAG, "threadRead status = " + threadRead.isAlive());
            printOutR();
        }
    }

    public void onClickedButton_E(View view) {
        stopFlag = true;
    }

    @NonNull
    private Runnable makeRunnable(final int mode) {
        return new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 1000; index++) {
                    if (mode == MODE_A) {
                        count++;
                    } else if (mode == MODE_B) {
                        volatileCount++;
                    } else {
                        count++;
                        volatileCount++;
                    }
                    printOutR();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d(TAG, "sleep Exception");
                    }
                    if (stopFlag) {
                        break;
                    }
                }
            }
        };
    }

    @NonNull
    private Runnable makeReadRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 1000; index++) {
                    Log.d(TAG, "READ count = " + count);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d(TAG, "sleep Exception");
                    }
                    if (stopFlag) {
                        break;
                    }
                }
            }
        };
    }

    private void ExecutorWithMode(final int mode) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(makeRunnable(mode));
        executorService.shutdown();
        printOutR();
    }

}
