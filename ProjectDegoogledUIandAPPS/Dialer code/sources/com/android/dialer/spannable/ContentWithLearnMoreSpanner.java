package com.android.dialer.spannable;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import com.android.dialer.R;
import com.android.dialer.common.Assert;

public final class ContentWithLearnMoreSpanner {
    private final Context context;

    public ContentWithLearnMoreSpanner(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public SpannableString create(String str, String str2) {
        String string = this.context.getString(R.string.general_learn_more);
        SpannableString spannableString = new SpannableString(String.format(str, new Object[]{string}));
        Assert.checkArgument(spannableString.toString().contains(string), "Couldn't add learn more link to %s", str);
        int lastIndexOf = spannableString.toString().lastIndexOf(string);
        int length = string.length() + lastIndexOf;
        spannableString.setSpan(new TypefaceSpan("sans-serif-medium"), lastIndexOf, length, 18);
        spannableString.setSpan(new UrlSpanWithoutUnderline(str2), lastIndexOf, length, 18);
        return spannableString;
    }
}
