package cn.lcy.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 用于返回json数据
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object object)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(object);
        out.flush();
        out.close();
    }


}
