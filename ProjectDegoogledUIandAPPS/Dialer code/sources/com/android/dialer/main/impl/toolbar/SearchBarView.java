package com.android.dialer.main.impl.toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.main.impl.toolbar.SearchBarListener;
import com.android.dialer.util.DialerUtils;
import com.google.common.base.Optional;

final class SearchBarView extends FrameLayout {
    private final float animationEndHeight = getContext().getResources().getDimension(R.dimen.expanded_search_bar_height);
    private final float animationStartHeight = getContext().getResources().getDimension(R.dimen.collapsed_search_bar_height);
    /* access modifiers changed from: private */
    public View clearButton;
    /* access modifiers changed from: private */
    public boolean isExpanded;
    /* access modifiers changed from: private */
    public SearchBarListener listener;
    private final float margin = getContext().getResources().getDimension(R.dimen.search_bar_margin);
    /* access modifiers changed from: private */
    public EditText searchBox;
    private View searchBoxCollapsed;
    /* access modifiers changed from: private */
    public View searchBoxExpanded;
    /* access modifiers changed from: private */
    public boolean skipLatestTextChange;

    private class SearchBoxTextWatcher implements TextWatcher {
        /* synthetic */ SearchBoxTextWatcher(C05011 r2) {
        }

        public void afterTextChanged(Editable editable) {
            SearchBarView.this.clearButton.setVisibility(TextUtils.isEmpty(editable) ? 8 : 0);
            if (SearchBarView.this.skipLatestTextChange) {
                boolean unused = SearchBarView.this.skipLatestTextChange = false;
            } else if (SearchBarView.this.listener != null) {
                SearchBarView.this.listener.onSearchQueryUpdated(editable.toString());
            } else {
                Assert.checkArgument(TextUtils.isEmpty(editable.toString()));
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public SearchBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setMargins(float f) {
        int i = (int) (this.margin * f);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.topMargin = i;
        marginLayoutParams.bottomMargin = i;
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.rightMargin = i;
        ViewGroup.LayoutParams layoutParams = this.searchBoxExpanded.getLayoutParams();
        float f2 = this.animationEndHeight;
        layoutParams.height = (int) (f2 - ((f2 - this.animationStartHeight) * f));
    }

    /* access modifiers changed from: package-private */
    public void collapse(boolean z) {
        if (this.isExpanded) {
            int i = z ? 200 : 0;
            AnimUtils.crossFadeViews(this.searchBoxCollapsed, this.searchBoxExpanded, i);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 0.8f});
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SearchBarView.this.lambda$collapse$6$SearchBarView(valueAnimator);
                }
            });
            ofFloat.setDuration((long) i);
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    SearchBarView.this.searchBox.setText("");
                    SearchBarView.this.searchBoxExpanded.setVisibility(4);
                    SearchBarView.this.setBackgroundResource(R.drawable.search_bar_background_rounded_corners);
                }

                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    DialerUtils.hideInputMethod(SearchBarView.this.searchBox);
                    boolean unused = SearchBarView.this.isExpanded = false;
                }
            });
            ofFloat.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void expand(boolean z, final Optional<String> optional, final boolean z2) {
        if (!this.isExpanded) {
            int i = z ? 200 : 0;
            this.searchBoxExpanded.setVisibility(0);
            AnimUtils.crossFadeViews(this.searchBoxExpanded, this.searchBoxCollapsed, i);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.8f, 0.0f});
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SearchBarView.this.lambda$expand$5$SearchBarView(valueAnimator);
                }
            });
            ofFloat.setDuration((long) i);
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (optional.isPresent()) {
                        SearchBarView.this.searchBox.setText((CharSequence) optional.get());
                    }
                    if (z2) {
                        SearchBarView.this.searchBox.requestFocus();
                    }
                    SearchBarView.this.setBackgroundResource(R.drawable.search_bar_background);
                }

                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    DialerUtils.showInputMethod(SearchBarView.this.searchBox);
                    boolean unused = SearchBarView.this.isExpanded = true;
                }
            });
            ofFloat.start();
        }
    }

    public String getQuery() {
        return this.searchBox.getText().toString();
    }

    public void hideKeyboard() {
        R$style.hideKeyboardFrom(getContext(), this.searchBox);
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public /* synthetic */ void lambda$collapse$6$SearchBarView(ValueAnimator valueAnimator) {
        setMargins(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$expand$5$SearchBarView(ValueAnimator valueAnimator) {
        setMargins(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$onFinishInflate$0$SearchBarView(View view) {
        this.listener.onSearchBarClicked();
    }

    public /* synthetic */ void lambda$onFinishInflate$1$SearchBarView(View view) {
        this.listener.onVoiceButtonClicked(new SearchBarListener.VoiceSearchResultCallback() {
        });
    }

    public /* synthetic */ void lambda$onFinishInflate$2$SearchBarView(View view) {
        if (this.isExpanded) {
            this.listener.onSearchBackButtonClicked();
            collapse(true);
        }
    }

    public /* synthetic */ void lambda$onFinishInflate$3$SearchBarView(View view) {
        this.searchBox.setText("");
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.clearButton = findViewById(R.id.search_clear_button);
        this.searchBox = (EditText) findViewById(R.id.search_view);
        TextView textView = (TextView) findViewById(R.id.search_box_start_search);
        this.searchBoxCollapsed = findViewById(R.id.search_box_collapsed);
        this.searchBoxExpanded = findViewById(R.id.search_box_expanded);
        setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SearchBarView.this.lambda$onFinishInflate$0$SearchBarView(view);
            }
        });
        findViewById(R.id.voice_search_button).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SearchBarView.this.lambda$onFinishInflate$1$SearchBarView(view);
            }
        });
        findViewById(R.id.search_back_button).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SearchBarView.this.lambda$onFinishInflate$2$SearchBarView(view);
            }
        });
        this.clearButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SearchBarView.this.lambda$onFinishInflate$3$SearchBarView(view);
            }
        });
        this.searchBox.addTextChangedListener(new SearchBoxTextWatcher((C05011) null));
    }

    public void setQueryWithoutUpdate(String str) {
        this.skipLatestTextChange = true;
        this.searchBox.setText(str);
        EditText editText = this.searchBox;
        editText.setSelection(editText.getText().length());
    }

    /* access modifiers changed from: package-private */
    public void setSearchBarListener(SearchBarListener searchBarListener) {
        Assert.isNotNull(searchBarListener);
        this.listener = searchBarListener;
    }

    public void showKeyboard() {
        ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInputFromWindow(this.searchBox.getApplicationWindowToken(), 2, 0);
    }
}
