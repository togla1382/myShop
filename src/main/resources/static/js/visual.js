/**
 * 비주얼 이미지 컨트롤
 */

//var size=415;//이미지사이즈(400)+15

var marginLeft=415;
var basePos=marginLeft*6;
var speed=1000;
var timmer=3000;
var myTimeout;
var flag=false;
//ready()
$(function(){
	$(".visuals").css("margin-left", -basePos);
	start();
	
	$(".visuals, .btn").hover(stop, start);
	$(".btn").hover(function(){
		$(this).css({"opacity": 1});
	}, function(){
		$(this).css({"opacity": .15});
	});
	
	$(".next").click(move);
	$(".prev").click(prev);
});

//브라우저의 화면이 보여지냐 숨겨지냐에 따라 실행되는 이벤트
document.addEventListener("visibilitychange", function() {
	console.log( document.visibilityState );
	if(document.visibilityState=="hidden"){
		stop();
	}else if(document.visibilityState=="visible"){
		start();
	}
});

function start(){
	myTimeout = setTimeout(run, timmer);
}
function run(){
	console.log("타이머 시작");
	move();
	myTimeout = setTimeout(run, timmer);
}
function stop(){
	clearTimeout(myTimeout);
	console.log("타이머 멈춤");	
}

function move(){
	if(flag)return;//true(실행중) 이면 아래실행하지 않고 종료
	flag=true;
	var imgWrap=$(".visuals");
	//marginLeft += size;
	imgWrap.animate({marginLeft: -basePos-marginLeft},speed, function(){
		var lis=$("ol.visuals li");
		//var lastLi=lis.last();
		//var firstLi=lis.first();
		//lastLi.after(firstLi);
		//첫번째 li태그가 마지막 li태그뒤로 이동
		lis.last().after(lis.first());
		imgWrap.css("margin-left", -basePos);
		flag=false;
	});
}

function prev(){
	if(flag)return;//true(실행중) 이면 아래실행하지 않고 종료
	flag=true;
	var imgWrap=$(".visuals");
	imgWrap.animate({marginLeft: -basePos+marginLeft},speed,function(){
		//이미지 이동
		var lis=$("ol.visuals li");
		//첫번째 li태그가 마지막 li태그뒤로 이동
		lis.first().before(lis.last());
		imgWrap.css("margin-left", -basePos);
		flag=false;
	});
	
}

