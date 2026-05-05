# Serve Today - Volunteer Management Platform

Serve Today is an Android application designed to bridge the gap between volunteers in local communities and non-profits. Our project features event discovery, simplified registration, and tools for tracking and exporting volunteer hours.
Created by Yossef Eini and Ramzi Carter

## Prerequisites

Before you begin, ensure you have the following installed:
* **Android Studio** (Ladybug | 2024.2.1 or newer recommended)
* **Android SDK** (API Level 34 or newer)
* **Git** installed on your local machine

## Getting Started:

Follow these instructions to get the application running on your machine.

### 1. Copy the Repository Link 
* Navigate to the **GitHub Repository** for the project.
* Click the green **Code** button.
* Copy the **HTTPS** URL provided (https://github.com/Yossefgit/ServeToday.git)

### 2. Clone the Project in Android Studio
* Launch **Android Studio**.
* On the Welcome screen, click **Get from VCS**.
* Paste the link you copied into the **URL** field.
* Choose a local directory where you want to save the project and click **Clone**.

### 3. Sync and Build the Project
* After cloning is complete, Android Studio will open the project automatically.
* Wait for the **Gradle Sync** to finish. There is a status bar where you can visually check the eta for sync to finish
* If any SDK components or build tools are missing, you will be prompted to install them
* Click the links in the **Build** window to download them.

### 4. Set Up a Device
To run the app, you need an emulator:
* **Emulator:** * Go to **Tools > Device Manager**.
    * Click **Create Device**.
    * Select a phone (e.g., Pixel 8) and a system image
    * Click **Finish** to create the virtual device.

### 5. Run the Application
* In the top toolbar, ensure your connected device or emulator is selected in the device dropdown.
* Make sure the **app** configuration is selected in the run configuration dropdown.
* Click the green **Run** button
* The application will build and install and it should launch on your device immediately

## App Features
* **Event Discovery:** Search by Zip Code and Date
* **Filtering:** Filter opportunities and events by time or name
* **Registration:** One-tap sign up for specific volunteer shifts and instant confirmation
* **Tracking:** Log hours and export them via email

## Tech Stack
* **Language:** Java
* **IDE:** Android Studio
* **UI:** WindowInsets, Design buttons, and dynamic LinearLayout containers.
