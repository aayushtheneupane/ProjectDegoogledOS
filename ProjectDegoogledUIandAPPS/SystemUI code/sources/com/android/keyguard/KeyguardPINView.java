package com.android.keyguard;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.PasswordTextView;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyguardPINView extends KeyguardPinBasedInputView {
    private static List<Integer> sNumbers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
    private final AppearAnimationUtils mAppearAnimationUtils;
    private ViewGroup mContainer;
    private final DisappearAnimationUtils mDisappearAnimationUtils;
    private final DisappearAnimationUtils mDisappearAnimationUtilsLocked;
    private int mDisappearYTranslation;
    private View mDivider;
    private final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private ViewGroup mRow0;
    private ViewGroup mRow1;
    private ViewGroup mRow2;
    private ViewGroup mRow3;
    private View[][] mViews;
    /* access modifiers changed from: private */
    public final int userId;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public KeyguardPINView(Context context) {
        this(context, (AttributeSet) null);
    }

    public KeyguardPINView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.userId = KeyguardUpdateMonitor.getCurrentUser();
        this.mAppearAnimationUtils = new AppearAnimationUtils(context);
        Context context2 = context;
        this.mDisappearAnimationUtils = new DisappearAnimationUtils(context2, 125, 0.6f, 0.45f, AnimationUtils.loadInterpolator(this.mContext, 17563663));
        this.mDisappearAnimationUtilsLocked = new DisappearAnimationUtils(context2, 187, 0.6f, 0.45f, AnimationUtils.loadInterpolator(this.mContext, 17563663));
        this.mDisappearYTranslation = getResources().getDimensionPixelSize(C1775R$dimen.disappear_y_translation);
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public void resetState() {
        super.resetState();
        SecurityMessageDisplay securityMessageDisplay = this.mSecurityMessageDisplay;
        if (securityMessageDisplay != null) {
            securityMessageDisplay.setMessage((CharSequence) "");
        }
    }

    /* access modifiers changed from: protected */
    public int getPasswordTextViewId() {
        return C1777R$id.pinEntry;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mContainer = (ViewGroup) findViewById(C1777R$id.container);
        this.mRow0 = (ViewGroup) findViewById(C1777R$id.row0);
        this.mRow1 = (ViewGroup) findViewById(C1777R$id.row1);
        this.mRow2 = (ViewGroup) findViewById(C1777R$id.row2);
        this.mRow3 = (ViewGroup) findViewById(C1777R$id.row3);
        this.mDivider = findViewById(C1777R$id.divider);
        boolean z = true;
        this.mViews = new View[][]{new View[]{this.mRow0, null, null}, new View[]{findViewById(C1777R$id.key1), findViewById(C1777R$id.key2), findViewById(C1777R$id.key3)}, new View[]{findViewById(C1777R$id.key4), findViewById(C1777R$id.key5), findViewById(C1777R$id.key6)}, new View[]{findViewById(C1777R$id.key7), findViewById(C1777R$id.key8), findViewById(C1777R$id.key9)}, new View[]{findViewById(C1777R$id.delete_button), findViewById(C1777R$id.key0), findViewById(C1777R$id.key_enter)}, new View[]{null, this.mEcaView, null}};
        View findViewById = findViewById(C1777R$id.cancel_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    KeyguardPINView.this.lambda$onFinishInflate$0$KeyguardPINView(view);
                }
            });
        }
        if (Settings.System.getIntForUser(getContext().getContentResolver(), "lockscreen_quick_unlock_control", 0, -2) == 1) {
            this.mPasswordEntry.setQuickUnlockListener(new PasswordTextView.QuickUnlockListener() {
                public void onValidateQuickUnlock(String str) {
                    if (str != null && str.length() == KeyguardPINView.this.keyguardPinPasswordLength()) {
                        KeyguardPINView keyguardPINView = KeyguardPINView.this;
                        AsyncTask unused = keyguardPINView.validateQuickUnlock(keyguardPINView.mLockPatternUtils, str, keyguardPINView.userId);
                    }
                }
            });
        } else {
            this.mPasswordEntry.setQuickUnlockListener((PasswordTextView.QuickUnlockListener) null);
        }
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "lockscreen_scramble_pin_layout", 0, -2) != 1) {
            z = false;
        }
        if (z) {
            Collections.shuffle(sNumbers);
            LinearLayout linearLayout = (LinearLayout) findViewById(C1777R$id.container);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (linearLayout.getChildAt(i) instanceof LinearLayout) {
                    LinearLayout linearLayout2 = (LinearLayout) linearLayout.getChildAt(i);
                    for (int i2 = 0; i2 < linearLayout2.getChildCount(); i2++) {
                        View childAt = linearLayout2.getChildAt(i2);
                        if (childAt.getClass() == NumPadKey.class) {
                            arrayList.add((NumPadKey) childAt);
                        }
                    }
                }
            }
            for (int i3 = 0; i3 < sNumbers.size(); i3++) {
                ((NumPadKey) arrayList.get(i3)).setDigit(sNumbers.get(i3).intValue());
            }
        }
    }

    public /* synthetic */ void lambda$onFinishInflate$0$KeyguardPINView(View view) {
        this.mCallback.reset();
        this.mCallback.onCancelClicked();
    }

    public int getWrongPasswordStringId() {
        return C1784R$string.kg_wrong_pin;
    }

    public void startAppearAnimation() {
        enableClipping(false);
        setAlpha(1.0f);
        setTranslationY(this.mAppearAnimationUtils.getStartTranslation());
        AppearAnimationUtils.startTranslationYAnimation(this, 0, 500, 0.0f, this.mAppearAnimationUtils.getInterpolator());
        this.mAppearAnimationUtils.startAnimation2d(this.mViews, new Runnable() {
            public void run() {
                KeyguardPINView.this.enableClipping(true);
            }
        });
    }

    public boolean startDisappearAnimation(final Runnable runnable) {
        DisappearAnimationUtils disappearAnimationUtils;
        enableClipping(false);
        setTranslationY(0.0f);
        AppearAnimationUtils.startTranslationYAnimation(this, 0, 280, (float) this.mDisappearYTranslation, this.mDisappearAnimationUtils.getInterpolator());
        if (this.mKeyguardUpdateMonitor.needsSlowUnlockTransition()) {
            disappearAnimationUtils = this.mDisappearAnimationUtilsLocked;
        } else {
            disappearAnimationUtils = this.mDisappearAnimationUtils;
        }
        disappearAnimationUtils.startAnimation2d(this.mViews, new Runnable() {
            public void run() {
                KeyguardPINView.this.enableClipping(true);
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public void enableClipping(boolean z) {
        this.mContainer.setClipToPadding(z);
        this.mContainer.setClipChildren(z);
        this.mRow1.setClipToPadding(z);
        this.mRow2.setClipToPadding(z);
        this.mRow3.setClipToPadding(z);
        setClipChildren(z);
    }

    /* access modifiers changed from: private */
    public AsyncTask<?, ?, ?> validateQuickUnlock(final LockPatternUtils lockPatternUtils, final String str, final int i) {
        C04454 r0 = new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... voidArr) {
                try {
                    return Boolean.valueOf(lockPatternUtils.checkPassword(str, i));
                } catch (LockPatternUtils.RequestThrottledException unused) {
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean bool) {
                KeyguardPINView.this.runQuickUnlock(bool);
            }
        };
        r0.execute(new Void[0]);
        return r0;
    }

    /* access modifiers changed from: private */
    public void runQuickUnlock(Boolean bool) {
        if (bool.booleanValue()) {
            this.mPasswordEntry.setEnabled(false);
            this.mCallback.reportUnlockAttempt(this.userId, true, 0);
            this.mCallback.dismiss(true, this.userId);
            resetPasswordText(true, true);
        }
    }

    /* access modifiers changed from: private */
    public int keyguardPinPasswordLength() {
        int i;
        try {
            i = (int) this.mLockPatternUtils.getLockSettings().getLong("lockscreen.pin_password_length", -1, this.userId);
        } catch (Exception unused) {
            i = -1;
        }
        if (i >= 4) {
            return i;
        }
        return -1;
    }
}
