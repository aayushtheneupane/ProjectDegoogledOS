package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p000v4.view.TintableBackgroundView;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* renamed from: android.support.v7.widget.AppCompatSpinner */
public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    /* access modifiers changed from: private */
    public int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    /* access modifiers changed from: private */
    public DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    /* access modifiers changed from: private */
    public final Rect mTempRect = new Rect();

    /* renamed from: android.support.v7.widget.AppCompatSpinner$DropDownAdapter */
    private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                int i = Build.VERSION.SDK_INT;
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                    }
                } else if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter2 = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                        themedSpinnerAdapter2.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return -1;
            }
            return spinnerAdapter.getItemId(i);
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        public boolean isEmpty() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            return (spinnerAdapter == null ? 0 : spinnerAdapter.getCount()) == 0;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: android.support.v7.widget.AppCompatSpinner$DropdownPopup */
    private class DropdownPopup extends ListPopupWindow {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect = new Rect();

        public DropdownPopup(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i, 0);
            setAnchorView(AppCompatSpinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterView.OnItemClickListener(AppCompatSpinner.this) {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    AppCompatSpinner.this.setSelection(i);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        DropdownPopup dropdownPopup = DropdownPopup.this;
                        AppCompatSpinner.this.performItemClick(view, i, dropdownPopup.mAdapter.getItemId(i));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void computeContentWidth() {
            int i;
            Drawable background = getBackground();
            int i2 = 0;
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.mTempRect);
                if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
                    i = AppCompatSpinner.this.mTempRect.right;
                } else {
                    i = -AppCompatSpinner.this.mTempRect.left;
                }
                i2 = i;
            } else {
                Rect access$100 = AppCompatSpinner.this.mTempRect;
                AppCompatSpinner.this.mTempRect.right = 0;
                access$100.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.mDropDownWidth == -2) {
                int compatMeasureContentWidth = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
                int i3 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left) - AppCompatSpinner.this.mTempRect.right;
                if (compatMeasureContentWidth > i3) {
                    compatMeasureContentWidth = i3;
                }
                setContentWidth(Math.max(compatMeasureContentWidth, (width - paddingLeft) - paddingRight));
            } else if (AppCompatSpinner.this.mDropDownWidth == -1) {
                setContentWidth((width - paddingLeft) - paddingRight);
            } else {
                setContentWidth(AppCompatSpinner.this.mDropDownWidth);
            }
            setHorizontalOffset(ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? ((width - paddingRight) - getWidth()) + i2 : i2 + paddingLeft);
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibleToUser(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        public void show() {
            ViewTreeObserver viewTreeObserver;
            boolean isShowing = isShowing();
            computeContentWidth();
            this.mPopup.setInputMethodMode(2);
            super.show();
            this.mDropDownList.setChoiceMode(1);
            int selectedItemPosition = AppCompatSpinner.this.getSelectedItemPosition();
            DropDownListView dropDownListView = this.mDropDownList;
            if (isShowing() && dropDownListView != null) {
                dropDownListView.setListSelectionHidden(false);
                dropDownListView.setSelection(selectedItemPosition);
                if (dropDownListView.getChoiceMode() != 0) {
                    dropDownListView.setItemChecked(selectedItemPosition, true);
                }
            }
            if (!isShowing && (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) != null) {
                final C01922 r1 = new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        DropdownPopup dropdownPopup = DropdownPopup.this;
                        if (!dropdownPopup.isVisibleToUser(AppCompatSpinner.this)) {
                            DropdownPopup.this.dismiss();
                            return;
                        }
                        DropdownPopup.this.computeContentWidth();
                        DropdownPopup.super.show();
                    }
                };
                viewTreeObserver.addOnGlobalLayoutListener(r1);
                this.mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    public void onDismiss() {
                        ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                        if (viewTreeObserver != null) {
                            viewTreeObserver.removeGlobalOnLayoutListener(r1);
                        }
                    }
                });
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
        if (r6 != null) goto L_0x0059;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = 2130969001(0x7f0401a9, float:1.7546672E38)
            r10.<init>(r11, r12, r0)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r10.mTempRect = r1
            int[] r1 = android.support.p002v7.appcompat.R$styleable.Spinner
            r2 = 0
            android.support.v7.widget.TintTypedArray r1 = android.support.p002v7.widget.TintTypedArray.obtainStyledAttributes(r11, r12, r1, r0, r2)
            android.support.v7.widget.AppCompatBackgroundHelper r3 = new android.support.v7.widget.AppCompatBackgroundHelper
            r3.<init>(r10)
            r10.mBackgroundTintHelper = r3
            r3 = 0
            r4 = 4
            int r4 = r1.getResourceId(r4, r2)
            if (r4 == 0) goto L_0x002b
            android.support.v7.view.ContextThemeWrapper r5 = new android.support.v7.view.ContextThemeWrapper
            r5.<init>(r11, r4)
            r10.mPopupContext = r5
            goto L_0x002f
        L_0x002b:
            int r4 = android.os.Build.VERSION.SDK_INT
            r10.mPopupContext = r3
        L_0x002f:
            android.content.Context r4 = r10.mPopupContext
            r5 = 1
            if (r4 == 0) goto L_0x0097
            r4 = -1
            int[] r6 = ATTRS_ANDROID_SPINNERMODE     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            android.content.res.TypedArray r6 = r11.obtainStyledAttributes(r12, r6, r0, r2)     // Catch:{ Exception -> 0x004d, all -> 0x004b }
            boolean r7 = r6.hasValue(r2)     // Catch:{ Exception -> 0x0049 }
            if (r7 == 0) goto L_0x0059
            int r4 = r6.getInt(r2, r2)     // Catch:{ Exception -> 0x0049 }
            goto L_0x0059
        L_0x0046:
            r10 = move-exception
            r3 = r6
            goto L_0x0091
        L_0x0049:
            r7 = move-exception
            goto L_0x0050
        L_0x004b:
            r10 = move-exception
            goto L_0x0091
        L_0x004d:
            r6 = move-exception
            r7 = r6
            r6 = r3
        L_0x0050:
            java.lang.String r8 = "AppCompatSpinner"
            java.lang.String r9 = "Could not read android:spinnerMode"
            android.util.Log.i(r8, r9, r7)     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x005c
        L_0x0059:
            r6.recycle()
        L_0x005c:
            if (r4 != r5) goto L_0x0097
            android.support.v7.widget.AppCompatSpinner$DropdownPopup r4 = new android.support.v7.widget.AppCompatSpinner$DropdownPopup
            android.content.Context r6 = r10.mPopupContext
            r4.<init>(r6, r12, r0)
            android.content.Context r6 = r10.mPopupContext
            int[] r7 = android.support.p002v7.appcompat.R$styleable.Spinner
            android.support.v7.widget.TintTypedArray r2 = android.support.p002v7.widget.TintTypedArray.obtainStyledAttributes(r6, r12, r7, r0, r2)
            r6 = 3
            r7 = -2
            int r6 = r2.getLayoutDimension(r6, r7)
            r10.mDropDownWidth = r6
            android.graphics.drawable.Drawable r6 = r2.getDrawable(r5)
            r4.setBackgroundDrawable(r6)
            r6 = 2
            java.lang.String r6 = r1.getString(r6)
            r4.setPromptText(r6)
            r2.recycle()
            r10.mPopup = r4
            android.support.v7.widget.AppCompatSpinner$1 r2 = new android.support.v7.widget.AppCompatSpinner$1
            r2.<init>(r10, r4)
            r10.mForwardingListener = r2
            goto L_0x0097
        L_0x0091:
            if (r3 == 0) goto L_0x0096
            r3.recycle()
        L_0x0096:
            throw r10
        L_0x0097:
            int r2 = android.support.p002v7.appcompat.R$styleable.Spinner_android_entries
            java.lang.CharSequence[] r2 = r1.getTextArray(r2)
            if (r2 == 0) goto L_0x00b0
            android.widget.ArrayAdapter r4 = new android.widget.ArrayAdapter
            r6 = 17367048(0x1090008, float:2.5162948E-38)
            r4.<init>(r11, r6, r2)
            r11 = 2131493071(0x7f0c00cf, float:1.8609612E38)
            r4.setDropDownViewResource(r11)
            r10.setAdapter((android.widget.SpinnerAdapter) r4)
        L_0x00b0:
            r1.recycle()
            r10.mPopupSet = r5
            android.widget.SpinnerAdapter r11 = r10.mTempAdapter
            if (r11 == 0) goto L_0x00be
            r10.setAdapter((android.widget.SpinnerAdapter) r11)
            r10.mTempAdapter = r3
        L_0x00be:
            android.support.v7.widget.AppCompatBackgroundHelper r10 = r10.mBackgroundTintHelper
            r10.loadFromAttributes(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: package-private */
    public int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int i2 = 0;
        View view = null;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        return i2 + rect.left + rect.right;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
    }

    public int getDropDownHorizontalOffset() {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            return dropdownPopup.getHorizontalOffset();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            return dropdownPopup.getVerticalOffset();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownVerticalOffset();
    }

    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        int i = Build.VERSION.SDK_INT;
        return super.getDropDownWidth();
    }

    public Drawable getPopupBackground() {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            return dropdownPopup.getBackground();
        }
        int i = Build.VERSION.SDK_INT;
        return super.getPopupBackground();
    }

    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        int i = Build.VERSION.SDK_INT;
        return super.getPopupContext();
    }

    public CharSequence getPrompt() {
        DropdownPopup dropdownPopup = this.mPopup;
        return dropdownPopup != null ? dropdownPopup.getHintText() : super.getPrompt();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null && dropdownPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mPopup != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener = this.mForwardingListener;
        if (forwardingListener == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup == null) {
            return super.performClick();
        }
        if (dropdownPopup.isShowing()) {
            return true;
        }
        this.mPopup.show();
        return true;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    public void setDropDownHorizontalOffset(int i) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setHorizontalOffset(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownHorizontalOffset(i);
    }

    public void setDropDownVerticalOffset(int i) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setVerticalOffset(i);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownVerticalOffset(i);
    }

    public void setDropDownWidth(int i) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i;
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        super.setDropDownWidth(i);
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.mPopup.setBackgroundDrawable(drawable);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        super.setPopupBackgroundDrawable(drawable);
    }

    public void setPopupBackgroundResource(int i) {
        Context context;
        if (this.mPopup != null) {
            context = this.mPopupContext;
        } else {
            int i2 = Build.VERSION.SDK_INT;
            context = super.getPopupContext();
        }
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setBackgroundDrawable(drawable);
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        super.setPopupBackgroundDrawable(drawable);
    }

    public void setPrompt(CharSequence charSequence) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.mPopup != null) {
            Context context = this.mPopupContext;
            if (context == null) {
                context = getContext();
            }
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter, context.getTheme()));
        }
    }
}
