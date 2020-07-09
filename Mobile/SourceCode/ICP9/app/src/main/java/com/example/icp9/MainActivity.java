package com.example.icp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 5;
    final int CHEESE_PRICE = 2;

    final int PEPPERONI_PRICE = 2;
    final int MUSHROOMS_PRICE = 1;
    final int PEPPERS_PRICE = 1;
    int quantity = 1;
    EditText userInputNameView;
    EditText userEmailView;
    CheckBox cheese;
    CheckBox pepperoni;
    CheckBox mushrooms;
    CheckBox peppers;

    Button OrderBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInputNameView = findViewById(R.id.user_input);
        cheese = findViewById(R.id.cheese);
        pepperoni = findViewById(R.id.pepperoni);
        mushrooms = findViewById(R.id.mushrooms);
        peppers =  findViewById(R.id.peppers);
        userEmailView =  findViewById(R.id.email);
        OrderBtn = findViewById(R.id.orderbtn);

        OrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    public void sendEmail() {
        // get user input
        String userInputName = userInputNameView.getText().toString();

        // get user email
        String userEmail = userEmailView.getText().toString();
        String[] userEmails = userEmail.split(",");

        // check if whipped cream is selected
        boolean hasCheese = cheese.isChecked();

        // check if chocolate is selected
        boolean hasPepperoni = pepperoni.isChecked();

        // check if whipped cream is selected
        boolean hasMushrooms = mushrooms.isChecked();

        // check if chocolate is selected
        boolean hasPeppers = peppers.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasCheese, hasPepperoni, hasMushrooms, hasPeppers);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasCheese, hasPepperoni, hasMushrooms, hasPeppers, totalPrice);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        Log.i("Send email", "");
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, userEmails);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pizza Delivery Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose any email client"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

        // Write the relevant code for triggering email

        // Hint to accomplish the task

        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        boolean hasCheese = cheese.isChecked();

        // check if chocolate is selected
        boolean hasPepperoni = pepperoni.isChecked();

        // check if whipped cream is selected
        boolean hasMushrooms = mushrooms.isChecked();

        // check if chocolate is selected
        boolean hasPeppers = peppers.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasCheese, hasPepperoni, hasMushrooms, hasPeppers);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasCheese, hasPepperoni, hasMushrooms, hasPeppers, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent redirect = new Intent(MainActivity.this, Summary.class);
        redirect.putExtra("MESSAGE", orderSummaryMessage);
        MainActivity.this.startActivity(redirect);
    }


    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasCheese, boolean hasPepperoni, boolean hasMushrooms, boolean hasPeppers, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n\n" +
                getString(R.string.order_details) + "\n" +
                getString(R.string.order_summary_chesse, boolToString(hasCheese)) + "\n" +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" +
                getString(R.string.order_summary_mushrooms, boolToString(hasMushrooms)) + "\n" +
                getString(R.string.order_summary_peppers, boolToString(hasPeppers)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasCheese, boolean hasPepperoni, boolean hasMushrooms, boolean hasPeppers) {
        int basePrice = PIZZA_PRICE;
        if (hasCheese) {
            basePrice += CHEESE_PRICE;
        }
        if (hasPepperoni) {
            basePrice += PEPPERONI_PRICE;
        }
        if (hasMushrooms) {
            basePrice += MUSHROOMS_PRICE;
        }
        if (hasPeppers) {
            basePrice += PEPPERS_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}