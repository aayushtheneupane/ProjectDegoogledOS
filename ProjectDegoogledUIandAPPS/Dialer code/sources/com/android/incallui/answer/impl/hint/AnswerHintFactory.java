package com.android.incallui.answer.impl.hint;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.storage.StorageComponent;

public class AnswerHintFactory {
    static final String ANSWERED_COUNT_PREFERENCE_KEY = "answer_hint_answered_count";
    static final String CONFIG_ANSWER_HINT_ANSWERED_THRESHOLD_KEY = "answer_hint_answered_threshold";
    static final String CONFIG_ANSWER_HINT_WHITELISTED_DEVICES_KEY = "answer_hint_whitelisted_devices";
    private final PawImageLoader pawImageLoader;

    public AnswerHintFactory(PawImageLoader pawImageLoader2) {
        Assert.isNotNull(pawImageLoader2);
        this.pawImageLoader = pawImageLoader2;
    }

    static boolean shouldShowAnswerHint(Context context, String str) {
        if (R$style.isTouchExplorationEnabled(context)) {
            return false;
        }
        String string = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getString(CONFIG_ANSWER_HINT_WHITELISTED_DEVICES_KEY, "/hammerhead//bullhead//angler//shamu//gm4g//gm4g_s//AQ4501//gce_x86_phone//gm4gtkc_s//Sparkle_V//Mi-498//AQ4502//imobileiq2//A65//H940//m8_google//m0xx//A10//ctih220//Mi438S//bacon/");
        if (!string.contains("/" + str + "/")) {
            return false;
        }
        int i = StorageComponent.get(context).unencryptedSharedPrefs().getInt(ANSWERED_COUNT_PREFERENCE_KEY, 0);
        long j = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getLong(CONFIG_ANSWER_HINT_ANSWERED_THRESHOLD_KEY, 3);
        LogUtil.m9i("AnswerHintFactory.shouldShowAnswerHint", "answerCount: %d, threshold: %d", Integer.valueOf(i), Long.valueOf(j));
        if (((long) i) < j) {
            return true;
        }
        return false;
    }

    public AnswerHint create(Context context, long j, long j2) {
        if (shouldShowAnswerHint(context, Build.PRODUCT)) {
            return new DotAnswerHint(context, j, j2);
        }
        Drawable loadPayload = ((PawImageLoaderImpl) this.pawImageLoader).loadPayload(context);
        if (loadPayload != null) {
            return new PawAnswerHint(context, loadPayload, j, j2);
        }
        return new EmptyAnswerHint();
    }
}
