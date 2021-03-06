package com.gxl.intelligentkitchen.presenter;

import android.util.Log;
import android.widget.Toast;

import com.gxl.intelligentkitchen.entity.DynamicItem;
import com.gxl.intelligentkitchen.entity.FoodGeneralItem;
import com.gxl.intelligentkitchen.model.DynamicModel;
import com.gxl.intelligentkitchen.model.FoodModel;
import com.gxl.intelligentkitchen.model.impl.FoodModelImpl;
import com.gxl.intelligentkitchen.ui.customview.SlideShowView;
import com.gxl.intelligentkitchen.ui.view.IFoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：GXL on 2016/8/3 0003
 * 博客: http://blog.csdn.net/u014316462
 * 作用：FoodFragment的Presenter
 */
public class FoodFragmentPresenter {
    private IFoodFragment mIFoodFragment;
    private FoodModel mFoodModel = new FoodModel();
    private DynamicModel mDynamicModel = new DynamicModel();

    public FoodFragmentPresenter(IFoodFragment mIFoodFragment) {
        this.mIFoodFragment = mIFoodFragment;
    }

    /**
     * 上拉加载更多
     *
     * @param sortby
     * @param lm
     * @param page
     */
    public void onLoadMore(String sortby, int lm, int page) {
        mFoodModel.getGeneralFoodsItem(sortby, lm, page, new FoodModelImpl.BaseListener() {
            @Override
            public void getSuccess(Object o) {
                List<FoodGeneralItem> list = (List<FoodGeneralItem>) o;
                mIFoodFragment.onLoadMore(list);
            }

            @Override
            public void getFailure() {
            }
        });
    }

    /**
     * 下拉刷新
     *
     * @param sortby
     * @param lm
     * @param page
     */
    public void onRefresh(String sortby, int lm, int page) {
        mFoodModel.getGeneralFoodsItem(sortby, lm, page, new FoodModelImpl.BaseListener() {
            @Override
            public void getSuccess(Object o) {
                List<FoodGeneralItem> list = (List<FoodGeneralItem>) o;
                mIFoodFragment.onRefresh(list);
            }

            @Override
            public void getFailure() {
            }
        });

        mDynamicModel.getDynamicItem(new FoodModelImpl.BaseListener() {
            @Override
            public void getSuccess(Object o) {
                List<DynamicItem> list = (List<DynamicItem>) o;
                mIFoodFragment.onInitDynamic(list.get(0));
            }

            @Override
            public void getFailure() {

            }
        });
    }

    /**
     * 初始化SliderShow
     */
    public void onInitSliderShow() {
        mFoodModel.getSliderShowFood(new FoodModelImpl.BaseListener() {
            @Override
            public void getSuccess(Object o) {
                List<SlideShowView.SliderShowViewItem> list = (List<SlideShowView.SliderShowViewItem>) o;
                for (SlideShowView.SliderShowViewItem item : list
                        ) {
                    Log.i("TAG", "onInitSliderShow: " + item.getFoodname());
                }
                mIFoodFragment.onInitSliderShow(list);
            }

            @Override
            public void getFailure() {
            }
        });
    }
}
