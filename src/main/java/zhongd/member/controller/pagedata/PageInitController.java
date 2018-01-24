package zhongd.member.controller.pagedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.DTO.advice.IgAdviceCollectionDTO;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.advice.IgAdviceService;
import zhongd.member.service.member.IgMemberService;
import zhongd.member.service.org.IgOrgService;
import zhongd.member.utils.constant.ReturnCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiezd 2018-01-05 17:51
 */
@RestController
@RequestMapping("/pagedata")
public class PageInitController extends BaseController {
    @Autowired
    private IgAdviceService igAdviceService;
    @Autowired
    private IgOrgService igOrgService;
    @Autowired
    private IgMemberService igMemberService;
    @GetMapping("/home")
    public ReturnObj homeData(){
        ReturnObj obj = new ReturnObj();
        try{
            Map<String, Object> homedata = new HashMap<>();
            homedata.put("collections", igAdviceService.list(new IgAdviceCollectionDTO()));
            homedata.put("orgList", igOrgService.getOrgList());
            obj.setData(homedata);
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setMsg("获取主页数据失败");
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    @GetMapping("/profile")
    public ReturnObj profileData(){
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igMemberService.getById(getCurrentMember().getIgMember().getIgMemberId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return  obj;
    }
}
