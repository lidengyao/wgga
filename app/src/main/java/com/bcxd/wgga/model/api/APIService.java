package com.bcxd.wgga.model.api;

import com.bcxd.wgga.model.bean.BaoJingBean;
import com.bcxd.wgga.model.bean.ChangePwdObject;
import com.bcxd.wgga.model.bean.CommonMessageBean;
import com.bcxd.wgga.model.bean.HeadImgObject;
import com.bcxd.wgga.model.bean.JianChaBean;
import com.bcxd.wgga.model.bean.JianChaSearchBean;
import com.bcxd.wgga.model.bean.JianChaUpdateBean;
import com.bcxd.wgga.model.bean.LoginBean;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.bean.QuestionTypeBean;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.bean.ToxicCompanyMemberBean;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.model.bean.VideoControlBean;
import com.bcxd.wgga.model.bean.W_LoginBean;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.model.bean.ZhengGaiBean;
import com.bcxd.wgga.model.info.BaoJingInfo;
import com.bcxd.wgga.model.info.BaoJingInfoDetail;
import com.bcxd.wgga.model.info.ChildMenuInfo;
import com.bcxd.wgga.model.info.CityenvironmentInfo;
import com.bcxd.wgga.model.info.CommonMessageInfo;
import com.bcxd.wgga.model.info.FuZeRenInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.JianChaDetailInfo;
import com.bcxd.wgga.model.info.JianChaInfo;
import com.bcxd.wgga.model.info.PengListInfo;
import com.bcxd.wgga.model.info.PhoneLoginInfo;
import com.bcxd.wgga.model.info.ProjectDetailInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.RealtimeInfo;
import com.bcxd.wgga.model.info.RuleIndexInfo;
import com.bcxd.wgga.model.info.SheBeiInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.model.info.ZhengGaiInfo;
import com.bcxd.wgga.model.info.ZhengGaiInfoDetail;
import com.bcxd.wgga.model.info.apppicInfo;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jinxh on 16/2/17.
 */
public interface APIService {

    //region 老系统
    //1.1登录
    @POST("user/phoneLogin.do")
    Observable<ResponseBean<PhoneLoginInfo>> phoneLogin(@Query("phone") String phone, @Query("password") String password);


    @POST("user/login")
    Observable<ResponseBean<LoginInfo>> login(@Body LoginBean loginBean);

    @GET("auth/logout")
    Observable<ResponseBean<String>> logout();


    //更新版本检测
    @GET("apppic/apkinfo")
    Observable<ResponseBean<VersionInfo>> checkVersion();

    @GET("user/getuserinfo")
    Observable<ResponseBean<Z_UserInfo>> getuserinfo();


    //环境检测
    @GET("site/list")
    Observable<ResponseBean<ArrayList<GongDiInfo>>> list();


    @Multipart
    @POST("picturedetails/upload")
    Observable<ResponseBean<UpLoadInfo>> upload(@Part("imageFile\"; filename=\"test.png") RequestBody file);

    //更新用户对象信息
    @POST("user/update")
    Observable<ResponseBean<String>> update(@Query("pictureurl") String pictureurl,
                                            @Query("position") String position,
                                            @Query("selfieid") String selfieid,
                                            @Query("uid") String uid,
                                            @Query("usercode") String usercode,
                                            @Query("username") String username,
                                            @Query("userpass") String userpass);

    //更新用户头像
    @POST("user/update")
    Observable<ResponseBean<String>> updateHeadImg(@Body HeadImgObject headImgObject);

    //修改密码
    @POST("user/update")
    Observable<ResponseBean<String>> changepwdSucess(@Body ChangePwdObject changPwdObject);

    @GET("environment/realtimebypid/{pid}")
    Observable<ResponseBean<ArrayList<RealtimeInfo>>> realtimebypid(@Path("pid") Integer pid);

    //检测列表
    @POST("check/searchbystatus/{status}")
    Observable<ResponseBean<JianChaInfo>> searchbystatus(@Body JianChaSearchBean jianChaSearchBean, @Path("status") Integer status);

    //检测详情
    @GET("check/searchbycheckid/{cid}")
    Observable<ResponseBean<JianChaDetailInfo>> checksearchbycheckid(@Path("cid") Integer cid);

    //项目详情
    @GET("project/detail/{pid}")
    Observable<ResponseBean<ProjectDetailInfo>> projectDetail(@Path("pid") Integer pid);

    //消息列表
    @POST("message/list")
    Observable<ResponseBean<XiaoXiInfo>> messagelist(@Body MessageBean messageBean);

    //实时报警列表
    @POST("alert/listbyuidandpage")
    Observable<ResponseBean<BaoJingInfo>> listbyuidandpage(@Body BaoJingBean baoJingBean);

    //历史报警列表
    @POST("alert/hispagebysid/{sid}")
    Observable<ResponseBean<BaoJingInfo>> hispagebysid(@Path("sid") Integer sid, @Body BaoJingBean baoJingBean);

    //报警详情
    @GET("alert/searchreal/{rid}")
    Observable<ResponseBean<BaoJingInfoDetail>> alertsearchrealbirid(@Path("rid") Integer rid);

    //整改列表
    @POST("zhenggai/querypagebycondition")
    Observable<ResponseBean<ZhengGaiInfo>> querypagebycondition(@Body ZhengGaiBean zhengGaiBean);

    //整改详情
    @GET("zhenggai/query/{zid}")
    Observable<ResponseBean<ZhengGaiInfoDetail>> zhenggaiquerybyzid(@Path("zid") Integer zid);

    //视频设备控制开始接口
    @POST("video/start")
    Observable<ResponseBean<String>> videoStart(@Body VideoControlBean videoControlBean);

    //视频设备控制开始接口
    @POST("video/stop")
    Observable<ResponseBean<String>> videoStop(@Body VideoControlBean videoControlBean);


    //消息列表-根据工地sid查询
    @POST("message/list/{sid}")
    Observable<ResponseBean<XiaoXiInfo>> messagelistbysid(@Body MessageBean messageBean, @Path("sid") String sid);

    //问题类型
    @POST("codevalue/listbycondition")
    Observable<ResponseBean<ArrayList<TypeInfo>>> listbycondition_question(@Body QuestionTypeBean questionTypeBean);

    //项目列表
    @GET("project/list/{type}/{level}")
    Observable<ResponseBean<ArrayList<ProjectInfo>>> projectlist(@Path("type") Integer type, @Path("level") Integer level);

    //首页
    @GET("apppic/query/{type}/{pid}")
    Observable<ResponseBean<apppicInfo>> apppicQuery(@Path("type") Integer type, @Path("pid") Integer pid);

    //天气
    @GET("cityenvironment/latest")
    Observable<ResponseBean<CityenvironmentInfo>> Cityenvironment();

    //工地列表by项目id
    @GET("project/getsitebypid/{pid}")
    Observable<ResponseBean<ArrayList<GongDiInfo>>> gongdibyprojectlist(@Path("pid") Integer pid);

    //负责人
    @GET("site/humanduty/{pid}")
    Observable<ResponseBean<ArrayList<FuZeRenInfo>>> humanduty(@Path("pid") Integer pid);

    //新增检查
    @POST("check/add")
    Observable<ResponseBean<String>> checkAdd(@Body JianChaBean jianChaBean);

    //回复新增
    @POST("check/update")
    Observable<ResponseBean<String>> checkAddUpate(@Body JianChaUpdateBean jianChaUpdateBean);

    //修改报警状态
    @GET("alert/updatealertstatus/{rid}/{status}")
    Observable<ResponseBean<String>> updatealertstatus(@Path("rid") Integer rid, @Path("status") Integer status);

    //修改整改状态
    @GET("zhenggai/update/{zid}/{status}")
    Observable<ResponseBean<String>> zhenggaiUpdate(@Path("zid") Integer rid, @Path("status") Integer status);

    @GET("site/graphic/{sid}")
    Observable<ResponseBean<SheBeiInfo>> siteGraphic(@Path("sid") String sid);


    //刷新Access Token
    @POST("auth/refresh-token")
    Observable<ResponseBean<String>> refreshtoken();


//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    @POST("picturedetails/uploadSuccess")
//    Observable<ResponseBean<String>> upload2(@Query("imageBase64") String base64);

    @POST("picturedetails/uploadSuccess")
    Observable<ResponseBean<UpLoadInfo>> upload2(@Query("imageBase64") String imgdata);

    //2.1获取消息接口说明（提示信息、告警信息）
    @POST("message/commonMessage.do")
    Observable<CommonMessageBean<ArrayList<CommonMessageInfo>>> commonMessage(@Query("userId") String userId, @Query("messageMode") String messageMode, @Query("index") String index, @Query("pageSize") String pageSize);

    //5.5获取功能导航界面的Banner图标
    @POST("config/bannerImg.do")
    Observable<ResponseBean<ArrayList<String>>> bannerImg();

    //5.1读取所有大棚状态表接口说明
    @POST("status/pengList.do")
    Observable<ResponseBean<ArrayList<PengListInfo>>> pengList(@Query("userId") String phone, @Query("fnMode") String fnMode);

//
//    @Multipart
//    @POST("img/uploadSuccess.do")
//    Observable<UploadfileBean> uploadImage(
//            @PartMap Map<String, RequestBody> path,
//            @Query("type") int type,
//            @Query("id") String id);

//endregion
    //危管系统-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @POST("user/login")
    Observable<ResponseBean<W_LoginInfo>> w_login(@Body W_LoginBean w_loginBean);

    //获取模块列表
    @GET("rule/index")
    Observable<ResponseBean<ArrayList<RuleIndexInfo>>> ruleIndex();

    //根据模块获得模块下菜单列表
    @GET("rule/getMenuByUser/{moduleId}")
    Observable<ResponseBean<ArrayList<ChildMenuInfo>>> getMenuByUser(@Path("moduleId") int moduleId);

    //剧毒人员管理查询
    @GET("toxicCompanyMember/list")
    Observable<ResponseBean<ArrayList<ToxicCompanyMemberInfo>>> toxicCompanyMember();

    //剧毒人员管理添加
    @POST("toxicCompanyMember/add")
    Observable<ResponseBean<String>> toxicCompanyMemberadd(@Body ToxicCompanyMemberBean toxicCompanyMemberBean);

    //剧毒人员管理查询个人
    @GET("toxicCompanyMember/{id}")
    Observable<ResponseBean<ToxicCompanyMemberInfo>> toxicCompanyMemberDetail(@Path("id") int id);
}
