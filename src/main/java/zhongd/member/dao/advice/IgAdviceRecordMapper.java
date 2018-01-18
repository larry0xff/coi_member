package zhongd.member.dao.advice;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.advice.IgAdviceRecord;
import zhongd.member.entity.DTO.advice.IgAdviceRecordDTO;

import java.util.List;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  23:08 星期日 2017/12/17/017
 */
@Mapper
public interface IgAdviceRecordMapper extends BaseMapper<IgAdviceRecord> {

    List<IgAdviceRecordDTO> selectByCollectionId(Integer igAdviceCollectionId);

    IgAdviceRecordDTO selectByMemberIdAndCollectionId(Integer igMemberId, Integer igAdviceCollectionId);
}
