package avinash.distributeddownloadingsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import avinash.distributeddownloadingsystem.Database.Download_Info;
import avinash.distributeddownloadingsystem.Database.SQLiteHelper;

import static avinash.distributeddownloadingsystem.Database.SQLiteHelper.COLUMN_FILENAME;
import static avinash.distributeddownloadingsystem.Database.SQLiteHelper.COLUMN_ID;
import static avinash.distributeddownloadingsystem.Database.SQLiteHelper.COLUMN_KEY;
import static avinash.distributeddownloadingsystem.Database.SQLiteHelper.COLUMN_URL;
import static avinash.distributeddownloadingsystem.Database.SQLiteHelper.COLUMN_isADMIN;

/**
 * Created by Avinash Sharma on 15-Apr-17.
 */

public class ManageDownloads extends AppCompatActivity {
    ArrayList<Download_Info> DownloadList;
    RecyclerView RV;
    SQLiteHelper sq;

    adapter DownloadAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_manage);
        RV = (RecyclerView) findViewById(R.id.RV);
        DownloadList = new ArrayList<>();
        sq = new SQLiteHelper(this);
        GetList();
        DownloadAdapter = new adapter();
        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(DownloadAdapter);
        context = this;
    }

    public void GetList() {
        Cursor c = sq.GetListCursor();

        c.moveToFirst();
        Download_Info obj;
        while (!c.isAfterLast()) {
            String Fname, URL, key;
            long id;
            int isAdmin;
            Fname = c.getString(c.getColumnIndex(COLUMN_FILENAME));
            URL = c.getString(c.getColumnIndex(COLUMN_URL));
            isAdmin = c.getInt(c.getColumnIndex(COLUMN_isADMIN));
            key = c.getString(c.getColumnIndex(COLUMN_KEY));
            id = c.getInt(c.getColumnIndex(COLUMN_ID));


            obj = new Download_Info(id, Fname, URL, isAdmin, key);
            DownloadList.add(obj);
            c.moveToNext();

        }
        c.close();

    }


    private class VH extends RecyclerView.ViewHolder {
        CardView CV;
        TextView FileName;
        TextView URL;
        TextView isAdmin;
        TextView size;

        private VH(View view) {
            super(view);
            CV = (CardView) findViewById(R.id.CV);
            FileName = (TextView) findViewById(R.id.tvFname);
            URL = (TextView) findViewById(R.id.tvURL);
            isAdmin = (TextView) findViewById(R.id.tvisAdmin);
            size = (TextView) findViewById(R.id.tvSize);

        }

    }


    public class adapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater LI = getLayoutInflater();
            View v = LI.inflate(R.layout.list_item, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, final int position) {
            final Download_Info obj = DownloadList.get(position);

            holder.FileName.setText(obj.getFileName());
            holder.URL.setText(obj.getURL());
            if (obj.getAdmin() != '0')
                holder.isAdmin.setText("You are the administrator for this download");
            else
                holder.isAdmin.setText(" ");


            holder.CV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  ArrayList<Download_Info> A = new ArrayList<Download_Info>();
                   /* if(obj.getAdmin()==1) {
                        for (int i = 0; i < DownloadList.size(); i++) {
                            Download_Info a = DownloadList.get(position);
                            Download_Info b = DownloadList.get(i);
                            if (b.getKey() == a.getKey()) {
                                A.add(b);

                            }


                        }*/
                    Intent i = new Intent(context, Download_Details.class);
                    i.putExtra("Key", obj.getKey());
                    i.putExtra("Admin",obj.getAdmin());


                }


            });

        }

        @Override
        public int getItemCount() {
            return DownloadList.size();
        }
    }
}
