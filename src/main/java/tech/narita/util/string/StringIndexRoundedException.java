package tech.narita.util.string;
/**
 * {@link TextPerser}クラスもしくは{@link TextLoopPerser}クラスの利用時に、無限ループや処理の重複が発生し得る場合にスローされます。
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public final class StringIndexRoundedException extends RuntimeException{
    public StringIndexRoundedException(){
        super("開始インデックスが既に一周しているため、無限ループや意図せぬ結果が発生する可能性があります。\nエラー発生行の直前に if(isFinish())return; と記述してエラーの回避を試みてください。");
    }
}