package com.trivediinfoway.firebasechatapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by TI A1 on 22-12-2017.
 */

public class ChatActivity1 extends AppCompatActivity {

    ListView listchat;
    EditText edtmessage;
    Button btnenter;
    TextView tvuser;
    ImageView imgselectimage;
    String message = "";
    Bundle bn;
    String room_name = "", user_name = "";
    DatabaseReference dr;//= FirebaseDatabase.getInstance().getReference();
    ArrayList<chat_class> arraylist = new ArrayList<chat_class>();
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    String b64Image;
    public DataSnapshot dataSnapshot_glob;
    StorageReference storageReference;
    int Image_Request_Code = 7;
    Uri FilePathUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listchat = (ListView) findViewById(R.id.listchat);
        edtmessage = (EditText) findViewById(R.id.edtmessage);
        btnenter = (Button) findViewById(R.id.btnenter);
        tvuser = (TextView) findViewById(R.id.tvuser);
        imgselectimage = (ImageView) findViewById(R.id.imgselectimage);

        bn = getIntent().getExtras();

        if (bn != null) {
            room_name = bn.getString("room_name");
            user_name = bn.getString("user_name");

            Log.e("room_name", room_name + "\n" + user_name);
        }

        tvuser.setText(room_name + "");
        storageReference = FirebaseStorage.getInstance().getReference();
        dr = FirebaseDatabase.getInstance().getReference().child(room_name);

        final ArrayAdapter adapter = new ArrayAdapter(ChatActivity1.this, android.R.layout.simple_list_item_1, android.R.id.text1, arraylist);
        listchat.setAdapter(adapter);

        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMessage msg = new ChatMessage();
                message = edtmessage.getText().toString();
                Map<String, Object> map = new HashMap<String, Object>();
                dr.child("messages").push().setValue(message + "\n" + System.currentTimeMillis());
                edtmessage.setText("");
            }
        });
        imgselectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        final CustomeAdapterChat arrayAdapter = new CustomeAdapterChat(ChatActivity1.this, arraylist);
        listchat.setAdapter(arrayAdapter);

        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot_glob = dataSnapshot;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child(room_name).getValue(String.class);
                    Set<chat_class> set = new HashSet<chat_class>();

                    Iterator i = dataSnapshot.getChildren().iterator();
                    while(i.hasNext()){
                        String inner_msg = ((DataSnapshot) i.next()).getValue().toString();
                        chat_class data = new chat_class();
                        data.setTxt_message(inner_msg);
                        set.add(data);
                    }
                    arraylist.clear();
                    arraylist.addAll(set);
                }
             //   adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                dataSnapshot_glob = dataSnapshot;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child(room_name).getValue(String.class);
                    // String value = dataSnapshot.getValue(String.class);
                    Set<chat_class> set = new HashSet<chat_class>();

                    Iterator i = dataSnapshot.getChildren().iterator();
                    while (i.hasNext()) {
                        String inner_msg = ((DataSnapshot) i.next()).getValue().toString();
                        chat_class data = new chat_class();
                        data.setTxt_message(inner_msg);
                        set.add(data);
                    }
                    arraylist.clear();
                    arraylist.addAll(set);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); // 'bitmap' is the image returned
                byte[] b = stream.toByteArray();*/
               /* FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference();
                StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ChatActivity1.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChatActivity1.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                            }
                        });*/
               /* b64Image = Base64.encodeToString(b, Base64.DEFAULT);
                dr.child("messages").child("image").setValue(b64Image);
                chat_class data_new = new chat_class();*/
        // StorageReference storageRef = dr.getReference();
              /*  Iterator i = dataSnapshot_glob.getChildren().iterator();
                while(i.hasNext()) {
                    data_new.setTxt_message("");
                    data_new.setIamge(b64Image);
                }*/
              /*  data_new.setTxt_message("");
                data_new.setIamge(b64Image);
                arraylist.clear();
                arraylist.add(data_new);*/
        // mDatabase.child("users").child("image").setValue(b64Image);
        //    imgselectimage.setImageBitmap(bitmap);
          /*  }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                imgselectimage.setImageBitmap(bitmap);
                UploadImageFileToFirebaseStorage();

                // After selecting image change choose button above text.
                //    ChooseButton.setText("Image Selected");

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child("images" + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            //String TempImageName = ImageName.getText().toString().trim();

                            // Hiding the progressDialog after done uploading.

                            // Showing toast message after done uploading.
                            chat_class data = new chat_class();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo("new1", taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = dr.push().getKey();
                            //      data.setIamge(taskSnapshot.getDownloadUrl().toString());
                            data.setTxt_message(taskSnapshot.getDownloadUrl().toString());

                            // Adding image upload id s child element into databaseReference.
                            dr.child("messages").push().setValue(taskSnapshot.getDownloadUrl().toString());
                            Log.e("URL...", taskSnapshot.getDownloadUrl().toString() + " : url.....");
                          /*  Set<String> set = new HashSet<String>();
                            Iterator i = dataSnapshot_glob.getChildren().iterator();
                            while(i.hasNext()) {
                                String inner_msg = ((DataSnapshot) i.next()).getValue().toString();
                                set.add(inner_msg);
                            }
                            arraylist.clear();
                            arraylist.addAll(set);*/
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            Toast.makeText(ChatActivity1.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.

                        }
                    });
        } else {
            Toast.makeText(ChatActivity1.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
}
