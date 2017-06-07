package com.aptech.current_demo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {
    Button sub;
    Button can;
    EditText textname,textlname,textemail,textpassword,textphoneno,textprofile;
    String Firstname, Lastname, Emailid, Password, Phoneno, Profile,citystr;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    String Registration_URL = "http://vishalsinghrajput.000webhostapp.com/myapp/register.php";

    int success;
    AutoCompleteTextView city;
    String[] list = new String[]{"Ludhiana", "Amritsar", "Ambala", "Ambaji ",
            "Ayodhya", "Abids",
            "Agra ",
            "Ahmedabad",
            "Ahmednagar",
            "Alappuzha",
            "Allahabad ",
            "Alwar ",
            "Akola",
            "Alibag",
            "Almora",
            "Amlapuram ",
            "Amravati ",
            "Amritsar",
            "Anand ",
            "Anandpur Sahib",
            "Angul",
            "Anna Salai",
            "Arambagh",
            "Asansol",
            "Ajmer",
            "Amreli",
            "Aizawl",
            "Agartala",
            "Aligarh",
            "Auli",
            "Aurangabad ",
            "Azamgarh",
            "Aurangabad",
            "Baran",
            "Bettiah",
            "Badaun ",
            "Badrinath ",
            "Balasore",
            "Banaswara ",
            "Bankura",
            "Ballia",
            "Bardhaman",
            "Baripada",
            "Barrackpore",
            "Barnala ",
            "Barwani",
            "Beed",
            "Beawar ",
            "Bellary",
            "Bhadohi",
            "Bhadrak",
            "Bharuch",
            "Bhilai ",
            "Bhilwara ", "Bhiwani ",
            "Bidar ",
            "Bilaspur ",
            "Bangalore ",
            "Bhind ",
            "Bhagalpu ",
            "Bharatpur ",
            "Bhavnagar ",
            "Bhopal ",
            "Bhubaneshwar ",
            "Bhuj ",
            "Bilimora ",
            "Bijapur ",
            "Bikaner ",
            "Bodh Gaya",
            "Bokaro ",
            "Bundi ",
            "Barasat ",
            "Bareilly ",
            "Basti",
            "Bijnor ",
            "Burhanpur ",
            "Buxur ",
            "Calangute ",
            "Chandigarh ",
            "Chamba ",
            "Chandausi ",
            "Chandauli ",
            "Chandrapur ",
            "Chhapra ",
            "Chidambaram ",
            "Chiplun ",
            "Chhindwara ",
            "Chitradurga",
            "Chittoor ",
            "Cooch Behar ",
            "Chennai",
            "Chittaurgarh ",
            "Churu ",
            "Coimbatore ",
            "Cuddapah ",
            "Cuttack ",
            "Dahod ",
            "Dalhousie ",
            "Davangere ",
            "Dehri ",
            "Dewas ",
            "Dwarka ",
            "Dehradun ",
            "Delhi  ",
            "Deoria  ",
            "Dhanbad ",
            "Dharamshala ",
            "Dispur ",
            "Dholpur ",
            "Diu Island ",
            "Durgapur ",
            "Didwana ",
            "Ernakulam ",
            "Etah ",
            "Etawah ",
            "Erode ",
            "Faridabad ",
            "Ferozpur ",
            "Faizabad ",
            "Gandhinagar ",
            "Gangapur ",
            "Garia",
            "Gaya ",
            "Ghaziabad ",
            "Godhra",
            "Gokul ",
            "Gonda ",
            "Gorakhpur ",
            "Greater Mumbai ",
            "Gulbarga ",
            "Guna ",
            "Guntur ",
            "Gurgaon ",
            "Greater Noida ",
            "Gulmarg ",
            "Hanumangarh ",
            "Haflong ",
            "Haldia ",
            "Haridwar ",
            "Hajipur ",
            "Haldwani ",
            "Hampi ",
            "Hapur ",
            "Hubli ",
            "Hardoi ",
            "Hyderabad ",
            "Guwahati ",
            "Gangtok ",
            "Gwalior ",
            "Imphal ",
            "Indore ",
            "Itanagar ",
            "Itarsi ",
            "Jabalpur ",
            "Jagadhri ",
            "Jalna ",
            "Jamalpur ",
            "Jhajjar ",
            "Jhalawar ",
            "Jaipur ",
            "Jaisalmer ",
            "Jalandhar ",
            "Jammu ",
            "Jamshedpur ",
            "Jhansi ",
            "Jaunpur ",
            "Jodhpur ",
            "Junagadh ",
            "Jalore ",
            "Kishanganj",
            "Katihar ",
            "Kanpur ",
            "Kangra ",
            "Kasauli ",
            "Kapurthala ",
            "Kanchipuram ",
            "Karnal ",
            "Karaikudi ",
            "Kanyakumari ",
            "Katni ",
            "Khajuraho ",
            "Khandala ",
            "Khandwa ",
            "khargone ",
            "Kishangarh",
            "Kochi ",
            "Kodaikanal ",
            "Kohima ",
            "Kolhapur ",
            "Kolkata ",
            "Kollam ",
            "Kota ",
            "Kottayam ",
            "Kovalam ",
            "Kozhikode ",
            "Kumbakonam ",
            "Kumarakom ",
            "Kurukshetra",
            "Lalitpur",
            "Latur ",
            "Lucknow ",
            "Ludhiana ",
            "Lavasa ",
            "Leh",
            "Laxmangarh",
            "Madikeri",
            "Madurai",
            "Mahabaleshwar ",
            "Mahabalipuram",
            "Mahbubnagar",
            "Manali",
            "Mandu Fort",
            "Mangalore",
            "Malegaon",
            "Manipal",
            "Margoa",
            "Mathura",
            "Meerut",
            "Mirzapur",
            "Mohali",
            "Morena",
            "Motihari",
            "Moradabad",
            "Mount Abu",
            "Mumbai",
            "Munger",
            "Munnar",
            "Mussoorie",
            "Mysore",
            "Muzaffarnagar", "Mokokchung", "Muktsar", "Nagpur", "Nagaon", "Nagercoil", "Naharlagun", "Naihati", "Nainital", "Nalgonda ", "Namakkal", "Nanded", "Narnaul", "Nasik", "Nathdwara", "Navsari", "Neemuch", "Noida", "Ooty", "Orchha ", "Palakkad", "Palanpur", "Pali", "Palwal", "Panaji ", "Panipat", "Panvel", "Pathanamthitta", "Pandharpur ", "Patna Sahib", "Panchkula", "Patna", "Periyar", "Phagwara", "Pilibhit", "Pinjaur", "Pollachi", "Pondicherry", "Ponnani", "Porbandar", "Port Blair", "Porur ", "Pudukkottai", "Punalur ", "Pune", "Puri", "Purnia", "Pushkar", "Patiala", "Raxual", "Rajkot", "Rameswaram", "Rajahmundry", "Ranchi", "Ratlam", "Raipur", "Rewa", "Rewari", "Rishikesh", "Rourkela", "Sitamrahi ", "Sagar", "Sangareddy", "Saharanpur", "Salem", "Salt Lake", "Samastipur", "Sambalpur", "Sambhal ", "Sanchi", "Sangli", "Sarnath", "Sasaram", "Satara", "Satna", "Secunderabad", "Sehore ", "Serampore", "Sangrur", "Sirhind", "Shillong", "Shimla", "Shirdi", "ShivaGanga", "Shivpuri", "Silvassa", "Singrauli", "Sirsa", "Sikar", "Siwan", "Somnath", "Sonipat", "Sopore ", "Srikakulam", "Srirangapattna", "Srinagar", "Sultanpur", "Surat", "Surendranagar", "Suri", "Tawang", "Tezpur", "Thrippunithura", "Thrissur ", "Tiruchchirappalli", "Tirumala", "Tirunelveli", "Thiruvannamalai", "Tirur", "Thalassery", "Thanjavur", "Thekkady", "Theni", "Thiruvananthpuram", "Thiruvannamalai", "Tirupati", "Trichy", "Trippur", "Tumkur", "Tuni ", "Udaipur", "Udhampur", "Udupi", "Ujjain", "Unnao ", "Ujjain", "Vidisha", "Vadodra", "Valsad ", "Vapi", "Varanasi", "Varkala", "Vasco da Gama", "Vellore", "Vishakhapatnam ", "Vijayawada ", "Vizianagaram", "Vrindavan", "Warangal", "Washim", "Yamunanagar", "Yelahanka"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textname = (EditText) findViewById(R.id.Firstname);
        textlname=(EditText) findViewById(R.id.Lastname);
        textemail=(EditText)findViewById(R.id.Emailid);
        textpassword=(EditText)findViewById(R.id.Password);
        textphoneno=(EditText)findViewById(R.id.Phoneno);
        textprofile=(EditText)findViewById(R.id.Profile);
        city = (AutoCompleteTextView) findViewById(R.id.citylist);
        ArrayAdapter adapter = new ArrayAdapter(Registration.this, android.R.layout.simple_list_item_1, list);
        city.setAdapter(adapter);

        sub = (Button) findViewById(R.id.sub);
        can = (Button) findViewById(R.id.can);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firstname = textname.getText().toString();
                Lastname = textlname.getText().toString();
                Emailid = textemail.getText().toString();
                Password=textpassword.getText().toString();
                Phoneno = textphoneno.getText().toString();
                Profile = textprofile.getText().toString();
                citystr= city.getText().toString();


                if(Firstname.isEmpty())
                {
                    textname.setError("Enter Your Name");
                }
                else if(Emailid.isEmpty())
                {
                    textemail.setError("Enter Your Email Address");
                }
                else if(Password.isEmpty())
                {
                    textpassword.setError("Enter Your Password");
                }
                else if(Phoneno.isEmpty())
                {
                    textphoneno.setError("Enter Your Mobile no");

                }
                else if (Profile.isEmpty())
                {
                    textprofile.setError("Enter Your Profile");
                }
                else if(citystr.isEmpty())
                {
                   city .setError("Select Your city");
                }


                else
                {
                    new Registrationall().execute();
                }


            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(Registration.this);
                d.setTitle("Do You want to exit?");
                d.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                d.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                d.show();
            }
        });


    }


    class Registrationall extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration.this);
            pDialog.setMessage("Processing...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            if (!pDialog.isShowing()) {
                pDialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Building Parameters
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("fname", Firstname));
                param.add(new BasicNameValuePair("Lname", Lastname));
                param.add(new BasicNameValuePair("email", Emailid));
                param.add(new BasicNameValuePair("Password", Password));
                param.add(new BasicNameValuePair("mob", Phoneno));
                param.add(new BasicNameValuePair("Profile", Profile));
                param.add(new BasicNameValuePair("City", citystr));

                // getting product details by making HTTP request
                String jsonstr = jsonParser.makeHttpRequest(Registration_URL, "POST", param);
                // check your log for json response
                Log.d("Processing", jsonstr);
                JSONObject json = new JSONObject(jsonstr);
                Log.d("RESPONSE::::", json.toString());
                // json success tag
                success = json.getInt("success");

                if (success == 1) {
                    Log.d("Register Successful!", json.toString());
                    String name = json.getString("message");
                    return name;
                } else {
                    Log.d("Error!", json.getString("message"));
                    return json.getString("message");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();

            if(success==1) {
                Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration.this, LoginActivity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
