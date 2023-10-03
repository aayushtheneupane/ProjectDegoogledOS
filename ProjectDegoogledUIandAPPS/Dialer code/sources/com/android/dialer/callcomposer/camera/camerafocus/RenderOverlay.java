package com.android.dialer.callcomposer.camera.camerafocus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RenderOverlay extends FrameLayout {
    /* access modifiers changed from: private */
    public List<Renderer> clients;
    private int[] position = new int[2];
    private RenderView renderView;
    /* access modifiers changed from: private */
    public List<Renderer> touchClients;

    @SuppressLint({"ClickableViewAccessibility"})
    private class RenderView extends View {
        public RenderView(Context context) {
            super(context);
            setWillNotDraw(false);
        }

        public void draw(Canvas canvas) {
            boolean z;
            super.draw(canvas);
            if (RenderOverlay.this.clients != null) {
                Iterator it = RenderOverlay.this.clients.iterator();
                loop0:
                while (true) {
                    z = false;
                    while (true) {
                        if (!it.hasNext()) {
                            break loop0;
                        }
                        OverlayRenderer overlayRenderer = (OverlayRenderer) it.next();
                        overlayRenderer.draw(canvas);
                        if (z || overlayRenderer.isVisible()) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    invalidate();
                }
            }
        }

        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            RenderOverlay.this.getLocationInWindow(RenderOverlay.this.position);
            super.onLayout(z, i, i2, i3, i4);
            if (RenderOverlay.this.clients != null) {
                for (PieRenderer layout : RenderOverlay.this.clients) {
                    layout.layout(i, i2, i3, i4);
                }
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z = false;
            if (RenderOverlay.this.touchClients != null) {
                for (PieRenderer onTouchEvent : RenderOverlay.this.touchClients) {
                    z |= onTouchEvent.onTouchEvent(motionEvent);
                }
            }
            return z;
        }
    }

    interface Renderer {
    }

    public RenderOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.renderView = new RenderView(context);
        addView(this.renderView, new FrameLayout.LayoutParams(-1, -1));
        this.clients = new ArrayList(10);
        this.touchClients = new ArrayList(10);
        setWillNotDraw(false);
        addRenderer(new PieRenderer(context));
    }

    public void addRenderer(Renderer renderer) {
        this.clients.add(renderer);
        ((OverlayRenderer) renderer).overlay = this;
        this.touchClients.add(0, renderer);
        ((PieRenderer) renderer).layout(getLeft(), getTop(), getRight(), getBottom());
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public PieRenderer getPieRenderer() {
        for (Renderer next : this.clients) {
            if (next instanceof PieRenderer) {
                return (PieRenderer) next;
            }
        }
        return null;
    }

    public void update() {
        this.renderView.invalidate();
    }
}
