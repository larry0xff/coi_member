package zhongd.member.service.manage;

import zhongd.member.entity.DO.manage.IgSwitch;

import java.util.List;

/**
 * @Author xiezd
 * @Date 2018-01-01 13:27
 * @Description
 */
public interface IgSwitchService {
    List<IgSwitch> getSwitchList();

    boolean changeStatus(Integer igSwitchId, Integer status);
}
