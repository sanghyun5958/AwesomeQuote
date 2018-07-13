

## Screenshot - Android app with Wordpress

![screenshot_quote](./screenshot_quote.jpg)

## The purpose of the project

To help people interested in learning either Korean or English using pictures of quotes which are translated by using the Android application.

## the project summary

* **Gson/Json Parsing Using Restrofit2** - I have developed using Restrofit2 to convert the REST API(Wordpress) in the Java interface.
* **Custom Wordpress plugin** - I developed a plug-in that sends HTTP messages to Firebase so that users can get notifications when I post on Wordpress.
* **Android UI Design** -  I used Adobe XD to design the entire app UI from prototype to small buttons.

Download Link : https://play.google.com/store/apps/details?id=com.knily.awesomequote


## Programming code description

### Gson/Json Parsing Using Restrofit2
```java
private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create());
    public static <T> T createService(Class<T> serviceClass) {
        addLoggingInterceptor(httpClient);
        Retrofit retrofit = retrofitBuilder.
                client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
        ......
    public static Retrofit provideRetrofit (String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl( baseUrl )
                .client( provideOkHttpClient() )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

    private static OkHttpClient provideOkHttpClient ()
    {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(provideHttpLoggingInterceptor())
                    .addInterceptor(provideOfflineCacheInterceptor())
                    .addNetworkInterceptor(provideCacheInterceptor())
                    .cache(provideCache())
                    .build();
        }
        return client;
    }
?>

```

### Custom Wordpress plugin
```php
function _do_post( $refPath, $title, $message, $url ) {
    $options = get_option( 'fa_options' );
    $server_key = $options['server_key'];
    $data = [
        'title' => print_r( $title, true ),
        'body' => print_r( $message, true ),
        'url' => print_r( $url, true ),
        'request_time' => print_r( $_SERVER['REQUEST_TIME'], true ),
        'remote_addr' => print_r( getenv( 'REMOTE_ADDR' ), true ),
        'forwarded_for' => print_r( getenv( 'HTTP_FORWARDED_FOR' ), true )
    ];
    $topic = '/topics/' . $refPath;
    $body = [
        'data' => $data,
        'to' => $topic
    ];
    $headers = array
    (
      'Authorization: key=' . $server_key,
      'Content-Type: application/json'
    );
    $ch = curl_init();
    curl_setopt( $ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
    curl_setopt( $ch, CURLOPT_POST, true );
    curl_setopt( $ch, CURLOPT_HTTPHEADER, $headers );
    curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
    curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER, false );
    curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode( $body ) );
    $result = curl_exec( $ch );
    curl_close( $ch );
    error_log( "fcm result: " . $result );
}
```
