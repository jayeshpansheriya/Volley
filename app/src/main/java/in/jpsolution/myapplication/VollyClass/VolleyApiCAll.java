package in.jpsolution.myapplication.VollyClass;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class VolleyApiCAll
{
    Context context;

    public VolleyApiCAll(Context context_) {
        context=context_;
    }

    public interface VolleyCallback
    {
        void onSuccessResponse(String result);
    }
    public void Volley_GET(String url, final VolleyCallback callback)
    {

        StringRequest strREQ = new StringRequest(Request.Method.GET, url, new Response.Listener <String> ()
        {
            @Override
            public void onResponse(String Response)
            {
                callback.onSuccessResponse(Response);
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError e)
            {
                callback.onSuccessResponse("VOLLEY_NETWORK_ERROR");
            }
        })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", "enter_username");
                params.put("Password", "enter_password");
                params.put("grant_type", "password");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(strREQ);
    }
    private HashMap<String, String> createBasicAuthHeader(String username, String password)
    {
        HashMap<String, String> headerMap = new HashMap<String, String>();

        String credentials = username + ":" + password;
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);

        return headerMap;
    }
    String BOUNDARY = "INTELLIJ_AMIYA";

    public void Volley_POST(final Map<String, String> params, String url, final VolleyCallback callback)
    {
        try
        {

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    callback.onSuccessResponse(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    callback.onSuccessResponse("POST_ERROR");

                }
            })
            {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    return new JSONObject(params).toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };

            requestQueue.add(stringRequest);
        }
        catch (NegativeArraySizeException n)
        {
            n.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}