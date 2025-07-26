var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;

    // Show/hide chat card
    const chatCard = document.querySelector('.chat-card');
    if (connected) {
        chatCard.classList.remove('hidden');
    } else {
        chatCard.classList.add('hidden');
    }

    document.getElementById('response').innerHTML = '';
}

function connect() {
    console.log("Connecting to /chat...");

    stompClient = new StompJs.Client({
        webSocketFactory: () => new SockJS('/chat'),
        debug: function (str) {
            console.log(str);
        },
        reconnectDelay: 5000,
        onConnect: function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);

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

// Map user -> color index
const userColors = {};
let nextColorIndex = 1;
const totalColors = 5;

// Display message with bubbles
function showMessageOutput(messageOutput) {
    const response = document.getElementById('response');
    const currentUser = document.getElementById('from').value.trim();
    const username = messageOutput.from?.trim() || "Unknown";

    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message');

    if (username === currentUser) {
        // Current user bubble
        messageDiv.classList.add('my-message');

        const senderSpan = document.createElement('span');
        senderSpan.textContent = username;
        senderSpan.style.color = "#ffffff"; // white name on blue bubble

        const textSpan = document.createElement('span');
        textSpan.textContent = `: ${messageOutput.text}`;

        const timestamp = document.createElement('span');
        timestamp.classList.add('timestamp');
        timestamp.textContent = messageOutput.time;

        messageDiv.appendChild(senderSpan);
        messageDiv.appendChild(textSpan);
        messageDiv.appendChild(document.createElement('br'));
        messageDiv.appendChild(timestamp);

    } else {
        // Assign gray color if new user
        if (!userColors[username]) {
            userColors[username] = nextColorIndex;
            nextColorIndex = (nextColorIndex % totalColors) + 1;
        }
        const colorIndex = userColors[username];

        messageDiv.classList.add('other-message', `user-color-${colorIndex}`);

        const senderSpan = document.createElement('span');
        senderSpan.classList.add(`user-color-${colorIndex}-name`);
        senderSpan.textContent = username;

        const textSpan = document.createElement('span');
        textSpan.textContent = `: ${messageOutput.text}`;

        const timestamp = document.createElement('span');
        timestamp.classList.add('timestamp');
        timestamp.textContent = messageOutput.time;

        messageDiv.appendChild(senderSpan);
        messageDiv.appendChild(textSpan);
        messageDiv.appendChild(document.createElement('br'));
        messageDiv.appendChild(timestamp);
    }

    response.appendChild(messageDiv);
    response.scrollTop = response.scrollHeight;
}