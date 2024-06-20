# Mobile Development
The MD part is responsible to make the functional android app. ArtScape app has 3 menus on navigation bar and will have 3 menus on tool bar, but only 1 menu can be selected. On the homepage, there will be 2 main features, namely displaying artworks from artists and viewing painting details.

<img src="https://github.com/Joevandyta/ArtScape/assets/148755512/6001e147-938f-4a3f-abfc-ccf603332f49" alt="Logo" style="width:900px;"/>

## ArtScape 

Welcome to ArtScape, an Android application designed to connect artists and art enthusiasts. ArtScape provides a platform for artists to showcase and sell their paintings, and for users to discover, search, and purchase artworks.

ArtScape is designed to describe the purpose of your app. It provides users with describe the main functionalities or benefits to enhance their experience.


## Logo 
<img src="https://github.com/Joevandyta/ArtScape/assets/108873685/2e8937fd-520b-4e7c-ab26-cf61df89b687" alt="Logo" style="width:200px;"/>

### Filosofi

    "ArtScape" is present as a solution for the art landscape in Indonesia.
    Combining the arts industry with technology.
    Addressing artists' challenges in terms of exposure and accessibility.
    Providing a digital platform to exhibit and sell works of art.
    AI features include genre recognition, personal recommendations, and sentiment analysis.

   
## Getting Started

### Prerequisites
- [Android Studio](https://developer.android.com/studio) 4.1 or higher
- [JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 8 or higher
- Android SDK


## USER INTERFACE
For this part, most of the work we did in [figma](https://www.figma.com/design/sOtiTNwQxBWisybg9KX45v/FINAL-UI?node-id=32-153&t=7dx0883Wp94RS4eg-1) so please check the link for mode details. 

| Home             | Detail Painting |
| ----------------- | -------------------------------------------------------------------------- |
| <img src = "https://github.com/Joevandyta/ArtScape/assets/148755512/397908ce-4818-4e36-b680-01edfb531043" alt="Logo" style="width:230px;"/>|<img src = "https://github.com/Joevandyta/ArtScape/assets/148755512/30dcc456-ad37-420a-9904-ecfbf27bd61a" alt="Logo" style="width:225px;"/> |

We immediately proceeded to the next step, which involved creating the high-fidelity design to save time. We designed three pages: a splash screen, a login screen, and additionally, an interest page. Initially, we used a simple color scheme, primarily white, as shown below. Following this, we iterated on the design by incorporating logos and custom-colored buttons.

| Splash Screen | Login | Interest |
| --- | --- | --- |
| Before  |
| <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/ebc1a9ed-f3ed-46d9-bead-5f4512e600a7" alt="Logo" style="width:230px;"/> | <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/5c20cc32-de32-472a-85a3-ee24e81a224b" alt="Logo" style="width:230px;"/> | <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/0918ea7d-6d0b-453f-ac6e-f0860a9da2f0" alt="Logo" style="width:230px;"/> |
| After | | |
| <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/aac673f6-8b33-4a40-978f-25a509fe1a24" alt="Logo" style="width:230px;"/> | <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/df6dd584-c97d-40ed-8ce6-970008272d79" alt="Logo" style="width:230px;"/> | <img src="https://github.com/Joevandyta/ArtScape/assets/148755512/94de6492-da75-4dea-97e6-42135bb74df6" alt="Logo" style="width:230px;"/> |

## Resources

- [Material Symbols & Icons by Google](https://fonts.google.com/icons)

- [Google Font ](https://fonts.google.com/)

- [Chips – Material Design 3](https://m3.material.io/components/chips/overview)

- [LottieFiles](https://lottiefiles.com/)



## Features

1. **Login** : Google One Tap Sign-In: Users can sign in using their Google account credentials, providing a seamless and secure login experience.
 
2. **HomePage** > **Main Homepage** : After signing in, users are greeted with the main homepage, showcasing featured paintings and artists.

3. **Account Page** > **Account Information** : Users can view and manage their account details, including personal information and app settings.

4. **Search** > **Advanced Search** : Users can search for paintings by genre, title, and description, as well as search for artists.

5. **Upload Painting** > **Automatic Genre Detection** : Artists can upload their paintings, and the app will automatically classify the artwork into the appropriate genre.

6. *(Coming Soon)* **Painting Transaction**  > **Sell Paintings** : Artists will soon be able to sell their paintings directly through the app, making ArtScape a comprehensive marketplace for art transactions.

## Installation

| Normal Course             | Post Condition                                                               | Precondition Sign in |
| ----------------- | ------------------------------------------------------------------ | ------------- |
|Download the app | Artist registered as ArtScape user   | Be registered as a google account user  |
| Open the app| Artists can access the features provided by ArtScape      |       Have a smartphone |
| | |  Have internet access |


## Application Development
### Technology Used

**Kotlin :** The primary programming language used for developing the application.

**Firebase:** Used for backend services, including authentication, database, and cloud storage.

**Retrofit :**  A REST client used for networking and API interactions. Room: A robust database library for local data storage.

**Glide :**   An image loading library for efficient loading and caching of images.

### dependencies 
Here are all the dependencies i use in ArtScape application:

    //firebase
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.preference)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // coroutine
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // googlePlayServices
    implementation(libs.play.services.base)

    implementation(libs.github.glide)
    // dataStore
    implementation(libs.androidx.datastore.preferences)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    implementation(libs.logging.interceptor)

    // credential
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Firebase
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // ui
    implementation(libs.lottie)



