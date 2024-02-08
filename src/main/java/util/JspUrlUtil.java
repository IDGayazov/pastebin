package util;

public class JspUrlUtil {
    private static final String URL = "/WEB-INF/jsp/%s";

    public static String getJspUrl(String jsp){
        return String.format(URL, jsp);
    }
}
