package luis.idp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
        public class KeycloakAuthoritiesExtractorTest {

    public static final String KEY_AUTH = "roles";
    public static final List<String> INPUT_FROM_KEYCLOAK =  Stream.of("ROLE_a", "ROLE_b").collect(Collectors.toList());
    public static final String SPRING_ROLES = "ROLE_a, ROLE_b";

    @Test
    public void testAuthOkFree() throws IOException {
        KeycloakAuthoritiesExtractor auth = Mockito.spy(KeycloakAuthoritiesExtractor.class);
        Map objs = new HashMap();
        List<GrantedAuthority> respAuths =  auth.extractAuthorities(objs);
        Assert.assertEquals(respAuths, auth.FREE_AUTHORITIES);

        objs.put(KEY_AUTH, INPUT_FROM_KEYCLOAK);
        respAuths =  auth.extractAuthorities(objs);
        Assert.assertEquals(respAuths,
                AuthorityUtils.commaSeparatedStringToAuthorityList(SPRING_ROLES ));

    }
}

