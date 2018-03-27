function openLoginModal(){
    $("#registeredModal").modal("hide");  
    $("#registeredModal").on("hidden.bs.modal",function(){  
        $("#loginModal").modal("show");  
        $("#registeredModal").off().on("hidden","hidden.bs.modal");  
    }) 
}
