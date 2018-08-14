package com.tudoujf.activity.discover;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.other.LoginActivity;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.GoodsDetailsBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.BoderTextView;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ImageGlideUtils;
import com.tudoujf.utils.LUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             GoodsDetailsActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2018/1/8
 * description：     商品详情activity
 * history：
 * *==================================================================
 */
public class GoodsDetailsActivity extends BaseActivity {
    @BindView(R.id.mtb_act_godsdetails)
    MTopBarView mtb;
    @BindView(R.id.iv_act_godsdetails)
    ImageView iv;
    @BindView(R.id.iv_act_goodsdetails_leftarrow)
    ImageView ivLeft;
    @BindView(R.id.iv_act_goodsdetails_rightarrow)
    ImageView ivRight;
    @BindView(R.id.hsv_act_godsdetails)
    HorizontalScrollView hsv;
    @BindView(R.id.tv_act_godsdetails_nowexchange)
    TextView tvNowExchange;
    @BindView(R.id.tv_act_goodsdetails_title)
    TextView tvTitle;
    @BindView(R.id.tv_act_goodsdetails_content)
    TextView tvContent;
    @BindView(R.id.tv_act_goodsdetails_bewrite)
    TextView tvBewrite;
    @BindView(R.id.tv_act_goodsdetails_num)
    TextView tvNum;
    @BindView(R.id.tv_act_goodsdetails_needintegral)
    TextView tvNeedIntegral;
    @BindView(R.id.tv_act_goodsdetails_myintegral)
    TextView tvMyintegral;
    @BindView(R.id.tv_act_goodsdetails_reduce)
    TextView tvReduce;
    @BindView(R.id.tv_act_goodsdetails_number)
    TextView tvNumber;
    @BindView(R.id.tv_act_goodsdetails_add)
    TextView tvAdd;
    @BindView(R.id.tv_act_goodsdetails_stock)
    TextView tvStock;
    @BindView(R.id.btv_act_godsdetails_btv1)
    BoderTextView btvBtv1;
    @BindView(R.id.btv_act_godsdetails_btv2)
    BoderTextView btvBtv2;
    @BindView(R.id.btv_act_godsdetails_btv3)
    BoderTextView btvBtv3;
    @BindView(R.id.ll_act_goodsdetails_imagesgroup)
    LinearLayout llImagesGroup;
    private String id = "";
    private GoodsDetailsBean bean;
    private List<BoderTextView> list;

    private List<LinearLayout> linearList;

    //当前显示图片的位置
    private int position = 0;
    private boolean flag = true;
    private String integral;
    private AlertDialog dialog;

    @Override
    public int getLayoutResId() {
        return R.layout.act_godsdetails;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btv_act_godsdetails_btv1:
                if (bean != null) {
                    tvContent.setText(bean.getName());
                }
                setBtnStyle(0);
                break;
            case R.id.btv_act_godsdetails_btv2:
                if (bean != null) {
                    tvContent.setText(bean.getDetail());
                }
                setBtnStyle(1);

                break;
            case R.id.btv_act_godsdetails_btv3:
                if (bean != null) {
                    tvContent.setText(bean.getUse_description());
                }
                setBtnStyle(2);
                break;
            case R.id.iv_act_goodsdetails_leftarrow:
                if (position == 0) {
                    position = linearList.size() - 1;
                } else {
                    position = position - 1;
                }
                if (bean != null && bean.getImages() != null) {
                    ImageGlideUtils.loadImage(iv, bean.getImages().get(position));
                }
                setBorderColor();

                break;
            case R.id.iv_act_goodsdetails_rightarrow:
                if (position == linearList.size() - 1) {
                    position = 0;
                } else {
                    position = position + 1;
                }
                if (bean != null && bean.getImages() != null) {
                    ImageGlideUtils.loadImage(iv, bean.getImages().get(position));
                }
                setBorderColor();
                break;
            case R.id.tv_act_goodsdetails_add:
                if (bean != null && StringUtils.string2Integer(tvNumber.getText().toString()) <
                        StringUtils.string2Integer(bean.getStock())) {
                    tvNumber.setText(("" + (StringUtils.string2Integer(tvNumber.getText()
                            .toString()) + 1)));
                    flag = true;
                } else {
                    if (flag) {
                        ToastUtils.showToast(GoodsDetailsActivity.this, R.string
                                .yichaochukucunshuliang);
                        flag = false;
                    }
                }
                break;
            case R.id.tv_act_goodsdetails_reduce:
                if (StringUtils.string2Integer(tvNumber.getText().toString()) > 1) {
                    tvNumber.setText(("" + (StringUtils.string2Integer(tvNumber.getText()
                            .toString()) - 1)));
                }
                break;
            case R.id.tv_act_godsdetails_nowexchange://
                if (UserConfig.getInstance().getLoginToken(this).equals("")) {
                    openActivity(LoginActivity.class);
                } else {
                    if (StringUtils.string2Integer(tvStock.getText().toString()) < 1) {
                        ToastUtils.showToast(GoodsDetailsActivity.this, R.string.kucunshuliangbuzu);
                    } else if (StringUtils.string2Integer(bean.getCredit()) < 1) {
                        ToastUtils.showToast(GoodsDetailsActivity.this, R.string.huodongshangpin);
                    } else if (StringUtils.string2Integer(integral) >
                            StringUtils.string2Integer(tvNumber.getText().toString()) *
                                    StringUtils.string2Integer(bean.getCredit())) {
                        Intent intent = new Intent(this, GoodsExchangeActivity.class);
                        intent.putExtra("number", tvNumber.getText().toString());
                        intent.putExtra("url", bean.getImages().get(0));
                        intent.putExtra("needintegral", bean.getCredit());
                        intent.putExtra("myintegral", integral);
                        intent.putExtra("lipinhao", bean.getNum());
                        startActivity(intent);
                    } else {
                        if (dialog == null) {
                            dialog = DialogUtils.showIntegralInsufficient(this);
                        } else {
                            dialog.show();
                        }
                    }
                }

                break;
//                 case R.id.:break;
        }
    }


    @Override
    public void initDataFromIntent() {
        list = new ArrayList<>();
        linearList = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        integral = getIntent().getStringExtra("integral");
    }

    @Override
    public void initView() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtb.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtb.setLayoutParams(params);

        btvBtv1.setDrawBoder(true);
        tvMyintegral.setText(integral);
        list.add(btvBtv1);
        list.add(btvBtv2);
        list.add(btvBtv3);
    }

    @Override
    public void initListener() {

        mtb.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        btvBtv1.setOnClickListener(this);
        btvBtv2.setOnClickListener(this);
        btvBtv3.setOnClickListener(this);

        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        tvAdd.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        tvNowExchange.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("id", id);
        HttpMethods.getInstance().POST(this, Constants.GOODS_DETAILS, map, "info", new
                StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissPDialog();
                        String result = StringUtils.getDecodeString(response.body());
                        LUtils.e(GoodsDetailsActivity.class, "logflag-积分商城商品详情接口请求返回数据--" + result);
                        BaseBean baseBean = ParseJson.getJsonResult(response.body(), new
                                        TypeToken<GoodsDetailsBean>() {
                                        }.getType()
                                , GoodsDetailsBean.class, GoodsDetailsActivity.this);
                        if (baseBean != null) {
                            bean = (GoodsDetailsBean) baseBean;
                            LoadInternetDataToUi();
                        } else {
                            ToastUtils.showToast(GoodsDetailsActivity.this, R.string
                                    .shujujiazaichucuo);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        dismissPDialog();
                        ToastUtils.showToast(GoodsDetailsActivity.this, R.string
                                .huoqushangpinxiangqingshibai);
                        super.onError(response);
                    }
                });
    }

    /**
     *
     “我进入的是黄雪的梦境，可自己为什么会出现在这个房间里？”多亏之前接受了黄岚的委托，根据她的资料，
     黄伯元和黄雪从未入住过类似的房间。

     在黄伯元未发迹之前，他们住不起这样的屋子，等黄伯元成为乾鼎‘药’业高层后，这样的房子又配不上他的身份，所以我推断这间屋子的主人和黄家没有太大的关联。

     “桐桑符中藏有厉鬼‘阴’邪之物，这间房子会不会是那些脏东西的幻象？就如同鬼打墙那样。”我在屋子里自言自语，耳朵一动，忽然听到客厅传来“咚咚”的拍‘门’声。

     “樱子？”小心谨慎，我手持一张镇压符来到‘门’口，房‘门’并没有上锁，只是用一根铁链挂住‘门’栓。

     等我来到这里时，敲‘门’声忽然停止。

     “会是谁呢？”要想开‘门’非常简单，只需要将铁链从‘门’栓上取下就行，幕后黑手似乎并没有囚禁我的打算。

     不过谨慎起见，我还是蹲下身体，趴在地上，透过‘门’缝向外面看去：“樱子穿着一双小巧的白‘色’鞋子，敲‘门’的是不是她，一看便知。”

     走廊里光线太暗看不清楚，我眼睛紧贴着‘门’缝，然后用手机照明。

     在视野清晰的一瞬间，我脸‘色’如纸，心脏重重的跳了一下。

     默默拿开手机，我快速起身，朝后退了几步。

     ‘门’外面站着的不是樱子，那是一张血流满面的惨死者的脸，她倒在‘门’口，刚才我从‘门’缝中正好和她对视。

     一双布满血丝的眼珠子无规律转动，直到我出现，她透过‘门’缝死死的盯上了我。

     “咚咚！”敲‘门’声再次响起……

     “这就是恐怖游戏里经典的开‘门’杀。”我心有余悸的看了一眼这扇未上锁的房‘门’，下意识远离：“正‘门’肯定是无法出去了，就算我用雷符将其灭杀，但难保不会遇到第二只鬼物。”

     回到客厅中央，我开始在屋内寻找线索，对于特异‘性’犯罪来说，任何行为都是具有目的‘性’的，对方无缘无故将我囚禁在这里，应该不仅仅只是为了吓唬我。

     将客厅搜查一遍，并没有太多发现，搬开柜子和沙发，也只是看到了一些孩童随手勾画的涂鸦。

     “图案有怪兽有公主，应该不是出自同一人之手，这屋子里可能住着几个年龄段不同的孩子。”

     蹲下身仔细观看，在墙角最不起眼的地方还有一行用铅笔写的字：“我要嫁给哥哥，我要永远和哥哥生活在一起！”

     “妹妹要嫁给哥哥吗？”我只当是童言无忌，并没有放在心上，把家具搬回原位，开始去其他房间查看。

     来到厨房，餐桌上摆着四副碗筷，左边三副，右边一副。

     有意思的是，盛着腐烂菜肴的盘子全部集中在餐桌左侧，右侧那人只有饭，但是却吃不到菜。

     “这一家应该是四口人，其中三人抱团合伙欺负另外一人，生活在同一个屋檐下，他们为什么要这么做？难道有一个不是亲生的吗？”

     我这不是八卦，而是从细节着手，用发散的思维去推测。

     走出厨房，我进入第一间卧室，屋子‘挺’大，除了一张双人**外，还有一个写字台和一个书柜。

     “七宗罪？”随手拿起写字台上的一本书，这间卧室的主人似乎是专‘门’研究西方文学的，在书架上找到了许多译本和文艺复兴时期的各种原文著作。

     在一些笔记和专业‘性’极强的论文上，我看到了一个名字——郭志明。

     “很多论文最后都盖着某个学院的公章，他难道是大学讲师？”此人文化水平很高，但是的书籍却总让我觉得不对劲，比如手中的这本《七宗罪》，我还在书架上找到了很多诸如《羊脂球》、《刑法与艺术》、《浅论‘生’殖崇拜》等等的书。

     “这可能是一个表面斯文的衣冠**。”原著我虽然看不懂，但郭志明在书里留下了各种‘露’骨的笔记。

     除了书外，我没有碰屋子里的任何东西，眼神扫过**单，散发腥臭的**单和这一屋子的书反差很大，上面还有颜‘色’很淡的斑状物。

     将书放在桌子上，我又看到了一张倒扣的相框。

     拿到眼前，上面正好有四个人，中间站着一位带着眼睛的中年男人，在他左边是一对笑逐颜开的年轻男‘女’，右边则是一个面目模糊不清的瘦小男孩。

     “这一家人有些奇怪。”站在中间的应该是父亲，也就是这第一间卧室的主人，跟我预想的一样，看起来文质彬彬。

     左边的那对男‘女’似乎正处在热恋当中，就算是照相，年轻男人的手也不老实，偷偷黏在‘女’人后腰。

     ‘女’人并没有反抗，眼中‘露’出嗔怪之‘色’，身体却主动靠近男人。

     画面中唯一不和谐的地方在于右边，那个瘦弱男孩孤零零的被排除在外，他干瘦的手无力下垂，掌心和‘露’在外面的膝盖上还有被殴打留下的淤青。

     “参照厨房餐桌上饭菜的奇怪摆放位置，被欺负虐待的应该就是这个面容模糊的瘦小男孩。”想通了一个问题，又有新的问题接踵而至。

     “他们为什么要虐待这个孩子？这张全家福里怎么没有看见母亲的身影？”我坐在书桌前面的椅子上，抬头看着被拉上的窗帘。

     “窗户？如果对方存心要限制我出去，不可能漏掉窗户，我如果拉开窗帘会不会看到极为恐怖的一幕？”做好心理准备，我一手将窗帘拉开。

     出现在面前的既不是逃出生天的通道，也不是吓人的鬼怪，只是两扇用金属的窗户。

     两扇窗户被密码锁锁住，密不透风，看不见外界的场景。

     “有些意思，需要密码才能逃出去吗？”翻看锁头，只有按照顺序输入四个数字或者字母才能开锁。

     “四位数密码看似不困难，但数字0-9再加上26个英文字母一起组合的话，那就会有36的4次方，也就是1679616种不同的情况。”

     一个一个尝试肯定不可能，我还需要在房间里找到其他线索才行。

     托着下巴凝视密码锁，正当我注意力全部集中在窗户上时，我感觉自己的后颈被什么东西轻轻碰了一下。

     “谁！”如此诡异的屋子里任何风吹草动都会让人抓狂，更别说脖子被莫名其妙碰了一下。

     “屋子里有人吗？”回头一看，什么都没有。

     “刚才那绝对不是错觉，有什么东西确实从后面碰到了我的脖子。”我假装转身，没过一会脖子又被碰了一下。

     我扭头看去依旧没有任何发现。

     “奇怪？”我没有死心，拿出‘阴’间秀场的手机对准自己身后，然后转身坐直。

     大约过了十几秒种，脖子再次被碰到，我没有第一时间扭头，而是看向自己的直播间。

     从手机屏幕上能够清楚的看到，有一个上吊而死的男尸就挂在我身后，他双脚下垂，脚尖正一下一下的碰着我的脖子。

     第135章 你猜屋子里有几个人

     汗‘毛’竖起，脖颈上出现一层‘鸡’皮疙瘩，我强迫自己保持冷静，没有转身，而是移动手机摄像头将身后那人的全貌录入屏幕当中。1357924?6810ggggggggggd

     他身穿睡衣，皮肤松弛，年龄大约在四十到五十岁之间。

     屏幕上移，最后定格在男人的脸上，他脸‘色’青紫，因为窒息，脸部轮廓显得异常肿胀。

     “上吊而死？是自杀吗？”上吊这种死法并不少见，对于自杀者来说这是一种不需要多少成本就可以完成的廉价死亡方式。

     仔细观察男人的面部表情，除了痛苦之外，他整张脸肌‘肉’痉挛，蕴含着深深的恐惧。

     “不对，如果简单定义为自杀的话，他临死前应该不会‘露’出如此惊恐的表情，他是被迫上吊而死的，当然，也有可能是他临死时看到了什么非常恐怖的东西，以致于大脑中的惊恐超过了死亡本身带来的痛苦。”

     我双眼紧盯着屏幕，男尸在身后有规律摇晃，这是一幕极为诡异的场景。

     我能清晰感知到脖颈被一下一下的触碰，就在我犹豫要不要回头时，屏幕中双眼圆凸，几乎要把眼珠子瞪出眼眶的男尸忽然看了我一眼。

     居高临下，带着一丝怨恨和痛苦转动眼珠，他的眼神从我的身上划过，落在书架倒数第三层。

     我被上吊男尸的奇怪反应吓了一跳，差点扔掉手机。

     从座位上站起，我深吸一口气猛然转身，然而身后依旧一切正常，什么都没有。

     “‘阴’间秀场手机能够看见常人看不见的脏东西，这个挂在我身后的男尸应该是冤死的亡魂。”

     书房中间挂着一个吊死鬼，看他的模样正是照片里文质彬彬的父亲。

     “父亲死在了自己的房间里，其他几个屋子是不是也有类似的鬼魂存在。”我又点燃一根烟，老实说刚才那一下确实把我吓得够呛，此时点烟的手还有些颤抖。

     “刚才尸体的眼珠子转动了一下，他似乎是在提醒我注意某一个地方。”脑中想起吊死鬼目光停留的地方，我站在书架旁边，倒数第三层摆着一个钟表，上面只有两根指针，显示的时间是一点十分。

     “钟表？”我摇了摇头，先将第三层的书籍全部拿出放在地上。

     “他想给我什么提示？”随手翻开一本书，上面全是用红‘色’水笔书写的“对不起”三个字，密密麻麻看起来颇为渗人。

     大致翻了几本，我很快有了新的发现。

     在一篇名为苦痛信仰的读书随感中，我找到了吊死鬼父亲关于自己生活的只言片语。

     “生活于愿望之中而没有希望，是人生最大的悲哀。”

     “关于爱，我或许能够做出更深的定义，真正的爱是无需顾忌道德和人‘性’的。”

     乍一看这似乎是一篇‘逼’格极高的学术论文，但往后看差点没让我吐出来。

     其后以观察记录的方式，讲述了这位西方文学教授和七位‘女’学生之间不得不说的故事，更‘精’彩的是在后面，他和七位‘女’学生热火朝天，他的妻子也没闲着，常常邀请他的男学生到家里做客。

     这些东西无法细说，等到他的妻子生下第三个孩子后，两人便结束了这段荒唐的婚姻。

     将这篇随感装在兜里，我没有找到其他有价值的线索，离开书房进入旁边的一间卧室。

     这间卧室只有书房一半大小，除了一张堆满课本放着闹钟的书桌外，只有一张分为上下铺的组合**。

     我看了看墙后面的足球和两双破旧的球鞋：“这间卧室里住的应该是那两个男孩，他们年龄相差五六岁，又是住在一起，难免会产生摩擦，难道全家福上瘦小男孩身上的伤就是他哥哥打的？”

     翻看书桌上的课本和作业本，干干净净好像新的一样，拉开‘抽’屉，里面也都是男生常玩的小东西。

     “这间屋子除了脏‘乱’差似乎并没有什么特别的地方。”我不想遗漏任何线索，检查完书桌又看向**铺，组合**下宽上窄，搭着蚊帐，站在外面向里看去，朦朦胧胧什么都看不清楚。

     下铺睡着的应该是哥哥，**头还摆着本厚厚的高考参考资料。

     “一个连课本都没有翻过的人会去买参考资料？”我觉得有些反常，把参考书拿到手中，一翻开里面竟然还夹着一本薄薄的小册子。

     “这是什么？”小册子的封面鲜‘艳’无比，我看了以后都觉得面红耳赤：“成.人杂志？果然是有其父必有其子啊。”

     随手将小册子塞进口袋，这种关键‘性’证据必须要保存。

     下铺被子褥子‘乱’七八糟，胡‘乱’堆在一起，上铺却正好相反，**单没有一丝褶皱，被子叠的规规矩矩，好像刚做好的豆腐块。

     “两兄弟‘性’格完全相反，弟弟应该是个内向、认真，有点小自卑的人。”爬到上铺，掀开枕头和**单，我找到了一个棕‘色’日记本。

     “又是这种解谜恐怖游戏里的经典桥段。”拉开蚊帐，我坐在**板上，开始一个孩子在畸形家庭中的苦难生活。

     “在我记忆之中自己只见过母亲两次，一次是父亲酒后差点将我打死，哥哥打电话给她，让她将我这个‘私’生的杂种带走。还有一次是我偷偷从家里逃走，在冬天穿着单薄的衣服走了四个小时到相邻的城市去找她，那天晚上很冷，我在她和另外一个男人的公寓‘门’口蹲了一晚上，她没有让我进‘门’，只是拉开一条‘门’缝对我说‘快滚’。”

     “我不清楚自己活着的意义，也许我的存在就是为了帮这个‘女’人赎罪吧，她背叛了父亲，我却像个寄生虫般靠父亲养活。”

     “我的哥哥高大帅气，擅长足球，有很多‘女’孩子都喜欢他，这其中就包括我的姐姐。”

     “哥哥很喜欢在父亲熟睡以后，偷偷溜出房间，进入姐姐的屋子。”

     “我不清楚他们背着父亲在做什么，但总感觉是一件不好的事情。”

     “有一次在哥哥深夜离开房间后，我偷偷跟了出去，趴在姐姐的房‘门’上偷听，这时候我才知道了哥哥和姐姐之间的秘密。”

     “那一晚我无法忘记，也许我的某些启‘蒙’就是从那一晚开始的，我在姐姐‘门’口呆了半个小时，直到凌晨两点，‘门’忽然被打开。”

     “从那以后哥哥和姐姐对我的态度更加恶劣，稍不顺心，就对我大打出手，‘逼’我吃变质的饭菜，‘逼’我喝脏水，把我当成是一袋发臭的垃圾，只是看一眼都觉得恶心。”

     “我和他们住在一起，但却跟他们生活在不同的世界里，为了活下去，我只有忍受。”

     “更糟糕的事情是从我初中毕业以后开始的，那一年父亲领回家一个年轻的‘女’人，那是他的学生，也是一所‘私’立高中的语文老师。”

     “自从这个‘女’人到来以后，我生存的空间就被进一步压榨，我不敢让他们看到我，似乎每个人心里都憋着一把火，更可怕是他们认为这一切都是因为我。”

     “我很害怕，我怕他们会杀了我，他们常常背着我说话，看我的目光没有任何温度，我不想死。”

     “后来父亲没有让我去离家很近的市一中，而是托关系让我去了一所‘私’立高中。”

     “这所高中在新沪和江城之间，学费很贵，我甚至在某一个瞬间还产生过感动，以为这些年都是我误会了父亲，但现实却狠狠给了我一巴掌，他们只是不想看见我，将我送到偏远的寄宿学校去，让我在那个‘女’人的班级里，任由她和人的折磨欺负。”

     日记上信息量略大，我只观看了其中一部分。

     “桐桑符是从新沪高中带出来的，看来这梦境的主人应该是新沪高中的某一位学生。”具体是谁我还不能确定，不过我心中已经隐隐有了一个人选。

     习惯‘性’想要去‘摸’一根烟，手指却在挥动的过程中碰了奇怪的东西，那是人类皮肤才有的触感。

     “难道是吊死鬼老爹跟过来了？”我把日记本揣在怀里，顺势‘摸’出一张镇压符。

     “何方厉鬼作祟！你家道爷在此！”我先喊一嗓子壮胆，然后抬头看去，蚊帐违背力学原理向内凹陷，好像有什么东西压在上面，正不断向我‘逼’近。

     二十厘米，十五厘米，十厘米，太近了，近到我就是想要看不出来都难。

     那是一张男人的脸，死灰‘色’，仿佛是纵‘欲’过度般，本应藏在皮肤下面的血管全都浮现在脸皮表面，看起来着实惊悚。

     “不是吊死鬼老爹，这个……应该是哥哥。”近距离端详一张这么恐怖的脸不是任何人都能做到的，所幸我在‘阴’间秀场的磨炼下，对这些东西有了很强的免疫能力：“你爹是吊死的，看你的样子有点像是中毒而死。”

     蚊帐距离我十厘米远时停止靠近，鬼脸似乎受限于某种规则无法对我出手。

     它翻起一双死鱼眼，瞟了一下书桌上的闹钟，而后慢慢消散。

     “为什么要在意闹钟？”我从**上跳下来，拿起桌子上的闹钟，上面显示的时间是一点三十。

     “我在书房的时候，钟表上显示的是一点十分，这个时间又代表着什么呢？”

     刚刚入梦，就已经遇到了两个鬼魂，我擦了一下额头的汗水走到‘门’外，进入最后一间卧室。

     “‘女’孩子的闺房里会有什么秘密呢？”
     */
    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            if (bean.getImages() != null && bean.getImages().size() > 0) {
                ImageGlideUtils.loadImage(iv, bean.getImages().get(0));
                int desity = ScreenSizeUtils.getDensity(this);
                for (int i = 0; i < bean.getImages().size(); i++) {
                    ImageView imageView = new ImageView(this);
                    final LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(60 *
                            desity, 52 * desity);
                    params1.setMargins(0, 0, 10 * desity, 0);
                    params1.gravity = Gravity.CENTER;
                    linearLayout.setLayoutParams(params1);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setGravity(Gravity.CENTER);
                    if (i == 0) {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color
                                .global_theme_background_color));
                    } else {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color
                                .color_gray3));
                    }


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(56 * desity,
                            48 * desity);
//                    imageView.setPadding(20 * desity, 0, 20 * desity, 0);

                    imageView.setLayoutParams(params);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageGlideUtils.loadImageFromUrl(imageView, bean.getImages().get(i));
                    imageView.setTag(i);
                    final String url = bean.getImages().get(i);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageGlideUtils.loadImage(iv, url);
                            position = (int) (v.getTag());
                            position = (int) (v.getTag());
                            setBorderColor();
                        }
                    });

                    linearLayout.addView(imageView);
                    linearList.add(linearLayout);
                    llImagesGroup.addView(linearLayout);
                }
            }

            tvTitle.setText(bean.getSummary());
            tvBewrite.setText(bean.getBewrite());
            tvNum.setText(bean.getNum());

            tvNeedIntegral.setText(bean.getCredit());
            tvStock.setText(bean.getStock());
            tvContent.setText(bean.getName());


        }

    }

    private void setBorderColor() {
        for (int j = 0; j < linearList.size(); j++) {
            if (j == position) {
                linearList.get(j).setBackgroundColor(getResources().getColor(R.color
                        .global_theme_background_color));
            } else {
                linearList.get(j).setBackgroundColor(getResources().getColor(R.color.color_gray3));
            }
        }
    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    /**
     * 设置按钮样式
     *
     * @param position 位置
     */
    private void setBtnStyle(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(i).setDrawBoder(true);
                list.get(i).setUnderlinecolor(getResources().getColor(R.color
                        .global_theme_background_color));
                list.get(i).setTextColor(getResources().getColor(R.color
                        .global_theme_background_color));
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_white));
            } else {
                list.get(i).setDrawBoder(false);
                list.get(i).setTextColor(getResources().getColor(R.color.color_black2));
                list.get(i).setBackgroundColor(getResources().getColor(R.color.color_line));
            }
        }
    }
}
