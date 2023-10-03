package com.android.contacts.quickcontact;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.dialog.CallSubjectDialog;
import java.util.ArrayList;
import java.util.List;

public class ExpandingEntryCardView extends CardView {
    /* access modifiers changed from: private */
    public static final Property<View, Integer> VIEW_LAYOUT_HEIGHT_PROPERTY = new Property<View, Integer>(Integer.class, "height") {
        public void set(View view, Integer num) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = num.intValue();
            view.setLayoutParams(layoutParams);
        }

        public Integer get(View view) {
            return Integer.valueOf(view.getLayoutParams().height);
        }
    };
    private boolean mAllEntriesInflated;
    private ViewGroup mAnimationViewGroup;
    private int mCollapsedEntriesCount;
    private LinearLayout mContainer;
    /* access modifiers changed from: private */
    public final int mDividerLineHeightPixels;
    private List<List<Entry>> mEntries;
    private LinearLayout mEntriesViewGroup;
    private List<List<View>> mEntryViews;
    private final ImageView mExpandCollapseArrow;
    private View mExpandCollapseButton;
    private final View.OnClickListener mExpandCollapseButtonListener;
    private TextView mExpandCollapseTextView;
    private boolean mIsAlwaysExpanded;
    /* access modifiers changed from: private */
    public boolean mIsExpanded;
    /* access modifiers changed from: private */
    public ExpandingEntryCardViewListener mListener;
    private int mNumEntries;
    private View.OnClickListener mOnClickListener;
    private View.OnCreateContextMenuListener mOnCreateContextMenuListener;
    private List<View> mSeparators;
    private int mThemeColor;
    private ColorFilter mThemeColorFilter;
    private TextView mTitleTextView;

    public interface ExpandingEntryCardViewListener {
        void onCollapse(int i);

        void onExpand();

        void onExpandDone();
    }

    public static final class Entry {
        private Spannable mAlternateContentDescription;
        private final Drawable mAlternateIcon;
        private final Intent mAlternateIntent;
        private final EntryContextMenuInfo mEntryContextMenuInfo;
        private final String mHeader;
        private final Drawable mIcon;
        private final int mIconResourceId;
        private final int mId;
        private final Intent mIntent;
        private final boolean mIsEditable;
        private Spannable mPrimaryContentDescription;
        private final boolean mShouldApplyColor;
        private final boolean mShouldApplyThirdIconColor;
        private final String mSubHeader;
        private final Drawable mSubHeaderIcon;
        private final String mText;
        private final Drawable mTextIcon;
        private final int mThirdAction;
        private final String mThirdContentDescription;
        private final Bundle mThirdExtras;
        private final Drawable mThirdIcon;
        private final Intent mThirdIntent;

        public Entry(int i, Drawable drawable, String str, String str2, Drawable drawable2, String str3, Drawable drawable3, Spannable spannable, Intent intent, Drawable drawable4, Intent intent2, Spannable spannable2, boolean z, boolean z2, EntryContextMenuInfo entryContextMenuInfo, Drawable drawable5, Intent intent3, String str4, int i2, Bundle bundle, boolean z3, int i3) {
            this.mId = i;
            this.mIcon = drawable;
            this.mHeader = str;
            this.mSubHeader = str2;
            this.mSubHeaderIcon = drawable2;
            this.mText = str3;
            this.mTextIcon = drawable3;
            this.mPrimaryContentDescription = spannable;
            this.mIntent = intent;
            this.mAlternateIcon = drawable4;
            this.mAlternateIntent = intent2;
            this.mAlternateContentDescription = spannable2;
            this.mShouldApplyColor = z;
            this.mIsEditable = z2;
            this.mEntryContextMenuInfo = entryContextMenuInfo;
            this.mThirdIcon = drawable5;
            this.mThirdIntent = intent3;
            this.mThirdContentDescription = str4;
            this.mThirdAction = i2;
            this.mThirdExtras = bundle;
            this.mShouldApplyThirdIconColor = z3;
            this.mIconResourceId = i3;
        }

        /* access modifiers changed from: package-private */
        public Drawable getIcon() {
            return this.mIcon;
        }

        /* access modifiers changed from: package-private */
        public String getHeader() {
            return this.mHeader;
        }

        /* access modifiers changed from: package-private */
        public String getSubHeader() {
            return this.mSubHeader;
        }

        /* access modifiers changed from: package-private */
        public Drawable getSubHeaderIcon() {
            return this.mSubHeaderIcon;
        }

        public String getText() {
            return this.mText;
        }

        /* access modifiers changed from: package-private */
        public Drawable getTextIcon() {
            return this.mTextIcon;
        }

        /* access modifiers changed from: package-private */
        public Spannable getPrimaryContentDescription() {
            return this.mPrimaryContentDescription;
        }

        /* access modifiers changed from: package-private */
        public Intent getIntent() {
            return this.mIntent;
        }

        /* access modifiers changed from: package-private */
        public Drawable getAlternateIcon() {
            return this.mAlternateIcon;
        }

        /* access modifiers changed from: package-private */
        public Intent getAlternateIntent() {
            return this.mAlternateIntent;
        }

        /* access modifiers changed from: package-private */
        public Spannable getAlternateContentDescription() {
            return this.mAlternateContentDescription;
        }

        /* access modifiers changed from: package-private */
        public boolean shouldApplyColor() {
            return this.mShouldApplyColor;
        }

        /* access modifiers changed from: package-private */
        public int getId() {
            return this.mId;
        }

        /* access modifiers changed from: package-private */
        public EntryContextMenuInfo getEntryContextMenuInfo() {
            return this.mEntryContextMenuInfo;
        }

        /* access modifiers changed from: package-private */
        public Drawable getThirdIcon() {
            return this.mThirdIcon;
        }

        /* access modifiers changed from: package-private */
        public Intent getThirdIntent() {
            return this.mThirdIntent;
        }

        /* access modifiers changed from: package-private */
        public String getThirdContentDescription() {
            return this.mThirdContentDescription;
        }

        public int getThirdAction() {
            return this.mThirdAction;
        }

        public Bundle getThirdExtras() {
            return this.mThirdExtras;
        }

        /* access modifiers changed from: package-private */
        public boolean shouldApplyThirdIconColor() {
            return this.mShouldApplyThirdIconColor;
        }
    }

    public ExpandingEntryCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExpandingEntryCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsExpanded = false;
        this.mNumEntries = 0;
        this.mAllEntriesInflated = false;
        this.mExpandCollapseButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (ExpandingEntryCardView.this.mIsExpanded) {
                    ExpandingEntryCardView.this.collapse();
                } else {
                    ExpandingEntryCardView.this.expand();
                }
            }
        };
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.expanding_entry_card_view, this);
        this.mEntriesViewGroup = (LinearLayout) inflate.findViewById(R.id.content_area_linear_layout);
        this.mTitleTextView = (TextView) inflate.findViewById(R.id.title);
        this.mContainer = (LinearLayout) inflate.findViewById(R.id.container);
        this.mExpandCollapseButton = from.inflate(R.layout.quickcontact_expanding_entry_card_button, this, false);
        this.mExpandCollapseTextView = (TextView) this.mExpandCollapseButton.findViewById(R.id.text);
        this.mExpandCollapseArrow = (ImageView) this.mExpandCollapseButton.findViewById(R.id.arrow);
        this.mExpandCollapseButton.setOnClickListener(this.mExpandCollapseButtonListener);
        this.mDividerLineHeightPixels = getResources().getDimensionPixelSize(R.dimen.divider_line_height);
    }

    public void initialize(List<List<Entry>> list, int i, boolean z, boolean z2, ExpandingEntryCardViewListener expandingEntryCardViewListener, ViewGroup viewGroup) {
        LayoutInflater from = LayoutInflater.from(getContext());
        this.mIsExpanded = z;
        this.mIsAlwaysExpanded = z2;
        this.mIsExpanded |= this.mIsAlwaysExpanded;
        this.mEntryViews = new ArrayList(list.size());
        this.mEntries = list;
        this.mNumEntries = 0;
        this.mAllEntriesInflated = false;
        for (List<Entry> size : this.mEntries) {
            this.mNumEntries += size.size();
            this.mEntryViews.add(new ArrayList());
        }
        this.mCollapsedEntriesCount = Math.min(i, this.mNumEntries);
        if (list.size() > 1) {
            this.mSeparators = new ArrayList(list.size() - 1);
        }
        this.mListener = expandingEntryCardViewListener;
        this.mAnimationViewGroup = viewGroup;
        if (this.mIsExpanded) {
            updateExpandCollapseButton(getCollapseButtonText(), 0);
            inflateAllEntries(from);
        } else {
            updateExpandCollapseButton(getExpandButtonText(), 0);
            inflateInitialEntries(from);
        }
        insertEntriesIntoViewGroup();
        applyColor();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnCreateContextMenuListener(View.OnCreateContextMenuListener onCreateContextMenuListener) {
        this.mOnCreateContextMenuListener = onCreateContextMenuListener;
    }

    private List<View> calculateEntriesToRemoveDuringCollapse() {
        List<View> viewsToDisplay = getViewsToDisplay(true);
        viewsToDisplay.removeAll(getViewsToDisplay(false));
        return viewsToDisplay;
    }

    /* access modifiers changed from: private */
    public void insertEntriesIntoViewGroup() {
        this.mEntriesViewGroup.removeAllViews();
        for (View addView : getViewsToDisplay(this.mIsExpanded)) {
            this.mEntriesViewGroup.addView(addView);
        }
        removeView(this.mExpandCollapseButton);
        if (this.mCollapsedEntriesCount < this.mNumEntries && this.mExpandCollapseButton.getParent() == null && !this.mIsAlwaysExpanded) {
            this.mContainer.addView(this.mExpandCollapseButton, -1);
        }
    }

    private List<View> getViewsToDisplay(boolean z) {
        View view;
        View view2;
        ArrayList arrayList = new ArrayList();
        if (z) {
            for (int i = 0; i < this.mEntryViews.size(); i++) {
                List<View> list = this.mEntryViews.get(i);
                if (i > 0) {
                    int i2 = i - 1;
                    if (this.mSeparators.size() <= i2) {
                        view2 = generateSeparator((View) list.get(0));
                        this.mSeparators.add(view2);
                    } else {
                        view2 = this.mSeparators.get(i2);
                    }
                    arrayList.add(view2);
                }
                for (View add : list) {
                    arrayList.add(add);
                }
            }
        } else {
            int size = this.mCollapsedEntriesCount - this.mEntryViews.size();
            int i3 = 0;
            for (int i4 = 0; i4 < this.mEntryViews.size() && i3 < this.mCollapsedEntriesCount; i4++) {
                List list2 = this.mEntryViews.get(i4);
                if (i4 > 0) {
                    int i5 = i4 - 1;
                    if (this.mSeparators.size() <= i5) {
                        view = generateSeparator((View) list2.get(0));
                        this.mSeparators.add(view);
                    } else {
                        view = this.mSeparators.get(i5);
                    }
                    arrayList.add(view);
                }
                arrayList.add((View) list2.get(0));
                i3++;
                for (int i6 = 1; i6 < list2.size() && i3 < this.mCollapsedEntriesCount && size > 0; i6++) {
                    arrayList.add((View) list2.get(i6));
                    i3++;
                    size--;
                }
            }
        }
        formatEntryIfFirst(arrayList);
        return arrayList;
    }

    private void formatEntryIfFirst(List<View> list) {
        if (TextUtils.isEmpty(this.mTitleTextView.getText()) && list.size() > 0) {
            View view = list.get(0);
            view.setPaddingRelative(view.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_item_padding_top) + getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_null_title_top_extra_padding), view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    private View generateSeparator(View view) {
        View view2 = new View(getContext());
        Resources resources = getResources();
        view2.setBackgroundColor(resources.getColor(R.color.divider_line_color_light));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.mDividerLineHeightPixels);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.expanding_entry_card_item_padding_start);
        if (((ImageView) view.findViewById(R.id.icon)).getVisibility() == 0) {
            dimensionPixelSize += resources.getDimensionPixelSize(R.dimen.expanding_entry_card_item_icon_width) + resources.getDimensionPixelSize(R.dimen.expanding_entry_card_item_image_spacing);
        }
        layoutParams.setMarginStart(dimensionPixelSize);
        view2.setLayoutParams(layoutParams);
        return view2;
    }

    private CharSequence getExpandButtonText() {
        return getResources().getText(R.string.expanding_entry_card_view_see_more);
    }

    private CharSequence getCollapseButtonText() {
        return getResources().getText(R.string.expanding_entry_card_view_see_less);
    }

    private void inflateInitialEntries(LayoutInflater layoutInflater) {
        int i = this.mCollapsedEntriesCount;
        if (i == this.mNumEntries) {
            inflateAllEntries(layoutInflater);
            return;
        }
        int size = i - this.mEntries.size();
        int i2 = 0;
        for (int i3 = 0; i3 < this.mEntries.size() && i2 < this.mCollapsedEntriesCount; i3++) {
            List list = this.mEntries.get(i3);
            List list2 = this.mEntryViews.get(i3);
            list2.add(createEntryView(layoutInflater, (Entry) list.get(0), 0));
            i2++;
            for (int i4 = 1; i4 < list.size() && i2 < this.mCollapsedEntriesCount && size > 0; i4++) {
                list2.add(createEntryView(layoutInflater, (Entry) list.get(i4), 4));
                i2++;
                size--;
            }
        }
    }

    private void inflateAllEntries(LayoutInflater layoutInflater) {
        if (!this.mAllEntriesInflated) {
            for (int i = 0; i < this.mEntries.size(); i++) {
                List list = this.mEntries.get(i);
                List list2 = this.mEntryViews.get(i);
                int size = list2.size();
                while (size < list.size()) {
                    Entry entry = (Entry) list.get(size);
                    list2.add(createEntryView(layoutInflater, entry, entry.getIcon() == null ? 8 : size == 0 ? 0 : 4));
                    size++;
                }
            }
            this.mAllEntriesInflated = true;
        }
    }

    public void setColorAndFilter(int i, ColorFilter colorFilter) {
        this.mThemeColor = i;
        this.mThemeColorFilter = colorFilter;
        applyColor();
    }

    public void setEntryHeaderColor(int i) {
        if (this.mEntries != null) {
            for (List<View> it : this.mEntryViews) {
                for (View findViewById : it) {
                    TextView textView = (TextView) findViewById.findViewById(R.id.header);
                    if (textView != null) {
                        textView.setTextColor(i);
                    }
                }
            }
        }
    }

    public void applyColor() {
        Drawable icon;
        int i = this.mThemeColor;
        if (i != 0 && this.mThemeColorFilter != null) {
            TextView textView = this.mTitleTextView;
            if (textView != null) {
                textView.setTextColor(i);
            }
            List<List<Entry>> list = this.mEntries;
            if (list != null) {
                for (List<Entry> it : list) {
                    for (Entry entry : it) {
                        if (entry.shouldApplyColor() && (icon = entry.getIcon()) != null) {
                            icon.mutate();
                            icon.setColorFilter(this.mThemeColorFilter);
                        }
                        Drawable alternateIcon = entry.getAlternateIcon();
                        if (alternateIcon != null) {
                            alternateIcon.mutate();
                            alternateIcon.setColorFilter(this.mThemeColorFilter);
                        }
                        Drawable thirdIcon = entry.getThirdIcon();
                        if (thirdIcon != null && entry.shouldApplyThirdIconColor()) {
                            thirdIcon.mutate();
                            thirdIcon.setColorFilter(this.mThemeColorFilter);
                        }
                    }
                }
            }
            this.mExpandCollapseTextView.setTextColor(this.mThemeColor);
            this.mExpandCollapseArrow.setColorFilter(this.mThemeColorFilter);
        }
    }

    private View createEntryView(LayoutInflater layoutInflater, final Entry entry, int i) {
        EntryView entryView = (EntryView) layoutInflater.inflate(R.layout.expanding_entry_card_item, this, false);
        entryView.setContextMenuInfo(entry.getEntryContextMenuInfo());
        if (!TextUtils.isEmpty(entry.getPrimaryContentDescription())) {
            entryView.setContentDescription(entry.getPrimaryContentDescription());
        }
        ImageView imageView = (ImageView) entryView.findViewById(R.id.icon);
        imageView.setVisibility(i);
        if (entry.getIcon() != null) {
            imageView.setImageDrawable(entry.getIcon());
        }
        TextView textView = (TextView) entryView.findViewById(R.id.header);
        if (!TextUtils.isEmpty(entry.getHeader())) {
            textView.setText(entry.getHeader());
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) entryView.findViewById(R.id.sub_header);
        if (!TextUtils.isEmpty(entry.getSubHeader())) {
            textView2.setText(entry.getSubHeader());
        } else {
            textView2.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) entryView.findViewById(R.id.icon_sub_header);
        if (entry.getSubHeaderIcon() != null) {
            imageView2.setImageDrawable(entry.getSubHeaderIcon());
        } else {
            imageView2.setVisibility(8);
        }
        TextView textView3 = (TextView) entryView.findViewById(R.id.text);
        if (!TextUtils.isEmpty(entry.getText())) {
            textView3.setText(entry.getText());
        } else {
            textView3.setVisibility(8);
        }
        ImageView imageView3 = (ImageView) entryView.findViewById(R.id.icon_text);
        if (entry.getTextIcon() != null) {
            imageView3.setImageDrawable(entry.getTextIcon());
        } else {
            imageView3.setVisibility(8);
        }
        if (entry.getIntent() != null) {
            entryView.setOnClickListener(this.mOnClickListener);
            entryView.setTag(new EntryTag(entry.getId(), entry.getIntent()));
        }
        if (entry.getIntent() == null && entry.getEntryContextMenuInfo() == null) {
            entryView.setBackground((Drawable) null);
        }
        if (textView.getVisibility() == 0 && textView2.getVisibility() == 8 && textView3.getVisibility() == 8) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen.expanding_entry_card_item_header_only_margin_top);
            layoutParams.bottomMargin += (int) getResources().getDimension(R.dimen.expanding_entry_card_item_header_only_margin_bottom);
            textView.setLayoutParams(layoutParams);
        }
        if (i == 4 && (!TextUtils.isEmpty(entry.getSubHeader()) || !TextUtils.isEmpty(entry.getText()))) {
            entryView.setPaddingRelative(entryView.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_item_no_icon_margin_top), entryView.getPaddingEnd(), entryView.getPaddingBottom());
        } else if (i == 4 && TextUtils.isEmpty(entry.getSubHeader()) && TextUtils.isEmpty(entry.getText())) {
            entryView.setPaddingRelative(entryView.getPaddingStart(), 0, entryView.getPaddingEnd(), entryView.getPaddingBottom());
        }
        ImageView imageView4 = (ImageView) entryView.findViewById(R.id.icon_alternate);
        ImageView imageView5 = (ImageView) entryView.findViewById(R.id.third_icon);
        if (!(entry.getAlternateIcon() == null || entry.getAlternateIntent() == null)) {
            imageView4.setImageDrawable(entry.getAlternateIcon());
            imageView4.setOnClickListener(this.mOnClickListener);
            imageView4.setTag(new EntryTag(entry.getId(), entry.getAlternateIntent()));
            imageView4.setVisibility(0);
            imageView4.setContentDescription(entry.getAlternateContentDescription());
        }
        if (!(entry.getThirdIcon() == null || entry.getThirdAction() == 1)) {
            imageView5.setImageDrawable(entry.getThirdIcon());
            if (entry.getThirdAction() == 2) {
                imageView5.setOnClickListener(this.mOnClickListener);
                imageView5.setTag(new EntryTag(entry.getId(), entry.getThirdIntent()));
            } else if (entry.getThirdAction() == 3) {
                imageView5.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (view.getTag() instanceof Bundle) {
                            Context context = ExpandingEntryCardView.this.getContext();
                            if (context instanceof Activity) {
                                CallSubjectDialog.start((Activity) context, entry.getThirdExtras());
                            }
                        }
                    }
                });
                imageView5.setTag(entry.getThirdExtras());
            }
            imageView5.setVisibility(0);
            imageView5.setContentDescription(entry.getThirdContentDescription());
        }
        entryView.setOnTouchListener(new EntryTouchListener(entryView, imageView4, imageView5));
        entryView.setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        return entryView;
    }

    private void updateExpandCollapseButton(CharSequence charSequence, long j) {
        if (this.mIsExpanded) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mExpandCollapseArrow, "rotation", new float[]{180.0f});
            ofFloat.setDuration(j);
            ofFloat.start();
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mExpandCollapseArrow, "rotation", new float[]{0.0f});
            ofFloat2.setDuration(j);
            ofFloat2.start();
        }
        this.mExpandCollapseTextView.setText(charSequence);
    }

    /* access modifiers changed from: private */
    public void expand() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(300);
        Fade fade = new Fade(1);
        fade.setDuration(200);
        fade.setStartDelay(100);
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(changeBounds);
        transitionSet.addTransition(fade);
        transitionSet.excludeTarget(R.id.text, true);
        ViewGroup viewGroup = this.mAnimationViewGroup;
        if (viewGroup == null) {
            viewGroup = this;
        }
        transitionSet.addListener(new Transition.TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
                ExpandingEntryCardView.this.mListener.onExpand();
            }

            public void onTransitionEnd(Transition transition) {
                ExpandingEntryCardView.this.mListener.onExpandDone();
            }
        });
        TransitionManager.beginDelayedTransition(viewGroup, transitionSet);
        this.mIsExpanded = true;
        inflateAllEntries(LayoutInflater.from(getContext()));
        insertEntriesIntoViewGroup();
        updateExpandCollapseButton(getCollapseButtonText(), 300);
    }

    /* access modifiers changed from: private */
    public void collapse() {
        final List<View> calculateEntriesToRemoveDuringCollapse = calculateEntriesToRemoveDuringCollapse();
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList(calculateEntriesToRemoveDuringCollapse.size());
        int i = 0;
        for (View next : calculateEntriesToRemoveDuringCollapse) {
            ObjectAnimator ofObject = ObjectAnimator.ofObject(next, VIEW_LAYOUT_HEIGHT_PROPERTY, (TypeEvaluator) null, new Integer[]{Integer.valueOf(next.getHeight()), 0});
            i += next.getHeight();
            ofObject.setDuration(300);
            arrayList.add(ofObject);
            next.animate().alpha(ContactPhotoManager.OFFSET_DEFAULT).setDuration(75);
        }
        animatorSet.playTogether(arrayList);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ExpandingEntryCardView.this.insertEntriesIntoViewGroup();
                for (View view : calculateEntriesToRemoveDuringCollapse) {
                    if (view instanceof EntryView) {
                        ExpandingEntryCardView.VIEW_LAYOUT_HEIGHT_PROPERTY.set(view, -2);
                    } else {
                        ExpandingEntryCardView.VIEW_LAYOUT_HEIGHT_PROPERTY.set(view, Integer.valueOf(ExpandingEntryCardView.this.mDividerLineHeightPixels));
                    }
                    view.animate().cancel();
                    view.setAlpha(1.0f);
                }
            }
        });
        this.mListener.onCollapse(i);
        this.mIsExpanded = false;
        updateExpandCollapseButton(getExpandButtonText(), 300);
    }

    public boolean isExpanded() {
        return this.mIsExpanded;
    }

    public void setTitle(String str) {
        if (this.mTitleTextView == null) {
            Log.e("ExpandingEntryCardView", "mTitleTextView is null");
        }
        this.mTitleTextView.setText(str);
        int i = 8;
        this.mTitleTextView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        View findViewById = findViewById(R.id.title_separator);
        if (!TextUtils.isEmpty(str)) {
            i = 0;
        }
        findViewById.setVisibility(i);
        if (!TextUtils.isEmpty(str) && this.mEntriesViewGroup.getChildCount() > 0) {
            View childAt = this.mEntriesViewGroup.getChildAt(0);
            childAt.setPadding(childAt.getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_item_padding_top), childAt.getPaddingRight(), childAt.getPaddingBottom());
        } else if (!TextUtils.isEmpty(str) && this.mEntriesViewGroup.getChildCount() > 0) {
            View childAt2 = this.mEntriesViewGroup.getChildAt(0);
            childAt2.setPadding(childAt2.getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_item_padding_top) + getResources().getDimensionPixelSize(R.dimen.expanding_entry_card_null_title_top_extra_padding), childAt2.getPaddingRight(), childAt2.getPaddingBottom());
        }
    }

    public static final class EntryView extends RelativeLayout {
        private EntryContextMenuInfo mEntryContextMenuInfo;

        public EntryView(Context context) {
            super(context);
        }

        public EntryView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setContextMenuInfo(EntryContextMenuInfo entryContextMenuInfo) {
            this.mEntryContextMenuInfo = entryContextMenuInfo;
        }

        /* access modifiers changed from: protected */
        public ContextMenu.ContextMenuInfo getContextMenuInfo() {
            return this.mEntryContextMenuInfo;
        }
    }

    public static final class EntryContextMenuInfo implements ContextMenu.ContextMenuInfo {
        private final String mCopyLabel;
        private final String mCopyText;
        private final long mId;
        private final boolean mIsSuperPrimary;
        private final String mMimeType;

        public EntryContextMenuInfo(String str, String str2, String str3, long j, boolean z) {
            this.mCopyText = str;
            this.mCopyLabel = str2;
            this.mMimeType = str3;
            this.mId = j;
            this.mIsSuperPrimary = z;
        }

        public String getCopyText() {
            return this.mCopyText;
        }

        public String getCopyLabel() {
            return this.mCopyLabel;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public long getId() {
            return this.mId;
        }

        public boolean isSuperPrimary() {
            return this.mIsSuperPrimary;
        }
    }

    static final class EntryTag {
        private final int mId;
        private final Intent mIntent;

        public EntryTag(int i, Intent intent) {
            this.mId = i;
            this.mIntent = intent;
        }

        public int getId() {
            return this.mId;
        }

        public Intent getIntent() {
            return this.mIntent;
        }
    }

    private static final class EntryTouchListener implements View.OnTouchListener {
        private final ImageView mAlternateIcon;
        private final View mEntry;
        private int mSlop;
        private final ImageView mThirdIcon;
        private View mTouchedView;

        public EntryTouchListener(View view, ImageView imageView, ImageView imageView2) {
            this.mEntry = view;
            this.mAlternateIcon = imageView;
            this.mThirdIcon = imageView2;
            this.mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean z;
            boolean z2;
            View view2 = this.mTouchedView;
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action == 0) {
                if (hitThirdIcon(motionEvent)) {
                    this.mTouchedView = this.mThirdIcon;
                } else if (hitAlternateIcon(motionEvent)) {
                    this.mTouchedView = this.mAlternateIcon;
                } else {
                    this.mTouchedView = this.mEntry;
                    z2 = false;
                    z = z2;
                    view2 = this.mTouchedView;
                }
                z2 = true;
                z = z2;
                view2 = this.mTouchedView;
            } else if (action == 1 || action == 2) {
                View view3 = this.mTouchedView;
                z = (view3 == null || view3 == this.mEntry) ? false : true;
                if (z) {
                    Rect rect = new Rect();
                    view2.getHitRect(rect);
                    int i = this.mSlop;
                    rect.inset(-i, -i);
                    z3 = rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
                }
            } else if (action != 3) {
                z = false;
            } else {
                View view4 = this.mTouchedView;
                z = (view4 == null || view4 == this.mEntry) ? false : true;
                this.mTouchedView = null;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                motionEvent.setLocation((float) (view2.getWidth() / 2), (float) (view2.getHeight() / 2));
            } else {
                int i2 = this.mSlop;
                motionEvent.setLocation((float) (-(i2 * 2)), (float) (-(i2 * 2)));
            }
            return view2.dispatchTouchEvent(motionEvent);
        }

        private boolean hitThirdIcon(MotionEvent motionEvent) {
            if (this.mEntry.getLayoutDirection() == 1) {
                if (this.mThirdIcon.getVisibility() != 0 || motionEvent.getX() >= ((float) this.mThirdIcon.getRight())) {
                    return false;
                }
                return true;
            } else if (this.mThirdIcon.getVisibility() != 0 || motionEvent.getX() <= ((float) this.mThirdIcon.getLeft())) {
                return false;
            } else {
                return true;
            }
        }

        private boolean hitAlternateIcon(MotionEvent motionEvent) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mAlternateIcon.getLayoutParams();
            if (this.mEntry.getLayoutDirection() == 1) {
                if (this.mAlternateIcon.getVisibility() != 0 || motionEvent.getX() >= ((float) (this.mAlternateIcon.getRight() + layoutParams.rightMargin))) {
                    return false;
                }
                return true;
            } else if (this.mAlternateIcon.getVisibility() != 0 || motionEvent.getX() <= ((float) (this.mAlternateIcon.getLeft() - layoutParams.leftMargin))) {
                return false;
            } else {
                return true;
            }
        }
    }
}
