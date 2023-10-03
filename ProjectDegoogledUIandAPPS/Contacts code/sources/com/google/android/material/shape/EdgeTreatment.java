package com.google.android.material.shape;

import com.android.contacts.ContactPhotoManager;

public class EdgeTreatment implements Cloneable {
    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        shapePath.lineTo(f, ContactPhotoManager.OFFSET_DEFAULT);
    }

    public EdgeTreatment clone() {
        try {
            return (EdgeTreatment) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
