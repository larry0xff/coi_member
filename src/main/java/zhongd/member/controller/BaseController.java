package zhongd.member.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import zhongd.coiplatform.entity.DTO.member.IgMemberLoginDTO;

/**
 * @author xiezd 2018-01-06 11:06
 */
public class BaseController {
    public IgMemberLoginDTO getCurrentMember() {
        IgMemberLoginDTO currentMember = (IgMemberLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentMember");
        return currentMember;
    }
    public Logger logger = LogManager.getLogger(getClass());
}
