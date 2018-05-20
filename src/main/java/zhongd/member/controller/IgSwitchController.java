package zhongd.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.manage.IgSwitchService;
import zhongd.member.utils.constant.ReturnCode;

/**
 * @author xiezd 2018-05-18 14:15
 */
@RestController
@RequestMapping("/switch")
public class IgSwitchController extends BaseController{
    @Autowired
    IgSwitchService igSwitchService;

    @GetMapping("/check")
    public ReturnObj check(@RequestParam String name){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igSwitchService.getById(name));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("操作开关失败！");
        }
        return obj;
    }
}
