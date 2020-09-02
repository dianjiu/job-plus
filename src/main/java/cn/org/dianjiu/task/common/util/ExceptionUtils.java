package cn.org.dianjiu.task.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    //获取控制台打印日志
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 获取具体的异常信息
     * @param ex
     * @return
     */
    public static String getExceptionDetail(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            ex.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
