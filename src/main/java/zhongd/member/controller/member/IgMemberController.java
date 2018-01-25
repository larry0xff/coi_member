package zhongd.member.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.DO.member.IgMember;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.member.IgMemberService;
import zhongd.member.utils.PasswordHandler;
import zhongd.member.utils.constant.ReturnCode;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author xiezd 2018-01-24 17:53
 */
@RestController
@RequestMapping("/member")
public class IgMemberController extends BaseController{
    @Autowired
    IgMemberService igMemberService;

    /**
     * 修改密码
     * @param oldPsw 旧密码
     * @param newPsw 新密码
     */
    @PostMapping("/changePsw")
    public ReturnObj changePsw(String oldPsw, String newPsw){
        ReturnObj obj = new ReturnObj();
        try {
            IgMember current = getCurrentMember().getIgMember();
            String psw = igMemberService.getById(current.getIgMemberId()).getPassword();
            if(!psw.equals(PasswordHandler.encodePassword(oldPsw, current.getUsername(), "MD5"))){
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                obj.setMsg("旧密码错误！");
                return obj;
            }
            if(newPsw.length() < 6 || newPsw.length() > 16){
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                obj.setMsg("密码长度不符！");
                return obj;
            }
            igMemberService.changePassword(current, newPsw);
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("服务器出错");
        }
        return obj;
    }

    /**
     * 修改邮箱或手机号
     * @param email
     * @param tel
     */
    @PostMapping("/changeInfo")
    public ReturnObj changeInfo(String email, String tel){
        ReturnObj obj = new ReturnObj();
        try {
            IgMember igMember = new IgMember();
            igMember.setIgMemberId(getCurrentMember().getIgMember().getIgMemberId());
            Pattern phonePattern = Pattern.compile("^\\d{11}$");
            if(Objects.nonNull(tel) && !phonePattern.matcher(tel).matches()){
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                obj.setMsg("手机号格式不正确");
                return obj;
            }else{
                igMember.setTel(tel);
            }
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
            if(Objects.nonNull(email) && !emailPattern.matcher(email).matches()){
                obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
                obj.setMsg("邮箱格式不正确");
                return obj;
            }else{
                igMember.setEmail(email);
            }
            obj.setData(igMemberService.updateById(igMember));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("服务器出错");
        }
        return obj;
    }
}
