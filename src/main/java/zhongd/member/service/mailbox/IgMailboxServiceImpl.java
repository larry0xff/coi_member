package zhongd.member.service.mailbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.member.dao.mailbox.IgMailMapper;
import zhongd.member.entity.DO.mailbox.IgMail;
import zhongd.member.entity.DTO.mailbox.IgMailDTO;
import zhongd.member.entity.DTO.mailbox.MailDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:25
 * @Description
 */
@Service
public class IgMailboxServiceImpl implements IgMailboxService {
    @Autowired
    IgMailMapper igMailMapper;

    @Override
    public int save(MailDTO mail, Integer igMemberId) {
        IgMail mailDO = new IgMail();
        mailDO.setContent(mail.getContent());
        mailDO.setIgOrgId(mail.getToOrgId());
        mailDO.setTitle(mail.getTitle());
        mailDO.setCreateBy(igMemberId);
        mailDO.setCreateTime(new Date());
        return igMailMapper.insertSelective(mailDO);
    }

    @Override
    public List<MailDTO> notifyList(Integer igMemberId) {
        return igMailMapper.notifyList(igMemberId);
    }
}
