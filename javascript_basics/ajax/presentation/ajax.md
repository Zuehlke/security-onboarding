## AJAX ##

AJAX stands for Asynchronous JavaScript and XML. In a nutshell, it is the use of the XMLHttpRequest object to communicate with server-side scripts. 

**XMLHttpRequest** is an API that provides client functionality for transferring data between a client and a server.

It can send as well as receive information in a variety of formats, including JSON, XML, HTML, and text files. 

AJAX’s most appealing characteristic, is its "asynchronous" nature, which means it can do all of this without having to refresh the page. This lets you update portions of a page based upon user events.

        httpRequest.onreadystatechange = nameOfTheFunction;

**httpRequest.onreadystatechange** - tells the HTTP request object which JavaScript function will handle processing the response.

Note that there are no parentheses after the function name and no parameters passed, because you're simply assigning a reference to the function, rather than actually calling it.

    var xhttp = new XMLHttpRequest();

	    xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("demo").innerHTML = xhttp.responseText;
		    }
	    }

    xhttp.open("GET", "ajax_info.txt", true);
    xhttp.send();

The open() parameters are:

1. method: the type of request: GET, POST etc.
2. url
3. async: true (asynchronous) or false (synchronous)

jQuery example of AJAX:

    //GET REQUEST
    $.ajax({
        url: getUrlString,
        type: 'GET',
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
           console.dir(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
        }
    });

    //getJSON method
    $.getJSON( "http://jsonplaceholder.typicode.com/posts", function( data ) {
	    var items = [];
	    $.each( data, function( key, val ) {
	    	items.push( key  + ": " + val.title);
	    });
	    
	    console.log(items);
    });
    
    //POST REQUEST
    $.ajax('http://jsonplaceholder.typicode.com/posts', {
	    method: 'POST',
	    data: {
	    	title: 'foo',
	    	body: 'bar',
	    	userId: 1
	    },
	    success: function(data, textStatus, jqXHR) {
	    	console.log(data)
	    }
    });


**Same-Origin Policy and JSONP**

In general, Ajax requests are limited to the same protocol (http or https), the same port, and the same domain as the page making the request. This limitation does not apply to scripts that are loaded via jQuery's Ajax methods.

The other exception is requests targeted at a JSONP service on another domain. In the case of JSONP, the provider of the service has agreed to respond to your request with a script that can be loaded into the page using a `<script>` tag, thus avoiding the same-origin limitation; that script will include the data you requested, wrapped in a callback function you provide.

	//JSONP
	$.ajax({
	    url: 'http://jsonplaceholder.typicode.com/posts/1',
	    type: 'GET',
	    dataType: "jsonp",
	    success: function(data, textStatus, jqXHR) {
	        console.log(data)
	    },
	    error: function(jqXHR, textStatus, errorThrown) {
	        console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
	    }
	});


	//Multiple Simultaneous Ajax Requests in jQuery
    var posts = [];

    $.when(
        $.get("http://jsonplaceholder.typicode.com/posts/1", function(data) {
            posts.push(data);
        }),

        $.get("http://jsonplaceholder.typicode.com/posts/2", function(data) {
            posts.push(data);
        }),

        $.get("http://jsonplaceholder.typicode.com/posts/2", function(data) {
            posts.push(data);
        })

    ).then(function() {

        console.dir(posts);

    });

The jqXHR objects returned by $.ajax() as of jQuery 1.5 implement the Promise interface, giving them all the properties, methods, and behavior of a Promise.

	//Sequential AJAX calls
	var posts = [];
	$.get("http://jsonplaceholder.typicode.com/posts/1", function(data) {
	    posts.push(data);
	    console.log(posts.length);
	}).then(function() {
	    $.get("http://jsonplaceholder.typicode.com/posts/2", function(data) {
	        posts.push(data);
	        console.log(posts.length);
	    });
	}).then(function() {
	    $.get("http://jsonplaceholder.typicode.com/posts/3", function(data) {
	        posts.push(data);
	        console.log(posts.length);
	    });
	});


## Exercise ##

Start the Node server, in the **/task/myapp** directory, and create an AJAX request for the following url: [http://localhost:3000/users](http://localhost:3000/users)