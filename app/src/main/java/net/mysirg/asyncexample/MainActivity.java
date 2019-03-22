package net.mysirg.asyncexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ListView mainList;
    ProgressBar progressBar;
    private String[] texts={"AA","BB","CC","DD","EE","FF","AA","BB","CC","DD","EE","FF","AA","BB","CC"
            ,"DD","EE","FF","AA","BB","CC","DD","EE","FF","AA","BB","CC","DD","EE","FF","AA","BB","CC","DD","EE","FF"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        mainList =(ListView)findViewById(R.id.listview);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        mainList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));

        new Mytask().execute();
    }


    class Mytask extends AsyncTask<Void, String,Void>{

        private ArrayAdapter<String> adapter;
        private  int count=0;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) mainList.getAdapter();
           progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            for (String item: texts){
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            return null;

            }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int) (((double)count/texts.length)*10000));
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"All items were added successfully",Toast.LENGTH_SHORT).show();
        }
    }
    }

