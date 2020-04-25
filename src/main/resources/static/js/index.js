function init() {
    $.ajax({
        url : 'init',
        type : 'post',
        dataType : 'json',
        success : function(data) {

        }
    })
}

function kubelistPodForAllNamespaces(){
    $('#test').empty();
    $.ajax({
            url : 'kubelistPodForAllNamespaces',
            type : 'post',
            dataType : 'json',
            success : function(data) {
                if(data){
                var tempHtml = "";
                $(data).each(function(i, value) {
                   //var val1= value.replace(/[/g,"");
                    var val = value.split(",");
                    tempHtml +="<tr>";
                    tempHtml +=" <td>'" + val[0] + "'</td>";
                    tempHtml +=" <td>'" + val[1] + "'</td>";
                    tempHtml +=" <td>'" + val[2] + "'</td>";
                    tempHtml +="</tr>";
                })
                $('#test').append(tempHtml);
              }
            },

        })
}

function kubelistNamespace(){
    $('#test').empty();
    $.ajax({
            url : 'kubelistNamespace',
            type : 'post',
            dataType : 'json',
            success : function(data) {
                if(data){
                var tempHtml = "";
                $(data).each(function(index, val) {
                    val = val.replace(/'/g,"");
                    tempHtml +="<tr>";
                    tempHtml +=" <td>'" + val + "'</td>";
                    tempHtml +="</tr>";
                })
                $('#test').append(tempHtml);
              }
            },

        })
}
