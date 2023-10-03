package com.android.incallui.answer.impl.answermethod;

import android.app.Activity;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.LogUtil;

public class AnswerMethodFactory {
    private static boolean shouldUseTwoButtonMethodForTesting;

    public static AnswerMethod createAnswerMethod(Activity activity) {
        if (needTwoButton(activity)) {
            return new TwoButtonMethod();
        }
        return new FlingUpDownMethod();
    }

    private static boolean needTwoButton(Activity activity) {
        if (shouldUseTwoButtonMethodForTesting) {
            LogUtil.m9i("AnswerMethodFactory.needTwoButton", "enabled for testing", new Object[0]);
            return true;
        } else if (R$style.isTouchExplorationEnabled(activity) || activity.isInMultiWindowMode()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean needsReplacement(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return !(fragment instanceof TwoButtonMethod) && needTwoButton(fragment.getActivity());
    }

    public static void setShouldUseTwoButtonMethodForTesting(boolean z) {
        shouldUseTwoButtonMethodForTesting = z;
    }
}
