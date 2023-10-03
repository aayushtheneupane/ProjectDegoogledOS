package com.android.settings.password;

import android.app.admin.PasswordMetrics;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.TextViewInputDisabler;
import com.android.settings.SettingsActivity;
import com.android.settings.SetupWizardUtils;
import com.android.settings.Utils;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.notification.RedactionInterstitial;
import com.android.settings.password.ChooseLockPassword;
import com.android.settings.password.SaveChosenLockWorkerBase;
import com.android.settings.widget.ImeAwareEditText;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseLockPassword extends SettingsActivity {
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
            this.mIntent = new Intent(context, ChooseLockPassword.class);
            this.mIntent.putExtra("confirm_credentials", false);
            this.mIntent.putExtra("extra_require_password", false);
            this.mIntent.putExtra("has_challenge", false);
        }

        public IntentBuilder setPasswordQuality(int i) {
            this.mIntent.putExtra("lockscreen.password_type", i);
            return this;
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

        public IntentBuilder setPassword(byte[] bArr) {
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

        public IntentBuilder setRequestedMinComplexity(int i) {
            this.mIntent.putExtra("requested_min_complexity", i);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ChooseLockPasswordFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends Fragment> getFragmentClass() {
        return ChooseLockPasswordFragment.class;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean booleanExtra = getIntent().getBooleanExtra("for_fingerprint", false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("for_face", false);
        CharSequence text = getText(C1715R.string.lockpassword_choose_your_screen_lock_header);
        if (booleanExtra) {
            text = getText(C1715R.string.lockpassword_choose_your_password_header_for_fingerprint);
        } else if (booleanExtra2) {
            text = getText(C1715R.string.lockpassword_choose_your_password_header_for_face);
        }
        setTitle(text);
        findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
    }

    public static class ChooseLockPasswordFragment extends InstrumentedFragment implements TextView.OnEditorActionListener, TextWatcher, SaveChosenLockWorkerBase.Listener {
        private long mChallenge;
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;
        private byte[] mChosenPassword;
        private byte[] mCurrentPassword;
        private byte[] mFirstPin;
        protected boolean mForFace;
        protected boolean mForFingerprint;
        private boolean mHasChallenge;
        protected boolean mIsAlphaMode;
        private GlifLayout mLayout;
        private LockPatternUtils mLockPatternUtils;
        private TextView mMessage;
        private FooterButton mNextButton;
        private ImeAwareEditText mPasswordEntry;
        private TextViewInputDisabler mPasswordEntryInputDisabler;
        private byte[] mPasswordHistoryHashFactor;
        private int mPasswordMaxLength = 16;
        private int mPasswordMinLength = 4;
        private int mPasswordMinLengthToFulfillAllPolicies = 0;
        private int mPasswordMinLetters = 0;
        private int mPasswordMinLowerCase = 0;
        private int mPasswordMinNonLetter = 0;
        private int mPasswordMinNumeric = 0;
        private int mPasswordMinSymbols = 0;
        private int mPasswordMinUpperCase = 0;
        private boolean mPasswordNumSequenceAllowed = true;
        private PasswordRequirementAdapter mPasswordRequirementAdapter;
        private RecyclerView mPasswordRestrictionView;
        private int mRequestedMinComplexity = 0;
        private int mRequestedQuality = 131072;
        private SaveAndFinishWorker mSaveAndFinishWorker;
        protected FooterButton mSkipOrClearButton;
        private TextChangedHandler mTextChangedHandler;
        protected Stage mUiStage = Stage.Introduction;
        protected int mUserId;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public int getMetricsCategory() {
            return 28;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        /* access modifiers changed from: protected */
        public int toVisibility(boolean z) {
            return z ? 0 : 8;
        }

        protected enum Stage {
            Introduction(C1715R.string.lockpassword_choose_your_screen_lock_header, C1715R.string.lockpassword_choose_your_password_header_for_fingerprint, C1715R.string.lockpassword_choose_your_password_header_for_face, C1715R.string.lockpassword_choose_your_screen_lock_header, C1715R.string.lockpassword_choose_your_pin_header_for_fingerprint, C1715R.string.lockpassword_choose_your_pin_header_for_face, C1715R.string.lockpassword_choose_your_password_message, C1715R.string.lock_settings_picker_biometrics_added_security_message, C1715R.string.lockpassword_choose_your_pin_message, C1715R.string.lock_settings_picker_biometrics_added_security_message, C1715R.string.next_label),
            NeedToConfirm(C1715R.string.lockpassword_confirm_your_password_header, C1715R.string.lockpassword_confirm_your_password_header, C1715R.string.lockpassword_confirm_your_password_header, C1715R.string.lockpassword_confirm_your_pin_header, C1715R.string.lockpassword_confirm_your_pin_header, C1715R.string.lockpassword_confirm_your_pin_header, 0, 0, 0, 0, C1715R.string.lockpassword_confirm_label),
            ConfirmWrong(C1715R.string.lockpassword_confirm_passwords_dont_match, C1715R.string.lockpassword_confirm_passwords_dont_match, C1715R.string.lockpassword_confirm_passwords_dont_match, C1715R.string.lockpassword_confirm_pins_dont_match, C1715R.string.lockpassword_confirm_pins_dont_match, C1715R.string.lockpassword_confirm_pins_dont_match, 0, 0, 0, 0, C1715R.string.lockpassword_confirm_label);
            
            public final int alphaHint;
            public final int alphaHintForFace;
            public final int alphaHintForFingerprint;
            public final int alphaMessage;
            public final int alphaMessageForBiometrics;
            public final int buttonText;
            public final int numericHint;
            public final int numericHintForFace;
            public final int numericHintForFingerprint;
            public final int numericMessage;
            public final int numericMessageForBiometrics;

            private Stage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
                this.alphaHint = i;
                this.alphaHintForFingerprint = i2;
                this.alphaHintForFace = i3;
                this.numericHint = i4;
                this.numericHintForFingerprint = i5;
                this.numericHintForFace = i6;
                this.alphaMessage = i7;
                this.alphaMessageForBiometrics = i8;
                this.numericMessage = i9;
                this.numericMessageForBiometrics = i10;
                this.buttonText = i11;
            }

            public int getHint(boolean z, int i) {
                if (z) {
                    if (i == 1) {
                        return this.alphaHintForFingerprint;
                    }
                    if (i == 2) {
                        return this.alphaHintForFace;
                    }
                    return this.alphaHint;
                } else if (i == 1) {
                    return this.numericHintForFingerprint;
                } else {
                    if (i == 2) {
                        return this.numericHintForFace;
                    }
                    return this.numericHint;
                }
            }

            public int getMessage(boolean z, int i) {
                return z ? i != 0 ? this.alphaMessageForBiometrics : this.alphaMessage : i != 0 ? this.numericMessageForBiometrics : this.numericMessage;
            }
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mLockPatternUtils = new LockPatternUtils(getActivity());
            Intent intent = getActivity().getIntent();
            if (getActivity() instanceof ChooseLockPassword) {
                this.mUserId = Utils.getUserIdFromBundle(getActivity(), intent.getExtras());
                this.mForFingerprint = intent.getBooleanExtra("for_fingerprint", false);
                this.mForFace = intent.getBooleanExtra("for_face", false);
                this.mRequestedMinComplexity = intent.getIntExtra("requested_min_complexity", 0);
                this.mRequestedQuality = Math.max(intent.getIntExtra("lockscreen.password_type", this.mRequestedQuality), this.mLockPatternUtils.getRequestedPasswordQuality(this.mUserId));
                loadDpmPasswordRequirements();
                this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
                if (intent.getBooleanExtra("for_cred_req_boot", false)) {
                    SaveAndFinishWorker saveAndFinishWorker = new SaveAndFinishWorker();
                    boolean booleanExtra = getActivity().getIntent().getBooleanExtra("extra_require_password", true);
                    byte[] byteArrayExtra = intent.getByteArrayExtra("password");
                    saveAndFinishWorker.setBlocking(true);
                    saveAndFinishWorker.setListener(this);
                    saveAndFinishWorker.start(this.mChooseLockSettingsHelper.utils(), booleanExtra, false, 0, byteArrayExtra, byteArrayExtra, this.mRequestedQuality, this.mUserId);
                }
                this.mTextChangedHandler = new TextChangedHandler();
                return;
            }
            throw new SecurityException("Fragment contained in wrong activity");
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(C1715R.layout.choose_lock_password, viewGroup, false);
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mLayout = (GlifLayout) view;
            ((ViewGroup) view.findViewById(C1715R.C1718id.password_container)).setOpticalInsets(Insets.NONE);
            FooterBarMixin footerBarMixin = (FooterBarMixin) this.mLayout.getMixin(FooterBarMixin.class);
            FooterButton.Builder builder = new FooterButton.Builder(getActivity());
            builder.setText(C1715R.string.lockpassword_clear_label);
            builder.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ChooseLockPassword.ChooseLockPasswordFragment.this.onSkipOrClearButtonClick(view);
                }
            });
            builder.setButtonType(7);
            builder.setTheme(2131952051);
            footerBarMixin.setSecondaryButton(builder.build());
            FooterButton.Builder builder2 = new FooterButton.Builder(getActivity());
            builder2.setText(C1715R.string.next_label);
            builder2.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ChooseLockPassword.ChooseLockPasswordFragment.this.onNextButtonClick(view);
                }
            });
            builder2.setButtonType(5);
            builder2.setTheme(2131952050);
            footerBarMixin.setPrimaryButton(builder2.build());
            this.mSkipOrClearButton = footerBarMixin.getSecondaryButton();
            this.mNextButton = footerBarMixin.getPrimaryButton();
            this.mMessage = (TextView) view.findViewById(C1715R.C1718id.sud_layout_description);
            if (this.mForFingerprint) {
                this.mLayout.setIcon(getActivity().getDrawable(C1715R.C1717drawable.ic_fingerprint_header));
            } else if (this.mForFace) {
                this.mLayout.setIcon(getActivity().getDrawable(C1715R.C1717drawable.ic_face_header));
            }
            int i = this.mRequestedQuality;
            this.mIsAlphaMode = 262144 == i || 327680 == i || 393216 == i;
            setupPasswordRequirementsView(view);
            this.mPasswordRestrictionView.setLayoutManager(new LinearLayoutManager(getActivity()));
            this.mPasswordEntry = (ImeAwareEditText) view.findViewById(C1715R.C1718id.password_entry);
            this.mPasswordEntry.setOnEditorActionListener(this);
            this.mPasswordEntry.addTextChangedListener(this);
            this.mPasswordEntry.requestFocus();
            this.mPasswordEntryInputDisabler = new TextViewInputDisabler(this.mPasswordEntry);
            FragmentActivity activity = getActivity();
            int inputType = this.mPasswordEntry.getInputType();
            ImeAwareEditText imeAwareEditText = this.mPasswordEntry;
            if (!this.mIsAlphaMode) {
                inputType = 18;
            }
            imeAwareEditText.setInputType(inputType);
            this.mPasswordEntry.setTypeface(Typeface.create(getContext().getString(17039781), 0));
            Intent intent = getActivity().getIntent();
            boolean booleanExtra = intent.getBooleanExtra("confirm_credentials", true);
            this.mCurrentPassword = intent.getByteArrayExtra("password");
            this.mHasChallenge = intent.getBooleanExtra("has_challenge", false);
            this.mChallenge = intent.getLongExtra("challenge", 0);
            if (bundle == null) {
                updateStage(Stage.Introduction);
                if (booleanExtra) {
                    this.mChooseLockSettingsHelper.launchConfirmationActivity(58, getString(C1715R.string.unlock_set_unlock_launch_picker_title), true, this.mUserId);
                }
            } else {
                this.mFirstPin = bundle.getByteArray("first_pin");
                String string = bundle.getString("ui_stage");
                if (string != null) {
                    this.mUiStage = Stage.valueOf(string);
                    updateStage(this.mUiStage);
                }
                if (this.mCurrentPassword == null) {
                    this.mCurrentPassword = bundle.getByteArray("current_password");
                }
                this.mSaveAndFinishWorker = (SaveAndFinishWorker) getFragmentManager().findFragmentByTag("save_and_finish_worker");
            }
            if (activity instanceof SettingsActivity) {
                int hint = Stage.Introduction.getHint(this.mIsAlphaMode, getStageType());
                ((SettingsActivity) activity).setTitle(hint);
                this.mLayout.setHeaderText(hint);
            }
        }

        /* access modifiers changed from: protected */
        public int getStageType() {
            if (this.mForFingerprint) {
                return 1;
            }
            return this.mForFace ? 2 : 0;
        }

        private void setupPasswordRequirementsView(View view) {
            this.mPasswordRestrictionView = (RecyclerView) view.findViewById(C1715R.C1718id.password_requirements_view);
            this.mPasswordRestrictionView.setLayoutManager(new LinearLayoutManager(getActivity()));
            this.mPasswordRequirementAdapter = new PasswordRequirementAdapter();
            this.mPasswordRestrictionView.setAdapter(this.mPasswordRequirementAdapter);
        }

        public void onResume() {
            super.onResume();
            updateStage(this.mUiStage);
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener(this);
                return;
            }
            this.mPasswordEntry.requestFocus();
            this.mPasswordEntry.scheduleShowSoftInput();
        }

        public void onPause() {
            SaveAndFinishWorker saveAndFinishWorker = this.mSaveAndFinishWorker;
            if (saveAndFinishWorker != null) {
                saveAndFinishWorker.setListener((SaveChosenLockWorkerBase.Listener) null);
            }
            super.onPause();
        }

        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putString("ui_stage", this.mUiStage.name());
            bundle.putByteArray("first_pin", this.mFirstPin);
            bundle.putByteArray("current_password", this.mCurrentPassword);
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i == 58) {
                if (i2 != -1) {
                    getActivity().setResult(1);
                    getActivity().finish();
                    return;
                }
                this.mCurrentPassword = intent.getByteArrayExtra("password");
            }
        }

        /* access modifiers changed from: protected */
        public Intent getRedactionInterstitialIntent(Context context) {
            return RedactionInterstitial.createStartIntent(context, this.mUserId);
        }

        /* access modifiers changed from: protected */
        public void updateStage(Stage stage) {
            Stage stage2 = this.mUiStage;
            this.mUiStage = stage;
            updateUi();
            if (stage2 != stage) {
                GlifLayout glifLayout = this.mLayout;
                glifLayout.announceForAccessibility(glifLayout.getHeaderText());
            }
        }

        private void loadDpmPasswordRequirements() {
            int requestedPasswordQuality = this.mLockPatternUtils.getRequestedPasswordQuality(this.mUserId);
            if (requestedPasswordQuality == 196608) {
                this.mPasswordNumSequenceAllowed = false;
            }
            this.mPasswordMinLength = Math.max(4, this.mLockPatternUtils.getRequestedMinimumPasswordLength(this.mUserId));
            this.mPasswordMaxLength = this.mLockPatternUtils.getMaximumPasswordLength(this.mRequestedQuality);
            this.mPasswordMinLetters = this.mLockPatternUtils.getRequestedPasswordMinimumLetters(this.mUserId);
            this.mPasswordMinUpperCase = this.mLockPatternUtils.getRequestedPasswordMinimumUpperCase(this.mUserId);
            this.mPasswordMinLowerCase = this.mLockPatternUtils.getRequestedPasswordMinimumLowerCase(this.mUserId);
            this.mPasswordMinNumeric = this.mLockPatternUtils.getRequestedPasswordMinimumNumeric(this.mUserId);
            this.mPasswordMinSymbols = this.mLockPatternUtils.getRequestedPasswordMinimumSymbols(this.mUserId);
            this.mPasswordMinNonLetter = this.mLockPatternUtils.getRequestedPasswordMinimumNonLetter(this.mUserId);
            if (requestedPasswordQuality != 262144) {
                if (requestedPasswordQuality == 327680) {
                    if (this.mPasswordMinLetters == 0) {
                        this.mPasswordMinLetters = 1;
                    }
                    if (this.mPasswordMinNumeric == 0) {
                        this.mPasswordMinNumeric = 1;
                    }
                } else if (requestedPasswordQuality != 393216) {
                    this.mPasswordMinNumeric = 0;
                    this.mPasswordMinLetters = 0;
                    this.mPasswordMinUpperCase = 0;
                    this.mPasswordMinLowerCase = 0;
                    this.mPasswordMinSymbols = 0;
                    this.mPasswordMinNonLetter = 0;
                }
            } else if (this.mPasswordMinLetters == 0) {
                this.mPasswordMinLetters = 1;
            }
            this.mPasswordMinLengthToFulfillAllPolicies = getMinLengthToFulfillAllPolicies();
        }

        private void mergeMinComplexityAndDpmRequirements(int i) {
            if (this.mRequestedMinComplexity != 0) {
                loadDpmPasswordRequirements();
                PasswordMetrics minimumMetrics = PasswordMetrics.getMinimumMetrics(this.mRequestedMinComplexity, i, this.mRequestedQuality, requiresNumeric(), requiresLettersOrSymbols());
                this.mPasswordNumSequenceAllowed = this.mPasswordNumSequenceAllowed && minimumMetrics.quality != 196608;
                this.mPasswordMinLength = Math.max(this.mPasswordMinLength, minimumMetrics.length);
                this.mPasswordMinLetters = Math.max(this.mPasswordMinLetters, minimumMetrics.letters);
                this.mPasswordMinUpperCase = Math.max(this.mPasswordMinUpperCase, minimumMetrics.upperCase);
                this.mPasswordMinLowerCase = Math.max(this.mPasswordMinLowerCase, minimumMetrics.lowerCase);
                this.mPasswordMinNumeric = Math.max(this.mPasswordMinNumeric, minimumMetrics.numeric);
                this.mPasswordMinSymbols = Math.max(this.mPasswordMinSymbols, minimumMetrics.symbols);
                this.mPasswordMinNonLetter = Math.max(this.mPasswordMinNonLetter, minimumMetrics.nonLetter);
                if (minimumMetrics.quality == 262144 && !requiresLettersOrSymbols()) {
                    this.mPasswordMinLetters = 1;
                }
                if (minimumMetrics.quality == 327680) {
                    if (!requiresLettersOrSymbols()) {
                        this.mPasswordMinLetters = 1;
                    }
                    if (!requiresNumeric()) {
                        this.mPasswordMinNumeric = 1;
                    }
                }
                this.mPasswordMinLengthToFulfillAllPolicies = getMinLengthToFulfillAllPolicies();
            }
        }

        private boolean requiresLettersOrSymbols() {
            return (((this.mPasswordMinLetters + this.mPasswordMinUpperCase) + this.mPasswordMinLowerCase) + this.mPasswordMinSymbols) + this.mPasswordMinNonLetter > 0;
        }

        private boolean requiresNumeric() {
            return this.mPasswordMinNumeric > 0;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public int validatePassword(byte[] bArr) {
            int i;
            PasswordMetrics computeForPassword = PasswordMetrics.computeForPassword(bArr);
            mergeMinComplexityAndDpmRequirements(computeForPassword.quality);
            int i2 = 0;
            if (bArr == null || bArr.length < this.mPasswordMinLength) {
                i = this.mPasswordMinLength > this.mPasswordMinLengthToFulfillAllPolicies ? 2 : 0;
            } else if (bArr.length > this.mPasswordMaxLength) {
                i = 4;
            } else {
                i = (this.mPasswordNumSequenceAllowed || requiresLettersOrSymbols() || computeForPassword.numeric != bArr.length || PasswordMetrics.maxLengthSequence(bArr) <= 3) ? 0 : 16;
                if (this.mLockPatternUtils.checkPasswordHistory(bArr, getPasswordHistoryHashFactor(), this.mUserId)) {
                    i |= 32;
                }
            }
            while (true) {
                if (i2 >= bArr.length) {
                    break;
                }
                char c = (char) bArr[i2];
                if (c < ' ' || c > 127) {
                    i |= 1;
                } else {
                    i2++;
                }
            }
            int i3 = this.mRequestedQuality;
            if ((i3 == 131072 || i3 == 196608) && (computeForPassword.letters > 0 || computeForPassword.symbols > 0)) {
                i |= 8;
            }
            if (computeForPassword.letters < this.mPasswordMinLetters) {
                i |= 64;
            }
            if (computeForPassword.upperCase < this.mPasswordMinUpperCase) {
                i |= 128;
            }
            if (computeForPassword.lowerCase < this.mPasswordMinLowerCase) {
                i |= 256;
            }
            if (computeForPassword.symbols < this.mPasswordMinSymbols) {
                i |= 1024;
            }
            if (computeForPassword.numeric < this.mPasswordMinNumeric) {
                i |= 512;
            }
            return computeForPassword.nonLetter < this.mPasswordMinNonLetter ? i | 2048 : i;
        }

        private byte[] getPasswordHistoryHashFactor() {
            if (this.mPasswordHistoryHashFactor == null) {
                this.mPasswordHistoryHashFactor = this.mLockPatternUtils.getPasswordHistoryHashFactor(this.mCurrentPassword, this.mUserId);
            }
            return this.mPasswordHistoryHashFactor;
        }

        public void handleNext() {
            if (this.mSaveAndFinishWorker == null) {
                this.mChosenPassword = LockPatternUtils.charSequenceToByteArray(this.mPasswordEntry.getText());
                byte[] bArr = this.mChosenPassword;
                if (bArr != null && bArr.length != 0) {
                    Stage stage = this.mUiStage;
                    if (stage == Stage.Introduction) {
                        if (validatePassword(bArr) == 0) {
                            this.mFirstPin = this.mChosenPassword;
                            this.mPasswordEntry.setText("");
                            updateStage(Stage.NeedToConfirm);
                            return;
                        }
                        Arrays.fill(this.mChosenPassword, (byte) 0);
                    } else if (stage != Stage.NeedToConfirm) {
                    } else {
                        if (Arrays.equals(this.mFirstPin, bArr)) {
                            startSaveAndFinish();
                            Utils.savePINPasswordLength(this.mLockPatternUtils, this.mChosenPassword.length, this.mUserId);
                            return;
                        }
                        Editable text = this.mPasswordEntry.getText();
                        if (text != null) {
                            Selection.setSelection(text, 0, text.length());
                        }
                        updateStage(Stage.ConfirmWrong);
                        Arrays.fill(this.mChosenPassword, (byte) 0);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void setNextEnabled(boolean z) {
            this.mNextButton.setEnabled(z);
        }

        /* access modifiers changed from: protected */
        public void setNextText(int i) {
            this.mNextButton.setText(getActivity(), i);
        }

        /* access modifiers changed from: protected */
        public void onSkipOrClearButtonClick(View view) {
            this.mPasswordEntry.setText("");
        }

        /* access modifiers changed from: protected */
        public void onNextButtonClick(View view) {
            handleNext();
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 0 && i != 6 && i != 5) {
                return false;
            }
            handleNext();
            return true;
        }

        /* access modifiers changed from: package-private */
        public String[] convertErrorCodeToMessages(int i) {
            ArrayList arrayList = new ArrayList();
            if ((i & 1) > 0) {
                arrayList.add(getString(C1715R.string.lockpassword_illegal_character));
            }
            if ((i & 8) > 0) {
                arrayList.add(getString(C1715R.string.lockpassword_pin_contains_non_digits));
            }
            if ((i & 128) > 0) {
                Resources resources = getResources();
                int i2 = this.mPasswordMinUpperCase;
                arrayList.add(resources.getQuantityString(C1715R.plurals.lockpassword_password_requires_uppercase, i2, new Object[]{Integer.valueOf(i2)}));
            }
            if ((i & 256) > 0) {
                Resources resources2 = getResources();
                int i3 = this.mPasswordMinLowerCase;
                arrayList.add(resources2.getQuantityString(C1715R.plurals.lockpassword_password_requires_lowercase, i3, new Object[]{Integer.valueOf(i3)}));
            }
            if ((i & 64) > 0) {
                Resources resources3 = getResources();
                int i4 = this.mPasswordMinLetters;
                arrayList.add(resources3.getQuantityString(C1715R.plurals.lockpassword_password_requires_letters, i4, new Object[]{Integer.valueOf(i4)}));
            }
            if ((i & 512) > 0) {
                Resources resources4 = getResources();
                int i5 = this.mPasswordMinNumeric;
                arrayList.add(resources4.getQuantityString(C1715R.plurals.lockpassword_password_requires_numeric, i5, new Object[]{Integer.valueOf(i5)}));
            }
            if ((i & 1024) > 0) {
                Resources resources5 = getResources();
                int i6 = this.mPasswordMinSymbols;
                arrayList.add(resources5.getQuantityString(C1715R.plurals.lockpassword_password_requires_symbols, i6, new Object[]{Integer.valueOf(i6)}));
            }
            if ((i & 2048) > 0) {
                Resources resources6 = getResources();
                int i7 = this.mPasswordMinNonLetter;
                arrayList.add(resources6.getQuantityString(C1715R.plurals.lockpassword_password_requires_nonletter, i7, new Object[]{Integer.valueOf(i7)}));
            }
            if ((i & 2) > 0) {
                Resources resources7 = getResources();
                int i8 = this.mIsAlphaMode ? C1715R.plurals.lockpassword_password_too_short : C1715R.plurals.lockpassword_pin_too_short;
                int i9 = this.mPasswordMinLength;
                arrayList.add(resources7.getQuantityString(i8, i9, new Object[]{Integer.valueOf(i9)}));
            }
            if ((i & 4) > 0) {
                Resources resources8 = getResources();
                int i10 = this.mIsAlphaMode ? C1715R.plurals.lockpassword_password_too_long : C1715R.plurals.lockpassword_pin_too_long;
                int i11 = this.mPasswordMaxLength;
                arrayList.add(resources8.getQuantityString(i10, i11 + 1, new Object[]{Integer.valueOf(i11 + 1)}));
            }
            if ((i & 16) > 0) {
                arrayList.add(getString(C1715R.string.lockpassword_pin_no_sequential_digits));
            }
            if ((i & 32) > 0) {
                arrayList.add(getString(this.mIsAlphaMode ? C1715R.string.lockpassword_password_recently_used : C1715R.string.lockpassword_pin_recently_used));
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        private int getMinLengthToFulfillAllPolicies() {
            return Math.max(this.mPasswordMinLetters, this.mPasswordMinUpperCase + this.mPasswordMinLowerCase) + Math.max(this.mPasswordMinNonLetter, this.mPasswordMinSymbols + this.mPasswordMinNumeric);
        }

        /* access modifiers changed from: protected */
        public void updateUi() {
            boolean z = true;
            boolean z2 = this.mSaveAndFinishWorker == null;
            byte[] charSequenceToByteArray = LockPatternUtils.charSequenceToByteArray(this.mPasswordEntry.getText());
            int length = charSequenceToByteArray.length;
            if (this.mUiStage == Stage.Introduction) {
                this.mPasswordRestrictionView.setVisibility(0);
                int validatePassword = validatePassword(charSequenceToByteArray);
                this.mPasswordRequirementAdapter.setRequirements(convertErrorCodeToMessages(validatePassword));
                if (validatePassword != 0) {
                    z = false;
                }
                setNextEnabled(z);
            } else {
                this.mPasswordRestrictionView.setVisibility(8);
                setHeaderText(getString(this.mUiStage.getHint(this.mIsAlphaMode, getStageType())));
                setNextEnabled(z2 && length >= this.mPasswordMinLength);
                FooterButton footerButton = this.mSkipOrClearButton;
                if (!z2 || length <= 0) {
                    z = false;
                }
                footerButton.setVisibility(toVisibility(z));
            }
            int message = this.mUiStage.getMessage(this.mIsAlphaMode, getStageType());
            if (message != 0) {
                this.mMessage.setVisibility(0);
                this.mMessage.setText(message);
            } else {
                this.mMessage.setVisibility(4);
            }
            setNextText(this.mUiStage.buttonText);
            this.mPasswordEntryInputDisabler.setInputEnabled(z2);
            Arrays.fill(charSequenceToByteArray, (byte) 0);
        }

        private void setHeaderText(String str) {
            if (TextUtils.isEmpty(this.mLayout.getHeaderText()) || !this.mLayout.getHeaderText().toString().equals(str)) {
                this.mLayout.setHeaderText((CharSequence) str);
            }
        }

        public void afterTextChanged(Editable editable) {
            if (this.mUiStage == Stage.ConfirmWrong) {
                this.mUiStage = Stage.NeedToConfirm;
            }
            this.mTextChangedHandler.notifyAfterTextChanged();
        }

        private void startSaveAndFinish() {
            if (this.mSaveAndFinishWorker != null) {
                Log.w("ChooseLockPassword", "startSaveAndFinish with an existing SaveAndFinishWorker.");
                return;
            }
            this.mPasswordEntryInputDisabler.setInputEnabled(false);
            setNextEnabled(false);
            this.mSaveAndFinishWorker = new SaveAndFinishWorker();
            this.mSaveAndFinishWorker.setListener(this);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) this.mSaveAndFinishWorker, "save_and_finish_worker");
            beginTransaction.commit();
            getFragmentManager().executePendingTransactions();
            this.mSaveAndFinishWorker.start(this.mLockPatternUtils, getActivity().getIntent().getBooleanExtra("extra_require_password", true), this.mHasChallenge, this.mChallenge, this.mChosenPassword, this.mCurrentPassword, this.mRequestedQuality, this.mUserId);
        }

        public void onChosenLockSaveFinished(boolean z, Intent intent) {
            Intent redactionInterstitialIntent;
            getActivity().setResult(1, intent);
            byte[] bArr = this.mChosenPassword;
            if (bArr != null) {
                Arrays.fill(bArr, (byte) 0);
            }
            byte[] bArr2 = this.mCurrentPassword;
            if (bArr2 != null) {
                Arrays.fill(bArr2, (byte) 0);
            }
            byte[] bArr3 = this.mFirstPin;
            if (bArr3 != null) {
                Arrays.fill(bArr3, (byte) 0);
            }
            this.mPasswordEntry.setText("");
            if (!z && (redactionInterstitialIntent = getRedactionInterstitialIntent(getActivity())) != null) {
                startActivity(redactionInterstitialIntent);
            }
            getActivity().finish();
        }

        class TextChangedHandler extends Handler {
            TextChangedHandler() {
            }

            /* access modifiers changed from: private */
            public void notifyAfterTextChanged() {
                removeMessages(1);
                sendEmptyMessageDelayed(1, 100);
            }

            public void handleMessage(Message message) {
                if (ChooseLockPasswordFragment.this.getActivity() != null && message.what == 1) {
                    ChooseLockPasswordFragment.this.updateUi();
                }
            }
        }
    }

    public static class SaveAndFinishWorker extends SaveChosenLockWorkerBase {
        private byte[] mChosenPassword;
        private byte[] mCurrentPassword;
        private int mRequestedQuality;

        public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        public /* bridge */ /* synthetic */ void setBlocking(boolean z) {
            super.setBlocking(z);
        }

        public /* bridge */ /* synthetic */ void setListener(SaveChosenLockWorkerBase.Listener listener) {
            super.setListener(listener);
        }

        public void start(LockPatternUtils lockPatternUtils, boolean z, boolean z2, long j, byte[] bArr, byte[] bArr2, int i, int i2) {
            prepare(lockPatternUtils, z, z2, j, i2);
            this.mChosenPassword = bArr;
            this.mCurrentPassword = bArr2;
            this.mRequestedQuality = i;
            this.mUserId = i2;
            start();
        }

        /* access modifiers changed from: protected */
        public Pair<Boolean, Intent> saveAndVerifyInBackground() {
            Intent intent;
            boolean saveLockPassword = this.mUtils.saveLockPassword(this.mChosenPassword, this.mCurrentPassword, this.mRequestedQuality, this.mUserId);
            byte[] bArr = null;
            if (!saveLockPassword || !this.mHasChallenge) {
                intent = null;
            } else {
                try {
                    bArr = this.mUtils.verifyPassword(this.mChosenPassword, this.mChallenge, this.mUserId);
                } catch (LockPatternUtils.RequestThrottledException unused) {
                }
                if (bArr == null) {
                    Log.e("ChooseLockPassword", "critical: no token returned for known good password.");
                }
                intent = new Intent();
                intent.putExtra("hw_auth_token", bArr);
            }
            return Pair.create(Boolean.valueOf(saveLockPassword), intent);
        }
    }
}
