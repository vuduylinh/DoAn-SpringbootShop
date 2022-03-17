$(document).ready(function(){
        $(".add-like").on("click", function(){
         var id = $(this).closest("[data-id]").attr("data-id");
           var url = "/product/like/" + id;
           fetch(url).then(resp => resp.json()).then(json => {
            alert("Đã Like -- "+ json)
           })
        });

        $(".add-share").on("click", function(){
            alert("share")
        });
});