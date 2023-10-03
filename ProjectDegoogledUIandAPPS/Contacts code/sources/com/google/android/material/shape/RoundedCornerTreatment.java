package com.google.android.material.shape;

import com.android.contacts.ContactPhotoManager;

public class RoundedCornerTreatment extends CornerTreatment implements Cloneable {
    public RoundedCornerTreatment(float f) {
        super(f);
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        float f3 = this.cornerSize;
        shapePath.reset(ContactPhotoManager.OFFSET_DEFAULT, f3 * f2, 180.0f, 180.0f - f);
        float f4 = f3 * 2.0f * f2;
        shapePath.addArc(ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, f4, f4, 180.0f, f);
    }
}
