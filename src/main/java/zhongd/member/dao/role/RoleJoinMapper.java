package zhongd.member.dao.role;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.entity.DO.user.IgPermission;

import java.util.Map;
import java.util.Set;

@Mapper
public interface RoleJoinMapper {
    int setPermission(Map<String, Object> param);

    int rmPermission(Map<String, Object> param);

    Set<IgPermission> getPermissionSet(Integer igRoleId);

    Set<IgPermission> getPermissionSelectSet(Integer igRoleId);
}
