package amy.com.funjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashSet;
import java.util.Set;

/*set class and Integer test class*/
public class SampleActivity extends AppCompatActivity {

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
    }

    public void onClickedButton_B(View view) {
    }
    public void onClickedButton_C(View view) {
    }
    public void onClickedButton_D(View view) {
    }
    public void onClickedButton_E(View view) {
    }
}
