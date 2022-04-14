package tech.narita.util.string;
/**
 * 文字列検索等を用いて、検索対象文字列から特定の文字列を抜き出すためのクラスです。<br>
 * 匿名クラスとしてインスタンスを生成し、{@link perse}メソッドをオーバーライドして使います。<br>
 * このクラスのメソッドは主に操作メソッドと抜き出しメソッドの２種類となっています。<br>
 * 操作メソッドで文字列抜き出しの開始インデックスと終了インデックスを操作し、抜き出しメソッドで目的の文言を抜き出します。<br>
 * 例として、文字列"Ojama Iminashi Mukankei Keyword=Target Udo-no-taiboku Muyou-no-choubutsu"から文字列"Keyword"を目印に文字列"Target"を抜き出して表示する場合は下記のようなソ－スコードになります。<br>
 * <pre>
 * <code>
 * String text = "Ojama Iminashi Mukankei Keyword=Target Udo-no-taiboku Muyou-no-choubutsu";
 * new TextPerser(text){
 *     {@literal @}Override
 *     protected final void perse(){
 *         findFirst("Keyword");
 *         find("=", 1);
 *         findEnd(" ");
 *         System.out.println(substring());
 *     }
 * };
 * </code>
 * </pre>
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public abstract class TextPerser{
    String text;
    boolean finish;
    int start;
    int end;
    /**
     * 検索対象文字列を指定して初期化します。
     * @param text 検索対象の文字列
     */
    protected TextPerser(String text){
        this.text = text;
        perse();
    }
    /**
     * このメソッドをオーバーライドし、その他のメソッドを用いて、文字列の抜き出しを行ってください。
     */
    protected abstract void perse();
    /**
     * 開始インデックスを指定分移動します。
     * @param move 開始インデックスの移動量
     */
    protected final void move(int move){
        start += move;
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void findFirst(String keyword){
        setStart(firstIndexOf(keyword));
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void findFirst(String keyword, int moreMove){
        setStart(firstIndexOf(keyword) + moreMove);
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void find(String keyword){
        setStart(indexOf(keyword));
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void find(String keyword, int moreMove){
        setStart(indexOf(keyword) + moreMove);
    }
    /**
     * 開始インデックスを指定の位置に移動します。
     * @param start 開始インデックス
     */
    protected final void setStart(int start){
        this.start = start;
    }
    /**
     * 開始インデックスもしくは終了インデックスが検索対象文字列のインデックスを一周し終えている場合はtrueを、し終えてない場合はfalseを返します。<br>
     * その他のメソッドを呼び出す前に<code>if(isFinish())return;</code>とチェックすると意図せぬ動作や例外エラーを回避できます。
     * @return 開始インデックスもしくは終了インデックスが検索対象文字列のインデックスを一周し終えている場合はtrue(し終えてない場合はfalse)
     */
    protected final boolean isFinish(){
        return finish;
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ終了インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void findEnd(String keyword){
        setEnd(indexOf(keyword));
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ終了インデックスを移動し、更に追加移動量分だけ終了インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void findEnd(String keyword, int moreMove){
        setEnd(indexOf(keyword) + moreMove);
    }
    /**
     * 終了インデックスを指定の位置に移動します。
     * @param end 終了インデックス
     */
    protected final void setEnd(int end){
        this.end = end;
    }
    /**
     * 検索対象文字列から最後に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void findLast(String keyword){
        setStart(lastIndexOf(keyword));
    }
    /**
     * 検索対象文字列から最後に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void findLast(String keyword, int moreMove){
        setStart(lastIndexOf(keyword) + moreMove);
    }
    /**
     * 開始インデックスを現在の終了インデックスまで移動します。
     */
    protected final void startToEnd(){
        start = end;
    }
    /**
     * 終了インデックスを検索対象文字列の最後の文字まで移動します。
     */
    protected final void endToLast(){
        end = text.length() - 1;
        finish = true;
    }
    /**
     * 検索対象文字列からn文字目の文字を返します。
     * @param index 検索するインデックス
     * @return 文字
     */
    protected final char charAt(int index){
        return text.charAt(start + index);
    }
    /**
     * 検索対象文字列に検索文字列が含まれる場合はtrueを、含まれない場合はfalseを返します。
     * @param keyword 検索文字列
     * @return 検索対象文字列に検索文字列が含まれる場合はtrue(含まれない場合はfalse)
     */
    protected final boolean contains(String keyword){
        return text.contains(keyword);
    }
    /**
     * 現在の開始インデックス(この位置)から現在の終了インデックス(この位置のひとつ左)までにある文字列を抜き出します。<br>
     * @return 抜き出した文字列
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final String substring(){
        if(finish)throw new StringIndexRoundedException();
        return text.substring(start, end);
    }
    /**
     * 文字列を抜き出し、int型に変換します。<br>
     * @return 抜き出したint型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final int substringInt(){
        if(finish)throw new StringIndexRoundedException();
        return Integer.parseInt(substring());
    }
    /**
     * 文字列を抜き出し、long型に変換します。<br>
     * @return 抜き出したlong型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final long substringLong(){
        if(finish)throw new StringIndexRoundedException();
        return Long.parseLong(substring());
    }
    /**
     * 文字列を抜き出し、float型に変換します。<br>
     * @return 抜き出したfloat型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final float substringFloat(){
        if(finish)throw new StringIndexRoundedException();
        return Float.parseFloat(substring());
    }
    /**
     * 文字列を抜き出し、double型に変換します。<br>
     * @return 抜き出したdouble型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final double substringDouble(){
        if(finish)throw new StringIndexRoundedException();
        return Double.parseDouble(substring());
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にあるhref属性を抜き出します。<br>
     * この際、抜き出したhref属性の位置まで開始インデックスと終了インデックスが移動します。
     * @return href属性
     */
    protected final String href(){
        find("href=\"", 6);
        findEnd("\"");
        return substring();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にあるsrc属性を抜き出します。<br>
     * この際、抜き出したsrc属性の位置まで開始インデックスと終了インデックスが移動します。
     * @return src属性
     */
    protected final String src(){
        find("src=\"", 5);
        findEnd("\"");
        return substring();
    }
    /**
     * 検索対象文字列から最初に見つかった指定タグから要素の内容を抜き出します。<br>
     * この際、抜き出したタグの位置まで開始インデックスと終了インデックスが移動します。
     * @param tag タグ名
     * @return 要素の内容
     */
    protected final String tagFirst(String tag){
        if(!text.contains("<" + tag))return null;
        findFirst("<" + tag);
        find(">", 1);
        if(start == tag.length() + 1)return null;
        findEnd("</" + tag + ">");
        return substring();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある指定タグから要素の内容を抜き出します。<br>
     * この際、抜き出したタグの位置まで開始インデックスと終了インデックスが移動します。
     * @param tag タグ名
     * @return 要素の内容
     */
    protected final String tag(String tag){
        if(!text.contains("<" + tag))return null;
        int before = start;
        find("<" + tag);
        find(">", 1);
        if(start == before)return null;
        if(start == tag.length() + 1){
            start = before;
            return null;
        }
        findEnd("</" + tag + ">");
        return substring();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある検索文字列で囲まれた文字列を抜き出します。<br>
     * この際、抜き出した文字列の位置まで開始インデックスと終了インデックスが移動します。
     * @param keyword 検索文字列
     * @return 検索文字列で囲まれた文字列
     */
    protected final String surrounded(String keyword){
        find(keyword, 1);
        findEnd(keyword);
        return substring();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある検索文字列で囲まれた文字列を抜き出し、文字列の左端から指定文字数分削除します。<br>
     * この際、抜き出した文字列の位置まで終了インデックスが移動します。<br>
     * 開始インデックスは抜き出した文字列の位置から削除文字数分進んだ位置に移動します。
     * @param keyword 検索文字列
     * @param deleteLength 文字列削除文字数
     * @return 
     */
    protected final String surrounded(String keyword, int deleteLength){
        find(keyword, deleteLength);
        findEnd(keyword);
        return substring();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置を返します。
     * @param keyword 検索文字列
     * @return 検索文字列を発見した位置
     */
    protected final int indexOf(String keyword){
        int index = text.indexOf(keyword, start);
        if(index == -1){
            finish = true;
        }
        return index;
    }
    /**
     * 検索対象文字列から最後に検索文字列が見つかった位置を返します。
     * @param keyword 検索文字列
     * @return 検索文字列を発見した位置
     */
    protected final int lastIndexOf(String keyword){
        return text.lastIndexOf(keyword);
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置を返します。
     * @param keyword 検索文字列
     * @return 検索文字列を発見した位置
     */
    protected final int firstIndexOf(String keyword){
        return text.indexOf(keyword);
    }
    /**
     * 現在の開始インデックスの位置を返します。
     * @return 開始インデックスの位置
     */
    protected final int getStart(){
        return start;
    }
    /**
     * 現在の終了インデックスの位置を返します。
     * @return 終了インデックスの位置
     */
    protected final int getEnd(){
        return end;
    }
}