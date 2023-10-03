package p000;

import android.content.res.AssetManager;
import android.util.Log;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import p003j$.util.DesugarTimeZone;

/* renamed from: abz */
/* compiled from: PG */
public final class abz {

    /* renamed from: A */
    private static final abx[] f124A = {new abx("PreviewImageStart", 257, 4), new abx("PreviewImageLength", 258, 4)};

    /* renamed from: B */
    private static final abx[] f125B = {new abx("AspectFrame", 4371, 3)};

    /* renamed from: C */
    private static final abx[] f126C;

    /* renamed from: D */
    private static final abx[][] f127D;

    /* renamed from: E */
    private static final abx[] f128E = {new abx("SubIFDPointer", 330, 4), new abx("ExifIFDPointer", 34665, 4), new abx("GPSInfoIFDPointer", 34853, 4), new abx("InteroperabilityIFDPointer", 40965, 4), new abx("CameraSettingsIFDPointer", 8224, 1), new abx("ImageProcessingIFDPointer", 8256, 1)};

    /* renamed from: F */
    private static final HashMap[] f129F = new HashMap[10];

    /* renamed from: G */
    private static final HashMap[] f130G = new HashMap[10];

    /* renamed from: H */
    private static final HashSet f131H = new HashSet(Arrays.asList(new String[]{"FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"}));

    /* renamed from: I */
    private static final HashMap f132I = new HashMap();

    /* renamed from: J */
    private static final byte[] f133J;

    /* renamed from: K */
    private static final byte[] f134K = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(f138d);

    /* renamed from: a */
    public static final String[] f135a = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};

    /* renamed from: b */
    public static final int[] f136b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};

    /* renamed from: c */
    public static final byte[] f137c = {65, 83, 67, 73, 73, 0, 0, 0};

    /* renamed from: d */
    public static final Charset f138d;

    /* renamed from: e */
    private static final int[] f139e = {8, 8, 8};

    /* renamed from: f */
    private static final int[] f140f = {8};

    /* renamed from: g */
    private static final byte[] f141g = {-1, -40, -1};

    /* renamed from: h */
    private static final byte[] f142h = {102, 116, 121, 112};

    /* renamed from: i */
    private static final byte[] f143i = {109, 105, 102, 49};

    /* renamed from: j */
    private static final byte[] f144j = {104, 101, 105, 99};

    /* renamed from: k */
    private static final byte[] f145k = {79, 76, 89, 77, 80, 0};

    /* renamed from: l */
    private static final byte[] f146l = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};

    /* renamed from: m */
    private static final byte[] f147m = {-119, 80, 78, 71, 13, 10, 26, 10};

    /* renamed from: n */
    private static final byte[] f148n = {101, 88, 73, 102};

    /* renamed from: o */
    private static final byte[] f149o = {73, 72, 68, 82};

    /* renamed from: p */
    private static final byte[] f150p = {73, 69, 78, 68};

    /* renamed from: q */
    private static final byte[] f151q = {82, 73, 70, 70};

    /* renamed from: r */
    private static final byte[] f152r = {87, 69, 66, 80};

    /* renamed from: s */
    private static final byte[] f153s = {69, 88, 73, 70};

    /* renamed from: t */
    private static final abx[] f154t = {new abx("NewSubfileType", 254, 4), new abx("SubfileType", 255, 4), new abx("ImageWidth", 256), new abx("ImageLength", 257), new abx("BitsPerSample", 258, 3), new abx("Compression", 259, 3), new abx("PhotometricInterpretation", 262, 3), new abx("ImageDescription", 270, 2), new abx("Make", 271, 2), new abx("Model", 272, 2), new abx("StripOffsets", 273), new abx("Orientation", 274, 3), new abx("SamplesPerPixel", 277, 3), new abx("RowsPerStrip", 278), new abx("StripByteCounts", 279), new abx("XResolution", 282, 5), new abx("YResolution", 283, 5), new abx("PlanarConfiguration", 284, 3), new abx("ResolutionUnit", 296, 3), new abx("TransferFunction", 301, 3), new abx("Software", 305, 2), new abx("DateTime", 306, 2), new abx("Artist", 315, 2), new abx("WhitePoint", 318, 5), new abx("PrimaryChromaticities", 319, 5), new abx("SubIFDPointer", 330, 4), new abx("JPEGInterchangeFormat", 513, 4), new abx("JPEGInterchangeFormatLength", 514, 4), new abx("YCbCrCoefficients", 529, 5), new abx("YCbCrSubSampling", 530, 3), new abx("YCbCrPositioning", 531, 3), new abx("ReferenceBlackWhite", 532, 5), new abx("Copyright", 33432, 2), new abx("ExifIFDPointer", 34665, 4), new abx("GPSInfoIFDPointer", 34853, 4), new abx("SensorTopBorder", 4, 4), new abx("SensorLeftBorder", 5, 4), new abx("SensorBottomBorder", 6, 4), new abx("SensorRightBorder", 7, 4), new abx("ISO", 23, 3), new abx("JpgFromRaw", 46, 7), new abx("Xmp", 700, 1)};

    /* renamed from: u */
    private static final abx[] f155u = {new abx("ExposureTime", 33434, 5), new abx("FNumber", 33437, 5), new abx("ExposureProgram", 34850, 3), new abx("SpectralSensitivity", 34852, 2), new abx("PhotographicSensitivity", 34855, 3), new abx("OECF", 34856, 7), new abx("SensitivityType", 34864, 3), new abx("StandardOutputSensitivity", 34865, 4), new abx("RecommendedExposureIndex", 34866, 4), new abx("ISOSpeed", 34867, 4), new abx("ISOSpeedLatitudeyyy", 34868, 4), new abx("ISOSpeedLatitudezzz", 34869, 4), new abx("ExifVersion", 36864, 2), new abx("DateTimeOriginal", 36867, 2), new abx("DateTimeDigitized", 36868, 2), new abx("OffsetTime", 36880, 2), new abx("OffsetTimeOriginal", 36881, 2), new abx("OffsetTimeDigitized", 36882, 2), new abx("ComponentsConfiguration", 37121, 7), new abx("CompressedBitsPerPixel", 37122, 5), new abx("ShutterSpeedValue", 37377, 10), new abx("ApertureValue", 37378, 5), new abx("BrightnessValue", 37379, 10), new abx("ExposureBiasValue", 37380, 10), new abx("MaxApertureValue", 37381, 5), new abx("SubjectDistance", 37382, 5), new abx("MeteringMode", 37383, 3), new abx("LightSource", 37384, 3), new abx("Flash", 37385, 3), new abx("FocalLength", 37386, 5), new abx("SubjectArea", 37396, 3), new abx("MakerNote", 37500, 7), new abx("UserComment", 37510, 7), new abx("SubSecTime", 37520, 2), new abx("SubSecTimeOriginal", 37521, 2), new abx("SubSecTimeDigitized", 37522, 2), new abx("FlashpixVersion", 40960, 7), new abx("ColorSpace", 40961, 3), new abx("PixelXDimension", 40962), new abx("PixelYDimension", 40963), new abx("RelatedSoundFile", 40964, 2), new abx("InteroperabilityIFDPointer", 40965, 4), new abx("FlashEnergy", 41483, 5), new abx("SpatialFrequencyResponse", 41484, 7), new abx("FocalPlaneXResolution", 41486, 5), new abx("FocalPlaneYResolution", 41487, 5), new abx("FocalPlaneResolutionUnit", 41488, 3), new abx("SubjectLocation", 41492, 3), new abx("ExposureIndex", 41493, 5), new abx("SensingMethod", 41495, 3), new abx("FileSource", 41728, 7), new abx("SceneType", 41729, 7), new abx("CFAPattern", 41730, 7), new abx("CustomRendered", 41985, 3), new abx("ExposureMode", 41986, 3), new abx("WhiteBalance", 41987, 3), new abx("DigitalZoomRatio", 41988, 5), new abx("FocalLengthIn35mmFilm", 41989, 3), new abx("SceneCaptureType", 41990, 3), new abx("GainControl", 41991, 3), new abx("Contrast", 41992, 3), new abx("Saturation", 41993, 3), new abx("Sharpness", 41994, 3), new abx("DeviceSettingDescription", 41995, 7), new abx("SubjectDistanceRange", 41996, 3), new abx("ImageUniqueID", 42016, 2), new abx("CameraOwnerName", 42032, 2), new abx("BodySerialNumber", 42033, 2), new abx("LensSpecification", 42034, 5), new abx("LensMake", 42035, 2), new abx("LensModel", 42036, 2), new abx("Gamma", 42240, 5), new abx("DNGVersion", 50706, 1), new abx("DefaultCropSize", 50720)};

    /* renamed from: v */
    private static final abx[] f156v = {new abx("GPSVersionID", 0, 1), new abx("GPSLatitudeRef", 1, 2), new abx("GPSLatitude", 2, 5), new abx("GPSLongitudeRef", 3, 2), new abx("GPSLongitude", 4, 5), new abx("GPSAltitudeRef", 5, 1), new abx("GPSAltitude", 6, 5), new abx("GPSTimeStamp", 7, 5), new abx("GPSSatellites", 8, 2), new abx("GPSStatus", 9, 2), new abx("GPSMeasureMode", 10, 2), new abx("GPSDOP", 11, 5), new abx("GPSSpeedRef", 12, 2), new abx("GPSSpeed", 13, 5), new abx("GPSTrackRef", 14, 2), new abx("GPSTrack", 15, 5), new abx("GPSImgDirectionRef", 16, 2), new abx("GPSImgDirection", 17, 5), new abx("GPSMapDatum", 18, 2), new abx("GPSDestLatitudeRef", 19, 2), new abx("GPSDestLatitude", 20, 5), new abx("GPSDestLongitudeRef", 21, 2), new abx("GPSDestLongitude", 22, 5), new abx("GPSDestBearingRef", 23, 2), new abx("GPSDestBearing", 24, 5), new abx("GPSDestDistanceRef", 25, 2), new abx("GPSDestDistance", 26, 5), new abx("GPSProcessingMethod", 27, 7), new abx("GPSAreaInformation", 28, 7), new abx("GPSDateStamp", 29, 2), new abx("GPSDifferential", 30, 3), new abx("GPSHPositioningError", 31, 5)};

    /* renamed from: w */
    private static final abx[] f157w = {new abx("InteroperabilityIndex", 1, 2)};

    /* renamed from: x */
    private static final abx[] f158x = {new abx("NewSubfileType", 254, 4), new abx("SubfileType", 255, 4), new abx("ThumbnailImageWidth", 256), new abx("ThumbnailImageLength", 257), new abx("BitsPerSample", 258, 3), new abx("Compression", 259, 3), new abx("PhotometricInterpretation", 262, 3), new abx("ImageDescription", 270, 2), new abx("Make", 271, 2), new abx("Model", 272, 2), new abx("StripOffsets", 273), new abx("ThumbnailOrientation", 274, 3), new abx("SamplesPerPixel", 277, 3), new abx("RowsPerStrip", 278), new abx("StripByteCounts", 279), new abx("XResolution", 282, 5), new abx("YResolution", 283, 5), new abx("PlanarConfiguration", 284, 3), new abx("ResolutionUnit", 296, 3), new abx("TransferFunction", 301, 3), new abx("Software", 305, 2), new abx("DateTime", 306, 2), new abx("Artist", 315, 2), new abx("WhitePoint", 318, 5), new abx("PrimaryChromaticities", 319, 5), new abx("SubIFDPointer", 330, 4), new abx("JPEGInterchangeFormat", 513, 4), new abx("JPEGInterchangeFormatLength", 514, 4), new abx("YCbCrCoefficients", 529, 5), new abx("YCbCrSubSampling", 530, 3), new abx("YCbCrPositioning", 531, 3), new abx("ReferenceBlackWhite", 532, 5), new abx("Copyright", 33432, 2), new abx("ExifIFDPointer", 34665, 4), new abx("GPSInfoIFDPointer", 34853, 4), new abx("DNGVersion", 50706, 1), new abx("DefaultCropSize", 50720)};

    /* renamed from: y */
    private static final abx f159y = new abx("StripOffsets", 273, 3);

    /* renamed from: z */
    private static final abx[] f160z = {new abx("ThumbnailImage", 256, 7), new abx("CameraSettingsIFDPointer", 8224, 4), new abx("ImageProcessingIFDPointer", 8256, 4)};

    /* renamed from: L */
    private FileDescriptor f161L;

    /* renamed from: M */
    private AssetManager.AssetInputStream f162M;

    /* renamed from: N */
    private int f163N;

    /* renamed from: O */
    private final HashMap[] f164O = new HashMap[10];

    /* renamed from: P */
    private Set f165P = new HashSet(10);

    /* renamed from: Q */
    private ByteOrder f166Q = ByteOrder.BIG_ENDIAN;

    /* renamed from: R */
    private int f167R;

    /* renamed from: S */
    private int f168S;

    /* renamed from: T */
    private int f169T;

    /* renamed from: U */
    private int f170U;

    /* renamed from: V */
    private int f171V;

    static {
        Arrays.asList(new Integer[]{1, 6, 3, 8});
        Arrays.asList(new Integer[]{2, 7, 4, 5});
        "VP8X".getBytes(Charset.defaultCharset());
        "VP8L".getBytes(Charset.defaultCharset());
        "VP8 ".getBytes(Charset.defaultCharset());
        "ANIM".getBytes(Charset.defaultCharset());
        "ANMF".getBytes(Charset.defaultCharset());
        "XMP ".getBytes(Charset.defaultCharset());
        abx[] abxArr = {new abx("ColorSpace", 55, 3)};
        f126C = abxArr;
        abx[] abxArr2 = f154t;
        f127D = new abx[][]{abxArr2, f155u, f156v, f157w, f158x, abxArr2, f160z, f124A, f125B, abxArr};
        new abx("JPEGInterchangeFormat", 513, 4);
        new abx("JPEGInterchangeFormatLength", 514, 4);
        Charset forName = Charset.forName("US-ASCII");
        f138d = forName;
        f133J = "Exif\u0000\u0000".getBytes(forName);
        new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        for (int i = 0; i < 10; i++) {
            f129F[i] = new HashMap();
            f130G[i] = new HashMap();
            for (abx abx : f127D[i]) {
                f129F[i].put(Integer.valueOf(abx.f118a), abx);
                f130G[i].put(abx.f119b, abx);
            }
        }
        f132I.put(Integer.valueOf(f128E[0].f118a), 5);
        f132I.put(Integer.valueOf(f128E[1].f118a), 1);
        f132I.put(Integer.valueOf(f128E[2].f118a), 2);
        f132I.put(Integer.valueOf(f128E[3].f118a), 3);
        f132I.put(Integer.valueOf(f128E[4].f118a), 7);
        f132I.put(Integer.valueOf(f128E[5].f118a), 8);
        Pattern.compile(".*[1-9].*");
        Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0167, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x016a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x016c, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x016d, code lost:
        if (r7 != null) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x016f, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r7 = new p000.abv(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r8 = m196d(r7);
        r1.f166Q = r8;
        r7.f111a = r8;
        r8 = r7.readShort();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0188, code lost:
        if (r8 == 85) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x018b, code lost:
        r2 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x018e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x018f, code lost:
        r2 = r0;
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0194, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0195, code lost:
        r2 = r0;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0197, code lost:
        if (r9 != null) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0199, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x019c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x019e, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x019f, code lost:
        if (r7 != null) goto L_0x01a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01a1, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01a6, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01aa, code lost:
        if (r7 < f147m.length) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x01b2, code lost:
        if (r2[r7] == f147m[r7]) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01b4, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01b7, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01bb, code lost:
        if (r7 < f151q.length) goto L_0x01bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x01c3, code lost:
        if (r2[r7] == f151q[r7]) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x01c5, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01c8, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01ca, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01cf, code lost:
        if (r7 < f152r.length) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x01dc, code lost:
        if (r2[(f151q.length + r7) + 4] == f152r[r7]) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01de, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x01e1, code lost:
        r2 = 14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x01e4, code lost:
        r2 = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x01e7, code lost:
        r2 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0088, code lost:
        r11 = "FUJIFILMCCD-RAW".getBytes(java.nio.charset.Charset.defaultCharset());
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
        if (r12 >= r11.length) goto L_0x01e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009a, code lost:
        if (r2[r12] != r11[r12]) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x051f, code lost:
        if (java.util.Arrays.equals(r4, f139e) != false) goto L_0x0521;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009c, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r7 = new p000.abv(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r11 = (long) r7.readInt();
        r8 = new byte[4];
        r7.read(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        if (java.util.Arrays.equals(r8, f142h) == false) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        r17 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
        if (r11 != 1) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c0, code lost:
        r11 = r7.readLong();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c6, code lost:
        if (r11 < 16) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c9, code lost:
        r17 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cf, code lost:
        if (r11 <= 5000) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d1, code lost:
        r11 = 5000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d3, code lost:
        r11 = r11 - r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d7, code lost:
        if (r11 >= 8) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r8 = new byte[4];
        r15 = false;
        r17 = 0;
        r21 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ec, code lost:
        if (r17 < (r11 / 4)) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f3, code lost:
        if (r7.read(r8) != 4) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f7, code lost:
        if (r17 == 1) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ff, code lost:
        if (java.util.Arrays.equals(r8, f143i) != false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0107, code lost:
        if (java.util.Arrays.equals(r8, f144j) == false) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0109, code lost:
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010b, code lost:
        r21 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010d, code lost:
        if (r21 != false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0110, code lost:
        if (r15 != false) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r7.close();
        r2 = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011a, code lost:
        r17 = r17 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0120, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0121, code lost:
        r2 = r0;
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0127, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0128, code lost:
        r2 = r0;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x012a, code lost:
        if (r9 != null) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012c, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x012f, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0131, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0132, code lost:
        if (r7 != null) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r7 = new p000.abv(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r8 = m196d(r7);
        r1.f166Q = r8;
        r7.f111a = r8;
        r8 = r7.readShort();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0148, code lost:
        if (r8 == 20306) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x014c, code lost:
        if (r8 == 21330) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x014e, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0152, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0156, code lost:
        if (r8 != false) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0159, code lost:
        r2 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x015c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x015d, code lost:
        r2 = r0;
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0162, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0163, code lost:
        r2 = r0;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0165, code lost:
        if (r9 != null) goto L_0x0167;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0167 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x016f A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x018a A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x018b A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0199 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01a1 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01ac A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x01fd A[SYNTHETIC, Splitter:B:153:0x01fd] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0202 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0207 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0316 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0352 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03bf A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x04ae A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x04b3 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x04c5 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x0599 A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x01e4 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x012c A[Catch:{ all -> 0x0310, IOException -> 0x05ad, all -> 0x05a7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public abz(java.io.InputStream r26) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            java.lang.String r3 = "PhotographicSensitivity"
            java.lang.String r4 = "yes"
            r25.<init>()
            r5 = 10
            java.util.HashMap[] r6 = new java.util.HashMap[r5]
            r1.f164O = r6
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>(r5)
            r1.f165P = r6
            java.nio.ByteOrder r6 = java.nio.ByteOrder.BIG_ENDIAN
            r1.f166Q = r6
            if (r2 == 0) goto L_0x05af
            boolean r6 = r2 instanceof android.content.res.AssetManager.AssetInputStream
            r7 = 0
            r9 = 0
            r10 = 0
            if (r6 == 0) goto L_0x002f
            r6 = r2
            android.content.res.AssetManager$AssetInputStream r6 = (android.content.res.AssetManager.AssetInputStream) r6
            r1.f162M = r6
            r1.f161L = r9
            r6 = 0
            goto L_0x0054
        L_0x002f:
            boolean r6 = r2 instanceof java.io.FileInputStream
            if (r6 == 0) goto L_0x004d
            r6 = r2
            java.io.FileInputStream r6 = (java.io.FileInputStream) r6
            java.io.FileDescriptor r11 = r6.getFD()
            int r12 = android.os.Build.VERSION.SDK_INT
            int r12 = android.system.OsConstants.SEEK_CUR     // Catch:{ Exception -> 0x004c }
            android.system.Os.lseek(r11, r7, r12)     // Catch:{ Exception -> 0x004c }
            r1.f162M = r9
            java.io.FileDescriptor r6 = r6.getFD()
            r1.f161L = r6
            r6 = 0
            goto L_0x0054
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            r1.f162M = r9
            r1.f161L = r9
            r6 = 0
        L_0x0054:
            if (r6 >= r5) goto L_0x0062
            java.util.HashMap[] r11 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r12.<init>()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r11[r6] = r12     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r6 = r6 + 1
            goto L_0x0054
        L_0x0062:
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r11 = 5000(0x1388, float:7.006E-42)
            r6.<init>(r2, r11)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6.mark(r11)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r2 = new byte[r11]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6.read(r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6.reset()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r11 = 0
        L_0x0075:
            byte[] r12 = f141g     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r12 = r12.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r13 = 8
            r5 = 4
            if (r11 < r12) goto L_0x0080
            r2 = 4
            goto L_0x01e9
        L_0x0080:
            byte r12 = r2[r11]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r16 = f141g     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte r15 = r16[r11]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r12 == r15) goto L_0x05a0
            java.lang.String r11 = "FUJIFILMCCD-RAW"
            java.nio.charset.Charset r12 = java.nio.charset.Charset.defaultCharset()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r11 = r11.getBytes(r12)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r12 = 0
        L_0x0093:
            int r15 = r11.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r12 >= r15) goto L_0x01e7
            byte r15 = r2[r12]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte r7 = r11[r12]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r15 != r7) goto L_0x00a1
            int r12 = r12 + 1
            r7 = 0
            goto L_0x0093
        L_0x00a1:
            abv r7 = new abv     // Catch:{ Exception -> 0x0130, all -> 0x0127 }
            r7.<init>((byte[]) r2)     // Catch:{ Exception -> 0x0130, all -> 0x0127 }
            int r8 = r7.readInt()     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            long r11 = (long) r8     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            byte[] r8 = new byte[r5]     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            r7.read(r8)     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            byte[] r15 = f142h     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            boolean r8 = java.util.Arrays.equals(r8, r15)     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            if (r8 == 0) goto L_0x00d9
            r17 = 16
            r19 = 1
            int r8 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r8 != 0) goto L_0x00c9
            long r11 = r7.readLong()     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            int r8 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r8 < 0) goto L_0x00d9
            goto L_0x00cb
        L_0x00c9:
            r17 = r13
        L_0x00cb:
            r21 = 5000(0x1388, double:2.4703E-320)
            int r8 = (r11 > r21 ? 1 : (r11 == r21 ? 0 : -1))
            if (r8 <= 0) goto L_0x00d3
            r11 = r21
        L_0x00d3:
            long r11 = r11 - r17
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 >= 0) goto L_0x00dd
        L_0x00d9:
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x0135
        L_0x00dd:
            byte[] r8 = new byte[r5]     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            r15 = 0
            r17 = 0
            r21 = 0
        L_0x00e6:
            r22 = 4
            long r22 = r11 / r22
            int r24 = (r17 > r22 ? 1 : (r17 == r22 ? 0 : -1))
            if (r24 < 0) goto L_0x00ef
            goto L_0x0134
        L_0x00ef:
            int r13 = r7.read(r8)     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            if (r13 != r5) goto L_0x00d9
            int r13 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r13 == 0) goto L_0x011a
            byte[] r13 = f143i     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            boolean r13 = java.util.Arrays.equals(r8, r13)     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            if (r13 != 0) goto L_0x010b
            byte[] r13 = f144j     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            boolean r13 = java.util.Arrays.equals(r8, r13)     // Catch:{ Exception -> 0x0125, all -> 0x0120 }
            if (r13 == 0) goto L_0x010d
            r15 = 1
            goto L_0x010d
        L_0x010b:
            r21 = 1
        L_0x010d:
            if (r21 != 0) goto L_0x0110
            goto L_0x011a
        L_0x0110:
            if (r15 != 0) goto L_0x0113
            goto L_0x011a
        L_0x0113:
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2 = 12
            goto L_0x01e9
        L_0x011a:
            long r17 = r17 + r19
            r13 = 8
            goto L_0x00e6
        L_0x0120:
            r0 = move-exception
            r2 = r0
            r9 = r7
            goto L_0x012a
        L_0x0125:
            r0 = move-exception
            goto L_0x0132
        L_0x0127:
            r0 = move-exception
            r2 = r0
            r9 = 0
        L_0x012a:
            if (r9 == 0) goto L_0x012f
            r9.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x012f:
            throw r2     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x0130:
            r0 = move-exception
            r7 = 0
        L_0x0132:
            if (r7 == 0) goto L_0x0135
        L_0x0134:
            goto L_0x00d9
        L_0x0135:
            abv r7 = new abv     // Catch:{ Exception -> 0x016b, all -> 0x0162 }
            r7.<init>((byte[]) r2)     // Catch:{ Exception -> 0x016b, all -> 0x0162 }
            java.nio.ByteOrder r8 = m196d(r7)     // Catch:{ Exception -> 0x0160, all -> 0x015c }
            r1.f166Q = r8     // Catch:{ Exception -> 0x0160, all -> 0x015c }
            r7.f111a = r8     // Catch:{ Exception -> 0x0160, all -> 0x015c }
            short r8 = r7.readShort()     // Catch:{ Exception -> 0x0160, all -> 0x015c }
            r11 = 20306(0x4f52, float:2.8455E-41)
            if (r8 == r11) goto L_0x0152
            r11 = 21330(0x5352, float:2.989E-41)
            if (r8 == r11) goto L_0x0150
            r8 = 0
            goto L_0x0153
        L_0x0150:
        L_0x0152:
            r8 = 1
        L_0x0153:
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r8 != 0) goto L_0x0159
            goto L_0x0172
        L_0x0159:
            r2 = 7
            goto L_0x01e9
        L_0x015c:
            r0 = move-exception
            r2 = r0
            r9 = r7
            goto L_0x0165
        L_0x0160:
            r0 = move-exception
            goto L_0x016d
        L_0x0162:
            r0 = move-exception
            r2 = r0
            r9 = 0
        L_0x0165:
            if (r9 == 0) goto L_0x016a
            r9.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x016a:
            throw r2     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x016b:
            r0 = move-exception
            r7 = 0
        L_0x016d:
            if (r7 == 0) goto L_0x0172
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x0172:
            abv r7 = new abv     // Catch:{ Exception -> 0x019d, all -> 0x0194 }
            r7.<init>((byte[]) r2)     // Catch:{ Exception -> 0x019d, all -> 0x0194 }
            java.nio.ByteOrder r8 = m196d(r7)     // Catch:{ Exception -> 0x0192, all -> 0x018e }
            r1.f166Q = r8     // Catch:{ Exception -> 0x0192, all -> 0x018e }
            r7.f111a = r8     // Catch:{ Exception -> 0x0192, all -> 0x018e }
            short r8 = r7.readShort()     // Catch:{ Exception -> 0x0192, all -> 0x018e }
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r7 = 85
            if (r8 == r7) goto L_0x018b
            goto L_0x01a6
        L_0x018b:
            r2 = 10
            goto L_0x01e9
        L_0x018e:
            r0 = move-exception
            r2 = r0
            r9 = r7
            goto L_0x0197
        L_0x0192:
            r0 = move-exception
            goto L_0x019f
        L_0x0194:
            r0 = move-exception
            r2 = r0
            r9 = 0
        L_0x0197:
            if (r9 == 0) goto L_0x019c
            r9.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x019c:
            throw r2     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x019d:
            r0 = move-exception
            r7 = 0
        L_0x019f:
            if (r7 == 0) goto L_0x01a5
            r7.close()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x01a6
        L_0x01a5:
        L_0x01a6:
            r7 = 0
        L_0x01a7:
            byte[] r8 = f147m     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r7 >= r8) goto L_0x01e4
            byte r8 = r2[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r11 = f147m     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte r11 = r11[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r8 != r11) goto L_0x01b7
            int r7 = r7 + 1
            goto L_0x01a7
        L_0x01b7:
            r7 = 0
        L_0x01b8:
            byte[] r8 = f151q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r7 >= r8) goto L_0x01ca
            byte r8 = r2[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r11 = f151q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte r11 = r11[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r8 != r11) goto L_0x01c8
            int r7 = r7 + 1
            goto L_0x01b8
        L_0x01c8:
            r2 = 0
            goto L_0x01e9
        L_0x01ca:
            r7 = 0
        L_0x01cc:
            byte[] r8 = f152r     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r7 >= r8) goto L_0x01e1
            byte[] r8 = f151q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8 + r7
            int r8 = r8 + r5
            byte r8 = r2[r8]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r11 = f152r     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte r11 = r11[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r8 != r11) goto L_0x01c8
            int r7 = r7 + 1
            goto L_0x01cc
        L_0x01e1:
            r2 = 14
            goto L_0x01e9
        L_0x01e4:
            r2 = 13
            goto L_0x01e9
        L_0x01e7:
            r2 = 9
        L_0x01e9:
            r1.f163N = r2     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abv r2 = new abv     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.<init>((java.io.InputStream) r6)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r6 = r1.f163N     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r8 = "ImageLength"
            java.lang.String r11 = "ImageWidth"
            java.lang.String r13 = "ExifInterface"
            r14 = 5
            r15 = 6
            switch(r6) {
                case 0: goto L_0x04b3;
                case 1: goto L_0x04b3;
                case 2: goto L_0x04b3;
                case 3: goto L_0x04b3;
                case 4: goto L_0x04ae;
                case 5: goto L_0x04b3;
                case 6: goto L_0x04b3;
                case 7: goto L_0x03bf;
                case 8: goto L_0x04b3;
                case 9: goto L_0x0352;
                case 10: goto L_0x0316;
                case 11: goto L_0x04b3;
                case 12: goto L_0x0207;
                case 13: goto L_0x0202;
                default: goto L_0x01fd;
            }
        L_0x01fd:
            r1.m194c(r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x0202:
            r1.m192b((p000.abv) r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x0207:
            android.media.MediaMetadataRetriever r3 = new android.media.MediaMetadataRetriever     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3.<init>()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0310 }
            abu r6 = new abu     // Catch:{ all -> 0x0310 }
            r6.<init>(r2)     // Catch:{ all -> 0x0310 }
            r3.setDataSource(r6)     // Catch:{ all -> 0x0310 }
            r6 = 33
            java.lang.String r6 = r3.extractMetadata(r6)     // Catch:{ all -> 0x0310 }
            r14 = 34
            java.lang.String r14 = r3.extractMetadata(r14)     // Catch:{ all -> 0x0310 }
            r12 = 26
            java.lang.String r12 = r3.extractMetadata(r12)     // Catch:{ all -> 0x0310 }
            r7 = 17
            java.lang.String r7 = r3.extractMetadata(r7)     // Catch:{ all -> 0x0310 }
            boolean r12 = r4.equals(r12)     // Catch:{ all -> 0x0310 }
            if (r12 == 0) goto L_0x0247
            r4 = 29
            java.lang.String r4 = r3.extractMetadata(r4)     // Catch:{ all -> 0x0310 }
            r7 = 30
            java.lang.String r7 = r3.extractMetadata(r7)     // Catch:{ all -> 0x0310 }
            r12 = 31
            java.lang.String r12 = r3.extractMetadata(r12)     // Catch:{ all -> 0x0310 }
            goto L_0x0265
        L_0x0247:
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0310 }
            if (r4 == 0) goto L_0x0261
            r4 = 18
            java.lang.String r4 = r3.extractMetadata(r4)     // Catch:{ all -> 0x0310 }
            r7 = 19
            java.lang.String r7 = r3.extractMetadata(r7)     // Catch:{ all -> 0x0310 }
            r12 = 24
            java.lang.String r12 = r3.extractMetadata(r12)     // Catch:{ all -> 0x0310 }
            goto L_0x0265
        L_0x0261:
            r4 = 0
            r7 = 0
            r12 = 0
        L_0x0265:
            if (r4 == 0) goto L_0x0278
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ all -> 0x0310 }
            r5 = r5[r10]     // Catch:{ all -> 0x0310 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ all -> 0x0310 }
            java.nio.ByteOrder r9 = r1.f166Q     // Catch:{ all -> 0x0310 }
            abw r4 = p000.abw.m172a((int) r4, (java.nio.ByteOrder) r9)     // Catch:{ all -> 0x0310 }
            r5.put(r11, r4)     // Catch:{ all -> 0x0310 }
        L_0x0278:
            if (r7 == 0) goto L_0x028b
            java.util.HashMap[] r4 = r1.f164O     // Catch:{ all -> 0x0310 }
            r4 = r4[r10]     // Catch:{ all -> 0x0310 }
            int r5 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x0310 }
            java.nio.ByteOrder r7 = r1.f166Q     // Catch:{ all -> 0x0310 }
            abw r5 = p000.abw.m172a((int) r5, (java.nio.ByteOrder) r7)     // Catch:{ all -> 0x0310 }
            r4.put(r8, r5)     // Catch:{ all -> 0x0310 }
        L_0x028b:
            if (r12 == 0) goto L_0x02b7
            int r4 = java.lang.Integer.parseInt(r12)     // Catch:{ all -> 0x0310 }
            r5 = 90
            if (r4 == r5) goto L_0x02a6
            r5 = 180(0xb4, float:2.52E-43)
            if (r4 == r5) goto L_0x02a2
            r5 = 270(0x10e, float:3.78E-43)
            if (r4 == r5) goto L_0x029f
            r7 = 1
            goto L_0x02a8
        L_0x029f:
            r7 = 8
            goto L_0x02a8
        L_0x02a2:
            r7 = 3
            goto L_0x02a8
        L_0x02a6:
            r7 = 6
        L_0x02a8:
            java.util.HashMap[] r4 = r1.f164O     // Catch:{ all -> 0x0310 }
            r4 = r4[r10]     // Catch:{ all -> 0x0310 }
            java.lang.String r5 = "Orientation"
            java.nio.ByteOrder r8 = r1.f166Q     // Catch:{ all -> 0x0310 }
            abw r7 = p000.abw.m172a((int) r7, (java.nio.ByteOrder) r8)     // Catch:{ all -> 0x0310 }
            r4.put(r5, r7)     // Catch:{ all -> 0x0310 }
        L_0x02b7:
            if (r6 != 0) goto L_0x02ba
            goto L_0x030b
        L_0x02ba:
            if (r14 == 0) goto L_0x030b
            int r4 = java.lang.Integer.parseInt(r6)     // Catch:{ all -> 0x0310 }
            int r5 = java.lang.Integer.parseInt(r14)     // Catch:{ all -> 0x0310 }
            if (r5 <= r15) goto L_0x0303
            long r6 = (long) r4     // Catch:{ all -> 0x0310 }
            r2.mo128a(r6)     // Catch:{ all -> 0x0310 }
            byte[] r6 = new byte[r15]     // Catch:{ all -> 0x0310 }
            int r7 = r2.read(r6)     // Catch:{ all -> 0x0310 }
            if (r7 != r15) goto L_0x02fb
            int r4 = r4 + r15
            int r5 = r5 + -6
            byte[] r7 = f133J     // Catch:{ all -> 0x0310 }
            boolean r6 = java.util.Arrays.equals(r6, r7)     // Catch:{ all -> 0x0310 }
            if (r6 == 0) goto L_0x02f3
            byte[] r6 = new byte[r5]     // Catch:{ all -> 0x0310 }
            int r7 = r2.read(r6)     // Catch:{ all -> 0x0310 }
            if (r7 != r5) goto L_0x02eb
            r1.f167R = r4     // Catch:{ all -> 0x0310 }
            r1.m186a((byte[]) r6, (int) r10)     // Catch:{ all -> 0x0310 }
            goto L_0x030b
        L_0x02eb:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0310 }
            java.lang.String r4 = "Can't read exif"
            r2.<init>(r4)     // Catch:{ all -> 0x0310 }
            throw r2     // Catch:{ all -> 0x0310 }
        L_0x02f3:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0310 }
            java.lang.String r4 = "Invalid identifier"
            r2.<init>(r4)     // Catch:{ all -> 0x0310 }
            throw r2     // Catch:{ all -> 0x0310 }
        L_0x02fb:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0310 }
            java.lang.String r4 = "Can't read identifier"
            r2.<init>(r4)     // Catch:{ all -> 0x0310 }
            throw r2     // Catch:{ all -> 0x0310 }
        L_0x0303:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0310 }
            java.lang.String r4 = "Invalid exif length"
            r2.<init>(r4)     // Catch:{ all -> 0x0310 }
            throw r2     // Catch:{ all -> 0x0310 }
        L_0x030b:
            r3.release()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x0310:
            r0 = move-exception
            r2 = r0
            r3.release()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            throw r2     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x0316:
            r1.m182a((p000.abv) r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r4 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = r4[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "JpgFromRaw"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 != 0) goto L_0x0328
            goto L_0x032d
        L_0x0328:
            int r4 = r1.f171V     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r1.m184a(r2, r4, r14)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x032d:
            java.util.HashMap[] r4 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = r4[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "ISO"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 1
            r5 = r5[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r5 = (p000.abw) r5     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 == 0) goto L_0x04b6
            if (r5 != 0) goto L_0x04b6
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 1
            r5 = r5[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5.put(r3, r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x0352:
            r3 = 84
            r2.skipBytes(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3 = 4
            byte[] r4 = new byte[r3]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r5 = new byte[r3]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.read(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.skipBytes(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.read(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r3 = r3.getInt()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r4 = r4.getInt()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r1.m184a(r2, r3, r14)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            long r3 = (long) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.mo128a(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r3 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.f111a = r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r3 = r2.readInt()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = 0
        L_0x0385:
            if (r4 >= r3) goto L_0x04b6
            int r5 = r2.readUnsignedShort()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r6 = r2.readUnsignedShort()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abx r7 = f159y     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r7 = r7.f118a     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r5 == r7) goto L_0x039b
            r2.skipBytes(r6)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r4 = r4 + 1
            goto L_0x0385
        L_0x039b:
            short r3 = r2.readShort()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            short r4 = r2.readShort()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = p000.abw.m172a((int) r3, (java.nio.ByteOrder) r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = p000.abw.m172a((int) r4, (java.nio.ByteOrder) r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = r5[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5.put(r8, r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3 = r3[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3.put(r11, r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x03bf:
            r1.m182a((p000.abv) r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = 1
            r3 = r3[r4]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r4 = "MakerNote"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = (p000.abw) r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 == 0) goto L_0x04b6
            abv r4 = new abv     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r3 = r3.f116b     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.<init>((byte[]) r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r3 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.f111a = r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r3 = f145k     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r3 = r3.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.readFully(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = 0
            r4.mo128a(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r7 = f146l     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r7 = r7.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.readFully(r7)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r9 = f145k     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            boolean r3 = java.util.Arrays.equals(r3, r9)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 != 0) goto L_0x0407
            byte[] r3 = f146l     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            boolean r3 = java.util.Arrays.equals(r7, r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 == 0) goto L_0x040d
            r5 = 12
            r4.mo128a(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x040d
        L_0x0407:
            r5 = 8
            r4.mo128a(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x040d:
            r1.m193b(r4, r15)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = 7
            r3 = r3[r4]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r4 = "PreviewImageStart"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = (p000.abw) r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r4 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = 7
            r4 = r4[r5]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "PreviewImageLength"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 != 0) goto L_0x042e
            goto L_0x0442
        L_0x042e:
            if (r4 == 0) goto L_0x0442
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = r5[r14]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r6 = "JPEGInterchangeFormat"
            r5.put(r6, r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3 = r3[r14]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "JPEGInterchangeFormatLength"
            r3.put(r5, r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x0442:
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = 8
            r3 = r3[r4]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r4 = "AspectFrame"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = (p000.abw) r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 == 0) goto L_0x04b6
            java.nio.ByteOrder r4 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.Object r3 = r3.mo147a((java.nio.ByteOrder) r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int[] r3 = (int[]) r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r3 != 0) goto L_0x045d
            goto L_0x0495
        L_0x045d:
            int r4 = r3.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = 4
            if (r4 != r5) goto L_0x0495
            r4 = 2
            r4 = r3[r4]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = r3[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 <= r5) goto L_0x04b6
            r6 = 3
            r7 = r3[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 1
            r3 = r3[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r7 <= r3) goto L_0x04b6
            int r4 = r4 - r5
            int r4 = r4 + r6
            int r7 = r7 - r3
            int r7 = r7 + r6
            if (r4 >= r7) goto L_0x047a
            int r4 = r4 + r7
            int r7 = r4 - r7
            int r4 = r4 - r7
        L_0x047a:
            java.nio.ByteOrder r3 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = p000.abw.m172a((int) r4, (java.nio.ByteOrder) r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r4 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = p000.abw.m172a((int) r7, (java.nio.ByteOrder) r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r5 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = r5[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5.put(r11, r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3 = r3[r10]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r3.put(r8, r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x0495:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.<init>()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "Invalid aspect frame values. frame="
            r4.append(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r3 = java.util.Arrays.toString(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4.append(r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            android.util.Log.w(r13, r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x04ae:
            r1.m184a(r2, r10, r10)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x04b6
        L_0x04b3:
            r1.m182a((p000.abv) r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x04b6:
            java.util.HashMap[] r3 = r1.f164O     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r4 = 4
            r3 = r3[r4]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r4 = "Compression"
            java.lang.Object r4 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 == 0) goto L_0x0599
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r4 = r4.mo148b(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r5 = 1
            if (r4 == r5) goto L_0x04da
            if (r4 == r15) goto L_0x04d5
            r5 = 7
            if (r4 == r5) goto L_0x04da
            goto L_0x059c
        L_0x04d5:
            r1.m185a((p000.abv) r2, (java.util.HashMap) r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x059c
        L_0x04da:
            java.lang.String r4 = "BitsPerSample"
            java.lang.Object r4 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 == 0) goto L_0x059c
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.Object r4 = r4.mo147a((java.nio.ByteOrder) r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int[] r4 = (int[]) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int[] r5 = f139e     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            boolean r5 = java.util.Arrays.equals(r5, r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r5 == 0) goto L_0x04f5
            goto L_0x0521
        L_0x04f5:
            int r5 = r1.f163N     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 3
            if (r5 != r6) goto L_0x059c
            java.lang.String r5 = "PhotometricInterpretation"
            java.lang.Object r5 = r3.get(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r5 = (p000.abw) r5     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r5 == 0) goto L_0x059c
            java.nio.ByteOrder r6 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r5 = r5.mo148b(r6)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 1
            if (r5 != r6) goto L_0x0517
            int[] r5 = f140f     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            boolean r4 = java.util.Arrays.equals(r4, r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 != 0) goto L_0x0521
            goto L_0x059c
        L_0x0517:
            if (r5 != r15) goto L_0x059c
            int[] r5 = f139e     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            boolean r4 = java.util.Arrays.equals(r4, r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 == 0) goto L_0x059c
        L_0x0521:
            java.lang.String r4 = "StripOffsets"
            java.lang.Object r4 = r3.get(r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r4 = (p000.abw) r4     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.String r5 = "StripByteCounts"
            java.lang.Object r3 = r3.get(r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            abw r3 = (p000.abw) r3     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 != 0) goto L_0x0535
            goto L_0x059c
        L_0x0535:
            if (r3 == 0) goto L_0x059c
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.Object r4 = r4.mo147a((java.nio.ByteOrder) r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            long[] r4 = m189a((java.lang.Object) r4)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.nio.ByteOrder r5 = r1.f166Q     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            java.lang.Object r3 = r3.mo147a((java.nio.ByteOrder) r5)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            long[] r3 = m189a((java.lang.Object) r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r4 != 0) goto L_0x054e
            goto L_0x0593
        L_0x054e:
            int r5 = r4.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r5 == 0) goto L_0x0593
            if (r3 != 0) goto L_0x0554
            goto L_0x058d
        L_0x0554:
            int r6 = r3.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r6 == 0) goto L_0x058d
            if (r5 == r6) goto L_0x055f
            java.lang.String r2 = "stripOffsets and stripByteCounts should have same length."
            android.util.Log.w(r13, r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x059c
        L_0x055f:
            r5 = 0
            r7 = 0
        L_0x0562:
            if (r5 < r6) goto L_0x0586
            int r5 = (int) r7     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x056a:
            int r9 = r4.length     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            if (r6 >= r9) goto L_0x059c
            r11 = r4[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r9 = (int) r11     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r11 = r3[r6]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r12 = (int) r11     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r9 = r9 - r7
            long r13 = (long) r9     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.mo128a(r13)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r7 = r7 + r9
            byte[] r9 = new byte[r12]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            r2.read(r9)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r7 = r7 + r12
            java.lang.System.arraycopy(r9, r10, r5, r8, r12)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            int r8 = r8 + r12
            int r6 = r6 + 1
            goto L_0x056a
        L_0x0586:
            r11 = r3[r5]     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            long r7 = r7 + r11
            int r5 = r5 + 1
            goto L_0x0562
        L_0x058d:
            java.lang.String r2 = "stripByteCounts should not be null or have zero length."
            android.util.Log.w(r13, r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x059c
        L_0x0593:
            java.lang.String r2 = "stripOffsets should not be null or have zero length."
            android.util.Log.w(r13, r2)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
            goto L_0x059c
        L_0x0599:
            r1.m185a((p000.abv) r2, (java.util.HashMap) r3)     // Catch:{ IOException -> 0x05ad, all -> 0x05a7 }
        L_0x059c:
            r25.m180a()
            return
        L_0x05a0:
            int r11 = r11 + 1
            r5 = 10
            r9 = 0
            goto L_0x0075
        L_0x05a7:
            r0 = move-exception
            r2 = r0
            r25.m180a()
            throw r2
        L_0x05ad:
            r0 = move-exception
            goto L_0x059c
        L_0x05af:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            java.lang.String r3 = "inputStream cannot be null"
            r2.<init>(r3)
            goto L_0x05b8
        L_0x05b7:
            throw r2
        L_0x05b8:
            goto L_0x05b7
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.abz.<init>(java.io.InputStream):void");
    }

    /* renamed from: a */
    private final void m180a() {
        String a = mo153a("DateTimeOriginal");
        if (a != null && mo153a("DateTime") == null) {
            this.f164O[0].put("DateTime", abw.m175a(a));
        }
        if (mo153a("ImageWidth") == null) {
            this.f164O[0].put("ImageWidth", abw.m173a(0, this.f166Q));
        }
        if (mo153a("ImageLength") == null) {
            this.f164O[0].put("ImageLength", abw.m173a(0, this.f166Q));
        }
        if (mo153a("Orientation") == null) {
            this.f164O[0].put("Orientation", abw.m173a(0, this.f166Q));
        }
        if (mo153a("LightSource") == null) {
            this.f164O[1].put("LightSource", abw.m173a(0, this.f166Q));
        }
    }

    /* renamed from: a */
    private static String m179a(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static long[] m189a(Object obj) {
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            long[] jArr = new long[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                jArr[i] = (long) iArr[i];
            }
            return jArr;
        } else if (!(obj instanceof long[])) {
            return null;
        } else {
            return (long[]) obj;
        }
    }

    /* renamed from: a */
    public final String mo153a(String str) {
        double d;
        abw b = m190b(str);
        if (b == null) {
            return null;
        }
        if (!f131H.contains(str)) {
            return b.mo149c(this.f166Q);
        }
        if (str.equals("GPSTimeStamp")) {
            int i = b.f115a;
            if (i == 5 || i == 10) {
                aby[] abyArr = (aby[]) b.mo147a(this.f166Q);
                if (abyArr != null && abyArr.length == 3) {
                    aby aby = abyArr[0];
                    aby aby2 = abyArr[1];
                    aby aby3 = abyArr[2];
                    return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) aby.f122a) / ((float) aby.f123b))), Integer.valueOf((int) (((float) aby2.f122a) / ((float) aby2.f123b))), Integer.valueOf((int) (((float) aby3.f122a) / ((float) aby3.f123b)))});
                }
                Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(abyArr));
                return null;
            }
            Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + b.f115a);
            return null;
        }
        try {
            Object a = b.mo147a(this.f166Q);
            if (a != null) {
                if (a instanceof String) {
                    d = Double.parseDouble((String) a);
                } else if (a instanceof long[]) {
                    long[] jArr = (long[]) a;
                    if (jArr.length == 1) {
                        d = (double) jArr[0];
                    } else {
                        throw new NumberFormatException("There are more than one component");
                    }
                } else if (a instanceof int[]) {
                    int[] iArr = (int[]) a;
                    if (iArr.length == 1) {
                        d = (double) iArr[0];
                    } else {
                        throw new NumberFormatException("There are more than one component");
                    }
                } else if (a instanceof double[]) {
                    double[] dArr = (double[]) a;
                    if (dArr.length == 1) {
                        d = dArr[0];
                    } else {
                        throw new NumberFormatException("There are more than one component");
                    }
                } else if (a instanceof aby[]) {
                    aby[] abyArr2 = (aby[]) a;
                    if (abyArr2.length == 1) {
                        aby aby4 = abyArr2[0];
                        double d2 = (double) aby4.f122a;
                        double d3 = (double) aby4.f123b;
                        Double.isNaN(d2);
                        Double.isNaN(d3);
                        d = d2 / d3;
                    } else {
                        throw new NumberFormatException("There are more than one component");
                    }
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
                return Double.toString(d);
            }
            throw new NumberFormatException("NULL can't be converted to a double value");
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final int mo152a(String str, int i) {
        abw b = m190b(str);
        if (b != null) {
            try {
                return b.mo148b(this.f166Q);
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    /* renamed from: b */
    private final abw m190b(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i = 0; i < 10; i++) {
            abw abw = (abw) this.f164O[i].get(str);
            if (abw != null) {
                return abw;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x011c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0085 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m184a(p000.abv r12, int r13, int r14) {
        /*
            r11 = this;
            java.nio.ByteOrder r0 = java.nio.ByteOrder.BIG_ENDIAN
            r12.f111a = r0
            long r0 = (long) r13
            r12.mo128a(r0)
            byte r0 = r12.readByte()
            java.lang.String r1 = "Invalid marker: "
            r2 = -1
            r3 = 255(0xff, float:3.57E-43)
            if (r0 != r2) goto L_0x0163
            byte r0 = r12.readByte()
            r4 = -40
            if (r0 != r4) goto L_0x014a
            int r13 = r13 + 2
        L_0x001d:
            byte r0 = r12.readByte()
            if (r0 != r2) goto L_0x012d
            byte r0 = r12.readByte()
            r1 = -39
            if (r0 != r1) goto L_0x002d
            goto L_0x0128
        L_0x002d:
            r1 = -38
            if (r0 == r1) goto L_0x0128
            int r1 = r12.readUnsignedShort()
            r4 = -2
            int r1 = r1 + r4
            int r13 = r13 + 4
            java.lang.String r5 = "Invalid length"
            if (r1 < 0) goto L_0x0122
            r6 = -31
            r7 = 1
            r8 = 0
            if (r0 == r6) goto L_0x00bd
            if (r0 == r4) goto L_0x008d
            switch(r0) {
                case -64: goto L_0x0053;
                case -63: goto L_0x0053;
                case -62: goto L_0x0053;
                case -61: goto L_0x0053;
                default: goto L_0x0048;
            }
        L_0x0048:
            switch(r0) {
                case -59: goto L_0x0053;
                case -58: goto L_0x0053;
                case -57: goto L_0x0053;
                default: goto L_0x004b;
            }
        L_0x004b:
            switch(r0) {
                case -55: goto L_0x0053;
                case -54: goto L_0x0053;
                case -53: goto L_0x0053;
                default: goto L_0x004e;
            }
        L_0x004e:
            switch(r0) {
                case -51: goto L_0x0053;
                case -50: goto L_0x0053;
                case -49: goto L_0x0053;
                default: goto L_0x0051;
            }
        L_0x0051:
            goto L_0x0109
        L_0x0053:
            int r0 = r12.skipBytes(r7)
            if (r0 != r7) goto L_0x0085
            java.util.HashMap[] r0 = r11.f164O
            r0 = r0[r14]
            int r4 = r12.readUnsignedShort()
            long r6 = (long) r4
            java.nio.ByteOrder r4 = r11.f166Q
            abw r4 = p000.abw.m173a((long) r6, (java.nio.ByteOrder) r4)
            java.lang.String r6 = "ImageLength"
            r0.put(r6, r4)
            java.util.HashMap[] r0 = r11.f164O
            r0 = r0[r14]
            int r4 = r12.readUnsignedShort()
            long r6 = (long) r4
            java.nio.ByteOrder r4 = r11.f166Q
            abw r4 = p000.abw.m173a((long) r6, (java.nio.ByteOrder) r4)
            java.lang.String r6 = "ImageWidth"
            r0.put(r6, r4)
            int r1 = r1 + -5
            goto L_0x0109
        L_0x0085:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r13 = "Invalid SOFx"
            r12.<init>(r13)
            throw r12
        L_0x008d:
            byte[] r0 = new byte[r1]
            int r4 = r12.read(r0)
            if (r4 != r1) goto L_0x00b5
            java.lang.String r1 = "UserComment"
            java.lang.String r4 = r11.mo153a((java.lang.String) r1)
            if (r4 != 0) goto L_0x00b3
            java.util.HashMap[] r4 = r11.f164O
            r4 = r4[r7]
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r7 = f138d
            r6.<init>(r0, r7)
            abw r0 = p000.abw.m175a((java.lang.String) r6)
            r4.put(r1, r0)
            r1 = 0
            goto L_0x0109
        L_0x00b3:
            goto L_0x0108
        L_0x00b5:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r13 = "Invalid exif"
            r12.<init>(r13)
            throw r12
        L_0x00bd:
            byte[] r0 = new byte[r1]
            r12.readFully(r0)
            int r4 = r13 + r1
            byte[] r6 = f133J
            boolean r6 = m188a((byte[]) r0, (byte[]) r6)
            if (r6 == 0) goto L_0x00de
            byte[] r6 = f133J
            int r7 = r6.length
            int r6 = r6.length
            byte[] r0 = java.util.Arrays.copyOfRange(r0, r6, r1)
            int r13 = r13 + r7
            r11.f167R = r13
            r11.m186a((byte[]) r0, (int) r14)
            r13 = r4
            r1 = 0
            goto L_0x0109
        L_0x00de:
            byte[] r13 = f134K
            boolean r13 = m188a((byte[]) r0, (byte[]) r13)
            if (r13 == 0) goto L_0x0107
            byte[] r13 = f134K
            int r13 = r13.length
            byte[] r13 = java.util.Arrays.copyOfRange(r0, r13, r1)
            java.lang.String r0 = "Xmp"
            java.lang.String r1 = r11.mo153a((java.lang.String) r0)
            if (r1 != 0) goto L_0x0107
            java.util.HashMap[] r1 = r11.f164O
            r1 = r1[r8]
            abw r6 = new abw
            int r9 = r13.length
            r10 = 0
            r6.<init>(r7, r9, r13, r10)
            r1.put(r0, r6)
            r13 = r4
            r1 = 0
            goto L_0x0109
        L_0x0107:
            r13 = r4
        L_0x0108:
            r1 = 0
        L_0x0109:
            if (r1 < 0) goto L_0x011c
            int r0 = r12.skipBytes(r1)
            if (r0 != r1) goto L_0x0114
            int r13 = r13 + r1
            goto L_0x001d
        L_0x0114:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r13 = "Invalid JPEG segment"
            r12.<init>(r13)
            throw r12
        L_0x011c:
            java.io.IOException r12 = new java.io.IOException
            r12.<init>(r5)
            throw r12
        L_0x0122:
            java.io.IOException r12 = new java.io.IOException
            r12.<init>(r5)
            throw r12
        L_0x0128:
            java.nio.ByteOrder r13 = r11.f166Q
            r12.f111a = r13
            return
        L_0x012d:
            java.io.IOException r12 = new java.io.IOException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Invalid marker:"
            r13.append(r14)
            r14 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r14 = java.lang.Integer.toHexString(r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x014a:
            java.io.IOException r12 = new java.io.IOException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r1)
            java.lang.String r14 = java.lang.Integer.toHexString(r3)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x0163:
            java.io.IOException r12 = new java.io.IOException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r1)
            r14 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r14 = java.lang.Integer.toHexString(r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            goto L_0x017f
        L_0x017e:
            throw r12
        L_0x017f:
            goto L_0x017e
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.abz.m184a(abv, int, int):void");
    }

    /* renamed from: b */
    private final void m192b(abv abv) {
        abv.f111a = ByteOrder.BIG_ENDIAN;
        abv.skipBytes(f147m.length);
        int length = f147m.length;
        while (true) {
            try {
                int readInt = abv.readInt();
                byte[] bArr = new byte[4];
                if (abv.read(bArr) == 4) {
                    int i = length + 8;
                    if (i == 16) {
                        if (!Arrays.equals(bArr, f149o)) {
                            throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                        }
                    }
                    if (Arrays.equals(bArr, f150p)) {
                        return;
                    }
                    if (!Arrays.equals(bArr, f148n)) {
                        int i2 = readInt + 4;
                        abv.skipBytes(i2);
                        length = i + i2;
                    } else {
                        byte[] bArr2 = new byte[readInt];
                        if (abv.read(bArr2) == readInt) {
                            int readInt2 = abv.readInt();
                            CRC32 crc32 = new CRC32();
                            crc32.update(bArr);
                            crc32.update(bArr2);
                            if (((int) crc32.getValue()) == readInt2) {
                                this.f167R = i;
                                m186a(bArr2, 0);
                                m191b();
                                return;
                            }
                            throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt2 + ", calculated CRC value: " + crc32.getValue());
                        }
                        throw new IOException("Failed to read given length for given PNG chunk type: " + m179a(bArr));
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    /* renamed from: a */
    private final void m182a(abv abv) {
        abw abw;
        m183a(abv, abv.available());
        m193b(abv, 0);
        m195c(abv, 0);
        m195c(abv, 5);
        m195c(abv, 4);
        m191b();
        if (this.f163N == 8 && (abw = (abw) this.f164O[1].get("MakerNote")) != null) {
            abv abv2 = new abv(abw.f116b);
            abv2.f111a = this.f166Q;
            abv2.mo128a(6);
            m193b(abv2, 9);
            abw abw2 = (abw) this.f164O[9].get("ColorSpace");
            if (abw2 != null) {
                this.f164O[1].put("ColorSpace", abw2);
            }
        }
    }

    /* renamed from: c */
    private final void m194c(abv abv) {
        abv.f111a = ByteOrder.LITTLE_ENDIAN;
        abv.skipBytes(f151q.length);
        int readInt = abv.readInt() + 8;
        int skipBytes = abv.skipBytes(f152r.length) + 8;
        while (true) {
            try {
                byte[] bArr = new byte[4];
                if (abv.read(bArr) == 4) {
                    int readInt2 = abv.readInt();
                    int i = skipBytes + 8;
                    if (!Arrays.equals(f153s, bArr)) {
                        if (readInt2 % 2 == 1) {
                            readInt2++;
                        }
                        int i2 = i + readInt2;
                        if (i2 == readInt) {
                            return;
                        }
                        if (i2 <= readInt) {
                            int skipBytes2 = abv.skipBytes(readInt2);
                            if (skipBytes2 == readInt2) {
                                skipBytes = i + skipBytes2;
                            } else {
                                throw new IOException("Encountered WebP file with invalid chunk size");
                            }
                        } else {
                            throw new IOException("Encountered WebP file with invalid chunk size");
                        }
                    } else {
                        byte[] bArr2 = new byte[readInt2];
                        if (abv.read(bArr2) == readInt2) {
                            this.f167R = i;
                            m186a(bArr2, 0);
                            this.f167R = i;
                            return;
                        }
                        throw new IOException("Failed to read given length for given PNG chunk type: " + m179a(bArr));
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    /* renamed from: a */
    private final void m185a(abv abv, HashMap hashMap) {
        abw abw = (abw) hashMap.get("JPEGInterchangeFormat");
        abw abw2 = (abw) hashMap.get("JPEGInterchangeFormatLength");
        if (abw != null && abw2 != null) {
            int b = abw.mo148b(this.f166Q);
            int b2 = abw2.mo148b(this.f166Q);
            if (this.f163N == 7) {
                b += this.f168S;
            }
            int min = Math.min(b2, abv.f112b - b);
            if (b > 0 && min > 0) {
                int i = b + this.f167R;
                if (this.f162M == null && this.f161L == null) {
                    abv.mo128a((long) i);
                    abv.readFully(new byte[min]);
                }
            }
        }
    }

    /* renamed from: a */
    private final boolean m187a(HashMap hashMap) {
        abw abw = (abw) hashMap.get("ImageLength");
        abw abw2 = (abw) hashMap.get("ImageWidth");
        if (abw == null || abw2 == null) {
            return false;
        }
        int b = abw.mo148b(this.f166Q);
        int b2 = abw2.mo148b(this.f166Q);
        if (b > 512 || b2 > 512) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private final void m183a(abv abv, int i) {
        ByteOrder d = m196d(abv);
        this.f166Q = d;
        abv.f111a = d;
        int readUnsignedShort = abv.readUnsignedShort();
        int i2 = this.f163N;
        if (i2 == 7 || i2 == 10 || readUnsignedShort == 42) {
            int readInt = abv.readInt();
            if (readInt < 8 || readInt >= i) {
                throw new IOException("Invalid first Ifd offset: " + readInt);
            }
            int i3 = readInt - 8;
            if (i3 > 0 && abv.skipBytes(i3) != i3) {
                throw new IOException("Couldn't jump to first Ifd: " + i3);
            }
            return;
        }
        throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
    }

    /* renamed from: d */
    private static final ByteOrder m196d(abv abv) {
        short readShort = abv.readShort();
        if (readShort == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (readShort == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
    }

    /* renamed from: a */
    private final void m186a(byte[] bArr, int i) {
        abv abv = new abv(bArr);
        m183a(abv, bArr.length);
        m193b(abv, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0097  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m193b(p000.abv r26, int r27) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            java.util.Set r3 = r0.f165P
            int r4 = r1.f113c
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.add(r4)
            int r3 = r1.f113c
            int r3 = r3 + 2
            int r4 = r1.f112b
            if (r3 > r4) goto L_0x027b
            short r3 = r26.readShort()
            int r4 = r1.f113c
            int r5 = r3 * 12
            int r4 = r4 + r5
            int r5 = r1.f112b
            if (r4 > r5) goto L_0x027b
            if (r3 <= 0) goto L_0x027b
            r5 = 0
        L_0x0029:
            r8 = 4
            if (r5 >= r3) goto L_0x0235
            int r9 = r26.readUnsignedShort()
            int r10 = r26.readUnsignedShort()
            int r11 = r26.readInt()
            int r12 = r1.f113c
            long r12 = (long) r12
            r14 = 4
            long r12 = r12 + r14
            java.util.HashMap[] r16 = f129F
            r4 = r16[r2]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object r4 = r4.get(r9)
            abx r4 = (p000.abx) r4
            r14 = 9
            r6 = 3
            r7 = 7
            if (r4 != 0) goto L_0x0056
        L_0x0052:
            r6 = 0
        L_0x0054:
            r14 = 0
            goto L_0x0095
        L_0x0056:
            if (r10 <= 0) goto L_0x0052
            int[] r15 = f136b
            int r15 = r15.length
            if (r10 >= r15) goto L_0x0052
            int r15 = r4.f120c
            if (r15 == r7) goto L_0x0078
            if (r10 == r7) goto L_0x0078
            if (r15 == r10) goto L_0x0078
            int r7 = r4.f121d
            if (r7 == r10) goto L_0x0078
            if (r15 == r8) goto L_0x006e
            if (r7 == r8) goto L_0x006e
            goto L_0x0072
        L_0x006e:
            if (r10 != r6) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            if (r15 != r14) goto L_0x0052
            r7 = 8
            if (r10 != r7) goto L_0x0052
        L_0x0078:
            r7 = 7
            if (r10 != r7) goto L_0x007c
            r10 = r15
        L_0x007c:
            long r14 = (long) r11
            int[] r20 = f136b
            r7 = r20[r10]
            long r6 = (long) r7
            long r6 = r6 * r14
            r14 = 0
            int r21 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r21 < 0) goto L_0x0094
            r14 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r21 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r21 <= 0) goto L_0x0092
            goto L_0x0094
        L_0x0092:
            r14 = 1
            goto L_0x0095
        L_0x0094:
            goto L_0x0054
        L_0x0095:
            if (r14 == 0) goto L_0x0227
            java.lang.String r14 = "Compression"
            r17 = 4
            int r15 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r15 <= 0) goto L_0x0147
            int r15 = r26.readInt()
            int r8 = r0.f163N
            r18 = r3
            r3 = 7
            if (r8 != r3) goto L_0x011c
            java.lang.String r3 = r4.f119b
            java.lang.String r8 = "MakerNote"
            boolean r3 = r8.equals(r3)
            if (r3 != 0) goto L_0x0112
            r3 = 6
            if (r2 == r3) goto L_0x00c0
            r19 = r5
            r22 = r10
            r21 = r11
            r11 = r9
            goto L_0x0133
        L_0x00c0:
            java.lang.String r8 = r4.f119b
            java.lang.String r3 = "ThumbnailImage"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x010a
            r0.f169T = r15
            r0.f170U = r11
            java.nio.ByteOrder r3 = r0.f166Q
            r8 = 6
            abw r3 = p000.abw.m172a((int) r8, (java.nio.ByteOrder) r3)
            int r8 = r0.f169T
            r19 = r5
            java.nio.ByteOrder r5 = r0.f166Q
            r22 = r10
            r21 = r11
            long r10 = (long) r8
            abw r5 = p000.abw.m173a((long) r10, (java.nio.ByteOrder) r5)
            int r8 = r0.f170U
            java.nio.ByteOrder r10 = r0.f166Q
            r11 = r9
            long r8 = (long) r8
            abw r8 = p000.abw.m173a((long) r8, (java.nio.ByteOrder) r10)
            java.util.HashMap[] r9 = r0.f164O
            r10 = 4
            r9 = r9[r10]
            r9.put(r14, r3)
            java.util.HashMap[] r3 = r0.f164O
            r3 = r3[r10]
            java.lang.String r9 = "JPEGInterchangeFormat"
            r3.put(r9, r5)
            java.util.HashMap[] r3 = r0.f164O
            r3 = r3[r10]
            java.lang.String r5 = "JPEGInterchangeFormatLength"
            r3.put(r5, r8)
            goto L_0x0133
        L_0x010a:
            r19 = r5
            r22 = r10
            r21 = r11
            r11 = r9
            goto L_0x0133
        L_0x0112:
            r19 = r5
            r22 = r10
            r21 = r11
            r11 = r9
            r0.f168S = r15
            goto L_0x0133
        L_0x011c:
            r19 = r5
            r22 = r10
            r21 = r11
            r11 = r9
            r3 = 10
            if (r8 != r3) goto L_0x0133
            java.lang.String r3 = r4.f119b
            java.lang.String r5 = "JpgFromRaw"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0133
            r0.f171V = r15
        L_0x0133:
            long r8 = (long) r15
            long r23 = r8 + r6
            int r3 = r1.f112b
            r5 = r14
            long r14 = (long) r3
            int r3 = (r23 > r14 ? 1 : (r23 == r14 ? 0 : -1))
            if (r3 <= 0) goto L_0x0143
            r1.mo128a(r12)
            goto L_0x022e
        L_0x0143:
            r1.mo128a(r8)
            goto L_0x0151
        L_0x0147:
            r18 = r3
            r19 = r5
            r22 = r10
            r21 = r11
            r5 = r14
            r11 = r9
        L_0x0151:
            java.util.HashMap r3 = f132I
            java.lang.Object r3 = r3.get(r11)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 != 0) goto L_0x01cd
            int r3 = (int) r6
            byte[] r3 = new byte[r3]
            r1.readFully(r3)
            abw r6 = new abw
            r7 = 0
            r8 = r21
            r10 = r22
            r6.<init>(r10, r8, r3, r7)
            java.util.HashMap[] r3 = r0.f164O
            r3 = r3[r2]
            java.lang.String r7 = r4.f119b
            r3.put(r7, r6)
            java.lang.String r3 = r4.f119b
            java.lang.String r7 = "DNGVersion"
            boolean r3 = r7.equals(r3)
            if (r3 != 0) goto L_0x017f
            goto L_0x0183
        L_0x017f:
            r3 = 3
            r0.f163N = r3
        L_0x0183:
            java.lang.String r3 = r4.f119b
            java.lang.String r7 = "Make"
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L_0x018f
        L_0x018e:
            goto L_0x019b
        L_0x018f:
            java.lang.String r3 = r4.f119b
            java.lang.String r7 = "Model"
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L_0x01a9
            goto L_0x018e
        L_0x019b:
            java.nio.ByteOrder r3 = r0.f166Q
            java.lang.String r3 = r6.mo149c(r3)
            java.lang.String r7 = "PENTAX"
            boolean r3 = r3.contains(r7)
            if (r3 != 0) goto L_0x01bd
        L_0x01a9:
            java.lang.String r3 = r4.f119b
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01c2
            java.nio.ByteOrder r3 = r0.f166Q
            int r3 = r6.mo148b(r3)
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r3 == r4) goto L_0x01bd
            goto L_0x01c2
        L_0x01bd:
            r3 = 8
            r0.f163N = r3
        L_0x01c2:
            int r3 = r1.f113c
            long r3 = (long) r3
            int r5 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r5 == 0) goto L_0x022e
            r1.mo128a(r12)
            goto L_0x022e
        L_0x01cd:
            r10 = r22
            r4 = 3
            if (r10 == r4) goto L_0x01f8
            r4 = 4
            if (r10 == r4) goto L_0x01f2
            r4 = 8
            if (r10 == r4) goto L_0x01eb
            r4 = 9
            if (r10 == r4) goto L_0x01e4
            r4 = 13
            if (r10 == r4) goto L_0x01e4
            r4 = -1
            goto L_0x01ff
        L_0x01e4:
            int r4 = r26.readInt()
            long r4 = (long) r4
            goto L_0x01ff
        L_0x01eb:
            short r4 = r26.readShort()
            long r4 = (long) r4
            goto L_0x01ff
        L_0x01f2:
            long r4 = r26.mo127a()
            goto L_0x01ff
        L_0x01f8:
            int r4 = r26.readUnsignedShort()
            long r4 = (long) r4
        L_0x01ff:
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0223
            int r6 = r1.f112b
            long r6 = (long) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0223
            java.util.Set r6 = r0.f165P
            int r7 = (int) r4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            boolean r6 = r6.contains(r7)
            if (r6 != 0) goto L_0x0223
            r1.mo128a(r4)
            int r3 = r3.intValue()
            r0.m193b(r1, r3)
        L_0x0223:
            r1.mo128a(r12)
            goto L_0x022e
        L_0x0227:
            r18 = r3
            r19 = r5
            r1.mo128a(r12)
        L_0x022e:
            int r5 = r19 + 1
            short r5 = (short) r5
            r3 = r18
            goto L_0x0029
        L_0x0235:
            int r2 = r1.f113c
            r3 = 4
            int r2 = r2 + r3
            int r3 = r1.f112b
            if (r2 > r3) goto L_0x027b
            int r2 = r26.readInt()
            long r3 = (long) r2
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x027b
            int r5 = r1.f112b
            if (r2 >= r5) goto L_0x027b
            java.util.Set r5 = r0.f165P
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L_0x027b
            r1.mo128a(r3)
            java.util.HashMap[] r2 = r0.f164O
            r3 = 4
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0275
            java.util.HashMap[] r2 = r0.f164O
            r3 = 5
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x027b
            r0.m193b(r1, r3)
            return
        L_0x0275:
            r2 = 4
            r0.m193b(r1, r2)
            return
        L_0x027b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.abz.m193b(abv, int):void");
    }

    /* renamed from: a */
    private static boolean m188a(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private final void m181a(int i, int i2) {
        if (!this.f164O[i].isEmpty() && !this.f164O[i2].isEmpty()) {
            abw abw = (abw) this.f164O[i].get("ImageLength");
            abw abw2 = (abw) this.f164O[i].get("ImageWidth");
            abw abw3 = (abw) this.f164O[i2].get("ImageLength");
            abw abw4 = (abw) this.f164O[i2].get("ImageWidth");
            if (abw != null && abw2 != null && abw3 != null && abw4 != null) {
                int b = abw.mo148b(this.f166Q);
                int b2 = abw2.mo148b(this.f166Q);
                int b3 = abw3.mo148b(this.f166Q);
                int b4 = abw4.mo148b(this.f166Q);
                if (b < b3 && b2 < b4) {
                    HashMap[] hashMapArr = this.f164O;
                    HashMap hashMap = hashMapArr[i];
                    hashMapArr[i] = hashMapArr[i2];
                    hashMapArr[i2] = hashMap;
                }
            }
        }
    }

    /* renamed from: c */
    private final void m195c(abv abv, int i) {
        abw abw;
        abw abw2;
        abw abw3;
        abw abw4 = (abw) this.f164O[i].get("DefaultCropSize");
        abw abw5 = (abw) this.f164O[i].get("SensorTopBorder");
        abw abw6 = (abw) this.f164O[i].get("SensorLeftBorder");
        abw abw7 = (abw) this.f164O[i].get("SensorBottomBorder");
        abw abw8 = (abw) this.f164O[i].get("SensorRightBorder");
        if (abw4 != null) {
            if (abw4.f115a != 5) {
                int[] iArr = (int[]) abw4.mo147a(this.f166Q);
                if (iArr != null && iArr.length == 2) {
                    abw3 = abw.m172a(iArr[0], this.f166Q);
                    abw2 = abw.m172a(iArr[1], this.f166Q);
                } else {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
            } else {
                aby[] abyArr = (aby[]) abw4.mo147a(this.f166Q);
                if (abyArr != null && abyArr.length == 2) {
                    abw3 = abw.m174a(abyArr[0], this.f166Q);
                    abw2 = abw.m174a(abyArr[1], this.f166Q);
                } else {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(abyArr));
                    return;
                }
            }
            this.f164O[i].put("ImageWidth", abw3);
            this.f164O[i].put("ImageLength", abw2);
        } else if (abw5 == null || abw6 == null || abw7 == null || abw8 == null) {
            abw abw9 = (abw) this.f164O[i].get("ImageLength");
            abw abw10 = (abw) this.f164O[i].get("ImageWidth");
            if ((abw9 == null || abw10 == null) && (abw = (abw) this.f164O[i].get("JPEGInterchangeFormat")) != null) {
                m184a(abv, abw.mo148b(this.f166Q), i);
            }
        } else {
            int b = abw5.mo148b(this.f166Q);
            int b2 = abw7.mo148b(this.f166Q);
            int b3 = abw8.mo148b(this.f166Q);
            int b4 = abw6.mo148b(this.f166Q);
            if (b2 > b && b3 > b4) {
                abw a = abw.m172a(b2 - b, this.f166Q);
                abw a2 = abw.m172a(b3 - b4, this.f166Q);
                this.f164O[i].put("ImageLength", a);
                this.f164O[i].put("ImageWidth", a2);
            }
        }
    }

    /* renamed from: b */
    private final void m191b() {
        m181a(0, 5);
        m181a(0, 4);
        m181a(5, 4);
        abw abw = (abw) this.f164O[1].get("PixelXDimension");
        abw abw2 = (abw) this.f164O[1].get("PixelYDimension");
        if (!(abw == null || abw2 == null)) {
            this.f164O[0].put("ImageWidth", abw);
            this.f164O[0].put("ImageLength", abw2);
        }
        if (this.f164O[4].isEmpty() && m187a(this.f164O[5])) {
            HashMap[] hashMapArr = this.f164O;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap();
        }
        m187a(this.f164O[4]);
    }
}
