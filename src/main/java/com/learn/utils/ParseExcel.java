package com.learn.utils;

import com.learn.domain.Words;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author zjz
 * @version 1.0
 * @ClassName ParseExcel
 * @description
 * @date 2021/5/16 下午2:01
 * @since JDK 1.8
 */
public class ParseExcel {
    URL url = null;
    List<Words> wordsList;

    public ParseExcel() {
        this(null);
    }

    public ParseExcel(String filePath) {
        if (filePath == null) {
            URL resource = getClass().getClassLoader().getResource("learn.xls");
            this.url = resource;
        }
    }

    public List<Words> getWordsList() {
        if (CollectionUtils.isEmpty(wordsList)) {
            readFile();
        }
        return wordsList;
    }

    @SneakyThrows
    private void readFile() {
        File file = new File(this.url.getFile());
        InputStream inputStream = new FileInputStream(file);

        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();
        Iterator<Cell> cellItr;
        Cell cell;
        int columnIndex;
        List<Words> list = new ArrayList<>();

        Words words;
        List<String> meansList;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastDateRecord = null;

        while (itr.hasNext()) {
            Row row = itr.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            cellItr = row.cellIterator();
            words = new Words();
            meansList = new ArrayList<>();

            while (cellItr.hasNext()) {
                cell = cellItr.next();
                columnIndex = cell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        if (cell.getDateCellValue() != null) {
                            lastDateRecord = format.format(cell.getDateCellValue());
                        }
                        words.setBuildDate(lastDateRecord);
                        break;
                    case 1:
                        words.setWords(cell.getStringCellValue());
                        break;
                    case 2:
                        words.setPhoneticSymbol(cell.getStringCellValue());
                        break;
                    case 3:
                        words.setCharacteristic(Arrays.asList(cell.getStringCellValue().split(",")));
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        wordsSetMeans(meansList, words, cell.getStringCellValue());
                        break;
                    case 8:
                        words.setPhrase(cell.getStringCellValue());
                        break;
                    case 9:
                        words.setExampleSentence(cell.getStringCellValue());
                    default:
                }

            }

            list.add(words);
        }

        this.wordsList = list;
    }

    private void wordsSetMeans(List<String> meansList, Words words, String means) {
        assert meansList != null;
        if (words.getMeans() == null) {
            words.setMeans(meansList);
        }
        if (means != null) {
            meansList.add(means);
        }
    }
}
