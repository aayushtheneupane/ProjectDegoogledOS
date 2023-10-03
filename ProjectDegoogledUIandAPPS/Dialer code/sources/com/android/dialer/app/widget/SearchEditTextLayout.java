package com.android.dialer.app.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.util.DialerUtils;

public class SearchEditTextLayout extends FrameLayout {
    private ValueAnimator animator;
    private int bottomMargin;
    /* access modifiers changed from: private */
    public View clearButtonView;
    /* access modifiers changed from: private */
    public View collapsed;
    private float collapsedElevation;
    private View collapsedSearchBox;
    private View expanded;
    protected boolean isExpanded = false;
    protected boolean isFadedOut = false;
    private int leftMargin;
    private View overflowButtonView;
    private View.OnKeyListener preImeKeyListener;
    private int rightMargin;
    private View searchIcon;
    /* access modifiers changed from: private */
    public EditText searchView;
    private int topMargin;
    private View voiceSearchButtonView;
    private boolean voiceSearchEnabled;

    public SearchEditTextLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    static /* synthetic */ void access$100(SearchEditTextLayout searchEditTextLayout) {
    }

    private void prepareAnimator() {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SearchEditTextLayout.this.setMargins(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.animator.setDuration(200);
        this.animator.start();
    }

    /* access modifiers changed from: private */
    public void setMargins(float f) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.topMargin = (int) (((float) this.topMargin) * f);
        marginLayoutParams.bottomMargin = (int) (((float) this.bottomMargin) * f);
        marginLayoutParams.leftMargin = (int) (((float) this.leftMargin) * f);
        marginLayoutParams.rightMargin = (int) (((float) this.rightMargin) * f);
        requestLayout();
    }

    private void updateVisibility(boolean z) {
        int i = 0;
        int i2 = z ? 8 : 0;
        if (!z) {
            i = 8;
        }
        this.searchIcon.setVisibility(i2);
        this.collapsedSearchBox.setVisibility(i2);
        if (this.voiceSearchEnabled) {
            this.voiceSearchButtonView.setVisibility(i2);
        } else {
            this.voiceSearchButtonView.setVisibility(8);
        }
        this.overflowButtonView.setVisibility(i2);
        if (TextUtils.isEmpty(this.searchView.getText())) {
            this.clearButtonView.setVisibility(8);
        } else {
            this.clearButtonView.setVisibility(i);
        }
    }

    public void collapse(boolean z) {
        updateVisibility(false);
        if (z) {
            AnimUtils.crossFadeViews(this.collapsed, this.expanded, 200);
            this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            prepareAnimator();
        } else {
            this.collapsed.setVisibility(0);
            this.collapsed.setAlpha(1.0f);
            setMargins(1.0f);
            this.expanded.setVisibility(8);
        }
        this.isExpanded = false;
        setElevation(this.collapsedElevation);
        setBackgroundResource(R.drawable.rounded_corner);
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        View.OnKeyListener onKeyListener = this.preImeKeyListener;
        if (onKeyListener == null || !onKeyListener.onKey(this, keyEvent.getKeyCode(), keyEvent)) {
            return super.dispatchKeyEventPreIme(keyEvent);
        }
        return true;
    }

    public void expand(boolean z, boolean z2) {
        updateVisibility(true);
        if (z) {
            AnimUtils.crossFadeViews(this.expanded, this.collapsed, 200);
            this.animator = ValueAnimator.ofFloat(new float[]{0.8f, 0.0f});
            setMargins(0.8f);
            prepareAnimator();
        } else {
            this.expanded.setVisibility(0);
            this.expanded.setAlpha(1.0f);
            setMargins(0.0f);
            this.collapsed.setVisibility(8);
        }
        int paddingTop = getPaddingTop();
        int paddingStart = getPaddingStart();
        int paddingBottom = getPaddingBottom();
        int paddingEnd = getPaddingEnd();
        setBackgroundResource(R.drawable.search_shadow);
        setElevation(0.0f);
        setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom);
        if (z2) {
            this.searchView.requestFocus();
        }
        this.isExpanded = true;
    }

    public void fadeIn() {
        AnimUtils.fadeIn(this, 200);
        this.isFadedOut = false;
    }

    public void fadeOut(AnimUtils.AnimationCallback animationCallback) {
        AnimUtils.fadeOut(this, 200, animationCallback);
        this.isFadedOut = true;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public boolean isFadedOut() {
        return this.isFadedOut;
    }

    public /* synthetic */ boolean lambda$onFinishInflate$0$SearchEditTextLayout(View view, MotionEvent motionEvent) {
        this.searchView.onTouchEvent(motionEvent);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        this.topMargin = marginLayoutParams.topMargin;
        this.bottomMargin = marginLayoutParams.bottomMargin;
        this.leftMargin = marginLayoutParams.leftMargin;
        this.rightMargin = marginLayoutParams.rightMargin;
        this.collapsedElevation = getElevation();
        this.collapsed = findViewById(R.id.search_box_collapsed);
        this.expanded = findViewById(R.id.search_box_expanded);
        this.searchView = (EditText) this.expanded.findViewById(R.id.search_view);
        this.searchIcon = findViewById(R.id.search_magnifying_glass);
        this.collapsedSearchBox = findViewById(R.id.search_box_start_search);
        this.voiceSearchButtonView = findViewById(R.id.voice_search_button);
        this.overflowButtonView = findViewById(R.id.dialtacts_options_menu_button);
        this.clearButtonView = findViewById(R.id.search_close_button);
        this.collapsed.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                SearchEditTextLayout.this.collapsed.performClick();
                return false;
            }
        });
        this.collapsed.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return SearchEditTextLayout.this.lambda$onFinishInflate$0$SearchEditTextLayout(view, motionEvent);
            }
        });
        this.searchView.setOnFocusChangeListener(new View.OnFocusChangeListener(this) {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    DialerUtils.showInputMethod(view);
                } else {
                    DialerUtils.hideInputMethod(view);
                }
            }
        });
        this.searchView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SearchEditTextLayout.access$100(SearchEditTextLayout.this);
            }
        });
        this.searchView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchEditTextLayout.this.clearButtonView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
            }
        });
        findViewById(R.id.search_close_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SearchEditTextLayout.this.searchView.setText((CharSequence) null);
            }
        });
        findViewById(R.id.search_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SearchEditTextLayout.access$100(SearchEditTextLayout.this);
            }
        });
        super.onFinishInflate();
    }

    public void setVisible(boolean z) {
        if (z) {
            setAlpha(1.0f);
            setVisibility(0);
            this.isFadedOut = false;
            return;
        }
        setAlpha(0.0f);
        setVisibility(8);
        this.isFadedOut = true;
    }

    public void setVoiceSearchEnabled(boolean z) {
        this.voiceSearchEnabled = z;
        updateVisibility(this.isExpanded);
    }
}
