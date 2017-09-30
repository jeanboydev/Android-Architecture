# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Develop\Android\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
##--------------------------------------data 配置---------------------------------------------------
# model
-keep class com.jeanboy.data.cache.database.model.** { *; }
-keep class com.jeanboy.data.net.entity.** { *; }
-keep class com.jeanboy.data.mapper.** { *; }

##--------------------------------------data 配置---------------------------------------------------

##greenDAO
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties

-dontwarn rx.**
-keep class rx.** { *; }
-dontwarn net.sqlcipher.database.SQLiteOpenHelper
-keep class net.sqlcipher.database.SQLiteOpenHelper
-dontwarn net.sqlcipher.database.SQLiteDatabase
-keep class net.sqlcipher.database.SQLiteDatabase
-dontwarn net.sqlcipher.database.SQLiteStatement
-keep class net.sqlcipher.database.SQLiteStatement
-dontwarn org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper
-keep class org.greenrobot.greendao.database.DatabaseOpenHelper$EncryptedHelper
-dontwarn net.sqlcipher.database.SQLiteDatabase$CursorFactory

##FastJson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

##gson
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** {
    <fields>;
    <methods>;
}
-dontwarn com.google.gson.**

##OkHttp
-dontwarn okhttp3.**

##retrofit2
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.fasterxml.**
-dontwarn retrofit2.**


#simplexml
-dontwarn sun.misc.**
-libraryjars <java.home>/lib/rt.jar(java/**,javax/**)
-keepattributes *Annotation*,EnclosingMethod
-keep public class org.simpleframework.**{ *; }
-keep public class org.simpleframework.xml.**{ *; }
-keep public class org.simpleframework.xml.core.**{ *; }
-keep public class org.simpleframework.xml.util.**{ *; }
-keep public class org.simpleframework.xml.stream.**{ *; }
-keep public class javax.** { *; }
-keep public class javax.xml.stream.**{ *; }

-keep public class org.simpleframework.** {
      public void set*(***);
      public *** get*();
}