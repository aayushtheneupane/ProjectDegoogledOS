package com.android.messaging.p041ui.p042a;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* renamed from: com.android.messaging.ui.a.e */
class C1078e extends View {
    final /* synthetic */ C1079f this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1078e(C1079f fVar, Context context) {
        super(context);
        this.this$0 = fVar;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.clipRect(getLeft(), this.this$0.f1703fl.bottom - this.this$0.f1702el.top, getRight(), getBottom());
        canvas.drawColor(0);
        float alpha = this.this$0.f1698al.getAlpha();
        this.this$0.f1698al.setAlpha(1.0f);
        canvas.translate((float) this.this$0.f1700cl.left, (float) (this.this$0.f1700cl.top - this.this$0.f1702el.top));
        float width = (float) this.this$0.f1698al.getWidth();
        float height = (float) this.this$0.f1698al.getHeight();
        if (width > 0.0f && height > 0.0f) {
            canvas.scale(((float) this.this$0.f1700cl.width()) / width, ((float) this.this$0.f1700cl.height()) / height);
        }
        canvas.clipRect(0, 0, this.this$0.f1700cl.width(), this.this$0.f1700cl.height());
        if (!this.this$0.f1702el.isEmpty()) {
            this.this$0.f1698al.draw(canvas);
        }
        this.this$0.f1698al.setAlpha(alpha);
        canvas.restore();
    }
}
