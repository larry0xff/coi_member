package zhongd.member.dao.advice;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.advice.IgAdviceCollection;
import zhongd.member.entity.DTO.advice.IgAdviceCollectionDTO;

import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 8:47 星期二 2017/12/12 0012
 * @Description
 */
@Mapper
public interface IgAdviceCollectionMapper extends BaseMapper<IgAdviceCollection> {
    List<IgAdviceCollectionDTO> list(IgAdviceCollectionDTO paramMap);

    List<IgAdviceCollection> getAllCollectingCollection();

    IgAdviceCollectionDTO getAdviceCollectionById(Integer igAdviceCollectionId);
}
