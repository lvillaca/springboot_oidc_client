package luis.idp;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.*;

public class KeycloakAuthoritiesExtractor 
  implements AuthoritiesExtractor {
    List<GrantedAuthority> FREE_AUTHORITIES
     = AuthorityUtils.commaSeparatedStringToAuthorityList(
     "ROLE_TESTE,ROLE_FREE");
 
    @Override
    public List<GrantedAuthority> extractAuthorities
      (Map<String, Object> map) {

	System.out.println("roles map:"+map);

        if (Objects.nonNull(map.get("roles"))) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList( ((String)String.join(",",(List<String>)map.get("roles"))) );
        }
        return FREE_AUTHORITIES;
    }
}
