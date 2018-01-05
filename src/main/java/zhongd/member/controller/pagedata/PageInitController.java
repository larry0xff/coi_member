package zhongd.member.controller.pagedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceCollectionDTO;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.advice.IgAdviceService;
import zhongd.coiplatform.service.org.IgOrgService;
import zhongd.coiplatform.utils.constant.ReturnCode;

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
}
