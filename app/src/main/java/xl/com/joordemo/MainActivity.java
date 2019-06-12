package xl.com.joordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;

import xl.com.joordemo.joor.Reflect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用最简单的方法测试
        Class clazz = Person.class;
        try {
            Log.d("haha","reflect before name ;"+Person.name);
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);
            Object fieldObject = field.get(null);
            field.set(fieldObject,"rx");
            Log.d("haha","reflect after name ;"+Person.name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 使用JOOR测试
        /**
         *   Field modifiersField = Field.class.getDeclaredField("modifiers");
         *   modifiersField.setAccessible(true);
         *   modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
         *
         *   希望通过修改Field的修饰符，但是，Android的Field类中没有modifiers这个属性，所以，导致失败
         */
       Reflect reflect = Reflect.on("xl.com.joordemo.Person");
        reflect.set("name","rx");
        Log.d("haha","reflect after name ;"+Person.name);

    }
}
