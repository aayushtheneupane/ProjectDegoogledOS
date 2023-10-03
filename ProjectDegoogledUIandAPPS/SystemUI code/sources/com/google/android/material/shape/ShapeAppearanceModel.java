package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShapeAppearanceModel {
    private EdgeTreatment bottomEdge;
    private CornerTreatment bottomLeftCorner;
    private CornerTreatment bottomRightCorner;
    private EdgeTreatment leftEdge;
    private final Set<OnChangedListener> onChangedListeners;
    private EdgeTreatment rightEdge;
    private EdgeTreatment topEdge;
    private CornerTreatment topLeftCorner;
    private CornerTreatment topRightCorner;

    public interface OnChangedListener {
        void onShapeAppearanceModelChanged();
    }

    public ShapeAppearanceModel() {
        this.onChangedListeners = new LinkedHashSet();
        setTopLeftCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setTopRightCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setBottomRightCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setBottomLeftCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setLeftEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setTopEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setRightEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setBottomEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        onShapeAppearanceModelChanged();
    }

    public ShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.onChangedListeners = new LinkedHashSet();
        setTopLeftCornerInternal(shapeAppearanceModel.getTopLeftCorner().clone());
        setTopRightCornerInternal(shapeAppearanceModel.getTopRightCorner().clone());
        setBottomRightCornerInternal(shapeAppearanceModel.getBottomRightCorner().clone());
        setBottomLeftCornerInternal(shapeAppearanceModel.getBottomLeftCorner().clone());
        setLeftEdgeInternal(shapeAppearanceModel.getLeftEdge().clone());
        setTopEdgeInternal(shapeAppearanceModel.getTopEdge().clone());
        setRightEdgeInternal(shapeAppearanceModel.getRightEdge().clone());
        setBottomEdgeInternal(shapeAppearanceModel.getBottomEdge().clone());
    }

    public ShapeAppearanceModel(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, 0);
    }

    public ShapeAppearanceModel(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this.onChangedListeners = new LinkedHashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        if (resourceId2 != 0) {
            context = new ContextThemeWrapper(context, resourceId);
            resourceId = resourceId2;
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R$styleable.ShapeAppearance);
        int i4 = obtainStyledAttributes2.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
        int i5 = obtainStyledAttributes2.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i4);
        int i6 = obtainStyledAttributes2.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i4);
        int i7 = obtainStyledAttributes2.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i4);
        int i8 = obtainStyledAttributes2.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i4);
        int dimensionPixelSize = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.ShapeAppearance_cornerSize, i3);
        int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.ShapeAppearance_cornerSizeTopLeft, dimensionPixelSize);
        int dimensionPixelSize3 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.ShapeAppearance_cornerSizeTopRight, dimensionPixelSize);
        int dimensionPixelSize4 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.ShapeAppearance_cornerSizeBottomRight, dimensionPixelSize);
        int dimensionPixelSize5 = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.ShapeAppearance_cornerSizeBottomLeft, dimensionPixelSize);
        setTopLeftCornerInternal(MaterialShapeUtils.createCornerTreatment(i5, dimensionPixelSize2));
        setTopRightCornerInternal(MaterialShapeUtils.createCornerTreatment(i6, dimensionPixelSize3));
        setBottomRightCornerInternal(MaterialShapeUtils.createCornerTreatment(i7, dimensionPixelSize4));
        setBottomLeftCornerInternal(MaterialShapeUtils.createCornerTreatment(i8, dimensionPixelSize5));
        setTopEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setRightEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setBottomEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setLeftEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        obtainStyledAttributes2.recycle();
    }

    public void setCornerRadius(float f) {
        setCornerRadii(f, f, f, f);
    }

    public void setCornerRadii(float f, float f2, float f3, float f4) {
        if ((setTopLeftCornerSizeInternal(f) | setTopRightCornerSizeInternal(f2) | setBottomRightCornerSizeInternal(f3)) || setBottomLeftCornerSizeInternal(f4)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopLeftCornerSizeInternal(float f) {
        CornerTreatment cornerTreatment = this.topLeftCorner;
        if (cornerTreatment.cornerSize == f) {
            return false;
        }
        cornerTreatment.cornerSize = f;
        return true;
    }

    private boolean setTopRightCornerSizeInternal(float f) {
        CornerTreatment cornerTreatment = this.topRightCorner;
        if (cornerTreatment.cornerSize == f) {
            return false;
        }
        cornerTreatment.cornerSize = f;
        return true;
    }

    private boolean setBottomRightCornerSizeInternal(float f) {
        CornerTreatment cornerTreatment = this.bottomRightCorner;
        if (cornerTreatment.cornerSize == f) {
            return false;
        }
        cornerTreatment.cornerSize = f;
        return true;
    }

    private boolean setBottomLeftCornerSizeInternal(float f) {
        CornerTreatment cornerTreatment = this.bottomLeftCorner;
        if (cornerTreatment.cornerSize == f) {
            return false;
        }
        cornerTreatment.cornerSize = f;
        return true;
    }

    private boolean setTopLeftCornerInternal(CornerTreatment cornerTreatment) {
        if (this.topLeftCorner == cornerTreatment) {
            return false;
        }
        this.topLeftCorner = cornerTreatment;
        return true;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    private boolean setTopRightCornerInternal(CornerTreatment cornerTreatment) {
        if (this.topRightCorner == cornerTreatment) {
            return false;
        }
        this.topRightCorner = cornerTreatment;
        return true;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    private boolean setBottomRightCornerInternal(CornerTreatment cornerTreatment) {
        if (this.bottomRightCorner == cornerTreatment) {
            return false;
        }
        this.bottomRightCorner = cornerTreatment;
        return true;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    private boolean setBottomLeftCornerInternal(CornerTreatment cornerTreatment) {
        if (this.bottomLeftCorner == cornerTreatment) {
            return false;
        }
        this.bottomLeftCorner = cornerTreatment;
        return true;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    private boolean setLeftEdgeInternal(EdgeTreatment edgeTreatment) {
        if (this.leftEdge == edgeTreatment) {
            return false;
        }
        this.leftEdge = edgeTreatment;
        return true;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public void setTopEdge(EdgeTreatment edgeTreatment) {
        if (setTopEdgeInternal(edgeTreatment)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopEdgeInternal(EdgeTreatment edgeTreatment) {
        if (this.topEdge == edgeTreatment) {
            return false;
        }
        this.topEdge = edgeTreatment;
        return true;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    private boolean setRightEdgeInternal(EdgeTreatment edgeTreatment) {
        if (this.rightEdge == edgeTreatment) {
            return false;
        }
        this.rightEdge = edgeTreatment;
        return true;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    private boolean setBottomEdgeInternal(EdgeTreatment edgeTreatment) {
        if (this.bottomEdge == edgeTreatment) {
            return false;
        }
        this.bottomEdge = edgeTreatment;
        return true;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    /* access modifiers changed from: package-private */
    public void addOnChangedListener(OnChangedListener onChangedListener) {
        this.onChangedListeners.add(onChangedListener);
    }

    /* access modifiers changed from: package-private */
    public void removeOnChangedListener(OnChangedListener onChangedListener) {
        this.onChangedListeners.remove(onChangedListener);
    }

    private void onShapeAppearanceModelChanged() {
        for (OnChangedListener next : this.onChangedListeners) {
            if (next != null) {
                next.onShapeAppearanceModelChanged();
            }
        }
    }

    public boolean isRoundRect() {
        boolean z = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.topLeftCorner.getCornerSize();
        boolean z2 = this.topRightCorner.getCornerSize() == cornerSize && this.bottomLeftCorner.getCornerSize() == cornerSize && this.bottomRightCorner.getCornerSize() == cornerSize;
        boolean z3 = (this.topRightCorner instanceof RoundedCornerTreatment) && (this.topLeftCorner instanceof RoundedCornerTreatment) && (this.bottomRightCorner instanceof RoundedCornerTreatment) && (this.bottomLeftCorner instanceof RoundedCornerTreatment);
        if (!z || !z2 || !z3) {
            return false;
        }
        return true;
    }
}
