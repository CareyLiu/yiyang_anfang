package com.smarthome.magic.activity.xiupeichang;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.common.utils.AlibcLogger;
import com.flyco.roundview.RoundLinearLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuangou.TuanGouShangJiaDetailsActivity;
import com.smarthome.magic.adapter.Custom5HistoryAdapter;
import com.smarthome.magic.adapter.TaoKeListAdapter2;
import com.smarthome.magic.adapter.tuangou.TuanGouShangJiaListAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.db.DBManager;
import com.smarthome.magic.db.HistoryRecordDao;
import com.smarthome.magic.db.table.HistoryRecord;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.TaoKeDetailList;
import com.smarthome.magic.model.TuanGouShangJiaListBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.smarthome.magic.app.App.JINGDU;
import static com.smarthome.magic.app.App.WEIDU;
import static com.smarthome.magic.db.DbField.XIUPEICHANGSEARCH;
import static com.smarthome.magic.get_net.Urls.LIBAOLIST;


public class XiuPeiChangSearchThingActivity extends BaseActivity {
    private static final String tag = XiuPeiChangSearchThingActivity.class.getSimpleName();
    TuanGouShangJiaListAdapter tuanGouShangJiaListAdapter;
    List<TuanGouShangJiaListBean.DataBean.StoreListBean> storeListBeans = new ArrayList<>();
    String strTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_serarchKey)
    EditText etSerarchKey;
    @BindView(R.id.rl_search)
    RoundLinearLayout rlSearch;
    @BindView(R.id.iv_cancel)
    TextView ivCancel;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_quanbu)
    LinearLayout llQuanbu;
    @BindView(R.id.rl_quanbu)
    RelativeLayout rlQuanbu;
    @BindView(R.id.ll_zhinengpaixu)
    LinearLayout llZhinengpaixu;
    @BindView(R.id.rl_zhinengpaixu)
    RelativeLayout rlZhinengpaixu;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.rl_shaixuan)
    RelativeLayout rlShaixuan;
    @BindView(R.id.rl_ll)
    LinearLayout rlLl;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.constrain_xx)
    LinearLayout constrainXx;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.ll_none)
    LinearLayout llNone;
    @BindView(R.id.activity_custom3_search)
    RelativeLayout activityCustom3Search;
    @BindView(R.id.ll_tou)
    LinearLayout llTou;
    @BindView(R.id.tv_quanbu)
    TextView tvQuanbu;
    @BindView(R.id.tv_zhinengpaixu)
    TextView tvZhinengpaixu;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;


    private Custom5HistoryAdapter historyAdapter;
    private TaoKeListAdapter2 taoKeListAdapter2;
    private InputMethodManager imm;
    List<HistoryRecord> searchKeywords1;
    public DBManager dbManager;
    private Context context = XiuPeiChangSearchThingActivity.this;
    private List<TaoKeDetailList.DataBean> mDatas = new ArrayList<>();
    int pagesize = 20;
    int pageNumber = 0;

    List<TuanGouShangJiaListBean.DataBean.IconBean> iconListBeans = new ArrayList<>();
    private String item_id = "";//经营项id（三级页时传）
    private String neibour = "";//三级页小图标对应id（三级页时传）
    private String order = ""; //排序：1.离我最近 2.好评优先 3.销量最高  .可传空，为智能排序
    private String inst_id = "";
    private String three_img_id = "";//三级页小图标对应的id
    private String meter = "";//order 第一个
    private String inst_number = "";
    private String count = "";
    private String type = "7";//首页图标类型 1.美食 2.电影/演出 3.酒店住宿 4.休闲娱乐 5.旅游

    /**
     * 用于其他activity跳转到该activity
     */
    public static void actionStart(Context context, String string) {
        Intent intent = new Intent(context, XiuPeiChangSearchThingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("string", string);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setBaseTitleGone();

        if (getIntent() == null) {
            return;
        }
        strTitle = getIntent().getStringExtra("string");
        etSerarchKey.setText(strTitle);
//        douDaRen5_6SearchPresenter = new DouDaRenSearchPresenterImpl(this);
        dbManager = DBManager.getInstance(this);
        dbManager.setDebug();
        searchKeywords1 = new ArrayList<>();
        context = this;
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipeTarget.setLayoutManager(gridLayoutManager);
        initAdapter();
        getNet();
        srLSmart.setEnableAutoLoadMore(true);
        srLSmart.setEnableRefresh(true);
        srLSmart.setEnableLoadMore(true);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                srLSmart.setEnableLoadMore(true);
                getNet();

            }
        });
        srLSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNumber++;
                getNet();
            }
        });
        historyAdapter();
        etSerarchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                insertKeyword(textView.getText().toString().trim(), 1);
                strTitle = textView.getText().toString();
                pageNumber = 0;
                //getNet();
                //      BeautifulTellArticleSearchDtoIn in = new BeautifulTellArticleSearchDtoIn();
                //      App app = App.getInstance();
                //    if (app.isLogin()) {
                //      in.personId = app.getLoginUid();
                // }
                //        in.areaName = String.valueOf(App.getInstance().getProperty(AppConfig.CONF_CITY));
                // in.setPageNumber(pageNumber);
                // in.setPageSize(10);
                //if (StringUtils.isEmpty(textView.getText().toString().trim())) {
                //   UIHelper.ToastMessage(Custom5SearchThingActivity.this, "检索内容不能为空", Toast.LENGTH_SHORT);
                //} else {
                //  in.searchValue = textView.getText().toString().trim();
                // tv = textView;
                // try {
                //   douDaRen5_6SearchPresenter.getDouDaRenSearch(in);
                // } catch (AppException e) {
                //    e.printStackTrace();
                // }
                // }

                getNet();

                return false;
            }
        });

        searchKeywords1 = queryKeywordList(XIUPEICHANGSEARCH);
        // historyAdapter.setNewData(searchKeywords1);


        constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constrain.setVisibility(View.GONE);
            }
        });

        constrainXx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        tuanGouShangJiaListAdapter = new TuanGouShangJiaListAdapter(R.layout.item_shangjia, storeListBeans);
        tuanGouShangJiaListAdapter.setOnItemChildClickListener(new com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(com.chad.library.adapter.base.BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.constrain:
                        if (type.equals("7")) {
                            XiupeichangShangActivity.actionStart(mContext, tuanGouShangJiaListAdapter.getData().get(position).getInst_id());
                        } else {
                            TuanGouShangJiaDetailsActivity.actionStart(mContext, tuanGouShangJiaListAdapter.getData().get(position).getInst_id(), type);
                        }
                        break;
                }
            }
        });

        LinearLayoutManager gridLayoutManager1 = new LinearLayoutManager(this);
        swipeTarget.setLayoutManager(gridLayoutManager1);

        //  taoKeListAdapter = new TaoKeListAdapter(R.layout.layout_taokeshop, dataBeanList);
        tuanGouShangJiaListAdapter.openLoadAnimation();//默认为渐显效果
        swipeTarget.setAdapter(tuanGouShangJiaListAdapter);


        rlQuanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全部
                initPopuptWindow(iconListBeans);
            }
        });
        rlZhinengpaixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //智能排序
                initZhiNengPaiXuPopuptWindow();
            }
        });
        rlShaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //筛选
            }
        });
    }

    /**
     * 插入
     *
     * @param keyword
     */
    public void insertKeyword(String keyword, int type) {
        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        HistoryRecordDao historyRecordDao = dbManager.getDaoSession().getHistoryRecordDao();
        List<HistoryRecord> searchKeywordList = queryKeywordList(type);
        if (searchKeywordList.size() > 9) {
            historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Date.eq(searchKeywordList.get(0).getDate())).buildDelete().executeDeleteWithoutDetachingEntities();
        }

        Iterator<HistoryRecord> iterator = searchKeywordList.iterator();
        while (iterator.hasNext()) {
            HistoryRecord next = iterator.next();
            if (keyword.equals(next.getName())) {
                iterator.remove();
                historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Name.eq(next.getName())).buildDelete().executeDeleteWithoutDetachingEntities();
            }
        }

        HistoryRecord sk = new HistoryRecord();
        sk.setName(keyword);
        sk.setDate(System.currentTimeMillis());
        sk.setType(type);
        historyRecordDao.insertInTx(sk);

        if (historyAdapter != null) {
            searchKeywords1.clear();
            searchKeywords1 = queryKeywordList(1);
            historyAdapter.setNewData(searchKeywords1);
        }
    }

    /**
     * 查询
     */
    public List<HistoryRecord> queryKeywordList(int type) {
        HistoryRecordDao historyRecordDao = dbManager.getDaoSession().getHistoryRecordDao();
        List<HistoryRecord> searchKeywordList = historyRecordDao.queryBuilder().where(HistoryRecordDao.Properties.Type.eq(type)).orderAsc(HistoryRecordDao.Properties.Date).build().list();
        return searchKeywordList;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void initAdapter() {
        taoKeListAdapter2 = new TaoKeListAdapter2(R.layout.layout_taokeshop, mDatas);
        taoKeListAdapter2.openLoadAnimation();//默认为渐显效果
        swipeTarget.setAdapter(taoKeListAdapter2);
        taoKeListAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Native);
                showParams.setClientType("taobao");
                showParams.setBackUrl("alisdk://");
//                showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpDOWNLOAD);
                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_1075130017_1502650362_110240000445");
                Map<String, String> trackParams = new HashMap<>();


//                // 以显示传入url的方式打开页面（第二个参数是套件名称）
//                AlibcTrade.openByUrl(getActivity(), "", "https://temai.m.taobao.com/", null,
//                        new WebViewClient(), new WebChromeClient(), showParams,
//                        taokeParams, trackParams, new AlibcTradeCallback() {
//                            @Override
//                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
//                              //  AlibcLogger.i(TAG, "request success");
//                            }
//                            @Override
//                            public void onFailure(int code, String msg) {
//                               // AlibcLogger.e(TAG, "code=" + code + ", msg=" + msg);
//                              //  if (code == -1) {
//                               //     Toast.makeText(FeatureActivity.this, msg, Toast.LENGTH_SHORT).show();
//                               // }
//                            }
//                        });


                AlibcBasePage page = new AlibcDetailPage(taoKeListAdapter2.getData().get(position).getItem_id() + "");
                AlibcTrade.openByBizCode(XiuPeiChangSearchThingActivity.this, page, null, new WebViewClient(), new WebChromeClient(),
                        "detail", showParams, taokeParams, trackParams, new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(AlibcTradeResult tradeResult) {
                                // 交易成功回调（其他情形不回调）
                                AlibcLogger.i("taobaoSuccess", "open detail page success");
                                Log.i("taobaoSuccess", tradeResult.toString() + "--");
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                AlibcLogger.e("MainActivity", "code=" + code + ", msg=" + msg);
                                if (code == -1) {
                                    Toast.makeText(XiuPeiChangSearchThingActivity.this, "唤端失败，失败模式为none", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_custom5_search_thing;
    }

    @OnClick({R.id.iv_cancel, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                //imm.hideSoftInputFromWindow(activityCustom3Search.getWindowToken(), 0);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //历史记录
    private void historyAdapter() {
        historyAdapter = new Custom5HistoryAdapter(searchKeywords1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    private String sort = "total_sales";//
    private String shengxu = "_asc";
    private String jiangxu = "_des";

    private String sortBenTi = sort + jiangxu;
    Response<AppResponse<TuanGouShangJiaListBean.DataBean>> response;

    //  private String
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08011");
        map.put("key", Urls.key);
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, "0X11"));
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, "0X11"));
        map.put("img_type", "7");//上一页带过来的
        map.put("item_id", item_id);
        map.put("neibour", neibour);
        map.put("three_img_id", three_img_id);
        map.put("order", order);
        map.put("text", strTitle);
        map.put("inst_id", inst_id);
        if (order.equals("1")) {
            map.put("meter", meter);
        } else if (order.equals("2")) {
            map.put("inst_number", inst_number);
        } else if (order.equals("3")) {
            map.put("count", count);
        }
        if (type.equals("10")) {
            map.put("more_type", "1");
        }
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TuanGouShangJiaListBean.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShangJiaListBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShangJiaListBean.DataBean>> response) {
                        XiuPeiChangSearchThingActivity.this.response = response;
                        iconListBeans.clear();
                        storeListBeans.clear();
                        iconListBeans.addAll(response.body().data.get(0).getIcon());
                        storeListBeans.addAll(response.body().data.get(0).getStore_list());
                        tuanGouShangJiaListAdapter.setNewData(storeListBeans);
                        tuanGouShangJiaListAdapter.notifyDataSetChanged();

                        if (!iconListBeans.get(0).getName().equals("全部")) {
                            TuanGouShangJiaListBean.DataBean.IconBean iconBean = new TuanGouShangJiaListBean.DataBean.IconBean();
                            iconBean.setName("全部");
                            iconListBeans.add(0, iconBean);
                        }

                    }
                });
    }

    /**
     * 设置pop框
     */
    PopupWindow mPopupWindow;

    private void initPopuptWindow(List<TuanGouShangJiaListBean.DataBean.IconBean> iconListBeans) {
        // 获取屏幕的width和height
        mPopupWindow = null;

        //加载pop框的视图布局view
        View view = View.inflate(mContext, R.layout.pop_xiupeichang, null);
        LinearLayout llPop = view.findViewById(R.id.ll_pop);

        if (!iconListBeans.get(0).getName().equals("全部")) {
            TuanGouShangJiaListBean.DataBean.IconBean iconBean = new TuanGouShangJiaListBean.DataBean.IconBean();
            iconBean.setName("全部");
            iconListBeans.add(0, iconBean);
        }

        llPop.removeAllViews();
        for (int i = 0; i < iconListBeans.size(); i++) {
            View viewHortial = View.inflate(mContext, R.layout.item_hortial_view, null);
            TextView tvText = viewHortial.findViewById(R.id.tv_text);
            tvText.setText(iconListBeans.get(i).getName());
            int finalI = i;
            tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvQuanbu.setText(iconListBeans.get(finalI).getName());
                    if (iconListBeans.get(finalI).getName().equals("全部")) {
                        //pageNumber = "0";
                        item_id = "";
                        three_img_id = "";
                        inst_id = "";
                        //imgType = type;
                    } else {
                        // pageNumber = "0";
                        item_id = iconListBeans.get(finalI).getHref_url();
                        three_img_id = iconListBeans.get(finalI).getThree_img_id();
                        inst_id = "";
                        // imgType = type;
                    }
                    getNet();
                    mPopupWindow.dismiss();
                }
            });
            llPop.addView(viewHortial);
        }


        int mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int zhuangtailan_height = resources.getDimensionPixelSize(resourceId);

        int height = mScreenHeight - llTou.getHeight() - rlLl.getHeight() - zhuangtailan_height;


        Log.i("jiba", "mScreenWidth==" + mScreenWidth + ",mScreenHeight==" + mScreenHeight);


        // 创建一个PopupWindow
        // 参数1：contentView 指定PopupWindow的内容
        // 参数2：width 指定PopupWindow的width
        // 参数3：height 指定PopupWindow的height
        mPopupWindow = new PopupWindow(view, mScreenWidth, height);

        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 设置点击窗口外边窗口消失    这两步用于点击手机的返回键的时候，不是直接关闭activity,而是关闭pop框
        mPopupWindow.setOutsideTouchable(true);

        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        mPopupWindow.setFocusable(true);

        mPopupWindow.setContentView(view);
        mPopupWindow.showAtLocation(srLSmart, Gravity.BOTTOM, 0, 0);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f; //0.0-1.0
        getWindow().setAttributes(lp);
        PopupWindow finalMPopupWindow = mPopupWindow;
        llPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalMPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                getWindow().setAttributes(lp);
            }
        });

    }


    /**
     * 设置pop框
     */
    PopupWindow mPopupWindow_zhinengpaixu;
    private List<String> listData = Arrays.asList("智能排序", "离我最近", "好评优先", "销量最高");

    private void initZhiNengPaiXuPopuptWindow() {
        // 获取屏幕的width和height
        mPopupWindow_zhinengpaixu = null;

        //加载pop框的视图布局view
        View view = View.inflate(mContext, R.layout.pop_xiupeichang, null);
        LinearLayout llPop = view.findViewById(R.id.ll_pop);

        llPop.removeAllViews();
        for (int i = 0; i < listData.size(); i++) {
            View viewHortial = View.inflate(mContext, R.layout.item_hortial_view, null);
            TextView tvText = viewHortial.findViewById(R.id.tv_text);
            tvText.setText(listData.get(i));
            int finalI = i;
            tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listData.get(finalI).equals("智能排序")) {
                        order = "";
                    } else if (listData.get(finalI).equals("离我最近")) {

                        order = "1";
                    } else if (listData.get(finalI).equals("好评优先")) {

                        order = "2";
                    } else if (listData.get(finalI).equals("销量最高")) {

                        order = "3";
                    }
                    getNet();
                    mPopupWindow_zhinengpaixu.dismiss();
                }
            });
            llPop.addView(viewHortial);
        }


        int mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int zhuangtailan_height = resources.getDimensionPixelSize(resourceId);

        int height = mScreenHeight - llTou.getHeight() - rlLl.getHeight() - zhuangtailan_height;


        Log.i("jiba", "mScreenWidth==" + mScreenWidth + ",mScreenHeight==" + mScreenHeight);


        // 创建一个PopupWindow
        // 参数1：contentView 指定PopupWindow的内容
        // 参数2：width 指定PopupWindow的width
        // 参数3：height 指定PopupWindow的height
        mPopupWindow_zhinengpaixu = new PopupWindow(view, mScreenWidth, height);

        // 需要设置一下此参数，点击外边可消失
        mPopupWindow_zhinengpaixu.setBackgroundDrawable(new BitmapDrawable());

        // 设置点击窗口外边窗口消失    这两步用于点击手机的返回键的时候，不是直接关闭activity,而是关闭pop框
        mPopupWindow_zhinengpaixu.setOutsideTouchable(true);

        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        mPopupWindow_zhinengpaixu.setFocusable(true);

        mPopupWindow_zhinengpaixu.setContentView(view);
        mPopupWindow_zhinengpaixu.showAtLocation(srLSmart, Gravity.BOTTOM, 0, 0);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f; //0.0-1.0
        getWindow().setAttributes(lp);
        PopupWindow finalMPopupWindow = mPopupWindow;
        llPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalMPopupWindow.dismiss();
            }
        });

        mPopupWindow_zhinengpaixu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                getWindow().setAttributes(lp);
            }
        });

    }

}
