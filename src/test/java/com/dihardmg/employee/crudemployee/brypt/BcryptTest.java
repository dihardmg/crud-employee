package com.dihardmg.employee.crudemployee.brypt;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : Otorus
 * @since : 12/23/17
 */


public class BcryptTest {

    @Test
    public void testBcrypt(){
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String hasil = enc.encode("123");
        System.out.println("hasil:" +hasil);


    }
}
