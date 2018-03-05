package com.ATG.World.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.User_details;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkUtility;
import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfileFragment extends Fragment {


    @BindView(R.id.e_username)
    EditText username;
    @BindView(R.id.e_firstname)
    EditText firstName;
    @BindView(R.id.e_lastname)
    EditText lastName;
    @BindView(R.id.e_email)
    EditText email;
    @BindView(R.id.e_mobile)
    EditText mobileNo;
    @BindView(R.id.e_phone)
    EditText phoneNo;
    @BindView(R.id.e_location)
    EditText location;
    @BindView(R.id.e_aboutme)
    EditText aboutMe;
    @BindView(R.id.e_profiletag)
    EditText profileTag;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.e_profession)
    EditText profession;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    CircleImageView circleImageView;
    Button saveUpdate;
    Button button;
    private Unbinder unbinder;
    private List<String> listProfession;
    private ArrayAdapter<String> adapter;
    private AtgService atgService;
    private Uri uri;
    private RxPermissions permissions;
    private byte[] byteArray;
    private int spinPosition;
    private Uri realUri;
    private TextView f_name;
    private TextView l_name;

    public UpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        circleImageView = view.findViewById(R.id.e_profileimage);
        permissions = new RxPermissions(getActivity());
        f_name = view.findViewById(R.id.f_name);
        l_name = view.findViewById(R.id.l_name);
        button = view.findViewById(R.id.update_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSettingsFragment();
            }
        });

        unbinder = ButterKnife.bind(this, view);
        circleImageView.setOnClickListener(v -> checkPermissions());
        saveUpdate = view.findViewById(R.id.updateProfileButton);
        saveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionAvailable();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listProfession = new ArrayList<>();
        additems();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listProfession);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                spinPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });
        if (NetworkUtility.isNetworkAvailable(getActivity())) {
            GetData getData = new GetData();
            getData.execute("http://atg.party/ws-account-setting?user_id=" + UserPreferenceManager.getUserId(getActivity()));
        } else {
            Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
        }
        atgService = AtgClient.getClient().create(AtgService.class);
      /*  Toast.makeText(getActivity(), "onViewCreated called", Toast.LENGTH_SHORT).show();
        Call<WsLoginResponse> getProfileData = atgService.getUserDetails(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        getProfileData.enqueue(new Callback<WsLoginResponse>() {
            @Override
            public void onResponse(Call<WsLoginResponse> call, Response<WsLoginResponse> response) {
                WsLoginResponse wsLoginResponse=response.body();
                Toast.makeText(getActivity(), "Response +"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Response code "+response.code(), Toast.LENGTH_SHORT).show();
                 if(wsLoginResponse.getError_code()==0){
                    User_details user_details=wsLoginResponse.getUser_details();
                    String profilePic=getString(R.string.WS_PROFILE_IMAGE_PATH)+"/"+UserPreferenceManager.getUserId(getActivity())+
                            "/"+"thumb/"+user_details.getProfile_picture();
                    try {
                        if (profilePic!= null) {

                            Glide.with(getActivity())
                                    .load(profilePic)

                                    .into(circleImageView);
                        }


                        if (user_details.getUser_name() != null) {
                            username.setText(user_details.getUser_name());
                        }
                        if (user_details.getFirst_name() != null) {
                            firstName.setText(user_details.getFirst_name());
                        }
                        if (user_details.getLast_name() != null) {
                            lastName.setText(user_details.getLast_name());
                        }
                        if (user_details.getEmail() != null) {

                            email.setText(user_details.getEmail());
                        }
                        if (user_details.getMob_no() != null) {
                            mobileNo.setText(user_details.
                                    getMob_no());
                        }
                        if (user_details.getPhone_no() != null) {
                            phoneNo.setText(user_details.getPhone_no());
                        }
                        if (user_details.getLocation() != null) {
                            location.setText(user_details.getLocation());
                        }
                        if (user_details.getAbout_me() != null) {
                            aboutMe.setText(user_details.getAbout_me());
                        }
                        if (user_details.getTagline() != null) {
                            profileTag.setText(user_details.getTagline());
                        }
                        if (user_details.getUser_type() != null) {
                            spinner.setSelection(spinnerItemSelect(user_details.getUser_type()));
                        }
                        if (user_details.getProfession() != null) {
                            profession.setText(user_details.getProfession());
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    }else {
                    Toast.makeText(getActivity(), "error code "+wsLoginResponse.getError_code(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<WsLoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Throws "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String userData = "";
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char curr = (char) data;
                    userData = userData + curr;
                    data = inputStreamReader.read();
                }
                return userData;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            Log.d(getActivity().getLocalClassName(), "onPostExecute: " + s);
            try {
                JSONObject topObject = new JSONObject(s);
                String accountSetting = topObject.getString("AccountSetting");
                JSONObject acObject = new JSONObject(accountSetting);
                String profilePic = getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(getActivity()) +
                        "/" + "thumb/" + acObject.getString("profile_picture");

                Glide.with(getActivity())
                        .load(profilePic)
                        .into(circleImageView);
                if (!acObject.getString("user_name").equals("")) {
                    username.setText(acObject.getString("user_name"));
                }
                if (!acObject.getString("first_name").equals("")) {
                    firstName.setText(acObject.getString("first_name"));
// Get Firstname
                    f_name.setText(acObject.getString("first_name"));
                }
                if (!acObject.getString("last_name").equals("")) {
                    lastName.setText(acObject.getString("last_name"));
//Get Secondname
                    l_name.setText(acObject.getString("last_name"));
                }
                if (!acObject.getString("email").equals("")) {

                    email.setText(acObject.getString("email"));
                }
                if (!acObject.getString("email").equals("")) {
                    mobileNo.setText(acObject.getString("mob_no"));
                }
                if (!acObject.getString("phone_no").equals("")) {
                    phoneNo.setText(acObject.getString("phone_no"));
                }
                if (!acObject.getString("location").equals("")) {
                    location.setText(acObject.getString("location"));
                }
                if (!acObject.getString("about_me").equals("")) {
                    aboutMe.setText(acObject.getString("about_me"));
                }
                if (!acObject.getString("tagline").equals("")) {
                    profileTag.setText(acObject.getString("tagline"));
                }
                if (!acObject.getString("user_type").equals("")) {
                    spinner.setSelection(spinnerItemSelect(acObject.getString("user_type")));
                }
                if (!acObject.getString("profession").equals("")) {
                    profession.setText(acObject.getString("profession"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressBar.setVisibility(View.GONE);
        }
    }

    private void checkPermissions() {
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        selectImage();

                    } else {
                        Toast.makeText(getActivity(), "Please give the permission(s)", Toast.LENGTH_SHORT).show();
                        checkPermissions();
                    }
                });

    }

    private void permissionAvailable() {
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        if (validation()) {
                            updateData();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Please give the permission(s)", Toast.LENGTH_SHORT).show();
                        checkPermissions();
                    }
                });
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    setImagePath();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    getActivity().startActivityForResult(intent, 100);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    getActivity().startActivityForResult(Intent.createChooser(intent, "Select File"), 200);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String capturedImageFilePath = cursor.getString(column_index_data);
                Uri imageuri = Uri.parse("file:///" + capturedImageFilePath);
                circleImageView.setImageBitmap(compressImage(imageuri));
                realUri = Uri.parse(getRealPathFromURI(data.getData()));
            }

        } else if (requestCode == 200) {
            uri = data.getData();
            circleImageView.setImageBitmap(compressImage(uri));
        } else {
            Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap compressImage(Uri imageU) {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getContentResolver().openInputStream(imageU);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
            byteArrayOutputStream = null;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return bitmap;
    }


    private void setImagePath() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis() + ".png");
        values.put(MediaStore.Images.Media.TITLE, System.currentTimeMillis() + ".jpg");
        uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private int spinnerItemSelect(String k) {
        if (k.equals("1")) {
            return 1;
        } else if (k.equals("2")) {
            return 2;
        } else if (k.equals("3")) {
            return 3;
        } else if (k.equals("4")) {
            return 4;
        } else return 0;
    }

    private void additems() {
        listProfession.add("User type");
        listProfession.add("Student");
        listProfession.add("Admin");
        listProfession.add("Professional");
        listProfession.add("Company");
    }

    private byte[] getImageByteArray(Bitmap bmp) {
        byte[] byteArray = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (bmp != null)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
        return byteArray;
    }

    private void updateData() {
        if (NetworkUtility.isNetworkAvailable(getActivity())) {
            if (validation()) {
                progressBar.setVisibility(View.VISIBLE);
                try {
                    // File imageFIle = new File(String.valueOf(realUri));
                    byteArray = getImageByteArray(((BitmapDrawable) circleImageView.getDrawable()).getBitmap());
                    //  RequestBody rFile = RequestBody.create(MediaType.parse("image/jpeg"),file);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("user_profile", "image.png", requestFile);
                    RequestBody uName = RequestBody.create(MediaType.parse("multipart/form-data"), username.getText().toString());
                    RequestBody fName = RequestBody.create(MediaType.parse("multipart/form-data"), firstName.getText().toString());
                    RequestBody lName = RequestBody.create(MediaType.parse("multipart/form-data"), lastName.getText().toString());
                    RequestBody eMail = RequestBody.create(MediaType.parse("multipart/form-data"), email.getText().toString());
                    RequestBody mNo = RequestBody.create(MediaType.parse("multipart/form-data"), mobileNo.getText().toString());
                    RequestBody pNo = RequestBody.create(MediaType.parse("multipart/form-data"), phoneNo.getText().toString());
                    RequestBody loc = RequestBody.create(MediaType.parse("multipart/form-data"), location.getText().toString());
                    RequestBody aM = RequestBody.create(MediaType.parse("multipart/form-data"), aboutMe.getText().toString());
                    RequestBody pT = RequestBody.create(MediaType.parse("multipart/form-data"), profileTag.getText().toString());
                    RequestBody spin = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(spinPosition));
                    RequestBody uId = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(getActivity()));
                    RequestBody pF = RequestBody.create(MediaType.parse("multipart/form-data"), profession.getText().toString());
                    Call<User_details> updateCall = atgService.updateUserDetails(uId, body, uName, fName, lName, pT, eMail, pF, aM, mNo, pNo, loc, spin);
                    updateCall.enqueue(new Callback<User_details>() {
                        @Override
                        public void onResponse(Call<User_details> call, Response<User_details> response) {
                            progressBar.setVisibility(View.GONE);
                            int code = response.code();
                            if (code == 200) {
                                Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                //loadHomeFragment();
                            } else {
                                Toast.makeText(getActivity(), "Failed to update profile " + code, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User_details> call, Throwable t) {
                            Toast.makeText(getActivity(), "Failed" + t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            ;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void loadHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = homeFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    private boolean validation() {
        byte[] abc = null;
        try {
            abc = getImageByteArray(((BitmapDrawable) circleImageView.getDrawable()).getBitmap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (abc == null) {
            Toast.makeText(getActivity(), "Please select profile picture.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (username.getText().toString().equalsIgnoreCase("")) {
            username.requestFocus();
            username.setError("Please enter user name.");
            return false;
        } else if (username.getText().toString().contains(" ")) {
            username.requestFocus();
            username.setError("Username field should not contain space.");
            return false;
        } else if (firstName.getText().toString().equalsIgnoreCase("")) {
            firstName.requestFocus();
            firstName.setError("Please enter first name.");
            return false;
        } else if (lastName.getText().toString().equalsIgnoreCase("")) {
            lastName.requestFocus();
            lastName.setError("Please enter last name.");
            return false;
        } else if (email.getText().toString().equalsIgnoreCase("")) {
            email.requestFocus();
            email.setError("Please enter email address.");
            return false;
        }

        /*else if (mTvMobile.getText().toString().equalsIgnoreCase("")) {
            mTvMobile.requestFocus();
            mTvMobile.setError("Please enter mobile number.");
            return false;
        } else if (mTvMobile.getText().toString().length() < 10) {
            mTvMobile.requestFocus();
            mTvMobile.setError("Mobile number must be 10 digit.");
            return false;
        } else if (mTvPhone.getText().toString().length() < 10 || mTvPhone.getText().toString().length() > 12) {
            mTvPhone.requestFocus();
            mTvPhone.setError("Phone number must be 10 to 12 digit.");
            return false;
        } */
        else if (location.getText().toString().equalsIgnoreCase("")) {
            location.requestFocus();
            location.setError("Please enter location.");
            return false;
        } else if (aboutMe.getText().toString().equalsIgnoreCase("")) {
            aboutMe.requestFocus();
            aboutMe.setError("Please enter about me.");
            return false;
        } else if (profileTag.getText().toString().equalsIgnoreCase("")) {
            profileTag.requestFocus();
            profileTag.setError("Please enter tag line.");
            return false;
        } else if (profession.getText().toString().equalsIgnoreCase("")) {
            profession.requestFocus();
            profession.setError("Please enter profession.");
            return false;
        } else if (spinner.getSelectedItemPosition() == 0) {
            spinner.requestFocus();
            Toast.makeText(getActivity(), "Please select user type.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mobileNo.getText().toString().length() == 0) {
            mobileNo.requestFocus();
            mobileNo.setError("Please enter mobile or phone number.");
            return false;
        } else if (phoneNo.getText().toString().length() < 10 || phoneNo.getText().toString().length() > 12) {
            phoneNo.requestFocus();
            phoneNo.setError("Phone number must be 10 to 12 digit.");
            return false;
        } else if (mobileNo.getText().toString().length() < 10 || mobileNo.getText().toString().length() > 12) {
            mobileNo.requestFocus();
            mobileNo.setError("Mobile number must be 10 to 12 digit.");
            return false;

        } else if (!emailValidation()) {
            email.requestFocus();
            email.setError("Enter a valid email address");
        }
        return true;
    }

    private boolean emailValidation() {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText().toString());
        return matcher.matches();
    }

    private void loadSettingsFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        Fragment fragment = settingsFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).showNButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).showBack();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
