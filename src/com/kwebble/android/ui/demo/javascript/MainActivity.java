package com.kwebble.android.ui.demo.javascript;

import java.text.DateFormat;
import java.util.Date;

import com.kwebble.android.ui.demo.javascript.bridge.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * The main screen.
 * 
 * @author Rob Schlüter
 */
public class MainActivity extends Activity {

    /**
     * Asset URL of the web UI of this application.
     */
    private static final String WEB_UI_URL        = "file:///android_asset/app.html";

    /**
     * JavaScript call to initialize the web UI.
     */
    private static final String LOG_TO_WEB_UI_URL = "javascript:api.log('%s')";

    /**
     * Explicit constructor.
     */
    public MainActivity() {
        super();
    }

    /**
     * Appends text to the text view.
     * 
     * @param text
     *            the text to append.
     */
    public void appendToTextView(final String text) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) MainActivity.this.findViewById(R.id.textView)).append("Received " + text
                        + System.getProperty("line.separator"));
            }
        });
    }

    /**
     * Returns the configured {@link WebView} to display the user interface.
     * 
     * @return the view.
     */
    private WebView getUi() {
        return (WebView) this.findViewById(R.id.webView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        final WebView webView = this.getUi();
        webView.getSettings().setJavaScriptEnabled(Boolean.TRUE);
        webView.loadUrl(MainActivity.WEB_UI_URL);
        webView.addJavascriptInterface(new AppApi(this), "APP");

        this.setupButtons();
    }

    /**
     * Registers button handlers.
     */
    private void setupButtons() {
        ((Button) this.findViewById(R.id.call_javascript_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MainActivity.this.getUi().loadUrl(
                        String.format(MainActivity.LOG_TO_WEB_UI_URL, DateFormat.getTimeInstance().format(new Date())));
            }
        });
    }
}
