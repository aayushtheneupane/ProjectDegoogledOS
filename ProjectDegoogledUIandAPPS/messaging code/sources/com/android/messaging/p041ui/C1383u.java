package com.android.messaging.p041ui;

import android.graphics.drawable.ColorDrawable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.u */
class C1383u extends ActionMode {
    private final ActionMode.Callback mCallback;
    private View mCustomView;
    private CharSequence mSubtitle;
    private CharSequence mTitle;
    final /* synthetic */ BugleActionBarActivity this$0;

    public C1383u(BugleActionBarActivity bugleActionBarActivity, ActionMode.Callback callback) {
        this.this$0 = bugleActionBarActivity;
        this.mCallback = callback;
    }

    public void finish() {
        C1383u unused = this.this$0.mActionMode = null;
        this.mCallback.onDestroyActionMode(this);
        this.this$0.supportInvalidateOptionsMenu();
        this.this$0.invalidateActionBar();
    }

    public ActionMode.Callback getCallback() {
        return this.mCallback;
    }

    public View getCustomView() {
        return this.mCustomView;
    }

    public Menu getMenu() {
        return this.this$0.f1603Rb;
    }

    public MenuInflater getMenuInflater() {
        return this.this$0.getMenuInflater();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void invalidate() {
        this.this$0.invalidateActionBar();
    }

    public void setCustomView(View view) {
        this.mCustomView = view;
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }

    public void updateActionBar(ActionBar actionBar) {
        actionBar.setDisplayOptions(4);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(false);
        this.this$0.mActionMode.mCallback.onPrepareActionMode(this.this$0.mActionMode, this.this$0.f1603Rb);
        actionBar.setBackgroundDrawable(new ColorDrawable(this.this$0.getResources().getColor(R.color.contextual_action_bar_background_color)));
        actionBar.setHomeAsUpIndicator((int) R.drawable.ic_cancel_small);
        actionBar.show();
    }

    public void setSubtitle(int i) {
        this.mSubtitle = this.this$0.getResources().getString(i);
    }

    public void setTitle(int i) {
        this.mTitle = this.this$0.getResources().getString(i);
    }
}
