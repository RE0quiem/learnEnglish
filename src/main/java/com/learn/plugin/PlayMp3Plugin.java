package com.learn.plugin;

import com.learn.domain.WordsWrapper;
import com.learn.main.MainClass;
import com.learn.utils.GetNetResourceByUrl;
import org.eclipse.core.runtime.Assert;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName PlayMp3
 * @description
 * @date 2021/6/8 15:24
 * @since JDK 1.8
 */
public class PlayMp3Plugin extends CustomPlugin{
    private final String TTSURL = "https://fanyi.baidu.com/gettts";
    private final String LANG = "en";
    // todo 后面考虑换成guava的cacheBuilder
    private static Map<String, byte[]> mp3Cache = new HashMap<>();

    public PlayMp3Plugin() {
        runningTime = enumRunningTime[0];
    }

    @Override
    public Object apply(Object o) {
        Object[] params = (Object[]) o;
        WordsWrapper wordsWrapper = (WordsWrapper) params[0];
        byte[] cahche = mp3Cache.get(wordsWrapper.getWords().getWords());
        if (cahche == null) {
            Map<String, String> pram = new HashMap();
            //https://fanyi.baidu.com/gettts?lan=en&text=spring!&spd=3&source=web
            pram.put("lan", LANG);
            pram.put("text", wordsWrapper.getWords().getWords().replaceAll(" ", ""));
            pram.put("spd", "6");
            pram.put("source", "web");

            byte[] mp3Bytes = GetNetResourceByUrl.GetNetResourceByUrl(TTSURL, pram);
            mp3Cache.put(wordsWrapper.getWords().getWords(), mp3Bytes);
            cahche = mp3Bytes;
        }

        Assert.isTrue(cahche != null);
        playMap3Execute(cahche);


        return null;
    }

    private void playMap3Execute(byte[] mp3Bytes) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(mp3Bytes));

        MainClass.executor.execute(() -> {
            try {
                // 文件流
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                // 文件编码
                AudioFormat audioFormat = audioInputStream.getFormat();
                // 转换文件编码
                if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16, audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
                    // 将数据流也转换成指定编码
                    audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
                }


                // 打开输出设备
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
                // 使数据行得到一个播放设备
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                // 将数据行用指定的编码打开
                sourceDataLine.open(audioFormat);
                // 使数据行得到数据时就开始播放
                sourceDataLine.start();

                int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
                // 将流数据逐渐写入数据行,边写边播
                int numBytes = 1024 * bytesPerFrame;
                byte[] audioBytes = new byte[numBytes];
                while (audioInputStream.read(audioBytes) != -1) {
                    sourceDataLine.write(audioBytes, 0, audioBytes.length);
                }
                sourceDataLine.drain();
                sourceDataLine.stop();
                sourceDataLine.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
