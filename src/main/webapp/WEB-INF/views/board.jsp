<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}" />
<c:set var="loginOutLink"
	value="${loginId=='' ? '/login/login' : '/login/logout'}" />
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고헌터</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: "Noto Sans KR", sans-serif;
}

a { text-decoration: none;  }

#logo {
	color:white;
    font-size: 18px;
    padding-left:40px; 
    margin-right:auto;
    display: flex;
}

.boardUl {
    list-style-type: none;
    height: 48px;
    width: 100%;
    background-color: #30426E;
    display: flex;
}

.boardUl > .boardLi {
    color: lightgray;
    height : 100%;
    width:90px;
    display:flex;
    align-items: center;
}

.boardUl > .boardLi > a {
    color: lightgray;
    margin:auto;
    padding: 10px;
    font-size:20px;
    align-items: center;
}

.boardUl > .boardLi > a:hover {
    color :white;
    border-bottom: 3px solid rgb(209, 209, 209);
}

.container {
	width: 50%;
	margin: auto;
}

.writing-header {
	position: relative;
	margin: 20px 0 0 0;
	padding-bottom: 10px;
	border-bottom: 1px solid #323232;
}

input {
	width: 100%;
	height: 35px;
	margin: 5px 0px 10px 0px;
	border: 1px solid #e9e8e8;
	padding: 8px;
	background: #f8f8f8;
	outline-color: #e6e6e6;
}

textarea {
	width: 100%;
	background: #f8f8f8;
	margin: 5px 0px 10px 0px;
	border: 1px solid #e9e8e8;
	resize: none;
	padding: 8px;
	outline-color: #e6e6e6;
}

.frm {
	width: 100%;
}

.btn {
	background-color: rgb(236, 236, 236); /* Blue background */
	border: none; /* Remove borders */
	color: black; /* White text */
	padding: 6px 12px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
	border-radius: 5px;
}

.btn:hover {
	text-decoration: underline;
}

.reUl {
    border:  1px solid rgb(235,236,239);
    border-bottom : 0;
}



#commentList {
    width : 100%;
    margin : auto;
}

#comment {
    width : 50%;
    margin : auto;
}

.comment-content {
    overflow-wrap: break-word;
}

.comment-bottom {
    font-size:9pt;
    color : rgb(97,97,97);
    padding: 8px 0 8px 0;
}

.comment-bottom > a {
    color : rgb(97,97,97);
    text-decoration: none;
    margin : 0 6px 0 0;
}

.comment-area {
    padding : 0 0 0 46px;
}

.commenter {
    font-size:12pt;
    font-weight:bold;
}

.commenter-writebox {
    padding : 15px 20px 20px 20px;
}

.comment-img {
    font-size:25px;
    position: absolute;
}

.comment-item {
    position:relative;
    background-color: #f9f9fa;
    list-style-type: none;
    border-bottom : 1px solid rgb(235,236,239);
    padding : 18px 18px 0 18px;
}

.up_date {
    margin : 0 8px 0 0;
}

#comment-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
}

#re-textarea {
    display: block;
    width: 100%;
    min-height: 17px;
    padding: 0 20px;
    border: 0;
    outline: 0;
    font-size: 13px;
    resize: none;
    box-sizing: border-box;
    background: transparent;
    overflow-wrap: break-word;
    overflow-x: hidden;
    overflow-y: auto;
}

#comment-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}



#btn-write-comment, #btn-write-reply { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-reply { 
    background-color: #eff0f2;
    margin-right : 10px;
}

#btn-write-modify { 
    color : #009f47;
    background-color: #e0f8eb;
}

#btn-cancel-modify { 
    margin-right : 10px;
}

#reply-writebox {
    display : none;
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#reply-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

#modify-writebox {
    background-color: white;
    border : 1px solid #e5e5e5;
    border-radius: 5px;
    margin : 10px;
}

#modify-writebox-bottom {
    padding : 3px 10px 10px 10px;
    min-height : 35px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}



.paging {
    color: black;
    width: 100%;
    text-align: center;
}

.page {
    color: black;
    text-decoration: none;
    padding: 6px;
    margin-right: 10px;
}

.paging-active {
    background-color: rgb(216, 216, 216);
    text-decoration: none;
    border-radius: 3px;
    padding: 6px;
    margin-right: 10px;
    color: rgb(24, 24, 24);
}

.paging-container {
    width:100%;
    height: 70px;
    margin-top: 50px;
    margin : auto;
}

</style>

</head>
<body>
	<div id="menu">
		<ul class="boardUl">
			<li class="boardLi" id="logo">중고 헌터</li>
			<li class="boardLi"><a href="<c:url value='/'/>">Home</a></li>
			<li class="boardLi"><a href="<c:url value='/board/list'/>">Board</a></li>
			<li class="boardLi"><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
			<li class="boardLi"><a href="<c:url value='/register/add'/>">Sign in</a></li>
			<li class="boardLi"><a href=""><i class="fa fa-search"></i></a></li>
		</ul>
	</div>
	<script>
	let msg = "${msg}"
    if(msg=="WRT_ERROR") alert("게시물 등록에 실패했습니다. 다시 시도해 주세요.");
	if(msg=="MOD_ERROR") alert("게시물 등록에 실패했습니다. 다시 시도해 주세요.");
</script>
	<div class="container">
		<h2 class="writing-header">게시물 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
		<form action="" id="form">
			<input type="hidden" name="bno" value="${boardDto.bno}"
				readonly="readonly"> <input type="text" name="title"
				value="<c:out value='${boardDto.title}'/>"
				placeholder=" 제목을 입력해 주세요."
				${mode=="new" ? '' : 'readonly="readonly"'}><br>
			<textarea name="content" id="" rows="20" placeholder=" 내용을 입력해 주세요."
				${mode=="new" ? '' : 'readonly="readonly"'}><c:out
					value="${boardDto.content}" /></textarea>
			<br>

			<c:if test="${mode eq 'new'}">
				<button type="button" id="writeBtn" class="btn btn-write">
					<i class="fa fa-pencil"></i>등록
				</button>
			</c:if>
			<c:if test="${mode ne 'new'}">
				<button type="button" id="writeNewBtn" class="btn btn-write">
					<i class="fa fa-pencil"></i>글쓰기
				</button>
			</c:if>
			<c:if test="${boardDto.writer eq loginId}">
				<button type="button" id="modifyBtn" class="btn btn-modify">
					<i class="fa fa-edit"></i>수정
				</button>
				<button type="button" id="removeBtn" class="btn btn-remove">
					<i class="fa fa-trash"></i>삭제
				</button>
			</c:if>
			<button type="button" id="listBtn" class="btn btn-list">
				<i class="fa fa-bars"></i>목록
			</button>
		</form>
	</div>
 <div id="comment">
 <h2 class="writing-header">댓글</h2>
 	<div id="commentList">
 		
     </div>   
         <div class="paging-container">
            <div class="paging">
            </div>
        </div>
        <div id="comment-writebox">
            <div class="commenter commenter-writebox">${id}</div>
            <div class="comment-writebox-content">
                <textarea name="comment" id="re-textarea" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
            </div>
            <div id="comment-writebox-bottom">
                <div class="register-box">
                    <a href="#" class="btn" id="btn-write-comment">등록</a>
                </div>
            </div>
        </div>
    </div>
    <div id="reply-writebox">
        <div class="commenter commenter-writebox">${id}</div>
        <div class="reply-writebox-content">
            <textarea name="re-comment" id="rere-textarea" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
        </div>
        <div id="reply-writebox-bottom">
            <div class="register-box">
                <a href="#" class="btn" id="btn-write-reply">등록</a>
                <a href="#" class="btn" id="btn-cancel-reply">취소</a> 
            </div>
        </div>
    </div>
    <div id="modalWin" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>
            <h2> | 댓글 수정</h2>
            <div id="modify-writebox">
                <div class="commenter commenter-writebox"></div>
                <div class="modify-writebox-content">
                    <textarea name="mod-comment" id="mod-textarea" cols="30" rows="5" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div id="modify-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="btn-write-modify">등록</a>
                    </div>
                </div>
            </div>
            </p>
        </div>
    </div>
<script> // 댓글
        let id = 'asdf2';
        let bno = ${boardDto.bno};
        let page = 11;
        let pageSize=10;

        let showList = function(bno){
   		 $.ajax({
                type:'GET',       // 요청 메서드
                url: '/comments/'+bno+'/'+page+'/'+pageSize,  // 요청 URI  
                success : function(result){
                   $("#commentList").html(toHtmlComment(result.list));
                   $(".paging").html(toHtmlPaging(result.ph));// 서버로부터 응답이 도착하면 호출될 함수
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
   	}
        
       
        
        let addZero = function(value=1){
            return value > 9 ? value : "0"+value;
        }

        // datetime 을 yyyy.mm.dd HH:MM:ss 형으로 바꾸기
        let dateToString = function(ms=0) {
            let date = new Date(ms);

            let yyyy = date.getFullYear();
            let mm = addZero(date.getMonth() + 1);
            let dd = addZero(date.getDate());

            let HH = addZero(date.getHours());
            let MM = addZero(date.getMinutes());
            let ss = addZero(date.getSeconds());          

            return yyyy+"."+mm+"."+dd+ " " + HH + ":" + MM + ":" + ss;            
        }

        $(document).ready(function(){
        	showList(bno);
        	
        	$(".paging").on("click", "a", function(e){ // 답글쓰기.등록
        		e.preventDefault();
            	var target = e.target;
            	var page = target.getAttribute("href");
            	
            	$.ajax({
                    type:'GET',       // 요청 메서드
                    url: '/comments/'+bno+'/'+page+'/'+pageSize,  // 요청 URI  
                    success : function(result){
                       $("#commentList").html(toHtmlComment(result.list));
                       $(".paging").html(toHtmlPaging(result.ph));// 서버로부터 응답이 도착하면 호출될 함수
                    },
                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                }); // $.ajax()
            })
        	
        	$("#btn-write-comment").click(function(){ // 새 댓글 등록
            	let comment = $("textarea[name=comment]").val();
            	
            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("textarea[name=comment]").focus()
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
                	  	$("textarea[name=comment]").val("");
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
            })
            
            $("#commentList").on("click", ".btn-write", function(e){ // 답글쓰기
            	let target = e.target;
                let cno = target.getAttribute("data-cno")
                let bno = target.getAttribute("data-bno")
                let pcno = target.getAttribute("data-pcno")
            	// 1. reply 폼을 답글 밑으로 옮기고
            	let repForm = $("#reply-writebox");
                repForm.appendTo($("li[data-cno="+cno+"]"));
            	// 2. 답글을 입력할 폼을 보여줌
                repForm.css("display", "block");
                repForm.attr("data-pcno", pcno);
                repForm.attr("data-bno",  bno);
                
            });
        	
        	$("#commentList").on("click", "#btn-write-reply", function(){ // 답글쓰기.등록
            	let comment = $("textarea[name=re-comment]").val();
            	let pcno = $("#reply-writebox").parent().attr("data-pcno");
            	let repForm = $("#reply-writebox");
            	
            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("textarea[name=re-comment]").focus()
            		return;
            	}
            	 $.ajax({
                     type:'POST',       // 요청 메서드
                     url: '/comments?bno='+bno,  // 요청 URI /comments?bno=1081
                     headers : { "content-type": "application/json"}, // 요청 헤더
                     data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                     success : function(result){
                    	 alert(result);
                    	 repForm.appendTo($("li[data-pcno="+pcno+"]"));
                	  	 showList(bno);
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
            })
            
            $("#btn-cancel-reply").click(function(e){ // 답글쓰기.취소
                $("#reply-writebox").css("display", "none");
            });
        	
        	$("#commentList").on("click", "a.btn-delete", function(e){ // 삭제
        		if (!confirm("정말로 삭제하시겠습니까?")) return;
        		let target = e.target;
                let cno = target.getAttribute("data-cno")
                let bno = target.getAttribute("data-bno")
            	
            	 $.ajax({
                     type:'DELETE',       // 요청 메서드
                     url: '/comments/'+cno+'?bno='+bno,  // 요청 URI /comments?bno=1081
                     success : function(result){
                    	 alert(result);
                	  	 showList(bno);
                     },
                     error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                 }); // $.ajax()
            })

            $("#commentList").on("click", "a.btn-modify", function(e){ // 수정
                let target = e.target;
                let cno = target.getAttribute("data-cno");
                let bno = target.getAttribute("data-bno");
                let pcno = target.getAttribute("data-pcno");
                let li = $("li[data-cno="+cno+"]");
                let commenter = $(".commenter", li).first().text();
                let comment = $(".comment-content", li).first().text();

                $("#modalWin .commenter").text(commenter);
                $("#modalWin textarea").text(comment);
                $("#btn-write-modify").attr("data-cno", cno);
                $("#btn-write-modify").attr("data-pcno", pcno);
                $("#btn-write-modify").attr("data-bno", bno);

                // 팝업창을 열고 내용을 보여준다.
                $("#modalWin").css("display","block");
            });


            $("#btn-write-modify").click(function(){ // 수정.등록
                // 1. 변경된 내용을 서버로 전송
            	let cno = $(this).attr("data-cno");
            	let comment = $("textarea[name=mod-comment]").val();

            	if(comment.trim()==''){
            		alert("댓글을 입력해주세요.");
            		$("textarea[name=mod-comment]").focus()
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
                // 2. 모달 창을 닫는다. 
                $(".close").trigger("click");
            });

            $(".close").click(function(){
                $("#modalWin").css("display","none");
            });
        });
        
        let toHtmlComment = function(comments) {
    		let tmp = '<ul class="reUl">';
    		comments.forEach(function(comment) {
    			tmp += '<li class="comment-item" data-cno=' + comment.cno
    			tmp += ' data-pcno=' + comment.pcno
    			tmp += ' data-bno=' + comment.bno + '>'
    			if(comment.cno!=comment.pcno)
    				tmp += 'ㄴ'
    			tmp += '<span class="comment-img">'
    			tmp += '<i class="fa fa-user-circle" aria-hidden="true"></i>'
    			tmp += '</span>'
    			tmp += '<div class="comment-area">'
    			tmp += '<div class="commenter">' +comment.commenter+ '</div>'
    			tmp += '<div class="comment-content">' +comment.comment+ '</div>'
    			tmp += '<div class="comment-bottom">'
    			
    			tmp += '<span class="up_date">' +dateToString(comment.up_date)+ '</span>'
    			
    			tmp += '<a href="#" class="btn-write" data-cno=' + comment.cno
    			tmp += ' data-bno=' + comment.bno
    			tmp += ' data-pcno=' + comment.pcno + '>답글쓰기</a>'
    			
    			tmp += '<a href="#" class="btn-modify" data-cno=' + comment.cno
    			tmp += ' data-bno=' + comment.bno
    			tmp += ' data-pcno=' + comment.pcno + '>수정</a>'	
    			
    			tmp += '<a href="#" class="btn-delete" data-cno=' + comment.cno
    			tmp += ' data-bno=' + comment.bno
    			tmp += ' data-pcno=' + comment.pcno + '>삭제</a>'	
    			tmp += '</div>'
    			tmp += '</div>'
    			tmp += '</li>'
    				
    		})
    		
    		  return tmp + '</ul>';
    		  
    		
    	}
        
        let toHtmlPaging = function(ph) { // 댓글 페이징
        	let tmp = "";
        	if(ph.showPrev){
        		tmp += "<a class=page href="+(ph.beginPage-1)+">&lt;</a>";
        	}
        	for (var i=ph.beginPage, len=ph.endPage; i<=len; i++){
        		var tmpClass = ph.page == i? 'paging-active' : 'page';
        		tmp += "<a class=" +tmpClass+ " href="+i+">"+i+"</a>";
        	}
        	if(ph.showNext){
        		tmp += "<a class=page href="+(ph.endPage+1)+">&gt;</a>";
        	}    
         return tmp;
        	
        		
        	
        
        }
        
       
    </script>
<script> // 게시판
	$(document).ready(function(){// main
		 let formCheck = function() {
		      let form = document.getElementById("form");
		      if(form.title.value=="") {
		        alert("제목을 입력해 주세요.");
		        form.title.focus();
		        return false;
		      }
		      if(form.content.value=="") {
		        alert("내용을 입력해 주세요.");
		        form.content.focus();
		        return false;
		      }
		      return true;
		    }
	
		$('#listBtn').on("click", function(){
			location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
		});
		
		$("#writeNewBtn").on("click", function(){
		      location.href="<c:url value='/board/write'/>";
		    });
		
		$('#writeBtn').on("click", function(){
			let form = $('#form');
			form.attr("action", "<c:url value='/board/write'/>");
			form.attr("method", "post");
			form.submit();
		});
		
		$('#modifyBtn').on("click", function(){
			// 1. 읽기 상태이면 수정 상태로 변경
			let form = $('#form');
			let isReadOnly = $("input[name=title]").attr('readonly');
			
			if (isReadOnly=='readonly') {
				$("input[name=title]").attr('readonly', false);
				$("textarea").attr('readonly', false);
				$("#modifyBtn").html("등록");
				$("h2").html("게시물 수정");
				return;
			}
			
			// 2. 수정 상태이면, 수정된 내용을 서버로 전송
			form.attr("action", "<c:url value='/board/modify'/>");
			form.attr("method", "post");
			form.submit();
		});
		
		$('#removeBtn').on("click", function(){
			if (!confirm("정말로 삭제하시겠습니까?")) return;
			let form = $('#form');
			form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
			form.attr("method", "post");
			form.submit();
		});
	});
</script>
</body>
</html>