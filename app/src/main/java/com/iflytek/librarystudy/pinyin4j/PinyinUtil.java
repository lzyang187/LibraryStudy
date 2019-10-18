package com.iflytek.librarystudy.pinyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author: cyli8
 * @date: 2019-10-17 15:56
 */
public class PinyinUtil {
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        String str = "此消彼长";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(c);
            for (String string : strings) {
                System.out.println(string);
            }
        }

        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //音调的输出格式
//        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        //定义字符“u”的输出格式
//        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
//        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        //定义汉语拼音字符的输出大小写
//        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        str = "军旅";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
            for (String string : strings) {
                System.out.println(string);
            }
        }

        System.out.println(getFirst("中华人民共和国"));
    }

    public static String getFirst(String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(string.charAt(i));
            if (strings.length > 0) {
                builder.append(strings[0].charAt(0));
            }
        }
        return builder.toString();
    }

}
