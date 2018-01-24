package zhongd.member.dao.movie;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-01-24 12:55
 */
@Mapper
public interface MovieMapper {

    List<Map<String,Object>> search(String condition);
}
