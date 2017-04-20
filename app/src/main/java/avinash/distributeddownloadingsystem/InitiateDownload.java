package avinash.distributeddownloadingsystem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import avinash.distributeddownloadingsystem.Database.Download_Info;
import avinash.distributeddownloadingsystem.Database.SQLiteHelper;

public class InitiateDownload extends Activity {

    private EditText url,numOfRequests, file;
    private Button download;
    Context context;
    ProgressDialog progressDialog;
    SQLiteHelper sq;

    private String requestUrl = "localhost:3000/download";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_initiate);
        context=this;
        url = (EditText) findViewById(R.id.link);
        numOfRequests = (EditText) findViewById(R.id.editText2);
        download = (Button) findViewById(R.id.download);
        file = (EditText) findViewById(R.id.tvFilename);
        progressDialog = new ProgressDialog(context);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!url.getText().toString().contains(" ")&&!numOfRequests.getText().toString().contains(" ")&&!url.getText().toString().equals("")&&!numOfRequests.getText().toString().equals(""))
                {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    Download_Info DI = new Download_Info(file.getText().toString(), url.getText().toString(), 1, "Pending");
                    sq.addRow(DI);
                    makeRequest(url.getText().toString(),Integer.parseInt(numOfRequests.getText().toString()));
                    progressDialog.dismiss();

                }
                else
                {
                    if(url.getText().toString().contains(" ")||url.getText().toString().equals(""))
                        url.setError("url cannot contain blank spaces");
                    if(numOfRequests.getText().toString().contains(" ")||numOfRequests.getText().toString().equals(""))
                        numOfRequests.setError("key cannot contain blank spaces");
                }
            }
        });
    }

    private void makeRequest(String url, int n) {
        FirstRequest firstRequest = new FirstRequest(url,n);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrl,firstRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
