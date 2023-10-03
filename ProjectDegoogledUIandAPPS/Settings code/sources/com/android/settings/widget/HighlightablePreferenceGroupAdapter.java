package com.android.settings.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;

public class HighlightablePreferenceGroupAdapter extends PreferenceGroupAdapter {
    static final long DELAY_HIGHLIGHT_DURATION_MILLIS = 600;
    boolean mFadeInAnimated;
    final int mHighlightColor;
    private final String mHighlightKey;
    private int mHighlightPosition = -1;
    private boolean mHighlightRequested;
    /* access modifiers changed from: private */
    public final int mNormalBackgroundRes;

    public static void adjustInitialExpandedChildCount(SettingsPreferenceFragment settingsPreferenceFragment) {
        PreferenceScreen preferenceScreen;
        if (settingsPreferenceFragment != null && (preferenceScreen = settingsPreferenceFragment.getPreferenceScreen()) != null) {
            Bundle arguments = settingsPreferenceFragment.getArguments();
            if (arguments == null || TextUtils.isEmpty(arguments.getString(":settings:fragment_args_key"))) {
                int initialExpandedChildCount = settingsPreferenceFragment.getInitialExpandedChildCount();
                if (initialExpandedChildCount > 0) {
                    preferenceScreen.setInitialExpandedChildrenCount(initialExpandedChildCount);
                    return;
                }
                return;
            }
            preferenceScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
        }
    }

    public HighlightablePreferenceGroupAdapter(PreferenceGroup preferenceGroup, String str, boolean z) {
        super(preferenceGroup);
        this.mHighlightKey = str;
        this.mHighlightRequested = z;
        Context context = preferenceGroup.getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843534, typedValue, true);
        this.mNormalBackgroundRes = typedValue.resourceId;
        this.mHighlightColor = context.getColor(C1715R.C1716color.preference_highligh_color);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i) {
        super.onBindViewHolder(preferenceViewHolder, i);
        updateBackground(preferenceViewHolder, i);
    }

    /* access modifiers changed from: package-private */
    public void updateBackground(PreferenceViewHolder preferenceViewHolder, int i) {
        View view = preferenceViewHolder.itemView;
        if (i == this.mHighlightPosition) {
            addHighlightBackground(view, !this.mFadeInAnimated);
        } else if (Boolean.TRUE.equals(view.getTag(C1715R.C1718id.preference_highlighted))) {
            removeHighlightBackground(view, false);
        }
    }

    public void requestHighlight(View view, RecyclerView recyclerView) {
        if (!this.mHighlightRequested && recyclerView != null && !TextUtils.isEmpty(this.mHighlightKey)) {
            view.postDelayed(new Runnable(recyclerView) {
                private final /* synthetic */ RecyclerView f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    HighlightablePreferenceGroupAdapter.this.lambda$requestHighlight$0$HighlightablePreferenceGroupAdapter(this.f$1);
                }
            }, DELAY_HIGHLIGHT_DURATION_MILLIS);
        }
    }

    public /* synthetic */ void lambda$requestHighlight$0$HighlightablePreferenceGroupAdapter(RecyclerView recyclerView) {
        int preferenceAdapterPosition = getPreferenceAdapterPosition(this.mHighlightKey);
        if (preferenceAdapterPosition >= 0) {
            this.mHighlightRequested = true;
            recyclerView.smoothScrollToPosition(preferenceAdapterPosition);
            this.mHighlightPosition = preferenceAdapterPosition;
            notifyItemChanged(preferenceAdapterPosition);
        }
    }

    public boolean isHighlightRequested() {
        return this.mHighlightRequested;
    }

    /* access modifiers changed from: package-private */
    public void requestRemoveHighlightDelayed(View view) {
        view.postDelayed(new Runnable(view) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                HighlightablePreferenceGroupAdapter.this.mo12136x7ee74a46(this.f$1);
            }
        }, 15000);
    }

    /* renamed from: lambda$requestRemoveHighlightDelayed$1$HighlightablePreferenceGroupAdapter */
    public /* synthetic */ void mo12136x7ee74a46(View view) {
        this.mHighlightPosition = -1;
        removeHighlightBackground(view, true);
    }

    private void addHighlightBackground(View view, boolean z) {
        view.setTag(C1715R.C1718id.preference_highlighted, true);
        if (!z) {
            view.setBackgroundColor(this.mHighlightColor);
            Log.d("HighlightableAdapter", "AddHighlight: Not animation requested - setting highlight background");
            requestRemoveHighlightDelayed(view);
            return;
        }
        this.mFadeInAnimated = true;
        int i = this.mHighlightColor;
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{-1, Integer.valueOf(i)});
        ofObject.setDuration(200);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(view) {
            private final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofObject.setRepeatMode(2);
        ofObject.setRepeatCount(4);
        ofObject.start();
        Log.d("HighlightableAdapter", "AddHighlight: starting fade in animation");
        requestRemoveHighlightDelayed(view);
    }

    private void removeHighlightBackground(final View view, boolean z) {
        if (!z) {
            view.setTag(C1715R.C1718id.preference_highlighted, false);
            view.setBackgroundResource(this.mNormalBackgroundRes);
            Log.d("HighlightableAdapter", "RemoveHighlight: No animation requested - setting normal background");
        } else if (!Boolean.TRUE.equals(view.getTag(C1715R.C1718id.preference_highlighted))) {
            Log.d("HighlightableAdapter", "RemoveHighlight: Not highlighted - skipping");
        } else {
            int i = this.mHighlightColor;
            view.setTag(C1715R.C1718id.preference_highlighted, false);
            ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(i), -1});
            ofObject.setDuration(500);
            ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(view) {
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            ofObject.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    view.setBackgroundResource(HighlightablePreferenceGroupAdapter.this.mNormalBackgroundRes);
                }
            });
            ofObject.start();
            Log.d("HighlightableAdapter", "Starting fade out animation");
        }
    }
}
