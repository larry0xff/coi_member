package zhongd.member.dao.mailbox;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.mailbox.IgMail;
import zhongd.member.entity.DTO.mailbox.MailDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:27
 * @Description
 */
@Mapper
public interface IgMailMapper extends BaseMapper<IgMail> {
    List<IgMail> list(Map<String, Object> paramMap);

    List<MailDTO> notifyList(Integer igMemberId);
}
