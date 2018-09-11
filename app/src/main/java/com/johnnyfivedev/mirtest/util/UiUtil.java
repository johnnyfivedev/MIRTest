package com.johnnyfivedev.mirtest.util;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

public class UiUtil {

    public static void setParsedHtmlText(TextView target, String htmlText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            target.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            target.setText(Html.fromHtml(htmlText));
        }
    }
}
