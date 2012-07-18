package ${package}.test;

import android.test.ActivityInstrumentationTestCase2;
import ${package}.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<HelloAndroidActivity> {

    public HelloAndroidActivityTest() {
#set( $Integer = 0 )
#set( $platformNumber = $Integer.parseInt($platform) )
#if($platformNumber > 7) 
        super(HelloAndroidActivity.class); 
#else 
        super("${package}", HelloAndroidActivity.class);
#end
    }

    public void testActivity() {
        HelloAndroidActivity activity = getActivity();
        assertNotNull(activity);
    }
}

