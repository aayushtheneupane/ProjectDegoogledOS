package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.C0126R;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;

public class ActionMenuItemView extends AppCompatTextView implements C0213F, View.OnClickListener, ActionMenuView.ActionMenuChildView {

    /* renamed from: Sg */
    C0241t f196Sg;

    /* renamed from: _j */
    C0237p f197_j;

    /* renamed from: ak */
    private boolean f198ak;

    /* renamed from: bk */
    private boolean f199bk;

    /* renamed from: ck */
    private int f200ck;

    /* renamed from: dk */
    private int f201dk;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    private int mMinWidth;
    C0224c mPopupCallback;
    private CharSequence mTitle;

    public ActionMenuItemView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* renamed from: sn */
    private boolean m193sn() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    /* renamed from: tn */
    private void m194tn() {
        CharSequence charSequence;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.mTitle);
        if (this.mIcon != null && (!this.f196Sg.showsTextAsAction() || (!this.f198ak && !this.f199bk))) {
            z = false;
        }
        boolean z3 = z2 & z;
        CharSequence charSequence2 = null;
        setText(z3 ? this.mTitle : null);
        CharSequence contentDescription = this.f196Sg.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            if (z3) {
                charSequence = null;
            } else {
                charSequence = this.f196Sg.getTitle();
            }
            setContentDescription(charSequence);
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f196Sg.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!z3) {
                charSequence2 = this.f196Sg.getTitle();
            }
            int i = Build.VERSION.SDK_INT;
            setTooltipText(charSequence2);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        setTooltipText(tooltipText);
    }

    /* renamed from: a */
    public void mo1367a(C0241t tVar, int i) {
        this.f196Sg = tVar;
        setIcon(tVar.getIcon());
        setTitle(tVar.mo1643a((C0213F) this));
        setId(tVar.getItemId());
        setVisibility(tVar.isVisible() ? 0 : 8);
        setEnabled(tVar.isEnabled());
        if (tVar.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new C0223b(this);
        }
    }

    public C0241t getItemData() {
        return this.f196Sg;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    public boolean needsDividerAfter() {
        return hasText();
    }

    public boolean needsDividerBefore() {
        return hasText() && this.f196Sg.getIcon() == null;
    }

    public void onClick(View view) {
        C0237p pVar = this.f197_j;
        if (pVar != null) {
            pVar.invokeItem(this.f196Sg);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f198ak = m193sn();
        m194tn();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        boolean hasText = hasText();
        if (hasText && (i3 = this.f200ck) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.mMinWidth) : this.mMinWidth;
        if (mode != 1073741824 && this.mMinWidth > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i2);
        }
        if (!hasText && this.mIcon != null) {
            super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener;
        if (!this.f196Sg.hasSubMenu() || (forwardingListener = this.mForwardingListener) == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.f201dk;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i) / ((float) intrinsicWidth)));
                intrinsicWidth = i;
            }
            int i2 = this.f201dk;
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i2) / ((float) intrinsicHeight)));
                intrinsicHeight = i2;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        m194tn();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f200ck = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        m194tn();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.f198ak = m193sn();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0126R.styleable.ActionMenuItemView, i, 0);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(C0126R.styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.f201dk = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f200ck = -1;
        setSaveEnabled(false);
    }

    /* renamed from: a */
    public void mo1366a(C0237p pVar) {
        this.f197_j = pVar;
    }

    /* renamed from: a */
    public void mo1365a(C0224c cVar) {
        this.mPopupCallback = cVar;
    }
}
