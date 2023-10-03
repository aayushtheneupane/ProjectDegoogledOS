package com.android.dialer.common.preference;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.common.Assert;

public class SwitchPreferenceWithClickableSummary extends SwitchPreference {
    /* access modifiers changed from: private */
    public final String urlToOpen;

    public SwitchPreferenceWithClickableSummary(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.urlToOpen = String.valueOf(context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceWithClickableSummary).getText(0));
    }

    /* access modifiers changed from: protected */
    public void onBindView(final View view) {
        super.onBindView(view);
        Assert.checkArgument(this.urlToOpen != null, "must have a urlToOpen attribute when using SwitchPreferenceWithClickableSummary", new Object[0]);
        view.findViewById(16908304).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContextCompat.startActivity(view.getContext(), new Intent("android.intent.action.VIEW", Uri.parse(SwitchPreferenceWithClickableSummary.this.urlToOpen)), (Bundle) null);
            }
        });
    }

    /* access modifiers changed from: protected */
    public View onCreateView(ViewGroup viewGroup) {
        return super.onCreateView(viewGroup);
    }

    public SwitchPreferenceWithClickableSummary(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, i);
        this.urlToOpen = String.valueOf(context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceWithClickableSummary).getText(0));
    }

    public SwitchPreferenceWithClickableSummary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.urlToOpen = String.valueOf(context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceWithClickableSummary).getText(0));
    }

    public SwitchPreferenceWithClickableSummary(Context context) {
        this(context, (AttributeSet) null);
    }
}
