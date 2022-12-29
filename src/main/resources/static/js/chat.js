/**
 * 
 */
var stompClient = null;
function openChat(el){
	$(el).hide();
	$("#chat-disp").show();
	connect();
}
function showMessage(message) {
    $("#chat-content").append(message);
}

function connect() {
    var socket = new SockJS('/my-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        //브라우저에서 메시지를 수신하려면 STOMP 클라이언트가 먼저 대상을 구독
        //subscribe()방법을 사용하여 대상에 가입
        //2개의 필수 인수를 사용. destination목적지에 해당하는 문자열, callback,
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showMessage(JSON.parse(greeting.body).message);
        });
        
        stompClient.send("/app/hello", {}, JSON.stringify({'content': 'guest'}));
        //stompClient.subscribe('/topic/message', onmessage);
    });
}
