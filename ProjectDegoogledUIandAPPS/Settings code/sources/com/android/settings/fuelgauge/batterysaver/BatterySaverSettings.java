package com.android.settings.fuelgauge.batterysaver;

import android.content.Context;
import android.provider.SearchIndexableResource;
import android.text.Annotation;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.widget.FooterPreference;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class BatterySaverSettings extends DashboardFragment {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.battery_saver_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };
    private SpannableStringBuilder mFooterText;
    private String mHelpUri;

    public int getHelpResource() {
        return C1715R.string.help_url_battery_saver_settings;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "BatterySaverSettings";
    }

    public int getMetricsCategory() {
        return 52;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.battery_saver_settings;
    }

    public void onStart() {
        super.onStart();
        setupFooter();
    }

    /* access modifiers changed from: package-private */
    public void setupFooter() {
        this.mFooterText = new SpannableStringBuilder(getText(17039634));
        this.mHelpUri = getString(C1715R.string.help_url_battery_saver_settings);
        if (!TextUtils.isEmpty(this.mHelpUri)) {
            addHelpLink();
        }
    }

    /* access modifiers changed from: package-private */
    public void addHelpLink() {
        FooterPreference footerPreference = (FooterPreference) getPreferenceScreen().findPreference("footer_preference");
        if (footerPreference != null) {
            SupportPageLearnMoreSpan.linkify(this.mFooterText, this, this.mHelpUri);
            footerPreference.setTitle((CharSequence) this.mFooterText);
        }
    }

    public static class SupportPageLearnMoreSpan extends URLSpan {
        private final Fragment mFragment;
        private final String mUriString;

        public SupportPageLearnMoreSpan(Fragment fragment, String str) {
            super("");
            this.mFragment = fragment;
            this.mUriString = str;
        }

        public void onClick(View view) {
            Fragment fragment = this.mFragment;
            if (fragment != null) {
                fragment.startActivityForResult(HelpUtils.getHelpIntent(fragment.getContext(), this.mUriString, ""), 0);
            }
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
        }

        public static CharSequence linkify(Spannable spannable, Fragment fragment, String str) {
            for (Annotation annotation : (Annotation[]) spannable.getSpans(0, spannable.length(), Annotation.class)) {
                int spanStart = spannable.getSpanStart(annotation);
                int spanEnd = spannable.getSpanEnd(annotation);
                if ("url".equals(annotation.getValue())) {
                    SupportPageLearnMoreSpan supportPageLearnMoreSpan = new SupportPageLearnMoreSpan(fragment, str);
                    spannable.removeSpan(annotation);
                    spannable.setSpan(supportPageLearnMoreSpan, spanStart, spanEnd, 33);
                }
            }
            return spannable;
        }
    }
}
