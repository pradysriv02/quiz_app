# InterviewIQ

InterviewIQ is a modern Android application built using **Kotlin** and **Jetpack Compose**, designed to provide users with an engaging quiz experience. Users can register and log in via Firebase Authentication (including Google sign-in), access questions from multiple topics, and interact with an AI-powered chatbot using **Gemini AI**.

## Features

- **Firebase Authentication**:
  - Register and Login using Email and Password.
  - Google Sign-In for Registration and Login.
  
- **Firebase Storage**:
  - Secure storage for user profile data and quiz records.
  
- **Quiz Functionality**:
  - Multiple topics available for quiz questions.
  - Dynamic loading of questions from Firebase.
  
- **AI Chatbot**:
  - Integrated AI chatbot powered by **Gemini AI** to assist users with quiz hints, explanations, and general queries.
  
## Screenshots


## Technologies Used

- **Kotlin**: For building the app's core logic and UI.
- **Jetpack Compose**: For creating modern, declarative UI components.
- **Firebase Authentication**: For handling user authentication (Email/Password, Google Sign-In).
- **Firebase Storage**: To store and retrieve user data.
- **Gemini AI**: For the AI chatbot that interacts with users.
- **Android Studio**: The IDE used for development.

## Installation

To run this project locally, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/QuizApp.git
   cd QuizApp
   ```

2. **Open in Android Studio**:
   - Open the project in Android Studio.
   - Make sure you have the latest version of **Android Studio** and **Kotlin** plugin installed.

3. **Firebase Setup**:
   - Create a Firebase project from the [Firebase Console](https://console.firebase.google.com/).
   - Add your Android app to the Firebase project.
   - Download the `google-services.json` file from Firebase and place it in the `app` folder of your Android project.
   - Enable **Email/Password Authentication** and **Google Sign-In** in Firebase Authentication settings.

4. **Gemini AI Integration**:
   - Set up the Gemini AI chatbot by integrating the Gemini SDK into your project.
   - Add your API keys and other necessary configurations to enable AI functionalities.

5. **Build and Run**:
   - Once the setup is done, run the app on an emulator or a physical device.

## How It Works

1. **User Authentication**:
   - New users can register using Email/Password or sign in with Google.
   - Returning users can log in using the same methods.

2. **Quiz Section**:
   - After logging in, users can choose from multiple quiz topics.
   - Questions are dynamically loaded from Firebase.

3. **AI Chatbot**:
   - The Gemini AI chatbot provides hints or explanations during the quiz.
   - Users can interact with the chatbot to ask for help, feedback, or general queries.

## Dependencies

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Firebase Authentication](https://firebase.google.com/docs/auth)
- [Firebase Storage](https://firebase.google.com/docs/storage)
- [Gemini AI SDK](https://gemini.ai/)

## Contributing

Contributions are welcome! If you have any ideas or suggestions to improve the app, feel free to open an issue or submit a pull request.

---
