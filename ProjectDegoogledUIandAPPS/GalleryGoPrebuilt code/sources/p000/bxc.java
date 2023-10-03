package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* renamed from: bxc */
/* compiled from: PG */
public interface bxc {
    void changeToDesiredCropRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, RectF rectF);

    boolean computeEditingData(boolean z);

    boolean drawFrame();

    PipelineParams fitAndRotateRect(PipelineParams pipelineParams, PipelineParams pipelineParams2, float f);

    PipelineParams getAdjustmentsAutoParams(PipelineParams pipelineParams);

    PipelineParams getAdvancedParams(PipelineParams pipelineParams);

    boolean getCroppedZoomParams(PipelineParams pipelineParams);

    RectF getImageScreenRect(PipelineParams pipelineParams);

    boolean getUncroppedZoomParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, PipelineParams pipelineParams);

    RectF getUserFriendlyCropCoordinates(PipelineParams pipelineParams);

    boolean initializeThumbnailProcessor(Context context, Bitmap bitmap);

    boolean isCropWidthConstrained(PipelineParams pipelineParams, float f, float f2, float f3, float f4);

    boolean loadGpuInputImage();

    PipelineParams magicMove(PipelineParams pipelineParams, int i, float f, float f2, float f3, float f4, float f5);

    PipelineParams magicPinch(PipelineParams pipelineParams, float f, float f2, float f3, float f4);

    void resizeCropRectWithForcedAspectRatio(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, RectF rectF);

    boolean setPipelineParams(PipelineParams pipelineParams);

    boolean surfaceChanged(int i, int i2);

    void surfaceCreated(Context context, boolean z);

    PipelineParams zoomToFitRect(PipelineParams pipelineParams);
}
