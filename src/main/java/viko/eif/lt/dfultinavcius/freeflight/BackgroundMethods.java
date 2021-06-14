package viko.eif.lt.dfultinavcius.freeflight;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class BackgroundMethods
{
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public static String getClientIpAddress(HttpServletRequest request) throws IOException {
        String ip = request.getRemoteAddr();
        for (String header : IP_HEADER_CANDIDATES) {
            if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
            {
                URL whatismyip = null;
                try {
                    whatismyip = new URL("http://checkip.amazonaws.com");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    return in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }

        }
        return request.getRemoteAddr();
    }
}
