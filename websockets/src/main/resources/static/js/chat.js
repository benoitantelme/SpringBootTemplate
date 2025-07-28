// Global variable to store STOMP client connection
var stompClient = null;

/**
 * Updates the UI based on the connection state.
 * - Enables/disables connect/disconnect buttons
 * - Shows or hides the chat card
 * - Clears chat messages when disconnected
 */
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;

    // Show or hide the chat message container
    const chatCard = document.querySelector('.chat-card');
    if (connected) {
        chatCard.classList.remove('hidden');
    } else {
        chatCard.classList.add('hidden');
    }

    // Clear message area when disconnecting
    document.getElementById('response').innerHTML = '';
}

/**
 * Establishes a connection to the WebSocket endpoint (/chat) using SockJS and STOMP.
 * - Subscribes to the /topic/messages channel to receive broadcast messages
 * - Sets up reconnect behavior and debug logging
 */
function connect() {
    console.log("Connecting to /chat...");

    // Create new STOMP client using SockJS
    stompClient = new StompJs.Client({
        webSocketFactory: () => new SockJS('/chat'),
        debug: function (str) {
            console.log(str);
        },
        reconnectDelay: 5000,
        onConnect: function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);

            // Subscribe to backend topic
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

/**
 * Disconnects from the WebSocket/STOMP connection
 * - Deactivates the client
 * - Resets UI state
 */
function disconnect() {
    if (stompClient !== null) {
        stompClient.deactivate();
    }
    setConnected(false);
    console.log("Disconnected");
}

/**
 * Sends a message to the server
 * - The message is published to the backend endpoint
 * - Message body contains username and content
 */
function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;

    if (stompClient && stompClient.connected) {
        // Publish message to backend
        stompClient.publish({
            destination: '/app/chat',
            body: JSON.stringify({ 'from': from, 'text': text })
        });
    } else {
        console.error("Not connected to STOMP broker");
    }
}

/**
 * User color assignment logic:
 * - Each user is mapped to a color index for consistent bubble color
 * - Cycles through 5 colors for different users
 */
const userColors = {};
let nextColorIndex = 1;
const totalColors = 5;

/**
 * Renders a message in the chat window with styled bubbles
 * - Distinguishes between current user and others
 * - Applies unique colors for other users
 * - Appends timestamp
 */
function showMessageOutput(messageOutput) {
    const response = document.getElementById('response');
    const currentUser = document.getElementById('from').value.trim();
    const username = messageOutput.from?.trim() || "Unknown";

    // Create wrapper div for message bubble
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message');

    if (username === currentUser) {
        messageDiv.classList.add('my-message');

        // Display sender name (white text for contrast)
        const senderSpan = document.createElement('span');
        senderSpan.textContent = username;
        senderSpan.style.color = "#ffffff"; // White name on blue bubble

        // Message text
        const textSpan = document.createElement('span');
        textSpan.textContent = `: ${messageOutput.text}`;

        // Timestamp
        const timestamp = document.createElement('span');
        timestamp.classList.add('timestamp');
        timestamp.textContent = messageOutput.time;

        // Append elements to bubble
        messageDiv.appendChild(senderSpan);
        messageDiv.appendChild(textSpan);
        messageDiv.appendChild(document.createElement('br'));
        messageDiv.appendChild(timestamp);

    } else {
        // Assign color if user not yet mapped
        if (!userColors[username]) {
            userColors[username] = nextColorIndex;
            nextColorIndex = (nextColorIndex % totalColors) + 1;
        }
        const colorIndex = userColors[username];

        // Apply CSS classes for bubble and username color
        messageDiv.classList.add('other-message', `user-color-${colorIndex}`);

        const senderSpan = document.createElement('span');
        senderSpan.classList.add(`user-color-${colorIndex}-name`);
        senderSpan.textContent = username;

        const textSpan = document.createElement('span');
        textSpan.textContent = `: ${messageOutput.text}`;

        const timestamp = document.createElement('span');
        timestamp.classList.add('timestamp');
        timestamp.textContent = messageOutput.time;

        // Append elements to bubble
        messageDiv.appendChild(senderSpan);
        messageDiv.appendChild(textSpan);
        messageDiv.appendChild(document.createElement('br'));
        messageDiv.appendChild(timestamp);
    }

    // Add message to chat and auto-scroll to bottom
    response.appendChild(messageDiv);
    response.scrollTop = response.scrollHeight;
}
