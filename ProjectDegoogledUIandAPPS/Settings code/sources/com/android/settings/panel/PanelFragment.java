package com.android.settings.panel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.widget.SliceLiveData;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.google.android.setupdesign.DividerItemDecoration;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PanelFragment extends Fragment {
    private PanelSlicesAdapter mAdapter;
    private Button mDoneButton;
    private View mLayoutView;
    private MetricsFeatureProvider mMetricsProvider;
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            PanelFragment.this.animateIn();
            if (PanelFragment.this.mPanelSlices != null) {
                PanelFragment.this.mPanelSlices.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };
    private ViewTreeObserver.OnPreDrawListener mOnPreDrawListener = $$Lambda$PanelFragment$rdpxKzRnUEXEAOP00WJYU0ZKA.INSTANCE;
    private PanelContent mPanel;
    private String mPanelClosedKey;
    /* access modifiers changed from: private */
    public RecyclerView mPanelSlices;
    @VisibleForTesting
    PanelSlicesLoaderCountdownLatch mPanelSlicesLoaderCountdownLatch;
    private Button mSeeMoreButton;
    private final List<LiveData<Slice>> mSliceLiveData = new ArrayList();
    private TextView mTitleView;

    static /* synthetic */ boolean lambda$new$0() {
        return false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLayoutView = layoutInflater.inflate(C1715R.layout.panel_layout, viewGroup, false);
        createPanelContent();
        return this.mLayoutView;
    }

    /* access modifiers changed from: package-private */
    public void updatePanelWithAnimation() {
        AnimatorSet buildAnimatorSet = buildAnimatorSet(this.mLayoutView, 0.0f, (float) this.mLayoutView.findViewById(C1715R.C1718id.panel_container).getHeight(), 1.0f, 0.0f, 200);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        buildAnimatorSet.play(valueAnimator);
        buildAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                PanelFragment.this.createPanelContent();
            }
        });
        buildAnimatorSet.start();
    }

    /* access modifiers changed from: private */
    public void createPanelContent() {
        FragmentActivity activity = getActivity();
        if (this.mLayoutView == null) {
            activity.finish();
        }
        this.mPanelSlices = (RecyclerView) this.mLayoutView.findViewById(C1715R.C1718id.panel_parent_layout);
        this.mSeeMoreButton = (Button) this.mLayoutView.findViewById(C1715R.C1718id.see_more);
        this.mDoneButton = (Button) this.mLayoutView.findViewById(C1715R.C1718id.done);
        this.mTitleView = (TextView) this.mLayoutView.findViewById(C1715R.C1718id.panel_title);
        this.mPanelSlices.setVisibility(8);
        Bundle arguments = getArguments();
        String string = arguments.getString("PANEL_TYPE_ARGUMENT");
        String string2 = arguments.getString("PANEL_CALLING_PACKAGE_NAME");
        this.mPanel = FeatureFactory.getFactory(activity).getPanelFeatureProvider().getPanel(activity, string, arguments.getString("PANEL_MEDIA_PACKAGE_NAME"));
        if (this.mPanel == null) {
            activity.finish();
        }
        this.mMetricsProvider = FeatureFactory.getFactory(activity).getMetricsFeatureProvider();
        this.mPanelSlices.setLayoutManager(new LinearLayoutManager(activity));
        this.mLayoutView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        loadAllSlices();
        this.mTitleView.setText(this.mPanel.getTitle());
        this.mSeeMoreButton.setOnClickListener(getSeeMoreListener());
        this.mDoneButton.setOnClickListener(getCloseListener());
        if (this.mPanel.getSeeMoreIntent() == null) {
            this.mSeeMoreButton.setVisibility(8);
        }
        this.mMetricsProvider.action(0, 1, this.mPanel.getMetricsCategory(), string2, 0);
    }

    private void loadAllSlices() {
        this.mSliceLiveData.clear();
        List<Uri> slices = this.mPanel.getSlices();
        this.mPanelSlicesLoaderCountdownLatch = new PanelSlicesLoaderCountdownLatch(slices.size());
        for (Uri next : slices) {
            LiveData<Slice> fromUri = SliceLiveData.fromUri(getActivity(), next);
            this.mSliceLiveData.add(fromUri);
            fromUri.observe(getViewLifecycleOwner(), new Observer(next, fromUri) {
                private final /* synthetic */ Uri f$1;
                private final /* synthetic */ LiveData f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onChanged(Object obj) {
                    PanelFragment.this.lambda$loadAllSlices$2$PanelFragment(this.f$1, this.f$2, (Slice) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$loadAllSlices$2$PanelFragment(Uri uri, LiveData liveData, Slice slice) {
        if (!this.mPanelSlicesLoaderCountdownLatch.isSliceLoaded(uri)) {
            SliceMetadata from = SliceMetadata.from(getActivity(), slice);
            if (slice == null || from.isErrorSlice()) {
                this.mSliceLiveData.remove(liveData);
                this.mPanelSlicesLoaderCountdownLatch.markSliceLoaded(uri);
            } else if (from.getLoadingState() == 2) {
                this.mPanelSlicesLoaderCountdownLatch.markSliceLoaded(uri);
            } else {
                new Handler().postDelayed(new Runnable(uri) {
                    private final /* synthetic */ Uri f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        PanelFragment.this.lambda$loadAllSlices$1$PanelFragment(this.f$1);
                    }
                }, 250);
            }
            loadPanelWhenReady();
        }
    }

    public /* synthetic */ void lambda$loadAllSlices$1$PanelFragment(Uri uri) {
        this.mPanelSlicesLoaderCountdownLatch.markSliceLoaded(uri);
        loadPanelWhenReady();
    }

    private void loadPanelWhenReady() {
        if (this.mPanelSlicesLoaderCountdownLatch.isPanelReadyToLoad()) {
            this.mAdapter = new PanelSlicesAdapter(this, this.mSliceLiveData, this.mPanel.getMetricsCategory());
            this.mPanelSlices.setAdapter(this.mAdapter);
            this.mPanelSlices.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
            this.mPanelSlices.setVisibility(0);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity());
            dividerItemDecoration.setDividerCondition(1);
            this.mPanelSlices.addItemDecoration(dividerItemDecoration);
        }
    }

    /* access modifiers changed from: private */
    public void animateIn() {
        AnimatorSet buildAnimatorSet = buildAnimatorSet(this.mLayoutView, (float) this.mLayoutView.findViewById(C1715R.C1718id.panel_container).getHeight(), 0.0f, 0.0f, 1.0f, 250);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        buildAnimatorSet.play(valueAnimator);
        buildAnimatorSet.start();
        this.mLayoutView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
    }

    private static AnimatorSet buildAnimatorSet(View view, float f, float f2, float f3, float f4, int i) {
        View findViewById = view.findViewById(C1715R.C1718id.panel_container);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration((long) i);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(findViewById, View.TRANSLATION_Y, new float[]{f, f2}), ObjectAnimator.ofFloat(findViewById, View.ALPHA, new float[]{f3, f4})});
        return animatorSet;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (TextUtils.isEmpty(this.mPanelClosedKey)) {
            this.mPanelClosedKey = "others";
        }
        this.mMetricsProvider.action(0, 2, this.mPanel.getMetricsCategory(), this.mPanelClosedKey, 0);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public View.OnClickListener getSeeMoreListener() {
        return new View.OnClickListener() {
            public final void onClick(View view) {
                PanelFragment.this.lambda$getSeeMoreListener$3$PanelFragment(view);
            }
        };
    }

    public /* synthetic */ void lambda$getSeeMoreListener$3$PanelFragment(View view) {
        this.mPanelClosedKey = "see_more";
        FragmentActivity activity = getActivity();
        activity.startActivityForResult(this.mPanel.getSeeMoreIntent(), 0);
        activity.finish();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public View.OnClickListener getCloseListener() {
        return new View.OnClickListener() {
            public final void onClick(View view) {
                PanelFragment.this.lambda$getCloseListener$4$PanelFragment(view);
            }
        };
    }

    public /* synthetic */ void lambda$getCloseListener$4$PanelFragment(View view) {
        this.mPanelClosedKey = "done";
        getActivity().finish();
    }
}
