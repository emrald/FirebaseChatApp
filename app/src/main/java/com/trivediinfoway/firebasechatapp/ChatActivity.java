package com.trivediinfoway.firebasechatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ListView listchat;
 //   ArrayAdapter<String> adapter;
    ArrayList<String> arraylist = new ArrayList<String>();
    String room_name = "", user_name = "";
    Bundle bn;
    EditText edtmessage;
    Button btnenter;
    String message = "";
    private DatabaseReference chat_data_ref;
    private FirebaseListAdapter<ChatMessage> adapter;
    // private DatabaseReference user_name_ref;
    private FirebaseAuth mAuth;
    String name;
    //   private ArrayList<Message> messageList;
    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mAuth = FirebaseAuth.getInstance();
        //   messageList = new ArrayList<Message>();
        listchat = (ListView) findViewById(R.id.listchat);
        edtmessage = (EditText) findViewById(R.id.edtmessage);
        btnenter = (Button) findViewById(R.id.btnenter);

        bn = getIntent().getExtras();
        if (bn != null) {
            room_name = bn.getString("room_name");
            user_name = bn.getString("user_name");
        }

        chat_data_ref = FirebaseDatabase.getInstance().getReference().child("chat_data");
        //   user_name_ref=FirebaseDatabase.getInstance().getReference().child("user_name").child(mAuth.getCurrentUser().getUid()).child(user_name);

        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMessage msg = new ChatMessage();
                message = edtmessage.getText().toString();
                Map<String,Object> map = new HashMap<String,Object>();
            //    map.put(message,"");
                String key = dr.child("messages").push().getKey();
                map.put("/messages/" + key, message);
                msg.setMessageText(message+"");
                msg.setMessageUser("messages");
                dr.updateChildren(map);
                //   chat_data_ref.push().setValue(edtmessage.getText().toString());//storing actual msg with name of the user
               // dr.push().setValue(new ChatMessage("messages", message));
                edtmessage.setText("");
            }
        });
/* adapter = new FirebaseListAdapter<ChatMessage>(dr, ChatMessage.class,
                R.layout.message, this) {
            @Override
            protected void populateView(View v, ChatMessage model) {
                ((TextView)v.findViewById(R.id.username_text_view)).setText(model.getMessageUser());
                ((TextView)v.findViewById(R.id.message_text_view)).setText(model.getMessageText());
            }
        };
        listchat.setAdapter(adapter);*/


       /* listchat.setAdapter(adapter);*/

        /*dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap mapMessage = (HashMap) dataSnapshot.getValue();
                String msg = (String)mapMessage.get("messages");
                arraylist.add(msg);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
             *//*   ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1, chat_data_ref);
                name = dataSnapshot.getValue().toString();*//*

                //   listchat.setAdapter(adapter);
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
        listchat.setAdapter(adapter);*/
       /* dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext())
                {
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                arraylist.clear();
                arraylist.addAll(set);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
       /* ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               // Set<String> set = new HashSet<String>();
                //Iterator i = dataSnapshot.getChildren().iterator();
                Message message = dataSnapshot.getValue(Message.class);
                messageList.add(message);
               *//* while(i.hasNext()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                }*//*
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               *//* Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext())
                {
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                arrayList.clear();
                arrayList.addAll(set);

                adapter.notifyDataSetChanged();*//*
                Message message = dataSnapshot.getValue(Message.class);
                Log.e("Message",message+" : message");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Comment movedComment = dataSnapshot.getValue(Comment.class);
                Message message = dataSnapshot.getValue(Message.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "onCancelled", databaseError.toException());
                Toast.makeText(ChatActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        };
        dr.addChildEventListener(childEventListener);*/
        /*chat_data_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                name=dataSnapshot.getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });*/
       /* FirebaseListAdapter<Message> adapter=new FirebaseListAdapter<Message>(
                this,Message.class,R.layout.individual_row,chat_data_ref
        ) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView msg=(TextView)v.findViewById(R.id.textView1);
                msg.setText(model.getUser_name()+" : "+model.getMessage());
            }
        };
        listView.setAdapter(adapter);*/
    }
      /*  adapter = new ArrayAdapter<String>(ChatActivity.this,android.R.layout.simple_list_item_1,arraylist);
        listchat.setAdapter(adapter);*/

}
