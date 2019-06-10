package com.example.threeproductevaluation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    private ImageView imageView_return;
    private ScrollView scrollView;
    private TextView textView_article_content , textView_article_content_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initWidget();
    }

    private void initWidget() {

        imageView_return = findViewById(R.id.imageView_article_return);
        imageView_return.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        scrollView = findViewById(R.id.scrollView_article);
        textView_article_content = findViewById(R.id.textView_article_content);
        textView_article_content_title = findViewById(R.id.textView_article_content_title);

        StringBuilder  stringBuilder = new StringBuilder();

        Intent intent = getIntent();
        String originalActivity = intent.getStringExtra("OriginalActivity");
        switch (originalActivity){
            case "Follow":
                textView_article_content_title.setText("精美巧致 联想ideacentre AIO 520C一体机评测");
                stringBuilder.append("  一体电脑作为PC产品线的组成部分在近年来已经形成比较成熟的市场，联想作为全球PC行业的领军者之一，在一体电脑领域有着颇高的建树，ideacentre产品线有自己的一体电脑系列产品，我们今天要评测的这台联想ideacentre AIO 520C就是其中的一个新成员。\n");
                stringBuilder.append("  联想ideacentre AIO 520C这款产品的外观设计很独特，与市面常见的一体电脑产品相比颇具个性化，我们一起来看一下.\n");
                stringBuilder.append("  ");
                stringBuilder.append("首先是产品的正面，可以看到联想ideacentre AIO 520C的屏幕采用了一体隐藏式设计，在熄屏状态下整个屏幕面是一体的，观感出众。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("再来看一看背面，联想ideacentre AIO 520C的背面同时采用了磨砂与亮面处理，通过材质上的区别提升了整机的格调。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("相信大家的目光已经被这款产品独特的支架吸引了，而这也是联想ideacentre AIO 520C的外观设计最吸引人的地方了，黑色的异形支架搭配尾部亮银色联想LOGO，质感尤为突出。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("凑近来看一看联想ideacentre AIO 520C的后部接口，可以看到这款产品提供了一个USB3.0接口，两个USB2.0接口，一个HDMI接口，一个网线接口，最左侧采用了联想标准的电源接口。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("除了后部之外，机身的左侧也额外提供了一个USB3.0接口，一个读卡器以及一个3.5mm耳机麦克风二合一接口，总的来说联想ideacentre AIO 520C接口数量和种类能够满足用户日常的使用需要。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("联想ideacentre AIO 520C拥有-5度至+25度的俯仰调整功能，能够良好适应不同的使用环境，调整好角度后较强的阻尼能够保证显示器不会出现松动的问题。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("评测总结：联想ideacentre AIO 520C是一款定位非常明确的产品，它的外观设计具有时尚现代感，硬件规格对于家庭和办公用户来说也足够使用，而一体电脑对于使用场景良好的融入能力也使得联想ideacentre AIO 520C的受众更为广泛，结合其出色的性价比，自然是一款优秀的一体电脑产品。");
                stringBuilder.append("\n");

                break;
            case "Recommend":
                textView_article_content_title.setText("ThinkPad X390 4G版全球首测：全时在线商务利器");

                stringBuilder.append("  ");
                stringBuilder.append("X240之后，我已经很多年不再关注ThinkPad X系列了。现如今把我又拉回来的，是这台ThinkPad X390 4G版。由于中间的断层，我并不知道X200系列已经没有了，一搜之下才发现，原来X280是最后一代X200系列。自那之后，X300正式登上舞台。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("像我一样年纪的80后，有不少都对ThinkPad有着特殊的情怀，过手的产品也不只一台，所以在看到ThinkPad X390 4G版的时候，一种熟悉感油然而生，而这些大部分来源于它时刻保持的一种专业商务范儿。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("既然这款产品型号为ThinkPad X390 4G版，那么自然而然它就支持4G 联网模块，通过SIM卡可以实现全时在线，这使得机器的适用场景更加广泛，在场地经常变化的移动办公场景下，也不必担心没有WiFi的情况下无法联网的状况发生，这种特性显然契合了新时代商务人士时刻在线联网办公的习惯和需求。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("首次使用ThinkPad X390 4G版的LTE功能需要在WiFi环境下下载微软商店里的“移动套餐”应用，进入之后需要绑定自己的联想账号，并激活相应套餐。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("A4纸大小 便携性出色");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("屏幕素质出色 视觉体验舒适;安全保障独树一帜");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("配置强劲 小巧机身下系统程序流畅运行");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("散热效率不错 续航能力极佳");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("优点：\n" +
                        "\n" +
                        "续航能力出众，电池无虚标\n" +
                        "\n" +
                        "处理器性能出色，散热效率不错\n" +
                        "\n" +
                        "支持4G LTE功能，可实现全时在线\n" +
                        "\n" +
                        "键盘触控板手感舒适，操控精准\n" +
                        "\n" +
                        "便携性出色\n" +
                        "\n" +
                        "不足：\n" +
                        "\n" +
                        "表面类肤涂层易沾手印，且不易清洁\n" +
                        "\n" +
                        "侧面出风口在右侧");
                stringBuilder.append("\n");
                stringBuilder.append("结语：\n" +
                        "总体来说，ThinkPad X390 4G版是一款优点非常多、且突出的产品，作为一款专业向的商务办公本，具备4G LTE功能使其能够随时随地在线，全时互联办公；性能方面，充分发挥出处理器性能的同时，保证了散热效率；拥有极佳的续航能力、良好的操控手感以及出色的便携性，多个维度证明这是一款很不错的便携商务办公本。");
                break;
            case "Rankinglist":
                textView_article_content_title.setText("联想Z6 Pro对比小米9 神仙打架 巅峰对决");
                stringBuilder.append("  ");
                stringBuilder.append("两款旗舰均高颜值 联想Z6 Pro用细节定下成败\n" +
                        "\n" +
                        "先来看看小米9。这款在2019年2月20日发布的旗舰机，被官方称之为小米9是迄今为止小米颜值最高的一款手机。（而很多网友则感觉，小米9的后壳山寨感较浓，更喜欢小米MIX 3故宫特别版的后背）。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("通过机身背部约1.7亿根纳米光栅的衍射，随着光线的流转变幻，360°每个角度都可以呈现出彩虹般的斑驳，这就是全息幻彩色设计。\n" +
                        "\n" +
                        "实际上，并非小米9全系类带有这样的设计，仅仅只有全息幻彩系列的配色拥有，而深空灰则不带有这样的设计。\n" +
                        "\n" +
                        "正面水滴的形状，小米9有些类似圆头水柱状，显得比较刚硬。而正式版发售的系统补丁中，将小米9凹陷部分的过渡衔接变得更加自然了些，但是在强光照射的环境下，也会出现穿帮的现象。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("小米官方称小米9是3999元以下最窄的下巴，确实，要比联想Z6 Pro显得更窄，不过额头方面，显然联想Z6 Pro更占优势。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("联想Z6 Pro的摄像头要比小米9极其凸起的设计，显得更加平整，美感上要更胜一筹。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("拍照：四摄和三摄之间的强势对决\n" +
                        "\n" +
                        "联想Z6 Pro后置搭载4800万像素+1600万像素超广角镜头+800万像素长焦+200万夜拍镜头，光圈分别是f/1.8、f/2.2、f/2.4、f/1.8，前置为一颗3200万像素自拍镜头，拥有微距、广角、运动、美体等功能，并且有ToF镜头参与对焦辅助。此外还支持2倍光学变焦和8倍数字变焦，OIS八向光学防抖、六轴陀螺仪防抖等。\n" +
                        "\n" +
                        "素质之高，令人瞠目结舌。\n" +
                        "\n" +
                        "小米9这边，也是搭载了4800万像素主摄像头+1200万像素的人像镜头+1600万像素的超广角及微距镜头三摄设计，光圈分别是f/1.75、f/2.2、f/2.2，前置拥有一个2000万美颜相机，支持智能瘦脸、瘦身、瘦腿和微距拍摄等功能。并且支持2倍及10倍数字变焦。\n" +
                        "\n" +
                        "总的来说，联想Z6 Pro多了一颗200万夜拍镜头，ToF辅助镜头；前置上，Z6 Pro以3200万像素占据优势。在光圈及变焦方面，两者差异不是很明显。");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("");
                stringBuilder.append("\n");
                stringBuilder.append("  ");
                stringBuilder.append("写到最后\n" +
                        "\n" +
                        "通过综合能力加上硬件的实力对抗，联想Z6 Pro似乎粉碎了小米9主打性价比的卖点。可以感受到，联想Z6 Pro在配置上要略强于小米9。\n" +
                        "\n" +
                        "售价上，联想Z6 Pro 6GB+128GB售价为2899元；8GB+128GB为2999元；8GB+256GB为3799元；12GB+512GB为4999元；而小米9的6+128GB版为2999元，8+128GB版为3299元，8GB+256GB版为3699元。");
                stringBuilder.append("\n");

                break;
        }
        textView_article_content.setText(stringBuilder.toString());
    }
}
