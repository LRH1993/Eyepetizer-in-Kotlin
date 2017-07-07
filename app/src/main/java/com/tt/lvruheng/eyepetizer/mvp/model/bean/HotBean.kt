package com.tt.lvruheng.eyepetizer.mvp.model.bean

/**
 * Created by lvruheng on 2017/7/7.
 */

data class HotBean(var count: Int, var total: Int, var nextPageUrl: Any?, var itemList: List<ItemListBean>?) {

    data class ItemListBean (var type: String?,var data: DataBean?,var tag: Any?){

        data class DataBean(var dataType: String?,var id: Int,var title: String?,
                            var slogan: Any?, var description: String?,var provider: ProviderBean?,
                            var category: String?,var author: Any? ,var cover: CoverBean?,
                            var playUrl: String?,var thumbPlayUrl: Any?,var duration: Long,
                            var releaseTime: Long,var library: String?,
                            var consumption: ConsumptionBean?,var campaign: Any?,var waterMarks: Any?,
                            var adTrack: Any?, var type: String?,var titlePgc: Any?,var descriptionPgc: Any?,
                            var remark: Any?, var idx: Int,var shareAdTrack: Any?,var favoriteAdTrack: Any?,
                            var webAdTrack: Any?,var date: Long,var promotion: Any? ,var label: Any?,
                            var descriptionEditor: String?,var isCollected: Boolean,var isPlayed: Boolean,
                            var lastViewTime: Any?,var playInfo: List<PlayInfoBean>? , var tags: List<TagsBean>? ,
                            var labelList: List<*>? ,var subtitles: List<*>?) {


            data class ProviderBean(var name: String?,var alias: String?,var icon: String?) {
            }

            data class CoverBean(var feed: String?,var detail: String?,var blurred: String?,
                                 var sharing: Any? ,var homepage: Any?) {
            }



            data class ConsumptionBean(var collectionCount: Int,var shareCount: Int, var replyCount: Int) {
            }

            data class PlayInfoBean(var height: Int,var width: Int ,var name: String?,
                                    var type: String?,var url: String? ,var urlList: List<UrlListBean>?) {

                data class UrlListBean(var name: String?,var url: String?,var size: Int) {
                }
            }

            data class TagsBean(var id: Int,var name: String?,var actionUrl: String?,var adTrack: Any?) {
            }
        }
    }
}
