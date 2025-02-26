# 🍽️ MealBuddy App

## 📌 Overview
MealBuddy is an Android app that helps users plan their meals, discover new recipes, and manage their favorites. It integrates **TheMealDB API** for recipe inspiration, supports **meal planning**, and syncs data across devices using **Firebase**.

## ✨ Features
- 🌟 **Meal of the Day** for daily inspiration.
- 🔍 **Search meals** by category, country, or ingredient.
- 📌 **Favorite meals** and store them locally using **Room**.
- 📅 **Meal Planning** to organize meals for the future.
- 🔄 **Sync & Backup** favorites and meal plans via **Cloud Firestore**.
- 👤 **Authentication** with Google **Firebase**.
- 🎥 **Meal Details**: Name, image, country, ingredients (with images), steps, and a video.
- ⚡ **Offline Support**: Access saved meals and plans without an internet connection.
- 🚀 **Optimized Performance** with **RxJava & MVP architecture**.

## 📱 Screenshots
<div align="center">
  <img src="screenshots/splash.jpg" width="250">
  <img src="screenshots/welcome.jpg" width="250">
  <img src="screenshots/google.jpg" width="250">
  <img src="screenshots/login.jpg" width="250">
  <img src="screenshots/signup.jpg" width="250">
  <img src="screenshots/home.jpg" width="250">
  <img src="screenshots/meal_details.jpg" width="250">
  <img src="screenshots/search_options.jpg" width="250">
  <img src="screenshots/ingredients.jpg" width="250">
  <img src="screenshots/meals.jpg" width="250">
  <img src="screenshots/favs.jpg" width="250">
  <img src="screenshots/no_favs.jpg" width="250">
  <img src="screenshots/plans.jpg" width="250">
  <img src="screenshots/profile.jpg" width="250">
  <img src="screenshots/guest.jpeg" width="250">
  <img src="screenshots/offline.jpeg" width="250">
  
</div>

## 🏗️ Architecture
- **MVP (Model-View-Presenter) Architecture** for better maintainability.
- **Repositories** to manage local (Room) and remote data.
- **RxJava** for reactive programming and async operations.

## 🔧 Tech Stack
- **Language:** Java ☕
- **Framework:** Android SDK 📱
- **Database:** Room (local storage) 🏛️
- **Backend:** Firebase (Auth + Firestore) 🔥
- **API:** TheMealDB 🍲
- **Networking:** Retrofit 🌐
- **Asynchronous Handling:** RxJava ⚡
- **UI:** XML + Jetpack Components 🎨

## 🛠️ Setup & Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/MasameEh/FoodPlanner.git
   cd FoodPlanner
   ```
2. **Open in Android Studio** and sync Gradle.
3. **Add API Keys**:
   - Set up Firebase and add `google-services.json` to the `app/` folder.
4. **Run the app** on an emulator or device.

## 💡 Contributing
Pull requests are welcome! Feel free to fork and contribute to the project.


