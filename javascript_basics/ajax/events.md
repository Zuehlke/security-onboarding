## AJAX ##

AJAX stands for Asynchronous JavaScript and XML. In a nutshell, it is the use of the XMLHttpRequest object to communicate with server-side scripts. 

It can send as well as receive information in a variety of formats, including JSON, XML, HTML, and even text files. 

AJAX’s most appealing characteristic, however, is its "asynchronous" nature, which means it can do all of this without having to refresh the page. This lets you update portions of a page based upon user events.

    var xhttp = new XMLHttpRequest();

	    xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("demo").innerHTML = xhttp.responseText;
		    }
	    }

    xhttp.open("GET", "ajax_info.txt", true);
    xhttp.send();

The open() parameters are:

1. method: the type of request: GET or POST
2. url
3. async: true (asynchronous) or false (synchronous)

jQuery example of AJAX:

    $.ajax({
	    url: 'getTwitterFollowers.php',
	    type: 'GET',
	    data: 'twitterUsername=jquery4u',
	    success: function(data) {
	    	//do something
	    },
	    error: function(e) {
	    	//do something else
	    }
    });
