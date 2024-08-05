package com.doggo.dogadopt.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.doggo.dogadopt.DogListAdapter;
import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class faqActivity extends AppCompatActivity {

    String type = "Name";
    String order = "Ascending";
    ListView lView;
    DogListAdapter lAdapter;
    List<Dog> dogList;
    QueryProcessor processor = new QueryProcessor();
    LoadingDialog progress = new LoadingDialog(faqActivity.this);
    Account account;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout layout;
    private Toolbar toolbar;

    @Override
    protected void onRestart() {
        super.onRestart();
        reloadList("Reloading data...", true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("accountDetails");
        reloadList("Initializing data...", true);

        layout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, layout,R.string.nav_open,R.string.nav_close);

        layout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar = findViewById(R.id.toolbar_mainmenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        LinearLayout sideNavLayoutFAQ = (LinearLayout) header.findViewById(R.id.nav_header);
        TextView fullFAQName = sideNavLayoutFAQ.findViewById(R.id.fullname_menuLabel);
        TextView usertype = sideNavLayoutFAQ.findViewById(R.id.usertype_menuLabel);

        if (account != null) {
            // Ensure account object is not null before accessing its methods
            String fullName = "";
            if (account.getFirstName() != null) {
                fullName += account.getFirstName();
            }
            if (account.getLastName() != null) {
                if (!fullName.isEmpty()) {
                    fullName += " ";
                }
                fullName += account.getLastName();
            }
            fullFAQName.setText(fullName);
        } else {
            // Handle the case where account is null
            fullFAQName.setText("Unknown User");
        }
        if (account != null) {
            // Check if account is not null before accessing its methods
            String role = account.getRole();
            if (role != null) {
                // Check the role and set the appropriate text
                if (role.equals("ADMIN")) {
                    usertype.setText("Administrator");
                } else if (role.equals("USER")) {
                    usertype.setText("Adoptee");
                } else {
                    usertype.setText("UNKNOWN");
                }
            } else {
                // Handle the case where role is null
                // For example, set a default value or display an error message
                usertype.setText("Role not specified");
            }
        } else {
            // Handle the case where account is null
            // For example, you could display a default value or show an error message
            usertype.setText("UNKNOWN");
        }


        Menu menu = navigationView.getMenu();
        SubMenu sb = menu.addSubMenu("Administrative Functions");
        sb.add("Add a Dog");
        SubMenu sb2 = menu.addSubMenu("Testing purposes only");
        sb2.add("Sort List");
        if (account != null) {
            // Check if account is not null before accessing its methods
            String role = account.getRole();
            if (role != null) {
                // Check the role and set the appropriate text
                if (role.equals("ADMIN")) {
                    usertype.setText("Administrator");
                } else if (role.equals("USER")) {
                    usertype.setText("Adoptee");
                } else {
                    usertype.setText("UNKNOWN");
                }
            } else {
                // Handle the case where role is null
                // For example, set a default value or display an error message
                usertype.setText("Role not specified");
            }
        } else {
            // Handle the case where account is null
            // For example, you could display a default value or show an error message
            usertype.setText("UNKNOWN");
        }

        menu.add("Reload List");
        menu.add("View Requests");
        menu.add("FAQ");
        menu.add("Logout");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().equals("View Requests")){
                    layout.closeDrawers();
                    Intent i = new Intent(getApplicationContext(), allrequestActivity.class);
                    startActivity(i);
                }
                else if(item.getTitle().equals("Sort List")){
                    showSortDialog();
                }
                else if(item.getTitle().equals("Add a Dog")){
                    layout.closeDrawers();
                    Intent i = new Intent(getApplicationContext(), addActivity.class);
                    startActivity(i);
                }
                else if (item.getTitle().equals("Reload List")){
                    layout.closeDrawers();
                    reloadList("Reloading data...", true);
                }
                else if (item.getTitle().equals("Logout")){
                    layout.closeDrawers();
                    triggerLogout();
                }
                else if (item.getTitle().equals("FAQ")){
                    layout.closeDrawers();
                    Intent i = new Intent(getApplicationContext(), faqActivity.class);
                    startActivity(i);
                }

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void triggerLogout(){
        AlertDialog.Builder frameBuilder = new AlertDialog.Builder(this);
        frameBuilder.setCancelable(true);
        frameBuilder.setTitle("Logout");
        frameBuilder.setMessage("Do you want to logout?");
        frameBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), startupActivity.class);
                startActivity(i);
                finish();
            }
        });
        frameBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = frameBuilder.create();
        dialog.show();
        layout.closeDrawers();
    }

    private List<Dog> sortDogs(List<Dog> dogList, String type, String order){

        Collections.sort(dogList, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {

                String value1 = getValueForComparison(o1, type);
                String value2 = getValueForComparison(o2, type);

                int result;

                if(order.equals("Ascending")){
                    result = value1.compareToIgnoreCase(value2);
                } else{
                    result = value2.compareToIgnoreCase(value1);
                }
                return result;
            }
        });
        return dogList;

    }

    private String getValueForComparison(Dog dog, String type){
        switch (type){
            case "Name":
                return dog.getName().replace("\"", "");
            case "Breed":
                return dog.getBreed().replace("\"", "");
            case "Status":
                return dog.getStatus().replace("\"", "");
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private void reloadList(String text, boolean fetch){
        progress.startLoadingAnimation(text);
        if(fetch){
            processor.DogReadAll();
            processor.setCbs(new CallBack() {
                @Override
                public void returnResult(Object obj) {
                    dogList = (List<Dog>) obj;
                    dogList = sortDogs(dogList, type, order);
                    lView = (ListView) findViewById(R.id.dogList);
                    if (account != null) {
                        // Ensure account object is not null before accessing its methods
                        String role = account.getRole();
                        if (role != null) {
                            // Check if role is not null before using it
                            role = role.replace("\"", "");

                        }

                        if (lView != null) {
                            lView.setAdapter(lAdapter);
                        } else {
                            // Handle the case where lView is null
                        }
                        progress.dismissAnimation();
                    }
                }

            })  ;
            }
        else{
            dogList = sortDogs(dogList,type,order);
            lView = (ListView) findViewById(R.id.dogList);
            lAdapter = new DogListAdapter(faqActivity.this, dogList.toArray(new Dog[0]),account.getRole().replace("\"", ""),account.getMyId());
            lView.setAdapter(lAdapter);
            progress.dismissAnimation();
        }
    }

    private void showSortDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sortlist_dialog);
        dialog.setCancelable(false);
        //dialog.getWindow().setBackgroundDrawable();

        Spinner orderSpin = dialog.findViewById(R.id.orderSort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.UpDownSortSpin, android.R.layout.simple_spinner_item);
        orderSpin.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(order);
        orderSpin.setSelection(spinnerPosition);
        Spinner typeSpin = dialog.findViewById(R.id.TypeSort);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.TypeSortSpin, android.R.layout.simple_spinner_item);
        typeSpin.setAdapter(adapter2);
        int spinnerPosition2 = adapter2.getPosition(type);
        typeSpin.setSelection(spinnerPosition2);
        Button sortBtn = dialog.findViewById(R.id.OKSort);
        Button cancelBtn = dialog.findViewById(R.id.CancelSort);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                layout.closeDrawers();
                type = typeSpin.getSelectedItem().toString();
                order = orderSpin.getSelectedItem().toString();
                reloadList("Organizing Data...", false);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}


