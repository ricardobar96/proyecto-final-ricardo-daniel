import React, { useState } from 'react';
import Chat, { Message } from 'react-simple-chat';


export default function ChatHome() {
    const [messages, setMessages] = useState<Message[]>([
        {
            id: 1,
            text: 'Hello my friend!',
            createdAt: '2021-07-21 12:09:12',
            user: {
                id: 2,
                avatar: '../../API/images/fallout-4.jpg'
            }
        }
    ]);

    return (
        <Chat
            title="Jane Doe"
            user={{ id: 1 }}
            messages={messages}
            onSend={message => setMessages([...messages, message])}
        />
    )
}