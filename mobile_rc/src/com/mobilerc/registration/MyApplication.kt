package com.mobilerc.registration

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.glassfish.jersey.server.ResourceConfig
import java.io.IOException
import javax.ws.rs.ApplicationPath

@ApplicationPath("/*")
class MyApplication : ResourceConfig() {
    init {
        try {
            initFirebase()
        } catch (e: IOException) {
            e.printStackTrace()
            println("FIREBASE INIT FAIL")
        }
    }

    @Throws(IOException::class)
    private fun initFirebase() { //   FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir") + "/resources/firebase/serviceAccountKey.json");
        val inputStream = this.javaClass.classLoader.getResourceAsStream("firebase/serviceAccountKey.json")
        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://mobilerc-bfa66.firebaseio.com")
                .build()
        FirebaseApp.initializeApp(options)
    }

}
