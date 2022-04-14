package tech.narita.util.string;
/**
 * 検索対象文字列を区切り文字列で分割し、それぞれの文字列に{@link TextPerser}クラスを適用します。
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public abstract class TextLoopPerser extends TextPerser{
    /**
     * 検索対象文字列と区切り文字列を定義し初期化します。
     * @param text 検索対象文字列
     * @param delimiter 区切り文字列
     */
    protected TextLoopPerser(String text, String delimiter){
        super(text);
        if(!text.contains(delimiter)){
            noPerse();
            return;
        }
        String[] texts = text.split(delimiter);
        int size = texts.length;
        for(int i=1;i<size;i++){
            super.text = texts[i];
            finish = false;
            start = 0;
            end = 0;
            perseInLoop();
        }
    }
    /**
     * このメソッドを呼び出さないでください。
     * @deprecated このメソッドを呼び出さないでください。
     */
    @Override
    @Deprecated
    protected final void perse(){}
    /**
     * 検索対象文字列に区切り文字列が含まれていない場合にこのメソッドが処理されます。
     */
    public void noPerse(){}
    /**
     * 検索対象文字列から区切り文字列が発見される度にこのメソッドが処理されます。<br>
     * 発見された区切り文字列と次回発見される区切り文字列、もしくは検索対象文字列の最後の文字の間の文字列に対して抜き出し処理を行うことができます。
     */
    public abstract void perseInLoop();
}