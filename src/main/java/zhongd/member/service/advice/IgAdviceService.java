package zhongd.member.service.advice;

import zhongd.member.entity.DO.advice.IgAdviceCollection;
import zhongd.member.entity.DTO.advice.IgAdviceCollectionDTO;
import zhongd.member.entity.DTO.advice.IgAdviceRecordDTO;

import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 9:38 星期二 2017/12/12 0012
 * @Description
 */
public interface IgAdviceService {
    boolean save(IgAdviceCollection igAdviceCollection);

    boolean deleteById(Integer igAdviceCollectionId);

    List<IgAdviceCollectionDTO> list(IgAdviceCollectionDTO paramMap);

    List<IgAdviceCollection> getAllCollectingCollection();

    List<IgAdviceRecordDTO> getRecordsByCollectionId(Integer igAdviceCollectionId);

    Integer baseSave(IgAdviceCollection collection);

    IgAdviceCollectionDTO getById(Integer igAdviceCollectionId);

    IgAdviceRecordDTO getByMemberIdAndCollectionId(Integer igMemberId, Integer igAdviceCollectionId);
}
