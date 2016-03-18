# AJAX #

AJAX stands for Asynchronous JavaScript and XML. In a nutshell, it is the use of the XMLHttpRequest object to communicate with server-side scripts. 

**XMLHttpRequest** is an API that provides client functionality for transferring data between a client and a server.

It can send as well as receive information in a variety of formats, including JSON, XML, HTML, and text files. 

AJAX’s most appealing characteristic, is its "asynchronous" nature, which means it can do all of this without having to refresh the page. This lets you update portions of a page based upon user events. You just assign the function that will be called after the response comes from the server asynchronously (not blocking the UI).

        httpRequest.onreadystatechange = nameOfTheFunction;

**httpRequest.onreadystatechange** - tells the HTTP request object which JavaScript function will handle processing the response.

Note that there are no parentheses after the function name and no parameters passed, because you're simply assigning a reference to the function, rather than actually calling it.

## Default browser AJAX implementation ##

In order to use AJAX using the default browser implementation you need to do the following:

    var xhttp = new XMLHttpRequest(); // Create the XMLHttpRequest object

	// Assign the function that will be called when the state of the
	// request changes
    xhttp.onreadystatechange = function() {  

		// Check if the state of the request is in completed (readyState == 4)
		// and if the HTTP response status code indicates success of request (status === 200) 
	    if (xhttp.readyState === 4 && xhttp.status === 200) {			
	      document.getElementById("demo").innerHTML = xhttp.responseText; // Do something with response
	    }
    }

	// Open the channel to the server using the open function of XMLHttpRequest object
    xhttp.open("GET", "someurlbase/someurl", true);
	// Send the request to the server
    xhttp.send();

The parameters of the open() function are:

1. method: the type of request: GET, POST etc.
2. URL
3. async: true (asynchronous) or false (synchronous)

## JQuery AJAX implementation ##

jQuery examples of AJAX:

    // Opens and sends the data to the server using the GET function and expects JSON as response
    $.ajax({
        url: "http://jsonplaceholder.typicode.com/posts",
        type: 'GET',
        dataType: "json",
        success: function(data, textStatus, jqXHR) { 
		   // will be called when response comes from server with 200 response status - success
           console.dir(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
			// will be called when response comes from server with and error response status (400, 500)
            console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
        }
    });

    // Shorter but less configurable way to do the previous example
    $.getJSON( "http://jsonplaceholder.typicode.com/posts", function( data ) {	    
	    console.log(data);
    });
    
    // Shorter but less configurable way to do a POST request
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



### Multiple AJAX requests ###

If you need multiple AJAX requests whose results you would like to have at the same time, you can do it in the following way:

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

The jqXHR objects returned by $.ajax() as of jQuery 1.5 implement the Promise interface, giving them all the properties, methods, and behaviour of a Promise. You can read more about promises on the Q promise framework documentation [page](https://github.com/kriskowal/q).

	// Sequential AJAX calls using promises
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

To start the node server:   
1. Open command line and navigate to the `/task/myapp` folder.   
2. Run command `npm i`   
3. Run command `npm start`   
4. Verify the server is running by navigating to [http://localhost:3000/users](http://localhost:3000/users)


Task itself is simple:   
1. Create a GET request to the [http://localhost:3000/users](http://localhost:3000/users)   
2. console.log the resulting data