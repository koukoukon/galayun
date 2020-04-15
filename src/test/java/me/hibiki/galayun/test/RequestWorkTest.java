package me.hibiki.galayun.test;


import me.hibiki.galayun.util.RequestWork;
import org.junit.jupiter.api.Test;


/**
 * @author 高弘昆
 * @date 2020/3/26 20:48
 */
public class RequestWorkTest {
    @Test
    public void testInsertWork() {
        RequestWork requestWork = new RequestWork();
        requestWork.insertWork(151, 238);
    }

}
