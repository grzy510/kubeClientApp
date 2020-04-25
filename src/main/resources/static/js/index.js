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
    $.ajax({
            url : 'kubelistPodForAllNamespaces',
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

function kubelistNamespace(){
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
