package com.rehome.huizhouxdj.utils;

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

//    /**
//     * 设备联查任务上传
//     *
//     * @param action SBLC_RW_SET
//     * @param SCR    上传人ID（必填）
//     * @param JHID   计划ID（必填）
//     * @param LCSB   联查设备（必填）
//     * @param WTMS   问题描述（必填）
//     * @param LRSJ   录入时间（创建任务的时间）
//     * @param ZGYJ   整改意见（选填）
//     * @param ZGBM   整改部门（选填）
//     * @param ZGZRR  整改责任人（选填）
//     * @return
//     */
//
//    @POST("SBLC/SBLC_RWSC.ashx")
//    Call<UploadPhotosBean> upload_RWPhoto(@Query("Action") String action,
//                                          @Query("SCR") String SCR,
//                                          @Query("JHID") String JHID,
//                                          @Query("LCSB") String LCSB,
//                                          @Query("WTMS") String WTMS,
//                                          @Query("LRSJ") String LRSJ,
//                                          @Query("ZGYJ") String ZGYJ,
//                                          @Query("ZGBM") String ZGBM,
//                                          @Query("ZGZRR") String ZGZRR,
//                                          @Body RequestBody body);
//
//
//    /**
//     * 安全检查任务上传
//     *
//     * @param action AQJC_RW_SET（必填）
//     * @param SCR    上传人ID（必填）
//     * @param JHID   计划ID（必填）
//     * @param WTQY   问题区域（必填）
//     * @param WTMS   问题描述（必填）
//     * @param LRSJ   录入时间（创建任务的时间）
//     * @param FXLB   风险类别（选填）
//     * @param YHDJ   隐患等级（选填）
//     * @param ZRBM   责任部门（选填）
//     * @return
//     */
//
//    @POST("AQJC/AQJC_RWSC.ashx")
//    Call<UploadPhotosBean> upload_QJRWPhoto(@Query("Action") String action,
//                                            @Query("SCR") String SCR,
//                                            @Query("JHID") String JHID,
//                                            @Query("WTQY") String WTQY,
//                                            @Query("WTMS") String WTMS,
//                                            @Query("LRSJ") String LRSJ,
//                                            @Query("FXLB") String FXLB,
//                                            @Query("YHDJ") String YHDJ,
//                                            @Query("ZRBM") String ZRBM,
//                                            @Body RequestBody body);
//
//
//    /**
//     * 设备整改任务上传
//     *
//     * @param action SBLC_ZG_SET
//     * @param YHID   用户ID（必填）
//     * @param JHID   计划ID（必填）
//     * @param RWID   任务ID（必填）
//     * @param ZGJG   整改结果（必填）
//     * @return
//     */
//
//    @POST("SBLC/SBLC_ZGSC.ashx")
//    Call<UploadPhotosBean> upload_SBZGJG(@Query("Action") String action,
//                                         @Query("YHID") String YHID,
//                                         @Query("JHID") String JHID,
//                                         @Query("RWID") String RWID,
//                                         @Query("ZGJG") String ZGJG,
//                                         @Body RequestBody body);
//
//    /**
//     * 安全检查整改任务上传
//     *
//     * @param action SBLC_ZG_SET
//     * @param YHID   用户ID（必填）
//     * @param JHID   计划ID（必填）
//     * @param RWID   任务ID（必填）
//     * @param ZGJG   整改结果（必填）
//     * @return
//     */
//
//    @POST("AQJC/AQJC_RWSC.ashx")
//    Call<UploadPhotosBean> upload_AQZGJG(@Query("Action") String action,
//                                         @Query("YHID") String YHID,
//                                         @Query("JHID") String JHID,
//                                         @Query("RWID") String RWID,
//                                         @Query("ZGJG") String ZGJG,
//                                         @Body RequestBody body);
//
//
//    /**
//     * 重点场所任务上传
//     *
//     * @param action ZDCS_RW_SET
//     * @param YHID   用户ID（必填）
//     * @param ZXID   执行ID（必填）
//     * @param QYBH   区域编号
//     * @param WTMS   问题描述
//     * @param LUSJ   录入时间
//     * @param FXLB   风险类别
//     * @param ZRBM   责任部门
//     * @param YHDJ   隐患等级
//     * @return
//     */
//
//    @POST("ZDCS/ZDCS_RWSC.ashx")
//    Call<UploadPhotosBean> upload_zdcsrw(@Query("Action") String action,
//                                         @Query("YHID") String YHID,
//                                         @Query("ZXID") String ZXID,
//                                         @Query("QYBH") String QYBH,
//                                         @Query("WTMS") String WTMS,
//                                         @Query("LUSJ") String LUSJ,
//                                         @Query("FXLB") String FXLB,
//                                         @Query("ZRBM") String ZRBM,
//                                         @Query("YHDJ") String YHDJ,
//                                         @Body RequestBody body);
//
//
//    /**
//     * 重点场所整改任务上传
//     *
//     * @param action ZDCS_ZG_SET
//     * @param YHID   用户ID（必填）
//     * @param RWID   任务ID（必填）
//     * @param ZXID   执行ID（必填）
//     * @param ZGJG   整改结果（必填）
//     * @return
//     */
//
//    @POST("ZDCS/ZDCS_RWSC.ashx")
//    Call<UploadPhotosBean> upload_ZDCSZGJG(@Query("Action") String action,
//                                           @Query("YHID") String YHID,
//                                           @Query("RWID") String RWID,
//                                           @Query("ZXID") String ZXID,
//                                           @Query("ZGJG") String ZGJG,
//                                           @Body RequestBody body);


}
