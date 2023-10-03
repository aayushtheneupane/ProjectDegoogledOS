package com.android.dialer.oem;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class MotorolaHiddenMenuKeySequence {
    private static MotorolaHiddenMenuKeySequence instance;
    boolean featureHiddenMenuEnabled = false;
    final List<String> hiddenKeyPatternIntents = new ArrayList();
    final List<String> hiddenKeyPatterns = new ArrayList();
    final List<String> hiddenKeySequenceIntents = new ArrayList();
    final List<String> hiddenKeySequences = new ArrayList();

    MotorolaHiddenMenuKeySequence(Context context, SystemPropertiesAccessor systemPropertiesAccessor) {
        if (context.getPackageManager().hasSystemFeature("com.motorola.software.sprint.hidden_menu") && context.getResources().getBoolean(R.bool.motorola_hidden_menu_enabled)) {
            Collections.addAll(this.hiddenKeySequences, context.getResources().getStringArray(R.array.motorola_hidden_menu_key_sequence));
            Collections.addAll(this.hiddenKeySequenceIntents, context.getResources().getStringArray(R.array.motorola_hidden_menu_key_sequence_intents));
            Collections.addAll(this.hiddenKeyPatterns, context.getResources().getStringArray(R.array.motorola_hidden_menu_key_pattern));
            Collections.addAll(this.hiddenKeyPatternIntents, context.getResources().getStringArray(R.array.motorola_hidden_menu_key_pattern_intents));
            this.featureHiddenMenuEnabled = true;
        }
        if ("tracfone".equals(systemPropertiesAccessor.get("ro.carrier"))) {
            this.hiddenKeySequences.add("#83865625#");
            this.hiddenKeySequenceIntents.add("com.motorola.extensions.TFUnlock");
            this.hiddenKeySequences.add("#83782887#");
            this.hiddenKeySequenceIntents.add("com.motorola.extensions.TFStatus");
            this.featureHiddenMenuEnabled = true;
        }
        if (this.hiddenKeySequences.size() != this.hiddenKeySequenceIntents.size() || this.hiddenKeyPatterns.size() != this.hiddenKeyPatternIntents.size() || (this.hiddenKeySequences.isEmpty() && this.hiddenKeyPatterns.isEmpty())) {
            LogUtil.m8e("MotorolaHiddenMenuKeySequence", "the key sequence array is not matching, turn off feature.key sequence: %d != %d, key pattern %d != %d", Integer.valueOf(this.hiddenKeySequences.size()), Integer.valueOf(this.hiddenKeySequenceIntents.size()), Integer.valueOf(this.hiddenKeyPatterns.size()), Integer.valueOf(this.hiddenKeyPatternIntents.size()));
            this.featureHiddenMenuEnabled = false;
        }
    }

    private static synchronized MotorolaHiddenMenuKeySequence getInstance(Context context) {
        MotorolaHiddenMenuKeySequence motorolaHiddenMenuKeySequence;
        synchronized (MotorolaHiddenMenuKeySequence.class) {
            if (instance == null) {
                instance = new MotorolaHiddenMenuKeySequence(context, new SystemPropertiesAccessor());
            }
            motorolaHiddenMenuKeySequence = instance;
        }
        return motorolaHiddenMenuKeySequence;
    }

    static boolean handleCharSequence(Context context, String str) {
        boolean z;
        boolean z2;
        if (!getInstance(context).featureHiddenMenuEnabled) {
            return false;
        }
        MotorolaHiddenMenuKeySequence instance2 = getInstance(context);
        if (str.length() > 3 && instance2.hiddenKeySequences != null && instance2.hiddenKeySequenceIntents != null) {
            int i = 0;
            while (true) {
                if (i >= instance2.hiddenKeySequences.size()) {
                    break;
                } else if (instance2.hiddenKeySequences.get(i).equals(str)) {
                    z = sendIntent(context, str, instance2.hiddenKeySequenceIntents.get(i));
                    break;
                } else {
                    i++;
                }
            }
        }
        z = false;
        if (!z) {
            MotorolaHiddenMenuKeySequence instance3 = getInstance(context);
            if (str.length() > 3 && instance3.hiddenKeyPatterns != null && instance3.hiddenKeyPatternIntents != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= instance3.hiddenKeyPatterns.size()) {
                        break;
                    } else if (Pattern.matches(instance3.hiddenKeyPatterns.get(i2), str)) {
                        z2 = sendIntent(context, str, instance3.hiddenKeyPatternIntents.get(i2));
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            z2 = false;
            if (z2) {
                return true;
            }
            return false;
        }
        return true;
    }

    private static boolean sendIntent(Context context, String str, String str2) {
        ActivityInfo activityInfo;
        new Object[1][0] = str;
        try {
            Intent intent = new Intent(str2);
            intent.addFlags(335544320);
            intent.putExtra("HiddenMenuCode", str);
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null || (activityInfo = resolveActivity.activityInfo) == null || !activityInfo.enabled) {
                LogUtil.m10w("MotorolaHiddenMenuKeySequence.sendIntent", "not able to resolve the intent", new Object[0]);
                return false;
            }
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtil.m7e("MotorolaHiddenMenuKeySequence.sendIntent", "handleHiddenMenu Key Pattern Exception", (Throwable) e);
        }
    }

    static void setInstanceForTest(MotorolaHiddenMenuKeySequence motorolaHiddenMenuKeySequence) {
        instance = motorolaHiddenMenuKeySequence;
    }
}
