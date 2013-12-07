package com.madan.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Madan on 12/4/13.
 */
public class SignInActivity extends Activity{

    public static Activity mCallingActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final EditText username = (EditText) this.findViewById(R.id.username);
        final EditText password = (EditText) this.findViewById(R.id.password);
        final Context context = this;
        final SignInActivity signInActivity = SignInActivity.this;
        Button signIn = (Button) this.findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText() != null && username.getText().toString().trim().equals(getResources().getString(R.string.registered_user)) && password.getText() != null && password.getText().toString().trim().equals(getResources().getString(R.string.secret))){
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("default_preferences", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn",true);
                    editor.putBoolean("didLogOut",false);
                    editor.putString("username", username.getText().toString().trim());
                    editor.commit();
                    Intent intent = new Intent(signInActivity, mCallingActivity.getClass());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(getResources().getString(R.string.incorrect_login));
                    builder.setTitle(getResources().getString(R.string.incorrect_login_title));
                    builder.setPositiveButton(getResources().getString(R.string.okay),new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });
    }



}
