$(document).ready(function() {
    $("a[href*=delete]").on("click", function() {
        if (!confirm("Are you sure to delete this?")) {
            return false;
        }
    });

    $("[name=photo_file]").on("change", function() {
        showImage(this, '#photo_img');
    });

    $("input[name=image_file]").on("change", function() {
        showImage(this, '#image_img');
    });

    showImage = function(fileSelector, imageSelector) {
        let file = $(fileSelector).get(0).files[0];
        let fileReader = new FileReader();
        fileReader.onload = function() {
            $(imageSelector).attr("src", fileReader.result);
        };
        fileReader.onerror = function() {
            alert(fileReader.error);
        };
        fileReader.readAsDataURL(file);
    }
});