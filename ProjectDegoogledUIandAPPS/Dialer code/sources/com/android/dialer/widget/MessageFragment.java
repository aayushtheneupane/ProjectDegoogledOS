package com.android.dialer.widget;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;

public class MessageFragment extends Fragment implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {
    private int charLimit;
    private EditText customMessage;
    private TextView remainingChar;
    private ImageView sendMessage;
    private View sendMessageContainer;

    public static class Builder {
        /* access modifiers changed from: private */
        public int charLimit = -1;
        /* access modifiers changed from: private */
        public String[] messages;
        /* access modifiers changed from: private */
        public boolean showSendIcon;

        public MessageFragment build() {
            MessageFragment messageFragment = new MessageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("char_limit", this.charLimit);
            bundle.putBoolean("show_send_icon", this.showSendIcon);
            bundle.putStringArray("message_list", this.messages);
            messageFragment.setArguments(bundle);
            return messageFragment;
        }

        public Builder setCharLimit(int i) {
            this.charLimit = i;
            return this;
        }

        public Builder setMessages(String... strArr) {
            Assert.checkArgument(strArr.length > 0 && strArr.length <= 3);
            this.messages = strArr;
            return this;
        }

        public Builder showSendIcon() {
            this.showSendIcon = true;
            return this;
        }
    }

    public interface Listener {
        void onMessageFragmentAfterTextChange(String str);

        void onMessageFragmentSendMessage(String str);
    }

    public static Builder builder() {
        return new Builder();
    }

    private Listener getListener() {
        return (Listener) FragmentUtils.getParentUnsafe((Fragment) this, Listener.class);
    }

    public void afterTextChanged(Editable editable) {
        int i = this.charLimit;
        if (i != -1) {
            this.remainingChar.setText(Integer.toString(i - editable.length()));
        }
        getListener().onMessageFragmentAfterTextChange(editable.toString());
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onClick(View view) {
        if (view == this.sendMessageContainer) {
            if (!TextUtils.isEmpty(this.customMessage.getText())) {
                getListener().onMessageFragmentSendMessage(this.customMessage.getText().toString());
            }
        } else if (view.getId() == R.id.selectable_text_view) {
            this.customMessage.setText(((TextView) view).getText());
            EditText editText = this.customMessage;
            editText.setSelection(editText.getText().length());
        } else {
            throw new AssertionError("Unknown view clicked");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_message, viewGroup, false);
        this.sendMessage = (ImageView) inflate.findViewById(R.id.send_message);
        this.sendMessageContainer = inflate.findViewById(R.id.count_and_send_container);
        if (getArguments().getBoolean("show_send_icon", false)) {
            this.sendMessage.setVisibility(0);
            this.sendMessage.setEnabled(false);
            this.sendMessageContainer.setOnClickListener(this);
        }
        this.customMessage = (EditText) inflate.findViewById(R.id.custom_message);
        this.customMessage.addTextChangedListener(this);
        this.customMessage.setOnEditorActionListener(this);
        this.charLimit = getArguments().getInt("char_limit", -1);
        if (this.charLimit != -1) {
            this.remainingChar = (TextView) inflate.findViewById(R.id.remaining_characters);
            this.remainingChar.setVisibility(0);
            this.remainingChar = (TextView) inflate.findViewById(R.id.remaining_characters);
            this.remainingChar.setText(Integer.toString(this.charLimit));
            this.customMessage.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.charLimit)});
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.message_container);
        for (String text : getArguments().getStringArray("message_list")) {
            TextView textView = (TextView) layoutInflater.inflate(R.layout.selectable_text_view, (ViewGroup) null);
            textView.setOnClickListener(this);
            textView.setText(text);
            linearLayout.addView(textView);
        }
        return inflate;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        EditText editText = this.customMessage;
        String str = null;
        if (TextUtils.isEmpty(editText == null ? null : editText.getText().toString())) {
            return true;
        }
        Listener listener = getListener();
        EditText editText2 = this.customMessage;
        if (editText2 != null) {
            str = editText2.getText().toString();
        }
        listener.onMessageFragmentSendMessage(str);
        return true;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.sendMessage.setEnabled(charSequence.length() > 0);
    }
}
