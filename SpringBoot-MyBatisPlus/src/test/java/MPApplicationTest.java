import com.ovopark.tao.mp.MPApplication;
import com.ovopark.tao.mp.entity.po.User;
import com.ovopark.tao.mp.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
@SpringBootTest(classes = MPApplication.class)
public class MPApplicationTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void myTest() {
    User user = userMapper.selectById(1);
    log.info("user的名字: {user}", user);
  }

  Logger logger = LoggerFactory.getLogger(MPApplicationTest.class);

  @Test
  public void log4j2() {
    logger.trace("trace level");
    logger.debug("debug level");
    logger.info("info level");
    logger.warn("warn level");
    logger.error("error message");
//        logger.fatal("fatal level");
    logger.error("字符串快捷拼接方式一:{},此方式比使用+号速度块！", "dsjkfadjlks");
  }


}
