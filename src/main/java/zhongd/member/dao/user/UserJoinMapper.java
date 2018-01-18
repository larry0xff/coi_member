package zhongd.member.dao.user;

import org.apache.ibatis.annotations.Mapper;
import zhongd.member.entity.DO.user.IgPermission;
import zhongd.member.entity.DO.user.IgRole;
import zhongd.member.entity.DTO.user.IgUserDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserJoinMapper {
	Set<IgRole> getRoleSet(Integer igUserId);

	List<IgUserDTO> getUserList(Map<String, Object> param);

    int setRole(Map<String, Object> param);

    int rmRole(Map<String, Object> param);

    Set<IgPermission> getUserPermissionSet(Integer igUserId);

    Set<IgRole> getRoleSelectSet(Integer igUserId);
}
