package com.android.dialer.voicemail.settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telecom.PhoneAccountHandle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.PinChanger;
import com.android.voicemail.VoicemailComponent;
import java.lang.ref.WeakReference;

@TargetApi(26)
public class VoicemailChangePinActivity extends Activity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {
    private Button cancelButton;
    private DialerExecutor<ChangePinParams> changePinExecutor;
    /* access modifiers changed from: private */
    public TextView errorText;
    /* access modifiers changed from: private */
    public String firstPin;
    private Handler handler = new ChangePinHandler(new WeakReference(this), (C06111) null);
    /* access modifiers changed from: private */
    public TextView headerText;
    /* access modifiers changed from: private */
    public TextView hintText;
    /* access modifiers changed from: private */
    public Button nextButton;
    /* access modifiers changed from: private */
    public String oldPin;
    private PhoneAccountHandle phoneAccountHandle;
    /* access modifiers changed from: private */
    public PinChanger pinChanger;
    /* access modifiers changed from: private */
    public EditText pinEntry;
    /* access modifiers changed from: private */
    public int pinMaxLength;
    /* access modifiers changed from: private */
    public int pinMinLength;
    private ProgressDialog progressDialog;
    /* access modifiers changed from: private */
    public State uiState = State.Initial;

    private static class ChangePinHandler extends Handler {
        private final WeakReference<VoicemailChangePinActivity> activityWeakReference;

        /* synthetic */ ChangePinHandler(WeakReference weakReference, C06111 r2) {
            this.activityWeakReference = weakReference;
        }

        public void handleMessage(Message message) {
            VoicemailChangePinActivity voicemailChangePinActivity = (VoicemailChangePinActivity) this.activityWeakReference.get();
            if (voicemailChangePinActivity != null && message.what == 1) {
                voicemailChangePinActivity.uiState.handleResult(voicemailChangePinActivity, message.arg1);
            }
        }
    }

    private static class ChangePinParams {
        String newPin;
        String oldPin;
        PinChanger pinChanger;

        private ChangePinParams() {
        }

        /* synthetic */ ChangePinParams(C06111 r1) {
        }
    }

    private static class ChangePinWorker implements DialerExecutor.Worker<ChangePinParams, Integer> {
        /* synthetic */ ChangePinWorker(C06111 r1) {
        }

        public Object doInBackground(Object obj) throws Throwable {
            ChangePinParams changePinParams = (ChangePinParams) obj;
            return Integer.valueOf(changePinParams.pinChanger.changePin(changePinParams.oldPin, changePinParams.newPin));
        }
    }

    private enum State {
        Initial,
        EnterOldPin {
            public void handleNext(VoicemailChangePinActivity voicemailChangePinActivity) {
                String unused = voicemailChangePinActivity.oldPin = voicemailChangePinActivity.pinEntry.getText().toString();
                VoicemailChangePinActivity.access$900(voicemailChangePinActivity);
            }

            public void handleResult(VoicemailChangePinActivity voicemailChangePinActivity, int i) {
                if (i == 0) {
                    voicemailChangePinActivity.updateState(State.EnterNewPin);
                    return;
                }
                voicemailChangePinActivity.showError(voicemailChangePinActivity.getChangePinResultMessage(i), (DialogInterface.OnDismissListener) null);
                voicemailChangePinActivity.pinEntry.setText("");
            }

            public void onEnter(VoicemailChangePinActivity voicemailChangePinActivity) {
                VoicemailChangePinActivity.access$200(voicemailChangePinActivity, R.string.change_pin_enter_old_pin_header);
                voicemailChangePinActivity.hintText.setText(R.string.change_pin_enter_old_pin_hint);
                voicemailChangePinActivity.nextButton.setText(R.string.change_pin_continue_label);
                voicemailChangePinActivity.errorText.setText((CharSequence) null);
            }

            public void onInputChanged(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.nextButton.setEnabled(voicemailChangePinActivity.pinEntry.getText().toString().length() > 0);
            }
        },
        VerifyOldPin {
            public void handleResult(final VoicemailChangePinActivity voicemailChangePinActivity, int i) {
                if (i == 0) {
                    voicemailChangePinActivity.updateState(State.EnterNewPin);
                } else if (i == 6) {
                    voicemailChangePinActivity.getWindow().setSoftInputMode(3);
                    voicemailChangePinActivity.showError(voicemailChangePinActivity.getString(R.string.change_pin_system_error), new DialogInterface.OnDismissListener(this) {
                        public void onDismiss(DialogInterface dialogInterface) {
                            voicemailChangePinActivity.finish();
                        }
                    });
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("invalid default old PIN: ");
                    outline13.append(voicemailChangePinActivity.getChangePinResultMessage(i));
                    LogUtil.m8e("VmChangePinActivity", outline13.toString(), new Object[0]);
                    voicemailChangePinActivity.pinChanger.setScrambledPin((String) null);
                    voicemailChangePinActivity.updateState(State.EnterOldPin);
                }
            }

            public void onEnter(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.findViewById(16908290).setVisibility(4);
                VoicemailChangePinActivity.access$900(voicemailChangePinActivity);
            }

            public void onLeave(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.findViewById(16908290).setVisibility(0);
            }
        },
        EnterNewPin {
            public void handleNext(VoicemailChangePinActivity voicemailChangePinActivity) {
                CharSequence access$1900 = VoicemailChangePinActivity.access$1900(voicemailChangePinActivity, voicemailChangePinActivity.pinEntry.getText().toString());
                if (access$1900 != null) {
                    voicemailChangePinActivity.showError(access$1900, (DialogInterface.OnDismissListener) null);
                    return;
                }
                String unused = voicemailChangePinActivity.firstPin = voicemailChangePinActivity.pinEntry.getText().toString();
                voicemailChangePinActivity.updateState(State.ConfirmNewPin);
            }

            public void onEnter(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.headerText.setText(R.string.change_pin_enter_new_pin_header);
                voicemailChangePinActivity.nextButton.setText(R.string.change_pin_continue_label);
                voicemailChangePinActivity.hintText.setText(voicemailChangePinActivity.getString(R.string.change_pin_enter_new_pin_hint, new Object[]{Integer.valueOf(voicemailChangePinActivity.pinMinLength), Integer.valueOf(voicemailChangePinActivity.pinMaxLength)}));
            }

            public void onInputChanged(VoicemailChangePinActivity voicemailChangePinActivity) {
                String access$600 = voicemailChangePinActivity.pinEntry.getText().toString();
                if (access$600.length() == 0) {
                    voicemailChangePinActivity.nextButton.setEnabled(false);
                    return;
                }
                CharSequence access$1900 = VoicemailChangePinActivity.access$1900(voicemailChangePinActivity, access$600);
                if (access$1900 != null) {
                    voicemailChangePinActivity.errorText.setText(access$1900);
                    voicemailChangePinActivity.nextButton.setEnabled(false);
                    return;
                }
                voicemailChangePinActivity.errorText.setText((CharSequence) null);
                voicemailChangePinActivity.nextButton.setEnabled(true);
            }
        },
        ConfirmNewPin {
            public void handleNext(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.processPinChange(voicemailChangePinActivity.oldPin, voicemailChangePinActivity.firstPin);
            }

            public void handleResult(VoicemailChangePinActivity voicemailChangePinActivity, int i) {
                if (i == 0) {
                    voicemailChangePinActivity.pinChanger.setScrambledPin((String) null);
                    voicemailChangePinActivity.finish();
                    ((LoggingBindingsStub) Logger.get(voicemailChangePinActivity)).logImpression(DialerImpression$Type.VVM_CHANGE_PIN_COMPLETED);
                    Toast.makeText(voicemailChangePinActivity, voicemailChangePinActivity.getString(R.string.change_pin_succeeded), 0).show();
                    return;
                }
                CharSequence access$1100 = voicemailChangePinActivity.getChangePinResultMessage(i);
                LogUtil.m9i("VmChangePinActivity", GeneratedOutlineSupport.outline6("Change PIN failed: ", access$1100), new Object[0]);
                voicemailChangePinActivity.showError(access$1100, (DialogInterface.OnDismissListener) null);
                if (i == 4) {
                    voicemailChangePinActivity.updateState(State.EnterOldPin);
                } else {
                    voicemailChangePinActivity.updateState(State.EnterNewPin);
                }
            }

            public void onEnter(VoicemailChangePinActivity voicemailChangePinActivity) {
                voicemailChangePinActivity.headerText.setText(R.string.change_pin_confirm_pin_header);
                voicemailChangePinActivity.hintText.setText((CharSequence) null);
                voicemailChangePinActivity.nextButton.setText(R.string.change_pin_ok_label);
            }

            public void onInputChanged(VoicemailChangePinActivity voicemailChangePinActivity) {
                if (voicemailChangePinActivity.pinEntry.getText().toString().length() == 0) {
                    voicemailChangePinActivity.nextButton.setEnabled(false);
                } else if (voicemailChangePinActivity.pinEntry.getText().toString().equals(voicemailChangePinActivity.firstPin)) {
                    voicemailChangePinActivity.nextButton.setEnabled(true);
                    voicemailChangePinActivity.errorText.setText((CharSequence) null);
                } else {
                    voicemailChangePinActivity.nextButton.setEnabled(false);
                    voicemailChangePinActivity.errorText.setText(R.string.change_pin_confirm_pins_dont_match);
                }
            }
        };

        public void handleNext(VoicemailChangePinActivity voicemailChangePinActivity) {
        }

        public void handleResult(VoicemailChangePinActivity voicemailChangePinActivity, int i) {
        }

        public void onEnter(VoicemailChangePinActivity voicemailChangePinActivity) {
        }

        public void onInputChanged(VoicemailChangePinActivity voicemailChangePinActivity) {
        }

        public void onLeave(VoicemailChangePinActivity voicemailChangePinActivity) {
        }
    }

    static /* synthetic */ CharSequence access$1900(VoicemailChangePinActivity voicemailChangePinActivity, String str) {
        if ((voicemailChangePinActivity.pinMinLength != 0 || voicemailChangePinActivity.pinMaxLength != 0) && str.length() < voicemailChangePinActivity.pinMinLength) {
            return voicemailChangePinActivity.getString(R.string.vm_change_pin_error_too_short);
        }
        return null;
    }

    static /* synthetic */ void access$200(VoicemailChangePinActivity voicemailChangePinActivity, int i) {
        voicemailChangePinActivity.headerText.setText(i);
        voicemailChangePinActivity.pinEntry.setContentDescription(voicemailChangePinActivity.headerText.getText());
    }

    static /* synthetic */ void access$900(VoicemailChangePinActivity voicemailChangePinActivity) {
        String str = voicemailChangePinActivity.oldPin;
        voicemailChangePinActivity.processPinChange(str, str);
    }

    /* access modifiers changed from: private */
    public CharSequence getChangePinResultMessage(int i) {
        switch (i) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                return getString(R.string.vm_change_pin_error_too_short);
            case 2:
                return getString(R.string.vm_change_pin_error_too_long);
            case 3:
                return getString(R.string.vm_change_pin_error_too_weak);
            case 4:
                return getString(R.string.vm_change_pin_error_mismatch);
            case 5:
                return getString(R.string.vm_change_pin_error_invalid);
            case 6:
                return getString(R.string.vm_change_pin_error_system_error);
            default:
                LogUtil.m8e("VmChangePinActivity", GeneratedOutlineSupport.outline5("Unexpected ChangePinResult ", i), new Object[0]);
                return null;
        }
    }

    public static boolean isPinScrambled(Context context, PhoneAccountHandle phoneAccountHandle2) {
        return VoicemailComponent.get(context).getVoicemailClient().createPinChanger(context, phoneAccountHandle2).getScrambledPin() != null;
    }

    /* access modifiers changed from: private */
    public void processPinChange(String str, String str2) {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage(getString(R.string.vm_change_pin_progress_message));
        this.progressDialog.show();
        ChangePinParams changePinParams = new ChangePinParams((C06111) null);
        changePinParams.pinChanger = this.pinChanger;
        changePinParams.oldPin = str;
        changePinParams.newPin = str2;
        this.changePinExecutor.executeSerial(changePinParams);
    }

    /* access modifiers changed from: private */
    public void sendResult(int i) {
        LogUtil.m9i("VmChangePinActivity", GeneratedOutlineSupport.outline5("Change PIN result: ", i), new Object[0]);
        if (!this.progressDialog.isShowing() || isDestroyed() || isFinishing()) {
            LogUtil.m9i("VmChangePinActivity", "Dialog not visible, not dismissing", new Object[0]);
        } else {
            this.progressDialog.dismiss();
        }
        this.handler.obtainMessage(1, i, 0).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void showError(CharSequence charSequence, DialogInterface.OnDismissListener onDismissListener) {
        new AlertDialog.Builder(this).setMessage(charSequence).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setOnDismissListener(onDismissListener).show();
    }

    /* access modifiers changed from: private */
    public void updateState(State state) {
        State state2 = this.uiState;
        this.uiState = state;
        if (state2 != state) {
            state2.onLeave(this);
            this.pinEntry.setText("");
            this.uiState.onEnter(this);
        }
        this.uiState.onInputChanged(this);
    }

    public void afterTextChanged(Editable editable) {
        this.uiState.onInputChanged(this);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void handleNext() {
        if (this.pinEntry.length() != 0) {
            this.uiState.handleNext(this);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$VoicemailChangePinActivity(Throwable th) {
        sendResult(6);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.next_button) {
            handleNext();
        } else if (view.getId() == R.id.cancel_button) {
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.phoneAccountHandle = (PhoneAccountHandle) getIntent().getParcelableExtra("phone_account_handle");
        this.pinChanger = VoicemailComponent.get(this).getVoicemailClient().createPinChanger(getApplicationContext(), this.phoneAccountHandle);
        setContentView(R.layout.voicemail_change_pin);
        setTitle(R.string.change_pin_title);
        PinChanger.PinSpecification pinSpecification = this.pinChanger.getPinSpecification();
        this.pinMinLength = pinSpecification.minLength;
        this.pinMaxLength = pinSpecification.maxLength;
        View findViewById = findViewById(16908290);
        this.cancelButton = (Button) findViewById.findViewById(R.id.cancel_button);
        this.cancelButton.setOnClickListener(this);
        this.nextButton = (Button) findViewById.findViewById(R.id.next_button);
        this.nextButton.setOnClickListener(this);
        this.pinEntry = (EditText) findViewById.findViewById(R.id.pin_entry);
        this.pinEntry.setOnEditorActionListener(this);
        this.pinEntry.addTextChangedListener(this);
        int i = this.pinMaxLength;
        if (i != 0) {
            this.pinEntry.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
        }
        this.headerText = (TextView) findViewById.findViewById(R.id.headerText);
        this.hintText = (TextView) findViewById.findViewById(R.id.hintText);
        this.errorText = (TextView) findViewById.findViewById(R.id.errorText);
        this.changePinExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "changePin", new ChangePinWorker((C06111) null)).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                VoicemailChangePinActivity.this.sendResult(((Integer) obj).intValue());
            }
        }).onFailure(new DialerExecutor.FailureListener() {
            public final void onFailure(Throwable th) {
                VoicemailChangePinActivity.this.lambda$onCreate$0$VoicemailChangePinActivity(th);
            }
        }).build();
        if (isPinScrambled(this, this.phoneAccountHandle)) {
            this.oldPin = this.pinChanger.getScrambledPin();
            updateState(State.VerifyOldPin);
            return;
        }
        updateState(State.EnterOldPin);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (!this.nextButton.isEnabled()) {
            return true;
        }
        if (i != 0 && i != 6 && i != 5) {
            return false;
        }
        handleNext();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onResume() {
        super.onResume();
        updateState(this.uiState);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
