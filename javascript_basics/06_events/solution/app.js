(function() {

    var d1 = document.querySelector(".d1");
    var d2 = document.querySelector(".d2");
    var d3 = document.querySelector(".d3");

    d1.onclick = function(e) {
        showClass(e, d1);
    };

    d2.onclick = function(e) {
        showClass(e, d2);
    };

    d3.onclick = function(e) {
        showClass(e, d3);
    };

    function showClass(event, element) {
        alert(element.className);
        event.stopPropagation();
    }

})();