package com.google.android.apps.photosgo.editor.nativerenderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.InitializationResult;
import com.google.android.apps.photosgo.editor.PresetThumbnail;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;

/* compiled from: PG */
public final class NativeRenderer implements bxc {

    /* renamed from: a */
    public final String f4835a;
    public final long editListHandle = 0;
    public final long editProcessorHandle = 0;
    public final long gpuRendererHandle = 0;
    public boolean isEditListValid;
    public boolean isNdeEnabled;
    public final long thumbnailProcessorHandle = 0;

    public NativeRenderer(cwf cwf) {
        cwf.mo3858a();
        allocate();
        this.f4835a = ColorSpace.Named.SRGB.name();
    }

    private native void allocate();

    public native void changeToDesiredCropRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, RectF rectF);

    public native boolean computeEditingData(boolean z);

    public native Bitmap computeResultImage(PipelineParams pipelineParams, boolean z, String str);

    public native boolean drawFrame();

    public native PipelineParams fitAndRotateRect(PipelineParams pipelineParams, PipelineParams pipelineParams2, float f);

    public native PipelineParams getAdjustmentsAutoParams(PipelineParams pipelineParams);

    public native PipelineParams getAdvancedParams(PipelineParams pipelineParams);

    public native boolean getCroppedZoomParams(PipelineParams pipelineParams);

    public native RectF getImageScreenRect(PipelineParams pipelineParams);

    public native PresetThumbnail getPresetThumbnail(PipelineParams pipelineParams, String str);

    public native boolean getUncroppedZoomParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, PipelineParams pipelineParams);

    public native RectF getUserFriendlyCropCoordinates(PipelineParams pipelineParams);

    public native boolean initializeThumbnailProcessor(Context context, Bitmap bitmap);

    public native boolean isCropWidthConstrained(PipelineParams pipelineParams, float f, float f2, float f3, float f4);

    public native boolean loadGpuInputImage();

    public native PipelineParams magicMove(PipelineParams pipelineParams, int i, float f, float f2, float f3, float f4, float f5);

    public native PipelineParams magicPinch(PipelineParams pipelineParams, float f, float f2, float f3, float f4);

    public native InitializationResult nativeInitializeImage(Context context, Bitmap bitmap);

    public native void resizeCropRectWithForcedAspectRatio(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, RectF rectF);

    public native boolean setPipelineParams(PipelineParams pipelineParams);

    public native boolean surfaceChanged(int i, int i2);

    public native void surfaceCreated(Context context, boolean z);

    public native PipelineParams zoomToFitRect(PipelineParams pipelineParams);
}
