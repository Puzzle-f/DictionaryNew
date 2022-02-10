import org.gradle.api.JavaVersion

object Config{
    const val application_id = "com.example.dictionarynew"
    const val compile_sdk = 31
    const val min_sdk = 28
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases{
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val history = ":history"
}


object Versions {

        //    RecyclerView
    const val recyclerview = "1.2.1"

        // AndroidX
    const val appcompat = "1.4.0"
    const val swiperefreshlayout = "1.1.0"

        // Design
    const val material = "1.4.0"

        //    Kotlin
    const val core = "1.0.2"
    const val stdlib = "1.3.41"

        // Rx-Java
    const val rxandroid = "2.1.0"
    const val rxjava = "2.2.8"

        //    Rertofit
    const val retrofit = "2.6.0"
    const val logging = "3.12.1"
    const val rxjava2Adapter = "1.0.0"

        //    Test
    const val junit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.4.0"
    const val ext_junit = "1.1.3"

//    Koin
    const val koin = "2.0.1"

//    Coroutines
    const val coroutines = "1.5.2"
    const val retrofit2Coroutines = "0.9.2"

//    Delegate
    const val viewbindingpropertydelegate = "1.4.6"

//    Picasso
    const val picasso = "2.5.2"

//    Glide
    const val glide = "4.9.0"

//    Room
    const val room = "2.4.1"

//    MyMain
    const val core_ktx = "1.7.0"
const val constraintlayout = "2.1.2"

}

object MyRecyclerView{
    const val recyclerview = "androidx.recyclerview:recyclerview: ${Versions.recyclerview}"
}

object MyAndroidX{
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
}

object Design{
    const val material = "com.google.android.material:${Versions.material}"
}

object MyKotlin{
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.41${Versions.stdlib}"
}

object RxJava{
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
}

object Retrofit{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging}"
    const val rxjava2Aapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjava2Adapter}"
}

object TestImpl{
    const val junit = "junit:junit:${Versions.junit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Koin{
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
}

object Coroutines{
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit2Coroutines}"
}

object Delegateviewbindingpropertydelegate{
    const val viewbindingpropertydelegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewbindingpropertydelegate}"
}

object Picasso{
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Glide{
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object MyMain{
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val junit = "junit:junit:4.+"
    const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
}






