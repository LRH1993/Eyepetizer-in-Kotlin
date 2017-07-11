package com.tt.lvruheng.eyepetizer.network

/**
 * Created by lvruheng on 2017/7/4.
 */
//每日精选
const val DAILY="http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
//发现更多
const val FIND_MORE="http://baobab.wandoujia.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
//热门排行
const val HOT_STRATEGY="http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
//发现更多详情接口
const val FIND_DETAIL="http://baobab.wandoujia.com/api/v3/videos?categoryName=%s&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
//搜索关键字
const val SEARCH_RELATED = "http://baobab.kaiyanapp.com/api/v1/search?num=10&query=%E4%BD%A0&start=10"