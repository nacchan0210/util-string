package tech.narita.util.string;
/**
 * {@link TextPerser}クラスの名称短縮版クラスです。
 * @author <a href="https://narita.tech/" target="_blank">ナリテック</a>
 */
public abstract class TP{
    String text;
    boolean finish;
    int start;
    int end;
    /**
     * 検索対象文字列を指定して初期化します。
     * @param text 検索対象の文字列
     */
    protected TP(String text){
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
    protected final void m(int move){
        start += move;
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void ff(String keyword){
        s(fi(keyword));
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void ff(String keyword, int moreMove){
        s(fi(keyword) + moreMove);
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void f(String keyword){
        s(i(keyword));
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void f(String keyword, int moreMove){
        s(i(keyword) + moreMove);
    }
    /**
     * 開始インデックスを指定の位置に移動します。
     * @param start 開始インデックス
     */
    protected final void s(int start){
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
    protected final void e(String keyword){
        e(i(keyword));
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置へ終了インデックスを移動し、更に追加移動量分だけ終了インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void e(String keyword, int moreMove){
        e(i(keyword) + moreMove);
    }
    /**
     * 終了インデックスを指定の位置に移動します。
     * @param end 終了インデックス
     */
    protected final void e(int end){
        this.end = end;
    }
    /**
     * 検索対象文字列から最後に検索文字列が見つかった位置へ開始インデックスを移動します。
     * @param keyword 検索文字列
     */
    protected final void fl(String keyword){
        s(li(keyword));
    }
    /**
     * 検索対象文字列から最後に検索文字列が見つかった位置へ開始インデックスを移動し、更に追加移動量分だけ開始インデックスを移動します。
     * @param keyword 検索文字列
     * @param moreMove 追加移動量
     */
    protected final void fl(String keyword, int moreMove){
        s(li(keyword) + moreMove);
    }
    /**
     * 開始インデックスを現在の終了インデックスまで移動します。
     */
    protected final void ste(){
        start = end;
    }
    /**
     * 終了インデックスを検索対象文字列の最後の文字まで移動します。
     */
    protected final void etl(){
        end = text.length() - 1;
        finish = true;
    }
    /**
     * 検索対象文字列からn文字目の文字を返します。
     * @param index 検索するインデックス
     * @return 文字
     */
    protected final char c(int index){
        return text.charAt(start + index);
    }
    /**
     * 検索対象文字列に検索文字列が含まれる場合はtrueを、含まれない場合はfalseを返します。
     * @param keyword 検索文字列
     * @return 検索対象文字列に検索文字列が含まれる場合はtrue(含まれない場合はfalse)
     */
    protected final boolean con(String keyword){
        return text.contains(keyword);
    }
    /**
     * 現在の開始インデックス(この位置)から現在の終了インデックス(この位置のひとつ左)までにある文字列を抜き出します。<br>
     * @return 抜き出した文字列
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final String ss(){
        if(finish)throw new StringIndexRoundedException();
        return text.substring(start, end);
    }
    /**
     * 文字列を抜き出し、int型に変換します。<br>
     * @return 抜き出したint型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final int ssi(){
        if(finish)throw new StringIndexRoundedException();
        return Integer.parseInt(ss());
    }
    /**
     * 文字列を抜き出し、long型に変換します。<br>
     * @return 抜き出したlong型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final long ssl(){
        if(finish)throw new StringIndexRoundedException();
        return Long.parseLong(ss());
    }
    /**
     * 文字列を抜き出し、float型に変換します。<br>
     * @return 抜き出したfloat型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final float ssf(){
        if(finish)throw new StringIndexRoundedException();
        return Float.parseFloat(ss());
    }
    /**
     * 文字列を抜き出し、double型に変換します。<br>
     * @return 抜き出したdouble型整数
     * @throws java.lang.StringIndexOutOfBoundsException 終了インデックスが開始インデックスより小さい場合に発生します。
     * @throws StringIndexRoundedException 開始インデックスが既に一周していると発生します。
     */
    protected final double ssd(){
        if(finish)throw new StringIndexRoundedException();
        return Double.parseDouble(ss());
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にあるhref属性を抜き出します。<br>
     * この際、抜き出したhref属性の位置まで開始インデックスと終了インデックスが移動します。
     * @return href属性
     */
    protected final String h(){
        f("href=\"", 6);
        e("\"");
        return ss();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にあるsrc属性を抜き出します。<br>
     * この際、抜き出したsrc属性の位置まで開始インデックスと終了インデックスが移動します。
     * @return src属性
     */
    protected final String src(){
        f("src=\"", 5);
        e("\"");
        return ss();
    }
    /**
     * 検索対象文字列から最初に見つかった指定タグから要素の内容を抜き出します。<br>
     * この際、抜き出したタグの位置まで開始インデックスと終了インデックスが移動します。
     * @param tag タグ名
     * @return 要素の内容
     */
    protected final String tf(String tag){
        if(!text.contains("<" + tag))return null;
        ff("<" + tag);
        f(">", 1);
        if(start == tag.length() + 1)return null;
        e("</" + tag + ">");
        return ss();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある指定タグから要素の内容を抜き出します。<br>
     * この際、抜き出したタグの位置まで開始インデックスと終了インデックスが移動します。
     * @param tag タグ名
     * @return 要素の内容
     */
    protected final String t(String tag){
        if(!text.contains("<" + tag))return null;
        int before = start;
        f("<" + tag);
        f(">", 1);
        if(start == before)return null;
        if(start == tag.length() + 1){
            start = before;
            return null;
        }
        e("</" + tag + ">");
        return ss();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある検索文字列で囲まれた文字列を抜き出します。<br>
     * この際、抜き出した文字列の位置まで開始インデックスと終了インデックスが移動します。
     * @param keyword 検索文字列
     * @return 検索文字列で囲まれた文字列
     */
    protected final String sur(String keyword){
        f(keyword, 1);
        e(keyword);
        return ss();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初にある検索文字列で囲まれた文字列を抜き出し、文字列の左端から指定文字数分削除します。<br>
     * この際、抜き出した文字列の位置まで終了インデックスが移動します。<br>
     * 開始インデックスは抜き出した文字列の位置から削除文字数分進んだ位置に移動します。
     * @param keyword 検索文字列
     * @param deleteLength 文字列削除文字数
     * @return 
     */
    protected final String sur(String keyword, int deleteLength){
        f(keyword, deleteLength);
        e(keyword);
        return ss();
    }
    /**
     * 現在の開始インデックスより後でかつ、最初に検索文字列が見つかった位置を返します。
     * @param keyword 検索文字列
     * @return 検索文字列を発見した位置
     */
    protected final int i(String keyword){
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
    protected final int li(String keyword){
        return text.lastIndexOf(keyword);
    }
    /**
     * 検索対象文字列から最初に検索文字列が見つかった位置を返します。
     * @param keyword 検索文字列
     * @return 検索文字列を発見した位置
     */
    protected final int fi(String keyword){
        return text.indexOf(keyword);
    }
    /**
     * 現在の開始インデックスの位置を返します。
     * @return 開始インデックスの位置
     */
    protected final int gs(){
        return start;
    }
    /**
     * 現在の終了インデックスの位置を返します。
     * @return 終了インデックスの位置
     */
    protected final int ge(){
        return end;
    }
}