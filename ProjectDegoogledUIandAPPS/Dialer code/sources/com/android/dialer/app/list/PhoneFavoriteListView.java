package com.android.dialer.app.list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.GridView;
import android.widget.ImageView;
import com.android.dialer.R;
import com.android.dialer.app.list.DragDropController;
import com.android.dialer.common.LogUtil;
import com.android.dialer.searchfragment.list.NewSearchFragment;

public class PhoneFavoriteListView extends GridView implements OnDragDropListener, DragDropController.DragItemContainer {
    private int animationDuration;
    /* access modifiers changed from: private */
    public int bottomScrollBound;
    private DragDropController dragDropController;
    private final Runnable dragScroller;
    /* access modifiers changed from: private */
    public Bitmap dragShadowBitmap;
    private int dragShadowLeft;
    private final AnimatorListenerAdapter dragShadowOverAnimatorListener;
    /* access modifiers changed from: private */
    public ImageView dragShadowOverlay;
    private View dragShadowParent;
    private int dragShadowTop;
    private boolean isDragScrollerRunning;
    /* access modifiers changed from: private */
    public int lastDragY;
    final int[] locationOnScreen;
    /* access modifiers changed from: private */
    public Handler scrollHandler;
    /* access modifiers changed from: private */
    public int topScrollBound;
    private int touchDownForDragStartY;
    private int touchOffsetToChildLeft;
    private int touchOffsetToChildTop;
    private float touchSlop;

    public PhoneFavoriteListView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public DragDropController getDragDropController() {
        return this.dragDropController;
    }

    public PhoneFavoriteSquareTileView getViewForLocation(int i, int i2) {
        View view;
        getLocationOnScreen(this.locationOnScreen);
        int[] iArr = this.locationOnScreen;
        int i3 = 0;
        int i4 = i - iArr[0];
        int i5 = i2 - iArr[1];
        int childCount = getChildCount();
        while (true) {
            if (i3 >= childCount) {
                view = null;
                break;
            }
            view = getChildAt(i3);
            if (i5 >= view.getTop() && i5 <= view.getBottom() && i4 >= view.getLeft() && i4 <= view.getRight()) {
                break;
            }
            i3++;
        }
        if (!(view instanceof PhoneFavoriteSquareTileView)) {
            return null;
        }
        return (PhoneFavoriteSquareTileView) view;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.touchSlop = (float) ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
    }

    public boolean onDragEvent(DragEvent dragEvent) {
        int action = dragEvent.getAction();
        int x = (int) dragEvent.getX();
        int y = (int) dragEvent.getY();
        switch (action) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return "PHONE_FAVORITE_TILE".equals(dragEvent.getLocalState()) && this.dragDropController.handleDragStarted(this, x, y);
            case 2:
                this.lastDragY = y;
                this.dragDropController.handleDragHovered(this, x, y);
                if (!this.isDragScrollerRunning && ((float) Math.abs(this.lastDragY - this.touchDownForDragStartY)) >= this.touchSlop * 4.0f) {
                    this.isDragScrollerRunning = true;
                    if (this.scrollHandler == null) {
                        this.scrollHandler = getHandler();
                    }
                    this.scrollHandler.postDelayed(this.dragScroller, 5);
                    break;
                }
            case 3:
            case 4:
            case 6:
                if (this.scrollHandler == null) {
                    this.scrollHandler = getHandler();
                }
                this.scrollHandler.removeCallbacks(this.dragScroller);
                this.isDragScrollerRunning = false;
                if (action == 3 || action == 4) {
                    this.dragDropController.handleDragFinished(x, y, false);
                    break;
                }
            case 5:
                int height = (int) (((float) getHeight()) * 0.2f);
                this.topScrollBound = getTop() + height;
                this.bottomScrollBound = getBottom() - height;
                break;
        }
    }

    public void onDragFinished(int i, int i2) {
        ImageView imageView = this.dragShadowOverlay;
        if (imageView != null) {
            imageView.clearAnimation();
            this.dragShadowOverlay.animate().alpha(0.0f).setDuration((long) this.animationDuration).setListener(this.dragShadowOverAnimatorListener).start();
        }
    }

    public void onDragHovered(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        this.dragShadowParent.getLocationOnScreen(this.locationOnScreen);
        int i3 = i - this.touchOffsetToChildLeft;
        int[] iArr = this.locationOnScreen;
        this.dragShadowLeft = i3 - iArr[0];
        this.dragShadowTop = (i2 - this.touchOffsetToChildTop) - iArr[1];
        ImageView imageView = this.dragShadowOverlay;
        if (imageView != null) {
            imageView.setX((float) this.dragShadowLeft);
            this.dragShadowOverlay.setY((float) this.dragShadowTop);
        }
    }

    public void onDragStarted(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        ImageView imageView = this.dragShadowOverlay;
        if (imageView != null) {
            imageView.clearAnimation();
            phoneFavoriteSquareTileView.setDrawingCacheEnabled(true);
            Bitmap drawingCache = phoneFavoriteSquareTileView.getDrawingCache();
            Bitmap bitmap = null;
            if (drawingCache != null) {
                try {
                    bitmap = drawingCache.copy(Bitmap.Config.ARGB_8888, false);
                } catch (OutOfMemoryError e) {
                    LogUtil.m10w("PhoneFavoriteListView", "Failed to copy bitmap from Drawing cache", e);
                }
            }
            phoneFavoriteSquareTileView.destroyDrawingCache();
            phoneFavoriteSquareTileView.setDrawingCacheEnabled(false);
            this.dragShadowBitmap = bitmap;
            if (this.dragShadowBitmap != null) {
                phoneFavoriteSquareTileView.getLocationOnScreen(this.locationOnScreen);
                int[] iArr = this.locationOnScreen;
                this.dragShadowLeft = iArr[0];
                this.dragShadowTop = iArr[1];
                this.touchOffsetToChildLeft = i - this.dragShadowLeft;
                this.touchOffsetToChildTop = i2 - this.dragShadowTop;
                this.dragShadowParent.getLocationOnScreen(iArr);
                int i3 = this.dragShadowLeft;
                int[] iArr2 = this.locationOnScreen;
                this.dragShadowLeft = i3 - iArr2[0];
                this.dragShadowTop -= iArr2[1];
                this.dragShadowOverlay.setImageBitmap(this.dragShadowBitmap);
                this.dragShadowOverlay.setVisibility(0);
                this.dragShadowOverlay.setAlpha(0.7f);
                this.dragShadowOverlay.setX((float) this.dragShadowLeft);
                this.dragShadowOverlay.setY((float) this.dragShadowTop);
            }
        }
    }

    public void onDroppedOnRemove() {
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.touchDownForDragStartY = (int) motionEvent.getY();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setDragShadowOverlay(ImageView imageView) {
        this.dragShadowOverlay = imageView;
        this.dragShadowParent = (View) this.dragShadowOverlay.getParent();
    }

    public PhoneFavoriteListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhoneFavoriteListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.locationOnScreen = new int[2];
        this.dragScroller = new Runnable() {
            public void run() {
                if (PhoneFavoriteListView.this.lastDragY <= PhoneFavoriteListView.this.topScrollBound) {
                    PhoneFavoriteListView.this.smoothScrollBy(-25, 5);
                } else if (PhoneFavoriteListView.this.lastDragY >= PhoneFavoriteListView.this.bottomScrollBound) {
                    PhoneFavoriteListView.this.smoothScrollBy(25, 5);
                }
                PhoneFavoriteListView.this.scrollHandler.postDelayed(this, 5);
            }
        };
        this.isDragScrollerRunning = false;
        this.dragShadowOverAnimatorListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (PhoneFavoriteListView.this.dragShadowBitmap != null) {
                    PhoneFavoriteListView.this.dragShadowBitmap.recycle();
                    Bitmap unused = PhoneFavoriteListView.this.dragShadowBitmap = null;
                }
                PhoneFavoriteListView.this.dragShadowOverlay.setVisibility(8);
                PhoneFavoriteListView.this.dragShadowOverlay.setImageBitmap((Bitmap) null);
            }
        };
        this.dragDropController = new DragDropController(this);
        this.animationDuration = context.getResources().getInteger(R.integer.fade_duration);
        this.touchSlop = (float) ViewConfiguration.get(context).getScaledPagingTouchSlop();
        this.dragDropController.addOnDragDropListener(this);
    }
}
