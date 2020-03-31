package me.hibiki.galayun.test;

import me.hibiki.galayun.util.RequestWorkDetail;
import org.junit.jupiter.api.Test;

/**
 * @author 高弘昆
 * @date 2020/3/26 22:32
 */
public class RequestWorkDetailTest {

    @Test
    public void testInsertWorkDetail() {
        RequestWorkDetail requestWorkDetail = new RequestWorkDetail();
        requestWorkDetail.insertWorkDetail(915, 928, 928);
    }
}
