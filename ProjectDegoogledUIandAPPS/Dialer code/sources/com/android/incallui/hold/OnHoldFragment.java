package com.android.incallui.hold;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.incallui.incall.protocol.SecondaryInfo;

public class OnHoldFragment extends Fragment {
    private boolean padTopInset = true;
    /* access modifiers changed from: private */
    public int topInset;

    /* access modifiers changed from: private */
    public void applyInset() {
        if (getView() != null) {
            int i = this.padTopInset ? this.topInset : 0;
            if (i != getView().getPaddingTop()) {
                TransitionManager.beginDelayedTransition((ViewGroup) getView().getParent());
                getView().setPadding(0, i, 0, 0);
            }
        }
    }

    public static OnHoldFragment newInstance(SecondaryInfo secondaryInfo) {
        OnHoldFragment onHoldFragment = new OnHoldFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("info", secondaryInfo);
        onHoldFragment.setArguments(bundle);
        return onHoldFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence charSequence;
        View inflate = layoutInflater.inflate(R.layout.incall_on_hold_banner, viewGroup, false);
        SecondaryInfo secondaryInfo = (SecondaryInfo) getArguments().getParcelable("info");
        Assert.isNotNull(secondaryInfo);
        SecondaryInfo secondaryInfo2 = secondaryInfo;
        TextView textView = (TextView) inflate.findViewById(R.id.hold_contact_name);
        if (secondaryInfo2.nameIsNumber()) {
            charSequence = PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(secondaryInfo2.name(), TextDirectionHeuristics.LTR));
        } else {
            charSequence = secondaryInfo2.name();
        }
        textView.setText(charSequence);
        ((ImageView) inflate.findViewById(R.id.hold_phone_icon)).setImageResource(secondaryInfo2.isVideoCall() ? R.drawable.quantum_ic_videocam_white_18 : R.drawable.quantum_ic_phone_paused_vd_theme_24);
        inflate.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                int unused = OnHoldFragment.this.topInset = view.getRootWindowInsets().getSystemWindowInsetTop();
                OnHoldFragment.this.applyInset();
            }

            public void onViewDetachedFromWindow(View view) {
            }
        });
        return inflate;
    }

    public void setPadTopInset(boolean z) {
        this.padTopInset = z;
        applyInset();
    }
}
