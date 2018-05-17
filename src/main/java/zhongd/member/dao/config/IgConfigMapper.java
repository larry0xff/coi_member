package zhongd.member.dao.config;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-05-17 23:32
 */
@Mapper
public interface IgConfigMapper {

    List<Map<String, Object>> getGlobalData();
}
