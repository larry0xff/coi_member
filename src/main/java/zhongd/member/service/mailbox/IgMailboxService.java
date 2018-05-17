package zhongd.member.service.mailbox;

import zhongd.member.entity.DO.mailbox.IgMail;
import zhongd.member.entity.DTO.mailbox.MailDTO;

import java.util.List;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:24
 * @Description
 */
public interface IgMailboxService {

    int save(MailDTO mail, Integer igMemberId);

    List<MailDTO> notifyList(Integer igMemberId);
}
