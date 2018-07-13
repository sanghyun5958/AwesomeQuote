

## Screenshot - Android app with Wordpress

![screenshot_quote](./screenshot_quote.jpg)

## The purpose of the project

To help people interested in learning either Korean or English using pictures of quotes which are translated by using the Android application.



## the project summary

* **Gson/Json Parsing Using Restrofit2** - I have developed using Restrofit2 to convert the REST API in the Java interface.
* **Custom Wordpress plugin** - I designed this entire app with Bootstrap responsive grid system, prebuilt components, and plugins built on jQuery. I also used the html5 feature.
* **Android UI Design** -  I created and modified web site images using Photoshop.

Download Link : https://play.google.com/store/apps/details?id=com.knily.awesomequote


## Programming code description
### PHP MVC Framework - Controller


```java
public class MessageService extends FirebaseMessagingService {
    private static final String TAG = "MessageService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String from = remoteMessage.getFrom();
        // Check if message contains a data payload.
        Map<String, String> data = remoteMessage.getData();
        if (data.size() > 0) {
            Log.d(TAG, "Message data payload: " + data);
            switch (from) {
                case "/topics/log":
                    onLogMessage(data);
                break;
                case "/topics/login":
                    ...
                    ...
                case "/topics/new_page":
                    onNewPageMessage(data);
                break;
            }
        }
?>

```

