package com.securekey.securekey.ui.bitmanipulation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.securekey.securekey.R;
import com.securekey.securekey.ui.textparser.ParserFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button cal;
    private EditText inputA;
    private EditText inputB;
    private TextView result;


    public BitFragment () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BitFragment newInstance (String param1, String param2) {
        BitFragment fragment=new BitFragment ();
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
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_bit, container, false);

        cal = (Button) view.findViewById (R.id.calculate);
        inputA = (EditText) view.findViewById (R.id.input_a);
        inputB = (EditText) view.findViewById (R.id.input_b);
        result = (TextView) view.findViewById (R.id.result);

        cal.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                String a = inputA.getText ().toString ();
                String b = inputB.getText ().toString ();

                if (a.isEmpty () || b.isEmpty ())
                    Toast.makeText (getContext (), getString(R.string.non_empty), Toast.LENGTH_SHORT).show ();
                else {
                    int count = 0;
                    try {
                        count = flippedCount (Integer.parseInt (a), Integer.parseInt (b));
                        result.setText (count+"");
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText (getContext (), getString(R.string.too_big), Toast.LENGTH_SHORT).show ();
                    }

                }
            }
        });


        return view;
    }

    // Function that count set bits
    private  int countSetBits(int n)
    {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    // Function that return count of
    // flipped number
    private  int flippedCount(int a, int b)
    {
        // Return count of set bits in
        // a XOR b
        return countSetBits(a ^ b);
    }

}
