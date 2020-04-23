package com.iflytek.librarystudy.jsoup;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.iflytek.librarystudy.R;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class JSoupActivity extends AppCompatActivity {
    private static final String TAG = "JSoupActivity";
    private CompositeDisposable compositeDisposable;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        webView = findViewById(R.id.webview);
        compositeDisposable = new CompositeDisposable();

        //解析一个URL资源获取Document对象
        String url = "https://lzyang187.github.io/sunyue/course/arguement.html";
        Observable<Document> urlConnect = JSoupUtil.connect(url);
        compositeDisposable.add(urlConnect.subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {

            }
        }));

        //解析一个文件获取Document对象
        File file = new File(Environment.getExternalStorageDirectory(), "tag.html");
        Uri uri = Uri.fromFile(file);
        webView.loadUrl(uri.toString());
        Observable<Document> fileParse = JSoupUtil.parse(file);
        compositeDisposable.add(fileParse.subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {
                Log.i(TAG, "title：" + document.title());
                Charset charset = document.charset();
                Log.i(TAG, "charset：" + charset.name());
                Element body = document.body();
                /*解析数据**/

                //使用dom方法
                //getElementById
                Element inputtext = body.getElementById("inputtext");
                Log.i(TAG, "inputtext text: " + inputtext.text());
                Log.i(TAG, "inputtext tagName: " + inputtext.tagName());
                Log.i(TAG, "inputtext className: " + inputtext.className());
                //getElementsByClass
                Elements btns = body.getElementsByClass("btn");
                Element btn = btns.get(0);
                //getElementsByTag
                Elements as = btn.getElementsByTag("a");
                Element a = as.get(0);
                Log.i(TAG, "a text: " + a.text());

                //使用选择语句
                Elements selectHrefs = body.select("a[href]");//a with href
                Log.i(TAG, "select herf text: " + selectHrefs.get(1).text());
                Element selectImg = body.select("img[src$=.gif]").first();
                Log.i(TAG, "select: selectImg src = " + selectImg.attr("src"));

                /*修改数据**/

                //设置属性值
                selectImg.attr("src", "hhhhh.png");
                Log.i(TAG, "修改后: " + body.getElementsByTag("img").first().attr("src"));
                Log.i(TAG, "addClass: " + selectImg.addClass("newClass").className());
                Log.i(TAG, "removeClass: " + selectImg.removeClass("inexist").className());

                //移除属性
                body.select("img").removeAttr("onclick");//删除所有图片标签的点击属性

                //设置text内容
                selectHrefs.get(1).text("直接text");
                Log.i(TAG, "text(): " + selectHrefs.get(1).text());
                selectHrefs.get(1).append("加到后面的text");
                Log.i(TAG, "append text(): " + selectHrefs.get(1).text());
                selectHrefs.prepend("加到前面的text");
                Log.i(TAG, "prepend text(): " + selectHrefs.get(1).text());

                //获取修改后的html文档
                Log.i(TAG, "修改后的html: \n" + document.html());

                //JsoupXPath
                //JsoupXPath是基于Jsoup的拓展，使用路径的形式解析XML和HTML文档。核心类为JXDocument
                //JsoupXPath的节点对象JXNode不仅可以获取标签节点，还可以获取属性节点
                JXDocument jxDocument = JXDocument.create(document);
                //1、绝对路径语法
                //父元素/子元素/孙元素/...
                List<JXNode> absJxNodes = jxDocument.selN("/body/ol");
                for (JXNode jxNode : absJxNodes) {
                    Log.i(TAG, "jxNode: " + jxNode.toString());
                    Element element = jxNode.asElement();
                    Elements children = element.children();
                    for (Element child : children) {
                        Log.i(TAG, "child.text: " + child.text());
                        Log.i(TAG, "child.tagName: " + child.tagName());
                    }
                }
                //2.相对路径语法，已有JXNode节点对象的情况下，通过此节点再往下寻找此对象内的其他节点
                //./子元素/孙元素
                JXNode jxNode = absJxNodes.get(0);
                List<JXNode> relativeLiJXNode = jxNode.sel("./li");
                for (JXNode node : relativeLiJXNode) {
                    Log.i(TAG, "node: " + node.toString());
                }
                //3、全文搜索路径，在全局搜索对应的标签，不需要从根目录开始搜索
                //  //元素 全局搜索元素
                //  //元素/子元素或@元素属性 全局搜索元素后的子路径中找子元素或属性
                JXNode one = jxDocument.selNOne("//ol/li");
                Log.i(TAG, "one: " + one.toString());

                //4、条件筛选语法，前面部分为筛选的条件，后面可以加上操作的动作
                //  //元素[@属性=value] 筛选属性为value值的节点对象
                //  //元素[@属性=value]/text()   //元素[@属性=value]/html()  获取元素为value值的标签体内容对象
                List<JXNode> condNodes = jxDocument.selN("//input[@type=checkbox]");

            }
        }));

    }

}
