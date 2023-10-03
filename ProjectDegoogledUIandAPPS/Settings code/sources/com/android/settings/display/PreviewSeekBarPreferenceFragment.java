package com.android.settings.display;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.display.PreviewSeekBarPreferenceFragment;
import com.android.settings.widget.DotsPageIndicator;
import com.android.settings.widget.LabeledSeekBar;
import com.havoc.config.center.C1715R;

public abstract class PreviewSeekBarPreferenceFragment extends SettingsPreferenceFragment {
    protected int mCurrentIndex;
    protected String[] mEntries;
    protected int mInitialIndex;
    private TextView mLabel;
    private View mLarger;
    private DotsPageIndicator mPageIndicator;
    private ViewPager.OnPageChangeListener mPageIndicatorPageChangeListener = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            PreviewSeekBarPreferenceFragment.this.setPagerIndicatorContentDescription(i);
        }
    };
    private ViewPager.OnPageChangeListener mPreviewPageChangeListener = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            PreviewSeekBarPreferenceFragment.this.mPreviewPager.sendAccessibilityEvent(16384);
        }
    };
    /* access modifiers changed from: private */
    public ViewPager mPreviewPager;
    /* access modifiers changed from: private */
    public PreviewPagerAdapter mPreviewPagerAdapter;
    private LabeledSeekBar mSeekBar;
    private View mSmaller;

    /* access modifiers changed from: protected */
    public abstract void commit();

    /* access modifiers changed from: protected */
    public abstract Configuration createConfig(Configuration configuration, int i);

    /* access modifiers changed from: protected */
    public abstract int getActivityLayoutResId();

    /* access modifiers changed from: protected */
    public abstract int[] getPreviewSampleResIds();

    private class onPreviewSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private boolean mSeekByTouch;

        private onPreviewSeekBarChangeListener() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            PreviewSeekBarPreferenceFragment.this.setPreviewLayer(i, false);
            if (!this.mSeekByTouch) {
                PreviewSeekBarPreferenceFragment.this.commit();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.mSeekByTouch = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (PreviewSeekBarPreferenceFragment.this.mPreviewPagerAdapter.isAnimating()) {
                PreviewSeekBarPreferenceFragment.this.mPreviewPagerAdapter.setAnimationEndAction(new Runnable() {
                    public final void run() {
                        PreviewSeekBarPreferenceFragment.onPreviewSeekBarChangeListener.this.mo9703xa045b4e();
                    }
                });
            } else {
                PreviewSeekBarPreferenceFragment.this.commit();
            }
            this.mSeekByTouch = false;
        }

        /* renamed from: lambda$onStopTrackingTouch$0$PreviewSeekBarPreferenceFragment$onPreviewSeekBarChangeListener */
        public /* synthetic */ void mo9703xa045b4e() {
            PreviewSeekBarPreferenceFragment.this.commit();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewGroup viewGroup2 = (ViewGroup) onCreateView.findViewById(16908351);
        viewGroup2.removeAllViews();
        View inflate = layoutInflater.inflate(getActivityLayoutResId(), viewGroup2, false);
        viewGroup2.addView(inflate);
        this.mLabel = (TextView) inflate.findViewById(C1715R.C1718id.current_label);
        int max = Math.max(1, this.mEntries.length - 1);
        this.mSeekBar = (LabeledSeekBar) inflate.findViewById(C1715R.C1718id.seek_bar);
        this.mSeekBar.setLabels(this.mEntries);
        this.mSeekBar.setMax(max);
        this.mSmaller = inflate.findViewById(C1715R.C1718id.smaller);
        this.mSmaller.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PreviewSeekBarPreferenceFragment.this.lambda$onCreateView$0$PreviewSeekBarPreferenceFragment(view);
            }
        });
        this.mLarger = inflate.findViewById(C1715R.C1718id.larger);
        this.mLarger.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PreviewSeekBarPreferenceFragment.this.lambda$onCreateView$1$PreviewSeekBarPreferenceFragment(view);
            }
        });
        if (this.mEntries.length == 1) {
            this.mSeekBar.setEnabled(false);
        }
        Context context = getContext();
        Configuration configuration = context.getResources().getConfiguration();
        boolean z = configuration.getLayoutDirection() == 1;
        Configuration[] configurationArr = new Configuration[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            configurationArr[i] = createConfig(configuration, i);
        }
        int[] previewSampleResIds = getPreviewSampleResIds();
        this.mPreviewPager = (ViewPager) inflate.findViewById(C1715R.C1718id.preview_pager);
        this.mPreviewPagerAdapter = new PreviewPagerAdapter(context, z, previewSampleResIds, configurationArr);
        this.mPreviewPager.setAdapter(this.mPreviewPagerAdapter);
        this.mPreviewPager.setCurrentItem(z ? previewSampleResIds.length - 1 : 0);
        this.mPreviewPager.addOnPageChangeListener(this.mPreviewPageChangeListener);
        this.mPageIndicator = (DotsPageIndicator) inflate.findViewById(C1715R.C1718id.page_indicator);
        if (previewSampleResIds.length > 1) {
            this.mPageIndicator.setViewPager(this.mPreviewPager);
            this.mPageIndicator.setVisibility(0);
            this.mPageIndicator.setOnPageChangeListener(this.mPageIndicatorPageChangeListener);
        } else {
            this.mPageIndicator.setVisibility(8);
        }
        setPreviewLayer(this.mInitialIndex, false);
        return onCreateView;
    }

    public /* synthetic */ void lambda$onCreateView$0$PreviewSeekBarPreferenceFragment(View view) {
        int progress = this.mSeekBar.getProgress();
        if (progress > 0) {
            this.mSeekBar.setProgress(progress - 1, true);
        }
    }

    public /* synthetic */ void lambda$onCreateView$1$PreviewSeekBarPreferenceFragment(View view) {
        int progress = this.mSeekBar.getProgress();
        if (progress < this.mSeekBar.getMax()) {
            this.mSeekBar.setProgress(progress + 1, true);
        }
    }

    public void onStart() {
        super.onStart();
        this.mSeekBar.setProgress(this.mCurrentIndex);
        this.mSeekBar.setOnSeekBarChangeListener(new onPreviewSeekBarChangeListener());
    }

    public void onStop() {
        super.onStop();
        this.mSeekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
    }

    /* access modifiers changed from: private */
    public void setPreviewLayer(int i, boolean z) {
        this.mLabel.setText(this.mEntries[i]);
        boolean z2 = false;
        this.mSmaller.setEnabled(i > 0);
        View view = this.mLarger;
        if (i < this.mEntries.length - 1) {
            z2 = true;
        }
        view.setEnabled(z2);
        setPagerIndicatorContentDescription(this.mPreviewPager.getCurrentItem());
        this.mPreviewPagerAdapter.setPreviewLayer(i, this.mCurrentIndex, this.mPreviewPager.getCurrentItem(), z);
        this.mCurrentIndex = i;
    }

    /* access modifiers changed from: private */
    public void setPagerIndicatorContentDescription(int i) {
        this.mPageIndicator.setContentDescription(getString(C1715R.string.preview_page_indicator_content_description, Integer.valueOf(i + 1), Integer.valueOf(getPreviewSampleResIds().length)));
    }
}
