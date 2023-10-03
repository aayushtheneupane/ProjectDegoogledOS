package com.android.incallui.answer.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.p000v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.incallui.incalluilock.InCallUiLock;
import java.util.ArrayList;

public class SmsBottomSheetFragment extends BottomSheetDialogFragment {
    private InCallUiLock inCallUiLock;

    public interface SmsSheetHolder {
    }

    private TextView newTextViewItem(final CharSequence charSequence) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), 2131886532);
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(new int[]{16843534});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        TextView textView = new TextView(contextThemeWrapper);
        textView.setText(charSequence == null ? getString(R.string.call_incoming_message_custom) : charSequence);
        int i = (int) (16.0f * contextThemeWrapper.getResources().getDisplayMetrics().density);
        textView.setPadding(i, i, i, i);
        textView.setBackground(drawable);
        textView.setTextColor(contextThemeWrapper.getColor(R.color.blue_grey_100));
        textView.setTextAppearance(2131886463);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) SmsBottomSheetFragment.this, SmsSheetHolder.class)).smsSelected(charSequence);
                SmsBottomSheetFragment.this.dismiss();
            }
        });
        return textView;
    }

    public int getTheme() {
        return 2131886532;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentUtils.checkParent(this, SmsSheetHolder.class);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LogUtil.m9i("SmsBottomSheetFragment.onCreateDialog", (String) null, new Object[0]);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), getTheme());
        bottomSheetDialog.getWindow().addFlags(524288);
        this.inCallUiLock = ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) this, SmsSheetHolder.class)).acquireInCallUiLock("SmsBottomSheetFragment");
        return bottomSheetDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        ArrayList<CharSequence> charSequenceArrayList = getArguments().getCharSequenceArrayList(SelectPhoneAccountDialogFragment.ARG_OPTIONS);
        if (charSequenceArrayList != null) {
            for (CharSequence newTextViewItem : charSequenceArrayList) {
                linearLayout.addView(newTextViewItem(newTextViewItem));
            }
        }
        linearLayout.addView(newTextViewItem((CharSequence) null));
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        return linearLayout;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) this, SmsSheetHolder.class)).smsDismissed();
        this.inCallUiLock.release();
    }
}
