package com.android.settings.tts;

import android.content.Context;
import android.content.DialogInterface;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class TtsEnginePreference extends Preference {
    private final TextToSpeech.EngineInfo mEngineInfo;
    private volatile boolean mPreventRadioButtonCallbacks;
    private RadioButton mRadioButton;
    private final CompoundButton.OnCheckedChangeListener mRadioChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            TtsEnginePreference.this.onRadioButtonClicked(compoundButton, z);
        }
    };
    private final RadioButtonGroupState mSharedState;

    public interface RadioButtonGroupState {
        Checkable getCurrentChecked();

        String getCurrentKey();

        void setCurrentChecked(Checkable checkable);

        void setCurrentKey(String str);
    }

    public TtsEnginePreference(Context context, TextToSpeech.EngineInfo engineInfo, RadioButtonGroupState radioButtonGroupState) {
        super(context);
        setWidgetLayoutResource(C1715R.layout.preference_widget_radiobutton);
        setLayoutResource(C1715R.layout.preference_radio);
        setIconSpaceReserved(false);
        this.mSharedState = radioButtonGroupState;
        this.mEngineInfo = engineInfo;
        this.mPreventRadioButtonCallbacks = false;
        setKey(this.mEngineInfo.name);
        setTitle((CharSequence) this.mEngineInfo.label);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mSharedState != null) {
            RadioButton radioButton = (RadioButton) preferenceViewHolder.itemView.findViewById(16908289);
            radioButton.setOnCheckedChangeListener(this.mRadioChangeListener);
            boolean equals = getKey().equals(this.mSharedState.getCurrentKey());
            if (equals) {
                this.mSharedState.setCurrentChecked(radioButton);
            }
            this.mPreventRadioButtonCallbacks = true;
            radioButton.setChecked(equals);
            this.mPreventRadioButtonCallbacks = false;
            this.mRadioButton = radioButton;
            return;
        }
        throw new IllegalStateException("Call to getView() before a call tosetSharedState()");
    }

    public void onClick() {
        this.mRadioButton.setChecked(true);
    }

    private boolean shouldDisplayDataAlert() {
        return !this.mEngineInfo.system;
    }

    private void displayDataAlert(DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Log.i("TtsEnginePreference", "Displaying data alert for :" + this.mEngineInfo.name);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(17039380);
        builder.setMessage((CharSequence) getContext().getString(C1715R.string.tts_engine_security_warning, new Object[]{this.mEngineInfo.label}));
        builder.setCancelable(true);
        builder.setPositiveButton(17039370, onClickListener);
        builder.setNegativeButton(17039360, onClickListener2);
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void onRadioButtonClicked(final CompoundButton compoundButton, boolean z) {
        if (!this.mPreventRadioButtonCallbacks && this.mSharedState.getCurrentChecked() != compoundButton && z) {
            if (shouldDisplayDataAlert()) {
                displayDataAlert(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TtsEnginePreference.this.makeCurrentEngine(compoundButton);
                    }
                }, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        compoundButton.setChecked(false);
                    }
                });
            } else {
                makeCurrentEngine(compoundButton);
            }
        }
    }

    /* access modifiers changed from: private */
    public void makeCurrentEngine(Checkable checkable) {
        if (this.mSharedState.getCurrentChecked() != null) {
            this.mSharedState.getCurrentChecked().setChecked(false);
        }
        this.mSharedState.setCurrentChecked(checkable);
        this.mSharedState.setCurrentKey(getKey());
        callChangeListener(this.mSharedState.getCurrentKey());
    }
}
