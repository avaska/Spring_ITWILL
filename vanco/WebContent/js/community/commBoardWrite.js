/**
 * write.js
 */

// 전역변수 선언
var xE = {};
var sel_files = [];
var in_files = [];
var index = 0;
var filesArr;
var sumSize = 0;
var fileList = document.getElementById("fileList");
// 페이지 로딩시 
document.body.onload = function() {
	
	/**
	 *	글 작성 에디터 세팅
	 */
	xE.w = document.getElementById("content2").contentWindow;
	xE.d = xE.w.document;
	xE.d.write("<!DOCTYPE html><html><head>" +
			"<style>" +
				".myimages{display:block; width:500px; border-radius: 5px; margin:0 auto;} " +
				"div{width:600px; margin:0 auto; text-align: center !important; } " +
				"body{width:600px; margin:0 auto; text-align: center !important; color:#999;}" +
			"</style>" +
			"</head><body></body></html>");
	xE.d.designMode = "on";

	window.URL = window.URL || window.webkitURL;

	/**
	 *	파일 업로더 세팅
	 */
	
	const fileSelect = document.getElementById("fileSelect"), 
	fileElem = document.getElementById("fileElem");

	fileSelect.addEventListener("click", function(e) {
		if (fileElem) {
			fileElem.click();
		}
		e.preventDefault(); // prevent navigation to "#"
	}, false);

	/**
	 *	뮤테이션옵저버(이상행동감지기) 세팅
	 */
	
	var target = xE.d.getElementsByTagName("body")[0];

	var observer = new MutationObserver(function(mutations) {
		mutations.forEach(function(mutation) {
			if (!mutation.removedNodes){
				return;
			}
			

			for (var i = 0; i < mutation.removedNodes.length; i++) {
				var node = mutation.removedNodes[i];
				console.log(node.id);
				if (node.className === "myimages") {
					isloading.start();
					while (xE.d.getElementById("upDiv" + node.id.substring(5))) {
						xE.d.getElementById("upDiv" + node.id.substring(5)).remove();
					}
					if(document.getElementById(node.id))
					document.getElementById(node.id).parentNode.remove();
					// 배열내의 파일값 삭제
					for (let i=0; i<sel_files.length; i++) {
						  if(sel_files[i].name === node.alt){
							  sel_files.splice(i,1);
						  }
						}
					
					isloading.stop();
				}
			}

		});
	});

	var config = {
			attributes : true,
			childList : true,
			characterData : true,
			subtree : true || null,
			attributeOldValue : true || null,
			characterDataOldValue : true || null,
		}; // 감시할 내용 설정
	
	observer.observe(target, config); // 감시할 대상 등록

}

// 윈도우 닫힐때
// 필요없는 URL 삭제
window.beforeunload = function() {
	filesArr.forEach(function(f) {
		window.URL.revokeObjectURL(f);
	});
}

/*
function deleteFile(imgNum) {
	alert(imgNum);
}
*/

// 파일 업로드 핸들러
function handleFiles(files) {
	
	// 업로드 파일 없이 취소되었을때
	if (!files.length) {
		
		// 오류 메시지
		document.getElementById("filemsg").innerHTML = "";
		document.getElementById("filemsg").innerHTML = "사진읍따";

	// 파일이 업로드 되었을 때
	} else {
		// 로딩 화면 시작
		isloading.start();
		
		// 메시지 초기화
		document.getElementById("filemsg").innerHTML = "";
		
		// 이미지 리스트 감쌀 <ul>태그 생성
		const list = document.createElement("ul");
		
		// 파일 배열로 받기
		filesArr = Array.prototype.slice.call(files);

		// <ul>태그 생성
		fileList.appendChild(list);
		
		// <!-- 다중 파일 사이즈 총량 계산기
		var lastSum = sumSize;
		filesArr.forEach(function(f) {
			sumSize += f.size;
			console.log(f.size);
		});
		
		if (sumSize > 10485760) {
			alert("사이즈가 초과하였습니다 \n 최대용량 : 10,485,760bytes 현재용량 : "+sumSize+"bytes");
			
			isloading.stop();
			sumSize = lastSum;
			return;
		}
		// 다중 파일 사이즈 총량 계산기  -->
		
		// 다중 파일 for문
		filesArr.forEach(function(f) {
			
			// li태그로 감싸기
			const li = document.createElement("li");
			li.id = "liImg" + index;
			li.className = "liImg";
			list.appendChild(li);
	
			// blob방식 img 태그 만들기
			const img = document.createElement("img");
			img.src = window.URL.createObjectURL(f);
			img.id = "upImg" + index;
			img.className = "upImg"
			img.alt = f.name;
			//img.height = 60;
	
			// 업로드 이미지 목록에 img 태그 삽입
			li.appendChild(img);
			
			// 에디터에 img삽입
			insertImageAction(index);
			
			// 파일 목록의 각각 파일 용량/삽입/삭제 출력
			const info = document.createElement("span");
			info.className="delBox";
			info.innerHTML //= f.name+ ": "+ f.size + " bytes "
						   //+ "<a href=\"javascript:void(0);\" onclick=\"insertImageAction("+ index +")\">삽입</a> "
						   = "<a class='delBtn' href=\"javascript:void(0);\" onclick=\"deleteImageAction("+ index +")\">삭제</a>";
			// span태그 삽입
			li.appendChild(info);
			
			// 배열에 업로드한 파일 담기
			in_files.push(f.name);
			sel_files.push(f);
			
			// 인덱스 값 조절
			index = document.getElementsByClassName("liImg").length;
		});
		
		// 로딩 화면 해제
		isloading.stop();
		preImgCss(); // 동적태그 css적용하기
	}
}


function preImgCss(){
	var li = $(".liImg"); 
	var upImg = $(".upImg");
	var delBox = $(".delBox");
	var delBtn = $(".delBtn");
		
		
	li.css('display','inline-block');
	delBox.css('display','block');
	delBtn.css({
		'margin-left':'35px',
		'color':'#999'});
	
	upImg.css({
		'width':'70px',
		'height':'70px',
		'border-radius':'7px',
		'margin-left':'15px',
		'margin-top':'15px'});
	
}



function deleteImageAction(index) {
	// 로딩화면 시작
	isloading.start();
	
	// 배열내의 파일값 삭제
	sel_files.splice(index, 1);
	
	// 이미지 검사 후 삭제
	var img_id = document.getElementById("liImg" + index);
	while (xE.d.getElementById("upImg" + index)) {
		xE.d.getElementById("upImg" + index).parentNode.remove();
	}
	img_id.remove();
	
	// 로딩 화면 해제
	isloading.stop();
}

function insertImageAction(index) {
	// 로딩화면 시작
	isloading.start();
	
	// 이미지 삽입
	var output = xE.d.getElementsByTagName("body")[0];
	var img_id = document.getElementById("upImg" + index);
	var cln = img_id.cloneNode(true);
	var div = document.createElement("div");
	var br1 = document.createElement("br");
	var br2 = document.createElement("br");
	var br3 = document.createElement("br");
	var br4 = document.createElement("br");
	
	div.className = "divImg";
	div.id = "upDiv" + index;
	cln.className = "myimages";
	//cln.height = 200;
	
	div.appendChild(cln);
	output.appendChild(br1);
	output.appendChild(br2);
	output.appendChild(div);	
	output.appendChild(br3);
	output.appendChild(br4);
		
	
	// 로딩 화면 해제
	isloading.stop();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function getImagesByAlt(alt) {
    var allImages = xE.d.getElementsByTagName("img");
    var images = [];
    for (var i = 0, len = allImages.length; i < len; ++i) {
        if (allImages[i].alt == alt) {
            images.push(allImages[i]);
        }
    }
    return images;
}

function filesListing() {
	
	// 로딩화면 시작
	isloading.start();
	
	// 가상 폼 생성
	var formData = new FormData();
	var moimNum = document.getElementById("moimNum").value;
	var writer = document.getElementById("writer").value;
	
	// 배열의 파일 값, 가상 폼의 input으로 넘길 for문
	for (var i = 0; i < sel_files.length; i++) {
		var name = "image_" + i;
		formData.append(name, sel_files[i]);
	}
	
	// 기타 값 input으로 전송
	formData.append("moimNum", moimNum);
	formData.append("image_count", sel_files.length);
	formData.append("writer",writer);
	
	// ajax
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		
		// ajax 로딩 완료
		if (this.readyState == 4 && this.status == 200) {
			
			// ajax로 넘어온 resonseText값 JSON으로 파싱
			var fileObj = JSON.parse(xhttp.responseText);
			
			// 출력할 에디터 output 선언
			var output = xE.d.getElementsByTagName("body")[0];
			
			// 업로드 된 이미지 파일 src 속성 변경
			for (var kk = 0; kk < fileObj.filesList.length; kk++) {
				
				getImagesByAlt(sel_files[kk].name)[0].src = "commUpload/" + fileObj.filesList[kk].filename;
				
				// 업로드된 파일 목록 다중 input값
				var inputS = document.createElement("input");
				inputS.type = "hidden";
				inputS.name = "files";
				inputS.value = fileObj.filesList[kk].fileNum;
				fileList.appendChild(inputS);

			}
			
			// 에디터 content값 textarea로 재설정
			var content2 = xE.d.getElementsByTagName("body")[0].innerHTML;
			document.forms["fr"].content.value = content2;
			
			return formCheck();
			
		}
	};
	xhttp.open("POST", "file.bo", false);
	xhttp.send(formData);

}

function formCheck() {

	// 체크할 값 변수 선언
	var title = document.forms["fr"].title.value;
	var writer = document.forms["fr"].writer.value;
	var content = document.forms["fr"].content.value;

	if (title == null || title == "") {
		alert('제목을 입력하세요');
		document.forms["fr"].title.focus();
		return false;
	}

	if (writer == null || writer == "") {
		alert('작성자를 입력하세요');
		document.forms["fr"].writer.focus();
		return false;
	}

	if (content == null || content == "") {
		alert('내용을 입력하세요');
		document.forms["fr"].content.focus();
		return false;
	}
}

// 로딩 화면 생성기
isloading = {
	start : function() {
		if (document.getElementById('wfLoading')) {
			return;
		}
		var ele = document.createElement('div');
		ele.setAttribute('id', 'wfLoading');
		ele.classList.add('loading-layer');
		ele.innerHTML = '<span class="loading-wrap"><span class="loading-text"><span>.</span><span>.</span><span>.</span></span></span>';
		document.body.append(ele);

		// Animation
		ele.classList.add('active-loading');
	},
	stop : function() {
		var ele = document.getElementById('wfLoading');
		if (ele) {
			ele.remove();
		}
	}
}