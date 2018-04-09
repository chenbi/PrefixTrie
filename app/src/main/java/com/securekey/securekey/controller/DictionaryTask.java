package com.securekey.securekey.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.securekey.securekey.R;
import com.securekey.securekey.data.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by chenbi on 3/22/18.
 */

public class DictionaryTask extends AsyncTask<String, Integer, Set<String>> {

    public static final int INSERT = 0;
    public static final int PARSE = 1;
    private int flag;
    private Context cxt;
    private UpdateUI updateUI;

    public interface UpdateUI{
        void parseCompleted(String s);
        void loadCompleted();
        void updateProgress(int i);
    }

    public DictionaryTask (Context cxt, UpdateUI updateUI, int flag) {
        this.updateUI = updateUI;
        this.flag = flag;
        this.cxt = cxt;
    }

    protected void onProgressUpdate(Integer... progress) {
        updateUI.updateProgress (progress [0]);
    }

    @Override
    protected Set<String> doInBackground (String... strings) {
        if (flag == INSERT){
            InputStream inputStream = cxt.getResources().openRawResource(R.raw.dictionary);
            BufferedReader reader = new BufferedReader(new InputStreamReader (inputStream));
             try {
                 String line;
                 int num = 1;
                while (  (line = reader.readLine()) != null) {
                    Log.d (this.toString (),line);
                    Dictionary.getInstance ().insert (line);
                    onProgressUpdate (num*100/10000);
                    num++;
                }
                //bruteForceAttack("");
            } catch (IOException e) {
                return null;
            }
            return null;
        }else{

            Dictionary.getInstance ().parse (strings[0]);
            return Dictionary.getInstance ().searchResult;
        }

    }

//    private void bruteForceAttack (String s) {
//
//        if (s.length ()==8) return;
//
//        for (int i=0; i<26;i++){
//            String hash = (s+((char) ('a'+i)));
//            Log.d (this.toString (), hash);
//            if (hash.equals ("aaaaaaab")) {
//
//                throw new StackOverflowError ("found aaaaapwd");
//            }
//            else
//                bruteForceAttack(hash);
//        }
//
//    }

    protected void onPostExecute(Set<String> strings) {
        if (flag == PARSE)
            updateUI.parseCompleted (strings.toString ());
        else
            updateUI.loadCompleted ();

    }
}
