package permission.android.chinnadurai;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import permission.android.library.Permission;
import permission.android.library.PermissionResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Permission.OnRequestPermissionsBack {
    private static final String TAG = "MainActivity";
    private TextView camera, gps, call;
    private Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initViews();


    }

    private void initViews() {
        gps = (TextView) findViewById( R.id.gpsStatus );
        call = (TextView) findViewById( R.id.callStatus );

        camera = (TextView) findViewById( R.id.cameraStatus );
        checkButton = (Button) findViewById( R.id.checkButton );
        checkButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        new Permission.Builder( this )
                .withPermissions( Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS )
                .requestId( 1 )
                .setListener( this )
                .check();


    }


    @Override
    public void onRequestBack(int requestId, @NonNull PermissionResponse permissionResponse) {
        if (permissionResponse.isGranted( Manifest.permission.CAMERA )) {
            camera.setText( "Allow" );
            camera.setTextColor( getResources().getColor( android.R.color.holo_green_dark ) );
        }
        if (permissionResponse.isGranted( Manifest.permission.CALL_PHONE )) {
            gps.setText( "Allow" );
            gps.setTextColor( getResources().getColor( android.R.color.holo_green_dark ) );

        }
        if (permissionResponse.isGranted( Manifest.permission.READ_CONTACTS )) {
            call.setText( "Allow" );
            call.setTextColor( getResources().getColor( android.R.color.holo_green_dark ) );

        }

        if (permissionResponse.isOnNeverAskAgain( Manifest.permission.CAMERA ))
            Log.d( TAG, "onRequestBack: CAMERA" );

    }
}
