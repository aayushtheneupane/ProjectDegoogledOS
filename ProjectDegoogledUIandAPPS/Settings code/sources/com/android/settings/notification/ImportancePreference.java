package com.android.settings.notification;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class ImportancePreference extends Preference {
    private View mAlertButton;
    private Context mContext;
    private boolean mDisplayInStatusBar;
    private boolean mDisplayOnLockscreen;
    private int mImportance;
    private boolean mIsConfigurable = true;
    private View mSilenceButton;
    Drawable selectedBackground;
    Drawable unselectedBackground;

    public ImportancePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public ImportancePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public ImportancePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ImportancePreference(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.selectedBackground = this.mContext.getDrawable(C1715R.C1717drawable.button_border_selected);
        this.unselectedBackground = this.mContext.getDrawable(C1715R.C1717drawable.button_border_unselected);
        setLayoutResource(C1715R.layout.notif_importance_preference);
    }

    public void setImportance(int i) {
        this.mImportance = i;
    }

    public void setConfigurable(boolean z) {
        this.mIsConfigurable = z;
    }

    public void setDisplayInStatusBar(boolean z) {
        this.mDisplayInStatusBar = z;
    }

    public void setDisplayOnLockscreen(boolean z) {
        this.mDisplayOnLockscreen = z;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        this.mSilenceButton = preferenceViewHolder.findViewById(C1715R.C1718id.silence);
        this.mAlertButton = preferenceViewHolder.findViewById(C1715R.C1718id.alert);
        if (!this.mIsConfigurable) {
            this.mSilenceButton.setEnabled(false);
            this.mAlertButton.setEnabled(false);
        }
        setImportanceSummary((ViewGroup) preferenceViewHolder.itemView, this.mImportance, false);
        int i = this.mImportance;
        if (i == 1 || i == 2) {
            this.mAlertButton.setBackground(this.unselectedBackground);
            this.mSilenceButton.setBackground(this.selectedBackground);
            this.mSilenceButton.setSelected(true);
        } else {
            this.mSilenceButton.setBackground(this.unselectedBackground);
            this.mAlertButton.setBackground(this.selectedBackground);
            this.mAlertButton.setSelected(true);
        }
        this.mSilenceButton.setOnClickListener(new View.OnClickListener(preferenceViewHolder) {
            private final /* synthetic */ PreferenceViewHolder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ImportancePreference.this.lambda$onBindViewHolder$1$ImportancePreference(this.f$1, view);
            }
        });
        this.mAlertButton.setOnClickListener(new View.OnClickListener(preferenceViewHolder) {
            private final /* synthetic */ PreferenceViewHolder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ImportancePreference.this.lambda$onBindViewHolder$3$ImportancePreference(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$ImportancePreference(PreferenceViewHolder preferenceViewHolder, View view) {
        callChangeListener(2);
        this.mAlertButton.setBackground(this.unselectedBackground);
        this.mSilenceButton.setBackground(this.selectedBackground);
        setImportanceSummary((ViewGroup) preferenceViewHolder.itemView, 2, true);
        preferenceViewHolder.itemView.post(new Runnable() {
            public final void run() {
                ImportancePreference.this.lambda$onBindViewHolder$0$ImportancePreference();
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$ImportancePreference() {
        this.mAlertButton.setSelected(false);
        this.mSilenceButton.setSelected(true);
    }

    public /* synthetic */ void lambda$onBindViewHolder$3$ImportancePreference(PreferenceViewHolder preferenceViewHolder, View view) {
        callChangeListener(3);
        this.mSilenceButton.setBackground(this.unselectedBackground);
        this.mAlertButton.setBackground(this.selectedBackground);
        setImportanceSummary((ViewGroup) preferenceViewHolder.itemView, 3, true);
        preferenceViewHolder.itemView.post(new Runnable() {
            public final void run() {
                ImportancePreference.this.lambda$onBindViewHolder$2$ImportancePreference();
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$2$ImportancePreference() {
        this.mSilenceButton.setSelected(false);
        this.mAlertButton.setSelected(true);
    }

    private ColorStateList getAccentTint() {
        return Utils.getColorAccent(getContext());
    }

    private ColorStateList getRegularTint() {
        return Utils.getColorAttr(getContext(), 16842806);
    }

    /* access modifiers changed from: package-private */
    public void setImportanceSummary(ViewGroup viewGroup, int i, boolean z) {
        if (z) {
            AutoTransition autoTransition = new AutoTransition();
            autoTransition.setDuration(100);
            TransitionManager.beginDelayedTransition(viewGroup, autoTransition);
        }
        ColorStateList accentTint = getAccentTint();
        ColorStateList regularTint = getRegularTint();
        if (i >= 3) {
            viewGroup.findViewById(C1715R.C1718id.silence_summary).setVisibility(8);
            ((ImageView) viewGroup.findViewById(C1715R.C1718id.silence_icon)).setImageTintList(regularTint);
            ((TextView) viewGroup.findViewById(C1715R.C1718id.silence_label)).setTextColor(regularTint);
            ((ImageView) viewGroup.findViewById(C1715R.C1718id.alert_icon)).setImageTintList(accentTint);
            ((TextView) viewGroup.findViewById(C1715R.C1718id.alert_label)).setTextColor(accentTint);
            viewGroup.findViewById(C1715R.C1718id.alert_summary).setVisibility(0);
            return;
        }
        viewGroup.findViewById(C1715R.C1718id.alert_summary).setVisibility(8);
        ((ImageView) viewGroup.findViewById(C1715R.C1718id.alert_icon)).setImageTintList(regularTint);
        ((TextView) viewGroup.findViewById(C1715R.C1718id.alert_label)).setTextColor(regularTint);
        ((ImageView) viewGroup.findViewById(C1715R.C1718id.silence_icon)).setImageTintList(accentTint);
        ((TextView) viewGroup.findViewById(C1715R.C1718id.silence_label)).setTextColor(accentTint);
        viewGroup.findViewById(C1715R.C1718id.silence_summary).setVisibility(0);
    }
}
