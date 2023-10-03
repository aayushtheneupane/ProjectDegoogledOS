package com.android.contacts.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.list.ContactsRequest;
import com.android.contacts.util.MaterialColorMapUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionBarAdapter implements SearchView.OnCloseListener {
    private final ActionBar mActionBar;
    private int mActionBarAnimationDuration;
    /* access modifiers changed from: private */
    public final Activity mActivity;
    /* access modifiers changed from: private */
    public View mClearSearchView;
    /* access modifiers changed from: private */
    public Listener mListener;
    private int mMaxToolbarContentInsetStart = this.mToolbar.getContentInsetStart();
    /* access modifiers changed from: private */
    public String mQueryString;
    /* access modifiers changed from: private */
    public View mSearchContainer;
    private int mSearchHintResId;
    /* access modifiers changed from: private */
    public boolean mSearchMode;
    private EditText mSearchView;
    /* access modifiers changed from: private */
    public View mSelectionContainer;
    /* access modifiers changed from: private */
    public boolean mSelectionMode;
    private boolean mShowHomeAsUp;
    private boolean mShowHomeIcon;
    private ValueAnimator mStatusBarAnimator;
    /* access modifiers changed from: private */
    public final FrameLayout mToolBarFrame = ((FrameLayout) this.mToolbar.getParent());
    /* access modifiers changed from: private */
    public final Toolbar mToolbar;

    public interface Listener {
        void onAction(int i);

        void onUpButtonPressed();
    }

    public ActionBarAdapter(Activity activity, Listener listener, ActionBar actionBar, Toolbar toolbar, int i) {
        this.mActivity = activity;
        this.mListener = listener;
        this.mActionBar = actionBar;
        this.mToolbar = toolbar;
        this.mSearchHintResId = i;
        this.mActionBarAnimationDuration = this.mActivity.getResources().getInteger(R.integer.action_bar_animation_duration);
        setupSearchAndSelectionViews();
    }

    public void setShowHomeIcon(boolean z) {
        this.mShowHomeIcon = z;
    }

    public void setShowHomeAsUp(boolean z) {
        this.mShowHomeAsUp = z;
    }

    public View getSelectionContainer() {
        return this.mSelectionContainer;
    }

    private void setupSearchAndSelectionViews() {
        LayoutInflater layoutInflater = (LayoutInflater) this.mToolbar.getContext().getSystemService("layout_inflater");
        this.mSearchContainer = layoutInflater.inflate(R.layout.search_bar_expanded, this.mToolbar, false);
        this.mSearchContainer.setVisibility(0);
        this.mToolbar.addView(this.mSearchContainer);
        this.mSearchContainer.setBackgroundColor(this.mActivity.getResources().getColor(R.color.searchbox_background_color));
        this.mSearchView = (EditText) this.mSearchContainer.findViewById(R.id.search_view);
        this.mSearchView.setHint(this.mActivity.getString(this.mSearchHintResId));
        this.mSearchView.addTextChangedListener(new SearchTextWatcher());
        ImageButton imageButton = (ImageButton) this.mSearchContainer.findViewById(R.id.search_back_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActionBarAdapter.this.mListener != null) {
                    ActionBarAdapter.this.mListener.onUpButtonPressed();
                }
            }
        });
        imageButton.getDrawable().setAutoMirrored(true);
        this.mClearSearchView = this.mSearchContainer.findViewById(R.id.search_close_button);
        this.mClearSearchView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActionBarAdapter.this.setQueryString((String) null);
            }
        });
        this.mSelectionContainer = layoutInflater.inflate(R.layout.selection_bar, this.mToolbar, false);
        this.mToolBarFrame.addView(this.mSelectionContainer, 0);
        this.mSelectionContainer.findViewById(R.id.selection_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActionBarAdapter.this.mListener != null) {
                    ActionBarAdapter.this.mListener.onUpButtonPressed();
                }
            }
        });
    }

    public void initialize(Bundle bundle, ContactsRequest contactsRequest) {
        if (bundle == null) {
            this.mSearchMode = contactsRequest.isSearchMode();
            this.mQueryString = contactsRequest.getQueryString();
            this.mSelectionMode = false;
        } else {
            this.mSearchMode = bundle.getBoolean("navBar.searchMode");
            this.mSelectionMode = bundle.getBoolean("navBar.selectionMode");
            this.mQueryString = bundle.getString("navBar.query");
        }
        update(true);
        if (this.mSearchMode && !TextUtils.isEmpty(this.mQueryString)) {
            setQueryString(this.mQueryString);
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    private class SearchTextWatcher implements TextWatcher {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private SearchTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!charSequence.equals(ActionBarAdapter.this.mQueryString)) {
                String unused = ActionBarAdapter.this.mQueryString = charSequence.toString();
                int i4 = 0;
                if (!ActionBarAdapter.this.mSearchMode) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        ActionBarAdapter.this.setSearchMode(true);
                    }
                } else if (ActionBarAdapter.this.mListener != null) {
                    ActionBarAdapter.this.mListener.onAction(0);
                }
                View access$400 = ActionBarAdapter.this.mClearSearchView;
                if (TextUtils.isEmpty(charSequence)) {
                    i4 = 8;
                }
                access$400.setVisibility(i4);
            }
        }
    }

    public boolean isSearchMode() {
        return this.mSearchMode;
    }

    public boolean isSelectionMode() {
        return this.mSelectionMode;
    }

    public void setSearchMode(boolean z) {
        if (this.mSearchMode != z) {
            this.mSearchMode = z;
            update(false);
            EditText editText = this.mSearchView;
            if (editText != null) {
                if (this.mSearchMode) {
                    editText.setEnabled(true);
                    setFocusOnSearchView();
                } else {
                    editText.setEnabled(false);
                }
                setQueryString((String) null);
            }
        } else if (z && this.mSearchView != null) {
            setFocusOnSearchView();
        }
    }

    public void setSelectionMode(boolean z) {
        if (this.mSelectionMode != z) {
            this.mSelectionMode = z;
            update(false);
        }
    }

    public String getQueryString() {
        if (this.mSearchMode) {
            return this.mQueryString;
        }
        return null;
    }

    public void setQueryString(String str) {
        int i;
        this.mQueryString = str;
        EditText editText = this.mSearchView;
        if (editText != null) {
            editText.setText(str);
            EditText editText2 = this.mSearchView;
            if (editText2.getText() == null) {
                i = 0;
            } else {
                i = this.mSearchView.getText().length();
            }
            editText2.setSelection(i);
        }
    }

    public boolean isUpShowing() {
        return this.mSearchMode;
    }

    private void updateDisplayOptionsInner() {
        int i;
        int displayOptions = this.mActionBar.getDisplayOptions() & 14;
        boolean z = this.mSearchMode || this.mSelectionMode;
        if (!this.mShowHomeIcon || z) {
            i = 0;
        } else {
            i = 2;
            if (this.mShowHomeAsUp) {
                i = 6;
            }
        }
        if (this.mSearchMode && !this.mSelectionMode) {
            Toolbar toolbar = this.mToolbar;
            toolbar.setContentInsetsRelative(0, toolbar.getContentInsetEnd());
        }
        if (!z) {
            i |= 8;
            Toolbar toolbar2 = this.mToolbar;
            toolbar2.setContentInsetsRelative(this.mMaxToolbarContentInsetStart, toolbar2.getContentInsetEnd());
            this.mToolbar.setNavigationIcon((int) R.drawable.quantum_ic_menu_vd_theme_24);
        } else {
            this.mToolbar.setNavigationIcon((Drawable) null);
        }
        if (this.mSelectionMode) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mToolbar.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.gravity = 8388613;
            this.mToolbar.setLayoutParams(layoutParams);
        } else {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mToolbar.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.gravity = 8388613;
            this.mToolbar.setLayoutParams(layoutParams2);
        }
        if (displayOptions != i) {
            this.mActionBar.setDisplayOptions(i, 14);
        }
    }

    private void update(boolean z) {
        updateOverflowButtonColor();
        boolean z2 = true;
        boolean z3 = (this.mSelectionContainer.getParent() == null) == this.mSelectionMode;
        boolean z4 = (this.mSearchMode && z3) || (this.mSearchMode && this.mSelectionMode);
        final boolean z5 = (this.mSearchContainer.getParent() == null) == this.mSearchMode;
        boolean z6 = z5 || z3;
        this.mToolBarFrame.setBackgroundColor(MaterialColorMapUtils.getToolBarColor(this.mActivity));
        if (!z3 || z5) {
            z2 = false;
        }
        updateStatusBarColor(z2);
        if (!z && !z4) {
            if (z3) {
                if (this.mSelectionMode) {
                    addSelectionContainer();
                    this.mSelectionContainer.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
                    this.mSelectionContainer.animate().alpha(1.0f).setDuration((long) this.mActionBarAnimationDuration);
                    updateDisplayOptions(z5);
                } else {
                    Listener listener = this.mListener;
                    if (listener != null) {
                        listener.onAction(4);
                    }
                    this.mSelectionContainer.setAlpha(1.0f);
                    this.mSelectionContainer.animate().alpha(ContactPhotoManager.OFFSET_DEFAULT).setDuration((long) this.mActionBarAnimationDuration).withEndAction(new Runnable() {
                        public void run() {
                            ActionBarAdapter.this.updateDisplayOptions(z5);
                            ActionBarAdapter.this.mToolBarFrame.removeView(ActionBarAdapter.this.mSelectionContainer);
                        }
                    });
                }
            }
            if (!z5) {
                return;
            }
            if (this.mSearchMode) {
                addSearchContainer();
                this.mSearchContainer.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
                this.mSearchContainer.animate().alpha(1.0f).setDuration((long) this.mActionBarAnimationDuration);
                updateDisplayOptions(z5);
                return;
            }
            this.mSearchContainer.setAlpha(1.0f);
            this.mSearchContainer.animate().alpha(ContactPhotoManager.OFFSET_DEFAULT).setDuration((long) this.mActionBarAnimationDuration).withEndAction(new Runnable() {
                public void run() {
                    ActionBarAdapter.this.updateDisplayOptions(z5);
                    ActionBarAdapter.this.mToolbar.removeView(ActionBarAdapter.this.mSearchContainer);
                }
            });
        } else if (z6 || z4) {
            this.mToolbar.removeView(this.mSearchContainer);
            this.mToolBarFrame.removeView(this.mSelectionContainer);
            if (this.mSelectionMode) {
                addSelectionContainer();
            } else if (this.mSearchMode) {
                addSearchContainer();
            }
            updateDisplayOptions(z5);
        }
    }

    public void updateOverflowButtonColor() {
        final String string = this.mActivity.getResources().getString(R.string.abc_action_menu_overflow_description);
        final ViewGroup viewGroup = (ViewGroup) this.mActivity.getWindow().getDecorView();
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int i;
                ArrayList arrayList = new ArrayList();
                viewGroup.findViewsWithText(arrayList, string, 2);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    View view = (View) it.next();
                    if (view instanceof ImageView) {
                        ImageView imageView = (ImageView) view;
                        if (ActionBarAdapter.this.mSelectionMode) {
                            i = ActionBarAdapter.this.mActivity.getResources().getColor(R.color.actionbar_color_grey_solid);
                        } else {
                            i = ActionBarAdapter.this.mActivity.getResources().getColor(R.color.actionbar_text_color);
                        }
                        imageView.setImageTintList(ColorStateList.valueOf(i));
                    }
                }
                viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void setSelectionCount(int i) {
        TextView textView = (TextView) this.mSelectionContainer.findViewById(R.id.selection_count_text);
        if (i == 0) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
        textView.setText(String.valueOf(i));
    }

    public void setActionBarTitle(String str) {
        TextView textView = (TextView) this.mSelectionContainer.findViewById(R.id.selection_count_text);
        textView.setVisibility(0);
        textView.setText(str);
    }

    private void updateStatusBarColor(boolean z) {
        if (CompatUtils.isLollipopCompatible()) {
            if (this.mSelectionMode) {
                runStatusBarAnimation(ContextCompat.getColor(this.mActivity, R.color.contextual_selection_bar_status_bar_color));
            } else if (z) {
                runStatusBarAnimation(MaterialColorMapUtils.getStatusBarColor(this.mActivity));
            } else {
                Activity activity = this.mActivity;
                if (activity instanceof PeopleActivity) {
                    ((PeopleActivity) activity).updateStatusBarBackground();
                }
            }
        }
    }

    private void runStatusBarAnimation(int i) {
        final Window window = this.mActivity.getWindow();
        if (window.getStatusBarColor() != i) {
            ValueAnimator valueAnimator = this.mStatusBarAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mStatusBarAnimator.cancel();
            }
            int statusBarColor = window.getStatusBarColor();
            this.mStatusBarAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(statusBarColor), Integer.valueOf(i)});
            this.mStatusBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    window.setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            this.mStatusBarAnimator.setDuration((long) this.mActionBarAnimationDuration);
            this.mStatusBarAnimator.setStartDelay(0);
            this.mStatusBarAnimator.start();
        }
    }

    private void addSearchContainer() {
        this.mToolbar.removeView(this.mSearchContainer);
        this.mToolbar.addView(this.mSearchContainer);
        this.mSearchContainer.setAlpha(1.0f);
    }

    private void addSelectionContainer() {
        this.mToolBarFrame.removeView(this.mSelectionContainer);
        this.mToolBarFrame.addView(this.mSelectionContainer, 0);
        this.mSelectionContainer.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public void updateDisplayOptions(boolean z) {
        if (this.mSearchMode && !this.mSelectionMode) {
            setFocusOnSearchView();
            if (z) {
                Editable text = this.mSearchView.getText();
                if (!TextUtils.isEmpty(text)) {
                    this.mSearchView.setText(text);
                }
            }
        }
        Listener listener = this.mListener;
        if (listener != null) {
            if (this.mSearchMode) {
                listener.onAction(1);
            }
            if (this.mSelectionMode) {
                this.mListener.onAction(2);
            }
            if (!this.mSearchMode && !this.mSelectionMode) {
                this.mListener.onAction(3);
            }
        }
        updateDisplayOptionsInner();
    }

    public boolean onClose() {
        setSearchMode(false);
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("navBar.searchMode", this.mSearchMode);
        bundle.putBoolean("navBar.selectionMode", this.mSelectionMode);
        bundle.putString("navBar.query", this.mQueryString);
    }

    public void setFocusOnSearchView() {
        this.mSearchView.requestFocus();
        showInputMethod(this.mSearchView);
    }

    private void showInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mActivity.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }
}
