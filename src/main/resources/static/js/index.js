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
    $('#title').empty();
    $.ajax({
            url : 'kubelistPodForAllNamespaces',
            type : 'post',
            dataType : 'json',
            success : function(data) {
                if(data){
                var tempHtml = "";
                $(data).each(function(i, value) {
                    value1 = value.replace(/\[|]/g,'');
                    var val = value1.split(",");
                    tempHtml +="<tr>";
                    tempHtml +=" <td width='30%'>'" + val[0] + "'</td>";
                    tempHtml +=" <td width='30%'>'" + val[1] + "'</td>";
                    tempHtml +=" <td width='30%'>'" + val[2] + "'</td>";
                    tempHtml +="</tr>";
                })
                $('#title').load("html/allPods.html");
                $('#test').append(tempHtml);
              }
            },

        })
}

function kubelistNamespace(){
    $('#test').empty();
    $('#title').empty();
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
                $('#title').load("html/allNamespaces.html");
              }
            },

        })
}

function kubelistNode(){
    $('#test').empty();
    $('#title').empty();
    $.ajax({
            url : 'kubelistNode',
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
                $('#title').load("html/allNodes.html");
              }
            },

        })
}

function kubeLoadCreateNamespace(){
    $('#test').empty();
    $('#title').empty();
    $('#title').load("html/createNamespace.html");
    //$('#test').load("html/createNamespace.html");
}

function kubeLoadCreatePod(){
    $('#test').empty();
    $('#title').empty();
    $('#title').load("html/createPod.html");
    //$('#test').load("html/createNamespace.html");
}
