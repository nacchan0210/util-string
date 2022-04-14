package tech.narita.util.string;
import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.List;
/**
 * 標準ライブラリの{@link String}クラスにはない文字列操作や、記述がより簡略化された静的メソッド群です。
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public final class StringUtil{
    /**
     * 日本語、句読点、数字、アルファベットのみを抜き出した文字列を返します。<br>
     * スクリーンリーダーで自然な日本語として読み上げられる文字列への変換を目的としています。
     * @param text 変換前の文字列
     * @return 日本語、句読点、数字、アルファベットのみを抜き出した文字列
     */
    public final static String japanese(String text){
        StringBuilder sb = new StringBuilder();
        UnicodeBlock block;
        int codePoint;
        for(char c:text.toCharArray()){
            codePoint = (int)c;
            if(codePoint >= 48 && codePoint <= 57){
                sb.append(c);
            }else if(codePoint >= 65 && codePoint <= 90){
                sb.append(c);
            }else if(codePoint >= 97 && codePoint <= 122){
                sb.append(c);
            }else if(codePoint >= 65296 && codePoint <= 65305){
                sb.append(c);
            }else if(codePoint >= 65313 && codePoint <= 65338){
                sb.append(c);
            }else if(codePoint >= 65345 && codePoint <= 65370){
                sb.append(c);
            }else if(codePoint >= 65382 && codePoint <= 65439){
                sb.append(c);
            }else if(codePoint >= 65584 && codePoint <= 65593){
                sb.append(c);
            }else if(codePoint >= 65601 && codePoint <= 65626){
                sb.append(c);
            }else if(codePoint >= 65633 && codePoint <= 65658){
                sb.append(c);
            }else if(c == '。'){
                sb.append(c);
            }else if(c == '、'){
                sb.append(c);
            }else{
                block = UnicodeBlock.of(c);
                if(block == UnicodeBlock.HIRAGANA){
                    sb.append(c);
                }else if(block == UnicodeBlock.KATAKANA){
                    sb.append(c);
                }else if(block == UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS){
                    sb.append(c);
                }else if(block == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    /**
     * 日本語、句読点、数字、アルファベットのみを抜き出した文字列を返します。<br>
     * スクリーンリーダーで自然な日本語として読み上げられる文字列への変換を目的としています。
     * @param text 変換前の文字列
     * @return 日本語、句読点、数字、アルファベットのみを抜き出した文字列
     */
    public final static String jp(String text){
        return japanese(text);
    }
    /**
     * 引数文字列の桁数が指定桁数に満たない場合、文字列の接頭に0をつけて桁数を揃えます。<br>
     * 引数文字列の桁数が指定桁数を超えている場合は、引数文字列をそのまま返します。<br>
     * 8桁のJANコードを13桁表記にする場合などに便利です。
     * @param number 変換前の文字列
     * @param numberOfDigit 指定桁数
     * @return 指定桁数に足りるよう接頭に0を付け加えた文字列
     */
    public final static String alignmentDigit(String number, int numberOfDigit){
        try{
            return "0".repeat(numberOfDigit - number.length()) + number;
        }catch(IllegalArgumentException e){
            return number;
        }
    }
    /**
     * 引数文字列の桁数が指定桁数に満たない場合、文字列の接頭に0をつけて桁数を揃えます。<br>
     * 引数文字列の桁数が指定桁数を超えている場合は、引数文字列をそのまま返します。<br>
     * 8桁のJANコードを13桁表記にする場合などに便利です。
     * @param number 変換前の数値
     * @param numberOfDigit 指定桁数
     * @return 指定桁数に足りるよう接頭に0を付け加えた文字列
     */
    public final static String alignmentDigit(long number, int numberOfDigit){
        return alignmentDigit(String.valueOf(number), numberOfDigit);
    }
    /**
     * 引数文字列の桁数が指定桁数に満たない場合、文字列の接頭に0をつけて桁数を揃えます。<br>
     * 引数文字列の桁数が指定桁数を超えている場合は、引数文字列をそのまま返します。<br>
     * 8桁のJANコードを13桁表記にする場合などに便利です。
     * @param number 変換前の文字列
     * @param numberOfDigit 指定桁数
     * @return 指定桁数に足りるよう接頭に0を付け加えた文字列
     */
    public final static String ketazoroe(String number, int numberOfDigit){
        return alignmentDigit(String.valueOf(number), numberOfDigit);
    }
    /**
     * 引数文字列の桁数が指定桁数に満たない場合、文字列の接頭に0をつけて桁数を揃えます。<br>
     * 引数文字列の桁数が指定桁数を超えている場合は、引数文字列をそのまま返します。<br>
     * 8桁のJANコードを13桁表記にする場合などに便利です。
     * @param number 変換前の数値
     * @param numberOfDigit 指定桁数
     * @return 指定桁数に足りるよう接頭に0を付け加えた文字列
     */
    public final static String ketazoroe(long number, int numberOfDigit){
        return alignmentDigit(String.valueOf(number), numberOfDigit);
    }
    /**
     * 第１引数を第２引数で分割したリストを返します。※リストには空文字と空白は含まれません。
     * @param text 分割前の文字列
     * @param delimiter 区切り文字列
     * @return 区切り文字列で分割されたリスト
     */
    public final static List<String> split(String text, String delimiter){
        List<String> result = new ArrayList<>();
        String part;
        int begin = 0;
        int end, temp;
        while(true){
            temp = text.indexOf(delimiter, begin);
            if(temp == -1){
                result.add(text.substring(begin));
                break;
            }
            end = temp;
            if(begin != end){
                part = text.substring(begin, end);
                if(!part.isBlank()){
                    result.add(part);
                }
            }
            begin = end + delimiter.length();
        }
        return result;
    }
    /**
     * 第１引数を第２引数で分割したリストを返します。※リストには空文字と空白は含まれません。
     * @param text 分割前の文字列
     * @param delimiter 区切り文字列
     * @return 区切り文字列で分割されたリスト
     */
    public final static List<String> bunkatsu(String text, String delimiter){
        return split(text, delimiter);
    }
    /**
     * 第１引数から第２引数を削除した文字列を返します。
     * @param text 部分削除前の文字列
     * @param deleteString 削除する文字列
     * @return 部分削除後の文字列
     */
    public final static String delete(String text, String deleteString){
        return text.replaceAll(deleteString, "");
    }
    /**
     * {@link StringBuilder#delete(int, int)}の表記簡略化メソッドです。
     * @param text 部分削除前の文字列
     * @param begin 開始インデックス(この値を含む)
     * @param end 終了インデックス(この値を含まない)
     * @return 部分削除後の文字列
     */
    public final static String delete(String text, int begin, int end){
        return new StringBuilder(text).delete(begin, end).toString();
    }
    /**
     * {@link StringBuilder#deleteCharAt(int)}の表記簡略化メソッドです。
     * @param text 部分削除前の文字列
     * @param index 削除されるcharのインデックス
     * @return 部分削除後の文字列
     */
    public final static String deleteCharAt(String text, int index){
        return new StringBuilder(text).deleteCharAt(index).toString();
    }
    /**
     * {@link StringBuilder#insert(int, java.lang.String)}の表記簡略化メソッドです。
     * @param text 挿入前の文字列
     * @param offset 文字列を挿入する位置
     * @param addString 挿入する文字列
     * @return 挿入後の文字列
     */
    public final static String insert(String text, int offset, String addString){
        return new StringBuilder(text).insert(offset, addString).toString();
    }
    /**
     * {@link StringBuilder#insert(int, java.lang.Object)}の表記簡略化メソッドです。
     * @param text 挿入前の文字列
     * @param offset 文字列を挿入する位置
     * @param addString 挿入する文字列
     * @return 挿入後の文字列
     */
    public final static String insert(String text, int offset, Object addString){
        return new StringBuilder(text).insert(offset, addString).toString();
    }
    /**
     * 引数の文字列がASINコードならtrueを返し、それ以外の場合はfalseを返します。
     * @param text 検査対象の文字列
     * @return 引数の文字列がASINコードならtrueを返します(それ以外の場合はfalse)
     */
    public final static boolean isAsin(String text){
        return text.matches("[0-9B]([0-9A-Z]){9}");
    }
    /**
     * 引数の文字列がJANコードならtrueを返し、それ以外の場合はfalseを返します。
     * @param text 検査対象の文字列
     * @return 引数の文字列がJANコードならtrueを返します(それ以外の場合はfalse)
     */
    public final static boolean isJan(String text){
        if(!text.matches("\\d{13}|\\d{8}"))return false;
        int answer = Character.digit(text.charAt(text.length() - 1), 10);
        int[] array = toIntArray(text);
        int length = text.length();
        int number = 0;
        for(int i=1;i<length-1;i+=2){
            number += array[i];
        }
        number *= 3;
        
        for(int i=0;i<length-1;i+=2){
            number += array[i];
        }
        return 10 - number % 10 == answer;
    }
    /**
     * 文字列をint型配列に変換します。<br>
     * 例えば、文字列"13524"はint型配列の[1, 3, 5, 2, 4]に変換されます。
     * @param text 文字列
     * @return int型配列
     * @throws java.lang.NumberFormatException 文字列に数字以外の文字が含まれている場合
     */
    public final static int[] toIntArray(String text){
        int length = text.length();
        int[] array = new int[length];
        int number;
        for(int i=0;i<length;i++){
            number = text.charAt(i) - 48;
            if(number < 0 || number > 9){
                throw new NumberFormatException(text.substring(0, i) + "(" + text.charAt(i) + ")" + text.substring(i + 1) + " " + i + "番目の文字「" + text.charAt(i) + "」は数字ではありません。");
            }
            array[i] = number;
        }
        return array;
    }
    /**
     * 数字以外の文字を削除して、文字列を無理やりint型に変換します。<br>
     * 文字列に数字が含まれていない場合は0を返します。<br>
     * 戻り値が{@link Integer#MAX_VALUE}より大きい場合は{@link Integer#MAX_VALUE}を、{@link Integer#MIN_VALUE}より小さい場合は{@link Integer#MIN_VALUE}を返します。<br>
     * 絶対に例外エラーにはならないので、慎重に利用してください。
     * @param text 変換前の文字列
     * @return 変換後のint型変数
     */
    public final static int forceInt(String text){
        String numString = text.replaceAll("[^\\-0-9]", "");
        if(numString.isEmpty())return 0;
        try{
            return Integer.parseInt(numString);
        }catch(NumberFormatException e){
            if(text.contains("-")){
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
    }
    /**
     * 数字以外の文字を削除して、文字列を無理やりlong型に変換します。<br>
     * 文字列に数字が含まれていない場合は0を返します。<br>
     * 戻り値が{@link Long#MAX_VALUE}より大きい場合は{@link Long#MAX_VALUE}を、{@link Long#MIN_VALUE}より小さい場合は{@link Long#MIN_VALUE}を返します。<br>
     * 絶対に例外エラーにはならないので、慎重に利用してください。
     * @param text 変換前の文字列
     * @return 変換後のlong型変数
     */
    public final static long forceLong(String text){
        String numString = text.replaceAll("[^\\-0-9]", "");
        if(numString.isEmpty())return 0;
        try{
            return Long.parseLong(numString);
        }catch(NumberFormatException e){
            if(text.contains("-")){
                return Long.MIN_VALUE;
            }
            return Long.MAX_VALUE;
        }
    }
    /**
     * 数字以外の文字を削除して、文字列を無理やりdouble型に変換します。<br>
     * 文字列に数字が含まれていない場合は0を返します。<br>
     * 戻り値が{@link Double#MAX_VALUE}より大きい場合は{@link Double#MAX_VALUE}を、{@link Double#MIN_VALUE}より小さい場合は{@link Double#MIN_VALUE}を返します。<br>
     * 絶対に例外エラーにはならないので、慎重に利用してください。
     * @param text 変換前の文字列
     * @return 変換後のdouble型変数
     */
    public final static double forceDouble(String text){
        String numString = text.replaceAll("[^\\-\\.0-9]", "");
        if(numString.isEmpty())return 0;
        try{
            return Double.parseDouble(numString);
        }catch(NumberFormatException e){
            if(text.contains("-")){
                return Double.MIN_VALUE;
            }
            return Double.MAX_VALUE;
        }
    }
    /**
     * 数字以外の文字を削除して、文字列を無理やりfloat型に変換します。<br>
     * 文字列に数字が含まれていない場合は0を返します。<br>
     * 戻り値が{@link Float#MAX_VALUE}より大きい場合は{@link Float#MAX_VALUE}を、{@link Float#MIN_VALUE}より小さい場合は{@link Float#MIN_VALUE}を返します。<br>
     * 絶対に例外エラーにはならないので、慎重に利用してください。
     * @param text 変換前の文字列
     * @return 変換後のfloat型変数
     */
    public final static float forceFloat(String text){
        String numString = text.replaceAll("[^\\-\\.0-9]", "");
        if(numString.isEmpty())return 0;
        try{
            return Float.parseFloat(numString);
        }catch(NumberFormatException e){
            if(text.contains("-")){
                return Float.MIN_VALUE;
            }
            return Float.MAX_VALUE;
        }
    }
}