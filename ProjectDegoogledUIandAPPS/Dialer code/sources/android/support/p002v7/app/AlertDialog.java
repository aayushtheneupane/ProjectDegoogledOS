package android.support.p002v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p000v4.widget.NestedScrollView;
import android.support.p002v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import com.android.dialer.R;

/* renamed from: android.support.v7.app.AlertDialog */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    final AlertController mAlert = new AlertController(getContext(), this, getWindow());

    /* renamed from: android.support.v7.app.AlertDialog$Builder */
    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f0P;
        private final int mTheme;

        public Builder(Context context) {
            int resolveDialogTheme = AlertDialog.resolveDialogTheme(context, 0);
            this.f0P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, resolveDialogTheme)));
            this.mTheme = resolveDialogTheme;
        }

        /* JADX WARNING: type inference failed for: r12v0, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r12v3 */
        /* JADX WARNING: type inference failed for: r12v4 */
        /* JADX WARNING: type inference failed for: r4v10, types: [android.widget.SimpleCursorAdapter] */
        /* JADX WARNING: type inference failed for: r3v19, types: [android.support.v7.app.AlertController$AlertParams$2] */
        /* JADX WARNING: type inference failed for: r3v20, types: [android.support.v7.app.AlertController$AlertParams$1] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.p002v7.app.AlertDialog create() {
            /*
                r14 = this;
                android.support.v7.app.AlertDialog r0 = new android.support.v7.app.AlertDialog
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                android.content.Context r1 = r1.mContext
                int r2 = r14.mTheme
                r0.<init>(r1, r2)
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                android.support.v7.app.AlertController r10 = r0.mAlert
                android.view.View r2 = r1.mCustomTitleView
                if (r2 == 0) goto L_0x0017
                r10.setCustomTitle(r2)
                goto L_0x0037
            L_0x0017:
                java.lang.CharSequence r2 = r1.mTitle
                if (r2 == 0) goto L_0x001e
                r10.setTitle(r2)
            L_0x001e:
                android.graphics.drawable.Drawable r2 = r1.mIcon
                if (r2 == 0) goto L_0x0025
                r10.setIcon((android.graphics.drawable.Drawable) r2)
            L_0x0025:
                int r2 = r1.mIconId
                if (r2 == 0) goto L_0x002c
                r10.setIcon((int) r2)
            L_0x002c:
                int r2 = r1.mIconAttrId
                if (r2 == 0) goto L_0x0037
                int r2 = r10.getIconAttributeResId(r2)
                r10.setIcon((int) r2)
            L_0x0037:
                java.lang.CharSequence r2 = r1.mMessage
                if (r2 == 0) goto L_0x003e
                r10.setMessage(r2)
            L_0x003e:
                java.lang.CharSequence r2 = r1.mPositiveButtonText
                if (r2 != 0) goto L_0x0046
                android.graphics.drawable.Drawable r2 = r1.mPositiveButtonIcon
                if (r2 == 0) goto L_0x0052
            L_0x0046:
                r3 = -1
                java.lang.CharSequence r4 = r1.mPositiveButtonText
                android.content.DialogInterface$OnClickListener r5 = r1.mPositiveButtonListener
                r6 = 0
                android.graphics.drawable.Drawable r7 = r1.mPositiveButtonIcon
                r2 = r10
                r2.setButton(r3, r4, r5, r6, r7)
            L_0x0052:
                java.lang.CharSequence r2 = r1.mNegativeButtonText
                if (r2 != 0) goto L_0x005a
                android.graphics.drawable.Drawable r2 = r1.mNegativeButtonIcon
                if (r2 == 0) goto L_0x0066
            L_0x005a:
                r3 = -2
                java.lang.CharSequence r4 = r1.mNegativeButtonText
                android.content.DialogInterface$OnClickListener r5 = r1.mNegativeButtonListener
                r6 = 0
                android.graphics.drawable.Drawable r7 = r1.mNegativeButtonIcon
                r2 = r10
                r2.setButton(r3, r4, r5, r6, r7)
            L_0x0066:
                java.lang.CharSequence r2 = r1.mNeutralButtonText
                if (r2 != 0) goto L_0x006e
                android.graphics.drawable.Drawable r2 = r1.mNeutralButtonIcon
                if (r2 == 0) goto L_0x007a
            L_0x006e:
                r3 = -3
                java.lang.CharSequence r4 = r1.mNeutralButtonText
                android.content.DialogInterface$OnClickListener r5 = r1.mNeutralButtonListener
                r6 = 0
                android.graphics.drawable.Drawable r7 = r1.mNeutralButtonIcon
                r2 = r10
                r2.setButton(r3, r4, r5, r6, r7)
            L_0x007a:
                java.lang.CharSequence[] r2 = r1.mItems
                r11 = 1
                if (r2 != 0) goto L_0x0087
                android.database.Cursor r2 = r1.mCursor
                if (r2 != 0) goto L_0x0087
                android.widget.ListAdapter r2 = r1.mAdapter
                if (r2 == 0) goto L_0x0125
            L_0x0087:
                android.view.LayoutInflater r2 = r1.mInflater
                int r3 = r10.mListLayout
                r4 = 0
                android.view.View r2 = r2.inflate(r3, r4)
                android.support.v7.app.AlertController$RecycleListView r2 = (android.support.p002v7.app.AlertController.RecycleListView) r2
                boolean r3 = r1.mIsMultiChoice
                if (r3 == 0) goto L_0x00b9
                android.database.Cursor r6 = r1.mCursor
                if (r6 != 0) goto L_0x00ac
                android.support.v7.app.AlertController$AlertParams$1 r12 = new android.support.v7.app.AlertController$AlertParams$1
                android.content.Context r5 = r1.mContext
                int r6 = r10.mMultiChoiceItemLayout
                r7 = 16908308(0x1020014, float:2.3877285E-38)
                java.lang.CharSequence[] r8 = r1.mItems
                r3 = r12
                r4 = r1
                r9 = r2
                r3.<init>(r5, r6, r7, r8, r9)
                goto L_0x00ed
            L_0x00ac:
                android.support.v7.app.AlertController$AlertParams$2 r12 = new android.support.v7.app.AlertController$AlertParams$2
                android.content.Context r5 = r1.mContext
                r7 = 0
                r3 = r12
                r4 = r1
                r8 = r2
                r9 = r10
                r3.<init>(r5, r6, r7, r8, r9)
                goto L_0x00ed
            L_0x00b9:
                boolean r3 = r1.mIsSingleChoice
                if (r3 == 0) goto L_0x00c0
                int r3 = r10.mSingleChoiceItemLayout
                goto L_0x00c2
            L_0x00c0:
                int r3 = r10.mListItemLayout
            L_0x00c2:
                r6 = r3
                android.database.Cursor r7 = r1.mCursor
                r3 = 16908308(0x1020014, float:2.3877285E-38)
                if (r7 == 0) goto L_0x00df
                android.widget.SimpleCursorAdapter r12 = new android.widget.SimpleCursorAdapter
                android.content.Context r5 = r1.mContext
                java.lang.String[] r8 = new java.lang.String[r11]
                java.lang.String r4 = r1.mLabelColumn
                r9 = 0
                r8[r9] = r4
                int[] r13 = new int[r11]
                r13[r9] = r3
                r4 = r12
                r9 = r13
                r4.<init>(r5, r6, r7, r8, r9)
                goto L_0x00ed
            L_0x00df:
                android.widget.ListAdapter r12 = r1.mAdapter
                if (r12 == 0) goto L_0x00e4
                goto L_0x00ed
            L_0x00e4:
                android.support.v7.app.AlertController$CheckedItemAdapter r12 = new android.support.v7.app.AlertController$CheckedItemAdapter
                android.content.Context r4 = r1.mContext
                java.lang.CharSequence[] r5 = r1.mItems
                r12.<init>(r4, r6, r3, r5)
            L_0x00ed:
                r10.mAdapter = r12
                int r3 = r1.mCheckedItem
                r10.mCheckedItem = r3
                android.content.DialogInterface$OnClickListener r3 = r1.mOnClickListener
                if (r3 == 0) goto L_0x0100
                android.support.v7.app.AlertController$AlertParams$3 r3 = new android.support.v7.app.AlertController$AlertParams$3
                r3.<init>(r10)
                r2.setOnItemClickListener(r3)
                goto L_0x010c
            L_0x0100:
                android.content.DialogInterface$OnMultiChoiceClickListener r3 = r1.mOnCheckboxClickListener
                if (r3 == 0) goto L_0x010c
                android.support.v7.app.AlertController$AlertParams$4 r3 = new android.support.v7.app.AlertController$AlertParams$4
                r3.<init>(r2, r10)
                r2.setOnItemClickListener(r3)
            L_0x010c:
                android.widget.AdapterView$OnItemSelectedListener r3 = r1.mOnItemSelectedListener
                if (r3 == 0) goto L_0x0113
                r2.setOnItemSelectedListener(r3)
            L_0x0113:
                boolean r3 = r1.mIsSingleChoice
                if (r3 == 0) goto L_0x011b
                r2.setChoiceMode(r11)
                goto L_0x0123
            L_0x011b:
                boolean r3 = r1.mIsMultiChoice
                if (r3 == 0) goto L_0x0123
                r3 = 2
                r2.setChoiceMode(r3)
            L_0x0123:
                r10.mListView = r2
            L_0x0125:
                android.view.View r3 = r1.mView
                if (r3 == 0) goto L_0x013e
                boolean r2 = r1.mViewSpacingSpecified
                if (r2 == 0) goto L_0x013a
                int r4 = r1.mViewSpacingLeft
                int r5 = r1.mViewSpacingTop
                int r6 = r1.mViewSpacingRight
                int r7 = r1.mViewSpacingBottom
                r2 = r10
                r2.setView(r3, r4, r5, r6, r7)
                goto L_0x0145
            L_0x013a:
                r10.setView((android.view.View) r3)
                goto L_0x0145
            L_0x013e:
                int r1 = r1.mViewLayoutResId
                if (r1 == 0) goto L_0x0145
                r10.setView((int) r1)
            L_0x0145:
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                boolean r1 = r1.mCancelable
                r0.setCancelable(r1)
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                boolean r1 = r1.mCancelable
                if (r1 == 0) goto L_0x0155
                r0.setCanceledOnTouchOutside(r11)
            L_0x0155:
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                android.content.DialogInterface$OnCancelListener r1 = r1.mOnCancelListener
                r0.setOnCancelListener(r1)
                android.support.v7.app.AlertController$AlertParams r1 = r14.f0P
                android.content.DialogInterface$OnDismissListener r1 = r1.mOnDismissListener
                r0.setOnDismissListener(r1)
                android.support.v7.app.AlertController$AlertParams r14 = r14.f0P
                android.content.DialogInterface$OnKeyListener r14 = r14.mOnKeyListener
                if (r14 == 0) goto L_0x016c
                r0.setOnKeyListener(r14)
            L_0x016c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AlertDialog.Builder.create():android.support.v7.app.AlertDialog");
        }

        public Context getContext() {
            return this.f0P.mContext;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f0P.mCancelable = z;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.f0P.mCustomTitleView = view;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.f0P.mIcon = drawable;
            return this;
        }

        public Builder setMessage(int i) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mMessage = alertParams.mContext.getText(i);
            return this;
        }

        public Builder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(i);
            this.f0P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mNeutralButtonText = charSequence;
            alertParams.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f0P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f0P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(i);
            this.f0P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setTitle(int i) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mTitle = alertParams.mContext.getText(i);
            return this;
        }

        public Builder setView(View view) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.f0P.mMessage = charSequence;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.f0P.mTitle = charSequence;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mNegativeButtonText = charSequence;
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f0P;
            alertParams.mPositiveButtonText = charSequence;
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }
    }

    protected AlertDialog(Context context, int i) {
        super(context, resolveDialogTheme(context, i));
    }

    static int resolveDialogTheme(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }
}
