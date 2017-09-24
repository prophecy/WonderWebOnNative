package WonderUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ProphecyX on 9/3/2017 AD.
 */

public class WonderWebView extends WebView {

    static final String TAG = WonderWebView.class.getSimpleName();
    static final String WONDERLAND = "wonderEvent";
    private WonderEvent wonderEvent = new WonderEvent();

    public class JavaScriptInterface {

        Context mContext;

        // Instantiate the interface and set the context
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void log(String tag, String message) {

            WonderLog.logDebug(tag, message);
        }

        @JavascriptInterface
        public void onEvent(String evt) {

            WonderLog.logDebug(TAG, evt);

            wonderEvent.dispatch(evt);
        }
    }

    public WonderWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        clearCache(true);
        clearHistory();
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        addJavascriptInterface(new JavaScriptInterface(context), WONDERLAND);
    }

    public void load(final String fileName) {

        // Set WebView client
        WebViewClient loadFinishedClient = new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

                // Set platform to android
                JSONObject evt = new JSONObject();
                try {
                    evt.put("name", "android");
                } catch (JSONException e) {
                    WonderLog.logError(e);
                }
                initPlatformSetting(evt.toString());
            }
        };

        setWebViewClient(loadFinishedClient);

        // Load content from URL
        loadUrl(fileName);
    }

    private void initPlatformSetting(String setting) {

        loadUrl("javascript:wonderland.init('android', '" + setting + "')");
    }

    public void sendEvent(String evt) {

        loadUrl("javascript:wonderland.onEvent('" + evt + "')");
    }

    public WonderEvent getWonderEvent() {
        return wonderEvent;
    }
}
