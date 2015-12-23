$( document ).ready(function() {


    //GET REQUEST
   /* $.ajax({
        url: "http://jsonplaceholder.typicode.com/posts",
        type: 'GET',
        dataType: "json",
        success: function(data, textStatus, jqXHR) {
           console.dir(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
        }
    });*/

    //getJSON method
   /* $.getJSON( "http://jsonplaceholder.typicode.com/posts", function( data ) {
        var items = [];
        $.each( data, function( key, val ) {
            items.push( key  + ": " + val.title);
        });

        console.log(items);
    });*/

    //POST REQUEST
   /* $.ajax('http://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        data: {
            title: 'foo',
            body: 'bar',
            userId: 1
        },
        success: function(data, textStatus, jqXHR) {
            console.log(data)
        }
    });*/

    //JSONP
  /*  $.ajax({
        url: 'http://jsonplaceholder.typicode.com/posts/1',
        type: 'GET',
        dataType: "jsonp",
        success: function(data, textStatus, jqXHR) {
            console.log(data)
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
        }
    });*/

    //Multiple Simultaneous Ajax Requests in jQuery
    /*var posts = [];

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

    });*/

    //Sequential AJAX calls
    //The jqXHR objects returned by $.ajax() as of jQuery 1.5 implement the Promise interface, giving them all the properties, methods, and behavior of a Promise
   /* var posts = [];
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
    });*/

});