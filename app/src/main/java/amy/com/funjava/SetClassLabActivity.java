package amy.com.funjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashSet;
import java.util.Set;

/*set class and Integer test class*/
public class SetClassLabActivity extends AppCompatActivity {

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
        ((RadioButton) findViewById(R.id.radio_b)).setText("int targetB = 110033");
        ((RadioButton) findViewById(R.id.radio_c)).setText("Integer targetC = Integer.valueOf(110033)");

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
        makeList();

        Log.d("testf", "mode:" + mode);
        Log.d("testf", "testList01 size:" + testList01.size());
        if (testList01.remove(targetA)) {
            Log.d("testf", "remove:targetA");
        }else {
            Log.d("testf", "not remove:targetA");
        }
        Log.d("testf", "testList01 size:" + testList01.size());

        if (testList01.contains(targetB)) {
            Log.d("testf", "remove:targetB");
        }else {
            Log.d("testf", "not remove:targetB");
        }

        Log.d("testf", "testList01 size:" + testList01.size());
        if (testList01.contains(targetC)) {
            Log.d("testf", "remove:targetC");
        }else {
            Log.d("testf", "not remove:targetC");
        }

    }

    public void onClickedButton_B(View view) {
        makeList();

        Log.d("testf", "mode:" + mode);
        Log.d("testf", "testList01 size:" + testList01.size());
        if (testList01.contains(targetA)) {
            Log.d("testf", "contains:targetA");
        }else {
            Log.d("testf", "not contains:targetA");
        }

        if (testList01.contains(targetB)) {
            Log.d("testf", "contains:targetB");
        }else {
            Log.d("testf", "not contains:targetB");
        }

        if (testList01.contains(targetC)) {
            Log.d("testf", "contains:targetC");
        }else {
            Log.d("testf", "not contains:targetC");
        }


    }

    private void makeList() {
        testList01.clear();
        switch (mode) {
            case MODE_A: {
                testList01.add(testA);
            }
            break;
            case MODE_B: {
                testList01.add(testB);
            }
            break;
            case MODE_C: {
                testList01.add(testC);
            }
            break;
        }
    }
}
