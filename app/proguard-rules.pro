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
##--------------------------------------App 配置---------------------------------------------------
# R 文件
-keep public class com.jeanboy.app.architecture.R$*{
		public static final int *;
}

##--------------------------------------App 配置---------------------------------------------------

##Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#fabric
#-libraryjars libs/io-fabric-sdk-andorid-fabric-class.jar
-dontwarn com.crashlytics.android.**
-keep class com.crashlytics.android.** {*;}
#io-fabric-sdk-andorid-fabric-class.jar
-keep class com.fabric.sdk.android.** {*;}