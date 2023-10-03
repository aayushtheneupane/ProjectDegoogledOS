package com.android.messaging.p041ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.p016v4.media.session.C0107q;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MediaPickerMessagePartData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.p041ui.p042a.C1079f;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.messaging.ui.MultiAttachmentLayout */
public class MultiAttachmentLayout extends FrameLayout {

    /* renamed from: sf */
    private static final C1061P[] f1652sf = {null, null, new C1061P(new C1065S[]{C1065S.m2643z(0, 0), C1065S.m2643z(2, 0)}), new C1061P(new C1065S[]{C1065S.m2643z(0, 0), C1065S.m2642B(2, 0), C1065S.m2642B(2, 1)}), new C1061P(new C1065S[]{C1065S.m2643z(0, 0), C1065S.m2642B(2, 0), C1065S.m2641A(2, 1), C1065S.m2641A(3, 1)})};

    /* renamed from: tf */
    private static final C1061P[] f1653tf = {null, null, new C1061P(new C1065S[]{C1065S.m2643z(2, 0), C1065S.m2643z(0, 0)}), new C1061P(new C1065S[]{C1065S.m2643z(2, 0), C1065S.m2642B(0, 0), C1065S.m2642B(0, 1)}), new C1061P(new C1065S[]{C1065S.m2643z(2, 0), C1065S.m2642B(0, 0), C1065S.m2641A(1, 1), C1065S.m2641A(0, 1)})};

    /* renamed from: nf */
    private C1061P f1654nf;

    /* renamed from: of */
    private ArrayList f1655of = new ArrayList();

    /* renamed from: pf */
    private int f1656pf;

    /* renamed from: qf */
    private TextView f1657qf;

    /* renamed from: rf */
    private C1063Q f1658rf;

    public MultiAttachmentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void mo7041a(C1114b bVar) {
    }

    /* renamed from: a */
    public void mo7042a(Iterable iterable, Rect rect, int i) {
        C1066T t;
        MessagePartData messagePartData;
        Rect rect2 = rect;
        int i2 = i;
        ArrayList arrayList = this.f1655of;
        this.f1655of = new ArrayList();
        removeView(this.f1657qf);
        this.f1657qf = null;
        C1424b.m3592ia(iterable != null);
        if (C0107q.isLayoutRtl(getRootView())) {
            C1061P[] pArr = f1653tf;
            this.f1654nf = pArr[Math.min(i2, pArr.length - 1)];
        } else {
            C1061P[] pArr2 = f1652sf;
            this.f1654nf = pArr2[Math.min(i2, pArr2.length - 1)];
        }
        C1424b.m3594t(this.f1654nf);
        this.f1656pf = i2 - this.f1654nf.f1664_F.size();
        C1424b.m3592ia(this.f1656pf >= 0);
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = this.f1654nf.f1664_F.size();
        Iterator it = iterable.iterator();
        int i3 = 0;
        while (it.hasNext() && i3 < size) {
            MessagePartData messagePartData2 = (MessagePartData) it.next();
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    t = null;
                    break;
                }
                t = (C1066T) arrayList.get(i4);
                if (t.attachment.equals(messagePartData2) && !(t.attachment instanceof PendingAttachmentData)) {
                    arrayList.remove(i4);
                    break;
                }
                i4++;
            }
            if (t == null) {
                messagePartData = messagePartData2;
                View a = C1269l.m3184a(from, messagePartData2, this, 2, false, this.f1658rf);
                if (a != null) {
                    boolean z = a instanceof AsyncImageView;
                    addView(a);
                    t = new C1066T(a, messagePartData);
                    if (size == 2 && i3 == 1 && rect2 != null) {
                        t.f1673bG = rect2.left;
                        t.f1674cG = rect2.top;
                        t.f1675dG = rect.width();
                        t.f1676eG = rect.height();
                    }
                }
            } else {
                messagePartData = messagePartData2;
            }
            i3++;
            C1424b.m3594t(t);
            this.f1655of.add(t);
            if (i3 == 0 && (messagePartData instanceof MediaPickerMessagePartData)) {
                new C1079f(((MediaPickerMessagePartData) messagePartData).mo6241kh(), t.view).mo7127dc();
            }
            t.f1672aG = i3 > 0;
        }
        if (this.f1656pf > 0) {
            this.f1657qf = (TextView) from.inflate(R.layout.attachment_more_text_view, (ViewGroup) null);
            this.f1657qf.setText(getResources().getString(R.string.attachment_more_items, new Object[]{Integer.valueOf(this.f1656pf)}));
            addView(this.f1657qf);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            removeView(((C1066T) it2.next()).view);
        }
        requestLayout();
    }

    public void clearColorFilter() {
        Iterator it = this.f1655of.iterator();
        while (it.hasNext()) {
            View view = ((C1066T) it.next()).view;
            if (view instanceof AsyncImageView) {
                ((AsyncImageView) view).clearColorFilter();
            }
        }
    }

    /* renamed from: e */
    public View mo7044e(MessagePartData messagePartData) {
        Iterator it = this.f1655of.iterator();
        while (it.hasNext()) {
            C1066T t = (C1066T) it.next();
            if (t.attachment.equals(messagePartData) && !(t.attachment instanceof PendingAttachmentData)) {
                return t.view;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        TextView textView;
        int measuredWidth = getMeasuredWidth() / 4;
        int measuredHeight = getMeasuredHeight() / 2;
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.multiple_attachment_preview_padding);
        int size = this.f1655of.size();
        int i8 = 0;
        while (i8 < size) {
            C1066T t = (C1066T) this.f1655of.get(i8);
            View view = t.view;
            C1065S s = (C1065S) this.f1654nf.f1664_F.get(i8);
            int i9 = s.startX * measuredWidth;
            int i10 = s.startY * measuredHeight;
            int i11 = i9 + dimensionPixelOffset;
            int i12 = i10 + dimensionPixelOffset;
            view.layout(i11, i12, view.getMeasuredWidth() + i9, view.getMeasuredHeight() + i10);
            if (t.f1672aG) {
                if (!(t.attachment instanceof MediaPickerMessagePartData)) {
                    i7 = measuredWidth;
                } else {
                    View view2 = t.view;
                    int left = t.f1673bG - view2.getLeft();
                    int top = t.f1674cG - view2.getTop();
                    float width = ((float) t.f1675dG) / ((float) view2.getWidth());
                    i7 = measuredWidth;
                    float height = ((float) t.f1676eG) / ((float) view2.getHeight());
                    if (!(left == 0 && top == 0 && width == 1.0f && height == 1.0f)) {
                        i6 = measuredHeight;
                        AnimationSet animationSet = new AnimationSet(true);
                        i5 = dimensionPixelOffset;
                        animationSet.addAnimation(new TranslateAnimation((float) left, 0.0f, (float) top, 0.0f));
                        animationSet.addAnimation(new ScaleAnimation(width, 1.0f, height, 1.0f));
                        animationSet.setDuration((long) C1486ya.f2354YK);
                        animationSet.setInterpolator(C1486ya.f2357aL);
                        view2.startAnimation(animationSet);
                        view2.invalidate();
                        t.f1673bG = view2.getLeft();
                        t.f1674cG = view2.getTop();
                        t.f1675dG = view2.getWidth();
                        t.f1676eG = view2.getHeight();
                        t.f1672aG = false;
                    }
                }
                i6 = measuredHeight;
                i5 = dimensionPixelOffset;
                t.f1672aG = false;
            } else {
                i7 = measuredWidth;
                i6 = measuredHeight;
                i5 = dimensionPixelOffset;
                t.f1673bG = view.getLeft();
                t.f1674cG = view.getTop();
                t.f1675dG = view.getWidth();
                t.f1676eG = view.getHeight();
            }
            if (i8 == size - 1 && (textView = this.f1657qf) != null) {
                textView.layout(i11, i12, textView.getMeasuredWidth() + i9, this.f1657qf.getMeasuredHeight() + i10);
            }
            i8++;
            measuredWidth = i7;
            measuredHeight = i6;
            dimensionPixelOffset = i5;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        TextView textView;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.multiple_attachment_preview_width);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.multiple_attachment_preview_height);
        int min = Math.min(View.MeasureSpec.getSize(i), dimensionPixelSize);
        int i3 = min / 4;
        int i4 = dimensionPixelSize2 / 2;
        int size = this.f1655of.size();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.multiple_attachment_preview_padding);
        for (int i5 = 0; i5 < size; i5++) {
            View view = ((C1066T) this.f1655of.get(i5)).view;
            C1065S s = (C1065S) this.f1654nf.f1664_F.get(i5);
            view.measure(s.mo7078y(i3, dimensionPixelOffset), s.mo7077x(i4, dimensionPixelOffset));
            if (view instanceof AsyncImageView) {
                ((AsyncImageView) view).mo6858a(C1269l.m3185a(((C1066T) this.f1655of.get(i5)).attachment, view.getMeasuredWidth(), view.getMeasuredHeight()));
            }
            if (i5 == size - 1 && (textView = this.f1657qf) != null) {
                textView.measure(s.mo7078y(i3, dimensionPixelOffset), s.mo7077x(i4, dimensionPixelOffset));
            }
        }
        setMeasuredDimension(min, dimensionPixelSize2);
    }

    public void setColorFilter(int i) {
        Iterator it = this.f1655of.iterator();
        while (it.hasNext()) {
            View view = ((C1066T) it.next()).view;
            if (view instanceof AsyncImageView) {
                ((AsyncImageView) view).setColorFilter(i);
            }
        }
    }

    /* renamed from: a */
    public void mo7040a(C1063Q q) {
        this.f1658rf = q;
    }
}
