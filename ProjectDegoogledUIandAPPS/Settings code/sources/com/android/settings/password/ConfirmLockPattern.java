package com.android.settings.password;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.settings.password.ConfirmDeviceCredentialBaseActivity;
import com.android.settings.password.ConfirmLockPattern;
import com.android.settings.password.CredentialCheckResultTracker;
import com.android.settingslib.animation.AppearAnimationCreator;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.havoc.config.center.C1715R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfirmLockPattern extends ConfirmDeviceCredentialBaseActivity {

    public static class InternalActivity extends ConfirmLockPattern {
    }

    private enum Stage {
        NeedToUnlock,
        NeedToUnlockWrong,
        LockedOut
    }

    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", ConfirmLockPatternFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ConfirmLockPatternFragment.class.getName().equals(str);
    }

    public static class ConfirmLockPatternFragment extends ConfirmDeviceCredentialBaseFragment implements AppearAnimationCreator<Object>, CredentialCheckResultTracker.Listener {
        private AppearAnimationUtils mAppearAnimationUtils;
        /* access modifiers changed from: private */
        public Runnable mClearPatternRunnable = new Runnable() {
            public void run() {
                ConfirmLockPatternFragment.this.mLockPatternView.clearPattern();
            }
        };
        private LockPatternView.OnPatternListener mConfirmExistingLockPatternListener = new LockPatternView.OnPatternListener() {
            public void onPatternCellAdded(List<LockPatternView.Cell> list) {
            }

            public void onPatternStart() {
                ConfirmLockPatternFragment.this.mLockPatternView.removeCallbacks(ConfirmLockPatternFragment.this.mClearPatternRunnable);
            }

            public void onPatternCleared() {
                ConfirmLockPatternFragment.this.mLockPatternView.removeCallbacks(ConfirmLockPatternFragment.this.mClearPatternRunnable);
            }

            public void onPatternDetected(List<LockPatternView.Cell> list) {
                if (ConfirmLockPatternFragment.this.mPendingLockCheck == null && !ConfirmLockPatternFragment.this.mDisappearing) {
                    ConfirmLockPatternFragment.this.mLockPatternView.setEnabled(false);
                    boolean booleanExtra = ConfirmLockPatternFragment.this.getActivity().getIntent().getBooleanExtra("has_challenge", false);
                    Intent intent = new Intent();
                    if (!booleanExtra) {
                        startCheckPattern(list, intent);
                    } else if (isInternalActivity()) {
                        startVerifyPattern(list, intent);
                    } else {
                        ConfirmLockPatternFragment.this.mCredentialCheckResultTracker.setResult(false, intent, 0, ConfirmLockPatternFragment.this.mEffectiveUserId);
                    }
                }
            }

            /* access modifiers changed from: private */
            public boolean isInternalActivity() {
                return ConfirmLockPatternFragment.this.getActivity() instanceof InternalActivity;
            }

            private void startVerifyPattern(List<LockPatternView.Cell> list, final Intent intent) {
                AsyncTask asyncTask;
                ConfirmLockPatternFragment confirmLockPatternFragment = ConfirmLockPatternFragment.this;
                final int i = confirmLockPatternFragment.mEffectiveUserId;
                int i2 = confirmLockPatternFragment.mUserId;
                long longExtra = confirmLockPatternFragment.getActivity().getIntent().getLongExtra("challenge", 0);
                C11311 r8 = new LockPatternChecker.OnVerifyCallback() {
                    public void onVerified(byte[] bArr, int i) {
                        boolean z;
                        AsyncTask unused = ConfirmLockPatternFragment.this.mPendingLockCheck = null;
                        if (bArr != null) {
                            z = true;
                            if (ConfirmLockPatternFragment.this.mReturnCredentials) {
                                intent.putExtra("hw_auth_token", bArr);
                            }
                        } else {
                            z = false;
                        }
                        ConfirmLockPatternFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i, i);
                    }
                };
                ConfirmLockPatternFragment confirmLockPatternFragment2 = ConfirmLockPatternFragment.this;
                if (i == i2) {
                    asyncTask = LockPatternChecker.verifyPattern(confirmLockPatternFragment2.mLockPatternUtils, list, longExtra, i2, r8);
                } else {
                    LockPatternUtils lockPatternUtils = confirmLockPatternFragment2.mLockPatternUtils;
                    asyncTask = LockPatternChecker.verifyTiedProfileChallenge(lockPatternUtils, LockPatternUtils.patternToByteArray(list, lockPatternUtils.getLockPatternSize(confirmLockPatternFragment2.mEffectiveUserId)), true, longExtra, i2, r8);
                }
                AsyncTask unused = confirmLockPatternFragment2.mPendingLockCheck = asyncTask;
            }

            private void startCheckPattern(final List<LockPatternView.Cell> list, final Intent intent) {
                if (list.size() < 4) {
                    ConfirmLockPatternFragment confirmLockPatternFragment = ConfirmLockPatternFragment.this;
                    confirmLockPatternFragment.onPatternChecked(false, intent, 0, confirmLockPatternFragment.mEffectiveUserId, false);
                    return;
                }
                ConfirmLockPatternFragment confirmLockPatternFragment2 = ConfirmLockPatternFragment.this;
                final int i = confirmLockPatternFragment2.mEffectiveUserId;
                AsyncTask unused = confirmLockPatternFragment2.mPendingLockCheck = LockPatternChecker.checkPattern(confirmLockPatternFragment2.mLockPatternUtils, list, i, new LockPatternChecker.OnCheckCallback() {
                    public void onChecked(boolean z, int i) {
                        AsyncTask unused = ConfirmLockPatternFragment.this.mPendingLockCheck = null;
                        if (z && C11303.this.isInternalActivity() && ConfirmLockPatternFragment.this.mReturnCredentials) {
                            intent.putExtra("type", 2);
                            intent.putExtra("password", ConfirmLockPatternFragment.this.mLockPatternUtils.patternToByteArray(list, i));
                        }
                        ConfirmLockPatternFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i, i);
                    }
                });
            }
        };
        private CountDownTimer mCountdownTimer;
        /* access modifiers changed from: private */
        public CredentialCheckResultTracker mCredentialCheckResultTracker;
        private CharSequence mDetailsText;
        private TextView mDetailsTextView;
        private DisappearAnimationUtils mDisappearAnimationUtils;
        /* access modifiers changed from: private */
        public boolean mDisappearing = false;
        private CharSequence mHeaderText;
        private TextView mHeaderTextView;
        private View mLeftSpacerLandscape;
        /* access modifiers changed from: private */
        public LockPatternView mLockPatternView;
        /* access modifiers changed from: private */
        public AsyncTask<?, ?, ?> mPendingLockCheck;
        private View mRightSpacerLandscape;

        public int getMetricsCategory() {
            return 31;
        }

        public void onSaveInstanceState(Bundle bundle) {
        }

        /* access modifiers changed from: protected */
        public void onShowError() {
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(((ConfirmLockPattern) getActivity()).getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.NORMAL ? C1715R.layout.confirm_lock_pattern_normal : C1715R.layout.confirm_lock_pattern, viewGroup, false);
            this.mHeaderTextView = (TextView) inflate.findViewById(C1715R.C1718id.headerText);
            this.mLockPatternView = inflate.findViewById(C1715R.C1718id.lockPattern);
            this.mDetailsTextView = (TextView) inflate.findViewById(C1715R.C1718id.sud_layout_description);
            this.mErrorTextView = (TextView) inflate.findViewById(C1715R.C1718id.errorText);
            this.mLeftSpacerLandscape = inflate.findViewById(C1715R.C1718id.leftSpacer);
            this.mRightSpacerLandscape = inflate.findViewById(C1715R.C1718id.rightSpacer);
            inflate.findViewById(C1715R.C1718id.topLayout).setDefaultTouchRecepient(this.mLockPatternView);
            Intent intent = getActivity().getIntent();
            if (intent != null) {
                this.mHeaderText = intent.getCharSequenceExtra("com.android.settings.ConfirmCredentials.header");
                this.mDetailsText = intent.getCharSequenceExtra("com.android.settings.ConfirmCredentials.details");
            }
            this.mLockPatternView.setTactileFeedbackEnabled(this.mLockPatternUtils.isTactileFeedbackEnabled());
            this.mLockPatternView.setInStealthMode(!this.mLockPatternUtils.isVisiblePatternEnabled(this.mEffectiveUserId));
            this.mLockPatternView.setLockPatternSize(this.mLockPatternUtils.getLockPatternSize(this.mEffectiveUserId));
            this.mLockPatternView.setOnPatternListener(this.mConfirmExistingLockPatternListener);
            updateStage(Stage.NeedToUnlock);
            if (bundle == null && !this.mFrp && !this.mLockPatternUtils.isLockPatternEnabled(this.mEffectiveUserId)) {
                getActivity().setResult(-1);
                getActivity().finish();
            }
            this.mAppearAnimationUtils = new AppearAnimationUtils(getContext(), 220, 2.0f, 1.3f, AnimationUtils.loadInterpolator(getContext(), 17563662));
            this.mDisappearAnimationUtils = new DisappearAnimationUtils(getContext(), 125, 4.0f, 0.3f, AnimationUtils.loadInterpolator(getContext(), 17563663), new AppearAnimationUtils.RowTranslationScaler() {
                public float getRowTranslationScale(int i, int i2) {
                    return ((float) (i2 - i)) / ((float) i2);
                }
            });
            setAccessibilityTitle(this.mHeaderTextView.getText());
            this.mCredentialCheckResultTracker = (CredentialCheckResultTracker) getFragmentManager().findFragmentByTag("check_lock_result");
            if (this.mCredentialCheckResultTracker == null) {
                this.mCredentialCheckResultTracker = new CredentialCheckResultTracker();
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.add((Fragment) this.mCredentialCheckResultTracker, "check_lock_result");
                beginTransaction.commit();
            }
            return inflate;
        }

        public void onPause() {
            super.onPause();
            CountDownTimer countDownTimer = this.mCountdownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCredentialCheckResultTracker.setListener((CredentialCheckResultTracker.Listener) null);
        }

        public void onResume() {
            super.onResume();
            long lockoutAttemptDeadline = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mEffectiveUserId);
            if (lockoutAttemptDeadline != 0) {
                this.mCredentialCheckResultTracker.clearResult();
                handleAttemptLockout(lockoutAttemptDeadline);
            } else if (!this.mLockPatternView.isEnabled()) {
                updateStage(Stage.NeedToUnlock);
            }
            this.mCredentialCheckResultTracker.setListener(this);
        }

        public void prepareEnterAnimation() {
            super.prepareEnterAnimation();
            this.mHeaderTextView.setAlpha(0.0f);
            this.mCancelButton.setAlpha(0.0f);
            this.mLockPatternView.setAlpha(0.0f);
            this.mDetailsTextView.setAlpha(0.0f);
        }

        private int getDefaultDetails() {
            if (this.mFrp) {
                return C1715R.string.lockpassword_confirm_your_pattern_details_frp;
            }
            boolean isStrongAuthRequired = isStrongAuthRequired();
            return UserManager.get(getActivity()).isManagedProfile(this.mEffectiveUserId) ? isStrongAuthRequired ? C1715R.string.lockpassword_strong_auth_required_work_pattern : C1715R.string.lockpassword_confirm_your_pattern_generic_profile : isStrongAuthRequired ? C1715R.string.lockpassword_strong_auth_required_device_pattern : C1715R.string.lockpassword_confirm_your_pattern_generic;
        }

        private Object[][] getActiveViews() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ArrayList(Collections.singletonList(this.mHeaderTextView)));
            arrayList.add(new ArrayList(Collections.singletonList(this.mDetailsTextView)));
            if (this.mCancelButton.getVisibility() == 0) {
                arrayList.add(new ArrayList(Collections.singletonList(this.mCancelButton)));
            }
            LockPatternView.CellState[][] cellStates = this.mLockPatternView.getCellStates();
            for (int i = 0; i < cellStates.length; i++) {
                ArrayList arrayList2 = new ArrayList();
                for (LockPatternView.CellState add : cellStates[i]) {
                    arrayList2.add(add);
                }
                arrayList.add(arrayList2);
            }
            Object[][] objArr = (Object[][]) Array.newInstance(Object.class, new int[]{arrayList.size(), cellStates[0].length});
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ArrayList arrayList3 = (ArrayList) arrayList.get(i2);
                for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                    objArr[i2][i3] = arrayList3.get(i3);
                }
            }
            return objArr;
        }

        public void startEnterAnimation() {
            super.startEnterAnimation();
            this.mLockPatternView.setAlpha(1.0f);
            this.mAppearAnimationUtils.startAnimation2d(getActiveViews(), (Runnable) null, this);
        }

        /* access modifiers changed from: private */
        public void updateStage(Stage stage) {
            int i = C11271.f60xec47707f[stage.ordinal()];
            if (i == 1) {
                CharSequence charSequence = this.mHeaderText;
                if (charSequence != null) {
                    this.mHeaderTextView.setText(charSequence);
                } else {
                    this.mHeaderTextView.setText(getDefaultHeader());
                }
                CharSequence charSequence2 = this.mDetailsText;
                if (charSequence2 != null) {
                    this.mDetailsTextView.setText(charSequence2);
                } else {
                    this.mDetailsTextView.setText(getDefaultDetails());
                }
                this.mErrorTextView.setText("");
                updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId));
                this.mLockPatternView.setEnabled(true);
                this.mLockPatternView.enableInput();
                this.mLockPatternView.clearPattern();
            } else if (i == 2) {
                showError((int) C1715R.string.lockpattern_need_to_unlock_wrong, 3000);
                this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                this.mLockPatternView.setEnabled(true);
                this.mLockPatternView.enableInput();
            } else if (i == 3) {
                this.mLockPatternView.clearPattern();
                this.mLockPatternView.setEnabled(false);
            }
            TextView textView = this.mHeaderTextView;
            textView.announceForAccessibility(textView.getText());
        }

        private int getDefaultHeader() {
            return this.mFrp ? C1715R.string.lockpassword_confirm_your_pattern_header_frp : C1715R.string.lockpassword_confirm_your_pattern_header;
        }

        private void postClearPatternRunnable() {
            this.mLockPatternView.removeCallbacks(this.mClearPatternRunnable);
            this.mLockPatternView.postDelayed(this.mClearPatternRunnable, 3000);
        }

        private void startDisappearAnimation(Intent intent) {
            if (!this.mDisappearing) {
                this.mDisappearing = true;
                ConfirmLockPattern confirmLockPattern = (ConfirmLockPattern) getActivity();
                if (confirmLockPattern != null && !confirmLockPattern.isFinishing()) {
                    if (confirmLockPattern.getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.DARK) {
                        this.mLockPatternView.clearPattern();
                        this.mDisappearAnimationUtils.startAnimation2d(getActiveViews(), new Runnable(intent) {
                            private final /* synthetic */ Intent f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                ConfirmLockPattern.ConfirmLockPatternFragment.lambda$startDisappearAnimation$0(ConfirmLockPattern.this, this.f$1);
                            }
                        }, this);
                        return;
                    }
                    confirmLockPattern.setResult(-1, intent);
                    confirmLockPattern.finish();
                }
            }
        }

        static /* synthetic */ void lambda$startDisappearAnimation$0(ConfirmLockPattern confirmLockPattern, Intent intent) {
            confirmLockPattern.setResult(-1, intent);
            confirmLockPattern.finish();
            confirmLockPattern.overridePendingTransition(C1715R.anim.confirm_credential_close_enter, C1715R.anim.confirm_credential_close_exit);
        }

        /* access modifiers changed from: private */
        public void onPatternChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            this.mLockPatternView.setEnabled(true);
            if (z) {
                if (z2) {
                    ConfirmDeviceCredentialUtils.reportSuccessfulAttempt(this.mLockPatternUtils, this.mUserManager, this.mEffectiveUserId);
                }
                this.mBiometricManager.onConfirmDeviceCredentialSuccess();
                startDisappearAnimation(intent);
                ConfirmDeviceCredentialUtils.checkForPendingIntent(getActivity());
                return;
            }
            if (i > 0) {
                refreshLockScreen();
                handleAttemptLockout(this.mLockPatternUtils.setLockoutAttemptDeadline(i2, i));
            } else {
                updateStage(Stage.NeedToUnlockWrong);
                postClearPatternRunnable();
            }
            if (z2) {
                reportFailedAttempt();
            }
        }

        public void onCredentialChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            onPatternChecked(z, intent, i, i2, z2);
        }

        /* access modifiers changed from: protected */
        public int getLastTryErrorMessage(int i) {
            if (i == 1) {
                return C1715R.string.lock_last_pattern_attempt_before_wipe_device;
            }
            if (i == 2) {
                return C1715R.string.lock_last_pattern_attempt_before_wipe_profile;
            }
            if (i == 3) {
                return C1715R.string.lock_last_pattern_attempt_before_wipe_user;
            }
            throw new IllegalArgumentException("Unrecognized user type:" + i);
        }

        private void handleAttemptLockout(long j) {
            updateStage(Stage.LockedOut);
            this.mCountdownTimer = new CountDownTimer(j - SystemClock.elapsedRealtime(), 1000) {
                public void onTick(long j) {
                    ConfirmLockPatternFragment confirmLockPatternFragment = ConfirmLockPatternFragment.this;
                    confirmLockPatternFragment.mErrorTextView.setText(confirmLockPatternFragment.getString(C1715R.string.lockpattern_too_many_failed_confirmation_attempts, Integer.valueOf((int) (j / 1000))));
                }

                public void onFinish() {
                    ConfirmLockPatternFragment.this.updateStage(Stage.NeedToUnlock);
                }
            }.start();
        }

        public void createAnimation(Object obj, long j, long j2, float f, boolean z, Interpolator interpolator, Runnable runnable) {
            Object obj2 = obj;
            if (obj2 instanceof LockPatternView.CellState) {
                this.mLockPatternView.startCellStateAnimation((LockPatternView.CellState) obj2, 1.0f, z ? 1.0f : 0.0f, z ? f : 0.0f, z ? 0.0f : f, z ? 0.0f : 1.0f, 1.0f, j, j2, interpolator, runnable);
                return;
            }
            this.mAppearAnimationUtils.createAnimation((View) obj2, j, j2, f, z, interpolator, runnable);
        }
    }

    /* renamed from: com.android.settings.password.ConfirmLockPattern$1 */
    static /* synthetic */ class C11271 {

        /* renamed from: $SwitchMap$com$android$settings$password$ConfirmLockPattern$Stage */
        static final /* synthetic */ int[] f60xec47707f = new int[Stage.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.android.settings.password.ConfirmLockPattern$Stage[] r0 = com.android.settings.password.ConfirmLockPattern.Stage.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f60xec47707f = r0
                int[] r0 = f60xec47707f     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.password.ConfirmLockPattern$Stage r1 = com.android.settings.password.ConfirmLockPattern.Stage.NeedToUnlock     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f60xec47707f     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.password.ConfirmLockPattern$Stage r1 = com.android.settings.password.ConfirmLockPattern.Stage.NeedToUnlockWrong     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f60xec47707f     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.password.ConfirmLockPattern$Stage r1 = com.android.settings.password.ConfirmLockPattern.Stage.LockedOut     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ConfirmLockPattern.C11271.<clinit>():void");
        }
    }
}
