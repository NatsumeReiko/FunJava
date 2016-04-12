package amy.com.funjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ObjectActivity extends AppCompatActivity {

    private String[] elements;
    private int count = 0;
    private String removedObject = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        elements = new String[]{"1", "2", "3", "4", "5"};
    }

    public void onClickedButton(View view) {
        switch (view.getId()) {
            case R.id.remove: {
                if (elements.length == 0) {
                    return;
                }
                removedObject = elements[elements.length - 1];
                elements[elements.length - 1] = null;
                printOutString("remove");
            }
            break;
            case R.id.add: {
                elements[elements.length - 1] = "add " + String.valueOf(++count);
                printOutString("add");
            }
            break;
        }
    }

    private void printOutString(String tag) {
        if (tag == null || tag.isEmpty()) {
            tag = "no tag";
        }

        if (elements.length == 0) {
            return;
        }

        Log.d(tag, "size: " + elements.length);

        for (String item : elements) {
            Log.d(tag, "item:" + item);
        }

    }
}
