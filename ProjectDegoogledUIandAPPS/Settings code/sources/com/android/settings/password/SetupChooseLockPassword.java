package com.android.settings.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SetupRedactionInterstitial;
import com.android.settings.password.ChooseLockPassword;
import com.android.settings.password.ChooseLockTypeDialogFragment;
import com.android.settings.password.SetupChooseLockPassword;
import com.havoc.config.center.C1715R;

public class SetupChooseLockPassword extends ChooseLockPassword {
    public static Intent modifyIntentForSetup(Context context, Intent intent) {
        intent.setClass(context, SetupChooseLockPassword.class);
        intent.putExtra("extra_prefs_show_button_bar", false);
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return SetupChooseLockPasswordFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends Fragment> getFragmentClass() {
        return SetupChooseLockPasswordFragment.class;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LinearLayout) findViewById(C1715R.C1718id.content_parent)).setFitsSystemWindows(false);
    }

    public static class SetupChooseLockPasswordFragment extends ChooseLockPassword.ChooseLockPasswordFragment implements ChooseLockTypeDialogFragment.OnLockTypeSelectedListener {
        private boolean mLeftButtonIsSkip;
        private Button mOptionsButton;

        /* access modifiers changed from: protected */
        public int getStageType() {
            return 0;
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            FragmentActivity activity = getActivity();
            boolean z = new ChooseLockGenericController(activity, this.mUserId).getVisibleScreenLockTypes(65536, false).size() > 0;
            boolean booleanExtra = activity.getIntent().getBooleanExtra("show_options_button", false);
            if (!z) {
                Log.w("SetupChooseLockPassword", "Visible screen lock types is empty!");
            }
            if (booleanExtra && z) {
                this.mOptionsButton = (Button) view.findViewById(C1715R.C1718id.screen_lock_options);
                this.mOptionsButton.setVisibility(0);
                this.mOptionsButton.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        SetupChooseLockPassword.SetupChooseLockPasswordFragment.this.mo11494x2d5f4baf(view);
                    }
                });
            }
        }

        /* renamed from: lambda$onViewCreated$0$SetupChooseLockPassword$SetupChooseLockPasswordFragment */
        public /* synthetic */ void mo11494x2d5f4baf(View view) {
            ChooseLockTypeDialogFragment.newInstance(this.mUserId).show(getChildFragmentManager(), "skip_screen_lock_dialog");
        }

        /* access modifiers changed from: protected */
        public void onSkipOrClearButtonClick(View view) {
            if (this.mLeftButtonIsSkip) {
                SetupSkipDialog.newInstance(getActivity().getIntent().getBooleanExtra(":settings:frp_supported", false), false, this.mIsAlphaMode, getActivity().getIntent().getBooleanExtra("for_fingerprint", false), getActivity().getIntent().getBooleanExtra("for_face", false)).show(getFragmentManager());
            } else {
                super.onSkipOrClearButtonClick(view);
            }
        }

        /* access modifiers changed from: protected */
        public Intent getRedactionInterstitialIntent(Context context) {
            SetupRedactionInterstitial.setEnabled(context, true);
            return null;
        }

        public void onLockTypeSelected(ScreenLockType screenLockType) {
            if (screenLockType != (this.mIsAlphaMode ? ScreenLockType.PASSWORD : ScreenLockType.PIN)) {
                startChooseLockActivity(screenLockType, getActivity());
            }
        }

        /* access modifiers changed from: protected */
        public void updateUi() {
            super.updateUi();
            int i = 0;
            if (this.mUiStage == ChooseLockPassword.ChooseLockPasswordFragment.Stage.Introduction) {
                this.mSkipOrClearButton.setText(getActivity(), C1715R.string.skip_label);
                this.mLeftButtonIsSkip = true;
            } else {
                this.mSkipOrClearButton.setText(getActivity(), C1715R.string.lockpassword_clear_label);
                this.mLeftButtonIsSkip = false;
            }
            Button button = this.mOptionsButton;
            if (button != null) {
                if (this.mUiStage != ChooseLockPassword.ChooseLockPasswordFragment.Stage.Introduction) {
                    i = 8;
                }
                button.setVisibility(i);
            }
        }
    }
}
