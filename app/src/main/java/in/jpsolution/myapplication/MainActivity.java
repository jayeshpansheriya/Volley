package in.jpsolution.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import in.jpsolution.myapplication.VollyClass.VolleyApiCAll;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // You should add NETWORK Validation
        _loadAPI_POST();


    }

    public void _loadAPI_POST()
    {
        // Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.
        //Volley is not suitable for large download or streaming operations, since Volley holds all responses in memory during parsing.
        // Read https://developer.android.com/training/volley/index.html

        //Add New Address
        Map<String, String> jsonPOST = new HashMap<>();
        jsonPOST.put("mode", "loginstu");
        jsonPOST.put("studentEmailId", "ishani.vnurture@gmail.com");
        jsonPOST.put("passowrd", "Ishani@123");
        jsonPOST.put("deviceToken", "aaaffff");

        _SEND(jsonPOST); // Check INTERNET is ON or NOT ?


    }


    private void _SEND(Map<String, String> getPARAM)
    {
        try
        {

            String URL = "http://vnurture.in/vn/vnurture/admin/webservice.php";
            VolleyApiCAll volleyApiCAll = new VolleyApiCAll(MainActivity.this);
            volleyApiCAll.Volley_POST(getPARAM, URL, new VolleyApiCAll.VolleyCallback()
            {
                @Override
                public void onSuccessResponse(String result)
                {


                    try
                    {
                        if(result.matches("VOLLEY_NETWORK_ERROR"))
                        {
                            Toast.makeText(MainActivity.this, "NETWORK PROBLEM", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try
                            {


                                System.out.println("RESULT"+result);
                                // GET JSON THROUGH result

                                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                Log.d("Response ",result);


                            }
                            catch (Exception e)
                            {

                                e.printStackTrace();
                            }
                        }
                    }
                    catch (Exception e)
                    {

                        e.printStackTrace();
                    }
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
