

## Android app with Wordpress - Screenshot

![screenshot_quote](./screenshot_quote.jpg)

## About project

I designed UI mockups and implemented end-to-end web applications using the CodeIgniter MVC framework and bootstrap front-end framework;


## Built With

* *PHP MVC Framework* - Dependency Management * *CodeIgniter Web Framework* - The web framework used
* *Bootstrap* -  Used to generate RSS Feeds 

URL : http://wifiinfobank.unapcict.org


## Programming code description
### PHP MVC Framework - Controller

A step by step series of examples that tell you how to get a development env running

Say what the step will be

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

And there are more php files by category

```php
<?php
if (!defined('BASEPATH'))
    exit('No direct script access allowed');
/**
 *  게시판 메인 컨트롤러
 */
 
class Board extends CI_Controller {
 
    function __construct() {
        parent::__construct();
        $this -> load -> database();
        $this -> load -> model('board_m');
        $this -> load -> helper(array('url', 'date'));
    }
 
    /**
     * 주소에서 메서드가 생략되었을 때 실행되는 기본 메서드
     */
    public function index() {
        $this -> lists();
    }
 
    /**
     * 사이트 헤더, 푸터가 자동으로 추가된다.
     */
    public function _remap($method) {
        // 헤더 include
        $this -> load -> view('header_v');
 
        if (method_exists($this, $method)) {
            $this -> {"{$method}"}();
        }
 
        // 푸터 include
        $this -> load -> view('footer_v');
    }
 
    /**
     * 목록 불러오기
     */
    public function lists() {
        $data['list'] = $this -> board_m -> get_list();
        $this -> load -> view('board/list_v', $data);
    }
 
}

```

### PHP MVC Framework - Model

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```php
 
<?php
if (!defined('BASEPATH'))
    exit('No direct script access allowed');
 
/**
 * 공통 게시판 모델
 */
 
class Board_m extends CI_Model {
    function __construct() {
        parent::__construct();
    }
 
    function get_list($table = 'ci_board') {
        $sql = "SELECT * FROM ".$table." ORDER BY board_id DESC";
        $query = $this -> db -> query($sql);
        $result = $query -> result();
        // $result = $query->result_array();
 
        return $result;
    }
 
}

```


### PHP MVC Framework - View (Bootstrap)

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```html
<!-- Navigation -->
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    		<div class="container-fluid">
    			
    			<div class="navbar-header page-scroll">
    				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
    					<span class="sr-only">Toggle navigation</span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    				</button>
    				<a href="/main/"><img src="/static/lib/bootstrap/img/logo.jpg"></a>
    			</div>
    			<!-- Collect the nav links, forms, and other content for toggling -->
    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    				<ul class="nav navbar-nav navbar-right ">
    					<li>
    						<a href="/main/">HOME</a>
    					</li>
    					<li class="dropdown">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">ABOUT<span class="caret"></span></a>
    						<ul class="dropdown-menu" role="menu">
    							<li>
    								<a href="/about/aboutus/">What is WIFI? </a>
    							</li>
    							<li class="divider"></li>
    							<li>
    								<a href="/about/aboutus_2/">What is WIFI InfoBank?</a>
    							</li>
    							<li class="divider"></li>
    						</ul>
    					</li>

```
xxx

```html
<div class="col-md-12">
              <div id="carousel" class="carousel slide carousel-fade" data-ride="carousel">
                
                <!-- Carousel items -->
                <div class="carousel-inner carousel-zoom">
                  <div class="active item">
                    <img class="img-responsive" src="/static/lib/bootstrap/img/home-bg-1.jpg">
                  </div>
                  <div class="item">
                    <img class="img-responsive" src="/static/lib/bootstrap/img/home-bg-2.jpg">
                    <div class="carousel-caption"></div>
                  </div>

```

xxx

```html
<!-- Navigation -->
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    		<div class="container-fluid">
    			
    			<div class="navbar-header page-scroll">
    				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
    					<span class="sr-only">Toggle navigation</span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    					<span class="icon-bar"></span>
    				</button>
    				<a href="/main/"><img src="/static/lib/bootstrap/img/logo.jpg"></a>
    			</div>
    			<!-- Collect the nav links, forms, and other content for toggling -->
    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    				<ul class="nav navbar-nav navbar-right ">
    					<li>
    						<a href="/main/">HOME</a>
    					</li>
    					<li class="dropdown">
    						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">ABOUT<span class="caret"></span></a>
    						<ul class="dropdown-menu" role="menu">
    							<li>
    								<a href="/about/aboutus/">What is WIFI? </a>
    							</li>
    							<li class="divider"></li>
    							<li>
    								<a href="/about/aboutus_2/">What is WIFI InfoBank?</a>
    							</li>
    							<li class="divider"></li>
    						</ul>
    					</li>

```


## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system



## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

