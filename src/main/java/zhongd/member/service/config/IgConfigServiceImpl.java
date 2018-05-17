package zhongd.member.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.member.dao.config.IgConfigMapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-05-17 23:31
 */
@Service
public class IgConfigServiceImpl implements IgConfigService {

    @Autowired
    IgConfigMapper igConfigMapper;
    @Override
    public List<Map<String, Object>> getGlobalData() {
        return igConfigMapper.getGlobalData();
    }
}
