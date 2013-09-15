package com.kwebble.android.ui.demo.javascript;

import android.webkit.JavascriptInterface;

/**
 * Exposed JavaScript API.
 * 
 * @author Rob Schlüter
 */
public class AppApi {

    /**
     * The activity used by this API.
     */
    private final MainActivity activity;

    /**
     * Creates the API.
     * 
     * @param activity
     *            the activity used by this API.
     */
    public AppApi(final MainActivity activity) {
        this.activity = activity;
    }

    /**
     * Logs a message.
     * 
     * @param text
     *            the text to log.
     */
    @JavascriptInterface
    public void log(final String text) {
        this.activity.appendToTextView(text);
    }
}
