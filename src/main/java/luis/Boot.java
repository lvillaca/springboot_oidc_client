package luis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe principal SpringBoot.
 * @author Luis
 */
@SpringBootApplication
@RestController
public class Boot {

    static {
            //for localhost testing only
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                 new javax.net.ssl.HostnameVerifier(){

                  public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                             if (hostname!=null && sslSession!=null) {
                                    return true;
                             } else return true;
                    }
           });
    }

    /**
     * Sobe o SpringBoot.
     * @param args
     */
    //classe principal do main
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }


}
