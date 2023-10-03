package android.support.design.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix = new Matrix();
    private final float[] tempStartValues = new float[9];

    public Object evaluate(float f, Object obj, Object obj2) {
        ((Matrix) obj).getValues(this.tempStartValues);
        ((Matrix) obj2).getValues(this.tempEndValues);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.tempEndValues;
            float f2 = fArr[i];
            float[] fArr2 = this.tempStartValues;
            fArr[i] = ((f2 - fArr2[i]) * f) + fArr2[i];
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
