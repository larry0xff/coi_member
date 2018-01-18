package zhongd.member.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import zhongd.member.entity.DO.user.IgRole;

import java.util.Set;

@Mapper
public interface IgUserRoleMapper {
	@Select(value = 
			"SELECT r.role_code FROM ig_user_role ur " + 
			"LEFT JOIN ig_role r on ur.ig_role_id = r.ig_role_id " + 
			"WHERE ur.ig_user_id = #{arg1}")
	public Set<IgRole> getUserRoleSet(Integer igUserId);
}
