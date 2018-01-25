package zhongd.member.service.member;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zhongd.member.dao.advice.IgAdviceCollectionMapper;
import zhongd.member.dao.member.IgMemberMapper;
import zhongd.member.entity.DO.advice.IgAdviceCollection;
import zhongd.member.entity.DO.member.IgMember;
import zhongd.member.entity.DTO.member.IgMemberDTO;
import zhongd.member.entity.DTO.member.IgMemberLoginDTO;
import zhongd.member.entity.ReturnObj;
import zhongd.member.utils.ConvertTools;
import zhongd.member.utils.DateUtil;
import zhongd.member.utils.PasswordHandler;
import zhongd.member.utils.StringUtil;
import zhongd.member.utils.constant.Constant;
import zhongd.member.utils.constant.ReturnCode;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:20 星期六 2017/12/2/002
 */
@Service
public class IgMemberServiceImpl implements IgMemberService {
    @Value("${file.uploadpath}")
    private String path;
    @Autowired
    IgMemberMapper igMemberMapper;
    @Autowired
    IgAdviceCollectionMapper igAdviceCollectionMapper;

    @Override
    public ReturnObj login(HttpServletRequest request) {
        ReturnObj obj = new ReturnObj();
        Subject subject = SecurityUtils.getSubject();
        String verifyCode = request.getParameter("verifyCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //校验验证码
        if(!verifyCode.equalsIgnoreCase((String)subject.getSession().getAttribute("loginVerifyCode"))){
            obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
            obj.setMsg("验证码不正确！");
            return obj;
        }
        IgMember member = new IgMember();
        member.setUsername(username);
        IgMember result = igMemberMapper.selectOne(member);
        if(result != null){
            if(result.getPassword().equals(PasswordHandler.encodePassword(password, result.getUsername(), Constant.MD5_STR))) {
                UsernamePasswordToken token = new UsernamePasswordToken(result.getUsername(), result.getPassword());
                subject.login(token);
                // 将登录信息放进Session中
                IgMemberLoginDTO loginDTO = new IgMemberLoginDTO(result, new Date());
                subject.getSession().setAttribute("currentMember", loginDTO);
                obj.setData(result);
                obj.setMsg("登录成功");
                obj.setReturnCode(ReturnCode.LOGIN_SUCCESS);
            }else{
                obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
                obj.setMsg("密码错误");
            }
        }else{
            obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
            obj.setMsg("用户不存在");
        }
        return obj;
    }

    @Override
    public Map<String, Object> getInfoById(Integer igMemberId) {
        IgMemberDTO igMemberDTO = igMemberMapper.selectOneDTO(igMemberId);
        List<IgAdviceCollection> collections = igAdviceCollectionMapper.getMemberAdviceCollection(igMemberId);
        Map<String, Object> data = new HashMap<>();
        data.put("profile", igMemberDTO);
        data.put("collections", collections);
        return data;
    }

    @Override
    public int changePassword(IgMember current, String newPsw) {
        IgMember member = new IgMember();
        member.setIgMemberId(current.getIgMemberId());
        member.setPassword(PasswordHandler.encodePassword(newPsw, current.getUsername(), Constant.MD5_STR));
        return igMemberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public int updateById(IgMember igMember) {
        return igMemberMapper.updateByPrimaryKeySelective(igMember);
    }

    @Override
    public IgMember getById(Integer igMemberId){
        return igMemberMapper.selectByPrimaryKey(igMemberId);
    }
}
