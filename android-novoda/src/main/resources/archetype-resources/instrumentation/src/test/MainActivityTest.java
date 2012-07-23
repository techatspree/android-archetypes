package ${package}.test;

import android.test.ActivityInstrumentationTestCase2;
import ${package}.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
#set( $Integer = 0 )
#set( $platformNumber = $Integer.parseInt($platform) )
#if($platformNumber > 7) 
        super(MainActivity.class);
#else 
        super("${package}", MainActivity.class);
#end
    }

    public void testActivity() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}

