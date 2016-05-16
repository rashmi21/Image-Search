package assignment.outlook.com.outlookassignment.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtils {
    private static int TIME_OUT = 60 * 1000;


    private static final String TAG = HttpUtils.class.getSimpleName();
    private Context mContext;

    public HttpUtils(Context context) {
        mContext = context;
    }

    public String doGetRaw(String stringURL) {


        Log.d("Rrequest url:", stringURL);
        String respoString = null;
        try {
            URL url = new URL(stringURL);
            MyHostnameVerifier verifier = new MyHostnameVerifier();
            HttpsURLConnection
                    .setDefaultHostnameVerifier(verifier);
            HttpsURLConnection.setDefaultHostnameVerifier(verifier);
            try {
                trustAllHttpsCertificates();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setRequestProperty("Content-Type", "application/json");

            httpConn.setRequestMethod("GET");
            httpConn.setReadTimeout(TIME_OUT);
            httpConn.setConnectTimeout(TIME_OUT);
            httpConn.connect();
            /*
             * httpConn.setDoOutput(true); httpConn.setDoInput(true);
			 */

            // if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {

            int statusCode =0;
            InputStreamReader isr = null;

            //temp Written by ashish needs to change.
            try {
                // attempt to execute request
                statusCode = httpConn.getResponseCode();
            } catch (EOFException e) {
                return "";
            }

            if (statusCode != 200 && statusCode != 204 && statusCode != 201) {
                isr = new InputStreamReader(
                        httpConn.getErrorStream());
            } else {
                isr = new InputStreamReader(
                        httpConn.getInputStream());
            }

            String line;
            String tempResponse = "";

            BufferedReader br = new BufferedReader(isr);

            // Create a string using response from web services
            while ((line = br.readLine()) != null)
                tempResponse = tempResponse + line;
            respoString = tempResponse;
            if (!TextUtils.isEmpty(respoString)){
                return respoString;}

            Log.d(TAG, "JSON-->" + respoString);

            // JSONObject jsonResponse = new JSONObject(respoString);
            // respoString = jsonResponse.getString("status");


        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respoString;
    }

    private TrustManager[] getTrustMgr() {
        TrustManager[] certs = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String t)
                    throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] certs, String t)
                    throws CertificateException {
            }
        }};
        return certs;
    }

    public void updateContext(Context context) {
        mContext=context;
    }

    class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String urlHostName, String certHostName) {
            return true;
        }

        public boolean verify(String urlHost, SSLSession sslSession) {
            return true;
        }
    }

    public class miTM implements TrustManager,
            X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }

    private void trustAllHttpsCertificates() throws Exception {

        // Create a trust manager that does not validate certificate chains:
        TrustManager[] trustAllCerts =

                new TrustManager[1];

        TrustManager tm = new miTM();

        trustAllCerts[0] = tm;

        javax.net.ssl.SSLContext sc =

                javax.net.ssl.SSLContext.getInstance("SSL");

        sc.init(null, trustAllCerts, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(

                sc.getSocketFactory());

    }


    public static void setTIME_OUT(int tIME_OUT) {
        TIME_OUT = tIME_OUT;
    }

}
