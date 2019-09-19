
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frame.cache.util.RedisClientUtils;
import com.frame.common.common.CommonCode;
import com.frame.common.common.CommonInternationalText;
import com.frame.common.constants.SystemConstant;
import com.frame.common.core.beans.WherePrams;
import com.frame.common.core.dao.idc.Healthy.HealthyEncyclopediaDao;
import com.frame.common.core.dao.idc.Healthy.HealthySubjectDao;
import com.frame.common.core.dao.idc.Healthy.HealthyTopicDao;
import com.frame.common.core.dao.idc.chat.ChatListDao;
import com.frame.common.core.dao.idc.chat.ChatRecordDao;
import com.frame.common.core.dao.idc.collection.CollectionDao;
import com.frame.common.core.dao.idc.disease.DiseasedPartDao;
import com.frame.common.core.dao.idc.doctor.DepartmentDao;
import com.frame.common.core.dao.idc.doctor.DoctorDao;
import com.frame.common.core.dao.idc.doctor.ExamDao;
import com.frame.common.core.dao.idc.doctor.HospitalDao;
import com.frame.common.core.dao.idc.doctor.InterFlowCircleDao;
import com.frame.common.core.dao.idc.doctor.MessageDao;
import com.frame.common.core.dao.idc.doctor.PositionalDao;
import com.frame.common.core.dao.idc.doctor.StudioLabelDao;
import com.frame.common.core.dao.idc.doctor.TrainDao;
import com.frame.common.core.dao.idc.inquiring.InquiringPersonDao;
import com.frame.common.core.dao.idc.order.DetailRecordDao;
import com.frame.common.core.dao.idc.order.FollowupOrdeRecordDao;
import com.frame.common.core.dao.idc.order.FollowupOrderDoctorInfoDao;
import com.frame.common.core.dao.idc.order.InquisitionRecordInfoDao;
import com.frame.common.core.dao.idc.order.OnlineInquiryOrdeRecordDao;
import com.frame.common.core.dao.idc.order.OrderCommentDao;
import com.frame.common.core.dao.idc.order.PayOrderInfoDao;
import com.frame.common.core.dao.idc.serv.DoctorServiceDao;
import com.frame.common.core.dao.idc.serv.ServiceTimeDao;
import com.frame.common.core.dao.idc.serv.TimedServiceDao;
import com.frame.common.core.dao.idc.servp.FollowupDoctorInfoDao;
import com.frame.common.core.dao.idc.servp.HospitalFollowUpBagDao;
import com.frame.common.core.dao.idc.store.BeadHouseDao;
import com.frame.common.core.dao.idc.store.ChannelMenuDao;
import com.frame.common.core.dao.idc.store.ClerkDao;
import com.frame.common.core.dao.idc.store.MenuRightDao;
import com.frame.common.core.dao.idc.store.ModoulePropertyDao;
import com.frame.common.core.dao.idc.store.ModoulePropertyExtendDao;
import com.frame.common.core.dao.idc.store.ModoulePropertyResultUserDao;
import com.frame.common.core.dao.idc.store.ModuleMenuDao;
import com.frame.common.core.dao.idc.store.PadAdDao;
import com.frame.common.core.dao.idc.store.PharmacyDao;
import com.frame.common.core.dao.idc.studio.StudioDao;
import com.frame.common.core.dao.idc.studio.StudioDoctorDao;
import com.frame.common.core.dao.idc.sys.AdminDao;
import com.frame.common.core.dao.idc.sys.AdminMerchantDao;
import com.frame.common.core.dao.idc.sys.ChannelDao;
import com.frame.common.core.dao.idc.sys.GoodsDao;
import com.frame.common.core.dao.idc.sys.GoodsTypeDao;
import com.frame.common.core.dao.idc.sys.HealthLectureContentDao;
import com.frame.common.core.dao.idc.sys.HealthLectureMenuDao;
import com.frame.common.core.dao.idc.sys.SysConfigDao;
import com.frame.common.core.dao.idc.sys.TemplateDao;
import com.frame.common.core.dao.idc.user.BodyDataDao;
import com.frame.common.core.dao.idc.user.DetectionTypeDao;
import com.frame.common.core.dao.idc.user.DeviceDao;
import com.frame.common.core.dao.idc.user.ElectronicReceiptDao;
import com.frame.common.core.dao.idc.user.GoodsGroupDao;
import com.frame.common.core.dao.idc.user.LogonRecordDao;
import com.frame.common.core.dao.idc.user.MemberLevelDao;
import com.frame.common.core.dao.idc.user.PadUserDao;
import com.frame.common.core.dao.idc.user.SignRecordDao;
import com.frame.common.core.dao.idc.user.UserAgreementDao;
import com.frame.common.core.dao.idc.user.UserArchivesDao;
import com.frame.common.core.dao.idc.user.UserBeadHouseRecordDao;
import com.frame.common.core.dao.idc.user.UserDao;
import com.frame.common.core.domain.idc.chat.ChatList;
import com.frame.common.core.domain.idc.chat.ChatRecord;
import com.frame.common.core.domain.idc.disease.DiseasedPart;
import com.frame.common.core.domain.idc.doctor.Department;
import com.frame.common.core.domain.idc.doctor.Doctor;
import com.frame.common.core.domain.idc.doctor.Hospital;
import com.frame.common.core.domain.idc.doctor.Positional;
import com.frame.common.core.domain.idc.healthy.HealthyEncyclopedia;
import com.frame.common.core.domain.idc.healthy.HealthySubject;
import com.frame.common.core.domain.idc.healthy.HealthyTopic;
import com.frame.common.core.domain.idc.infomations.Exam;
import com.frame.common.core.domain.idc.infomations.InterFlowCircle;
import com.frame.common.core.domain.idc.infomations.Message;
import com.frame.common.core.domain.idc.infomations.Train;
import com.frame.common.core.domain.idc.inquiring.InquiringPerson;
import com.frame.common.core.domain.idc.order.DetailRecord;
import com.frame.common.core.domain.idc.order.FollowupOrdeRecord;
import com.frame.common.core.domain.idc.order.FollowupOrderDoctorInfo;
import com.frame.common.core.domain.idc.order.InquisitionRecordInfo;
import com.frame.common.core.domain.idc.order.OnlineInquiryOrdeRecord;
import com.frame.common.core.domain.idc.order.OrderComment;
import com.frame.common.core.domain.idc.pay.AlipayAsyncRresponse;
import com.frame.common.core.domain.idc.pay.PayOrderInfo;
import com.frame.common.core.domain.idc.serv.DoctorService;
import com.frame.common.core.domain.idc.serv.ServiceTime;
import com.frame.common.core.domain.idc.serv.TimedService;
import com.frame.common.core.domain.idc.servp.FollowupDoctorInfo;
import com.frame.common.core.domain.idc.servp.HospitalFollowUpBag;
import com.frame.common.core.domain.idc.store.BeadHouse;
import com.frame.common.core.domain.idc.store.ChannelMenu;
import com.frame.common.core.domain.idc.store.Clerk;
import com.frame.common.core.domain.idc.store.MenuRight;
import com.frame.common.core.domain.idc.store.ModouleProperty;
import com.frame.common.core.domain.idc.store.ModoulePropertyExtend;
import com.frame.common.core.domain.idc.store.ModoulePropertyResultUser;
import com.frame.common.core.domain.idc.store.ModuleMenu;
import com.frame.common.core.domain.idc.store.PadAd;
import com.frame.common.core.domain.idc.store.Pharmacy;
import com.frame.common.core.domain.idc.studio.Studio;
import com.frame.common.core.domain.idc.studio.StudioDoctor;
import com.frame.common.core.domain.idc.studio.StudioLabel;
import com.frame.common.core.domain.idc.sys.Admin;
import com.frame.common.core.domain.idc.sys.AdminMerchant;
import com.frame.common.core.domain.idc.sys.Channel;
import com.frame.common.core.domain.idc.sys.Goods;
import com.frame.common.core.domain.idc.sys.GoodsType;
import com.frame.common.core.domain.idc.sys.HealthLectureContent;
import com.frame.common.core.domain.idc.sys.HealthLectureMenu;
import com.frame.common.core.domain.idc.sys.SysConfig;
import com.frame.common.core.domain.idc.sys.Template;
import com.frame.common.core.domain.idc.user.BodyData;
import com.frame.common.core.domain.idc.user.CollectionRecord;
import com.frame.common.core.domain.idc.user.DetectionType;
import com.frame.common.core.domain.idc.user.Device;
import com.frame.common.core.domain.idc.user.ElectronicReceipt;
import com.frame.common.core.domain.idc.user.GoodsGroup;
import com.frame.common.core.domain.idc.user.LogonRecord;
import com.frame.common.core.domain.idc.user.MemberLevel;
import com.frame.common.core.domain.idc.user.PadUser;
import com.frame.common.core.domain.idc.user.SignRecord;
import com.frame.common.core.domain.idc.user.User;
import com.frame.common.core.domain.idc.user.UserAgreement;
import com.frame.common.core.domain.idc.user.UserArchives;
import com.frame.common.dto.BasicResponse;
import com.frame.common.dto.TempFormMap;
import com.frame.common.exceptions.BizException;
import com.frame.common.page.PagerModel;
import com.frame.common.utils.CommonUtil;
import com.frame.common.utils.SecurityUtil;
import com.frame.common.utils.StringUtil;
import com.frame.common.utils.ali.AliBigFishUtil;
import com.frame.common.utils.alipay.AlipayConfig;
import com.frame.common.utils.alipay.OrderInfoUtil2_0;
import com.frame.common.utils.password.PasswordHelper;
import com.frame.common.utils.weixin.GetWxOrderno;
import com.frame.common.utils.weixin.RequestHandler;
import com.frame.common.utils.weixin.Sha1Util;
import com.frame.common.utils.weixin.TenpayUtil;
import com.frame.service.sys.SysConfigBiz;
import com.io.rong.RongCloud;
import com.io.rong.models.TokenResult;
import com.io.rong.util.ConversionTimeUtil;
import com.io.rong.util.SengSystemMessageByRongYunUtil;

@Service("UserBiz")
public class UserBiz {

	@Autowired
	private StudioLabelDao studioLabelDao;
	@Autowired
	private HospitalFollowUpBagDao hospitalFollowUpBagDao;
	@Autowired
	private FollowupDoctorInfoDao followupDoctorInfoDao;
	@Autowired
	private FollowupOrdeRecordDao followupOrdeRecordDao;
	@Autowired
	private FollowupOrderDoctorInfoDao followupOrderDoctorInfoDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private StudioDoctorDao studioDoctorDao;
	@Autowired
	private StudioDao studioDao;
	@Autowired
	private HospitalDao hospitalDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PositionalDao positionalDao;
	@Autowired
	private DoctorServiceDao doctorServiceDao;
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private BodyDataDao bodyDataDao;
	@Autowired
	private MemberLevelDao memberLevelDao;
	@Autowired
	private PharmacyDao pharmacyDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AdminMerchantDao adminMerchantDao;
	@Autowired
	private BeadHouseDao beadHouseDao;
	@Autowired
	private SignRecordDao signRecordDao;
	@Autowired
	private HealthLectureMenuDao healthLectureMenuDao;
	@Autowired
	private HealthLectureContentDao healthLectureContentDao;
	@Autowired
	private ElectronicReceiptDao electronicReceiptDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private UserBeadHouseRecordDao userBeadHouseRecordDao;
	@Autowired
	private ClerkDao clerkDao;
	@Autowired
	private ChannelMenuDao channelMenuDao;
	@Autowired
	private ModuleMenuDao moduleMenuDao;
	@Autowired
	private MenuRightDao menuRightDao;
	@Autowired
	private ModoulePropertyDao modoulePropertyDao;
	@Autowired
	private ModoulePropertyExtendDao modoulePropertyExtendDao;
	@Autowired
	private ModoulePropertyResultUserDao modoulePropertyResultUserDao;
	@Autowired
	private DetectionTypeDao detectionTypeDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private GoodsTypeDao goodsTypeDao;
	@Autowired
	private GoodsGroupDao goodsGroupDao;
	@Autowired
	private UserArchivesDao userArchivesDao;
	@Autowired
	private ServiceTimeDao serviceTimeDao;
	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private HealthyEncyclopediaDao healthyEncyclopediaDao;
	@Autowired
	private InquiringPersonDao inquiringPersonDao;
	@Autowired
	private DiseasedPartDao diseasedPartDao;
	@Autowired
	private OnlineInquiryOrdeRecordDao onlineInquiryOrdeRecordDao;
	@Autowired
	private PadUserDao padUserDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private PadAdDao padAdDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private SysConfigBiz sysConfigBiz;
	@Autowired
	private InquisitionRecordInfoDao inquisitionRecordInfoDao;
	@Autowired
	private OrderCommentDao orderCommentDao;
	@Autowired
	private ChatRecordDao chatRecordDao;
	@Autowired
	private CollectionDao collectionDao;
	@Autowired
	private DetailRecordDao detailRecordDao;
	@Autowired
	private ChatListDao  chatListDao;
	@Autowired
	private TrainDao trainDao;
	@Autowired
	private InterFlowCircleDao  interFlowCircleDao;
	@Autowired
	private ExamDao examDao;
	@Autowired
	private UserAgreementDao userAgreementDao;
	@Autowired
	private HealthySubjectDao healthySubjectDao;
	@Autowired
	private HealthyTopicDao	healthyTopicDao;
	@Autowired
	private PayOrderInfoDao  payOrderInfoDao;
	@Autowired
	private LogonRecordDao logonRecordDao;
	@Autowired
	private TimedServiceDao timedServiceDao;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public BasicResponse getOrderPage(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		// 语言
		Integer langType = tempDto.getInt("langType");
		// 区号
		String intPhoneCode = tempDto.getStr("intPhoneCode");
		//用户
		String u_id=tempDto.getStr("user_id");
		//token
		String token=tempDto.getStr("token");
		//查询用户
		User user=this.userDao.getEntity(u_id);
		
		if(!token.equals(user.getToken())){
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.TOKEN_ILLEGAL);
			b.setCode(String.valueOf(CommonCode.TOKEN_ILLEGAL));
			return b;
		}
		
		//渠道
		String app_id=tempDto.getStr("app_id");

		PagerModel model = this.followupOrdeRecordDao.getOrderPage(tempDto);
		List<FollowupOrdeRecord> list = model.getRows();
		for (FollowupOrdeRecord followupOrdeRecord : list) {
			// 查询服务包
			HospitalFollowUpBag hospitalFollowUpBag = this.hospitalFollowUpBagDao
					.getEntity(followupOrdeRecord.getHospital_followup_id());
			if (hospitalFollowUpBag == null) {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.SERVP_NOT_EXIST);
				return b;
			}
			followupOrdeRecord.setServpImg(hospitalFollowUpBag.getIcon_img());
			followupOrdeRecord.setServpName(hospitalFollowUpBag.getName());
			//查询工作室
			Studio studio=this.studioDao.getEntity(followupOrdeRecord.getStudio_id());
			if(studio!=null){
				followupOrdeRecord.setStudio_img(studio.getIcon_img());
			}
			WherePrams where = new WherePrams();
			where.and("followup_order_record_id", "=",
					followupOrdeRecord.getId());
			List<FollowupOrderDoctorInfo> orderDoctor = this.followupOrderDoctorInfoDao
					.getEntitys(where);
			if (orderDoctor.size() > 0) {
				for (FollowupOrderDoctorInfo followupOrderDoctorInfo : orderDoctor) {
					//查询会话
					WherePrams prams =new WherePrams();
					prams.and("order_id","=", followupOrdeRecord.getId());
					prams.and("order_type","=", 1);
					prams.and("u_id","=", u_id);
					prams.and("doctor_id","=", followupOrderDoctorInfo.getDoctor_id());
					List<ChatList> chatList=this.chatListDao.getEntitys(prams);
					if(chatList.size()>0){
						followupOrderDoctorInfo.setChat_list_id(chatList.get(0).getId());
					}
					//查询是否评论
					WherePrams  p=new WherePrams();
					p.and("app_id","=", app_id);
					p.and("order_id","=", followupOrdeRecord.getId());
					p.and("doctor_id","=", followupOrderDoctorInfo.getDoctor_id());
					OrderComment  orderComment=this.orderCommentDao.getEntity(p);
					if(orderComment==null){
						followupOrderDoctorInfo.setComment_type(0);
					}else{
						followupOrderDoctorInfo.setComment_type(1);
					}
					// 查询医生
					Doctor doctor = this.doctorDao
							.getEntity(followupOrderDoctorInfo.getDoctor_id());
					followupOrderDoctorInfo.setDoctor_head_img(doctor
							.getHead_img());
					followupOrderDoctorInfo.setDoctor_nick_name(doctor
							.getNick_name());
					followupOrderDoctorInfo.setDoctor_adept(doctor.getAdept());

					// 医院
					Hospital hospital = this.hospitalDao.getEntity(doctor
							.getHospital_id());
					followupOrderDoctorInfo.setDoctor_hospital_name(hospital
							.getName());
					// 科室
					Department department = this.departmentDao.getEntity(doctor
							.getDepartment_id());
					followupOrderDoctorInfo
					.setDoctor_department_name(department.getName());
					// 职位
					Positional positional = this.positionalDao.getEntity(doctor
							.getPositional_id());
					followupOrderDoctorInfo
					.setDoctor_positional_name(positional.getName());
					//服务时间查询
					WherePrams pram=new WherePrams();
					pram.and("doctor_id","=",followupOrderDoctorInfo.getDoctor_id());
					pram.and("type","=", 1);
					pram.and("state","=", 1);
					List<ServiceTime> time=this.serviceTimeDao.getEntitys(pram);
					if(time.size()>0){
						for (ServiceTime serviceTime : time) {
							followupOrderDoctorInfo.getTime().add(serviceTime);
						}
					}
					// 剩余服务次数
					WherePrams wherePrams = new WherePrams();
					wherePrams.and("hospital_bag_id", "=",
							followupOrdeRecord.getHospital_followup_id());
					List<FollowupDoctorInfo> doctorInfo = this.followupDoctorInfoDao
							.getEntitys(wherePrams);
					if (doctorInfo.size() > 0) {
						for (FollowupDoctorInfo followupDoctorInfo : doctorInfo) {
							if (followupDoctorInfo.getType() == followupOrderDoctorInfo
									.getType()) {
								followupOrderDoctorInfo
								.setCounts(followupDoctorInfo
										.getService_times());
							}
						}
					}

					followupOrdeRecord.getOrderDoctor().add(
							followupOrderDoctorInfo);
				}
			}
		}
		b.setObject(list);
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public BasicResponse addRegister(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		String login_name = tempDto.getStr("login_name");
		String password = tempDto.getStr("password");
		String app_id = tempDto.getStr("app_id");
		String intPhoneCode = tempDto.getStr("intPhoneCode");
		// 验证用户是否存在
		if (!StringUtil.isNull(login_name)) {
			WherePrams where = new WherePrams();
			where.and("login_name", "=", login_name);
			where.and("app_id", "=", app_id);
			where.and("del_state", "=", 0);
			User userPojo = this.userDao.getEntity(where);
			if (userPojo != null) {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.ACCOUNT_ALREADY_EXISTS);
				return b;
			}
		}

		User user = new User();
		user.setBaseInfo();
		user.setApp_id(app_id);
		user.setLogin_name(login_name);

		String md5Pssword = SecurityUtil.md5(login_name, password);
		user.setPassword(md5Pssword);
		if(app_id.equals("6020")){
			// 截取手机号码后4位
			user.setNick_name(login_name.substring(login_name.length() - 4,
					login_name.length()) + "洲际健康");			
		}else if(app_id.equals("6021")){
			// 截取手机号码后4位
			user.setNick_name(login_name.substring(login_name.length() - 4,
					login_name.length()) + "Здоровье IDG");		
		}
		// 默认一岁
		user.setBirthday("2017-06-13");
		user.setSex(1);
		user.setHeight("165");
		user.setWeight("60");
		user.setDel_state(0);
		user.setHead_img("http://p7dfdhwq4.bkt.clouddn.com/default/1533802865896.png");
		user.setArchives_number(CommonUtil.getDefaulOrderNumber(app_id, "1", 1));
		// 生成token 令牌
		// 生成令牌
		String token = SecurityUtil.getTokenStr(login_name, password);
		user.setToken(token);

		// 生成融云账号信息
		// 融云用户名
		String ry_name = user.getNick_name();
		// 融云用户头像
		String ry_head_img = user.getHead_img();
		String ry_user_id = "";

		ry_user_id = user.getId();

		RongCloud rongC = CommonUtil.getRongCloudByAppId(app_id);
		TokenResult tokenResult = rongC.user.getToken(ry_user_id, ry_name,ry_head_img);
		user.setRy_token(tokenResult.getToken());
		
		this.userDao.save(user);

		// 系统消息 
		Message message = new Message();
		message.setType(1);
		message.setReceiver(user.getId());
		message.setReceiver_type(2);
		message.setIs_show(0);
		if(app_id.equals("6020")){
			message.setTitle("欢迎您使用洲际健康");
			message.setContent("如果您体验中遇到任务问题,可以随时通过联系客服向我们反馈");			
		}else if(app_id.equals("6021")){
			message.setTitle("Добро пожаловать в IDG");
			message.setContent("Eсли у Вас возникнут какие-либо проблемы во время использования, Вы всегда можете обратиться в службу поддержки клиентов");
		}
		
		// 返回客服融云id
		JSONObject jObj = new JSONObject();
		jObj.put("id","008d4887bbox6020b0442abde2455654");
		String remarks = jObj.toString();
		message.setRemarks(remarks);
		this.messageDao.save(message);
		//获取系统配置
		SysConfig sysConfig = sysConfigBiz.getSystemConfigByAppId(app_id);
		//
		JSONObject jsonCotent=new JSONObject();
		jsonCotent.put("message_id",message.getId());
		//推送医生
		List<String> userids = new ArrayList<String>();
		userids.add(user.getId());
		String message2="";
		String messageType2=null;
		
		if(app_id.equals("6020")){
			message2 +="欢迎您使用洲际健康";			
		}else if(app_id.equals("6021")){
			message2 +="Добро пожаловать в IDG";	
		}
		
		String extraType2="m_type_is_register_success";
		SengSystemMessageByRongYunUtil.sengCustomSystemMessageByRongYun(app_id,
				CommonInternationalText.OFFICIAL_DOCTOR_RONGYUN_USER_ID, userids, message2, null,
				extraType2, sysConfig.getRy_key(), sysConfig.getRy_secret(),jsonCotent.toString());

		if(app_id.equals("6020")){
			//发送短信
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login_name", CommonUtil.getPhoneByLoginName(user.getLogin_name()));
			map.put("code", AliBigFishUtil.VERIFY_ZJYL_REMIND_REGISTERED);
			map.put("accessKeyId", sysConfig.getAli_sms_app_key());
			map.put("accessKeySecret", sysConfig.getAli_sms_secret());
			map.put("signName", sysConfig.getAli_sms_autograph());
			AliBigFishUtil.sendSMSALICommon(map, null);			
		}else if(app_id.equals("6021")){
			//发送短信(英文)(后期替换俄文)
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("login_name", CommonUtil.getPhoneByLoginName(user.getLogin_name()));
			map.put("code", AliBigFishUtil.VERIFY_ZJYL_REMIND_REGISTERED_English);
			map.put("accessKeyId", sysConfig.getAli_sms_app_key());
			map.put("accessKeySecret", sysConfig.getAli_sms_secret());
			map.put("signName", sysConfig.getAli_sms_autograph());
			AliBigFishUtil.sendSMSALICommon(map, null);	
		}
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("id", user.getId());
		reMap.put("login_name", user.getLogin_name());
		reMap.put("token", user.getToken());
		reMap.put("ry_token", user.getRy_token());
		reMap.put("nick_name", user.getNick_name());
		reMap.put("birthday", user.getBirthday());
		reMap.put("sex", user.getSex());
		reMap.put("height", user.getHeight());
		reMap.put("weight", user.getWeight());
		reMap.put("head_img", user.getHead_img());
		b.setObject(reMap);
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicResponse Login(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		// 判断是验证码登录还是密码登录
		String phone = tempDto.getStr("login_name");

		String code = tempDto.getStr("code");

		String password = tempDto.getStr("password");

		String app_id = tempDto.getStr("app_id");

		String u_type = tempDto.getStr("u_type");
		

		User user;
		if (!StringUtil.isNull(code)) {
			// 验证码登录
			// 验证验证码 终端类型 0 医生端 1机器人端 2用户端 3网站端 4微信端
			String key = String.format(SystemConstant.T_VERI_CODE, app_id,
					u_type, phone);
			Object redis_code = RedisClientUtils.get(key);
			if (!code.equals(redis_code)) {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.VERI_CODE_DOES_NOT_VERIFICATION);
				return b;
			}
			WherePrams where = new WherePrams();
			where.and("login_name", "=", phone);
			where.and("del_state", "=", 0);
			where.and("app_id", "=", app_id);
			user = this.userDao.getEntity(where);
			if (user != null) {
				// 生成令牌
				String token = SecurityUtil.getTokenStr(user.getLogin_name(),
						user.getPassword());

				user.setToken(token);
				// 融云登录刷新

				// 生成融云账号信息
				// 融云用户名
				String ry_name = user.getNick_name();
				// 融云用户头像
				String ry_head_img = user.getHead_img();
				String ry_user_id = "";

				ry_user_id = user.getId();

				RongCloud rongC = CommonUtil.getRongCloudByAppId(app_id);
				TokenResult tokenResult = rongC.user.getToken(ry_user_id,
						ry_name, ry_head_img);
				user.setRy_token(tokenResult.getToken());

				this.userDao.update(user);
				// 清除登录验证码
				/* RedisClientUtils.del(key); */

				Map<String, Object> reMap = new HashMap<String, Object>();
				reMap.put("id", user.getId());
				reMap.put("login_name", user.getLogin_name());
				reMap.put("token", user.getToken());
				reMap.put("ry_token", user.getRy_token());
				reMap.put("nick_name", user.getNick_name());
				reMap.put("birthday", user.getBirthday());
				reMap.put("sex", user.getSex());
				reMap.put("height", user.getHeight());
				reMap.put("weight", user.getWeight());
				reMap.put("head_img", user.getHead_img());
				b.setObject(reMap);
				return b;
			} else {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.ACCOUNT_DOES_NOT_EXIST);
				return b;
			}

		} else {
			// 账号密码登录
			// 根据电话号码查询到数据
			WherePrams where = new WherePrams();
			where.and("login_name", "=", phone);
			where.and("del_state", "=", 0);
			where.and("app_id", "=", app_id);
			user = this.userDao.getEntity(where);
			if (user != null) {
				// 核对密码
				String md5Pssword = SecurityUtil.md5(phone, password);
				if (md5Pssword.equals(user.getPassword())) {
					// 生成令牌
					String token = SecurityUtil.getTokenStr(
							user.getLogin_name(), user.getPassword());
					user.setToken(token);

					// 生成融云账号信息
					// 融云用户名
					String ry_name = user.getNick_name();
					// 融云用户头像
					String ry_head_img = user.getHead_img();
					String ry_user_id = "";

					ry_user_id = user.getId();

					RongCloud rongC = CommonUtil.getRongCloudByAppId(app_id);
					TokenResult tokenResult = rongC.user.getToken(ry_user_id,
							ry_name, ry_head_img);
					user.setRy_token(tokenResult.getToken());

					this.userDao.update(user);
					Map<String, Object> reMap = new HashMap<String, Object>();
					reMap.put("id", user.getId());
					reMap.put("login_name", user.getLogin_name());
					reMap.put("token", user.getToken());
					reMap.put("ry_token", user.getRy_token());
					reMap.put("nick_name", user.getNick_name());
					reMap.put("birthday", user.getBirthday());
					reMap.put("sex", user.getSex());
					reMap.put("height", user.getHeight());
					reMap.put("weight", user.getWeight());
					reMap.put("head_img", user.getHead_img());
					b.setObject(reMap);
					return b;
				} else {
					b.setDefultFailInfo();
					b.setMessage(CommonInternationalText.PASSWORD_DOES_NOT_CORRECT);
					return b;
				}
			} else {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.ACCOUNT_DOES_NOT_EXIST);
				return b;
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicResponse modify(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		String id = tempDto.getStr("user_id");

		String nick_name = tempDto.getStr("nick_name");
		String birthday = tempDto.getStr("birthday");
		String sexStr = tempDto.getStr("sex");

		String height = tempDto.getStr("height");
		String weight = tempDto.getStr("weight");
		String head_img = tempDto.getStr("head_img");
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		// 根据用户的user_id 查询用户数据
		User user = this.userDao.getEntity(id);
		
		//效验token
		String token=tempDto.getStr("token");
		if(!token.equals(user.getToken())){
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.TOKEN_ILLEGAL);
			b.setCode(String.valueOf(CommonCode.TOKEN_ILLEGAL));
			return b;
		}
		
		if (user != null) {
			if (!StringUtil.isNull(nick_name)) {
				user.setNick_name(nick_name);
			}
			if (!StringUtil.isNull(birthday)) {
				user.setBirthday(birthday);
			}
			if (!StringUtil.isNull(sexStr)) {
				Integer sex = Integer.valueOf(sexStr);
				user.setSex(sex);
			}
			if (!StringUtil.isNull(height)) {
				user.setHeight(height);
			}
			if (!StringUtil.isNull(weight)) {
				user.setWeight(weight);
			}
			if (!StringUtil.isNull(head_img)) {
				user.setHead_img(head_img);
			}
			reMap.put("id", user.getId());
			reMap.put("login_name", user.getLogin_name());
			reMap.put("nick_name", user.getNick_name());
			reMap.put("birthday", user.getBirthday());
			reMap.put("sex", user.getSex());
			reMap.put("height", user.getHeight());
			reMap.put("token", user.getToken());
			reMap.put("weight", user.getWeight());
			reMap.put("head_img", user.getHead_img());
			reMap.put("ry_token", user.getRy_token());
			this.userDao.update(user);
		}
		b.setObject(reMap);
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicResponse getById(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		String id = tempDto.getStr("id");
		User user = this.userDao.getEntity(id);
		if (user != null) {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("id", user.getId());
			reMap.put("login_name", user.getLogin_name());
			reMap.put("token", user.getToken());
			reMap.put("ry_token", user.getRy_token());
			reMap.put("nick_name", user.getNick_name());
			reMap.put("birthday", user.getBirthday());
			reMap.put("sex", user.getSex());
			reMap.put("height", user.getHeight());
			reMap.put("weight", user.getWeight());
			reMap.put("head_img", user.getHead_img());
			b.setObject(reMap);
		} else {
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.ACCOUNT_DOES_NOT_EXIST);
			return b;
		}
		return b;
	}

	@SuppressWarnings({ "rawtypes" })
	public BasicResponse getResetPassword(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		String phone = tempDto.getStr("login_name");
		String code = tempDto.getStr("code");
		String app_id = tempDto.getStr("app_id");
		String password = tempDto.getStr("password");
		String u_type = tempDto.getStr("u_type");

		// 验证验证码 终端类型 0 医生端 1机器人端 2用户端 3网站端 4微信端
		String key = String.format(SystemConstant.T_VERI_CODE, app_id, u_type,
				phone);
		Object redis_code = RedisClientUtils.get(key);

		if(StringUtil.isNull(redis_code)){
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.CLOCK_CODE_NOT_END);
			return b;
		}
		if (!code.equals(redis_code)) {
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.VERI_CODE_DOES_NOT_VERIFICATION);
			return b;
		}
		// 查询用户数据
		WherePrams where = new WherePrams();
		where.and("login_name", "=", phone);
		where.and("app_id", "=", app_id);
		where.and("del_state", "=", 0);
		User user = this.userDao.getEntity(where);
		/*//token 效验
		String token=tempDto.getStr("token");
		if(!token.equals(user.getToken())){
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.TOKEN_ILLEGAL);
			b.setCode(String.valueOf(CommonCode.TOKEN_ILLEGAL));
			return b;
		}*/
		
		if (user != null) {
			String newpssword = SecurityUtil.md5(phone, password);
			user.setPassword(newpssword);
			this.userDao.update(user);
			// 清除验证码
			RedisClientUtils.del(key);
		} else {
			b.setDefultFailInfo();
			b.setMessage(CommonInternationalText.ACCOUNT_DOES_NOT_EXIST);
			return b;
		}
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicResponse getValidationPhone(TempFormMap tempDto)
			throws Exception {
		BasicResponse b = new BasicResponse();
		String phone = tempDto.getStr("login_name");
		String app_id = tempDto.getStr("app_id");
		// 查询用户数据
		WherePrams where = new WherePrams();
		where.and("login_name", "=", phone);
		where.and("app_id", "=", app_id);
		where.and("del_state", "=", 0);
		User user = this.userDao.getEntity(where);
		if (user != null) {
			b.setObject("200");
		} else {
			b.setObject("600");
		}
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BasicResponse getStudioDoctor(TempFormMap tempDto) throws Exception {
		BasicResponse b = new BasicResponse();
		// 判断二维码扫描还是 直接搜索 1 二维码 2名字搜索
		Integer type = tempDto.getInt("type");
		// 二维码id
		String u_id = tempDto.getStr("u_id");
		// app_id
		String app_id = tempDto.getStr("app_id");
		
		// 对外输出对象
		List<Doctor> temp = new ArrayList<Doctor>();

		// 二维码搜索医生(精确搜索医生)
		Doctor doctor;
		if (type == 1) {
			WherePrams where = new WherePrams();
			where.and("id", "=", u_id);
			where.and("del_state", "=", 0);
			where.and("app_id", "=", app_id);
			doctor = this.doctorDao.getEntity(where);
			if (doctor == null) {
				b.setDefultFailInfo();
				b.setMessage(CommonInternationalText.ACCOUNT_DOES_NOT_EXIST);
				return b;
			} else {
				// 查询当前医生是否开通的 院外随访服务
				WherePrams serv = new WherePrams();
				serv.and("doctor_id", "=", doctor.getId());
				serv.and("type", "=", 4);
				serv.and("state", "=", 1);
				DoctorService doctorService = this.doctorServiceDao
						.getEntity(serv);
				if (doctorService == null) {
					b.setDefultFailInfo();
					b.setMessage(CommonInternationalText.DOCTOR_SERV_NO_EXIST);
					return b;
				}
				// 加入的工作室 和创建的工作室
				WherePrams studiodoc = new WherePrams();
				studiodoc.and("doctor_id", "=", doctor.getId());
				studiodoc.and("state","=",1);
				List<StudioDoctor> studioDoctor = this.studioDoctorDao.getEntitys(studiodoc);
				if (studioDoctor.size() > 0) {
					// 循环查询工作室
					for (StudioDoctor studioDoctor2 : studioDoctor) {
						// 获取必要数据
						Doctor d = new Doctor();
						d.setLogin_name(doctor.getLogin_name());
						d.setNick_name(doctor.getNick_name());
						d.setHospital_id(doctor.getHospital_id());
						// 查询医院
						Hospital hospital = this.hospitalDao.getEntity(d
								.getHospital_id());
						if (hospital != null) {
							d.setHospital_name(hospital.getName());
						}

						// 查询科室
						WherePrams departmentWhere = new WherePrams();
						departmentWhere.and("app_id", "=", app_id);
						departmentWhere.and("id", "=",
								doctor.getDepartment_id());
						Department department = this.departmentDao
								.getEntity(departmentWhere);

						d.setDepartment_name(department.getName());

						// 查询职位
						WherePrams positionalWhere = new WherePrams();
						positionalWhere.and("app_id", "=", app_id);
						positionalWhere.and("id", "=",
								doctor.getPositional_id());
						Positional positional = this.positionalDao
								.getEntity(doctor.getPositional_id());

						d.setPositional_name(positional.getName());

						// 擅长
						d.setAdept(doctor.getAdept());
						// 医生id
						d.setId(doctor.getId());
						// 头像
						d.setHead_img(doctor.getHead_img());

						// 工作室
						WherePrams studio = new WherePrams();
						studio.and("id", "=", studioDoctor2.getStudio_id());
						studio.and("del_state","=",0);
						Studio studiotemp = this.studioDao.getEntity(studio);
						if (studiotemp != null) {
							d.setStudioId(studiotemp.getId());
							d.setStudioName(studiotemp.getName());
							d.setDisease_id(studiotemp.getDisease_id());
							d.setStudio_icon_img(studiotemp.getIcon_img());
							//获取工作室创建者数据
							Doctor doc=this.doctorDao.getEntity(studiotemp.getDoctor_id());
							if(doc!=null){
								d.setHospital_id(doc.getHospital_id());
							}
							temp.add(d);
						}
					}
				}
			}
		}

		// 名字模糊查询医生
		if (type == 2) {
			List<Doctor> vagueDoctor = this.doctorDao.getVagueNickName(tempDto);
			if (vagueDoctor.size() == 0) {
				b.setObject(vagueDoctor);
				return b;
			}
			// 查询是否开通 院外随访服务
			for (Doctor doctor2 : vagueDoctor) {
				// 查询当前医生是否开通的 院外随访服务
				WherePrams serv = new WherePrams();
				serv.and("doctor_id", "=", doctor2.getId());
				serv.and("type", "=", 4);
				serv.and("state", "=", 1);
				DoctorService doctorService = this.doctorServiceDao
						.getEntity(serv);
				if (doctorService != null) {
					// 查询创建的和加入的工作室
					// 加入的工作室 和创建的工作室
					WherePrams studiodoc = new WherePrams();
					studiodoc.and("doctor_id", "=", doctor2.getId());
					studiodoc.and("state","=",1);
					List<StudioDoctor> studioDoctor = this.studioDoctorDao
							.getEntitys(studiodoc);
					if (studioDoctor.size() > 0) {
						// 循环查询工作室
						for (StudioDoctor studioDoctor2 : studioDoctor) {
							// 获取必要数据
							Doctor d = new Doctor();
							d.setLogin_name(doctor2.getLogin_name());
							d.setNick_name(doctor2.getNick_name());
							d.setHospital_id(doctor2.getHospital_id());
							// 查询医院
							Hospital hospital = this.hospitalDao
									.getEntity(d.getHospital_id());
							if (hospital != null) {
								d.setHospital_name(hospital.getName());
							}
							//查询科室
							WherePrams departmentWhere=new WherePrams();
							departmentWhere.and("app_id","=", app_id);
							departmentWhere.and("id","=", doctor2.getDepartment_id());
							Department department=this.departmentDao.getEntity(departmentWhere);
							if(department!=null){
								d.setDepartment_name(department.getName());
							}

							//查询职位
							WherePrams positionalWhere=new WherePrams();
							positionalWhere.and("app_id","=", app_id);
							positionalWhere.and("id","=",doctor2.getPositional_id());
							Positional positional=this.positionalDao.getEntity(doctor2.getPositional_id());
							if(positional!=null){
								d.setPositional_name(positional.getName());								
							}
							

							// 擅长
							d.setAdept(doctor2.getAdept());
							// 医生id
							d.setId(doctor2.getId());
							// 头像
							d.setHead_img(doctor2.getHead_img());

							// 工作室
							WherePrams studio = new WherePrams();
							studio.and("id", "=", studioDoctor2.getStudio_id());
							studio.and("del_state","=",0);
							Studio studiotemp = this.studioDao
									.getEntity(studio);
							if (studiotemp != null) {
								d.setStudioId(studiotemp.getId());
								d.setStudioName(studiotemp.getName());
								d.setDisease_id(studiotemp.getDisease_id());
								d.setStudio_icon_img(studiotemp.getIcon_img());
								//获取工作室创建者数据
								Doctor doc=this.doctorDao.getEntity(studiotemp.getDoctor_id());
								if(doc!=null){
									d.setHospital_id(doc.getHospital_id());
								}
								temp.add(d);
							}
						}
					}
				}
			}
		}
		b.setObject(temp);
		return b;
	}
}