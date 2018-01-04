package zhongd.member.controller.member;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhongd.coiplatform.controller.BaseController;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.service.member.IgMemberService;
import zhongd.coiplatform.utils.constant.ReturnCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiezd 2018-01-04 10:50
 */
@RestController
public class PermissionController extends BaseController {

    @Autowired
    private IgMemberService igMemberService;
    @RequestMapping("/login")
    public ReturnObj login(HttpServletRequest request){
        return igMemberService.login(request);
    }

    @RequestMapping(value = {"/logout", "/"})
    public String unlogin() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            logger.error(e);
        }
        return "redirect:/page/login/login.html";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ReturnObj denied() {
        ReturnObj rtobj= new ReturnObj();
        rtobj.setReturnCode(ReturnCode.NO_PERMISSION);
        rtobj.setMsg("没有权限进行此操作");
        return rtobj;
    }
}
