package cc.zhanyun.util.fileutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by hyh on 16-9-23.
 */
public class StreamUtil {

    public static InputStream outToIn(ByteArrayOutputStream outputStream) {
        InputStream in = new ByteArrayInputStream(outputStream.toByteArray());
        return in;

    }
}
