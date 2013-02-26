package ${package};

import android.content.Context;
import android.util.Log;
import net.jarlehansen.android.gcm.client.GCMUtilsBaseIntentService;

public class GCMIntentService extends GCMUtilsBaseIntentService {

    @Override
    protected void onMessage(Context context, String msg) {
        Log.i(HelloAndroidActivity.TAG, "Message received: " + msg);
    }

    @Override
    protected void onError(Context context, String error) {
        Log.e(HelloAndroidActivity.TAG, "Error: " + error);
    }
}
