package avinash.distributeddownloadingsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import avinash.distributeddownloadingsystem.Database.Download_Info;
import avinash.distributeddownloadingsystem.Database.SQLiteHelper;

public class DownloadPart extends AppCompatActivity {

    private EditText url,key,file;
    private Button download;
    Context context;
    SQLiteHelper sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_part);
        context=this;
        url = (EditText) findViewById(R.id.link);
        key = (EditText) findViewById(R.id.key);
        file = (EditText) findViewById(R.id.file);
        download = (Button) findViewById(R.id.download);
        sq = new SQLiteHelper(this);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!url.getText().toString().contains(" ")&&!key.getText().toString().contains(" ")&&!url.getText().toString().equals("")&&!key.getText().toString().equals(""))
                {
                    Toast.makeText(context, "Download has started", Toast.LENGTH_SHORT).show();
                    String JSON_LinkKey = "{\"LinkKey\":{\"link\":\" Link  \",\"Key\":Key}}";
                    try {
                        JSONObject LinkKey = (new JSONObject(JSON_LinkKey)).getJSONObject("LinkKey");

                        //Communicate with the API here.

                    }catch (Exception e){e.printStackTrace();}
                    Download_Info DI = new Download_Info(file.getText().toString(), url.getText().toString(), 0, key.getText().toString());
                    sq.addRow(DI);

                }
                else
                {
                    if(url.getText().toString().contains(" ")||url.getText().toString().equals(""))
                        url.setError("url cannot contain blank spaces");
                    if(key.getText().toString().contains(" ")||key.getText().toString().equals(""))
                        key.setError("key cannot contain blank spaces");
                }
            }
        });
    }
}
