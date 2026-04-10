// EventListActivity.java
package com.example.servetoday;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private LinearLayout filterPanel;
    private LinearLayout eventsContainer;
    private LinearLayout cardSoupKitchen;
    private LinearLayout cardClothingDrive;
    private LinearLayout cardToyDrive;
    private LinearLayout cardVolunteerChaperone;
    private TextView textResultsSubtitle;
    private TextView textActiveFilters;
    private TextView textNoResults;
    private Button buttonFilterToggle;
    private Button buttonClearFilters;
    private Button buttonSortSoonest;
    private Button buttonSortAlphabetical;
    private CheckBox checkboxFood;
    private CheckBox checkboxClothing;
    private CheckBox checkboxKids;
    private CheckBox checkboxEducation;
    private String currentSort = "SOONEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.eventListRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindViews();
        setupHeader();
        setupButtons();
        applyFilters();
    }

    private void bindViews() {
        filterPanel = findViewById(R.id.filterPanel);
        eventsContainer = findViewById(R.id.eventsContainer);
        cardSoupKitchen = findViewById(R.id.cardSoupKitchen);
        cardClothingDrive = findViewById(R.id.cardClothingDrive);
        cardToyDrive = findViewById(R.id.cardToyDrive);
        cardVolunteerChaperone = findViewById(R.id.cardVolunteerChaperone);
        textResultsSubtitle = findViewById(R.id.textResultsSubtitle);
        textActiveFilters = findViewById(R.id.textActiveFilters);
        textNoResults = findViewById(R.id.textNoResults);
        buttonFilterToggle = findViewById(R.id.buttonFilterToggle);
        buttonClearFilters = findViewById(R.id.buttonClearFilters);
        buttonSortSoonest = findViewById(R.id.buttonSortSoonest);
        buttonSortAlphabetical = findViewById(R.id.buttonSortAlphabetical);
        checkboxFood = findViewById(R.id.checkboxFood);
        checkboxClothing = findViewById(R.id.checkboxClothing);
        checkboxKids = findViewById(R.id.checkboxKids);
        checkboxEducation = findViewById(R.id.checkboxEducation);
    }

    private void setupHeader() {
        ImageButton buttonBack = findViewById(R.id.buttonBackResults);
        buttonBack.setOnClickListener(v -> finish());

        String zipCode = getIntent().getStringExtra("zipCode");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        if (zipCode == null || zipCode.trim().isEmpty()) {
            zipCode = "00000";
        }

        if (selectedDate == null || selectedDate.trim().isEmpty()) {
            selectedDate = "Not selected";
        }

        textResultsSubtitle.setText("Showing volunteer events near " + zipCode + " on " + selectedDate);
    }

    private void setupButtons() {
        buttonFilterToggle.setOnClickListener(v -> toggleFilterPanel());

        Button buttonApplyFilters = findViewById(R.id.buttonApplyFilters);
        Button buttonResetFilters = findViewById(R.id.buttonResetFilters);

        buttonApplyFilters.setOnClickListener(v -> {
            applyFilters();
            filterPanel.setVisibility(View.GONE);
            buttonFilterToggle.setText("Filter");
        });

        buttonResetFilters.setOnClickListener(v -> {
            clearCategorySelections();
            currentSort = "SOONEST";
            applyFilters();
            filterPanel.setVisibility(View.GONE);
            buttonFilterToggle.setText("Filter");
        });

        buttonClearFilters.setOnClickListener(v -> {
            clearCategorySelections();
            applyFilters();
        });

        buttonSortSoonest.setOnClickListener(v -> {
            currentSort = "SOONEST";
            applyFilters();
        });

        buttonSortAlphabetical.setOnClickListener(v -> {
            currentSort = "AZ";
            applyFilters();
        });

        Button buttonSoupKitchen = findViewById(R.id.buttonSoupKitchen);
        Button buttonClothingDrive = findViewById(R.id.buttonClothingDrive);
        Button buttonToyDrive = findViewById(R.id.buttonToyDrive);
        Button buttonVolunteerChaperone = findViewById(R.id.buttonVolunteerChaperone);

        buttonSoupKitchen.setOnClickListener(v -> openEventDetails(
                "Soup Kitchen",
                "Feed the homeless at our shelter and help prepare hot meals and a welcoming dining experience.",
                "04/18/2026",
                "Open shift selection",
                "1333 Water Street, San Francisco, CA",
                "Volunteers should be comfortable helping with meal preparation, serving food, and welcoming guests."
        ));

        buttonClothingDrive.setOnClickListener(v -> openEventDetails(
                "Clothing Drive",
                "Bring clothes to donate for families in need. Most items are welcome, including shirts, pants, and jackets.",
                "04/19/2026",
                "Open shift selection",
                "245 Market Street, San Francisco, CA",
                "Please help sort donations, organize sizes, and assist families during pickup."
        ));

        buttonToyDrive.setOnClickListener(v -> openEventDetails(
                "Toy Drive",
                "Help collect and organize toys for children who would otherwise go without gifts and fun experiences.",
                "04/21/2026",
                "Open shift selection",
                "880 Pine Avenue, San Francisco, CA",
                "Volunteers should be comfortable organizing donations and helping families browse available items."
        ));

        buttonVolunteerChaperone.setOnClickListener(v -> openEventDetails(
                "Volunteer Chaperone",
                "Help provide a safe travel experience for a public school field trip. Adults 18+ with youth experience preferred.",
                "04/18/2026",
                "Open shift selection",
                "550 School Lane, San Francisco, CA",
                "Adults 18+ with experience working with youth are preferred for this event."
        ));
    }

    private void clearCategorySelections() {
        checkboxFood.setChecked(false);
        checkboxClothing.setChecked(false);
        checkboxKids.setChecked(false);
        checkboxEducation.setChecked(false);
    }

    private boolean hasActiveCategoryFilters() {
        return checkboxFood.isChecked()
                || checkboxClothing.isChecked()
                || checkboxKids.isChecked()
                || checkboxEducation.isChecked();
    }

    private void openEventDetails(String title, String description, String date, String time, String location, String requirements) {
        Intent intent = new Intent(EventListActivity.this, EventDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("location", location);
        intent.putExtra("requirements", requirements);
        startActivity(intent);
    }

    private void toggleFilterPanel() {
        if (filterPanel.getVisibility() == View.VISIBLE) {
            filterPanel.setVisibility(View.GONE);
            buttonFilterToggle.setText("Filter");
        } else {
            filterPanel.setVisibility(View.VISIBLE);
            buttonFilterToggle.setText("Hide Filters");
        }
    }

    private void applyFilters() {
        boolean hasCategoryFilter = hasActiveCategoryFilters();

        boolean showSoupKitchen = matchesCategory("Food", hasCategoryFilter);
        boolean showClothingDrive = matchesCategory("Clothing", hasCategoryFilter);
        boolean showToyDrive = matchesCategory("Kids", hasCategoryFilter);
        boolean showVolunteerChaperone = matchesCategory("Education", hasCategoryFilter);

        cardSoupKitchen.setVisibility(showSoupKitchen ? View.VISIBLE : View.GONE);
        cardClothingDrive.setVisibility(showClothingDrive ? View.VISIBLE : View.GONE);
        cardToyDrive.setVisibility(showToyDrive ? View.VISIBLE : View.GONE);
        cardVolunteerChaperone.setVisibility(showVolunteerChaperone ? View.VISIBLE : View.GONE);

        reorderCards();
        updateSortButtons();
        updateActiveFiltersText();
        updateClearFiltersButton();

        boolean hasVisibleCards = showSoupKitchen || showClothingDrive || showToyDrive || showVolunteerChaperone;
        textNoResults.setVisibility(hasVisibleCards ? View.GONE : View.VISIBLE);
    }

    private void updateClearFiltersButton() {
        buttonClearFilters.setVisibility(hasActiveCategoryFilters() ? View.VISIBLE : View.GONE);
    }

    private boolean matchesCategory(String category, boolean hasCategoryFilter) {
        if (!hasCategoryFilter) {
            return true;
        }

        if (category.equals("Food")) {
            return checkboxFood.isChecked();
        }

        if (category.equals("Clothing")) {
            return checkboxClothing.isChecked();
        }

        if (category.equals("Kids")) {
            return checkboxKids.isChecked();
        }

        if (category.equals("Education")) {
            return checkboxEducation.isChecked();
        }

        return true;
    }

    private void reorderCards() {
        eventsContainer.removeAllViews();

        if (currentSort.equals("AZ")) {
            addCardIfVisible(cardClothingDrive);
            addCardIfVisible(cardSoupKitchen);
            addCardIfVisible(cardToyDrive);
            addCardIfVisible(cardVolunteerChaperone);
        } else {
            addCardIfVisible(cardSoupKitchen);
            addCardIfVisible(cardVolunteerChaperone);
            addCardIfVisible(cardClothingDrive);
            addCardIfVisible(cardToyDrive);
        }
    }

    private void addCardIfVisible(LinearLayout card) {
        if (card.getVisibility() == View.VISIBLE) {
            eventsContainer.addView(card);
        }
    }

    private void updateSortButtons() {
        if (currentSort.equals("AZ")) {
            setButtonSelected(buttonSortAlphabetical, true);
            setButtonSelected(buttonSortSoonest, false);
        } else {
            setButtonSelected(buttonSortSoonest, true);
            setButtonSelected(buttonSortAlphabetical, false);
        }
    }

    private void setButtonSelected(Button button, boolean selected) {
        if (selected) {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7E57C2")));
            button.setTextColor(Color.WHITE);
        } else {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E8DDFF")));
            button.setTextColor(Color.parseColor("#5A3E8B"));
        }
    }

    private void updateActiveFiltersText() {
        ArrayList<String> selectedCategories = new ArrayList<>();

        if (checkboxFood.isChecked()) {
            selectedCategories.add("Food");
        }

        if (checkboxClothing.isChecked()) {
            selectedCategories.add("Clothing");
        }

        if (checkboxKids.isChecked()) {
            selectedCategories.add("Kids");
        }

        if (checkboxEducation.isChecked()) {
            selectedCategories.add("Education");
        }

        String categoryText = selectedCategories.isEmpty() ? "All categories" : joinList(selectedCategories);
        String sortText = currentSort.equals("AZ") ? "A-Z" : "Soonest";

        textActiveFilters.setText("Filters: " + categoryText + " · Sort: " + sortText);
    }

    private String joinList(ArrayList<String> items) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            builder.append(items.get(i));
            if (i < items.size() - 1) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }
}