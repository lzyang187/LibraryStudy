package com.iflytek.librarystudy.jsoup;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

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
import io.reactivex.functions.Consumer;

public class JSoupActivity extends AppCompatActivity {
    private static final String TAG = "JSoupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        //解析一个URL资源获取Document对象
        Observable<Document> observable = JSoupUtil.connect("https://lzyang187.github.io/sunyue/course/arguement.html");
        observable.subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {
                Log.i(TAG, "title：" + document.title());
                Charset charset = document.charset();
                Log.i(TAG, "charset：" + charset.name());
            }
        });
        //解析一个文件获取Document对象
        File file = new File(Environment.getExternalStorageDirectory(), "tag.html");
        Observable<Document> parse = JSoupUtil.parse(file);
        parse.subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {
                Element body = document.body();
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

                //JsoupXPath
                //JsoupXPath是基于Jsoup的拓展，使用路径的形式解析XML和HTML文档。核心类为JXDocument
                //JsoupXPath的节点对象JXNode不仅可以获取标签节点，还可以获取属性节点
                JXDocument jxDocument = JXDocument.create(document);
                //1、绝对路径语法
                //父元素/子元素/孙元素/...
                List<JXNode> jxNodes = jxDocument.selN("/body/ol");
                for (JXNode jxNode : jxNodes) {
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
                JXNode jxNode = jxNodes.get(0);
                List<JXNode> liJXNode = jxNode.sel("./li");
                for (JXNode node : liJXNode) {
                    Log.i(TAG, "node: " + node.toString());
                }
                //3、全文搜索路径，在全局搜索对应的标签，不需要从根目录开始搜索
                //  //元素 全局搜索元素
                //  //元素/子元素或@元素属性 全局搜索元素后的子路径中找子元素或属性
                JXNode one = jxDocument.selNOne("//ol/li");
                Log.i(TAG, "one: " + one.toString());

                //4、条件筛选语法
                //  //元素[@属性=value] 筛选属性为value值的节点对象
                //  //元素[@属性=value]/text()   //元素[@属性=value]/html()  获取元素为value值的标签体内容对象

            }
        });

    }

}
