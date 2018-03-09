package com.ATG.World.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.EditProfileInfoResponse;
import com.ATG.World.models.UserData;
import com.ATG.World.models.UserInformation;
import com.ATG.World.models.UserProfile;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = EditProfileActivity.class.getSimpleName();

    private int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STORAGE_PERMISSION = 1;
    private static final String FILE_PROVIDER_AUTHORITY = "com.example.android.fileprovider";
    private String mTempPhotoPath;
    private Bitmap mResultsBitmap;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.circleImageViewProfilePhoto)
    CircleImageView profilePicture;
    @BindView(R.id.editTextUsersFirstName)
    TextInputEditText usersFirstName;
    @BindView(R.id.editTextUsersLastName)
    EditText usersLastName;
    @BindView(R.id.editTextUsersProfession)
    EditText userProfession;
    @BindView(R.id.editTextUsersEducation)
    EditText userEducation;
    @BindView(R.id.editTextUsersLocation)
    EditText userLocation;
    @BindView(R.id.editTextUsersBio)
    EditText userBio;
    @BindView(R.id.editTextProfileHeadline)
    EditText userProfileTagline;
    @BindView(R.id.imageViewEditProfilePhoto)
    ImageView editProfilePhoto;

    private UserData accountSetting;
    private AtgService atgService;
    private Uri uri;
    private RxPermissions permissions;
    private byte[] byteArray;
    private Uri realUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        accountSetting = new UserData();

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.edit_intro_label);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // API Call for getting User's Profile Details
        atgService = AtgClient.getClient().create(AtgService.class);
        Call<UserProfile> call = atgService.getAccountSettings(Integer.parseInt(UserPreferenceManager.getUserId(this)));
        call.enqueue(fetchAccountSettings);

        String profilePictureUrl = getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(this) + "/thumb/" +
                UserPreferenceManager.getUserImage(this);

        GlideApp.with(EditProfileActivity.this)
                .load(profilePictureUrl)
                .error(R.drawable.ic_avtar_male)
                .into(profilePicture);

        editProfilePhoto.setOnClickListener(view -> {
            selectImage();
        });
    }

    public Callback<UserProfile> fetchAccountSettings = new Callback<UserProfile>() {
        @Override
        public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) {
                }
                return;
            }

            UserProfile accountSettingResponse = response.body();
            if (accountSettingResponse != null) {
                accountSetting = response.body().getArrUData();
                if (accountSetting != null) {
                    setupProfileDetails(accountSetting);
                }
            }
        }

        @Override
        public void onFailure(Call<UserProfile> call, Throwable t) {

        }
    };

    private void setupProfileDetails(UserData userDetails) {
        // Setup User's First Name
        usersFirstName.setText(userDetails.getFirstName());

        // Setup User's Last Name
        usersLastName.setText(userDetails.getLastName());

        // Setup User's Tagline
        userProfileTagline.setText(userDetails.getTagline());

        // Setup User's Location Details
        userLocation.setText(userDetails.getLocation());

        // Setup User's Profile Summary Details
        userBio.setText(userDetails.getAboutMe());

        // Setup User's Profession Details
        userProfession.setText(userDetails.getProfession());
    }

    private void checkPermissions() {
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        selectImage();

                    } else {
                        Toast.makeText(EditProfileActivity.this, "Please give the permission(s)", Toast.LENGTH_SHORT).show();
                        checkPermissions();
                    }
                });

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add Photo!");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    setImagePath();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    EditProfileActivity.this.startActivityForResult(intent, 100);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    EditProfileActivity.this.startActivityForResult(Intent.createChooser(intent, "Select File"), 200);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String capturedImageFilePath = cursor.getString(column_index_data);
                Uri imageuri = Uri.parse("file:///" + capturedImageFilePath);
                profilePicture.setImageBitmap(compressImage(imageuri));
                //  realUri=Uri.parse(getRealPathFromURI(data.getData()));
            }

        } else if (requestCode == 200) {
            uri = data.getData();
            profilePicture.setImageBitmap(compressImage(uri));
        } else {
            Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show();
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = EditProfileActivity.this.getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private Bitmap compressImage(Uri imageU) {
        InputStream inputStream = null;
        try {
            inputStream = EditProfileActivity.this.getContentResolver().openInputStream(imageU);
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
        uri = EditProfileActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private byte[] getImageByteArray(Bitmap bmp) {
        byte[] byteArray = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (bmp != null)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Toast.makeText(this, "Save Clicked", Toast.LENGTH_SHORT).show();
                getEditedProfileDetails();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void getEditedProfileDetails() {
        usersFirstName.getText();
        usersLastName.getText();
        userLocation.getText();
        userBio.getText();
        userProfession.getText();
        userProfileTagline.getText();
        byteArray = getImageByteArray(((BitmapDrawable) profilePicture.getDrawable()).getBitmap());

        Log.d(TAG, "getEditedProfileDetails: " + byteArray);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray);
        MultipartBody.Part body = MultipartBody.Part.createFormData("user_profile", UserPreferenceManager.getUserId(this) + ".png" , requestFile);
        Log.d(TAG, "getEditedProfileDetails: " + body);
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(this));
        RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(this));
        RequestBody firstName = RequestBody.create(MediaType.parse("multipart/form-data"), usersFirstName.getText().toString());
        RequestBody lastName = RequestBody.create(MediaType.parse("multipart/form-data"), usersLastName.getText().toString());
        RequestBody tagline = RequestBody.create(MediaType.parse("multipart/form-data"), userProfileTagline.getText().toString());
        RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserEmail(this));
        RequestBody profession = RequestBody.create(MediaType.parse("multipart/form-data"), userProfession.getText().toString());
        RequestBody about_me = RequestBody.create(MediaType.parse("multipart/form-data"), userBio.getText().toString());
        RequestBody mob_no = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(this));
        RequestBody phone_no = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(this));
        RequestBody location = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserLocatione(this));
        RequestBody userType = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserType(this));

        // Call Edit Profile API
        Call<EditProfileInfoResponse> editProfileCall = atgService.editProfile(userId,
                body,
                userName,
                firstName,
                lastName,
                tagline,
                email,
                profession,
                about_me,
                mob_no,
                phone_no,
                location,
                userType);

        editProfileCall.enqueue(editProfileCallback);
    }

    Callback<EditProfileInfoResponse> editProfileCallback = new Callback<EditProfileInfoResponse>() {
        @Override
        public void onResponse(Call<EditProfileInfoResponse> call, Response<EditProfileInfoResponse> response) {
            if (!response.isSuccessful()) {
                Toast.makeText(EditProfileActivity.this, "Unable to Update Profile Information", Toast.LENGTH_SHORT).show();
            }

            EditProfileInfoResponse editProfileInfoResponse = response.body();
            if (editProfileInfoResponse != null) {
                UserInformation userInformation = editProfileInfoResponse.getUserInformation();
                updateProfileInformation(userInformation);
            }

        }

        @Override
        public void onFailure(Call<EditProfileInfoResponse> call, Throwable t) {

        }
    };

    private void updateProfileInformation(UserInformation userInformation) {
        String profilePhotoUrl = getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(this) + "/thumb/" + userInformation.getProfilePicture();
        UserPreferenceManager.setUserImage(this, profilePhotoUrl);

        GlideApp.with(EditProfileActivity.this)
                .load(profilePhotoUrl)
                .error(R.drawable.ic_avtar_male)
                .into(profilePicture);

        // Setup User's First Name
        usersFirstName.setText(userInformation.getFirstName());

        // Setup User's Last Name
        usersLastName.setText(userInformation.getLastName());

        // Setup User's Tagline
        userProfileTagline.setText(userInformation.getTagline());

        // Setup User's Location Details
        userLocation.setText(userInformation.getLocation());

        // Setup User's Profile Summary Details
        userBio.setText(userInformation.getAboutMe());

        // Setup User's Profession Details
        userProfession.setText(userInformation.getProfession());
    }
}
