package com.rehome.huizhouxdj.utils;

import com.rehome.huizhouxdj.bean.ApkUpdateBean;
import com.rehome.huizhouxdj.bean.BmidBean;
import com.rehome.huizhouxdj.bean.ContactListBean;
import com.rehome.huizhouxdj.bean.UploadPhotosBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ruihong on 2017/12/22.
 */

public interface Api {
    /**
     * 检查更新
     *
     * @return
     */
    @GET("ApkUpdate/GetData.aspx")
    Call<ApkUpdateBean> getCheckUpdataApk();

    /**
     * 安全检查任务上传
     *
     * @param action AQJC_RW_SET（必填）
     * @param SCR    上传人ID（必填）
     * @param JHID   计划ID（必填）
     * @param WTQY   问题区域（必填）
     * @param WTMS   问题描述（必填）
     * @param LRSJ   录入时间（创建任务的时间）
     * @param FXLB   风险类别（选填）
     * @param YHDJ   隐患等级（选填）
     * @param ZRBM   责任部门（选填）
     * @return
     */

    @POST("AQJC/AQJC_RWSC.ashx")
    Call<UploadPhotosBean> upload_QJRWPhoto(@Query("Action") String action,
                                            @Query("SCR") String SCR,
                                            @Query("JHID") String JHID,
                                            @Query("WTQY") String WTQY,
                                            @Query("WTMS") String WTMS,
                                            @Query("LRSJ") String LRSJ,
                                            @Query("FXLB") String FXLB,
                                            @Query("YHDJ") String YHDJ,
                                            @Query("ZRBM") String ZRBM,
                                            @Body RequestBody body);


    @POST("Q4GD/Q4GD_CKGL.ashx")
    Call<UploadPhotosBean> upload_QXGD(@Query("Action") String action,
                                       @Query("YHID") String YHID,
                                       @Query("GDZT_SO") String GDZT_SO,
                                       @Query("GDZT_NO") String GDZT_NO,
                                       @Query("GDDJ") String GDDJ,
                                       @Query("QXMS") String QXMS,
                                       @Query("GZMS") String GZMS,
                                       @Query("SBBH") String SBBH,
                                       @Query("SBMC") String SBMC,
                                       @Query("ZRBZ") String ZRBZ,
                                       @Query("ZRR") String ZRR,
                                       @Query("JXBZ") String JXBZ,
                                       @Query("JXR") String JXR,
                                       @Query("GZXZ") String GZXZ,
                                       @Query("ST") String ST,
                                       @Query("ET") String ET,
                                       @Body RequestBody body);


    @POST("Q4GD/Q4GD_IMG.ashx")
    Call<UploadPhotosBean> upload_QXGDTP(
            @Query("GDID") String GDID,
            @Body RequestBody body);


    /**
     * 安全检查整改任务上传
     *
     * @param action SBLC_ZG_SET
     * @param YHID   用户ID（必填）
     * @param JHID   计划ID（必填）
     * @param RWID   任务ID（必填）
     * @param ZGJG   整改结果（必填）
     * @return
     */

    @POST("AQJC/AQJC_RWSC.ashx")
    Call<UploadPhotosBean> upload_AQZGJG(@Query("Action") String action,
                                         @Query("YHID") String YHID,
                                         @Query("JHID") String JHID,
                                         @Query("RWID") String RWID,
                                         @Query("ZGJG") String ZGJG,
                                         @Body RequestBody body);


    /**
     * 获取通讯录列表
     *
     * @return
     */
    @GET("PubFile/Data/GetAddressbook.ashx")
    Call<ContactListBean> getContactList();


    /**
     * 获取部门id
     *
     * @return
     */
    @GET("UserInfos/GetDeptByUserid.aspx")
    Call<BmidBean> getbmid(@Query("yhid") String yhid);


}
