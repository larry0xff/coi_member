package zhongd.member.dao.member;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.member.IgMember;

import java.util.List;
import java.util.Map;

@Mapper
public interface IgMemberMapper extends BaseMapper<IgMember> {
}
