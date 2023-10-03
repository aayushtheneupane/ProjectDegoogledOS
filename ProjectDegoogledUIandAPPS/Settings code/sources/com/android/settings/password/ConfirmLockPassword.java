package com.android.settings.password;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.TextViewInputDisabler;
import com.android.settings.password.ConfirmDeviceCredentialBaseActivity;
import com.android.settings.password.ConfirmLockPassword;
import com.android.settings.password.CredentialCheckResultTracker;
import com.android.settings.widget.ImeAwareEditText;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;

public class ConfirmLockPassword extends ConfirmDeviceCredentialBaseActivity {
    /* access modifiers changed from: private */
    public static final int[] DETAIL_TEXTS = {C1715R.string.lockpassword_confirm_your_pin_generic, C1715R.string.lockpassword_confirm_your_password_generic, C1715R.string.lockpassword_confirm_your_pin_generic_profile, C1715R.string.lockpassword_confirm_your_password_generic_profile, C1715R.string.lockpassword_strong_auth_required_device_pin, C1715R.string.lockpassword_strong_auth_required_device_password, C1715R.string.lockpassword_strong_auth_required_work_pin, C1715R.string.lockpassword_strong_auth_required_work_password};

    public static class InternalActivity extends ConfirmLockPassword {
    }

    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", ConfirmLockPasswordFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ConfirmLockPasswordFragment.class.getName().equals(str);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(C1715R.C1718id.main_content);
        if (findFragmentById != null && (findFragmentById instanceof ConfirmLockPasswordFragment)) {
            ((ConfirmLockPasswordFragment) findFragmentById).onWindowFocusChanged(z);
        }
    }

    public static class ConfirmLockPasswordFragment extends ConfirmDeviceCredentialBaseFragment implements View.OnClickListener, TextView.OnEditorActionListener, CredentialCheckResultTracker.Listener {
        private AppearAnimationUtils mAppearAnimationUtils;
        private CountDownTimer mCountdownTimer;
        /* access modifiers changed from: private */
        public CredentialCheckResultTracker mCredentialCheckResultTracker;
        private TextView mDetailsTextView;
        private DisappearAnimationUtils mDisappearAnimationUtils;
        private boolean mDisappearing = false;
        private TextView mHeaderTextView;
        private InputMethodManager mImm;
        /* access modifiers changed from: private */
        public boolean mIsAlpha;
        private ImeAwareEditText mPasswordEntry;
        private TextViewInputDisabler mPasswordEntryInputDisabler;
        /* access modifiers changed from: private */
        public AsyncTask<?, ?, ?> mPendingLockCheck;

        public int getMetricsCategory() {
            return 30;
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mEffectiveUserId);
            View inflate = layoutInflater.inflate(((ConfirmLockPassword) getActivity()).getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.NORMAL ? C1715R.layout.confirm_lock_password_normal : C1715R.layout.confirm_lock_password, viewGroup, false);
            this.mPasswordEntry = (ImeAwareEditText) inflate.findViewById(C1715R.C1718id.password_entry);
            this.mPasswordEntry.setOnEditorActionListener(this);
            this.mPasswordEntry.requestFocus();
            this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordEntry);
            this.mHeaderTextView = (TextView) inflate.findViewById(C1715R.C1718id.headerText);
            if (this.mHeaderTextView == null) {
                this.mHeaderTextView = (TextView) inflate.findViewById(C1715R.C1718id.suc_layout_title);
            }
            this.mDetailsTextView = (TextView) inflate.findViewById(C1715R.C1718id.sud_layout_description);
            this.mErrorTextView = (TextView) inflate.findViewById(C1715R.C1718id.errorText);
            this.mIsAlpha = 262144 == keyguardStoredPasswordQuality || 327680 == keyguardStoredPasswordQuality || 393216 == keyguardStoredPasswordQuality || 524288 == keyguardStoredPasswordQuality;
            this.mImm = (InputMethodManager) getActivity().getSystemService("input_method");
            Intent intent = getActivity().getIntent();
            if (intent != null) {
                CharSequence charSequenceExtra = intent.getCharSequenceExtra("com.android.settings.ConfirmCredentials.header");
                CharSequence charSequenceExtra2 = intent.getCharSequenceExtra("com.android.settings.ConfirmCredentials.details");
                if (TextUtils.isEmpty(charSequenceExtra)) {
                    charSequenceExtra = getString(getDefaultHeader());
                }
                if (TextUtils.isEmpty(charSequenceExtra2)) {
                    charSequenceExtra2 = getString(getDefaultDetails());
                }
                this.mHeaderTextView.setText(charSequenceExtra);
                this.mDetailsTextView.setText(charSequenceExtra2);
            }
            int inputType = this.mPasswordEntry.getInputType();
            ImeAwareEditText imeAwareEditText = this.mPasswordEntry;
            if (!this.mIsAlpha) {
                inputType = 18;
            }
            imeAwareEditText.setInputType(inputType);
            this.mPasswordEntry.setTypeface(Typeface.create(getContext().getString(17039781), 0));
            this.mAppearAnimationUtils = new AppearAnimationUtils(getContext(), 220, 2.0f, 1.0f, AnimationUtils.loadInterpolator(getContext(), 17563662));
            this.mDisappearAnimationUtils = new DisappearAnimationUtils(getContext(), 110, 1.0f, 0.5f, AnimationUtils.loadInterpolator(getContext(), 17563663));
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

        private int getDefaultHeader() {
            return this.mFrp ? this.mIsAlpha ? C1715R.string.lockpassword_confirm_your_password_header_frp : C1715R.string.lockpassword_confirm_your_pin_header_frp : this.mIsAlpha ? C1715R.string.lockpassword_confirm_your_password_header : C1715R.string.lockpassword_confirm_your_pin_header;
        }

        private int getDefaultDetails() {
            if (this.mFrp) {
                return this.mIsAlpha ? C1715R.string.lockpassword_confirm_your_password_details_frp : C1715R.string.lockpassword_confirm_your_pin_details_frp;
            }
            return ConfirmLockPassword.DETAIL_TEXTS[((isStrongAuthRequired() ? 1 : 0) << 2) + ((UserManager.get(getActivity()).isManagedProfile(this.mEffectiveUserId) ? 1 : 0) << true) + (this.mIsAlpha ? 1 : 0)];
        }

        private int getErrorMessage() {
            return this.mIsAlpha ? C1715R.string.lockpassword_invalid_password : C1715R.string.lockpassword_invalid_pin;
        }

        /* access modifiers changed from: protected */
        public int getLastTryErrorMessage(int i) {
            if (i == 1) {
                return this.mIsAlpha ? C1715R.string.lock_last_password_attempt_before_wipe_device : C1715R.string.lock_last_pin_attempt_before_wipe_device;
            }
            if (i == 2) {
                return this.mIsAlpha ? C1715R.string.lock_last_password_attempt_before_wipe_profile : C1715R.string.lock_last_pin_attempt_before_wipe_profile;
            }
            if (i == 3) {
                return this.mIsAlpha ? C1715R.string.lock_last_password_attempt_before_wipe_user : C1715R.string.lock_last_pin_attempt_before_wipe_user;
            }
            throw new IllegalArgumentException("Unrecognized user type:" + i);
        }

        public void prepareEnterAnimation() {
            super.prepareEnterAnimation();
            this.mHeaderTextView.setAlpha(0.0f);
            this.mDetailsTextView.setAlpha(0.0f);
            this.mCancelButton.setAlpha(0.0f);
            this.mPasswordEntry.setAlpha(0.0f);
            this.mErrorTextView.setAlpha(0.0f);
        }

        private View[] getActiveViews() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mHeaderTextView);
            arrayList.add(this.mDetailsTextView);
            if (this.mCancelButton.getVisibility() == 0) {
                arrayList.add(this.mCancelButton);
            }
            arrayList.add(this.mPasswordEntry);
            arrayList.add(this.mErrorTextView);
            return (View[]) arrayList.toArray(new View[0]);
        }

        public void startEnterAnimation() {
            super.startEnterAnimation();
            this.mAppearAnimationUtils.startAnimation(getActiveViews(), new Runnable() {
                public final void run() {
                    ConfirmLockPassword.ConfirmLockPasswordFragment.this.updatePasswordEntry();
                }
            });
        }

        public void onPause() {
            super.onPause();
            CountDownTimer countDownTimer = this.mCountdownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.mCountdownTimer = null;
            }
            this.mCredentialCheckResultTracker.setListener((CredentialCheckResultTracker.Listener) null);
        }

        public void onResume() {
            super.onResume();
            long lockoutAttemptDeadline = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mEffectiveUserId);
            if (lockoutAttemptDeadline != 0) {
                this.mCredentialCheckResultTracker.clearResult();
                handleAttemptLockout(lockoutAttemptDeadline);
            } else {
                updatePasswordEntry();
                this.mErrorTextView.setText("");
                updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId));
            }
            this.mCredentialCheckResultTracker.setListener(this);
        }

        /* access modifiers changed from: private */
        public void updatePasswordEntry() {
            boolean z = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mEffectiveUserId) != 0;
            this.mPasswordEntry.setEnabled(!z);
            this.mPasswordEntryInputDisabler.setInputEnabled(!z);
            if (z) {
                this.mImm.hideSoftInputFromWindow(this.mPasswordEntry.getWindowToken(), 0);
            } else {
                this.mPasswordEntry.scheduleShowSoftInput();
            }
        }

        public void onWindowFocusChanged(boolean z) {
            if (z) {
                this.mPasswordEntry.post(new Runnable() {
                    public final void run() {
                        ConfirmLockPassword.ConfirmLockPasswordFragment.this.updatePasswordEntry();
                    }
                });
            }
        }

        private void handleNext() {
            byte[] charSequenceToByteArray;
            if (this.mPendingLockCheck == null && !this.mDisappearing && (charSequenceToByteArray = LockPatternUtils.charSequenceToByteArray(this.mPasswordEntry.getText())) != null && charSequenceToByteArray.length != 0) {
                this.mPasswordEntryInputDisabler.setInputEnabled(false);
                boolean booleanExtra = getActivity().getIntent().getBooleanExtra("has_challenge", false);
                Intent intent = new Intent();
                if (!booleanExtra) {
                    startCheckPassword(charSequenceToByteArray, intent);
                } else if (isInternalActivity()) {
                    startVerifyPassword(charSequenceToByteArray, intent);
                } else {
                    this.mCredentialCheckResultTracker.setResult(false, intent, 0, this.mEffectiveUserId);
                }
            }
        }

        /* access modifiers changed from: private */
        public boolean isInternalActivity() {
            return getActivity() instanceof InternalActivity;
        }

        private void startVerifyPassword(byte[] bArr, final Intent intent) {
            AsyncTask<?, ?, ?> asyncTask;
            long longExtra = getActivity().getIntent().getLongExtra("challenge", 0);
            final int i = this.mEffectiveUserId;
            int i2 = this.mUserId;
            C11241 r10 = new LockPatternChecker.OnVerifyCallback() {
                public void onVerified(byte[] bArr, int i) {
                    boolean z;
                    AsyncTask unused = ConfirmLockPasswordFragment.this.mPendingLockCheck = null;
                    if (bArr != null) {
                        z = true;
                        if (ConfirmLockPasswordFragment.this.mReturnCredentials) {
                            intent.putExtra("hw_auth_token", bArr);
                        }
                    } else {
                        z = false;
                    }
                    ConfirmLockPasswordFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i, i);
                }
            };
            if (i == i2) {
                asyncTask = LockPatternChecker.verifyPassword(this.mLockPatternUtils, bArr, longExtra, i2, r10);
            } else {
                asyncTask = LockPatternChecker.verifyTiedProfileChallenge(this.mLockPatternUtils, bArr, false, longExtra, i2, r10);
            }
            this.mPendingLockCheck = asyncTask;
        }

        private void startCheckPassword(final byte[] bArr, final Intent intent) {
            final int i = this.mEffectiveUserId;
            this.mPendingLockCheck = LockPatternChecker.checkPassword(this.mLockPatternUtils, bArr, i, new LockPatternChecker.OnCheckCallback() {
                public void onChecked(boolean z, int i) {
                    AsyncTask unused = ConfirmLockPasswordFragment.this.mPendingLockCheck = null;
                    if (z && ConfirmLockPasswordFragment.this.isInternalActivity()) {
                        ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                        if (confirmLockPasswordFragment.mReturnCredentials) {
                            intent.putExtra("type", confirmLockPasswordFragment.mIsAlpha ? 0 : 3);
                            intent.putExtra("password", bArr);
                        }
                    }
                    ConfirmLockPasswordFragment.this.mCredentialCheckResultTracker.setResult(z, intent, i, i);
                }
            });
        }

        private void startDisappearAnimation(Intent intent) {
            if (!this.mDisappearing) {
                this.mDisappearing = true;
                ConfirmLockPassword confirmLockPassword = (ConfirmLockPassword) getActivity();
                if (confirmLockPassword != null && !confirmLockPassword.isFinishing()) {
                    if (confirmLockPassword.getConfirmCredentialTheme() == ConfirmDeviceCredentialBaseActivity.ConfirmCredentialTheme.DARK) {
                        this.mDisappearAnimationUtils.startAnimation(getActiveViews(), new Runnable(intent) {
                            private final /* synthetic */ Intent f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                ConfirmLockPassword.ConfirmLockPasswordFragment.lambda$startDisappearAnimation$0(ConfirmLockPassword.this, this.f$1);
                            }
                        });
                        return;
                    }
                    confirmLockPassword.setResult(-1, intent);
                    confirmLockPassword.finish();
                }
            }
        }

        static /* synthetic */ void lambda$startDisappearAnimation$0(ConfirmLockPassword confirmLockPassword, Intent intent) {
            confirmLockPassword.setResult(-1, intent);
            confirmLockPassword.finish();
            confirmLockPassword.overridePendingTransition(C1715R.anim.confirm_credential_close_enter, C1715R.anim.confirm_credential_close_exit);
        }

        private void onPasswordChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            this.mPasswordEntryInputDisabler.setInputEnabled(true);
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
                showError(getErrorMessage(), 3000);
            }
            if (z2) {
                reportFailedAttempt();
            }
        }

        public void onCredentialChecked(boolean z, Intent intent, int i, int i2, boolean z2) {
            onPasswordChecked(z, intent, i, i2, z2);
        }

        /* access modifiers changed from: protected */
        public void onShowError() {
            this.mPasswordEntry.setText((CharSequence) null);
        }

        private void handleAttemptLockout(long j) {
            this.mCountdownTimer = new CountDownTimer(j - SystemClock.elapsedRealtime(), 1000) {
                public void onTick(long j) {
                    ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                    confirmLockPasswordFragment.showError((CharSequence) confirmLockPasswordFragment.getString(C1715R.string.lockpattern_too_many_failed_confirmation_attempts, Integer.valueOf((int) (j / 1000))), 0);
                }

                public void onFinish() {
                    ConfirmLockPasswordFragment.this.updatePasswordEntry();
                    ConfirmLockPasswordFragment.this.mErrorTextView.setText("");
                    ConfirmLockPasswordFragment confirmLockPasswordFragment = ConfirmLockPasswordFragment.this;
                    confirmLockPasswordFragment.updateErrorMessage(confirmLockPasswordFragment.mLockPatternUtils.getCurrentFailedPasswordAttempts(confirmLockPasswordFragment.mEffectiveUserId));
                }
            }.start();
            updatePasswordEntry();
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == C1715R.C1718id.cancel_button) {
                getActivity().setResult(0);
                getActivity().finish();
            } else if (id == C1715R.C1718id.next_button) {
                handleNext();
            }
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 0 && i != 6 && i != 5) {
                return false;
            }
            handleNext();
            return true;
        }
    }
}
