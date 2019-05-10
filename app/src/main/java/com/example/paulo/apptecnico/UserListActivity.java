package com.example.paulo.apptecnico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private static final String TAG = "UserListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_user_list);

        //implementa um método da classe RecyclerView  do SISTEMA ****************************
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Cria um objeto da classe adapter
        UserListAdapter adapter = new UserListAdapter();

        //cria um objeto da classe swipeAndDrag
        SwipeAndDragHelper swipeAndDragHelper = new SwipeAndDragHelper(adapter);

        //cria um objeto da classe ItemTouchHelper do SISTEMA  ****************************
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeAndDragHelper);

        /*
        public void setTouchHelper(ItemTouchHelper touchHelper) {
            this.touchHelper = touchHelper;
        }
        *///o metodo acima é chamado, da classe adapter
        adapter.setTouchHelper(touchHelper);

        //o metodo set adapter, velho conhecido
        userRecyclerView.setAdapter(adapter);


        touchHelper.attachToRecyclerView(userRecyclerView);

        UsersData usersData = new UsersData();
        List<User> usersList = usersData.getUsersList();
        adapter.setUserList(usersList);

    }

}
