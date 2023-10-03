package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.widget.ListPopupWindow */
public class ListPopupWindow implements ShowableListMenu {
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible = false;
    private View mDropDownAnchorView;
    private int mDropDownGravity = 0;
    private int mDropDownHeight = -2;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth = -2;
    private int mDropDownWindowLayoutType = 1002;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch = false;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector = new ListSelectorHider();
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum = Integer.MAX_VALUE;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition = 0;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable();
    private final PopupScrollListener mScrollListener = new PopupScrollListener();
    private final Rect mTempRect = new Rect();
    private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor();

    /* renamed from: android.support.v7.widget.ListPopupWindow$ListSelectorHider */
    private class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView != null) {
                dropDownListView.setListSelectionHidden(true);
                dropDownListView.requestLayout();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupDataSetObserver */
    private class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupScrollListener */
    private class PopupScrollListener implements AbsListView.OnScrollListener {
        PopupScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            boolean z = true;
            if (i == 1) {
                if (ListPopupWindow.this.mPopup.getInputMethodMode() != 2) {
                    z = false;
                }
                if (!z && ListPopupWindow.this.mPopup.getContentView() != null) {
                    ListPopupWindow listPopupWindow = ListPopupWindow.this;
                    listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
                    ResizePopupRunnable resizePopupRunnable = ListPopupWindow.this.mResizePopupRunnable;
                    DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
                    if (dropDownListView != null && ViewCompat.isAttachedToWindow(dropDownListView) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
                        int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
                        ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                        if (childCount <= listPopupWindow2.mListItemExpandMaximum) {
                            listPopupWindow2.mPopup.setInputMethodMode(2);
                            ListPopupWindow.this.show();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupTouchInterceptor */
    private class PopupTouchInterceptor implements View.OnTouchListener {
        PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.mPopup) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                listPopupWindow2.mHandler.removeCallbacks(listPopupWindow2.mResizePopupRunnable);
                return false;
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$ResizePopupRunnable */
    private class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView != null && ViewCompat.isAttachedToWindow(dropDownListView) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
                int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                if (childCount <= listPopupWindow.mListItemExpandMaximum) {
                    listPopupWindow.mPopup.setInputMethodMode(2);
                    ListPopupWindow.this.show();
                }
            }
        }
    }

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            sClipToWindowEnabledMethod = cls.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException unused) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        Class<PopupWindow> cls2 = PopupWindow.class;
        try {
            sGetMaxAvailableHeightMethod = cls2.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException unused2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException unused3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        this.mPopup = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.mPopup.setInputMethodMode(1);
    }

    /* access modifiers changed from: package-private */
    public DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    public void dismiss() {
        this.mPopup.dismiss();
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
        this.mPopup.setContentView((View) null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int i) {
        this.mPopup.setAnimationStyle(i);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            this.mDropDownWidth = rect.left + rect.right + i;
            return;
        }
        this.mDropDownWidth = i;
    }

    public void setDropDownGravity(int i) {
        this.mDropDownGravity = i;
    }

    public void setEpicenterBounds(Rect rect) {
        this.mEpicenterBounds = rect;
    }

    public void setHorizontalOffset(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public void setInputMethodMode(int i) {
        this.mPopup.setInputMethodMode(i);
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setPromptPosition(int i) {
        this.mPromptPosition = i;
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v44, resolved type: android.support.v7.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v45, resolved type: android.support.v7.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v49, resolved type: android.support.v7.widget.DropDownListView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void show() {
        /*
            r18 = this;
            r1 = r18
            android.support.v7.widget.DropDownListView r0 = r1.mDropDownList
            r2 = 0
            r3 = 1
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            java.lang.String r5 = "ListPopupWindow"
            r6 = -1
            if (r0 != 0) goto L_0x00bb
            android.content.Context r0 = r1.mContext
            android.support.v7.widget.ListPopupWindow$2 r7 = new android.support.v7.widget.ListPopupWindow$2
            r7.<init>()
            boolean r7 = r1.mModal
            r7 = r7 ^ r3
            android.support.v7.widget.DropDownListView r7 = r1.createDropDownListView(r0, r7)
            r1.mDropDownList = r7
            android.graphics.drawable.Drawable r7 = r1.mDropDownListHighlight
            if (r7 == 0) goto L_0x0026
            android.support.v7.widget.DropDownListView r8 = r1.mDropDownList
            r8.setSelector(r7)
        L_0x0026:
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            android.widget.ListAdapter r8 = r1.mAdapter
            r7.setAdapter(r8)
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            android.widget.AdapterView$OnItemClickListener r8 = r1.mItemClickListener
            r7.setOnItemClickListener(r8)
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            r7.setFocusable(r3)
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            r7.setFocusableInTouchMode(r3)
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            android.support.v7.widget.ListPopupWindow$3 r8 = new android.support.v7.widget.ListPopupWindow$3
            r8.<init>()
            r7.setOnItemSelectedListener(r8)
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            android.support.v7.widget.ListPopupWindow$PopupScrollListener r8 = r1.mScrollListener
            r7.setOnScrollListener(r8)
            android.widget.AdapterView$OnItemSelectedListener r7 = r1.mItemSelectedListener
            if (r7 == 0) goto L_0x0058
            android.support.v7.widget.DropDownListView r8 = r1.mDropDownList
            r8.setOnItemSelectedListener(r7)
        L_0x0058:
            android.support.v7.widget.DropDownListView r7 = r1.mDropDownList
            android.view.View r8 = r1.mPromptView
            if (r8 == 0) goto L_0x00b4
            android.widget.LinearLayout r9 = new android.widget.LinearLayout
            r9.<init>(r0)
            r9.setOrientation(r3)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r10 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r6, r2, r10)
            int r10 = r1.mPromptPosition
            if (r10 == 0) goto L_0x008d
            if (r10 == r3) goto L_0x0086
            java.lang.String r0 = "Invalid hint position "
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            int r7 = r1.mPromptPosition
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r5, r0)
            goto L_0x0093
        L_0x0086:
            r9.addView(r7, r0)
            r9.addView(r8)
            goto L_0x0093
        L_0x008d:
            r9.addView(r8)
            r9.addView(r7, r0)
        L_0x0093:
            int r0 = r1.mDropDownWidth
            if (r0 < 0) goto L_0x0099
            r7 = r4
            goto L_0x009b
        L_0x0099:
            r0 = r2
            r7 = r0
        L_0x009b:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r7)
            r8.measure(r0, r2)
            android.view.ViewGroup$LayoutParams r0 = r8.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r7 = r8.getMeasuredHeight()
            int r8 = r0.topMargin
            int r7 = r7 + r8
            int r0 = r0.bottomMargin
            int r0 = r0 + r7
            r7 = r9
            goto L_0x00b5
        L_0x00b4:
            r0 = r2
        L_0x00b5:
            android.widget.PopupWindow r8 = r1.mPopup
            r8.setContentView(r7)
            goto L_0x00d9
        L_0x00bb:
            android.widget.PopupWindow r0 = r1.mPopup
            android.view.View r0 = r0.getContentView()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r0 = r1.mPromptView
            if (r0 == 0) goto L_0x00d8
            android.view.ViewGroup$LayoutParams r7 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r7 = (android.widget.LinearLayout.LayoutParams) r7
            int r0 = r0.getMeasuredHeight()
            int r8 = r7.topMargin
            int r0 = r0 + r8
            int r7 = r7.bottomMargin
            int r0 = r0 + r7
            goto L_0x00d9
        L_0x00d8:
            r0 = r2
        L_0x00d9:
            android.widget.PopupWindow r7 = r1.mPopup
            android.graphics.drawable.Drawable r7 = r7.getBackground()
            if (r7 == 0) goto L_0x00f5
            android.graphics.Rect r8 = r1.mTempRect
            r7.getPadding(r8)
            android.graphics.Rect r7 = r1.mTempRect
            int r8 = r7.top
            int r7 = r7.bottom
            int r7 = r7 + r8
            boolean r9 = r1.mDropDownVerticalOffsetSet
            if (r9 != 0) goto L_0x00fb
            int r8 = -r8
            r1.mDropDownVerticalOffset = r8
            goto L_0x00fb
        L_0x00f5:
            android.graphics.Rect r7 = r1.mTempRect
            r7.setEmpty()
            r7 = r2
        L_0x00fb:
            android.widget.PopupWindow r8 = r1.mPopup
            int r8 = r8.getInputMethodMode()
            r9 = 2
            if (r8 != r9) goto L_0x0106
            r8 = r3
            goto L_0x0107
        L_0x0106:
            r8 = r2
        L_0x0107:
            android.view.View r10 = r1.mDropDownAnchorView
            int r11 = r1.mDropDownVerticalOffset
            java.lang.reflect.Method r12 = sGetMaxAvailableHeightMethod
            if (r12 == 0) goto L_0x0132
            android.widget.PopupWindow r13 = r1.mPopup     // Catch:{ Exception -> 0x012d }
            r14 = 3
            java.lang.Object[] r14 = new java.lang.Object[r14]     // Catch:{ Exception -> 0x012d }
            r14[r2] = r10     // Catch:{ Exception -> 0x012d }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)     // Catch:{ Exception -> 0x012d }
            r14[r3] = r15     // Catch:{ Exception -> 0x012d }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ Exception -> 0x012d }
            r14[r9] = r8     // Catch:{ Exception -> 0x012d }
            java.lang.Object r8 = r12.invoke(r13, r14)     // Catch:{ Exception -> 0x012d }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x012d }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x012d }
            goto L_0x0138
        L_0x012d:
            java.lang.String r8 = "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version."
            android.util.Log.i(r5, r8)
        L_0x0132:
            android.widget.PopupWindow r8 = r1.mPopup
            int r8 = r8.getMaxAvailableHeight(r10, r11)
        L_0x0138:
            boolean r10 = r1.mDropDownAlwaysVisible
            r11 = -2
            if (r10 != 0) goto L_0x01a0
            int r10 = r1.mDropDownHeight
            if (r10 != r6) goto L_0x0142
            goto L_0x01a0
        L_0x0142:
            int r10 = r1.mDropDownWidth
            if (r10 == r11) goto L_0x0168
            r4 = 1073741824(0x40000000, float:2.0)
            if (r10 == r6) goto L_0x014f
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r4)
            goto L_0x0180
        L_0x014f:
            android.content.Context r10 = r1.mContext
            android.content.res.Resources r10 = r10.getResources()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.widthPixels
            android.graphics.Rect r12 = r1.mTempRect
            int r13 = r12.left
            int r12 = r12.right
            int r13 = r13 + r12
            int r10 = r10 - r13
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r4)
            goto L_0x0180
        L_0x0168:
            android.content.Context r10 = r1.mContext
            android.content.res.Resources r10 = r10.getResources()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.widthPixels
            android.graphics.Rect r12 = r1.mTempRect
            int r13 = r12.left
            int r12 = r12.right
            int r13 = r13 + r12
            int r10 = r10 - r13
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r4)
        L_0x0180:
            r13 = r4
            android.support.v7.widget.DropDownListView r12 = r1.mDropDownList
            r14 = 0
            r15 = -1
            int r16 = r8 - r0
            r17 = -1
            int r4 = r12.measureHeightOfChildrenCompat(r13, r14, r15, r16, r17)
            if (r4 <= 0) goto L_0x019e
            android.support.v7.widget.DropDownListView r8 = r1.mDropDownList
            int r8 = r8.getPaddingTop()
            android.support.v7.widget.DropDownListView r10 = r1.mDropDownList
            int r10 = r10.getPaddingBottom()
            int r10 = r10 + r8
            int r10 = r10 + r7
            int r0 = r0 + r10
        L_0x019e:
            int r4 = r4 + r0
            goto L_0x01a2
        L_0x01a0:
            int r8 = r8 + r7
            r4 = r8
        L_0x01a2:
            android.widget.PopupWindow r0 = r1.mPopup
            int r0 = r0.getInputMethodMode()
            if (r0 != r9) goto L_0x01ac
            r0 = r3
            goto L_0x01ad
        L_0x01ac:
            r0 = r2
        L_0x01ad:
            android.widget.PopupWindow r7 = r1.mPopup
            int r8 = r1.mDropDownWindowLayoutType
            int r9 = android.os.Build.VERSION.SDK_INT
            r7.setWindowLayoutType(r8)
            android.widget.PopupWindow r7 = r1.mPopup
            boolean r7 = r7.isShowing()
            if (r7 == 0) goto L_0x022c
            android.view.View r5 = r1.mDropDownAnchorView
            boolean r5 = android.support.p000v4.view.ViewCompat.isAttachedToWindow(r5)
            if (r5 != 0) goto L_0x01c7
            return
        L_0x01c7:
            int r5 = r1.mDropDownWidth
            if (r5 != r6) goto L_0x01cd
            r5 = r6
            goto L_0x01d5
        L_0x01cd:
            if (r5 != r11) goto L_0x01d5
            android.view.View r5 = r1.mDropDownAnchorView
            int r5 = r5.getWidth()
        L_0x01d5:
            int r7 = r1.mDropDownHeight
            if (r7 != r6) goto L_0x0203
            if (r0 == 0) goto L_0x01dc
            goto L_0x01dd
        L_0x01dc:
            r4 = r6
        L_0x01dd:
            if (r0 == 0) goto L_0x01f1
            android.widget.PopupWindow r0 = r1.mPopup
            int r7 = r1.mDropDownWidth
            if (r7 != r6) goto L_0x01e7
            r7 = r6
            goto L_0x01e8
        L_0x01e7:
            r7 = r2
        L_0x01e8:
            r0.setWidth(r7)
            android.widget.PopupWindow r0 = r1.mPopup
            r0.setHeight(r2)
            goto L_0x0207
        L_0x01f1:
            android.widget.PopupWindow r0 = r1.mPopup
            int r7 = r1.mDropDownWidth
            if (r7 != r6) goto L_0x01f9
            r7 = r6
            goto L_0x01fa
        L_0x01f9:
            r7 = r2
        L_0x01fa:
            r0.setWidth(r7)
            android.widget.PopupWindow r0 = r1.mPopup
            r0.setHeight(r6)
            goto L_0x0207
        L_0x0203:
            if (r7 != r11) goto L_0x0206
            goto L_0x0207
        L_0x0206:
            r4 = r7
        L_0x0207:
            android.widget.PopupWindow r0 = r1.mPopup
            boolean r7 = r1.mForceIgnoreOutsideTouch
            if (r7 != 0) goto L_0x0212
            boolean r7 = r1.mDropDownAlwaysVisible
            if (r7 != 0) goto L_0x0212
            r2 = r3
        L_0x0212:
            r0.setOutsideTouchable(r2)
            android.widget.PopupWindow r7 = r1.mPopup
            android.view.View r8 = r1.mDropDownAnchorView
            int r9 = r1.mDropDownHorizontalOffset
            int r10 = r1.mDropDownVerticalOffset
            if (r5 >= 0) goto L_0x0221
            r11 = r6
            goto L_0x0222
        L_0x0221:
            r11 = r5
        L_0x0222:
            if (r4 >= 0) goto L_0x0226
            r12 = r6
            goto L_0x0227
        L_0x0226:
            r12 = r4
        L_0x0227:
            r7.update(r8, r9, r10, r11, r12)
            goto L_0x02d4
        L_0x022c:
            int r0 = r1.mDropDownWidth
            if (r0 != r6) goto L_0x0232
            r0 = r6
            goto L_0x023a
        L_0x0232:
            if (r0 != r11) goto L_0x023a
            android.view.View r0 = r1.mDropDownAnchorView
            int r0 = r0.getWidth()
        L_0x023a:
            int r7 = r1.mDropDownHeight
            if (r7 != r6) goto L_0x0240
            r4 = r6
            goto L_0x0244
        L_0x0240:
            if (r7 != r11) goto L_0x0243
            goto L_0x0244
        L_0x0243:
            r4 = r7
        L_0x0244:
            android.widget.PopupWindow r7 = r1.mPopup
            r7.setWidth(r0)
            android.widget.PopupWindow r0 = r1.mPopup
            r0.setHeight(r4)
            java.lang.reflect.Method r0 = sClipToWindowEnabledMethod
            if (r0 == 0) goto L_0x0265
            android.widget.PopupWindow r4 = r1.mPopup     // Catch:{ Exception -> 0x0260 }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0260 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0260 }
            r7[r2] = r8     // Catch:{ Exception -> 0x0260 }
            r0.invoke(r4, r7)     // Catch:{ Exception -> 0x0260 }
            goto L_0x0265
        L_0x0260:
            java.lang.String r0 = "Could not call setClipToScreenEnabled() on PopupWindow. Oh well."
            android.util.Log.i(r5, r0)
        L_0x0265:
            android.widget.PopupWindow r0 = r1.mPopup
            boolean r4 = r1.mForceIgnoreOutsideTouch
            if (r4 != 0) goto L_0x0271
            boolean r4 = r1.mDropDownAlwaysVisible
            if (r4 != 0) goto L_0x0271
            r4 = r3
            goto L_0x0272
        L_0x0271:
            r4 = r2
        L_0x0272:
            r0.setOutsideTouchable(r4)
            android.widget.PopupWindow r0 = r1.mPopup
            android.support.v7.widget.ListPopupWindow$PopupTouchInterceptor r4 = r1.mTouchInterceptor
            r0.setTouchInterceptor(r4)
            boolean r0 = r1.mOverlapAnchorSet
            if (r0 == 0) goto L_0x0289
            android.widget.PopupWindow r0 = r1.mPopup
            boolean r4 = r1.mOverlapAnchor
            int r7 = android.os.Build.VERSION.SDK_INT
            r0.setOverlapAnchor(r4)
        L_0x0289:
            java.lang.reflect.Method r0 = sSetEpicenterBoundsMethod
            if (r0 == 0) goto L_0x029f
            android.widget.PopupWindow r4 = r1.mPopup     // Catch:{ Exception -> 0x0299 }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0299 }
            android.graphics.Rect r8 = r1.mEpicenterBounds     // Catch:{ Exception -> 0x0299 }
            r7[r2] = r8     // Catch:{ Exception -> 0x0299 }
            r0.invoke(r4, r7)     // Catch:{ Exception -> 0x0299 }
            goto L_0x029f
        L_0x0299:
            r0 = move-exception
            java.lang.String r2 = "Could not invoke setEpicenterBounds on PopupWindow"
            android.util.Log.e(r5, r2, r0)
        L_0x029f:
            android.widget.PopupWindow r0 = r1.mPopup
            android.view.View r2 = r1.mDropDownAnchorView
            int r4 = r1.mDropDownHorizontalOffset
            int r5 = r1.mDropDownVerticalOffset
            int r7 = r1.mDropDownGravity
            int r8 = android.os.Build.VERSION.SDK_INT
            r0.showAsDropDown(r2, r4, r5, r7)
            android.support.v7.widget.DropDownListView r0 = r1.mDropDownList
            r0.setSelection(r6)
            boolean r0 = r1.mModal
            if (r0 == 0) goto L_0x02bf
            android.support.v7.widget.DropDownListView r0 = r1.mDropDownList
            boolean r0 = r0.isInTouchMode()
            if (r0 == 0) goto L_0x02c9
        L_0x02bf:
            android.support.v7.widget.DropDownListView r0 = r1.mDropDownList
            if (r0 == 0) goto L_0x02c9
            r0.setListSelectionHidden(r3)
            r0.requestLayout()
        L_0x02c9:
            boolean r0 = r1.mModal
            if (r0 != 0) goto L_0x02d4
            android.os.Handler r0 = r1.mHandler
            android.support.v7.widget.ListPopupWindow$ListSelectorHider r1 = r1.mHideSelector
            r0.post(r1)
        L_0x02d4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.ListPopupWindow.show():void");
    }
}
