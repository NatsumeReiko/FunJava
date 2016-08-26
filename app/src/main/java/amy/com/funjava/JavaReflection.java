package amy.com.funjava;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class JavaReflection extends AppCompatActivity {
    private static final String TAG = "aaa";

    final private static int MODE_A = 1;
    final private static int MODE_B = 2;
    final private static int MODE_C = 3;

    int mode = MODE_A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set);

        setViews();
    }

    private void setViews() {
        Log.d(TAG, "OS VERSION:" + Build.VERSION.RELEASE);
        Log.d(TAG, "OS VERSION NAME:" + Build.VERSION.SDK_INT);
        findViewById(R.id.root).setBackgroundResource(R.drawable.bg_pink);
    }

    public void onClickedButton_A(View view) {

        try {
            List<Field> privateFields = new ArrayList<>();
            Field[] allFields = Resources.class.getDeclaredFields();
            for (Field item : allFields) {
                Log.d(TAG, "f " + item.getName());
                if (Modifier.isPrivate(item.getModifiers())) {
                    privateFields.add(item);
                }
            }
            Log.d(TAG, "---------------------------------");

            Method[] methods = Resources.class.getDeclaredMethods();
            for (Method item : methods) {
                Log.d(TAG, "m " + item.getName());
                if (Modifier.isPrivate(item.getModifiers())) {
                }
                if ("getPreloadedDrawables".equals(item.getName())) {
                    mode = MODE_C;
                }
            }

            final Method method = Resources.class.getDeclaredMethod("getPreloadedDrawables");
            method.setAccessible(true);

            LongSparseArray<Drawable.ConstantState> result
                    = (LongSparseArray<Drawable.ConstantState>)
                    method.invoke(this.getResources());

            mode = MODE_C;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickedButton_B(View view) {

        try {
//            Class clazz = Class.forName("android.content.res.Resources");
//            Field field = clazz.getDeclaredField("sPreloadedDrawables");
            Field field = Resources.class.getDeclaredField("sPreloadedDrawables");
            field.setAccessible(true);
            Object cacheField = field.get(this);
            if (cacheField instanceof LongSparseArray[]) {
            } else if (cacheField instanceof LongSparseArray) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickedButton_C(View view) {
        try {
            Class clazz = Class.forName("android.content.res.Resources");
            Field field = clazz.getDeclaredField("mResourcesImpl");
            field.setAccessible(true);
            Object mResourcesImpl = field.get(this.getResources());

            Class classResourcesImpl = Class.forName("android.content.res.ResourcesImpl");

            Field fieldSPreloadedDrawables = classResourcesImpl.getDeclaredField("sPreloadedDrawables");
            fieldSPreloadedDrawables.setAccessible(true);
            Object sPreloadedDrawables = fieldSPreloadedDrawables.get(mResourcesImpl);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onClickedButton_D(View view) {
    }

    public void onClickedButton_E(View view) {
    }


}
