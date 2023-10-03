package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;

public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    static {
        ExtensionRegistryLite.getEmptyRegistry();
    }

    private MessageType checkMessageInitialized(MessageType messagetype) throws InvalidProtocolBufferException {
        UninitializedMessageException uninitializedMessageException;
        if (messagetype == null || messagetype.isInitialized()) {
            return messagetype;
        }
        if (messagetype instanceof AbstractMessageLite) {
            uninitializedMessageException = ((AbstractMessageLite) messagetype).newUninitializedMessageException();
        } else {
            uninitializedMessageException = new UninitializedMessageException();
        }
        throw uninitializedMessageException.asInvalidProtocolBufferException().setUnfinishedMessage(messagetype);
    }

    public Object parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        MessageLite messageLite;
        try {
            CodedInputStream newCodedInput = byteString.newCodedInput();
            messageLite = (MessageLite) ((GeneratedMessageLite.DefaultInstanceBasedParser) this).parsePartialFrom(newCodedInput, extensionRegistryLite);
            newCodedInput.checkLastTagWas(0);
            checkMessageInitialized(messageLite);
            return messageLite;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messageLite);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }
}
