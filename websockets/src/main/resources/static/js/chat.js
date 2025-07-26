var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').classList.toggle('hidden', !connected);
    document.getElementById('response').innerHTML = '';
}



function connect() {
    console.log("Connecting to /chat...");

    stompClient = new StompJs.Client({
        webSocketFactory: () => new SockJS('/chat'),
        debug: function (str) {
            console.log(str);
        },
        reconnectDelay: 5000, // auto-reconnect every 5s
        onConnect: function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);

            // Subscribe to topic
            stompClient.subscribe('/topic/messages', function (messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
            });
        },
        onStompError: function (frame) {
            console.error('Broker error: ' + frame.headers['message']);
            console.error('Details: ' + frame.body);
        }
    });

    stompClient.activate();
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.deactivate();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;

    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: '/app/chat',
            body: JSON.stringify({ 'from': from, 'text': text })
        });
    } else {
        console.error("Not connected to STOMP broker");
    }
}

function showMessageOutput(messageOutput) {
    const response = document.getElementById('response');
    const p = document.createElement('p');
    p.textContent = `${messageOutput.from}: ${messageOutput.text} (${messageOutput.time})`;
    response.appendChild(p);
    response.scrollTop = response.scrollHeight; // auto-scroll
}
