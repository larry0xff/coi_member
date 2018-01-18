package zhongd.member.dao.org;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.dao.BaseMapper;
import zhongd.member.entity.DO.org.IgOrg;

import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 9:41 星期六 2017/12/2 0002
 * @Description
 */
@Mapper
public interface IgOrgMapper extends BaseMapper<IgOrg> {
    List<IgOrg> getOrgList();
}
