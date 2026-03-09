package com.lx.user_center;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testDegest(){
        String encryptPassword = DigestUtils.md5DigestAsHex(( "lxlxl" + "mypassword").getBytes());
        System.out.println(encryptPassword);
    }

}
