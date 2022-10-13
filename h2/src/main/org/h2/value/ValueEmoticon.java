package org.h2.value;

import org.h2.engine.CastDataProvider;

public class ValueEmoticon extends Value {

    protected Emoticon emoticon;
    private TypeInfo type;

    public ValueEmoticon(String emoticon) {
        this.emoticon = new Emoticon(emoticon);
    }

    public ValueEmoticon(Emoticon emoticonValue){
        this.emoticon = emoticonValue;
    }

    public static Value get(Emoticon emoticonObj)  {
        ValueEmoticon value = new ValueEmoticon(emoticonObj);
        return value;
    }

    @Override
    public StringBuilder getSQL(StringBuilder builder, int sqlFlags) {
        return new StringBuilder(emoticon.toString());
    }

    @Override
    public TypeInfo getType() {
        TypeInfo type = this.type;
        if (type == null) {
            int length = emoticon.toString().length();
            this.type = type = new TypeInfo(getValueType(), length, 0, null);
        }
        return type;
    }

    @Override
    public int getValueType() {
        return EMOTICON;
    }

    @Override
    public int hashCode() {
        return emoticon.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ValueEmoticon && emoticon.equals(((ValueEmoticon) other).emoticon);
    }

    @Override
    public String getString() {
        return emoticon.toString();
    }

    @Override
    public int compareTypeSafe(Value v, CompareMode mode, CastDataProvider provider) {
        return mode.compareString(emoticon.toString(), ((ValueEmoticon) v).emoticon.toString(), false);
    }
}
