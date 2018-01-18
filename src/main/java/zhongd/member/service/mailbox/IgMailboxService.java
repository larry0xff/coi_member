package zhongd.member.service.mailbox;

import zhongd.member.entity.DO.mailbox.IgMail;

import java.util.List;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:24
 * @Description
 */
public interface IgMailboxService {
    List<IgMail> list(Integer igOrgId, String status);

    boolean reply(String reply, Integer igMailId, Integer igUserId);

    boolean changeReadStatus(Integer igMailId, String isRead);
}
