
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.common.core.domain.idc.pay.AlipayAsyncRresponse;
import com.frame.common.dto.BasicResponse;
import com.frame.common.dto.TempFormMap;
import com.frame.facade.idc.service.UserSideFacade;
import com.frame.service.user.UserBiz;

/**
 * @ClassName:  UserSideFacadeImpl   
 * @Description:服务接口实现类--用户端
 * @company:  信息技术力量(成都)有限公司 
 * @author: zhangwen
 * @date:   2018年5月15日 下午2:26:27
 */
@Service("userSideFacade")
@Transactional
public class UserSideFacadeImpl implements UserSideFacade {
	
	@Autowired
	private  UserBiz  userBiz;
	
	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse addRegister(TempFormMap tempDto) throws Exception {
		BasicResponse b= new BasicResponse();
		b=this.userBiz.addRegister(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse Login(TempFormMap tempDto) throws Exception {
		BasicResponse b= new BasicResponse();
		b=this.userBiz.Login(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse modify(TempFormMap tempDto) throws Exception {
		BasicResponse b=new BasicResponse();
		b=this.userBiz.modify(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getById(TempFormMap tempDto) throws Exception {
		BasicResponse b=new BasicResponse();
		b=this.userBiz.getById(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getResetPassword(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getResetPassword(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getValidationPhone(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getValidationPhone(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getStudioDoctor(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getStudioDoctor(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getRandomDoctor(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getRandomDoctor(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getServp(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getServp(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	public BasicResponse getServpOrder(TempFormMap tempDto,String userIp) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getServpOrder(tempDto,userIp);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getOrderPage(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getOrderPage(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getHospitalList(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getHospitalList(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getFindDoctor(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getFindDoctor(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getDepartmentCount(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getDepartmentCount(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getDepartmentPush(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getDepartmentPush(tempDto);
		return b;
	}	
	
	@SuppressWarnings("rawtypes")
	public BasicResponse getHospitalPush(TempFormMap tempDto)throws Exception{
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getHospitalPush(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getDoctorPush(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getDoctorPush(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getDoctorDetails(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getDoctorDetails(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getHealthyEncyclopediaList(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getHealthyEncyclopediaList(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getPatientData(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getPatientData(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getAddPatientData(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getAddPatientData(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getDiseasePosition(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getDiseasePosition(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getOnlineOrder(TempFormMap tempDto,String userIp) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getOnlineOrder(tempDto,userIp);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getOnlineOrderRecord(TempFormMap tempDto) throws Exception {
		BasicResponse b=new BasicResponse();
		b=this.userBiz.getOnlineOrderRecord(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getSearch(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getSearch(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getInquisitionRecord(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getInquisitionRecord(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getComment(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getComment(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse setComment(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.setComment(tempDto);
		return b;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BasicResponse getInquisitionList(TempFormMap tempDto) throws Exception {
		BasicResponse b =new BasicResponse();
		b=this.userBiz.getInquisitionList(tempDto);
		return b;
	}

	
	
}
