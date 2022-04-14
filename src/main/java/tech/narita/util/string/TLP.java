package tech.narita.util.string;
/**
 * {@link TextLoopPerser}クラスの名称短縮版クラスです。
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public abstract class TLP extends TP{
    /**
     * 検索対象文字列と区切り文字列を定義し初期化します。
     * @param text 検索対象文字列
     * @param delimiter 区切り文字列
     */
    protected TLP(String text, String delimiter){
        super(text);
        if(!text.contains(delimiter)){
            no();
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
    public void no(){}
    /**
     * 検索対象文字列から区切り文字列が発見される度にこのメソッドが処理されます。<br>
     * 発見された区切り文字列と次回発見される区切り文字列、もしくは検索対象文字列の最後の文字の間の文字列に対して抜き出し処理を行うことができます。
     */
    public abstract void perseInLoop();
}