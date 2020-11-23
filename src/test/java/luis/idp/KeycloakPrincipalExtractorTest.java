package luis.idp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class KeycloakPrincipalExtractorTest {

    public static final String SPRING_STR = "WHATEVER";
    public static final String KEY_AUTH = "preferred_username";

    @Test
    public void testPrincOkFree() throws IOException {
        KeycloakPrincipalExtractor principalExtractor = Mockito.spy(KeycloakPrincipalExtractor.class);
        Map objs = new HashMap();
        objs.put(KEY_AUTH, SPRING_STR);
        Object principal =  principalExtractor.extractPrincipal(objs);
        Assert.assertEquals(principal, SPRING_STR);

    }
}

