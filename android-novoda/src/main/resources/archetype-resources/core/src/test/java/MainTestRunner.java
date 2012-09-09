package ${package}.tests;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.runners.model.InitializationError;

import java.io.File;

public class MainTestRunner extends RobolectricTestRunner {
    public MainTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new File("../app/AndroidManifest.xml"), new File("../app/res/"));
    }
}