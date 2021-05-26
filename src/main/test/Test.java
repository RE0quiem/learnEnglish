import java.util.regex.Pattern;

/**
 * @author zjz
 * @version 1.0
 * @ClassName Test
 * @description
 * @date 2021/5/17 下午9:46
 * @since JDK 1.8
 */
public class Test {
    private static String p="(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}-\\d{1,2}-\\d{1,2}) To (\\d{4}-\\d{1,2}-\\d{1,2})";

    @org.junit.jupiter.api.Test
    public void myTest() {
        String str ="2021-05-13";
        Pattern.matches(p,str);
    }
}
