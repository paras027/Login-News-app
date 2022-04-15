package com.codingstuff.customtablayout;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;

public class SignInFragment extends Fragment {
    TextView email, pass, regis, forgot;
    Button button;
    ImageView google;
    FirebaseAuth auth;
    ProgressBar progressBar;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        email = view.findViewById(R.id.editEmailSignIN);
        pass = view.findViewById(R.id.editPassSignIn);

        regis = view.findViewById(R.id.signUpText);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new SignUpFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.your_placeholderi, SignUpFragment.class, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        button = view.findViewById(R.id.signInBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String femail = email.getText().toString();
                String fpass = pass.getText().toString();

                if (!femail.isEmpty()) {
                    email.setError(null);
                    if (!fpass.isEmpty()) {

                        auth = FirebaseAuth.getInstance();

                        auth.signInWithEmailAndPassword(femail, fpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getActivity().getApplicationContext(), "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getActivity().getApplicationContext(),News.class);
                                startActivity(intent);
                            }
                        });


                    } else {
                        pass.setError("Enter Password");
                    }
                } else {
                    email.setError("Enter Email");
                }
            }
        });
        forgot = view.findViewById(R.id.textView5);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity2.class);
                startActivity(intent);


            }
        });
        return view;
    }

}