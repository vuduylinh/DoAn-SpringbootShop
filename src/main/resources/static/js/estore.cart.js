$(document).ready(function() {
	 function showCartInfo(json){
		$("#cart-cnt").html(json.count);
		$("#cart-amt").html(json.amount.toFixed(2));
	}
	
	fetch('/cart/info').then(resp => resp.json()).then(json => {
		showCartInfo(json);
    });

    $(".add-to-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        fetch(`/cart/add/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
    });
    
     $(".remove-from-cart").on("click", function() {
        var id = $(this).closest("[data-id]").attr("data-id"); 
        fetch(`/cart/remove/${id}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
        $(this).closest("[data-id]").remove();
    });
    
     $(".qty-cart-update").on("input", function() {
        var id = $(this).closest("[data-id]").attr("data-id");
        var price = $(this).closest("[data-price]").attr("data-price");
        var qty = $(this).val();
        fetch(`/cart/update/${id}/${qty}`).then(resp => resp.json()).then(json => {
			showCartInfo(json);
        });
        $(this).closest("[data-id]").find(".item-amount").html((price * qty).toFixed(2));
    });
    
    $("#clearCart").on("click", function(){
        if(!confirm("Bạn có muốn xóa hết không?"))
        return false;
    });
    
 });