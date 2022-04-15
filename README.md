# util-string

## 概要 -Overview-

Javaの文字列操作ライブラリです。
- 文字列操作系の静的メソッドを用意したStringUtilクラス
- XMLやHTML以外のあらゆる文書から柔軟にキーワードを抽出できるTextPerserクラス
- 文書中で繰り返される単語を起点にキーワードを反復抽出できるTextLoopPerserクラス
- 横着な人向け！TextPerser、TextLoopPerserの命名短縮クラス、TP、TLP

This is Java string utility class library.
- StringUtil class is collection of static method.
- TextPerser class can extract keywords flexibly from any document other than XML or HTML.
- TextLoopPerser class can extract keywords starting from words repeated in the document.
- It's recommend the lazy person! TP, TLP class! Method name is very short!

## 利用要件 -Requirement-

言語：Java
バージョン：17
依存モジュール：なし

Language:Java
version:17
dependent module:none

## デモ -Demo-

XMLでもHTMLでもJSONでもない商品一覧文書から正しいJANコードの商品情報だけ抽出して表示するデモ。

This demonstration extracts and displays only product information that is correct JAN code from the product list documents that are neither XML nor HTML nor JSON.

```
import static tech.narita.util.string.StringUtil.isJan;
import tech.narita.util.string.TextLoopPerser;
public class Demo{
    public static void main(String[] args){
        //2はJANのチェックディジットが合わない、4はJANがない、5はJANが桁数違い
        String document = """
                          ItemCode: 1, ItemName: 'Javapple', Price: 298, JAN: 0123456789012
                          ItemCode: 2, ItemName: 'Lispudding', Price: 9800, JAN: 19283742
                          ItemCode: 3, ItemName: 'Ruby cherry', Price: 450, JAN: 1123581321346
                          ItemCode: 4, ItemName: 'Nullpoin C', Price: 120
                          ItemCode: 5, ItemName: 'Basic pasta', Price: 700, JAN: 987654321987
                          ItemCode: 6, ItemName: 'Meatpython', Price: 1280, JAN: 08725257
                          """;
                          
        //"ItemCode"を区切り文字列として、6つの文章に分割し、ループしてそれぞれの文章から抽出処理をしている。
        new TextLoopPerser(document, "ItemCode") {
            @Override
            public void perseInLoop(){
                //String#substring(start, end)のstartを文字列検索でずらしている。
                find(":", 2);
                //String#substring(start, end)のendを文字列検索でずらしている。
                findEnd(",");
                int itemCode = substringInt();
                
                find("ItemName");
                String itemName = surrounded("'");
                
                find("Price");
                find(":", 2);
                findEnd(",");
                
                //Nullpoin Cの値段の後に , がないので処理を終わらせるべきか？をスーパークラスに判断してもらい、終わらせるべきと判断されればreturnされる。
                if(isFinish())return;
                int price = substringInt();
                
                find("JAN");
                find(":", 2);
                endToLast();
                String jan = substring();
                
                //JANの桁数が8桁または13桁か？チェックディジットが正しいかを判定。
                if(isJan(jan)){
                    System.out.println("ItemCode=" + itemCode + ", ItemName=" + itemName + ", Price=" + price + ", JAN=" + jan);
                }else{
                    System.out.println("Incorrect or not exists JAN code");
                }
            }
        };
    }
}
```

```
Command line

ItemCode=1, ItemName=Javapple, Price=298, JAN=0123456789012
Incorrect JAN code
ItemCode=3, ItemName=Ruby cherry, Price=450, JAN=1123581321346
Incorrect JAN code
ItemCode=6, ItemName=Meatpython, Price=1280, JAN=08725257
```

## 今後のリリース予定 -Future Releases-

StringUtilクラスのメソッドを増やす。

Increase the StringUtil classes method.

## 作成者サイト -Author's Web Site-

ナリテック -Naritech-
https://narita.tech/

## ライセンス -Licence-

MIT
