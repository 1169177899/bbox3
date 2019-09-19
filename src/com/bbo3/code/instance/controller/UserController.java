import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.http.HttpResponse;
import com.frame.common.common.CommonInternationalText;
import com.frame.common.core.domain.idc.pay.AlipayAsyncRresponse;
import com.frame.common.dto.BasicResponse;
import com.frame.common.dto.TempFormMap;
import com.frame.common.utils.CommonUtil;
import com.frame.common.utils.StringUtil;
import com.frame.common.utils.weixin.HttpUtil;
import com.frame.common.utils.weixin.PayCommonUtil;
import com.frame.common.utils.weixin.XMLUtil;
import com.frame.facade.idc.service.UserSideFacade;

/**
 * @ClassName:  UserController   
 * @Description: 用户控制层 
 * @company: 
 * @author: ysw
 * @date:   2018年5月15日 下午2:11:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private  UserSideFacade userSideFacade;
	
	/**
	 * @Title: addConsumer   
	 * @Description: 用户注册   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午2:52:31
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addRegister", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse addRegister(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)
			throws Exception {
		BasicResponse b = new BasicResponse();		
		b = this.userSideFacade.addRegister(tempDto);
		return b;
	}
	
	/**
	 * @Title: Login_Code   
	 * @Description: 验证码  登录 
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午5:26:02
	 */
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse Login(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b = new BasicResponse();
		b=this.userSideFacade.Login(tempDto);
		return b;
	}
	
	/**
	 * @Title: modify   
	 * @Description: 用户数据修改   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午6:14:50
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse  modify(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b=new BasicResponse();
		
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		
		
		b=this.userSideFacade.modify(tempDto);
		return b;
	}
	
	/**
	 * @Title: getById   
	 * @Description: 根据id查询用户数据   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午6:34:14
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getById(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b= new BasicResponse();
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		b=this.userSideFacade.getById(tempDto);
		return b;
	}
	
	/**
	 * @Title: getResetPssword   
	 * @Description: 重置密码   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月16日 上午9:49:29
	 */
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getResetPassword", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getResetPassword(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b=this.userSideFacade.getResetPassword(tempDto);
		return b;
	}
	
	/**
	 * @Title: getValidationPhone   
	 * @Description: 电话号码效验   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月17日 下午3:06:24
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getValidationPhone", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getValidationPhone(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b=this.userSideFacade.getValidationPhone(tempDto);
		return b;
	}
	
	/**
	 * @Title: getStudioDoctor   
	 * @Description: 通过医生的姓名搜素   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月22日 上午9:41:27
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getStudioDoctor", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getStudioDoctor(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		b=this.userSideFacade.getStudioDoctor(tempDto);
	    return b;	
	}
	
	/**
	 * @Title: getRandomDoctor   
	 * @Description: 随机医生   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月22日 下午2:18:58
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getRandomDoctor", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getRandomDoctor(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
			BasicResponse b =new BasicResponse();
			
			b = CommonUtil.localVerify(b, tempDto);
			if (!b.isSuccess()) {
				return b;
			}
			b=this.userSideFacade.getRandomDoctor(tempDto);
			return b;
	 }
	
	/**
	 * @Title: getServp   
	 * @Description: 根据医院 疾病 查询服务包   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月23日 下午2:57:48
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getServp", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getServp(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		
		b=this.userSideFacade.getServp(tempDto);
		return b;
	}
	
	/**
	 * @Title: getServpOrder   
	 * @Description: 生成服务包订单   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月23日 下午6:31:43
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getServpOrder", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getServpOrder(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		
		String u_id=tempDto.getStr("u_id");
		if(StringUtil.isNull(u_id)){
			b.setMessage(CommonInternationalText.CMS_USER_IS_NULL);
		    return b;
		}
		//获取ip 地址
		String spbill_create_ip = HttpUtil.getIpAddress(request);
		
		b=this.userSideFacade.getServpOrder(tempDto,spbill_create_ip);
		return b;
	}
	
	/**
	 * @Title: getOrderPage   
	 * @Description: 用户端拉取订单列表   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月24日 下午1:43:54
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getOrderPage", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getOrderPage(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}
		b=this.userSideFacade.getOrderPage(tempDto);
		return b;
	}
	
	/**
	 * @Title: getHospitalList   
	 * @Description: 查询所有医院列表   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月28日 下午3:50:54
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getHospitalList", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getHospitalList(HttpServletRequest request,
		HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		 BasicResponse b =new BasicResponse();
		/*b = CommonUtil.localVerify(b, tempDto);
		if (!b.isSuccess()) {
			return b;
		}*/
		// 区号
		String intPhoneCode = tempDto.getStr("intPhoneCode");
		if(StringUtil.isNull(intPhoneCode)){
			b.setMessage(CommonInternationalText.INT_PHONE_CODE_NOT_EXIST);
			return b;
		}
		b = this.userSideFacade.getHospitalList(tempDto);
		 return b;
	}
	
	/**
	 * @Title: getFindDoctor   
	 * @Description: 找医生  查询 符合的医生   
	 * @param request
	 * @param response
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月28日 下午5:36:02
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFindDoctor", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse getFindDoctor(HttpServletRequest request,
			HttpServletResponse response, @RequestBody TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		if (!b.isSuccess()) {
			return b;
		}
		b=this.userSideFacade.getFindDoctor(tempDto);
		return b;
	}
	

}
