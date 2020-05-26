package luis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.core.Authentication;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.StringBuilder;
import java.security.Principal;
import java.util.Map;
import java.net.URLEncoder;

/**
 * Classe de busca - Principal e Role
 * @author Luis
 */
@RestController
public class PullData {

    private static final Logger logger = LoggerFactory.getLogger(PullData.class);

    @GetMapping(path = "/hasLogged")
    public ResponseEntity<String> geraPrincipalJson(Principal userPrincipal) {

        return new ResponseEntity<String>("{\"user\":\""+userPrincipal.getName()+"\"}",HttpStatus.OK);
 
  }

   @GetMapping("/details")
   public Map<String, Object> details(Principal userPrincipal) {

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) userPrincipal;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        Map<String, Object> details = (Map<String, Object>) authentication.getDetails();

        StringBuilder appender = new StringBuilder();

        return details;
   }
  
   @Value("${security.oauth2.client.logoutUri}") // TODO find a better logout strategy
   private String path;
   @Value("${security.oauth2.client.clientId}") // TODO find a better logout strategy
   private String clientId;
 
   @GetMapping(path = "/logoutOIDC")
   public ResponseEntity<String> logoutOIDC(HttpServletRequest request) throws ServletException, IOException, UnsupportedEncodingException {
       String returnPath = request.getRequestURL().toString();
       returnPath = returnPath.substring(0,returnPath.indexOf(request.getRequestURI())); // set SP root path
 
       request.logout();

       String redirectURL = this.path + "?client_id=" + this.clientId + "&redirect_uri=" + URLEncoder.encode(returnPath, java.nio.charset.StandardCharsets.UTF_8.toString());
 
       logger.debug("exiting - needs to redirect to" + redirectURL);
       return new ResponseEntity<String>("{\"url\":\""+redirectURL+"\"}",HttpStatus.OK);
  } 

}
