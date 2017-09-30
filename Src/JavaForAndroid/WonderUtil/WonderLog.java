package WonderUtil;

import android.util.Log;

/**
 * Created by ProphecyX on 6/18/16 AD.
 */
public class WonderLog {
    
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // Log
    
    private static String TAG = WonderLog.class.getSimpleName();
    
    public static void logDebug(String msg) {
        logDebug(TAG, msg);
    }
    
    public static void logDebug(String tag, String msg) {
        Log.d(tag, msg);
    }
    
    public static void logDebug(Exception e) {
        logDebug(TAG, e);
    }
    
    public static void logDebug(String tag, Exception e) {
        Log.d(tag, Log.getStackTraceString(e));
    }
    
    public static void logWarning(String msg) {
        logWarning(TAG, msg);
    }
    
    public static void logWarning(String tag, String msg) {
        Log.w(tag, msg);
    }
    
    public static void logWarning(Exception e) {
        logWarning(TAG, e);
    }
    
    public static void logWarning(String tag, Exception e) {
        Log.w(tag, Log.getStackTraceString(e));
    }
    
    public static void logError(String msg) {
        logError(TAG, msg);
    }
    
    public static void logError(String tag, String msg) {
        Log.e(tag, msg);
    }
    
    public static void logError(Exception e) {
        logError(TAG, e);
    }
    
    public static void logError(String tag, Exception e) {
        Log.e(tag, Log.getStackTraceString(e));
    }
}

