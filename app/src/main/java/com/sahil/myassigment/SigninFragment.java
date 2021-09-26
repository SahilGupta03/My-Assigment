package com.sahil.myassigment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SigninFragment extends Fragment {
    private EditText vemail,vpassword;
    CheckBox checkBox;

    GoogleSignInClient mGoogleSignInClient;
private static int RC_SIGN_IN=100;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    public SigninFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_signin, container, false);
        Button btn_login= (Button)view.findViewById(R.id.btn_login);
        EditText vemail= (EditText) view.findViewById(R.id.vemail);
        EditText vpassword= (EditText) view.findViewById(R.id.vpassword);
        fAuth=FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());

        SignInButton signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email=vemail.getText().toString().trim();
               String password=vpassword.getText().toString().trim();

               if (TextUtils.isEmpty(email)){
                   vemail.setError("Email is required");
                   return;
               }
               if (TextUtils.isEmpty(password)){
                   vpassword.setError("password is required");
                   return;
               }
               if (password.length()<6){
                   vpassword.setError("Password Must be >=6 character ");
                   return;
               }
//               progressBar.setVisibility(View.VISIBLE);
else {


               fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(getActivity(), "Logged is Successfully", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getActivity(),HomeActivity.class));

                       }else {
                           Toast.makeText(getActivity(), "Erro!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                           progressBar.setVisibility(View.GONE);
                       }
                   }
               });}
           }
       });

        return view;
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(getActivity(), "useremail: "+personEmail, Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(personEmail,personName).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Sucessfull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),HomeActivity.class);
                            startActivity(intent);


                        }else {
                            Toast.makeText(getActivity(), "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

            startActivity(new Intent(getActivity(),HomeActivity.class));
        } catch (ApiException e) {


            Log.d("Message",e.toString());
        }
    }
}