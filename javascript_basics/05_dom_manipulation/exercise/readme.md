# Shopping cart #

Implement a shopping cart with following requirements:

- User should be able to add products from the presented list to the shopping cart by clicking on a button next to the product name. If the product has already been added to the cart increase the amount.			 
- Shopping cart should be shown by clicking on the button in the upper right corner. 
- Shopping cart items should contain name of the product and amount.
- User should be able to remove the products from the cart by clicking on the button next to the amount. The product should be removed completely from the cart (do not decrease the amount).
- User should be able to close the cart by clicking on the Close button in the upper right corner of the cart window.
- If there is nothing in the cart the user should see the message "There are no items in the cart."

Task needs to be solved first using native javascript libraries and afterwards using jQuery.

Resources and guidelines:

- html and css resources are already provided for this task - shopping_cart.html, shopping_cart.css
- list of products is defined in product.js and and loaded in shopping_cart.html
- for the first part of the task (native javascript) provide the implementation in task\native.js and enable the script in shopping_cart.html
- for the second part of the task (jQuery) provide the implementation in task\jquery.js and enable the script in shopping_cart.html
- the html file contains the layout of the web page and required templates to generate the content. You can also find some hints in the file on where the templates should be used.