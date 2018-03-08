package com.interestcontent.liudeyu.settings.activitys;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.AppRestartUtil;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.settings.components.NewsTopicManager;
import com.pchmn.materialchips.ChipView;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2018/3/6.
 */

public class NewsTopicSettingActivity extends BaseActivity {


    private static final int REFLASH_DATA = 3;
    private ChipsInput mChipsInput;
    private Button mConfirm;
    private SlideAndDragListView mSlideAndDragListView;
    @BindView(R.id.reset_default_topic_btn)
    Button mRestDefaultButton;
    @BindView(R.id.topic_show_ll)
    LinearLayout mTopicShowContainer;
    @BindView(R.id.add_new_topic_btn)
    Button mAddNewCustomTopicButton;
    private MyListViewAdapter mAdapter;
    private String dragItem;//拖动的item


    public static void start(Context context) {
        Intent starter = new Intent(context, NewsTopicSettingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        return LayoutInflater.from(this).inflate(R.layout.activity_news_topic_settting_layout, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mToolbarTitle.setText("新闻主题设置");
        mConfirm = findViewById(R.id.confirm_btn);
        mChipsInput = findViewById(R.id.chips_input);
        mSlideAndDragListView = findViewById(R.id.slide_list_view);
        initTopicTipInput();
        initDragAndDeleteListView();
        initTopicShowTipViews();
        mRestDefaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsTopicManager.getInstance().setNewsCategories(NewsTopicManager.getInstance().getDefaultCategories());
                reInitListViewState(NewsTopicManager.getInstance().getNewsCatetory());
            }
        });

        initAddCustomNewTopic();
        mToolbarRightBtn.setVisibility(View.VISIBLE);

    }

    private void initAddCustomNewTopic() {
        mAddNewCustomTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(NewsTopicSettingActivity.this).inflate(R.layout.news_topic_dialog_edit_layout,
                        null);
                final EditText editText = view1.findViewById(R.id.edittext);
                AlertDialog alertDialog = new AlertDialog.Builder(NewsTopicSettingActivity.this).setView(view1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dealWithNewTopicText(editText.getText().toString());

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
    }

    private void dealWithNewTopicText(String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (SharePreferenceUtil.getBooleanPreference(this, SpConstants.NEWS_CUSTOM_TAG_FIRST_SHOW_TIP, true)) {
            ToastUtils.showLong("太奇怪的新闻主题可能会没有数据或者数据不完全符合要求哦!!");
            SharePreferenceUtil.setBooleanPreference(this, SpConstants.NEWS_CUSTOM_TAG_FIRST_SHOW_TIP, false);
        }
        String[] array = s.split("\\s+");
        if (array != null && array.length > 0) {
            List<String> list = Arrays.asList(array);
            NewsTopicManager.getInstance().addCustomCreateTopicTag(list);
            reInitListViewState(NewsTopicManager.getInstance().getNewsCatetory());
        }
    }

    @Override
    protected void onToolbarRightBtnClick() {
        confirmBtnClick();
        AppRestartUtil.restartAppWithoutKillProcess(this);
    }

    private void initTopicShowTipViews() {
        mTopicShowContainer.removeAllViews();
        List<ChipInterface> chipInterfaceList = NewsTopicManager.getInstance().getNotBeSelectedChipInterfaces();
        for (ChipInterface chipInterface : chipInterfaceList) {
            final ChipView chipView = new ChipView(this);
            chipView.setChip(chipInterface);
            chipView.setDeletable(false);
            chipView.setLabel(chipInterface.getLabel());
            chipView.setHasAvatarIcon(true);
            chipView.setOnChipClicked(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mChipsInput.addChip(chipView.getLabel(), chipView.getLabel());
                }
            });
            chipView.setClickable(true);
            mTopicShowContainer.addView(chipView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    private void reInitListViewState(List<String> newsCatetory) {
        setMenus(newsCatetory);
        mAdapter.setData(newsCatetory);
        initTopicShowTipViews();
    }

    private void initDragAndDeleteListView() {
        List<String> mTopicData = NewsTopicManager.getInstance().getNewsCatetory();
        setMenus(mTopicData);
        mAdapter = new MyListViewAdapter(this, R.layout.news_topic_listview_item_layout, mTopicData);
        mSlideAndDragListView.setAdapter(mAdapter);
        mSlideAndDragListView.setDivider(new ColorDrawable(getResources().getColor(R.color.white)));
        mSlideAndDragListView.setDividerHeight(10);
        mSlideAndDragListView.setFooterDividersEnabled(true);
        mSlideAndDragListView.setHeaderDividersEnabled(true);
        mSlideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
                switch (direction) {
                    case MenuItem.DIRECTION_RIGHT:
                        // 只有一个删除按钮
                        if (buttonPosition == 0) {
                            List<String> data = NewsTopicManager.getInstance().deleteNewsCateory(itemPosition);
                            mAdapter.setData(NewsTopicManager.getInstance().getNewsCatetory());
                            initTopicShowTipViews();
                            return Menu.ITEM_NOTHING;
                        }
                        break;
                    default:
                        return Menu.ITEM_NOTHING;
                }
                return Menu.ITEM_NOTHING;
            }
        });
        mSlideAndDragListView.setOnDragDropListener(new SlideAndDragListView.OnDragDropListener() {
            @Override
            public void onDragViewStart(int beginPosition) {
                dragItem = NewsTopicManager.getInstance().getNewsCatetory().get(beginPosition);
            }

            @Override
            public void onDragDropViewMoved(int fromPosition, int toPosition) {
                NewsTopicManager.getInstance().moveItemPosition(fromPosition, toPosition);
            }

            @Override
            public void onDragViewDown(int finalPosition) {
                NewsTopicManager.getInstance().setItemAtIndex(finalPosition, dragItem);
                mAdapter.setData(NewsTopicManager.getInstance().getNewsCatetory());
            }
        });
        mSlideAndDragListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSlideAndDragListView.startDrag(i);
                Vibrator vib = (Vibrator) NewsTopicSettingActivity.this.getSystemService(Service.VIBRATOR_SERVICE);
                vib.vibrate(100);
                return true;
            }
        });
    }

    private void setMenus(List<String> topic) {
        List<Menu> menus = new ArrayList<>();
        for (String tmp : topic) {
            int redColor = getResources().getColor(R.color.md_red_400);
            int textColor = getResources().getColor(R.color.white);
            Menu menu = new Menu(true, 0);
            menu.addItem(new MenuItem.Builder().setWidth(SizeUtils.dp2px(100))
                    .setBackground(new ColorDrawable(redColor))
                    .setTextColor(textColor)
                    .setText("删除")
                    .setTextSize(16)
                    .setDirection(MenuItem.DIRECTION_RIGHT)
                    .build()
            );
            menus.add(menu);
        }
        mSlideAndDragListView.setMenu(menus);
    }

    private void initTopicTipInput() {
        mChipsInput.setFilterableList(NewsTopicManager.getInstance().getNotBeSelectedChipInterfaces());
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBtnClick();
            }

        });

    }

    private void confirmBtnClick() {
        List<ChipInterface> list = (List<ChipInterface>) mChipsInput.getSelectedChipList();
        if (list != null && !list.isEmpty()) {
            List<String> allNewsCate = new ArrayList<>();
            for (ChipInterface chipInterface : list) {
                allNewsCate.add(chipInterface.getLabel());
            }
            NewsTopicManager.getInstance().addNewsCatetory(allNewsCate);
            reInitListViewState(NewsTopicManager.getInstance().getNewsCatetory());
            mChipsInput.clearFocus();
            for (String t : allNewsCate) {
                mChipsInput.removeChipByLabel(t);
            }
        }
        //这里为了设置的tab主题生效
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppRestartUtil.restartAppWithoutKillProcess(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private static class MyListViewAdapter extends CommonAdapter<String> {
        public MyListViewAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        public void setData(List<String> data) {
            mDatas = data;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(ViewHolder viewHolder, String item, int position) {

            TextView textView = viewHolder.getView(R.id.topic_tv);
            textView.setText(item);
        }
    }
}
