import java.util.Map;

import com.frame.common.core.domain.idc.pay.AlipayAsyncRresponse;
import com.frame.common.dto.BasicResponse;
import com.frame.common.dto.TempFormMap;

/**
 * @ClassName:  UserSideFacade   
 * @Description: 服务接口--用户端
 * @company:  信息技术力量(成都)有限公司 
 * @author: ysw
 * @date:   2018年5月15日 下午2:23:17
 */
public interface UserSideFacade {
	
	/**
	 * @Title: getRegister   
	 * @Description: 用户注册   
	 * @param tempDto
	 * @return 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午2:17:41
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse addRegister(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: Login_Code   
	 * @Description: 手机验证码登录   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午5:31:52
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse Login(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: modify   
	 * @Description: 用户数据修改   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午6:15:49
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse modify(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getById   
	 * @Description: 根据id查询用户数据   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月15日 下午6:35:36
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getById(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getResetPssword   
	 * @Description: 重置密码   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月16日 上午9:51:10
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getResetPassword(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getValidationPhone   
	 * @Description: 电话号码效验   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月17日 下午3:08:09
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getValidationPhone(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getStudioDoctor   
	 * @Description: 根据姓名搜索医生   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月22日 上午9:43:37
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getStudioDoctor(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getRandomDoctor   
	 * @Description: 随机医生   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月22日 下午2:20:43
	 */
	
	@SuppressWarnings("rawtypes")
	public BasicResponse getRandomDoctor(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getServp   
	 * @Description: 查询服务包   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月23日 下午2:59:30
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getServp(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getServpOrder   
	 * @Description: 下单   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月23日 下午6:33:12
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getServpOrder(TempFormMap tempDto,String userIp)throws Exception;
	
	/**
	 * @Title: getOrderPage   
	 * @Description: TODO   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月24日 下午1:44:39
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getOrderPage(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getHospitalList   
	 * @Description: 查询医院列表   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月28日 下午3:52:49
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getHospitalList(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getFindDoctor   
	 * @Description: 找医生    
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月28日 下午5:38:43
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getFindDoctor(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getDepartmentCount   
	 * @Description: 科室统计   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月29日 上午10:44:01
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getDepartmentCount(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getDepartmentPush   
	 * @Description: 科室推荐
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月29日 上午11:21:40
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getDepartmentPush(TempFormMap tempDto)throws Exception;
	
	/**
	 * @Title: getHospitalPush   
	 * @Description:医院推荐   
	 * @param tempDto
	 * @return
	 * @throws Exception 
	 * @author: ysw  
	 * @date:  2018年5月29日 下午1:38:15
	 */
	@SuppressWarnings("rawtypes")
	public BasicResponse getHospitalPush(TempFormMap tempDto)throws Exception;
	
	
}
