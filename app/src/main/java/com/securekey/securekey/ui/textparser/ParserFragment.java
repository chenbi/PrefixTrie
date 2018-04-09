package com.securekey.securekey.ui.textparser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.securekey.securekey.R;
import com.securekey.securekey.data.Dictionary;
import com.securekey.securekey.controller.DictionaryTask;


public class ParserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button parseBtn;
    private EditText input;
    private TextView result;
    private ProgressBar progressBar;


    public ParserFragment () {
        // Required empty public constructor
    }


    public static ParserFragment newInstance (String param1, String param2) {
        ParserFragment fragment=new ParserFragment ();
        Bundle args=new Bundle ();
        args.putString (ARG_PARAM1, param1);
        args.putString (ARG_PARAM2, param2);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if (getArguments () != null) {
            mParam1=getArguments ().getString (ARG_PARAM1);
            mParam2=getArguments ().getString (ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_parser, container, false);

        progressBar = (ProgressBar) view.findViewById (R.id.indeterminateBar);
        parseBtn = (Button) view.findViewById (R.id.button);
        input = (EditText)  view.findViewById (R.id.input_txt);
        result = (TextView)  view.findViewById (R.id. result);

        parseBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                dictionaryTask (DictionaryTask.PARSE);
            }
        });


        if (Dictionary.getInstance ().root.children.isEmpty ()){
            Toast toast = Toast.makeText(getContext (), R.string.loading, Toast.LENGTH_SHORT);
            toast.show();
            progressBar.setVisibility (View.VISIBLE);
            dictionaryTask (DictionaryTask.INSERT);
        }

        return view;
    }


    private  void dictionaryTask ( int flag){
        final DictionaryTask task = new DictionaryTask (this.getContext (), new DictionaryTask.UpdateUI () {
            @Override
            public void parseCompleted (String s) {
                result.setText (s);
            }

            @Override
            public void loadCompleted () {
                if (!isAdded ()) return;
                    progressBar.setVisibility (View.GONE);
                    Toast toast=Toast.makeText (getContext (), getString(R.string.loaded), Toast.LENGTH_SHORT);
                    toast.show ();

            }

            @Override
            public void updateProgress (int i) {
                progressBar.setProgress (i);
            }


        }, flag);

        task.execute (input.getText ().toString ());

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed (Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction (uri);
        }
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach (context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener=(OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException (context.toString () + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach () {
        super.onDetach ();
        mListener=null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction (Uri uri);
    }
}
