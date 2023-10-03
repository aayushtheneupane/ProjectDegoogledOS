package com.android.incallui.answer.impl.answermethod;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.app.Fragment;
import com.android.dialer.common.FragmentUtils;

public abstract class AnswerMethod extends Fragment {
    /* access modifiers changed from: protected */
    public AnswerMethodHolder getParent() {
        return (AnswerMethodHolder) FragmentUtils.getParentUnsafe((Fragment) this, AnswerMethodHolder.class);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentUtils.checkParent(this, AnswerMethodHolder.class);
    }

    public void setContactPhoto(Drawable drawable) {
    }

    public abstract void setHintText(CharSequence charSequence);

    public abstract void setShowIncomingWillDisconnect(boolean z);
}
