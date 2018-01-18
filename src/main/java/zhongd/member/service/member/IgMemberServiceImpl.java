package zhongd.member.service.member;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zhongd.member.dao.member.IgMemberMapper;
import zhongd.member.dao.org.IgOrgMapper;
import zhongd.member.entity.DO.member.IgMember;
import zhongd.member.entity.DO.member.IgMemberBulkRecord;
import zhongd.member.entity.DTO.member.IgMemberBulkRecordDTO;
import zhongd.member.entity.DTO.member.IgMemberDTO;
import zhongd.member.entity.DTO.member.IgMemberLoginDTO;
import zhongd.member.entity.DTO.user.IgUserLoginDTO;
import zhongd.member.entity.ReturnObj;
import zhongd.member.utils.ConvertTools;
import zhongd.member.utils.PasswordHandler;
import zhongd.member.utils.StringUtil;
import zhongd.member.utils.constant.Constant;
import zhongd.member.utils.constant.ReturnCode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
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
    IgOrgMapper igOrgMapper;

    @Override
    public Map<String, Object> getMemberList(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        String condition = request.getParameter("condition")==null?"":request.getParameter("condition");
        if(StringUtil.isNum(condition))
            param.put("igMemberId", Integer.parseInt(condition));
        param.put("username", "%" + condition + "%");
        param.put("realname", "%" + condition + "%");
        param.put("tel", "%" + condition + "%");
        param.put("email", "%" + condition + "%");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        page = (page - 1) * pageSize;
        param.put("page", page);
        param.put("pageSize", pageSize);
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.getMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    public int deleteById(int igMemberId){
        return igMemberMapper.deleteByPrimaryKey(igMemberId);
    }

    public int insert(IgMemberDTO dto){
        IgMember igMember = ConvertTools.convert(IgMember.class, dto);
        igMember.setCreateBy(((IgUserLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getIgUserDO().getIgUserId());
        igMember.setCreateTime(new Date());
        igMember.setUpdateTime(new Date());
        igMember.setPassword(PasswordHandler.encodePassword("123456", igMember.getUsername(), Constant.MD5_STR));
        return igMemberMapper.insertSelective(igMember);
    }

    @Override
    public int resetPassword(Integer igMemberId, String username) {
        IgMember member = new IgMember();
        member.setIgMemberId(igMemberId);
        member.setPassword(PasswordHandler.encodePassword("123456", username, Constant.MD5_STR));
        return igMemberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Map<String, Object> searchMemberList(String condition) {
        Map<String, Object> param = new HashMap<>();
        if(StringUtil.isNum(condition))
            param.put("idcondition", Integer.parseInt(condition));
        param.put("condition", "%" + condition + "%");
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.searchMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }


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
}
