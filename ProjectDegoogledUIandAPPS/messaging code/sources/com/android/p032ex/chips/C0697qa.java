package com.android.p032ex.chips;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.QwertyKeyListener;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.p032ex.chips.p033a.C0660b;
import com.android.p032ex.chips.p033a.C0661c;
import com.android.p032ex.chips.p033a.C0662d;
import com.android.p032ex.chips.p033a.C0664f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.ex.chips.qa */
public class C0697qa extends MultiAutoCompleteTextView implements AdapterView.OnItemClickListener, ActionMode.Callback, C0644L, GestureDetector.OnGestureListener, TextView.OnEditorActionListener, C0702t, C0703u {
    private static final int AVATAR_POSITION_END = 0;
    private static final int AVATAR_POSITION_START = 1;
    static final int CHIP_LIMIT = 2;
    private static final char COMMIT_CHAR_COMMA = ',';
    private static final char COMMIT_CHAR_SEMICOLON = ';';
    private static final char COMMIT_CHAR_SPACE = ' ';
    /* access modifiers changed from: private */
    public static final int DISMISS = "dismiss".hashCode();
    private static final long DISMISS_DELAY = 300;
    private static final int MAX_CHIPS_PARSED = 50;
    private static final String SEPARATOR = (String.valueOf(COMMIT_CHAR_COMMA) + String.valueOf(COMMIT_CHAR_SPACE));
    public static final String STATE_CURRENT_WARNING_TEXT = "savedCurrentWarningText";
    public static final String STATE_TEXT_VIEW = "savedTextView";
    private static final String TAG = "RecipientEditTextView";
    private final Runnable mAddTextWatcher = new C0650S(this);
    private ListPopupWindow mAddressPopup;
    /* access modifiers changed from: private */
    public View mAlternatePopupAnchor;
    /* access modifiers changed from: private */
    public AdapterView.OnItemClickListener mAlternatesListener;
    /* access modifiers changed from: private */
    public ListPopupWindow mAlternatesPopup;
    /* access modifiers changed from: private */
    public boolean mAttachedToWindow;
    private int mAvatarPosition;
    /* access modifiers changed from: private */
    public int mCheckedItem;
    private Drawable mChipBackground = null;
    private Drawable mChipDelete = null;
    private float mChipFontSize;
    private float mChipHeight;
    private int mChipTextEndPadding;
    private int mChipTextStartPadding;
    /* access modifiers changed from: private */
    public final int[] mCoords = new int[2];
    /* access modifiers changed from: private */
    public int mCurrentSuggestionCount;
    /* access modifiers changed from: private */
    public String mCurrentWarningText = "";
    /* access modifiers changed from: private */
    public Bitmap mDefaultContactPhoto;
    private Runnable mDelayedShrink = new C0652U(this);
    private boolean mDisableDelete;
    private boolean mDragEnabled = false;
    /* access modifiers changed from: private */
    public View mDropdownAnchor = this;
    protected C0704v mDropdownChipLayouter;
    private GestureDetector mGestureDetector;
    private Runnable mHandlePendingChips = new C0651T(this);
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public ArrayList mHiddenSpans;
    /* access modifiers changed from: private */
    public C0675fa mIndividualReplacements;
    private Drawable mInvalidChipBackground;
    /* access modifiers changed from: private */
    public float mLineSpacingExtra;
    private int mMaxLines;
    /* access modifiers changed from: private */
    public C0662d mMoreChip;
    private TextView mMoreItem;
    /* access modifiers changed from: private */
    public boolean mNoChipMode = false;
    final ArrayList mPendingChips = new ArrayList();
    private int mPendingChipsCount = 0;
    private C0679ha mPermissionsRequestItemClickedListener;
    private C0681ia mRecipientChipAddedListener;
    private C0683ja mRecipientChipDeletedListener;
    private C0687la mRecipientEntryItemClickedListener;
    /* access modifiers changed from: private */
    public final Rect mRect = new Rect();
    private boolean mRequiresShrinkWhenNotGone = false;
    private ScrollView mScrollView;
    /* access modifiers changed from: private */
    public C0660b mSelectedChip;
    private boolean mShouldShrink = true;
    ArrayList mTemporaryRecipients;
    private final int mTextHeight;
    /* access modifiers changed from: private */
    public TextWatcher mTextWatcher;
    /* access modifiers changed from: private */
    public MultiAutoCompleteTextView.Tokenizer mTokenizer;
    private boolean mTriedGettingScrollView;
    private int mUnselectedChipBackgroundColor;
    private int mUnselectedChipTextColor;
    private Set mUntrustedAddresses = new HashSet();
    private AutoCompleteTextView.Validator mValidator;
    private Bitmap mWarningIcon;
    private int mWarningIconHeight;
    private String mWarningTextTemplate = "";
    private String mWarningTitle = "";
    private Paint mWorkPaint = new Paint();

    public C0697qa(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setChipDimensions(context, attributeSet);
        this.mTextHeight = calculateTextHeight();
        this.mAlternatesPopup = new ListPopupWindow(context);
        setupPopupWindow(this.mAlternatesPopup);
        this.mAddressPopup = new ListPopupWindow(context);
        setupPopupWindow(this.mAddressPopup);
        this.mAlternatesListener = new C0653V(this);
        setInputType(getInputType() | 524288);
        setOnItemClickListener(this);
        setCustomSelectionActionModeCallback(this);
        this.mHandler = new C0654W(this);
        this.mTextWatcher = new C0695pa(this, (C0650S) null);
        addTextChangedListener(this.mTextWatcher);
        this.mGestureDetector = new GestureDetector(context, this);
        setOnEditorActionListener(this);
        setDropdownChipLayouter(new C0704v(LayoutInflater.from(context), context));
    }

    static /* synthetic */ C0683ja access$3100(C0697qa qaVar) {
        return null;
    }

    private boolean alreadyHasChip(int i, int i2) {
        if (this.mNoChipMode) {
            return true;
        }
        C0660b[] bVarArr = (C0660b[]) getSpannable().getSpans(i, i2, C0660b.class);
        if (bVarArr == null || bVarArr.length <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void announceForAccessibilityCompat(String str) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            int i = Build.VERSION.SDK_INT;
            ViewParent parent = getParent();
            if (parent != null) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
                onInitializeAccessibilityEvent(obtain);
                obtain.getText().add(str);
                obtain.setContentDescription((CharSequence) null);
                parent.requestSendAccessibilityEvent(this, obtain);
            }
        }
    }

    private float calculateAvailableWidth() {
        return (float) ((((getWidth() - getPaddingLeft()) - getPaddingRight()) - this.mChipTextStartPadding) - this.mChipTextEndPadding);
    }

    /* access modifiers changed from: private */
    public int calculateOffsetFromBottomToTop(int i) {
        return -((int) ((((this.mLineSpacingExtra * 2.0f) + this.mChipHeight) * ((float) Math.abs(getLineCount() - i))) + ((float) getPaddingBottom())));
    }

    private int calculateTextHeight() {
        TextPaint paint = getPaint();
        this.mRect.setEmpty();
        paint.getTextBounds("a", 0, 1, this.mRect);
        Rect rect = this.mRect;
        rect.left = 0;
        rect.right = 0;
        return rect.height();
    }

    private void checkChipWidths() {
        C0660b[] sortedRecipients = getSortedRecipients();
        if (sortedRecipients != null) {
            for (C0660b bVar : sortedRecipients) {
                Rect bounds = bVar.getBounds();
                if (getWidth() > 0 && bounds.right - bounds.left > (getWidth() - getPaddingLeft()) - getPaddingRight()) {
                    replaceChip(bVar, bVar.getEntry());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1.mHiddenSpans;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean chipsPending() {
        /*
            r1 = this;
            int r0 = r1.mPendingChipsCount
            if (r0 > 0) goto L_0x0011
            java.util.ArrayList r1 = r1.mHiddenSpans
            if (r1 == 0) goto L_0x000f
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            r1 = 0
            goto L_0x0012
        L_0x0011:
            r1 = 1
        L_0x0012:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0697qa.chipsPending():boolean");
    }

    /* access modifiers changed from: private */
    public void commitByCharacter() {
        if (this.mTokenizer != null) {
            Editable text = getText();
            int selectionEnd = getSelectionEnd();
            int findTokenStart = this.mTokenizer.findTokenStart(text, selectionEnd);
            if (shouldCreateChip(findTokenStart, selectionEnd)) {
                commitChip(findTokenStart, selectionEnd, text);
            }
            setSelection(getText().length());
        }
    }

    private boolean commitChip(int i, int i2, Editable editable) {
        char charAt;
        int positionOfFirstEntryWithTypePerson = positionOfFirstEntryWithTypePerson();
        if (positionOfFirstEntryWithTypePerson == -1 || !enoughToFilter() || i2 != getSelectionEnd() || isPhoneQuery() || isValidEmailAddress(editable.toString().substring(i, i2).trim())) {
            int findTokenEnd = this.mTokenizer.findTokenEnd(editable, i);
            int i3 = findTokenEnd + 1;
            if (editable.length() > i3 && ((charAt = editable.charAt(i3)) == ',' || charAt == ';')) {
                findTokenEnd = i3;
            }
            String trim = editable.toString().substring(i, findTokenEnd).trim();
            clearComposingText();
            if (trim.length() <= 0 || trim.equals(" ")) {
                return false;
            }
            C0699ra createTokenizedEntry = createTokenizedEntry(trim);
            if (createTokenizedEntry != null) {
                QwertyKeyListener.markAsReplaced(editable, i, i2, "");
                CharSequence createChip = createChip(createTokenizedEntry);
                if (createChip != null && i > -1 && i2 > -1) {
                    editable.replace(i, i2, createChip);
                }
            }
            if (i2 == getSelectionEnd()) {
                dismissDropDown();
            }
            sanitizeBetween();
            return true;
        }
        int listSelection = getListSelection();
        if (listSelection == -1 || !isEntryAtPositionTypePerson(listSelection)) {
            submitItemAtPosition(positionOfFirstEntryWithTypePerson);
        } else {
            submitItemAtPosition(listSelection);
        }
        dismissDropDown();
        return true;
    }

    private boolean commitDefault() {
        if (this.mTokenizer == null) {
            return false;
        }
        Editable text = getText();
        int selectionEnd = getSelectionEnd();
        int findTokenStart = this.mTokenizer.findTokenStart(text, selectionEnd);
        if (!shouldCreateChip(findTokenStart, selectionEnd)) {
            return false;
        }
        int movePastTerminators = movePastTerminators(this.mTokenizer.findTokenEnd(getText(), findTokenStart));
        if (movePastTerminators == getSelectionEnd()) {
            return commitChip(findTokenStart, selectionEnd, text);
        }
        handleEdit(findTokenStart, movePastTerminators);
        return true;
    }

    /* access modifiers changed from: private */
    public C0660b constructChipSpan(C0699ra raVar) {
        TextPaint paint = getPaint();
        float textSize = paint.getTextSize();
        int color = paint.getColor();
        C0669ca createChipBitmap = createChipBitmap(raVar, paint);
        Rect rect = new Rect(0, 0, 0, 0);
        if (this.mUntrustedAddresses.contains(raVar.getDestination())) {
            drawWarningIcon(createChipBitmap).round(rect);
        }
        Bitmap bitmap = createChipBitmap.bitmap;
        int width = bitmap != null ? bitmap.getWidth() : 0;
        int height = bitmap != null ? bitmap.getHeight() : 0;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        bitmapDrawable.setBounds(0, 0, width, height);
        C0664f fVar = new C0664f(bitmapDrawable, raVar);
        fVar.mo5477a(this.mLineSpacingExtra);
        paint.setTextSize(textSize);
        paint.setColor(color);
        fVar.mo5491a(rect);
        return fVar;
    }

    private StateListDrawable constructStateListDeleteDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (!this.mDisableDelete) {
            stateListDrawable.addState(new int[]{16843518}, this.mChipDelete);
        }
        stateListDrawable.addState(new int[0], (Drawable) null);
        return stateListDrawable;
    }

    private CharSequence createChip(C0699ra raVar) {
        String createAddressText = createAddressText(raVar);
        if (TextUtils.isEmpty(createAddressText)) {
            return null;
        }
        int length = createAddressText.length() - 1;
        SpannableString spannableString = new SpannableString(createAddressText);
        if (!this.mNoChipMode) {
            try {
                C0660b constructChipSpan = constructChipSpan(raVar);
                spannableString.setSpan(constructChipSpan, 0, length, 33);
                constructChipSpan.mo5464a(spannableString.toString());
            } catch (NullPointerException e) {
                Log.e(TAG, e.getMessage(), e);
                return null;
            }
        }
        onChipCreated(raVar);
        return spannableString;
    }

    private C0669ca createChipBitmap(C0699ra raVar, TextPaint textPaint) {
        textPaint.setColor(getDefaultChipTextColor(raVar));
        C0669ca createChipBitmap = createChipBitmap(raVar, textPaint, getChipBackground(raVar), getDefaultChipBackgroundColor(raVar));
        if (createChipBitmap.f781ov) {
            loadAvatarIcon(raVar, createChipBitmap);
        }
        return createChipBitmap;
    }

    private C0677ga createMoreSpan(int i) {
        String format = String.format(this.mMoreItem.getText().toString(), new Object[]{Integer.valueOf(i)});
        this.mWorkPaint.set(getPaint());
        this.mWorkPaint.setTextSize(this.mMoreItem.getTextSize());
        this.mWorkPaint.setColor(this.mMoreItem.getCurrentTextColor());
        int paddingRight = this.mMoreItem.getPaddingRight() + this.mMoreItem.getPaddingLeft() + ((int) this.mWorkPaint.measureText(format));
        int i2 = (int) this.mChipHeight;
        Bitmap createBitmap = Bitmap.createBitmap(paddingRight, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Layout layout = getLayout();
        canvas.drawText(format, 0, format.length(), 0.0f, (float) (layout != null ? i2 - layout.getLineDescent(0) : i2), this.mWorkPaint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), createBitmap);
        bitmapDrawable.setBounds(0, 0, paddingRight, i2);
        return new C0677ga(this, bitmapDrawable);
    }

    private ListAdapter createSingleAddressAdapter(C0660b bVar) {
        return new C0701sa(getContext(), bVar.getEntry(), this.mDropdownChipLayouter, constructStateListDeleteDrawable());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        r5 = r5.mValidator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.p032ex.chips.C0699ra createValidatedEntry(com.android.p032ex.chips.C0699ra r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0004
            r5 = 0
            return r5
        L_0x0004:
            java.lang.String r0 = r6.getDestination()
            boolean r1 = r5.isPhoneQuery()
            if (r1 != 0) goto L_0x0025
            long r1 = r6.getContactId()
            r3 = -2
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0025
            java.lang.String r5 = r6.getDisplayName()
            boolean r6 = r6.isValid()
            com.android.ex.chips.ra r6 = com.android.p032ex.chips.C0699ra.m1081a(r5, r0, r6)
            goto L_0x0055
        L_0x0025:
            long r1 = r6.getContactId()
            boolean r1 = com.android.p032ex.chips.C0699ra.m1084b(r1)
            if (r1 == 0) goto L_0x0055
            java.lang.String r1 = r6.getDisplayName()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x004d
            java.lang.String r1 = r6.getDisplayName()
            boolean r1 = android.text.TextUtils.equals(r1, r0)
            if (r1 != 0) goto L_0x004d
            android.widget.AutoCompleteTextView$Validator r5 = r5.mValidator
            if (r5 == 0) goto L_0x0055
            boolean r5 = r5.isValid(r0)
            if (r5 != 0) goto L_0x0055
        L_0x004d:
            boolean r5 = r6.isValid()
            com.android.ex.chips.ra r6 = com.android.p032ex.chips.C0699ra.m1083b(r0, r5)
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0697qa.createValidatedEntry(com.android.ex.chips.ra):com.android.ex.chips.ra");
    }

    private void dismissPopups() {
        ListPopupWindow listPopupWindow = this.mAlternatesPopup;
        if (listPopupWindow != null && listPopupWindow.isShowing()) {
            this.mAlternatesPopup.dismiss();
        }
        ListPopupWindow listPopupWindow2 = this.mAddressPopup;
        if (listPopupWindow2 != null && listPopupWindow2.isShowing()) {
            this.mAddressPopup.dismiss();
        }
        setSelection(getText().length());
    }

    /* access modifiers changed from: private */
    public void drawIcon(C0669ca caVar, Bitmap bitmap) {
        if (bitmap != null) {
            drawCircularIconOnCanvas(bitmap, new Canvas(caVar.bitmap), new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight()), new RectF(caVar.left, caVar.top, caVar.right, caVar.bottom));
        }
    }

    private void drawRectanglularIconOnCanvas(Bitmap bitmap, Canvas canvas, RectF rectF, RectF rectF2) {
        setWorkPaintForIcon(bitmap, rectF, rectF2);
        canvas.drawRect(rectF2, this.mWorkPaint);
        setWorkPaintForBorder(1.0f);
        canvas.drawRect(rectF2, this.mWorkPaint);
        this.mWorkPaint.reset();
    }

    private RectF drawWarningIcon(C0669ca caVar) {
        if (this.mWarningIcon == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        Canvas canvas = new Canvas(caVar.bitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.mWarningIcon.getWidth(), (float) this.mWarningIcon.getHeight());
        RectF rectF2 = new RectF(caVar.f782pv, caVar.f783qv, caVar.f784rv, caVar.f785sv);
        drawRectanglularIconOnCanvas(this.mWarningIcon, canvas, rectF, rectF2);
        return rectF2;
    }

    private CharSequence ellipsizeText(CharSequence charSequence, TextPaint textPaint, float f) {
        textPaint.setTextSize(this.mChipFontSize);
        if (f <= 0.0f && Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Max width is negative: " + f);
        }
        return TextUtils.ellipsize(charSequence, textPaint, f, TextUtils.TruncateAt.END);
    }

    private void expand() {
        if (this.mShouldShrink) {
            setMaxLines(Integer.MAX_VALUE);
        }
        removeMoreChip();
        setCursorVisible(true);
        Editable text = getText();
        setSelection((text == null || text.length() <= 0) ? 0 : text.length());
        ArrayList arrayList = this.mTemporaryRecipients;
        if (arrayList != null && arrayList.size() > 0) {
            new C0693oa(this, (C0650S) null).execute(new Void[0]);
            this.mTemporaryRecipients = null;
        }
    }

    private C0660b findChip(int i) {
        Spannable spannable = getSpannable();
        for (C0660b bVar : (C0660b[]) spannable.getSpans(0, spannable.length(), C0660b.class)) {
            int chipStart = getChipStart(bVar);
            int chipEnd = getChipEnd(bVar);
            if (i >= chipStart && i <= chipEnd) {
                return bVar;
            }
        }
        return null;
    }

    private static int findText(Editable editable, int i) {
        if (editable.charAt(i) != ' ') {
            return i;
        }
        return -1;
    }

    private boolean focusNext() {
        View focusSearch = focusSearch(130);
        if (focusSearch == null) {
            return false;
        }
        focusSearch.requestFocus();
        return true;
    }

    private int getChipEnd(C0660b bVar) {
        return getSpannable().getSpanEnd(bVar);
    }

    private int getChipStart(C0660b bVar) {
        return getSpannable().getSpanStart(bVar);
    }

    private int getDefaultChipBackgroundColor(C0699ra raVar) {
        if (raVar.isValid()) {
            return this.mUnselectedChipBackgroundColor;
        }
        return getResources().getColor(C0636D.chip_background_invalid);
    }

    private int getDefaultChipTextColor(C0699ra raVar) {
        if (raVar.isValid()) {
            return this.mUnselectedChipTextColor;
        }
        return getResources().getColor(17170444);
    }

    private void handleEdit(int i, int i2) {
        if (i == -1 || i2 == -1) {
            dismissDropDown();
            return;
        }
        Editable text = getText();
        setSelection(i2);
        String substring = getText().toString().substring(i, i2);
        if (!TextUtils.isEmpty(substring)) {
            C0699ra b = C0699ra.m1083b(substring, isValid(substring));
            QwertyKeyListener.markAsReplaced(text, i, i2, "");
            CharSequence createChip = createChip(b);
            int selectionEnd = getSelectionEnd();
            if (createChip != null && i > -1 && selectionEnd > -1) {
                text.replace(i, selectionEnd, createChip);
            }
        }
        dismissDropDown();
    }

    private void handlePasteAndReplace() {
        ArrayList handlePaste = handlePaste();
        if (handlePaste != null && handlePaste.size() > 0) {
            new C0675fa(this, (C0650S) null).execute(new ArrayList[]{handlePaste});
        }
    }

    private boolean isEntryAtPositionTypePerson(int i) {
        return getAdapter().getItem(i).getEntryType() == 0;
    }

    private boolean isTouchExplorationEnabled() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityManager) getContext().getSystemService("accessibility")).isTouchExplorationEnabled();
    }

    private boolean isValid(String str) {
        AutoCompleteTextView.Validator validator = this.mValidator;
        if (validator == null) {
            return true;
        }
        return validator.isValid(str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.mValidator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isValidEmailAddress(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0012
            android.widget.AutoCompleteTextView$Validator r1 = r1.mValidator
            if (r1 == 0) goto L_0x0012
            boolean r1 = r1.isValid(r2)
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0697qa.isValidEmailAddress(java.lang.String):boolean");
    }

    private void loadAvatarIcon(C0699ra raVar, C0669ca caVar) {
        long contactId = raVar.getContactId();
        boolean z = true;
        if (!isPhoneQuery() ? contactId == -1 || contactId == -2 : contactId == -1) {
            z = false;
        }
        if (z) {
            byte[] xd = raVar.mo5665xd();
            if (xd == null) {
                getAdapter().fetchPhoto(raVar, new C0665aa(this, raVar, caVar));
            } else {
                drawIcon(caVar, BitmapFactory.decodeByteArray(xd, 0, xd.length));
            }
        }
    }

    private int positionOfFirstEntryWithTypePerson() {
        C0684k adapter = getAdapter();
        int count = adapter != null ? adapter.getCount() : 0;
        for (int i = 0; i < count; i++) {
            if (isEntryAtPositionTypePerson(i)) {
                return i;
            }
        }
        return -1;
    }

    private void postHandlePendingChips() {
        this.mHandler.removeCallbacks(this.mHandlePendingChips);
        this.mHandler.post(this.mHandlePendingChips);
    }

    private int putOffsetInRange(float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        return putOffsetInRange(getOffsetForPosition(f, f2));
    }

    private void selectChip(C0660b bVar) {
        boolean z = true;
        if (shouldShowEditableText(bVar)) {
            CharSequence value = bVar.getValue();
            Editable text = getText();
            Spannable spannable = getSpannable();
            int spanStart = spannable.getSpanStart(bVar);
            int spanEnd = spannable.getSpanEnd(bVar);
            spannable.removeSpan(bVar);
            if (spanEnd - spanStart == text.length() - 1) {
                spanEnd++;
            }
            text.delete(spanStart, spanEnd);
            setCursorVisible(true);
            setSelection(text.length());
            text.append(value);
            this.mSelectedChip = constructChipSpan(C0699ra.m1083b((String) value, isValid(value.toString())));
            boolean z2 = this.mNoChipMode;
            return;
        }
        if (bVar.getContactId() != -2 && !getAdapter().forceShowAddress()) {
            z = false;
        }
        if ((!z || !this.mNoChipMode) && !isTouchExplorationEnabled()) {
            this.mSelectedChip = bVar;
            setSelection(getText().getSpanEnd(this.mSelectedChip));
            setCursorVisible(false);
            if (z) {
                showAddress(bVar, this.mAddressPopup);
            } else {
                showAlternates(bVar, this.mAlternatesPopup);
            }
        }
    }

    private void setChipDimensions(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0643K.RecipientEditTextView, 0, 0);
        Resources resources = getContext().getResources();
        this.mChipBackground = obtainStyledAttributes.getDrawable(C0643K.RecipientEditTextView_chipBackground);
        this.mInvalidChipBackground = obtainStyledAttributes.getDrawable(C0643K.RecipientEditTextView_invalidChipBackground);
        this.mChipDelete = obtainStyledAttributes.getDrawable(C0643K.RecipientEditTextView_chipDelete);
        if (this.mChipDelete == null) {
            this.mChipDelete = resources.getDrawable(C0638F.ic_cancel_wht_24dp);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0643K.RecipientEditTextView_chipPadding, -1);
        this.mChipTextEndPadding = dimensionPixelSize;
        this.mChipTextStartPadding = dimensionPixelSize;
        if (this.mChipTextStartPadding == -1) {
            int dimension = (int) resources.getDimension(C0637E.chip_padding);
            this.mChipTextEndPadding = dimension;
            this.mChipTextStartPadding = dimension;
        }
        int dimension2 = (int) resources.getDimension(C0637E.chip_padding_start);
        if (dimension2 >= 0) {
            this.mChipTextStartPadding = dimension2;
        }
        int dimension3 = (int) resources.getDimension(C0637E.chip_padding_end);
        if (dimension3 >= 0) {
            this.mChipTextEndPadding = dimension3;
        }
        this.mDefaultContactPhoto = BitmapFactory.decodeResource(resources, C0638F.ic_contact_picture);
        this.mMoreItem = (TextView) LayoutInflater.from(getContext()).inflate(C0641I.more_item, (ViewGroup) null);
        this.mChipHeight = (float) obtainStyledAttributes.getDimensionPixelSize(C0643K.RecipientEditTextView_chipHeight, -1);
        if (this.mChipHeight == -1.0f) {
            this.mChipHeight = resources.getDimension(C0637E.chip_height);
        }
        this.mChipFontSize = (float) obtainStyledAttributes.getDimensionPixelSize(C0643K.RecipientEditTextView_chipFontSize, -1);
        if (this.mChipFontSize == -1.0f) {
            this.mChipFontSize = resources.getDimension(C0637E.chip_text_size);
        }
        this.mAvatarPosition = obtainStyledAttributes.getInt(C0643K.RecipientEditTextView_avatarPosition, 1);
        this.mDisableDelete = obtainStyledAttributes.getBoolean(C0643K.RecipientEditTextView_disableDelete, false);
        this.mMaxLines = resources.getInteger(C0640H.chips_max_lines);
        this.mLineSpacingExtra = (float) resources.getDimensionPixelOffset(C0637E.line_spacing_extra);
        this.mUnselectedChipTextColor = obtainStyledAttributes.getColor(C0643K.RecipientEditTextView_unselectedChipTextColor, resources.getColor(17170444));
        this.mUnselectedChipBackgroundColor = obtainStyledAttributes.getColor(C0643K.RecipientEditTextView_unselectedChipBackgroundColor, resources.getColor(C0636D.chip_background));
        obtainStyledAttributes.recycle();
    }

    private void setWorkPaintForBorder(float f) {
        this.mWorkPaint.reset();
        this.mWorkPaint.setColor(0);
        this.mWorkPaint.setStyle(Paint.Style.STROKE);
        this.mWorkPaint.setStrokeWidth(f);
        this.mWorkPaint.setAntiAlias(true);
    }

    private void setWorkPaintForIcon(Bitmap bitmap, RectF rectF, RectF rectF2) {
        Matrix matrix = new Matrix();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        matrix.reset();
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
        bitmapShader.setLocalMatrix(matrix);
        this.mWorkPaint.reset();
        this.mWorkPaint.setShader(bitmapShader);
        this.mWorkPaint.setAntiAlias(true);
        this.mWorkPaint.setFilterBitmap(true);
        this.mWorkPaint.setDither(true);
    }

    private void setupPopupWindow(ListPopupWindow listPopupWindow) {
        listPopupWindow.setOnDismissListener(new C0655X(this));
    }

    private boolean shouldCreateChip(int i, int i2) {
        return !this.mNoChipMode && hasFocus() && enoughToFilter() && !alreadyHasChip(i, i2);
    }

    private boolean shouldPositionAvatarOnRight() {
        int i = Build.VERSION.SDK_INT;
        boolean z = getLayoutDirection() == 1;
        boolean z2 = this.mAvatarPosition == 0;
        if (z) {
            return !z2;
        }
        return z2;
    }

    private boolean shouldShowEditableText(C0660b bVar) {
        long contactId = bVar.getContactId();
        return contactId == -1 || (!isPhoneQuery() && contactId == -2);
    }

    private void showAddress(C0660b bVar, ListPopupWindow listPopupWindow) {
        if (this.mAttachedToWindow) {
            int calculateOffsetFromBottomToTop = calculateOffsetFromBottomToTop(getLayout().getLineForOffset(getSpannable().getSpanStart(bVar)));
            View view = this.mAlternatePopupAnchor;
            if (view == null) {
                view = this;
            }
            listPopupWindow.setAnchorView(view);
            listPopupWindow.setVerticalOffset(calculateOffsetFromBottomToTop);
            listPopupWindow.setAdapter(createSingleAddressAdapter(bVar));
            listPopupWindow.setOnItemClickListener(new C0649Q(this, bVar, listPopupWindow));
            listPopupWindow.show();
            ListView listView = listPopupWindow.getListView();
            listView.setChoiceMode(1);
            listView.setItemChecked(0, true);
        }
    }

    private void showAlternates(C0660b bVar, ListPopupWindow listPopupWindow) {
        new C0647O(this, bVar, listPopupWindow).execute((Object[]) null);
    }

    private void showCopyDialog(String str) {
        Context context = getContext();
        if (this.mAttachedToWindow && context != null && (context instanceof Activity)) {
            C0690n nVar = new C0690n();
            Bundle bundle = new Bundle(1);
            bundle.putString("text", str);
            nVar.setArguments(bundle);
            nVar.show(((Activity) context).getFragmentManager(), "chips-copy-dialog");
        }
    }

    private void showWarningDialog(String str) {
        this.mCurrentWarningText = str;
        new AlertDialog.Builder(getContext()).setTitle(this.mWarningTitle).setOnDismissListener(new C0667ba(this)).setMessage(this.mCurrentWarningText).show();
    }

    /* access modifiers changed from: private */
    public void shrink() {
        if (this.mTokenizer != null) {
            C0660b bVar = this.mSelectedChip;
            long contactId = bVar != null ? bVar.getEntry().getContactId() : -1;
            if (this.mSelectedChip != null && contactId != -1 && !isPhoneQuery() && contactId != -2) {
                clearSelectedChip();
            } else if (getWidth() <= 0) {
                this.mHandler.removeCallbacks(this.mDelayedShrink);
                if (getVisibility() == 8) {
                    this.mRequiresShrinkWhenNotGone = true;
                    return;
                } else {
                    this.mHandler.post(this.mDelayedShrink);
                    return;
                }
            } else {
                if (this.mPendingChipsCount > 0) {
                    postHandlePendingChips();
                } else {
                    Editable text = getText();
                    int selectionEnd = getSelectionEnd();
                    int findTokenStart = this.mTokenizer.findTokenStart(text, selectionEnd);
                    C0660b[] bVarArr = (C0660b[]) getSpannable().getSpans(findTokenStart, selectionEnd, C0660b.class);
                    if (bVarArr == null || bVarArr.length == 0) {
                        Editable text2 = getText();
                        int findTokenEnd = this.mTokenizer.findTokenEnd(text2, findTokenStart);
                        if (findTokenEnd < text2.length() && text2.charAt(findTokenEnd) == ',') {
                            findTokenEnd = movePastTerminators(findTokenEnd);
                        }
                        if (findTokenEnd != getSelectionEnd()) {
                            handleEdit(findTokenStart, findTokenEnd);
                        } else {
                            commitChip(findTokenStart, selectionEnd, text);
                        }
                    }
                }
                this.mHandler.post(this.mAddTextWatcher);
            }
            createMoreChip();
        }
    }

    private void startDrag(C0660b bVar) {
        String destination = bVar.getEntry().getDestination();
        startDrag(ClipData.newPlainText(destination, destination + COMMIT_CHAR_COMMA), new C0685ka(this, bVar), (Object) null, 0);
        removeChip(bVar);
    }

    private int submitItemAtPosition(int i) {
        C0699ra createValidatedEntry = createValidatedEntry(getAdapter().getItem(i));
        if (createValidatedEntry == null) {
            return -1;
        }
        clearComposingText();
        int selectionEnd = getSelectionEnd();
        int findTokenStart = this.mTokenizer.findTokenStart(getText(), selectionEnd);
        Editable text = getText();
        QwertyKeyListener.markAsReplaced(text, findTokenStart, selectionEnd, "");
        CharSequence createChip = createChip(createValidatedEntry);
        if (createChip != null && findTokenStart >= 0 && selectionEnd >= 0) {
            text.replace(findTokenStart, selectionEnd, createChip);
        }
        sanitizeBetween();
        return selectionEnd - findTokenStart;
    }

    private float supportConvertToLocalHorizontalCoordinate(float f) {
        return Math.min((float) ((getWidth() - getTotalPaddingRight()) - 1), Math.max(0.0f, f - ((float) getTotalPaddingLeft()))) + ((float) getScrollX());
    }

    private int supportGetLineAtCoordinate(float f) {
        return getLayout().getLineForVertical((int) (Math.min((float) ((getHeight() - getTotalPaddingBottom()) - 1), Math.max(0.0f, f - ((float) getTotalPaddingLeft()))) + ((float) getScrollY())));
    }

    private int supportGetOffsetAtCoordinate(int i, float f) {
        return getLayout().getOffsetForHorizontal(i, supportConvertToLocalHorizontalCoordinate(f));
    }

    private int supportGetOffsetForPosition(float f, float f2) {
        if (getLayout() == null) {
            return -1;
        }
        return supportGetOffsetAtCoordinate(supportGetLineAtCoordinate(f2), f);
    }

    /* access modifiers changed from: private */
    public static String tokenizeAddress(String str) {
        Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(str);
        return (rfc822TokenArr == null || rfc822TokenArr.length <= 0) ? str : rfc822TokenArr[0].getAddress();
    }

    private boolean touchedWarningIcon(float f, float f2, C0660b bVar) {
        Rect aa;
        int i;
        if (bVar == null || (aa = bVar.mo5465aa()) == null) {
            return false;
        }
        if (shouldPositionAvatarOnRight()) {
            i = getSpannable().getSpanEnd(bVar);
        } else {
            i = getSpannable().getSpanStart(bVar);
        }
        float primaryHorizontal = getLayout().getPrimaryHorizontal(i);
        float totalPaddingTop = (float) (getTotalPaddingTop() + getLayout().getLineTop(getLayout().getLineForOffset(i)));
        return new RectF(((float) aa.left) + primaryHorizontal, ((float) aa.top) + totalPaddingTop, primaryHorizontal + ((float) aa.right), totalPaddingTop + ((float) aa.bottom)).contains(f, f2);
    }

    /* access modifiers changed from: private */
    public void unselectChip(C0660b bVar) {
        int spanStart = getSpannable().getSpanStart(bVar);
        int spanEnd = getSpannable().getSpanEnd(bVar);
        Editable text = getText();
        this.mSelectedChip = null;
        if (spanStart == -1 || spanEnd == -1) {
            Log.w(TAG, "The chip doesn't exist or may be a chip a user was editing");
            setSelection(text.length());
            commitDefault();
        } else {
            getSpannable().removeSpan(bVar);
            QwertyKeyListener.markAsReplaced(text, spanStart, spanEnd, "");
            text.removeSpan(bVar);
            try {
                if (!this.mNoChipMode) {
                    text.setSpan(constructChipSpan(bVar.getEntry()), spanStart, spanEnd, 33);
                }
            } catch (NullPointerException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        setCursorVisible(true);
        setSelection(text.length());
        ListPopupWindow listPopupWindow = this.mAlternatesPopup;
        if (listPopupWindow != null && listPopupWindow.isShowing()) {
            this.mAlternatesPopup.dismiss();
        }
    }

    public void append(CharSequence charSequence, int i, int i2) {
        TextWatcher textWatcher = this.mTextWatcher;
        if (textWatcher != null) {
            removeTextChangedListener(textWatcher);
        }
        super.append(charSequence, i, i2);
        if (!TextUtils.isEmpty(charSequence) && TextUtils.getTrimmedLength(charSequence) > 0) {
            String charSequence2 = charSequence.toString();
            if (!charSequence2.trim().endsWith(String.valueOf(COMMIT_CHAR_COMMA))) {
                String str = SEPARATOR;
                super.append(str, 0, str.length());
                StringBuilder Pa = C0632a.m1011Pa(charSequence2);
                Pa.append(SEPARATOR);
                charSequence2 = Pa.toString();
            }
            if (!TextUtils.isEmpty(charSequence2) && TextUtils.getTrimmedLength(charSequence2) > 0) {
                this.mPendingChipsCount++;
                this.mPendingChips.add(charSequence2);
            }
        }
        if (this.mPendingChipsCount > 0) {
            postHandlePendingChips();
        }
        this.mHandler.post(this.mAddTextWatcher);
    }

    public void appendRecipientEntry(C0699ra raVar) {
        clearComposingText();
        Editable text = getText();
        C0660b[] sortedRecipients = getSortedRecipients();
        int spanEnd = (sortedRecipients == null || sortedRecipients.length <= 0) ? 0 : text.getSpanEnd(sortedRecipients[sortedRecipients.length - 1]) + 1;
        CharSequence createChip = createChip(raVar);
        if (createChip != null) {
            text.insert(spanEnd, createChip);
        }
    }

    public void clearSelectedChip() {
        C0660b bVar = this.mSelectedChip;
        if (bVar != null) {
            unselectChip(bVar);
            this.mSelectedChip = null;
        }
        setCursorVisible(true);
        setSelection(getText().length());
    }

    /* access modifiers changed from: package-private */
    public int countTokens(Editable editable) {
        int i = 0;
        int i2 = 0;
        while (i < editable.length()) {
            i = movePastTerminators(this.mTokenizer.findTokenEnd(editable, i));
            i2++;
            if (i >= editable.length()) {
                break;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public String createAddressText(C0699ra raVar) {
        String str;
        Rfc822Token[] rfc822TokenArr;
        String displayName = raVar.getDisplayName();
        String destination = raVar.getDestination();
        if (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, destination)) {
            displayName = null;
        }
        if (!isPhoneQuery() || !C0705w.isPhoneNumber(destination)) {
            if (!(destination == null || (rfc822TokenArr = Rfc822Tokenizer.tokenize(destination)) == null || rfc822TokenArr.length <= 0)) {
                destination = rfc822TokenArr[0].getAddress();
            }
            str = new Rfc822Token(displayName, destination, (String) null).toString().trim();
        } else {
            str = destination.trim();
        }
        return (this.mTokenizer == null || TextUtils.isEmpty(str) || str.indexOf(",") >= str.length() + -1) ? str : (String) this.mTokenizer.terminateToken(str);
    }

    /* access modifiers changed from: protected */
    public ListAdapter createAlternatesAdapter(C0660b bVar) {
        Context context = getContext();
        long contactId = bVar.getContactId();
        Long R = bVar.mo5463R();
        String m = bVar.mo5474m();
        long ga = bVar.mo5467ga();
        int queryType = getAdapter().getQueryType();
        C0704v vVar = this.mDropdownChipLayouter;
        StateListDrawable constructStateListDeleteDrawable = constructStateListDeleteDrawable();
        getAdapter().getPermissionsCheckListener();
        return new C0646N(context, contactId, R, m, ga, queryType, this, vVar, constructStateListDeleteDrawable);
    }

    /* access modifiers changed from: package-private */
    public String createChipDisplayText(C0699ra raVar) {
        String displayName = raVar.getDisplayName();
        String destination = raVar.getDestination();
        if (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, destination)) {
            displayName = null;
        }
        if (!TextUtils.isEmpty(displayName)) {
            return displayName;
        }
        if (!TextUtils.isEmpty(destination)) {
            return destination;
        }
        return new Rfc822Token(displayName, destination, (String) null).toString();
    }

    /* access modifiers changed from: package-private */
    public void createMoreChip() {
        if (this.mNoChipMode) {
            createMoreChipPlainText();
        } else if (this.mShouldShrink) {
            C0662d[] dVarArr = (C0662d[]) getSpannable().getSpans(0, getText().length(), C0677ga.class);
            if (dVarArr.length > 0) {
                getSpannable().removeSpan(dVarArr[0]);
            }
            C0660b[] sortedRecipients = getSortedRecipients();
            if (sortedRecipients == null || sortedRecipients.length <= 2) {
                this.mMoreChip = null;
                return;
            }
            Spannable spannable = getSpannable();
            int length = sortedRecipients.length;
            int i = length - 2;
            C0677ga createMoreSpan = createMoreSpan(i);
            this.mHiddenSpans = new ArrayList();
            Editable text = getText();
            int i2 = length - i;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = i2; i5 < sortedRecipients.length; i5++) {
                this.mHiddenSpans.add(sortedRecipients[i5]);
                if (i5 == i2) {
                    i4 = spannable.getSpanStart(sortedRecipients[i5]);
                }
                if (i5 == sortedRecipients.length - 1) {
                    i3 = spannable.getSpanEnd(sortedRecipients[i5]);
                }
                ArrayList arrayList = this.mTemporaryRecipients;
                if (arrayList == null || !arrayList.contains(sortedRecipients[i5])) {
                    sortedRecipients[i5].mo5464a(text.toString().substring(spannable.getSpanStart(sortedRecipients[i5]), spannable.getSpanEnd(sortedRecipients[i5])));
                }
                spannable.removeSpan(sortedRecipients[i5]);
            }
            if (i3 < text.length()) {
                i3 = text.length();
            }
            int max = Math.max(i4, i3);
            int min = Math.min(i4, i3);
            SpannableString spannableString = new SpannableString(text.subSequence(min, max));
            spannableString.setSpan(createMoreSpan, 0, spannableString.length(), 33);
            text.replace(min, max, spannableString);
            this.mMoreChip = createMoreSpan;
            if (!isPhoneQuery() && getLineCount() > this.mMaxLines) {
                setMaxLines(getLineCount());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void createMoreChipPlainText() {
        Editable text = getText();
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            i = movePastTerminators(this.mTokenizer.findTokenEnd(text, i));
        }
        C0677ga createMoreSpan = createMoreSpan(countTokens(text) - 2);
        SpannableString spannableString = new SpannableString(text.subSequence(i, text.length()));
        spannableString.setSpan(createMoreSpan, 0, spannableString.length(), 33);
        text.replace(i, text.length(), spannableString);
        this.mMoreChip = createMoreSpan;
    }

    /* access modifiers changed from: package-private */
    public void createReplacementChip(int i, int i2, Editable editable, boolean z) {
        if (!alreadyHasChip(i, i2)) {
            String substring = editable.toString().substring(i, i2);
            String trim = substring.trim();
            int lastIndexOf = trim.lastIndexOf(44);
            if (lastIndexOf != -1 && lastIndexOf == trim.length() - 1) {
                substring = trim.substring(0, trim.length() - 1);
            }
            C0699ra createTokenizedEntry = createTokenizedEntry(substring);
            if (createTokenizedEntry != null) {
                C0660b bVar = null;
                try {
                    if (!this.mNoChipMode) {
                        bVar = z ? constructChipSpan(createTokenizedEntry) : new C0661c(createTokenizedEntry);
                    }
                } catch (NullPointerException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
                editable.setSpan(bVar, i, i2, 33);
                if (bVar != null) {
                    if (this.mTemporaryRecipients == null) {
                        this.mTemporaryRecipients = new ArrayList();
                    }
                    bVar.mo5464a(substring);
                    this.mTemporaryRecipients.add(bVar);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public C0699ra createTokenizedEntry(String str) {
        String str2;
        String str3 = str;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (isPhoneQuery() && C0705w.isPhoneNumber(str)) {
            return new C0699ra(0, str, str, -1, (String) null, -1, (Long) null, -1, (Uri) null, true, true, (String) null, (String[]) null);
        }
        Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(str);
        AutoCompleteTextView.Validator validator = this.mValidator;
        boolean isValid = validator == null ? true : validator.isValid(str3);
        if (isValid && rfc822TokenArr != null && rfc822TokenArr.length > 0) {
            String name = rfc822TokenArr[0].getName();
            if (!TextUtils.isEmpty(name)) {
                return C0699ra.m1081a(name, rfc822TokenArr[0].getAddress(), isValid);
            }
            String address = rfc822TokenArr[0].getAddress();
            if (!TextUtils.isEmpty(address)) {
                return C0699ra.m1083b(address, isValid);
            }
        }
        AutoCompleteTextView.Validator validator2 = this.mValidator;
        if (validator2 == null || isValid) {
            str2 = null;
        } else {
            str2 = validator2.fixText(str3).toString();
            if (!TextUtils.isEmpty(str2)) {
                if (str2.contains(str3)) {
                    Rfc822Token[] rfc822TokenArr2 = Rfc822Tokenizer.tokenize(str2);
                    if (rfc822TokenArr2.length > 0) {
                        str2 = rfc822TokenArr2[0].getAddress();
                        isValid = true;
                    }
                } else {
                    str2 = null;
                    isValid = false;
                }
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str3;
        }
        return C0699ra.m1083b(str2, isValid);
    }

    /* access modifiers changed from: protected */
    public void drawCircularIconOnCanvas(Bitmap bitmap, Canvas canvas, RectF rectF, RectF rectF2) {
        setWorkPaintForIcon(bitmap, rectF, rectF2);
        canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), rectF2.width() / 2.0f, this.mWorkPaint);
        setWorkPaintForBorder(1.0f);
        canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), (rectF2.width() / 2.0f) - 0.5f, this.mWorkPaint);
        this.mWorkPaint.reset();
    }

    public void enableDrag() {
        this.mDragEnabled = true;
    }

    public List getAllRecipients() {
        List selectedRecipients = getSelectedRecipients();
        ArrayList arrayList = this.mHiddenSpans;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                selectedRecipients.add(((C0660b) it.next()).getEntry());
            }
        }
        return selectedRecipients;
    }

    /* access modifiers changed from: package-private */
    public Drawable getChipBackground(C0699ra raVar) {
        return raVar.isValid() ? this.mChipBackground : this.mInvalidChipBackground;
    }

    public float getChipHeight() {
        return this.mChipHeight;
    }

    /* access modifiers changed from: package-private */
    public C0660b getLastChip() {
        C0660b[] sortedRecipients = getSortedRecipients();
        if (sortedRecipients == null || sortedRecipients.length <= 0) {
            return null;
        }
        return sortedRecipients[sortedRecipients.length - 1];
    }

    /* access modifiers changed from: package-private */
    public C0662d getMoreChip() {
        C0677ga[] gaVarArr = (C0677ga[]) getSpannable().getSpans(0, getText().length(), C0677ga.class);
        if (gaVarArr == null || gaVarArr.length <= 0) {
            return null;
        }
        return gaVarArr[0];
    }

    /* access modifiers changed from: protected */
    public ScrollView getScrollView() {
        return this.mScrollView;
    }

    public List getSelectedRecipients() {
        C0660b[] bVarArr = (C0660b[]) getText().getSpans(0, getText().length(), C0660b.class);
        ArrayList arrayList = new ArrayList();
        if (bVarArr == null) {
            return arrayList;
        }
        for (C0660b entry : bVarArr) {
            arrayList.add(entry.getEntry());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public C0660b[] getSortedRecipients() {
        ArrayList arrayList = new ArrayList(Arrays.asList((C0660b[]) getSpannable().getSpans(0, getText().length(), C0660b.class)));
        Collections.sort(arrayList, new C0648P(this, getSpannable()));
        return (C0660b[]) arrayList.toArray(new C0660b[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public Spannable getSpannable() {
        return getText();
    }

    public String getSuggestionDropdownOpenedVerbalization(int i) {
        return getResources().getString(C0642J.accessbility_suggestion_dropdown_opened);
    }

    /* access modifiers changed from: protected */
    public float getTextYOffset(int i) {
        return (float) (i - ((i - this.mTextHeight) / 2));
    }

    /* access modifiers changed from: package-private */
    public int getViewWidth() {
        return getWidth();
    }

    /* access modifiers changed from: package-private */
    public ArrayList handlePaste() {
        String obj = getText().toString();
        int findTokenStart = this.mTokenizer.findTokenStart(obj, getSelectionEnd());
        String substring = obj.substring(findTokenStart);
        ArrayList arrayList = new ArrayList();
        if (findTokenStart != 0) {
            C0660b bVar = null;
            int i = 0;
            int i2 = findTokenStart;
            while (true) {
                if (i2 != 0 && bVar == null && i2 != i) {
                    int findTokenStart2 = this.mTokenizer.findTokenStart(obj, i2);
                    C0660b findChip = findChip(findTokenStart2);
                    if (findTokenStart2 == findTokenStart && findChip == null) {
                        C0660b bVar2 = findChip;
                        i = i2;
                        i2 = findTokenStart2;
                        bVar = bVar2;
                        break;
                    }
                    C0660b bVar3 = findChip;
                    i = i2;
                    i2 = findTokenStart2;
                    bVar = bVar3;
                } else {
                    break;
                }
            }
            if (i2 != findTokenStart) {
                if (bVar != null) {
                    i2 = i;
                }
                while (i2 < findTokenStart) {
                    commitChip(i2, movePastTerminators(this.mTokenizer.findTokenEnd(getText().toString(), i2)), getText());
                    C0660b findChip2 = findChip(i2);
                    if (findChip2 == null) {
                        break;
                    }
                    i2 = getSpannable().getSpanEnd(findChip2) + 1;
                    arrayList.add(findChip2);
                }
            }
        }
        if (isCompletedToken(substring)) {
            Editable text = getText();
            int indexOf = text.toString().indexOf(substring, findTokenStart);
            commitChip(indexOf, text.length(), text);
            arrayList.add(findChip(indexOf));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void handlePasteClip(ClipData clipData) {
        if (clipData != null) {
            ClipDescription description = clipData.getDescription();
            if (description.hasMimeType("text/plain") || description.hasMimeType("text/html")) {
                removeTextChangedListener(this.mTextWatcher);
                ClipDescription description2 = clipData.getDescription();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    String mimeType = description2.getMimeType(i);
                    if ("text/plain".equals(mimeType) || "text/html".equals(mimeType)) {
                        CharSequence text = clipData.getItemAt(i).getText();
                        if (!TextUtils.isEmpty(text)) {
                            Editable text2 = getText();
                            int selectionStart = getSelectionStart();
                            int selectionEnd = getSelectionEnd();
                            if (selectionStart < 0 || selectionEnd < 1) {
                                text2.append(text);
                            } else if (selectionStart == selectionEnd) {
                                text2.insert(selectionStart, text);
                            } else {
                                text2.append(text, selectionStart, selectionEnd);
                            }
                            handlePasteAndReplace();
                        }
                    }
                }
                this.mHandler.post(this.mAddTextWatcher);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handlePendingChips() {
        boolean z;
        if (getViewWidth() > 0 && this.mPendingChipsCount > 0) {
            synchronized (this.mPendingChips) {
                Editable text = getText();
                if (this.mPendingChipsCount <= 50) {
                    for (int i = 0; i < this.mPendingChips.size(); i++) {
                        String str = (String) this.mPendingChips.get(i);
                        int indexOf = text.toString().indexOf(str);
                        int length = (str.length() + indexOf) - 1;
                        if (indexOf >= 0) {
                            if (length < text.length() - 2 && text.charAt(length) == ',') {
                                length++;
                            }
                            if (i >= 2) {
                                if (this.mShouldShrink) {
                                    z = false;
                                    createReplacementChip(indexOf, length, text, z);
                                }
                            }
                            z = true;
                            createReplacementChip(indexOf, length, text, z);
                        }
                        this.mPendingChipsCount--;
                    }
                    sanitizeEnd();
                } else {
                    this.mNoChipMode = true;
                }
                if (this.mTemporaryRecipients == null || this.mTemporaryRecipients.size() <= 0 || this.mTemporaryRecipients.size() > 50) {
                    this.mTemporaryRecipients = null;
                    createMoreChip();
                } else {
                    if (!hasFocus()) {
                        if (this.mTemporaryRecipients.size() >= 2) {
                            this.mIndividualReplacements = new C0675fa(this, (C0650S) null);
                            this.mIndividualReplacements.execute(new ArrayList[]{new ArrayList(this.mTemporaryRecipients.subList(0, 2))});
                            if (this.mTemporaryRecipients.size() > 2) {
                                this.mTemporaryRecipients = new ArrayList(this.mTemporaryRecipients.subList(2, this.mTemporaryRecipients.size()));
                            } else {
                                this.mTemporaryRecipients = null;
                            }
                            createMoreChip();
                        }
                    }
                    new C0693oa(this, (C0650S) null).execute(new Void[0]);
                    this.mTemporaryRecipients = null;
                }
                this.mPendingChipsCount = 0;
                this.mPendingChips.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCompletedToken(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        String trim = charSequence.toString().substring(this.mTokenizer.findTokenStart(charSequence, length), length).trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        char charAt = trim.charAt(trim.length() - 1);
        return charAt == ',' || charAt == ';';
    }

    public boolean isGeneratedContact(C0660b bVar) {
        long contactId = bVar.getContactId();
        return contactId == -1 || (!isPhoneQuery() && contactId == -2);
    }

    public boolean isNoChipMode() {
        return this.mNoChipMode;
    }

    /* access modifiers changed from: protected */
    public boolean isPhoneQuery() {
        if (getAdapter() == null || getAdapter().getQueryType() != 1) {
            return false;
        }
        return true;
    }

    public boolean lastCharacterIsCommitCharacter(CharSequence charSequence) {
        char c;
        int selectionEnd = getSelectionEnd() == 0 ? 0 : getSelectionEnd() - 1;
        int length = length() - 1;
        if (selectionEnd != length) {
            c = charSequence.charAt(selectionEnd);
        } else {
            c = charSequence.charAt(length);
        }
        if (c == ',' || c == ';') {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int movePastTerminators(int i) {
        if (i >= length()) {
            return i;
        }
        char charAt = getText().toString().charAt(i);
        if (charAt == ',' || charAt == ';') {
            i++;
        }
        return (i >= length() || getText().toString().charAt(i) != ' ') ? i : i + 1;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        int dropDownAnchor = getDropDownAnchor();
        if (dropDownAnchor != -1) {
            this.mDropdownAnchor = getRootView().findViewById(dropDownAnchor);
        }
    }

    public void onCheckedItemChanged(int i) {
        ListView listView = this.mAlternatesPopup.getListView();
        if (listView != null && listView.getCheckedItemCount() == 0) {
            listView.setItemChecked(i, true);
        }
        this.mCheckedItem = i;
    }

    /* access modifiers changed from: protected */
    public void onChipCreated(C0699ra raVar) {
        boolean z = this.mNoChipMode;
    }

    public void onChipDelete() {
        if (this.mSelectedChip != null) {
            boolean z = this.mNoChipMode;
            removeChip(this.mSelectedChip);
        }
        dismissPopups();
    }

    public void onClick(C0660b bVar) {
        if (bVar.isSelected()) {
            clearSelectedChip();
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        int i = editorInfo.imeOptions;
        int i2 = i & 255;
        if ((i2 & 6) != 0) {
            editorInfo.imeOptions = i ^ i2;
            editorInfo.imeOptions |= 6;
        }
        int i3 = editorInfo.imeOptions;
        if ((1073741824 & i3) != 0) {
            editorInfo.imeOptions = i3 & -1073741825;
        }
        editorInfo.actionId = 6;
        int i4 = Build.VERSION.SDK_INT;
        editorInfo.actionLabel = null;
        return onCreateInputConnection;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDragEvent(DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action == 1) {
            return dragEvent.getClipDescription().hasMimeType("text/plain");
        }
        if (action == 3) {
            handlePasteClip(dragEvent.getClipData());
            return true;
        } else if (action != 5) {
            return false;
        } else {
            requestFocus();
            return true;
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        if (commitDefault()) {
            return true;
        }
        if (this.mSelectedChip != null) {
            clearSelectedChip();
            return true;
        } else if (!hasFocus() || !focusNext()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!z) {
            shrink();
        } else {
            expand();
        }
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i >= 0 && getAdapter().getItem(i).getEntryType() != 1) {
            int submitItemAtPosition = submitItemAtPosition(i);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mSelectedChip != null && i == 67) {
            ListPopupWindow listPopupWindow = this.mAlternatesPopup;
            if (listPopupWindow != null && listPopupWindow.isShowing()) {
                this.mAlternatesPopup.dismiss();
            }
            removeChip(this.mSelectedChip);
        }
        if ((i == 23 || i == 66) && keyEvent.hasNoModifiers()) {
            if (commitDefault()) {
                return true;
            }
            if (this.mSelectedChip != null) {
                clearSelectedChip();
                return true;
            } else if (focusNext()) {
                return true;
            }
        }
        C0660b lastChip = getLastChip();
        boolean onKeyDown = super.onKeyDown(i, keyEvent);
        if (i == 67 && onKeyDown && lastChip != null) {
            lastChip.getEntry();
            boolean z = this.mNoChipMode;
        }
        return onKeyDown;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i != 4 || this.mSelectedChip == null) {
            return super.onKeyPreIme(i, keyEvent);
        }
        clearSelectedChip();
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 61 && keyEvent.hasNoModifiers()) {
            if (this.mSelectedChip != null) {
                clearSelectedChip();
            } else {
                commitDefault();
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onLongPress(MotionEvent motionEvent) {
        C0660b findChip;
        if (this.mSelectedChip != null || (findChip = findChip(putOffsetInRange(motionEvent.getX(), motionEvent.getY()))) == null) {
            return;
        }
        if (this.mDragEnabled) {
            startDrag(findChip);
        } else {
            showCopyDialog(findChip.getEntry().getDestination());
        }
    }

    public void onPermissionRequestDismissed() {
        dismissDropDown();
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        if (!TextUtils.isEmpty(getText())) {
            super.onRestoreInstanceState((Parcelable) null);
        } else {
            super.onRestoreInstanceState(bundle.getParcelable(STATE_TEXT_VIEW));
        }
        String string = bundle.getString(STATE_CURRENT_WARNING_TEXT);
        if (!string.isEmpty()) {
            showWarningDialog(string);
        }
    }

    public Parcelable onSaveInstanceState() {
        clearSelectedChip();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_TEXT_VIEW, super.onSaveInstanceState());
        bundle.putString(STATE_CURRENT_WARNING_TEXT, this.mCurrentWarningText);
        return bundle;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onSelectionChanged(int i, int i2) {
        C0660b lastChip = getLastChip();
        if (this.mSelectedChip == null && lastChip != null && i < getSpannable().getSpanEnd(lastChip)) {
            setSelection(Math.min(getSpannable().getSpanEnd(lastChip) + 1, getText().length()));
        }
        super.onSelectionChanged(i, i2);
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!(i == 0 || i2 == 0)) {
            if (this.mPendingChipsCount > 0) {
                postHandlePendingChips();
            } else {
                checkChipWidths();
            }
        }
        if (this.mScrollView == null && !this.mTriedGettingScrollView) {
            ViewParent parent = getParent();
            while (parent != null && !(parent instanceof ScrollView)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                this.mScrollView = (ScrollView) parent;
            }
            this.mTriedGettingScrollView = true;
        }
    }

    public boolean onTextContextMenuItem(int i) {
        if (i != 16908322) {
            return super.onTextContextMenuItem(i);
        }
        handlePasteClip(((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip());
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        C0660b findChip = findChip(putOffsetInRange(x, y));
        boolean z2 = true;
        if (action == 1) {
            boolean z3 = touchedWarningIcon(x, y, findChip);
            if (z3) {
                showWarningDialog(String.format(this.mWarningTextTemplate, new Object[]{findChip.getEntry().getDestination()}));
                return true;
            } else if (isFocused()) {
                z = super.onTouchEvent(motionEvent);
                if (this.mSelectedChip == null) {
                    this.mGestureDetector.onTouchEvent(motionEvent);
                }
                if (findChip != null) {
                    C0660b bVar = this.mSelectedChip;
                    if (bVar == null || bVar == findChip) {
                        C0660b bVar2 = this.mSelectedChip;
                        if (bVar2 == null) {
                            commitDefault();
                            selectChip(findChip);
                        } else {
                            onClick(bVar2);
                        }
                    } else {
                        clearSelectedChip();
                        selectChip(findChip);
                    }
                    z = true;
                } else {
                    C0660b bVar3 = this.mSelectedChip;
                    if (bVar3 == null || !shouldShowEditableText(bVar3)) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    clearSelectedChip();
                }
            } else if (z3 || super.onTouchEvent(motionEvent)) {
                return true;
            } else {
                return false;
            }
        } else if (touchedWarningIcon(x, y, findChip)) {
            return true;
        } else {
            z = super.onTouchEvent(motionEvent);
            if (!isFocused()) {
                return z;
            }
            if (this.mSelectedChip == null) {
                this.mGestureDetector.onTouchEvent(motionEvent);
            }
        }
        return z;
    }

    public void performFiltering(CharSequence charSequence, int i) {
        boolean isCompletedToken = isCompletedToken(charSequence);
        if (enoughToFilter() && !isCompletedToken) {
            int selectionEnd = getSelectionEnd();
            C0660b[] bVarArr = (C0660b[]) getSpannable().getSpans(this.mTokenizer.findTokenStart(charSequence, selectionEnd), selectionEnd, C0660b.class);
            if (bVarArr != null && bVarArr.length > 0) {
                dismissDropDown();
                return;
            }
        } else if (isCompletedToken) {
            dismissDropDown();
            return;
        }
        super.performFiltering(charSequence, i);
    }

    public void performValidation() {
    }

    /* access modifiers changed from: package-private */
    public void removeChip(C0660b bVar) {
        Spannable spannable = getSpannable();
        int spanStart = spannable.getSpanStart(bVar);
        int spanEnd = spannable.getSpanEnd(bVar);
        Editable text = getText();
        boolean z = bVar == this.mSelectedChip;
        if (z) {
            this.mSelectedChip = null;
        }
        while (spanEnd >= 0 && spanEnd < text.length() && text.charAt(spanEnd) == ' ') {
            spanEnd++;
        }
        spannable.removeSpan(bVar);
        if (spanStart >= 0 && spanEnd > 0) {
            text.delete(spanStart, spanEnd);
        }
        if (z) {
            clearSelectedChip();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeMoreChip() {
        C0660b[] sortedRecipients;
        if (this.mMoreChip != null) {
            Spannable spannable = getSpannable();
            spannable.removeSpan(this.mMoreChip);
            this.mMoreChip = null;
            ArrayList arrayList = this.mHiddenSpans;
            if (arrayList != null && arrayList.size() > 0 && (sortedRecipients = getSortedRecipients()) != null && sortedRecipients.length != 0) {
                int spanEnd = spannable.getSpanEnd(sortedRecipients[sortedRecipients.length - 1]);
                Editable text = getText();
                Iterator it = this.mHiddenSpans.iterator();
                while (it.hasNext()) {
                    C0660b bVar = (C0660b) it.next();
                    String str = (String) bVar.getOriginalText();
                    int indexOf = text.toString().indexOf(str, spanEnd);
                    int min = Math.min(text.length(), str.length() + indexOf);
                    if (indexOf != -1) {
                        text.setSpan(bVar, indexOf, min, 33);
                    }
                    spanEnd = min;
                }
                this.mHiddenSpans.clear();
            }
        }
    }

    public void removeRecipientEntry(C0699ra raVar) {
        for (C0660b bVar : (C0660b[]) getText().getSpans(0, getText().length(), C0660b.class)) {
            C0699ra entry = bVar.getEntry();
            if (entry != null && entry.isValid() && entry.mo5650a(raVar)) {
                removeChip(bVar);
            }
        }
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        this.mTextWatcher = null;
        super.removeTextChangedListener(textWatcher);
    }

    /* access modifiers changed from: package-private */
    public void replaceChip(C0660b bVar, C0699ra raVar) {
        boolean z = bVar == this.mSelectedChip;
        if (z) {
            this.mSelectedChip = null;
        }
        int spanStart = getSpannable().getSpanStart(bVar);
        int spanEnd = getSpannable().getSpanEnd(bVar);
        getSpannable().removeSpan(bVar);
        Editable text = getText();
        raVar.mo5648F(true);
        CharSequence createChip = createChip(raVar);
        if (createChip != null) {
            if (spanStart == -1 || spanEnd == -1) {
                Log.e(TAG, "The chip to replace does not exist but should.");
                text.insert(0, createChip);
            } else if (!TextUtils.isEmpty(createChip)) {
                while (spanEnd >= 0 && spanEnd < text.length() && text.charAt(spanEnd) == ' ') {
                    spanEnd++;
                }
                text.replace(spanStart, spanEnd, createChip);
            }
        }
        setCursorVisible(true);
        if (z) {
            clearSelectedChip();
        }
    }

    /* access modifiers changed from: protected */
    public void replaceText(CharSequence charSequence) {
    }

    /* access modifiers changed from: package-private */
    public void sanitizeBetween() {
        C0660b[] sortedRecipients;
        if (this.mPendingChipsCount <= 0 && (sortedRecipients = getSortedRecipients()) != null && sortedRecipients.length > 0) {
            C0660b bVar = sortedRecipients[sortedRecipients.length - 1];
            C0660b bVar2 = null;
            if (sortedRecipients.length > 1) {
                bVar2 = sortedRecipients[sortedRecipients.length - 2];
            }
            int i = 0;
            int spanStart = getSpannable().getSpanStart(bVar);
            if (bVar2 != null) {
                i = getSpannable().getSpanEnd(bVar2);
                Editable text = getText();
                if (i != -1 && i <= text.length() - 1) {
                    if (text.charAt(i) == ' ') {
                        i++;
                    }
                } else {
                    return;
                }
            }
            if (i >= 0 && spanStart >= 0 && i < spanStart) {
                getText().delete(i, spanStart);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sanitizeEnd() {
        int i;
        if (this.mPendingChipsCount <= 0) {
            C0660b[] sortedRecipients = getSortedRecipients();
            Spannable spannable = getSpannable();
            if (sortedRecipients != null && sortedRecipients.length > 0) {
                this.mMoreChip = getMoreChip();
                C0662d dVar = this.mMoreChip;
                if (dVar != null) {
                    i = spannable.getSpanEnd(dVar);
                } else {
                    i = getSpannable().getSpanEnd(getLastChip());
                }
                Editable text = getText();
                int length = text.length();
                if (length > i) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "There were extra characters after the last tokenizable entry." + text);
                    }
                    text.delete(i + 1, length);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void scrollBottomIntoView() {
        if (this.mScrollView != null && this.mShouldShrink) {
            getLocationInWindow(this.mCoords);
            int height = getHeight();
            int[] iArr = this.mCoords;
            int i = iArr[1] + height;
            this.mScrollView.getLocationInWindow(iArr);
            int lineCount = (height / getLineCount()) + this.mCoords[1];
            if (i > lineCount) {
                this.mScrollView.scrollBy(0, i - lineCount);
            }
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        C0684k kVar = (C0684k) listAdapter;
        kVar.registerUpdateObserver(new C0656Y(this));
        kVar.setDropdownChipLayouter(this.mDropdownChipLayouter);
    }

    public void setAlternatePopupAnchor(View view) {
        this.mAlternatePopupAnchor = view;
    }

    /* access modifiers changed from: package-private */
    public void setChipBackground(Drawable drawable) {
        this.mChipBackground = drawable;
    }

    /* access modifiers changed from: package-private */
    public void setChipHeight(int i) {
        this.mChipHeight = (float) i;
    }

    public void setDropDownAnchor(int i) {
        super.setDropDownAnchor(i);
        if (i != -1) {
            this.mDropdownAnchor = getRootView().findViewById(i);
        }
    }

    public void setDropdownChipLayouter(C0704v vVar) {
        this.mDropdownChipLayouter = vVar;
        this.mDropdownChipLayouter.setDeleteListener(this);
        this.mDropdownChipLayouter.setPermissionRequestDismissedListener(this);
    }

    /* access modifiers changed from: package-private */
    public void setMoreItem(TextView textView) {
        this.mMoreItem = textView;
    }

    public void setOnFocusListShrinkRecipients(boolean z) {
        this.mShouldShrink = z;
    }

    public void setPermissionsRequestItemClickedListener(C0679ha haVar) {
    }

    public void setRecipientChipAddedListener(C0681ia iaVar) {
    }

    public void setRecipientChipDeletedListener(C0683ja jaVar) {
    }

    public void setRecipientEntryItemClickedListener(C0687la laVar) {
    }

    public void setTokenizer(MultiAutoCompleteTextView.Tokenizer tokenizer) {
        this.mTokenizer = tokenizer;
        super.setTokenizer(this.mTokenizer);
    }

    public void setUntrustedAddressWarning(Set set, Bitmap bitmap, int i, String str, String str2) {
        this.mUntrustedAddresses = set;
        this.mWarningIcon = bitmap;
        this.mWarningIconHeight = i;
        this.mWarningTextTemplate = str;
        this.mWarningTitle = str2;
    }

    public void setValidator(AutoCompleteTextView.Validator validator) {
        this.mValidator = validator;
        super.setValidator(validator);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i != 8 && this.mRequiresShrinkWhenNotGone) {
            this.mRequiresShrinkWhenNotGone = false;
            this.mHandler.post(this.mDelayedShrink);
        }
    }

    public C0684k getAdapter() {
        return (C0684k) super.getAdapter();
    }

    private int putOffsetInRange(int i) {
        Editable text = getText();
        int length = text.length();
        int i2 = length - 1;
        while (i2 >= 0 && text.charAt(i2) == ' ') {
            length--;
            i2--;
        }
        if (i >= length) {
            return i;
        }
        Editable text2 = getText();
        while (i >= 0 && findText(text2, i) == -1 && findChip(i) == null) {
            i--;
        }
        return i;
    }

    private C0669ca createChipBitmap(C0699ra raVar, TextPaint textPaint, Drawable drawable, int i) {
        Drawable drawable2;
        int i2;
        int i3;
        C0669ca caVar;
        boolean z;
        int i4;
        int i5;
        float f;
        int i6;
        TextPaint textPaint2 = textPaint;
        Drawable drawable3 = drawable;
        C0669ca caVar2 = new C0669ca((C0650S) null);
        if (raVar.mo5663vd() != 0) {
            Drawable drawable4 = getContext().getDrawable(raVar.mo5663vd());
            drawable4.setBounds(0, 0, drawable4.getIntrinsicWidth(), drawable4.getIntrinsicHeight());
            i2 = drawable4.getBounds().width() + this.mChipTextEndPadding;
            drawable2 = drawable4;
        } else {
            drawable2 = null;
            i2 = 0;
        }
        Rect rect = new Rect();
        if (drawable3 != null) {
            drawable3.getPadding(rect);
        }
        int i7 = (int) this.mChipHeight;
        boolean z2 = raVar.isValid() && raVar.mo5647Ad();
        int i8 = z2 ? (i7 - rect.top) - rect.bottom : 0;
        boolean contains = this.mUntrustedAddresses.contains(raVar.getDestination());
        float f2 = contains ? (float) this.mWarningIconHeight : 0.0f;
        float f3 = (this.mChipHeight - ((float) this.mWarningIconHeight)) / 2.0f;
        float f4 = contains ? (float) this.mChipTextEndPadding : 0.0f;
        float[] fArr = new float[1];
        textPaint2.getTextWidths(" ", fArr);
        CharSequence ellipsizeText = ellipsizeText(createChipDisplayText(raVar), textPaint2, ((((((calculateAvailableWidth() - ((float) i8)) - f2) - f4) - fArr[0]) - ((float) rect.left)) - ((float) rect.right)) - ((float) i2));
        int measureText = (int) textPaint2.measureText(ellipsizeText, 0, ellipsizeText.length());
        float f5 = f3;
        int i9 = (int) f2;
        float f6 = f2;
        int i10 = (int) f4;
        float f7 = f4;
        int max = Math.max(i8 * 2, (z2 ? this.mChipTextStartPadding : this.mChipTextEndPadding) + measureText + this.mChipTextEndPadding + i8 + i9 + i10 + rect.left + rect.right + i2);
        caVar2.bitmap = Bitmap.createBitmap(max, i7, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(caVar2.bitmap);
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, max, i7);
            drawable3.draw(canvas);
            caVar = caVar2;
            z = z2;
            i3 = i8;
        } else {
            this.mWorkPaint.reset();
            this.mWorkPaint.setColor(i);
            float f8 = (float) (i7 / 2);
            z = z2;
            caVar = caVar2;
            i3 = i8;
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) max, (float) i7), f8, f8, this.mWorkPaint);
        }
        if (shouldPositionAvatarOnRight()) {
            i4 = this.mChipTextEndPadding + rect.left + i2 + i9 + i10;
        } else {
            i4 = (((((max - rect.right) - this.mChipTextEndPadding) - measureText) - i2) - i9) - i10;
        }
        Canvas canvas2 = canvas;
        float f9 = f7;
        int i11 = max;
        canvas.drawText(ellipsizeText, 0, ellipsizeText.length(), (float) i4, getTextYOffset(i7), textPaint);
        if (drawable2 != null) {
            if (shouldPositionAvatarOnRight()) {
                i6 = rect.left + this.mChipTextEndPadding;
            } else {
                i6 = ((i11 - rect.right) - drawable2.getBounds().width()) - this.mChipTextEndPadding;
            }
            drawable2.getBounds().offsetTo(i6, (i7 / 2) - (drawable2.getBounds().height() / 2));
            drawable2.draw(canvas2);
        }
        if (shouldPositionAvatarOnRight()) {
            i5 = (i11 - rect.right) - i3;
        } else {
            i5 = rect.left;
        }
        C0669ca caVar3 = caVar;
        caVar3.left = (float) i5;
        caVar3.top = (float) rect.top;
        caVar3.right = (float) (i5 + i3);
        caVar3.bottom = (float) (i7 - rect.bottom);
        caVar3.f781ov = z;
        if (shouldPositionAvatarOnRight()) {
            f = ((float) rect.left) + f9;
        } else {
            f = (((float) (i11 - rect.right)) - f6) - f9;
        }
        caVar3.f782pv = f;
        float f10 = f5;
        caVar3.f783qv = f10;
        caVar3.f784rv = f + f6;
        caVar3.f785sv = f10 + ((float) this.mWarningIconHeight);
        return caVar3;
    }
}
