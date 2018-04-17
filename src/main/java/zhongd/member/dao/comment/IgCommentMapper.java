package zhongd.member.dao.comment;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.comment.IgComment;
import zhongd.member.entity.DTO.comment.IgCommentDTO;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-01-06 10:44
 */
@Mapper
public interface IgCommentMapper extends BaseMapper<IgComment> {
    List<IgCommentDTO> list(Map<String, Object> paramMap);

    int getNotifyCountById(Integer igMemberId);
}
