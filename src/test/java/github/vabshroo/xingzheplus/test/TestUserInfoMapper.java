package github.vabshroo.xingzheplus.test;

import github.vabshroo.xingzheplus.dao.UserInfoMapper;
import github.vabshroo.xingzheplus.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 13:42
 * @desc TestUserInfoMapper
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/ApplicationContext-da*.xml")
public class TestUserInfoMapper {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void testInsertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(949201L);
        userInfo.setUsername("CL有毒");
        userInfo.setSessionId("xsmwyskl53c66lw25ufjwva2n6xpxtwl");
        userInfoMapper.insertSelective(userInfo);
    }

}
