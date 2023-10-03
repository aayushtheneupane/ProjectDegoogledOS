package com.android.settings.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.android.settings.SetupRedactionInterstitial;
import com.android.settings.password.ChooseLockPattern;
import com.android.settings.password.ChooseLockTypeDialogFragment;
import com.android.settings.password.SetupChooseLockPattern;
import com.havoc.config.center.C1715R;

public class SetupChooseLockPattern extends ChooseLockPattern {
    public static Intent modifyIntentForSetup(Context context, Intent intent) {
        intent.setClass(context, SetupChooseLockPattern.class);
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return SetupChooseLockPatternFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends Fragment> getFragmentClass() {
        return SetupChooseLockPatternFragment.class;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(C1715R.string.lockpassword_choose_your_screen_lock_header);
    }

    public static class SetupChooseLockPatternFragment extends ChooseLockPattern.ChooseLockPatternFragment implements ChooseLockTypeDialogFragment.OnLockTypeSelectedListener {
        private boolean mLeftButtonIsSkip;
        private Button mOptionsButton;

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            if (!getResources().getBoolean(C1715R.bool.config_lock_pattern_minimal_ui)) {
                this.mOptionsButton = (Button) onCreateView.findViewById(C1715R.C1718id.screen_lock_options);
                this.mOptionsButton.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        SetupChooseLockPattern.SetupChooseLockPatternFragment.this.mo11495x72a7ba19(view);
                    }
                });
            }
            this.mSkipOrClearButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    SetupChooseLockPattern.SetupChooseLockPatternFragment.this.onSkipOrClearButtonClick(view);
                }
            });
            return onCreateView;
        }

        /* renamed from: lambda$onCreateView$0$SetupChooseLockPattern$SetupChooseLockPatternFragment */
        public /* synthetic */ void mo11495x72a7ba19(View view) {
            ChooseLockTypeDialogFragment.newInstance(this.mUserId).show(getChildFragmentManager(), "skip_screen_lock_dialog");
        }

        /* access modifiers changed from: protected */
        public void onSkipOrClearButtonClick(View view) {
            if (this.mLeftButtonIsSkip) {
                SetupSkipDialog.newInstance(getActivity().getIntent().getBooleanExtra(":settings:frp_supported", false), true, false, getActivity().getIntent().getBooleanExtra("for_fingerprint", false), getActivity().getIntent().getBooleanExtra("for_face", false)).show(getFragmentManager());
            } else {
                super.onSkipOrClearButtonClick(view);
            }
        }

        public void onLockTypeSelected(ScreenLockType screenLockType) {
            if (ScreenLockType.PATTERN != screenLockType) {
                startChooseLockActivity(screenLockType, getActivity());
            }
        }

        /* access modifiers changed from: protected */
        public void updateStage(ChooseLockPattern.ChooseLockPatternFragment.Stage stage) {
            Button button;
            super.updateStage(stage);
            if (!getResources().getBoolean(C1715R.bool.config_lock_pattern_minimal_ui) && (button = this.mOptionsButton) != null) {
                button.setVisibility((stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.Introduction || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.HelpScreen || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.ChoiceTooShort || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.FirstChoiceValid) ? 0 : 4);
            }
            if (stage.leftMode == ChooseLockPattern.ChooseLockPatternFragment.LeftButtonMode.Gone && stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.Introduction) {
                this.mSkipOrClearButton.setVisibility(0);
                this.mSkipOrClearButton.setText(getActivity(), C1715R.string.skip_label);
                this.mLeftButtonIsSkip = true;
            } else {
                this.mLeftButtonIsSkip = false;
            }
            int i = stage.message;
            if (i == -1) {
                this.mMessageText.setText("");
            } else {
                this.mMessageText.setText(i);
            }
        }

        /* access modifiers changed from: protected */
        public Intent getRedactionInterstitialIntent(Context context) {
            SetupRedactionInterstitial.setEnabled(context, true);
            return null;
        }
    }
}
