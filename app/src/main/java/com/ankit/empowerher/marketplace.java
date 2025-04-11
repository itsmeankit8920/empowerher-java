package com.ankit.empowerher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class marketplace extends AppCompatActivity implements PaymentResultListener {

    private EditText inputName, inputEmail, inputReason, inputAmount, inputContact;
    private Spinner spinnerDomain;
    private Button buttonPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        // Initialize views
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputReason = findViewById(R.id.input_reason);
        inputAmount = findViewById(R.id.input_amount);
        inputContact = findViewById(R.id.input_contact);
        spinnerDomain = findViewById(R.id.spinner_domain);
        buttonPay = findViewById(R.id.button_pay);

        // Set up Razorpay Checkout
        Checkout.preload(getApplicationContext());

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    private void startPayment() {
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String reason = inputReason.getText().toString().trim();
        String amount = inputAmount.getText().toString().trim();
        String contact = inputContact.getText().toString().trim();
        String domain = spinnerDomain.getSelectedItem().toString();

        if (name.isEmpty() || email.isEmpty() || amount.isEmpty() || contact.isEmpty()) {
            Toast.makeText(marketplace.this, "Please fill all the mandatory fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int amountInPaise = Integer.parseInt(amount) * 100; // Converting rupees to paise

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_CuPgKU0gzyCGP5");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "EmpowerHer");
            options.put("description", "Donation for " + domain);
            options.put("image", "https://your-logo-url.com"); // Add your logo URL here
            options.put("currency", "INR");
            options.put("amount", amountInPaise);

            JSONObject prefill = new JSONObject();
            prefill.put("email", email);
            prefill.put("contact", contact);

            options.put("prefill", prefill);
            options.put("theme.color", "#FF6F00");

            checkout.open(marketplace.this, options);

        } catch (Exception e) {
            Toast.makeText(marketplace.this, "Error in starting payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        // You can also handle post-payment actions here, such as updating the server or UI
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
        // Handle payment failure here
    }
}
