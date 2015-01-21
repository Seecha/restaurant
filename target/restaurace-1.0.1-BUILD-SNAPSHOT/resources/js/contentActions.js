//TODO po odecteni vsech jidel z kosiku obcas par haleru zbyde v headeru
$(document).ready(function(){

    /**
      * actualize field with new orders count on every page ready with admin logged in
      */
    $("#newOrdersCount").ready(function(){
        var ele = $("#newOrdersCount").length;
        //this actually tests, if admin is logged in, the element above exists only in that case
        if(ele > 0){
            $.getJSON("/restaurace/admin/showNewOrders", function(size){
                console.log(size);
                $("#newOrdersCount").text(size);
            });
        }
    });

    /*
     * submit form on click, move to checkout
     */
    $("#checkoutSubmit").click(function(){
        $("#deliveryForm").submit();
    });

    /*
     * add dish to cart on form submit
     */
    $(".buyForm").submit(function(event){
        var dish = $(this).serializeObject();
        addDishToCart(dish);
        event.preventDefault();
    });

    /*
     * add ingredient to custom dish on form submit
     */
    $(".addIngredientForm").submit(function(event){
        var ingredient = $(this).serializeObject();
        addIngredient(ingredient);
        event.preventDefault();
    });

    /*
     * on submit event remove dish from cart, related to the whole document
     * in order to prevent issues after ajax reloads of page fragments
     */
    $(document).on('submit', ".removeCartItem", function(event){
        var id = $(this).serializeObject();
        removeCartDishItem(id);
        event.preventDefault();
    });

    /*
     * on submit event remove custom dish from cart, related to the whole document
     * in order to prevent issues after ajax reloads of page fragments
     */
    $(document).on('submit', ".removeCustomCartItem", function(event){
        var id = $(this).serializeObject();
        removeCustomCartDishItem(id);
        event.preventDefault();
    });

    //TODO dodelat rucni aktualizaci kosiku v headeru a prepoctu cen
    /*
     * calls controller method with mapping "/restaurace/removeItem",
     * gets output, if valid JSON, executes function, which reaload some page fragments
     */
    function removeCartDishItem(data){
        $.getJSON("/restaurace/removeItem", data, function(dish){
            actualizeCalculationsAfterItemRemoved(dish);
        });
    }

    /*
     * calls controller method with mapping "/restaurace/removeCustomItem",
     * gets output, if valid JSON, executes function, which reaload some page fragments
     */
    function removeCustomCartDishItem(data){
        $.getJSON("/restaurace/removeCustomItem", data, function(dish){
            actualizeCalculationsAfterItemRemoved(dish);
        });
    }

    /*
     * get some html elements and actualize their values after cart items changes
     */
    function actualizeCalculationsAfterItemRemoved(dish){
        var dishesPrice = dish.price * dish.amount;

        //actualize list of ordered dishes
        $("#cartItems").load(document.URL + ' #cartItems');

        //Change header calculations
        $("#cartPrice").text(parseFloat(($("#cartPrice").text()) - dishesPrice).toFixed(2));
        $("#totalItems").text(parseInt($("#totalItems").text()) - dish.amount);

        //TODO mozno prepsat bez loadu do budoucna kvuli optimalizaci
        //Change cart calculations
        $("#cartCalculations").load(document.URL + ' #cartCalculations');

        //Change orderCart calculations
        var orderCartDishPrice = parseFloat($("#dishTotalPrice").text()) - (dish.price * dish.amount);
        $("#dishTotalPrice").text(orderCartDishPrice.toFixed(2));
        $("#totalPrice").text((orderCartDishPrice + parseFloat($("#deliveryPrice").text())).toFixed(2));
    }

    /*
     * calls controller method with mapping "/restaurace/addIngredient",
     * gets output, if valid JSON, executes function, which reaload some page fragments
     */
    function addIngredient(data){
        $.getJSON("/restaurace/addIngredient", data, function(){
            $("#tepIngredients").load(document.URL +  ' #tepIngredients');
        });
    }

    /*
     * calls controller method with mapping "/restaurace/addToCart",
     * gets output, if valid JSON, executes function, which recounts some fields on a page
     */
    function addDishToCart(dish){
        $.getJSON("/restaurace/addToCart", dish, function(){
            var currentAmount = parseInt($("#totalItems").text());
            var additionalAmount = parseInt(dish.amount);

            var currentPrice = parseFloat($("#cartPrice").text());
            var additionalPrice = (parseFloat(dish.price) * parseFloat(additionalAmount));

            $("#totalItems").text(currentAmount + additionalAmount);
            $("#cartPrice").text((currentPrice + additionalPrice).toFixed(2));
        });
    }

    /*
     * counting various delivery prices on page LOAD
     */
    $("#cartCalculation").ready(function(){
        var $deliveryRadio = $(".deliveryRadio:first");
        $deliveryRadio.prop("checked", true);
        if($($deliveryRadio).is(":checked")){
                    $("#deliveryPrice").text($($deliveryRadio).parent().parent().find(".deliveryPrice").text());
                    $("#totalPrice").text((parseFloat($($deliveryRadio).parent().parent().find(".deliveryPrice").text())
                        + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

    /*
     * counting various delivery prices on radio button click
     */
    $(".deliveryRadio").click(function(){
        if($(this).is(":checked")){
            $("#deliveryPrice").text($(this).parent().parent().find(".deliveryPrice").text());
            $("#totalPrice").text((parseFloat($(this).parent().parent().find(".deliveryPrice").text())
                + parseFloat($("#dishTotalPrice").text())).toFixed(2)  + ' Kč');
        }
    });

    /*
     * not used yet
     */
    function getUrlParameter(sParam)
    {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++)
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam)
            {
                return sParameterName[1];
            }
        }
    }

    //remove ingredient from custom dish
    /*$("#actualIngredients .remove").click(function(){
        var id = this.name;
        removeIngredient(id);
    });

    function removeIngredient(data){
        $.getJSON("/restaurace/removeIngredient",{id: data}, function(){
            $("#tepIngredients").load(document.URL +  ' #tepIngredients');
        });
    }*/
});