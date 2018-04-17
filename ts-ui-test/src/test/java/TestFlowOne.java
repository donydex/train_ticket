import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ZDH on 2017/7/19.
 */
public class TestFlowOne {
    @BeforeClass
    public void setUp() throws Exception {
        //do nothing
    }

    @Test
    public void testLogin()throws Exception{

        RestTemplate restTemplate = new RestTemplate();

        //注意把这里换成你的集群的ip
        CancelOrderResult result = restTemplate.getForObject(
                "10.141.212.21:30085/cancelOrder/5ad7750b-a68b-49c0-a8c0-32776b067703",
                CancelOrderResult.class);
        //[Error Process Seq] - 顺序没控制好的话result.message返回这个 status为false
        //Success.Processes Seq. - 顺序控制好了返回这个 status为true
        //Something Wrong - 其他不知道什么意外乱七八糟的情况返回这个,status为false

        Assert.assertEquals(result.isStatus() && result.getMessage().contains("Success.Processes Seq"),true);
    }


    @AfterClass
    public void tearDown() throws Exception {
    }
}
