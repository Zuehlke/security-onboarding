$( document ).ready(function() {


    //GET REQUEST
    $.ajax({
        url: "http://localhost:3000/users",
        type: 'GET',
        dataType: "jsonp",
        success: function(data, textStatus, jqXHR) {
           console.dir(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Status: " + textStatus + ". Error Thrown: " + errorThrown)
        }
    });

});