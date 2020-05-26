package luis.idp;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import java.util.*;

public class KeycloakPrincipalExtractor 
  implements PrincipalExtractor {
 
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
	System.out.println("Principal at map:"+map);
        return map.get("preferred_username");
    }
}
