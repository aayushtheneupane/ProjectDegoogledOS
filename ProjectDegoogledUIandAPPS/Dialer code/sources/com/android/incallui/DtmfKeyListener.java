package com.android.incallui;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.DialerKeyListener;
import android.view.KeyEvent;
import android.view.View;
import com.android.dialer.common.LogUtil;

final class DtmfKeyListener extends DialerKeyListener {
    private static final Spannable EMPTY_SPANNABLE = new SpannableString("");
    private static final char[] VALID_DTMF_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '#', '*'};
    private final DialpadPresenter presenter;

    DtmfKeyListener(DialpadPresenter dialpadPresenter) {
        this.presenter = dialpadPresenter;
    }

    public boolean backspace(View view, Editable editable, int i, KeyEvent keyEvent) {
        return false;
    }

    /* access modifiers changed from: protected */
    public char[] getAcceptedChars() {
        return VALID_DTMF_CHARACTERS;
    }

    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
        LogUtil.m9i("DtmfKeyListener.onKeyDown", "overload", new Object[0]);
        if (super.onKeyDown(view, editable, i, keyEvent)) {
            return onKeyDown(keyEvent);
        }
        LogUtil.m9i("DtmfKeyListener.onKeyDown", "parent type didn't support event", new Object[0]);
        return false;
    }

    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
        LogUtil.m9i("DtmfKeyListener.onKeyUp", "overload", new Object[0]);
        super.onKeyUp(view, editable, i, keyEvent);
        return onKeyUp(keyEvent);
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyUp(KeyEvent keyEvent) {
        LogUtil.enterBlock("DtmfKeyListener.onKeyUp");
        if (keyEvent == null) {
            return true;
        }
        if (!DialerKeyListener.ok(VALID_DTMF_CHARACTERS, (char) lookup(keyEvent, EMPTY_SPANNABLE))) {
            LogUtil.m9i("DtmfKeyListener.onKeyUp", "not an accepted character", new Object[0]);
            return false;
        }
        this.presenter.stopDtmf();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyDown(KeyEvent keyEvent) {
        LogUtil.enterBlock("DtmfKeyListener.onKeyDown");
        if (keyEvent.getRepeatCount() != 0) {
            LogUtil.m9i("DtmfKeyListener.onKeyDown", "long press, ignoring", new Object[0]);
            return false;
        }
        char lookup = (char) lookup(keyEvent, EMPTY_SPANNABLE);
        if (!DialerKeyListener.ok(VALID_DTMF_CHARACTERS, lookup)) {
            LogUtil.m9i("DtmfKeyListener.onKeyDown", "not an accepted character", new Object[0]);
            return false;
        }
        this.presenter.processDtmf(lookup);
        return true;
    }
}
