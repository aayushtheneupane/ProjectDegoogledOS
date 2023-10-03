package com.android.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.internal.telephony.CommandException;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.settings.EditPinPreference;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class IccLockSettings extends SettingsPreferenceFragment implements EditPinPreference.OnPinEnteredListener {
    private int mDialogState = 0;
    private TabHost.TabContentFactory mEmptyTabContent = new TabHost.TabContentFactory() {
        public View createTabContent(String str) {
            return new View(IccLockSettings.this.mTabHost.getContext());
        }
    };
    private String mError;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            AsyncResult asyncResult = (AsyncResult) message.obj;
            boolean z = true;
            switch (message.what) {
                case 100:
                    IccLockSettings iccLockSettings = IccLockSettings.this;
                    if (asyncResult.exception != null) {
                        z = false;
                    }
                    iccLockSettings.iccLockChanged(z, message.arg1, asyncResult.exception);
                    return;
                case 101:
                    IccLockSettings iccLockSettings2 = IccLockSettings.this;
                    if (asyncResult.exception != null) {
                        z = false;
                    }
                    iccLockSettings2.iccPinChanged(z, message.arg1);
                    return;
                case 102:
                    IccLockSettings.this.updatePreferences();
                    return;
                default:
                    return;
            }
        }
    };
    private ListView mListView;
    private String mNewPin;
    private String mOldPin;
    /* access modifiers changed from: private */
    public Phone mPhone;
    private String mPin;
    private EditPinPreference mPinDialog;
    private SwitchPreference mPinToggle;
    private Resources mRes;
    private final BroadcastReceiver mSimStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                IccLockSettings.this.mHandler.sendMessage(IccLockSettings.this.mHandler.obtainMessage(102));
            }
        }
    };
    /* access modifiers changed from: private */
    public TabHost mTabHost;
    private TabHost.OnTabChangeListener mTabListener = new TabHost.OnTabChangeListener() {
        public void onTabChanged(String str) {
            Phone phone;
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = SubscriptionManager.from(IccLockSettings.this.getActivity().getBaseContext()).getActiveSubscriptionInfoForSimSlotIndex(Integer.parseInt(str));
            IccLockSettings iccLockSettings = IccLockSettings.this;
            if (activeSubscriptionInfoForSimSlotIndex == null) {
                phone = null;
            } else {
                phone = PhoneFactory.getPhone(SubscriptionManager.getPhoneId(activeSubscriptionInfoForSimSlotIndex.getSubscriptionId()));
            }
            Phone unused = iccLockSettings.mPhone = phone;
            IccLockSettings.this.updatePreferences();
        }
    };
    private TabWidget mTabWidget;
    private boolean mToState;

    public int getHelpResource() {
        return C1715R.string.help_url_icc_lock;
    }

    public int getMetricsCategory() {
        return 56;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Utils.isMonkeyRunning()) {
            finish();
            return;
        }
        addPreferencesFromResource(C1715R.xml.sim_lock_settings);
        this.mPinDialog = (EditPinPreference) findPreference("sim_pin");
        this.mPinToggle = (SwitchPreference) findPreference("sim_toggle");
        if (bundle != null && bundle.containsKey("dialogState")) {
            this.mDialogState = bundle.getInt("dialogState");
            this.mPin = bundle.getString("dialogPin");
            this.mError = bundle.getString("dialogError");
            this.mToState = bundle.getBoolean("enableState");
            int i = this.mDialogState;
            if (i == 3) {
                this.mOldPin = bundle.getString("oldPinCode");
            } else if (i == 4) {
                this.mOldPin = bundle.getString("oldPinCode");
                this.mNewPin = bundle.getString("newPinCode");
            }
        }
        this.mPinDialog.setOnPinEnteredListener(this);
        getPreferenceScreen().setPersistent(false);
        this.mRes = getResources();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Phone phone;
        Object obj;
        int simCount = ((TelephonyManager) getContext().getSystemService("phone")).getSimCount();
        if (simCount > 1) {
            View inflate = layoutInflater.inflate(C1715R.layout.icc_lock_tabs, viewGroup, false);
            ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(C1715R.C1718id.prefs_container);
            Utils.prepareCustomPreferencesList(viewGroup, inflate, viewGroup2, false);
            viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup2, bundle));
            this.mTabHost = (TabHost) inflate.findViewById(16908306);
            this.mTabWidget = (TabWidget) inflate.findViewById(16908307);
            this.mListView = (ListView) inflate.findViewById(16908298);
            this.mTabHost.setup();
            this.mTabHost.setOnTabChangedListener(this.mTabListener);
            this.mTabHost.clearAllTabs();
            SubscriptionManager from = SubscriptionManager.from(getContext());
            for (int i = 0; i < simCount; i++) {
                SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = from.getActiveSubscriptionInfoForSimSlotIndex(i);
                TabHost tabHost = this.mTabHost;
                String valueOf = String.valueOf(i);
                if (activeSubscriptionInfoForSimSlotIndex == null) {
                    obj = getContext().getString(C1715R.string.sim_editor_title, new Object[]{Integer.valueOf(i + 1)});
                } else {
                    obj = activeSubscriptionInfoForSimSlotIndex.getDisplayName();
                }
                tabHost.addTab(buildTabSpec(valueOf, String.valueOf(obj)));
            }
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex2 = from.getActiveSubscriptionInfoForSimSlotIndex(0);
            if (activeSubscriptionInfoForSimSlotIndex2 == null) {
                phone = null;
            } else {
                phone = PhoneFactory.getPhone(SubscriptionManager.getPhoneId(activeSubscriptionInfoForSimSlotIndex2.getSubscriptionId()));
            }
            this.mPhone = phone;
            if (bundle != null && bundle.containsKey("currentTab")) {
                this.mTabHost.setCurrentTabByTag(bundle.getString("currentTab"));
            }
            return inflate;
        }
        this.mPhone = PhoneFactory.getDefaultPhone();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePreferences();
    }

    /* access modifiers changed from: private */
    public void updatePreferences() {
        EditPinPreference editPinPreference = this.mPinDialog;
        boolean z = true;
        if (editPinPreference != null) {
            editPinPreference.setEnabled(this.mPhone != null);
        }
        SwitchPreference switchPreference = this.mPinToggle;
        if (switchPreference != null) {
            if (this.mPhone == null) {
                z = false;
            }
            switchPreference.setEnabled(z);
            Phone phone = this.mPhone;
            if (phone != null) {
                this.mPinToggle.setChecked(phone.getIccCard().getIccLockEnabled());
            }
        }
    }

    public void onResume() {
        super.onResume();
        getContext().registerReceiver(this.mSimStateReceiver, new IntentFilter("android.intent.action.SIM_STATE_CHANGED"));
        if (this.mDialogState != 0) {
            showPinDialog();
        } else {
            resetDialogState();
        }
    }

    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(this.mSimStateReceiver);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mPinDialog.isDialogOpen()) {
            bundle.putInt("dialogState", this.mDialogState);
            bundle.putString("dialogPin", this.mPinDialog.getEditText().getText().toString());
            bundle.putString("dialogError", this.mError);
            bundle.putBoolean("enableState", this.mToState);
            int i = this.mDialogState;
            if (i == 3) {
                bundle.putString("oldPinCode", this.mOldPin);
            } else if (i == 4) {
                bundle.putString("oldPinCode", this.mOldPin);
                bundle.putString("newPinCode", this.mNewPin);
            }
        } else {
            super.onSaveInstanceState(bundle);
        }
        TabHost tabHost = this.mTabHost;
        if (tabHost != null) {
            bundle.putString("currentTab", tabHost.getCurrentTabTag());
        }
    }

    private void showPinDialog() {
        if (this.mDialogState != 0) {
            setDialogValues();
            this.mPinDialog.showPinDialog();
            EditText editText = this.mPinDialog.getEditText();
            if (!TextUtils.isEmpty(this.mPin) && editText != null) {
                editText.setSelection(this.mPin.length());
            }
        }
    }

    private void setDialogValues() {
        String str;
        String str2;
        this.mPinDialog.setText(this.mPin);
        int i = this.mDialogState;
        if (i == 1) {
            str = this.mRes.getString(C1715R.string.sim_enter_pin);
            EditPinPreference editPinPreference = this.mPinDialog;
            if (this.mToState) {
                str2 = this.mRes.getString(C1715R.string.sim_enable_sim_lock);
            } else {
                str2 = this.mRes.getString(C1715R.string.sim_disable_sim_lock);
            }
            editPinPreference.setDialogTitle(str2);
        } else if (i == 2) {
            str = this.mRes.getString(C1715R.string.sim_enter_old);
            this.mPinDialog.setDialogTitle(this.mRes.getString(C1715R.string.sim_change_pin));
        } else if (i == 3) {
            str = this.mRes.getString(C1715R.string.sim_enter_new);
            this.mPinDialog.setDialogTitle(this.mRes.getString(C1715R.string.sim_change_pin));
        } else if (i != 4) {
            str = "";
        } else {
            str = this.mRes.getString(C1715R.string.sim_reenter_new);
            this.mPinDialog.setDialogTitle(this.mRes.getString(C1715R.string.sim_change_pin));
        }
        if (this.mError != null) {
            str = this.mError + "\n" + str;
            this.mError = null;
        }
        this.mPinDialog.setDialogMessage(str);
    }

    public void onPinEntered(EditPinPreference editPinPreference, boolean z) {
        if (!z) {
            resetDialogState();
            return;
        }
        this.mPin = editPinPreference.getText();
        if (!reasonablePin(this.mPin)) {
            this.mError = this.mRes.getString(C1715R.string.sim_bad_pin);
            showPinDialog();
            return;
        }
        int i = this.mDialogState;
        if (i == 1) {
            tryChangeIccLockState();
        } else if (i == 2) {
            this.mOldPin = this.mPin;
            this.mDialogState = 3;
            this.mError = null;
            this.mPin = null;
            showPinDialog();
        } else if (i == 3) {
            this.mNewPin = this.mPin;
            this.mDialogState = 4;
            this.mPin = null;
            showPinDialog();
        } else if (i == 4) {
            if (!this.mPin.equals(this.mNewPin)) {
                this.mError = this.mRes.getString(C1715R.string.sim_pins_dont_match);
                this.mDialogState = 3;
                this.mPin = null;
                showPinDialog();
                return;
            }
            this.mError = null;
            tryChangePin();
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        SwitchPreference switchPreference = this.mPinToggle;
        if (preference == switchPreference) {
            this.mToState = switchPreference.isChecked();
            this.mPinToggle.setChecked(!this.mToState);
            this.mDialogState = 1;
            showPinDialog();
        } else if (preference == this.mPinDialog) {
            this.mDialogState = 2;
            return false;
        }
        return true;
    }

    private void tryChangeIccLockState() {
        this.mPhone.getIccCard().setIccLockEnabled(this.mToState, this.mPin, Message.obtain(this.mHandler, 100));
        this.mPinToggle.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void iccLockChanged(boolean z, int i, Throwable th) {
        if (z) {
            this.mPinToggle.setChecked(this.mToState);
        } else if (th instanceof CommandException) {
            if (((CommandException) th).getCommandError() == CommandException.Error.PASSWORD_INCORRECT) {
                createCustomTextToast(getPinPasswordErrorMessage(i));
            } else if (this.mToState) {
                Toast.makeText(getContext(), this.mRes.getString(C1715R.string.sim_pin_enable_failed), 1).show();
            } else {
                Toast.makeText(getContext(), this.mRes.getString(C1715R.string.sim_pin_disable_failed), 1).show();
            }
        }
        this.mPinToggle.setEnabled(true);
        resetDialogState();
    }

    private void createCustomTextToast(CharSequence charSequence) {
        final View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(17367331, (ViewGroup) null);
        ((TextView) inflate.findViewById(16908299)).setText(charSequence);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        int absoluteGravity = Gravity.getAbsoluteGravity(getContext().getResources().getInteger(17694928), inflate.getContext().getResources().getConfiguration().getLayoutDirection());
        layoutParams.gravity = absoluteGravity;
        if ((absoluteGravity & 7) == 7) {
            layoutParams.horizontalWeight = 1.0f;
        }
        if ((absoluteGravity & 112) == 112) {
            layoutParams.verticalWeight = 1.0f;
        }
        layoutParams.y = getContext().getResources().getDimensionPixelSize(17105491);
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        layoutParams.type = 2014;
        layoutParams.setTitle(charSequence);
        layoutParams.flags = 152;
        final WindowManager windowManager = (WindowManager) getSystemService("window");
        windowManager.addView(inflate, layoutParams);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                windowManager.removeViewImmediate(inflate);
            }
        }, 7000);
    }

    /* access modifiers changed from: private */
    public void iccPinChanged(boolean z, int i) {
        if (!z) {
            createCustomTextToast(getPinPasswordErrorMessage(i));
        } else {
            Toast.makeText(getContext(), this.mRes.getString(C1715R.string.sim_change_succeeded), 0).show();
        }
        resetDialogState();
    }

    private void tryChangePin() {
        this.mPhone.getIccCard().changeIccLockPassword(this.mOldPin, this.mNewPin, Message.obtain(this.mHandler, 101));
    }

    private String getPinPasswordErrorMessage(int i) {
        if (i == 0) {
            return this.mRes.getString(C1715R.string.wrong_pin_code_pukked);
        }
        if (i <= 0) {
            return this.mRes.getString(C1715R.string.pin_failed);
        }
        return this.mRes.getQuantityString(C1715R.plurals.wrong_pin_code, i, new Object[]{Integer.valueOf(i)});
    }

    private boolean reasonablePin(String str) {
        return str != null && str.length() >= 4 && str.length() <= 8;
    }

    private void resetDialogState() {
        this.mError = null;
        this.mDialogState = 2;
        this.mPin = "";
        setDialogValues();
        this.mDialogState = 0;
    }

    private TabHost.TabSpec buildTabSpec(String str, String str2) {
        return this.mTabHost.newTabSpec(str).setIndicator(str2).setContent(this.mEmptyTabContent);
    }
}
