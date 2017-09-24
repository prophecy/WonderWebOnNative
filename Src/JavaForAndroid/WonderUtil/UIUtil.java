package WonderUtil;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;


/**
 * Created by ProphecyX on 9/3/2017 AD.
 */

public class UIUtil {

    // ---------------------------------------------------------------------------------------------
    // Singleton
    // ---------------------------------------------------------------------------------------------

    private static UIUtil instance = null;

    public static UIUtil getInstance() {

        if (instance == null)
            instance = new UIUtil();

        return instance;
    }

    // ---------------------------------------------------------------------------------------------
    // Functions
    // ---------------------------------------------------------------------------------------------

    public interface ViewTreeListener {

        void onViewAddedOnViewTree();
    }

    public void waitForViewAddedOnViewTree(final View view, final ViewTreeListener viewTreeListener) {

        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } else {
                            view.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }

                        viewTreeListener.onViewAddedOnViewTree();
                    }
                });
    }
}
