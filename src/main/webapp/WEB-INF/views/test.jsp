<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <h2>commentTest</h2>
    comment: <input type="text" name="comment"><br>
    <button id="sendBtn" type="button">SEND</button>
    <button id="modBtn" type="button">수정</button>
    <div id=commentList></div>
    <div id="replyForm" style="display:none">
    	<input type="text" name="replyComment">
    	<button id="wrtRepBtn" type="button">등록</button>
    </div>
    <script>
    
   		let bno = 1066;
    
    	let showList = function(bno){
    		 $.ajax({
                 type:'GET',       // 요청 메서드
                 url: '/comments?bno='+bno,  // 요청 URI
                 success : function(result){
                    $("#commentList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
                 },
                 error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
             }); // $.ajax()
    	}
    
        $(document).ready(function(){
    	  	 showList(bno);

            $("#sendBtn").click(function(){
            	let comment = $("input[name=comment]").val();
            	
            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("input[name=comment]").focus()
            		return;
            	}
            	 $.ajax({
                     type:'POST',       // 요청 메서드
                     url: '/comments?bno='+bno,  // 요청 URI /comments?bno=1081
                     headers : { "content-type": "application/json"}, // 요청 헤더
                     data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                     success : function(result){
                    	 alert(result);
                	  	 showList(bno);
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
            });
            
            $("#modBtn").click(function(){
            	let cno = $(this).attr("data-cno");
            	let comment = $("input[name=comment]").val();
            	
            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("input[name=comment]").focus()
            		return;
            	}
            	 $.ajax({
                     type:'PATCH',       // 요청 메서드
                     url: '/comments/'+cno,  // 요청 URI /comments/{cno}
                     headers : { "content-type": "application/json"}, // 요청 헤더
                     data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                     success : function(result){
                    	 alert(result);
                	  	 showList(bno);
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
            });
            
            $("#wrtRepBtn").click(function(){
            	let comment = $("input[name=replyComment]").val();
            	let pcno = $("#replyForm").parent().attr("data-pcno");
            	
            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("input[name=replyComment]").focus()
            		return;
            	}
            	 $.ajax({
                     type:'POST',       // 요청 메서드
                     url: '/comments?bno='+bno,  // 요청 URI /comments?bno=1081
                     headers : { "content-type": "application/json"}, // 요청 헤더
                     data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                     success : function(result){
                    	 alert(result);
                	  	 showList(bno);
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
                 
                 $("#replyForm").css("display", none)
                 $("input[name=replyComment]").val('')
                 $("#replyForm").appendTo("body");
            });
            
            $("#commentList").on("click", ".replyBtn", function(){
            	// 1. reply 폼을 답글 밑으로 옮기고
            	$("#replyForm").appendTo($(this).parent());
            	// 2. 답글을 입력할 폼을 보여줌
            	$("#replyForm").css("display", "block");
            });
            
            $("#commentList").on("click", ".modBtn", function(){ // . 은 클래스를 지칭함
            	let cno = $(this).parent().attr("data-cno");
            	let comment = $("span.comment", $(this).parent()).text();
            	
            	// 1. commnet 의 내용을 input 에 넣어주기
            	$("input[name=comment]").val(comment);
            	// 2. cno 전달하기
            	$("#modBtn").attr("data-cno", cno); // # 은 id를 지칭함
            });
            
            // $("#.delBtn").click(function(){}
            $("#commentList").on("click", ".delBtn", function(){
            	let cno = $(this).parent().attr("data-cno");
            	let bno = $(this).parent().attr("data-bno");
            	
            	$.ajax({
                    type:'DELETE',       // 요청 메서드
                    url: '/comments/'+cno+'?bno='+bno,  // 요청 URI
                    success : function(result){
                       alert(result)
                       showList(bno);
                    },
                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                }); // $.ajax()
                
              
            });
            
            
        });
        
        let toHtml = function(comments) {
    		let tmp = "<ul>";
    		comments.forEach(function(comment) {
    			tmp += '<li data-cno=' + comment.cno
    			tmp += ' data-pcno=' + comment.pcno
    			tmp += ' data-bno=' + comment.bno + '>'
    			if(comment.cno!=comment.pcno)
    				tmp += 'ㄴ'
    			tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
    			tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
    			tmp += ' up_date=' + comment.up_date
    			tmp += '<button class="delBtn">삭제</button>'
    			tmp += '<button class="modBtn">수정</button>'
    			tmp += '<button class="replyBtn">답글</button>'
    			tmp += '</li>'
    		})
    		
    		return tmp + "</ul>";
    	}
    </script>
</body>
</html>