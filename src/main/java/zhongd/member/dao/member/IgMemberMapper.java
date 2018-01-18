package zhongd.member.dao.member;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.member.IgMember;
import zhongd.member.entity.DTO.member.IgMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface IgMemberMapper extends BaseMapper<IgMember> {
    List<IgMemberDTO> getMemberList(Map<String, Object> param);

    List<IgMemberDTO> searchMemberList(Map<String, Object> param);
}
