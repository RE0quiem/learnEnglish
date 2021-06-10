import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;
import com.learn.plugin.PlayMp3Plugin;
import com.learn.plugin.pluginType.BeforeType;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MemberUsageScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
    private static String p = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}-\\d{1,2}-\\d{1,2}) To (\\d{4}-\\d{1,2}-\\d{1,2})";

    @org.junit.jupiter.api.Test
    public void myTest() {
        String str = "2021-05-13";
        Pattern.matches(p, str);
    }

    @org.junit.jupiter.api.Test
    public void testScan() {
        Reflections reflections = new Reflections("com.learn.plugin.*", Arrays.asList(
                new SubTypesScanner(false)
                , new MethodParameterNamesScanner()
                , new MethodAnnotationsScanner()
                , new MemberUsageScanner()
                , new TypeAnnotationsScanner()
                , new FieldAnnotationsScanner()
        ));
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(BeforeType.class,true);
        System.out.println(typesAnnotatedWith);
    }

    @org.junit.jupiter.api.Test
    public void testFileWrite() {
//        WriteFile.writeFile(null,"aaaa");?
    }

    @org.junit.jupiter.api.Test
    public void testPlayMp3() throws InterruptedException {
        Words words = new Words();
        words.setWords("get");
        WordsWrapper wordsWrapper = new WordsWrapper();
        wordsWrapper.setWords(words);

        PlayMp3Plugin mp3Plugin = new PlayMp3Plugin();
        mp3Plugin.apply(new Object[]{wordsWrapper});
        TimeUnit.SECONDS.sleep(10);
    }
}
