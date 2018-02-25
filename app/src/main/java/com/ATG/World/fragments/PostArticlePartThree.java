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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.PostArticleDetails;
import com.ATG.World.models.PostArticleResponse;
import com.ATG.World.models.PostArticleResponse2;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkUtility;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostArticlePartThree extends Fragment {
    private AtgService atgService;
    EditText tags;
    Button finishButton,skipButton,backButton;
    ImageButton selectButton;
    String articleID,transfereedTitle;
    private Uri uri;
    private RxPermissions permissions;
    private byte[] byteArray;
    private Uri realUri;
    ArrayList<String> articleIds;
    public PostArticlePartThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_article_part_three, container, false);
        atgService= AtgClient.getClient().create(AtgService.class);
        selectButton=view.findViewById(R.id.selectarticleImage);
        finishButton=view.findViewById(R.id.finalPostButton);
        skipButton=view.findViewById(R.id.skipAndFinish);
        backButton=view.findViewById(R.id.change_back_button_article3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPreviousFragment();
            }
        });
        tags=view.findViewById(R.id.tagEdittext);
        permissions=new RxPermissions(getActivity());
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkPermissions();
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalSubmit();
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHomeFragment();
            }
        });
        atgService=AtgClient.getClient().create(AtgService.class);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).showNButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((MainActivity)getActivity()).showBack();
        ((MainActivity)getActivity()).hideFAB();
    }
    private String getTags(){
        String requiredTags,returnTag = "",finalTags;
        if(!TextUtils.isEmpty(tags.getText().toString())){
            finalTags=tags.getText().toString();
            requiredTags=finalTags.trim();
            returnTag=requiredTags.replace(" ",",");

        }
        return returnTag;
    }
    public void receiveArticleData(String aId,String title){
        //articleID=aId;
        articleIds=new ArrayList<>();
        transfereedTitle=title;
        articleIds.add(aId);
        Log.d("article data received", "receiveArticleData: "+articleIds.get(articleIds.size()-1)+title);

    }
    private void checkPermissions(){
        permissions.request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted->{
                    if (granted){
                        selectImage();

                    }else {
                        Toast.makeText(getActivity(), "Please give the permission(s)", Toast.LENGTH_SHORT).show();
                        checkPermissions();
                    }
                });

    }
    private void selectImage(){
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
        //super.onActivityResult(requestCode, resultCode, data);
        ((MainActivity)getActivity()).hideFAB();
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String capturedImageFilePath = cursor.getString(column_index_data);
                Uri imageuri = Uri.parse("file:///" + capturedImageFilePath);
                selectButton.setImageBitmap(compressImage(imageuri));
              //  realUri=Uri.parse(getRealPathFromURI(data.getData()));
                ((MainActivity)getActivity()).hideFAB();
            }

        } else if (requestCode == 200) {
            uri=data.getData();
            selectButton.setImageBitmap(compressImage(uri));
            ((MainActivity)getActivity()).hideFAB();
        } else {
            Toast.makeText(getActivity(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            ((MainActivity)getActivity()).hideFAB();
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor != null;
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    private Bitmap compressImage(Uri imageU){
        InputStream inputStream=null;
        try{
            inputStream=getActivity().getContentResolver().openInputStream(imageU);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
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
    private void finalSubmit(){
        if(validation()){
            if(NetworkUtility.isNetworkAvailable(getActivity())){
                try {
                    for(int i=0;i<articleIds.size();i++) {
                        // File imageFIle = new File(String.valueOf(realUri));
                        byteArray = getImageByteArray(((BitmapDrawable) selectButton.getDrawable()).getBitmap());
                        //  RequestBody rFile = RequestBody.create(MediaType.parse("image/jpeg"),file);
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("article_img", "image.png", requestFile);
                        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), UserPreferenceManager.getUserId(getActivity()));
                        RequestBody articleId = RequestBody.create(MediaType.parse("multipart/form-data"), articleIds.get(i));
                        RequestBody tags = RequestBody.create(MediaType.parse("multipart/form-data"), getTags());
                        RequestBody title = RequestBody.create(MediaType.parse("multipart/form-data"), transfereedTitle);
                        Call<PostArticleResponse2> postArticleResponseCall = atgService.postArticleStepTwo(userId, articleId, tags, body, title);
                        postArticleResponseCall.enqueue(new Callback<PostArticleResponse2>() {
                            @Override
                            public void onResponse(Call<PostArticleResponse2> call, Response<PostArticleResponse2> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(getActivity(), "Article updated successfully ", Toast.LENGTH_SHORT).show();
                                    loadHomeFragment();
                                    Log.d("Tags", "Tags: "+getTags());
                                }
                            }

                            @Override
                            public void onFailure(Call<PostArticleResponse2> call, Throwable t) {
                                Toast.makeText(getActivity(), "Failed to update article", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }catch (Exception e){
                }
            }

        }
    }
        private byte[] getImageByteArray(Bitmap bmp) {
            byte[] byteArray = null;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (bmp != null)
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            return byteArray;
        }
    private boolean validation(){
        if(TextUtils.isEmpty(tags.getText().toString())){
           tags.requestFocus();
           tags.setError("Tags cannot be empty");
           return false;
        }else if(byteArray==null){
            selectButton.requestFocus();
            Toast.makeText(getActivity(), "Select an image", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    private void loadHomeFragment(){
        HomeFragment homeFragment=new HomeFragment();
        Fragment fragment = homeFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        ((MainActivity)getActivity()).setUpNavigationView();
        ((MainActivity)getActivity()).loadHomeFragment();
    }
    private void loadPreviousFragment(){
       // PostArticlePartTwo postArticlePartTwo=new PostArticlePartTwo();
      //  Fragment fragment = postArticlePartTwo;
       // FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
       // fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
       //         android.R.anim.fade_out);
      //  fragmentTransaction.replace(R.id.main_content, fragment);
        getActivity().getSupportFragmentManager().popBackStack();

    }
}
