package zhongd.member.service.member;

import zhongd.member.entity.DO.member.IgMember;
import zhongd.member.entity.ReturnObj;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:15 星期六 2017/12/2/002
 */
public interface IgMemberService {

    ReturnObj login(HttpServletRequest request);

    Map<String, Object> getInfoById(Integer igMemberId);

    int changePassword(IgMember current, String newPsw);

    int updateById(IgMember igMember);

    IgMember getById(Integer igMemberId);
}
