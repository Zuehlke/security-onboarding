## Listening for events

The Event interface represents any event of the DOM. It contains common properties and methods to any event.

DOM Events are sent to notify code of interesting things that have taken place. Each event is represented by an object which is based on the Event interface, and may have additional custom fields and/or functions used to get additional information about what happened. 

Events can represent everything from basic user interactions to automated notifications of things happening in the rendering model.

The addEventListener() method attaches an event handler to the specified element.

    //execute displayDate() on the click event
    document.getElementById("myBtn").addEventListener("click", function() {
		console.log( "click" );
    });

The same in jQuery:

    $("#myBtn").on( "click", function() {
    	console.log( "click" );
    });


A list of the standard DOM events can be found on the following link:
[https://developer.mozilla.org/en-US/docs/Web/Events](https://developer.mozilla.org/en-US/docs/Web/Events)

## Event bubbling and capturing ##

**Event bubbling** states that after an event triggers on the deepest possible element, it then triggers on parents in nesting order.

    <div class="d1">1  <!-- the topmost -->
	    <div class="d2">2
		    <div class="d3">3 <!-- the innermost -->
		    </div>
	    </div>
    </div>

The bubbling guarantees that click on Div 3 will trigger onclick first on the innermost element 3 (also caled the target), then on the element 2, and the last will be element 1.

![Event Bubbling](/event-order-bubbling.gif)

The deepest element which triggered the event is called the target or, the originating element.

Internet Explorer has the **srcElement** property for it, all W3C-compliant browsers use **event.target**.

When event handlers trigger on parents:

1. **Event.target**/**srcElement** - remains the same originating element.
2. **This** - is the current element, the one event has bubbled to, the one which runs the handler.

![Event target](/event-order-bubbling-target.png)


A handler may decide that event is fully processed and stop the bubbling.

1. For W3C-compliant browsers: **event.stopPropagation()**
2. For IE<9: **event.cancelBubble = true**


In all browsers, except IE<9, there are two stages of event processing.


**The event first goes down - that’s called capturing, and then bubbles up**. This behavior is standartized in W3C specification.

**All methods of event handling ignore the capturing phase**. 

Using **addEventListener with last argument true is only the way to catch the event at capturing**.

    elem.addEventListener( type, handler, phase )


![Event Bubbling Order](/event-order-w3c.gif)

According to this model, the event:

1. Captures down - through 1 -> 2 -> 3.
2. Bubbles up - through 3 -> 2 -> 1.
    

## Exercise ##

In the exercise folder there is an HTML with 3 nested divs.

    <div class="d1">1
	    <div class="d2">2
		    <div class="d3">3
		    </div>
	    </div>
    </div>

Add an click listener to each of them, without changing the HTML. On each click you should show the **div** class in  an alert dialog, but only of the div that was clicked.
