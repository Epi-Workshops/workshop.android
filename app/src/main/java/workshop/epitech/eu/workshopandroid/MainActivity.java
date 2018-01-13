package workshop.epitech.eu.workshopandroid;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton twitterLoginButton;
    TwitterSession session;
    String token;
    String secret;
    long userId;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig("nIgmTLRQTHJvuqaS5r6OVO0U5", "XtSS8r3x75SzCLVeFyTzNze50AxPMtHRDVOU2noJoRJqRW0YWU"))
                .build();
        Twitter.initialize(config);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, TweetsActivity.class);

        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                session = result.data;
                userName = session.getUserName();
                token = session.getAuthToken().token;
                secret = session.getAuthToken().secret;
                intent.putExtra("Name", userName);
                startActivity(intent);
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }
}
