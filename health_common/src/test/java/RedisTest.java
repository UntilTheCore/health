import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
    public void redis1() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-redis.xml");
        ConfigurableListableBeanFactory beanFactory = classPathXmlApplicationContext.getBeanFactory();
        JedisPool pool = beanFactory.getBean(JedisPool.class);
        Jedis jedis = pool.getResource();
        jedis.set("name", "臧三");
    }
}
