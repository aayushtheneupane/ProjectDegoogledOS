package com.android.dialer.callcomposer;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class MessageComposerFragment extends CallComposerFragment implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {
    /* access modifiers changed from: private */
    public int charLimit;
    private EditText customMessage;

    public void afterTextChanged(Editable editable) {
        getListener().composeCall(this);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void clearComposer() {
        this.customMessage.getText().clear();
    }

    public String getMessage() {
        EditText editText = this.customMessage;
        if (editText == null) {
            return null;
        }
        return editText.getText().toString();
    }

    public void onClick(View view) {
        this.customMessage.setText(((TextView) view).getText());
        EditText editText = this.customMessage;
        editText.setSelection(editText.getText().length());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.charLimit = getArguments().getInt("char_limit", -1);
        View inflate = layoutInflater.inflate(R.layout.fragment_message_composer, viewGroup, false);
        this.customMessage = (EditText) inflate.findViewById(R.id.custom_message);
        ((TextView) inflate.findViewById(R.id.message_urgent)).setOnClickListener(this);
        this.customMessage.addTextChangedListener(this);
        this.customMessage.setOnEditorActionListener(this);
        if (this.charLimit != -1) {
            final TextView textView = (TextView) inflate.findViewById(R.id.remaining_characters);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("");
            outline13.append(this.charLimit);
            textView.setText(outline13.toString());
            this.customMessage.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.charLimit)});
            this.customMessage.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                    TextView textView = textView;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("");
                    outline13.append(MessageComposerFragment.this.charLimit - editable.length());
                    textView.setText(outline13.toString());
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }
            });
        }
        inflate.findViewById(R.id.message_chat).setOnClickListener(this);
        inflate.findViewById(R.id.message_question).setOnClickListener(this);
        return inflate;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (getMessage() == null) {
            return false;
        }
        getListener().sendAndCall();
        return true;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean shouldHide() {
        return TextUtils.isEmpty(getMessage());
    }
}
