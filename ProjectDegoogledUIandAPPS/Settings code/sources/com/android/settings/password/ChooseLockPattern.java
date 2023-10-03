package com.android.settings.password;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.settings.SettingsActivity;
import com.android.settings.SetupWizardUtils;
import com.android.settings.Utils;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.notification.RedactionInterstitial;
import com.android.settings.password.ChooseLockPattern;
import com.android.settings.password.SaveChosenLockWorkerBase;
import com.google.android.collect.Lists;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChooseLockPattern extends SettingsActivity {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", getFragmentClass().getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    public static class IntentBuilder {
        private final Intent mIntent;

        public IntentBuilder(Context context) {
            this.mIntent = new Intent(context, ChooseLockPatternSize.class);
            this.mIntent.putExtra("extra_require_password", false);
            this.mIntent.putExtra("confirm_credentials", false);
            this.mIntent.putExtra("has_challenge", false);
        }

        public IntentBuilder setUserId(int i) {
            this.mIntent.putExtra("android.intent.extra.USER_ID", i);
            return this;
        }

        public IntentBuilder setChallenge(long j) {
            this.mIntent.putExtra("has_challenge", true);
            this.mIntent.putExtra("challenge", j);
            return this;
        }

        public IntentBuilder setPattern(byte[] bArr) {
            this.mIntent.putExtra("password", bArr);
            return this;
        }

        public IntentBuilder setForFingerprint(boolean z) {
            this.mIntent.putExtra("for_fingerprint", z);
            return this;
        }

        public IntentBuilder setForFace(boolean z) {
            this.mIntent.putExtra("for_face", z);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ChooseLockPatternFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends Fragment> getFragmentClass() {
        return ChooseLockPatternFragment.class;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getIntent().getBooleanExtra("for_fingerprint", false) ? C1715R.string.lockpassword_choose_your_pattern_header_for_fingerprint : getIntent().getBooleanExtra("for_face", false) ? C1715R.string.lockpassword_choose_your_pattern_header_for_face : C1715R.string.lockpassword_choose_your_screen_lock_header);
        findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public static class ChooseLockPatternFragment extends InstrumentedFragment implements SaveChosenLockWorkerBase.Listener {
        private List<LockPatternView.Cell> mAnimatePattern;
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;
        protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {
            public void onPatternCellAdded(List<LockPatternView.Cell> list) {
            }

            public void onPatternStart() {
                ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                chooseLockPatternFragment.mLockPatternView.removeCallbacks(chooseLockPatternFragment.mClearPatternRunnable);
                patternInProgress();
            }

            public void onPatternCleared() {
                ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                chooseLockPatternFragment.mLockPatternView.removeCallbacks(chooseLockPatternFragment.mClearPatternRunnable);
            }

            public void onPatternDetected(List<LockPatternView.Cell> list) {
                if (ChooseLockPatternFragment.this.mUiStage == Stage.NeedToConfirm || ChooseLockPatternFragment.this.mUiStage == Stage.ConfirmWrong) {
                    ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                    List<LockPatternView.Cell> list2 = chooseLockPatternFragment.mChosenPattern;
                    if (list2 == null) {
                        throw new IllegalStateException("null chosen pattern in stage 'need to confirm");
                    } else if (LockPatternUtils.patternToString(list2, chooseLockPatternFragment.mPatternSize).equals(LockPatternUtils.patternToString(list, ChooseLockPatternFragment.this.mPatternSize))) {
                        ChooseLockPatternFragment.this.updateStage(Stage.ChoiceConfirmed);
                    } else {
                        ChooseLockPatternFragment.this.updateStage(Stage.ConfirmWrong);
                    }
                } else if (ChooseLockPatternFragment.this.mUiStage != Stage.Introduction && ChooseLockPatternFragment.this.mUiStage != Stage.ChoiceTooShort) {
                    throw new IllegalStateException("Unexpected stage " + ChooseLockPatternFragment.this.mUiStage + " when entering the pattern.");
                } else if (list.size() < 4) {
                    ChooseLockPatternFragment.this.updateStage(Stage.ChoiceTooShort);
                } else {
                    ChooseLockPatternFragment.this.mChosenPattern = new ArrayList(list);
                    ChooseLockPatternFragment.this.updateStage(Stage.FirstChoiceValid);
                }
            }

            private void patternInProgress() {
                ChooseLockPatternFragment.this.mHeaderText.setText(C1715R.string.lockpattern_recording_inprogress);
                if (ChooseLockPatternFragment.this.mDefaultHeaderColorList != null) {
                    ChooseLockPatternFragment chooseLockPatternFragment = ChooseLockPatternFragment.this;
                    chooseLockPatternFragment.mHeaderText.setTextColor(chooseLockPatternFragment.mDefaultHeaderColorList);
                }
                ChooseLockPatternFragment.this.mFooterText.setText("");
                ChooseLockPatternFragment.this.mNextButton.setEnabled(false);
                if (ChooseLockPatternFragment.this.mTitleHeaderScrollView != null) {
                    ChooseLockPatternFragment.this.mTitleHeaderScrollView.post(new Runnable() {
                        public void run() {
                            ChooseLockPatternFragment.this.mTitleHeaderScrollView.fullScroll(130);
                        }
                    });
                }
            }
        };
        protected List<LockPatternView.Cell> mChosenPattern = null;
        /* access modifiers changed from: private */
        public Runnable mClearPatternRunnable = new Runnable() {
            public void run() {
                ChooseLockPatternFragment.this.mLockPatternView.clearPattern();
            }
        };
        private byte[] mCurrentPattern;
        /* access modifiers changed from: private */
        public ColorStateList mDefaultHeaderColorList;
        protected TextView mFooterText;
        protected boolean mForFace;
        protected boolean mForFingerprint;
        private boolean mHasChallenge;
        protected TextView mHeaderText;
        private LockPatternUtils mLockPatternUtils;
        protected LockPatternView mLockPatternView;
        protected TextView mMessageText;
        /* access modifiers changed from: private */
        public FooterButton mNextButton;
        /* access modifiers changed from: private */
        public byte mPatternSize = 3;
        private SaveAndFinishWorker mSaveAndFinishWorker;
        protected FooterButton mSkipOrClearButton;
        /* access modifiers changed from: private */
        public ScrollView mTitleHeaderScrollView;
        protected TextView mTitleText;
        /* access modifiers changed from: private */
        public Stage mUiStage = Stage.Introduction;
        protected int mUserId;

        public int getMetricsCategory() {
            return 29;
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i == 55) {
                if (i2 != -1) {
                    getActivity().setResult(1);
                    getActivity().finish();
                } else {
                    this.mCurrentPattern = intent.getByteArrayExtra("password");
                }
                updateStage(Stage.Introduction);
            }
        }

        /* access modifiers changed from: protected */
        public void setRightButtonEnabled(boolean z) {
            this.mNextButton.setEnabled(z);
        }

        /* access modifiers changed from: protected */
        public void setRightButtonText(int i) {
            this.mNextButton.setText(getActivity(), i);
        }

        enum LeftButtonMode {
            Retry(C1715R.string.lockpattern_retry_button_text, true),
            RetryDisabled(C1715R.string.lockpattern_retry_button_text, false),
            Gone(-1, false);
            
            final boolean enabled;
            final int text;

            private LeftButtonMode(int i, boolean z) {
                this.text = i;
                this.enabled = z;
            }
        }

        enum RightButtonMode {
            Continue(C1715R.string.next_label, true),
            ContinueDisabled(C1715R.string.next_label, false),
            Confirm(C1715R.string.lockpattern_confirm_button_text, true),
            ConfirmDisabled(C1715R.string.lockpattern_confirm_button_text, false),
            Ok(17039370, true);
            
            final boolean enabled;
            final int text;

            private RightButtonMode(int i, boolean z) {
                this.text = i;
                this.enabled = z;
            }
        }

        protected enum Stage {
            Introduction(C1715R.string.lock_settings_picker_biometrics_added_security_message, C1715R.string.lockpassword_choose_your_pattern_message, C1715R.string.lockpattern_recording_intro_header, LeftButtonMode.Gone, RightButtonMode.ContinueDisabled, -1, true),
            HelpScreen(-1, -1, C1715R.string.lockpattern_settings_help_how_to_record, LeftButtonMode.Gone, RightButtonMode.Ok, -1, false),
            ChoiceTooShort(C1715R.string.lock_settings_picker_biometrics_added_security_message, C1715R.string.lockpassword_choose_your_pattern_message, C1715R.string.lockpattern_recording_incorrect_too_short, LeftButtonMode.Retry, RightButtonMode.ContinueDisabled, -1, true),
            FirstChoiceValid(C1715R.string.lock_settings_picker_biometrics_added_security_message, C1715R.string.lockpassword_choose_your_pattern_message, C1715R.string.lockpattern_pattern_entered_header, LeftButtonMode.Retry, RightButtonMode.Continue, -1, false),
            NeedToConfirm(-1, -1, C1715R.string.lockpattern_need_to_confirm, LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled, -1, true),
            ConfirmWrong(-1, -1, C1715R.string.lockpattern_need_to_unlock_wrong, LeftButtonMode.Gone, RightButtonMode.ConfirmDisabled, -1, true),
            ChoiceConfirmed(-1, -1, C1715R.string.lockpattern_pattern_confirmed_header, LeftButtonMode.Gone, RightButtonMode.Confirm, -1, false);
            
            final int footerMessage;
            final int headerMessage;
            final LeftButtonMode leftMode;
            final int message;
            final int messageForBiometrics;
            final boolean patternEnabled;
            final RightButtonMode rightMode;

            private Stage(int i, int i2, int i3, LeftButtonMode leftButtonMode, RightButtonMode rightButtonMode, int i4, boolean z) {
                this.headerMessage = i3;
                this.messageForBiometrics = i;
                this.message = i2;
                this.leftMode = leftButtonMode;
                this.rightMode = rightButtonMode;
                this.footerMessage = i4;
                this.patternEnabled = z;
            }
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
            if (getActivity() instanceof ChooseLockPattern) {
                Intent intent = getActivity().getIntent();
                this.mUserId = Utils.getUserIdFromBundle(getActivity(), intent.getExtras());
                this.mLockPatternUtils = new LockPatternUtils(getActivity());
                if (intent.getBooleanExtra("for_cred_req_boot", false)) {
                    SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
                    boolean booleanExtra = getActivity().getIntent().getBooleanExtra("extra_require_password", true);
                    byte[] byteArrayExtra = intent.getByteArrayExtra("password");
                    saveAndFinishWorker.setBlocking(true);
                    saveAndFinishWorker.setListener(this);
                    saveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), booleanExtra, false, 0, LockPatternUtils.byteArrayToPattern(byteArrayExtra, this.mPatternSize), byteArrayExtra, this.mUserId, this.mPatternSize);
                }
                this.mForFingerprint = intent.getBooleanExtra("for_fingerprint", false);
                this.mForFace = intent.getBooleanExtra("for_face", false);
                return;
            }
            throw new SecurityException("Fragment contained in wrong activity");
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            GlifLayout glifLayout = (GlifLayout) layoutInflater.inflate(C1715R.layout.choose_lock_pattern, viewGroup, false);
            glifLayout.setHeaderText(getActivity().getTitle());
            if (getResources().getBoolean(C1715R.bool.config_lock_pattern_minimal_ui)) {
                View findViewById = glifLayout.findViewById(C1715R.C1718id.sud_layout_icon);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
            } else if (this.mForFingerprint) {
                glifLayout.setIcon(getActivity().getDrawable(C1715R.C1717drawable.ic_fingerprint_header));
            } else if (this.mForFace) {
                glifLayout.setIcon(getActivity().getDrawable(C1715R.C1717drawable.ic_face_header));
            }
            FooterBarMixin footerBarMixin = (FooterBarMixin) glifLayout.getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getActivity());
            builder.setText(C1715R.string.lockpattern_tutorial_cancel_label);
            builder.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ChooseLockPattern.ChooseLockPatternFragment.this.onSkipOrClearButtonClick(view);
                }
            });
            builder.setButtonType(0);
            builder.setTheme(2131952051);
            footerBarMixin.setSecondaryButton(builder.build());
            FooterButton.Builder builder2 = new FooterButton.Builder(getActivity());
            builder2.setText(C1715R.string.lockpattern_tutorial_continue_label);
            builder2.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ChooseLockPattern.ChooseLockPatternFragment.this.onNextButtonClick(view);
                }
            });
            builder2.setButtonType(5);
            builder2.setTheme(2131952050);
            footerBarMixin.setPrimaryButton(builder2.build());
            this.mSkipOrClearButton = footerBarMixin.getSecondaryButton();
            this.mNextButton = footerBarMixin.getPrimaryButton();
            this.mPatternSize = getActivity().getIntent().getByteExtra("pattern_size", (byte) 3);
            LockPatternView.Cell.updateSize(this.mPatternSize);
            this.mAnimatePattern = Collections.unmodifiableList(Lists.newArrayList(new LockPatternView.Cell[]{LockPatternView.Cell.of(0, 0, this.mPatternSize), LockPatternView.Cell.of(0, 1, this.mPatternSize), LockPatternView.Cell.of(1, 1, this.mPatternSize), LockPatternView.Cell.of(2, 1, this.mPatternSize)}));
            return glifLayout;
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mTitleText = (TextView) view.findViewById(C1715R.C1718id.suc_layout_title);
            this.mHeaderText = (TextView) view.findViewById(C1715R.C1718id.headerText);
            this.mDefaultHeaderColorList = this.mHeaderText.getTextColors();
            this.mMessageText = (TextView) view.findViewById(C1715R.C1718id.sud_layout_description);
            this.mLockPatternView = view.findViewById(C1715R.C1718id.lockPattern);
            this.mLockPatternView.setOnPatternListener(this.mChooseNewLockPatternListener);
            this.mLockPatternView.setTactileFeedbackEnabled(this.mChooseLockSettingsHelper.utils().isTactileFeedbackEnabled());
            this.mLockPatternView.setFadePattern(false);
            this.mLockPatternView.setLockPatternUtils(this.mChooseLockSettingsHelper.utils());
            this.mLockPatternView.setLockPatternSize(this.mPatternSize);
            this.mFooterText = (TextView) view.findViewById(C1715R.C1718id.footerText);
            this.mTitleHeaderScrollView = (ScrollView) view.findViewById(C1715R.C1718id.scroll_layout_title_header);
            view.findViewById(C1715R.C1718id.topLayout).setDefaultTouchRecepient(this.mLockPatternView);
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("confirm_credentials", true);
            Intent intent = getActivity().getIntent();
            this.mCurrentPattern = intent.getByteArrayExtra("password");
            this.mHasChallenge = intent.getBooleanExtra("has_challenge", false);
            this.mChallenge = intent.getLongExtra("challenge", 0);
            if (bundle != null) {
                byte[] byteArray = bundle.getByteArray("chosenPattern");
                if (byteArray != null) {
                    this.mChosenPattern = LockPatternUtils.byteArrayToPattern(byteArray, this.mPatternSize);
                    this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Correct, this.mChosenPattern);
                }
                if (this.mCurrentPattern == null) {
                    this.mCurrentPattern = bundle.getByteArray("currentPattern");
                }
                updateStage(Stage.values()[bundle.getInt("uiStage")]);
                this.mSaveAndFinishWorker = (SaveAndFinishWorker) getFragmentManager().findFragmentByTag("save_and_finish_worker");
            } else if (booleanExtra) {
                updateStage(Stage.NeedToConfirm);
                if (!this.mChooseLockSettingsHelper.launchConfirmationActivity(55, getString(C1715R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId)) {
                    updateStage(Stage.Introduction);
                }
            } else {
                updateStage(Stage.Introduction);
            }
        }

        public void onResume() {
            super.onResume();
            updateStage(this.mUiStage);
            if (this.mSaveAndFinishWorker != null) {
                setRightButtonEnabled(false);
                this.mSaveAndFinishWorker.setListener(this);
            }
        }

        public void onPause() {
            super.onPause();
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener((SaveChosenLockWorkerBase.Listener) null);
            }
        }

        /* access modifiers changed from: protected */
        public Intent getRedactionInterstitialIntent(Context context) {
            return RedactionInterstitial.createStartIntent(context, this.mUserId);
        }

        public void handleLeftButton() {
            if (this.mUiStage.leftMode == LeftButtonMode.Retry) {
                this.mChosenPattern = null;
                this.mLockPatternView.clearPattern();
                updateStage(Stage.Introduction);
                return;
            }
            throw new IllegalStateException("left footer button pressed, but stage of " + this.mUiStage + " doesn't make sense");
        }

        public void handleRightButton() {
            Stage stage = this.mUiStage;
            RightButtonMode rightButtonMode = stage.rightMode;
            if (rightButtonMode == RightButtonMode.Continue) {
                if (stage == Stage.FirstChoiceValid) {
                    updateStage(Stage.NeedToConfirm);
                    return;
                }
                throw new IllegalStateException("expected ui stage " + Stage.FirstChoiceValid + " when button is " + RightButtonMode.Continue);
            } else if (rightButtonMode == RightButtonMode.Confirm) {
                if (stage == Stage.ChoiceConfirmed) {
                    startSaveAndFinish();
                    Utils.savePINPasswordLength(this.mLockPatternUtils, 0, this.mUserId);
                    return;
                }
                throw new IllegalStateException("expected ui stage " + Stage.ChoiceConfirmed + " when button is " + RightButtonMode.Confirm);
            } else if (rightButtonMode != RightButtonMode.Ok) {
            } else {
                if (stage == Stage.HelpScreen) {
                    this.mLockPatternView.clearPattern();
                    this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
                    updateStage(Stage.Introduction);
                    return;
                }
                throw new IllegalStateException("Help screen is only mode with ok button, but stage is " + this.mUiStage);
            }
        }

        /* access modifiers changed from: protected */
        public void onSkipOrClearButtonClick(View view) {
            handleLeftButton();
        }

        /* access modifiers changed from: protected */
        public void onNextButtonClick(View view) {
            handleRightButton();
        }

        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putInt("uiStage", this.mUiStage.ordinal());
            List<LockPatternView.Cell> list = this.mChosenPattern;
            if (list != null) {
                bundle.putByteArray("chosenPattern", LockPatternUtils.patternToByteArray(list, this.mPatternSize));
            }
            byte[] bArr = this.mCurrentPattern;
            if (bArr != null) {
                bundle.putByteArray("currentPattern", bArr);
            }
        }

        /* access modifiers changed from: protected */
        public void updateStage(Stage stage) {
            Stage stage2 = this.mUiStage;
            this.mUiStage = stage;
            boolean z = true;
            if (stage == Stage.ChoiceTooShort) {
                this.mHeaderText.setText(getResources().getString(stage.headerMessage, new Object[]{4}));
            } else {
                this.mHeaderText.setText(stage.headerMessage);
            }
            boolean z2 = this.mForFingerprint || this.mForFace;
            int i = z2 ? stage.messageForBiometrics : stage.message;
            if (i == -1) {
                this.mMessageText.setText("");
            } else {
                this.mMessageText.setText(i);
            }
            int i2 = stage.footerMessage;
            if (i2 == -1) {
                this.mFooterText.setText("");
            } else {
                this.mFooterText.setText(i2);
            }
            if (stage == Stage.ConfirmWrong || stage == Stage.ChoiceTooShort) {
                TypedValue typedValue = new TypedValue();
                getActivity().getTheme().resolveAttribute(C1715R.attr.colorError, typedValue, true);
                this.mHeaderText.setTextColor(typedValue.data);
            } else {
                ColorStateList colorStateList = this.mDefaultHeaderColorList;
                if (colorStateList != null) {
                    this.mHeaderText.setTextColor(colorStateList);
                }
                if (stage == Stage.NeedToConfirm && z2) {
                    this.mHeaderText.setText("");
                    this.mTitleText.setText(C1715R.string.lockpassword_draw_your_pattern_again_header);
                }
            }
            updateFooterLeftButton(stage);
            setRightButtonText(stage.rightMode.text);
            setRightButtonEnabled(stage.rightMode.enabled);
            if (stage.patternEnabled) {
                this.mLockPatternView.enableInput();
            } else {
                this.mLockPatternView.disableInput();
            }
            this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
            switch (C11151.f58x4f217302[this.mUiStage.ordinal()]) {
                case 1:
                    this.mLockPatternView.clearPattern();
                    break;
                case 2:
                    this.mLockPatternView.setPattern(LockPatternView.DisplayMode.Animate, this.mAnimatePattern);
                    break;
                case 3:
                    this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                    postClearPatternRunnable();
                    break;
                case 5:
                    this.mLockPatternView.clearPattern();
                    break;
                case 6:
                    this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                    postClearPatternRunnable();
                    break;
            }
            z = false;
            if (stage2 != stage || z) {
                TextView textView = this.mHeaderText;
                textView.announceForAccessibility(textView.getText());
            }
        }

        /* access modifiers changed from: protected */
        public void updateFooterLeftButton(Stage stage) {
            if (stage.leftMode == LeftButtonMode.Gone) {
                this.mSkipOrClearButton.setVisibility(8);
                return;
            }
            this.mSkipOrClearButton.setVisibility(0);
            this.mSkipOrClearButton.setText(getActivity(), stage.leftMode.text);
            this.mSkipOrClearButton.setEnabled(stage.leftMode.enabled);
        }

        private void postClearPatternRunnable() {
            this.mLockPatternView.removeCallbacks(this.mClearPatternRunnable);
            this.mLockPatternView.postDelayed(this.mClearPatternRunnable, 2000);
        }

        private void startSaveAndFinish() {
            if (this.mSaveAndFinishWorker != null) {
                Log.w("ChooseLockPattern", "startSaveAndFinish with an existing SaveAndFinishWorker.");
                return;
            }
            setRightButtonEnabled(false);
            this.mSaveAndFinishWorker = new SaveAndFinishWorker();
            this.mSaveAndFinishWorker.setListener(this);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) this.mSaveAndFinishWorker, "save_and_finish_worker");
            beginTransaction.commit();
            getFragmentManager().executePendingTransactions();
            this.mSaveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), getActivity().getIntent().getBooleanExtra("extra_require_password", true), this.mHasChallenge, this.mChallenge, this.mChosenPattern, this.mCurrentPattern, this.mUserId, this.mPatternSize);
        }

        public void onChosenLockSaveFinished(boolean z, Intent intent) {
            Intent redactionInterstitialIntent;
            getActivity().setResult(1, intent);
            byte[] bArr = this.mCurrentPattern;
            if (bArr != null) {
                Arrays.fill(bArr, (byte) 0);
            }
            if (!z && (redactionInterstitialIntent = getRedactionInterstitialIntent(getActivity())) != null) {
                startActivity(redactionInterstitialIntent);
            }
            getActivity().finish();
        }
    }

    /* renamed from: com.android.settings.password.ChooseLockPattern$1 */
    static /* synthetic */ class C11151 {

        /* renamed from: $SwitchMap$com$android$settings$password$ChooseLockPattern$ChooseLockPatternFragment$Stage */
        static final /* synthetic */ int[] f58x4f217302 = new int[ChooseLockPatternFragment.Stage.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage[] r0 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f58x4f217302 = r0
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.Introduction     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.HelpScreen     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.ChoiceTooShort     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.FirstChoiceValid     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.NeedToConfirm     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.ConfirmWrong     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f58x4f217302     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.android.settings.password.ChooseLockPattern$ChooseLockPatternFragment$Stage r1 = com.android.settings.password.ChooseLockPattern.ChooseLockPatternFragment.Stage.ChoiceConfirmed     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.password.ChooseLockPattern.C11151.<clinit>():void");
        }
    }

    public static class SaveAndFinishWorker extends SaveChosenLockWorkerBase {
        private List<LockPatternView.Cell> mChosenPattern;
        private byte[] mCurrentPattern;
        private boolean mLockVirgin;
        private byte mPatternSize;

        public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        public /* bridge */ /* synthetic */ void setBlocking(boolean z) {
            super.setBlocking(z);
        }

        public /* bridge */ /* synthetic */ void setListener(SaveChosenLockWorkerBase.Listener listener) {
            super.setListener(listener);
        }

        public void start(LockPatternUtils lockPatternUtils, boolean z, boolean z2, long j, List<LockPatternView.Cell> list, byte[] bArr, int i, byte b) {
            prepare(lockPatternUtils, z, z2, j, i);
            this.mCurrentPattern = bArr;
            this.mChosenPattern = list;
            this.mUserId = i;
            this.mPatternSize = b;
            this.mLockVirgin = !this.mUtils.isPatternEverChosen(this.mUserId);
            start();
        }

        /* access modifiers changed from: protected */
        public Pair<Boolean, Intent> saveAndVerifyInBackground() {
            Intent intent;
            int i = this.mUserId;
            this.mUtils.setLockPatternSize((long) this.mPatternSize, i);
            boolean saveLockPattern = this.mUtils.saveLockPattern(this.mChosenPattern, this.mCurrentPattern, i);
            byte[] bArr = null;
            if (!saveLockPattern || !this.mHasChallenge) {
                intent = null;
            } else {
                try {
                    bArr = this.mUtils.verifyPattern(this.mChosenPattern, this.mChallenge, i);
                } catch (LockPatternUtils.RequestThrottledException unused) {
                }
                if (bArr == null) {
                    Log.e("ChooseLockPattern", "critical: no token returned for known good pattern");
                }
                intent = new Intent();
                intent.putExtra("hw_auth_token", bArr);
            }
            return Pair.create(Boolean.valueOf(saveLockPattern), intent);
        }

        /* access modifiers changed from: protected */
        public void finish(Intent intent) {
            if (this.mLockVirgin) {
                this.mUtils.setVisiblePatternEnabled(true, this.mUserId);
            }
            super.finish(intent);
        }
    }
}
