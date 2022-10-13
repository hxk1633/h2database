package org.h2.value;
import org.h2.api.ErrorCode;
import org.h2.message.DbException;
import java.io.Serializable;

public class Emoticon implements Serializable {
    private String emoticon;

    public Emoticon(String emoticon) {
        if (emoticon.equals(":)") || emoticon.equals(":(")) {
            this.emoticon = emoticon;
        } else {
            throw DbException.get(ErrorCode.INVALID_EMOTICON_ERROR_CODE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Emoticon other = (Emoticon) obj;
        if (emoticon == null) {
            if (other.emoticon != null)
                return false;
        } else if (!emoticon.equals(other.emoticon))
            return false;
        return true;
    }


    @Override
    public String toString(){
        return emoticon;
    }

    @Override
    public int hashCode() {
        final int prime = 91;
        return 4 * prime *  + ((emoticon == null) ? 0 : emoticon.hashCode());
    }
}
