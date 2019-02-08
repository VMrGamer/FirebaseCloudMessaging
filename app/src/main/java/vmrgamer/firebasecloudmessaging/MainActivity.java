package vmrgamer.firebasecloudmessaging;

//kamehameha

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.functions.FirebaseFunctions;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;

    private EditText mFirstNumberField;
    private EditText mSecondNumberField;
    private EditText mAddResultField;
    private EditText mMessageInputField;
    private EditText mMessageOutputField;
    private Button mCalculateButton;
    private Button mAddMessageButton;
    private Button mSignInButton;

    private FirebaseFunctions mFunctions;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFirebaseAuthListner();
        mFunctions = FirebaseFunctions.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void initializeFirebaseAuthListner() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, ": User signed in: " + user.getUid());
                } else {
                    Log.d(TAG, "User signed out");
                }
            }
        };
    }
}
