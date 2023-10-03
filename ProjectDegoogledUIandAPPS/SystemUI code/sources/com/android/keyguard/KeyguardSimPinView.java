package com.android.keyguard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.IccCardConstants;
import com.android.keyguard.PasswordTextView;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1782R$plurals;
import com.android.systemui.C1784R$string;

public class KeyguardSimPinView extends KeyguardPinBasedInputView {
    /* access modifiers changed from: private */
    public CheckSimPin mCheckSimPinThread;
    /* access modifiers changed from: private */
    public int mRemainingAttempts;
    private AlertDialog mRemainingAttemptsDialog;
    /* access modifiers changed from: private */
    public boolean mShowDefaultMessage;
    private ImageView mSimImageView;
    /* access modifiers changed from: private */
    public ProgressDialog mSimUnlockProgressDialog;
    /* access modifiers changed from: private */
    public int mSubId;
    KeyguardUpdateMonitorCallback mUpdateMonitorCallback;

    /* access modifiers changed from: protected */
    public int getPromptReasonStringRes(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean shouldLockout(long j) {
        return false;
    }

    public void startAppearAnimation() {
    }

    public boolean startDisappearAnimation(Runnable runnable) {
        return false;
    }

    /* renamed from: com.android.keyguard.KeyguardSimPinView$4 */
    static /* synthetic */ class C04664 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$IccCardConstants$State = new int[IccCardConstants.State.values().length];

        static {
            try {
                $SwitchMap$com$android$internal$telephony$IccCardConstants$State[IccCardConstants.State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public KeyguardSimPinView(Context context) {
        this(context, (AttributeSet) null);
    }

    public KeyguardSimPinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSimUnlockProgressDialog = null;
        this.mShowDefaultMessage = true;
        this.mRemainingAttempts = -1;
        this.mSubId = -1;
        this.mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() {
            public void onSimStateChanged(int i, int i2, IccCardConstants.State state) {
                Log.v("KeyguardSimPinView", "onSimStateChanged(subId=" + i + ",state=" + state + ")");
                if (C04664.$SwitchMap$com$android$internal$telephony$IccCardConstants$State[state.ordinal()] != 1) {
                    KeyguardSimPinView.this.resetState();
                    return;
                }
                int unused = KeyguardSimPinView.this.mRemainingAttempts = -1;
                KeyguardSimPinView.this.resetState();
            }
        };
    }

    public void resetState() {
        super.resetState();
        Log.v("KeyguardSimPinView", "Resetting state");
        handleSubInfoChangeIfNeeded();
        if (this.mShowDefaultMessage) {
            showDefaultMessage();
        }
        ((KeyguardEsimArea) findViewById(C1777R$id.keyguard_esim_area)).setVisibility(KeyguardEsimArea.isEsimLocked(this.mContext, this.mSubId) ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public void setLockedSimMessage() {
        String str;
        boolean isEsimLocked = KeyguardEsimArea.isEsimLocked(this.mContext, this.mSubId);
        int simCount = TelephonyManager.getDefault().getSimCount();
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{C1772R$attr.wallpaperTextColor});
        int color = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        if (simCount < 2) {
            str = resources.getString(C1784R$string.kg_sim_pin_instructions);
        } else {
            SubscriptionInfo subscriptionInfoForSubId = KeyguardUpdateMonitor.getInstance(this.mContext).getSubscriptionInfoForSubId(this.mSubId);
            str = resources.getString(C1784R$string.kg_sim_pin_instructions_multi, new Object[]{subscriptionInfoForSubId != null ? subscriptionInfoForSubId.getDisplayName() : ""});
            if (subscriptionInfoForSubId != null) {
                color = subscriptionInfoForSubId.getIconTint();
            }
        }
        if (isEsimLocked) {
            str = resources.getString(C1784R$string.kg_sim_lock_esim_instructions, new Object[]{str});
        }
        if (this.mSecurityMessageDisplay != null && getVisibility() == 0) {
            this.mSecurityMessageDisplay.setMessage((CharSequence) str);
        }
        this.mSimImageView.setImageTintList(ColorStateList.valueOf(color));
    }

    private void showDefaultMessage() {
        setLockedSimMessage();
        if (this.mRemainingAttempts < 0) {
            new CheckSimPin("", this.mSubId) {
                /* access modifiers changed from: package-private */
                public void onSimCheckResponse(int i, int i2) {
                    Log.d("KeyguardSimPinView", "onSimCheckResponse  dummy One result" + i + " attemptsRemaining=" + i2);
                    if (i2 >= 0) {
                        int unused = KeyguardSimPinView.this.mRemainingAttempts = i2;
                        KeyguardSimPinView.this.setLockedSimMessage();
                    }
                }
            }.start();
        }
    }

    private void handleSubInfoChangeIfNeeded() {
        int nextSubIdForState = KeyguardUpdateMonitor.getInstance(this.mContext).getNextSubIdForState(IccCardConstants.State.PIN_REQUIRED);
        if (nextSubIdForState != this.mSubId && SubscriptionManager.isValidSubscriptionId(nextSubIdForState)) {
            this.mSubId = nextSubIdForState;
            this.mShowDefaultMessage = true;
            this.mRemainingAttempts = -1;
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetState();
    }

    /* access modifiers changed from: private */
    public String getPinPasswordErrorMessage(int i, boolean z) {
        String str;
        int i2;
        if (i == 0) {
            str = getContext().getString(C1784R$string.kg_password_wrong_pin_code_pukked);
        } else if (i > 0) {
            if (z) {
                i2 = C1782R$plurals.kg_password_default_pin_message;
            } else {
                i2 = C1782R$plurals.kg_password_wrong_pin_code;
            }
            str = getContext().getResources().getQuantityString(i2, i, new Object[]{Integer.valueOf(i)});
        } else {
            str = getContext().getString(z ? C1784R$string.kg_sim_pin_instructions : C1784R$string.kg_password_pin_failed);
        }
        if (KeyguardEsimArea.isEsimLocked(this.mContext, this.mSubId)) {
            str = getResources().getString(C1784R$string.kg_sim_lock_esim_instructions, new Object[]{str});
        }
        Log.d("KeyguardSimPinView", "getPinPasswordErrorMessage: attemptsRemaining=" + i + " displayMessage=" + str);
        return str;
    }

    /* access modifiers changed from: protected */
    public int getPasswordTextViewId() {
        return C1777R$id.simPinEntry;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        View view = this.mEcaView;
        if (view instanceof EmergencyCarrierArea) {
            ((EmergencyCarrierArea) view).setCarrierTextVisible(true);
        }
        this.mSimImageView = (ImageView) findViewById(C1777R$id.keyguard_sim);
        this.mPasswordEntry.setQuickUnlockListener((PasswordTextView.QuickUnlockListener) null);
    }

    public void onResume(int i) {
        super.onResume(i);
        KeyguardUpdateMonitor.getInstance(this.mContext).registerCallback(this.mUpdateMonitorCallback);
        resetState();
    }

    public void onPause() {
        ProgressDialog progressDialog = this.mSimUnlockProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mSimUnlockProgressDialog = null;
        }
        KeyguardUpdateMonitor.getInstance(this.mContext).removeCallback(this.mUpdateMonitorCallback);
    }

    private abstract class CheckSimPin extends Thread {
        private final String mPin;
        private int mSubId;

        /* access modifiers changed from: package-private */
        public abstract void onSimCheckResponse(int i, int i2);

        protected CheckSimPin(String str, int i) {
            this.mPin = str;
            this.mSubId = i;
        }

        public void run() {
            try {
                Log.v("KeyguardSimPinView", "call supplyPinReportResultForSubscriber(subid=" + this.mSubId + ")");
                final int[] supplyPinReportResultForSubscriber = ITelephony.Stub.asInterface(ServiceManager.checkService("phone")).supplyPinReportResultForSubscriber(this.mSubId, this.mPin);
                Log.v("KeyguardSimPinView", "supplyPinReportResult returned: " + supplyPinReportResultForSubscriber[0] + " " + supplyPinReportResultForSubscriber[1]);
                KeyguardSimPinView.this.post(new Runnable() {
                    public void run() {
                        CheckSimPin checkSimPin = CheckSimPin.this;
                        int[] iArr = supplyPinReportResultForSubscriber;
                        checkSimPin.onSimCheckResponse(iArr[0], iArr[1]);
                    }
                });
            } catch (RemoteException e) {
                Log.e("KeyguardSimPinView", "RemoteException for supplyPinReportResult:", e);
                KeyguardSimPinView.this.post(new Runnable() {
                    public void run() {
                        CheckSimPin.this.onSimCheckResponse(2, -1);
                    }
                });
            }
        }
    }

    private Dialog getSimUnlockProgressDialog() {
        if (this.mSimUnlockProgressDialog == null) {
            this.mSimUnlockProgressDialog = new ProgressDialog(this.mContext);
            this.mSimUnlockProgressDialog.setMessage(this.mContext.getString(C1784R$string.kg_sim_unlock_progress_dialog_message));
            this.mSimUnlockProgressDialog.setIndeterminate(true);
            this.mSimUnlockProgressDialog.setCancelable(false);
            this.mSimUnlockProgressDialog.getWindow().setType(2009);
        }
        return this.mSimUnlockProgressDialog;
    }

    /* access modifiers changed from: private */
    public Dialog getSimRemainingAttemptsDialog(int i) {
        String pinPasswordErrorMessage = getPinPasswordErrorMessage(i, false);
        AlertDialog alertDialog = this.mRemainingAttemptsDialog;
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setMessage(pinPasswordErrorMessage);
            builder.setCancelable(false);
            builder.setNeutralButton(C1784R$string.f31ok, (DialogInterface.OnClickListener) null);
            this.mRemainingAttemptsDialog = builder.create();
            this.mRemainingAttemptsDialog.getWindow().setType(2009);
        } else {
            alertDialog.setMessage(pinPasswordErrorMessage);
        }
        return this.mRemainingAttemptsDialog;
    }

    /* access modifiers changed from: protected */
    public void verifyPasswordAndUnlock() {
        if (this.mPasswordEntry.getText().length() < 4) {
            this.mSecurityMessageDisplay.setMessage(C1784R$string.kg_invalid_sim_pin_hint);
            resetPasswordText(true, true);
            this.mCallback.userActivity();
            return;
        }
        getSimUnlockProgressDialog().show();
        if (this.mCheckSimPinThread == null) {
            this.mCheckSimPinThread = new CheckSimPin(this.mPasswordEntry.getText(), this.mSubId) {
                /* access modifiers changed from: package-private */
                public void onSimCheckResponse(final int i, final int i2) {
                    KeyguardSimPinView.this.post(new Runnable() {
                        public void run() {
                            int unused = KeyguardSimPinView.this.mRemainingAttempts = i2;
                            if (KeyguardSimPinView.this.mSimUnlockProgressDialog != null) {
                                KeyguardSimPinView.this.mSimUnlockProgressDialog.hide();
                            }
                            KeyguardSimPinView.this.resetPasswordText(true, i != 0);
                            if (i == 0) {
                                KeyguardUpdateMonitor.getInstance(KeyguardSimPinView.this.getContext()).reportSimUnlocked(KeyguardSimPinView.this.mSubId);
                                int unused2 = KeyguardSimPinView.this.mRemainingAttempts = -1;
                                boolean unused3 = KeyguardSimPinView.this.mShowDefaultMessage = true;
                                KeyguardSecurityCallback keyguardSecurityCallback = KeyguardSimPinView.this.mCallback;
                                if (keyguardSecurityCallback != null) {
                                    keyguardSecurityCallback.dismiss(true, KeyguardUpdateMonitor.getCurrentUser());
                                }
                            } else {
                                boolean unused4 = KeyguardSimPinView.this.mShowDefaultMessage = false;
                                if (i == 1) {
                                    int i = i2;
                                    if (i <= 2) {
                                        KeyguardSimPinView.this.getSimRemainingAttemptsDialog(i).show();
                                    } else {
                                        KeyguardSimPinView keyguardSimPinView = KeyguardSimPinView.this;
                                        keyguardSimPinView.mSecurityMessageDisplay.setMessage((CharSequence) keyguardSimPinView.getPinPasswordErrorMessage(i, false));
                                    }
                                } else {
                                    KeyguardSimPinView keyguardSimPinView2 = KeyguardSimPinView.this;
                                    keyguardSimPinView2.mSecurityMessageDisplay.setMessage((CharSequence) keyguardSimPinView2.getContext().getString(C1784R$string.kg_password_pin_failed));
                                }
                                Log.d("KeyguardSimPinView", "verifyPasswordAndUnlock  CheckSimPin.onSimCheckResponse: " + i + " attemptsRemaining=" + i2);
                            }
                            KeyguardSimPinView.this.mCallback.userActivity();
                            CheckSimPin unused5 = KeyguardSimPinView.this.mCheckSimPinThread = null;
                        }
                    });
                }
            };
            this.mCheckSimPinThread.start();
        }
    }

    public CharSequence getTitle() {
        return getContext().getString(17040219);
    }
}
