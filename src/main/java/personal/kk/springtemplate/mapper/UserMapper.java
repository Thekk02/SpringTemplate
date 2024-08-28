package personal.kk.springtemplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import personal.kk.springtemplate.entity.User;

/**
 * @author kk
 * @description 用户dao层
 * @date 2024-8-28 08:17:17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
