package com.ankit.empowerher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class onboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Initialize views
        TextView title = findViewById(R.id.title);
        TextView welcomeMessage = findViewById(R.id.welcome_message);
        TextView sectionHeader1 = findViewById(R.id.section_header_1);
        TextView skillDevelopmentHub = findViewById(R.id.skill_development_hub);
        TextView digitalMarketplace = findViewById(R.id.digital_marketplace);
        TextView communityNetworking = findViewById(R.id.community_networking);
        TextView sectionHeader2 = findViewById(R.id.section_header_2);
        TextView zeroBrokerageSales = findViewById(R.id.zero_brokerage_sales);
        TextView maximizedReach = findViewById(R.id.maximized_reach);
        TextView trainingToEarnMore = findViewById(R.id.training_to_earn_more);
        TextView sectionHeader3 = findViewById(R.id.section_header_3);
        TextView womenEntrepreneurs = findViewById(R.id.women_entrepreneurs);
        TextView trainersExperts = findViewById(R.id.trainers_experts);
        TextView communitySupporters = findViewById(R.id.community_supporters);
        TextView sectionHeader4 = findViewById(R.id.section_header_4);
        TextView whyEmpowerHer = findViewById(R.id.why_empower_her);
        Button btnGetStarted = findViewById(R.id.btn_get_started);

        // Set text content
        title.setText("Discover Empower Her");
        welcomeMessage.setText("Welcome to Empower Her—a vibrant community where women can learn, create, connect, and grow their businesses. Our app is designed to provide you with everything you need to turn your talents into profit and build a sustainable future.");
        sectionHeader1.setText("What You’ll Find in Empower Her:");
        skillDevelopmentHub.setText("Skill Development Hub: Explore our training programs tailored to enhance your skills in areas like crafting, cooking, and design. Learn at your own pace with expert-led tutorials and practical guidance.");
        digitalMarketplace.setText("Digital Marketplace: Showcase your handmade products, from beautiful crafts to delicious homemade treats, on our user-friendly marketplace. Reach a wider audience and manage your store with ease.");
        communityNetworking.setText("Community & Networking: Connect with like-minded women, share your experiences, collaborate on projects, and support each other's growth. Our platform fosters a strong network of empowered women.");
        sectionHeader2.setText("How You Generate Profit:");
        zeroBrokerageSales.setText("Zero Brokerage Sales: List your products on our marketplace with no brokerage fees. This means all the profits from your sales go directly into your pocket.");
        maximizedReach.setText("Maximized Reach: With our platform, your products are visible to a broad audience, increasing your chances of making sales and growing your business.");
        trainingToEarnMore.setText("Training to Earn More: By continuously improving your skills through our training programs, you can offer higher-quality products, which can command better prices in the marketplace.");
        sectionHeader3.setText("Our Stakeholders:");
        womenEntrepreneurs.setText("Women Entrepreneurs: At the heart of Empower Her, our primary stakeholders are the women who use our platform to build their businesses and improve their livelihoods.");
        trainersExperts.setText("Trainers & Experts: Experienced professionals who provide the knowledge and skills needed to succeed. They play a crucial role in empowering our users with the right tools.");
        communitySupporters.setText("Community Supporters: Individuals and organizations who believe in our mission and contribute by supporting our community through various means, including buying products, offering mentorship, or investing in the platform's growth.");
        sectionHeader4.setText("Why Empower Her?");
        whyEmpowerHer.setText("We believe in the power of women supporting women. Empower Her is more than just an app; it’s a movement to uplift, connect, and empower women from all walks of life. Join us today and start your journey toward financial independence and personal growth!");

        // Set up the Get Started button
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next activity (e.g., MainActivity)
                Intent intent = new Intent(onboardingActivity.this, loginActivity.class);
                startActivity(intent);
                finish(); // Close the onboarding activity
            }
        });
    }
}
