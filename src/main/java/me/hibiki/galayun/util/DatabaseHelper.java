package me.hibiki.galayun.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 单例模式
 *
 * @author 高弘昆
 * @date 2020/3/26 17:25
 */
public class DatabaseHelper {
    private static SqlSessionFactory sqlSessionFactory;

    private DatabaseHelper() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }
}
