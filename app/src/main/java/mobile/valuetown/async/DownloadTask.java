package mobile.valuetown.async;

import android.content.ContentValues;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import mobile.valuetown.meta.AsyncResponse;
/**
 * Created by stacyqt on 07/01/2016.
 */
public class DownloadTask extends  AsyncTask<String, String, String>{
    //url du serveur
    public String stringUrl = "http://www.alyasfolies.fr/server.php";
    public AsyncResponse delegate = null;

    //Constructeur recoit le delegate
    public DownloadTask (AsyncResponse d){
        delegate = d;
    }

    //Fonction execute à la fin de la "doItBackground"
    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    //Fonction execute quand le delegue fait appel a DownloadTask.execute(string)
    @Override
    protected String doInBackground(String... Query) {
        // Query vient de .execute(string) : Query[0] est la requete.
        try {
            InputStream is = null;
            OutputStream os = null;
            int len = 20000;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                ContentValues values = new ContentValues();
                values.put("request", Query[0]);
                os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(values));
                writer.flush();
                writer.close();
                os.close();
                int response = conn.getResponseCode();
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    //Formaion de la requete
    private String getQuery(ContentValues params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        result.append("request");
        result.append("=");
        result.append(params.get("request"));

        return result.toString();
    }

    //Trasforme inputstream en string
    public String readIt(InputStream stream, int len) throws IOException{
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
