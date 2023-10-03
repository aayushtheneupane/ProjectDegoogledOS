package com.android.messaging.util.exif;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TimeZone;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.exif.d */
public class C1435d {

    /* renamed from: AL */
    public static final int f2252AL = m3657a(4, 29);

    /* renamed from: BL */
    private static HashSet f2253BL = new HashSet();

    /* renamed from: CL */
    protected static HashSet f2254CL = new HashSet(f2253BL);

    /* renamed from: DL */
    public static final ByteOrder f2255DL = ByteOrder.BIG_ENDIAN;
    public static final int TAG_APERTURE_VALUE = m3657a(2, -28158);
    public static final int TAG_ARTIST = m3657a(0, 315);
    public static final int TAG_BITS_PER_SAMPLE = m3657a(0, 258);
    public static final int TAG_BRIGHTNESS_VALUE = m3657a(2, -28157);
    public static final int TAG_CFA_PATTERN = m3657a(2, -23806);
    public static final int TAG_COLOR_SPACE = m3657a(2, -24575);
    public static final int TAG_COMPONENTS_CONFIGURATION = m3657a(2, -28415);
    public static final int TAG_COMPRESSED_BITS_PER_PIXEL = m3657a(2, -28414);
    public static final int TAG_COMPRESSION = m3657a(0, 259);
    public static final int TAG_CONTRAST = m3657a(2, -23544);
    public static final int TAG_COPYRIGHT = m3657a(0, -32104);
    public static final int TAG_CUSTOM_RENDERED = m3657a(2, -23551);
    public static final int TAG_DEVICE_SETTING_DESCRIPTION = m3657a(2, -23541);
    public static final int TAG_DIGITAL_ZOOM_RATIO = m3657a(2, -23548);
    public static final int TAG_EXIF_VERSION = m3657a(2, -28672);
    public static final int TAG_EXPOSURE_BIAS_VALUE = m3657a(2, -28156);
    public static final int TAG_EXPOSURE_INDEX = m3657a(2, -24043);
    public static final int TAG_EXPOSURE_MODE = m3657a(2, -23550);
    public static final int TAG_EXPOSURE_PROGRAM = m3657a(2, -30686);
    public static final int TAG_EXPOSURE_TIME = m3657a(2, -32102);
    public static final int TAG_FILE_SOURCE = m3657a(2, -23808);
    public static final int TAG_FLASH = m3657a(2, -28151);
    public static final int TAG_FLASHPIX_VERSION = m3657a(2, -24576);
    public static final int TAG_FLASH_ENERGY = m3657a(2, -24053);
    public static final int TAG_FOCAL_LENGTH = m3657a(2, -28150);
    public static final int TAG_FOCAL_PLANE_RESOLUTION_UNIT = m3657a(2, -24048);
    public static final int TAG_FOCAL_PLANE_X_RESOLUTION = m3657a(2, -24050);
    public static final int TAG_FOCAL_PLANE_Y_RESOLUTION = m3657a(2, -24049);
    public static final int TAG_F_NUMBER = m3657a(2, -32099);
    public static final int TAG_GAIN_CONTROL = m3657a(2, -23545);
    public static final int TAG_GPS_ALTITUDE = m3657a(4, 6);
    public static final int TAG_GPS_ALTITUDE_REF = m3657a(4, 5);
    public static final int TAG_GPS_AREA_INFORMATION = m3657a(4, 28);
    public static final int TAG_GPS_DEST_BEARING = m3657a(4, 24);
    public static final int TAG_GPS_DEST_BEARING_REF = m3657a(4, 23);
    public static final int TAG_GPS_DEST_DISTANCE = m3657a(4, 26);
    public static final int TAG_GPS_DEST_DISTANCE_REF = m3657a(4, 25);
    public static final int TAG_GPS_DEST_LATITUDE = m3657a(4, 20);
    public static final int TAG_GPS_DEST_LATITUDE_REF = m3657a(4, 19);
    public static final int TAG_GPS_DIFFERENTIAL = m3657a(4, 30);
    public static final int TAG_GPS_DOP = m3657a(4, 11);
    public static final int TAG_GPS_IMG_DIRECTION = m3657a(4, 17);
    public static final int TAG_GPS_IMG_DIRECTION_REF = m3657a(4, 16);
    public static final int TAG_GPS_LATITUDE = m3657a(4, 2);
    public static final int TAG_GPS_LATITUDE_REF = m3657a(4, 1);
    public static final int TAG_GPS_LONGITUDE = m3657a(4, 4);
    public static final int TAG_GPS_LONGITUDE_REF = m3657a(4, 3);
    public static final int TAG_GPS_MAP_DATUM = m3657a(4, 18);
    public static final int TAG_GPS_MEASURE_MODE = m3657a(4, 10);
    public static final int TAG_GPS_PROCESSING_METHOD = m3657a(4, 27);
    public static final int TAG_GPS_SPEED = m3657a(4, 13);
    public static final int TAG_GPS_SPEED_REF = m3657a(4, 12);
    public static final int TAG_GPS_STATUS = m3657a(4, 9);
    public static final int TAG_GPS_TRACK = m3657a(4, 15);
    public static final int TAG_GPS_TRACK_REF = m3657a(4, 14);
    public static final int TAG_GPS_VERSION_ID = m3657a(4, 0);
    public static final int TAG_IMAGE_DESCRIPTION = m3657a(0, 270);
    public static final int TAG_IMAGE_LENGTH = m3657a(0, 257);
    public static final int TAG_IMAGE_UNIQUE_ID = m3657a(2, -23520);
    public static final int TAG_IMAGE_WIDTH = m3657a(0, 256);
    public static final int TAG_INTEROPERABILITY_INDEX = m3657a(3, 1);
    public static final int TAG_ISO_SPEED_RATINGS = m3657a(2, -30681);
    public static final int TAG_JPEG_INTERCHANGE_FORMAT = m3657a(1, 513);
    public static final int TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = m3657a(1, 514);
    public static final int TAG_LIGHT_SOURCE = m3657a(2, -28152);
    public static final int TAG_MAKE = m3657a(0, 271);
    public static final int TAG_MAKER_NOTE = m3657a(2, -28036);
    public static final int TAG_MAX_APERTURE_VALUE = m3657a(2, -28155);
    public static final int TAG_METERING_MODE = m3657a(2, -28153);
    public static final int TAG_MODEL = m3657a(0, 272);
    public static final int TAG_OECF = m3657a(2, -30680);
    public static final int TAG_ORIENTATION = m3657a(0, 274);
    public static final int TAG_PHOTOMETRIC_INTERPRETATION = m3657a(0, 262);
    public static final int TAG_PIXEL_X_DIMENSION = m3657a(2, -24574);
    public static final int TAG_PIXEL_Y_DIMENSION = m3657a(2, -24573);
    public static final int TAG_PLANAR_CONFIGURATION = m3657a(0, 284);
    public static final int TAG_PRIMARY_CHROMATICITIES = m3657a(0, 319);
    public static final int TAG_REFERENCE_BLACK_WHITE = m3657a(0, 532);
    public static final int TAG_RELATED_SOUND_FILE = m3657a(2, -24572);
    public static final int TAG_RESOLUTION_UNIT = m3657a(0, 296);
    public static final int TAG_ROWS_PER_STRIP = m3657a(0, 278);
    public static final int TAG_SAMPLES_PER_PIXEL = m3657a(0, 277);
    public static final int TAG_SATURATION = m3657a(2, -23543);
    public static final int TAG_SCENE_CAPTURE_TYPE = m3657a(2, -23546);
    public static final int TAG_SCENE_TYPE = m3657a(2, -23807);
    public static final int TAG_SENSING_METHOD = m3657a(2, -24041);
    public static final int TAG_SHARPNESS = m3657a(2, -23542);
    public static final int TAG_SHUTTER_SPEED_VALUE = m3657a(2, -28159);
    public static final int TAG_SOFTWARE = m3657a(0, 305);
    public static final int TAG_SPATIAL_FREQUENCY_RESPONSE = m3657a(2, -24052);
    public static final int TAG_SPECTRAL_SENSITIVITY = m3657a(2, -30684);
    public static final int TAG_STRIP_BYTE_COUNTS = m3657a(0, 279);
    public static final int TAG_STRIP_OFFSETS = m3657a(0, 273);
    public static final int TAG_SUBJECT_AREA = m3657a(2, -28140);
    public static final int TAG_SUBJECT_DISTANCE = m3657a(2, -28154);
    public static final int TAG_SUBJECT_DISTANCE_RANGE = m3657a(2, -23540);
    public static final int TAG_SUBJECT_LOCATION = m3657a(2, -24044);
    public static final int TAG_TRANSFER_FUNCTION = m3657a(0, 301);
    public static final int TAG_USER_COMMENT = m3657a(2, -28026);
    public static final int TAG_WHITE_BALANCE = m3657a(2, -23549);
    public static final int TAG_WHITE_POINT = m3657a(0, 318);
    public static final int TAG_X_RESOLUTION = m3657a(0, 282);
    public static final int TAG_Y_CB_CR_COEFFICIENTS = m3657a(0, 529);
    public static final int TAG_Y_CB_CR_POSITIONING = m3657a(0, 531);
    public static final int TAG_Y_CB_CR_SUB_SAMPLING = m3657a(0, 530);
    public static final int TAG_Y_RESOLUTION = m3657a(0, 283);

    /* renamed from: oL */
    public static final int f2256oL = m3657a(0, 306);

    /* renamed from: pL */
    public static final int f2257pL = m3657a(0, -30871);

    /* renamed from: qL */
    public static final int f2258qL = m3657a(0, -30683);

    /* renamed from: rL */
    public static final int f2259rL = m3657a(2, -28669);

    /* renamed from: sL */
    public static final int f2260sL = m3657a(2, -28668);

    /* renamed from: tL */
    public static final int f2261tL = m3657a(2, -28016);

    /* renamed from: uL */
    public static final int f2262uL = m3657a(2, -28015);

    /* renamed from: vL */
    public static final int f2263vL = m3657a(2, -28014);

    /* renamed from: wL */
    public static final int f2264wL = m3657a(2, -24571);

    /* renamed from: xL */
    public static final int f2265xL = m3657a(2, -23547);

    /* renamed from: yL */
    public static final int f2266yL = m3657a(4, 7);

    /* renamed from: zL */
    public static final int f2267zL = m3657a(4, 8);
    private C1433b mData = new C1433b(f2255DL);

    /* renamed from: mL */
    private final DateFormat f2268mL;

    /* renamed from: nL */
    private SparseIntArray f2269nL;

    static {
        f2253BL.add(Short.valueOf((short) f2258qL));
        f2253BL.add(Short.valueOf((short) f2257pL));
        f2253BL.add(Short.valueOf((short) TAG_JPEG_INTERCHANGE_FORMAT));
        f2253BL.add(Short.valueOf((short) f2264wL));
        f2253BL.add(Short.valueOf((short) TAG_STRIP_OFFSETS));
        f2254CL.add(Short.valueOf((short) -1));
        f2254CL.add(Short.valueOf((short) TAG_JPEG_INTERCHANGE_FORMAT_LENGTH));
        f2254CL.add(Short.valueOf((short) TAG_STRIP_BYTE_COUNTS));
    }

    public C1435d() {
        new SimpleDateFormat("yyyy:MM:dd kk:mm:ss");
        this.f2268mL = new SimpleDateFormat("yyyy:MM:dd");
        Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        this.f2269nL = null;
        this.f2268mL.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /* renamed from: Ua */
    public static C1434c m3656Ua(int i) {
        C1434c cVar = new C1434c();
        switch (i) {
            case 2:
                cVar.scaleX = -1;
                break;
            case 3:
                cVar.rotation = 180;
                break;
            case 4:
                cVar.scaleY = -1;
                break;
            case 5:
                cVar.rotation = 90;
                cVar.scaleX = -1;
                cVar.f2251lL = true;
                break;
            case 6:
                cVar.rotation = 90;
                cVar.f2251lL = true;
                break;
            case 7:
                cVar.rotation = 270;
                cVar.scaleX = -1;
                cVar.f2251lL = true;
                break;
            case 8:
                cVar.rotation = 270;
                cVar.f2251lL = true;
                break;
        }
        return cVar;
    }

    /* renamed from: a */
    public static int m3657a(int i, short s) {
        return (i << 16) | (s & 65535);
    }

    /* renamed from: c */
    protected static int m3659c(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        int[] Jk = C1443l.m3709Jk();
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (Jk[i2] == iArr[i3]) {
                    i |= 1 << i2;
                    break;
                }
                i3++;
            }
        }
        return i;
    }

    /* renamed from: I */
    public C1442k mo8094I(int i, int i2) {
        if (!C1442k.m3692Za(i2)) {
            return null;
        }
        return this.mData.mo8081a((short) i, i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sa */
    public C1442k mo8095Sa(int i) {
        int i2 = mo8103yk().get(i);
        if (i2 == 0) {
            return null;
        }
        int i3 = i2 & 65535;
        return new C1442k((short) i, (short) ((i2 >> 16) & 255), i3, i >>> 16, i3 != 0);
    }

    /* renamed from: Ta */
    public int mo8096Ta(int i) {
        if (mo8103yk().get(i) == 0) {
            return -1;
        }
        return i >>> 16;
    }

    /* renamed from: Va */
    public Integer mo8097Va(int i) {
        C1442k kVar;
        int[] iArr;
        int Ta = mo8096Ta(i);
        if (!C1442k.m3692Za(Ta)) {
            kVar = null;
        } else {
            kVar = this.mData.mo8081a((short) i, Ta);
        }
        if (kVar == null) {
            iArr = null;
        } else {
            iArr = kVar.mo8124Hk();
        }
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        return new Integer(iArr[0]);
    }

    /* renamed from: a */
    public void mo8098a(Bitmap bitmap, OutputStream outputStream) {
        if (bitmap == null || outputStream == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        C1436e eVar = new C1436e(outputStream, this);
        eVar.mo8104a(this.mData);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, eVar);
        eVar.flush();
    }

    /* renamed from: b */
    public C1442k mo8099b(C1442k kVar) {
        return this.mData.mo8080a(kVar);
    }

    /* renamed from: d */
    public void mo8100d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                this.mData = new C1441j(this).read(inputStream);
            } catch (ExifInvalidFormatException e) {
                throw new IOException(C0632a.m1014a("Invalid exif format : ", e));
            }
        } else {
            throw new IllegalArgumentException("Argument is null");
        }
    }

    /* renamed from: p */
    public boolean mo8101p(byte[] bArr) {
        this.mData.mo8087qk();
        this.mData.mo8086p(bArr);
        return true;
    }

    /* renamed from: xk */
    public void mo8102xk() {
        this.mData = new C1433b(f2255DL);
    }

    /* access modifiers changed from: protected */
    /* renamed from: yk */
    public SparseIntArray mo8103yk() {
        if (this.f2269nL == null) {
            this.f2269nL = new SparseIntArray();
            int c = m3659c(new int[]{0, 1}) << 24;
            int i = c | 131072;
            int i2 = i | 0;
            this.f2269nL.put(TAG_MAKE, i2);
            int i3 = c | 262144;
            int i4 = i3 | 1;
            this.f2269nL.put(TAG_IMAGE_WIDTH, i4);
            this.f2269nL.put(TAG_IMAGE_LENGTH, i4);
            int i5 = c | 196608;
            this.f2269nL.put(TAG_BITS_PER_SAMPLE, i5 | 3);
            int i6 = i5 | 1;
            this.f2269nL.put(TAG_COMPRESSION, i6);
            this.f2269nL.put(TAG_PHOTOMETRIC_INTERPRETATION, i6);
            this.f2269nL.put(TAG_ORIENTATION, i6);
            this.f2269nL.put(TAG_SAMPLES_PER_PIXEL, i6);
            this.f2269nL.put(TAG_PLANAR_CONFIGURATION, i6);
            this.f2269nL.put(TAG_Y_CB_CR_SUB_SAMPLING, i5 | 2);
            this.f2269nL.put(TAG_Y_CB_CR_POSITIONING, i6);
            int i7 = c | 327680;
            int i8 = i7 | 1;
            this.f2269nL.put(TAG_X_RESOLUTION, i8);
            this.f2269nL.put(TAG_Y_RESOLUTION, i8);
            this.f2269nL.put(TAG_RESOLUTION_UNIT, i6);
            int i9 = i3 | 0;
            this.f2269nL.put(TAG_STRIP_OFFSETS, i9);
            this.f2269nL.put(TAG_ROWS_PER_STRIP, i4);
            this.f2269nL.put(TAG_STRIP_BYTE_COUNTS, i9);
            this.f2269nL.put(TAG_TRANSFER_FUNCTION, i5 | 768);
            this.f2269nL.put(TAG_WHITE_POINT, i7 | 2);
            int i10 = i7 | 6;
            this.f2269nL.put(TAG_PRIMARY_CHROMATICITIES, i10);
            this.f2269nL.put(TAG_Y_CB_CR_COEFFICIENTS, i7 | 3);
            this.f2269nL.put(TAG_REFERENCE_BLACK_WHITE, i10);
            this.f2269nL.put(f2256oL, i | 20);
            this.f2269nL.put(TAG_IMAGE_DESCRIPTION, i2);
            this.f2269nL.put(TAG_MAKE, i2);
            this.f2269nL.put(TAG_MODEL, i2);
            this.f2269nL.put(TAG_SOFTWARE, i2);
            this.f2269nL.put(TAG_ARTIST, i2);
            this.f2269nL.put(TAG_COPYRIGHT, i2);
            this.f2269nL.put(f2257pL, i4);
            this.f2269nL.put(f2258qL, i4);
            int c2 = (m3659c(new int[]{1}) << 24) | 262144 | 1;
            this.f2269nL.put(TAG_JPEG_INTERCHANGE_FORMAT, c2);
            this.f2269nL.put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, c2);
            int c3 = m3659c(new int[]{2}) << 24;
            int i11 = c3 | 458752;
            int i12 = i11 | 4;
            this.f2269nL.put(TAG_EXIF_VERSION, i12);
            this.f2269nL.put(TAG_FLASHPIX_VERSION, i12);
            int i13 = c3 | 196608;
            int i14 = i13 | 1;
            this.f2269nL.put(TAG_COLOR_SPACE, i14);
            this.f2269nL.put(TAG_COMPONENTS_CONFIGURATION, i12);
            int i15 = c3 | 327680 | 1;
            this.f2269nL.put(TAG_COMPRESSED_BITS_PER_PIXEL, i15);
            int i16 = 262144 | c3 | 1;
            this.f2269nL.put(TAG_PIXEL_X_DIMENSION, i16);
            this.f2269nL.put(TAG_PIXEL_Y_DIMENSION, i16);
            int i17 = i11 | 0;
            this.f2269nL.put(TAG_MAKER_NOTE, i17);
            this.f2269nL.put(TAG_USER_COMMENT, i17);
            int i18 = c3 | 131072;
            this.f2269nL.put(TAG_RELATED_SOUND_FILE, i18 | 13);
            int i19 = i18 | 20;
            this.f2269nL.put(f2259rL, i19);
            this.f2269nL.put(f2260sL, i19);
            int i20 = i18 | 0;
            this.f2269nL.put(f2261tL, i20);
            this.f2269nL.put(f2262uL, i20);
            this.f2269nL.put(f2263vL, i20);
            this.f2269nL.put(TAG_IMAGE_UNIQUE_ID, i18 | 33);
            this.f2269nL.put(TAG_EXPOSURE_TIME, i15);
            this.f2269nL.put(TAG_F_NUMBER, i15);
            this.f2269nL.put(TAG_EXPOSURE_PROGRAM, i14);
            this.f2269nL.put(TAG_SPECTRAL_SENSITIVITY, i20);
            int i21 = i13 | 0;
            this.f2269nL.put(TAG_ISO_SPEED_RATINGS, i21);
            this.f2269nL.put(TAG_OECF, i17);
            int i22 = c3 | 655360 | 1;
            this.f2269nL.put(TAG_SHUTTER_SPEED_VALUE, i22);
            this.f2269nL.put(TAG_APERTURE_VALUE, i15);
            this.f2269nL.put(TAG_BRIGHTNESS_VALUE, i22);
            this.f2269nL.put(TAG_EXPOSURE_BIAS_VALUE, i22);
            this.f2269nL.put(TAG_MAX_APERTURE_VALUE, i15);
            this.f2269nL.put(TAG_SUBJECT_DISTANCE, i15);
            this.f2269nL.put(TAG_METERING_MODE, i14);
            this.f2269nL.put(TAG_LIGHT_SOURCE, i14);
            this.f2269nL.put(TAG_FLASH, i14);
            this.f2269nL.put(TAG_FOCAL_LENGTH, i15);
            this.f2269nL.put(TAG_SUBJECT_AREA, i21);
            this.f2269nL.put(TAG_FLASH_ENERGY, i15);
            this.f2269nL.put(TAG_SPATIAL_FREQUENCY_RESPONSE, i17);
            this.f2269nL.put(TAG_FOCAL_PLANE_X_RESOLUTION, i15);
            this.f2269nL.put(TAG_FOCAL_PLANE_Y_RESOLUTION, i15);
            this.f2269nL.put(TAG_FOCAL_PLANE_RESOLUTION_UNIT, i14);
            this.f2269nL.put(TAG_SUBJECT_LOCATION, 2 | i13);
            this.f2269nL.put(TAG_EXPOSURE_INDEX, i15);
            this.f2269nL.put(TAG_SENSING_METHOD, i14);
            int i23 = i11 | 1;
            this.f2269nL.put(TAG_FILE_SOURCE, i23);
            this.f2269nL.put(TAG_SCENE_TYPE, i23);
            this.f2269nL.put(TAG_CFA_PATTERN, i17);
            this.f2269nL.put(TAG_CUSTOM_RENDERED, i14);
            this.f2269nL.put(TAG_EXPOSURE_MODE, i14);
            this.f2269nL.put(TAG_WHITE_BALANCE, i14);
            this.f2269nL.put(TAG_DIGITAL_ZOOM_RATIO, i15);
            this.f2269nL.put(f2265xL, i14);
            this.f2269nL.put(TAG_SCENE_CAPTURE_TYPE, i14);
            this.f2269nL.put(TAG_GAIN_CONTROL, i15);
            this.f2269nL.put(TAG_CONTRAST, i14);
            this.f2269nL.put(TAG_SATURATION, i14);
            this.f2269nL.put(TAG_SHARPNESS, i14);
            this.f2269nL.put(TAG_DEVICE_SETTING_DESCRIPTION, i17);
            this.f2269nL.put(TAG_SUBJECT_DISTANCE_RANGE, i14);
            this.f2269nL.put(f2264wL, i16);
            int c4 = m3659c(new int[]{4}) << 24;
            int i24 = 65536 | c4;
            this.f2269nL.put(TAG_GPS_VERSION_ID, i24 | 4);
            int i25 = c4 | 131072;
            int i26 = i25 | 2;
            this.f2269nL.put(TAG_GPS_LATITUDE_REF, i26);
            this.f2269nL.put(TAG_GPS_LONGITUDE_REF, i26);
            int i27 = c4 | 655360 | 3;
            this.f2269nL.put(TAG_GPS_LATITUDE, i27);
            this.f2269nL.put(TAG_GPS_LONGITUDE, i27);
            this.f2269nL.put(TAG_GPS_ALTITUDE_REF, i24 | 1);
            int i28 = 327680 | c4;
            int i29 = i28 | 1;
            this.f2269nL.put(TAG_GPS_ALTITUDE, i29);
            this.f2269nL.put(f2266yL, i28 | 3);
            int i30 = i25 | 0;
            this.f2269nL.put(f2267zL, i30);
            this.f2269nL.put(TAG_GPS_STATUS, i26);
            this.f2269nL.put(TAG_GPS_MEASURE_MODE, i26);
            this.f2269nL.put(TAG_GPS_DOP, i29);
            this.f2269nL.put(TAG_GPS_SPEED_REF, i26);
            this.f2269nL.put(TAG_GPS_SPEED, i29);
            this.f2269nL.put(TAG_GPS_TRACK_REF, i26);
            this.f2269nL.put(TAG_GPS_TRACK, i29);
            this.f2269nL.put(TAG_GPS_IMG_DIRECTION_REF, i26);
            this.f2269nL.put(TAG_GPS_IMG_DIRECTION, i29);
            this.f2269nL.put(TAG_GPS_MAP_DATUM, i30);
            this.f2269nL.put(TAG_GPS_DEST_LATITUDE_REF, i26);
            this.f2269nL.put(TAG_GPS_DEST_LATITUDE, i29);
            this.f2269nL.put(TAG_GPS_DEST_BEARING_REF, i26);
            this.f2269nL.put(TAG_GPS_DEST_BEARING, i29);
            this.f2269nL.put(TAG_GPS_DEST_DISTANCE_REF, i26);
            this.f2269nL.put(TAG_GPS_DEST_DISTANCE, i29);
            int i31 = 458752 | c4 | 0;
            this.f2269nL.put(TAG_GPS_PROCESSING_METHOD, i31);
            this.f2269nL.put(TAG_GPS_AREA_INFORMATION, i31);
            this.f2269nL.put(f2252AL, i25 | 11);
            this.f2269nL.put(TAG_GPS_DIFFERENTIAL, c4 | 196608 | 11);
            this.f2269nL.put(TAG_INTEROPERABILITY_INDEX, (m3659c(new int[]{3}) << 24) | 131072 | 0);
        }
        return this.f2269nL;
    }

    /* renamed from: a */
    protected static boolean m3658a(short s) {
        return f2253BL.contains(Short.valueOf(s));
    }
}
