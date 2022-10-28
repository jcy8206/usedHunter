console.log("Reply Module........");

var replyService = (function(){

	function getList(param, callback, error){
	 $.getJSON('/comments/'+bno+'/' + '.json',
                function(data){ // 서버로부터 응답이 도착하면 호출될 함수
                       if(callback){
                    	  callback(data);
                       }
                }).fail(function(xhr, status, err){
                	if (error){
                		error();
                	}
            }); // $.ajax()
            }
}