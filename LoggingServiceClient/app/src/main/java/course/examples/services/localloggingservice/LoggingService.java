package course.examples.services.localloggingservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class LoggingService extends IntentService {

	public static String EXTRA_LOG = "course.examples.services.localloggingservice.MESSAGE";
	private static final String TAG = "LoggingService";

	public LoggingService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Log.i(TAG, Objects.requireNonNull(intent.getStringExtra(EXTRA_LOG)));

	}
}
