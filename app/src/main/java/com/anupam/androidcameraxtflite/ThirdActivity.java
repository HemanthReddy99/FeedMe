package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ThirdActivity extends AppCompatActivity {

    private final int jsoncode = 1;
    private ListView listView;
    ArrayList<Recipe> tennisModelArrayList;
    private RecipeAdapter tennisAdapter;
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String JsonURL = getIntent().getStringExtra("url");
        listView = findViewById(R.id.recipesyum);
        fetchJSON(JsonURL);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe r = tennisModelArrayList.get(position);
                String url = r.getUrl();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void fetchJSON(final String jsonurl) {
        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HTTPRequest req = new HTTPRequest(jsonurl);
                    response = req.prepare(HTTPRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String response) {
                //do something with response
                //Toast.makeText(ThirdActivity.this, response, Toast.LENGTH_SHORT).show();
                Log.d("newwwss",response);
                onTaskCompleted(response,jsoncode);
            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:

                if (isSuccess(response)) {
                    removeSimpleProgressDialog();  //will remove progress dialog
                    tennisModelArrayList = getInfo(response);
                    tennisAdapter = new RecipeAdapter(this,tennisModelArrayList);
                    listView.setAdapter(tennisAdapter);

                }else {
                   Toast.makeText(ThirdActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject("response");
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public ArrayList<Recipe> getInfo(String response) {
        ArrayList<Recipe> tennisModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("title").equals("Recipe Puppy")) {

                JSONArray dataArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < dataArray.length(); i++) {

                    Recipe playersModel = new Recipe();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    playersModel.setTitle(dataobj.getString("title"));
                    playersModel.setUrl(dataobj.getString("href"));
                    playersModel.setIngredient(dataobj.getString("ingredients"));
//                    playersModel.setImgURL(dataobj.getString("imgURL"));
                    tennisModelArrayList.add(playersModel);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tennisModelArrayList;
    }

    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("title").equals("Recipe Puppy")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


