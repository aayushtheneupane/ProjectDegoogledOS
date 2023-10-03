package com.android.settings.password;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.IBiometricConfirmDeviceCredentialCallback;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.BiometricFragment;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Executor;

public class BiometricFragment extends InstrumentedFragment {
    /* access modifiers changed from: private */
    public boolean mAuthenticating;
    /* access modifiers changed from: private */
    public BiometricPrompt.AuthenticationCallback mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() {
        public void onAuthenticationError(int i, CharSequence charSequence) {
            boolean unused = BiometricFragment.this.mAuthenticating = false;
            BiometricFragment.this.mClientExecutor.execute(new Runnable(i, charSequence) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ CharSequence f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    BiometricFragment.C11081.this.lambda$onAuthenticationError$0$BiometricFragment$1(this.f$1, this.f$2);
                }
            });
            BiometricFragment.this.cleanup();
        }

        public /* synthetic */ void lambda$onAuthenticationError$0$BiometricFragment$1(int i, CharSequence charSequence) {
            BiometricFragment.this.mClientCallback.onAuthenticationError(i, charSequence);
        }

        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
            boolean unused = BiometricFragment.this.mAuthenticating = false;
            BiometricFragment.this.mClientExecutor.execute(new Runnable(authenticationResult) {
                private final /* synthetic */ BiometricPrompt.AuthenticationResult f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BiometricFragment.C11081.this.lambda$onAuthenticationSucceeded$1$BiometricFragment$1(this.f$1);
                }
            });
            BiometricFragment.this.cleanup();
        }

        public /* synthetic */ void lambda$onAuthenticationSucceeded$1$BiometricFragment$1(BiometricPrompt.AuthenticationResult authenticationResult) {
            BiometricFragment.this.mClientCallback.onAuthenticationSucceeded(authenticationResult);
        }
    };
    private BiometricPrompt mBiometricPrompt;
    /* access modifiers changed from: private */
    public Bundle mBundle;
    private final IBiometricConfirmDeviceCredentialCallback mCancelCallback = new IBiometricConfirmDeviceCredentialCallback.Stub() {
        public void cancel() {
            FragmentActivity activity = BiometricFragment.this.getActivity();
            if (activity != null) {
                activity.finish();
            } else {
                Log.e("ConfirmDeviceCredential/BiometricFragment", "Activity null!");
            }
        }
    };
    private CancellationSignal mCancellationSignal;
    /* access modifiers changed from: private */
    public BiometricPrompt.AuthenticationCallback mClientCallback;
    /* access modifiers changed from: private */
    public Executor mClientExecutor;
    private final DialogInterface.OnClickListener mNegativeButtonListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            BiometricFragment.this.mAuthenticationCallback.onAuthenticationError(13, BiometricFragment.this.mBundle.getString("negative_text"));
        }
    };
    private int mUserId;

    public int getMetricsCategory() {
        return 1585;
    }

    public static BiometricFragment newInstance(Bundle bundle) {
        BiometricFragment biometricFragment = new BiometricFragment();
        biometricFragment.setArguments(bundle);
        return biometricFragment;
    }

    public void setCallbacks(Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mClientExecutor = executor;
        this.mClientCallback = authenticationCallback;
    }

    public void setUser(int i) {
        this.mUserId = i;
    }

    public void cancel() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        cleanup();
    }

    /* access modifiers changed from: private */
    public void cleanup() {
        if (getActivity() != null) {
            FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            beginTransaction.remove(this);
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAuthenticating() {
        return this.mAuthenticating;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mBundle = getArguments();
        BiometricPrompt.Builder confirmationRequired = new BiometricPrompt.Builder(getContext()).setTitle(this.mBundle.getString("title")).setUseDefaultTitle().setFromConfirmDeviceCredential().setSubtitle(this.mBundle.getString("subtitle")).setDescription(this.mBundle.getString("description")).setApplockPackage(this.mBundle.getString("applock_package_name")).setConfirmationRequired(this.mBundle.getBoolean("require_confirmation", true));
        int keyguardStoredPasswordQuality = FeatureFactory.getFactory(getContext()).getSecurityFeatureProvider().getLockPatternUtils(getContext()).getKeyguardStoredPasswordQuality(this.mUserId);
        if (keyguardStoredPasswordQuality == 65536) {
            confirmationRequired.setNegativeButton(getResources().getString(C1715R.string.confirm_device_credential_pattern), this.mClientExecutor, this.mNegativeButtonListener);
        } else if (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) {
            confirmationRequired.setNegativeButton(getResources().getString(C1715R.string.confirm_device_credential_pin), this.mClientExecutor, this.mNegativeButtonListener);
        } else if (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288) {
            confirmationRequired.setNegativeButton(getResources().getString(C1715R.string.confirm_device_credential_password), this.mClientExecutor, this.mNegativeButtonListener);
        }
        this.mBiometricPrompt = confirmationRequired.build();
        this.mCancellationSignal = new CancellationSignal();
        this.mAuthenticating = true;
        this.mBiometricPrompt.authenticateUser(this.mCancellationSignal, this.mClientExecutor, this.mAuthenticationCallback, this.mUserId, this.mCancelCallback);
    }
}
