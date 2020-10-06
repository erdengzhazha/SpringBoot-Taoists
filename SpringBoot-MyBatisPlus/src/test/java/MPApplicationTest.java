import com.ovopark.tao.mp.MPApplication;
import com.ovopark.tao.mp.entity.po.User;
import com.ovopark.tao.mp.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = MPApplication.class)
public class MPApplicationTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void myTest(){
    User user = userMapper.selectById(1);
    log.info("user的名字: {name}",user);
  }
}
