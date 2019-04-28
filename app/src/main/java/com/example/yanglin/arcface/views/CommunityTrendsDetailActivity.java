package com.example.yanglin.arcface.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Community;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-4-3.
 */

public class CommunityTrendsDetailActivity extends AppCompatActivity {
    @BindView(R.id.article_detail_title)
    TextView articleTitle;
    @BindView(R.id.article_detail_time)
    TextView articleSendTime;
    @BindView(R.id.article_detail_content)
    TextView articleContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_trends_detail);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        Intent intent = getIntent();
        Community.DataBean.DatasBean article = (Community.DataBean.DatasBean)intent.getSerializableExtra("article");
        articleTitle.setText(article.getTitle());
        articleSendTime.setText(article.getCreated_at().substring(0, 10));
        loadHtmlText(articleContent, article.getContent());
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            CharSequence c= (CharSequence) msg.getData().get("data");
            TextView textView= (TextView) msg.obj;
            textView.setText(c);
        }
    };

    /**
     * 加载html内容
     */
    public void loadHtmlText(final TextView textView, final String html) {
        textView.post(new Runnable() {
            @Override
            public void run() {
                final int screenWidth = textView.getMeasuredWidth();
                final Message msg = Message.obtain();
                msg.obj = textView;
                final Bundle bundle = new Bundle();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Spanned spanned = Html.fromHtml(html, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                InputStream is = null;
                                try {
                                    is = (InputStream) new URL(source.replace("https", "http")).getContent();
                                    Drawable dra = Drawable.createFromStream(is, "src");
                                    int i = dra.getIntrinsicWidth() * 13;
                                    int i1 = dra.getIntrinsicHeight() * 13;
                                    dra.setBounds(0, 0, i, i1);
                                    if (i > screenWidth & i1 != 0) {
                                        float i2 = (float) i / i1;
                                        dra.setBounds(0, 0, screenWidth, (int) (screenWidth / i2));
                                    }
                                    return dra;
                                } catch (MalformedURLException e) {
                                    return null;
                                } catch (IOException e) {
                                    return null;
                                } finally {
                                    try {
                                        if (null != is) {
                                            is.close();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, null);
                        if (null != mHandler) {
                            bundle.putCharSequence("data", spanned);
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);
                        }
                    }
                }).start();
            }
        });
    }

    @OnClick(R.id.trends_detail_back)
    void back() {
        CommunityTrendsDetailActivity.this.finish();
    }
}
