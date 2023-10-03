package com.google.android.systemui.smartspace.nano;

import android.support.constraint.R$styleable;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface SmartspaceProto {

    public static final class SmartspaceUpdate extends MessageNano {
        private static volatile SmartspaceUpdate[] _emptyArray;
        public SmartspaceCard[] card;

        public static final class SmartspaceCard extends MessageNano {
            public static final int CARD0 = 0;
            public static final int CARD1 = 1;
            public static final int CARD2 = 2;
            public static final int CARD3 = 3;
            public static final int CARD4 = 4;
            public static final int CARD5 = 5;
            public static final int CARD6 = 6;
            private static volatile SmartspaceCard[] _emptyArray;
            public int cardId;
            public int cardPriority;
            public int cardType;
            public Message duringEvent;
            public long eventDurationMillis;
            public long eventTimeMillis;
            public ExpiryCriteria expiryCriteria;
            public Image icon;
            public Message postEvent;
            public Message preEvent;
            public boolean shouldDiscard;
            public TapAction tapAction;
            public long updateTimeMillis;

            public static final class ExpiryCriteria extends MessageNano {
                private static volatile ExpiryCriteria[] _emptyArray;
                public long expirationTimeMillis;
                public int maxImpressions;

                public static ExpiryCriteria[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new ExpiryCriteria[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public ExpiryCriteria() {
                    clear();
                }

                public ExpiryCriteria clear() {
                    this.expirationTimeMillis = 0;
                    this.maxImpressions = 0;
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                    codedOutputByteBufferNano.writeInt64(1, this.expirationTimeMillis);
                    codedOutputByteBufferNano.writeInt32(2, this.maxImpressions);
                    super.writeTo(codedOutputByteBufferNano);
                }

                /* access modifiers changed from: protected */
                public int computeSerializedSize() {
                    return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt64Size(1, this.expirationTimeMillis) + CodedOutputByteBufferNano.computeInt32Size(2, this.maxImpressions);
                }

                public ExpiryCriteria mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    while (true) {
                        int readTag = codedInputByteBufferNano.readTag();
                        if (readTag == 0) {
                            return this;
                        }
                        if (readTag == 8) {
                            this.expirationTimeMillis = codedInputByteBufferNano.readInt64();
                        } else if (readTag == 16) {
                            this.maxImpressions = codedInputByteBufferNano.readInt32();
                        } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                            return this;
                        }
                    }
                }

                public static ExpiryCriteria parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                    ExpiryCriteria expiryCriteria = new ExpiryCriteria();
                    MessageNano.mergeFrom(expiryCriteria, bArr);
                    return expiryCriteria;
                }

                public static ExpiryCriteria parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    return new ExpiryCriteria().mergeFrom(codedInputByteBufferNano);
                }
            }

            public static final class Image extends MessageNano {
                private static volatile Image[] _emptyArray;
                public String gsaResourceName;
                public String key;
                public String uri;

                public static Image[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Image[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Image() {
                    clear();
                }

                public Image clear() {
                    this.key = "";
                    this.gsaResourceName = "";
                    this.uri = "";
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                    codedOutputByteBufferNano.writeString(1, this.key);
                    codedOutputByteBufferNano.writeString(2, this.gsaResourceName);
                    codedOutputByteBufferNano.writeString(3, this.uri);
                    super.writeTo(codedOutputByteBufferNano);
                }

                /* access modifiers changed from: protected */
                public int computeSerializedSize() {
                    return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.key) + CodedOutputByteBufferNano.computeStringSize(2, this.gsaResourceName) + CodedOutputByteBufferNano.computeStringSize(3, this.uri);
                }

                public Image mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    while (true) {
                        int readTag = codedInputByteBufferNano.readTag();
                        if (readTag == 0) {
                            return this;
                        }
                        if (readTag == 10) {
                            this.key = codedInputByteBufferNano.readString();
                        } else if (readTag == 18) {
                            this.gsaResourceName = codedInputByteBufferNano.readString();
                        } else if (readTag == 26) {
                            this.uri = codedInputByteBufferNano.readString();
                        } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                            return this;
                        }
                    }
                }

                public static Image parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                    Image image = new Image();
                    MessageNano.mergeFrom(image, bArr);
                    return image;
                }

                public static Image parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    return new Image().mergeFrom(codedInputByteBufferNano);
                }
            }

            public static final class Message extends MessageNano {
                private static volatile Message[] _emptyArray;
                public FormattedText subtitle;
                public FormattedText title;

                public static final class FormattedText extends MessageNano {
                    private static volatile FormattedText[] _emptyArray;
                    public FormatParam[] formatParam;
                    public String text;
                    public int truncateLocation;

                    public static final class FormatParam extends MessageNano {
                        public static final int SOMETHING0 = 0;
                        public static final int SOMETHING1 = 1;
                        public static final int SOMETHING2 = 2;
                        public static final int SOMETHING3 = 3;
                        private static volatile FormatParam[] _emptyArray;
                        public int formatParamArgs;
                        public String text;
                        public int truncateLocation;
                        public boolean updateTimeLocally;

                        public static FormatParam[] emptyArray() {
                            if (_emptyArray == null) {
                                synchronized (InternalNano.LAZY_INIT_LOCK) {
                                    if (_emptyArray == null) {
                                        _emptyArray = new FormatParam[0];
                                    }
                                }
                            }
                            return _emptyArray;
                        }

                        public FormatParam() {
                            clear();
                        }

                        public FormatParam clear() {
                            this.text = "";
                            this.truncateLocation = 0;
                            this.formatParamArgs = 0;
                            this.updateTimeLocally = false;
                            this.cachedSize = -1;
                            return this;
                        }

                        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                            codedOutputByteBufferNano.writeString(1, this.text);
                            codedOutputByteBufferNano.writeInt32(2, this.truncateLocation);
                            codedOutputByteBufferNano.writeInt32(3, this.formatParamArgs);
                            codedOutputByteBufferNano.writeBool(4, this.updateTimeLocally);
                            super.writeTo(codedOutputByteBufferNano);
                        }

                        /* access modifiers changed from: protected */
                        public int computeSerializedSize() {
                            return super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.text) + CodedOutputByteBufferNano.computeInt32Size(2, this.truncateLocation) + CodedOutputByteBufferNano.computeInt32Size(3, this.formatParamArgs) + CodedOutputByteBufferNano.computeBoolSize(4, this.updateTimeLocally);
                        }

                        public FormatParam mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                            while (true) {
                                int readTag = codedInputByteBufferNano.readTag();
                                if (readTag == 0) {
                                    return this;
                                }
                                if (readTag == 10) {
                                    this.text = codedInputByteBufferNano.readString();
                                } else if (readTag == 16) {
                                    this.truncateLocation = codedInputByteBufferNano.readInt32();
                                } else if (readTag == 24) {
                                    int readInt32 = codedInputByteBufferNano.readInt32();
                                    if (readInt32 == 0 || readInt32 == 1 || readInt32 == 2 || readInt32 == 3) {
                                        this.formatParamArgs = readInt32;
                                    }
                                } else if (readTag == 32) {
                                    this.updateTimeLocally = codedInputByteBufferNano.readBool();
                                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                                    return this;
                                }
                            }
                        }

                        public static FormatParam parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                            FormatParam formatParam = new FormatParam();
                            MessageNano.mergeFrom(formatParam, bArr);
                            return formatParam;
                        }

                        public static FormatParam parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                            return new FormatParam().mergeFrom(codedInputByteBufferNano);
                        }
                    }

                    public static FormattedText[] emptyArray() {
                        if (_emptyArray == null) {
                            synchronized (InternalNano.LAZY_INIT_LOCK) {
                                if (_emptyArray == null) {
                                    _emptyArray = new FormattedText[0];
                                }
                            }
                        }
                        return _emptyArray;
                    }

                    public FormattedText() {
                        clear();
                    }

                    public FormattedText clear() {
                        this.text = "";
                        this.truncateLocation = 0;
                        this.formatParam = FormatParam.emptyArray();
                        this.cachedSize = -1;
                        return this;
                    }

                    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                        codedOutputByteBufferNano.writeString(1, this.text);
                        codedOutputByteBufferNano.writeInt32(2, this.truncateLocation);
                        FormatParam[] formatParamArr = this.formatParam;
                        if (formatParamArr != null && formatParamArr.length > 0) {
                            int i = 0;
                            while (true) {
                                FormatParam[] formatParamArr2 = this.formatParam;
                                if (i >= formatParamArr2.length) {
                                    break;
                                }
                                FormatParam formatParam2 = formatParamArr2[i];
                                if (formatParam2 != null) {
                                    codedOutputByteBufferNano.writeMessage(3, formatParam2);
                                }
                                i++;
                            }
                        }
                        super.writeTo(codedOutputByteBufferNano);
                    }

                    /* access modifiers changed from: protected */
                    public int computeSerializedSize() {
                        int computeSerializedSize = super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.text) + CodedOutputByteBufferNano.computeInt32Size(2, this.truncateLocation);
                        FormatParam[] formatParamArr = this.formatParam;
                        if (formatParamArr != null && formatParamArr.length > 0) {
                            int i = 0;
                            while (true) {
                                FormatParam[] formatParamArr2 = this.formatParam;
                                if (i >= formatParamArr2.length) {
                                    break;
                                }
                                FormatParam formatParam2 = formatParamArr2[i];
                                if (formatParam2 != null) {
                                    computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, formatParam2);
                                }
                                i++;
                            }
                        }
                        return computeSerializedSize;
                    }

                    public FormattedText mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                        while (true) {
                            int readTag = codedInputByteBufferNano.readTag();
                            if (readTag == 0) {
                                return this;
                            }
                            if (readTag == 10) {
                                this.text = codedInputByteBufferNano.readString();
                            } else if (readTag == 16) {
                                this.truncateLocation = codedInputByteBufferNano.readInt32();
                            } else if (readTag == 26) {
                                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                                FormatParam[] formatParamArr = this.formatParam;
                                int length = formatParamArr == null ? 0 : formatParamArr.length;
                                FormatParam[] formatParamArr2 = new FormatParam[(repeatedFieldArrayLength + length)];
                                if (length != 0) {
                                    System.arraycopy(this.formatParam, 0, formatParamArr2, 0, length);
                                }
                                while (length < formatParamArr2.length - 1) {
                                    formatParamArr2[length] = new FormatParam();
                                    codedInputByteBufferNano.readMessage(formatParamArr2[length]);
                                    codedInputByteBufferNano.readTag();
                                    length++;
                                }
                                formatParamArr2[length] = new FormatParam();
                                codedInputByteBufferNano.readMessage(formatParamArr2[length]);
                                this.formatParam = formatParamArr2;
                            } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                                return this;
                            }
                        }
                    }

                    public static FormattedText parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                        FormattedText formattedText = new FormattedText();
                        MessageNano.mergeFrom(formattedText, bArr);
                        return formattedText;
                    }

                    public static FormattedText parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                        return new FormattedText().mergeFrom(codedInputByteBufferNano);
                    }
                }

                public static Message[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Message[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Message() {
                    clear();
                }

                public Message clear() {
                    this.title = null;
                    this.subtitle = null;
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                    FormattedText formattedText = this.title;
                    if (formattedText != null) {
                        codedOutputByteBufferNano.writeMessage(1, formattedText);
                    }
                    FormattedText formattedText2 = this.subtitle;
                    if (formattedText2 != null) {
                        codedOutputByteBufferNano.writeMessage(2, formattedText2);
                    }
                    super.writeTo(codedOutputByteBufferNano);
                }

                /* access modifiers changed from: protected */
                public int computeSerializedSize() {
                    int computeSerializedSize = super.computeSerializedSize();
                    FormattedText formattedText = this.title;
                    if (formattedText != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, formattedText);
                    }
                    FormattedText formattedText2 = this.subtitle;
                    return formattedText2 != null ? computeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(2, formattedText2) : computeSerializedSize;
                }

                public Message mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    while (true) {
                        int readTag = codedInputByteBufferNano.readTag();
                        if (readTag == 0) {
                            return this;
                        }
                        if (readTag == 10) {
                            if (this.title == null) {
                                this.title = new FormattedText();
                            }
                            codedInputByteBufferNano.readMessage(this.title);
                        } else if (readTag == 18) {
                            if (this.subtitle == null) {
                                this.subtitle = new FormattedText();
                            }
                            codedInputByteBufferNano.readMessage(this.subtitle);
                        } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                            return this;
                        }
                    }
                }

                public static Message parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                    Message message = new Message();
                    MessageNano.mergeFrom(message, bArr);
                    return message;
                }

                public static Message parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    return new Message().mergeFrom(codedInputByteBufferNano);
                }
            }

            public static final class TapAction extends MessageNano {
                public static final int ACTION0 = 0;
                public static final int ACTION1 = 1;
                public static final int ACTION2 = 2;
                private static volatile TapAction[] _emptyArray;
                public int actionType;
                public String intent;

                public static TapAction[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new TapAction[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public TapAction() {
                    clear();
                }

                public TapAction clear() {
                    this.actionType = 0;
                    this.intent = "";
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                    codedOutputByteBufferNano.writeInt32(1, this.actionType);
                    codedOutputByteBufferNano.writeString(2, this.intent);
                    super.writeTo(codedOutputByteBufferNano);
                }

                /* access modifiers changed from: protected */
                public int computeSerializedSize() {
                    return super.computeSerializedSize() + CodedOutputByteBufferNano.computeInt32Size(1, this.actionType) + CodedOutputByteBufferNano.computeStringSize(2, this.intent);
                }

                public TapAction mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    while (true) {
                        int readTag = codedInputByteBufferNano.readTag();
                        if (readTag == 0) {
                            return this;
                        }
                        if (readTag == 8) {
                            int readInt32 = codedInputByteBufferNano.readInt32();
                            if (readInt32 == 0 || readInt32 == 1 || readInt32 == 2) {
                                this.actionType = readInt32;
                            }
                        } else if (readTag == 18) {
                            this.intent = codedInputByteBufferNano.readString();
                        } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                            return this;
                        }
                    }
                }

                public static TapAction parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                    TapAction tapAction = new TapAction();
                    MessageNano.mergeFrom(tapAction, bArr);
                    return tapAction;
                }

                public static TapAction parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                    return new TapAction().mergeFrom(codedInputByteBufferNano);
                }
            }

            public static SmartspaceCard[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SmartspaceCard[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SmartspaceCard() {
                clear();
            }

            public SmartspaceCard clear() {
                this.shouldDiscard = false;
                this.cardId = 0;
                this.preEvent = null;
                this.duringEvent = null;
                this.postEvent = null;
                this.icon = null;
                this.cardType = 0;
                this.tapAction = null;
                this.updateTimeMillis = 0;
                this.eventTimeMillis = 0;
                this.eventDurationMillis = 0;
                this.expiryCriteria = null;
                this.cardPriority = 0;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
                codedOutputByteBufferNano.writeBool(1, this.shouldDiscard);
                codedOutputByteBufferNano.writeInt32(2, this.cardId);
                Message message = this.preEvent;
                if (message != null) {
                    codedOutputByteBufferNano.writeMessage(3, message);
                }
                Message message2 = this.duringEvent;
                if (message2 != null) {
                    codedOutputByteBufferNano.writeMessage(4, message2);
                }
                Message message3 = this.postEvent;
                if (message3 != null) {
                    codedOutputByteBufferNano.writeMessage(5, message3);
                }
                Image image = this.icon;
                if (image != null) {
                    codedOutputByteBufferNano.writeMessage(6, image);
                }
                codedOutputByteBufferNano.writeInt32(7, this.cardType);
                TapAction tapAction2 = this.tapAction;
                if (tapAction2 != null) {
                    codedOutputByteBufferNano.writeMessage(8, tapAction2);
                }
                codedOutputByteBufferNano.writeInt64(9, this.updateTimeMillis);
                codedOutputByteBufferNano.writeInt64(10, this.eventTimeMillis);
                codedOutputByteBufferNano.writeInt64(11, this.eventDurationMillis);
                ExpiryCriteria expiryCriteria2 = this.expiryCriteria;
                if (expiryCriteria2 != null) {
                    codedOutputByteBufferNano.writeMessage(12, expiryCriteria2);
                }
                codedOutputByteBufferNano.writeInt32(13, this.cardPriority);
                super.writeTo(codedOutputByteBufferNano);
            }

            /* access modifiers changed from: protected */
            public int computeSerializedSize() {
                int computeSerializedSize = super.computeSerializedSize() + CodedOutputByteBufferNano.computeBoolSize(1, this.shouldDiscard) + CodedOutputByteBufferNano.computeInt32Size(2, this.cardId);
                Message message = this.preEvent;
                if (message != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, message);
                }
                Message message2 = this.duringEvent;
                if (message2 != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(4, message2);
                }
                Message message3 = this.postEvent;
                if (message3 != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(5, message3);
                }
                Image image = this.icon;
                if (image != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(6, image);
                }
                int computeInt32Size = computeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(7, this.cardType);
                TapAction tapAction2 = this.tapAction;
                if (tapAction2 != null) {
                    computeInt32Size += CodedOutputByteBufferNano.computeMessageSize(8, tapAction2);
                }
                int computeInt64Size = computeInt32Size + CodedOutputByteBufferNano.computeInt64Size(9, this.updateTimeMillis) + CodedOutputByteBufferNano.computeInt64Size(10, this.eventTimeMillis) + CodedOutputByteBufferNano.computeInt64Size(11, this.eventDurationMillis);
                ExpiryCriteria expiryCriteria2 = this.expiryCriteria;
                if (expiryCriteria2 != null) {
                    computeInt64Size += CodedOutputByteBufferNano.computeMessageSize(12, expiryCriteria2);
                }
                return computeInt64Size + CodedOutputByteBufferNano.computeInt32Size(13, this.cardPriority);
            }

            public SmartspaceCard mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                while (true) {
                    int readTag = codedInputByteBufferNano.readTag();
                    switch (readTag) {
                        case 0:
                            return this;
                        case 8:
                            this.shouldDiscard = codedInputByteBufferNano.readBool();
                            break;
                        case 16:
                            this.cardId = codedInputByteBufferNano.readInt32();
                            break;
                        case 26:
                            if (this.preEvent == null) {
                                this.preEvent = new Message();
                            }
                            codedInputByteBufferNano.readMessage(this.preEvent);
                            break;
                        case 34:
                            if (this.duringEvent == null) {
                                this.duringEvent = new Message();
                            }
                            codedInputByteBufferNano.readMessage(this.duringEvent);
                            break;
                        case 42:
                            if (this.postEvent == null) {
                                this.postEvent = new Message();
                            }
                            codedInputByteBufferNano.readMessage(this.postEvent);
                            break;
                        case 50:
                            if (this.icon == null) {
                                this.icon = new Image();
                            }
                            codedInputByteBufferNano.readMessage(this.icon);
                            break;
                        case 56:
                            int readInt32 = codedInputByteBufferNano.readInt32();
                            switch (readInt32) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                    this.cardType = readInt32;
                                    break;
                            }
                        case R$styleable.ConstraintSet_layout_goneMarginBottom:
                            if (this.tapAction == null) {
                                this.tapAction = new TapAction();
                            }
                            codedInputByteBufferNano.readMessage(this.tapAction);
                            break;
                        case 72:
                            this.updateTimeMillis = codedInputByteBufferNano.readInt64();
                            break;
                        case 80:
                            this.eventTimeMillis = codedInputByteBufferNano.readInt64();
                            break;
                        case 88:
                            this.eventDurationMillis = codedInputByteBufferNano.readInt64();
                            break;
                        case 98:
                            if (this.expiryCriteria == null) {
                                this.expiryCriteria = new ExpiryCriteria();
                            }
                            codedInputByteBufferNano.readMessage(this.expiryCriteria);
                            break;
                        case 104:
                            this.cardPriority = codedInputByteBufferNano.readInt32();
                            break;
                        default:
                            if (WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                                break;
                            } else {
                                return this;
                            }
                    }
                }
            }

            public static SmartspaceCard parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
                SmartspaceCard smartspaceCard = new SmartspaceCard();
                MessageNano.mergeFrom(smartspaceCard, bArr);
                return smartspaceCard;
            }

            public static SmartspaceCard parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
                return new SmartspaceCard().mergeFrom(codedInputByteBufferNano);
            }
        }

        public static SmartspaceUpdate[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SmartspaceUpdate[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SmartspaceUpdate() {
            clear();
        }

        public SmartspaceUpdate clear() {
            this.card = SmartspaceCard.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            SmartspaceCard[] smartspaceCardArr = this.card;
            if (smartspaceCardArr != null && smartspaceCardArr.length > 0) {
                int i = 0;
                while (true) {
                    SmartspaceCard[] smartspaceCardArr2 = this.card;
                    if (i >= smartspaceCardArr2.length) {
                        break;
                    }
                    SmartspaceCard smartspaceCard = smartspaceCardArr2[i];
                    if (smartspaceCard != null) {
                        codedOutputByteBufferNano.writeMessage(1, smartspaceCard);
                    }
                    i++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        /* access modifiers changed from: protected */
        public int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            SmartspaceCard[] smartspaceCardArr = this.card;
            if (smartspaceCardArr != null && smartspaceCardArr.length > 0) {
                int i = 0;
                while (true) {
                    SmartspaceCard[] smartspaceCardArr2 = this.card;
                    if (i >= smartspaceCardArr2.length) {
                        break;
                    }
                    SmartspaceCard smartspaceCard = smartspaceCardArr2[i];
                    if (smartspaceCard != null) {
                        computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, smartspaceCard);
                    }
                    i++;
                }
            }
            return computeSerializedSize;
        }

        public SmartspaceUpdate mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    SmartspaceCard[] smartspaceCardArr = this.card;
                    int length = smartspaceCardArr == null ? 0 : smartspaceCardArr.length;
                    SmartspaceCard[] smartspaceCardArr2 = new SmartspaceCard[(repeatedFieldArrayLength + length)];
                    if (length != 0) {
                        System.arraycopy(this.card, 0, smartspaceCardArr2, 0, length);
                    }
                    while (length < smartspaceCardArr2.length - 1) {
                        smartspaceCardArr2[length] = new SmartspaceCard();
                        codedInputByteBufferNano.readMessage(smartspaceCardArr2[length]);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    smartspaceCardArr2[length] = new SmartspaceCard();
                    codedInputByteBufferNano.readMessage(smartspaceCardArr2[length]);
                    this.card = smartspaceCardArr2;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public static SmartspaceUpdate parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            SmartspaceUpdate smartspaceUpdate = new SmartspaceUpdate();
            MessageNano.mergeFrom(smartspaceUpdate, bArr);
            return smartspaceUpdate;
        }

        public static SmartspaceUpdate parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new SmartspaceUpdate().mergeFrom(codedInputByteBufferNano);
        }
    }

    public static final class CardWrapper extends MessageNano {
        private static volatile CardWrapper[] _emptyArray;
        public SmartspaceUpdate.SmartspaceCard card;
        public long gsaUpdateTime;
        public int gsaVersionCode;
        public byte[] icon;
        public boolean isIconGrayscale;
        public long publishTime;

        public static CardWrapper[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardWrapper[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CardWrapper() {
            clear();
        }

        public CardWrapper clear() {
            this.card = null;
            this.publishTime = 0;
            this.gsaUpdateTime = 0;
            this.gsaVersionCode = 0;
            this.icon = WireFormatNano.EMPTY_BYTES;
            this.isIconGrayscale = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            SmartspaceUpdate.SmartspaceCard smartspaceCard = this.card;
            if (smartspaceCard != null) {
                codedOutputByteBufferNano.writeMessage(1, smartspaceCard);
            }
            codedOutputByteBufferNano.writeInt64(2, this.publishTime);
            codedOutputByteBufferNano.writeInt64(3, this.gsaUpdateTime);
            codedOutputByteBufferNano.writeInt32(4, this.gsaVersionCode);
            codedOutputByteBufferNano.writeBytes(5, this.icon);
            codedOutputByteBufferNano.writeBool(6, this.isIconGrayscale);
            super.writeTo(codedOutputByteBufferNano);
        }

        /* access modifiers changed from: protected */
        public int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            SmartspaceUpdate.SmartspaceCard smartspaceCard = this.card;
            if (smartspaceCard != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, smartspaceCard);
            }
            return computeSerializedSize + CodedOutputByteBufferNano.computeInt64Size(2, this.publishTime) + CodedOutputByteBufferNano.computeInt64Size(3, this.gsaUpdateTime) + CodedOutputByteBufferNano.computeInt32Size(4, this.gsaVersionCode) + CodedOutputByteBufferNano.computeBytesSize(5, this.icon) + CodedOutputByteBufferNano.computeBoolSize(6, this.isIconGrayscale);
        }

        public CardWrapper mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 10) {
                    if (this.card == null) {
                        this.card = new SmartspaceUpdate.SmartspaceCard();
                    }
                    codedInputByteBufferNano.readMessage(this.card);
                } else if (readTag == 16) {
                    this.publishTime = codedInputByteBufferNano.readInt64();
                } else if (readTag == 24) {
                    this.gsaUpdateTime = codedInputByteBufferNano.readInt64();
                } else if (readTag == 32) {
                    this.gsaVersionCode = codedInputByteBufferNano.readInt32();
                } else if (readTag == 42) {
                    this.icon = codedInputByteBufferNano.readBytes();
                } else if (readTag == 48) {
                    this.isIconGrayscale = codedInputByteBufferNano.readBool();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public static CardWrapper parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
            CardWrapper cardWrapper = new CardWrapper();
            MessageNano.mergeFrom(cardWrapper, bArr);
            return cardWrapper;
        }

        public static CardWrapper parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new CardWrapper().mergeFrom(codedInputByteBufferNano);
        }
    }
}
