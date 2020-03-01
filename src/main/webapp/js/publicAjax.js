function publicAjax(data){
    $.ajax({
        type:data.type,
        url:data.url,
        dataType:data.dataType,
        data:data.data,
        success:eval(data.success),
        error:function (msg) {
            console.log(msg)
        }
    });
}

function publicFileAjax(data) {
    $.ajax({
        url: data.url,
        type: 'post',
        data: data.data,
        dataType:"json",
        // cache: false,
        processData: false,
        contentType: false,
        // async:false,
        success:eval(data.success),
        error:function(msg){
            console.log(msg)
        }
    });
}
