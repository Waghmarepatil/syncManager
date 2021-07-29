package android.cranberry.networksynclibrary;

import java.util.UUID;

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
public class UUIDGenerator {

    public static String randomUUID(int num) {

        UUID uid = null;
        for (int i = 1; i < num; i++) {

            /***** Generating Random UUID's *****/
            uid = UUID.randomUUID();
        }
        return uid.toString();
}

}
