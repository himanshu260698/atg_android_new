package com.ATG.World.utilities;

/**
 * Created by PrannavK on 1/9/2018.
 */

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.ATG.World.models.LocationDetails;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;


    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;
    boolean isInternetAvailable=false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public GPSTracker(Context context,boolean isIn) {
        this.mContext = context;
        this.isInternetAvailable=isIn;
        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSEnabled){this.canGetLocation = true;}

        if (this.isGPSEnabled) {
            getLocation();
            uploadLocation(this.getLatitude(),this.getLongitude(),"GPS");

        }
        else{
            showSettingsAlert();

        }



    }


    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if (!isGPSEnabled && !isNetworkEnabled ) {


                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);


                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {

                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    public void uploadLocation(Double latitude,Double longitude,String means){
        if(NetworkUtility.isNetworkAvailable(mContext)) {

            AtgService atgService = AtgClient.getClient().create(AtgService.class);

            Call<LocationDetails> call = atgService.Postlocation(UserPreferenceManager.getUserId(mContext),latitude,longitude);
            call.enqueue(new Callback<LocationDetails>() {
                @Override
                public void onResponse(Call<LocationDetails> call, Response<LocationDetails> response) {
                    // progressBar.setVisibility(View.GONE);
                    int responseCode = response.code();
                    if (responseCode == 200) {

                        Log.i("","Location uploaded by"+means+latitude+longitude);
                       // loadHomeFragment();
                    }
                }

                @Override
                public void onFailure(Call<LocationDetails> call, Throwable t) {
                    if (call.isCanceled()) {
                       // Toast.makeText(mContext, "Failed to update Location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
           // Toast.makeText(mContext, "Network not available", Toast.LENGTH_SHORT).show();
        }

    }












    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }



    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

         return latitude;

    }

    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }



    public boolean canGetLocation() {
        return this.canGetLocation;
    }



    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("Location Access Required");

        // Setting Dialog Message
        alertDialog.setMessage("ATG need GPS access to show you nearby events, jobs, meetups and education. Go to settings menu to enable Location Services?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //LocationNetwork();

               if(isInternetAvailable) {
                    Log.i("state","sent by ip ");
                    new RetriveByIP().execute();
                }
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

//Get location by IP

    class RetriveByIP extends AsyncTask<Void, Void, String> {
        static final String API_URL = "http://ip-api.com/json/?callback=yourfunction";


        private Exception exception;

        protected void onPreExecute() {
          //  progressBar.setVisibility(View.VISIBLE);
          //  responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
          //  String email = emailText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
           // progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
          //  responseView.setText(response);
            // TODO: check this.exception
            // TODO: do something with the feed
            String JsonResponse;
            JsonResponse=response.substring(13,response.length()-3);
           // responseView.setText(JsonResponse);

            try {
                JSONObject object = (JSONObject) new JSONTokener(JsonResponse).nextValue();

                Double latitude = object.getDouble("lat");
                Double longitude = object.getDouble("lon");



               // Toast.makeText(mContext,"By Ip Latitude"+Lat+"Longitude"+longi,Toast.LENGTH_SHORT).show();



                uploadLocation(latitude,longitude,"IP");
            } catch (JSONException e) {
                // Appropriate error handling code
            }
        }
    }
}