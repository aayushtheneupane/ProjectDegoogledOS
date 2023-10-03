package com.android.systemui.pip.p004tv;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.os.Bundle;
import com.android.systemui.C1770R$anim;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.pip.p004tv.PipManager;
import java.util.Collections;

/* renamed from: com.android.systemui.pip.tv.PipMenuActivity */
public class PipMenuActivity extends Activity implements PipManager.Listener {
    private Animator mFadeInAnimation;
    private Animator mFadeOutAnimation;
    private PipControlsView mPipControlsView;
    private final PipManager mPipManager = PipManager.getInstance();
    private boolean mRestorePipSizeWhenClose;

    public void onPipEntered() {
    }

    public void onShowPipMenu() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!this.mPipManager.isPipShown()) {
            finish();
        }
        setContentView(C1779R$layout.tv_pip_menu);
        this.mPipManager.addListener(this);
        this.mRestorePipSizeWhenClose = true;
        this.mPipControlsView = (PipControlsView) findViewById(C1777R$id.pip_controls);
        this.mFadeInAnimation = AnimatorInflater.loadAnimator(this, C1770R$anim.tv_pip_menu_fade_in_animation);
        this.mFadeInAnimation.setTarget(this.mPipControlsView);
        this.mFadeOutAnimation = AnimatorInflater.loadAnimator(this, C1770R$anim.tv_pip_menu_fade_out_animation);
        this.mFadeOutAnimation.setTarget(this.mPipControlsView);
        onPipMenuActionsChanged(getIntent().getParcelableExtra("custom_actions"));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onPipMenuActionsChanged(getIntent().getParcelableExtra("custom_actions"));
    }

    private void restorePipAndFinish() {
        if (this.mRestorePipSizeWhenClose) {
            this.mPipManager.resizePinnedStack(1);
        }
        finish();
    }

    public void onResume() {
        super.onResume();
        this.mFadeInAnimation.start();
    }

    public void onPause() {
        super.onPause();
        this.mFadeOutAnimation.start();
        restorePipAndFinish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mPipManager.removeListener(this);
        this.mPipManager.resumePipResizing(1);
    }

    public void onBackPressed() {
        restorePipAndFinish();
    }

    public void onPipActivityClosed() {
        finish();
    }

    public void onPipMenuActionsChanged(ParceledListSlice parceledListSlice) {
        this.mPipControlsView.setActions(parceledListSlice != null && !parceledListSlice.getList().isEmpty() ? parceledListSlice.getList() : Collections.EMPTY_LIST);
    }

    public void onMoveToFullscreen() {
        this.mRestorePipSizeWhenClose = false;
        finish();
    }

    public void onPipResizeAboutToStart() {
        finish();
        this.mPipManager.suspendPipResizing(1);
    }
}
