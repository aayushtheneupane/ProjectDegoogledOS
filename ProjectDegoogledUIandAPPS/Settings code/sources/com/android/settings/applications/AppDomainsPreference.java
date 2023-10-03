package com.android.settings.applications;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.settings.accessibility.ListDialogPreference;
import com.havoc.config.center.C1715R;

public class AppDomainsPreference extends ListDialogPreference {
    private int mNumEntries;

    public AppDomainsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(C1715R.layout.app_domains_dialog);
        setListItemLayoutResource(C1715R.layout.app_domains_item);
    }

    public void setTitles(CharSequence[] charSequenceArr) {
        this.mNumEntries = charSequenceArr != null ? charSequenceArr.length : 0;
        super.setTitles(charSequenceArr);
    }

    public CharSequence getSummary() {
        Context context = getContext();
        if (this.mNumEntries == 0) {
            return context.getString(C1715R.string.domain_urls_summary_none);
        }
        return context.getString(this.mNumEntries == 1 ? C1715R.string.domain_urls_summary_one : C1715R.string.domain_urls_summary_some, new Object[]{super.getSummary()});
    }

    /* access modifiers changed from: protected */
    public void onBindListItem(View view, int i) {
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(C1715R.C1718id.domain_name)).setText(titleAt);
        }
    }
}
